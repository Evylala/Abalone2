package jeu;

/**
* Représentation des cases du plateau du jeu
* Les CaseOut correspondent aux cases en dehors du plateau
* De ce fait, si une boule est positionnée sur cette case, elle est détruite du jeu.
*/
public class CaseOut extends Case {

	public CaseOut() {
	}

	/**
	* Si on boule est positionnée sur cette case, elle est supprimée.
	* Le nombre de boule du joueur possedant la boule diminue de 1
	* La boule est supprimée de la collection de boule du joueur
	*/
	@Override
	public void setBoule(Boule boule) {
		Joueur j = boule.getJoueur();
		j.supprBoule(boule);
	}
	
	/**
	* Retourne toujours vrai, car cette case est toujours vide
	*/
	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public String toString() {
		return ".";
	}

}
