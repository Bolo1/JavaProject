package player;
import miscellaneousItem.Item;
import userInterface.TextArea;

import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import maze.Maze;
public class Player {
	private String name;
	private Point2D currentPosition;
	public List<Point2D> positions = new  ArrayList<Point2D>() ;
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private int lineOfSight=2;//Only used in hard mode

	public Player(String playerName, Point2D playerPosition)
	{
		this.name = playerName;
		this.currentPosition = playerPosition;
		this.positions.add(this.currentPosition);
	}

	public String getName() {
		return (this.name);
	}

	public List<Point2D> getPosition() {
		return(this.positions);
	}

	public Point2D getPosition(int index) {
		return (this.positions.get(index));
	}


	public void setPosition(Point2D newPosition) {
		this.currentPosition = newPosition;
	}

	public void move(String direction, ArrayList <ArrayList<String>> mazeDescription) {

		switch (direction) {

		case "Up": {
			List <Point2D> newPos = new ArrayList <Point2D>();
			for (int i = 0; i<this.positions.size();i++)
			{
				newPos.add(new Point2D.Double(this.positions.get(i).getX(),this.positions.get(i).getY()));
			}
			int yPos = (int) this.currentPosition.getY();
			int xPos = (int) this.currentPosition.getX();
			xPos +=1;


			this.currentPosition.setLocation(xPos, yPos);
			newPos.add(new Point2D.Double(xPos,yPos));
			this.positions = newPos;
			break;}

		case "Left":{ 
			List <Point2D> newPos = new ArrayList <Point2D>();
			for (int i = 0; i<this.positions.size();i++)
			{
				newPos.add(new Point2D.Double(this.positions.get(i).getX(),this.positions.get(i).getY()));
			}
			int yPos = (int) this.currentPosition.getY();
			int xPos = (int) this.currentPosition.getX();
			yPos -=1;

			this.currentPosition.setLocation(xPos, yPos);
			newPos.add(new Point2D.Double(xPos,yPos));
			this.positions = newPos;
			break;}

		case "Right":{ 
			List <Point2D> newPos = new ArrayList <Point2D>();
			for (int i = 0; i<this.positions.size();i++)
			{
				newPos.add(new Point2D.Double(this.positions.get(i).getX(),this.positions.get(i).getY()));
			}
			int yPos = (int) this.currentPosition.getY();
			int xPos = (int) this.currentPosition.getX();
			
			yPos +=1;
			this.currentPosition.setLocation(xPos, yPos);
			newPos.add(new Point2D.Double(xPos,yPos));
			this.positions = newPos;
			break;}

		case "Down":{
			List <Point2D> newPos = new ArrayList <Point2D>();
			for (int i = 0; i<this.positions.size();i++)
			{
				newPos.add(new Point2D.Double(this.positions.get(i).getX(),this.positions.get(i).getY()));
			}
			int yPos = (int) this.currentPosition.getY();
			int xPos = (int) this.currentPosition.getX();

			xPos -=1;
			this.currentPosition.setLocation(xPos, yPos);
			newPos.add(new Point2D.Double(xPos,yPos));
			this.positions = newPos;
			break;}
		}
	}

	public boolean canMove(String dir, Maze myMaze, TextArea mazeText) {

		boolean canMove = false;
		switch (dir) {

		case "Up":{
			int indexWall = 2;
			canMove=checkWall(myMaze,indexWall,dir,mazeText);
			break;
		}
		case "Down":{
			int indexWall = 3;
			canMove=checkWall(myMaze,indexWall,dir,mazeText);
			break;
		}
		case "Left":{
			int indexWall = 5;
			canMove=checkWall(myMaze,indexWall,dir,mazeText);
			break;
		}

		case "Right":{
			int indexWall = 4;
			canMove=checkWall(myMaze,indexWall,dir,mazeText);
			break;
		}
		default :
			canMove =  false;
		}
		return canMove;
	}

	public ArrayList<Item> getInventory(){
		return (this.inventory);

	}

	public int getSight() {
		return lineOfSight;
	}

	public void increaseSight(int bonus) {
		this.lineOfSight+=bonus;
	}

