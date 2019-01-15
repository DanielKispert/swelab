package application.zug.impl;

import java.awt.Color;

public interface Spieler {
	
	// Abfragen	
	
	public String getName();
	
	public Color getFarbe();

	public HeimatfelderImpl getHeimatfelder();
	
	public WissensstandsanzeigerImpl getWissensstandsanzeiger();
	

}
