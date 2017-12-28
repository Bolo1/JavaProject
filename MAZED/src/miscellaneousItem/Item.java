package miscellaneousItem;

import java.util.Random;

import player.Player;

public class Item {
	private String type;
	private int scoreValue;
	
	public Item(String type, Player player) {
		switch (type) {
		
		case "K":
			this.type ="Key";
			this.scoreValue=0;
			break;
			
		case "C":
			this.type ="Trophy";
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
			player.increaseSight(2);
			break;
			
		case "T":
			this.type ="Trap";
			this.scoreValue=-5;
			break;
		}
		
	}
	
	public String getType() {
		return this.type;
	}
	
	public int getScoreVal() {
		return this.scoreValue;
	}
}
