package miscellaneousItem;

import java.util.Random;

public class Item {
	private String type;
	private int scoreValue;
	
	public Item(String type) {
		switch (type) {
		
		case "K":
			this.type ="Key";
			this.scoreValue=0;
			break;
			
		case "C":
			this.type ="Trophy";
			//Points is generated randomly
			Random rand =new Random();
			this.scoreValue=rand.nextInt(40)+11;
			break;
			
		case "H":
			this.type ="Hammer";
			this.scoreValue=0;
			break;
			
		case "L":
			this.type ="Torch";
			this.scoreValue=0;
			break;
			
		case "T":
			this.type ="Trap";
			break;
			
		case "empty":
			this.type ="empty";
			this.scoreValue=0;
		}
	}
	
	public String getType() {
		return this.type;
	}
	
	public int getScoreVal() {
		return this.scoreValue;
	}
}
