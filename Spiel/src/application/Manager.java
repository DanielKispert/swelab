package application;

import java.util.ArrayList;

public abstract class Manager {
	
	public ArrayList<GameObserver> observers;
	
	public Manager() {
		observers = new ArrayList<>();
	}
	
	public void attach(GameObserver observer) {
		observers.add(observer);
		
	}
	
	public void detach(GameObserver observer) {
		observers.remove(observer);
	}
	
	public void notifyObservers() {
		for (GameObserver observer: observers) {
			observer.update();
		}
	}

}
