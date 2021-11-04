package model.fighters;

import model.Fighter;
import model.Ship;

/**
 * Clase AWing: La clase del los cazas de tipo AWing. 
 * @author Francisco Wendeburg - Y8281851W.
 */
public class AWing extends Fighter {

	/**
	 * Constructor. Crea un nuevo caza con las carácterísticas del tipo.
	 * @param mother nava madre del caza.
	 */
	public AWing(Ship mother) {
		super(mother);
		
		this.addVelocity(40);
		this.addAttack(5);
		this.addShield(-50);
	}
	
	/**
	 * Constructor de copia.
	 * @param f caza del que se quiere hacer la copia.
	 */
	private AWing(Fighter f) {
		super(f);
	}
	
	@Override
	public Fighter copy() {
		return new AWing(this);
	}

	@Override
	public char getSymbol() {
		return 'A';
	}
	
	@Override
	public int getDamage(int n, Fighter enemy) {
		int damage = super.getDamage(n, enemy);
		
		if (enemy.getType().contentEquals("TIEBomber")) {
			return damage*2;
		}
		else {
			return damage;
		}
	}
}
