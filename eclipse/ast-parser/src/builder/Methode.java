package builder;

import java.util.HashSet;

import main.Main;

public class Methode {

	public static HashSet<Methode> instances = new HashSet<>();

	public String name;
	public String typeRetour;
	public String nameClasse = "undefined";

	// Contruit après coup
	public HashSet<Methode> appels = new HashSet<>();
	public Type classe = null; // Classe dont elle fait partie

	@Override
	public String toString() {
		return "Méthode " + name + " (classe " + nameClasse + "), retour de type " + typeRetour;
	}

	public static Methode getMethode(String nameClasse, String nomMethode) {
		for (Methode m : instances) {
			if (m.nameClasse.equals(nameClasse) && m.name.equals(nomMethode)) {
				return m;
			}
		}
		return null;
	}

	public static void addInvocation(String classeAppellante, String methodeappelante, String classeAppelle,
			String methodeApelle) {

		Methode appelante = getMethode(classeAppellante, methodeappelante);
		Methode appellee = getMethode(classeAppelle, methodeApelle);
		if (appelante != null) {
			if (appellee != null) {
				appelante.appels.add(appellee);
				Main.Log("Brut: " + classeAppellante + ":" + methodeappelante + " appelle la méthode "
						+ classeAppelle + ":" + methodeApelle);
				return;
			}
		}
	}

	public static void buildAppartenances() {
		Main.Log("=== buildAppartenances === ");
		for (Methode m : instances) {
			Type classeContenante = Type.getTypeFromName(m.nameClasse);
			if (classeContenante != null) {
				m.classe = classeContenante;
				classeContenante.methodes.add(m);
				// Main.Log("La classe "+classeContenante+" contient
				// la méthode "+m.name);
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Methode) {
			return this.name.equals(((Methode) obj).name);
		}
		return false;
	}
}
