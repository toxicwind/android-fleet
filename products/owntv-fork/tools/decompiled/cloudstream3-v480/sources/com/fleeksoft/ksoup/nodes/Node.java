package com.fleeksoft.ksoup.nodes;

import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.fleeksoft.ksoup.helper.Validate;
import com.fleeksoft.ksoup.internal.QuietAppendable;
import com.fleeksoft.ksoup.internal.StringUtil;
import com.fleeksoft.ksoup.parser.ParseSettings;
import com.fleeksoft.ksoup.parser.Parser;
import com.fleeksoft.ksoup.ported.Consumer;
import com.fleeksoft.ksoup.ported.KCloneable;
import com.fleeksoft.ksoup.select.NodeFilter;
import com.fleeksoft.ksoup.select.NodeVisitor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.sequences.Sequence;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Node.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\n\b'\u0018\u0000 \u0081\u00012\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002\u0081\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0010\u001a\u00020\u0011H&J\b\u0010\u0012\u001a\u00020\u0011H\u0016J\b\u0010\u0013\u001a\u00020\u0011H\u0016J\u0012\u0010\u0014\u001a\u00020\u00152\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0007J\u000e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u0011J\u0016\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0011J\b\u0010\u0019\u001a\u00020\u0015H&J\u0006\u0010\u001a\u001a\u00020\u0015J\u0010\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u0011H\u0016J\b\u0010\u001d\u001a\u00020\u001eH&J\u0006\u0010\u001f\u001a\u00020\u000bJ\u001a\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u00112\b\u0010 \u001a\u0004\u0018\u00010\u0011H\u0017J\u0010\u0010!\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u0011H\u0016J\u0010\u0010\"\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u0011H\u0016J\b\u0010#\u001a\u00020\u0000H\u0016J\b\u0010$\u001a\u00020\u0011H&J\u0012\u0010%\u001a\u00020&2\b\u0010$\u001a\u0004\u0018\u00010\u0011H$J\u000e\u0010'\u001a\u00020&2\u0006\u0010$\u001a\u00020\u0011J\u0010\u0010(\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u0011H\u0016J\u000e\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00000*H$J\u000e\u0010+\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\u000bJ\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00000.J\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00000.J\b\u00100\u001a\u00020\u000bH&J\u0013\u00101\u001a\b\u0012\u0004\u0012\u00020\u000002H\u0004¢\u0006\u0002\u00103J\n\u00104\u001a\u0004\u0018\u00010\u0000H&J\n\u00105\u001a\u0004\u0018\u00010\u0000H\u0016J\b\u00106\u001a\u0004\u0018\u00010\u0005J\b\u00107\u001a\u0004\u0018\u00010\u0000J\b\u00108\u001a\u00020\u0000H\u0016J\b\u00109\u001a\u0004\u0018\u00010:J\u0006\u0010;\u001a\u00020&J\u0010\u0010<\u001a\u00020\u00002\u0006\u0010=\u001a\u00020\u0011H\u0016J\u0010\u0010<\u001a\u00020\u00002\u0006\u0010>\u001a\u00020\u0000H\u0017J\u0010\u0010?\u001a\u00020\u00002\u0006\u0010=\u001a\u00020\u0011H\u0016J\u0010\u0010?\u001a\u00020\u00002\u0006\u0010>\u001a\u00020\u0000H\u0017J\u0018\u0010@\u001a\u00020&2\u0006\u0010,\u001a\u00020\u000b2\u0006\u0010=\u001a\u00020\u0011H\u0002J\u0010\u0010A\u001a\u00020\u00002\u0006\u0010=\u001a\u00020\u0011H\u0016J\b\u0010B\u001a\u0004\u0018\u00010\u0000J\u000e\u0010C\u001a\u00020&2\u0006\u0010D\u001a\u00020\u0000J\u0010\u0010E\u001a\u00020&2\u0006\u00107\u001a\u00020\u0000H\u0004J\u0016\u0010F\u001a\u00020&2\u0006\u0010G\u001a\u00020\u00002\u0006\u0010D\u001a\u00020\u0000J\u0010\u0010H\u001a\u00020&2\u0006\u0010G\u001a\u00020\u0000H\u0014J\u001f\u0010I\u001a\u00020&2\u0012\u0010J\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000002\"\u00020\u0000¢\u0006\u0002\u0010KJ)\u0010I\u001a\u00020&2\u0006\u0010,\u001a\u00020\u000b2\u0012\u0010J\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000002\"\u00020\u0000H\u0007¢\u0006\u0002\u0010LJ\u0010\u0010M\u001a\u00020&2\u0006\u0010N\u001a\u00020\u0000H\u0004J\f\u0010O\u001a\b\u0012\u0004\u0012\u00020\u00000.J\b\u0010P\u001a\u0004\u0018\u00010\u0000J\b\u0010Q\u001a\u0004\u0018\u00010\u0000J\u0006\u0010R\u001a\u00020\u000bJ\b\u0010S\u001a\u0004\u0018\u00010\u0000J\b\u0010T\u001a\u0004\u0018\u00010\u0000J\b\u0010U\u001a\u0004\u0018\u00010\u0000J\b\u0010V\u001a\u0004\u0018\u00010\u0000J\n\u0010W\u001a\u0004\u0018\u00010\u0005H\u0016J\n\u0010X\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010Y\u001a\u00020\u00002\u0006\u0010Z\u001a\u00020[H\u0016J\u0018\u0010\\\u001a\u00020\u00002\u000e\u0010]\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00000^H\u0016J\u0010\u0010_\u001a\u00020\u00002\u0006\u0010`\u001a\u00020aH\u0016J\f\u0010b\u001a\b\u0012\u0004\u0012\u00020\u00000cJ&\u0010b\u001a\b\u0012\u0004\u0012\u0002Hd0c\"\b\b\u0000\u0010d*\u00020\u00002\f\u0010e\u001a\b\u0012\u0004\u0012\u0002Hd0fH\u0007J\b\u0010g\u001a\u00020\u0011H\u0016J\u0014\u0010g\u001a\u00020&2\n\u0010h\u001a\u00060ij\u0002`jH\u0007J\u0010\u0010g\u001a\u00020&2\u0006\u0010h\u001a\u00020kH\u0007J\u001d\u0010l\u001a\u00020&2\u0006\u0010h\u001a\u00020k2\u0006\u0010G\u001a\u00020mH ¢\u0006\u0002\bnJ\u001d\u0010o\u001a\u00020&2\u0006\u0010h\u001a\u00020k2\u0006\u0010G\u001a\u00020mH ¢\u0006\u0002\bpJ#\u0010=\u001a\u0002Hd\"\f\b\u0000\u0010d*\u00060ij\u0002`j2\u0006\u0010q\u001a\u0002HdH\u0017¢\u0006\u0002\u0010rJ\u0006\u0010s\u001a\u00020tJ\n\u0010u\u001a\u00020\u0011H\u0096\u0080\u0004J\u0014\u0010v\u001a\u00020\u00152\b\u0010w\u001a\u0004\u0018\u00010xH\u0096\u0082\u0004J\n\u0010y\u001a\u00020\u000bH\u0096\u0080\u0004J\u0010\u0010z\u001a\u00020\u00152\b\u0010{\u001a\u0004\u0018\u00010xJ\r\u0010|\u001a\u00020\u0000H ¢\u0006\u0002\b}J\b\u0010~\u001a\u00020\u0000H\u0016J\b\u0010\u007f\u001a\u00020\u0000H\u0016J\u0013\u0010\u0080\u0001\u001a\u00020\u00002\b\u00105\u001a\u0004\u0018\u00010\u0000H\u0014R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0082\u0001"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Node;", "Lcom/fleeksoft/ksoup/ported/KCloneable;", "<init>", "()V", "_parentNode", "Lcom/fleeksoft/ksoup/nodes/Element;", "get_parentNode", "()Lcom/fleeksoft/ksoup/nodes/Element;", "set_parentNode", "(Lcom/fleeksoft/ksoup/nodes/Element;)V", "_siblingIndex", "", "get_siblingIndex", "()I", "set_siblingIndex", "(I)V", "nodeName", "", "normalName", "nodeValue", "nameIs", "", "parentNameIs", "parentElementIs", "namespace", "hasAttributes", "hasParent", "attr", "attributeKey", "attributes", "Lcom/fleeksoft/ksoup/nodes/Attributes;", "attributesSize", "attributeValue", "hasAttr", "removeAttr", "clearAttributes", "baseUri", "doSetBaseUri", "", "setBaseUri", "absUrl", "ensureChildNodes", "", "childNode", "index", "childNodes", "", "childNodesCopy", "childNodeSize", "childNodesAsArray", "", "()[Lcom/fleeksoft/ksoup/nodes/Node;", "empty", "parent", "parentElement", "parentNode", "root", "ownerDocument", "Lcom/fleeksoft/ksoup/nodes/Document;", "remove", TtmlNode.ANNOTATION_POSITION_BEFORE, "html", "node", TtmlNode.ANNOTATION_POSITION_AFTER, "addSiblingHtml", "wrap", "unwrap", "replaceWith", "inNode", "setParentNode", "replaceChild", "out", "removeChild", "addChildren", "children", "([Lcom/fleeksoft/ksoup/nodes/Node;)V", "(I[Lcom/fleeksoft/ksoup/nodes/Node;)V", "reparentChild", "child", "siblingNodes", "nextSibling", "previousSibling", "siblingIndex", "firstChild", "lastChild", "firstSibling", "lastSibling", "nextElementSibling", "previousElementSibling", "traverse", "nodeVisitor", "Lcom/fleeksoft/ksoup/select/NodeVisitor;", "forEachNode", "action", "Lcom/fleeksoft/ksoup/ported/Consumer;", "filter", "nodeFilter", "Lcom/fleeksoft/ksoup/select/NodeFilter;", "nodeStream", "Lkotlin/sequences/Sequence;", ExifInterface.GPS_DIRECTION_TRUE, "type", "Lkotlin/reflect/KClass;", "outerHtml", "accum", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "Lcom/fleeksoft/ksoup/internal/QuietAppendable;", "outerHtmlHead", "Lcom/fleeksoft/ksoup/nodes/Document$OutputSettings;", "outerHtmlHead$ksoup_release", "outerHtmlTail", "outerHtmlTail$ksoup_release", "appendable", "(Ljava/lang/Appendable;)Ljava/lang/Appendable;", "sourceRange", "Lcom/fleeksoft/ksoup/nodes/Range;", "toString", "equals", "other", "", "hashCode", "hasSameValue", "o", "createClone", "createClone$ksoup_release", "clone", "shallowClone", "doClone", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class Node implements KCloneable<Node> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final List<Node> EmptyNodes = new ArrayList();
    public static final String EmptyString = "";
    private Element _parentNode;
    private int _siblingIndex;

    public abstract Attributes attributes();

    public abstract String baseUri();

    public abstract int childNodeSize();

    public abstract Node createClone$ksoup_release();

    protected abstract void doSetBaseUri(String baseUri);

    public abstract Node empty();

    protected abstract List<Node> ensureChildNodes();

    public boolean equals(Object other) {
        return this == other;
    }

    public abstract boolean hasAttributes();

    public abstract String nodeName();

    public abstract void outerHtmlHead$ksoup_release(QuietAppendable accum, Document.OutputSettings out);

    public abstract void outerHtmlTail$ksoup_release(QuietAppendable accum, Document.OutputSettings out);

    protected Node() {
    }

    public final Element get_parentNode() {
        return this._parentNode;
    }

    public final void set_parentNode(Element element) {
        this._parentNode = element;
    }

    public final int get_siblingIndex() {
        return this._siblingIndex;
    }

    public final void set_siblingIndex(int i) {
        this._siblingIndex = i;
    }

    public String normalName() {
        return nodeName();
    }

    public String nodeValue() {
        return "";
    }

    public final boolean nameIs(String normalName) {
        return Intrinsics.areEqual(normalName(), normalName);
    }

    public final boolean parentNameIs(String normalName) {
        Intrinsics.checkNotNullParameter(normalName, "normalName");
        Element element = this._parentNode;
        if (element == null) {
            return false;
        }
        Intrinsics.checkNotNull(element);
        return Intrinsics.areEqual(element.normalName(), normalName);
    }

    public final boolean parentElementIs(String normalName, String namespace) {
        Intrinsics.checkNotNullParameter(normalName, "normalName");
        Intrinsics.checkNotNullParameter(namespace, "namespace");
        Element element = this._parentNode;
        if (element == null || element == null) {
            return false;
        }
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type com.fleeksoft.ksoup.nodes.Element");
        return element.elementIs(normalName, namespace);
    }

    public final boolean hasParent() {
        return this._parentNode != null;
    }

    public String attr(String attributeKey) {
        Intrinsics.checkNotNullParameter(attributeKey, "attributeKey");
        if (!hasAttributes()) {
            return "";
        }
        String ignoreCase = attributes().getIgnoreCase(attributeKey);
        if (ignoreCase.length() > 0) {
            return ignoreCase;
        }
        if (!StringsKt.startsWith$default(attributeKey, "abs:", false, 2, (Object) null)) {
            return "";
        }
        String strSubstring = attributeKey.substring(4);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return absUrl(strSubstring);
    }

    public final int attributesSize() {
        if (hasAttributes()) {
            return attributes().size();
        }
        return 0;
    }

    public Node attr(String attributeKey, String attributeValue) {
        ParseSettings htmlDefault;
        Intrinsics.checkNotNullParameter(attributeKey, "attributeKey");
        Document documentOwnerDocument = ownerDocument();
        if (documentOwnerDocument != null) {
            Parser parser = documentOwnerDocument.getParser();
            htmlDefault = parser != null ? parser.getSettings() : null;
        } else {
            htmlDefault = ParseSettings.INSTANCE.getHtmlDefault();
        }
        Intrinsics.checkNotNull(htmlDefault);
        attributes().putIgnoreCase(htmlDefault.normalizeAttribute(attributeKey), attributeValue);
        return this;
    }

    public boolean hasAttr(String attributeKey) {
        Intrinsics.checkNotNullParameter(attributeKey, "attributeKey");
        if (!hasAttributes()) {
            return false;
        }
        if (StringsKt.startsWith$default(attributeKey, "abs:", false, 2, (Object) null)) {
            String strSubstring = attributeKey.substring(4);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            if (attributes().hasKeyIgnoreCase(strSubstring) && absUrl(strSubstring).length() > 0) {
                return true;
            }
        }
        return attributes().hasKeyIgnoreCase(attributeKey);
    }

    public Node removeAttr(String attributeKey) {
        Intrinsics.checkNotNullParameter(attributeKey, "attributeKey");
        if (hasAttributes()) {
            attributes().removeIgnoreCase(attributeKey);
        }
        return this;
    }

    public Node clearAttributes() {
        if (hasAttributes()) {
            Iterator<Attribute> it = attributes().iterator();
            while (it.hasNext()) {
                it.next();
                it.remove();
            }
        }
        return this;
    }

    public final void setBaseUri(String baseUri) {
        Intrinsics.checkNotNullParameter(baseUri, "baseUri");
        doSetBaseUri(baseUri);
    }

    public String absUrl(String attributeKey) {
        Intrinsics.checkNotNullParameter(attributeKey, "attributeKey");
        Validate.INSTANCE.notEmpty(attributeKey);
        if (!hasAttributes() || !attributes().hasKeyIgnoreCase(attributeKey)) {
            return "";
        }
        return StringUtil.INSTANCE.resolve(baseUri(), attributes().getIgnoreCase(attributeKey));
    }

    public final Node childNode(int index) {
        return ensureChildNodes().get(index);
    }

    public final List<Node> childNodes() {
        if (childNodeSize() == 0) {
            return EmptyNodes;
        }
        List<Node> listEnsureChildNodes = ensureChildNodes();
        ArrayList arrayList = new ArrayList(listEnsureChildNodes.size());
        arrayList.addAll(listEnsureChildNodes);
        return arrayList;
    }

    public final List<Node> childNodesCopy() {
        List<Node> listEnsureChildNodes = ensureChildNodes();
        ArrayList arrayList = new ArrayList(listEnsureChildNodes.size());
        Iterator<Node> it = listEnsureChildNodes.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().clone2());
        }
        return arrayList;
    }

    protected final Node[] childNodesAsArray() {
        return (Node[]) ensureChildNodes().toArray(new Node[0]);
    }

    public Node parent() {
        return this._parentNode;
    }

    public final Element parentElement() {
        return this._parentNode;
    }

    public final Node parentNode() {
        return this._parentNode;
    }

    public Node root() {
        while (true) {
            Element element = this._parentNode;
            if (element == null) {
                return this;
            }
            Intrinsics.checkNotNull(element);
            this = element;
        }
    }

    public final Document ownerDocument() {
        while (this != null) {
            if (this instanceof Document) {
                return (Document) this;
            }
            this = this._parentNode;
        }
        return null;
    }

    public final void remove() {
        Element element = this._parentNode;
        if (element != null) {
            Intrinsics.checkNotNull(element);
            element.removeChild(this);
        }
    }

    public Node before(String html) {
        Intrinsics.checkNotNullParameter(html, "html");
        addSiblingHtml(siblingIndex(), html);
        return this;
    }

    public Node before(Node node) {
        Intrinsics.checkNotNullParameter(node, "node");
        if (node.parentNode() == parentNode()) {
            node.remove();
        }
        Node nodeParentNode = parentNode();
        if (nodeParentNode != null) {
            nodeParentNode.addChildren(siblingIndex(), node);
        }
        return this;
    }

    public Node after(String html) {
        Intrinsics.checkNotNullParameter(html, "html");
        addSiblingHtml(siblingIndex() + 1, html);
        return this;
    }

    public Node after(Node node) {
        Intrinsics.checkNotNullParameter(node, "node");
        if (node.parentNode() == parentNode()) {
            node.remove();
        }
        Node nodeParentNode = parentNode();
        Intrinsics.checkNotNull(nodeParentNode);
        nodeParentNode.addChildren(siblingIndex() + 1, node);
        return this;
    }

    private final void addSiblingHtml(int index, String html) {
        Element element = this._parentNode;
        if (element == null) {
            element = null;
        }
        List<Node> fragmentInput = NodeUtils.INSTANCE.parser(this).parseFragmentInput(html, element, baseUri());
        Element element2 = this._parentNode;
        if (element2 != null) {
            Node[] nodeArr = (Node[]) fragmentInput.toArray(new Node[0]);
            element2.addChildren(index, (Node[]) Arrays.copyOf(nodeArr, nodeArr.length));
        }
    }

    public Node wrap(String html) {
        Intrinsics.checkNotNullParameter(html, "html");
        Validate.INSTANCE.notEmpty(html);
        Element element = this._parentNode;
        if (element == null || element == null) {
            element = this instanceof Element ? (Element) this : null;
            Intrinsics.checkNotNull(element);
        } else {
            Intrinsics.checkNotNull(element, "null cannot be cast to non-null type com.fleeksoft.ksoup.nodes.Element");
        }
        List<Node> fragmentInput = NodeUtils.INSTANCE.parser(this).parseFragmentInput(html, element, baseUri());
        Node node = fragmentInput.get(0);
        Element element2 = node instanceof Element ? (Element) node : null;
        if (element2 != null) {
            Element deepChild = INSTANCE.getDeepChild(element2);
            Element element3 = this._parentNode;
            if (element3 != null) {
                Intrinsics.checkNotNull(element3);
                element3.replaceChild(this, element2);
            }
            deepChild.addChildren(this);
            List<Node> list = fragmentInput;
            if (!list.isEmpty()) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    Node node2 = fragmentInput.get(i);
                    if (element2 != node2) {
                        Element element4 = node2._parentNode;
                        if (element4 != null) {
                            Intrinsics.checkNotNull(element4);
                            element4.removeChild(node2);
                        }
                        element2.after(node2);
                    }
                }
            }
        }
        return this;
    }

    public final Node unwrap() {
        Node nodeFirstChild = firstChild();
        Element element = this._parentNode;
        Intrinsics.checkNotNull(element);
        int iSiblingIndex = siblingIndex();
        Node[] nodeArrChildNodesAsArray = childNodesAsArray();
        element.addChildren(iSiblingIndex, (Node[]) Arrays.copyOf(nodeArrChildNodesAsArray, nodeArrChildNodesAsArray.length));
        remove();
        return nodeFirstChild;
    }

    public final void replaceWith(Node inNode) {
        Intrinsics.checkNotNullParameter(inNode, "inNode");
        if (this._parentNode == null) {
            this._parentNode = inNode._parentNode;
        }
        Element element = this._parentNode;
        Intrinsics.checkNotNull(element);
        element.replaceChild(this, inNode);
    }

    protected final void setParentNode(Node parentNode) {
        Intrinsics.checkNotNullParameter(parentNode, "parentNode");
        Element element = this._parentNode;
        if (element != null) {
            Intrinsics.checkNotNull(element);
            element.removeChild(this);
        }
        this._parentNode = (Element) parentNode;
    }

    public final void replaceChild(Node out, Node inNode) {
        Intrinsics.checkNotNullParameter(out, "out");
        Intrinsics.checkNotNullParameter(inNode, "inNode");
        Validate.INSTANCE.isTrue(out._parentNode == this);
        if (out == inNode) {
            return;
        }
        Element element = inNode._parentNode;
        if (element != null) {
            Intrinsics.checkNotNull(element);
            element.removeChild(inNode);
        }
        int iSiblingIndex = out.siblingIndex();
        ensureChildNodes().set(iSiblingIndex, inNode);
        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type com.fleeksoft.ksoup.nodes.Element");
        Element element2 = (Element) this;
        inNode._parentNode = element2;
        inNode._siblingIndex = iSiblingIndex;
        out._parentNode = null;
        element2.getChildNodes().incrementMod();
    }

    protected void removeChild(Node out) {
        Intrinsics.checkNotNullParameter(out, "out");
        Validate.INSTANCE.isTrue(out._parentNode == this);
        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type com.fleeksoft.ksoup.nodes.Element");
        Element element = (Element) this;
        if (element.hasValidChildren()) {
            element.ensureChildNodes().remove(out._siblingIndex);
        } else {
            Boolean.valueOf(element.ensureChildNodes().remove(out));
        }
        element.invalidateChildren();
        out._parentNode = null;
    }

    public final void addChildren(Node... children) {
        Intrinsics.checkNotNullParameter(children, "children");
        List<Node> listEnsureChildNodes = ensureChildNodes();
        for (Node node : children) {
            reparentChild(node);
            listEnsureChildNodes.add(node);
            node._siblingIndex = listEnsureChildNodes.size() - 1;
        }
    }

    public final void addChildren(int index, Node... children) {
        Intrinsics.checkNotNullParameter(children, "children");
        if (children.length == 0) {
            return;
        }
        List<Node> listEnsureChildNodes = ensureChildNodes();
        Node nodeParent = children[0].parent();
        if (nodeParent != null && nodeParent.childNodeSize() == children.length) {
            List<Node> listEnsureChildNodes2 = nodeParent.ensureChildNodes();
            int length = children.length;
            while (true) {
                int i = length - 1;
                if (length > 0) {
                    if (!Intrinsics.areEqual(children[i], listEnsureChildNodes2.get(i))) {
                        break;
                    } else {
                        length = i;
                    }
                } else {
                    nodeParent.empty();
                    listEnsureChildNodes.addAll(index, CollectionsKt.listOf(Arrays.copyOf(children, children.length)));
                    int length2 = children.length;
                    while (true) {
                        int i2 = length2 - 1;
                        if (length2 > 0) {
                            Node node = children[i2];
                            Intrinsics.checkNotNull(this, "null cannot be cast to non-null type com.fleeksoft.ksoup.nodes.Element");
                            node._parentNode = (Element) this;
                            length2 = i2;
                        } else {
                            Intrinsics.checkNotNull(this, "null cannot be cast to non-null type com.fleeksoft.ksoup.nodes.Element");
                            ((Element) this).invalidateChildren();
                            return;
                        }
                    }
                }
            }
        }
        for (Node node2 : children) {
            reparentChild(node2);
        }
        listEnsureChildNodes.addAll(index, CollectionsKt.listOf(Arrays.copyOf(children, children.length)));
        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type com.fleeksoft.ksoup.nodes.Element");
        ((Element) this).invalidateChildren();
    }

    protected final void reparentChild(Node child) {
        Intrinsics.checkNotNullParameter(child, "child");
        child.setParentNode(this);
    }

    public final List<Node> siblingNodes() {
        Element element = this._parentNode;
        if (element == null) {
            return CollectionsKt.emptyList();
        }
        Intrinsics.checkNotNull(element);
        List<Node> listEnsureChildNodes = element.ensureChildNodes();
        ArrayList arrayList = new ArrayList(listEnsureChildNodes.size() - 1);
        for (Node node : listEnsureChildNodes) {
            if (!Intrinsics.areEqual(node, this)) {
                arrayList.add(node);
            }
        }
        return arrayList;
    }

    public final Node nextSibling() {
        Element element = this._parentNode;
        if (element == null) {
            return null;
        }
        Intrinsics.checkNotNull(element);
        List<Node> listEnsureChildNodes = element.ensureChildNodes();
        int iSiblingIndex = siblingIndex() + 1;
        if (listEnsureChildNodes.size() > iSiblingIndex) {
            return listEnsureChildNodes.get(iSiblingIndex);
        }
        return null;
    }

    public final Node previousSibling() {
        if (this._parentNode == null || siblingIndex() <= 0) {
            return null;
        }
        Element element = this._parentNode;
        Intrinsics.checkNotNull(element);
        return element.ensureChildNodes().get(this._siblingIndex - 1);
    }

    public final int siblingIndex() {
        Element element = this._parentNode;
        if (element != null) {
            Intrinsics.checkNotNull(element);
            if (!element.getChildNodes().getValidChildren()) {
                Element element2 = this._parentNode;
                Intrinsics.checkNotNull(element2);
                element2.reindexChildren();
            }
        }
        return this._siblingIndex;
    }

    public final Node firstChild() {
        if (childNodeSize() == 0) {
            return null;
        }
        return ensureChildNodes().get(0);
    }

    public final Node lastChild() {
        int iChildNodeSize = childNodeSize();
        if (iChildNodeSize == 0) {
            return null;
        }
        return ensureChildNodes().get(iChildNodeSize - 1);
    }

    public final Node firstSibling() {
        Element element = this._parentNode;
        if (element == null) {
            return this;
        }
        Intrinsics.checkNotNull(element);
        return element.firstChild();
    }

    public final Node lastSibling() {
        Element element = this._parentNode;
        if (element == null) {
            return this;
        }
        Intrinsics.checkNotNull(element);
        return element.lastChild();
    }

    public Element nextElementSibling() {
        do {
            this = this != null ? this.nextSibling() : null;
            if (this == null) {
                return null;
            }
        } while (!(this instanceof Element));
        return (Element) this;
    }

    public Element previousElementSibling() {
        do {
            this = this != null ? this.previousSibling() : null;
            if (this == null) {
                return null;
            }
        } while (!(this instanceof Element));
        return (Element) this;
    }

    public Node traverse(NodeVisitor nodeVisitor) {
        Intrinsics.checkNotNullParameter(nodeVisitor, "nodeVisitor");
        nodeVisitor.traverse(this);
        return this;
    }

    public Node forEachNode(Consumer<? super Node> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        Iterator<Node> it = nodeStream().iterator();
        while (it.hasNext()) {
            action.accept(it.next());
        }
        return this;
    }

    public Node filter(NodeFilter nodeFilter) {
        Intrinsics.checkNotNullParameter(nodeFilter, "nodeFilter");
        nodeFilter.traverse(this);
        return this;
    }

    public final Sequence<Node> nodeStream() {
        return NodeUtils.INSTANCE.stream(this, Reflection.getOrCreateKotlinClass(Node.class));
    }

    public final <T extends Node> Sequence<T> nodeStream(KClass<T> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return NodeUtils.INSTANCE.stream(this, type);
    }

    public String outerHtml() {
        StringBuilder sbBorrowBuilder = StringUtil.INSTANCE.borrowBuilder();
        outerHtml(QuietAppendable.INSTANCE.wrap(sbBorrowBuilder));
        return StringUtil.INSTANCE.releaseBuilder(sbBorrowBuilder);
    }

    public final void outerHtml(Appendable accum) {
        Intrinsics.checkNotNullParameter(accum, "accum");
        outerHtml(QuietAppendable.INSTANCE.wrap(accum));
    }

    public final void outerHtml(QuietAppendable accum) {
        Intrinsics.checkNotNullParameter(accum, "accum");
        Printer.INSTANCE.printerFor(this, accum).traverse(this);
    }

    public <T extends Appendable> T html(T appendable) {
        Intrinsics.checkNotNullParameter(appendable, "appendable");
        outerHtml(appendable);
        return appendable;
    }

    public final Range sourceRange() {
        return Range.INSTANCE.of(this, true);
    }

    public String toString() {
        return outerHtml();
    }

    public int hashCode() {
        return super.hashCode();
    }

    public final boolean hasSameValue(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return Intrinsics.areEqual(outerHtml(), ((Node) o).outerHtml());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fleeksoft.ksoup.ported.KCloneable
    /* JADX INFO: renamed from: clone */
    public Node clone2() {
        Node nodeDoClone = doClone(null);
        ArrayList arrayList = new ArrayList();
        arrayList.add(nodeDoClone);
        while (!arrayList.isEmpty()) {
            Node node = (Node) arrayList.remove(0);
            int iChildNodeSize = node.childNodeSize();
            for (int i = 0; i < iChildNodeSize; i++) {
                List<Node> listEnsureChildNodes = node.ensureChildNodes();
                Node nodeDoClone2 = listEnsureChildNodes.get(i).doClone(node);
                listEnsureChildNodes.set(i, nodeDoClone2);
                arrayList.add(nodeDoClone2);
            }
        }
        return nodeDoClone;
    }

    public Node shallowClone() {
        return doClone(null);
    }

    protected Node doClone(Node parent) {
        Document documentOwnerDocument;
        Node nodeCreateClone$ksoup_release = createClone$ksoup_release();
        nodeCreateClone$ksoup_release._parentNode = parent instanceof Element ? (Element) parent : null;
        nodeCreateClone$ksoup_release._siblingIndex = parent == null ? 0 : siblingIndex();
        if (parent == null && !(this instanceof Document) && (documentOwnerDocument = ownerDocument()) != null) {
            Document documentShallowClone = documentOwnerDocument.shallowClone();
            nodeCreateClone$ksoup_release._parentNode = documentShallowClone;
            documentShallowClone.ensureChildNodes().add(nodeCreateClone$ksoup_release);
        }
        return nodeCreateClone$ksoup_release;
    }

    /* JADX INFO: compiled from: Node.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Node$Companion;", "", "<init>", "()V", "EmptyNodes", "", "Lcom/fleeksoft/ksoup/nodes/Node;", "getEmptyNodes", "()Ljava/util/List;", "EmptyString", "", "getDeepChild", "Lcom/fleeksoft/ksoup/nodes/Element;", "el", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<Node> getEmptyNodes() {
            return Node.EmptyNodes;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Element getDeepChild(Element el) {
            Element elementFirstElementChild = el.firstElementChild();
            while (true) {
                Element element = el;
                el = elementFirstElementChild;
                if (el == null) {
                    return element;
                }
                elementFirstElementChild = el.firstElementChild();
            }
        }
    }
}
