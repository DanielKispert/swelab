package application.statemachine.impl;

import java.util.ArrayList;
import java.util.List;

import application.statemachine.port.Observer;
import application.statemachine.port.State;
import application.statemachine.port.StateMachine;
import application.statemachine.port.Subject;

public class StateMachineImpl implements StateMachine, Subject{

	private List<Observer> observers = new ArrayList<>();
	
	private State currentState;
	
	public StateMachineImpl() {
		this.currentState = State.S.DARF_NOCH_WÜRFELN;
	}
	

	@Override
	public void attach(Observer observer) {
		this.observers.add(observer);
		observer.update(currentState);
	}

	@Override
	public void detach(Observer observer) {
		this.observers.remove(observer);		
	}

	@Override
	public void setState(State state) {
		currentState = state;
		observers.forEach(obs -> obs.update(state));
	}

	@Override
	public State getState() {
		return this.currentState;
	}

}
