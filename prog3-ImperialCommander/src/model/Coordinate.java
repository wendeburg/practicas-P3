package model;

/**
 * Clase Coordinate: Una posición en un tablero. Toma un valor x y otro valor y. 
 * @author Francisco Wendeburg - Y8281851W.
 */
public class Coordinate {
    /**
     * Valor x de la coordenada.
     */
    private int x;
    
     /**
     * Valor y de la coordenada.
     */
    private int y;

    /**
     * Crea un nuevo objeto con los valores iniciales que se le pasan como parámetro.
     * @param x valor x de la coordenada.
     * @param y valor y de la coordenada.
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Crea un nuevo objeto Coordiate copia de otro objeto Coordinate.
     * @param c objeto que sea va a copiar.
     */
    public Coordinate(Coordinate c) {
        x = c.x;
        y = c.y;
    }

    /**
     * Accede al valor x de la coordenada.
     * @return el valor de la x de la coordenada.
     */
    public int getX() {
        return x;
    }

    /**
     * Accede al valor y de la coordenada.
     * @return el valor de la y de la coordenada.
     */
    public int getY() {
        return y;
    }

    /**
     * Suma a las coordenadas del objeto los parámetros pasados.
     * @param x numero a sumar en coordenada x.
     * @param y número a sumar en coordenada y. 
     * @return un nuevo objecto Coordinate.
     */
    public Coordinate add(int x, int y) {
        Coordinate newCoord = new Coordinate(this.x+x, this.y+y);

        return newCoord;
    }

    /**
     * Suma a las coordenadas del objeto las coordenadas del objeto pasado como parámetro.
     * @param c objeto de tipo Coordinate. 
     * @return un nuevo objeto Coordinate con la
     */
    public Coordinate add(Coordinate c) {
        Coordinate newCoord = new Coordinate(x+c.x, y+c.y);

        return newCoord;
    }

    /**
     * Devuelve la representación de la clase en String.
     * @return una referencia a String.
     */
    public String toString() {
        return "[" + x + "," + y + "]";
    }

	/**
	 * Genera el hashCode del objeto.
	 * @return devuelve el hashCode (int).
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

    /**
     * Compara el objeto actual con el que se pasa por parámetros para determinar la igualdad.
     * @param obj objeto de cualquier tipo.
     * @return true si las coordenadas x e y de ambos objetos coinciden, falso en cualqueir otro caso.
     */
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;

        if (!(obj instanceof Coordinate)) {
            return false;
        }
        else {
            Coordinate compObj = (Coordinate) obj;

            if (this.x == compObj.x && this.y == compObj.y) {
                return true;
            }
            else {
                return false;
            }
        }
    }	
}