# M2-evolution
HMIN306 - Evolution et restructuration  
Cours du lundi après-midi

## Supports de cours
Planning des interventions : https://www.lirmm.fr/users/utilisateurs-lirmm/marianne-huchard/enseignement/hmin306
Supports des séances de seriai : http://www.lirmm.fr/~seriai/index.php?n=Enseignement.CoursEvolutionEtRestructurationMaster2 (3 premiers cours)

## Résumé des séances

### Séannce du 14/09

- **Cours sur la maintenance** : http://www.lirmm.fr/~seriai/uploads/Enseignement/cours.pdf
- Pas de TP/TP

#### Installation du plugin Eclipse JRipples : 

Documentation : http://jripples.sourceforge.net/  

[Eclipse Juno 4.2.2](https://www.eclipse.org/downloads/packages/eclipse-classic-422/junosr2) conseillé pour compatibilité plugin lucene. Nécéssite Java 6 sous OS X.

Fonctionne aussi sous [Eclipse Kepler 4.3](http://www.eclipse.org/downloads/packages/release/Kepler/SR2)

Installation :
  - Télécharger le JAR : https://sourceforge.net/project/platformdownload.php?group_id=191931
  - Installer jripples_X.jar depuis l'ajout de logiciels Eclipse (add sources / archive)
  - Si erreur de dépendances : Supprimer les org.apache.lucene* de eclipse/plugins et placer ceux du zip, puis reboot Eclipse (n'a pas fonctionné sous Eclipse Luna 4.4.2)

### Séance du 21/09

- Commencement **TP Introduction**  en 1h30 : Processus de Maintenance Logicielle
  - Modification du code -> problème sur des cas particuliers pas pensés
  - Les étapes pour effectuer une modification :
    - **Ciblage d'information**
    - **Analyse d'impact**
      - Via Le graphe de dépendance :
        - Flow de contrôle (Méthodes)
        - Flow de données (Variable, affectations...)
    - T**est de regression** (tester la bonne execution du programme)

Étapes générales dans la modification d'un logiciel (incremental change) :  
http://jripples.sourceforge.net/jripples/manual/concepts/icmain.html

#### Puis petit cours sur **Outil CodeCity** (Pas de salle de TP)
Visualisation graphique du code
- District = packages
- Imeubles = Classes

Permet d'obtenir un tas d'informations via l'interface :
- Connaître les classes concernées par une méthode
- Voir les aspects les plus utilsés dans une classe

<img src="http://www.inf.usi.ch/phd/wettel/pics/codecity_screenshot.png" width="400px">  

### Séance du 28/09
Cours annulé, pas reporté.

### Séance du 05/10
#### Cours sur l'anaylse de 3 heures
- Objectifs : Vérifier, comprendre, transformer, décompiler, obfusquer, optimiser
- Deux types d'analyse
  - Analyse statique : code source uniquement, pas d'execution. Exemple : CodeCity
    - Se fait en deux phases : Extraction d'un modèle (graphes, arbres, flots de contrôles, diagrammes...), puis analyse de leurs propriétés
    - Permet par exemple le sclicing : découper le code indépendant
  - Analyse dynamique : Ecriture de code et execution
    - Permet de mesurer les temps d'execution, le nombre d'utilisation d'une fonction
    - De faire une vérification plus poussée des erreus : boucles infinies, code non atteignable...
    - Décomposer le code automatiquement

<img src="https://github.com/Doelia/M2-evolution/raw/master/cours/c21.png" width="400px">  

#### [TP](http://www.lirmm.fr/~seriai/uploads/Enseignement/TdEvol2.pdf) de 1h30 sur l'utilisation de AST avec Eclise
A peine avancé...  
Pleins de liens, code, lib... sur http://www.lirmm.fr/~seriai/index.php?n=Enseignement.CoursEvolutionEtRestructurationMaster2 (TD2)

### Séance du 13/10
Intervenant Nicolas Anquetil
- Cours "Software Visualisation"
  - [A quoi sert la visualisation](https://github.com/Doelia/M2-evolution/raw/master/cours/moose/software-visualisation.pdf)
  - [Langage Pharo](https://github.com/Doelia/M2-evolution/raw/master/cours/moose/pharo-intro.pdf)
  - [Utilitaire Moose](https://github.com/Doelia/M2-evolution/raw/master/cours/moose/moose-intro.pdf)
- TP avec [Moose](http://www.moosetechnology.org/) pour visualiser, basé sur le langage Pharo
  - Visualisation de n'importe quel code source, à partir d'un fichier MSE
