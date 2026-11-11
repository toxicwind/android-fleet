package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/* JADX INFO: loaded from: classes8.dex */
public class UpdateExpression extends AstNode {
    private boolean isPostfix;
    private AstNode operand;

    public UpdateExpression() {
    }

    public UpdateExpression(int i) {
        super(i);
    }

    public UpdateExpression(int i, int i2) {
        super(i, i2);
    }

    public UpdateExpression(int i, int i2, AstNode astNode) {
        this(i, i2, astNode, false);
    }

    public UpdateExpression(int i, int i2, AstNode astNode, boolean z) {
        assertNotNull(astNode);
        setBounds(z ? astNode.getPosition() : i2, z ? i2 + 2 : astNode.getPosition() + astNode.getLength());
        setOperator(i);
        setOperand(astNode);
        this.isPostfix = z;
    }

    public int getOperator() {
        return this.type;
    }

    public void setOperator(int i) {
        if (!Token.isValidToken(i)) {
            throw new IllegalArgumentException("Invalid token: " + i);
        }
        setType(i);
    }

    public AstNode getOperand() {
        return this.operand;
    }

    public void setOperand(AstNode astNode) {
        assertNotNull(astNode);
        this.operand = astNode;
        astNode.setParent(this);
    }

    public boolean isPostfix() {
        return this.isPostfix;
    }

    public boolean isPrefix() {
        return !this.isPostfix;
    }

    public void setIsPostfix(boolean z) {
        this.isPostfix = z;
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(i));
        int type = getType();
        if (!this.isPostfix) {
            sb.append(operatorToString(type));
        }
        sb.append(this.operand.toSource());
        if (this.isPostfix) {
            sb.append(operatorToString(type));
        }
        return sb.toString();
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.operand.visit(nodeVisitor);
        }
    }
}
