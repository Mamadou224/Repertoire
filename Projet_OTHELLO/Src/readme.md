# Projet Othello

## Auteur
[SOW Mamadou Baïllo]

## Description
Ce projet est une implémentation du jeu de société Othello (ou Reversi) en Java. Le programme permet à deux joueurs de jouer à Othello sur un plateau de 8x8 cases.

## Fonctionnalités
Le programme réalise les actions suivantes tant que le jeu n'est pas terminé :

1. Affiche le plateau de jeu et indique le nombre de pions noirs et blancs présents.
2. Informe le joueur s'il n'existe pas de position valide pour jouer.
3. Si au moins une position de jeu valide existe :
   - Demande au joueur actif d'indiquer, par une saisie au clavier, une position valide où il souhaite poser son pion.
   - Applique les effets de la pose du pion (retournement des pions adverses).
4. Passe au joueur suivant.

À la fin de la partie, le programme annonce le joueur vainqueur.

## Structure du projet
Le projet est organisé comme suit :
TP4/ ├── src/ │ └── classes/ │ ├── jeu/ │ ├── plateau/ │ ├── pièces/ │ └── utils/
              └── README.md

## Classes principales
1. `Othello`: Classe principale qui gère le déroulement du jeu
2. `Plateau`: Représente le plateau de jeu 8x8
3. `Case`: Représente une case individuelle du plateau
4. `Pion`: Représente un pion noir ou blanc
5. `Position`: Représente une position sur le plateau (ligne et colonne)
6. `Joueur`: Représente un joueur
7. `Couleur` (enum): Énumération pour les couleurs des pions (noir ou blanc)
8. `Direction` (enum): Énumération pour les 8 directions possibles

-- Question 2) un diagramme UML des classes
+-------------------+                                              
|      Othello      |
+-------------------+
- plateau: Plateau
- joueurs: List<Joueur>
- joueurCourant: Joueur
+-------------------+
+ jouerTour()
+ changerJoueur()
+ estTermine(): boolean
+ determinerVainqueur(): Joueur
+ afficherPlateau()
+ verifierPositionValide(Position): boolean
+ appliquerCoup(Position)                               

+-------------------+
|      Plateau      |
+-------------------+
- cases: Case[][]
+-------------------+
+ poserPion(Position, Couleur)
+ getPion(Position): Pion
+ estPositionValide(Position): boolean
+ compterPions(Couleur): int
+ afficher()

+-------------------+
|       Case        |
+-------------------+
- pion: Pion
+-------------------+
+ setPion(Pion)
+ getPion(): Pion
+ estVide(): boolean

+-------------------+
|       Pion        |
+-------------------+
- couleur: Couleur
+-------------------+
+ getCouleur(): Couleur
+ retourner()

+-------------------+
|      Joueur       |
+-------------------+
- couleur: Couleur
+-------------------+
+ jouer(Plateau): Position
+ getCouleur(): Couleur

+-------------------+
|     Position      |
+-------------------+
- ligne: int
- colonne: int
+-------------------+
+ getLigne(): int
+ getColonne(): int

<<enumeration>>
+-------------------+
|      Couleur      |
+-------------------+
NOIR
BLANC

<<enumeration>>
+-------------------+
|     Direction     |
+-------------------+
- dx: int
- dy: int
+-------------------+
+ getDx(): int
+ getDy(): int

## Comment jouer
-- Démarrage : Lorsque vous lancez le jeu, il vous demandera de saisir les noms des joueurs. Le joueur Noir commence toujours.

-- Faire un coup : À chaque tour, le joueur courant doit entrer une position sous la forme "ligne colonne" (par exemple, 3 4).

-- Validité des coups : Le jeu vérifiera si le coup est valide. Si ce n'est pas le cas, il demandera au joueur de réessayer.

-- Changement de joueur : Après chaque coup valide, le tour passe au joueur suivant.

-- Fin de partie : La partie se termine lorsque aucun des joueurs ne peut plus jouer. Le vainqueur est alors annoncé.

-- Affichage du plateau : À chaque tour, l'état actuel du plateau est affiché, ainsi que le nombre de pions de chaque couleur.

## commandes utilisées:
1- Pour compiler toutes les classes du projet, exécutez la commande suivante depuis le répertoire Src :
     cd Src javac Classes/Jeu/Othello.java Classes/Jeu/Joueur.java Classes/Plateau/Plateau.java Classes/Plateau/Position.java Classes/Utils/Couleur.java

2- Pour lancer le jeu Othello, utilisez cette commande depuis le répertoire principal du projet
    java -cp Src OthelloMain

3- Compilation des tests (depuis le répertoire Test) :
    cd Test
    javac -cp ".;../Src;./junit-platform-console-standalone-1.8.2.jar" TestOthello.java

4- Exécution des tests :
   java -jar junit-platform-console-standalone-1.8.2.jar --class-path ".:../Src" --scan-class-path

5- Génération de la documentation Javadoc
   javadoc -d ../doc Classes/**/*.java

6- Nettoyage des fichiers compilés
   find . -name "*.class" -delete