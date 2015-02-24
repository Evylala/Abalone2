package ui;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;

import uiControls.ControlBoutonCase;
import uiControls.ControlBoutonDirection;
import uiControls.ControlBoutonRecommencer;
import uiControls.ControlFenetreJeu;
import uiControls.ControlMenuNouvellePartie;
import uiControls.ControlMenuQuitter;
import jeu.*;

public class FenetreJeu extends JFrame {
	
	private Plateau plateau;
	private Jeu jeu;
	private JButton[][] lesBoutons;
	private JLabel[][] lesCasesOut;
	private Joueur joueur1;
	private Joueur joueur2;
	private JPanel panelPlateau;
	private JPanel menu;
	private JLabel labelBoules;
	private int nbBoulesChoisies;
	private JButton recommencer;
	private JPanel panelDirections;
	private JTextArea textAreaAction;
	private ArrayList<JButton> listeBoutonsDirections;
	private Container conteneur;
	private ArrayList<Boule> listeBoulesChoisies;
	private ArrayList<JButton> listeBoutonsSelectionnes;
 	private JMenuBar barreDeMenu;
	
	public FenetreJeu(Jeu jeu) {
		
		super("Abalone");
		this.setPreferredSize(new Dimension(820,710));
		this.jeu = jeu;
		this.jeu.addObserver(new ControlFenetreJeu(this));
		this.lesBoutons = new JButton[Plateau.HAUTEUR][Plateau.LARGEUR*2];
		this.lesCasesOut = new JLabel[Plateau.HAUTEUR][Plateau.LARGEUR*2];
		this.nbBoulesChoisies = 0;
		this.listeBoutonsDirections = new ArrayList<JButton>();
		this.listeBoulesChoisies = new ArrayList<Boule>();
		this.listeBoutonsSelectionnes = new ArrayList<JButton>();
		this.barreDeMenu = new JMenuBar();
		
		JMenu partie = new JMenu("Partie");
		JMenuItem nouveau = new JMenuItem("Nouvelle Partie");
		JMenuItem quitter = new JMenuItem("Quitter");
		nouveau.addActionListener(new ControlMenuNouvellePartie(this));
		quitter.addActionListener(new ControlMenuQuitter(this));
		
		partie.add(nouveau);
		partie.add(quitter);
		this.barreDeMenu.add(partie);
		this.setJMenuBar(this.barreDeMenu);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.conteneur = this.getContentPane();
		this.conteneur.setLayout(new BorderLayout());
		
		if (this.initialiserNomsJoueurs()) {
			this.lancerJeu();
			this.initialiserPanelMenu();
			this.initialiserPlateau();
			
			JLabel abalone = new JLabel(new ImageIcon("images"+File.separator+"abalone2.jpg"));
			JLabel abalone2 = new JLabel(new ImageIcon("images"+File.separator+"abalone4.jpg"));
			
			this.conteneur.add(this.panelPlateau, BorderLayout.CENTER);
			this.conteneur.add(abalone, BorderLayout.EAST);
			this.conteneur.add(abalone2, BorderLayout.WEST);
			this.conteneur.add(this.menu, BorderLayout.SOUTH);
			
			this.pack();
			this.setResizable(false);
			this.setVisible(true);
		} else {
			System.exit(0);
		}
		
	}
	
	public boolean initialiserNomsJoueurs() {
		
		String nomJ1 = JOptionPane.showInputDialog(this,"Nom du premier joueur : ", "Choix des joueurs", JOptionPane.DEFAULT_OPTION);
		String nomJ2;
		
		if (nomJ1 != null && !nomJ1.equals("")) {
			
			nomJ2 = JOptionPane.showInputDialog(this,"Nom du deuxième joueur : ", "Choix des joueurs", JOptionPane.DEFAULT_OPTION);
			
			if (nomJ2 != null && !nomJ2.equals("")) {
				this.jeu.setJoueur1(nomJ1, Jeu.JOUEUR_HUMAIN);
				this.jeu.setJoueur2(nomJ2, Jeu.JOUEUR_HUMAIN);
				
				return true;
			}
		}
		
		return false;
		
	}
	
	public void lancerJeu() {
		this.jeu.demarrer();
		this.joueur1 = this.jeu.getJoueur1();
		this.joueur2 = this.jeu.getJoueur2();
		this.plateau = jeu.getPlateau();
	}
	
