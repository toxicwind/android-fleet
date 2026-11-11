package com.fleeksoft.ksoup.safety;

import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.fleeksoft.ksoup.nodes.Attribute;
import com.fleeksoft.ksoup.nodes.Attributes;
import com.fleeksoft.ksoup.nodes.DataNode;
import com.fleeksoft.ksoup.nodes.Document;
import com.fleeksoft.ksoup.nodes.Element;
import com.fleeksoft.ksoup.nodes.Node;
import com.fleeksoft.ksoup.nodes.TextNode;
import com.fleeksoft.ksoup.parser.ParseErrorList;
import com.fleeksoft.ksoup.parser.Parser;
import com.fleeksoft.ksoup.select.NodeVisitor;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Cleaner.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002\u0016\u0017B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u0007J\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rJ\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0002J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0011H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/fleeksoft/ksoup/safety/Cleaner;", "", "safelist", "Lcom/fleeksoft/ksoup/safety/Safelist;", "<init>", "(Lcom/fleeksoft/ksoup/safety/Safelist;)V", "clean", "Lcom/fleeksoft/ksoup/nodes/Document;", "dirtyDocument", "isValid", "", "isValidBodyHtml", "bodyHtml", "", "copySafeNodes", "", "source", "Lcom/fleeksoft/ksoup/nodes/Element;", "dest", "createSafeElement", "Lcom/fleeksoft/ksoup/safety/Cleaner$ElementMeta;", "sourceEl", "CleaningVisitor", "ElementMeta", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Cleaner {
    private final Safelist safelist;

    public Cleaner(Safelist safelist) {
        Intrinsics.checkNotNullParameter(safelist, "safelist");
        this.safelist = safelist;
    }

    public final Document clean(Document dirtyDocument) {
        Intrinsics.checkNotNullParameter(dirtyDocument, "dirtyDocument");
        Document documentCreateShell = Document.INSTANCE.createShell(dirtyDocument.baseUri());
        copySafeNodes(dirtyDocument.body(), documentCreateShell.body());
        documentCreateShell.outputSettings(dirtyDocument.getOutputSettings().clone2());
        return documentCreateShell;
    }

    public final boolean isValid(Document dirtyDocument) {
        Intrinsics.checkNotNullParameter(dirtyDocument, "dirtyDocument");
        return copySafeNodes(dirtyDocument.body(), Document.INSTANCE.createShell(dirtyDocument.baseUri()).body()) == 0 && dirtyDocument.head().childNodes().isEmpty();
    }

    public final boolean isValidBodyHtml(String bodyHtml) {
        Intrinsics.checkNotNullParameter(bodyHtml, "bodyHtml");
        String str = this.safelist.getPreserveRelativeLinks() ? "https://dummy.example/" : "";
        Document documentCreateShell = Document.INSTANCE.createShell(str);
        Document documentCreateShell2 = Document.INSTANCE.createShell(str);
        ParseErrorList parseErrorListTracking = ParseErrorList.INSTANCE.tracking(1);
        documentCreateShell2.body().insertChildren(0, Parser.INSTANCE.parseFragment(bodyHtml, documentCreateShell2.body(), str, parseErrorListTracking));
        return copySafeNodes(documentCreateShell2.body(), documentCreateShell.body()) == 0 && parseErrorListTracking.isEmpty();
    }

    /* JADX INFO: compiled from: Cleaner.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\bH\u0016J\u0018\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0013"}, d2 = {"Lcom/fleeksoft/ksoup/safety/Cleaner$CleaningVisitor;", "Lcom/fleeksoft/ksoup/select/NodeVisitor;", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "destination", "<init>", "(Lcom/fleeksoft/ksoup/safety/Cleaner;Lcom/fleeksoft/ksoup/nodes/Element;Lcom/fleeksoft/ksoup/nodes/Element;)V", "numDiscarded", "", "getNumDiscarded$ksoup_release", "()I", "setNumDiscarded$ksoup_release", "(I)V", TtmlNode.TAG_HEAD, "", "node", "Lcom/fleeksoft/ksoup/nodes/Node;", "depth", "tail", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public final class CleaningVisitor implements NodeVisitor {
        private Element destination;
        private int numDiscarded;
        private final Element root;
        final /* synthetic */ Cleaner this$0;

        public CleaningVisitor(Cleaner cleaner, Element root, Element destination) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(destination, "destination");
            this.this$0 = cleaner;
            this.root = root;
            this.destination = destination;
        }

        @Override // com.fleeksoft.ksoup.select.NodeVisitor
        public /* bridge */ void traverse(Node node) {
            NodeVisitor.CC.$default$traverse(this, node);
        }

        /* JADX INFO: renamed from: getNumDiscarded$ksoup_release, reason: from getter */
        public final int getNumDiscarded() {
            return this.numDiscarded;
        }

        public final void setNumDiscarded$ksoup_release(int i) {
            this.numDiscarded = i;
        }

        @Override // com.fleeksoft.ksoup.select.NodeVisitor
        public void head(Node node, int depth) {
            Intrinsics.checkNotNullParameter(node, "node");
            if (node instanceof Element) {
                Element element = (Element) node;
                if (this.this$0.safelist.isSafeTag(element.normalName())) {
                    ElementMeta elementMetaCreateSafeElement = this.this$0.createSafeElement(element);
                    Element el = elementMetaCreateSafeElement.getEl();
                    this.destination.appendChild(el);
                    this.numDiscarded += elementMetaCreateSafeElement.getNumAttribsDiscarded();
                    this.destination = el;
                } else if (node != this.root) {
                    this.numDiscarded++;
                }
                Unit unit = Unit.INSTANCE;
                return;
            }
            if (node instanceof TextNode) {
                this.destination.appendChild(new TextNode(((TextNode) node).getWholeText()));
                return;
            }
            if (node instanceof DataNode) {
                Safelist safelist = this.this$0.safelist;
                DataNode dataNode = (DataNode) node;
                Element elementParent = dataNode.parent();
                Intrinsics.checkNotNull(elementParent);
                if (safelist.isSafeTag(elementParent.normalName())) {
                    this.destination.appendChild(new DataNode(dataNode.getWholeData()));
                    return;
                }
            }
            int i = this.numDiscarded;
            this.numDiscarded = i + 1;
            Integer.valueOf(i);
        }

        @Override // com.fleeksoft.ksoup.select.NodeVisitor
        public void tail(Node node, int depth) {
            Intrinsics.checkNotNullParameter(node, "node");
            if ((node instanceof Element) && this.this$0.safelist.isSafeTag(((Element) node).normalName())) {
                Element elementParent = this.destination.parent();
                Intrinsics.checkNotNull(elementParent);
                this.destination = elementParent;
            }
        }
    }

    private final int copySafeNodes(Element source, Element dest) {
        CleaningVisitor cleaningVisitor = new CleaningVisitor(this, source, dest);
        cleaningVisitor.traverse(source);
        return cleaningVisitor.getNumDiscarded();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ElementMeta createSafeElement(Element sourceEl) {
        Element elementShallowClone = sourceEl.shallowClone();
        String strTagName = sourceEl.tagName();
        Attributes attributes = elementShallowClone.attributes();
        elementShallowClone.clearAttributes();
        int i = 0;
        for (Attribute attribute : sourceEl.attributes()) {
            if (this.safelist.isSafeAttribute(strTagName, sourceEl, attribute)) {
                attributes.put(attribute);
            } else {
                Integer.valueOf(i);
                i++;
            }
        }
        Attributes enforcedAttributes = this.safelist.getEnforcedAttributes(strTagName);
        if (sourceEl.nameIs(CmcdData.OBJECT_TYPE_AUDIO_ONLY) && Intrinsics.areEqual(enforcedAttributes.get(LinkHeader.Parameters.Rel), "nofollow")) {
            String strAbsUrl = sourceEl.absUrl("href");
            String strBaseUri = sourceEl.baseUri();
            if (strAbsUrl.length() != 0 && strBaseUri.length() != 0 && StringsKt.startsWith$default(strAbsUrl, strBaseUri, false, 2, (Object) null)) {
                enforcedAttributes.remove(LinkHeader.Parameters.Rel);
            }
        }
        attributes.addAll(enforcedAttributes);
        elementShallowClone.attributes().addAll(attributes);
        return new ElementMeta(elementShallowClone, i);
    }

    /* JADX INFO: compiled from: Cleaner.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/fleeksoft/ksoup/safety/Cleaner$ElementMeta;", "", "el", "Lcom/fleeksoft/ksoup/nodes/Element;", "numAttribsDiscarded", "", "<init>", "(Lcom/fleeksoft/ksoup/nodes/Element;I)V", "getEl", "()Lcom/fleeksoft/ksoup/nodes/Element;", "setEl", "(Lcom/fleeksoft/ksoup/nodes/Element;)V", "getNumAttribsDiscarded", "()I", "setNumAttribsDiscarded", "(I)V", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private static final class ElementMeta {
        private Element el;
        private int numAttribsDiscarded;

        public ElementMeta(Element el, int i) {
            Intrinsics.checkNotNullParameter(el, "el");
            this.el = el;
            this.numAttribsDiscarded = i;
        }

        public final Element getEl() {
            return this.el;
        }

        public final int getNumAttribsDiscarded() {
            return this.numAttribsDiscarded;
        }

        public final void setEl(Element element) {
            Intrinsics.checkNotNullParameter(element, "<set-?>");
            this.el = element;
        }

        public final void setNumAttribsDiscarded(int i) {
            this.numAttribsDiscarded = i;
        }
    }
}
