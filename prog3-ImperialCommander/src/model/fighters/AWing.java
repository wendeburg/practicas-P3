package model.fighters;

import model.Fighter;
import model.Ship;

public class AWing extends Fighter {

	public AWing(Ship mother) {
		super(mother);
		
		this.addVelocity(40);
		this.addAttack(5);
		this.addShield(-50);
	}

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
