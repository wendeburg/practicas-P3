package model.fighters;

import model.Fighter;
import model.Ship;

/**
 * Clase TIEInterceptor: La clase del los cazas de tipo TIEInterceptor. 
 * @author Francisco Wendeburg - Y8281851W.
 */
public class TIEInterceptor extends Fighter {

	/**
	 * Constructor. Crea un nuevo caza con las carácterísticas del tipo.
	 * @param mother nava madre del caza.
	 */
	public TIEInterceptor(Ship mother) {
		super(mother);
		
		this.addVelocity(45);
		this.addAttack(5);
		this.addShield(-20);
	}

	/**
	 * Constructor de copia.
	 * @param f caza del que se quiere hacer la copia.
	 */
	private TIEInterceptor(Fighter f) {
		super(f);
	}
	
	@Override
	public Fighter copy() {
		return new TIEInterceptor(this);
	}

	@Override
	public char getSymbol() {
		return 'i';
	}
	
	@Override
	public int getDamage(int n, Fighter enemy) {
		int damage = super.getDamage(n, enemy);
		
		if (enemy.getType().contentEquals("YWing")) {
			return damage*2;
		}
		else if (enemy.getType().contentEquals("AWing")) {
			return damage/2;
		}
		else {
			return damage;
		}
	}
}
