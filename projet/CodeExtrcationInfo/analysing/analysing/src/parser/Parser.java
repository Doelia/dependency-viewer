package parser;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.internal.utils.FileUtil;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import visitor.MethodDeclarationVisitor;
import visitor.MethodInvocationVisitor;
import visitor.TypeDeclarationVisitor;
import visitor.VariableDeclarationFragmentVisitor;

public class Parser {
	
	public static final String projectPath = "C:\\Users\\seriai\\workspace\\testHMIN303";
	public static final String projectSourcePath = projectPath + "\\src";
	public static final String jrePath = "C:\\Program Files\\Java\\jre1.8.0_51\\lib\\rt.jar";
	
	public static List<VariableDeclarationFragment> staticFinalVariableList= new ArrayList<VariableDeclarationFragment>();

	public static void main(String[] args) throws IOException {

		// read java files
		final File folder = new File(projectPath);
		ArrayList<File> javaFiles = listJavaFilesForFolder(folder);

		//
		for (File fileEntry : javaFiles) {
			String content = FileUtils.readFileToString(fileEntry);
			// System.out.println(content);

			CompilationUnit parse = parse(content.toCharArray());
			
			// print methods info
			staticFinalVariableList.addAll(getFinalStaticVaribles(parse));
			
			System.out.println("Method Signature:");
			System.out.println(getMethodSignature(parse));
			
			System.out.println("Constructors:");
			System.out.println(getCostructor(parse));
			
			System.out.println("Parent Method");
			System.out.println(getParentMethods(parse));

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

	// create AST
	private static CompilationUnit parse(char[] classSource) {
		ASTParser parser = ASTParser.newParser(AST.JLS4); // java +1.6
		parser.setResolveBindings(true);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
 
		parser.setBindingsRecovery(true);
 
		Map options = JavaCore.getOptions();
		parser.setCompilerOptions(options);
 
		String unitName = "Apple.java";
		parser.setUnitName(unitName);
 
		String[] sources = { projectSourcePath }; 
		String[] classpath = {jrePath};
 
		parser.setEnvironment(classpath, sources, new String[] { "UTF-8"}, true);
		parser.setSource(classSource);
		
		return (CompilationUnit) parser.createAST(null); // create and parse
	}

	// navigate method information
	public static List<VariableDeclarationFragment> getFinalStaticVaribles(CompilationUnit parse) {
		
		List<VariableDeclarationFragment> staticFinalVariableList= new ArrayList<VariableDeclarationFragment>();
		
		TypeDeclarationVisitor typeDeclarationVisitor = new TypeDeclarationVisitor();
		parse.accept(typeDeclarationVisitor);
		for (TypeDeclaration typeDeclaration : typeDeclarationVisitor.getTypes()) {
			System.out.println("Class Name "+typeDeclaration.getName());
			for (FieldDeclaration fieldDeclaration : typeDeclaration.getFields()) {
				for (VariableDeclarationFragment variable : (List<VariableDeclarationFragment>)fieldDeclaration.fragments()) {
					if(Modifier.isStatic(variable.resolveBinding().getModifiers()) && Modifier.isFinal(variable.resolveBinding().getModifiers())){
						staticFinalVariableList.add(variable);
					}
					
				}
				
			}
		}
		
		return staticFinalVariableList;
	}

	public static String getMethodSignature(CompilationUnit parse){
		MethodDeclarationVisitor visitor = new MethodDeclarationVisitor();
		parse.accept(visitor);
		String methodSig = "";
		for (MethodDeclaration method : visitor.getMethods()) {
			methodSig += "Method name: " + method.getName()
					+ "  Modifier: " + (Modifier.isStatic(method.resolveBinding().getModifiers()) ? "static" : "not static");
			methodSig += "\n";
			for (SingleVariableDeclaration singleVariableDeclaration : (List<SingleVariableDeclaration>)method.parameters()) {
				methodSig += "ParameterName " + singleVariableDeclaration.getName().getFullyQualifiedName()
						  + "  ParameterType " + singleVariableDeclaration.resolveBinding().getType().getName();
				methodSig += "\n";
			}
			
			for (ITypeBinding exceptions : method.resolveBinding().getExceptionTypes()) {
				methodSig += "ExceptionType " + exceptions.getName();	
				methodSig += "\n";
			}
			
			methodSig += " Return type: " + method.getReturnType2();
			methodSig += "\n";
		}
		
		return methodSig;
	}
	
	
	public static String getCostructor(CompilationUnit parse){
		MethodDeclarationVisitor visitor = new MethodDeclarationVisitor();
		parse.accept(visitor);
		String methodSig = "";
		for (MethodDeclaration method : visitor.getMethods()) {
			if(!method.isConstructor()){continue;}
			methodSig += "Method name: " + method.getName();
			methodSig += "\n";
			for (SingleVariableDeclaration singleVariableDeclaration : (List<SingleVariableDeclaration>)method.parameters()) {
				methodSig += "ParameterName" + singleVariableDeclaration.getName().getFullyQualifiedName()
						  + "  ParameterType" + singleVariableDeclaration.resolveBinding().getType().getName();
				methodSig += "\n";
			}
			methodSig += "\n";
		}
		
		return methodSig;
	}
	
	
	
	public static String getParentMethods(CompilationUnit parse){
		TypeDeclarationVisitor typeVisitor = new TypeDeclarationVisitor();
		parse.accept(typeVisitor);
		
		Type parent = typeVisitor.getTypes().get(0).getSuperclassType();

		 if (parent == null || parent.equals(Object.class.getSimpleName())){//reach top of tree
	            return "";
	        }
		
		String methodSig = "";
		for (IMethodBinding method : parent.resolveBinding().getDeclaredMethods()) {
			methodSig += "Method name: " + method.getName();
			methodSig += "\n";
			for (ITypeBinding param : method.getParameterTypes()) {
				methodSig += "ParameterName " + param.getName();
				methodSig += "\n";
			}
		}
		
		return methodSig;
	}
	
	
}
