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
