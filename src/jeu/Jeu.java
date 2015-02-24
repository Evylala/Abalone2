package jeu;

import java.util.Observable;


/**
 * Classe g�rant l'integralit� du jeu Abalone
 * 
 * Il s'agit de l'API � utiliser pour r�aliser une partie. Elle fournit les
 * diff�rentes m�thodes n�cessaires � l'�x�cution de la boucle du jeu
 * 
 * Les m�thodes sont � appeler dans l'ordre suivant : - setJoueur1() -
 * setJoueur2() - demmarer()
 * 
 * METHODE DE LA BOUCLE DE JEU - isEnCours() - la ou les m�thodes de d�placement
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
	 * Initialisation du second joueur Il pourra �tre Humain si l'on d�sire
	 * r�aliser une partie avec deux joueurs Dans le cas contraire, il sera IA
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
	 * Initialisation de la partie M�thode � appeler apr�s avoir initialis� les
	 * deux joueurs et avant d'utiliser les diff�rentes m�thodes du jeu. Cette
	 * m�thode peut �tre appel�e � tout moment : -Avant la partie, � condition
	 * que les deux joueurs sont bien cr��s (setJoueur1(), setJoueur2()) -En
	 * cours de partie, cela r�initialise le jeu et repositionne les boules �
	 * leurs places de d�part -A la fin de la partie, permet de rejouer
	 * 
	 * @return True si le jeu a d�marr� correctement
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
			System.out.println("Les joueurs ne sont pas initialis�s");
			return false;
		}
		
	}

	/**
	 * M�thode pour initialiser la position des boules sur le plateau pour les
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
	 * Retourne true si la partie n'est pas encore termin�e Cette m�thode permet
	 * de r�aliser le test de la boucle du jeu Tant que le jeu est en cours (i-e
	 * return true), on appelle le joueur � r�aliser le(s) mouvements qu'il
	 * souhaite Une fois que le jeu est termin� (i-e return false), il devient
	 * impossible d'agir sur le jeu, on ne peut alors que reccup�rer le gagnant
	 * et recommencer une partie
	 * 
	 * @return
	 */
	public boolean isEnCours() {
		return this.mEnCours;
	}

	/**
	 * Apr�s avoir r�aliser le ou les d�placement de pions, appeler cette
	 * m�thode pour terminer le tour, Si le joueur suivant est une IA, son tour
	 * sera automatiquement jou� � l'appel de la m�thode Dans le cas contraire
	 * (joueur humain), le joueur actuel passe au joueur suivant. La m�thode
	 * v�rifie si l'un des joueurs a gagn�. Dans ce cas, le jeu se termine
	 * (isEnCours()) et le gagnant est d�fini
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
	 * porchains mouvements seront r�alis�s Ce joueur actuel est modifi� apr�s
	 * l'appel de finirTour()
	 * 
	 * @return Joueur le joueur qui va r�aliser le prochain mouvement
	 */
	public Joueur getJoueurActuel() {
		return this.mJoueurActuel;
	}

	/**
	 * retourne le gagnant � la fin de la partie Cette m�thode ne peut �tre
	 * appel�e que si le jeu est termin� (voir isEnCours())
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
	 * Acc�de � la boule pr�sente aux coordonn�es i;j du plateau
	 * La num�rotation des cases commence � 1 pour les lignes et les colonnes
	 * Premiere case en haut � gauche : i=1, j=1
	 * Si aucune boule n'est pr�sente sur la case, ou si la case est en dehors du plateau, null est retourn�
	 * @param i ligne du plateau
	 * @param j colonne du plateau
	 * @return la boule positionn�e sur la case correspondante, null si aucune boule n'est pr�sente
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

	// D�finition de trois m�thodes pour r�aliser les trois types de mouvement
	// possible, c'est � dire d�placer, une, deux ou trois boules.
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
		// Il s'agit du joueur actuel qui r�alise le mouvement
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
		// Il s'agit du joueur actuel qui r�alise le mouvement
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
		// Il s'agit du joueur actuel qui r�alise le mouvement
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
