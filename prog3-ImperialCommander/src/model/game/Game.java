package model.game;

import java.util.List;

import model.Side;
import model.exceptions.InvalidSizeException;

public class Game {
	private static final int BOARD_SIZE = 10;
	private GameBoard board;
	private IPlayer rebel;
	private IPlayer imperial;
	
	
	public Game(IPlayer imperial, IPlayer rebel) {
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
	
	public GameBoard etGameBoard() {
		return board;
	}
	
	private int getNumFighters(GameShip s) {
		List<Integer> fs = s.getFightersId("board");
		
		return fs.size();
	}
	
	public Side play() {
		boolean hasGameFinished = false;
		boolean isImperialWinner = false;
		boolean isImperialTurn = true;
		boolean playResult;
		imperial.initFighters();
		rebel.initFighters();
		
		while (!hasGameFinished) {
			if (isImperialTurn) {
				System.out.println("BEFORE IMPERIAL");
				
				System.out.println(board.toString());
				System.out.println(imperial.toString());
				System.out.println(rebel.toString());
				
				playResult = imperial.nextPlay();
				
				if (playResult == false || imperial.isFleetDestroyed()) {
					hasGameFinished = true;
					isImperialWinner = false;
				}
				else {
					System.out.print("IMPERIAL(" + getNumFighters(imperial.getGameShip()) + "): ");
					System.out.println("AFTER IMPERIAL, BEFORE REBEL");
					System.out.println(board.toString());
					System.out.println(imperial.toString());
					System.out.println(rebel.toString());
					isImperialTurn = false;
				}
			}
			else {
				playResult = rebel.nextPlay();
				
				if (playResult == false || rebel.isFleetDestroyed()) {
					hasGameFinished = true;
					isImperialWinner = true;
				}
				else {
					System.out.print("REBEL(" + getNumFighters(rebel.getGameShip()) + "): ");
					System.out.println("AFTER REBEL");
					System.out.println(board.toString());
					System.out.println(imperial.toString());
					System.out.println(rebel.toString());
					isImperialTurn = true;	
				}
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
