package application.presentation.view;

import javax.swing.JButton;

import application.LogicFactory;
import application.port.MVCPort;
import application.port.ManagerPort;
import application.presentation.view.impl.FeldButton;
import application.presentation.view.impl.Fenster;
import application.presentation.view.impl.HeimatfeldButton;
import application.presentation.view.impl.KategorieButton;
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
	public JButton getWürfelButton() {
		return this.ui.getWürfelButton();
	}

	@Override
	public HeimatfeldButton[] getHeimatfelder() {
		return this.ui.getHeimatfelder();
	}

	@Override
	public FeldButton[] getFelder() {
		return this.ui.getFelder();
	}

	@Override
	public KategorieButton[] getKategorien() {
		return this.ui.getKategorien();
	}

	@Override
	public JButton getFrageRichtigButton() {
		return this.ui.getFrageRichtigButton();
	}

	@Override
	public JButton getFrageFalschButton() {
		return this.ui.getFrageFalschButton();
	}

	
	
	

}
