package model.fighters;

import model.Fighter;
import model.Ship;

public class TIEInterceptor extends Fighter {

	public TIEInterceptor(Ship mother) {
		super(mother);
		
		this.addVelocity(45);
		this.addAttack(5);
		this.addShield(-20);
	}

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
