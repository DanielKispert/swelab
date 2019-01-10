package application.zug;


import application.statemachine.StateMachineFactory;
import application.statemachine.port.StateMachinePort;
import application.zug.impl.Feld;
import application.zug.impl.Kategorie;
import application.zug.impl.Spieler;
import application.zug.impl.ZugMachen;
import application.zug.port.ZugManagement;
import application.zug.port.ZugManagementPort;

public class ZugFactoryImpl implements ZugFactory, ZugManagementPort, ZugManagement {
	
	private StateMachinePort stateMachinePort = StateMachineFactory.FACTORY.stateMachinePort();
	
	private ZugMachen manager;
	
	public synchronized ZugManagement zugManagement() {
		if (this.manager == null) {
			this.stateMachinePort.stateMachine();
			this.manager = new ZugMachen(this.stateMachinePort);
		}
		return this;
	}

	@Override
	public ZugManagementPort zugManagerPort() {
		return this;
	}

	@Override
	public void beginneZug() {
		this.manager.beginneZug();
		
	}

	@Override
	public void zieheVonHeimatfeld(Spieler spieler) {
		this.manager.zieheVonHeimatfeld(spieler);
		
	}

	@Override
	public void zieheVonFeld(int id) {
		this.manager.zieheVonFeld(id);
		
	}

	@Override
	public void zieheAufFeld(int id) {
		this.manager.zieheAufFeld(id);			
	}

	@Override
	public void wähleKategorieFürFrage(int id) {
		this.manager.wähleKategorieFürFrage(id);
		
	}

	@Override
	public void frageRichtigBeantwortet() {
		this.manager.frageRichtigBeantwortet();
		
	}

	@Override
	public void frageFalschBeantwortet() {
		this.manager.frageFalschBeantwortet();
		
	}

	@Override
	public Spieler[] getSpieler() {
		return this.manager.getSpieler();
	}

	@Override
	public Spieler getSpielerAmZug() {
		return this.manager.getSpielerAmZug();
	}

	@Override
	public int getGewürfelt() {
		return this.manager.getGewürfelt();
	}

	@Override
	public boolean getDarfNochWürfeln() {
		return this.manager.getDarfNochWürfeln();
	}

	@Override
	public String getWissensstandVon(Spieler spieler) {
		return this.manager.getWissensstandVon(spieler);
	}

	@Override
	public Kategorie[] getKategorien() {
		return this.manager.getKategorien();
	}

	@Override
	public Feld[] getFelder() {
		return this.manager.getFelder();
	}

	@Override
	public String getAusgewählteKategorieNameFürFrage() {
		return this.manager.getAusgewählteKategorieNameFürFrage();
	}

	@Override
	public String getAktuellerFragetext() {
		return this.manager.getAktuellerFragetext();
	}
	
	@Override
	public String getAktuellerLösungstext() {
		return this.manager.getAktuellerLösungstext();
	}

	@Override
	public Spieler getGewinner() {
		return this.manager.getGewinner();
	}
	
	
	



	
	
}
