package org.mozilla.javascript.ast;

import org.mozilla.javascript.Node;

/* JADX INFO: loaded from: classes8.dex */
public class Jump extends AstNode {
    private Jump jumpNode;
    public Node target;
    private Node target2;

    public Jump() {
        this.type = -1;
    }

    public Jump(int i) {
        this.type = i;
    }

    public Jump(int i, Node node) {
        this(i);
        addChildToBack(node);
    }

    public Jump getJumpStatement() {
        if (this.type != 133 && this.type != 134) {
            codeBug();
        }
        return this.jumpNode;
    }

    public void setJumpStatement(Jump jump) {
        if (this.type != 133 && this.type != 134) {
            codeBug();
        }
        if (jump == null) {
            codeBug();
        }
        if (this.jumpNode != null) {
            codeBug();
        }
        this.jumpNode = jump;
    }

    public Node getDefault() {
        if (this.type != 127) {
            codeBug();
        }
        return this.target2;
    }

    public void setDefault(Node node) {
        if (this.type != 127) {
            codeBug();
        }
        if (node.getType() != 145) {
            codeBug();
        }
        if (this.target2 != null) {
            codeBug();
        }
        this.target2 = node;
    }

    public Node getFinally() {
        if (this.type != 90) {
            codeBug();
        }
        return this.target2;
    }

    public void setFinally(Node node) {
        if (this.type != 90) {
            codeBug();
        }
        if (node.getType() != 145) {
            codeBug();
        }
        if (this.target2 != null) {
            codeBug();
        }
        this.target2 = node;
    }

    public Jump getLoop() {
        if (this.type != 144) {
            codeBug();
        }
        return this.jumpNode;
    }

    public void setLoop(Jump jump) {
        if (this.type != 144) {
            codeBug();
        }
        if (jump == null) {
            codeBug();
        }
        if (this.jumpNode != null) {
            codeBug();
        }
        this.jumpNode = jump;
    }

    public Node getContinue() {
        if (this.type != 146) {
            codeBug();
        }
        return this.target2;
    }

    public void setContinue(Node node) {
        if (this.type != 146) {
            codeBug();
        }
        if (node.getType() != 145) {
            codeBug();
        }
        if (this.target2 != null) {
            codeBug();
        }
        this.target2 = node;
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        throw new UnsupportedOperationException(toString());
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int i) {
        throw new UnsupportedOperationException(toString());
    }
}
