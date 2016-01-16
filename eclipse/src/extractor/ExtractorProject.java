package extractor;

import java.util.HashMap;

import builder.Methode;
import builder.Type;

public class ExtractorProject extends Extractor {
	
	HashMap<String, Arc> map = new HashMap<>();
	
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
		
		for (Type c : Type.instances) {
			this.addNoeud(c.name);
		}
		
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
