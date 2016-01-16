package extractor;

import builder.Methode;
import builder.Type;
import main.Main;

// Permet d'extraite un graphede dépendance depuis une classe
public class ExtractorClasse extends Extractor {

	public String nameClasse;

	public void process() {
		Type c = Type.getTypeFromName(nameClasse);

		if (c == null) {
			Main.Log("Type " + nameClasse + " introuvable");
			System.exit(0);
		}

        // On crée un noeud pour chacune des méthodes de la classe
		for (Methode m : c.methodes) {
			this.addNoeud(m.name);
			for (Methode called : m.appels) {
				if (called.nameClasse.equals(nameClasse)) {
					this.addArc(m.name, called.name);
				}
			}
		}

	}
}
