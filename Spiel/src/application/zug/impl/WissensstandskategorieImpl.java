package application.zug.impl;

public class WissensstandskategorieImpl {
	
	private int stand;
	
	private final Kategorie kategorie;

	public WissensstandskategorieImpl(Kategorie kategorie) {
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

	public Kategorie getKategorie() {
		return kategorie;
	}

}
