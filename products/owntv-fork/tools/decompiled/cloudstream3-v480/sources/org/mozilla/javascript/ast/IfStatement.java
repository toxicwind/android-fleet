package org.mozilla.javascript.ast;

/* JADX INFO: loaded from: classes8.dex */
public class IfStatement extends AstNode {
    private AstNode condition;
    private AstNode elseKeyWordInlineComment;
    private AstNode elsePart;
    private int elsePosition;
    private int lp;
    private int rp;
    private AstNode thenPart;

    public IfStatement() {
        this.elsePosition = -1;
        this.lp = -1;
        this.rp = -1;
        this.type = 125;
    }

    public IfStatement(int i) {
        super(i);
        this.elsePosition = -1;
        this.lp = -1;
        this.rp = -1;
        this.type = 125;
    }

    public IfStatement(int i, int i2) {
        super(i, i2);
        this.elsePosition = -1;
        this.lp = -1;
        this.rp = -1;
        this.type = 125;
    }

    public AstNode getCondition() {
        return this.condition;
    }

    public void setCondition(AstNode astNode) {
        assertNotNull(astNode);
        this.condition = astNode;
        astNode.setParent(this);
    }

    public AstNode getThenPart() {
        return this.thenPart;
    }

    public void setThenPart(AstNode astNode) {
        assertNotNull(astNode);
        this.thenPart = astNode;
        astNode.setParent(this);
    }

    public AstNode getElsePart() {
        return this.elsePart;
    }

    public void setElsePart(AstNode astNode) {
        this.elsePart = astNode;
        if (astNode != null) {
            astNode.setParent(this);
        }
    }

    public int getElsePosition() {
        return this.elsePosition;
    }

    public void setElsePosition(int i) {
        this.elsePosition = i;
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
        String strMakeIndent = makeIndent(i);
        StringBuilder sb = new StringBuilder(32);
        sb.append(strMakeIndent);
        sb.append("if (");
        sb.append(this.condition.toSource(0));
        sb.append(") ");
        if (getInlineComment() != null) {
            sb.append("    ");
            sb.append(getInlineComment().toSource());
            sb.append("\n");
        }
        if (this.thenPart.getType() != 143) {
            if (getInlineComment() == null) {
                sb.append("\n");
            }
            sb.append(makeIndent(i + 1));
        }
        sb.append(this.thenPart.toSource(i).trim());
        if (this.elsePart != null) {
            if (this.thenPart.getType() != 143) {
                sb.append("\n");
                sb.append(strMakeIndent);
                sb.append("else ");
            } else {
                sb.append(" else ");
            }
            if (getElseKeyWordInlineComment() != null) {
                sb.append("    ");
                sb.append(getElseKeyWordInlineComment().toSource());
                sb.append("\n");
            }
            if (this.elsePart.getType() != 143 && this.elsePart.getType() != 125) {
                if (getElseKeyWordInlineComment() == null) {
                    sb.append("\n");
                }
                sb.append(makeIndent(i + 1));
            }
            sb.append(this.elsePart.toSource(i).trim());
        }
        sb.append("\n");
        return sb.toString();
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.condition.visit(nodeVisitor);
            this.thenPart.visit(nodeVisitor);
            AstNode astNode = this.elsePart;
            if (astNode != null) {
                astNode.visit(nodeVisitor);
            }
        }
    }

    public AstNode getElseKeyWordInlineComment() {
        return this.elseKeyWordInlineComment;
    }

    public void setElseKeyWordInlineComment(AstNode astNode) {
        this.elseKeyWordInlineComment = astNode;
    }
}
