package application;
import java.util.ArrayList;
import java.util.Random;

public class Kategorie implements IntKategorie {
	
	private String name;
	
	private ArrayList<IntFrage> fragen;
	
	public Kategorie(String name, ArrayList<IntFrage> fragen) {
		this.name = name;
		this.fragen = fragen;
	}
	
	/**
	 * gibt eine zufällige IntFrage aus allen IntFragen der Kategorie zurück
	 */
	public IntFrage getFrage() {
		return fragen.get(new Random().nextInt(fragen.size()));
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * fügt die angegebene IntFrage den Kategoriefragen hinzu
	 */
	public boolean addFrage(IntFrage frage) {
		return fragen.add(frage);
	}
	
	/**
	 * entfernt die angegebene IntFrage von den Kategoriefragen
	 */
	public boolean removeFrage(IntFrage frage) {
		return fragen.remove(frage);
	}
}
