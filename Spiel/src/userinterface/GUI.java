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
		// add würfel
		würfel = new JButton();
		würfel.setBounds(450, 325, 100, 50);
		würfel.addActionListener(myController);
		würfel.setText("würfeln");
		add(würfel);
		// add würfeldisplay
		würfelDisplay = new JLabel("", SwingConstants.CENTER);
		würfelDisplay.setBounds(450, 275, 100, 50);
		würfelDisplay.setBorder(BorderFactory.createLineBorder(Color.black));
		add(würfelDisplay);
	}

	/**
	 * spielfeldstate was updated
	 */
	public void update() {
		// update würfeldisplay
		würfelDisplay.setText(spielfeld.getGewürfelt() + "");
	}
	
	public JButton getWürfelButton() {
		return this.würfel;
	}
}
