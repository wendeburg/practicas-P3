package model.exceptions;

import model.Fighter;

@SuppressWarnings("serial")
public class FighterNotInBoardException extends Exception {
	private Fighter f;
	
	public FighterNotInBoardException(Fighter f) {
		super();
		this.f = f;
	}
	
	public String getMessage() {
		return "ERROR: el caza " + f.toString() + " no tiene una posición asignada / no es correcta.";
	}
}