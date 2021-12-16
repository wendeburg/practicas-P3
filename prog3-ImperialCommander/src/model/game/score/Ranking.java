package model.game.score;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Clase DestroyedFightersScore: Representa una puntuación basada en Fighter y suma el valor de los diferentes cazas destruidos por la nave del jugador.
 * @author Francisco Wendeburg - Y8281851W.
 */
public class Ranking <ScoreType extends Score<?>> {
	/**
	 * Set de puntuaciones. Solo va a haber 1 para el jugador imperial y uno para el rebelde.
	 */
	private SortedSet<ScoreType> scoreSet;
	
	/**
	 * Constructor.
	 */
	public Ranking() {
		scoreSet = new TreeSet<>();
	}
	
	/**
	 * Añade el Score que se le pase al atributo scoreSet.
	 * @param st Score que se quiere añadir al scoreSet.
	 */
	public void addScore(ScoreType st) {
		scoreSet.add(st);
	}
	
	/**
	 * Devuelve el primer elemento del scoreSet.
	 * @return devuelve el Score con más puntuación del scoreSet.
	 */
	public ScoreType getWinner() {
		if (scoreSet.isEmpty()) {
			throw new RuntimeException();
		}
		else {
			return scoreSet.first();	
		}
	}

	/**
	 * Devuelve el scoreSet.
	 * @return devuelve el set de Score.
	 */
	public SortedSet<ScoreType> getSortedRanking() {
		return scoreSet;
	}
	
	@Override
	/**
	 * Devuelve la representación como string de la clase Ranking.
	 * @return la representación como String de la clase Ranking.
	 */
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
