package userinterface;

import javax.swing.JButton;
import javax.swing.JFrame;

import controller.GameController;
import model.Spielfeldmanager;

public class GUI extends JFrame implements GameObserver{
	
	//GUI observing spielfeld
	private final Spielfeldmanager spielfeld;
	
	private final GameController controller;
	
	private final JButton w�rfel;
	
	public GUI(Spielfeldmanager spielfeld) {
		
		//attach to spielfeld
		this.spielfeld = spielfeld;
		spielfeld.attach(this);
		
		//make controller
		this.controller = new GameController(spielfeld);
		
		
		setTitle("Learn-Master");
		setSize(1000,700);
		setResizable(false);
		setLayout(null);
		setVisible(true);
		//add circle layout
		//add w�rfel
		w�rfel = new JButton();
		w�rfel.setBounds(450,300,100,100);
		w�rfel.addActionListener(controller);
		w�rfel.setText("w�rfeln");
		add(w�rfel);
	}
	
	
	/**
	 * spielfeldstate was updated
	 */
	public void update() {
		//spielfeld.getData();
	}
}
