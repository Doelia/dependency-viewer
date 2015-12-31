package extractor;

import builder.Methode;
import builder.Type;

public class ExcratorClasse extends Extractor {

	
	public String nameClasse;
	
	
	public void process() {
		Type c = Type.getTypeFromName(nameClasse);
		
		if (c == null) {
			System.out.println("Type "+nameClasse+" introuvable");
			System.exit(0);
		}
		
		for (Methode m : c.methodes) {
			this.noeuds.add(m.name);
			
			for (Methode called : m.appels) {
				if (called.nameClasse.equals(nameClasse)) {
				
					this.addArc(m.name, called.name);
				}
			}
		}
		
		
	}
}
