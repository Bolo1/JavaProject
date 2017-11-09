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
		 FontMetrics fm = g.getFontMetrics(f);
		  
		    int cx = 300; int cy = 100;
		    g.setFont(f);
		    g.drawString("Hello, ", cx, cy);
		    cx += fm.stringWidth("Hello, ");
		    g.drawString("World!", cx, cy);
	}
	
	
	
	
  /*@Override 
	
	void paintComponent(){
		
	}
*/
}
