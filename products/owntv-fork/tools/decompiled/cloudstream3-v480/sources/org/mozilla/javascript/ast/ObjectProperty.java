package org.mozilla.javascript.ast;

/* JADX INFO: loaded from: classes8.dex */
public class ObjectProperty extends InfixExpression {
    private boolean shorthand;

    public void setNodeType(int i) {
        if (i != 116 && i != 165 && i != 166 && i != 177) {
            throw new IllegalArgumentException("invalid node type: " + i);
        }
        setType(i);
    }

    public ObjectProperty() {
        this.type = 116;
    }

    public ObjectProperty(int i) {
        super(i);
        this.type = 116;
    }

    public ObjectProperty(int i, int i2) {
        super(i, i2);
        this.type = 116;
    }

    public void setIsGetterMethod() {
        this.type = 165;
    }

    public boolean isGetterMethod() {
        return this.type == 165;
    }

    public void setIsSetterMethod() {
        this.type = 166;
    }

    public boolean isSetterMethod() {
        return this.type == 166;
    }

    public void setIsNormalMethod() {
        this.type = 177;
    }

    public boolean isNormalMethod() {
        return this.type == 177;
    }

    public boolean isMethod() {
        return isGetterMethod() || isSetterMethod() || isNormalMethod();
    }

    public void setIsShorthand(boolean z) {
        this.shorthand = z;
    }

    public boolean isShorthand() {
        return this.shorthand;
    }

    @Override // org.mozilla.javascript.ast.InfixExpression, org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        StringBuilder sb = new StringBuilder();
        int i2 = i + 1;
        sb.append(makeIndent(i2));
        if (isGetterMethod()) {
            sb.append("get ");
        } else if (isSetterMethod()) {
            sb.append("set ");
        }
        AstNode astNode = this.left;
        if (getType() == 116) {
            i = 0;
        }
        sb.append(astNode.toSource(i));
        if (!this.shorthand) {
            if (this.type == 116) {
                sb.append(": ");
            }
            AstNode astNode2 = this.right;
            if (getType() == 116) {
                i2 = 0;
            }
            sb.append(astNode2.toSource(i2));
        }
        return sb.toString();
    }
}
