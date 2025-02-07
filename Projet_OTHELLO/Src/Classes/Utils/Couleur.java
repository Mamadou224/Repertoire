package Classes.Utils;

/**
 * Énumération représentant les couleurs des pions dans le jeu Othello.
 */
public enum Couleur {
    /** Représente la couleur noire. */
    NOIR("N"),
    /** Représente la couleur blanche. */
    BLANC("B");

    /** La représentation en chaîne de caractères de la couleur. */
    private final String representation;

    /**
     * Constructeur de l'énumération Couleur.
     * @param representation La représentation en chaîne de la couleur.
     */
    Couleur(String representation) {
        this.representation = representation;
    }

    /**
     * Retourne la représentation en chaîne de la couleur.
     * @return La représentation en chaîne de la couleur.
     */
    public String getRepresentation() {
        return representation;
    }

    /**
     * Retourne la couleur opposée.
     * @return BLANC si la couleur actuelle est NOIR, et vice versa.
     */
    public Couleur opposee() {
        return this == NOIR ? BLANC : NOIR;
    }

    /**
     * Retourne la représentation en chaîne de la couleur.
     * @return La représentation en chaîne de la couleur.
     */
    @Override
    public String toString() {
        return representation;
    }
}