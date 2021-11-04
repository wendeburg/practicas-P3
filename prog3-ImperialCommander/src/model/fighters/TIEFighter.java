package model.fighters;

import model.Fighter;
import model.Ship;

/**
 * Clase TIEFighter: La clase del los cazas de tipo TIEFighter. 
 * @author Francisco Wendeburg - Y8281851W.
 */
public class TIEFighter extends Fighter {

	/**
	 * Constructor. Crea un nuevo caza con las carácterísticas del tipo.
	 * @param mother nava madre del caza.
	 */
	public TIEFighter(Ship mother) {
		super(mother);
		
		this.addVelocity(10);
		this.addAttack(5);
		this.addShield(-10);
	}

	/**
	 * Constructor de copia.
	 * @param f caza del que se quiere hacer la copia.
	 */
	private TIEFighter(Fighter f) {
		super(f);
	}
	
	@Override
	public Fighter copy() {
		return new TIEFighter(this);
	}

	@Override
	public char getSymbol() {
		return 'f';
	}
}
