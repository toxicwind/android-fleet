package org.mozilla.javascript.ast;

/* JADX INFO: loaded from: classes8.dex */
public class XmlMemberGet extends InfixExpression {
    public XmlMemberGet() {
        this.type = 157;
    }

    public XmlMemberGet(int i) {
        super(i);
        this.type = 157;
    }

    public XmlMemberGet(int i, int i2) {
        super(i, i2);
        this.type = 157;
    }

    public XmlMemberGet(int i, int i2, AstNode astNode, XmlRef xmlRef) {
        super(i, i2, astNode, xmlRef);
        this.type = 157;
    }

    public XmlMemberGet(AstNode astNode, XmlRef xmlRef) {
        super(astNode, xmlRef);
        this.type = 157;
    }

    public XmlMemberGet(AstNode astNode, XmlRef xmlRef, int i) {
        super(157, astNode, xmlRef, i);
        this.type = 157;
    }

    public AstNode getTarget() {
        return getLeft();
    }

    public void setTarget(AstNode astNode) {
        setLeft(astNode);
    }

    public XmlRef getMemberRef() {
        return (XmlRef) getRight();
    }

    public void setProperty(XmlRef xmlRef) {
        setRight(xmlRef);
    }

    @Override // org.mozilla.javascript.ast.InfixExpression, org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        return makeIndent(i) + getLeft().toSource(0) + dotsToString() + getRight().toSource(0);
    }

    private String dotsToString() {
        int type = getType();
        if (type == 121) {
            return ".";
        }
        if (type == 157) {
            return "..";
        }
        throw new IllegalArgumentException("Invalid type of XmlMemberGet: " + getType());
    }
}
