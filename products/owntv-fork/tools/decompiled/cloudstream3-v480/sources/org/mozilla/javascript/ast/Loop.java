package org.mozilla.javascript.ast;

/* JADX INFO: loaded from: classes8.dex */
public abstract class Loop extends Scope {
    protected AstNode body;
    protected int lp;
    protected int rp;

    public Loop() {
        this.lp = -1;
        this.rp = -1;
    }

    public Loop(int i) {
        super(i);
        this.lp = -1;
        this.rp = -1;
    }

    public Loop(int i, int i2) {
        super(i, i2);
        this.lp = -1;
        this.rp = -1;
    }

    public AstNode getBody() {
        return this.body;
    }

    public void setBody(AstNode astNode) {
        this.body = astNode;
        setLength((astNode.getPosition() + astNode.getLength()) - getPosition());
        astNode.setParent(this);
    }

    public int getLp() {
        return this.lp;
    }

    public void setLp(int i) {
        this.lp = i;
    }

    public int getRp() {
        return this.rp;
    }

    public void setRp(int i) {
        this.rp = i;
    }

    public void setParens(int i, int i2) {
        this.lp = i;
        this.rp = i2;
    }
}
