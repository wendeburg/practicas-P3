package model.game.score;

import model.Side;

public class WinsScore extends Score<Integer> {
	public WinsScore(Side side) {
		super(side);
	}
	
	@Override
	public void score(Integer sc) {
		if (sc == 1) {
			score++;
		}
	}
}