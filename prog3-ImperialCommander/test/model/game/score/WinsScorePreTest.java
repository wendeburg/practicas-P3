package model.game.score;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Fighter;
import model.Side;

public class WinsScorePreTest {

	Score<Integer> scRebel, scImperial;
	@Before
	public void setUp() throws Exception {
		scImperial = new WinsScore(Side.IMPERIAL);
		scRebel = new WinsScore(Side.REBEL);
	}

	/* Test del constructor WinsScore. Se comprueba que es
	 * un tipo de Score y que se inicia a 0
	 */
	@Test
	public void testWinsScore() {
		assertTrue(scImperial instanceof Score);
		assertEquals(0, scImperial.getScore());
	}

	 /* Se comprueba que los valores null en el método score no incrementan
     * los marcadores.
     */
	@Test
	public void testScoreNullFighter() {
		scImperial.score(null);
		assertEquals(0,scImperial.getScore());
		scImperial.score(null);
		assertEquals(0,scImperial.getScore());
		scImperial.score(null);
		assertEquals(0,scImperial.getScore());
	}

	/* Inicialmente se comprueba que el marcador de scRebel es igual a sí mismo
	 * y menor que el de scImperial (aun teniendo los mismos score)
	 * Se aplica sobre uno de ellos un score que incremente su puntuación.
	 * Se comprueba ahora que el que ha aumentado, si es el que invoca a compareTo da un
	 * valor negativo y si es el menor el que lo invoca, da un valor positivo.
	 */
	@Test
	public void testCompareTo() {
		scImperial.score(1);
		scRebel.score(1);
		assertTrue(scRebel.compareTo(scRebel)==0);
		assertTrue(scRebel.compareTo(scImperial)>0);
		scImperial.score(1);
		assertTrue(scImperial.compareTo(scRebel)<0);
		for (int i=0; i<10; i++) scRebel.score(1);
		assertTrue(scRebel.compareTo(scImperial)<0);
		assertTrue(scImperial.compareTo(scRebel)>0);
		
	}
	
	/* Se pasan varios valores al WinsScore scImperial mediante
	 * el método score.
	 * Comprueba con getScore() que  los valores que se van obteniendo se van 
	 * acumulando sucesivamente. 
	 */
	//TODO
	@Test
	public void testScoreCraft() {
		assertEquals(0,scImperial.getScore());
		scImperial.score(10);
		assertEquals(0,scImperial.getScore());
		fail("Termina el test");
	}
	
	/* Se comprueba toString sobre el WinsScore scRebel y se comprueba que
	 * inicialmente es: "Player REBEL: 0"
	 * aplica el método score sobre scRebel varias veces con el valor
	 * Integer a 1. 
	 * Comprueba que la salida va cambiando de valor.
	 * Introduce luego un valor distinto de 1 y comprueba que no cambia el score.
	 */
	@Test
	public void testToString() {
			
		compareLines ("Player REBEL: 0",scRebel.toString());
	
		scRebel.score(0);
		compareLines ("Player REBEL: 0",scRebel.toString());
		fail("Termina el test");
	}
	
	/*************************
	 * FUNCIONES AUXILIARES
	 *************************/
		
	/* Compara dos String línea a línea.
	 * Parámetros: la cadena esperada, la cadena resultado.
	 */
	private void  compareLines(String expected, String result) {
		expected=expected.replaceAll("\n+","\n");
		result=result.replaceAll("\n+","\n");
		String exp[]=expected.split("\n");
		String res[]=result.split("\n");
		boolean iguales = true;
		if (exp.length!=res.length) 
			fail("Cadena esperada de tamaño ("+exp.length+") distinto a la resultante ("+res.length+")");
		for (int i=0; i<exp.length && iguales; i++) {
			if (! exp[i].contains("ERROR:")) {
				res[i]=res[i].trim();
				if (res[i].length()>1 && (res[i].charAt(1)=='|')) //Es un Board
						assertEquals("linea "+i, exp[i].trim(),res[i]);
				else
					assertEquals("linea "+i, exp[i].replaceAll(" ",""), res[i].replaceAll(" ","")); 
			} 
			else if (! res[i].contains("ERROR:"))
					fail("Error: el resultado esperado debía contener en la línea "+i+" la cadena 'ERROR:'");
		}
	}
}
