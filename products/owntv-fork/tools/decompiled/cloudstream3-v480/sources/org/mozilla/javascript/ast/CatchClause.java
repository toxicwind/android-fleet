package org.mozilla.javascript.ast;

/* JADX INFO: loaded from: classes8.dex */
public class CatchClause extends AstNode {
    private Scope body;
    private AstNode catchCondition;
    private int ifPosition;
    private int lp;
    private int rp;
    private Name varName;

    public CatchClause() {
        this.ifPosition = -1;
        this.lp = -1;
        this.rp = -1;
        this.type = 137;
    }

    public CatchClause(int i) {
        super(i);
        this.ifPosition = -1;
        this.lp = -1;
        this.rp = -1;
        this.type = 137;
    }

    public CatchClause(int i, int i2) {
        super(i, i2);
        this.ifPosition = -1;
        this.lp = -1;
        this.rp = -1;
        this.type = 137;
    }

    public Name getVarName() {
        return this.varName;
    }

    public void setVarName(Name name) {
        this.varName = name;
        if (name != null) {
            name.setParent(this);
        }
    }

    public AstNode getCatchCondition() {
        return this.catchCondition;
    }

    public void setCatchCondition(AstNode astNode) {
        this.catchCondition = astNode;
        if (astNode != null) {
            astNode.setParent(this);
        }
    }

    public Scope getBody() {
        return this.body;
    }

    public void setBody(Scope scope) {
        assertNotNull(scope);
        this.body = scope;
        scope.setParent(this);
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

    public int getIfPosition() {
        return this.ifPosition;
    }

    public void setIfPosition(int i) {
        this.ifPosition = i;
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(i));
        sb.append("catch (");
        sb.append(this.varName.toSource(0));
        if (this.catchCondition != null) {
            sb.append(" if ");
            sb.append(this.catchCondition.toSource(0));
        }
        sb.append(") ");
        sb.append(this.body.toSource(0));
        return sb.toString();
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.varName.visit(nodeVisitor);
            AstNode astNode = this.catchCondition;
            if (astNode != null) {
                astNode.visit(nodeVisitor);
            }
            this.body.visit(nodeVisitor);
        }
    }
}
