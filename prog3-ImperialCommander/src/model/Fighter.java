package model;

public class Fighter {
	private final int KDIVDANYO = 300;
	private static int nextId = 1;
	
	private String type;
	private int velocity;
	private int attack;
	private int shield;
	private int id;
	private Coordinate position;
	private Ship motherShip;
	
	Fighter(String type, Ship mother) {
		id = nextId;
		nextId++;
		
		velocity = 100;
		attack = 80;
		shield = 80;
		this.type = type;
		position = null;
		motherShip = mother;
	}
	
	public Fighter(Fighter f) {
		id = f.id;
		velocity = f.velocity;
		attack = f.attack;
		shield = f.shield;
		type = f.type;
		position = f.position;
		motherShip = f.motherShip;
	}
	
	public static void resetNextId() {
		nextId = 1;
	}

	public String getType() {
		return type;
	}
	
	public int getId() {
		return id;
	}

	public int getVelocity() {
		return velocity;
	}

	public int getAttack() {
		return attack;
	}

	public int getShield() {
		return shield;
	}
	
	public Side getSide() {
		return motherShip.getSide();
	}

	public Coordinate getPosition() {
		return position;
	}

	public Ship getMotherShip() {
		return motherShip;
	}
	
	public void setPosition(Coordinate position) {
		this.position = position;
	}

	public void addAttack(int attack) {
		int newAttack = this.attack + attack;
		
		if (newAttack >= 0) {
			this.attack = newAttack;
		}
		else {
			this.attack = 0;
		}
	}
	
	public void addVelocity(int velocity) {
		int newVel = this.velocity + velocity;
		
		if (newVel >= 0) {
			this.velocity = velocity;
		}
		else {
			this.velocity = 0;
		}
	}

	public void addShield(int shield) {
		int newShield = this.shield + shield;
		
		if (newShield >= 0) {
			this.shield = newShield;
		}
		else {
			this.shield = 0;
		}
	}
	
	public boolean isDestroyed() {
		return (shield <= 0);
	}
	
	public int getDamage(int n, Fighter enemy) {
		return (n*attack)/KDIVDANYO;
	}
	
	public int fight(Fighter enemy) {
		int n, umbral;
		
		if (this.isDestroyed() || enemy.isDestroyed()) return 0;
		
		do {
			n = RandomNumber.newRandomNumber(100);
			umbral = (velocity*100)/(velocity + enemy.velocity);
			
			if (umbral <= n) {
				enemy.addShield(-this.getDamage(n, enemy)); // Se resta??.			
			}
			else {
				this.addShield(-(100-n));
			}			
		} while(!(this.isDestroyed() || enemy.isDestroyed()));
	
		if (this.isDestroyed()) {
			return -1;
		}
		else {
			return 1;
		}
	}

	@Override
	public String toString() {
		String stringToReturn = "(" + type + " " + id + " " + this.getSide() + " ";
		
		if (position != null) {
			stringToReturn += "[" + position.getX() + "," + position.getY() + "] ";
		}
		else {
			stringToReturn += "null ";
		}
		
		stringToReturn += "{" + velocity + "," + attack + "," + shield + "})";
	
		return stringToReturn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fighter other = (Fighter) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
}
