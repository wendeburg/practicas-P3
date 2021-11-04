package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import model.fighters.AWing;
import model.fighters.TIEBomber;
import model.fighters.TIEFighter;
import model.fighters.TIEInterceptor;
import model.fighters.XWing;
import model.fighters.YWing;

public class FightersPreTest {

	Ship rebelShip, imperialShip;
	Board board;
	
	@Before
	public void setUp() throws Exception {
		rebelShip = new Ship("Tydirium", Side.REBEL);
		imperialShip = new Ship("Lanzadera T-4a", Side.IMPERIAL);
		board = new Board(5);
	}

	@Test
	public void testCreateFighter() {
		Fighter rebel = FighterFactory.createFighter("AWing", rebelShip);
		assertTrue(rebel instanceof Fighter);
		assertTrue(rebel instanceof AWing);
		
		Fighter imperial = FighterFactory.createFighter("TIEBomber", imperialShip);
		assertTrue(imperial instanceof Fighter);
		assertTrue(imperial instanceof TIEBomber);	
	}
	
	@Test
	public void testCopy() {
		Fighter fx = FighterFactory.createFighter("XWing", rebelShip);
		Fighter fy = FighterFactory.createFighter("YWing", rebelShip);
		Fighter fa = FighterFactory.createFighter("AWing", rebelShip);
		Fighter fTIEB = FighterFactory.createFighter("TIEBomber", imperialShip);
		Fighter fTIEF = FighterFactory.createFighter("TIEFighter", imperialShip);
		Fighter fTIEI = FighterFactory.createFighter("TIEInterceptor", imperialShip);
		
		YWing fyy = (YWing)fy;
		YWing fycopy = (YWing) fy.copy();
		assertEquals("copy YWing",fy,fy.copy());
		assertEquals("copy YWing as YWing", fyy, fycopy);
		
		XWing fxx = (XWing)fx;
		XWing fxcopy = (XWing) fx.copy();
		assertEquals("copy XWing",fx,fx.copy());
		assertEquals("copy XWing as XWing",fxx,fxcopy);
		
		AWing faa = (AWing)fa;
		AWing facopy = (AWing) fa.copy();
		assertEquals("copy AWing",fa,fa.copy());
		assertEquals("copy AWing as AWing", faa, facopy);
		
		TIEBomber fB = (TIEBomber)fTIEB;
		TIEBomber fBcopy = (TIEBomber) fB.copy();
		assertEquals("copy TIEBomber",fB,fB.copy());
		assertEquals("copy TIEBomber as TIEBomber",fB,fBcopy);
		
		TIEFighter fF = (TIEFighter)fTIEF;
		TIEFighter fFcopy = (TIEFighter) fF.copy();
		assertEquals("copy TIEBomber",fF,fF.copy());
		assertEquals("copy TIEFighter as TIEFighter",fF,fFcopy);
		
		TIEInterceptor fI = (TIEInterceptor)fTIEI;
		TIEInterceptor fIcopy = (TIEInterceptor) fI.copy();
		assertEquals("copy TIEBomber",fI,fI.copy());
		assertEquals("copy TIEBomber as TIEBomber",fI,fIcopy);
	}
	
	@Test
	public void testTIEInterceptor() {
		Fighter ti = FighterFactory.createFighter("TIEInterceptor", imperialShip);
		
		assertEquals("velocity",145,ti.getVelocity());
		assertEquals("attack",85,ti.getAttack());
		assertEquals("shield",60,ti.getShield());
		assertEquals("symbol",'i',ti.getSymbol());
		
		Fighter fx = FighterFactory.createFighter("XWing", rebelShip);
		Fighter fy = FighterFactory.createFighter("YWing", rebelShip);
		Fighter fa = FighterFactory.createFighter("AWing", rebelShip);
		
		assertEquals("getDamage(50,XWing)",14,ti.getDamage(50, fx));
		assertEquals("getDamage(50,YWing)",28,ti.getDamage(50, fy));
		assertEquals("getDamage(50,XWing)",7,ti.getDamage(50, fa));
		
		// añade métodos con tests similares para los demás tipos de cazas
	}
}
