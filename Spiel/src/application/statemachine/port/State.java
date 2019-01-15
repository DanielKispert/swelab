package application.statemachine.port;

public interface State {	

	public S getS();
	
	// Spielzustände
	public enum S implements State {
		DARF_NOCH_WÜRFELN, MUSS_AUS_HEIMATFELD_ZIEHEN, FIGUR_AUS_HEIMATFELD_AUSGEWÄHLT, MUSS_FELD_ZIEHEN, FIGUR_AUS_FELD_AUSGEWÄHLT, FRAGE_KATEGORIE_AUSWÄHLEN, ERSTE_FRAGE_BEANTWORTEN, ZWEITE_FRAGE_BEANTWORTEN, GEWONNEN;
	
		public S getS() {
			return this;
		}
	
	}
	

}
