package uiControls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.FenetreJeu;
import jeu.Jeu;

public class ControlMenuNouvellePartie implements ActionListener {
	
	private FenetreJeu fen;
	
	public ControlMenuNouvellePartie(FenetreJeu fen) {
		this.fen = fen;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Jeu jeu = new Jeu();
		
		this.fen.nouvellePartie(jeu);
	}

}
