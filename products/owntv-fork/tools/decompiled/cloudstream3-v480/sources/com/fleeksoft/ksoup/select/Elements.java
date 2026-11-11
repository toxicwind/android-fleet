package com.fleeksoft.ksoup.select;

import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.fleeksoft.ksoup.helper.Validate;
import com.fleeksoft.ksoup.nodes.Comment;
import com.fleeksoft.ksoup.nodes.DataNode;
import com.fleeksoft.ksoup.nodes.Element;
import com.fleeksoft.ksoup.nodes.FormElement;
import com.fleeksoft.ksoup.nodes.Node;
import com.fleeksoft.ksoup.nodes.TextNode;
import com.google.android.gms.actions.SearchIntents;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KClasses;
import org.mozilla.javascript.ES6Iterator;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: compiled from: Elements.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u001d\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B!\u0012\u0018\b\u0002\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0004j\b\u0012\u0004\u0012\u00020\u0002`\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0017\u0012\u0006\u0010\b\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\tB\u0017\b\u0017\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b¢\u0006\u0004\b\u0006\u0010\fJ\b\u0010\r\u001a\u00020\u0000H\u0016J\u0018\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0004j\b\u0012\u0004\u0012\u00020\u0002`\u0005H\u0016J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\u0010J\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00100\u00152\u0006\u0010\u0011\u001a\u00020\u0010J\u001a\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010\u0016\u001a\u0004\u0018\u00010\u0010H\u0007J\u000e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0010J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0010J\u000e\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0010J\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0010J\u000e\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0010J\u0006\u0010\u001d\u001a\u00020\u0010J\u0010\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u0010H\u0007J\u0006\u0010\u001e\u001a\u00020\u0010J\u0006\u0010\u001f\u001a\u00020\u0013J\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00100\u0015J\u0006\u0010!\u001a\u00020\u0010J\u000e\u0010\"\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\u0010J\u0010\u0010!\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u0010H\u0007J\u000e\u0010#\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u0010J\u000e\u0010$\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u0010J\u0010\u0010%\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u0010H\u0016J\u0010\u0010&\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u0010H\u0016J\u0010\u0010'\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u0010H\u0016J\u0006\u0010(\u001a\u00020\u0000J\u0006\u0010)\u001a\u00020\u0000J\b\u0010*\u001a\u00020\u0000H\u0016J\u000e\u0010+\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\u0010J\u0010\u0010-\u001a\u0004\u0018\u00010\u00022\u0006\u0010.\u001a\u00020\u0010J\u000e\u0010/\u001a\u00020\u00022\u0006\u0010.\u001a\u00020\u0010J\u000e\u00100\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\u0010J\u000e\u00101\u001a\u00020\u00002\u0006\u00102\u001a\u000203J\u000e\u00104\u001a\u00020\u00132\u0006\u0010,\u001a\u00020\u0010J\t\u00105\u001a\u00020\u0000H\u0087\u0002J\u0012\u00105\u001a\u00020\u00002\b\u0010,\u001a\u0004\u0018\u00010\u0010H\u0007J\b\u00106\u001a\u00020\u0000H\u0007J\u0012\u00106\u001a\u00020\u00002\b\u0010,\u001a\u0004\u0018\u00010\u0010H\u0007J\b\u00107\u001a\u00020\u0000H\u0007J\u0012\u00107\u001a\u00020\u00002\b\u0010,\u001a\u0004\u0018\u00010\u0010H\u0007J\b\u00108\u001a\u00020\u0000H\u0007J\u0012\u00108\u001a\u00020\u00002\b\u0010,\u001a\u0004\u0018\u00010\u0010H\u0007J$\u00109\u001a\u00020\u00002\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u00102\u0006\u00105\u001a\u00020\u00132\u0006\u0010:\u001a\u00020\u0013H\u0002J\u0006\u0010;\u001a\u00020\u0000J\n\u0010<\u001a\u0004\u0018\u00010\u0002H\u0016J\n\u0010=\u001a\u0004\u0018\u00010\u0002H\u0016J\u000e\u0010>\u001a\u00020\u00002\u0006\u0010?\u001a\u00020@J\u000e\u0010A\u001a\u00020\u00002\u0006\u0010B\u001a\u00020CJ\f\u0010D\u001a\b\u0012\u0004\u0012\u00020E0\u0015J\f\u0010F\u001a\b\u0012\u0004\u0012\u00020G0\u0015J\f\u0010H\u001a\b\u0012\u0004\u0012\u00020I0\u0015J\f\u0010J\u001a\b\u0012\u0004\u0012\u00020K0\u0015J&\u0010L\u001a\b\u0012\u0004\u0012\u0002HM0\u0015\"\b\b\u0000\u0010M*\u00020N2\f\u0010O\u001a\b\u0012\u0004\u0012\u0002HM0PH\u0002¨\u0006Q"}, d2 = {"Lcom/fleeksoft/ksoup/select/Elements;", "Lcom/fleeksoft/ksoup/select/Nodes;", "Lcom/fleeksoft/ksoup/nodes/Element;", "delegateList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "<init>", "(Ljava/util/ArrayList;)V", "element", "(Lcom/fleeksoft/ksoup/nodes/Element;)V", "elements", "", "(Ljava/util/Collection;)V", "clone", "asList", "attr", "", "attributeKey", "hasAttr", "", "eachAttr", "", "attributeValue", "removeAttr", "addClass", "className", "removeClass", "toggleClass", "hasClass", ES6Iterator.VALUE_PROPERTY, "text", "hasText", "eachText", "html", "tagName", "prepend", "append", TtmlNode.ANNOTATION_POSITION_BEFORE, TtmlNode.ANNOTATION_POSITION_AFTER, "wrap", "unwrap", "empty", "remove", "select", SearchIntents.EXTRA_QUERY, "selectFirst", "cssQuery", "expectFirst", "not", "eq", "index", "", "is", "next", "nextAll", "prev", "prevAll", "siblings", "all", "parents", "first", "last", "traverse", "nodeVisitor", "Lcom/fleeksoft/ksoup/select/NodeVisitor;", "filter", "nodeFilter", "Lcom/fleeksoft/ksoup/select/NodeFilter;", "forms", "Lcom/fleeksoft/ksoup/nodes/FormElement;", "comments", "Lcom/fleeksoft/ksoup/nodes/Comment;", "textNodes", "Lcom/fleeksoft/ksoup/nodes/TextNode;", "dataNodes", "Lcom/fleeksoft/ksoup/nodes/DataNode;", "childNodesOfType", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/fleeksoft/ksoup/nodes/Node;", "tClass", "Lkotlin/reflect/KClass;", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Elements extends Nodes<Element> {
    /* JADX WARN: Multi-variable type inference failed */
    public Elements() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // com.fleeksoft.ksoup.select.Nodes, java.util.List
    public final /* bridge */ Element remove(int i) {
        return removeAt(i);
    }

    @Override // com.fleeksoft.ksoup.select.Nodes
    public /* bridge */ Element removeAt(int i) {
        return (Element) super.removeAt(i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Elements(ArrayList<Element> delegateList) {
        super((List) delegateList);
        Intrinsics.checkNotNullParameter(delegateList, "delegateList");
    }

    public /* synthetic */ Elements(ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((ArrayList<Element>) ((i & 1) != 0 ? new ArrayList() : arrayList));
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public Elements(Element element) {
        this(null, 1, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(element, "element");
        add(element);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public Elements(Collection<? extends Element> elements) {
        this(null, 1, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(elements, "elements");
        addAll(elements);
    }

    @Override // com.fleeksoft.ksoup.select.Nodes
    public Elements clone() {
        Elements elements = this;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(elements, 10));
        Iterator<T> it = elements.iterator();
        while (it.hasNext()) {
            arrayList.add(((Element) it.next()).clone2());
        }
        return new Elements((Collection<? extends Element>) arrayList);
    }

    @Override // com.fleeksoft.ksoup.select.Nodes
    public ArrayList<Element> asList() {
        return new ArrayList<>(this);
    }

    public final String attr(String attributeKey) {
        Intrinsics.checkNotNullParameter(attributeKey, "attributeKey");
        for (Element element : this) {
            if (element.hasAttr(attributeKey)) {
                return element.attr(attributeKey);
            }
        }
        return "";
    }

    public final boolean hasAttr(String attributeKey) {
        Intrinsics.checkNotNullParameter(attributeKey, "attributeKey");
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            if (it.next().hasAttr(attributeKey)) {
                return true;
            }
        }
        return false;
    }

    public final List<String> eachAttr(String attributeKey) {
        Intrinsics.checkNotNullParameter(attributeKey, "attributeKey");
        ArrayList arrayList = new ArrayList(size());
        for (Element element : this) {
            if (element.hasAttr(attributeKey)) {
                arrayList.add(element.attr(attributeKey));
            }
        }
        return arrayList;
    }

    public final Elements attr(String attributeKey, String attributeValue) {
        Intrinsics.checkNotNullParameter(attributeKey, "attributeKey");
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            it.next().attr(attributeKey, attributeValue);
        }
        return this;
    }

    public final Elements removeAttr(String attributeKey) {
        Intrinsics.checkNotNullParameter(attributeKey, "attributeKey");
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            it.next().removeAttr(attributeKey);
        }
        return this;
    }

    public final Elements addClass(String className) {
        Intrinsics.checkNotNullParameter(className, "className");
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            it.next().addClass(className);
        }
        return this;
    }

    public final Elements removeClass(String className) {
        Intrinsics.checkNotNullParameter(className, "className");
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            it.next().removeClass(className);
        }
        return this;
    }

    public final Elements toggleClass(String className) {
        Intrinsics.checkNotNullParameter(className, "className");
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            it.next().toggleClass(className);
        }
        return this;
    }

    public final boolean hasClass(String className) {
        Intrinsics.checkNotNullParameter(className, "className");
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            if (it.next().hasClass(className)) {
                return true;
            }
        }
        return false;
    }

    public final String value() {
        if (size() > 0) {
            Element elementFirst = first();
            Intrinsics.checkNotNull(elementFirst);
            return elementFirst.value();
        }
        return "";
    }

    public final Elements value(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            it.next().value(value);
        }
        return this;
    }

    /* JADX INFO: renamed from: com.fleeksoft.ksoup.select.Elements$text$1, reason: invalid class name */
    /* JADX INFO: compiled from: Elements.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Element, String> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(1, Element.class, "text", "text()Ljava/lang/String;", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public final String invoke(Element p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return p0.text();
        }
    }

    public final String text() {
        return CollectionsKt.joinToString$default(this, Stream.ID_UNKNOWN, null, null, 0, null, AnonymousClass1.INSTANCE, 30, null);
    }

    public final boolean hasText() {
        Elements elements = this;
        if ((elements instanceof Collection) && elements.isEmpty()) {
            return false;
        }
        Iterator<T> it = elements.iterator();
        while (it.hasNext()) {
            if (((Element) it.next()).hasText()) {
                return true;
            }
        }
        return false;
    }

    public final List<String> eachText() {
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            Element element = (Element) it.next();
            String strText = element.hasText() ? element.text() : null;
            if (strText != null) {
                arrayList.add(strText);
            }
        }
        return CollectionsKt.toList(arrayList);
    }

    static final CharSequence html$lambda$0(Element it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.html();
    }

    public final String html() {
        return CollectionsKt.joinToString$default(this, "\n", null, null, 0, null, new Function1() { // from class: com.fleeksoft.ksoup.select.Elements$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Elements.html$lambda$0((Element) obj);
            }
        }, 30, null);
    }

    public final Elements tagName(String tagName) {
        Intrinsics.checkNotNullParameter(tagName, "tagName");
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element.tagName$default(it.next(), tagName, null, 2, null);
        }
        return this;
    }

    public final Elements html(String html) {
        Intrinsics.checkNotNullParameter(html, "html");
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            it.next().html(html);
        }
        return this;
    }

    public final Elements prepend(String html) {
        Intrinsics.checkNotNullParameter(html, "html");
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            it.next().prepend(html);
        }
        return this;
    }

    public final Elements append(String html) {
        Intrinsics.checkNotNullParameter(html, "html");
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            it.next().append(html);
        }
        return this;
    }

    @Override // com.fleeksoft.ksoup.select.Nodes
    public Elements before(String html) {
        Intrinsics.checkNotNullParameter(html, "html");
        super.before(html);
        return this;
    }

    @Override // com.fleeksoft.ksoup.select.Nodes
    public Elements after(String html) {
        Intrinsics.checkNotNullParameter(html, "html");
        super.after(html);
        return this;
    }

    @Override // com.fleeksoft.ksoup.select.Nodes
    public Elements wrap(String html) {
        Intrinsics.checkNotNullParameter(html, "html");
        super.wrap(html);
        return this;
    }

    public final Elements unwrap() {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            it.next().unwrap();
        }
        return this;
    }

    public final Elements empty() {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            it.next().empty();
        }
        return this;
    }

    @Override // com.fleeksoft.ksoup.select.Nodes
    public Elements remove() {
        super.remove();
        return this;
    }

    public final Elements select(String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        return Selector.INSTANCE.select(query, this);
    }

    public final Element selectFirst(String cssQuery) {
        Intrinsics.checkNotNullParameter(cssQuery, "cssQuery");
        return Selector.INSTANCE.selectFirst(cssQuery, this);
    }

    public final Element expectFirst(String cssQuery) {
        Intrinsics.checkNotNullParameter(cssQuery, "cssQuery");
        return (Element) Validate.INSTANCE.expectNotNull(Selector.INSTANCE.selectFirst(cssQuery, this), "No elements matched the query '" + cssQuery + "' in the elements.");
    }

    public final Elements not(String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        return Selector.INSTANCE.filterOut(this, Selector.INSTANCE.select(query, this));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Elements eq(int index) {
        if (size() > index) {
            return new Elements(get(index));
        }
        return new Elements(null, 1, 0 == true ? 1 : 0);
    }

    public final boolean is(String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        Evaluator evaluatorEvaluatorOf = Selector.INSTANCE.evaluatorOf(query);
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            if (it.next().is(evaluatorEvaluatorOf)) {
                return true;
            }
        }
        return false;
    }

    public final Elements next() {
        return siblings(null, true, false);
    }

    public final Elements next(String query) {
        return siblings(query, true, false);
    }

    public final Elements nextAll() {
        return siblings(null, true, true);
    }

    public final Elements nextAll(String query) {
        return siblings(query, true, true);
    }

    public final Elements prev() {
        return siblings(null, false, false);
    }

    public final Elements prev(String query) {
        return siblings(query, false, false);
    }

    public final Elements prevAll() {
        return siblings(null, false, true);
    }

    public final Elements prevAll(String query) {
        return siblings(query, false, true);
    }

    static /* synthetic */ Elements siblings$default(Elements elements, String str, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        return elements.siblings(str, z, z2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Elements siblings(String query, boolean next, boolean all) {
        Elements elements = new Elements(0 == true ? 1 : 0, 1, 0 == true ? 1 : 0);
        Evaluator evaluatorEvaluatorOf = query != null ? Selector.INSTANCE.evaluatorOf(query) : null;
        for (Element elementNextElementSibling : this) {
            do {
                elementNextElementSibling = next ? elementNextElementSibling.nextElementSibling() : elementNextElementSibling.previousElementSibling();
                if (elementNextElementSibling == null) {
                    break;
                }
                if (evaluatorEvaluatorOf == null || elementNextElementSibling.is(evaluatorEvaluatorOf)) {
                    elements.add(elementNextElementSibling);
                }
            } while (all);
        }
        return elements;
    }

    public final Elements parents() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            linkedHashSet.addAll(it.next().parents());
        }
        return new Elements(linkedHashSet);
    }

    @Override // com.fleeksoft.ksoup.select.Nodes
    public Element first() {
        return (Element) super.first();
    }

    @Override // com.fleeksoft.ksoup.select.Nodes
    public Element last() {
        return (Element) super.last();
    }

    public final Elements traverse(NodeVisitor nodeVisitor) {
        Intrinsics.checkNotNullParameter(nodeVisitor, "nodeVisitor");
        NodeTraversor.INSTANCE.traverse(nodeVisitor, this);
        return this;
    }

    public final Elements filter(NodeFilter nodeFilter) {
        Intrinsics.checkNotNullParameter(nodeFilter, "nodeFilter");
        NodeTraversor.INSTANCE.filter(nodeFilter, this);
        return this;
    }

    public final List<FormElement> forms() {
        ArrayList arrayList = new ArrayList();
        for (Element element : this) {
            if (element instanceof FormElement) {
                arrayList.add(element);
            }
        }
        return arrayList;
    }

    public final List<Comment> comments() {
        return childNodesOfType(Reflection.getOrCreateKotlinClass(Comment.class));
    }

    public final List<TextNode> textNodes() {
        return childNodesOfType(Reflection.getOrCreateKotlinClass(TextNode.class));
    }

    public final List<DataNode> dataNodes() {
        return childNodesOfType(Reflection.getOrCreateKotlinClass(DataNode.class));
    }

    private final <T extends Node> List<T> childNodesOfType(KClass<T> tClass) {
        ArrayList arrayList = new ArrayList();
        for (Element element : this) {
            int iChildNodeSize = element.childNodeSize();
            for (int i = 0; i < iChildNodeSize; i++) {
                Node nodeChildNode = element.childNode(i);
                if (tClass.isInstance(nodeChildNode)) {
                    arrayList.add(KClasses.cast(tClass, nodeChildNode));
                }
            }
        }
        return arrayList;
    }
}
