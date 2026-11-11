package org.mozilla.javascript.ast;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: classes8.dex */
public class KeywordLiteral extends AstNode {
    public KeywordLiteral() {
    }

    public KeywordLiteral(int i) {
        super(i);
    }

    public KeywordLiteral(int i, int i2) {
        super(i, i2);
    }

    public KeywordLiteral(int i, int i2, int i3) {
        super(i, i2);
        setType(i3);
    }

    @Override // org.mozilla.javascript.Node
    public KeywordLiteral setType(int i) {
        if (i != 48 && i != 79 && i != 47 && i != 50 && i != 49 && i != 174) {
            throw new IllegalArgumentException("Invalid node type: " + i);
        }
        this.type = i;
        return this;
    }

    public boolean isBooleanLiteral() {
        return this.type == 50 || this.type == 49;
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(i));
        int type = getType();
        if (type == 79) {
            sb.append("super");
        } else if (type != 174) {
            switch (type) {
                case 47:
                    sb.append(AbstractJsonLexerKt.NULL);
                    break;
                case 48:
                    sb.append("this");
                    break;
                case 49:
                    sb.append("false");
                    break;
                case 50:
                    sb.append("true");
                    break;
                default:
                    throw new IllegalStateException("Invalid keyword literal type: " + getType());
            }
        } else {
            sb.append("debugger;\n");
        }
        return sb.toString();
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
    }
}
