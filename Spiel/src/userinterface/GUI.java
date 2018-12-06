package userinterface;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

import model.Frage;
import model.Kategorie;
import model.Spielerinfos;
import model.Spielfeld;

public class GUI extends JFrame{
	
	private final Spielfeld spielfeld;
	
	private final W�rfelHandler handler;
	
	private final JButton w�rfel;
	
	public GUI(Spielfeld spielfeld) {
		this.spielfeld = spielfeld;
		this.handler = new W�rfelHandler();
		setTitle("Learn-Master");
		setSize(1024,768);
		setResizable(false);
		setLayout(new GridLayout(5, 5));
		setVisible(true);
		//add w�rfel
		w�rfel = new JButton();
		w�rfel.addActionListener(handler);
		w�rfel.setText("w�rfeln");
		add(w�rfel);
	}
	
	private class W�rfelHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			GUI.this.spielfeld.w�rfle();
		}
		
	}

	public static void main(String[] args) {
		
		//initialize Model
		Spielerinfos[] infos = new Spielerinfos[4];
		infos[0] = new Spielerinfos("Adam", Color.RED);
		infos[1] = new Spielerinfos("Bertha", Color.BLUE);
		infos[2] = new Spielerinfos("Charlie", Color.GREEN);
		infos[3] = new Spielerinfos("Denise", Color.YELLOW);		

		ArrayList<Frage> fragen1 = new ArrayList<>();
		ArrayList<Frage> fragen2 = new ArrayList<>();
		ArrayList<Frage> fragen3 = new ArrayList<>();
		ArrayList<Frage> fragen4 = new ArrayList<>();
		for (int i = 0; i < 48; i++) {
			switch (i % 4) {
			case 0:
				fragen1.add(new Frage("Fragetext Nummer " + i, "L�sungstext Nummer " + i));
				break;
			case 1:
				fragen2.add(new Frage("Fragetext Nummer " + i, "L�sungstext Nummer " + i));
				break;
			case 2:
				fragen3.add(new Frage("Fragetext Nummer " + i, "L�sungstext Nummer " + i));
				break;
			case 3:
				fragen4.add(new Frage("Fragetext Nummer " + i, "L�sungstext Nummer " + i));
				break;	
			}
		}
		Kategorie[] kategorien = new Kategorie[4];
		kategorien[0] = new Kategorie("Kategorie 1", fragen1);
		kategorien[1] = new Kategorie("Kategorie 2", fragen2);
		kategorien[2] = new Kategorie("Kategorie 3", fragen3);
		kategorien[3] = new Kategorie("Kategorie 4", fragen4);		
		
		Spielfeld spielfeld = new Spielfeld(infos, kategorien);			
		
		//initialize GUI
		new GUI(spielfeld);

	}

}
