package model.game;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Coordinate;
import model.Fighter;
import model.Ship;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.InvalidSizeException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;

public class GameShipPreTest {

	GameShip gameShip;
	List<Fighter>fleet ;
	GameBoard gameBoard;
	
	@Before
	public void setUp() throws Exception {
		 gameShip = new GameShip("Lanzadera T-4a", Side.IMPERIAL);
		 Fighter.resetNextId();
	}

	/* Se comprueba que el constructor copia de GameShip es correcto y que
	   GameShip es una clase derivada de Ship (no se han duplicado los atributos)
	 */
	@Test
	public void testGameShip() {
		assertEquals ("Lanzadera T-4a", gameShip.getName());
		assertEquals (Side.IMPERIAL, gameShip.getSide());
		assertEquals (0,  gameShip.getWins());
		assertEquals (0,  gameShip.getLosses());
		fleet = (List<Fighter>) gameShip.getFleetTest();
		assertNotNull (fleet);
		
		if (!(gameShip instanceof Ship)) {
			fail("GameShip devería ser una clase derivada de Ship");
		}
	}

	/* Se comprueba que isFleetDestroyed devuelve true si no hay cazas en
	 * la nave.
	 */
	@Test
	public void testIsFleetDestroyedEmpty() {
		assertTrue(gameShip.isFleetDestroyed());
	}
	
	/* Añade fighters a un GameShip. Luego destruyelos todos y comprueba que 
	 * isFleetDestroyed devuelve true.
	 */
	@Test
	public void testIsFleetDestroyedAllDestroyed() {
		gameShip.addFighters("10/TIEFighter:35/TIEInterceptor:5/TIEBomber");
		
		for (Fighter f : gameShip.getFleetTest()) {
			f.addShield(-3000);
		}
		
		assertTrue(gameShip.isFleetDestroyed());
	}
	
	
	/* Añade cazas a una nave. Destruye todos menos uno y comprueba que 
	 * isFleetDestroyed() devuelve false
	 */
	@Test
	public void testIsFleetDestroyedNotAllDestroyed() {
		gameShip.addFighters("10/TIEFighter:35/TIEInterceptor:5/TIEBomber");
		List<Fighter> fs = gameShip.getFleetTest();
		
		for (int i = 0; i < fs.size()-1; i++) {
			fs.get(i).addShield(-3000);
		}
		
		assertFalse(gameShip.isFleetDestroyed());
	}
	
	/* Se comprueba que se obtiene una lista vacía de una nave sin cazas. Luego se
	 * añaden cazas, se destruyen todos y se comprueba que se sigue devolviendo una
	 * lista vacía.
	 */
	@Test
	public void testGetFightersIdListEmpty() {
		List<Integer> l = gameShip.getFightersId("");
		assertTrue (l.isEmpty());
		gameShip.addFighters("10/TIEFighter:35/TIEInterceptor:5/TIEBomber");
		for (Fighter f : gameShip.getFleetTest()) {
			f.addShield(-300);
		}
		l = gameShip.getFightersId("");
		assertTrue (l.isEmpty());
	}

	/* Añade cazas a un Ship. Comprueba que al invocar a getFightersId("ship") 
	 * se devuelve una lista con los 'id' de todos los cazas del la nave. 
	 * Además comprueba que al invocar a getFightesId("board") se devuelve una lista 
	 * vacía.
	 */
	@Test
	public void testGetFightersIdListNotEmpty1()  {
		List<Integer> correctos = new ArrayList<>();
		
		gameShip.addFighters("10/TIEFighter:35/TIEInterceptor:5/TIEBomber");	
		List<Integer> l = gameShip.getFightersId("ship");
		List<Integer> l2 = gameShip.getFightersId(null);
		
		for (int i = 1; i <= gameShip.getFleetTest().size(); i++) {
			correctos.add(i);
		}
		
		if (correctos.size() != l.size()) {
			fail("El tamaño debería de ser el mismo.");
		}
		
		for (int j = 0; j < l.size(); j++) {
			assertEquals(correctos.get(j), l.get(j));
		}
		
		if (correctos.size() != l2.size()) {
			fail("El tamaño debería de ser el mismo.");
		}
		
		for (int k = 0; k < l2.size(); k++) {
			assertEquals(correctos.get(k), l2.get(k));
		}
		
		assertEquals(0, gameShip.getFightersId("board").size());
	}
	
