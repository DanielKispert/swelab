package application;

import java.awt.Color;
import java.util.ArrayList;

import application.presentation.view.impl.Fenster;
import application.zug.ZugFactory;
import application.zug.impl.Frage;
import application.zug.impl.FrageImpl;
import application.zug.impl.KategorieImpl;
import application.zug.impl.ZugMachen;
import application.zug.port.ZugManagementPort;

public class GameStart {

	public static void main(String[] args) {
		
		// initialize Model
		LogicFactory.FACTORY.ManagerPort().zugManagement();
		
		// initialize GUI
		

		// initialize Model
		Spielerinfos[] infos = new Spielerinfos[4];
		infos[0] = new Spielerinfos("Adam", Color.RED);
		infos[1] = new Spielerinfos("Bertha", Color.YELLOW);
		infos[2] = new Spielerinfos("Charlie", Color.GREEN);
		infos[3] = new Spielerinfos("Denise", Color.CYAN);

		ArrayList<Frage> fragen1 = new ArrayList<>();
		ArrayList<Frage> fragen2 = new ArrayList<>();
		ArrayList<Frage> fragen3 = new ArrayList<>();
		ArrayList<Frage> fragen4 = new ArrayList<>();
		for (int i = 0; i < 48; i++) {
			switch (i % 4) {
			case 0:
				fragen1.add(new FrageImpl("Fragetext Nummer " + i, "Lösungstext Nummer " + i));
				break;
			case 1:
				fragen2.add(new FrageImpl("Fragetext Nummer " + i, "Lösungstext Nummer " + i));
				break;
			case 2:
				fragen3.add(new FrageImpl("Fragetext Nummer " + i, "Lösungstext Nummer " + i));
				break;
			case 3:
				fragen4.add(new FrageImpl("Fragetext Nummer " + i, "Lösungstext Nummer " + i));
				break;
			}
		}
		KategorieImpl[] kategorien = new KategorieImpl[4];
		kategorien[0] = new KategorieImpl("Kategorie 1", fragen1);
		kategorien[1] = new KategorieImpl("Kategorie 2", fragen2);
		kategorien[2] = new KategorieImpl("Kategorie 3", fragen3);
		kategorien[3] = new KategorieImpl("Kategorie 4", fragen4);
		
		ZugMachen spielfeld = new ZugMachen(infos, kategorien);

		// initialize GUI
		new Fenster(spielfeld);

	}

}
