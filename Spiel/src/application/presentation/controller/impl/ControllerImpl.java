package application.presentation.controller.impl;

import java.awt.event.ActionEvent;
import application.port.MVCPort;
import application.port.ManagerPort;
import application.presentation.controller.port.Controller;
import application.presentation.view.impl.FeldButton;
import application.presentation.view.impl.HeimatfeldButton;
import application.presentation.view.impl.KategorieButton;
import application.presentation.view.port.GUI;
import application.statemachine.port.State;
import application.zug.port.ZugManagement;

public class ControllerImpl implements Controller {

	private final ZugManagement spielfeld;
	
	private final GUI myView;
	
	private State.S zustand;

	public ControllerImpl(GUI gui, ManagerPort managerPort, MVCPort mvcport) {
		// attach to model
		mvcport.subject().attach(this);
		this.spielfeld = managerPort.zugManagement();
		this.myView = gui;
		this.zustand = State.S.DARF_NOCH_WÜRFELN;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (zustand) {
		case DARF_NOCH_WÜRFELN:
			if (e.getSource().equals(myView.getWürfelButton())) {
				spielfeld.beginneZug();
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

	@Override
	public void update(State newState) {
		this.zustand = newState.getS();
		
	}

}
