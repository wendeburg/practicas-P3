package model.exceptions;

@SuppressWarnings("serial")
public class NoFighterAvailableException extends Exception {
	private String type;
	
	public NoFighterAvailableException(String type) {
		super();
		this.type = type;
	}
	
	public String getMessage() {
		return "ERROR: no ha sido posible encontrar un caza del tipo " + type + ".";
	}
}