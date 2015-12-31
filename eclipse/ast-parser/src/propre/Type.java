package propre;

import java.util.ArrayList;

public class Type {
	
	public static ArrayList<Type> instances = new ArrayList<>();
	
	public static void createInFotExists(String nameClasse) {
		for (Type instance : instances) {
			if (instance.name.equals(nameClasse)) {
				return;
			}
		}
		Type t = new Type();
		t.name = nameClasse;
		instances.add(t);
		
		System.out.println("++ Type "+nameClasse+" created.");
	}

	// Statique
	public String name = "undefined";
	
	// Dynamique
	public ArrayList<Methode> methodes = new ArrayList<Methode>();
	
	public String toString() {
		return name;
	}
	
	public static Type getTypeFromName(String name) {
		for (Type t : instances) {
			if (t.name.equals(name)) {
				return t;
			}
		}
		return null;
	}
	
}
