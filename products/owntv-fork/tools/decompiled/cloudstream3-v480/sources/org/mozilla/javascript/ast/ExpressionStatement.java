package org.mozilla.javascript.ast;

/* JADX INFO: loaded from: classes8.dex */
public class ExpressionStatement extends AstNode {
    private AstNode expr;

    public void setHasResult() {
        this.type = 148;
    }

    public ExpressionStatement() {
        this.type = 147;
    }

    public ExpressionStatement(AstNode astNode, boolean z) {
        this(astNode);
        if (z) {
            setHasResult();
        }
    }

    public ExpressionStatement(AstNode astNode) {
        this(astNode.getPosition(), astNode.getLength(), astNode);
    }

    public ExpressionStatement(int i, int i2) {
        super(i, i2);
        this.type = 147;
    }

    public ExpressionStatement(int i, int i2, AstNode astNode) {
        super(i, i2);
        this.type = 147;
        setExpression(astNode);
    }

    public AstNode getExpression() {
        return this.expr;
    }

    public void setExpression(AstNode astNode) {
        assertNotNull(astNode);
        this.expr = astNode;
        astNode.setParent(this);
        setLineColumnNumber(astNode.getLineno(), this.expr.getColumn());
    }

    @Override // org.mozilla.javascript.ast.AstNode, org.mozilla.javascript.Node
    public boolean hasSideEffects() {
        return this.type == 148 || this.expr.hasSideEffects();
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.expr.toSource(i));
        sb.append(";");
        if (getInlineComment() != null) {
            sb.append(getInlineComment().toSource(i));
        }
        sb.append("\n");
        return sb.toString();
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.expr.visit(nodeVisitor);
        }
    }
}
