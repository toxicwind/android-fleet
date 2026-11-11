package org.mozilla.javascript.ast;

import org.slf4j.Marker;

/* JADX INFO: loaded from: classes8.dex */
public class GeneratorMethodDefinition extends AstNode {
    private AstNode methodName;

    public GeneratorMethodDefinition(int i, int i2, AstNode astNode) {
        super(i, i2);
        setType(23);
        setMethodName(astNode);
    }

    public AstNode getMethodName() {
        return this.methodName;
    }

    public void setMethodName(AstNode astNode) {
        assertNotNull(astNode);
        this.methodName = astNode;
        astNode.setParent(this);
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        return makeIndent(i) + Marker.ANY_MARKER + this.methodName.toSource(i);
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.methodName.visit(nodeVisitor);
        }
    }
}
