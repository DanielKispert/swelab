package application.zug.impl;

public class HeimatfeldImpl {

	private boolean istBesetzt;
	
	public HeimatfeldImpl() {
		setzeFigur();
	}
	
	public boolean istBesetzt() {
		return istBesetzt;
	}
	
	public boolean setzeFigur() {
		if (!istBesetzt) {
			istBesetzt = true;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean nimmFigur() {
		if (istBesetzt) {
			istBesetzt = false;
			return true;
		} else {
			return false;
		}
	}
	
	
}
