package mains;

import model.exceptions.InvalidSizeException;
import model.game.GameBoard;

public class MainP4 {
	public static void main(String args[]) throws InvalidSizeException {
		GameBoard g = new GameBoard(10);
		
		System.out.println(g.toString());
	}
}
