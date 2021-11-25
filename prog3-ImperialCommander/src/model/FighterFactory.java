package model;

import java.util.Objects;

import model.fighters.AWing;
import model.fighters.TIEBomber;
import model.fighters.TIEFighter;
import model.fighters.TIEInterceptor;
import model.fighters.XWing;
import model.fighters.YWing;

/**
 * Clase FighterFactory: Se encarga de fabricar cazas del tipo especificado. 
 * @author Francisco Wendeburg - Y8281851W.
 */
public class FighterFactory {
	
	/**
	 * Crea un nuevo caza.
	 * @param type tipo del caza a crear.
	 * @param mother nave madre del caza a crear.
	 * @return un nuevo caza del tipo especificado.
	 */
	public static Fighter createFighter(String type, Ship mother) {
		Objects.requireNonNull(type);
		Objects.requireNonNull(mother);
		
		switch (type) {
			case "AWing":
				return new AWing(mother);
			case "XWing":
				return new XWing(mother);
			case "YWing":
				return new YWing(mother);
			case "TIEInterceptor":
					return new TIEInterceptor(mother);
			case "TIEFighter":
					return new TIEFighter(mother);
			case "TIEBomber":
					return new TIEBomber(mother);
			default:
					return null;
		}
	}
}
