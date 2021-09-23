package model;

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
	 * Constructor: Crea un nuevo objeto Board del tamaño pasado como parámetro.
	 * @param size int que es el tamaño del tablero.
	 */
	public Board(int size) {
		this.size = size;
	}

	/**
	 * Devuelve la representación en String de la clase Board.
	 * @return String.
	 */
	public String toString() {
		return "Board [size=" + size + "]";
	}
}
