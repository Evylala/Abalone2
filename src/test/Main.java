package test;

import helper.HelperConsole;
import jeu.Boule;
import jeu.Case;
import jeu.Direction;
import jeu.Jeu;

public class Main {

	public static void main(String[] args) {

		Jeu jeu = new Jeu();

		// Initialisation de la partie
		/*jeu.setJoueur1("R", Jeu.JOUEUR_IA);
		jeu.setJoueur2("C", Jeu.JOUEUR_IA);

		jeu.demarrer();
		//int nbTour = 0;

		// Utilisation d'une classe d'aide pour manipuler le jeu dans la console
		HelperConsole helper = new HelperConsole(jeu);

		
		
		Case c1 = jeu.getPlateau().getCase(2, 12);
		Case c2 = c1.getVoisin(Direction.D);
		
		helper.afficherPlateau();
		
		Case cc1 = jeu.getPlateau().getCaseValide(7, 2);
		Case cc2 = cc1.getVoisin(Direction.D);
		Case cc3 = cc2.getVoisin(Direction.D);
		Case cc4 = cc3.getVoisin(Direction.D).getVoisin(Direction.D);
		
		jeu.getPlateau().getCaseValide(3, 2).setBoule(new Boule(jeu.getJoueur1()));
		jeu.getPlateau().getCaseValide(3, 3).setBoule(new Boule(jeu.getJoueur2()));
		
		jeu.getPlateau().getCaseValide(4, 2).setBoule(new Boule(jeu.getJoueur1()));
		jeu.getPlateau().getCaseValide(4, 3).setBoule(new Boule(jeu.getJoueur1()));
		jeu.getPlateau().getCaseValide(4, 4).setBoule(new Boule(jeu.getJoueur2()));
		
		jeu.getPlateau().getCaseValide(5, 2).setBoule(new Boule(jeu.getJoueur1()));
		jeu.getPlateau().getCaseValide(5, 3).setBoule(new Boule(jeu.getJoueur1()));
		jeu.getPlateau().getCaseValide(5, 4).setBoule(new Boule(jeu.getJoueur1()));
		jeu.getPlateau().getCaseValide(5, 5).setBoule(new Boule(jeu.getJoueur2()));
		jeu.getPlateau().getCaseValide(5, 6).setBoule(new Boule(jeu.getJoueur2()));
		
		
		System.out.println(jeu.faireMouvement(jeu.getPlateau().getCaseValide(3, 2).getBoule(), Direction.D));
		System.out.println(jeu.faireMouvement(jeu.getPlateau().getCaseValide(4, 2).getBoule(), jeu.getPlateau().getCaseValide(4, 3).getBoule(), Direction.D));
		System.out.println(jeu.faireMouvement(jeu.getPlateau().getCaseValide(5, 2).getBoule(), jeu.getPlateau().getCaseValide(5, 3).getBoule(), jeu.getPlateau().getCaseValide(5, 4).getBoule(), Direction.D));
		Boule bb1 = cc1.getBoule();
		Boule bb2 = cc2.getBoule();
		Boule bb3 = cc3.getBoule();
		Boule bb4 = cc4.getBoule();
		
		//System.out.println(bb1.isVoisin(bb2));
		//System.out.println(bb3.isVoisin(bb4));
		helper.afficherPlateau();
		
		// helper.afficherJoueur();
		// helper.afficherPlateau();
		/*while (jeu.isEnCours()) {
			jeu.finirTour();
			nbTour++;
			if(nbTour % 10 == 0){
				//helper.afficherPlateau();
			}
		}*/

	//System.out.println(jeu.getGagnant().getNom());
	//System.out.println("Nombre de tour : " + nbTour);*/

	}

}