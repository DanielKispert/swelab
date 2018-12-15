package application;

import java.util.ArrayList;

public abstract class Brettspielmanager implements IntBrettspielmanager{
	
	private ArrayList<BrettspielObserver> observers;
	
	public Brettspielmanager() {
		observers = new ArrayList<>();
	}
	
	public void attach(BrettspielObserver observer) {
		observers.add(observer);
		
	}
	
	public void detach(BrettspielObserver observer) {
		observers.remove(observer);
	}
	
	public void notifyObservers() {
		for (BrettspielObserver observer: observers) {
			observer.update();
		}
	}

}
