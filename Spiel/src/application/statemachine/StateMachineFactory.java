package application.statemachine;

import application.statemachine.port.StateMachinePort;
import application.statemachine.port.SubjectPort;

public interface StateMachineFactory {
	
	public StateMachineFactory FACTORY = new StateMachineFactoryImpl();
	
	public SubjectPort subjectPort();
	
	public StateMachinePort stateMachinePort();

}
