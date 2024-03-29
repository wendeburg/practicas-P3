package model.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.Board;
import model.Coordinate;
import model.Fighter;
import model.Ship;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;
import model.game.score.DestroyedFightersScore;
import model.game.score.WinsScore;

/**
 * Clase GameShip: subclase de Game para gestionar una nave en el juego.
 * @author Francisco Wendeburg - Y8281851W.
 */
public class GameShip extends Ship {
	/**
	 * Score de los cazas destruidos por la nave.
	 */
	private DestroyedFightersScore destroyedFightersScore;
	
	/**
	 * Score de vicotrias de la nave.
	 */
	private WinsScore winsScore;
	
	/**
	 * Constructor que llama al constructor de la clase padre.
	 * @param name nombre de la nave.
	 * @param side Side (imperial/rebel) de la nave.
	 */
	public GameShip(String name, Side side) {
		super(name, side);
		
		destroyedFightersScore = new DestroyedFightersScore(side);
		winsScore = new WinsScore(side);
	}
	
	/**
	 * Comprueba si la flota del caza se ha destruido o no.
	 * @return true si la flota del caza está destruida o no quedan cazas, false en otro caso.
	 */
	public boolean isFleetDestroyed() {
		for (Fighter f : fleet) {
			if (f != null) {
				if (!f.isDestroyed()) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Busca en la flota de la nave un caza cuyo identificador coincida con el argumento id.
	 * @param id identificador del caza que se quiere encontrar.
	 * @return una referencia al caza con el ID pasado como parámetro.
	 * @throws WrongFighterIdException se lanza cuando no se encuentra un caza con ese ID.
	 */
	private Fighter getFighter(int id) throws WrongFighterIdException {
		boolean foundFighter = false;
		
		for (Fighter f : fleet) {
			if (f != null) {
				if (f.getId() == id) {
					foundFighter = true;
					return f;
				}
			}
		}
	
		if (!foundFighter) {
			throw new WrongFighterIdException(id);
		}
		
		return null;
	}
	
	/**
	 * Devuelve una lista con los identificadores de los cazas (no destruidos) de la flota de la nave.
	 * @param where donde se quieren buscar los cazas. "ship" dentro de la nave, "board" en el tablero, cualqueir otra cosa todos los cazas.
	 * @return una lista de enteros con los IDs de los cazas encontrados.
	 */
	public List<Integer> getFightersId(String where) {
		List<Integer> list = new ArrayList<>();
		
		for (Fighter f : fleet) {
			if (f != null) {
				if (!f.isDestroyed()) {
					if (where != null) {
						if (where.contentEquals("board")) {
							if (f.getPosition() != null) {
								list.add(f.getId());
							}
						}
						else if (where.contentEquals("ship")) {
							if (f.getPosition() == null) {
								list.add(f.getId());
							}
						}
						else {
							list.add(f.getId());
						}
					}
					else {
						list.add(f.getId());
					}
				}
			}
		}
	
		return list;
	}
	
	/**
	 * Lanza el caza indicado por el ID en el tablero.
	 * @param id identificador del caza a lanzar en el tablero.
	 * @param c coordenada donde se va a lanzar el caza.
	 * @param b tablero en donde se va a lanzar el caza.
	 * @throws FighterAlreadyInBoardException se lanza cuando el caza con el id pasado como parámetro ya esta en el tablero.
	 * @throws OutOfBoundsException se lanza cuando la coordenada pasada ocmo paŕametro no está dentro del tablero. 
	 * @throws WrongFighterIdException se lanza cuando el caza con el id pasado como parámetro no existe.
	 */
	public void launch(int id, Coordinate c, Board b) throws FighterAlreadyInBoardException, OutOfBoundsException, WrongFighterIdException {
		Objects.requireNonNull(c);
		Objects.requireNonNull(b);
		
		if (!getFighter(id).isDestroyed()) {
			b.launch(c, getFighter(id));	
		} else {
			throw new WrongFighterIdException(id);
		}
	}

	/**
	 * Pone a patrullar el caza indicado por el ID.
	 * @param id identificador del caza que se quiere poner a patrullar.
	 * @param b tablero en donde se quiere poner a patrullar el caza.
	 * @throws FighterNotInBoardException se lanza cuando el caza que se quiere poner a patrullar no está en el tablero.
	 * @throws WrongFighterIdException se lanza cuando el caza con el id pasado como parámetro no existe.
	 */
	public void patrol(int id, Board b) throws FighterNotInBoardException, WrongFighterIdException {
		Objects.requireNonNull(b);
		
		b.patrol(getFighter(id));
	}
	
	/**
	 * Mejora los atributos del caza. Mitad de qty para ataque y mitad para escudo. Si el caza está en el tablero se saca del tablero.
	 * @param id identificador del caza que se quiere mejorar.
	 * @param qty cantidad que se quiere mejorar el caza.
	 * @param b tablero donde se encuentra el caza que se queire mejorar.
	 * @throws WrongFighterIdException se lanza cuando el caza con el id pasado como parámetro no existe.
	 */
	public void improveFighter(int id, int qty, Board b) throws WrongFighterIdException {
		Objects.requireNonNull(b);
		
		Fighter f = getFighter(id);
		
		try {
			b.removeFighter(f);
		}
		catch (FighterNotInBoardException e) {
			// No se hace nada.
		}
		
		f.addAttack(qty/2);
		
		if (qty % 2 != 0) {
			f.addShield((qty/2) + 1);
		}
		else {
			f.addShield(qty/2);
		}
	}
	
	/**
	 * Devuelve la puntuación de victorias de la nave.
	 * @return el WinsScore de la nave.
	 */
	public WinsScore getWinsScore() {
		return winsScore;
	}
	
	/**
	 * Devuelve la puntuación de Fighters destruidos de la nave.
	 * @return el DestroyedFightersScore de la nave.
	 */
	public DestroyedFightersScore getDestroyedFightersScore() {
		return destroyedFightersScore;
	}
	
	@Override
	public void updateResults(int r, Fighter f) {
		super.updateResults(r, f);
		
		if (r == 1) {
			winsScore.score(r);
			destroyedFightersScore.score(f);
		}
	}
}
