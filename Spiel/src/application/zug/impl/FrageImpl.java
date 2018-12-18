package application.zug.impl;

public class FrageImpl implements Frage{
	
	private final String fragetext;
	
	private final String l�sungstext;
	
	public FrageImpl(String frage, String l�sung) {
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
