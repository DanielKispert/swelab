package application.presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.BrettspielObserver;
import application.IntLernspielmanager;

public class GameController implements BrettspielObserver, ActionListener {

	private final IntLernspielmanager spielfeld;
	
	private final GUI myView;
	
	private IntLernspielmanager.Zugzustand zustand;

	public GameController(GUI gui, IntLernspielmanager spielfeld) {
		// attach to model
		this.spielfeld = spielfeld;
		this.myView = gui;
		spielfeld.attach(this);
		update();
	}

	@Override
	public void update() {
		zustand = spielfeld.getZugZustand();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (zustand) {
		case DARF_NOCH_WÜRFELN:
			if (e.getSource().equals(myView.getWürfelButton())) {
				spielfeld.würfle();
			}
			break;
		case MUSS_AUS_HEIMATFELD_ZIEHEN:
			for (HeimatfeldButton hfb: myView.getHeimatfelder()) {
				if (e.getSource().equals(hfb)) {
					spielfeld.zieheVonHeimatfeld(hfb.getSpieler());
					break;
				}
			}				
			break;
		case MUSS_FELD_ZIEHEN:
			for (FeldButton fb: myView.getFelder()) {
				if (e.getSource().equals(fb)) {
					spielfeld.zieheVonFeld(fb.getId());
				}
			}
			break;
		case FIGUR_AUS_FELD_AUSGEWÄHLT:
			for (FeldButton feld: myView.getFelder()) {
				if (e.getSource().equals(feld)) {
					spielfeld.zieheAufFeld(feld.getId());
					break;
				}
			}
			break;
		case FIGUR_AUS_HEIMATFELD_AUSGEWÄHLT:
			for (FeldButton feld: myView.getFelder()) {
				if (e.getSource().equals(feld)) {
					spielfeld.zieheAufFeld(feld.getId());
					break;
				}
			}
			break;
		case FRAGE_KATEGORIE_AUSWÄHLEN:
			for (KategorieButton ktb: myView.getKategorien()) {
				if (e.getSource().equals(ktb)) {
					spielfeld.wähleKategorieFürFrage(ktb.getId());
					break;
				}
			}
			break;
		case ERSTE_FRAGE_BEANTWORTEN:
			if (e.getSource().equals(myView.getFrageRichtigButton())) {
				spielfeld.frageRichtigBeantwortet();				
			} else if (e.getSource().equals(myView.getFrageFalschButton())) {
				spielfeld.frageFalschBeantwortet();	
			}
			break;
		case ZWEITE_FRAGE_BEANTWORTEN:
			if (e.getSource().equals(myView.getFrageRichtigButton())) {
				spielfeld.frageRichtigBeantwortet();				
			} else if (e.getSource().equals(myView.getFrageFalschButton())) {
				spielfeld.frageFalschBeantwortet();	
			}
			break;
		default:
			// do nothing
			break;
		
		}
	}

}
