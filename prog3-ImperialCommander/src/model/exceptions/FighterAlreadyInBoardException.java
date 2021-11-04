package model.exceptions;

import model.Fighter;

/**
 * Excepción FighterAlreadyInBoardException.
 * @author Francisco Wendeburg - Y8281851W.
 */
@SuppressWarnings("serial")
public class FighterAlreadyInBoardException extends Exception {
	/**
	 * Caza que genera la excepción.
	 */
	private Fighter f;
	
	/**
	 * Constructor de la excepción.
	 * @param f caza que genera la excepción.
	 */
	public FighterAlreadyInBoardException(Fighter f) {
		super();
		this.f = f;
	}
	
	/**
	 * Getter.
	 * @return el mensaje de error.
	 */
	public String getMessage() {
		return "ERROR: el caza " + f.toString() + " ya tiene una posición asignada.";
	}
}