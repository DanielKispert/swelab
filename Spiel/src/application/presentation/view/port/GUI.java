package application.presentation.view.port;

import javax.swing.JButton;

import application.presentation.view.impl.FeldButton;
import application.presentation.view.impl.HeimatfeldButton;
import application.presentation.view.impl.KategorieButton;

public interface GUI {	
	
	public JButton getWürfelButton();
	
	public HeimatfeldButton[] getHeimatfelder();
	
	public FeldButton[] getFelder();
	
	public KategorieButton[] getKategorien();
	
	public JButton getFrageRichtigButton();
	
	public JButton getFrageFalschButton();

}
