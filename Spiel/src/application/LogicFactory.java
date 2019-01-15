package application;

import application.port.MVCPort;
import application.port.ManagerPort;

public interface LogicFactory {
	
	public LogicFactory FACTORY = new LogicFactoryImpl();
	
	public ManagerPort ManagerPort();
	
	public MVCPort MVCPort();

}
