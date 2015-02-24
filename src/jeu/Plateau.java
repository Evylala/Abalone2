package jeu;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Classe représentant le plateau du jeu.
 * 
 * @author Roman
 * 
 */
public class Plateau {

	public static final int HAUTEUR = 11;
	public static final int LARGEUR = 11;
	private Case[][] plateau = new Case[HAUTEUR][LARGEUR * 2];

	public Plateau() {

		this.creerGrille();
		this.adjacentes();
	}

	private void creerLigne(int i, int j) {
		if ((j % 2 == i % 2) || (j % 2 == i % 2)) {
			this.plateau[i][j] = new CaseValide();
		} else {
			this.plateau[i][j] = null;
		}
	}

	/**
	 * Positionnent les cases dans le tableau
	 */
	private void creerGrille() {
		for (int i = 0; i < HAUTEUR; i++) {
			for (int j = 0; j < LARGEUR * 2; j++) {
				if (i == 0 || i == 10) {
					this.plateau[i][j] = new CaseOut();
				} else if (i == 1 || i == 9) {
					if (j == 0 || j == 1 || j == 2 || j == 3 || j == 4
							|| j == 5 || j == 6 || j == 16 || j == 17
							|| j == 18 || j == 19 || j == 20 || j == 21) {
						this.plateau[i][j] = new CaseOut();
					} else {
						this.creerLigne(i, j);
					}
				} else if (i == 2 || i == 8) {
					if (j == 0 || j == 1 || j == 2 || j == 3 || j == 4
							|| j == 5 || j == 17 || j == 18 || j == 19
							|| j == 20 || j == 21) {
						this.plateau[i][j] = new CaseOut();
					} else {
						this.creerLigne(i, j);
					}
				} else if (i == 3 || i == 7) {
					if (j == 0 || j == 1 || j == 2 || j == 3 || j == 4
							|| j == 18 || j == 19 || j == 20 || j == 21) {
						this.plateau[i][j] = new CaseOut();
					} else {
						this.creerLigne(i, j);
					}
				} else if (i == 4 || i == 6) {
					if (j == 0 || j == 1 || j == 2 || j == 3 || j == 19
							|| j == 20 || j == 21) {
						this.plateau[i][j] = new CaseOut();
					} else {
						this.creerLigne(i, j);
					}
				} else if (i == 5) {
					if (j == 0 || j == 1 || j == 2 || j == 20 || j == 21) {
						this.plateau[i][j] = new CaseOut();
					} else {
						this.creerLigne(i, j);
					}
				} else {
					this.creerLigne(i, j);
				}
			}
		}

	}

	private void adjacentes() {

		for (int i = 0; i < HAUTEUR; i++) {
			for (int j = 0; j < LARGEUR * 2; j++) {
				if (this.plateau[i][j] instanceof CaseValide) {
					this.plateau[i][j].adjacentes(i, j, this);
				}
			}
		}

	}

	public Case getCase(int i, int j) {
		return this.plateau[i][j];
	}

	public Case getCaseValide(int i, int j){
		i++;
		int k =0;
		Case c = this.plateau[i][0];
		for(int l = 0; l < 20; l++){
			c = this.plateau[i][l];
			
			if(c instanceof CaseValide){
				k++;
			}
			if(k == j){
				break;
			}
			
		}
		
		return c;
	}
	
	private boolean pousser(Boule b, Direction d, int nbBoule) throws Exception{
		List<Case> tabCase = new ArrayList<Case>();
		tabCase.add(0, b.getCase());
		for(int i = 1; i < nbBoule*2 ; i++){
			Case add = tabCase.get(i-1).getVoisin(d);
			if(add != null){
				if(i<nbBoule && !b.getJoueur().equals(add.getBoule().getJoueur())){
					throw new Exception("Deplacement impossible");
				}
				if(!add.isEmpty() && i>=nbBoule && b.getJoueur().equals(add.getBoule().getJoueur())){
					throw new Exception("Deplacement impossible");
				}
				tabCase.add(i, add);
			}
		}
		if(tabCase.get(tabCase.size()-1).isEmpty()){
			for(int i = tabCase.size()-1; i > 0; i--){
				if(!tabCase.get(i-1).isEmpty()){
					tabCase.get(i).setBoule(tabCase.get(i-1).getBoule());	
				}
			}
			tabCase.get(0).setBoule(null);
		}
		else{
			throw new Exception("Deplacement impossible");
		}
		return true;
	}
	
