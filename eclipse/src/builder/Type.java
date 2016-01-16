package builder;

import java.util.HashSet;

public class Type {

	public static HashSet<Type> instances = new HashSet<>();

    // Ajoute un nouveau type à la liste des instances
	public static void addType(String nameClasse) {
		Type t = new Type();
		t.name = nameClasse;
		instances.add(t);
	}

	// Statique
	public String name = "undefined";

	//  Dynamique
	public HashSet<Methode> methodes = new HashSet<Methode>();

	public String toString() {
		return name;
	}

    // Retourne l'instance d'un type depuis son nom
	public static Type getTypeFromName(String name) {
		for (Type t : instances) {
			if (t.name.equals(name)) {
				return t;
			}
		}
		return null;
	}

}
