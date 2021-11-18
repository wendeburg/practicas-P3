package model.game;

public interface IPlayer {
	public void setBoard(GameBoard gb);
	
	public GameShip getGameShip();
	
	public void initFighters();
	
	public boolean isFleetDestroyed();
	
	public String showShip();
	
	public void purgeFleet();
	
	public boolean nextPlay();
}
