package player;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import item.Item;

public class PlayerHistory {
	//keep track of positions,inventories,steps and scores obtained by the player
	private List<Point2D > position;
	private ArrayList<ArrayList<Item>> inventory;
	private ArrayList<Integer> step;
	private ArrayList<Integer> score;	

	public PlayerHistory() {//initalize the object with no value, content are initialize later when the setting of the game is finished
		//initialize the Arraylist
		this.position  = new ArrayList<Point2D> ();
		this.inventory = new ArrayList<ArrayList<Item>>();
		this.step      = new ArrayList<Integer> ();
		this.score 	   = new ArrayList<Integer> ();	

	}	
	//to get the full history of the position
	public List<Point2D> getPosition() {
		return this.position;
	}
	// to get a specific position
	public Point2D getPosition(int index) {
		return this.position.get(index);
	}
	//update the history of the positions
	private void updatePosition(Point2D playerPos){

		//clone List
		List <Point2D> newPos = new ArrayList <Point2D>();
		for (int i = 0; i<this.position.size();i++)
		{
			newPos.add(new Point2D.Double(this.position.get(i).getX(),this.position.get(i).getY()));
		}
		newPos.add(new Point2D.Double(playerPos.getX(),playerPos.getY()));
		//replace the list by the new updated cloned one
		this.position = newPos;	
	}
	// get full inventory history
	public ArrayList<ArrayList<Item>> getInv(){
		return inventory;
	}
	//get specific inventory of the list
	public ArrayList<Item> getInv(int index){
		return inventory.get(index);
	}
	// update inventory
	private void updateInv(ArrayList<Item> playerInventory) {
		this.inventory.add(playerInventory);
	}
	//get full step history
	public ArrayList<Integer> getStep(){
		return this.step;
	}
	//get a specific step
	public int getStep(int index){
		return this.step.get(index);
	}
	//update step
	private void updateStep(int playerSteps) {
		this.step.add(playerSteps);
	}
	//get the history of the score
	public ArrayList<Integer> getScore(){
		return this.score;
	}
	//get a specific score
	public int getScore(int index){
		return score.get(index);
	}
	//update score history
	private void updateScore(int playerScore) {
		this.score.add(playerScore);
	}
	//erase the last move from history
	public void eraseHistory() {
		this.position.remove(this.position.size()-1);
		this.inventory.remove(this.inventory.size()-1);
		this.step.remove(this.step.size()-1);
		this.score.remove(this.score.size()-1);
	}
	//update all history
	public void update(Point2D playerPos, ArrayList<Item> inventory, int nbOfSteps, int score) {
		this.updatePosition(playerPos);
		this.updateInv(inventory);
		this.updateStep(nbOfSteps);
		this.updateScore(score);
	}



}
