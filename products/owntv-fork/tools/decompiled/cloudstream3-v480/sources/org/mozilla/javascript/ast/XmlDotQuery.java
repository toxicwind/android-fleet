package org.mozilla.javascript.ast;

/* JADX INFO: loaded from: classes8.dex */
public class XmlDotQuery extends InfixExpression {
    private int rp;

    public XmlDotQuery() {
        this.rp = -1;
        this.type = 160;
    }

    public XmlDotQuery(int i) {
        super(i);
        this.rp = -1;
        this.type = 160;
    }

    public XmlDotQuery(int i, int i2) {
        super(i, i2);
        this.rp = -1;
        this.type = 160;
    }

    public int getRp() {
        return this.rp;
    }

    public void setRp(int i) {
        this.rp = i;
    }

    @Override // org.mozilla.javascript.ast.InfixExpression, org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        return makeIndent(i) + getLeft().toSource(0) + ".(" + getRight().toSource(0) + ")";
    }
}
