package model.exceptions;

/**
 * Excepción NoFighterAvailableException.
 * @author Francisco Wendeburg - Y8281851W.
 */
@SuppressWarnings("serial")
public class NoFighterAvailableException extends Exception {
	/**
	 * Tipo de caza del que no se encuentran cazas.
	 */
	private String type;
	
	/**
	 * Constructor de la excepción.
	 * @param type tipo de caza que genera la excepción.
	 */
	public NoFighterAvailableException(String type) {
		super();
		this.type = type;
	}
	
	/**
	 * Getter.
	 * @return el mensaje de error.
	 */
	public String getMessage() {
		return "ERROR: no ha sido posible encontrar un caza del tipo " + type + ".";
	}
}