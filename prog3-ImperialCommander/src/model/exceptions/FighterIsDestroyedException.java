package model.exceptions;

import model.Fighter;

@SuppressWarnings("serial")
public class FighterIsDestroyedException extends Exception {
	private Fighter f;
	
	public FighterIsDestroyedException(Fighter f) {
		super();
		this.f = f;
	}
	
	public String getMessage() {
		return "FighterIsDestroyedException: el caza " + f.toString() + " está destruido (antes de la lucha).";
	}
}