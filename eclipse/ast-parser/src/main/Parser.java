package main;

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
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import propre.Methode;
import propre.Type;

public class Parser {
	
	public static final String projectPath = "/Users/doelia/Documents/dev/M2/M2-evolution/eclipse/ast-parser";
	public static final String projectSourcePath = projectPath + "/src";
	public static final String jrePath = "/usr/lib/jvm/java-8-oracle/lib/dt.jar";

	public static void main(String[] args) throws IOException {

		// read java files
		final File folder = new File(projectSourcePath);
		ArrayList<File> javaFiles = listJavaFilesForFolder(folder);

		//
		for (File fileEntry : javaFiles) {
			System.out.println("****************");
			System.out.println("File "+fileEntry);
			String content = FileUtils.readFileToString(fileEntry);
			//System.out.println(content);
			System.out.println("METHOD INFO");
			CompilationUnit parse = parse(content.toCharArray());
			// print methods info
			printMethodInfo(parse);
			System.out.println(Methode.instances);
			
//			System.out.println("----------------");
//			
//			System.out.println("VARIABLE");
//			// print variables info
//			printVariableInfo(parse);
//			
			System.out.println("----------------");
						
			System.out.println("METHOD INVOCATION INFO");
			//print method invocations
			printMethodInvocationInfo(parse);

		}
	}
	
	// navigate variables inside method
	public static void printVariableInfo(CompilationUnit parse) {

		MethodDeclarationVisitor visitor1 = new MethodDeclarationVisitor();
		parse.accept(visitor1);
		for (MethodDeclaration method : visitor1.getMethods()) {

			VariableDeclarationFragmentVisitor visitor2 = new VariableDeclarationFragmentVisitor();
			method.accept(visitor2);

			for (VariableDeclarationFragment variableDeclarationFragment : visitor2
					.getVariables()) {
				System.out.println("variable name: "
						+ variableDeclarationFragment.getName()
						+ " variable Initializer: "
						+ variableDeclarationFragment.getInitializer());
			}

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

			for (MethodInvocation methodInvocation : visitor2.getMethods()) {
				
				System.out.println("method " + method.getName() + " invoc method "
						+ methodInvocation.getName());
			}

		}
	}

	public static void printMethodInfo(CompilationUnit parse) {
		MethodDeclarationVisitor visitor = new MethodDeclarationVisitor();
		parse.accept(visitor);

		for (MethodDeclaration method : visitor.getMethods()) {
			
			Methode m = new Methode();
			m.name = method.getName().toString();
			m.typeRetour = method.getReturnType2().toString();
			m.nameClasse = method.getClass().getName().toString();
			Methode.instances.add(m);
			
			Type.createInFotExists(method.getReturnType2().toString());
			Type.createInFotExists(method.getClass().getName().toString());
			
			System.out.println("Method name: " + method.getName()
					+ " Return type: " + method.getReturnType2());
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
 
		Map options = JavaCore.getOptions();
		parser.setCompilerOptions(options);
 
		parser.setUnitName("");
 
		String[] sources = { projectSourcePath }; 
		String[] classpath = {jrePath};
 
		parser.setEnvironment(classpath, sources, new String[] { "UTF-8"}, true);
		parser.setSource(classSource);
		
		return (CompilationUnit) parser.createAST(null); // create and parse
	  }

}
