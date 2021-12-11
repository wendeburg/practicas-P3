package model.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

import model.Coordinate;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.NoFighterAvailableException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;

/**
 * Clase PlayerFile: Jugador que lee sus juagdas de un buffer.
 * @author Francisco Wendeburg - Y8281851W.
 */
public class PlayerFile implements IPlayer {
	
	/**
	 * Buffer de donde se leen las jugadas.
	 */
	private BufferedReader br;
	
	/**
	 * Nave del jugador.
	 */
	private GameShip ship;
	
	/**
	 * Board donde se desarrolla la partida.
	 */
	private GameBoard board;
	
	/**
	 * Constructor de PlayerFile.
	 * @param side Side del jugador (de su nave).
	 * @param br buffer de donde se leen las instrucciones.
	 */
	public PlayerFile(Side side, BufferedReader br) {
		Objects.requireNonNull(side);
		Objects.requireNonNull(br);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("PlayerFile ");
		
		if (side == Side.REBEL) {
			sb.append("REBEL");
		}
		else {
			sb.append("IMPERIAL");
		}
		
		sb.append(" Ship");
		
		ship = new GameShip(sb.toString(), side);
		
		this.br = br;
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
		try {
			String fighters = br.readLine();
			if (fighters == null) {
				ship.addFighters("");
			}
			else {
				ship.addFighters(fighters);
			}
		} catch (IOException e) {
			throw new RuntimeException();
		}
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
		try {
			String nextPlay = br.readLine();
			String StrArr[] = nextPlay.split(" ");
			
			if (StrArr.length > 0) {				
				if (StrArr[0].contentEquals("exit")) {
					return false;
				}
				else if (StrArr[0].contentEquals("improve")) {
					if (StrArr.length == 3) {
						int id = Integer.parseInt(StrArr[1]);
						int qty = Integer.parseInt(StrArr[2]);
						
						if (qty >= 100) {
							System.out.println("ERROR: la cantidad a mejorar debe ser menor a 100.");
						}
						else {
							try {
								ship.improveFighter(id, qty, board);
							}
							catch (WrongFighterIdException e) {
								System.out.println("ERROR: No se ha podido mejorar el caza: " + e.getMessage());
							}
						}
					}
					else {
						System.out.println("ERROR: la jugada improve lleva 3 parámetros.");
					}
				}
				else if (StrArr[0].contentEquals("patrol")) {
					if (StrArr.length == 2) {
						int id = Integer.parseInt(StrArr[1]);
		
						try {
							ship.patrol(id, board);
						}
						catch (WrongFighterIdException | FighterNotInBoardException e) {
							System.out.println("ERROR: El caza no puede patrullar: " + e.getMessage());
						}
					}
					else {
						System.out.println("ERROR: la jugada patrol lleva 2 parámetros.");
					}
				}
				else if (StrArr[0].contentEquals("launch")) {
					if (StrArr.length == 3) {
						int x = Integer.parseInt(StrArr[1]);
						int y = Integer.parseInt(StrArr[2]);
						
						try {
							int id = ship.getFirstAvailableFighter("").getId();
							
							ship.launch(id, new Coordinate(x, y), board);
						}
						catch (NoFighterAvailableException | FighterAlreadyInBoardException | OutOfBoundsException | WrongFighterIdException e) { // Mejorar.
							System.out.println("ERROR: " + e.getMessage());
						}
					}
					else if (StrArr.length == 4) {
						int x = Integer.parseInt(StrArr[1]);
						int y = Integer.parseInt(StrArr[2]);
						
						try {
							int id = Integer.parseInt(StrArr[3]);
							ship.launch(id, new Coordinate(x, y), board);
						}
						catch (NumberFormatException e) {
							int id;
							try {
								id = ship.getFirstAvailableFighter(StrArr[3]).getId();
								ship.launch(id, new Coordinate(x, y), board);
							} catch (NoFighterAvailableException | FighterAlreadyInBoardException | OutOfBoundsException | WrongFighterIdException e1) {
								System.out.println("ERROR " + e.getMessage());
							}
						}
						catch (FighterAlreadyInBoardException | OutOfBoundsException | WrongFighterIdException e) {
							System.out.println("ERROR: " + e.getMessage());
						}
					}
					else {
						System.out.println("ERROR: la jugada launch lleva 2 o 3 parámetros.");
					}
				}
				else {
					System.out.println("ERROR: jugada no reconocida.");
				}
			}
			else {
				System.out.println("ERROR: jugada no reconocida.");
			}
			
			return true;
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

}
