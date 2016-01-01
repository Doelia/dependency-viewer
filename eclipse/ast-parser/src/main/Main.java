package main;

import java.io.IOException;

import externals.Parser;
import extractor.*;

public class Main {
	
	private static boolean LOGGING = false;
	
	public static void Log(String s) {
		if (LOGGING) {
			System.out.println(s);
		}
	}

	public static void main(String[] args) throws IOException {

		// Parametres
		String path = "/Users/doelia/Documents/dev/survivia-bukkit/src/rush/gameserver/game";
		String method = "classe"; // classe ou projet
		String classe = "GamerRushIG";
		LOGGING = true;
		
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
