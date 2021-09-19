package model;

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

	@Override
	public String toString() {
		return "Board [size=" + size + "]";
	}
}
