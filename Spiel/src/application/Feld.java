package application;

public class Feld implements IntFeld {
	
	private IntLernspielSpieler besetztVon;

	public Feld() {
		besetztVon = null;
	}
	
	public boolean setBesetztVon(IntLernspielSpieler spieler) {
		if (spieler == null || besetztVon == null || !besetztVon.equals(spieler)) {
			// Feld leer oder Feld soll leer gemacht werden oder Feld ist von jemand anderem besetzt, Aktion ok
			besetztVon = spieler;
			return true;
		} else {
			// Feld vom selben Spieler besetzt, man darf nicht darauf ziehen
			return false;
		} 
	}
	
	public IntLernspielSpieler getBesetztVon() {
		return besetztVon;
	}

}
