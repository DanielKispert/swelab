package application.presentation.view.impl;

import javax.swing.JButton;

import application.zug.impl.Spieler;

public class HeimatfeldButtonImpl extends JButton implements HeimatfeldButton {
	
	private static final long serialVersionUID = 1L;
	
	private final Spieler spieler;
	
	public HeimatfeldButtonImpl(Spieler spieler) {
		this.spieler = spieler;
	}

	public Spieler getSpieler() {
		return spieler;
	}

}
