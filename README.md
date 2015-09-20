# M2-evolution
HMIN306 - Evolution et restructuration

## Cours du 14/09

- Cours sur la maintenance : http://www.lirmm.fr/~seriai/uploads/Enseignement/cours.pdf
- Pas de TP/TP

### Installation du plugin Eclipse JRipples : 

Documentation : http://jripples.sourceforge.net/  

Eclipse Juno 4.2.2 conseillée pour compatibilité plugin lucene   https://www.eclipse.org/downloads/packages/eclipse-classic-422/junosr2  
Nécéssite Java 6 sous OS X...

Fonctionne aussi sous Eclipse Kepler 4.3  
http://www.eclipse.org/downloads/packages/release/Kepler/SR2

Installation :
  - Télécharger le JAR : https://sourceforge.net/project/platformdownload.php?group_id=191931
  - Installer jripples_X.jar depuis l'ajout de logiciels Eclipse (add sources / archive)
  - Si erreur de dépendances : Supprimer les org.apache.lucene* de eclipse/plugins et placer ceux du zip, puis reboot Eclipse (n'a pas fonctionné sous Eclipse Luna 4.4.2)
