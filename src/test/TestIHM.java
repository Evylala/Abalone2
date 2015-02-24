package test;

import ui.FenetreJeu;
import jeu.Jeu;
import jeu.Joueur;

public class TestIHM {
	
	public static void main(String[] args) {
		Jeu jeu = new Jeu();
		
		// Initialisation de la partie
		/*jeu.setJoueur1("Tom", Jeu.JOUEUR_HUMAIN);
		jeu.setJoueur2("Jerry", Jeu.JOUEUR_HUMAIN);
		
		jeu.demarrer();*/
		
		FenetreJeu fenetreDuJeu = new FenetreJeu(jeu);
	}

}
