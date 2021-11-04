package model.exceptions;

import model.Coordinate;

/**
 * Excepción OutOfBoundsException.
 * @author Francisco Wendeburg - Y8281851W.
 */
@SuppressWarnings("serial")
public class OutOfBoundsException extends Exception {
	/**
	 * Coordenada que genera la excepción.
	 */
	private Coordinate c;
	
	/**
	 * Constructor de la excepción.
	 * @param c coordenada que genera la excepción.
	 */
	public OutOfBoundsException(Coordinate c) {
		super();
		this.c = c;
	}
	
	/**
	 * Getter.
	 * @return el mensaje de error.
	 */
	public String getMessage() {
		return "ERROR: coordenada " + c.toString() + " fuera del tablero.";
	}
}
