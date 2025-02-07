package Classes.Jeu;

import Classes.Plateau.Plateau;
import Classes.Plateau.Position;
import Classes.Utils.Couleur;

import java.util.Scanner;

public class Othello {
    private Plateau plateau;
    private Joueur joueurNoir;
    private Joueur joueurBlanc;
    private Joueur joueurCourant;
    private Scanner scanner;
    
    //pour avoir le plateau
    public Plateau getPlateau() {
        return plateau;
    }
 
    //obtenir le joueur courant
    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    // Constructeur modifié
    public Othello(Joueur joueurNoir, Joueur joueurBlanc) {
        this.plateau = new Plateau();
        this.joueurNoir = joueurNoir;
        this.joueurBlanc = joueurBlanc;
        this.joueurCourant = joueurNoir; // Le joueur noir commence
        this.scanner = new Scanner(System.in);
    }

    // Méthode principale pour jouer une partie (inchangée)
    public void jouer() {
        boolean tourPasse = false;
        while (!estTermine()) {
            afficherPlateau();
            if (existePositionValide()) {
                jouerTour();
                tourPasse = false;
            } else {
                System.out.println("Pas de coup possible pour " + joueurCourant.getNom() + ". Tour passé.");
                if (tourPasse) {
                    // Si deux tours consécutifs sont passés, le jeu est terminé
                    break;
                }
                tourPasse = true;
            }
            changerJoueur();
        }
        afficherResultat();
    }

    // Méthode pour jouer un tour (mise à jour pour utiliser le nom du joueur)
    private void jouerTour() {
        afficherPlateau();
        if (existePositionValide()) {
            Position position = demanderPosition();
            while (!verifierPositionValide(position)) {
                System.out.println("Position invalide. Réessayez.");
                position = demanderPosition();
            }
            appliquerCoup(position);
        } else {
            System.out.println("Pas de coup possible pour " + joueurCourant.getNom() + ". Tour passé.");
        }
    }
    // Méthode pour changer de joueur (mise à jour)
    public void changerJoueur() {
        joueurCourant = (joueurCourant == joueurNoir) ? joueurBlanc : joueurNoir;
    }

    // Méthode pour vérifier si la partie est terminée (inchangée)
    public boolean estTermine() {
        return !existePositionValide() && !existePositionValidePourAutreJoueur();
    }

    // Méthode pour déterminer le vainqueur (mise à jour)
    public Joueur determinerVainqueur() {
        int scoreNoir = plateau.compterPions(Couleur.NOIR);
        int scoreBlanc = plateau.compterPions(Couleur.BLANC);
        if (scoreNoir > scoreBlanc) return joueurNoir;
        if (scoreBlanc > scoreNoir) return joueurBlanc;
        return null; // Égalité
    }

    // Méthode pour afficher le plateau (inchangée)
    private void afficherPlateau() {
        System.out.println(plateau);
        System.out.println("Pions noirs : " + plateau.compterPions(Couleur.NOIR));
        System.out.println("Pions blancs : " + plateau.compterPions(Couleur.BLANC));
    }

    // Méthode pour vérifier si une position est valide (à implémenter)
    private boolean verifierPositionValide(Position position) {
        return plateau.estPositionValide(position, joueurCourant.getCouleur());
    }

    // Méthode pour appliquer un coup (à implémenter)
    public void appliquerCoup(Position position) {
        plateau.poserPion(position, joueurCourant.getCouleur());
    }

    // Méthode pour vérifier s'il existe une position valide (à implémenter)
    private boolean existePositionValide() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (plateau.estPositionValide(new Position(i, j), joueurCourant.getCouleur())) {
                    return true;
                }
            }
        }
        return false;
    }

    // Méthode pour vérifier s'il existe une position valide pour l'autre joueur (à implémenter)
    private boolean existePositionValidePourAutreJoueur() {
        Joueur autreJoueur = (joueurCourant == joueurNoir) ? joueurBlanc : joueurNoir;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (plateau.estPositionValide(new Position(i, j), autreJoueur.getCouleur())) {
                    return true;
                }
            }
        }
        return false;
    }

    // Méthode pour demander une position au joueur (mise à jour pour utiliser le nom)
    private Position demanderPosition() {
        System.out.println(joueurCourant.getNom() + ", entrez votre coup (ligne colonne) : ");
        int ligne = scanner.nextInt();
        int colonne = scanner.nextInt();
        return new Position(ligne, colonne);
    }

    // Méthode pour afficher le résultat final (mise à jour pour utiliser le nom)
    private void afficherResultat() {
        afficherPlateau();
        Joueur vainqueur = determinerVainqueur();
        if (vainqueur != null) {
            System.out.println("Le vainqueur est : " + vainqueur.getNom());
        } else {
            System.out.println("Match nul !");
        }
    }

      // Méthode spécifique pour les tests
    public boolean testVerifierPositionValide(Position position) {
        return verifierPositionValide(position);
    }

    // Nouvelle méthode pour compter les pions
    public int compterPions(Couleur couleur) {
        return plateau.compterPions(couleur);
    }
    
}