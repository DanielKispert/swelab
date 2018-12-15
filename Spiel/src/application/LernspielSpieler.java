package application;

import java.awt.Color;

public class LernspielSpieler implements IntLernspielSpieler {
	
	private final Heimatfelder heimatfelder;
	
	private final Wissensstandsanzeiger anzeiger;
	
	private final Color farbe;
	
	private final String name;
	
	public LernspielSpieler(String name, Color farbe, IntKategorie[] kategorien) {
		heimatfelder = new Heimatfelder();
		anzeiger = new Wissensstandsanzeiger(kategorien);
		this.name = name;
		this.farbe = farbe;
	}
	
	public Heimatfelder getHeimatfelder() {
		return heimatfelder;
	}

	public String getName() {
		return name;
	}

	public Color getFarbe() {
		return farbe;
	}
	
	public Wissensstandsanzeiger getWissensstandsanzeiger() {
		return anzeiger;
	}
	
	

}
