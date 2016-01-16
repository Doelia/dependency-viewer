# Générateur de graphe de dépendance

![](http://i.imgur.com/rxdGtff.png)
![](http://i.imgur.com/fFtDOMp.png)
![](http://i.imgur.com/rI5r78O.png)

Le projet comporte 3 composants :
- Une application JAVA qui permet de générer un graphe au format json en utilisant un AST
    - Exporté en .jar pour une utilisation externe
- Un client web qui permet de visualiser les graphes et de configurer des parametres
- Un serveur en Golang pour relier le .jar au client web

## Utilisation

Le .jar est à disposition dans le repertoire et n'a pas besoin d'être recompilé  

Compiler le serveur golang :
```
ln -s golang/ast-linker $GOPATH/src/ast-linker
cd golang/ast-linker
go build
```

Lancer le serveur Golang en spécifiant le chemin absolue du JAR et le port HTTP voulu :
```
./ast-linker -port 2000 -jar /Users/doelia/Documents/dev/M2/M2-evolution/eclipse/ast-parser/graph-generator.jar
```

Ouvrir le client web dans un navigateur : http://localhost:2000
