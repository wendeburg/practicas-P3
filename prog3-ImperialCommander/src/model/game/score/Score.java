package model.game.score;

import model.Side;

/**
 * Clase Score: Crea un sistema de calificación para la partida.
 * @author Francisco Wendeburg - Y8281851W.
 * @param <T> Tipo de score (Integer/Fighter).
 */
public abstract class Score<T> implements Comparable<Score<T>> {
	/**
	 * Puntuación.
	 */
	protected int score;
	
	/**
	 * Side del que se lleva la contabilidad del score.
	 */
	private Side side;
	
	/**
	 * Constructor.
	 * @param side Side del que se va a llevar la contabilidad del score.
	 */
	public Score(Side side) {
		score = 0;
		this.side = side;
	}
	
	/**
	 * Getter.
	 * @return devuelve la puntuación.
	 */
	public int getScore() {
		return score;
	}
	
	@Override
	/**
	 * Convierte una instancia de la clase en su representación como String.
	 * @return devuelve la representación de la clase como String.
	 */
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
	/**
	 * Compara dos puntuaciones por su valor.
	 * @param other puntuación con la que se quiere comparar.
	 */
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
	
	/**
	 * Añade el valor de al score.
	 * @param sc valor que se va a agregar al score.
	 */
	public abstract void score(T sc);
}
