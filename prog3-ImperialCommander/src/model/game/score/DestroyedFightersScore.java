package model.game.score;

import model.Fighter;
import model.Side;

public class DestroyedFightersScore extends Score<Fighter> {
	/**
	 * Constructor.
	 * @param side Side que se quiere llevar la cuenta del puntaje.
	 */
	public DestroyedFightersScore(Side side) {
		super(side);
	}

	@Override
	/**
	 * Incrementa el valor de la puntuación del Fighter recibido como argumento es 1.
	 * @param sc Fighter del cual se obtiene la puntuación.
	 */
	public void score(Fighter sc) {
		if (sc != null) {
			score = score + sc.getValue();	
		}
	}
}
