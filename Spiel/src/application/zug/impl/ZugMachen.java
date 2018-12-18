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
	
	private int gew�rfelt;
	
	private int anzahlW�rfe;
	
	private Spieler amZug;
	
	private StateMachine stateMachine;
	
	private int idZugfeld;
	
	private int idZugziel;
	
	private Kategorie ausgew�hlteKategorieF�rFrage;
	
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
		gew�rfelt = -1;
		idZugfeld = -1;
		anzahlW�rfe = 0;
		ausgew�hlteKategorieF�rFrage = null;
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
	public void w�rfle() {
		// darf er noch w�rfeln?
		if (getZustand().equals(State.S.DARF_NOCH_W�RFELN) && getDarfNochW�rfeln()) {
			anzahlW�rfe++;
			this.gew�rfelt = new Random().nextInt(6) + 1;

			// n�chsten Zustand ermitteln
			if (amZug.getHeimatfelder().sindLeer()) {
				// der Spieler muss jetzt eine Figur auf den Feldern ziehen
				setZustand(State.S.MUSS_FELD_ZIEHEN);
			} else {
				if (this.gew�rfelt == 6 && kannSpielerAufStartfeldZiehen()) {
					//Spieler muss eine Figur aus dem Heimatfeld holen
					
					setZustand(State.S.MUSS_AUS_HEIMATFELD_ZIEHEN);
				} else {
					if (!amZug.getHeimatfelder().sindVoll()) {
						setZustand(State.S.MUSS_FELD_ZIEHEN);
					} else {
						if (anzahlW�rfe >= 3) {
							//n�chster Spieler dran
							beendeZug();
						} 
					}
				}
			}
			
		} else {
			// Spieler darf nicht w�rfeln, tue nichts
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
	

	
	private void erh�heWissensstandVonSpieler() {
		if (!this.gefragterSpieler.getWissensstandsanzeiger().erh�he(this.ausgew�hlteKategorieF�rFrage)
				&& !this.gefragterSpieler.getWissensstandsanzeiger().erh�heN�chsten()) {
			setZustand(State.S.GEWONNEN);
		}
	}
	
	/**
	 * Service
	 */
	public void frageRichtigBeantwortet() {
		if (this.getZustand().equals(State.S.ERSTE_FRAGE_BEANTWORTEN)) {
			erh�heWissensstandVonSpieler();
			// fragender Spieler (am Zug) kommt auf das Feld, gefragter Spieler auf sein Startfeld oder sein Heimatfeld falls das Startfeld belegt ist
			this.felder[idZugziel].setBesetztVon(amZug);
			if (this.felder[getStartfeldVonSpieler(gefragterSpieler)].getBesetztVon() == null) {
				this.felder[getStartfeldVonSpieler(gefragterSpieler)].setBesetztVon(gefragterSpieler);
			} else {
				gefragterSpieler.getHeimatfelder().f�geFigurHinzu();
			}
			beendeZug();
		} else if (this.getZustand().equals(State.S.ZWEITE_FRAGE_BEANTWORTEN)) {
			erh�heWissensstandVonSpieler();
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
			this.gefragterSpieler.getHeimatfelder().f�geFigurHinzu();
			this.gefragterSpieler.getWissensstandsanzeiger().verringere(ausgew�hlteKategorieF�rFrage);
			//Spieler am Zug darf Frage beantworten
			this.felder[idZugziel].setBesetztVon(amZug);
			// neue Frage
			this.frage = this.ausgew�hlteKategorieF�rFrage.getFrage();
			this.gefragterSpieler = amZug;	
			setZustand(State.S.ZWEITE_FRAGE_BEANTWORTEN);
		} else if (this.getZustand().equals(State.S.ZWEITE_FRAGE_BEANTWORTEN)) {
			this.felder[idZugziel].setBesetztVon(null);
			this.amZug.getWissensstandsanzeiger().verringere(ausgew�hlteKategorieF�rFrage);
			this.amZug.getHeimatfelder().f�geFigurHinzu();
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
			setZustand(State.S.FIGUR_AUS_FELD_AUSGEW�HLT);
		}	
	}
	
	/**
	 * Service
	 */
	public void zieheVonHeimatfeld(Spieler spieler) {
		if (amZug.equals(spieler) && kannSpielerAufStartfeldZiehen()) {
			setZustand(State.S.FIGUR_AUS_HEIMATFELD_AUSGEW�HLT);
		}	
	}
	
	/**
	 * Service
	 * setzt eine Kategorie und stellt eine Frage daraus
	 */
	public void w�hleKategorieF�rFrage(int id) {
		if (this.getZustand().equals(State.S.FRAGE_KATEGORIE_AUSW�HLEN)) {
			this.ausgew�hlteKategorieF�rFrage = this.getKategorien()[id];
			// finde Frage
			this.frage = this.ausgew�hlteKategorieF�rFrage.getFrage();
			setZustand(State.S.ERSTE_FRAGE_BEANTWORTEN);
		}
		
	}
	
	/**
	 * TODO
	 * 1. ziehender Spieler klickt auf Kategorie
	 * 2. zuf�llige Frage wird angezeigt
	 * 3. Frage wird richtig oder falsch beantwortet
	 * 4. Richtig: Fragender Spieler kommt auf Feld, gefragter auf Startfeld oder Heimatfeld wenn Startfeld voll
	 * 5. Falsch: gefragter kommt auf Heimatfeld, fragender Spieler kriegt Frage gestellt
	 * 6. Frage richtig: Figur kommt auf Feld. Falsch: Figur kommt auf Heimatfeld
	 */
	private void stelleFrage(Spieler gefragterSpieler) {
		//TODO
		this.gefragterSpieler = gefragterSpieler;
		setZustand(State.S.FRAGE_KATEGORIE_AUSW�HLEN);
	}
	
	/**
	 * Service
	 */
	public void zieheAufFeld(int id) {
		Spieler besetzenderSpieler = this.felder[id].getBesetztVon();
		if (this.getZustand().equals(State.S.FIGUR_AUS_HEIMATFELD_AUSGEW�HLT)) {		
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
					// Es kann nicht eintreten, dass derselbe Spieler auf dem Startfeld steht, da der Fall beim W�rfeln �berpr�ft wird
				}
			}			
		} else if (this.getZustand().equals(State.S.FIGUR_AUS_FELD_AUSGEW�HLT)) {
			//Spieler kann sich dorthin bewegen
			if (idZugfeld != -1 && (idZugfeld + gew�rfelt)%48 == id) {				
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
					// Feld besetzt vom Spieler selbst, andere Figur ausw�hlen
					setZustand(State.S.MUSS_FELD_ZIEHEN);
					
				}
			}
		
			
		}
		
	}
	
	/**
	 * n�chster Spieler am Zug
	 * updated nicht view & controller, notifyObservers muss gecallt werden
	 */
	private void beendeZug() {
		//set n�chster Spieler
		for (int i = 0; i < 4; i++) {
			if (this.spieler[i].equals(amZug)) {
				amZug = this.spieler[(i+1)%4];
				gew�rfelt = -1;
				idZugfeld = -1;
				idZugziel = -1;
				anzahlW�rfe = 0;
				ausgew�hlteKategorieF�rFrage = null;
				gefragterSpieler = null;
				frage = null;
				setZustand(State.S.DARF_NOCH_W�RFELN);
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

	public int getGew�rfelt() {
		return gew�rfelt;
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
	 * der Spieler darf w�rfeln, wenn: - er noch nicht gew�rfelt hat und mindestens
	 * eine Figur im Feld hat - er noch nicht 3 mal gew�rfelt hat (und auch keine 6)
	 * und alle Figuren noch im Heimatfeld sind
	 */
	public boolean getDarfNochW�rfeln() {
		return (anzahlW�rfe == 0) || (amZug.getHeimatfelder().sindVoll() && anzahlW�rfe < 3 && gew�rfelt != 6);
	}

	public String getAusgew�hlteKategorieNameF�rFrage() {
		return ausgew�hlteKategorieF�rFrage.getName();
	}
	
	public String getAktuellerFragetext() {
		return this.frage.getFragetext();
	}
	
	public String getAktuellerL�sungstext() {
		return this.frage.getL�sungstext();
	}
	
	public Spieler getGewinner() {
		return this.hatGewonnen;
	}

}
