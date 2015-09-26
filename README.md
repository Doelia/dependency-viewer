# M2-evolution
HMIN306 - Evolution et restructuration

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

- Commencement **TP Introduction**  : Processus de Maintenance Logicielle
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

#### **Outil CodeCity**  
Visualisation graphique du code
- District = packages
- Imeubles = Classes

Permet d'obtenir un tas d'informations via l'interface :
- Connaître les classes concernées par une méthode
- Voir les aspects les plus utilsés dans une classe

<img src="http://www.inf.usi.ch/phd/wettel/pics/codecity_screenshot.png" width="400px">  

