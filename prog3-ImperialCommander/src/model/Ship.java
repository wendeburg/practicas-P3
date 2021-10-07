package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Ship {
	private String name;
	private int wins;
	private int losses;
	private Side side;
	private ArrayList<Fighter> fleet;
	
	public Ship(String name, Side side) {
		this.name = name;
		this.side = side;
		wins = 0;
		losses = 0;
		fleet = new ArrayList<Fighter>();
	}

	public String getName() {
		return name;
	}
	
	public Side getSide() {
		return side;
	};

	public int getWins() {
		return wins;
	}

	public int getLosses() {
		return losses;
	}
	
	public List<Fighter> getFleetTest() {
		return fleet;
	}
	
	public void addFighters(String fd) {
		String strings[] = fd.split(":");
		
		for (int i = 0; i < strings.length; i++) {
			String stringParts[] = strings[i].split("/");
			
			int numFighters = Integer.parseInt(stringParts[0]);
			
			for (int j = 0; j < numFighters; j++) {
				fleet.add(new Fighter(stringParts[1], this));
			}
		}
	}
	
	public void updateResults(int r) {
		if (r == 1) wins++;
		if (r == -1) losses++;
	}
	
	public Fighter getFirstAvailableFighter(String type) {
		for (int i = 0; i < fleet.size(); i++) {
			if (!(fleet.get(i).isDestroyed())) {
				if (type.length() == 0) {
					return fleet.get(i);	
				}
				else if (fleet.get(i).getType() == type) {
					return fleet.get(i);
				}
			}
		}
		
		return null;
	}
	
	public void purgeFleet() {
		fleet = null;
	}
	
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
	
	
	public String myFleet() {
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		StringBuilder sb = new StringBuilder();
		String type;
		
		if (fleet.size() == 0) return null;
		
		// Cuenta los cazas de cada tipo.
		for (int i = 0; i < fleet.size(); i++) {
			type = fleet.get(i).getType();
			if (hm.containsKey(type)) {
				hm.put(type, hm.get(type) + 1);
			}
			else {
				hm.put(type, 1);
			}
		}
		
		for (String t : hm.keySet()) {
			sb.append(hm.get(t));
			sb.append("/");
			sb.append(t);
			sb.append(":");
		}
		
		String sbString = sb.toString();
		
		if (sbString.charAt(sbString.length() - 1) == ':') {
			sb.setLength(sbString.length() - 1);
		}
		
		return sb.toString();
	}

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

