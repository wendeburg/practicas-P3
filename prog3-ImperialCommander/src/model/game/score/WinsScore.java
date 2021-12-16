package model.game.score;

import model.Side;

/**
 * Clase WinsScore: Score basado en Integer. Lleva las cuentas de las victorias obtenidas por la nave del jugaddor.
 * @author Francisco Wendeburg - Y8281851W.
 */
public class WinsScore extends Score<Integer> {
	/**
	 * Constructor.
	 * @param side Side que se quiere llevar la cuenta del puntaje.
	 */
	public WinsScore(Side side) {
		super(side);
	}
	
	@Override
	/**
	 * Incrementa el valor del atributo score en 1 si el entero recibido como argumento es 1.
	 * @param sc valor entero.
	 */
	public void score(Integer sc) {
		if (sc != null) {
			if (sc == 1) {
				score++;
			}
		}
	}
}