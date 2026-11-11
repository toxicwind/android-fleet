package org.mozilla.javascript.ast;

import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public class SwitchStatement extends Jump {
    private static final List<SwitchCase> NO_CASES = DesugarCollections.unmodifiableList(new ArrayList());
    private List<SwitchCase> cases;
    private AstNode expression;
    private int lp = -1;
    private int rp = -1;

    public SwitchStatement() {
        this.type = 127;
    }

    public SwitchStatement(int i) {
        this.type = 127;
        this.position = i;
    }

    public SwitchStatement(int i, int i2) {
        this.type = 127;
        this.position = i;
        this.length = i2;
    }

    public AstNode getExpression() {
        return this.expression;
    }

    public void setExpression(AstNode astNode) {
        assertNotNull(astNode);
        this.expression = astNode;
        astNode.setParent(this);
    }

    public List<SwitchCase> getCases() {
        List<SwitchCase> list = this.cases;
        return list != null ? list : NO_CASES;
    }

    public void setCases(List<SwitchCase> list) {
        if (list == null) {
            this.cases = null;
            return;
        }
        List<SwitchCase> list2 = this.cases;
        if (list2 != null) {
            list2.clear();
        }
        Iterator<SwitchCase> it = list.iterator();
        while (it.hasNext()) {
            addCase(it.next());
        }
    }

    public void addCase(SwitchCase switchCase) {
        assertNotNull(switchCase);
        if (this.cases == null) {
            this.cases = new ArrayList();
        }
        this.cases.add(switchCase);
        switchCase.setParent(this);
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

    @Override // org.mozilla.javascript.ast.Jump, org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        String strMakeIndent = makeIndent(i);
        StringBuilder sb = new StringBuilder();
        sb.append(strMakeIndent);
        sb.append("switch (");
        sb.append(this.expression.toSource(0));
        sb.append(") {\n");
        List<SwitchCase> list = this.cases;
        if (list != null) {
            Iterator<SwitchCase> it = list.iterator();
            while (it.hasNext()) {
                sb.append(it.next().toSource(i + 1));
            }
        }
        sb.append(strMakeIndent);
        sb.append("}\n");
        return sb.toString();
    }

    @Override // org.mozilla.javascript.ast.Jump, org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.expression.visit(nodeVisitor);
            Iterator<SwitchCase> it = getCases().iterator();
            while (it.hasNext()) {
                it.next().visit(nodeVisitor);
            }
        }
    }
}
