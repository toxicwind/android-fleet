package com.fleeksoft.ksoup.parser;

import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.fleeksoft.ksoup.helper.Validate;
import com.fleeksoft.ksoup.nodes.Document;
import com.fleeksoft.ksoup.nodes.Element;
import com.fleeksoft.ksoup.nodes.Node;
import com.fleeksoft.ksoup.select.Evaluator;
import com.fleeksoft.ksoup.select.NodeVisitor;
import com.fleeksoft.ksoup.select.Selector;
import com.google.android.gms.actions.SearchIntents;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: StreamParser.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010)\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001-B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u000e\u001a\u00020\u00002\n\u0010\u000f\u001a\u00060\u0010j\u0002`\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u0016\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013J$\u0010\u0015\u001a\u00020\u00002\n\u0010\u000f\u001a\u00060\u0010j\u0002`\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0012\u001a\u00020\u0013J \u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0012\u001a\u00020\u0013J\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u0019J\u000e\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u001bJ\u0006\u0010\u001c\u001a\u00020\u0000J\u0006\u0010\u001d\u001a\u00020\u001eJ\u001a\u0010\u001f\u001a\u00020\u001e2\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u001e0!J\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\"\u001a\u00020\u000bJ\f\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$J\u0010\u0010&\u001a\u0004\u0018\u00010\u00172\u0006\u0010'\u001a\u00020\u0013J\u000e\u0010(\u001a\u00020\u00172\u0006\u0010'\u001a\u00020\u0013J\u0010\u0010&\u001a\u0004\u0018\u00010\u00172\u0006\u0010)\u001a\u00020*J\u0010\u0010+\u001a\u0004\u0018\u00010\u00172\u0006\u0010'\u001a\u00020\u0013J\u000e\u0010,\u001a\u00020\u00172\u0006\u0010'\u001a\u00020\u0013J\u0010\u0010+\u001a\u0004\u0018\u00010\u00172\u0006\u0010)\u001a\u00020*R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00060\tR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/fleeksoft/ksoup/parser/StreamParser;", "", "parser", "Lcom/fleeksoft/ksoup/parser/Parser;", "<init>", "(Lcom/fleeksoft/ksoup/parser/Parser;)V", "treeBuilder", "Lcom/fleeksoft/ksoup/parser/TreeBuilder;", "elementIterator", "Lcom/fleeksoft/ksoup/parser/StreamParser$ElementIterator;", "document", "Lcom/fleeksoft/ksoup/nodes/Document;", "stopped", "", "parse", TvContractCompat.PARAM_INPUT, "Ljava/io/Reader;", "Lcom/fleeksoft/io/Reader;", "baseUri", "", "html", "parseFragment", "context", "Lcom/fleeksoft/ksoup/nodes/Element;", "stream", "Lkotlin/sequences/Sequence;", "iterator", "", "stop", "close", "", "use", "receiver", "Lkotlin/Function1;", "complete", "completeFragment", "", "Lcom/fleeksoft/ksoup/nodes/Node;", "selectFirst", SearchIntents.EXTRA_QUERY, "expectFirst", "eval", "Lcom/fleeksoft/ksoup/select/Evaluator;", "selectNext", "expectNext", "ElementIterator", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class StreamParser {
    private Document document;
    private final ElementIterator elementIterator;
    private final Parser parser;
    private boolean stopped;
    private final TreeBuilder treeBuilder;

    public StreamParser(Parser parser) {
        Intrinsics.checkNotNullParameter(parser, "parser");
        this.parser = parser;
        TreeBuilder treeBuilder = parser.getTreeBuilder();
        this.treeBuilder = treeBuilder;
        ElementIterator elementIterator = new ElementIterator();
        this.elementIterator = elementIterator;
        treeBuilder.nodeListener(elementIterator);
    }

    public final StreamParser parse(Reader input, String baseUri) {
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(baseUri, "baseUri");
        close();
        this.elementIterator.reset();
        this.treeBuilder.initialiseParse(input, baseUri, this.parser);
        this.document = this.treeBuilder.getDoc();
        return this;
    }

    public final StreamParser parse(String html, String baseUri) {
        Intrinsics.checkNotNullParameter(html, "html");
        Intrinsics.checkNotNullParameter(baseUri, "baseUri");
        return parse(new StringReader(html), baseUri);
    }

    public final StreamParser parseFragment(Reader input, Element context, String baseUri) {
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(baseUri, "baseUri");
        parse(input, baseUri);
        this.treeBuilder.initialiseParseFragment(context);
        return this;
    }

    public final StreamParser parseFragment(String html, Element context, String baseUri) {
        Intrinsics.checkNotNullParameter(html, "html");
        Intrinsics.checkNotNullParameter(baseUri, "baseUri");
        return parseFragment(new StringReader(html), context, baseUri);
    }

    public final Sequence<Element> stream() {
        return SequencesKt.asSequence(this.elementIterator);
    }

    public final Iterator<Element> iterator() {
        return this.elementIterator;
    }

    public final StreamParser stop() {
        this.stopped = true;
        return this;
    }

    public final void close() {
        this.treeBuilder.completeParse();
    }

    public final void use(Function1<? super StreamParser, Unit> receiver) {
        Intrinsics.checkNotNullParameter(receiver, "receiver");
        receiver.invoke(this);
        close();
    }

    public final Document document() {
        Document doc = this.treeBuilder.getDoc();
        this.document = doc;
        if (doc == null) {
            throw new IllegalArgumentException("Must run parse() before calling.".toString());
        }
        Intrinsics.checkNotNull(doc);
        return doc;
    }

    public final Document complete() {
        Document document = document();
        this.treeBuilder.runParser();
        return document;
    }

    public final List<Node> completeFragment() {
        this.treeBuilder.runParser();
        return this.treeBuilder.completeParseFragment();
    }

    public final Element selectFirst(String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        return selectFirst(Selector.INSTANCE.evaluatorOf(query));
    }

    public final Element expectFirst(String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        return (Element) Validate.INSTANCE.expectNotNull(selectFirst(query), "No elements matched the query '" + query + "' in the document.");
    }

    public final Element selectFirst(Evaluator eval) {
        Intrinsics.checkNotNullParameter(eval, "eval");
        Element elementSelectFirst = document().selectFirst(eval);
        return elementSelectFirst != null ? elementSelectFirst : selectNext(eval);
    }

    public final Element selectNext(String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        return selectNext(Selector.INSTANCE.evaluatorOf(query));
    }

    public final Element expectNext(String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        return (Element) Validate.INSTANCE.expectNotNull(selectNext(query), "No elements matched the query '" + query + "' in the document.");
    }

    public final Element selectNext(Evaluator eval) {
        Element next;
        Intrinsics.checkNotNullParameter(eval, "eval");
        Document document = document();
        Sequence<Element> sequenceStream = stream();
        Function1<Element, Boolean> function1AsPredicate = eval.asPredicate(document);
        Iterator<Element> it = sequenceStream.iterator();
        while (it.hasNext()) {
            next = it.next();
            if (function1AsPredicate.invoke(next).booleanValue()) {
                return next;
            }
        }
        next = null;
        return next;
    }

    /* JADX INFO: compiled from: StreamParser.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010)\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0080\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\f\u001a\u00020\rJ\n\u0010\u000e\u001a\u00020\u000fH\u0096\u0082\u0004J\n\u0010\n\u001a\u00020\u0002H\u0096\u0082\u0004J\b\u0010\u0010\u001a\u00020\rH\u0002J\n\u0010\u0011\u001a\u00020\rH\u0096\u0080\u0004J\u0018\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010\u000b\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0007j\b\u0012\u0004\u0012\u00020\u0002`\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0002X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/fleeksoft/ksoup/parser/StreamParser$ElementIterator;", "", "Lcom/fleeksoft/ksoup/nodes/Element;", "Lcom/fleeksoft/ksoup/select/NodeVisitor;", "<init>", "(Lcom/fleeksoft/ksoup/parser/StreamParser;)V", "emitQueue", "", "Lcom/fleeksoft/ksoup/ported/LinkedList;", "current", "next", "tail", "reset", "", "hasNext", "", "maybeFindNext", "remove", TtmlNode.TAG_HEAD, "node", "Lcom/fleeksoft/ksoup/nodes/Node;", "depth", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public final class ElementIterator implements Iterator<Element>, NodeVisitor, KMutableIterator {
        private Element current;
        private final List<Element> emitQueue = new ArrayList();
        private Element next;
        private Element tail;

        public ElementIterator() {
        }

        @Override // com.fleeksoft.ksoup.select.NodeVisitor
        public /* bridge */ void traverse(Node node) {
            NodeVisitor.CC.$default$traverse(this, node);
        }

        public final void reset() {
            this.emitQueue.clear();
            this.tail = null;
            this.next = null;
            this.current = null;
            StreamParser.this.stopped = false;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            maybeFindNext();
            return this.next != null;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public Element next() {
            maybeFindNext();
            Element element = this.next;
            if (element == null) {
                throw new NoSuchElementException();
            }
            this.current = element;
            this.next = null;
            Intrinsics.checkNotNull(element);
            return element;
        }

        private final void maybeFindNext() {
            if (StreamParser.this.stopped || this.next != null) {
                return;
            }
            if (this.emitQueue.isEmpty()) {
                while (StreamParser.this.treeBuilder.stepParser()) {
                    if (!this.emitQueue.isEmpty()) {
                        this.next = this.emitQueue.remove(0);
                        return;
                    }
                }
                StreamParser.this.stop();
                StreamParser.this.close();
                Element element = this.tail;
                if (element != null) {
                    this.next = element;
                    this.tail = null;
                    return;
                }
                return;
            }
            this.next = this.emitQueue.remove(0);
        }

        @Override // java.util.Iterator
        public void remove() {
            Element element = this.current;
            if (element == null) {
                throw new NoSuchElementException();
            }
            if (element != null) {
                element.remove();
            }
        }

        @Override // com.fleeksoft.ksoup.select.NodeVisitor
        public void head(Node node, int depth) {
            Element elementPreviousElementSibling;
            Intrinsics.checkNotNullParameter(node, "node");
            if (!(node instanceof Element) || (elementPreviousElementSibling = node.previousElementSibling()) == null) {
                return;
            }
            this.emitQueue.add(elementPreviousElementSibling);
        }

        @Override // com.fleeksoft.ksoup.select.NodeVisitor
        public void tail(Node node, int depth) {
            Intrinsics.checkNotNullParameter(node, "node");
            if (node instanceof Element) {
                Element element = (Element) node;
                this.tail = element;
                Element elementLastElementChild = element != null ? element.lastElementChild() : null;
                if (elementLastElementChild != null) {
                    this.emitQueue.add(elementLastElementChild);
                }
            }
        }
    }
}
