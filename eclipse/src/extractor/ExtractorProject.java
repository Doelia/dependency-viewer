package extractor;

import java.util.HashMap;

import builder.Methode;
import builder.Type;

// Premet de contruire le graphe de dépendance pour le projet entier
public class ExtractorProject extends Extractor {

	HashMap<String, Arc> map = new HashMap<>();

    // Incrémente de 1 le poids de l'arc entre m1 et m2
	public void incrementArc(Methode m1, Methode m2) {
		String key = m1.nameClasse+"-"+m2.nameClasse;
		if (map.get(key) == null) {
			Arc arc = new Arc();
			arc.A = m1.nameClasse;
			arc.B = m2.nameClasse;
			map.put(key, arc);
		} else {
			Arc a = map.get(key);
			a.poids++;
		}
	}

	public void process() {

        // On crée un noeud pour chacune des classes
		for (Type c : Type.instances) {
			this.addNoeud(c.name);
		}

        // On incrémente l'arc entre chaque méthode 
		for (Methode m1 : Methode.instances) {
			for (Methode m2 : m1.appels) {
				if (!m1.nameClasse.equals(m2.nameClasse)) {
					this.incrementArc(m1, m2);
				}
			}
		}

		for (Arc c : map.values()) {
			this.arcs.add(c);
		}
	}
}
