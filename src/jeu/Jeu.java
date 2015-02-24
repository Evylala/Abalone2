package jeu;

import java.util.Observable;


/**
 * Classe gérant l'integralité du jeu Abalone
 * 
 * Il s'agit de l'API à utiliser pour réaliser une partie. Elle fournit les
 * différentes méthodes nécessaires à l'éxécution de la boucle du jeu
 * 
 * Les méthodes sont à appeler dans l'ordre suivant : - setJoueur1() -
 * setJoueur2() - demmarer()
 * 
 * METHODE DE LA BOUCLE DE JEU - isEnCours() - la ou les méthodes de déplacement
 * - finirTour()
 * 
 * Une fois que la partie est finie - getGagnant()
 * 
 * @author Roman
 * 
 */
public class Jeu extends Observable {

	// Les deux constantes pour l'instanciation des joueurs
	public static final int JOUEUR_IA = 1;
	public static final int JOUEUR_HUMAIN = 2;

	private Joueur mJoueur1;
	private Joueur mJoueur2;

	private Joueur mJoueurActuel;

	private boolean mEnCours;

	private Plateau mPlateau;
	private Joueur mGagnant;
	
	private boolean mPeutJouer;

	public Jeu() {
		this.mEnCours = false;
	}

	/**
	 * Initialise le premier joueur Celui-ci sera souvent un joueur Humain
	 * 
	 * @param nom
	 *            du joueur
	 * @param type
	 *            de joueur
	 */
	public void setJoueur1(String nom, int type) {
		this.mJoueur1 = new Joueur(nom, type);
	}

	/**
	 * Initialisation du second joueur Il pourra être Humain si l'on désire
	 * réaliser une partie avec deux joueurs Dans le cas contraire, il sera IA
	 * pour jouer avec un seul joueur
	 * 
	 * @param nom
	 *            du joueur
	 * @param type
	 *            de joueur
	 */
	public void setJoueur2(String nom, int type) {
		this.mJoueur2 = new Joueur(nom, type);
	}

	/**
	 * Initialisation de la partie Méthode à appeler après avoir initialisé les
	 * deux joueurs et avant d'utiliser les différentes méthodes du jeu. Cette
	 * méthode peut être appelée à tout moment : -Avant la partie, à condition
	 * que les deux joueurs sont bien créés (setJoueur1(), setJoueur2()) -En
	 * cours de partie, cela réinitialise le jeu et repositionne les boules à
	 * leurs places de départ -A la fin de la partie, permet de rejouer
	 * 
	 * @return True si le jeu a démarré correctement
	 */
	public boolean demarrer() {
		
		if (this.mJoueur1 != null && this.mJoueur2 != null) {
			this.mPlateau = new Plateau();
			this.positionnerBoules();
			this.mEnCours = true;
			this.mJoueurActuel = mJoueur1;
			this.mPeutJouer = true;
			
			for (int i = 0; i < 11; i++) {
				for (int j = 0; j < 11 * 2; j++) {
					if (this.mPlateau.getCase(i, j) != null && this.mPlateau.getCase(i, j).getBoule() != null) {
					}
				}
			}
			
			return true;
			
		} else {
			System.out.println("Les joueurs ne sont pas initialisés");
			return false;
		}
		
	}

	/**
	 * Méthode pour initialiser la position des boules sur le plateau pour les
	 * deux joueurs
	 */
	private void positionnerBoules() {

		Case c = this.mPlateau.getCase(1, 7);
		c.setBoule(new Boule(mJoueur1));
		c = c.d;
		c.setBoule(new Boule(mJoueur1));
		c = c.d;
		c.setBoule(new Boule(mJoueur1));
		c = c.d;
		c.setBoule(new Boule(mJoueur1));
		c = c.d;
		c.setBoule(new Boule(mJoueur1));
		c = c.bd;
		c.setBoule(new Boule(mJoueur1));
		c = c.g;
		c.setBoule(new Boule(mJoueur1));
		c = c.g;
		c.setBoule(new Boule(mJoueur1));
		c = c.g;
		c.setBoule(new Boule(mJoueur1));
		c = c.g;
		c.setBoule(new Boule(mJoueur1));
		c = c.g;
		c.setBoule(new Boule(mJoueur1));
		c = c.bg.bg.bg.bd.bd.bd.bd;

		c.setBoule(new Boule(mJoueur2));
		c = c.d;
		c.setBoule(new Boule(mJoueur2));
		c = c.d;
		c.setBoule(new Boule(mJoueur2));
		c = c.d;
		c.setBoule(new Boule(mJoueur2));
		c = c.d;
		c.setBoule(new Boule(mJoueur2));
		c = c.hd;
		c.setBoule(new Boule(mJoueur2));
		c = c.g;
		c.setBoule(new Boule(mJoueur2));
		c = c.g;
		c.setBoule(new Boule(mJoueur2));
		c = c.g;
		c.setBoule(new Boule(mJoueur2));
		c = c.g;
		c.setBoule(new Boule(mJoueur2));
		c = c.g;
		c.setBoule(new Boule(mJoueur2));
	}

	/**
	 * Retourne true si la partie n'est pas encore terminée Cette méthode permet
	 * de réaliser le test de la boucle du jeu Tant que le jeu est en cours (i-e
	 * return true), on appelle le joueur à réaliser le(s) mouvements qu'il
	 * souhaite Une fois que le jeu est terminé (i-e return false), il devient
	 * impossible d'agir sur le jeu, on ne peut alors que reccupérer le gagnant
	 * et recommencer une partie
	 * 
	 * @return
	 */
	public boolean isEnCours() {
		return this.mEnCours;
	}

