package org.mozilla.javascript.ast;

import org.mozilla.javascript.ScriptRuntime;

/* JADX INFO: loaded from: classes8.dex */
public class StringLiteral extends AstNode {
    private char quoteChar;
    private String value;

    public StringLiteral() {
        this.type = 46;
    }

    public StringLiteral(int i) {
        super(i);
        this.type = 46;
    }

    public StringLiteral(int i, int i2) {
        super(i, i2);
        this.type = 46;
    }

    public String getValue() {
        return this.value;
    }

    public String getValue(boolean z) {
        if (!z) {
            return this.value;
        }
        char c = this.quoteChar;
        return c + this.value + c;
    }

    public void setValue(String str) {
        assertNotNull(str);
        this.value = str;
    }

    public char getQuoteCharacter() {
        return this.quoteChar;
    }

    public void setQuoteCharacter(char c) {
        this.quoteChar = c;
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        String strMakeIndent = makeIndent(i);
        char c = this.quoteChar;
        return strMakeIndent + c + ScriptRuntime.escapeString(this.value, c) + this.quoteChar;
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
    }
}
