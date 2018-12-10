package model;

import java.util.Random;

public class Spielfeldmanager extends Manager {
	
	private final Spieler[] spieler;
	
	private final Kategorie[] kategorien;

	private final Feld[] felder;
	
	private int gewürfelt;
	
	private Spieler amZug;

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
		würfle();
		//spieler 1 always starts first
		amZug = spieler[0];
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
	 * würfelt
	 */
	public void würfle() {
		this.gewürfelt = new Random().nextInt(6) + 1;
		notifyObservers();
	}

	public int getGewürfelt() {
		return gewürfelt;
	}
	
	public String getNameVonSpielerAmZug() {
		return this.amZug.getName();
	}
	
	public String getWissensstandVon(Spieler spieler) {
		for (Spieler player: this.spieler) {
			if (spieler.equals(player)) {
				int[] ws = player.getWissensstand();
				return ws[0] + "/" + ws[1] + "/" + ws[2] + "/" + ws[3];
			}
		}
		return null;
	}

}
