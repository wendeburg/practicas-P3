package model.game.score;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Fighter;
import model.FighterFactory;
import model.Ship;
import model.Side;
import model.game
.GameShip;public class DestroyedFightersScorePreTest {
    Fighter tieBomber, tieInterceptor, tieFighter, xWing, yWing, aWing;
    GameShip imperialShip, rebelShip;
    Score<Fighter> scRebel, scImperial;
	@Before
	public void setUp() throws Exception {
		imperialShip = new GameShip("ImperialShip",Side.IMPERIAL);
		rebelShip = new GameShip("RebelShip",Side.REBEL);
		tieBomber = FighterFactory.createFighter("TIEBomber", imperialShip);
		tieInterceptor = FighterFactory.createFighter("TIEInterceptor", imperialShip);
		tieFighter = FighterFactory.createFighter("TIEFighter", imperialShip);
		xWing = FighterFactory.createFighter("XWing", rebelShip);
		yWing = FighterFactory.createFighter("YWing", rebelShip);
		aWing = FighterFactory.createFighter("AWing", rebelShip);
		scImperial = new DestroyedFightersScore(Side.IMPERIAL);
		scRebel = new DestroyedFightersScore(Side.REBEL);
		
	}

	/* Test del constructor DestroyedFighterScore. Se comprueba que es
	 * un tipo de Score y que se inicia a 0
	 */
	@Test
	public void testDestroyedFightersScore() {
		assertTrue(scImperial instanceof Score);
		assertEquals(0, scImperial.getScore());
	}

    /* Se comprueba que los valores null en el método score no incrementan
     * los marcadores.
     */
	@Test
	public void testScoreNullFighter() {
		scImperial = new DestroyedFightersScore(Side.IMPERIAL);
		tieBomber=tieInterceptor=tieFighter=null;
		scImperial.score(tieBomber);
		assertEquals(0,scImperial.getScore());
		scImperial.score(tieInterceptor);
		assertEquals(0,scImperial.getScore());
		scImperial.score(tieFighter);
		assertEquals(0,scImperial.getScore());
	}
	
		
	/* Inicialmente se comprueba que el marcador de scRebel es igual a sí mismo
	 * y menor que el de scImperial (aun teniendo los mismos score)
	 * Aplica sobre uno de ellos con score, un Fighter que incremente su puntuación.
	 * Comprueba ahora que el que ha aumentado, si es el que invoca a compareTo da un
	 * valor negativo y si es el menor el que lo invoca, da un valor positivo.
	 */
	//TODO
	@Test
	public void testCompareTo() {
		assertTrue(scRebel.compareTo(scRebel)==0);
		assertTrue(scRebel.compareTo(scImperial)>0);
		fail("Termina el test");
		
	}

	/* Se pasan varios Fighters sucesivamente al DestroyedFighterScore scImperial mediante
	 * el método score.
	 * Comprueba con getScore() que  los valores que se van obteniendo se van 
	 * acumulando sucesivamente. 
	 */
	@Test
	public void testScoreCraft() {
		assertEquals(0,scImperial.getScore());
		scImperial.score(xWing);
		assertEquals(210,scImperial.getScore());
		fail("Sigue aplicando score a scImperial con varios Fighters y comprobando "
				+ "que los valores son correctos");
	}


	/* Se comprueba toString sobre el DestroyedFighterScore scRebel y se comprueba que
	 * inicialmente es: "Player REBEL: 0"
	 * Aplica el método score sobre scRebel varias veces con distintos tipos de Fighter.
	 * Comprueba a la vez que la salida va cambiando de valor.
	 */
	//TODO
	@Test
	public void testToString() {
			
		compareLines ("Player REBEL: 0",scRebel.toString());
	
		scRebel.score(tieBomber);
		compareLines ("Player REBEL: 100",scRebel.toString());

		scRebel.score(tieFighter);
		compareLines ("Player REBEL: 295",scRebel.toString());

		fail("Continúa con el test");	
	}
	
	/*************************
	 * FUNCIONES AUXILIARES
	 *************************/
		
	/* Compara dos String línea a línea.
	 * Parámetros: la cadena esperada, la cadena resultado.
	 */
	private void  compareLines(String expected, String result) {
		expected=expected.replaceAll("\n+","\n");
		result=result.replaceAll("\n+","\n");
		String exp[]=expected.split("\n");
		String res[]=result.split("\n");
		boolean iguales = true;
		if (exp.length!=res.length) 
			fail("Cadena esperada de tamaño ("+exp.length+") distinto a la resultante ("+res.length+")");
		for (int i=0; i<exp.length && iguales; i++) {
			if (! exp[i].contains("ERROR:")) {
				res[i]=res[i].trim();
				if (res[i].length()>1 && (res[i].charAt(1)=='|')) //Es un Board
						assertEquals("linea "+i, exp[i].trim(),res[i]);
				else
					assertEquals("linea "+i, exp[i].replaceAll(" ",""), res[i].replaceAll(" ","")); 
			} 
			else if (! res[i].contains("ERROR:"))
					fail("Error: el resultado esperado debía contener en la línea "+i+" la cadena 'ERROR:'");
		}
	}
}
