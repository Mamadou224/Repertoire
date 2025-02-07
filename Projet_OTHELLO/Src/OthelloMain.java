import Classes.Jeu.*;
import Classes.Utils.Couleur;
import java.util.Scanner;

public class OthelloMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Demande des noms des joueurs
        System.out.println("Bienvenue dans le jeu Othello !");
        System.out.print("Nom du joueur Noir : ");
        String nomJoueurNoir = scanner.nextLine();
        System.out.print("Nom du joueur Blanc : ");
        String nomJoueurBlanc = scanner.nextLine();

        // Création des joueurs
        Joueur joueurNoir = new Joueur(nomJoueurNoir, Couleur.NOIR);
        Joueur joueurBlanc = new Joueur(nomJoueurBlanc, Couleur.BLANC);

        // Création et lancement du jeu
        Othello jeu = new Othello(joueurNoir, joueurBlanc);
        jeu.jouer();

        // Annonce du vainqueur
        Joueur vainqueur = jeu.determinerVainqueur();
        if (vainqueur != null) {
            System.out.println("Félicitations " + vainqueur.getNom() + " ! Vous avez gagné la partie !");
        } else {
            System.out.println("Match nul ! Bien joué à tous les deux !");
        }

        // Affichage du score final
        System.out.println("Score final :");
        System.out.println(joueurNoir.getNom() + " (Noir) : " + jeu.compterPions(Couleur.NOIR));
        System.out.println(joueurBlanc.getNom() + " (Blanc) : " + jeu.compterPions(Couleur.BLANC));

        scanner.close();
    }
}