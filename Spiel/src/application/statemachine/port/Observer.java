package application.statemachine.port;

public interface Observer {
	
	public void update(State newState);

}
