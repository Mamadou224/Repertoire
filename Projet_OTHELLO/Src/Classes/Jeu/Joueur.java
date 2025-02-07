package Classes.Jeu;

import Classes.Plateau.Plateau;
import Classes.Plateau.Position;
import Classes.Utils.Couleur;

import java.util.Scanner;

public class Joueur {
    // Attributs de la classe
    private String nom;  // Nouveau : le nom du joueur
    private Couleur couleur;
    private Scanner scanner;

    // Constructeur modifié
    public Joueur(String nom, Couleur couleur) {
        this.nom = nom;
        this.couleur = couleur;
        this.scanner = new Scanner(System.in);
    }

    // Nouvelle méthode pour obtenir le nom du joueur
    public String getNom() {
        return nom;
    }

    // Getter pour la couleur du joueur (inchangé)
    public Couleur getCouleur() {
        return couleur;
    }

    // Méthode pour faire jouer le joueur (modifiée pour inclure le nom)
    public Position jouer(Plateau plateau) {
        System.out.println(nom + " (Joueur " + couleur + "), c'est à votre tour.");
        Position position;
        do {
            position = demanderPosition();
        } while (!plateau.estPositionValide(position, couleur));
        return position;
    }

    // Méthode privée pour demander une position au joueur (inchangée)
    private Position demanderPosition() {
        System.out.print("Entrez la ligne (0-7) : ");
        int ligne = scanner.nextInt();
        System.out.print("Entrez la colonne (0-7) : ");
        int colonne = scanner.nextInt();
        return new Position(ligne, colonne);
    }

    // Méthode pour vérifier si le joueur peut jouer (inchangée)
    public boolean peutJouer(Plateau plateau) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (plateau.estPositionValide(new Position(i, j), couleur)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Méthode toString modifiée pour inclure le nom
    @Override
    public String toString() {
        return nom + " (Joueur " + couleur + ")";
    }
}