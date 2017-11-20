package player;
import miscellaneousItem.Item;
import java.util.ArrayList;
public class Player {
	private String name;
	private int[] position;
	private ArrayList<Item> inventory;
	private int lineOfSight=2;//Only used in hard mode

	
public Player(String playerName, int[] playerPosition)
	{
		this.name = playerName;
		this.position = playerPosition;
	}
public String getPlayerName() {
	return (this.name);
}
public int [] getPlayerPosition() {
	return (this.position);
}

public void setPlayerPosition(int[] newPosition) {
	this.position = newPosition;
}

public ArrayList<Item> getPlayerInventory(){
	return (this.inventory);
	
}
public int getPlayerSight() {
	return lineOfSight;
}
}
