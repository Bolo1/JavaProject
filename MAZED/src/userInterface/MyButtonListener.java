package userInterface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyButtonListener implements ActionListener {
	private String wasPressed = "";
	
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		
		//System.out.println(event.getSource());
		if(event.getActionCommand() == "Up") {
			this.wasPressed = "Up";
			
		}else if(event.getActionCommand() == "Left") {
			this.wasPressed = "Left";
			
		}else if(event.getActionCommand() == "Right") {
			this.wasPressed = "Right";
			
		}else if(event.getActionCommand() == "Down") {
			this.wasPressed = "Down";
			
		}else {
			System.err.println("Unrecognized Button");
			
		}
	}
	public String getWasPressed() {
		return this.wasPressed;
	}
	
	public void resetWasPressed() {
		this.wasPressed = "";
	}

}
