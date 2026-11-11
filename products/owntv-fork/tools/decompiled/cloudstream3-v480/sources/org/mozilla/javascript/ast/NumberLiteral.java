package org.mozilla.javascript.ast;

/* JADX INFO: loaded from: classes8.dex */
public class NumberLiteral extends AstNode {
    private double number;
    private String value;

    public NumberLiteral() {
        this.type = 45;
    }

    public NumberLiteral(int i) {
        super(i);
        this.type = 45;
    }

    public NumberLiteral(int i, int i2) {
        super(i, i2);
        this.type = 45;
    }

    public NumberLiteral(int i, String str) {
        super(i);
        this.type = 45;
        setValue(str);
        setLength(str.length());
    }

    public NumberLiteral(int i, String str, double d) {
        this(i, str);
        setDouble(d);
    }

    public NumberLiteral(double d) {
        this.type = 45;
        setDouble(d);
        setValue(Double.toString(d));
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        assertNotNull(str);
        this.value = str;
    }

    public double getNumber() {
        return this.number;
    }

    public void setNumber(double d) {
        this.number = d;
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        String strMakeIndent = makeIndent(i);
        String str = this.value;
        if (str == null) {
            str = "<null>";
        }
        return strMakeIndent + str;
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
    }
}
