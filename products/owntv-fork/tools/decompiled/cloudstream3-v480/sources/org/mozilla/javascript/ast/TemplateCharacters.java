package org.mozilla.javascript.ast;

/* JADX INFO: loaded from: classes8.dex */
public class TemplateCharacters extends AstNode {
    private String rawValue;
    private String value;

    public TemplateCharacters() {
        this.type = 181;
    }

    public TemplateCharacters(int i) {
        super(i);
        this.type = 181;
    }

    public TemplateCharacters(int i, int i2) {
        super(i, i2);
        this.type = 181;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public String getRawValue() {
        return this.rawValue;
    }

    public void setRawValue(String str) {
        assertNotNull(str);
        this.rawValue = str;
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        return makeIndent(i) + this.rawValue;
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
    }
}
