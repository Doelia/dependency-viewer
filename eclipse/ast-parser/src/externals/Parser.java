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
import extractor.ExcratorClasse;

public class Parser {
	
	public static final String projectPath = "/Users/doelia/Documents/dev/M2/M2-evolution/eclipse/ast-parser";
	public static final String projectSourcePath = projectPath + "/src";
	public static final String jrePath = "/usr/lib/jvm/java-8-oracle/lib/dt.jar";

	public static void main(String[] args) throws IOException {

		// read java files
		final File folder = new File(projectSourcePath);
		ArrayList<File> javaFiles = listJavaFilesForFolder(folder);

		
		System.out.println("=== BUILD LISTE DES MÉTHODES ET DES CLASSE");
		for (File fileEntry : javaFiles) {
			System.out.println("****************");
			System.out.println("File "+fileEntry);
			String content = FileUtils.readFileToString(fileEntry);
			
			CompilationUnit parse = parse(content.toCharArray());
			printMethodInfo(parse);

		}
		
		System.out.println(Methode.instances);
		
		System.out.println("Type créés:");
		System.out.println(Type.instances);
		
		Methode.buildAppartenances();
		
		
		System.out.println("=== BUILD LISTE APPELS");
		for (File fileEntry : javaFiles) {
			System.out.println("****************");
			System.out.println("File "+fileEntry);
			String content = FileUtils.readFileToString(fileEntry);

			CompilationUnit parse = parse(content.toCharArray());
			printMethodInvocationInfo(parse);

		}
		
		
		ExcratorClasse ex1 = new ExcratorClasse();
		ex1.nameClasse = "Parser";
		ex1.process();
		
	}
	
	public static void printMethodInfo(CompilationUnit parse) {
		MethodDeclarationVisitor visitor = new MethodDeclarationVisitor();
		parse.accept(visitor);
		

		for (MethodDeclaration method : visitor.getMethods()) {
			
			String nameClasse = method.resolveBinding().getDeclaringClass().getName();
			
			Methode m = new Methode();
			m.name = method.getName().toString();
			m.typeRetour = method.getReturnType2().toString();
			m.nameClasse = nameClasse;
			Methode.instances.add(m);
			
//			System.out.println("Méthode contruite : "+m);
			
			Type.createInFotExists(nameClasse);
		}

	}
	
	// navigate method invocations inside method
	// La liste des méthodes est contruite
	public static void printMethodInvocationInfo(CompilationUnit parse) {

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
					
					Methode.addInvocation(
							nameClasse, method.getName().toString(),
							nameClasseAppelee, methodInvocation.getName().toString());
				}
				
			}

		}
	}
	
	// read all java files from specific folder
	public static ArrayList<File> listJavaFilesForFolder(final File folder) {
		ArrayList<File> javaFiles = new ArrayList<File>();
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				javaFiles.addAll(listJavaFilesForFolder(fileEntry));
			} else if (fileEntry.getName().contains(".java")) {
				// System.out.println(fileEntry.getName());
				javaFiles.add(fileEntry);
			}
		}

		return javaFiles;
	}
	
	//create AST
	private static CompilationUnit parse(char[] classSource) {
		ASTParser parser = ASTParser.newParser(AST.JLS8); // java +1.6
		parser.setResolveBindings(true);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
 
		parser.setBindingsRecovery(true);
 
		Map<?, ?> options = JavaCore.getOptions();
		parser.setCompilerOptions(options);
 
		parser.setUnitName("");
 
		String[] sources = { projectSourcePath }; 
		String[] classpath = {jrePath};
 
		parser.setEnvironment(classpath, sources, new String[] { "UTF-8"}, true);
		parser.setSource(classSource);
		
		return (CompilationUnit) parser.createAST(null); // create and parse
	  }

}
