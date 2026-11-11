package com.fleeksoft.ksoup.nodes;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.fleeksoft.ksoup.internal.QuietAppendable;
import com.fleeksoft.ksoup.internal.StringUtil;
import com.fleeksoft.ksoup.ported.CharacterKt;
import com.fleeksoft.ksoup.select.NodeVisitor;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Printer.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u0000 #2\u00020\u0001:\u0003!\"#B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J \u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u000e\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0015J\u0018\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010 \u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0015H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006$"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Printer;", "Lcom/fleeksoft/ksoup/select/NodeVisitor;", "root", "Lcom/fleeksoft/ksoup/nodes/Node;", "accum", "Lcom/fleeksoft/ksoup/internal/QuietAppendable;", "settings", "Lcom/fleeksoft/ksoup/nodes/Document$OutputSettings;", "<init>", "(Lcom/fleeksoft/ksoup/nodes/Node;Lcom/fleeksoft/ksoup/internal/QuietAppendable;Lcom/fleeksoft/ksoup/nodes/Document$OutputSettings;)V", "getRoot", "()Lcom/fleeksoft/ksoup/nodes/Node;", "getAccum", "()Lcom/fleeksoft/ksoup/internal/QuietAppendable;", "getSettings", "()Lcom/fleeksoft/ksoup/nodes/Document$OutputSettings;", "addHead", "", "el", "Lcom/fleeksoft/ksoup/nodes/Element;", "depth", "", "addTail", "addText", "textNode", "Lcom/fleeksoft/ksoup/nodes/TextNode;", "textOptions", "addNode", "node", "Lcom/fleeksoft/ksoup/nodes/LeafNode;", "indent", TtmlNode.TAG_HEAD, "tail", "Pretty", "Outline", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class Printer implements NodeVisitor {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final QuietAppendable accum;
    private final Node root;
    private final Document.OutputSettings settings;

    public Printer(Node root, QuietAppendable accum, Document.OutputSettings settings) {
        Intrinsics.checkNotNullParameter(root, "root");
        Intrinsics.checkNotNullParameter(accum, "accum");
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.root = root;
        this.accum = accum;
        this.settings = settings;
    }

    @Override // com.fleeksoft.ksoup.select.NodeVisitor
    public /* bridge */ void traverse(Node node) {
        NodeVisitor.CC.$default$traverse(this, node);
    }

    public final Node getRoot() {
        return this.root;
    }

    public final QuietAppendable getAccum() {
        return this.accum;
    }

    public final Document.OutputSettings getSettings() {
        return this.settings;
    }

    public void addHead(Element el, int depth) {
        Intrinsics.checkNotNullParameter(el, "el");
        el.outerHtmlHead$ksoup_release(this.accum, this.settings);
    }

    public void addTail(Element el, int depth) {
        Intrinsics.checkNotNullParameter(el, "el");
        el.outerHtmlTail$ksoup_release(this.accum, this.settings);
    }

    public void addText(TextNode textNode, int textOptions, int depth) {
        Intrinsics.checkNotNullParameter(textNode, "textNode");
        Entities.INSTANCE.escape(this.accum, textNode.coreValue(), this.settings, textOptions | 1);
    }

    public void addNode(LeafNode node, int depth) {
        Intrinsics.checkNotNullParameter(node, "node");
        node.outerHtmlHead$ksoup_release(this.accum, this.settings);
    }

    public final void indent(int depth) {
        this.accum.append('\n').append(StringUtil.INSTANCE.padding(depth * this.settings.indentAmount(), this.settings.maxPaddingWidth()));
    }

    @Override // com.fleeksoft.ksoup.select.NodeVisitor
    public void head(Node node, int depth) {
        Intrinsics.checkNotNullParameter(node, "node");
        if (node.getClass() == TextNode.class) {
            addText((TextNode) node, 0, depth);
        } else if (node instanceof Element) {
            addHead((Element) node, depth);
        } else {
            addNode((LeafNode) node, depth);
        }
    }

    @Override // com.fleeksoft.ksoup.select.NodeVisitor
    public void tail(Node node, int depth) {
        Intrinsics.checkNotNullParameter(node, "node");
        if (node instanceof Element) {
            addTail((Element) node, depth);
        }
    }

    /* JADX INFO: compiled from: Printer.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u0000 !2\u00020\u0001:\u0001!B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J \u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0016\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u0015J\u0012\u0010\u001f\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0003H\u0016J\u0012\u0010 \u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0003H\u0016R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\""}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Printer$Pretty;", "Lcom/fleeksoft/ksoup/nodes/Printer;", "root", "Lcom/fleeksoft/ksoup/nodes/Node;", "accum", "Lcom/fleeksoft/ksoup/internal/QuietAppendable;", "settings", "Lcom/fleeksoft/ksoup/nodes/Document$OutputSettings;", "<init>", "(Lcom/fleeksoft/ksoup/nodes/Node;Lcom/fleeksoft/ksoup/internal/QuietAppendable;Lcom/fleeksoft/ksoup/nodes/Document$OutputSettings;)V", "preserveWhitespace", "", "getPreserveWhitespace", "()Z", "setPreserveWhitespace", "(Z)V", "addHead", "", "el", "Lcom/fleeksoft/ksoup/nodes/Element;", "depth", "", "addTail", "addNode", "node", "Lcom/fleeksoft/ksoup/nodes/LeafNode;", "addText", "Lcom/fleeksoft/ksoup/nodes/TextNode;", "textOptions", "textTrim", "options", "shouldIndent", "isBlockEl", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static class Pretty extends Printer {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        public static final int maxScan = 5;
        private boolean preserveWhitespace;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Pretty(Node root, QuietAppendable accum, Document.OutputSettings settings) {
            super(root, accum, settings);
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(accum, "accum");
            Intrinsics.checkNotNullParameter(settings, "settings");
            while (root != null) {
                if (INSTANCE.tagIs(64, root)) {
                    this.preserveWhitespace = true;
                    return;
                }
                root = root.parentNode();
            }
        }

        public final boolean getPreserveWhitespace() {
            return this.preserveWhitespace;
        }

        public final void setPreserveWhitespace(boolean z) {
            this.preserveWhitespace = z;
        }

        @Override // com.fleeksoft.ksoup.nodes.Printer
        public void addHead(Element el, int depth) {
            Intrinsics.checkNotNullParameter(el, "el");
            Element element = el;
            if (shouldIndent(element)) {
                indent(depth);
            }
            super.addHead(el, depth);
            if (INSTANCE.tagIs(64, element)) {
                this.preserveWhitespace = true;
            }
        }

        @Override // com.fleeksoft.ksoup.nodes.Printer
        public void addTail(Element el, int depth) {
            Intrinsics.checkNotNullParameter(el, "el");
            if (shouldIndent(INSTANCE.nextNonBlank(el.firstChild()))) {
                indent(depth);
            }
            super.addTail(el, depth);
            if (this.preserveWhitespace && el.getTag().is(64)) {
                for (Element elementParent = el.parent(); elementParent != null; elementParent = elementParent.parent()) {
                    if (elementParent.tag().preserveWhitespace()) {
                        return;
                    }
                }
                this.preserveWhitespace = false;
            }
        }

        @Override // com.fleeksoft.ksoup.nodes.Printer
        public void addNode(LeafNode node, int depth) {
            Intrinsics.checkNotNullParameter(node, "node");
            if (shouldIndent(node)) {
                indent(depth);
            }
            super.addNode(node, depth);
        }

        @Override // com.fleeksoft.ksoup.nodes.Printer
        public void addText(TextNode node, int textOptions, int depth) {
            Intrinsics.checkNotNullParameter(node, "node");
            if (!this.preserveWhitespace) {
                textOptions = textTrim(node, textOptions | 4);
                if (!node.isBlank() && isBlockEl(node.parentNode()) && shouldIndent(node)) {
                    indent(depth);
                }
            }
            super.addText(node, textOptions, depth);
        }

        public final int textTrim(TextNode node, int options) {
            Intrinsics.checkNotNullParameter(node, "node");
            if (!isBlockEl(node.parentNode())) {
                return options;
            }
            Node nodePreviousSibling = node.previousSibling();
            Node nodeNextSibling = node.nextSibling();
            if ((!(nodePreviousSibling instanceof Element) || isBlockEl(nodePreviousSibling)) && (nodePreviousSibling == null || (!(nodePreviousSibling instanceof TextNode) && shouldIndent(nodePreviousSibling)))) {
                options |= 8;
            }
            if (nodeNextSibling == null || (!(nodeNextSibling instanceof TextNode) && shouldIndent(nodeNextSibling))) {
                return options | 16;
            }
            Node nodeNextNonBlank = INSTANCE.nextNonBlank(nodeNextSibling);
            return ((nodeNextNonBlank instanceof TextNode) && StringUtil.INSTANCE.isWhitespace(CharacterKt.codePointAt(((TextNode) nodeNextNonBlank).nodeValue(), 0).getValue())) ? options | 16 : options;
        }

        public boolean shouldIndent(Node node) {
            if (node != null && !Intrinsics.areEqual(node, getRoot()) && !this.preserveWhitespace) {
                Companion companion = INSTANCE;
                if (!companion.isBlankText(node)) {
                    if (isBlockEl(node)) {
                        return true;
                    }
                    Node nodePreviousNonblank = companion.previousNonblank(node);
                    if (isBlockEl(nodePreviousNonblank)) {
                        return true;
                    }
                    Element element = node.get_parentNode();
                    if (isBlockEl(element)) {
                        Intrinsics.checkNotNull(element);
                        if (!element.tag().is(8) && companion.hasNonTextNodes(element)) {
                            return nodePreviousNonblank == null || (!(nodePreviousNonblank instanceof TextNode) && (isBlockEl(nodePreviousNonblank) || !(nodePreviousNonblank instanceof Element)));
                        }
                    }
                }
            }
            return false;
        }

        public boolean isBlockEl(Node node) {
            if (node == null || !(node instanceof Element)) {
                return false;
            }
            Element element = (Element) node;
            return element.nameIs("br") || element.isBlock() || (!element.getTag().isKnownTag() && ((element.parentNode() instanceof Document) || INSTANCE.hasChildBlocks(element)));
        }

        /* JADX INFO: compiled from: Printer.kt */
        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\fJ\u0012\u0010\u000e\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\fJ\u0010\u0010\u000f\u001a\u00020\u00072\b\u0010\r\u001a\u0004\u0018\u00010\fJ\u0018\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00052\b\u0010\r\u001a\u0004\u0018\u00010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Printer$Pretty$Companion;", "", "<init>", "()V", "maxScan", "", "hasChildBlocks", "", "el", "Lcom/fleeksoft/ksoup/nodes/Element;", "hasNonTextNodes", "previousNonblank", "Lcom/fleeksoft/ksoup/nodes/Node;", "node", "nextNonBlank", "isBlankText", "tagIs", "option", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final boolean hasChildBlocks(Element el) {
                Intrinsics.checkNotNullParameter(el, "el");
                Element elementFirstElementChild = el.firstElementChild();
                for (int i = 0; i < 5 && elementFirstElementChild != null; i++) {
                    if (elementFirstElementChild.isBlock() || !elementFirstElementChild.getTag().isKnownTag()) {
                        return true;
                    }
                    elementFirstElementChild = elementFirstElementChild.nextElementSibling();
                }
                return false;
            }

            public final boolean hasNonTextNodes(Element el) {
                Intrinsics.checkNotNullParameter(el, "el");
                Node nodeFirstChild = el.firstChild();
                for (int i = 0; i < 5 && nodeFirstChild != null; i++) {
                    if (!(nodeFirstChild instanceof TextNode)) {
                        return true;
                    }
                    nodeFirstChild = ((TextNode) nodeFirstChild).nextSibling();
                }
                return false;
            }

            public final Node previousNonblank(Node node) {
                Intrinsics.checkNotNullParameter(node, "node");
                Node nodePreviousSibling = node.previousSibling();
                while (isBlankText(nodePreviousSibling)) {
                    nodePreviousSibling = nodePreviousSibling != null ? nodePreviousSibling.previousSibling() : null;
                }
                return nodePreviousSibling;
            }

            public final Node nextNonBlank(Node node) {
                while (isBlankText(node)) {
                    node = node != null ? node.nextSibling() : null;
                }
                return node;
            }

            public final boolean isBlankText(Node node) {
                return (node instanceof TextNode) && ((TextNode) node).isBlank();
            }

            public final boolean tagIs(int option, Node node) {
                return (node instanceof Element) && ((Element) node).getTag().is(option);
            }
        }
    }

    /* JADX INFO: compiled from: Printer.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0003H\u0016J\u0012\u0010\r\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0003H\u0016¨\u0006\u000e"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Printer$Outline;", "Lcom/fleeksoft/ksoup/nodes/Printer$Pretty;", "root", "Lcom/fleeksoft/ksoup/nodes/Node;", "accum", "Lcom/fleeksoft/ksoup/internal/QuietAppendable;", "settings", "Lcom/fleeksoft/ksoup/nodes/Document$OutputSettings;", "<init>", "(Lcom/fleeksoft/ksoup/nodes/Node;Lcom/fleeksoft/ksoup/internal/QuietAppendable;Lcom/fleeksoft/ksoup/nodes/Document$OutputSettings;)V", "isBlockEl", "", "node", "shouldIndent", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static class Outline extends Pretty {
        @Override // com.fleeksoft.ksoup.nodes.Printer.Pretty
        public boolean isBlockEl(Node node) {
            return node != null;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Outline(Node root, QuietAppendable accum, Document.OutputSettings settings) {
            super(root, accum, settings);
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(accum, "accum");
            Intrinsics.checkNotNullParameter(settings, "settings");
        }

        @Override // com.fleeksoft.ksoup.nodes.Printer.Pretty
        public boolean shouldIndent(Node node) {
            if (node == null || Intrinsics.areEqual(node, getRoot()) || getPreserveWhitespace() || Pretty.INSTANCE.isBlankText(node)) {
                return false;
            }
            if (node instanceof TextNode) {
                TextNode textNode = (TextNode) node;
                if (textNode.previousSibling() == null && textNode.nextSibling() == null) {
                    return false;
                }
            }
            return true;
        }
    }

    /* JADX INFO: compiled from: Printer.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Printer$Companion;", "", "<init>", "()V", "printerFor", "Lcom/fleeksoft/ksoup/nodes/Printer;", "root", "Lcom/fleeksoft/ksoup/nodes/Node;", "accum", "Lcom/fleeksoft/ksoup/internal/QuietAppendable;", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Printer printerFor(Node root, QuietAppendable accum) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(accum, "accum");
            Document.OutputSettings outputSettings = NodeUtils.INSTANCE.outputSettings(root);
            if (outputSettings.outline()) {
                return new Outline(root, accum, outputSettings);
            }
            return outputSettings.prettyPrint() ? new Pretty(root, accum, outputSettings) : new Printer(root, accum, outputSettings);
        }
    }
}
