package application;

public class Wissensstandsanzeiger {

	private Wissensstandskategorie[] kategorien;

	public Wissensstandsanzeiger(IntKategorie[] kategorien) {
		this.kategorien = new Wissensstandskategorie[4];
		for (int i = 0; i < 4; i++) {
			this.kategorien[i] = new Wissensstandskategorie(kategorien[i]);
		}
	}

	public int[] getWissen() {
		int[] stand = { kategorien[0].getWissen(), kategorien[1].getWissen(), kategorien[2].getWissen(),
				kategorien[3].getWissen() };
		return stand;
	}
	
	public boolean erh�he(IntKategorie kategorie) {
		for (Wissensstandskategorie kat: kategorien) {
			if (kat.getKategorie().getName().equals(kategorie.getName()) && kat.erh�heWissen()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean erh�heN�chsten() {
		for (Wissensstandskategorie kat: kategorien) {
			if (kat.getWissen() != 3) {
				return kat.erh�heWissen();
			}
		}
		return false;
	}
	
	public boolean verringere(IntKategorie kategorie) {
		for (Wissensstandskategorie kat: kategorien) {
			if (kat.getKategorie().getName().equals(kategorie.getName()) && kat.verringereWissen()) {
				return true;
			}
		}
		return false;
	}

}
