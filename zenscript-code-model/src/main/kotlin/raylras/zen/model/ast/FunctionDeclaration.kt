package raylras.zen.model.ast

import com.strumenta.kolasu.model.EntityDeclaration
import com.strumenta.kolasu.model.Node
import com.strumenta.kolasu.model.PossiblyNamed
import com.strumenta.kolasu.model.Statement

data class FunctionDeclaration(
    override val declaringKind: DeclaringKind,
    val simpleName: String?,
    val parameters: List<ParameterDeclaration> = emptyList(),
    val returnTypeLiteral: TypeLiteral? = null,
    val body: List<Statement> = emptyList(),
) : Node(), EntityDeclaration, PossiblyNamed, DeclaringDescription {
    override val name: String?
        get() = simpleName
}
