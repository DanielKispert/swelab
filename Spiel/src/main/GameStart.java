package main;

import java.awt.Color;
import java.util.ArrayList;

import model.Frage;
import model.Kategorie;
import model.Spielerinfos;
import model.Spielfeldmanager;
import userinterface.GUI;

public class GameStart {

	public static void main(String[] args) {

		// initialize Model
		Spielerinfos[] infos = new Spielerinfos[4];
		infos[0] = new Spielerinfos("Adam", Color.RED);
		infos[1] = new Spielerinfos("Bertha", Color.YELLOW);
		infos[2] = new Spielerinfos("Charlie", Color.GREEN);
		infos[3] = new Spielerinfos("Denise", Color.BLUE);

		ArrayList<Frage> fragen1 = new ArrayList<>();
		ArrayList<Frage> fragen2 = new ArrayList<>();
		ArrayList<Frage> fragen3 = new ArrayList<>();
		ArrayList<Frage> fragen4 = new ArrayList<>();
		for (int i = 0; i < 48; i++) {
			switch (i % 4) {
			case 0:
				fragen1.add(new Frage("Fragetext Nummer " + i, "Lösungstext Nummer " + i));
				break;
			case 1:
				fragen2.add(new Frage("Fragetext Nummer " + i, "Lösungstext Nummer " + i));
				break;
			case 2:
				fragen3.add(new Frage("Fragetext Nummer " + i, "Lösungstext Nummer " + i));
				break;
			case 3:
				fragen4.add(new Frage("Fragetext Nummer " + i, "Lösungstext Nummer " + i));
				break;
			}
		}
		Kategorie[] kategorien = new Kategorie[4];
		kategorien[0] = new Kategorie("Kategorie 1", fragen1);
		kategorien[1] = new Kategorie("Kategorie 2", fragen2);
		kategorien[2] = new Kategorie("Kategorie 3", fragen3);
		kategorien[3] = new Kategorie("Kategorie 4", fragen4);
		
		Spielfeldmanager spielfeld = new Spielfeldmanager(infos, kategorien);

		// initialize GUI
		new GUI(spielfeld);

	}

}
