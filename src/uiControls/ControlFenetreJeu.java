package uiControls;

import java.util.Observable;
import java.util.Observer;

import ui.FenetreJeu;
import jeu.Jeu;

public class ControlFenetreJeu implements Observer {
	
	private FenetreJeu fen;
	
	public ControlFenetreJeu(FenetreJeu fen) {
		this.fen = fen;
	}

	@Override
	public void update(Observable o, Object arg1) {
		
		this.fen.setJeu((Jeu)o); 
		this.fen.remettreAZero();
		
	}

}
