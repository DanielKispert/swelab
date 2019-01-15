package application.zug.impl;

import java.awt.Color;

public class SpielerImpl implements Spieler {
	
	private final HeimatfelderImpl heimatfelder;
	
	private final WissensstandsanzeigerImpl anzeiger;
	
	private final Color farbe;
	
	private final String name;
	
	public SpielerImpl(String name, Color farbe, Kategorie[] kategorien) {
		heimatfelder = new HeimatfelderImpl();
		anzeiger = new WissensstandsanzeigerImpl(kategorien);
		this.name = name;
		this.farbe = farbe;
	}
	
	public HeimatfelderImpl getHeimatfelder() {
		return heimatfelder;
	}

	public String getName() {
		return name;
	}

	public Color getFarbe() {
		return farbe;
	}
	
	public WissensstandsanzeigerImpl getWissensstandsanzeiger() {
		return anzeiger;
	}
	
	

}
