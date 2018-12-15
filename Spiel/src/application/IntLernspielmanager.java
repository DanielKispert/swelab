package application;

public interface IntLernspielmanager extends IntBrettspielmanager {

	// Spielzust�nde
	public enum Zugzustand {
		DARF_NOCH_W�RFELN, MUSS_AUS_HEIMATFELD_ZIEHEN, FIGUR_AUS_HEIMATFELD_AUSGEW�HLT, MUSS_FELD_ZIEHEN, FIGUR_AUS_FELD_AUSGEW�HLT, FRAGE_KATEGORIE_AUSW�HLEN, ERSTE_FRAGE_BEANTWORTEN, ZWEITE_FRAGE_BEANTWORTEN, GEWONNEN;
	}

	// Abfragen von Informationen

	public Zugzustand getZugZustand();

	public IntLernspielSpieler[] getSpieler();
	
	public IntLernspielSpieler getSpielerAmZug();
	
	public int getGew�rfelt();
	
	public boolean getDarfNochW�rfeln();
	
	public String getWissensstandVon(IntLernspielSpieler spieler);
	
	public IntKategorie[] getKategorien();
	
	public IntFeld[] getFelder();
	
	public String getAusgew�hlteKategorieNameF�rFrage();
	
	public String getAktuellerFragetext();
	
	public IntLernspielSpieler getGewinner();

	// Services um Model zu ver�ndern

	public void w�rfle();

	public void zieheVonHeimatfeld(IntLernspielSpieler spieler);

	public void zieheVonFeld(int id);
	
	public void zieheAufFeld(int id);
	
	public void w�hleKategorieF�rFrage(int id);
	
	public void frageRichtigBeantwortet();
	
	public void frageFalschBeantwortet();

}
