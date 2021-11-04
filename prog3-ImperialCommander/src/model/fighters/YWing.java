package model.fighters;

import model.Fighter;
import model.Ship;

public class YWing extends Fighter {

	public YWing(Ship mother) {
		super(mother);
		
		this.addVelocity(-20);
		this.addAttack(-10);
		this.addShield(30);
	}

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
