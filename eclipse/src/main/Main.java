package main;

import java.io.IOException;

import externals.Parser;
import extractor.*;

public class Main {

    // Active l'affichage des logs
	private static boolean LOGGING = false;

	public static void Log(String s) {
		if (LOGGING) {
			System.out.println(s);
		}
	}

	public static void main(String[] args) throws IOException {

		// Parametres de tests
		String path = "/Users/doelia/Documents/dev/M2/M2-evolution/eclipse/ast-parser/src";
		String method = "projet"; // classe ou projet
		String classe = "GamerRushIG";
		LOGGING = true;

        // Parametres d'execution via le JAR
		if (args.length > 0) {
			path = args[0];
			method = args[1];
			classe = args[2];
			LOGGING = false;
		}

		Main.Log("path = "+path);
		Main.Log("method = "+method);
		Main.Log("classe = "+classe);

		Parser.buildHierarchy(path);

		Main.Log("Construction du graphe...");

        // Determinaison de la méchode voulue pour l'extraction
		Extractor ex = null;
		if (method.equals("classe")) {
			ExtractorClasse ex1 = new ExtractorClasse();
			ex1.nameClasse = classe;
			ex1.process();
			ex = ex1;
		} else {
			ExtractorProject ex2 = new ExtractorProject();
			ex2.process();
			ex = ex2;
		}

		Main.Log("Génération du json...");
		System.out.println(ex.toJson());
		Main.Log("End.");

	}
}
