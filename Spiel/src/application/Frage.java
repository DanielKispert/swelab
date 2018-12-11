package application;

public class Frage {
	
	private final String fragetext;
	
	private final String lösungstext;
	
	public Frage(String frage, String lösung) {
		this.fragetext = frage;
		this.lösungstext = lösung;
	}

	public String getFragetext() {
		return fragetext;
	}

	public String getLösungstext() {
		return lösungstext;
	}

}
