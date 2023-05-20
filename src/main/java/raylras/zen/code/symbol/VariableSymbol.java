package raylras.zen.code.symbol;

import org.antlr.v4.runtime.tree.ParseTree;
import raylras.zen.code.CompilationUnit;
import raylras.zen.code.data.Declarator;
import raylras.zen.code.resolve.DeclaratorResolver;
import raylras.zen.code.resolve.NameResolver;
import raylras.zen.code.resolve.VariableTypeResolver;
import raylras.zen.code.type.Type;

import java.util.Collections;
import java.util.List;

public class VariableSymbol extends Symbol {

    public VariableSymbol(ParseTree owner, CompilationUnit unit) {
        super(owner, unit);
    }

    @Override
    public String getName() {
        return new NameResolver().resolve(getOwner());
    }

    @Override
    public Type getType() {
        return new VariableTypeResolver(getUnit()).resolve(getOwner());
    }

    @Override
    public ZenSymbolKind getKind() {
        return ZenSymbolKind.LOCAL_VARIABLE;
    }

    @Override
    public List<Symbol> getMembers() {
        return Collections.emptyList();
    }

    @Override
    public Declarator getDeclarator() {
        return new DeclaratorResolver().resolve(getOwner());
    }

    @Override
    public boolean isDeclaredBy(Declarator declarator) {
        return declarator == getDeclarator();
    }

}
