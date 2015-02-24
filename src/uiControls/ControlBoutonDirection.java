package uiControls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import ui.FenetreJeu;
import jeu.Boule;
import jeu.Direction;
import jeu.Jeu;
import jeu.Plateau;

public class ControlBoutonDirection implements ActionListener {
	
	private Direction direction;
	private Jeu jeu;
	private FenetreJeu fen;
	private JTextArea labelAction;
	private JButton recommencer;
	
	public ControlBoutonDirection(Direction direction, Jeu jeu, FenetreJeu fen, JTextArea labelAction, JButton recommencer) {
		this.direction = direction;
		this.jeu = jeu;
		this.fen = fen;
		this.labelAction = labelAction;
		this.recommencer = recommencer;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		System.out.println("Action bouton direction");
		ArrayList<Boule> listeBoulesChoisies = this.fen.getListeBoulesChoisies();
		boolean mouvementPossible = false;
		JButton[][] lesBoutons;
		ActionListener[] action = null;
		System.out.println(listeBoulesChoisies.size());
		switch(listeBoulesChoisies.size()) {
			case 1 :
				if (this.jeu.faireMouvement(listeBoulesChoisies.get(0), direction)) {
					mouvementPossible = true;
				}
				break;
			case 2 :
				if (this.jeu.faireMouvement(listeBoulesChoisies.get(0), listeBoulesChoisies.get(1), direction)) {
					mouvementPossible = true;
				}
				break;
			case 3:
				if (this.jeu.faireMouvement(listeBoulesChoisies.get(0), listeBoulesChoisies.get(1), listeBoulesChoisies.get(2), direction)) {
					mouvementPossible = true;
				}
				break;
			default:
				break;
		}
		if (mouvementPossible) {
			this.jeu.finirTour();
			
			if (!this.jeu.isEnCours()) { // Partie terminée
				String msgGagnant = "Le gagnant est " + this.jeu.getGagnant().getNom() + " !"; 
				JOptionPane.showMessageDialog(this.fen, msgGagnant, "Fin de la partie", JOptionPane.PLAIN_MESSAGE);
				lesBoutons = this.fen.getLesBoutons();
				for (int i=0; i<Plateau.HAUTEUR; i++) {
					for (int j=3; j<(Plateau.LARGEUR*2)-2; j++) {
						if (lesBoutons[i][j] != null) {
							lesBoutons[i][j].setEnabled(true);
							action = lesBoutons[i][j].getActionListeners();
							lesBoutons[i][j].removeActionListener(action[0]);
						}
					}
				}
				this.recommencer.setEnabled(false);
			}
		} else {
			this.labelAction.setText("Mouvement Impossible, choisissez une nouvelle direction ou cliquez sur \"Recommencer\" pour choisir de nouvelles boules");
		}
	}

}
