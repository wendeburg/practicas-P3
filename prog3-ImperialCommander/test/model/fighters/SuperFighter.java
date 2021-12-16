package model.fighters;

import model.Fighter;
import model.Ship;

public class SuperFighter extends Fighter {

	public SuperFighter(Ship mother) {
		super(mother);
		addVelocity(10);
		addShield(-50);
		addAttack(5);
	}

	private SuperFighter(SuperFighter other) {
        super(other);	
	}

	@Override
	public Fighter copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public char getSymbol() {
		// TODO Auto-generated method stub
		return 0;
	}

}
