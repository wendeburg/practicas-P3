package model.game.score;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.FighterFactory;
import model.Side;
import model.game.GameShip;
import model.game.IPlayer;
import model.game.PlayerRandom;

public class RankingPreTest {

	Ranking<WinsScore> winsRanking;
	Ranking<DestroyedFightersScore> destroyedRanking;
	WinsScore winsScore;
	DestroyedFightersScore destroyedScore;
	//IPlayer playerJulia, playerRaul;
	IPlayer imperialPlayer, rebelPlayer;
	//IPlayer playerLaura, playerSimon;
	GameShip rebelShip, imperialShip;
	final String kIMPERIAL_FIGHTERS[]= {"XWing","YWing","AWing"};
	final String kREBEL_FIGHTERS[]= {"TIEFighter", "TIEBomber", "TIEInterceptor"};
	final String SRANKING1 = "DestroyedFighters ranking =  | Player IMPERIAL: 0 | \n" + 
			"Wins ranking =  | Player IMPERIAL: 0 | ";
	final String SRANKING2 = "DestroyedFighters ranking =  | Player REBEL: 4455 | Player IMPERIAL: 1770 | \n" + 
			"Wins ranking =  | Player IMPERIAL: 10 | Player REBEL: 9 |";
	
	@Before
	public void setUp() throws Exception {
		destroyedRanking = new Ranking<DestroyedFightersScore>();
		winsRanking = new Ranking<WinsScore>();
		imperialPlayer= new PlayerRandom(Side.IMPERIAL, 20);
		rebelPlayer = new PlayerRandom(Side.REBEL, 20);
		rebelShip = new GameShip("Rebel Ship", Side.REBEL);
		imperialShip = new GameShip("Imperial Ship", Side.IMPERIAL);
	}

	/* Se comprueba que el constructor crea los set y que están vacíos */
	@Test
	public void testRanking() {
		assertNotNull(destroyedRanking.getSortedRanking());
		assertNotNull(winsRanking.getSortedRanking());
		assertTrue(destroyedRanking.getSortedRanking().isEmpty());
		assertTrue(winsRanking.getSortedRanking().isEmpty());
	}

	/*
	 * Se crea para Imperial los 2 tipos de Scores y se añaden a los distintos Ranking. Se 
	 * comprueba que la salida de los ranking coincide con SRANKING1.
	 * Imperial consigue 10 victorias y destruye 4 XWing, 3 YWing y 3 AWing. 
	 * Realiza lo mismo ahora para Rebel, consiguiendo 9 victorias y destruyendo
	 * 8 TIEFighter, 8 TIEBomber y 7 TIEInterceptor.
	 * Comprueba que la salida  coincide con SRANKING2
	 * 
	 */
	//TODO
	@Test
	public void testAddScore() {
		//Iniciamos marcadores para Julia
		winsScore = new WinsScore(Side.IMPERIAL);
		destroyedScore = new DestroyedFightersScore(Side.IMPERIAL);
				
		//Los añadimos al ranking
		destroyedRanking.addScore(destroyedScore);
		winsRanking.addScore(winsScore);
		compareLines(SRANKING1, rankingsToString());
				
		//Modificamos los marcadores winsScore y destroyedScore de IMPERIAL
		for (int i=0; i<10; i++) {
				winsScore.score(1);
				destroyedScore.score(FighterFactory.createFighter(kREBEL_FIGHTERS[i%3], rebelShip));
		}
		destroyedRanking.addScore(destroyedScore);
		winsRanking.addScore(winsScore);
		
		WinsScore winsScoreReb = new WinsScore(Side.REBEL);
		DestroyedFightersScore destroyedScoreReb = new DestroyedFightersScore(Side.REBEL);
		for (int i=0; i<9; i++) {
			winsScoreReb.score(1);
		}
		for (int i=0; i<7; i++) {
			destroyedScoreReb.score(FighterFactory.createFighter("TIEInterceptor", imperialShip));
		}
		for (int i=0; i<8; i++) {
			destroyedScoreReb.score(FighterFactory.createFighter("TIEFighter", imperialShip));
			destroyedScoreReb.score(FighterFactory.createFighter("TIEBomber", imperialShip));
		}
		destroyedRanking.addScore(destroyedScoreReb);
		winsRanking.addScore(winsScoreReb);
		
		compareLines(SRANKING2, rankingsToString());
	}
	
	/* GetWinner debe lanzar RuntimeException cuando el ranking está vacío.
	 */
	@Test
	public void testGetWinnerEmpty() {
		try {
			destroyedRanking.getWinner();
			fail("ERROR: Debía haber lanzado RuntimeException por Ranking vacío");
		} catch (RuntimeException e) {} //ok
	}
	
	/* Inicia los dos tipos de scores para IMPERIAL. Añade varias victorias y Fighters destruídos en sus 
	 * respectivos marcadores. Añádelos a sus respectivos rankings.
	 * Haz lo mismo para REBEL. 
	 * Comprueba luego que los getWinner son efectivamente los primeros esperados (de más valor)
	 * en ambos rankings
	 */
	//TODO
	@Test
	public void testGetWinner() {
		//Iniciamos marcadores para Imperial
		winsScore = new WinsScore(Side.IMPERIAL);
		destroyedScore = new DestroyedFightersScore(Side.IMPERIAL);
		for (int i=0; i<2000; i++) {
			winsScore.score(1);
			destroyedScore.score(FighterFactory.createFighter(kREBEL_FIGHTERS[i%3], rebelShip));
		}
		destroyedRanking.addScore(destroyedScore);
		winsRanking.addScore(winsScore);
		
		//Iniciamos marcadores para Rebel
		WinsScore winsScoreReb = new WinsScore(Side.REBEL);
		DestroyedFightersScore destroyedScoreReb = new DestroyedFightersScore(Side.REBEL);
		for (int i=0; i<2001; i++) {
			winsScoreReb.score(1);
		}
		for (int i=0; i < 200; i++) {
			destroyedScoreReb.score(FighterFactory.createFighter(kIMPERIAL_FIGHTERS[i%2], imperialShip));
		}
		destroyedRanking.addScore(destroyedScoreReb);
		winsRanking.addScore(winsScoreReb);
		
		assertEquals(winsScoreReb, winsRanking.getWinner());
		assertEquals(destroyedScore, destroyedRanking.getWinner());	
}

	
	/*************************
	 * FUNCIONES AUXILIARES
	 *************************/
	
	/* Construye la salida como String, con los distintos Ranking */
	private String rankingsToString () {
		StringBuilder ps = new StringBuilder();
		ps.append("DestroyedFighters ranking = "+destroyedRanking.toString()+"\n");
		ps.append("Wins ranking = "+ winsRanking.toString()+"\n");
		return ps.toString();
	}
	
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
