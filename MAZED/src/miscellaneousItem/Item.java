package miscellaneousItem;

import java.util.Random;

public class Item {
	private String name;
	private int scoreValue;
	
	//Constructor
	public Item(String name) {
		switch (name) {
		
		case "key":
			this.name = "key";
			this.scoreValue = 0;
			break;
			
		case "trophy":
			this.name = "trophy";
			//Points is generated randomly
			Random rand = new Random();
			this.scoreValue = rand.nextInt(40)+11;
			break;
			
		case "hammer":
			this.name = "hammer";
			this.scoreValue = 0;
			break;
			
		case "Torch":
			this.name = "Torch";
			this.scoreValue = 0;
			break;
			
		case "trap":
			this.name = "Trap";
			this.scoreValue = 0;
			break;
			
		case "empty":
			this.name = "empty";
			this.scoreValue=0;
			break;
		default:
			System.err.println("Error Unknown Type of item");
		}
	}
	
	public int getScoreVal() {
		return this.scoreValue;
	}
	
	public String getName() {
		return this.name;
	}
}
