package application.presentation.view;

import application.LogicFactory;
import application.port.MVCPort;
import application.port.ManagerPort;
import application.presentation.view.impl.Fenster;
import application.presentation.view.port.GUI;
import application.presentation.view.port.GUIPort;

public class GUIFactoryImpl implements GUIFactory, GUIPort, GUI {
	
	private ManagerPort managerPort = LogicFactory.FACTORY.ManagerPort();
	
	private MVCPort mvcPort = LogicFactory.FACTORY.MVCPort();
	
	private Fenster ui;
	
	private void mkUi() {
		if (this.ui == null) {
			this.ui = new Fenster(this.managerPort, this.mvcPort);
		}
	}

	@Override
	public synchronized GUIPort guiPort() {
		return this;
	}
	
	public synchronized GUI gui() {
		this.mkUi();
		return this;
	}

	@Override
	public void startEventLoop() {
		this.ui.startEventLoop();
		
	}

	
	
	

}
