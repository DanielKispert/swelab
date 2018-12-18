package application.zug.impl;

public interface Kategorie {
	
	// Abfragen
	
	public String getName();
	
	public Frage getFrage();
	
	// Services
	
	public boolean addFrage(Frage frage);
	
	public boolean removeFrage(Frage frage);

}
