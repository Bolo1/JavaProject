package maze;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class GameTimer implements ActionListener {
	private Timer timer;
	
	public void start() {
		timer.start();
	}
	
	public void stop() {
		timer.stop();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
