package builder;

import java.util.ArrayList;

public class Methode {

	public static ArrayList<Methode> instances = new ArrayList<>();

	public String name;
	public String typeRetour;
	public String nameClasse = "undefined";

	// Contruit après coup
	public ArrayList<Methode> appels = new ArrayList<>();
	public Type classe = null; // Classe dont elle fait partie

	@Override
	public String toString() {
		return "Méthode " + name + " (classe " + nameClasse + "), retour de type " + typeRetour;
	}

	public static Methode getMothodeFromName(String nameClasse, String nomMethode) {
		for (Methode m : instances) {
			if (m.nameClasse.equals(nameClasse) && m.name.equals(nomMethode)) {
				return m;
			}
		}
		return null;
	}

	public static void addInvocation(String classeAppellante, String methodeappelante, String classeAppelle,
			String methodeApelle) {

		Methode appelante = getMothodeFromName(classeAppellante, methodeappelante);
		Methode appellee = getMothodeFromName(classeAppelle, methodeApelle);
		if (appelante != null) {
			if (appellee != null) {
				appelante.appels.add(appellee);
				System.out.println("Brut: " + classeAppellante + ":" + methodeappelante + " appelle la méthode "
						+ classeAppelle + ":" + methodeApelle);
				return;
			}
		}
	}

	public static void buildAppartenances() {
		System.out.println("=== buildAppartenances === ");
		for (Methode m : instances) {
			Type classeContenante = Type.getTypeFromName(m.nameClasse);
			if (classeContenante != null) {
				m.classe = classeContenante;
				classeContenante.methodes.add(m);
				// System.out.println("La classe "+classeContenante+" contient la méthode "+m.name);
			}
		}
	}
}
