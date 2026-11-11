package org.mozilla.javascript.ast;

/* JADX INFO: loaded from: classes8.dex */
public class EmptyExpression extends AstNode {
    public EmptyExpression() {
        this.type = 141;
    }

    public EmptyExpression(int i) {
        super(i);
        this.type = 141;
    }

    public EmptyExpression(int i, int i2) {
        super(i, i2);
        this.type = 141;
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        return makeIndent(i);
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
    }
}
