package application.presentation;

import javax.swing.JButton;

import application.Spieler;

public class HeimatfeldButton extends JButton {
	
	private final Spieler spieler;
	
	public HeimatfeldButton(Spieler spieler) {
		this.spieler = spieler;
	}

	public Spieler getSpieler() {
		return spieler;
	}

}
