package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: loaded from: classes8.dex */
public class InfixExpression extends AstNode {
    protected AstNode left;
    protected int operatorPosition;
    protected AstNode right;

    public InfixExpression() {
        this.operatorPosition = -1;
    }

    public InfixExpression(int i) {
        super(i);
        this.operatorPosition = -1;
    }

    public InfixExpression(int i, int i2) {
        super(i, i2);
        this.operatorPosition = -1;
    }

    public InfixExpression(int i, int i2, AstNode astNode, AstNode astNode2) {
        super(i, i2);
        this.operatorPosition = -1;
        setLeft(astNode);
        setRight(astNode2);
    }

    public InfixExpression(AstNode astNode, AstNode astNode2) {
        this.operatorPosition = -1;
        setLeftAndRight(astNode, astNode2);
    }

    public InfixExpression(int i, AstNode astNode, AstNode astNode2, int i2) {
        this.operatorPosition = -1;
        setType(i);
        setOperatorPosition(i2 - astNode.getPosition());
        setLeftAndRight(astNode, astNode2);
    }

    public void setLeftAndRight(AstNode astNode, AstNode astNode2) {
        assertNotNull(astNode);
        assertNotNull(astNode2);
        setBounds(astNode.getPosition(), astNode2.getPosition() + astNode2.getLength());
        setLeft(astNode);
        setRight(astNode2);
    }

    public int getOperator() {
        return getType();
    }

    public void setOperator(int i) {
        if (!Token.isValidToken(i)) {
            throw new IllegalArgumentException("Invalid token: " + i);
        }
        setType(i);
    }

    public AstNode getLeft() {
        return this.left;
    }

    public void setLeft(AstNode astNode) {
        assertNotNull(astNode);
        this.left = astNode;
        setLineColumnNumber(astNode.getLineno(), astNode.getColumn());
        astNode.setParent(this);
    }

    public AstNode getRight() {
        return this.right;
    }

    public void setRight(AstNode astNode) {
        assertNotNull(astNode);
        this.right = astNode;
        astNode.setParent(this);
    }

    public int getOperatorPosition() {
        return this.operatorPosition;
    }

    public void setOperatorPosition(int i) {
        this.operatorPosition = i;
    }

    @Override // org.mozilla.javascript.ast.AstNode, org.mozilla.javascript.Node
    public boolean hasSideEffects() {
        AstNode astNode;
        int type = getType();
        if (type == 98) {
            AstNode astNode2 = this.right;
            return astNode2 != null && astNode2.hasSideEffects();
        }
        if (type == 117 || type == 118) {
            AstNode astNode3 = this.left;
            return (astNode3 != null && astNode3.hasSideEffects()) || ((astNode = this.right) != null && astNode.hasSideEffects());
        }
        return super.hasSideEffects();
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        return makeIndent(i) + this.left.toSource() + Stream.ID_UNKNOWN + operatorToString(getType()) + Stream.ID_UNKNOWN + this.right.toSource();
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.left.visit(nodeVisitor);
            this.right.visit(nodeVisitor);
        }
    }
}
