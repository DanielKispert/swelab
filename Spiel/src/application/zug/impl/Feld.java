package application.zug.impl;

public interface Feld {
	
	// Abfragen
	
	public Spieler getBesetztVon();
	
	// Services
	
	public boolean setBesetztVon(Spieler spieler);

}
