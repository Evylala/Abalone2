package jeu;


public class Boule {

	protected Joueur mJoueur;
	private Case mCase;
	
	public Boule(Joueur j) {
		this.mJoueur = j;
		j.ajoutBoule(this);
	}
	
	public void detruireBoule(){
		this.mJoueur.supprBoule(this);
	}

	public Joueur getJoueur() {
		return this.mJoueur;
	}
	
	@Override
	public String toString() {
		return this.mJoueur.getNom();
	}

	public void setCase(Case c) {
		this.mCase = c;
	}

	public Case getCase() {
		return mCase;
	}

	public boolean isVoisin(Boule b2) {
		for (Direction d : Direction.values()) {
			if(this.mCase.getVoisin(d).getBoule() == b2){
				return true;
			}
		}
		return false;
	}
	
	public Direction getDirection(Boule b2) throws Exception {
		for (Direction d : Direction.values()) {
			if(this.mCase.getVoisin(d).getBoule() == b2){
				return d;
			}
		}
		throw new Exception("Les boules ne sont pas consécutives");
	}
	
	public Boule getBoule(Direction d){
		return this.getCase().getVoisin(d).getBoule();
	}
	
}
