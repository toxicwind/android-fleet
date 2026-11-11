package org.mozilla.javascript.ast;

/* JADX INFO: loaded from: classes8.dex */
public class WithStatement extends AstNode {
    private AstNode expression;
    private int lp;
    private int rp;
    private AstNode statement;

    public WithStatement() {
        this.lp = -1;
        this.rp = -1;
        this.type = 136;
    }

    public WithStatement(int i) {
        super(i);
        this.lp = -1;
        this.rp = -1;
        this.type = 136;
    }

    public WithStatement(int i, int i2) {
        super(i, i2);
        this.lp = -1;
        this.rp = -1;
        this.type = 136;
    }

    public AstNode getExpression() {
        return this.expression;
    }

    public void setExpression(AstNode astNode) {
        assertNotNull(astNode);
        this.expression = astNode;
        astNode.setParent(this);
    }

    public AstNode getStatement() {
        return this.statement;
    }

    public void setStatement(AstNode astNode) {
        assertNotNull(astNode);
        this.statement = astNode;
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

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(i));
        sb.append("with (");
        sb.append(this.expression.toSource(0));
        sb.append(") ");
        if (getInlineComment() != null) {
            sb.append(getInlineComment().toSource(i + 1));
        }
        if (this.statement.getType() == 143) {
            if (getInlineComment() != null) {
                sb.append("\n");
            }
            sb.append(this.statement.toSource(i).trim());
            sb.append("\n");
        } else {
            sb.append("\n");
            sb.append(this.statement.toSource(i + 1));
        }
        return sb.toString();
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.expression.visit(nodeVisitor);
            this.statement.visit(nodeVisitor);
        }
    }
}
