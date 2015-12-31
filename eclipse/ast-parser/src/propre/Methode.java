package propre;

import java.util.ArrayList;
import java.util.List;

public class Methode {
	
	public static ArrayList<Methode> instances = new ArrayList<>(); // Méthodes la composant
	
	
	
	public String name; // Contruit à li'init
	
	// Provisoire pour contruction plus tard
	public String typeRetour; // Contsruit à l'init
	public ArrayList<String> paramsString = new ArrayList<>();
	
	public String nameClasse = "undefined";
	
	
	// Contruit après coup
	public Type retour; // Type de retour
	public ArrayList<Type> params = new ArrayList<>(); // Liste des parametres
	public ArrayList<Methode> appels = new ArrayList<>(); // Appelle des méthodes
	public Type classe = null; // Classe dont elle fait partie
	
	@Override
	public String toString() {
		return "Méthode "+name+" (classe "+nameClasse+"), retour de type "+typeRetour;
	}
	
	public static Methode getMothodeFromName(String nameClasse, String nomMethode) {
		for (Methode m : instances) {
			if (m.nameClasse.equals(nameClasse) && m.name.equals(nomMethode)) {
				return m;
			}
		}
		return null;
	}
	
	public static void addInvocation(String classeAppellante, String methodeappelante, String classeAppelle, String methodeApelle) {
		System.out.println("Brut: "+classeAppellante+":"+methodeappelante+ " appelle la méthode "+classeAppelle+":"+methodeApelle);
		
		Methode appelante = getMothodeFromName(classeAppellante, methodeappelante);
		Methode appellee = getMothodeFromName(classeAppelle, methodeApelle);
		if (appelante != null) {
			if (appellee != null) { 
				appelante.appels.add(appellee);
				return;
			} else {
				System.out.println(classeAppelle+":"+methodeApelle+" introuvable");
			}
			//appelante.appels.add(appellee);
		} else {
			System.out.println(classeAppellante+":"+methodeappelante+" introuvable");
		}
	}
}
