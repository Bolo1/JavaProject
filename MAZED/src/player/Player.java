package player;
import miscellaneousItem.Item;

public class Player {
	public String name;
	public int[] position;
	public Item Object;
	public int lineOfSight=2;//Only used in hard mode
	
public Player(String playerName, int[] playerPosition)
	{
		name = playerName;
		position = playerPosition;
	}
	

}
