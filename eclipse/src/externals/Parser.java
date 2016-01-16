package externals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;

import builder.Methode;
import builder.Type;
import main.Main;

public class Parser {

	public static final String jrePath = "/usr/lib/jvm/java-8-oracle/lib/dt.jar";

    // Construit la hiérarchie nécessaise pour la navigation
	public static void buildHierarchy(String projectPath) throws IOException {

		final File folder = new File(projectPath);
		ArrayList<File> javaFiles = listJavaFilesForFolder(folder);

		Main.Log("=== BUILD LISTE DES MÉTHODES ET DES CLASSE");
		for (File fileEntry : javaFiles) {
			Main.Log("File " + fileEntry);
			String content = FileUtils.readFileToString(fileEntry);

			CompilationUnit parse = parse(content.toCharArray(), projectPath);
			printMethodInfo(parse);

		}

		Main.Log("Type créés:");

		Methode.buildAppartenances();

		Main.Log("=== BUILD LISTE APPELS");
		for (File fileEntry : javaFiles) {
			Main.Log("File " + fileEntry);
			String content = FileUtils.readFileToString(fileEntry);

			CompilationUnit parse = parse(content.toCharArray(), projectPath);
			printMethodInvocationInfo(parse);

		}
	}

	private static void printMethodInfo(CompilationUnit parse) {
		MethodDeclarationVisitor visitor = new MethodDeclarationVisitor();
		parse.accept(visitor);

		for (MethodDeclaration method : visitor.getMethods()) {

			try {
				String nameClasse = method.resolveBinding().getDeclaringClass().getName();

				Methode m = new Methode();
				m.name = method.getName().toString();
				m.nameClasse = nameClasse;
				Methode.instances.add(m);

				Type.addType(nameClasse);
			} catch (Exception e) {
			}

		}

	}

	// navigate method invocations inside method
	// La liste des méthodes est contruite
	private static void printMethodInvocationInfo(CompilationUnit parse) {

		MethodDeclarationVisitor visitor1 = new MethodDeclarationVisitor();
		parse.accept(visitor1);
		for (MethodDeclaration method : visitor1.getMethods()) {

			MethodInvocationVisitor visitor2 = new MethodInvocationVisitor();
			method.accept(visitor2);

			String nameClasse = method.resolveBinding().getDeclaringClass().getName();

			for (MethodInvocation methodInvocation : visitor2.getMethods()) {

				String nameClasseAppelee = "";

				if (methodInvocation.resolveMethodBinding() != null) {
					nameClasseAppelee = methodInvocation.resolveMethodBinding().getDeclaringClass().getName();

					Methode.addInvocation(nameClasse, method.getName().toString(), nameClasseAppelee,
							methodInvocation.getName().toString());
				}

			}

		}
	}

	// read all java files from specific folder
	private static ArrayList<File> listJavaFilesForFolder(final File folder) {
		ArrayList<File> javaFiles = new ArrayList<File>();
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				javaFiles.addAll(listJavaFilesForFolder(fileEntry));
			} else if (fileEntry.getName().contains(".java")) {
				// Main.Log(fileEntry.getName());
				javaFiles.add(fileEntry);
			}
		}

		return javaFiles;
	}

	// create AST
	private static CompilationUnit parse(char[] classSource, String path) {
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setResolveBindings(true);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);

		parser.setBindingsRecovery(true);

		Map<?, ?> options = JavaCore.getOptions();
		parser.setCompilerOptions(options);

		parser.setUnitName("");

		String[] sources = { path };
		String[] classpath = { jrePath };

		parser.setEnvironment(classpath, sources, new String[] { "UTF-8" }, true);
		parser.setSource(classSource);

		return (CompilationUnit) parser.createAST(null); // create and parse
	}

}
