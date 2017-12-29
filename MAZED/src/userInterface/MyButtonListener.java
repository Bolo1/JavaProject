package userInterface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyButtonListener implements ActionListener {
	private String wasPressed = "";
	
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		
		//System.out.println(event.getSource());
		switch(event.getActionCommand()) {
		case "Up":this.wasPressed = "Up";
		break;
		case "Left":this.wasPressed = "Left";
		break;
		case "Right":this.wasPressed = "Right";
		break;
		case "Down":this.wasPressed = "Down";
		break;
		case "undo":this.wasPressed = "undo";
		break;
		case "AI1":this.wasPressed = "AI1";
		break;
		case "AI2":this.wasPressed = "AI2";
		break;
		}
		
//		if(event.getActionCommand() == "Up") {
//			this.wasPressed = "Up";
//			
//		}else if(event.getActionCommand() == "Left") {
//			this.wasPressed = "Left";
//			
//		}else if(event.getActionCommand() == "Right") {
//			this.wasPressed = "Right";
//			
//		}else if(event.getActionCommand() == "Right") {
//			this.wasPressed = "Down";
//			
//		}else {
//			System.err.println("Unrecognized Button");
//			
//		}
	}
	public String getWasPressed() {
		return this.wasPressed;
	}
	
	public void resetWasPressed() {
		this.wasPressed = "";
	}

}
