package userinterface;

import javax.swing.JButton;

public class FeldButton extends JButton {
	
	public static final int SIZE = 40;
	
	private final int id;
	
	public FeldButton(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
