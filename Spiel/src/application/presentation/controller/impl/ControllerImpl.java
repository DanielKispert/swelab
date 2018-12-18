package application.presentation.controller.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.presentation.controller.port.Controller;
import application.presentation.view.impl.FeldButton;
import application.presentation.view.impl.HeimatfeldButton;
import application.presentation.view.impl.KategorieButton;
import application.presentation.view.port.GUI;
import application.zug.port.ZugManagement;

public class ControllerImpl implements Controller {

	private final ZugManagement spielfeld;
	
	private final GUI myView;
	
	private ZugManagement.S zustand;

	public ControllerImpl(GUI gui, ZugManagement spielfeld) {
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
		case DARF_NOCH_W�RFELN:
			if (e.getSource().equals(myView.getW�rfelButton())) {
				spielfeld.w�rfle();
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
		case FIGUR_AUS_FELD_AUSGEW�HLT:
			for (FeldButton feld: myView.getFelder()) {
				if (e.getSource().equals(feld)) {
					spielfeld.zieheAufFeld(feld.getId());
					break;
				}
			}
			break;
		case FIGUR_AUS_HEIMATFELD_AUSGEW�HLT:
			for (FeldButton feld: myView.getFelder()) {
				if (e.getSource().equals(feld)) {
					spielfeld.zieheAufFeld(feld.getId());
					break;
				}
			}
			break;
		case FRAGE_KATEGORIE_AUSW�HLEN:
			for (KategorieButton ktb: myView.getKategorien()) {
				if (e.getSource().equals(ktb)) {
					spielfeld.w�hleKategorieF�rFrage(ktb.getId());
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
