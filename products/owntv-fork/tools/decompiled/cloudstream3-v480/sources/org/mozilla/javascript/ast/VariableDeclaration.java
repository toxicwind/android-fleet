package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.mozilla.javascript.Node;
import org.mozilla.javascript.Token;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: loaded from: classes8.dex */
public class VariableDeclaration extends AstNode {
    private boolean isStatement;
    private List<VariableInitializer> variables;

    public VariableDeclaration() {
        this.variables = new ArrayList();
        this.type = 135;
    }

    public VariableDeclaration(int i) {
        super(i);
        this.variables = new ArrayList();
        this.type = 135;
    }

    public VariableDeclaration(int i, int i2) {
        super(i, i2);
        this.variables = new ArrayList();
        this.type = 135;
    }

    public List<VariableInitializer> getVariables() {
        return this.variables;
    }

    public void setVariables(List<VariableInitializer> list) {
        assertNotNull(list);
        this.variables.clear();
        Iterator<VariableInitializer> it = list.iterator();
        while (it.hasNext()) {
            addVariable(it.next());
        }
    }

    public void addVariable(VariableInitializer variableInitializer) {
        assertNotNull(variableInitializer);
        this.variables.add(variableInitializer);
        variableInitializer.setParent(this);
    }

    @Override // org.mozilla.javascript.Node
    public Node setType(int i) {
        if (i != 135 && i != 168 && i != 167) {
            throw new IllegalArgumentException("invalid decl type: " + i);
        }
        return super.setType(i);
    }

    public boolean isVar() {
        return this.type == 135;
    }

    public boolean isConst() {
        return this.type == 168;
    }

    public boolean isLet() {
        return this.type == 167;
    }

    public boolean isStatement() {
        return this.isStatement;
    }

    public void setIsStatement(boolean z) {
        this.isStatement = z;
    }

    private String declTypeName() {
        return Token.typeToName(this.type).toLowerCase(Locale.ROOT);
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(i));
        sb.append(declTypeName());
        sb.append(Stream.ID_UNKNOWN);
        printList(this.variables, sb);
        if (isStatement()) {
            sb.append(";");
        }
        if (getInlineComment() != null) {
            sb.append(getInlineComment().toSource(i));
            sb.append("\n");
        } else if (isStatement()) {
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            Iterator<VariableInitializer> it = this.variables.iterator();
            while (it.hasNext()) {
                it.next().visit(nodeVisitor);
            }
        }
    }
}
