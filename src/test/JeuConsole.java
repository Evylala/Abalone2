package test;

import helper.HelperConsole;
import jeu.Boule;
import jeu.Case;
import jeu.Direction;
import jeu.Jeu;

public class JeuConsole {

	public static void main(String[] args) {

		Jeu jeu = new Jeu();
		HelperConsole helper = new HelperConsole(jeu);
		int nbTour = 0;

		// Initialisation de la partie
		jeu.setJoueur1("R", Jeu.JOUEUR_HUMAIN);
		jeu.setJoueur2("C", Jeu.JOUEUR_IA);

		jeu.demarrer();

		while (jeu.isEnCours()) {
			// On affiche l'état des joueurs en cours
			helper.afficherJoueur();
			// On affiche le nom du joueur qui doit réaliser le mouvement
			System.out.println("Joueur actuel : "
					+ jeu.getJoueurActuel().getNom());
			// On affiche le plateau
			helper.afficherPlateau();
			// On demande le mouvement au joueur
			helper.appelCommande();
			// On termine le tour, passage au joueur suivant
			jeu.finirTour();

		}

		System.out.println(jeu.getGagnant().getNom());
		System.out.println("Nombre de tour : " + nbTour);

	}
}
