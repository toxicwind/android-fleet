package org.mozilla.javascript.ast;

/* JADX INFO: loaded from: classes8.dex */
public class LetNode extends Scope {
    private AstNode body;
    private int lp;
    private int rp;
    private VariableDeclaration variables;

    public LetNode() {
        this.lp = -1;
        this.rp = -1;
        this.type = 172;
    }

    public LetNode(int i) {
        super(i);
        this.lp = -1;
        this.rp = -1;
        this.type = 172;
    }

    public LetNode(int i, int i2) {
        super(i, i2);
        this.lp = -1;
        this.rp = -1;
        this.type = 172;
    }

    public VariableDeclaration getVariables() {
        return this.variables;
    }

    public void setVariables(VariableDeclaration variableDeclaration) {
        assertNotNull(variableDeclaration);
        this.variables = variableDeclaration;
        variableDeclaration.setParent(this);
    }

    public AstNode getBody() {
        return this.body;
    }

    public void setBody(AstNode astNode) {
        this.body = astNode;
        if (astNode != null) {
            astNode.setParent(this);
        }
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

    @Override // org.mozilla.javascript.ast.Scope, org.mozilla.javascript.ast.Jump, org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        String strMakeIndent = makeIndent(i);
        StringBuilder sb = new StringBuilder();
        sb.append(strMakeIndent);
        sb.append("let (");
        printList(this.variables.getVariables(), sb);
        sb.append(") ");
        AstNode astNode = this.body;
        if (astNode != null) {
            sb.append(astNode.toSource(i));
        }
        return sb.toString();
    }

    @Override // org.mozilla.javascript.ast.Scope, org.mozilla.javascript.ast.Jump, org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.variables.visit(nodeVisitor);
            AstNode astNode = this.body;
            if (astNode != null) {
                astNode.visit(nodeVisitor);
            }
        }
    }
}
