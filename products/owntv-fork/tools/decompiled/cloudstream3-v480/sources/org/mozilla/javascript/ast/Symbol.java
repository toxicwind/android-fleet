package org.mozilla.javascript.ast;

import org.mozilla.javascript.Node;
import org.mozilla.javascript.Token;

/* JADX INFO: loaded from: classes8.dex */
public class Symbol {
    private Scope containingTable;
    private int declType;
    private int index = -1;
    private String name;
    private Node node;

    public Symbol() {
    }

    public Symbol(int i, String str) {
        setName(str);
        setDeclType(i);
    }

    public int getDeclType() {
        return this.declType;
    }

    public void setDeclType(int i) {
        if (i != 122 && i != 96 && i != 135 && i != 167 && i != 168) {
            throw new IllegalArgumentException("Invalid declType: " + i);
        }
        this.declType = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Node getNode() {
        return this.node;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Scope getContainingTable() {
        return this.containingTable;
    }

    public void setContainingTable(Scope scope) {
        this.containingTable = scope;
    }

    public String getDeclTypeName() {
        return Token.typeToName(this.declType);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Symbol (");
        sb.append(getDeclTypeName());
        sb.append(") name=");
        sb.append(this.name);
        if (this.node != null) {
            sb.append(" line=");
            sb.append(this.node.getLineno());
        }
        return sb.toString();
    }
}
