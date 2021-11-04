package model.fighters;

import model.Fighter;
import model.Ship;

/**
 * Clase YWing: La clase del los cazas de tipo YWing. 
 * @author Francisco Wendeburg - Y8281851W.
 */
public class YWing extends Fighter {

	/**
	 * Constructor. Crea un nuevo caza con las carácterísticas del tipo.
	 * @param mother nava madre del caza.
	 */
	public YWing(Ship mother) {
		super(mother);
		
		this.addVelocity(-20);
		this.addAttack(-10);
		this.addShield(30);
	}

	/**
	 * Constructor de copia.
	 * @param f caza del que se quiere hacer la copia.
	 */
	private YWing(Fighter f) {
		super(f);
	}
	
	@Override
	public Fighter copy() {
		return new YWing(this);
	}

	@Override
	public char getSymbol() {
		return 'Y';
	}
	
	@Override
	public int getDamage(int n, Fighter enemy) {
		int damage = super.getDamage(n, enemy);
		
		if (enemy.getType().contentEquals("TIEBomber")) {
			return damage/2;
		}
		else if (enemy.getType().contentEquals("TIEFighter")) {
			return damage/3;
		}
		else if (enemy.getType().contentEquals("TIEInterceptor")) {
			return damage/3;
		}
		else {
			return damage;
		}
	}
}
