package application;

import java.util.Random;

public class Spielfeldmanager extends Manager {
	
	private final Spieler[] spieler;
	
	private final Kategorie[] kategorien;

	private final Feld[] felder;
	
	private int gewürfelt;
	
	private int anzahlWürfe;
	
	private Spieler amZug;
	
	private Zugzustand zugzustand;
	
	private int idZugfeld;
	
	private Kategorie ausgewählteKategorieFürFrage;
	
	private Spieler gefragterSpieler;
	
	private Frage frage;

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
		//spieler 1 always starts first
		amZug = spieler[0];
		zugzustand = Zugzustand.DARF_NOCH_WÜRFELN;
		gewürfelt = -1;
		idZugfeld = -1;
		anzahlWürfe = 0;
		ausgewählteKategorieFürFrage = null;
		gefragterSpieler = null;
		frage = null;
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
	 * Service
	 */
	public void würfle() {
		// darf er noch würfeln?
		if (zugzustand.equals(Zugzustand.DARF_NOCH_WÜRFELN) && getDarfNochWürfeln()) {
			anzahlWürfe++;
			this.gewürfelt = new Random().nextInt(6) + 1;

			// nächsten Zustand ermitteln
			if (amZug.getHeimatfelder().sindLeer()) {
				// der Spieler muss jetzt eine Figur auf den Feldern ziehen
				zugzustand = Zugzustand.MUSS_FELD_ZIEHEN;
			} else {
				if (this.gewürfelt == 6 && kannSpielerAufStartfeldZiehen()) {
					//Spieler muss eine Figur aus dem Heimatfeld holen
					zugzustand = Zugzustand.MUSS_AUS_HEIMATFELD_ZIEHEN;
				} else {
					if (!amZug.getHeimatfelder().sindVoll()) {
						zugzustand = Zugzustand.MUSS_FELD_ZIEHEN;
					} else {
						if (anzahlWürfe >= 3) {
							//nächster Spieler dran
							beendeZug();
						} 
					}
				}
			}			
			notifyObservers();
			
		} else {
			// Spieler darf nicht würfeln, tue nichts
		}
	}
	
	private int getStartfeldVonSpieler(Spieler spieler) {
		int startfeldId = -1;
		for (int i = 0; i < this.spieler.length; i++) {
			if (this.spieler[i].equals(spieler)) {
				switch (i) {
					case 0:
						startfeldId = 0;
						break;
					case 1:
						startfeldId = 12;
						break;
					case 2:
						startfeldId = 24;
						break;
					case 3:
						startfeldId = 36;
						break;
				}
				break;
			}
		}
		return startfeldId;
	}
	
