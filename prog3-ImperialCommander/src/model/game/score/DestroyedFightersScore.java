package model.game.score;

import model.Fighter;
import model.Side;

public class DestroyedFightersScore extends Score<Fighter> {
	public DestroyedFightersScore(Side side) {
		super(side);
	}

	@Override
	public void score(Fighter sc) {
		// Objects.RequireNonNull?
		score = score + sc.getValue();
	}
}
