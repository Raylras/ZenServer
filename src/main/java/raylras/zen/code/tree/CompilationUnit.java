package raylras.zen.code.tree;

import raylras.zen.code.Range;
import raylras.zen.code.SourceUnit;
import raylras.zen.code.scope.LocalScope;
import raylras.zen.code.tree.stmt.Statement;

import java.util.List;

/**
 * Represents an Abstract Syntax Tree (AST) of the source unit,
 * which may contain imports, statements, and other elements.
 */
public class CompilationUnit extends TreeNode {

    public List<ImportDeclaration> imports;
    public List<ClassDeclaration> classes;
    public List<FunctionDeclaration> functions;
    public List<Statement> statements;
    public LocalScope localScope;
    public SourceUnit sourceUnit;

    public CompilationUnit(List<ImportDeclaration> imports, List<ClassDeclaration> classes, List<FunctionDeclaration> functions, List<Statement> statements, Range range) {
        super(range);
        this.imports = imports;
        this.classes = classes;
        this.functions = functions;
        this.statements = statements;
    }

    @Override
    public void accept(TreeVisitor visitor) {
        boolean visitChildren = visitor.visit(this);
        if (visitChildren) {
            acceptChildren(visitor, imports);
            acceptChildren(visitor, classes);
            acceptChildren(visitor, functions);
            acceptChildren(visitor, statements);
        }
        visitor.afterVisit(this);
    }

}