package application.zug.impl;
import java.util.ArrayList;
import java.util.Random;

public class KategorieImpl implements Kategorie {
	
	private String name;
	
	private ArrayList<Frage> fragen;
	
	public KategorieImpl(String name, ArrayList<Frage> fragen) {
		this.name = name;
		this.fragen = fragen;
	}
	
	/**
	 * gibt eine zufällige IntFrage aus allen IntFragen der Kategorie zurück
	 */
	public Frage getFrage() {
		return fragen.get(new Random().nextInt(fragen.size()));
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * fügt die angegebene IntFrage den Kategoriefragen hinzu
	 */
	public boolean addFrage(Frage frage) {
		return fragen.add(frage);
	}
	
	/**
	 * entfernt die angegebene IntFrage von den Kategoriefragen
	 */
	public boolean removeFrage(Frage frage) {
		return fragen.remove(frage);
	}
}
