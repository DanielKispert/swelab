package application;

public class Feld {
	
	private Spieler besetztVon;

	public Feld() {
		besetztVon = null;
	}
	
	public boolean setBesetztVon(Spieler spieler) {
		if (besetztVon == null) {
			besetztVon = spieler;
			return true;
		} else {
			return false;
		}
	}
	
	public Spieler getBesetztVon() {
		return besetztVon;
	}

}