	public void printScore(int steps,Maze MyMaze) {
		try (
				FileWriter fileW   = new FileWriter("HighScores.txt",true);
				BufferedWriter bufferW = new BufferedWriter(fileW);
				PrintWriter printerW = new PrintWriter(bufferW);){
			printerW.println(this.getName() + ","+MyMaze.name+","+steps);
		}catch(IOException i) {
			i.printStackTrace();
		}

	}

	public boolean checkWall(Maze myMaze, int indexWall, String dir, TextArea mazeText) {
		
		int indexX = (int) this.currentPosition.getX();
		int indexY = (int) this.currentPosition.getY();
		int sizeMaze = myMaze.getSize()[0];
		int index1 = 1 + indexX * (sizeMaze + 1) + indexY;
		String mazeElem = myMaze.getDescription(index1,indexWall);
		boolean canMove = false;
		
		switch (mazeElem) {
		
		case "wall":
			canMove =  false;
			break;

		case "no":
			canMove =  true;
			break;

		case "fake":
			canMove = true;
			break;

		case "breakable":
			canMove = searchInventory("Hammer");
			if (canMove) {
				switch (dir) {
				
				case "Up":{
					myMaze.modDescription(index1, indexWall, "no");
					int index2 = 1 + (indexX-1) * (sizeMaze + 1) + indexY;
					int index3 = 3;
					myMaze.modDescription(index2, index3, "no");
					break;
				}
				
				case "Down":{
					myMaze.modDescription(index1, indexWall, "no");
					int index2 = 1 + (indexX-1) * (sizeMaze + 1) + indexY;
					int index3 = 2;
					myMaze.modDescription(index2, index3, "no");
					break;
				}
				
				case "Left":{
					myMaze.modDescription(index1, indexWall, "no");
					int index2 = 1 + indexX * (sizeMaze + 1) + indexY-1;
					int index3 = 4;
					myMaze.modDescription(index2, index3, "no");
					break;
				}
				
				case "Right":{
					myMaze.modDescription(index1, indexWall, "no");
					int index2 = 1 + indexX * (sizeMaze + 1) + indexY+1;
					int index3 = 5;
					myMaze.modDescription(index2, index3, "no");
					break;
				}
			    }
				
			}else {
				//mazeText.updateText("You need a Hammer to break this wall");
			}
			
			break;

		case "door":
			canMove = searchInventory("Key");
			//if (!canMove) {
				//mazeText.clearText();
				//mazeText.updateText("You need a key to open this door");
			//}
			break;
		}
		return canMove;
	}

	public void pickUpItem(Item item2Pick, Maze myMaze) {
		this.inventory.add(item2Pick);
		int index1 = 1+(int)this.currentPosition.getX()*(myMaze.getSize()[0]+1)+(int) this.currentPosition.getY();
		int index2 = myMaze.getNElemDesc()-1;
		myMaze.modDescription(index1, index2, "no");
		
	}
	public boolean searchInventory(String type) {
		int sizeInv = this.inventory.size();
		boolean isInInv = false;
		for(int i=0;i<sizeInv;i++) {
			if (type == this.inventory.get(i).getType()) {
				isInInv = true;
			}
		}
		return isInInv;
	}
	public String displayInventory() {
		int sizeInv = this.inventory.size();
		String disp="";
		for(int i=0;i<sizeInv;i++) {
			disp = disp+this.inventory.get(i).getType()+", ";
		}
		return disp;
	}

	public int countInventory(String type) {
		int sizeInv = this.inventory.size();
		int counter = 0;
		for(int i=0;i<sizeInv;i++) {
			if (type == this.inventory.get(i).getType()) {
				counter++;
			}
		}
		return counter;
	}
	public int scoreInventory() {
		
		int sizeInv = this.inventory.size();
		int score = 0;
		for(int i=0;i<sizeInv;i++) {
			score = score + this.inventory.get(i).getScoreVal();
		}
		return score;
	}

	public void useItem(Item item2use) {
		switch (item2use.getType()) {

		case "Key":
			break;

		case "Trophy":
			break;

		case "Hammer":
			break;

		case "Light":
			this.increaseSight(2);
			break;

		case "Trap":
			for (int i= 0; i<5;i++) {
				this.positions.add(this.currentPosition);
			}
			break;
		}
	}
}
