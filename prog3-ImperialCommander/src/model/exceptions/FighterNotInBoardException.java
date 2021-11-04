package model.exceptions;

import model.Fighter;

/**
 * Excepción FighterNotInBoardException.
 * @author Francisco Wendeburg - Y8281851W.
 */
@SuppressWarnings("serial")
public class FighterNotInBoardException extends Exception {
	/**
	 * Caza que genera la excepción.
	 */
	private Fighter f;
	
	/**
	 * Constructor de la excepción.
	 * @param f caza que genera la excepción.
	 */
	public FighterNotInBoardException(Fighter f) {
		super();
		this.f = f;
	}
	
	/**
	 * Getter.
	 * @return el mensaje de error.
	 */
	public String getMessage() {
		return "ERROR: el caza " + f.toString() + " no tiene una posición asignada / no es correcta.";
	}
}