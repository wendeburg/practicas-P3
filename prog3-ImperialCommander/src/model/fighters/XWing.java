package model.fighters;

import model.Fighter;
import model.Ship;

/**
 * Clase XWing: La clase del los cazas de tipo XWing. 
 * @author Francisco Wendeburg - Y8281851W.
 */
public class XWing extends Fighter {

	/**
	 * Constructor. Crea un nuevo caza con las carácterísticas del tipo.
	 * @param mother nava madre del caza.
	 */
	public XWing(Ship mother) {
		super(mother);
		
		this.addVelocity(10);
		this.addAttack(20);
	}

	/**
	 * Constructor de copia.
	 * @param f caza del que se quiere hacer la copia.
	 */
	private XWing(Fighter f) {
		super(f);
	}
	
	@Override
	public Fighter copy() {
		return new XWing(this);
	}

	@Override
	public char getSymbol() {
		return 'X';
	}
}