	public void initialiserPlateau() {
		
		this.panelPlateau = new JPanel(new GridLayout(Plateau.HAUTEUR, Plateau.LARGEUR*2));
		this.panelPlateau.setBackground(Color.BLACK);
		
		for (int i=0; i<Plateau.HAUTEUR; i++) {
			for (int j=3; j<(Plateau.LARGEUR*2)-2; j++) {
				if (this.plateau.getCase(i, j) == null) {
					lesCasesOut[i][j] = new JLabel(new ImageIcon("images"+File.separator+"Out.png"));
					lesBoutons[i][j] = null;
				} else if (this.plateau.getCase(i, j) instanceof CaseOut) {
					lesCasesOut[i][j] = new JLabel(new ImageIcon("images"+File.separator+"Case_Hors_Plateau.png"));
					lesBoutons[i][j] = null;
				} else if (this.plateau.getCase(i, j).isEmpty()) {
					lesBoutons[i][j] = new JButton(new ImageIcon("images"+File.separator+"Vide.png"));
					lesBoutons[i][j].setEnabled(false);
					lesCasesOut[i][j] = null;
				} else if (this.plateau.getCase(i, j).getBoule().getJoueur().equals(this.joueur1)) {
					lesBoutons[i][j] = new JButton(new ImageIcon("images"+File.separator+"Boule_Noire.png"));
					if (this.jeu.getJoueurActuel().equals(joueur2)) {
						lesBoutons[i][j].setEnabled(false);
					}
		
					lesCasesOut[i][j] = null;
				} else {
					lesBoutons[i][j] = new JButton(new ImageIcon("images"+File.separator+"Boule_Blanche.png"));
					if (this.jeu.getJoueurActuel().equals(joueur1)) {
						lesBoutons[i][j].setEnabled(false);
					}
					lesCasesOut[i][j] = null;
				}
				if (lesBoutons[i][j] != null) {
					this.panelPlateau.add(lesBoutons[i][j]);
					lesBoutons[i][j].addActionListener(new ControlBoutonCase(i, j, this, this.labelBoules, this.textAreaAction, lesBoutons[i][j], this.jeu, this.listeBoutonsDirections));
				}
				if (lesCasesOut[i][j] != null) {
					this.panelPlateau.add(lesCasesOut[i][j]);
				}
				
			}
		}
	}
	
	public void initialiserPanelMenu() {

		JPanel partieHaut = new JPanel(new GridLayout(2,2));
		JPanel boutonsBoules = new JPanel();
		
		JLabel nomJoueurCourant = new JLabel("Au tour de : " + this.jeu.getJoueurActuel().getNom());
		nomJoueurCourant.setFont(new Font("Californian FB", Font.BOLD, 12));
		
		this.textAreaAction = new JTextArea("Veuillez choisir les boules à déplacer (maximum 3)");
		this.textAreaAction.setPreferredSize(new Dimension(30,5));
		this.textAreaAction.setLineWrap(true);
		this.textAreaAction.setWrapStyleWord(true); 
		this.textAreaAction.setEditable(false);
		this.textAreaAction.setFont(new Font("Californian FB", Font.BOLD, 12));
		
		this.labelBoules = new JLabel("0 boule choisie");
		this.labelBoules.setFont(new Font("Californian FB", Font.BOLD, 12));
		
		this.menu = new JPanel(new BorderLayout());
		
		this.recommencer = new JButton("Réinitialiser");
		this.recommencer.setToolTipText("Recommencer le choix des boules");
		this.recommencer.addActionListener(new ControlBoutonRecommencer(this));
		
		boutonsBoules.add(this.recommencer);
		
		this.initialiserPanelDirections();
		
		partieHaut.add(nomJoueurCourant);
		partieHaut.add(boutonsBoules);
		partieHaut.add(this.labelBoules);
		partieHaut.add(this.textAreaAction);

		this.menu.add(partieHaut, BorderLayout.WEST);
		this.menu.add(this.panelDirections, BorderLayout.EAST);
	}
	
