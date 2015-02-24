package jeu;

/**
* Représentation des cases du plateau du jeu
* Les CaseValide correspondent aux cases du plateau.
* Ce sont sur ces cases que les boules se déplacent pendant le jeu
*/
public class CaseValide extends Case {

	@Override
	public void setBoule(Boule boule) {
		this.mBoule = boule;
		if(this.mBoule != null){
			this.mBoule.setCase(this);
		}
	}

	@Override
	public boolean isEmpty() {
		return this.mBoule == null;
	}
	
	@Override
	public String toString() {
		if(this.mBoule == null){
			return "o";
		}
		return this.mBoule.toString();
	}
}
