package uiControls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import ui.FenetreJeu;
import jeu.Boule;
import jeu.Jeu;

public class ControlBoutonCase implements ActionListener {
	
	private int lig;
	private int col;
	private FenetreJeu fen;
	private JLabel labelBoules;
	private JButton bouton;
	private Jeu jeu;
	private JTextArea labelAction;
	private ArrayList<JButton> boutonsDirection;
	private int nb;
	
	public ControlBoutonCase(int lig, int col, FenetreJeu fen, JLabel labelBoules, JTextArea labelAction, JButton bouton, Jeu jeu, ArrayList<JButton> boutonsDirection) {
		this.lig = lig;
		this.col = col;
		this.fen = fen;
		this.labelBoules = labelBoules;
		this.bouton = bouton;
		this.jeu = jeu;
		this.labelAction = labelAction;
		this.boutonsDirection = boutonsDirection;
		this.nb = 0;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(!this.fen.estDejaSelectionne(this.bouton)) {
			this.nb = this.fen.ajoutNbBoulesChoisies();
			Boule bouleChoisie = null;
			boolean aucuneDirectionPossible = true;
			
			if (nb <= 3) {
				this.labelBoules.setText(nb + " boule(s) choisie(s)");
				
				if (this.jeu.getJoueurActuel().equals(this.jeu.getJoueur1())) {
					this.bouton.setIcon(new ImageIcon("images"+File.separator+"Boule_Noire_Choisie.png"));
				} else {
					this.bouton.setIcon(new ImageIcon("images"+File.separator+"Boule_Blanche_Choisie.png"));
				}
				
	
				this.labelAction.setText("Veuillez choisir une boule à déplacer ou une direction");
				
				if (nb==1) {
					for (int i=0; i<this.boutonsDirection.size(); i++) {
						this.boutonsDirection.get(i).setEnabled(true);
					}
				}
				
				
				bouleChoisie = this.jeu.getPlateau().getCase(lig, col).getBoule();
				this.fen.ajoutBouleChoisie(bouleChoisie);
				this.fen.selectionneBouton(this.bouton);
			} 
			
			if (nb == 3) {
				
				for (int i=0; i<this.boutonsDirection.size(); i++) {
					if (this.boutonsDirection.get(i).isEnabled()) {
						aucuneDirectionPossible = false;
					}
				}
				
				if (!aucuneDirectionPossible) {
					this.labelAction.setText("Veuillez choisir une direction");
				}
			}
		} else {
			
		}
		
	}
	
}
