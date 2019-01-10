package application.statemachine.port;

public interface State {	

	public S getS();
	
	// Spielzust�nde
	public enum S implements State {
		DARF_NOCH_W�RFELN, MUSS_AUS_HEIMATFELD_ZIEHEN, FIGUR_AUS_HEIMATFELD_AUSGEW�HLT, MUSS_FELD_ZIEHEN, FIGUR_AUS_FELD_AUSGEW�HLT, FRAGE_KATEGORIE_AUSW�HLEN, ERSTE_FRAGE_BEANTWORTEN, ZWEITE_FRAGE_BEANTWORTEN, GEWONNEN;
	
		public S getS() {
			return this;
		}
	
	}
	

}
