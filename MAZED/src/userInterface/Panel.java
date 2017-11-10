package userInterface;
import javax.swing.*;
import java.awt.*;

 class Panel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Font f = new Font ("Candara", Font.BOLD, 14);
		g.setFont(f);
		
	}
	
	
	
	
  /*@Override 
	
	void paintComponent(){
		
	}
*/
}
