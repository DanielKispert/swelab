package application;

import application.presentation.view.GUIFactory;

public class GameStart {

	public static void main(String[] args) {
		
		// initialize Model
		LogicFactory.FACTORY.ManagerPort().zugManagement();	

		// initialize GUI
		GUIFactory.FACTORY.guiPort().gui();

	}

}
