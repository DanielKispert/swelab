package model.player;

public class Heimatfelder {
	
	private final Heimatfeld[] heimatfelder = {new Heimatfeld(), new Heimatfeld(), new Heimatfeld()};
	
	public Heimatfelder() {
		
	}
	
	public boolean sindVoll() {
		return heimatfelder[0].istBesetzt() && heimatfelder[1].istBesetzt() && heimatfelder[2].istBesetzt();
	}
	
	public boolean sindLeer() {
		return !heimatfelder[0].istBesetzt() && !heimatfelder[1].istBesetzt() && !heimatfelder[2].istBesetzt();
	}
	
	public boolean entnehmeFigur(int feldnummer) {
		return heimatfelder[feldnummer].nimmFigur();
	}
	
	public boolean fügeFigurHinzu() {
		for (Heimatfeld feld: heimatfelder) {
			if (feld.setzeFigur()) {
				return true;
			}
		}
		return false;
	}

}
