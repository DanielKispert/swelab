package application.zug;

import application.zug.port.ZugManagementPort;

public interface ZugFactory {
	
	public ZugManagementPort zugManagerPort();
	
	public ZugFactory FACTORY = new ZugFactoryImpl();

}
