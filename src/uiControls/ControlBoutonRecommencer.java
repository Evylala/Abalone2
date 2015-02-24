package uiControls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.FenetreJeu;

public class ControlBoutonRecommencer implements ActionListener {
	
	private FenetreJeu fen;
	
	public ControlBoutonRecommencer(FenetreJeu fen) {
		this.fen = fen;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.fen.recommencerChoixDesBoules();
		
	}

}
