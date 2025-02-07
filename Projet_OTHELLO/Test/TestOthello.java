import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Classes.Jeu.Othello;
import Classes.Jeu.Joueur;
import Classes.Plateau.Position;
import Classes.Utils.Couleur;

public class TestOthello {

    private Othello jeu;
    private Joueur joueurNoir;
    private Joueur joueurBlanc;

    @BeforeEach // Changé de @Before à @BeforeEach pour JUnit 5
    public void setUp() {
        joueurNoir = new Joueur("Joueur Noir", Couleur.NOIR);
        joueurBlanc = new Joueur("Joueur Blanc", Couleur.BLANC);
        jeu = new Othello(joueurNoir, joueurBlanc);
    }

    @Test
    public void testInitialisation() {
        assertEquals(2, jeu.compterPions(Couleur.NOIR));
        assertEquals(2, jeu.compterPions(Couleur.BLANC));
    }

    @Test
    public void testCoupValide() {
        assertTrue(jeu.testVerifierPositionValide(new Position(2, 3))); // Utilisez testVerifierPositionValide
        assertTrue(jeu.testVerifierPositionValide(new Position(3, 2)));
        assertTrue(jeu.testVerifierPositionValide(new Position(4, 5)));
        assertTrue(jeu.testVerifierPositionValide(new Position(5, 4)));
    }

    @Test
    public void testCoupInvalide() {
        assertFalse(jeu.testVerifierPositionValide(new Position(0, 0))); // Utilisez testVerifierPositionValide
        assertFalse(jeu.testVerifierPositionValide(new Position(3, 3)));
    }

    @Test
    public void testJouerCoup() {
        jeu.appliquerCoup(new Position(2, 3));
        assertEquals(4, jeu.compterPions(Couleur.NOIR));
        assertEquals(1, jeu.compterPions(Couleur.BLANC));
    }

    @Test
    public void testChangementJoueur() {
        assertEquals(joueurNoir, jeu.getJoueurCourant());
        jeu.changerJoueur();
        assertEquals(joueurBlanc, jeu.getJoueurCourant());
    }

    @Test
    public void testPartieNonTerminee() {
        assertFalse(jeu.estTermine());
    }

    @Test
    public void testDeterminerVainqueur() {
        // Simuler une fin de partie où Noir a plus de pions
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Position pos = new Position(i, j);
                try {
                    jeu.getPlateau().poserPion(pos, Couleur.NOIR);
                } catch (IllegalArgumentException e) {
                    System.out.println("Erreur à la position: " + i + "," + j);
                    throw e; // Relancer l'exception pour faire échouer le test
                }
            }
        }
        jeu.getPlateau().poserPion(new Position(0, 0), Couleur.BLANC);
    
        assertEquals(joueurNoir, jeu.determinerVainqueur());
    }
}