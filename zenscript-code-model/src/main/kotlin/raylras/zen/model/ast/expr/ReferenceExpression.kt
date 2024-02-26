package raylras.zen.model.ast.expr

import com.strumenta.kolasu.model.Expression
import com.strumenta.kolasu.model.Named
import com.strumenta.kolasu.model.Node
import com.strumenta.kolasu.model.ReferenceByName

data class ReferenceExpression(
    val ref: ReferenceByName<Named>,
) : Expression, Node()