	/**
	 * Après avoir réaliser le ou les déplacement de pions, appeler cette
	 * méthode pour terminer le tour, Si le joueur suivant est une IA, son tour
	 * sera automatiquement joué à l'appel de la méthode Dans le cas contraire
	 * (joueur humain), le joueur actuel passe au joueur suivant. La méthode
	 * vérifie si l'un des joueurs a gagné. Dans ce cas, le jeu se termine
	 * (isEnCours()) et le gagnant est défini
	 */
	public void finirTour() {
		this.mJoueurActuel = this.mJoueurActuel.equals(mJoueur1) ? mJoueur2
				: mJoueur1;
		this.mPeutJouer = true;
		// Si le jeu est toujours en cours faire jouer l'IA (si elle existe, ou
		// passer au joueur suivant
		if (this.isEnCours() && this.mJoueur1.getType() == JOUEUR_IA) {
			this.getJoueur1().jouerIA(this);
			this.mJoueurActuel = this.mJoueurActuel.equals(mJoueur1) ? mJoueur2
					: mJoueur1;
			this.mPeutJouer = true;
		}

		// Si le jeu est toujours en cours faire jouer l'IA (si elle existe, ou
		// passer au joueur suivant
		if (this.isEnCours() && this.mJoueur2.getType() == JOUEUR_IA) {
			this.getJoueur2().jouerIA(this);
			this.mJoueurActuel = this.mJoueurActuel.equals(mJoueur1) ? mJoueur2
					: mJoueur1;
			this.mPeutJouer = true;
		}

		if (this.mJoueur1.isPerdant()) {
			this.mEnCours = false;
			this.mGagnant = mJoueur2;
		} else if (this.mJoueur2.isPerdant()) {
			this.mEnCours = false;
			this.mGagnant = mJoueur1;
		}
		this.setChanged();
		this.notifyObservers("test");
	}
	
	public void lancerPartie() {
		while (this.mEnCours) {
			
		}
	}

	/**
	 * Retourne le joueur qui doit jouer Il s'agit du joueur pour lequel le(s)
	 * porchains mouvements seront réalisés Ce joueur actuel est modifié après
	 * l'appel de finirTour()
	 * 
	 * @return Joueur le joueur qui va réaliser le prochain mouvement
	 */
	public Joueur getJoueurActuel() {
		return this.mJoueurActuel;
	}

	/**
	 * retourne le gagnant à la fin de la partie Cette méthode ne peut être
	 * appelée que si le jeu est terminé (voir isEnCours())
	 * 
	 * @return
	 */
	public Joueur getGagnant() {

		if (!this.isEnCours()) {
			return this.mGagnant;
		} else {
			throw new RuntimeException("Le jeu n'est pas fini");
		}

	}
	
	/**
	 * Accède à la boule présente aux coordonnées i;j du plateau
	 * La numérotation des cases commence à 1 pour les lignes et les colonnes
	 * Premiere case en haut à gauche : i=1, j=1
	 * Si aucune boule n'est présente sur la case, ou si la case est en dehors du plateau, null est retourné
	 * @param i ligne du plateau
	 * @param j colonne du plateau
	 * @return la boule positionnée sur la case correspondante, null si aucune boule n'est présente
	 */
	public Boule getBoule(int i, int j){
		Case c = getPlateau().getCaseValide(i-1, j);
		
		return c.getBoule();
	}

	/**
	 * Getter du plateau
	 * 
	 * @return
	 */
	public Plateau getPlateau() {
		return this.mPlateau;
	}

	// Définition de trois méthodes pour réaliser les trois types de mouvement
	// possible, c'est à dire déplacer, une, deux ou trois boules.
	public boolean faireMouvement(Boule b1, Direction direction) {
		if(!mPeutJouer){
			return false;
		}
		if(b1 == null){
			return false;
		}
		if(!this.getJoueurActuel().equals(b1.getJoueur())){
			return false;
		}
		if(b1.getCase().getVoisin(direction) instanceof CaseOut || !b1.getCase().getVoisin(direction).isEmpty()){
			return false;
		}
		// Il s'agit du joueur actuel qui réalise le mouvement
		try {
			this.mPlateau.DeplaceUn(b1, direction);
			this.mPeutJouer = false;
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	public boolean faireMouvement(Boule b1, Boule b2, Direction direction) {
		if(!mPeutJouer){
			return false;
		}
		if(!this.getJoueurActuel().equals(b1.getJoueur()) && !this.getJoueurActuel().equals(b2.getJoueur())){
			return false;
		}
		// Il s'agit du joueur actuel qui réalise le mouvement
		try {
			this.mPlateau.DeplaceDeux(b1, b2, direction);
			this.mPeutJouer = false;
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	public boolean faireMouvement(Boule b1, Boule b2, Boule b3, Direction direction) {
		if(!mPeutJouer){
			return false;
		}
		if(!this.getJoueurActuel().equals(b1.getJoueur())  && !this.getJoueurActuel().equals(b2.getJoueur())  && !this.getJoueurActuel().equals(b3.getJoueur())){
			return false;
		}
		// Il s'agit du joueur actuel qui réalise le mouvement
		try {
			this.mPlateau.DeplaceTrois(b1, b2, b3, direction);
			this.mPeutJouer = false;
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Joueur getJoueur1() {
		return this.mJoueur1;
	}

	public Joueur getJoueur2() {
		return this.mJoueur2;
	}

}
