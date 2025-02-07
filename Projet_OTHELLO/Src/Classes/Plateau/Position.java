package Classes.Plateau;

import Classes.Utils.Direction;

/**
 * Représente une position sur le plateau de jeu.
 */
public class Position {
    private final int ligne;
    private final int colonne;

    /**
     * Constructeur de la classe Position.
     * @param ligne La ligne de la position.
     * @param colonne La colonne de la position.
     */
    public Position(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    /**
     * Retourne la ligne de la position.
     * @return La ligne de la position.
     */
    public int getLigne() {
        return ligne;
    }

    /**
     * Retourne la colonne de la position.
     * @return La colonne de la position.
     */
    public int getColonne() {
        return colonne;
    }

    /**
     * Calcule une nouvelle position en ajoutant une direction à la position actuelle.
     * @param direction La direction à ajouter.
     * @return Une nouvelle Position résultant de l'ajout de la direction.
     */
    public Position ajouterDirection(Direction direction) {
        return new Position(this.ligne + direction.getDx(), this.colonne + direction.getDy());
    }

    /**
     * Compare cette position avec un autre objet pour l'égalité.
     * @param obj L'objet à comparer avec cette position.
     * @return true si les positions sont égales, false sinon.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position position = (Position) obj;
        return ligne == position.ligne && colonne == position.colonne;
    }

    /**
     * Génère un code de hachage pour cette position.
     * @return Le code de hachage de la position.
     */
    @Override
    public int hashCode() {
        return 31 * ligne + colonne;
    }

    /**
     * Retourne une représentation en chaîne de la position.
     * @return Une chaîne représentant la position sous la forme "(ligne, colonne)".
     */
    @Override
    public String toString() {
        return "(" + ligne + ", " + colonne + ")";
    }
}