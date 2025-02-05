package application.zug.impl;

public class HeimatfelderImpl {
	
	private final HeimatfeldImpl[] heimatfelder = {new HeimatfeldImpl(), new HeimatfeldImpl(), new HeimatfeldImpl()};
	
	public HeimatfelderImpl() {
		
	}
	
	public boolean sindVoll() {
		return getAnzahlInHeimatfeldern() == 3;
	}
	
	public boolean sindLeer() {
		return getAnzahlInHeimatfeldern() == 0;
	}
	
	public boolean entnehmeFigur(int feldnummer) {
		return heimatfelder[feldnummer].nimmFigur();
	}
	
	public boolean fügeFigurHinzu() {
		for (HeimatfeldImpl feld: heimatfelder) {
			if (feld.setzeFigur()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean entnehmeFigur() {
		for (HeimatfeldImpl feld: heimatfelder) {
			if (feld.nimmFigur()) {
				return true;
			}
		}
		return false;
	}
	
	public int getAnzahlInHeimatfeldern() {
		int figuren = 0;
		if (heimatfelder[0].istBesetzt()) {
			figuren++;
		}
		if (heimatfelder[1].istBesetzt()) {
			figuren++;
		}
		if (heimatfelder[2].istBesetzt()) {
			figuren++;
		}
		return figuren;
	}

}
