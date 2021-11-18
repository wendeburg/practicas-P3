package model.game;

import java.util.ArrayList;
import java.util.List;

import model.Board;
import model.Coordinate;
import model.Fighter;
import model.Ship;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;

public class GameShip extends Ship {

	public GameShip(String name, Side side) {
		super(name, side);
	}
	
	public boolean isFleetDestroyed() {
		for (Fighter f : fleet) {
			if (f != null) {
				if (!f.isDestroyed()) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private Fighter getFighter(int id) throws WrongFighterIdException {
		boolean foundFighter = false;
		
		for (Fighter f : fleet) {
			if (f != null) {
				if (f.getId() == id) {
					foundFighter = true;
					return f;
				}
			}
		}
	
		if (!foundFighter) {
			throw new WrongFighterIdException(id);
		}
		
		return null;
	}
	
	public List<Integer> getFightersId(String where) {
		List<Integer> list = new ArrayList<>();
		
		for (Fighter f : fleet) {
			if (f != null) {
				if (!f.isDestroyed()) {
					if (where.contentEquals("board")) {
						if (f.getPosition() != null) {
							list.add(f.getId());
						}
					}
					else if (where.contentEquals("ship")) {
						if (f.getPosition() == null) {
							list.add(f.getId());
						}
					}
					else {
						list.add(f.getId());
					}
				}
			}
		}
	
		return list;
	}
	
	public void launch(int id, Coordinate c, Board b) throws FighterAlreadyInBoardException, OutOfBoundsException, WrongFighterIdException {
		b.launch(c, getFighter(id));
	}

	public void patrol(int id, Board b) throws FighterNotInBoardException, WrongFighterIdException {
		b.patrol(getFighter(id));
	}
	
	public void improveFighter(int id, int qty, Board b) throws WrongFighterIdException {
		Fighter f = getFighter(id);
		
		try {
			b.removeFighter(f);
		}
		catch (FighterNotInBoardException e) {
			// No se hace nada.
		}

		
		f.addAttack(qty/2);
		
		if (qty % 2 != 0) {
			f.addShield(qty/2 + 1);
		}
		else {
			f.addShield(qty/2);
		}
	}

}
