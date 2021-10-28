package model;

import model.exceptions.FighterIsDestroyedException;

/**
 * Clase Fighter: Un caza.
 * @author Francisco Wendeburg - Y8281851W.
 */
public abstract class Fighter {
	/**
	 * Constante por que se utiliza para calcvular el daño hecho a una nave.
	 */
	private final int KDIVDANYO = 300;
	
	/**
	 * Muestra la ID que va a tener el siguiente caza que se cree.
	 */
	private static int nextId = 1;
	
	/**
	 * La velocidad del caza.
	 */
	private int velocity;
	
	/**
	 * El daño deataque del caza.
	 */
	private int attack;
	
	/**
	 * El escudo del caza.
	 */
	private int shield;
	
	/**
	 * El identificador (unico) del caza.
	 */
	private int id;
	
	/**
	 * La posición del caza en el tablero.
	 */
	private Coordinate position;
	
	/**
	 * La nave de la que sale.
	 */
	private Ship motherShip;
	
	/**
	 * Crea un nuevo Fighter con una velocity=100, attack=80, shield=80 y sin posición en el tablero.
	 * @param type tipo de caza.
	 * @param mother nave de la que sale el caza.
	 */
	protected Fighter(Ship mother) {
		id = nextId;
		nextId++;
		
		velocity = 100;
		attack = 80;
		shield = 80;
		position = null;
		motherShip = mother;
	}

	
	/**
	 * Constructor de copia.
	 * @param f Fighter cuyos atributos se van a copiar.
	 */
	protected Fighter(Fighter f) {
		id = f.id;
		velocity = f.velocity;
		attack = f.attack;
		shield = f.shield;
		position = f.position;
		motherShip = f.motherShip;
	}
	
	/**
	 * Resetea el valor de nextId a 1.
	 */
	public static void resetNextId() {
		nextId = 1;
	}
	
	/**
	 * Devuelve el tipo de caza.
	 * @return una string con el tipo de caza.
	 */
	public String getType() {
		return getClass().getSimpleName();
	}
	
	
	/**
	 * Devuelve el ID del caza.
	 * @return la ID (int) del caza.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Devuelve la velocidad del caza.
	 * @return un entero que es la velocidad del caza.
	 */
	public int getVelocity() {
		return velocity;
	}

	/**
	 * Devuelve el ataque del caza.
	 * @return un entero que es el ataque del caza.
	 */
	public int getAttack() {
		return attack;
	}

	/**
	 * Devuelve el escudo del caza.
	 * @return un entero que es el escudo del caza.
	 */
	public int getShield() {
		return shield;
	}
	
	/**
	 * Devuelve el "Side" (Rebel o Imperial) del caza.
	 * @return una string que es el Side del caza. 
	 */
	public Side getSide() {
		return motherShip.getSide();
	}

	/**
	 * Devuelve la coordenada del caza en el tablero.
	 * @return un objeto Coordinate que es la posición del caza.
	 */
	public Coordinate getPosition() {
		return position;
	}

	/**
	 * Devuelve la nave madre del caza.
	 * @return un objeto Ship que es la nave madre del caza.
	 */
	public Ship getMotherShip() {
		return motherShip;
	}
	
	/**
	 * Pone el caza en la posición pasada como parámetro.
	 * @param position un objeto de tipo Coordinate.
	 */
	public void setPosition(Coordinate position) {
		this.position = position;
	}

	/**
	 * Añade el ataque pasado como parámetro al actual de la nave.
	 * @param attack ataque a sumar.
	 */
	public void addAttack(int attack) {
		int newAttack = this.attack + attack;
		
		if (newAttack >= 0) {
			this.attack = newAttack;
		}
		else {
			this.attack = 0;
		}
	}
	
	/**
	 * Añade la velocidad pasada como parámetro a la actual del caza.
	 * @param velocity velocidad a sumar.
	 */
	public void addVelocity(int velocity) {
		int newVel = this.velocity + velocity;
		
		if (newVel >= 0) {
			this.velocity += velocity;
		}
		else {
			this.velocity = 0;
		}
	}

	/**
	 * Añade el escudo pasado como parámetro al actual del caza.
	 * @param shield escudo a sumar.
	 */
	public void addShield(int shield) {		
		this.shield += shield;
	}
	
	/**
	 * Devuelve si el caza ha sido destruido.
	 * @return true si shield <= 0, false de otra manera.
	 */
	public boolean isDestroyed() {
		return (shield <= 0);
	}
	
	/**
	 * Calcula el daño hecho a un enemigo.
	 * @param n número utilizado para calcular el daño.
	 * @param enemy enemigo al que se le va a ser daño.
	 * @return un entero con el daño a realizar al enemigo pasado como parámetro.
	 */
	public int getDamage(int n, Fighter enemy) {
		return (n*attack)/KDIVDANYO;
	}
	
	/**
	 * Simula la pelea entre dos cazas. La pelea acaba una vez un caza es destruido.
	 * @param enemy enemigo contra quien se pelea.
	 * @return 1 si gana el caza que llama a la función, -1 si gana el enemigo.
	 */
	public int fight(Fighter enemy) throws FighterIsDestroyedException {
		int n, umbral;
		
		if (this.isDestroyed()) {
			throw new FighterIsDestroyedException(this);
		}
		else if (enemy.isDestroyed()) {
			throw new FighterIsDestroyedException(enemy);
		}
		
		do {
			n = RandomNumber.newRandomNumber(100);
			umbral = (velocity*100)/(velocity + enemy.velocity);
			
			if (umbral <= n) {
				enemy.addShield(-this.getDamage(n, enemy));			
			}
			else {
				this.addShield(-(enemy.getDamage(100-n, this)));
			}			
		} while(!(this.isDestroyed() || enemy.isDestroyed()));
	
		if (this.isDestroyed()) {
			return -1;
		}
		else {
			return 1;
		}
	}

    /**
     * Devuelve la representación de la clase en String.
     * @return una referencia a String.
     */
	@Override
	public String toString() {
		String stringToReturn = "(" + getType() + " " + id + " " + this.getSide() + " ";
		
		if (position != null) {
			stringToReturn += position.toString() + " ";
		}
		else {
			stringToReturn += "null ";
		}
		
		stringToReturn += "{" + velocity + "," + attack + "," + shield + "})";
	
		return stringToReturn;
	}
	
	/**
	 * Genera el hashCode del objeto.
	 * @return devuelve el hashCode (int).
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	
    /**
     * Compara el objeto actual con el que se pasa por parámetros para determinar la igualdad.
     * @param obj objeto de cualquier tipo.
     * @return true si los objetos son del mismo tipo y las IDs coinciden, false en cualqueir otro caso.
     */
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
	
    public abstract Fighter copy();

    public abstract char getSymbol();
	
}
