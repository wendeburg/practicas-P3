package model.game;

import model.Coordinate;
import model.Fighter;
import model.Side;
import model.exceptions.InvalidSizeException;

public class GameBoard extends model.Board {
	public GameBoard(int size) throws InvalidSizeException {
		super(size);
	}
	
	public int numFighters(Side side) {
		int counter = 0;
		
    	for (Coordinate k : board.keySet()) {
    		Fighter f = board.get(k);
    		if (f != null) {
    			if (f.getSide() == side) {
    				counter++;
    			}
    		}
    	}
    	
    	return counter;
	}
	
	@Override
	public String toString() {
		Coordinate c;
		StringBuilder sb = new StringBuilder();
		sb.append("  ");
		
		// adds the first two lines of the board.
		for(int k = 0; k < this.getSize(); k++) {
			sb.append(k);
		}
		
		sb.append("\n");
		sb.append("  ");
		
		for(int k = 0; k < this.getSize(); k++) {
			sb.append("-");
		}
		
		sb.append("\n");
		
    	for (int j = 0; j < this.getSize(); j++) {
    		sb.append(j + "|");
    		for (int i = 0; i < this.getSize(); i++) {
    			Fighter f = board.get(new Coordinate(i, j));
    			if (f != null) {
    				sb.append(f.getSymbol());
    			}
    			else {
    				sb.append(" ");
    			}
    		}
    		sb.append("\n");
    	}
    	
    	return sb.toString();
	}
	

}
