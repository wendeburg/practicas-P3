package model.game.score;

import java.util.SortedSet;
import java.util.TreeSet;

public class Ranking <ScoreType extends Score<?>>{
	private SortedSet<ScoreType> scoreSet;

	public Ranking() {
		scoreSet = new TreeSet<>();
	}
	
	public void addScore(ScoreType st) {
		scoreSet.add(st);
	}
	
	public ScoreType getWinner() {
		if (scoreSet.isEmpty()) {
			throw new RuntimeException();
		}
		else {
			return scoreSet.first();	
		}
	}

	public SortedSet<ScoreType> getSortedRanking() {
		return scoreSet;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (ScoreType sc : scoreSet) {
			sb.append(" | ");
			sb.append(sc.toString());
		}
		
		sb.append(" | ");
		
		return sb.toString();
	}
}
