package Classes.Plateau;

import Classes.Pieces.Pion;
import Classes.Utils.Couleur;
import Classes.Utils.Direction;

public class Plateau {
    private static final int TAILLE = 8;
    private Case[][] cases;
    
    /**
     * Constructeur du plateau.
     * Initialise le plateau avec la configuration de départ.
     */
    public Plateau() {
        initialiserPlateau();
    }

    /**
     * Initialise le plateau avec les 4 pions de départ.
     */
    private void initialiserPlateau() {
        cases = new Case[TAILLE][TAILLE];
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                cases[i][j] = new Case();
            }
        }
        // Placement des 4 pions initiaux
        cases[3][3].setPion(new Pion(Couleur.BLANC));
        cases[3][4].setPion(new Pion(Couleur.NOIR));
        cases[4][3].setPion(new Pion(Couleur.NOIR));
        cases[4][4].setPion(new Pion(Couleur.BLANC));
    }
    
     /**
     * Vérifie si une position est valide sur le plateau.
     * @param pos La position à vérifier.
     * @return true si la position est valide, false sinon.
     */
    public boolean estPositionValide(Position pos) {
        return pos.getLigne() >= 0 && pos.getLigne() < TAILLE &&
               pos.getColonne() >= 0 && pos.getColonne() < TAILLE;
    }
    
     /**
     * Vérifie si un coup est valide pour une couleur donnée.
     * @param pos La position du coup.
     * @param couleur La couleur du joueur.
     * @return true si le coup est valide, false sinon.
     */
    public boolean estPositionValide(Position pos, Couleur couleur) {
        if (!estPositionValide(pos) || !cases[pos.getLigne()][pos.getColonne()].estVide()) {
            return false;
        }
        for (Direction dir : Direction.values()) {
            if (peutEncadrer(pos, couleur, dir)) {
                return true;
            }
        }
        return false;
    }

        /**
     * Vérifie si un coup peut encadrer des pions adverses dans une direction donnée.
     * @param pos La position du coup.
     * @param couleur La couleur du joueur.
     * @param dir La direction à vérifier.
     * @return true si l'encadrement est possible, false sinon.
     */
    private boolean peutEncadrer(Position pos, Couleur couleur, Direction dir) {
        Position courante = pos.ajouterDirection(dir);
        if (!estPositionValide(courante) || cases[courante.getLigne()][courante.getColonne()].estVide()) {
            return false;
        }
        Couleur couleurOpposee = (couleur == Couleur.NOIR) ? Couleur.BLANC : Couleur.NOIR;
        if (cases[courante.getLigne()][courante.getColonne()].getPion().getCouleur() != couleurOpposee) {
            return false;
        }
        while (estPositionValide(courante)) {
            if (cases[courante.getLigne()][courante.getColonne()].estVide()) {
                return false;
            }
            if (cases[courante.getLigne()][courante.getColonne()].getPion().getCouleur() == couleur) {
                return true;
            }
            courante = courante.ajouterDirection(dir);
        }
        return false;
    }
    
       /**
     * Pose un pion sur le plateau et retourne les pions encadrés.
     * @param pos La position où poser le pion.
     * @param couleur La couleur du pion à poser.
     * @throws IllegalArgumentException si la position est invalide.
     */
    public void poserPion(Position pos, Couleur couleur) {
        if (!estPositionValide(pos, couleur)) {
            throw new IllegalArgumentException("Position invalide");
        }
        cases[pos.getLigne()][pos.getColonne()].setPion(new Pion(couleur));
        for (Direction dir : Direction.values()) {
            if (peutEncadrer(pos, couleur, dir)) {
                retournerPions(pos, couleur, dir);
            }
        }
    }

     /**
     * Retourne les pions encadrés dans une direction donnée.
     * @param pos La position de départ.
     * @param couleur La couleur du joueur.
     * @param dir La direction dans laquelle retourner les pions.
     */
    private void retournerPions(Position pos, Couleur couleur, Direction dir) {
        Position courante = pos.ajouterDirection(dir);
        while (estPositionValide(courante) && !cases[courante.getLigne()][courante.getColonne()].estVide()) {
            Pion pion = cases[courante.getLigne()][courante.getColonne()].getPion();
            if (pion.getCouleur() == couleur) {
                return;
            }
            pion.retourner();
            courante = courante.ajouterDirection(dir);
        }
    }

      /**
     * Compte le nombre de pions d'une couleur donnée sur le plateau.
     * @param couleur La couleur des pions à compter.
     * @return Le nombre de pions de la couleur spécifiée.
     */
    public int compterPions(Couleur couleur) {
        int compte = 0;
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                if (!cases[i][j].estVide() && cases[i][j].getPion().getCouleur() == couleur) {
                    compte++;
                }
            }
        }
        return compte;
    }

      /**
     * Retourne une représentation en chaîne du plateau.
     * @return Une chaîne représentant l'état actuel du plateau.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  0 1 2 3 4 5 6 7\n");
        for (int i = 0; i < TAILLE; i++) {
            sb.append(i).append(" ");
            for (int j = 0; j < TAILLE; j++) {
                sb.append(cases[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}