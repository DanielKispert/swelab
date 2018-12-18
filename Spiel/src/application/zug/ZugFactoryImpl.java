package application.zug;


import application.statemachine.StateMachineFactory;
import application.statemachine.port.StateMachine;
import application.statemachine.port.StateMachinePort;
import application.zug.impl.ZugMachen;
import application.zug.port.ZugManagement;
import application.zug.port.ZugManagementPort;

public class ZugFactoryImpl implements ZugFactory, ZugManagementPort, ZugManagement {
	
	private StateMachinePort stateMachinePort = StateMachineFactory.FACTORY.stateMachinePort();
	
	private StateMachine stateMachine;
	
	private ZugMachen manager;
	
	public synchronized ZugManagement zugManagement() {
		if (this.manager == null) {
			this.stateMachine = this.stateMachinePort.stateMachine();
			this.manager = new ZugMachen(this.stateMachinePort);
		}
		return this;
	}

	@Override
	public ZugManagementPort zugManagerPort() {
		return this;
	}
	
	
	



	
	
}
