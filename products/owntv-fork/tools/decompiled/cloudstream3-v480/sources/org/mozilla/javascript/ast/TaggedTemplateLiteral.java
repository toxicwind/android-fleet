package org.mozilla.javascript.ast;

/* JADX INFO: loaded from: classes8.dex */
public class TaggedTemplateLiteral extends AstNode {
    private AstNode target;
    private AstNode templateLiteral;

    public TaggedTemplateLiteral() {
        this.type = 183;
    }

    public TaggedTemplateLiteral(int i) {
        super(i);
        this.type = 183;
    }

    public TaggedTemplateLiteral(int i, int i2) {
        super(i, i2);
        this.type = 183;
    }

    public AstNode getTarget() {
        return this.target;
    }

    public void setTarget(AstNode astNode) {
        this.target = astNode;
        astNode.setParent(this);
    }

    public AstNode getTemplateLiteral() {
        return this.templateLiteral;
    }

    public void setTemplateLiteral(AstNode astNode) {
        this.templateLiteral = astNode;
        astNode.setParent(this);
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        return makeIndent(i) + this.target.toSource(0) + this.templateLiteral.toSource(0);
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.target.visit(nodeVisitor);
            this.templateLiteral.visit(nodeVisitor);
        }
    }
}
