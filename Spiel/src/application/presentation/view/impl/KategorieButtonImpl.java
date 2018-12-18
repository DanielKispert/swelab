package application.presentation.view.impl;

import javax.swing.JButton;

public class KategorieButtonImpl extends JButton implements KategorieButton {
	
	private final int id;
	
	public KategorieButtonImpl(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
