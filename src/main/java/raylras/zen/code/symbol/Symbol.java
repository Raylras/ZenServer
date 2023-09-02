package raylras.zen.code.symbol;

import raylras.zen.code.type.Type;

public interface Symbol {

    String getName();

    Kind getKind();

    Type getType();

    Modifier getModifier();

    default boolean isModifiedBy(Modifier modifier) {
        return getModifier() == modifier;
    }

    default boolean isStatic() {
        return isModifiedBy(Modifier.STATIC);
    }

    default boolean isGlobal() {
        return isModifiedBy(Modifier.GLOBAL);
    }

    enum Kind {
        IMPORT, PACKAGE, CLASS, VARIABLE, PARAMETER, FUNCTION, NONE
    }

    enum Modifier {
        VAR, VAL, STATIC, GLOBAL, EXPAND, NONE
    }

}
