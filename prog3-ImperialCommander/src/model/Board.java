package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterIsDestroyedException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.InvalidSizeException;
import model.exceptions.OutOfBoundsException;

/**
 * Clase Board: Un tablero. Dedfinido por su tamaño.
 * @author Francisco Wendeburg - Y8281851W.
 */
public class Board {
	
	/**
	 * Tamaño del tablero.
	 */
	private int size;
	
	/**
	 * Mapa para almacenar los cazas en el tablero.
	 */
	protected Map<Coordinate,Fighter> board;
	
	/**
	 * Constructor: Crea un nuevo objeto Board del tamaño pasado como parámetro.
	 * @param size int que es el tamaño del tablero.
	 * @throws InvalidSizeException 
	 */
	public Board(int size) throws InvalidSizeException {
		if (size < 5) {
			throw new InvalidSizeException(size);
		}

		this.size = size;
		board = new HashMap<Coordinate,Fighter>();
	}
	
	
	/**
	 * Getter.
	 * @return el tamaño del tablero.
	 */
	public int getSize() {
		return size;
	}
	
	
	/**
	 * Getter.
	 * @param c coordenada de la que se queire obtener el caza.
	 * @return devuelve una copia del caza que se encuentra en la coordinada indicada.
	 * Si no hay ningún caza en la coordenada devuelve null.
	 */
	public Fighter getFighter(Coordinate c) {
		Objects.requireNonNull(c);
		
		if (board.get(c) != null) {
			return board.get(c).copy();
		}
		
		return null;
	}
	
	/**
	 * Remueve el caza del tablero si el caza situado en la posición del caza pasado como parámetro son iguales.
	 * @param f caza que se quiere remover del tablero.
	 * @return true si se remueve del tablero, false en cualquier otro caso.
	 * @throws FighterNotInBoardException si el caza que se quiere eliminar no está en el tablero.
	 */
	public boolean removeFighter(Fighter f) throws FighterNotInBoardException {
		Objects.requireNonNull(f);
		
		if (!isFighterInBoard(f)) {
			throw new FighterNotInBoardException(f);
		}
		
		if (board.get(f.getPosition()) != null) {
			if (board.get(f.getPosition()).equals(f)) {
				board.remove(f.getPosition());
				f.setPosition(null);
				return true;
			}
			else {
				throw new FighterNotInBoardException(f);
			}
		}
		else {
			throw new FighterNotInBoardException(f);
		}
	}
	
