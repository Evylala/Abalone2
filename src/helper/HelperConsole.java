package helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import jeu.Boule;
import jeu.CaseOut;
import jeu.Direction;
import jeu.Jeu;
import jeu.Plateau;

public class HelperConsole {

	private Jeu mJeu;
	
	
	public HelperConsole(Jeu jeu) {
		this.mJeu = jeu;
	}
	
	/**
	 * Méthode permettant d'interagir avec le joueur dans la console
	 */
	public void appelCommande(){
		//TODO: Implémenter cette méthode
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int nbBouleDeplace = 0;
		
		do{
			System.out.println("Nombre de boule :");
			try {
				String nbDeplacement = br.readLine();
				nbBouleDeplace = Integer.parseInt(nbDeplacement);
				
			} catch (Exception e) {
			}
			System.out.println(nbBouleDeplace);
		}
		while(nbBouleDeplace != 1 && nbBouleDeplace != 2 && nbBouleDeplace != 3);
		
		
		try {
			System.out.println("Colonne :");
			String sCol = br.readLine();
			int col = Integer.parseInt(sCol);
			
			System.out.println("Ligne :");
			String sLig = br.readLine();
			int lig = Integer.parseInt(sLig);
			
			System.out.println("Direction :");
			String sDir = br.readLine();
			Direction dir = null;
			switch (sDir) {
			case "HG":
				dir = Direction.HG;
				break;
			case "HD":
				dir = Direction.HD;
				break;
			case "G":
				dir = Direction.G;
				break;
			case "D":
				dir = Direction.D;
				break;
			case "BG":
				dir = Direction.BG;
				break;
			case "BD":
				dir = Direction.BD;
				break;
			default:
				break;
			}
			System.out.println(dir);
			
			Boule bouleDeplace = mJeu.getPlateau().getCaseValide(col, lig).getBoule();
			boolean movementReussi = mJeu.faireMouvement(bouleDeplace, dir);
			System.out.println("Mouvement réussi : "+movementReussi);
			
		} catch (Exception e) {
		}
		System.out.println(nbBouleDeplace);
		
		
		
			
	}

	/**
	* Méthode permettant d'afficher dans la console l'état des joueurs
	* C'est à dire le nombre de boules restant pour chaque joueur
	*/
	public void afficherJoueur(){
		System.out.println(mJeu.getJoueur1().getNom()+" : "+mJeu.getJoueur1().getNbBoule());
		System.out.println(mJeu.getJoueur2().getNom()+" : "+mJeu.getJoueur2().getNbBoule());
	}
	
	/**
	 * Méthode permettant d'afficher dans la console l'état actuel du plateau
	 */
	public void afficherPlateau() {
		Plateau p = this.mJeu.getPlateau();
		for (int i = 0; i < Plateau.HAUTEUR; i++) {
			for (int j = 0; j < Plateau.LARGEUR * 2; j++) {
				if(p.getCase(i, j) == null){
					System.out.print(" ");
				}
				else if( p.getCase(i, j) instanceof CaseOut){
					System.out.print(" ");
				}
				else if(p.getCase(i, j).isEmpty()){
					System.out.print(".");
				}
				else{
					System.out.print(p.getCase(i, j));
				}
					
			}
			System.out.println();
		}
	}
}
