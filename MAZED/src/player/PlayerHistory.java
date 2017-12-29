package player;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import miscellaneousItem.Item;

public class PlayerHistory {
	
	private List<Point2D > position 	  = new ArrayList<Point2D> ();
	private ArrayList<ArrayList<Item>> inventory = new ArrayList<ArrayList<Item>>();
	private ArrayList<Integer> step       = new ArrayList<Integer> ();
	private ArrayList<Integer> score 	  = new ArrayList<Integer> ();	
	
	
	public PlayerHistory(Point2D initPos, ArrayList<Item> initInventory, int initNbOfSteps, int initScore) {
		this.position.add(initPos);
		this.inventory.add(initInventory);
		this.step.add(initNbOfSteps);
		this.score.add(initScore);		
	}
	
	public List<Point2D> getPosition() {
		return this.position;
	}
	
	public Point2D getPosition(int index) {
		return this.position.get(index);
	}
	
	public void updatePosition(Point2D playerPos){
		
		List <Point2D> newPos = new ArrayList <Point2D>();
		for (int i = 0; i<this.position.size();i++)
		{
			newPos.add(new Point2D.Double(this.position.get(i).getX(),this.position.get(i).getY()));
		}
		newPos.add(new Point2D.Double(playerPos.getX(),playerPos.getY()));
		//this.positions = newPos;
		
		this.position = newPos;	
	}
	
	public ArrayList<Item> getInv(int index){
		return inventory.get(index);
	}
	
	public void updateInv(ArrayList<Item> playerInventory) {
		this.inventory.add(playerInventory);
	}
	
	public Integer getStep(int index){
		return step.get(index);
	}
	
	public void updateStep(int playerSteps) {
		this.step.add(playerSteps);
	}
	
	public Integer getScore(int index){
		return score.get(index);
	}
	
	public void updateScore(int playerScore) {
		this.score.add(playerScore);
	}
	
	public void update(Point2D playerPos, ArrayList<Item> inventory, int nbOfSteps, int score) {
		this.updatePosition(playerPos);
		this.updateInv(inventory);
		this.updateStep(nbOfSteps);
		this.updateScore(score);
	}

	

}
