package model.exceptions;

/**
 * Excepción InvalidSizeException.
 * @author Francisco Wendeburg - Y8281851W.
 */
@SuppressWarnings("serial")
public class InvalidSizeException extends Exception {
	/**
	 * Tamaño del tablero que genera la excepción.
	 */
	private int size;
	
	/**
	 * Constructor de la excepción.
	 * @param size tamaño del tablero que genera la excepción.
	 */
	public InvalidSizeException(int size) {
		super();
		this.size = size;
	}
	
	/**
	 * Getter.
	 * @return el mensaje de error.
	 */
	public String getMessage() {
		return "ERROR: el tamaño de tablero " + size + " es inválido.";
	}
}