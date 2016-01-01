# Générateur de graphe de dépendance

Le projet comporte 3 composants :
- Le code JAVA qui permet de générer un graphe au format json
    - Exporté en .jar pour une utilisation externe
- Un client web qui permet de visualiser les graphes et de configurer des parametres
- Un serveur en Golang pour relier le .jar au client web

## Utilisation

Le .jar est à disposition dans le repertoire et n'a pa besoin d'être recompilé  

Compiler le serveur golang :
```
go build
```

Lancer le serveur Golang en spécifiant le repertoire du JAR et le port HTTP voulu :
```
./ast-linker -port 2000 -jar /Users/doelia/Documents/dev/M2/M2-evolution/eclipse/ast-parser/graph-generator.jar
```

Ouvrir le client web dans un navigateur : http://localhost:2000
