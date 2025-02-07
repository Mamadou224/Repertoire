package Classes.Plateau;

import Classes.Pieces.Pion;

public class Case {
    private Pion pion;

    /**
     * Constructeur de la classe Case.
     * Initialise une case vide.
     */
    public Case() {
        this.pion = null;
    }

    /**
     * Place un pion sur la case.
     * @param pion Le pion à placer sur la case.
     */
    public void setPion(Pion pion) {
        this.pion = pion;
    }

    /**
     * Récupère le pion présent sur la case.
     * @return Le pion présent sur la case, ou null si la case est vide.
     */
    public Pion getPion() {
        return pion;
    }

    /**
     * Vérifie si la case est vide.
     * @return true si la case est vide, false sinon.
     */
    public boolean estVide() {
        return pion == null;
    }

    /**
     * Vide la case en retirant le pion.
     */
    public void vider() {
        this.pion = null;
    }

    /**
     * Retourne une représentation en chaîne de la case.
     * @return "." si la case est vide, sinon la représentation du pion.
     */
    @Override
    public String toString() {
        if (estVide()) {
            return ".";
        } else {
            return pion.toString();
        }
    }

    /**
     * Compare cette case avec un autre objet.
     * @param obj L'objet à comparer avec cette case.
     * @return true si les cases sont égales, false sinon.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Case autre = (Case) obj;
        if (this.estVide() && autre.estVide()) return true;
        if (this.estVide() || autre.estVide()) return false;
        return this.pion.equals(autre.pion);
    }

    /**
     * Génère un code de hachage pour la case.
     * @return Le code de hachage de la case.
     */
    @Override
    public int hashCode() {
        return pion != null ? pion.hashCode() : 0;
    }
}