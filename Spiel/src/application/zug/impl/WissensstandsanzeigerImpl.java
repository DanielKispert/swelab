package application.zug.impl;

public class WissensstandsanzeigerImpl {

	private WissensstandskategorieImpl[] kategorien;

	public WissensstandsanzeigerImpl(Kategorie[] kategorien) {
		this.kategorien = new WissensstandskategorieImpl[4];
		for (int i = 0; i < 4; i++) {
			this.kategorien[i] = new WissensstandskategorieImpl(kategorien[i]);
		}
	}

	public int[] getWissen() {
		int[] stand = { kategorien[0].getWissen(), kategorien[1].getWissen(), kategorien[2].getWissen(),
				kategorien[3].getWissen() };
		return stand;
	}
	
	public boolean erh�he(Kategorie kategorie) {
		for (WissensstandskategorieImpl kat: kategorien) {
			if (kat.getKategorie().getName().equals(kategorie.getName()) && kat.erh�heWissen()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean erh�heN�chsten() {
		for (WissensstandskategorieImpl kat: kategorien) {
			if (kat.getWissen() != 3) {
				return kat.erh�heWissen();
			}
		}
		return false;
	}
	
	public boolean verringere(Kategorie kategorie) {
		for (WissensstandskategorieImpl kat: kategorien) {
			if (kat.getKategorie().getName().equals(kategorie.getName()) && kat.verringereWissen()) {
				return true;
			}
		}
		return false;
	}

}
