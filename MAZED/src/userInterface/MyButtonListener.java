package userInterface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyButtonListener implements ActionListener {
	private String wasPressed = "";//keep track of whether a button was pressed
	private String typePressed="";//keep track of the type of button pressed
	
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		
		//System.out.println(event.getSource());
		switch(event.getActionCommand()) {
		case "Up":
			this.wasPressed  = "Up";
			this.typePressed = "dir";
		break;
		
		case "Left":
			this.wasPressed  = "Left";
			this.typePressed = "dir";
		break;
		
		case "Right":
			this.wasPressed  = "Right";
			this.typePressed = "dir";
		break;
		
		case "Down":
			this.wasPressed  = "Down";
			this.typePressed = "dir";
		break;
		
		case "undo":
			this.wasPressed  = "undo";
			this.typePressed = "undo";
		break;
		case "AI1":
			this.wasPressed = "Dijkstra";
			this.typePressed = "AIsolving";
		break;
		case "AI2":
			this.wasPressed = "Tremaux";
			this.typePressed = "AIsolving";
		break;
		default:
			System.err.println("Unknown button was pressed");
		}
		
	}
	//to get which button was pressed
	public String getWasPressed() {
		return this.wasPressed;
	}
	//to reset wasPressed
	public void resetWasPressed() {
		this.wasPressed = "";
	}
	//to get the type of button that was pressed 
	public String getTypePressed() {
		return this.typePressed;
	}
	//to Reset the type
	public void resetTypePressed() {
		this.typePressed="";
	}
}
