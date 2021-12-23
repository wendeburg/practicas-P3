package model;

import static org.junit.Assert.*;

import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import model.exceptions.InvalidSizeException;
import model.exceptions.OutOfBoundsException;

public class BoardTest {
	Board tablero;
	
	@Before
	public void setUp() throws InvalidSizeException {
		 tablero = new Board(10);
	}
	
	@Test
	public void neighEsqSupDer() throws OutOfBoundsException {
		Coordinate esqSupDer = new Coordinate(9,0);
		
		TreeSet resp = tablero.getNeighborhood(esqSupDer);
		
		TreeSet<Coordinate> corr = new TreeSet<>();
		
		corr.add(new Coordinate (9, 1));
		corr.add(new Coordinate (8, 1));
		corr.add(new Coordinate (8, 0));
		
		assertEquals(corr, resp);
	}
	
	@Test
	public void neighEsqInfDer() throws OutOfBoundsException {
		Coordinate esqSupDer = new Coordinate(9,9);
		
		TreeSet resp = tablero.getNeighborhood(esqSupDer);
		
		TreeSet<Coordinate> corr = new TreeSet<>();
		
		corr.add(new Coordinate (9, 8));
		corr.add(new Coordinate (8, 8));
		corr.add(new Coordinate (8, 9));
		
		assertEquals(corr, resp);
	}
	
	@Test
	public void neighEsqSupIzq() throws OutOfBoundsException {
		Coordinate esqSupDer = new Coordinate(0,0);
		
		TreeSet resp = tablero.getNeighborhood(esqSupDer);
		
		TreeSet<Coordinate> corr = new TreeSet<>();
		
		corr.add(new Coordinate (1, 0));
		corr.add(new Coordinate (1, 1));
		corr.add(new Coordinate (0, 1));
		
		assertEquals(corr, resp);
	}
	
	@Test
	public void neighEsqInfIzq() throws OutOfBoundsException {
		Coordinate esqSupDer = new Coordinate(0,9);
		
		TreeSet resp = tablero.getNeighborhood(esqSupDer);
		
		TreeSet<Coordinate> corr = new TreeSet<>();
		
		corr.add(new Coordinate (1, 9));
		corr.add(new Coordinate (1, 8));
		corr.add(new Coordinate (0, 8));
		
		assertEquals(corr, resp);
	}
}
