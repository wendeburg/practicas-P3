package model.exceptions;

import model.Fighter;

/**
 * Excepción FighterIsDestroyedException.
 * @author Francisco Wendeburg - Y8281851W.
 */
@SuppressWarnings("serial")
public class FighterIsDestroyedException extends Exception {
	/**
	 * Caza que genera la excepción.
	 */
	private Fighter f;
	
	/**
	 * Constructor de la excepción.
	 * @param f caza que genera la excepción.
	 */
	public FighterIsDestroyedException(Fighter f) {
		super();
		this.f = f;
	}
	
	/**
	 * Getter.
	 * @return el mensaje de error.
	 */
	public String getMessage() {
		return "ERROR: el caza " + f.toString() + " está destruido (antes de la lucha).";
	}
}