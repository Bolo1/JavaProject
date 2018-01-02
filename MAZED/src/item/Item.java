package item;

import java.util.Random;

public class Item {
	//Item has a name and a score value
	private String name;
	private int scoreValue;
	
	//Constructor
	public Item(String name) {
		switch (name) {//scoreValue is given depending on the name of the object
		
		case "trophy":
			this.name = name;
			//Points is generated randomly
			Random rand = new Random();
			this.scoreValue = rand.nextInt(40)+11;
			break;
			
		default:
			this.name = name;
			this.scoreValue = 0;
			break;
		}
	}
	//to get the scorevalue of the object
	public int getScoreVal() {
		return this.scoreValue;
	}
	//to get the name of the object
	public String getName() {
		return this.name;
	}
}