	/* Se añaden cazas a un Ship. Se añaden todos a un tablero. Se comprueba que al invocar a  getFightersId("board") 
	 * se devuelve una lista con los id de todos los cazas del la nave.
	 * Además se comprueba que getFightersId("ship") devuelve una lista vacía
	 */
	@Test
	public void testGetFightersIdListNotEmpty2() throws InvalidSizeException, FighterAlreadyInBoardException, OutOfBoundsException  {
		gameShip.addFighters("10/TIEFighter:35/TIEInterceptor:5/TIEBomber");	
		fleet = gameShip.getFleetTest();
		gameBoard = new GameBoard(fleet.size());
		List<Integer> correctos = new ArrayList<>();

		for (int i = 0; i < fleet.size(); i++) {
			gameBoard.launch(new Coordinate(i, 0), fleet.get(i));
		}
		
		List<Integer> l = gameShip.getFightersId("board");
		
		for (int i = 1; i <= fleet.size(); i++) {
			correctos.add(i);
		}
		
		if (correctos.size() != l.size()) {
			fail("El tamaño debería de ser el mismo.");
		}
		
		for (int j = 0; j < l.size(); j++) {
			assertEquals(correctos.get(j), l.get(j));
		}
		
		assertEquals(0, gameShip.getFightersId("ship").size());
	}
	
	/* Añade cazas a un Ship. Añade algunos a un tablero. Destruye otros. 
	 * Comprueba que al invocar a:
	 *  - getFightersId("board") se devuelve una lista solo con los que realmente
	 * 		están en el tablero. 
     *  - getFightersId("ship") se devuelve una lista solo con los que no están en el tablero y no están destruídos.
     *  - getFightersId("") se devuelve una lista con todos los no destruídos.
	 */
	//TODO
	@Test
	public void testGetFightersIdListNotEmpty3() throws InvalidSizeException, FighterAlreadyInBoardException, OutOfBoundsException  {
		List<Integer> correctosShip = new ArrayList<>();
		List<Integer> correctosBoard = new ArrayList<>();
		List<Integer> correctosGeneral = new ArrayList<>();
		List<Integer> ship;
		List<Integer> board;
		List<Integer> general;
		gameShip.addFighters("10/TIEFighter:35/TIEInterceptor:5/TIEBomber");	
		fleet = gameShip.getFleetTest();
		gameBoard = new GameBoard(fleet.size());
		
		// Se añaden al board. 
		for (int i = 0; i < 5; i++) {
			gameBoard.launch(new Coordinate(i, 0), fleet.get(i));
			correctosBoard.add(i+1);
		}
		
		for (int i = 10; i < 25; i++) {
			fleet.get(i).addShield(-3000);	
		}
		
		ship = gameShip.getFightersId("ship");
		board = gameShip.getFightersId("board");
		general = gameShip.getFightersId("");
		
		// Se añaden los ids a correctos ship.
		for (int i = 0; i < fleet.size(); i++) {
			if (!(i >= 0 && i <= 4) && !(i >= 10 && i <= 24)) {
				correctosShip.add(i+1);
			}
		}
		
		for (int i = 0; i < fleet.size(); i++) {
			if (!(i >= 10 && i <= 24)) {
				correctosGeneral.add(i+1);
			}
		}
		
		if (correctosShip.size() != ship.size()) {
			fail("Deberían tener el mismo tamaño.");
		}
		
		for (int k = 0; k < ship.size(); k++) {
			assertEquals(correctosShip.get(k), ship.get(k));
		}
		
		if (correctosBoard.size() != board.size()) {
			fail("Deberían tener el mismo tamaño.");
		}
		
		for (int k = 0; k < board.size(); k++) {
			assertEquals(correctosBoard.get(k), board.get(k));
		}
		
		
		if (correctosGeneral.size() != general.size()) {
			System.out.println(general.size());
			fail("Deberían tener el mismo tamaño.");
		}
		
		for (int k = 0; k < board.size(); k++) {
			assertEquals(correctosGeneral.get(k), board.get(k));
		}

		
	}
	
