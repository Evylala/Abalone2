package jeu;

import java.util.ArrayList;
import java.util.List;

public class Joueur {

	private String mNom;
	private int mType;
	private int score;

	private List<Boule> mBoules;

	public Joueur(String nom, int type) {
		this.mNom = nom;
		this.mType = type;
		this.mBoules = new ArrayList<Boule>();
		this.score = 0;
	}

	public String getNom() {
		return this.mNom;
	}
	
	public boolean isPerdant(){
		return this.getNbBoule() < 11;
	}

	public void ajoutBoule(Boule b) {
		this.mBoules.add(b);
	}

	public void supprBoule(Boule b) {
		this.mBoules.remove(b);
	}

	public int getNbBoule() {
		return this.mBoules.size();
	}

	public int getType() {
		return this.mType;
	}

	public Boule getBoule(int n) {
		return this.mBoules.get(n);

	}
	
	public int getScore() {
		return this.score;
	}
	
	public void ajoutPoint() {
		this.score++;
	}
	
	@Override
	public String toString() {
		return this.mNom;
	}

	/**
	 * Algo de l'IA
	 */
	public void jouerIA(Jeu jeu) {
		Case caseDeplace;
		
		//On choisit une boule aléatoirement dans la liste des boules du joueur
		// On vérifie que cette boule peut réaliser au moins un mouvement
		//Sinon on retire une boule aléatoirement
		do {
			int numBoule = (int) (Math.random() * this.getNbBoule());
			caseDeplace = this.getBoule(numBoule).getCase();
		} 
		while (caseDeplace.mouvementPossible().isEmpty());
		
		if(caseDeplace.mouvementPousserPossible().size() > 0){
			Direction mouvement = caseDeplace.mouvementPousserPossible().get(0);
			jeu.faireMouvement(caseDeplace.getBoule(), mouvement);
		}
		else{
			//On réalise un des mouvements possible de manière aléatoire
			int numDeplacement = (int) (Math.random() * caseDeplace
					.mouvementPossible().size());
			Direction mouvement = caseDeplace.mouvementPossible().get(numDeplacement);
			//On effectue le deplacement
			jeu.faireMouvement(caseDeplace.getBoule(), mouvement);
		}
		
		
	}

}
