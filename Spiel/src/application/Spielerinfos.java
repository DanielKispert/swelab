package application;

import java.awt.Color;

public class Spielerinfos {

	private String name;

	private Color farbe;

	public Spielerinfos(String name, Color farbe) {
		this.name = name;
		this.farbe = farbe;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getFarbe() {
		return farbe;
	}

	public void setFarbe(Color farbe) {
		this.farbe = farbe;
	}
}