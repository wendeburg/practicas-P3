package model.game.exceptions;

/**
 * Excepción FWrongFighterIdException.
 * @author Francisco Wendeburg - Y8281851W.
 */
@SuppressWarnings("serial")
public class WrongFighterIdException extends Exception {
	/**
	 * Id del caza que genera la excepción.
	 */
	private int id;
	
	/**
	 * Constructor de la excepción.
	 * @param id la id del caza que genera la excepcioón.
	 */
	public WrongFighterIdException(int id) {
		super();
		this.id = id;
	}
	
	/**
	 * Getter.
	 * @return el mensaje de error.
	 */
	public String getMessage() {
		return "ERROR: no se ha encontrado un caza con el id: " +  id + ".";
	}
}