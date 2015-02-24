package test;

import helper.HelperConsole;
import jeu.Boule;
import jeu.Direction;
import jeu.Jeu;

public class TestHumainIA {

	public static void main(String[] args) {

		Jeu jeu = new Jeu();

		/*jeu.setJoueur1("G", Jeu.JOUEUR_HUMAIN);
		jeu.setJoueur2("Q", Jeu.JOUEUR_IA);

		jeu.demarrer();

		HelperConsole hc = new HelperConsole(jeu);

		hc.afficherJoueur();
		
		jeu.faireMouvement(jeu.getBoule(2, 1), Direction.BD);
		hc.afficherPlateau();
		jeu.finirTour();
		
		hc.afficherJoueur();
		System.out.println(jeu.getJoueurActuel().getNom());
		
		Boule b1 = jeu.getBoule(3, 2);
		Boule b2 = jeu.getBoule(2, 2);
		Boule b3 = jeu.getBoule(1, 2);
		System.out.println("equals"+b1.getJoueur().equals(b2.getJoueur()));
		jeu.faireMouvement(b1, b2, b3,  Direction.BG);
		jeu.finirTour();
		jeu.faireMouvement(b1, b2, b3,  Direction.BG);
		System.out.println("ETAPE 3");
		hc.afficherPlateau();
		jeu.finirTour();
		jeu.faireMouvement(b1, b2, b3,  Direction.BG);
		System.out.println("ETAPE 4");
		hc.afficherPlateau();
		jeu.finirTour();
		jeu.faireMouvement(b1, b2, b3,  Direction.D);
		jeu.finirTour();
		jeu.faireMouvement(b1, b2, b3,  Direction.D);
		jeu.finirTour();
		jeu.faireMouvement(b1, b2, b3,  Direction.BG);
		jeu.finirTour();
		jeu.faireMouvement(b1, b2, b3,  Direction.BG);
		jeu.finirTour();
		hc.afficherPlateau();
		jeu.faireMouvement(b1, b2, b3,  Direction.HD);
		jeu.finirTour();
		hc.afficherPlateau();
		jeu.faireMouvement(b1, b2, b3,  Direction.D);
		jeu.finirTour();
		System.out.println(jeu.faireMouvement(b1, b2, b3,  Direction.BG));
		jeu.finirTour();
		jeu.faireMouvement(b1, b2, b3,  Direction.BG);
		jeu.finirTour();
		jeu.faireMouvement(b1, b2, b3,  Direction.HD);
		jeu.finirTour();
		jeu.faireMouvement(b1, b2, b3,  Direction.HD);
		jeu.finirTour();
		
		jeu.faireMouvement(b1, b2, b3,  Direction.D);
		jeu.finirTour();
		jeu.faireMouvement(b1, b2, b3,  Direction.BG);
		jeu.finirTour();
		jeu.faireMouvement(b1, b2, b3,  Direction.BG);
		jeu.finirTour();
		jeu.faireMouvement(b1, b2, b3,  Direction.HD);
		jeu.finirTour();
		jeu.faireMouvement(b1, b2, b3,  Direction.HD);
		jeu.finirTour();
		
		jeu.faireMouvement(b1, b2, b3,  Direction.D);
		jeu.finirTour();
		jeu.faireMouvement(b1, b2, b3,  Direction.BG);
		jeu.finirTour();
		jeu.faireMouvement(b1, b2, b3,  Direction.BG);
		jeu.finirTour();
		
		System.out.println("en cours "+jeu.isEnCours()+jeu.getGagnant().toString());
		
		hc.afficherPlateau();*/
	
	}
}
