package application.zug.port;

import application.zug.impl.Feld;
import application.zug.impl.Kategorie;
import application.zug.impl.Spieler;

public interface ZugManagement {

	// Services um Model zu verändern. Werden vom Controller aufgerufen	
	public void beginneZug();
	
	public void zieheVonHeimatfeld(Spieler spieler);
	
	public void zieheVonFeld(int id);
	
	public void zieheAufFeld(int id);

	public void wähleKategorieFürFrage(int id);
	
	public void frageRichtigBeantwortet();
	
	public void frageFalschBeantwortet();
	
	//Abfragen von Informationen. Werden von der GUI aufgerufen

	public Spieler[] getSpieler();	

	public Spieler getSpielerAmZug();
	
	public int getGewürfelt();
	
	public boolean getDarfNochWürfeln();
	
	public String getWissensstandVon(Spieler spieler);
	
	public Kategorie[] getKategorien();
	
	public Feld[] getFelder();	
	
	public String getAusgewählteKategorieNameFürFrage();
	
	public String getAktuellerFragetext();
	
	public String getAktuellerLösungstext();
	
	public Spieler getGewinner();

}
