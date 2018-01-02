package userInterface;

import java.awt.Color;

import javax.swing.plaf.basic.BasicArrowButton;

public class MyArrowButton extends BasicArrowButton {

	private static final long serialVersionUID = 1L;
	public MyArrowButton(int arg0) {
		super(arg0);
		this.setBackground(Color.white);
		this.setForeground(Color.blue);

	}


}
