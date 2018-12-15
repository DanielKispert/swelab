package application;

public interface IntBrettspielmanager {
	
	public void attach(BrettspielObserver observer);
	
	public void detach(BrettspielObserver observer);
	
	public void notifyObservers();

}
