package raylras.zen.code.type;

import raylras.zen.code.symbol.OperatorFunctionSymbol.Operator;
import raylras.zen.code.symbol.Symbol;
import raylras.zen.code.symbol.SymbolFactory;

import java.util.List;

public class ListType extends Type {

    private final Type elementType;

    public ListType(Type elementType) {
        this.elementType = elementType;
    }

    public Type getElementType() {
        return elementType;
    }

    @Override
    public List<Symbol> getMembers() {
        return SymbolFactory.members()
                .variable("length", IntType.INSTANCE, Symbol.Modifier.VAL)
                .function("remove", VoidType.INSTANCE, params -> params.parameter("index", IntType.INSTANCE))
                .operator(Operator.INDEX_GET, elementType, params -> params.parameter("index", IntType.INSTANCE))
                .operator(Operator.INDEX_SET, elementType, params ->
                        params.parameter("index", IntType.INSTANCE).parameter("element", elementType)
                )
                .operator(Operator.ADD, this, params -> params.parameter("element", elementType))
                .build();
    }

    @Override
    public SubtypeResult isSubtypeOf(Type type) {
        if (this.equals(type)) {
            return SubtypeResult.SELF;
        }
        if (type == AnyType.INSTANCE) {
            return SubtypeResult.INHERIT;
        }
        if (type instanceof ArrayType) {
            ArrayType that = (ArrayType) type;
            return SubtypeResult.higher(this.elementType.isSubtypeOf(that.getElementType()), SubtypeResult.CASTER);
        }
        if (type instanceof ListType) {
            ListType that = (ListType) type;
            return this.elementType.isSubtypeOf(that.getElementType());
        }
        return super.isSubtypeOf(type);
    }

    @Override
    public String toString() {
        return "[" + elementType + "]";
    }

}
