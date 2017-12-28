package miscellaneousItem;

import player.Player;

public class Item {
	String type;
	int scoreValue;
	
	public Item(String type, Player player) {
		switch (type) {
		
		case "K":
			this.type ="Key";
			this.scoreValue=0;
			break;
			
		case "C":
			this.type ="Trophy";
			this.scoreValue=15;
			break;
			
		case "H":
			this.type ="Hammer";
			this.scoreValue=0;
			break;
			
		case "L":
			this.type ="Light";
			this.scoreValue=0;
			player.increaseSight(2);
			break;
			
		case "T":
			this.type ="Trap";
			this.scoreValue=-5;
			break;
		}
		
	}

}
