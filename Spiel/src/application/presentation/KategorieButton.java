package application.presentation;

import javax.swing.JButton;

public class KategorieButton extends JButton {
	
	private final int id;
	
	public KategorieButton(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
