package userinterface;

import javax.swing.JButton;

import model.Spieler;

public class HeimatfeldButton extends JButton {
	
	private final Spieler spieler;
	
	public HeimatfeldButton(Spieler spieler) {
		this.spieler = spieler;
	}

	public Spieler getSpieler() {
		return spieler;
	}

}
