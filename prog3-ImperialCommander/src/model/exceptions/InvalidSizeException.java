package model.exceptions;

@SuppressWarnings("serial")
public class InvalidSizeException extends Exception {
	private int size;
	
	public InvalidSizeException(int size) {
		super();
		this.size = size;
	}
	
	public String getMessage() {
		return "InvalidSizeException: el tamaño de tablero " + size + " es inválido (debe ser >5).";
	}
}