	/**
	 * Méthode permettant de déplacer une unique boule sur le plateau Gros
	 * réfactoring sur cette méthode
	 * @throws Exception 
	 */
	public void DeplaceUn(Boule b1, Direction d) throws Exception {
		Case c = b1.getCase();
		Case dest = c.getVoisin(d);
		if (dest.isEmpty()) {
			dest.setBoule(c.getBoule());
			c.setBoule(null);

		} else {
			Boule bouleDest = dest.getBoule();
			if (bouleDest.getJoueur().equals(c.getBoule().getJoueur())) {
				// Impossible de déplacer une boule d'un joueur sur une autre
				// du même joueur
				throw new Exception("Deplacement impossible");
			} else {
				Case destDest = dest.getVoisin(d);

				if (destDest.isEmpty()) {
					destDest.setBoule(dest.getBoule());
					dest.setBoule(c.getBoule());
					c.setBoule(null);

				} else {
					// Dans ce cas, peut importe à qui appartient la boule
					// derrière celle de l'adversaire, le mouvement est
					// impossible
					throw new Exception("Deplacement impossible");
				}
			}
		}
	}

	public void DeplaceDeux(Boule b1, Boule b2, Direction d) throws Exception {
		// Test s'il s'agit d'un déplacement en ligne ou en fleche
		if(!b1.isVoisin(b2)){
			throw new Exception("Boule non consécutive");
		}
		boolean align1 = b1.getCase().getVoisin(d).equals(b2.getCase())
				|| b2.getCase().getVoisin(d).equals(b1.getCase());
		boolean align2 = b1.getCase().getVoisin(Direction.inverse(d))
				.equals(b2.getCase())
				|| b2.getCase().getVoisin(Direction.inverse(d))
						.equals(b1.getCase());
		if (align1 || align2) {
			//Deplacement en ligne
			if(b1.getBoule(Direction.inverse(d)) != b2){
				this.pousser(b1, d, 2);
			}
			else if(b2.getBoule(Direction.inverse(d)) != b1){
				//La boule 2 est à l'arrière
				this.pousser(b2, d, 2);
			}
			
		} else {
			//Deplacement en fleche
			this.DeplaceUn(b1, d);
			this.DeplaceUn(b2, d);
		}
	}

	public void DeplaceTrois(Boule b1, Boule b2, Boule b3, Direction d) throws Exception {
		//On vérifie que les boules sont consécutive
		if(!b1.isVoisin(b2) || !b2.isVoisin(b3)){
			
		}
		if(b2.getCase().getVoisin(b1.getDirection(b2)).getBoule() != b3){
			throw new Exception("Boule non consécutive");
		}
		
		// Test s'il s'agit d'un déplacement en ligne ou en fleche
		boolean align1 = b1.getCase().getVoisin(d).equals(b2.getCase())
				&& b2.getCase().getVoisin(d).equals(b3.getCase());
		boolean align2 = b3.getCase().getVoisin(d).equals(b2.getCase())
				&& b2.getCase().getVoisin(d).equals(b1.getCase());
		boolean align3 = b1.getCase().getVoisin(d).equals(b2.getCase())
				&& b2.getCase().getVoisin(d).equals(b3.getCase());
		boolean align4 = b3.getCase().getVoisin(Direction.inverse(d))
				.equals(b2.getCase())
				&& b2.getCase().getVoisin(Direction.inverse(d))
						.equals(b1.getCase());

		if (align1 || align2 || align3 || align4) {
			
			//Deplacement en ligne
			if(b1.getBoule(Direction.inverse(d)) != b2 && b1.getBoule(Direction.inverse(d)) != b3){
				//La boule 1 est à l'arrière
				this.pousser(b1, d, 3);
			}
			else if(b2.getBoule(Direction.inverse(d)) != b1 && b2.getBoule(Direction.inverse(d)) != b3){
				//La boule 2 est à l'arrière
				this.pousser(b2, d, 3);
			}
			else if(b3.getBoule(Direction.inverse(d)) != b1 && b3.getBoule(Direction.inverse(d)) != b2){
				this.pousser(b3, d, 3);
			}

		} else {
			//Deplacement en fleche
			
			this.DeplaceUn(b1, d);
			this.DeplaceUn(b2, d);
			this.DeplaceUn(b3, d);
		}

	}
	
	

}
