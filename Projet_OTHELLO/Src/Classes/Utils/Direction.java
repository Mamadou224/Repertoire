package Classes.Utils;

/**
 * Énumération représentant les directions possibles sur le plateau de jeu Othello.
 */
public enum Direction {
    /** Direction vers le haut */
    HAUT(-1, 0),
    /** Direction en diagonale vers le haut à droite */
    HAUT_DROITE(-1, 1),
    /** Direction vers la droite */
    DROITE(0, 1),
    /** Direction en diagonale vers le bas à droite */
    BAS_DROITE(1, 1),
    /** Direction vers le bas */
    BAS(1, 0),
    /** Direction en diagonale vers le bas à gauche */
    BAS_GAUCHE(1, -1),
    /** Direction vers la gauche */
    GAUCHE(0, -1),
    /** Direction en diagonale vers le haut à gauche */
    HAUT_GAUCHE(-1, -1);

    /** Déplacement sur l'axe des x */
    private final int dx;
    /** Déplacement sur l'axe des y */
    private final int dy;

    /**
     * Constructeur de l'énumération Direction.
     * @param dx Déplacement sur l'axe des x.
     * @param dy Déplacement sur l'axe des y.
     */
    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Retourne le déplacement sur l'axe des x.
     * @return Le déplacement sur l'axe des x.
     */
    public int getDx() {
        return dx;
    }

    /**
     * Retourne le déplacement sur l'axe des y.
     * @return Le déplacement sur l'axe des y.
     */
    public int getDy() {
        return dy;
    }

    /**
     * Retourne la direction opposée.
     * @return La direction opposée à la direction courante.
     * @throws IllegalStateException si la direction est inconnue.
     */
    public Direction opposee() {
        switch (this) {
            case HAUT: return BAS;
            case HAUT_DROITE: return BAS_GAUCHE;
            case DROITE: return GAUCHE;
            case BAS_DROITE: return HAUT_GAUCHE;
            case BAS: return HAUT;
            case BAS_GAUCHE: return HAUT_DROITE;
            case GAUCHE: return DROITE;
            case HAUT_GAUCHE: return BAS_DROITE;
            default: throw new IllegalStateException("Direction inconnue");
        }
    }
}