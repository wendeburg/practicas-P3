package model.fighters;

import model.Fighter;
import model.Ship;

public class XWing extends Fighter {

	public XWing(Ship mother) {
		super(mother);
		
		this.addVelocity(10);
		this.addAttack(20);
	}

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
