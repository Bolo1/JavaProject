package player;
import miscellaneousItem.Item;

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

	public String getPlayerName() {
		return (this.name);
	}

	public List<Point2D> getPlayerPosition() {
		return(this.positions);
	}

	public Point2D getPlayerPosition(int index) {
		return (this.positions.get(index));
	}


	public void setPlayerPosition(Point2D newPosition) {
		this.currentPosition = newPosition;
	}

	public void movePlayer(String direction, ArrayList <ArrayList<String>> mazeDescription) {

		switch (direction) {

		case "Up": {
			List <Point2D> newPos = new ArrayList <Point2D>();
			for (int i = 0; i<this.positions.size();i++)
			{
				newPos.add(new Point2D.Double(this.positions.get(i).getX(),this.positions.get(i).getY()));
			}
			double yPos = this.currentPosition.getY();
			double xPos = this.currentPosition.getX();
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
			double yPos = this.currentPosition.getY();
			double xPos = this.currentPosition.getX();
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
			double yPos = this.currentPosition.getY();
			double xPos = this.currentPosition.getX();

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
			double yPos = this.currentPosition.getY();
			double xPos = this.currentPosition.getX();

			xPos -=1;
			this.currentPosition.setLocation(xPos, yPos);
			newPos.add(new Point2D.Double(xPos,yPos));
			this.positions = newPos;
			break;}
		}
	}

	public boolean canMove(String dir, Maze myMaze) {

		int indexX = (int) this.currentPosition.getX();
		int indexY = (int) this.currentPosition.getY();
		int sizeMaze = myMaze.getSizeMaze()[0];
		int index = 1 + indexX * (sizeMaze + 1) + indexY;
		boolean canMove = false;
		switch (dir) {

		case "Up":{
			int indexWall = 2;
			String mazeElem = myMaze.getMazeDescription(index,indexWall);
			switch (mazeElem) {
			case "wall":
				canMove =  false;
				break;
			case "no":
				canMove =  true;
				break;
			}
			break;
		}


		case "Down":{
			int indexWall = 3;
			String mazeElem = myMaze.getMazeDescription(index,indexWall);
			switch (mazeElem) {
			case "wall":
				canMove =  false;
				break;
			case "no":
				canMove =  true;
				break;

			}
			break;
		}
		case "Left":{
			int indexWall = 5;
			String mazeElem = myMaze.getMazeDescription(index,indexWall);
			switch (mazeElem) {
			case "wall":
				canMove =  false;
				break;

			case "no":
				canMove =  true;
				break;
			}
			break;
		}

		case "Right":{
			int indexWall = 4;
			String mazeElem = myMaze.getMazeDescription(index,indexWall);
			switch (mazeElem) {
			case "wall":
				canMove =  false;
				break;
			case "no":
				canMove =  true;
				break;
			}
			break;
		}
		default :
			canMove =  false;
		}
		return canMove;
	}

	public ArrayList<Item> getPlayerInventory(){
		return (this.inventory);

	}
	public int getPlayerSight() {
		return lineOfSight;
	}
	
	public void printPlayerScore(int steps,Maze MyMaze) {
		try (
			FileWriter fileW   = new FileWriter("HighScores.txt",true);
			BufferedWriter bufferW = new BufferedWriter(fileW);
			PrintWriter printerW = new PrintWriter(bufferW);){
			printerW.println(this.getPlayerName() + ","+MyMaze.name+","+steps);
		}catch(IOException i) {
			i.printStackTrace();
		}
			
	}
}
