package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.databind.node.ContainerNode;

/* JADX WARN: Unexpected interfaces in signature: [java.lang.Object] */
/* JADX INFO: loaded from: classes.dex */
public abstract class ContainerNode<T extends ContainerNode<T>> extends BaseJsonNode {
    private static final long serialVersionUID = 1;
    protected final JsonNodeFactory _nodeFactory;

    @Override // com.fasterxml.jackson.databind.JsonNode
    public String asText() {
        return "";
    }

    protected ContainerNode(JsonNodeFactory jsonNodeFactory) {
        this._nodeFactory = jsonNodeFactory;
    }

    protected ContainerNode() {
        this._nodeFactory = null;
    }

    public final BooleanNode booleanNode(boolean z) {
        return this._nodeFactory.booleanNode(z);
    }

    public final NullNode nullNode() {
        return this._nodeFactory.nullNode();
    }

    public final ArrayNode arrayNode() {
        return this._nodeFactory.arrayNode();
    }

    public final NumericNode numberNode(int i) {
        return this._nodeFactory.numberNode(i);
    }

    public final TextNode textNode(String str) {
        return this._nodeFactory.textNode(str);
    }
}
