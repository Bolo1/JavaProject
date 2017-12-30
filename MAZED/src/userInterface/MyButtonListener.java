package userInterface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyButtonListener implements ActionListener {
	private String wasPressed = "";
	private String typePressed="";
	
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
			this.wasPressed = "AI2";
			this.typePressed = "AIsolving";
		break;
		default:
			System.err.println("Unknown button was pressed");
		}
		
	}
	public String getWasPressed() {
		return this.wasPressed;
	}
	
	public void resetWasPressed() {
		this.wasPressed = "";
	}
	public String getTypePressed() {
		return this.typePressed;
	}
	public void resetTypePressed() {
		this.typePressed="";
	}
}
