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

	public GUI(Spielfeldmanager spielfeld) {

		// attach to spielfeld
		this.spielfeld = spielfeld;
		spielfeld.attach(this);

		// make controller
		this.myController = new GameController(this, spielfeld);

		setTitle("Learn-Master");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 700);
		setResizable(false);
		setLayout(null);
		setVisible(true);
		// add circle layout
		// add w�rfel
		w�rfel = new JButton();
		w�rfel.setBounds(450, 325, 100, 50);
		w�rfel.addActionListener(myController);
		w�rfel.setText("w�rfeln");
		add(w�rfel);
		// add w�rfeldisplay
		w�rfelDisplay = new JLabel("", SwingConstants.CENTER);
		w�rfelDisplay.setBounds(450, 275, 100, 50);
		w�rfelDisplay.setBorder(BorderFactory.createLineBorder(Color.black));
		add(w�rfelDisplay);
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
