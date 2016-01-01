package main;

import java.io.IOException;

import externals.Parser;
import extractor.*;

public class Main {

	public static void main(String[] args) throws IOException {

		// Parametres
		String path = "/Users/doelia/Documents/dev/survivia-bukkit/src/rush/gameserver/game";
		String method = "classe"; // classe ou projet
		String classe = "GamerRushIG";
		
		if (args.length > 0) {
			path = args[0];
			method = args[1];
			classe = args[2];
		}
		
		System.out.println("path = "+path);
		System.out.println("method = "+method);
		System.out.println("classe = "+classe);

		Parser.buildHierarchy(path);

		System.out.println("Construction du graphe...");
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

		System.out.println("Génération du json...");
		System.out.println(ex.toJson());
		System.out.println("End.");

	}
}
