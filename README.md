# Générateur de graphe de dépendance

## Description de l'outil

Outil permettant de générer des graphes à partir d'un code source java. La génération est faite par une analyse du code source java en utilisant l'arbre syntaxique abstrait (AST) et la visualisation peut se faire dans un navigateur Internet.

Génération de deux types de graphes :
- Graphe d'appel au sein d'une classe
- Graphe d'appel de l'application


## Description technique

Le projet comporte 3 composants :
- Une application JAVA qui permet de générer un graphe au format json en utilisant un AST
    - Exporté en .jar pour une utilisation externe
- Un client web qui permet de visualiser les graphes et de configurer des parametres
    - Bibliothèque utilisée pour la visualisation : https://github.com/almende/vis
- Un serveur en Golang pour relier le .jar au client web

### Documetation du code Java

4 packages :
- **Package externals/** Permet la génération de l'AST via le pattern Visitor ;
- **Package builder/** Représente les entités Methode et Type (classe) nécessaires à la gérération d'un graphe. Il propose toutes les méthodes nécessaires au stockage des ces entités et au calcul des dépendances entre elles ;
- **Package extractor/** Regroupe tous les outils nécessaires à la génération de graphes en fonction des objectifs voulus :
    - Extractor.java est la classe abstraite proposant les méthodes pour la construction du graphe ainsi que la conversion au format JSON de ces grahes ;
    - ExtractorClasse.java et ExtractorClasse.java sont la mise en pratique des cas voulus.
- **Package main/** contient la méthode main qui a le role de lancer les executions et de gérer les parametres d'entrée du programme

## Utilisation

Le .jar est à disposition dans le repertoire et n'a pas besoin d'être recompilé.

Donner le droit d'execution au .jar :
```
chmod u+x graph-generator.jar
```

Compiler le serveur golang :
```
ln -s golang/ast-linker $GOPATH/src/ast-linker
cd golang/ast-linker
go build
```

Lancer le serveur Golang en spécifiant le chemin absolue du JAR et le port HTTP voulu :
```
./ast-linker -port 2000 -jar \
/Users/doelia/Documents/dev/M2/dependency-viewer/graph-generator.jar
```

Ouvrir le client web dans un navigateur : http://localhost:2000

## Résultat

### Formulaire

![](http://i.imgur.com/rxdGtff.png)

### Graphe d'appel pour une classe
![](http://i.imgur.com/fFtDOMp.png)

### Graphe du projet
![](http://i.imgur.com/rI5r78O.png)