	/* Añade cazas a un GameShip e intenta poner uno, con launch, con una id que no existe. 
	 * Se comprueba que se lanza la excepción WrongFighterIdException y que no lo ha puesto.
	 * Luego destruye uno del GameShip e intenta ponerlo en el tablero. Comprueba que
	 * también se lanza la excepción WrongFighterIdException y que tampoco se ha puesto.
	 */
	//TODO
	@Test
	public void testLaunchWrongFighterIdException() throws InvalidSizeException {
		gameShip.addFighters("4/TIEFighter:3/TIEInterceptor:6/TIEBomber");
		Coordinate c=new Coordinate(4,3);
		fleet = gameShip.getFleetTest();
		gameBoard = new GameBoard(fleet.size());
		try {
			gameShip.launch(20, c, gameBoard);
			fail("ERROR: Debió lanzar la excepción WrongFighterIdException");
		} catch (WrongFighterIdException e1) {
			assertTrue(e1.getMessage().startsWith("ERROR:"));
			assertNull(gameBoard.getFighter(c));
		} catch (Exception e2) {
			fail("ERROR: No debió lanzar la excepción "+e2.getClass().getSimpleName());
		}
		
		gameShip.getFleetTest().get(0).addShield(-3000);
		
		try {
			gameShip.launch(1, c, gameBoard);
			fail("ERROR: Debió lanzar la excepción WrongFighterIdException");
		} catch (WrongFighterIdException e1) {
			assertTrue(e1.getMessage().startsWith("ERROR:"));
			assertNull(gameBoard.getFighter(c));
		} catch (Exception e2) {
			fail("ERROR: No debió lanzar la excepción "+e2.getClass().getSimpleName());
		}
	}
	
	
	/* Añaden cazas a un GameShip e intenta poner uno, con launch, en una coordenada
	 * fuera del tablero. 
	 * Comprueba que se lanza la excepción OutOfBoundsException.
	 */
	@Test(expected=OutOfBoundsException.class)
	public void testLaunchOutOfBoundsException() throws InvalidSizeException, FighterAlreadyInBoardException, OutOfBoundsException {
		gameShip.addFighters("4/TIEFighter:3/TIEInterceptor:6/TIEBomber");
		
		gameBoard = new GameBoard(6);
		
		gameBoard.launch(new Coordinate(7,7), gameShip.getFleetTest().get(0));
	}
	
	/* Se añaden cazas a un GameShip y se pone a patrullar a uno de ellos en un
	 * tablero. Como no está en él, se comprueba que se lanza la excepción 
	 * FighterNotInBoardException y no otra y que el mensaje empieza con la
	 * cadena "ERROR:"
	 */
	@Test
	public void testPatrolNotInBoardException() throws InvalidSizeException {
		gameShip.addFighters("4/TIEFighter:3/TIEInterceptor:6/TIEBomber");
		fleet = gameShip.getFleetTest();
		gameBoard = new GameBoard(fleet.size());
		try {
			gameShip.patrol(13, gameBoard);
			fail("ERROR: Debió lanzar la excepción FighterNotInBoardException");
		} catch (FighterNotInBoardException e1) {
			assertTrue(e1.getMessage().startsWith("ERROR:"));
		} catch (Exception e2) {
			fail("ERROR: No debió lanzar la excepción "+e2.getClass().getSimpleName());
		}	
	}

