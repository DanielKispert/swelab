package application;

public class Frage implements IntFrage{
	
	private final String fragetext;
	
	private final String l�sungstext;
	
	public Frage(String frage, String l�sung) {
		this.fragetext = frage;
		this.l�sungstext = l�sung;
	}

	public String getFragetext() {
		return fragetext;
	}

	public String getL�sungstext() {
		return l�sungstext;
	}

}
