package application;

public class Heimatfeld {

	private boolean istBesetzt;
	
	public Heimatfeld() {
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
