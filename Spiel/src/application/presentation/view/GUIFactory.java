package application.presentation.view;

import application.presentation.view.port.GUIPort;

public interface GUIFactory {
	
	public GUIFactory FACTORY = new GUIFactoryImpl();
	
	public GUIPort guiPort(); 

}
