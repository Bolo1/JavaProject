package userInterface;
import javax.swing.*;


public class TextArea extends JTextArea {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	TextArea(){
		setEditable(false);
	

	}
	
	public void updateText(String newText) {
		//this.setText(newText);
		if (this.getText()==null)
		{
			this.setText(newText);
		}else {
			this.append(newText);
	
		}
	}
	 public void clearText() {
		 this.setText(null);
	 }
	 
}
