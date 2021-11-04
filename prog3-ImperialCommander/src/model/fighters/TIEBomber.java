package model.fighters;

import model.Fighter;
import model.Ship;

/**
 * Clase TIEBomber: La clase del los cazas de tipo TIEBomber. 
 * @author Francisco Wendeburg - Y8281851W.
 */
public class TIEBomber extends Fighter {

	/**
	 * Constructor. Crea un nuevo caza con las carácterísticas del tipo.
	 * @param mother nava madre del caza.
	 */
	public TIEBomber(Ship mother) {
		super(mother);
		
		this.addVelocity(-30);
		this.addAttack(-50);
		this.addShield(35);
	}

	/**
	 * Constructor de copia.
	 * @param f caza del que se quiere hacer la copia.
	 */
	private TIEBomber(Fighter f) {
		super(f);
	}
	
	@Override
	public Fighter copy() {
		return new TIEBomber(this);
	}

	@Override
	public char getSymbol() {
		return 'b';
	}
	
	@Override
	public int getDamage(int n, Fighter enemy) {
		int damage = super.getDamage(n, enemy);
		
		if (enemy.getType().contentEquals("XWing")) {
			return damage/2;
		}
		else if (enemy.getType().contentEquals("YWing")) {
			return damage/2;
		}
		else if (enemy.getType().contentEquals("AWing")) {
			return damage/3;
		}
		else {
			return damage;
		}
	}
}
