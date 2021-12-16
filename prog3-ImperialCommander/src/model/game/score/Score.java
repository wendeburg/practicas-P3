package model.game.score;

import model.Side;

public abstract class Score<T> implements Comparable<Score<T>> {
	protected int score;
	private Side side;
	
	public Score(Side side) {
		score = 0;
		this.side = side;
	}
	
	public int getScore() {
		return score;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Player ");
		
		if (side == Side.IMPERIAL) {
			sb.append("IMPERIAL: ");
		}
		else {
			sb.append("REBEL: ");
		}
		
		sb.append(score);
		
		return sb.toString();
	}
	
	@Override
	public int compareTo(Score<T> other) {
		if (this.score > other.score) {
			return -1;
		}
		else if (this.score < other.score) {
			return 1;
		}
		else  {
			return side.compareTo(other.side);
		}
	}
	
	public abstract void score(T sc);
}
