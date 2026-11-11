package com.fleeksoft.ksoup.select;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.fleeksoft.ksoup.helper.Validate;
import com.fleeksoft.ksoup.internal.Normalizer;
import com.fleeksoft.ksoup.internal.StringUtil;
import com.fleeksoft.ksoup.nodes.Comment;
import com.fleeksoft.ksoup.nodes.Document;
import com.fleeksoft.ksoup.nodes.DocumentType;
import com.fleeksoft.ksoup.nodes.Element;
import com.fleeksoft.ksoup.nodes.LeafNode;
import com.fleeksoft.ksoup.nodes.Node;
import com.fleeksoft.ksoup.nodes.PseudoTextElement;
import com.fleeksoft.ksoup.nodes.TextNode;
import com.fleeksoft.ksoup.nodes.XmlDeclaration;
import com.fleeksoft.ksoup.parser.ParseSettings;
import java.util.Iterator;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;
import org.slf4j.Marker;

/* JADX INFO: compiled from: Evaluator.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b+\b&\u0018\u00002\u00020\u0001:*\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&'()*+,-./0123456789:;<=>B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052\u0006\u0010\b\u001a\u00020\u0006J\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00070\u00052\u0006\u0010\b\u001a\u00020\u0006J\u0018\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H&J\u0018\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0007H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016¨\u0006?"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator;", "", "<init>", "()V", "asPredicate", "Lkotlin/Function1;", "Lcom/fleeksoft/ksoup/nodes/Element;", "", "root", "asNodePredicate", "Lcom/fleeksoft/ksoup/nodes/Node;", "matches", "element", "node", "leafNode", "Lcom/fleeksoft/ksoup/nodes/LeafNode;", "wantsNodes", "reset", "", "cost", "", "Tag", "TagStartsWith", "TagEndsWith", "Id", "Class", "Attribute", "AttributeStarting", "AttributeWithValue", "AttributeWithValueNot", "AttributeWithValueStarting", "AttributeWithValueEnding", "AttributeWithValueContaining", "AttributeWithValueMatching", "AttributeKeyPair", "AllElements", "IndexLessThan", "IndexGreaterThan", "IndexEquals", "IsLastChild", "IsFirstOfType", "IsLastOfType", "CssNthEvaluator", "IsNthChild", "IsNthLastChild", "IsNthOfType", "IsNthLastOfType", "IsFirstChild", "IsRoot", "IsOnlyChild", "IsOnlyOfType", "IsEmpty", "IndexEvaluator", "ContainsText", "ContainsWholeText", "ContainsWholeOwnText", "ContainsData", "ContainsOwnText", "Matches", "MatchesOwn", "MatchesWholeText", "MatchesWholeOwnText", "MatchText", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class Evaluator {
    public int cost() {
        return 5;
    }

    public abstract boolean matches(Element root, Element element);

    public boolean matches(Element root, LeafNode leafNode) {
        Intrinsics.checkNotNullParameter(root, "root");
        Intrinsics.checkNotNullParameter(leafNode, "leafNode");
        return false;
    }

    public void reset() {
    }

    public boolean wantsNodes() {
        return false;
    }

    protected Evaluator() {
    }

    static final boolean asPredicate$lambda$0(Evaluator evaluator, Element element, Element element2) {
        Intrinsics.checkNotNullParameter(element2, "element");
        return evaluator.matches(element, element2);
    }

    public final Function1<Element, Boolean> asPredicate(final Element root) {
        Intrinsics.checkNotNullParameter(root, "root");
        return new Function1() { // from class: com.fleeksoft.ksoup.select.Evaluator$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(Evaluator.asPredicate$lambda$0(this.f$0, root, (Element) obj));
            }
        };
    }

    static final boolean asNodePredicate$lambda$0(Evaluator evaluator, Element element, Node node) {
        Intrinsics.checkNotNullParameter(node, "node");
        return evaluator.matches(element, node);
    }

    public final Function1<Node, Boolean> asNodePredicate(final Element root) {
        Intrinsics.checkNotNullParameter(root, "root");
        return new Function1() { // from class: com.fleeksoft.ksoup.select.Evaluator$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(Evaluator.asNodePredicate$lambda$0(this.f$0, root, (Node) obj));
            }
        };
    }

    public boolean matches(Element root, Node node) {
        Intrinsics.checkNotNullParameter(root, "root");
        Intrinsics.checkNotNullParameter(node, "node");
        if (node instanceof Element) {
            return matches(root, (Element) node);
        }
        if ((node instanceof LeafNode) && wantsNodes()) {
            return matches(root, (LeafNode) node);
        }
        return false;
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\n\u0010\r\u001a\u00020\u0003H\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$Tag;", "Lcom/fleeksoft/ksoup/select/Evaluator;", "tagName", "", "<init>", "(Ljava/lang/String;)V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "cost", "", "toString", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Tag extends Evaluator {
        private final String tagName;

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public int cost() {
            return 1;
        }

        public Tag(String tagName) {
            Intrinsics.checkNotNullParameter(tagName, "tagName");
            this.tagName = tagName;
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            return element.nameIs(this.tagName);
        }

        /* JADX INFO: renamed from: toString, reason: from getter */
        public String getTagName() {
            return this.tagName;
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\n\u0010\u000b\u001a\u00020\u0003H\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$TagStartsWith;", "Lcom/fleeksoft/ksoup/select/Evaluator;", "tagName", "", "<init>", "(Ljava/lang/String;)V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "toString", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class TagStartsWith extends Evaluator {
        private final String tagName;

        public TagStartsWith(String tagName) {
            Intrinsics.checkNotNullParameter(tagName, "tagName");
            this.tagName = tagName;
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            return StringsKt.startsWith$default(element.normalName(), this.tagName, false, 2, (Object) null);
        }

        public String toString() {
            return this.tagName + "|*";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\n\u0010\u000b\u001a\u00020\u0003H\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$TagEndsWith;", "Lcom/fleeksoft/ksoup/select/Evaluator;", "tagName", "", "<init>", "(Ljava/lang/String;)V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "toString", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class TagEndsWith extends Evaluator {
        private final String tagName;

        public TagEndsWith(String tagName) {
            Intrinsics.checkNotNullParameter(tagName, "tagName");
            this.tagName = tagName;
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            return StringsKt.endsWith$default(element.normalName(), this.tagName, false, 2, (Object) null);
        }

        public String toString() {
            return "*|" + this.tagName;
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\n\u0010\r\u001a\u00020\u0003H\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$Id;", "Lcom/fleeksoft/ksoup/select/Evaluator;", "id", "", "<init>", "(Ljava/lang/String;)V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "cost", "", "toString", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Id extends Evaluator {
        private final String id;

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public int cost() {
            return 2;
        }

        public Id(String id) {
            Intrinsics.checkNotNullParameter(id, "id");
            this.id = id;
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            return Intrinsics.areEqual(this.id, element.id());
        }

        public String toString() {
            return "#" + this.id;
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\n\u0010\r\u001a\u00020\u0003H\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$Class;", "Lcom/fleeksoft/ksoup/select/Evaluator;", "className", "", "<init>", "(Ljava/lang/String;)V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "cost", "", "toString", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Class extends Evaluator {
        private final String className;

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public int cost() {
            return 8;
        }

        public Class(String className) {
            Intrinsics.checkNotNullParameter(className, "className");
            this.className = className;
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            return element.hasClass(this.className);
        }

        public String toString() {
            return "." + this.className;
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\n\u0010\r\u001a\u00020\u0003H\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$Attribute;", "Lcom/fleeksoft/ksoup/select/Evaluator;", "key", "", "<init>", "(Ljava/lang/String;)V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "cost", "", "toString", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Attribute extends Evaluator {
        private final String key;

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public int cost() {
            return 2;
        }

        public Attribute(String key) {
            Intrinsics.checkNotNullParameter(key, "key");
            this.key = key;
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            return element.hasAttr(this.key);
        }

        public String toString() {
            return "[" + this.key + "]";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\n\u0010\r\u001a\u00020\u0003H\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$AttributeStarting;", "Lcom/fleeksoft/ksoup/select/Evaluator;", "keyPrefix", "", "<init>", "(Ljava/lang/String;)V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "cost", "", "toString", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class AttributeStarting extends Evaluator {
        private final String keyPrefix;

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public int cost() {
            return 6;
        }

        public AttributeStarting(String keyPrefix) {
            Intrinsics.checkNotNullParameter(keyPrefix, "keyPrefix");
            this.keyPrefix = Normalizer.INSTANCE.lowerCase(keyPrefix);
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            Iterator<com.fleeksoft.ksoup.nodes.Attribute> it = element.attributes().asList().iterator();
            while (it.hasNext()) {
                if (StringsKt.startsWith$default(Normalizer.INSTANCE.lowerCase(it.next().getKey()), this.keyPrefix, false, 2, (Object) null)) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return "[^" + this.keyPrefix + "]";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\n\u0010\u000e\u001a\u00020\u0003H\u0096\u0080\u0004¨\u0006\u000f"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$AttributeWithValue;", "Lcom/fleeksoft/ksoup/select/Evaluator$AttributeKeyPair;", "key", "", ES6Iterator.VALUE_PROPERTY, "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "cost", "", "toString", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class AttributeWithValue extends AttributeKeyPair {
        @Override // com.fleeksoft.ksoup.select.Evaluator
        public int cost() {
            return 3;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AttributeWithValue(String key, String value) {
            super(key, value);
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            return element.hasAttr(getKey()) && StringsKt.equals(getValue(), element.attr(getKey()), true);
        }

        public String toString() {
            return "[" + getKey() + "=" + getValue() + "]";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\n\u0010\u000e\u001a\u00020\u0003H\u0096\u0080\u0004¨\u0006\u000f"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$AttributeWithValueNot;", "Lcom/fleeksoft/ksoup/select/Evaluator$AttributeKeyPair;", "key", "", ES6Iterator.VALUE_PROPERTY, "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "cost", "", "toString", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class AttributeWithValueNot extends AttributeKeyPair {
        @Override // com.fleeksoft.ksoup.select.Evaluator
        public int cost() {
            return 3;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AttributeWithValueNot(String key, String value) {
            super(key, value);
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            return !StringsKt.equals(getValue(), element.attr(getKey()), true);
        }

        public String toString() {
            return "[" + getKey() + "!=" + getValue() + "]";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\n\u0010\u000e\u001a\u00020\u0003H\u0096\u0080\u0004¨\u0006\u000f"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$AttributeWithValueStarting;", "Lcom/fleeksoft/ksoup/select/Evaluator$AttributeKeyPair;", "key", "", ES6Iterator.VALUE_PROPERTY, "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "cost", "", "toString", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class AttributeWithValueStarting extends AttributeKeyPair {
        @Override // com.fleeksoft.ksoup.select.Evaluator
        public int cost() {
            return 4;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AttributeWithValueStarting(String key, String value) {
            super(key, value);
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            return element.hasAttr(getKey()) && StringsKt.startsWith$default(Normalizer.INSTANCE.lowerCase(element.attr(getKey())), getValue(), false, 2, (Object) null);
        }

        public String toString() {
            return "[" + getKey() + "^=" + getValue() + "]";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\n\u0010\u000e\u001a\u00020\u0003H\u0096\u0080\u0004¨\u0006\u000f"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$AttributeWithValueEnding;", "Lcom/fleeksoft/ksoup/select/Evaluator$AttributeKeyPair;", "key", "", ES6Iterator.VALUE_PROPERTY, "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "cost", "", "toString", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class AttributeWithValueEnding extends AttributeKeyPair {
        @Override // com.fleeksoft.ksoup.select.Evaluator
        public int cost() {
            return 4;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AttributeWithValueEnding(String key, String value) {
            super(key, value);
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            return element.hasAttr(getKey()) && StringsKt.endsWith$default(Normalizer.INSTANCE.lowerCase(element.attr(getKey())), getValue(), false, 2, (Object) null);
        }

        public String toString() {
            return "[" + getKey() + "$=" + getValue() + "]";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\n\u0010\u000e\u001a\u00020\u0003H\u0096\u0080\u0004¨\u0006\u000f"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$AttributeWithValueContaining;", "Lcom/fleeksoft/ksoup/select/Evaluator$AttributeKeyPair;", "key", "", ES6Iterator.VALUE_PROPERTY, "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "cost", "", "toString", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class AttributeWithValueContaining extends AttributeKeyPair {
        @Override // com.fleeksoft.ksoup.select.Evaluator
        public int cost() {
            return 6;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AttributeWithValueContaining(String key, String value) {
            super(key, value);
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            return element.hasAttr(getKey()) && StringsKt.contains$default((CharSequence) Normalizer.INSTANCE.lowerCase(element.attr(getKey())), (CharSequence) getValue(), false, 2, (Object) null);
        }

        public String toString() {
            return "[" + getKey() + "*=" + getValue() + "]";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\n\u0010\u0017\u001a\u00020\u0003H\u0096\u0080\u0004R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0018"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$AttributeWithValueMatching;", "Lcom/fleeksoft/ksoup/select/Evaluator;", "key", "", "regex", "Lkotlin/text/Regex;", "<init>", "(Ljava/lang/String;Lkotlin/text/Regex;)V", "getRegex", "()Lkotlin/text/Regex;", "setRegex", "(Lkotlin/text/Regex;)V", "getKey", "()Ljava/lang/String;", "setKey", "(Ljava/lang/String;)V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "cost", "", "toString", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class AttributeWithValueMatching extends Evaluator {
        private String key;
        private Regex regex;

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public int cost() {
            return 8;
        }

        public AttributeWithValueMatching(String str, Regex regex) {
            Intrinsics.checkNotNullParameter(regex, "regex");
            this.regex = regex;
            this.key = Normalizer.INSTANCE.normalize(str);
        }

        public final Regex getRegex() {
            return this.regex;
        }

        public final void setRegex(Regex regex) {
            Intrinsics.checkNotNullParameter(regex, "<set-?>");
            this.regex = regex;
        }

        public final String getKey() {
            return this.key;
        }

        public final void setKey(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.key = str;
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            return element.hasAttr(this.key) && Regex.find$default(this.regex, element.attr(this.key), 0, 2, null) != null;
        }

        public String toString() {
            return "[" + this.key + "~=" + this.regex.getPattern() + "]";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b&\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\n¨\u0006\r"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$AttributeKeyPair;", "Lcom/fleeksoft/ksoup/select/Evaluator;", "key", "", ES6Iterator.VALUE_PROPERTY, "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getKey", "()Ljava/lang/String;", "setKey", "(Ljava/lang/String;)V", "getValue", "setValue", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static abstract class AttributeKeyPair extends Evaluator {
        private String key;
        private String value;

        public AttributeKeyPair(String key, String value) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            Validate.INSTANCE.notEmpty(key);
            this.key = Normalizer.INSTANCE.normalize(key);
            if ((StringsKt.startsWith$default(value, "'", false, 2, (Object) null) && StringsKt.endsWith$default(value, "'", false, 2, (Object) null)) || (StringsKt.startsWith$default(value, "\"", false, 2, (Object) null) && StringsKt.endsWith$default(value, "\"", false, 2, (Object) null))) {
                Validate.INSTANCE.isTrue(value.length() > 1, "Quoted value must have content");
                value = value.substring(1, value.length() - 1);
                Intrinsics.checkNotNullExpressionValue(value, "substring(...)");
            }
            this.value = Normalizer.INSTANCE.lowerCase(value);
        }

        public final String getKey() {
            return this.key;
        }

        public final void setKey(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.key = str;
        }

        public final String getValue() {
            return this.value;
        }

        public final void setValue(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.value = str;
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\n\u0010\u000b\u001a\u00020\fH\u0096\u0080\u0004¨\u0006\r"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$AllElements;", "Lcom/fleeksoft/ksoup/select/Evaluator;", "<init>", "()V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "cost", "", "toString", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class AllElements extends Evaluator {
        @Override // com.fleeksoft.ksoup.select.Evaluator
        public int cost() {
            return 10;
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            return true;
        }

        public String toString() {
            return Marker.ANY_MARKER;
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\n\u0010\u000b\u001a\u00020\fH\u0096\u0080\u0004¨\u0006\r"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$IndexLessThan;", "Lcom/fleeksoft/ksoup/select/Evaluator$IndexEvaluator;", "index", "", "<init>", "(I)V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "toString", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class IndexLessThan extends IndexEvaluator {
        public IndexLessThan(int i) {
            super(i);
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            return !Intrinsics.areEqual(root, element) && element.elementSiblingIndex() < getIndex();
        }

        public String toString() {
            return ":lt(" + getIndex() + ")";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\n\u0010\u000b\u001a\u00020\fH\u0096\u0080\u0004¨\u0006\r"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$IndexGreaterThan;", "Lcom/fleeksoft/ksoup/select/Evaluator$IndexEvaluator;", "index", "", "<init>", "(I)V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "toString", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class IndexGreaterThan extends IndexEvaluator {
        public IndexGreaterThan(int i) {
            super(i);
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            return element.elementSiblingIndex() > getIndex();
        }

        public String toString() {
            return ":gt(" + getIndex() + ")";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\n\u0010\u000b\u001a\u00020\fH\u0096\u0080\u0004¨\u0006\r"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$IndexEquals;", "Lcom/fleeksoft/ksoup/select/Evaluator$IndexEvaluator;", "index", "", "<init>", "(I)V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "toString", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class IndexEquals extends IndexEvaluator {
        public IndexEquals(int i) {
            super(i);
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            return element.elementSiblingIndex() == getIndex();
        }

        public String toString() {
            return ":eq(" + getIndex() + ")";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016J\n\u0010\t\u001a\u00020\nH\u0096\u0080\u0004¨\u0006\u000b"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$IsLastChild;", "Lcom/fleeksoft/ksoup/select/Evaluator;", "<init>", "()V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "toString", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class IsLastChild extends Evaluator {
        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            Element elementParent = element.parent();
            return (elementParent == null || (elementParent instanceof Document) || element != elementParent.lastElementChild()) ? false : true;
        }

        public String toString() {
            return ":last-child";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0004\u001a\u00020\u0005H\u0096\u0080\u0004¨\u0006\u0006"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$IsFirstOfType;", "Lcom/fleeksoft/ksoup/select/Evaluator$IsNthOfType;", "<init>", "()V", "toString", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class IsFirstOfType extends IsNthOfType {
        public IsFirstOfType() {
            super(0, 1);
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator.CssNthEvaluator
        public String toString() {
            return ":first-of-type";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0004\u001a\u00020\u0005H\u0096\u0080\u0004¨\u0006\u0006"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$IsLastOfType;", "Lcom/fleeksoft/ksoup/select/Evaluator$IsNthLastOfType;", "<init>", "()V", "toString", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class IsLastOfType extends IsNthLastOfType {
        public IsLastOfType() {
            super(0, 1);
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator.CssNthEvaluator
        public String toString() {
            return ":last-of-type";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\bJ\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\n\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004J\b\u0010\u0013\u001a\u00020\u0012H$J\u0018\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH&R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$CssNthEvaluator;", "Lcom/fleeksoft/ksoup/select/Evaluator;", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "", "b", "<init>", "(II)V", TypedValues.CycleType.S_WAVE_OFFSET, "(I)V", "getA", "()I", "getB", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "toString", "", "getPseudoClass", "calculatePosition", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static abstract class CssNthEvaluator extends Evaluator {
        private final int a;
        private final int b;

        public abstract int calculatePosition(Element root, Element element);

        protected abstract String getPseudoClass();

        public CssNthEvaluator(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        public final int getA() {
            return this.a;
        }

        public final int getB() {
            return this.b;
        }

        public CssNthEvaluator(int i) {
            this(0, i);
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            Element elementParent = element.parent();
            if (elementParent == null || (elementParent instanceof Document)) {
                return false;
            }
            int iCalculatePosition = calculatePosition(root, element);
            int i = this.a;
            int i2 = this.b;
            if (i == 0) {
                return iCalculatePosition == i2;
            }
            return (iCalculatePosition - i2) * i >= 0 && (iCalculatePosition - i2) % i == 0;
        }

        public String toString() {
            String strValueOf;
            String pseudoClass = getPseudoClass();
            int i = this.a;
            int i2 = this.b;
            if (i == 0) {
                return ":" + pseudoClass + "(" + i2 + ")";
            }
            if (i2 == 0) {
                return ":" + pseudoClass + "(" + i + "n)";
            }
            if (i2 >= 0) {
                strValueOf = Marker.ANY_NON_NULL_MARKER + i2;
            } else {
                strValueOf = String.valueOf(i2);
            }
            return ":" + pseudoClass + "(" + this.a + "n" + strValueOf + ")";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0014¨\u0006\r"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$IsNthChild;", "Lcom/fleeksoft/ksoup/select/Evaluator$CssNthEvaluator;", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "", "b", "<init>", "(II)V", "calculatePosition", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "getPseudoClass", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class IsNthChild extends CssNthEvaluator {
        public IsNthChild(int i, int i2) {
            super(i, i2);
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator.CssNthEvaluator
        public int calculatePosition(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            return element.elementSiblingIndex() + 1;
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator.CssNthEvaluator
        protected String getPseudoClass() {
            return "nth-child";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0014¨\u0006\r"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$IsNthLastChild;", "Lcom/fleeksoft/ksoup/select/Evaluator$CssNthEvaluator;", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "", "b", "<init>", "(II)V", "calculatePosition", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "getPseudoClass", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class IsNthLastChild extends CssNthEvaluator {
        public IsNthLastChild(int i, int i2) {
            super(i, i2);
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator.CssNthEvaluator
        public int calculatePosition(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            if (element.parent() == null) {
                return 0;
            }
            Element elementParent = element.parent();
            Intrinsics.checkNotNull(elementParent);
            return elementParent.childrenSize() - element.elementSiblingIndex();
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator.CssNthEvaluator
        protected String getPseudoClass() {
            return "nth-last-child";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0014¨\u0006\r"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$IsNthOfType;", "Lcom/fleeksoft/ksoup/select/Evaluator$CssNthEvaluator;", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "", "b", "<init>", "(II)V", "calculatePosition", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "getPseudoClass", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static class IsNthOfType extends CssNthEvaluator {
        public IsNthOfType(int i, int i2) {
            super(i, i2);
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator.CssNthEvaluator
        public int calculatePosition(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            Element elementParent = element.parent();
            if (elementParent == null) {
                return 0;
            }
            int iChildNodeSize = elementParent.childNodeSize();
            int i = 0;
            for (int i2 = 0; i2 < iChildNodeSize; i2++) {
                Node nodeChildNode = elementParent.childNode(i2);
                if (Intrinsics.areEqual(nodeChildNode.normalName(), element.normalName())) {
                    i++;
                }
                if (Intrinsics.areEqual(nodeChildNode, element)) {
                    break;
                }
            }
            return i;
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator.CssNthEvaluator
        protected String getPseudoClass() {
            return "nth-of-type";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0014¨\u0006\r"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$IsNthLastOfType;", "Lcom/fleeksoft/ksoup/select/Evaluator$CssNthEvaluator;", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "", "b", "<init>", "(II)V", "calculatePosition", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "getPseudoClass", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static class IsNthLastOfType extends CssNthEvaluator {
        public IsNthLastOfType(int i, int i2) {
            super(i, i2);
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator.CssNthEvaluator
        public int calculatePosition(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            int i = 0;
            if (element.parent() == null) {
                return 0;
            }
            for (Element elementNextElementSibling = element; elementNextElementSibling != null; elementNextElementSibling = elementNextElementSibling.nextElementSibling()) {
                if (Intrinsics.areEqual(elementNextElementSibling.normalName(), element.normalName())) {
                    i++;
                }
            }
            return i;
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator.CssNthEvaluator
        protected String getPseudoClass() {
            return "nth-last-of-type";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016J\n\u0010\t\u001a\u00020\nH\u0096\u0080\u0004¨\u0006\u000b"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$IsFirstChild;", "Lcom/fleeksoft/ksoup/select/Evaluator;", "<init>", "()V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "toString", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class IsFirstChild extends Evaluator {
        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            Element elementParent = element.parent();
            return (elementParent == null || (elementParent instanceof Document) || element != elementParent.firstElementChild()) ? false : true;
        }

        public String toString() {
            return ":first-child";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\n\u0010\u000b\u001a\u00020\fH\u0096\u0080\u0004¨\u0006\r"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$IsRoot;", "Lcom/fleeksoft/ksoup/select/Evaluator;", "<init>", "()V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "cost", "", "toString", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class IsRoot extends Evaluator {
        @Override // com.fleeksoft.ksoup.select.Evaluator
        public int cost() {
            return 1;
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            if (root instanceof Document) {
                root = root.firstElementChild();
            }
            return element == root;
        }

        public String toString() {
            return ":root";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016J\n\u0010\t\u001a\u00020\nH\u0096\u0080\u0004¨\u0006\u000b"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$IsOnlyChild;", "Lcom/fleeksoft/ksoup/select/Evaluator;", "<init>", "()V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "toString", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class IsOnlyChild extends Evaluator {
        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            Element elementParent = element.parent();
            return (elementParent == null || (elementParent instanceof Document) || !element.siblingElements().isEmpty()) ? false : true;
        }

        public String toString() {
            return ":only-child";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016J\n\u0010\t\u001a\u00020\nH\u0096\u0080\u0004¨\u0006\u000b"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$IsOnlyOfType;", "Lcom/fleeksoft/ksoup/select/Evaluator;", "<init>", "()V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "toString", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class IsOnlyOfType extends Evaluator {
        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            Element elementParent = element.parent();
            if (elementParent != null && !(elementParent instanceof Document)) {
                int i = 0;
                for (Element elementFirstElementChild = elementParent.firstElementChild(); elementFirstElementChild != null; elementFirstElementChild = elementFirstElementChild.nextElementSibling()) {
                    if (Intrinsics.areEqual(elementFirstElementChild.normalName(), element.normalName())) {
                        i++;
                    }
                    if (i > 1) {
                        break;
                    }
                }
                if (i == 1) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return ":only-of-type";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016J\n\u0010\t\u001a\u00020\nH\u0096\u0080\u0004¨\u0006\u000b"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$IsEmpty;", "Lcom/fleeksoft/ksoup/select/Evaluator;", "<init>", "()V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "toString", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class IsEmpty extends Evaluator {
        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            for (Node nodeFirstChild = element.firstChild(); nodeFirstChild != null; nodeFirstChild = ((LeafNode) nodeFirstChild).nextSibling()) {
                if (nodeFirstChild instanceof TextNode) {
                    if (!((TextNode) nodeFirstChild).isBlank()) {
                        return false;
                    }
                } else if (!(nodeFirstChild instanceof Comment) && !(nodeFirstChild instanceof XmlDeclaration) && !(nodeFirstChild instanceof DocumentType)) {
                    return false;
                }
            }
            return true;
        }

        public String toString() {
            return ":empty";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b&\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\t"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$IndexEvaluator;", "Lcom/fleeksoft/ksoup/select/Evaluator;", "index", "", "<init>", "(I)V", "getIndex", "()I", "setIndex", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static abstract class IndexEvaluator extends Evaluator {
        private int index;

        public IndexEvaluator(int i) {
            this.index = i;
        }

        public final int getIndex() {
            return this.index;
        }

        public final void setIndex(int i) {
            this.index = i;
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\n\u0010\r\u001a\u00020\u0003H\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$ContainsText;", "Lcom/fleeksoft/ksoup/select/Evaluator;", "searchText", "", "<init>", "(Ljava/lang/String;)V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "cost", "", "toString", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class ContainsText extends Evaluator {
        private final String searchText;

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public int cost() {
            return 10;
        }

        public ContainsText(String searchText) {
            Intrinsics.checkNotNullParameter(searchText, "searchText");
            this.searchText = Normalizer.INSTANCE.lowerCase(StringUtil.INSTANCE.normaliseWhitespace(searchText));
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            return StringsKt.contains$default((CharSequence) Normalizer.INSTANCE.lowerCase(element.text()), (CharSequence) this.searchText, false, 2, (Object) null);
        }

        public String toString() {
            return ":contains(" + this.searchText + ")";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\n\u0010\r\u001a\u00020\u0003H\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$ContainsWholeText;", "Lcom/fleeksoft/ksoup/select/Evaluator;", "searchText", "", "<init>", "(Ljava/lang/String;)V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "cost", "", "toString", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class ContainsWholeText extends Evaluator {
        private final String searchText;

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public int cost() {
            return 10;
        }

        public ContainsWholeText(String searchText) {
            Intrinsics.checkNotNullParameter(searchText, "searchText");
            this.searchText = searchText;
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            return StringsKt.contains$default((CharSequence) element.wholeText(), (CharSequence) this.searchText, false, 2, (Object) null);
        }

        public String toString() {
            return ":containsWholeText(" + this.searchText + ")";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\n\u0010\u000b\u001a\u00020\u0003H\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$ContainsWholeOwnText;", "Lcom/fleeksoft/ksoup/select/Evaluator;", "searchText", "", "<init>", "(Ljava/lang/String;)V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "toString", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class ContainsWholeOwnText extends Evaluator {
        private final String searchText;

        public ContainsWholeOwnText(String searchText) {
            Intrinsics.checkNotNullParameter(searchText, "searchText");
            this.searchText = searchText;
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            return StringsKt.contains$default((CharSequence) element.wholeOwnText(), (CharSequence) this.searchText, false, 2, (Object) null);
        }

        public String toString() {
            return ":containsWholeOwnText(" + this.searchText + ")";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\n\u0010\u000b\u001a\u00020\u0003H\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$ContainsData;", "Lcom/fleeksoft/ksoup/select/Evaluator;", "searchText", "", "<init>", "(Ljava/lang/String;)V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "toString", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class ContainsData extends Evaluator {
        private final String searchText;

        public ContainsData(String str) {
            this.searchText = Normalizer.INSTANCE.lowerCase(str);
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            return StringsKt.contains$default((CharSequence) Normalizer.INSTANCE.lowerCase(element.data()), (CharSequence) this.searchText, false, 2, (Object) null);
        }

        public String toString() {
            return ":containsData(" + this.searchText + ")";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\n\u0010\u000b\u001a\u00020\u0003H\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$ContainsOwnText;", "Lcom/fleeksoft/ksoup/select/Evaluator;", "searchText", "", "<init>", "(Ljava/lang/String;)V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "toString", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class ContainsOwnText extends Evaluator {
        private final String searchText;

        public ContainsOwnText(String searchText) {
            Intrinsics.checkNotNullParameter(searchText, "searchText");
            this.searchText = Normalizer.INSTANCE.lowerCase(StringUtil.INSTANCE.normaliseWhitespace(searchText));
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            return StringsKt.contains$default((CharSequence) Normalizer.INSTANCE.lowerCase(element.ownText()), (CharSequence) this.searchText, false, 2, (Object) null);
        }

        public String toString() {
            return ":containsOwn(" + this.searchText + ")";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\n\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$Matches;", "Lcom/fleeksoft/ksoup/select/Evaluator;", "pattern", "Lkotlin/text/Regex;", "<init>", "(Lkotlin/text/Regex;)V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "cost", "", "toString", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Matches extends Evaluator {
        private final Regex pattern;

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public int cost() {
            return 8;
        }

        public Matches(Regex pattern) {
            Intrinsics.checkNotNullParameter(pattern, "pattern");
            this.pattern = pattern;
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            return this.pattern.containsMatchIn(element.text());
        }

        public String toString() {
            return ":matches(" + this.pattern.getPattern() + ")";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\n\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$MatchesOwn;", "Lcom/fleeksoft/ksoup/select/Evaluator;", "pattern", "Lkotlin/text/Regex;", "<init>", "(Lkotlin/text/Regex;)V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "cost", "", "toString", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class MatchesOwn extends Evaluator {
        private final Regex pattern;

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public int cost() {
            return 7;
        }

        public MatchesOwn(Regex pattern) {
            Intrinsics.checkNotNullParameter(pattern, "pattern");
            this.pattern = pattern;
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            return this.pattern.containsMatchIn(element.ownText());
        }

        public String toString() {
            return ":matchesOwn(" + this.pattern.getPattern() + ")";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\n\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$MatchesWholeText;", "Lcom/fleeksoft/ksoup/select/Evaluator;", "pattern", "Lkotlin/text/Regex;", "<init>", "(Lkotlin/text/Regex;)V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "cost", "", "toString", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class MatchesWholeText extends Evaluator {
        private final Regex pattern;

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public int cost() {
            return 8;
        }

        public MatchesWholeText(Regex pattern) {
            Intrinsics.checkNotNullParameter(pattern, "pattern");
            this.pattern = pattern;
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            return this.pattern.containsMatchIn(element.wholeText());
        }

        public String toString() {
            return ":matchesWholeText(" + this.pattern.getPattern() + ")";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\n\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$MatchesWholeOwnText;", "Lcom/fleeksoft/ksoup/select/Evaluator;", "pattern", "Lkotlin/text/Regex;", "<init>", "(Lkotlin/text/Regex;)V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "cost", "", "toString", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class MatchesWholeOwnText extends Evaluator {
        private final Regex pattern;

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public int cost() {
            return 7;
        }

        public MatchesWholeOwnText(Regex pattern) {
            Intrinsics.checkNotNullParameter(pattern, "pattern");
            this.pattern = pattern;
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            return this.pattern.containsMatchIn(element.wholeOwnText());
        }

        public String toString() {
            return ":matchesWholeOwnText(" + this.pattern.getPattern() + ")";
        }
    }

    /* JADX INFO: compiled from: Evaluator.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\n\u0010\u000b\u001a\u00020\fH\u0096\u0080\u0004¨\u0006\u000e"}, d2 = {"Lcom/fleeksoft/ksoup/select/Evaluator$MatchText;", "Lcom/fleeksoft/ksoup/select/Evaluator;", "<init>", "()V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "cost", "", "toString", "", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Deprecated(message = "This selector is deprecated and will be removed in a future version. Migrate to <code>::textnode</code> using the <code>Element#selectNodes()</code> method instead.")
    public static final class MatchText extends Evaluator {
        private static boolean loggedError;

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public int cost() {
            return -1;
        }

        public MatchText() {
            if (loggedError) {
                return;
            }
            loggedError = true;
            System.out.println((Object) "WARNING: :matchText selector is deprecated and will be removed in a future version. Use Element#selectNodes(String, Class) with selector ::textnode and class TextNode instead.");
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            if (element instanceof PseudoTextElement) {
                return true;
            }
            for (TextNode textNode : element.textNodes()) {
                PseudoTextElement pseudoTextElement = new PseudoTextElement(com.fleeksoft.ksoup.parser.Tag.INSTANCE.valueOf(element.tagName(), element.tag().namespace(), ParseSettings.INSTANCE.getPreserveCase()), element.baseUri(), element.attributes());
                textNode.replaceWith(pseudoTextElement);
                pseudoTextElement.appendChild(textNode);
            }
            return false;
        }

        public String toString() {
            return ":matchText";
        }
    }
}
