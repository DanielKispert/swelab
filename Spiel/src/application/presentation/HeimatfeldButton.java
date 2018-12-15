package application.presentation;

import javax.swing.JButton;

import application.IntLernspielSpieler;

public class HeimatfeldButton extends JButton {
	
	private final IntLernspielSpieler spieler;
	
	public HeimatfeldButton(IntLernspielSpieler spieler) {
		this.spieler = spieler;
	}

	public IntLernspielSpieler getSpieler() {
		return spieler;
	}

}
