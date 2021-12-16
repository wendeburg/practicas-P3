package model.game;

import model.game.score.DestroyedFightersScore;
import model.game.score.WinsScore;

/**
 * Interfaz IPlayer: Interfaz que modela un jugador del juego. Contiene los métodos necesarios para jugar.
 * @author Francisco Wendeburg - Y8281851W.
 */
public interface IPlayer {
	/**
	 * Asigna el tablero pasado como parámetro al atributo board del jugador.
	 * @param gb GameBoard que se quiere asignar al jugador.
	 */
	public void setBoard(GameBoard gb);
	
	/**
	 * Devuelve la nave del jugador (se utiliza para pruebas unitarias).
	 * @return una referencia a la nave del jugador.
	 */
	public GameShip getGameShip();
	
	/**
	 * Inicializa los cazas de un jugador. Los pone en la nave del jugador.
	 */
	public void initFighters();
	
	/**
	 * Llama al método del mismo nombre de la nave del jugador y devuelve su valor.
	 * @return true si la flota está destruida o no tiene cazas, false en otro caso.
	 */
	public boolean isFleetDestroyed();
	
	/**
	 * Muestra la nave del jugador.
	 * @return una String formada por la cadena que devuelve el método toString de la nave, un cambio de línea y la cadena devuelta por el método showFleet.
	 */
	public String showShip();
	
	/**
	 * Llama al método del mismo nombre de la nave del jugador.
	 */
	public void purgeFleet();
	
	/**
	 * Realiza la siguiente jugada del jugador. Puede ser un launch, patrol, improve o exit.
	 * @return true si el jugador sigue jugando, false si se va de la partida (exit).
	 */
	public boolean nextPlay();
	
	/**
	 * Devuelve el WinsScore de la nave del jugador.
	 * @return la puntuación de victorias de la nave del jugador.
	 */
	public WinsScore getWinsScore();
	
	/**
	 * Devuelve el DestroyedFightersScore de la nave del jugador.
	 * @return la puntuación de Fighters destruidos de la nave del jugador.
	 */
	public DestroyedFightersScore getdestroyedFightersScore();
}
