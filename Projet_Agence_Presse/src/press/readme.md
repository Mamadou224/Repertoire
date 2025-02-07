[Auteur: Mamadou Baïllo Sow]

# Projet Agence de Presse

## Description
Ce projet simule une agence de presse qui diffuse des nouvelles à différents lecteurs. Les lecteurs peuvent être de différents types, y compris des lecteurs normaux, spécifiques (qui s'intéressent à un mot-clé) et des flux d'informations pour les nouvelles urgentes.

## Fonctionnalités
- Enregistrement de lecteurs à l'agence de presse.
- Envoi de nouvelles avec différents statuts (NORMAL, URGENT, BREAKING).
- Publication de nouvelles en fonction des intérêts des lecteurs.
- Filtrage des nouvelles par mot-clé pour les lecteurs spécifiques.

## commandes utilisées:
- Pour compiler toutes les classes du projet, exécutez la commande suivante:

$ javac -cp classes -d classes src/press/*.java

- Pour exécuter le programme, exécutez la commande suivante:

$ java -classpath classes press.NewsMain

- Pour exécuter le fichier test:

$ java -cp "classes;junit-console.jar" org.junit.runner.JUnitCore press.news.NewsTest

