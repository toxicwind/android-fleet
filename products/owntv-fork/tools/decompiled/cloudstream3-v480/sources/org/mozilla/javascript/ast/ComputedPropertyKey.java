package org.mozilla.javascript.ast;

/* JADX INFO: loaded from: classes8.dex */
public class ComputedPropertyKey extends AstNode {
    private AstNode expression;

    public ComputedPropertyKey(int i, int i2) {
        super(i, i2);
        this.type = 142;
    }

    public AstNode getExpression() {
        return this.expression;
    }

    public void setExpression(AstNode astNode) {
        assertNotNull(astNode);
        this.expression = astNode;
        astNode.setParent(this);
    }

    @Override // org.mozilla.javascript.ast.AstNode, org.mozilla.javascript.Node
    public boolean hasSideEffects() {
        if (this.expression == null) {
            codeBug();
        }
        return this.expression.hasSideEffects();
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        return makeIndent(i) + "[" + this.expression.toSource(i) + "]";
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.expression.visit(nodeVisitor);
        }
    }
}
