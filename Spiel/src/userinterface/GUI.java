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

	private final JButton w�rfel;

	private final JLabel w�rfelDisplay;
	
	private final FeldButton[] felder;

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
		setVisible(true);
		// add circle layout
		// add w�rfel
		w�rfel = new JButton();
		w�rfel.setBounds(475, 475, 100, 50);
		w�rfel.addActionListener(myController);
		w�rfel.setText("w�rfeln");
		add(w�rfel);
		// add w�rfeldisplay
		w�rfelDisplay = new JLabel("", SwingConstants.CENTER);
		w�rfelDisplay.setBounds(475, 425, 100, 50);
		w�rfelDisplay.setBorder(BorderFactory.createLineBorder(Color.black));
		add(w�rfelDisplay);
		//add Kreis von Spielfeldern
		this.felder = new FeldButton[48];
		int feldPosX = 500;
		int feldPosY = 900;
		for (int i = 0; i < 48; i++) {			
			this.felder[i] = new FeldButton(i);
			this.felder[i].addActionListener(myController);
			this.felder[i].setBounds(feldPosX, feldPosY, FeldButton.SIZE, FeldButton.SIZE);
			this.felder[i].setForeground(Color.WHITE);
			this.felder[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
			add(this.felder[i]);
			if (i < 12) {
				feldPosX -= FeldButton.SIZE;
				feldPosY -= FeldButton.SIZE;
			} else if (i < 24) {
				
			} else if (i < 36) {
				
			} else {
				
			}
		}
	}

	/**
	 * spielfeldstate was updated
	 */
	public void update() {
		// update w�rfeldisplay
		w�rfelDisplay.setText(spielfeld.getGew�rfelt() + "");
	}
	
	public JButton getW�rfelButton() {
		return this.w�rfel;
	}
}