	/**
	 * Comprueba si una coordenada está dentro del tablero.
	 * @param c coordenada que se quiere comprobar si está dentro del tablero.
	 * @return true si la coordenada está dentro del tablero, false en cualquier otro caso.
	 */
	public boolean inside(Coordinate c) {
		if (c == null) return false;
		
		if ((c.getX() >= 0 && c.getX() <= size-1)) {
			if ((c.getY() >= 0 && c.getY() <= size-1)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Comprueba si una coordenada está en una esquina del tablero.
	 * @param c coordenada que se queire comprobar si está en una esquina del tablero.
	 * @return true si la coordenada está en una esquina del tablero, false en otro caso.
	 */
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
	
	/**
	 * Getter.
	 * @param c esquina de la que se queire las coordenadas vecinas.
	 * @return devuelve las coordenadas vecinas de una esquina.
	 */
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
	
	/**
	 * Devuelve las coordenadas vecinas de la que se pasa como argumento.
	 * @param c coordenada de la que se queire las coordenadas vecinas.
	 * @return TreeSet de las coordenadas vecinas.
	 * @throws OutOfBoundsException si la coordenada de la que se quiere obtener la vecindad no está en el tablero.
	 */
    public TreeSet<Coordinate> getNeighborhood(Coordinate c) throws OutOfBoundsException {
    	Objects.requireNonNull(c);
    	
    	if (!inside(c)) {
    		throw new OutOfBoundsException(c);
    	}

    	if (isCoordACorner(c)) {
    		return getCornerNeighborhood(c);
    	}
    	else {
        	TreeSet<Coordinate> cNeigh = c.getNeighborhood();
        	TreeSet<Coordinate> cNeighInside = new TreeSet<Coordinate>();
        	
        	for (Coordinate coord : cNeigh) {
        		if (inside(coord)) {
        			cNeighInside.add(coord);
        		}
        	}
        	
        	return cNeighInside;
    	}
    }
    
    /**
     * Intenta colocar un caza en el tablero.
     * @param c posición en la que se queire colocar el caza indicado.
     * @param f caza que se quiere colocar en la posición indicada.
     * @return Devuelve 1 si se ha colocado, 0 en cualquier otro caso.
     * @throws FighterAlreadyInBoardException si el caza ya tiene una posición asignada. 
     * @throws OutOfBoundsException si la coordenada no está dentro del tablero.
     */
    public int launch(Coordinate c, Fighter f) throws FighterAlreadyInBoardException, OutOfBoundsException {
    	Objects.requireNonNull(c);
    	Objects.requireNonNull(f);
    	
    	if (f.getPosition() != null) {
    		throw new FighterAlreadyInBoardException(f);
    	}
    	
    	if (inside(c)) {
    		if (!(board.get(c) == null)) {
    			if (!(board.get(c).getSide() == f.getSide())) {
    				try {
						if (f.fight(board.get(c)) == 1) {
							f.getMotherShip().updateResults(1);
							board.get(c).getMotherShip().updateResults(-1);
							removeFighter(board.get(c));
							board.put(c, f);
							f.setPosition(c);
							
							return 1;
						}
						else {
							f.getMotherShip().updateResults(-1);
							board.get(c).getMotherShip().updateResults(1);
							
							return -1;
						}
					} catch (FighterIsDestroyedException e) {
						throw new RuntimeException();
					} catch (FighterNotInBoardException e) {
						throw new RuntimeException();
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
    		throw new OutOfBoundsException(c);
    	}
    }
    
    /**
     * Comprueba que el caza indicado esé en el tablero.
     * @param f caza que se queire saber si está en el tablero.
     * @return true si el caza está en el tablerom false en otro caso.
     */
    private boolean isFighterInBoard(Fighter f) {
    	boolean inBoard = false;
    	
    	for (Coordinate k : board.keySet()) {
    		if (board.get(k).equals(f)) {
    			inBoard = true;
    			break;
    		}
    	}
    	
    	return inBoard;
    }
    
    /**
     * Recorre la vecindad del caza luchando con los enemigos que se encuentre en el camino hasta ser destruído o terminar el recorrido.
     * @param f caza que recorre su vecindad.
     * @throws FighterNotInBoardException cuando el caza no está en el tablero.
     */
    public void patrol(Fighter f) throws FighterNotInBoardException {
    	Objects.requireNonNull(f);
    	
    	if (f.getPosition() == null) {
    		throw new FighterNotInBoardException(f);
    	}
    	
    	if (f.isDestroyed()) {
    		throw new RuntimeException();
    	}
    	
    	
    	if (isFighterInBoard(f)) {
    		TreeSet<Coordinate> n;
			try {
				n = getNeighborhood(f.getPosition());
			} catch (OutOfBoundsException e) {
				throw new RuntimeException();
			}
    		
    		for (Coordinate c : n) {
    			if (f.isDestroyed() == true) {
    				break;
    			}
    			
    			if (!(board.get(c) == null)) {
    				if (board.get(c).getSide() != f.getSide()) {
    					int fightResult;
						try {
							fightResult = f.fight(board.get(c));
						} catch (FighterIsDestroyedException e) {
							throw new RuntimeException();
						}
    	   				if (fightResult == 1) {
        					f.getMotherShip().updateResults(1);
        					board.get(c).getMotherShip().updateResults(-1);
        	    			removeFighter(board.get(c));
        				}
    	   				else if (fightResult == -1){
        					f.getMotherShip().updateResults(-1);
        					board.get(c).getMotherShip().updateResults(1);
        					removeFighter(f);
    	   				}
    				}
    			}
    		}
    	}
    	else {
    		throw new RuntimeException();
    	}
    }
}
