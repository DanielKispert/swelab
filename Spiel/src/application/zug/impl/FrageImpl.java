package application.zug.impl;

public class FrageImpl implements Frage{
	
	private final String fragetext;
	
	private final String lösungstext;
	
	public FrageImpl(String frage, String lösung) {
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
