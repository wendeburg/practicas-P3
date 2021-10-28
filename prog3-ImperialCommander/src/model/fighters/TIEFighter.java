package model.fighters;

import model.Fighter;
import model.Ship;

public class TIEFighter extends Fighter {

	public TIEFighter(Ship mother) {
		super(mother);
		
		this.addVelocity(10);
		this.addAttack(5);
		this.addShield(-10);
	}

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
