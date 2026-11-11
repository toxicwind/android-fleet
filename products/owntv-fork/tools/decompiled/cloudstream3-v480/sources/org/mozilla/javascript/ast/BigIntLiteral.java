package org.mozilla.javascript.ast;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes8.dex */
public class BigIntLiteral extends AstNode {
    private BigInteger bigInt;
    private String value;

    public BigIntLiteral() {
        this.type = 89;
    }

    public BigIntLiteral(int i) {
        super(i);
        this.type = 89;
    }

    public BigIntLiteral(int i, int i2) {
        super(i, i2);
        this.type = 89;
    }

    public BigIntLiteral(int i, String str) {
        super(i);
        this.type = 89;
        setValue(str);
        setLength(str.length());
    }

    public BigIntLiteral(int i, String str, BigInteger bigInteger) {
        this(i, str);
        setBigInt(bigInteger);
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        assertNotNull(str);
        this.value = str;
    }

    @Override // org.mozilla.javascript.Node
    public BigInteger getBigInt() {
        return this.bigInt;
    }

    @Override // org.mozilla.javascript.Node
    public void setBigInt(BigInteger bigInteger) {
        this.bigInt = bigInteger;
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        String str;
        String strMakeIndent = makeIndent(i);
        BigInteger bigInteger = this.bigInt;
        if (bigInteger == null) {
            str = "<null>";
        } else {
            str = bigInteger.toString() + "n";
        }
        return strMakeIndent + str;
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
    }
}
