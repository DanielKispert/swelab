package application.presentation;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import application.IntFeld;
import application.BrettspielObserver;
import application.IntLernspielSpieler;
import application.IntLernspielmanager;

public class GUI extends JFrame implements BrettspielObserver {

	/**
	 * whatever this is
	 */
	private static final long serialVersionUID = 1L;

	// GUI observing spielfeld
	private final IntLernspielmanager spielfeld;

	private final GameController myController;

	private final JButton w�rfel;
	
	private final JButton frageRichtigButton;
	
	private final JButton frageFalschButton;
	
	private final JLabel frageDisplay;

	private final JLabel w�rfelDisplay;
	
	private final JLabel spielerAmZug;
	
	private final JLabel infoFeld;
	
	private final FeldButton[] felder;
	
	private final JLabel[] wissensstandsanzeiger;
	
	private final KategorieButton[] kategorien;
	
	private final HeimatfeldButton[] heimatfelder;
	
	private IntLernspielmanager.Zugzustand zugzustand;
	
	private IntLernspielSpieler aktiverSpieler;

	public GUI(IntLernspielmanager spielfeld) {

		// attach to spielfeld
		this.spielfeld = spielfeld;
		spielfeld.attach(this);

		// make controller
		this.myController = new GameController(this, spielfeld);

		setTitle("Learn-Master");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 1000);
		setResizable(false);
		setLayout(null);
		//add Frage richtig & Frage falsch buttons
		frageRichtigButton = new JButton();
		frageRichtigButton.setBounds(315, 465, 70, 70);
		frageRichtigButton.setBackground(Color.GREEN);
		frageRichtigButton.addActionListener(myController);
		frageRichtigButton.setText("<html><br>Frage korrekt");
		add(frageRichtigButton);
		frageFalschButton = new JButton();
		frageFalschButton.setBounds(615, 465, 70, 70);
		frageFalschButton.setBackground(Color.RED);
		frageFalschButton.addActionListener(myController);
		frageFalschButton.setText("<html><br>Frage falsch");
		add(frageFalschButton);
		//add Frage Feld
		frageDisplay = new JLabel("", SwingConstants.CENTER);
		frageDisplay.setBounds(425, 300, 150, 100);
		frageDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(frageDisplay);
		// add Info Feld
		infoFeld = new JLabel("", SwingConstants.CENTER);
		infoFeld.setBounds(425, 600, 150, 100);
		infoFeld.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(infoFeld);
		// add w�rfel
		w�rfel = new JButton();
		w�rfel.setBounds(450, 525, 100, 50);
		w�rfel.addActionListener(myController);
		w�rfel.setText("w�rfeln");
		add(w�rfel);
		// add w�rfeldisplay
		w�rfelDisplay = new JLabel("", SwingConstants.CENTER);
		w�rfelDisplay.setBounds(450, 475, 100, 50);
		w�rfelDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(w�rfelDisplay);
		// add Spielerdisplay
		spielerAmZug = new JLabel("", SwingConstants.CENTER);
		spielerAmZug.setBounds(450, 425, 100, 50);
		spielerAmZug.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(spielerAmZug);
		// add Wissensstandsanzeiger & Kategoriebuttons & Heimatfelder
		this.wissensstandsanzeiger = new JLabel[4];
		this.kategorien = new KategorieButton[4];
		this.heimatfelder = new HeimatfeldButton[4];
		for (int i = 0; i < 4; i++) {
			this.wissensstandsanzeiger[i] = new JLabel("", SwingConstants.CENTER);
			this.wissensstandsanzeiger[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			this.wissensstandsanzeiger[i].setBackground(spielfeld.getSpieler()[i].getFarbe());
			this.wissensstandsanzeiger[i].setOpaque(true);
			add(this.wissensstandsanzeiger[i]);
			this.kategorien[i] = new KategorieButton(i);
			this.kategorien[i].addActionListener(myController);
			this.kategorien[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
			add(this.kategorien[i]);
			this.heimatfelder[i] = new HeimatfeldButton(spielfeld.getSpieler()[i]);
			this.heimatfelder[i].addActionListener(myController);
			this.heimatfelder[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			this.heimatfelder[i].setBackground(spielfeld.getSpieler()[i].getFarbe());
			add(this.heimatfelder[i]);
		}
		// Spieler 1 oben
		this.wissensstandsanzeiger[0].setBounds(450, 50, 100, 50);
		this.heimatfelder[0].setBounds(475, 100, 50, 50);
		// Spieler 2 rechts
		this.wissensstandsanzeiger[1].setBounds(850, 475, 100, 50);
		this.heimatfelder[1].setBounds(875, 525, 50, 50);
		// Spieler 3 unten
		this.wissensstandsanzeiger[2].setBounds(450, 850, 100, 50);
		this.heimatfelder[2].setBounds(475, 900, 50, 50);
		//Spieler 4 links
		this.wissensstandsanzeiger[3].setBounds(50, 475, 100, 50);
		this.heimatfelder[3].setBounds(75, 525, 50, 50);
		// Kategorien platzieren
		this.kategorien[0].setBounds(300, 300, 100, 100);
		this.kategorien[1].setBounds(600, 300, 100, 100);
		this.kategorien[2].setBounds(600, 600, 100, 100);
		this.kategorien[3].setBounds(300, 600, 100, 100);
		
		// add Kreis von Spielfeldern
		this.felder = new FeldButton[48];
		int feldPosX = 245;
		int feldPosY = 245;
		for (int i = 0; i < 48; i++) {
			this.felder[i] = new FeldButton(i);
			this.felder[i].addActionListener(myController);
			this.felder[i].setBounds(feldPosX, feldPosY, FeldButton.SIZE, FeldButton.SIZE);
			this.felder[i].setText("");
			switch (i) {
			case 0: this.felder[i].setBackground(spielfeld.getSpieler()[0].getFarbe()); break;
			case 12:this.felder[i].setBackground(spielfeld.getSpieler()[1].getFarbe()); break;
			case 24:this.felder[i].setBackground(spielfeld.getSpieler()[2].getFarbe()); break;
			case 36:this.felder[i].setBackground(spielfeld.getSpieler()[3].getFarbe()); break;
				
			}
			add(this.felder[i]);
			if (i < 12) {
				feldPosX += FeldButton.SIZE;
			} else if (i < 24) {
				feldPosY += FeldButton.SIZE;
			} else if (i < 36) {
				feldPosX -= FeldButton.SIZE;
			} else {
				feldPosY -= FeldButton.SIZE;
			}
		}

		//update view
		update();
		// show frame
		setVisible(true);
	}

	/**
	 * spielfeldstate was updated
	 */
	public void update() {
		this.zugzustand = spielfeld.getZugZustand();
		this.aktiverSpieler = spielfeld.getSpielerAmZug();
		// update w�rfeldisplay
		int gew�rfelteZahl = spielfeld.getGew�rfelt();
		if (gew�rfelteZahl != -1) {
			w�rfelDisplay.setText(spielfeld.getGew�rfelt() + "");;
		}  else {
			w�rfelDisplay.setText("");
		}
		//update w�rfelbutton
		if (spielfeld.getDarfNochW�rfeln()) {
			w�rfel.setEnabled(true);
		} else {
			w�rfel.setEnabled(false);
		}
		// update Spieler am Zug
		spielerAmZug.setText("Am Zug: " + aktiverSpieler.getName());
		// update wissensst�nde & Kategorien & Heimatfelder
		for (int i = 0; i < 4; i++) {
			wissensstandsanzeiger[i].setText("<html>" + spielfeld.getSpieler()[i].getName() + ":<br>"
					+ spielfeld.getWissensstandVon(spielfeld.getSpieler()[i]));
			kategorien[i].setText(spielfeld.getKategorien()[i].getName());
			heimatfelder[i]
					.setText("<html><b>" + spielfeld.getSpieler()[i].getHeimatfelder().getAnzahlInHeimatfeldern());
		}
		// update Felder
		IntFeld[] feldUpdate = spielfeld.getFelder();
		for (int i = 0; i < 48; i++) {
			IntLernspielSpieler besetztVon = feldUpdate[i].getBesetztVon();
			if (besetztVon == null) {
				felder[i].setText("");
				felder[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
			} else {
				felder[i].setBorder(BorderFactory.createLineBorder(besetztVon.getFarbe(), 4));
				felder[i].setText("O");
			}
		}
		// Update Fragefeld & Buttons
		this.frageDisplay.setText("");
		// update InfoFeld & block Fields
		String infoString = "<html><br>";
		switch (zugzustand) {
		case DARF_NOCH_W�RFELN:
			infoString += "bitte w�rfeln";
			break;
		case MUSS_AUS_HEIMATFELD_ZIEHEN:
			//block Heimatfelder anderer Spieler
			for (int i = 0; i < heimatfelder.length; i++) {
				if (!heimatfelder[i].getSpieler().equals(aktiverSpieler)) {
					heimatfelder[i].setEnabled(false);
				}
			}
			infoString += "bitte Heimatfeld ausw�hlen";
			break;
		case MUSS_FELD_ZIEHEN:
			infoString += "bitte Spielfigur ausw�hlen";
			break;
		case FIGUR_AUS_FELD_AUSGEW�HLT:
			infoString += "bitte Zielfeld ausw�hlen";
			break;
		case FIGUR_AUS_HEIMATFELD_AUSGEW�HLT:
			for (HeimatfeldButton heimatfeld: heimatfelder) {
				heimatfeld.setEnabled(true);
			}
			infoString += "bitte Startfeld ausw�hlen";
			break;
		case FRAGE_KATEGORIE_AUSW�HLEN:
			infoString += "Bitte eine Kategorie f�r die Frage ausw�hlen";
			break;
		case ERSTE_FRAGE_BEANTWORTEN:
			infoString += spielfeld.getAusgew�hlteKategorieNameF�rFrage() + " ausgew�hlt";
			// f�lle Fragedisplay
			this.frageDisplay.setText(spielfeld.getAktuellerFragetext());
			break;
		case ZWEITE_FRAGE_BEANTWORTEN:
			infoString += this.aktiverSpieler.getName() + " muss eine Frage beantworten";
			// f�lle Fragedisplay
			this.frageDisplay.setText(spielfeld.getAktuellerFragetext());
			break;
		case GEWONNEN:
			infoString += this.spielfeld.getGewinner().getName() + " hat gewonnen!";
			break;
		}
		infoFeld.setText(infoString);

	}

	public JButton getW�rfelButton() {
		return this.w�rfel;
	}
	
	public HeimatfeldButton[] getHeimatfelder() {
		return this.heimatfelder;
	}
	
	public FeldButton[] getFelder() {
		return this.felder;
	}
	
	public KategorieButton[] getKategorien() {
		return this.kategorien;
	}
	
	public JButton getFrageRichtigButton() {
		return this.frageRichtigButton;
	}
	
	public JButton getFrageFalschButton() {
		return this.frageFalschButton;
	}
}
