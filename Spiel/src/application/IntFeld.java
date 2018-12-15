package application;

public interface IntFeld {
	
	// Abfragen
	
	public IntLernspielSpieler getBesetztVon();
	
	// Services
	
	public boolean setBesetztVon(IntLernspielSpieler spieler);

}
