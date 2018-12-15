package application;

public interface IntLernspielmanager extends IntBrettspielmanager {

	// Spielzustände
	public enum Zugzustand {
		DARF_NOCH_WÜRFELN, MUSS_AUS_HEIMATFELD_ZIEHEN, FIGUR_AUS_HEIMATFELD_AUSGEWÄHLT, MUSS_FELD_ZIEHEN, FIGUR_AUS_FELD_AUSGEWÄHLT, FRAGE_KATEGORIE_AUSWÄHLEN, ERSTE_FRAGE_BEANTWORTEN, ZWEITE_FRAGE_BEANTWORTEN, GEWONNEN;
	}

	// Abfragen von Informationen

	public Zugzustand getZugZustand();

	public IntLernspielSpieler[] getSpieler();
	
	public IntLernspielSpieler getSpielerAmZug();
	
	public int getGewürfelt();
	
	public boolean getDarfNochWürfeln();
	
	public String getWissensstandVon(IntLernspielSpieler spieler);
	
	public IntKategorie[] getKategorien();
	
	public IntFeld[] getFelder();
	
	public String getAusgewählteKategorieNameFürFrage();
	
	public String getAktuellerFragetext();
	
	public IntLernspielSpieler getGewinner();

	// Services um Model zu verändern

	public void würfle();

	public void zieheVonHeimatfeld(IntLernspielSpieler spieler);

	public void zieheVonFeld(int id);
	
	public void zieheAufFeld(int id);
	
	public void wähleKategorieFürFrage(int id);
	
	public void frageRichtigBeantwortet();
	
	public void frageFalschBeantwortet();

}
