package model;

import java.util.Random;

public class Spielfeldmanager extends Manager {
	
	private final Spieler[] spieler;
	
	private final Kategorie[] kategorien;

	private final Feld[] felder;
	
	private int gew�rfelt;

	public Spielfeldmanager(Spielerinfos[] infos, Kategorie[] kategorien) {
		this.spieler = new Spieler[infos.length];
		for (int i = 0; i < infos.length; i++) {
			this.spieler[i] = new Spieler(infos[i].getName(), infos[i].getFarbe(), kategorien);
		}
		this.kategorien = kategorien;
		felder = new Feld[48];
		for (int i = 0; i < 48; i++) {
			this.felder[i] = new Feld();
		}
		w�rfle();
	}

	public Spieler[] getSpieler() {
		return spieler;
	}

	public Kategorie[] getKategorien() {
		return kategorien;
	}

	public Feld[] getFelder() {
		return felder;
	}
	
	/**
	 * w�rfelt
	 */
	public void w�rfle() {
		this.gew�rfelt = new Random().nextInt(6) + 1;
		notifyObservers();
	}

	public int getGew�rfelt() {
		return gew�rfelt;
	}

}
