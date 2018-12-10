package userinterface;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controller.GameController;
import model.Spielfeldmanager;

public class GUI extends JFrame implements GameObserver {

	/**
	 * whatever this is
	 */
	private static final long serialVersionUID = 1L;

	// GUI observing spielfeld
	private final Spielfeldmanager spielfeld;

	private final GameController myController;

	private final JButton würfel;

	private final JLabel würfelDisplay;
	
	private final JLabel spielerAmZug;
	
	private final FeldButton[] felder;
	
	private final JLabel[] wissensstandsanzeiger;
	
	private final KategorieButton[] kategorien;
	
	private final JButton[] heimatfelder;

	public GUI(Spielfeldmanager spielfeld) {

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
		// add circle layout
		// add würfel
		würfel = new JButton();
		würfel.setBounds(450, 525, 100, 50);
		würfel.addActionListener(myController);
		würfel.setText("würfeln");
		add(würfel);
		// add würfeldisplay
		würfelDisplay = new JLabel("", SwingConstants.CENTER);
		würfelDisplay.setBounds(450, 475, 100, 50);
		würfelDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(würfelDisplay);
		// add Spielerdisplay
		spielerAmZug = new JLabel("Am Zug: " + spielfeld.getNameVonSpielerAmZug(), SwingConstants.CENTER);
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
		// add Heimatfelder
		
		// add Kreis von Spielfeldern
		this.felder = new FeldButton[48];
		int feldPosX = 245;
		int feldPosY = 245;
		for (int i = 0; i < 48; i++) {
			this.felder[i] = new FeldButton(i);
			this.felder[i].addActionListener(myController);
			this.felder[i].setBounds(feldPosX, feldPosY, FeldButton.SIZE, FeldButton.SIZE);
			this.felder[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
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
		// update würfeldisplay
		würfelDisplay.setText(spielfeld.getGewürfelt() + "");
		// update Spieler an der Reihe
		spielerAmZug.setText("Am Zug: " + spielfeld.getNameVonSpielerAmZug());
		// update wissensstände & Kategorien & Heimatfelder
		for (int i = 0; i < 4; i++) {
			wissensstandsanzeiger[i].setText("<html>" + spielfeld.getSpieler()[i].getName() + ":<br>"
					+ spielfeld.getWissensstandVon(spielfeld.getSpieler()[i]));
			kategorien[i].setText(spielfeld.getKategorien()[i].getName());
			heimatfelder[i]
					.setText("<html><b>" + spielfeld.getSpieler()[i].getHeimatfelder().getAnzahlInHeimatfeldern());
		}

	}

	public JButton getWürfelButton() {
		return this.würfel;
	}
}
