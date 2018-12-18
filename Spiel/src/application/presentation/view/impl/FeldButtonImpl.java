package application.presentation.view.impl;

import javax.swing.JButton;

public class FeldButtonImpl extends JButton implements FeldButton {
	
	private final int id;
	
	public FeldButtonImpl(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
