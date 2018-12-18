package application.zug.impl;

import java.util.Random;

import application.Spielerinfos;
import application.statemachine.port.State;
import application.statemachine.port.StateMachine;
import application.statemachine.port.StateMachinePort;
import application.zug.port.ZugManagement;

public class ZugMachen implements ZugManagement {
	
	private final Spieler[] spieler;
	
	private final Kategorie[] kategorien;

	private final Feld[] felder;
	
	private int gewürfelt;
	
	private int anzahlWürfe;
	
	private Spieler amZug;
	
	private StateMachine stateMachine;
	
	private int idZugfeld;
	
	private int idZugziel;
	
	private Kategorie ausgewählteKategorieFürFrage;
	
	private Spieler gefragterSpieler;
	
	private Frage frage;
	
	private Spieler hatGewonnen;

	public ZugMachen(Spielerinfos[] infos, Kategorie[] kategorien) {
		this.spieler = new SpielerImpl[infos.length];
		for (int i = 0; i < infos.length; i++) {
			this.spieler[i] = new SpielerImpl(infos[i].getName(), infos[i].getFarbe(), kategorien);
		}
		this.kategorien = kategorien;
		felder = new FeldImpl[48];
		for (int i = 0; i < 48; i++) {
			this.felder[i] = new FeldImpl();
		}
		//spieler 1 always starts first
		amZug = spieler[0];
		gewürfelt = -1;
		idZugfeld = -1;
		anzahlWürfe = 0;
		ausgewählteKategorieFürFrage = null;
		gefragterSpieler = null;
		frage = null;
		hatGewonnen = null;
	}

