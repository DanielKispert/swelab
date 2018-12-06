package model;
import java.util.ArrayList;
import java.util.Random;

public class Kategorie {
	
	private String name;
	
	private ArrayList<Frage> fragen;
	
	public Kategorie(String name, ArrayList<Frage> fragen) {
		this.name = name;
		this.fragen = fragen;
	}
	
	/**
	 * gibt eine zufällige Frage aus allen Fragen der Kategorie zurück
	 */
	public Frage getFrage() {
		return fragen.get(new Random().nextInt(fragen.size()));
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * fügt die angegebene Frage den Kategoriefragen hinzu
	 */
	public boolean addFrage(Frage frage) {
		return fragen.add(frage);
	}
	
	/**
	 * entfernt die angegebene Frage von den Kategoriefragen
	 */
	public boolean removeFrage(Frage frage) {
		return fragen.remove(frage);
	}
}
