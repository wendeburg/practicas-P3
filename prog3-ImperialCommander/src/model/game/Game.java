package model.game;

import java.util.List;
import java.util.Objects;

import model.Side;
import model.exceptions.InvalidSizeException;

public class Game {
	private static final int BOARD_SIZE = 10;
	private GameBoard board;
	private IPlayer rebel;
	private IPlayer imperial;
	
	
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
	
	public GameBoard getGameBoard() {
		return board;
	}
	
	private int getNumFighters(GameShip s) {
		List<Integer> fs = s.getFightersId("board");
		
		return fs.size();
	}
	
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
		}
		
		if (isImperialWinner) {
			return Side.IMPERIAL;
		}
		else {
			return Side.REBEL;
		}
	}
}
