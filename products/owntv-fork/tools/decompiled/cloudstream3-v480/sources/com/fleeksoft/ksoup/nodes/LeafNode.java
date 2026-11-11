package com.fleeksoft.ksoup.nodes;

import com.fleeksoft.ksoup.internal.QuietAppendable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: LeafNode.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\t\b\u0016¢\u0006\u0004\b\u0002\u0010\u0003B\u0011\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0002\u0010\u0006J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\u0006\u0010\u0004\u001a\u00020\u0005J\n\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0005H\u0016J\u0010\u0010\u0004\u001a\u00020\u00122\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005J\u0010\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0005H\u0016J\u001a\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00052\b\u0010\u0018\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0005H\u0016J\u0010\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0005H\u0016J\u0010\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0005H\u0016J\b\u0010\u001c\u001a\u00020\u0005H\u0016J\u0012\u0010\u001d\u001a\u00020\u00122\b\u0010\u001c\u001a\u0004\u0018\u00010\u0005H\u0014J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\u0001H\u0016J\u000e\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00010\"H\u0016J\u001d\u0010#\u001a\u00020\u00122\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0010¢\u0006\u0002\b(J\u0012\u0010)\u001a\u00020\u00002\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u0014R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006*"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/LeafNode;", "Lcom/fleeksoft/ksoup/nodes/Node;", "<init>", "()V", "coreValue", "", "(Ljava/lang/String;)V", ES6Iterator.VALUE_PROPERTY, "", "getValue$ksoup_release", "()Ljava/lang/Object;", "setValue$ksoup_release", "(Ljava/lang/Object;)V", "hasAttributes", "", "attributes", "Lcom/fleeksoft/ksoup/nodes/Attributes;", "ensureAttributes", "", "parent", "Lcom/fleeksoft/ksoup/nodes/Element;", "nodeValue", "attr", "attributeKey", "attributeValue", "hasAttr", "removeAttr", "absUrl", "baseUri", "doSetBaseUri", "childNodeSize", "", "empty", "ensureChildNodes", "", "outerHtmlTail", "accum", "Lcom/fleeksoft/ksoup/internal/QuietAppendable;", "out", "Lcom/fleeksoft/ksoup/nodes/Document$OutputSettings;", "outerHtmlTail$ksoup_release", "doClone", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class LeafNode extends Node {
    private Object value;

    @Override // com.fleeksoft.ksoup.nodes.Node
    public int childNodeSize() {
        return 0;
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    protected void doSetBaseUri(String baseUri) {
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public void outerHtmlTail$ksoup_release(QuietAppendable accum, Document.OutputSettings out) {
        Intrinsics.checkNotNullParameter(accum, "accum");
        Intrinsics.checkNotNullParameter(out, "out");
    }

    /* JADX INFO: renamed from: getValue$ksoup_release, reason: from getter */
    public final Object getValue() {
        return this.value;
    }

    public final void setValue$ksoup_release(Object obj) {
        this.value = obj;
    }

    public LeafNode() {
        this.value = "";
    }

    public LeafNode(String coreValue) {
        Intrinsics.checkNotNullParameter(coreValue, "coreValue");
        this.value = coreValue;
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public boolean hasAttributes() {
        return this.value instanceof Attributes;
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public Attributes attributes() {
        ensureAttributes();
        Object obj = this.value;
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.fleeksoft.ksoup.nodes.Attributes");
        return (Attributes) obj;
    }

    private final void ensureAttributes() {
        if (hasAttributes()) {
            return;
        }
        Object obj = this.value;
        String str = obj instanceof String ? (String) obj : null;
        Attributes attributes = new Attributes();
        this.value = attributes;
        attributes.put(nodeName(), str);
    }

    public final String coreValue() {
        return attr(nodeName());
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public Element parent() {
        return get_parentNode();
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public String nodeValue() {
        return coreValue();
    }

    public final void coreValue(String value) {
        attr(nodeName(), value);
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public String attr(String attributeKey) {
        Intrinsics.checkNotNullParameter(attributeKey, "attributeKey");
        if (!hasAttributes()) {
            if (!Intrinsics.areEqual(nodeName(), attributeKey)) {
                return "";
            }
            Object obj = this.value;
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
            return (String) obj;
        }
        return super.attr(attributeKey);
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public Node attr(String attributeKey, String attributeValue) {
        Intrinsics.checkNotNullParameter(attributeKey, "attributeKey");
        if (!hasAttributes() && Intrinsics.areEqual(attributeKey, nodeName())) {
            this.value = attributeValue;
        } else {
            ensureAttributes();
            super.attr(attributeKey, attributeValue);
        }
        return this;
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public boolean hasAttr(String attributeKey) {
        Intrinsics.checkNotNullParameter(attributeKey, "attributeKey");
        ensureAttributes();
        return super.hasAttr(attributeKey);
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public Node removeAttr(String attributeKey) {
        Intrinsics.checkNotNullParameter(attributeKey, "attributeKey");
        ensureAttributes();
        return super.removeAttr(attributeKey);
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public String absUrl(String attributeKey) {
        Intrinsics.checkNotNullParameter(attributeKey, "attributeKey");
        ensureAttributes();
        return super.absUrl(attributeKey);
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public String baseUri() {
        if (get_parentNode() == null) {
            return "";
        }
        Element element = get_parentNode();
        Intrinsics.checkNotNull(element);
        return element.baseUri();
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public Node empty() {
        return this;
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public List<Node> ensureChildNodes() {
        return Node.INSTANCE.getEmptyNodes();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fleeksoft.ksoup.nodes.Node
    public LeafNode doClone(Node parent) {
        Node nodeDoClone = super.doClone(parent);
        Intrinsics.checkNotNull(nodeDoClone, "null cannot be cast to non-null type com.fleeksoft.ksoup.nodes.LeafNode");
        LeafNode leafNode = (LeafNode) nodeDoClone;
        if (hasAttributes()) {
            Attributes attributes = (Attributes) this.value;
            Intrinsics.checkNotNull(attributes);
            leafNode.value = attributes.clone2();
        }
        return leafNode;
    }
}
