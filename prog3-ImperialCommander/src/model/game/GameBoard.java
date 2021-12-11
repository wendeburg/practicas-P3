package model.game;

import model.Coordinate;
import model.Fighter;
import model.Side;
import model.exceptions.InvalidSizeException;
import model.game.exceptions.WrongFighterIdException;

/**
 * Clase GameBoard: subclase de Board utilizada en la partida.
 * @author Francisco Wendeburg - Y8281851W.
 */
public class GameBoard extends model.Board {
	/**
	 * Constructor que llama al constructor de la clase padre.
	 * @param size tamaño del tablero.
	 * @throws InvalidSizeException cuando el tamaño del board no es válido.
	 */
	public GameBoard(int size) throws InvalidSizeException {
		super(size);
	}
	
	/**
	 * Devuelve el número de cazas que hay en el tablero del bando indicado por el argumento side.
	 * @param side Side del que se quiere obtener el número de Fighters.
	 * @return entero con el número de cazas del Side pasado como parámetro.
	 */
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
	
	/**
	 * Devuelve una cadena con una representación del tablero.
	 * @return una String con la representación del tablero.
	 */
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
