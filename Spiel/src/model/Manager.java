package model;

import java.util.ArrayList;

import userinterface.GameObserver;

public abstract class Manager {
	
	public ArrayList<GameObserver> observers;
	
	public Manager() {
		observers = new ArrayList<>();
	}
	
	public void attach(GameObserver observer) {
		
	}
	
	public void detach(GameObserver observer) {
		
	}
	
	public void notifyObservers() {
		for (GameObserver observer: observers) {
			observer.update();
		}
	}

}
