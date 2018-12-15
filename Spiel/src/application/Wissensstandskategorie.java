package application;

public class Wissensstandskategorie {
	
	private int stand;
	
	private final IntKategorie kategorie;

	public Wissensstandskategorie(IntKategorie kategorie) {
		this.stand = 0;
		this.kategorie = kategorie;
	}
	
	public boolean erhöheWissen() {
		if (this.stand < 3) {
			this.stand++;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean verringereWissen() {
		if (this.stand > 0) {
			this.stand--;
			return true;
		} else {
			return true;
		}
	}
	
	public int getWissen() {
		return this.stand;
	}

	public IntKategorie getKategorie() {
		return kategorie;
	}

}
