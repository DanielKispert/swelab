package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Spielfeldmanager;
import userinterface.GUI;
import userinterface.GameObserver;

public class GameController implements GameObserver, ActionListener {

	private final Spielfeldmanager spielfeld;
	
	private final GUI myView;

	public GameController(GUI gui, Spielfeldmanager spielfeld) {
		// attach to model
		this.spielfeld = spielfeld;
		this.myView = gui;
		spielfeld.attach(this);
	}

	@Override
	public void update() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(myView.getWürfelButton())) {
			//call service at spielfeld
			spielfeld.würfle();
		}
	}

}
