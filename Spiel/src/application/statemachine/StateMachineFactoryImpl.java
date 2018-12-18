package application.statemachine;

import application.statemachine.impl.StateMachineImpl;
import application.statemachine.port.Observer;
import application.statemachine.port.State;
import application.statemachine.port.StateMachine;
import application.statemachine.port.StateMachinePort;
import application.statemachine.port.Subject;
import application.statemachine.port.SubjectPort;

public class StateMachineFactoryImpl implements StateMachineFactory, SubjectPort, StateMachinePort, StateMachine, Subject {
	
	private StateMachineImpl stateMachine;
	
	private void mkStateMachine() {
		if (this.stateMachine == null) {
			this.stateMachine = new StateMachineImpl();
		}
	}

	@Override
	public SubjectPort subjectPort() {
		return this;
	}

	@Override
	public StateMachinePort stateMachinePort() {
		return this;
	}
	
	public synchronized StateMachine stateMachine() {
		this.mkStateMachine();
		return this;
	}
	
	public synchronized Subject subject() {
		this.mkStateMachine();
		return this;
	}

	@Override
	public synchronized void attach(Observer observer) {
		this.stateMachine.attach(observer);
		
	}

	@Override
	public synchronized void detach(Observer observer) {
		this.stateMachine.detach(observer);
		
	}

	@Override
	public synchronized State getState() {
		return this.stateMachine.getState();
	}

	@Override
	public synchronized void setState(State state) {
		this.stateMachine.setState(state);
		
	}
}