	public void initialiserPanelDirections() {
		
		this.panelDirections = new JPanel(new GridLayout(3,2));
		
		JButton boutonHG = new JButton(new ImageIcon("images"+File.separator+"flèche_HautGauche.png"));
		JButton boutonHD = new JButton(new ImageIcon("images"+File.separator+"flèche_HautDroite.png"));
		JButton boutonG = new JButton(new ImageIcon("images"+File.separator+"flèche_Gauche.png"));
		JButton boutonD = new JButton(new ImageIcon("images"+File.separator+"flèche_Droite.png"));
		JButton boutonBG = new JButton(new ImageIcon("images"+File.separator+"flèche_BasGauche.png"));
		JButton boutonBD = new JButton(new ImageIcon("images"+File.separator+"flèche_BasDroite.png"));
		
		boutonHG.addActionListener(new ControlBoutonDirection(Direction.HG,this.jeu, this, this.textAreaAction, this.recommencer));
		boutonHD.addActionListener(new ControlBoutonDirection(Direction.HD,this.jeu, this, this.textAreaAction, this.recommencer));
		boutonG.addActionListener(new ControlBoutonDirection(Direction.G,this.jeu, this, this.textAreaAction, this.recommencer));
		boutonD.addActionListener(new ControlBoutonDirection(Direction.D,this.jeu, this, this.textAreaAction, this.recommencer));
		boutonBG.addActionListener(new ControlBoutonDirection(Direction.BG,this.jeu, this, this.textAreaAction, this.recommencer));
		boutonBD.addActionListener(new ControlBoutonDirection(Direction.BD,this.jeu, this, this.textAreaAction, this.recommencer));
		
		boutonHG.setEnabled(false);
		boutonHD.setEnabled(false);
		boutonG.setEnabled(false);
		boutonD.setEnabled(false);
		boutonBG.setEnabled(false);
		boutonBD.setEnabled(false);
		
		this.listeBoutonsDirections.add(boutonHG);
		this.listeBoutonsDirections.add(boutonHD);
		this.listeBoutonsDirections.add(boutonG);
		this.listeBoutonsDirections.add(boutonD);
		this.listeBoutonsDirections.add(boutonBG);
		this.listeBoutonsDirections.add(boutonBD);
		
		this.panelDirections.add(boutonHG);
		this.panelDirections.add(boutonHD);
		this.panelDirections.add(boutonG);
		this.panelDirections.add(boutonD);
		this.panelDirections.add(boutonBG);
		this.panelDirections.add(boutonBD);
	}
	
	public void recommencerChoixDesBoules() {
		
		this.lesBoutons = new JButton[Plateau.HAUTEUR][Plateau.LARGEUR*2];
		this.lesCasesOut = new JLabel[Plateau.HAUTEUR][Plateau.LARGEUR*2];
		this.nbBoulesChoisies = 0;
		this.listeBoutonsDirections = new ArrayList<JButton>();
		this.listeBoulesChoisies = new ArrayList<Boule>();
		this.listeBoutonsSelectionnes = new ArrayList<JButton>();
		this.initialiserPanelMenu();
		this.initialiserPlateau();
		
		conteneur.removeAll();
		JLabel abalone = new JLabel(new ImageIcon("images"+File.separator+"abalone2.jpg"));
		JLabel abalone2 = new JLabel(new ImageIcon("images"+File.separator+"abalone4.jpg"));
		
		this.conteneur.add(this.panelPlateau, BorderLayout.CENTER);
		this.conteneur.add(abalone, BorderLayout.EAST);
		this.conteneur.add(abalone2, BorderLayout.WEST);
		this.conteneur.add(this.menu, BorderLayout.SOUTH);
		
		this.pack();
	}
	
	public int ajoutNbBoulesChoisies() {
		this.nbBoulesChoisies++;
		return this.nbBoulesChoisies;
	}
	
	public void setNbBoulesChoisies(int nb) {
		this.nbBoulesChoisies = nb;
	}
	
	public void ajoutBouleChoisie(Boule b) {
		this.listeBoulesChoisies.add(b);
	}
	
	public ArrayList<Boule> getListeBoulesChoisies() {
		return this.listeBoulesChoisies;
	}
	
	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}
	
	public void selectionneBouton(JButton b) {
		this.listeBoutonsSelectionnes.add(b);
	}
	
	public void deselectionneBouton(JButton b) {
		this.listeBoutonsSelectionnes.remove(b);
	}
	
	public boolean estDejaSelectionne(JButton b) {
		return this.listeBoutonsSelectionnes.contains(b);
	}
	
	public JButton[][] getLesBoutons() {
		return this.lesBoutons;
	}
	
	public void nouvellePartie(Jeu jeu) {
		
		this.jeu = jeu;
		if (this.initialiserNomsJoueurs()) {
			this.jeu.demarrer();
			this.joueur1 = this.jeu.getJoueur1();
			this.joueur2 = this.jeu.getJoueur2();
			this.plateau = jeu.getPlateau();
			this.jeu.addObserver(new ControlFenetreJeu(this));
			this.remettreAZero();
		}
	}

	
	public void remettreAZero() {
		
		this.plateau = jeu.getPlateau();
		this.joueur1 = jeu.getJoueur1();
		this.joueur2 = jeu.getJoueur2();
		
		this.recommencerChoixDesBoules();
	}
	
}
