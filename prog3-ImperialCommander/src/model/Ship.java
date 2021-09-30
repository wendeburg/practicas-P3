package model;

public class Ship {
	private String name;
	private int wins;
	private int losses;
	private Side side;
	
	public Ship() {};
	
	public Side getSide() {
		return side;
	}
}
