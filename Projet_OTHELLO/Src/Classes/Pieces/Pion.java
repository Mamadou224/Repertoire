package Classes.Pieces;

import Classes.Utils.Couleur;

public class Pion {
    // Attribut de la classe
    private Couleur couleur;

    // Constructeur
    public Pion(Couleur couleur) {
        this.couleur = couleur;
    }

    // Getter pour obtenir la couleur du pion
    public Couleur getCouleur() {
        return couleur;
    }

    // Méthode pour retourner le pion (changer sa couleur)
    public void retourner() {
        if (couleur == Couleur.NOIR) {
            couleur = Couleur.BLANC;
        } else {
            couleur = Couleur.NOIR;
        }
    }

    // Méthode toString pour représenter le pion sous forme de chaîne
    @Override
    public String toString() {
        return couleur == Couleur.NOIR ? "N" : "B";
    }

    // Méthode equals pour comparer deux pions
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pion pion = (Pion) obj;
        return couleur == pion.couleur;
    }

    // Méthode hashCode pour générer un code de hachage basé sur la couleur
    @Override
    public int hashCode() {
        return couleur.hashCode();
    }
}