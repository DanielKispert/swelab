package application;

public interface IntKategorie {
	
	// Abfragen
	
	public String getName();
	
	public IntFrage getFrage();
	
	// Services
	
	public boolean addFrage(IntFrage frage);
	
	public boolean removeFrage(IntFrage frage);

}
