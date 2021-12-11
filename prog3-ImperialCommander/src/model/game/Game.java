package model.game;

import java.util.List;
import java.util.Objects;

import model.Side;
import model.exceptions.InvalidSizeException;

/**
 * Clase Game: gestiona una partida entre un jugador imperial y otro rebelde.
 * @author Francisco Wendeburg - Y8281851W.
 */
public class Game {
	/**
	 * El tamaño del tablero con el que se juega.
	 */
	private static final int BOARD_SIZE = 10;
	
	/**
	 * El tablero con el que se juega.
	 */
	private GameBoard board;
	
	/**
	 * Jugador rebelde.
	 */
	private IPlayer rebel;

	/**
	 * Jugador imperial.
	 */
	private IPlayer imperial;
	
	/**
	 * Consutructor que guarda en sus atributos los jugadores que se le pasan como parámetro.
	 * @param imperial jugador imperial.
	 * @param rebel jugador rebelde.
	 */
	public Game(IPlayer imperial, IPlayer rebel) {
		Objects.requireNonNull(imperial);
		Objects.requireNonNull(rebel);
		
		this.rebel = rebel;
		this.imperial = imperial;
		
		try {
			board = new GameBoard(BOARD_SIZE);
			
			this.rebel.setBoard(board);
			this.imperial.setBoard(board);
		} catch (InvalidSizeException e) {
			throw new RuntimeException();
		}
	}
	
	/**
	 * Devuelve el GameBoard (para pruebas unitarias).
	 * @return el GameBoard del juego.
	 */
	public GameBoard getGameBoard() {
		return board;
	}
	
	/**
	 * Devuelve el número de Fighters dentro de un determinado GameShip.
	 * @param s el GameShip del que se quiere obtener el número de Fighters dentro.
	 * @return entero con el número de Fighters dentro del GameShip.
	 */
	private int getNumFighters(GameShip s) {
		List<Integer> fs = s.getFightersId("board");
		
		return fs.size();
	}
	
	/**
	 * Gestiona la partida entre ambos jugadores.
	 * @return el Side (imperial/rebel) ganador.
	 */
	public Side play() {
		boolean hasGameFinished = false;
		boolean isImperialWinner = false;
		boolean playResult;
		
		imperial.initFighters();
		rebel.initFighters();
		
		while (!hasGameFinished) {
			System.out.println("BEFORE IMPERIAL");
			
			System.out.println(board.toString());
			System.out.println(imperial.showShip());
			System.out.print(rebel.showShip() + "\n");
			
			System.out.print("IMPERIAL(" + getNumFighters(imperial.getGameShip()) + "): ");
			
			playResult = imperial.nextPlay();
			
			if (playResult == false) {
				hasGameFinished = true;
				isImperialWinner = false;
				break;
			}
			
			System.out.println("AFTER IMPERIAL, BEFORE REBEL");
			System.out.println(board.toString());
			System.out.println(imperial.showShip());
			System.out.print(rebel.showShip() + "\n");
			
			if (rebel.isFleetDestroyed() || imperial.isFleetDestroyed()) {
				hasGameFinished = true;
				isImperialWinner = imperial.isFleetDestroyed()? false : true;
				break;
			}
			
			System.out.print("REBEL(" + getNumFighters(rebel.getGameShip()) + "): ");
			
			playResult = rebel.nextPlay();
			
			if (playResult == false) {
				hasGameFinished = true;
				isImperialWinner = true;
				break;
			}
			
			System.out.println("AFTER REBEL");
			System.out.println(board.toString());
			System.out.println(imperial.showShip());
			System.out.print(rebel.showShip() + "\n");
		
			imperial.purgeFleet();
			rebel.purgeFleet();
			
			if (rebel.isFleetDestroyed() || imperial.isFleetDestroyed()) {
				hasGameFinished = true;
				isImperialWinner = imperial.isFleetDestroyed()? false : true;
				break;
			}
		}
		
		if (isImperialWinner) {
			return Side.IMPERIAL;
		}
		else {
			return Side.REBEL;
		}
	}
}
