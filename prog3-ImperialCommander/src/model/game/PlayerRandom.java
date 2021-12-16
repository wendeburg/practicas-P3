package model.game;

import java.util.List;
import java.util.Objects;

import model.Coordinate;
import model.RandomNumber;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.OutOfBoundsException;
import model.game.GameBoard;
import model.game.GameShip;
import model.game.exceptions.WrongFighterIdException;
import model.game.score.DestroyedFightersScore;
import model.game.score.WinsScore;

/**
 * Clase PlayerRandom: Jugador cuyas jugadas son aleatorias.
 * @author Francisco Wendeburg - Y8281851W.
 */
public class PlayerRandom implements IPlayer {
	/**
	 * Número de cazas que tiene el jugador.
	 */
	private int numFighters;
	/**
	 * Nave del jugador.
	 */
	private GameShip ship;
	
	/**
	 * Board donde se desarrolla la partida.
	 */
	private GameBoard board;
	
	/**
	 * Constructor de PlayerRandom.
	 * @param side Side del jugador (de su nave).
	 * @param numFighters número de cazas que va a tener la nave del jugador.
	 */
	public PlayerRandom(Side side, int numFighters) {
		Objects.requireNonNull(side);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("PlayerRandom ");
		
		if (side == Side.REBEL) {
			sb.append("REBEL");
		}
		else {
			sb.append("IMPERIAL");
		}
		
		sb.append(" Ship");
		
		ship = new GameShip(sb.toString(), side);
		this.numFighters = numFighters;
	}
	
	@Override
	public void setBoard(GameBoard gb) {
		Objects.requireNonNull(gb);
		
		board = gb;
	}

	@Override
	public GameShip getGameShip() {
		return ship;
	}

	@Override
	public void initFighters() {
		StringBuilder sb = new StringBuilder();
		String imperialTypes[] = {"TIEFighter", "TIEBomber", "TIEInterceptor"};
		String rebelTypes[] = {"XWing", "YWing", "AWing"};
		
		if (ship.getSide() == Side.REBEL) {
			for (int i = 0; i < rebelTypes.length; i++) {
				if (sb.length() != 0) {
					sb.append(":");
				}
				
				sb.append(RandomNumber.newRandomNumber(numFighters) + "/" + rebelTypes[i]);
			}
		}
		else {
			for (int i = 0; i < imperialTypes.length; i++) {
				if (sb.length() != 0) {
					sb.append(":");
				}
				
				sb.append(RandomNumber.newRandomNumber(numFighters) + "/" + imperialTypes[i]);
			}
		}
		
		ship.addFighters(sb.toString());
	}

	@Override
	public boolean isFleetDestroyed() {
		return ship.isFleetDestroyed();
	}

	@Override
	public String showShip() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(ship.toString() + "\n");
		sb.append(ship.showFleet());
		
		return sb.toString();
	}

	@Override
	public void purgeFleet() {
		ship.purgeFleet();
	}

	@Override
	public boolean nextPlay() {
		int option = RandomNumber.newRandomNumber(100);
		List<Integer> ids;
		int fighterId;
		
		if (option == 99) {
			return false;
		}
		else if (option >= 85 && option <= 98) {			
			ids = ship.getFightersId(null);
			
			if (ids.size() == 0) {
				System.out.println("ERROR: No se han encontrado cazas en la nave.");
			}
			else {
				fighterId = ids.get(RandomNumber.newRandomNumber(ids.size()));
				try {
					ship.improveFighter(fighterId, option, board);
				} catch (WrongFighterIdException e) {
					throw new RuntimeException();
				}
				
			}
		}
		else if (option > 24 && option <= 84) {
			ids = ship.getFightersId("ship");
			
			if (ids.size() == 0) {
				System.out.println("ERROR: No se han encontrado cazas que no estuvieran ya en el tablero.");
			}
			else {
				fighterId = ids.get(RandomNumber.newRandomNumber(ids.size()));
				int x = RandomNumber.newRandomNumber(board.getSize());
				int y = RandomNumber.newRandomNumber(board.getSize());
				
				try {
					ship.launch(fighterId, new Coordinate(x, y), board);
				} catch (FighterAlreadyInBoardException | OutOfBoundsException | WrongFighterIdException e) {				
					throw new RuntimeException();
				}
			}
		}
		else if (option <= 24) {
			ids = ship.getFightersId("board");
			
			if (ids.size() == 0) {
				System.out.println("ERROR: No se han encontrado cazas en el tablero.");
			}
			else {
				fighterId = ids.get(RandomNumber.newRandomNumber(ids.size()));
				try {
					ship.patrol(fighterId, board);
				} catch (FighterNotInBoardException | WrongFighterIdException e) {
					System.out.println(e.getMessage());
					throw new RuntimeException();
				}
			}
		}
		
		return true;
	}
	
	@Override
	public WinsScore getWinsScore() {
		return ship.getWinsScore();
	}
	
	@Override
	public DestroyedFightersScore getdestroyedFightersScore() {
		return ship.getDestroyedFightersScore();
	}
}
