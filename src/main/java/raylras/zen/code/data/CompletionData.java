package raylras.zen.code.data;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import raylras.zen.code.CompilationUnit;
import raylras.zen.code.parser.ZenScriptParser;
import raylras.zen.util.Nodes;

public class CompletionData {
    public final CompletionKind kind;
    public final ParserRuleContext node;
    public final String completingString;

    public CompletionData(CompletionKind kind, ParserRuleContext node, String completingString) {
        this.kind = kind;
        this.node = node;
        this.completingString = completingString;
    }

    public ZenScriptParser.ExpressionContext getQualifierExpression() {
        if (node instanceof ZenScriptParser.MemberAccessExprContext) {
            return ((ZenScriptParser.MemberAccessExprContext) node).Left;
        } else if (node instanceof ZenScriptParser.ArrayIndexExprContext) {
            return ((ZenScriptParser.ArrayIndexExprContext) node).Left;
        } else if (node instanceof ZenScriptParser.IntRangeExprContext) {
            return ((ZenScriptParser.IntRangeExprContext) node).From;
        }
        return null;
    }

    public boolean isEndsWithParen() {
        ParseTree next = Nodes.getNextNode(node);
        return next instanceof TerminalNode && ((TerminalNode) next).getSymbol().getType() == ZenScriptParser.PAREN_OPEN;
    }

    public static final CompletionData NONE = new CompletionData(CompletionKind.NONE, null, "");

}
