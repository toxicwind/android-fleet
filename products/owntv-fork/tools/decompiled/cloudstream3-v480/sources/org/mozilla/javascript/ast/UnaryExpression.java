package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: loaded from: classes8.dex */
public class UnaryExpression extends AstNode {
    private AstNode operand;

    public UnaryExpression() {
    }

    public UnaryExpression(int i) {
        super(i);
    }

    public UnaryExpression(int i, int i2) {
        super(i, i2);
    }

    public UnaryExpression(int i, int i2, AstNode astNode) {
        assertNotNull(astNode);
        setBounds(astNode.getPosition(), astNode.getPosition() + astNode.getLength());
        setOperator(i);
        setOperand(astNode);
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

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(i));
        int type = getType();
        sb.append(operatorToString(type));
        if (type == 32 || type == 31 || type == 139) {
            sb.append(Stream.ID_UNKNOWN);
        }
        sb.append(this.operand.toSource());
        return sb.toString();
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.operand.visit(nodeVisitor);
        }
    }
}