	private boolean kannSpielerAufStartfeldZiehen() {
		int startfeldId = getStartfeldVonSpieler(amZug);
		if (felder[startfeldId].getBesetztVon() == null || !felder[startfeldId].getBesetztVon().equals(amZug)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Service
	 */
	public void frageRichtigBeantwortet() {
		
	}
	
	/**
	 * Service
	 */
	public void frageFalschBeantwortet() {
		
	}
	
	/**
	 * Service zieht eine Figur von einem Feld
	 */
	public void zieheVonFeld(int id) {
		// ist das Feld vom aktiven Spieler besetzt
		if (felder[id].getBesetztVon() != null && felder[id].getBesetztVon().equals(amZug)) {
			this.zugzustand = Zugzustand.FIGUR_AUS_FELD_AUSGEWÄHLT;
			this.idZugfeld = id;
			notifyObservers();
		}	
	}
	
	/**
	 * Service
	 */
	public void zieheVonHeimatfeld(Spieler spieler) {
		if (amZug.equals(spieler) && kannSpielerAufStartfeldZiehen()) {
			this.zugzustand = Zugzustand.FIGUR_AUS_HEIMATFELD_AUSGEWÄHLT;
			notifyObservers();
		}	
	}
	
	/**
	 * Service
	 * setzt eine Kategorie und stellt eine Frage daraus
	 */
	public void wähleKategorieFürFrage(int id) {
		if (this.zugzustand.equals(Zugzustand.FRAGE_KATEGORIE_AUSWÄHLEN)) {
			this.ausgewählteKategorieFürFrage = this.getKategorien()[id];
			this.zugzustand = Zugzustand.ERSTE_FRAGE_BEANTWORTEN;
			notifyObservers();
		}
		
	}
	
	/**
	 * TODO
	 * 1. ziehender Spieler klickt auf Kategorie
	 * 2. zufällige Frage wird angezeigt
	 * 3. Frage wird richtig oder falsch beantwortet
	 * 4. Richtig: Fragender Spieler kommt auf Feld, gefragter auf Startfeld oder Heimatfeld wenn Startfeld voll
	 * 5. Falsch: gefragter kommt auf Heimatfeld, fragender Spieler kriegt Frage gestellt
	 * 6. Frage richtig: Figur kommt auf Feld. Falsch: Figur kommt auf Heimatfeld
	 */
	private void stelleFrage(Spieler gefragterSpieler) {
		//TODO
		this.zugzustand = Zugzustand.FRAGE_KATEGORIE_AUSWÄHLEN;
		this.gefragterSpieler = gefragterSpieler;
		notifyObservers();
	}
	
	/**
	 * Service
	 */
	public void zieheAufFeld(int id) {
		Spieler besetzenderSpieler = this.felder[id].getBesetztVon();
		if (this.zugzustand.equals(Zugzustand.FIGUR_AUS_HEIMATFELD_AUSGEWÄHLT)) {		
			if (id == getStartfeldVonSpieler(amZug)) {
				if (besetzenderSpieler == null) {
					amZug.getHeimatfelder().entnehmeFigur();
					// Versuche, die Figur auf das Startfeld des Spielers zu setzen, muss gelingen da Feld leer ist
					this.felder[id].setBesetztVon(amZug);
					beendeZug();
					notifyObservers();	
				} else if (besetzenderSpieler.equals(amZug)) {
					// jemand anders steht auf dem Startfeld, starte Fragerunde
					stelleFrage(besetzenderSpieler);
				} else {
					// Es kann nicht eintreten, dass derselbe Spieler auf dem Startfeld steht, da der Fall beim Würfeln überprüft wird
				}
			}			
		} else if (this.zugzustand.equals(Zugzustand.FIGUR_AUS_FELD_AUSGEWÄHLT)) {
			//Spieler kann sich dorthin bewegen
			if (idZugfeld != -1 && (idZugfeld + gewürfelt)%48 == id) {				
				if (besetzenderSpieler == null) {
					// Feld ist frei
					this.felder[idZugfeld].setBesetztVon(null);
					this.felder[id].setBesetztVon(amZug);
					beendeZug();
					notifyObservers();
				} else if (besetzenderSpieler.equals(amZug)) {
					// Feld besetzt von Gegner
					stelleFrage(besetzenderSpieler);
				} else {
					// Feld besetzt vom Spieler selbst, andere Figur auswählen
					this.zugzustand = Zugzustand.MUSS_FELD_ZIEHEN;
					notifyObservers();
					
				}
			}
		
			
		}
		
	}
	
	/**
	 * nächster Spieler am Zug
	 * updated nicht view & controller, notifyObservers muss gecallt werden
	 */
	private void beendeZug() {
		//set nächster Spieler
		for (int i = 0; i < 4; i++) {
			if (this.spieler[i].equals(amZug)) {
				amZug = this.spieler[(i+1)%4];
				zugzustand = Zugzustand.DARF_NOCH_WÜRFELN;
				gewürfelt = -1;
				idZugfeld = -1;
				anzahlWürfe = 0;
				break;
			}
		}
	}

	public int getGewürfelt() {
		return gewürfelt;
	}
	
	public Spieler getSpielerAmZug() {
		return this.amZug;
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
	
	public Zugzustand getZugZustand() {
		return this.zugzustand;
	}

	/**
	 * der Spieler darf würfeln, wenn: - er noch nicht gewürfelt hat und mindestens
	 * eine Figur im Feld hat - er noch nicht 3 mal gewürfelt hat (und auch keine 6)
	 * und alle Figuren noch im Heimatfeld sind
	 */
	public boolean getDarfNochWürfeln() {
		return (anzahlWürfe == 0) || (amZug.getHeimatfelder().sindVoll() && anzahlWürfe < 3 && gewürfelt != 6);
	}

	public String getAusgewählteKategorienameFürFrage() {
		return ausgewählteKategorieFürFrage.getName();
	}
	
	public String getAktuellerFragetext() {
		return this.frage.getFragetext();
	}
	
	public String getAktuellerLösungstext() {
		return this.frage.getLösungstext();
	}

}
