package application.statemachine.port;

public interface Subject {
	
	public void attach(Observer observer);
	
	public void detach(Observer observer);

}
