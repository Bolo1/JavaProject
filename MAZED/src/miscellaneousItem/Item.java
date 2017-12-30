package miscellaneousItem;

import java.util.Random;

public class Item {
	private String type;
	private int scoreValue;
	private String name;
	
	public Item(String type) {
		switch (type) {
		
		case "K":
			this.type = "K";
			this.name = "Key";
			this.scoreValue = 0;
			break;
			
		case "C":
			this.type = "C";
			this.name = "Trophy";
			//Points is generated randomly
			Random rand = new Random();
			this.scoreValue = rand.nextInt(40)+11;
			break;
			
		case "H":
			this.type = "H";
			this.name = "Hammer";
			this.scoreValue = 0;
			break;
			
		case "L":
			this.type = "L";
			this.name = "Torch";
			this.scoreValue = 0;
			break;
			
		case "T":
			this.type = "T";
			this.type = "Trap";
			break;
			
		case "empty":
			this.type = "empty";
			this.name = "empty";
			this.scoreValue=0;
			break;
		default:
			System.err.println("Error Unknown Type of item");
		}
	}
	
	public String getType() {
		return this.type;
	}
	
	public int getScoreVal() {
		return this.scoreValue;
	}
	
	public String getName() {
		return this.name;
	}
}