	/* Añade cazas a un GameShip y pon a patrullar a uno con una id que
	 * no existe en el tablero. Como no está en él, comprueba que se lanza 
	 * la excepción WrongFighterIdException y no otra.
	 */
	@Test(expected=WrongFighterIdException.class)
	public void testPatrolWrongFighterIdException() throws InvalidSizeException, FighterNotInBoardException, WrongFighterIdException {
		gameShip.addFighters("4/TIEFighter:3/TIEInterceptor:6/TIEBomber");
		gameBoard = new GameBoard(10);
		
		gameShip.patrol(67, gameBoard);
	}
	
	/* Añade cazas a un GameShip y pon un TIEInterceptor en un tablero.
	 * Añade una mejora de 97 al caza del tablero. Comprueba que ya no está en
	 * el tablero y que el ataque ahora es de 133 y el escudo de 109.
	 */
	@Test
	public void testImproveFighter() throws WrongFighterIdException, FighterAlreadyInBoardException, OutOfBoundsException, InvalidSizeException {
		gameShip.addFighters("4/TIEFighter:3/TIEInterceptor:6/TIEBomber");
		fleet = gameShip.getFleetTest();
		gameBoard = new GameBoard(fleet.size());
		
		gameBoard.launch(new Coordinate(1, 0), gameShip.getFleetTest().get(5));
		
		gameShip.improveFighter(6, 97, gameBoard);
		
		assertNull(gameShip.getFleetTest().get(5).getPosition());
		
		assertEquals(133, gameShip.getFleetTest().get(5).getAttack());
		assertEquals(109, gameShip.getFleetTest().get(5).getShield());
	}
	
	/* Se añaden cazas a un GameShip. Se intenta mejorar uno de los cazas del GameShip que
	 * no está en tablero alguno. Se comprueba que ha existido la mejora en dicho caza.
	 * Se intenta mejorar un id de un caza que no existe. Se comprueba que se lanza la excepción
	 * WrongFighterIdException y que lanza el mensaje con el inicio de 'ERROR:'
	 */
	@Test
	public void testImproveFighterExceptions() throws FighterAlreadyInBoardException, OutOfBoundsException, InvalidSizeException {
		gameShip.addFighters("4/TIEFighter:3/TIEInterceptor:6/TIEBomber");
		fleet = gameShip.getFleetTest();
		gameBoard = new GameBoard(fleet.size());	
		try {
			gameShip.improveFighter(6, 97, gameBoard);
		} catch (WrongFighterIdException e) {
			fail("ERROR: No debió lanzar la excepción "+e.getClass().getSimpleName());
		}
		assertEquals(133, gameShip.getFleetTest().get(5).getAttack());
		assertEquals(109, gameShip.getFleetTest().get(5).getShield());
		
		try {
			gameShip.improveFighter(89, 97, gameBoard);
		} catch (WrongFighterIdException e) {
			assertTrue(e.getMessage().startsWith("ERROR:"));
		}
	}
	
	/* Realiza las comprobaciones de los parámetros null en launch, patrol e improveFighter
	 * de GameShip */
	@Test
	public void testRequireNonNull() throws WrongFighterIdException, FighterAlreadyInBoardException, OutOfBoundsException, InvalidSizeException, FighterNotInBoardException  {
		
		try {
			gameShip.launch(2, null, new GameBoard(10));
			fail("ERROR: Debió lanzar NullPointerException");
		}catch (NullPointerException e) {}
		try {
			gameShip.launch(2, new Coordinate(3,2), null);
			fail("ERROR: Debió lanzar NullPointerException");
		}catch (NullPointerException e) {}
		
		try {
			gameShip.improveFighter(2, 2, null);
			fail("ERROR: Debió lanzar NullPointerException");
		}catch (NullPointerException e) {}
		try {
			gameShip.patrol(2, null);
			fail("ERROR: Debió lanzar NullPointerException");
		}catch (NullPointerException e) {}
	}

}