	public ZugMachen(StateMachinePort stateMachinePort) {
		this.stateMachine = stateMachinePort.stateMachine();
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
		if (getZustand().equals(State.S.DARF_NOCH_WÜRFELN) && getDarfNochWürfeln()) {
			anzahlWürfe++;
			this.gewürfelt = new Random().nextInt(6) + 1;

			// nächsten Zustand ermitteln
			if (amZug.getHeimatfelder().sindLeer()) {
				// der Spieler muss jetzt eine Figur auf den Feldern ziehen
				setZustand(State.S.MUSS_FELD_ZIEHEN);
			} else {
				if (this.gewürfelt == 6 && kannSpielerAufStartfeldZiehen()) {
					//Spieler muss eine Figur aus dem Heimatfeld holen
					
					setZustand(State.S.MUSS_AUS_HEIMATFELD_ZIEHEN);
				} else {
					if (!amZug.getHeimatfelder().sindVoll()) {
						setZustand(State.S.MUSS_FELD_ZIEHEN);
					} else {
						if (anzahlWürfe >= 3) {
							//nächster Spieler dran
							beendeZug();
						} 
					}
				}
			}
			
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
	

	
	private void erhöheWissensstandVonSpieler() {
		if (!this.gefragterSpieler.getWissensstandsanzeiger().erhöhe(this.ausgewählteKategorieFürFrage)
				&& !this.gefragterSpieler.getWissensstandsanzeiger().erhöheNächsten()) {
			setZustand(State.S.GEWONNEN);
		}
	}
	
	/**
	 * Service
	 */
	public void frageRichtigBeantwortet() {
		if (this.getZustand().equals(State.S.ERSTE_FRAGE_BEANTWORTEN)) {
			erhöheWissensstandVonSpieler();
			// fragender Spieler (am Zug) kommt auf das Feld, gefragter Spieler auf sein Startfeld oder sein Heimatfeld falls das Startfeld belegt ist
			this.felder[idZugziel].setBesetztVon(amZug);
			if (this.felder[getStartfeldVonSpieler(gefragterSpieler)].getBesetztVon() == null) {
				this.felder[getStartfeldVonSpieler(gefragterSpieler)].setBesetztVon(gefragterSpieler);
			} else {
				gefragterSpieler.getHeimatfelder().fügeFigurHinzu();
			}
			beendeZug();
		} else if (this.getZustand().equals(State.S.ZWEITE_FRAGE_BEANTWORTEN)) {
			erhöheWissensstandVonSpieler();
			//Spieler am Zug kommt auf das Feld
			this.felder[idZugziel].setBesetztVon(amZug);
			beendeZug();
		}
		
	}
	
	/**
	 * Service
	 */
	public void frageFalschBeantwortet() {
		if (this.getZustand().equals(State.S.ERSTE_FRAGE_BEANTWORTEN)) {
			//gefragter Spieler direkt aufs Heimatfeld
			this.gefragterSpieler.getHeimatfelder().fügeFigurHinzu();
			this.gefragterSpieler.getWissensstandsanzeiger().verringere(ausgewählteKategorieFürFrage);
			//Spieler am Zug darf Frage beantworten
			this.felder[idZugziel].setBesetztVon(amZug);
			// neue Frage
			this.frage = this.ausgewählteKategorieFürFrage.getFrage();
			this.gefragterSpieler = amZug;	
			setZustand(State.S.ZWEITE_FRAGE_BEANTWORTEN);
		} else if (this.getZustand().equals(State.S.ZWEITE_FRAGE_BEANTWORTEN)) {
			this.felder[idZugziel].setBesetztVon(null);
			this.amZug.getWissensstandsanzeiger().verringere(ausgewählteKategorieFürFrage);
			this.amZug.getHeimatfelder().fügeFigurHinzu();
			beendeZug();
			
		}
	}
	
	/**
	 * Service zieht eine Figur von einem Feld
	 */
	public void zieheVonFeld(int id) {
		// ist das Feld vom aktiven Spieler besetzt
		if (felder[id].getBesetztVon() != null && felder[id].getBesetztVon().equals(amZug)) {
			this.idZugfeld = id;
			setZustand(State.S.FIGUR_AUS_FELD_AUSGEWÄHLT);
		}	
	}
	
	/**
	 * Service
	 */
	public void zieheVonHeimatfeld(Spieler spieler) {
		if (amZug.equals(spieler) && kannSpielerAufStartfeldZiehen()) {
			setZustand(State.S.FIGUR_AUS_HEIMATFELD_AUSGEWÄHLT);
		}	
	}
	
	/**
	 * Service
	 * setzt eine Kategorie und stellt eine Frage daraus
	 */
	public void wähleKategorieFürFrage(int id) {
		if (this.getZustand().equals(State.S.FRAGE_KATEGORIE_AUSWÄHLEN)) {
			this.ausgewählteKategorieFürFrage = this.getKategorien()[id];
			// finde Frage
			this.frage = this.ausgewählteKategorieFürFrage.getFrage();
			setZustand(State.S.ERSTE_FRAGE_BEANTWORTEN);
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
		this.gefragterSpieler = gefragterSpieler;
		setZustand(State.S.FRAGE_KATEGORIE_AUSWÄHLEN);
	}
	
	/**
	 * Service
	 */
	public void zieheAufFeld(int id) {
		Spieler besetzenderSpieler = this.felder[id].getBesetztVon();
		if (this.getZustand().equals(State.S.FIGUR_AUS_HEIMATFELD_AUSGEWÄHLT)) {		
			if (id == getStartfeldVonSpieler(amZug)) {
				if (besetzenderSpieler == null) {
					amZug.getHeimatfelder().entnehmeFigur();
					// Versuche, die Figur auf das Startfeld des Spielers zu setzen, muss gelingen da Feld leer ist
					this.felder[id].setBesetztVon(amZug);
					beendeZug();	
				} else if (!besetzenderSpieler.equals(amZug)) {
					// jemand anders steht auf dem Startfeld, starte Fragerunde
					this.idZugziel = id;
					// entnehme Figur des Fragenden vom Heimatfeld
					this.amZug.getHeimatfelder().entnehmeFigur();
					stelleFrage(besetzenderSpieler);
				} else {
					// Es kann nicht eintreten, dass derselbe Spieler auf dem Startfeld steht, da der Fall beim Würfeln überprüft wird
				}
			}			
		} else if (this.getZustand().equals(State.S.FIGUR_AUS_FELD_AUSGEWÄHLT)) {
			//Spieler kann sich dorthin bewegen
			if (idZugfeld != -1 && (idZugfeld + gewürfelt)%48 == id) {				
				if (besetzenderSpieler == null) {
					// Feld ist frei
					this.felder[idZugfeld].setBesetztVon(null);
					this.felder[id].setBesetztVon(amZug);
					beendeZug();
				} else if (!besetzenderSpieler.equals(amZug)) {
					// Feld besetzt von Gegner
					this.felder[idZugfeld].setBesetztVon(null);
					this.idZugziel = id;
					stelleFrage(besetzenderSpieler);
				} else {
					// Feld besetzt vom Spieler selbst, andere Figur auswählen
					setZustand(State.S.MUSS_FELD_ZIEHEN);
					
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
				gewürfelt = -1;
				idZugfeld = -1;
				idZugziel = -1;
				anzahlWürfe = 0;
				ausgewählteKategorieFürFrage = null;
				gefragterSpieler = null;
				frage = null;
				setZustand(State.S.DARF_NOCH_WÜRFELN);
				break;
			}
		}
	}
	
	private State getZustand() {
		return this.stateMachine.getState();
	}
	
	private void setZustand(State state) {
		this.stateMachine.setState(state);
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
				int[] ws = player.getWissensstandsanzeiger().getWissen();
				return ws[0] + "/" + ws[1] + "/" + ws[2] + "/" + ws[3];
			}
		}
		return null;
	}

	/**
	 * der Spieler darf würfeln, wenn: - er noch nicht gewürfelt hat und mindestens
	 * eine Figur im Feld hat - er noch nicht 3 mal gewürfelt hat (und auch keine 6)
	 * und alle Figuren noch im Heimatfeld sind
	 */
	public boolean getDarfNochWürfeln() {
		return (anzahlWürfe == 0) || (amZug.getHeimatfelder().sindVoll() && anzahlWürfe < 3 && gewürfelt != 6);
	}

	public String getAusgewählteKategorieNameFürFrage() {
		return ausgewählteKategorieFürFrage.getName();
	}
	
	public String getAktuellerFragetext() {
		return this.frage.getFragetext();
	}
	
	public String getAktuellerLösungstext() {
		return this.frage.getLösungstext();
	}
	
	public Spieler getGewinner() {
		return this.hatGewonnen;
	}

}
