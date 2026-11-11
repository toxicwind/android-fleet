package org.mozilla.javascript.ast;

import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: loaded from: classes8.dex */
public class BreakStatement extends Jump {
    private Name breakLabel;
    private AstNode targetNode;

    public BreakStatement() {
        this.type = 133;
    }

    public BreakStatement(int i) {
        this.type = 133;
        this.position = i;
    }

    public BreakStatement(int i, int i2) {
        this.type = 133;
        this.position = i;
        this.length = i2;
    }

    public Name getBreakLabel() {
        return this.breakLabel;
    }

    public void setBreakLabel(Name name) {
        this.breakLabel = name;
        if (name != null) {
            name.setParent(this);
        }
    }

    public AstNode getBreakTarget() {
        return this.targetNode;
    }

    public void setBreakTarget(Jump jump) {
        assertNotNull(jump);
        this.targetNode = jump;
        setJumpStatement(jump);
    }

    @Override // org.mozilla.javascript.ast.Jump, org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(i));
        sb.append("break");
        if (this.breakLabel != null) {
            sb.append(Stream.ID_UNKNOWN);
            sb.append(this.breakLabel.toSource(0));
        }
        sb.append(";\n");
        return sb.toString();
    }

    @Override // org.mozilla.javascript.ast.Jump, org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        Name name;
        if (!nodeVisitor.visit(this) || (name = this.breakLabel) == null) {
            return;
        }
        name.visit(nodeVisitor);
    }
}
