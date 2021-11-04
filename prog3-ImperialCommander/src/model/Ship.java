package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import model.exceptions.NoFighterAvailableException;

/**
 * Clase Ship: una nave que contiene una flota de cazas.
 * @author @author Francisco Wendeburg - Y8281851W.
 *
 */
public class Ship {
	/**
	 * Nombre de la nave.
	 */
	private String name;
	
	/**
	 * Victorias que tiene la nave.
	 */
	private int wins;
	
	/**
	 * Derrotas que tiene la nave.
	 */
	private int losses;
	
	/**
	 * Side de la nave (REBEL/IMPERIAL).
	 */
	private Side side;
	
	/**
	 * Flota (conjunto de cazas) de la nave.
	 */
	private ArrayList<Fighter> fleet;
	
	/**
	 * Constructor de la clase Ship. Establece wins y losses a 0, side y
	 * name según los parámetros pasados.
	 * @param name nombre de la nave.
	 * @param side Side de la nave /(REBEL/IMPERIAL).
	 */
	public Ship(String name, Side side) {
		this.name = name;
		this.side = side;
		wins = 0;
		losses = 0;
		fleet = new ArrayList<Fighter>();
	}

	/**
	 * Getter.
	 * @return el nombre de la nave.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter.
	 * @return el Side la nave.
	 */
	public Side getSide() {
		return side;
	};

	/**
	 * Getter.
	 * @return el número de victorias de la nave.
	 */
	public int getWins() {
		return wins;
	}

	/**
	 * Getter.
	 * @return el número de derrotas de la nave.
	 */
	public int getLosses() {
		return losses;
	}
	
	/**
	 * Getter.
	 * @return la flota de cazas de la nave.
	 */
	public List<Fighter> getFleetTest() {
		return fleet;
	}
	
	/**
	 * Añade las naves pasadas en formato string a la flota de la nave.
	 * @param fd String con un formato establecido.
	 */
	public void addFighters(String fd) {
		String strings[] = fd.split(":");
		
		for (int i = 0; i < strings.length; i++) {
			String stringParts[] = strings[i].split("/");
			
			int numFighters = Integer.parseInt(stringParts[0]);
			
			for (int j = 0; j < numFighters; j++) {
				fleet.add(FighterFactory.createFighter(stringParts[1], this));
			}
		}
	}
	
	/**
	 * Sume 1 a las victorias si r = 1, suma uno a las derrotas si r = -1,
	 * no hace nada con cualquier otro valor.
	 * @param r resultado de una batalla.
	 */
	public void updateResults(int r) {
		if (r == 1) wins++;
		if (r == -1) losses++;
	}
	
	/**
	 * Devuelve un caza no destruido.
	 * @param type tipo del caza que se quiere.
	 * @return un caza del tipo pedido si el parámetro type no está vacío,
	 * un caza de cualquier tipo si lo está y en el caso de que no se encuentre
	 * ningún caza se devuelve null.
	 * @throws NoFighterAvailableException cundo no hay ningún caza que devolver.
	 */
	public Fighter getFirstAvailableFighter(String type) throws NoFighterAvailableException {
		boolean foundShip = false;
		
		for (int i = 0; i < fleet.size(); i++) {
			if (!(fleet.get(i).isDestroyed())) {
				if (type.length() == 0) {
					foundShip = true;
					return fleet.get(i);	
				}
				else if (fleet.get(i).getType().contentEquals(type)) {
					foundShip = true;
					return fleet.get(i);
				}
			}
		}
		
		if (!foundShip) {
			throw new NoFighterAvailableException(type);
		}
		
		return null;
	}
	
	/**
	 * Elimina de la flota los cazas destruidos.
	 */
	public void purgeFleet() {
		ArrayList<Fighter> auxFleet = new ArrayList<Fighter>();
		
		for (int i = 0; i < fleet.size(); i++) {
			if (!fleet.get(i).isDestroyed()) {
				auxFleet.add(fleet.get(i));
			}
		}
		
		fleet = auxFleet;
	}
	
	/**
	 * Devuelve una string que es la representación de la flota de cazas
	 * de la nave.
	 * @return una String que contiene información sobre cada caza de la flota.
	 */
	public String showFleet() {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < fleet.size(); i++) {
			sb.append(fleet.get(i).toString());
			
			if (fleet.get(i).isDestroyed()) {
				sb.append(" (X)");
			}
			
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	
	/**
	 * Devuelve información sobre la flota.
	 * @return una String con información sonre cuantos cazas de
	 * cada tipo hay.
	 */
	public String myFleet() {
		// Se utiliza un LinkedHashMap para que se conserve el orden de las keys.
		LinkedHashMap<String, Integer> hm = new LinkedHashMap<String, Integer>();
		StringBuilder sb = new StringBuilder();
		String type;
		
		if (fleet.size() == 0) return "";
		
		// Cuenta los cazas de cada tipo.
		for (int i = 0; i < fleet.size(); i++) {
			if (!fleet.get(i).isDestroyed()) {
				type = fleet.get(i).getType();
				if (hm.containsKey(type)) {
					hm.put(type, hm.get(type) + 1);
				}
				else {
					hm.put(type, 1);
				}
			}
		}
		
		for (String t : hm.keySet()) {
			sb.append(hm.get(t));
			sb.append("/");
			sb.append(t);
			sb.append(":");
		}
		
		String sbString = sb.toString();
		
		if (sbString.length() > 0) {
			if (sbString.charAt(sbString.length() - 1) == ':') {
				sb.setLength(sbString.length() - 1);
			}	
		}

		return sb.toString();
	}

	/**
	 * Devuelve la representación de String de la clase.
	 * @return una String con información sobre el nombre, victorias,
	 * derrotas y sobre la flota (myFleet) de la nave.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Ship [");
		sb.append(name);
		sb.append(" ");
		sb.append(wins);
		sb.append("/");
		sb.append(losses);
		sb.append("]");
		sb.append(" ");
		sb.append(myFleet());
		
		return sb.toString();
	}
}

