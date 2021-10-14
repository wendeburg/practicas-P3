package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

/**
 * Clase Board: Un tablero. Dedfinido por su tamaño.
 * @author Francisco Wendeburg - Y8281851W.
 */
public class Board {
	
	/**
	 * Tamaño del tablero.
	 */
	private int size;
	
	private Map<Coordinate,Fighter> board;
	
	/**
	 * Constructor: Crea un nuevo objeto Board del tamaño pasado como parámetro.
	 * @param size int que es el tamaño del tablero.
	 */
	public Board(int size) {
		this.size = size;
		board = new HashMap<Coordinate,Fighter>();
	}
	
	public int getSize() {
		return size;
	}
	
	public Fighter getFighter(Coordinate c) {
		if (board.get(c) != null) {
			return new Fighter(board.get(c));
		}
		
		return null;
	}
	
	public boolean removeFighter(Fighter f) {	
		if (board.get(f.getPosition()) != null) {
			if (board.get(f.getPosition()).equals(f)) {
				board.remove(f.getPosition());
				return true;
			}
		}
		
		return false;
	}
	
	public boolean inside(Coordinate c) {
		if (c == null) return false;
		
		if ((c.getX() >= 0 && c.getX() <= size-1)) {
			if ((c.getY() >= 0 && c.getY() <= size-1)) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean isCoordACorner(Coordinate c) {
		if (c.getX() == 0 && c.getY() == 0) {
			return true;
		}
		else if (c.getX() == size-1 && c.getY() == size-1) {
			return true;
		}
		else if (c.getX() == 0 && c.getY() == size-1) {
			return true;
		}
		else if (c.getX() == size-1 && c.getY() == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private TreeSet<Coordinate> getCornerNeighborhood(Coordinate c) {
		TreeSet<Coordinate> tree = new TreeSet<Coordinate>();
		int i, j;
		
		if (c.getX() == 0 && c.getY() == 0) { // Esquina sup. izq.
			for (i = 0; i < 2; i++) {
				for (j = 0; j < 2; j++) {
	    			if (!(j == 0 && i == 0)) {
	    				tree.add(new Coordinate(i, j));
	    			}
				}
			}
		}
		else if (c.getX() == size-1 && c.getY() == size-1) { // Esq. inf. der.
			for (i = c.getX(); i >= c.getX()-1; i--) {
				for (j = c.getY(); j >= c.getY()-1; j--) {
	    			if (!(j == c.getX() && i == c.getY())) {
	    				tree.add(new Coordinate(i, j));
	    			}
				}
			}
		}
		else if (c.getX() == 0 && c.getY() == size-1) { // Esq. inf. izq.
			for (i = 0; i < 2; i++) {
				for (j = 0; j < 2; j++) {
	    			if (!(j == 0 && i == 0)) {
	    				tree.add(new Coordinate(i, j));
	    			}
				}
			}
		}
		else if (c.getX() == size-1 && c.getY() == 0) { // Esq. sup. der.
			for (i = 0; i < 2; i++) {
				for (j = 0; j < 2; j++) {
	    			if (!(j == 0 && i == 0)) {
	    				tree.add(new Coordinate(i, j));
	    			}
				}
			}
		}
		
		return tree;
	}
	
    public TreeSet<Coordinate> getNeighborhood(Coordinate c) {
    	// No se comprueba que la coord esté en el tablero.
    	if (isCoordACorner(c)) {
    		return getCornerNeighborhood(c);
    	}
    	else {
        	return c.getNeighborhood();
    	}
    }
    
    public int launch(Coordinate c, Fighter f) {
    	if (inside(c)) {
    		if (!(board.get(c) == null)) {
    			if (!(board.get(c).getSide() == f.getSide())) {
    				if (f.fight(board.get(c)) == 1) {
    					f.getMotherShip().updateResults(1);
    					board.get(c).getMotherShip().updateResults(-1);
    	    			board.put(c, f);
    	    			f.setPosition(c);
    	    			
    	    			return 1;
    				}
    				else {
    					f.getMotherShip().updateResults(-1);
    					board.get(c).getMotherShip().updateResults(1);
    	    			
    	    			return -1;
    				}
    			}
    			else {
    				return 0;
    			}
    		}
    		else {
    			board.put(c, f);
    			f.setPosition(c);
    			return 0;
    		}
    	}
    	else {
    		return 0;
    	}
    }
    
    private boolean isInBoard(Fighter f) {
    	boolean inBoard = false;
    	
    	for (Coordinate k : board.keySet()) {
    		if (board.get(k).equals(f)) {
    			inBoard = true;
    			break;
    		}
    	}
    	
    	return inBoard;
    }
    
    public void patrol(Fighter f) {
    	if (isInBoard(f)) {
    		TreeSet<Coordinate> n = getNeighborhood(f.getPosition());
    		
    		for (Coordinate c : n) {
    			if (f.isDestroyed() == true) {
    				break;
    			}
    			
    			if (!(board.get(c) == null)) {
    				if (board.get(c).getSide() != f.getSide()) {
    	   				if (f.fight(board.get(c)) == 1) {
        					f.getMotherShip().updateResults(1);
        					board.get(c).getMotherShip().updateResults(-1);
        					board.get(c).setPosition(null);
        	    			board.put(c, null);
        				}
    	   				else {
        					f.getMotherShip().updateResults(-1);
        					board.get(c).getMotherShip().updateResults(1);
        					board.remove(f.getPosition());
        					f.setPosition(null);
    	   				}
    				}
    			}
    		}
    	}
    }

	/**
	 * Devuelve la representación en String de la clase Board.
	 * @return String.
	 */
	public String toString() {
		return "Board [size=" + size + "]";
	}
}
