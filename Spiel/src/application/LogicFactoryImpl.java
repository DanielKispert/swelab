package application;

import application.port.MVCPort;
import application.port.ManagerPort;
import application.statemachine.StateMachineFactory;
import application.statemachine.port.Subject;
import application.statemachine.port.SubjectPort;
import application.zug.ZugFactory;
import application.zug.port.ZugManagement;
import application.zug.port.ZugManagementPort;

public class LogicFactoryImpl implements LogicFactory, ManagerPort, MVCPort {
	
	private ZugManagementPort zugManagerPort = ZugFactory.FACTORY.zugManagerPort();
	
	private SubjectPort subjectPort = StateMachineFactory.FACTORY.subjectPort();

	@Override
	public synchronized MVCPort MVCPort() {		
		return this;
	}

	@Override
	public ZugManagement zugManagement() {
		return this.zugManagerPort.zugManagement();
	}

	@Override
	public synchronized Subject subject() {
		return this.subjectPort.subject();
	}

	@Override
	public ManagerPort ManagerPort() {
		return this;
	}
}
