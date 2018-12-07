package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Spielfeldmanager;
import userinterface.GameObserver;

public class GameController implements GameObserver, ActionListener {
	
	private final Spielfeldmanager spielfeld;
	
	public GameController(Spielfeldmanager spielfeld) {
		//attach to model
		this.spielfeld = spielfeld;
		spielfeld.attach(this);
	}
	

	@Override
	public void update() {
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {		
		
	}

}
