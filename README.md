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

Par M. Seriai
- Commencement **TP Introduction**  en 1h30 : Processus de Maintenance Logicielle
  - Modification du code -> problème sur des cas particuliers pas pensés
  - Les étapes pour effectuer une modification :
    - **Ciblage d'information**
    - **Analyse d'impact**
      - Via Le graphe de dépendance :
        - Flow de contrôle (Méthodes)
        - Flow de données (Variable, affectations...)
    - **Test de regression** (tester la bonne execution du programme)

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

### Séance du 12/10
Intervenant Nicolas Anquetil, Université de Lille
- Cours "Software Visualisation"
  - [A quoi sert la visualisation](https://github.com/Doelia/M2-evolution/raw/master/cours/moose/software-visualisation.pdf)
  - [Langage Pharo](https://github.com/Doelia/M2-evolution/raw/master/cours/moose/pharo-intro.pdf)
  - [Utilitaire Moose](https://github.com/Doelia/M2-evolution/raw/master/cours/moose/moose-intro.pdf)
- TP avec [Moose](http://www.moosetechnology.org/) pour visualiser, basé sur le langage Pharo
  - Visualisation de n'importe quel code source, à partir d'un fichier MSE

### Séance du 19/10
Invervenant Henri Basson sur l'anaylse d'impact  
Pas grand chose de nouveau, 4 pages de notes prisent en vrac  
Parti au bout de 1h30

### Séance 2/11
Intervenant Anne Etien, Université de Lille  
Méta-modélisation dans le contexte de l'évolution logicielle  
TP suite de moose, pas compris grand chose :(  
Création de packages, propriétes avec le logiciel...

### Séance 9/11
Cours avec Marianne Huchard sur les croisement des données
- [Cours sur l'analyse formelle](file:///Users/doelia/Downloads/coursFCAInterfaces%20(1).pdf)
- TP : début du projet avec les collections Java

### Séance 16/11
Intervenant Mathieu Acher, Université de Rennes 1
- Cours sur les lignes de produits : qu'est-ce qu'une ligne de produit
- [TP sur Familiar](https://www.lirmm.fr/content/download/10217/142938/file/TP-FAMILIAR-OC.pdf) (interface web ou on tape des expressions), assez long, mais bien commencé et compris
  - Peut être un rendu noté à faire, mais pas encore décidé

### Séance 23/11
Par M. Seriai
- Cours ["Approches de Re-architecturisation des systèmes existants"](http://www.lirmm.fr/~seriai/uploads/Enseignement/ExtractionComposant.pdf)
    - Définition d'un composant : Une unité de composition possédant des interfaces spécifiées par contrat et des dépendances explicites avec le contexte. Il peut être déployé indépendamment et peut être composé par un tiers
    - Cycle de vie d'un logiciel

### Séance 30/11
Cours annulé

### Séance 7/12
Par M. Chouki  
Support de cours : http://www.lirmm.fr/~tibermacin/ens/hmin306/
- Cours, 15h : http://www.lirmm.fr/~tibermacin/ens/hmin306/cours/cours1.pdf
    - Évolution dirigée par la qualité des architectures à base de composants
        - Partie 1, Évolution des architectures à composants
- Cours, 15h50 : http://www.lirmm.fr/~tibermacin/ens/hmin306/cours/cours3.pdf
    - Spécification des contraintes d'architecture
        - Ça correspond a ce qu'on a fait en ing. des modèle avec le langage OCL (TP tortues)
- Cours, 16h15 : http://www.lirmm.fr/~tibermacin/ens/hmin306/cours/cours4.pdf
    - Génération de code Java à partir de contraintes d'architecture
