package uiControls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.FenetreJeu;

public class ControlMenuQuitter implements ActionListener {
	
	private FenetreJeu fen;
	
	public ControlMenuQuitter(FenetreJeu fen) {
		this.fen = fen;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.fen.dispose();
		
	}

}
