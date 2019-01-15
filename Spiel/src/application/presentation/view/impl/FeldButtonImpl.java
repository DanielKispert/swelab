package application.presentation.view.impl;

import javax.swing.JButton;

public class FeldButtonImpl extends JButton implements FeldButton {
	
	private static final long serialVersionUID = 1L;
	
	private final int id;
	
	public FeldButtonImpl(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
