package jeu;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

/**
 * Représentation des cases du plateau du jeu La case peut être de plusieurs
 * types Dans son cas le plus simple, il s'agit des cases du jeux sur lesquelles
 * les boules se déplacent (voir CaseValide)
 */
public abstract class Case {

	/*public static final int HG = 10;
	public static final int HD = 11;
	public static final int G = 12;
	public static final int D = 13;
	public static final int BG = 14;
	public static final int BD = 15;*/

	protected Boule mBoule;

	// Case adjacentes
	protected Case hg; // case haut gauche
	protected Case hd; // case haut droite
	protected Case g; // case gauche
	protected Case d; // case droite
	protected Case bg; // case bas gauche
	protected Case bd; // case bas droite

	public Case() {
		this.mBoule = null;
	}

	public void adjacentes(int i, int j, Plateau p) {
		this.hg = p.getCase(i - 1, j - 1);
		this.hd = p.getCase(i - 1, j + 1);
		this.g = p.getCase(i, j - 2);
		this.d = p.getCase(i, j + 2);
		this.bg = p.getCase(i + 1, j - 1);
		this.bd = p.getCase(i + 1, j + 1);
	}

	public Case getVoisin(Direction d) {
		switch (d) {
		case HG:
			return this.hg;
		case HD:
			return this.hd;
		case G:
			return this.g;
		case D:
			return this.d;
		case BG:
			return this.bg;
		case BD:
			return this.bd;
		default:
			throw new RuntimeErrorException(null, "Direction invalide");
		}
	}

	public void deplacerVers(Case dest) throws Exception {
		if (this.mBoule == null) {
			throw new Exception("Pas de boule");
		}
		dest.mBoule = this.mBoule;
	}

	public Boule getBoule() {
		return this.mBoule;
	}

	public abstract void setBoule(Boule boule);

	public abstract boolean isEmpty();

	@Override
	public abstract String toString();

	public boolean deplacable(Direction direction) {
		boolean deplace = false;
		if (this.getVoisin(direction).isEmpty()) {
			deplace = true;
		} else if (this.getVoisin(direction).deplacable(direction)
				&& !this.getVoisin(direction).getBoule().getJoueur()
						.equals(this.getBoule().getJoueur())) {
			deplace = true;
		}
		return deplace;
	}

	public boolean pousser(Direction direction) {
		boolean deplace = false;
		if (!this.getVoisin(direction).isEmpty()) {
			if(this.getVoisin(direction).deplacable(direction)
					&& !this.getVoisin(direction).getBoule().getJoueur()
					.equals(this.getBoule().getJoueur())){
				deplace = true;
			}
		}
		return deplace;
	}

	public List<Direction> mouvementPousserPossible() {
		List<Direction> mouvementPossible = new ArrayList<Direction>();

		if (this.pousser(Direction.BG) && this.bg instanceof CaseValide) {
			mouvementPossible.add(Direction.BG);
		}
		if (this.pousser(Direction.BD) && this.bd instanceof CaseValide) {
			mouvementPossible.add(Direction.BD);
		}
		if (this.pousser(Direction.G) && this.g instanceof CaseValide) {
			mouvementPossible.add(Direction.G);
		}
		if (this.pousser(Direction.D) && this.d instanceof CaseValide) {
			mouvementPossible.add(Direction.D);
		}
		if (this.pousser(Direction.HG) && this.hg instanceof CaseValide) {
			mouvementPossible.add(Direction.HG);
		}
		if (this.pousser(Direction.HD) && this.hd instanceof CaseValide) {
			mouvementPossible.add(Direction.HD);
		}
		return mouvementPossible;
	}

	public List<Direction> mouvementPossible() {
		List<Direction> mouvementPossible = new ArrayList<Direction>();

		if (this.deplacable(Direction.BG) && this.bg instanceof CaseValide) {
			mouvementPossible.add(Direction.BG);
		}
		if (this.deplacable(Direction.BD) && this.bd instanceof CaseValide) {
			mouvementPossible.add(Direction.BD);
		}
		if (this.deplacable(Direction.G) && this.g instanceof CaseValide) {
			mouvementPossible.add(Direction.G);
		}
		if (this.deplacable(Direction.D) && this.d instanceof CaseValide) {
			mouvementPossible.add(Direction.D);
		}
		if (this.deplacable(Direction.HG) && this.hg instanceof CaseValide) {
			mouvementPossible.add(Direction.HG);
		}
		if (this.deplacable(Direction.HD) && this.hd instanceof CaseValide) {
			mouvementPossible.add(Direction.HD);
		}
		return mouvementPossible;
	}

}
