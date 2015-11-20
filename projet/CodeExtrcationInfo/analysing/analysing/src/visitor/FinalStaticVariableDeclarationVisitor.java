package visitor;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class FinalStaticVariableDeclarationVisitor extends ASTVisitor {
	private List<VariableDeclarationFragment> variables = new ArrayList<VariableDeclarationFragment>();
	
	public boolean visit(VariableDeclarationFragment node) {
	if(Modifier.isStatic(node.resolveBinding().getModifiers()) && Modifier.isFinal(node.resolveBinding().getModifiers())){
		variables.add(node);
	}
		return super.visit(node);
	}
	
	public List<VariableDeclarationFragment> getVariables() {
		return variables;
	}
}
