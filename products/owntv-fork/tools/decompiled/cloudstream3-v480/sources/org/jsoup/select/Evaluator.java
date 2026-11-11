package org.jsoup.select;

import j$.util.function.Predicate$CC;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import org.jsoup.helper.Regex;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.LeafNode;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.PseudoTextElement;
import org.jsoup.nodes.TextNode;
import org.jsoup.nodes.XmlDeclaration;
import org.jsoup.parser.ParseSettings;
import org.slf4j.Marker;

/* JADX INFO: loaded from: classes8.dex */
public abstract class Evaluator {
    protected int cost() {
        return 5;
    }

    /* JADX INFO: renamed from: matches, reason: merged with bridge method [inline-methods] */
    public abstract boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2);

    boolean matches(Element element, LeafNode leafNode) {
        return false;
    }

    protected void reset() {
    }

    boolean wantsNodes() {
        return false;
    }

    protected Evaluator() {
    }

    public Predicate<Element> asPredicate(final Element element) {
        return new Predicate() { // from class: org.jsoup.select.Evaluator$$ExternalSyntheticLambda1
            public /* synthetic */ Predicate and(Predicate predicate) {
                return Predicate$CC.$default$and(this, predicate);
            }

            public /* synthetic */ Predicate negate() {
                return Predicate$CC.$default$negate(this);
            }

            public /* synthetic */ Predicate or(Predicate predicate) {
                return Predicate$CC.$default$or(this, predicate);
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.m10844lambda$asPredicate$0$orgjsoupselectEvaluator(element, (Element) obj);
            }
        };
    }

    Predicate<Node> asNodePredicate(final Element element) {
        return new Predicate() { // from class: org.jsoup.select.Evaluator$$ExternalSyntheticLambda0
            public /* synthetic */ Predicate and(Predicate predicate) {
                return Predicate$CC.$default$and(this, predicate);
            }

            public /* synthetic */ Predicate negate() {
                return Predicate$CC.$default$negate(this);
            }

            public /* synthetic */ Predicate or(Predicate predicate) {
                return Predicate$CC.$default$or(this, predicate);
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.m10843lambda$asNodePredicate$1$orgjsoupselectEvaluator(element, (Node) obj);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: renamed from: matches, reason: merged with bridge method [inline-methods] */
    public final boolean m10843lambda$asNodePredicate$1$orgjsoupselectEvaluator(Element element, Node node) {
        if (node instanceof Element) {
            return m10844lambda$asPredicate$0$orgjsoupselectEvaluator(element, (Element) node);
        }
        if ((node instanceof LeafNode) && wantsNodes()) {
            return matches(element, (LeafNode) node);
        }
        return false;
    }

    public static final class Tag extends Evaluator {
        private final String tagName;

        @Override // org.jsoup.select.Evaluator
        protected int cost() {
            return 1;
        }

        public Tag(String str) {
            this.tagName = str;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            return element2.nameIs(this.tagName);
        }

        public String toString() {
            return String.format("%s", this.tagName);
        }
    }

    public static final class TagStartsWith extends Evaluator {
        private final String tagName;

        public TagStartsWith(String str) {
            this.tagName = str;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            return element2.normalName().startsWith(this.tagName);
        }

        public String toString() {
            return String.format("%s|*", this.tagName);
        }
    }

    public static final class TagEndsWith extends Evaluator {
        private final String tagName;

        public TagEndsWith(String str) {
            this.tagName = str;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            return element2.normalName().endsWith(this.tagName);
        }

        public String toString() {
            return String.format("*|%s", this.tagName);
        }
    }

    public static final class Id extends Evaluator {
        private final String id;

        @Override // org.jsoup.select.Evaluator
        protected int cost() {
            return 2;
        }

        public Id(String str) {
            this.id = str;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            return this.id.equals(element2.id());
        }

        public String toString() {
            return String.format("#%s", this.id);
        }
    }

    public static final class Class extends Evaluator {
        private final String className;

        @Override // org.jsoup.select.Evaluator
        protected int cost() {
            return 8;
        }

        public Class(String str) {
            this.className = str;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            return element2.hasClass(this.className);
        }

        public String toString() {
            return String.format(".%s", this.className);
        }
    }

    public static final class Attribute extends Evaluator {
        private final String key;

        @Override // org.jsoup.select.Evaluator
        protected int cost() {
            return 2;
        }

        public Attribute(String str) {
            this.key = str;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            return element2.hasAttr(this.key);
        }

        public String toString() {
            return String.format("[%s]", this.key);
        }
    }

    public static final class AttributeStarting extends Evaluator {
        private final String keyPrefix;

        @Override // org.jsoup.select.Evaluator
        protected int cost() {
            return 6;
        }

        public AttributeStarting(String str) {
            Validate.notNull(str);
            this.keyPrefix = Normalizer.lowerCase(str);
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            Iterator<org.jsoup.nodes.Attribute> it = element2.attributes().asList().iterator();
            while (it.hasNext()) {
                if (Normalizer.lowerCase(it.next().getKey()).startsWith(this.keyPrefix)) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return String.format("[^%s]", this.keyPrefix);
        }
    }

    public static final class AttributeWithValue extends AttributeKeyPair {
        @Override // org.jsoup.select.Evaluator
        protected int cost() {
            return 3;
        }

        public AttributeWithValue(String str, String str2) {
            super(str, str2);
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            return element2.hasAttr(this.key) && this.value.equalsIgnoreCase(element2.attr(this.key));
        }

        public String toString() {
            return String.format("[%s=%s]", this.key, this.value);
        }
    }

    public static final class AttributeWithValueNot extends AttributeKeyPair {
        @Override // org.jsoup.select.Evaluator
        protected int cost() {
            return 3;
        }

        public AttributeWithValueNot(String str, String str2) {
            super(str, str2);
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            return !this.value.equalsIgnoreCase(element2.attr(this.key));
        }

        public String toString() {
            return String.format("[%s!=%s]", this.key, this.value);
        }
    }

    public static final class AttributeWithValueStarting extends AttributeKeyPair {
        @Override // org.jsoup.select.Evaluator
        protected int cost() {
            return 4;
        }

        public AttributeWithValueStarting(String str, String str2) {
            super(str, str2);
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            return element2.hasAttr(this.key) && Normalizer.lowerCase(element2.attr(this.key)).startsWith(this.value);
        }

        public String toString() {
            return String.format("[%s^=%s]", this.key, this.value);
        }
    }

    public static final class AttributeWithValueEnding extends AttributeKeyPair {
        @Override // org.jsoup.select.Evaluator
        protected int cost() {
            return 4;
        }

        public AttributeWithValueEnding(String str, String str2) {
            super(str, str2);
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            return element2.hasAttr(this.key) && Normalizer.lowerCase(element2.attr(this.key)).endsWith(this.value);
        }

        public String toString() {
            return String.format("[%s$=%s]", this.key, this.value);
        }
    }

    public static final class AttributeWithValueContaining extends AttributeKeyPair {
        @Override // org.jsoup.select.Evaluator
        protected int cost() {
            return 6;
        }

        public AttributeWithValueContaining(String str, String str2) {
            super(str, str2);
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            return element2.hasAttr(this.key) && Normalizer.lowerCase(element2.attr(this.key)).contains(this.value);
        }

        public String toString() {
            return String.format("[%s*=%s]", this.key, this.value);
        }
    }

    public static final class AttributeWithValueMatching extends Evaluator {
        final String key;
        final Regex pattern;

        @Override // org.jsoup.select.Evaluator
        protected int cost() {
            return 8;
        }

        public AttributeWithValueMatching(String str, Regex regex) {
            this.key = Normalizer.normalize(str);
            this.pattern = regex;
        }

        public AttributeWithValueMatching(String str, Pattern pattern) {
            this(str, Regex.fromPattern(pattern));
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            return element2.hasAttr(this.key) && this.pattern.matcher(element2.attr(this.key)).find();
        }

        public String toString() {
            return String.format("[%s~=%s]", this.key, this.pattern.toString());
        }
    }

    public static abstract class AttributeKeyPair extends Evaluator {
        final String key;
        final String value;

        public AttributeKeyPair(String str, String str2) {
            Validate.notEmpty(str);
            Validate.notNull(str2);
            this.key = Normalizer.normalize(str);
            if ((str2.startsWith("'") && str2.endsWith("'")) || (str2.startsWith("\"") && str2.endsWith("\""))) {
                Validate.isTrue(str2.length() > 1, "Quoted value must have content");
                str2 = str2.substring(1, str2.length() - 1);
            }
            this.value = Normalizer.lowerCase(str2);
        }

        @Deprecated
        public AttributeKeyPair(String str, String str2, boolean z) {
            this(str, str2);
        }
    }

    public static final class AllElements extends Evaluator {
        @Override // org.jsoup.select.Evaluator
        protected int cost() {
            return 10;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            return true;
        }

        public String toString() {
            return Marker.ANY_MARKER;
        }
    }

    public static final class IndexLessThan extends IndexEvaluator {
        public IndexLessThan(int i) {
            super(i);
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            return element != element2 && element2.elementSiblingIndex() < this.index;
        }

        public String toString() {
            return String.format(":lt(%d)", Integer.valueOf(this.index));
        }
    }

    public static final class IndexGreaterThan extends IndexEvaluator {
        public IndexGreaterThan(int i) {
            super(i);
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            return element2.elementSiblingIndex() > this.index;
        }

        public String toString() {
            return String.format(":gt(%d)", Integer.valueOf(this.index));
        }
    }

    public static final class IndexEquals extends IndexEvaluator {
        public IndexEquals(int i) {
            super(i);
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            return element2.elementSiblingIndex() == this.index;
        }

        public String toString() {
            return String.format(":eq(%d)", Integer.valueOf(this.index));
        }
    }

    public static final class IsLastChild extends Evaluator {
        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            Element elementParent = element2.parent();
            return (elementParent == null || (elementParent instanceof Document) || element2 != elementParent.lastElementChild()) ? false : true;
        }

        public String toString() {
            return ":last-child";
        }
    }

    public static final class IsFirstOfType extends IsNthOfType {
        public IsFirstOfType() {
            super(0, 1);
        }

        @Override // org.jsoup.select.Evaluator.CssNthEvaluator
        public String toString() {
            return ":first-of-type";
        }
    }

    public static final class IsLastOfType extends IsNthLastOfType {
        public IsLastOfType() {
            super(0, 1);
        }

        @Override // org.jsoup.select.Evaluator.CssNthEvaluator
        public String toString() {
            return ":last-of-type";
        }
    }

    public static abstract class CssNthEvaluator extends Evaluator {
        protected final int a;
        protected final int b;

        protected abstract int calculatePosition(Element element, Element element2);

        protected abstract String getPseudoClass();

        public CssNthEvaluator(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        public CssNthEvaluator(int i) {
            this(0, i);
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            Element elementParent = element2.parent();
            if (elementParent != null && !(elementParent instanceof Document)) {
                int iCalculatePosition = calculatePosition(element, element2);
                int i = this.a;
                int i2 = this.b;
                if (i == 0) {
                    return iCalculatePosition == i2;
                }
                if ((iCalculatePosition - i2) * i >= 0 && (iCalculatePosition - i2) % i == 0) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            String str;
            if (this.a == 0) {
                str = ":%s(%3$d)";
            } else {
                str = this.b == 0 ? ":%s(%2$dn)" : ":%s(%2$dn%3$+d)";
            }
            return String.format(str, getPseudoClass(), Integer.valueOf(this.a), Integer.valueOf(this.b));
        }
    }

    public static final class IsNthChild extends CssNthEvaluator {
        public IsNthChild(int i, int i2) {
            super(i, i2);
        }

        @Override // org.jsoup.select.Evaluator.CssNthEvaluator
        protected int calculatePosition(Element element, Element element2) {
            return element2.elementSiblingIndex() + 1;
        }

        @Override // org.jsoup.select.Evaluator.CssNthEvaluator
        protected String getPseudoClass() {
            return "nth-child";
        }
    }

    public static final class IsNthLastChild extends CssNthEvaluator {
        public IsNthLastChild(int i, int i2) {
            super(i, i2);
        }

        @Override // org.jsoup.select.Evaluator.CssNthEvaluator
        protected int calculatePosition(Element element, Element element2) {
            if (element2.parent() == null) {
                return 0;
            }
            return element2.parent().childrenSize() - element2.elementSiblingIndex();
        }

        @Override // org.jsoup.select.Evaluator.CssNthEvaluator
        protected String getPseudoClass() {
            return "nth-last-child";
        }
    }

    public static class IsNthOfType extends CssNthEvaluator {
        public IsNthOfType(int i, int i2) {
            super(i, i2);
        }

        @Override // org.jsoup.select.Evaluator.CssNthEvaluator
        protected int calculatePosition(Element element, Element element2) {
            Element elementParent = element2.parent();
            if (elementParent == null) {
                return 0;
            }
            int iChildNodeSize = elementParent.childNodeSize();
            int i = 0;
            for (int i2 = 0; i2 < iChildNodeSize; i2++) {
                Node nodeChildNode = elementParent.childNode(i2);
                if (nodeChildNode.normalName().equals(element2.normalName())) {
                    i++;
                }
                if (nodeChildNode == element2) {
                    return i;
                }
            }
            return i;
        }

        @Override // org.jsoup.select.Evaluator.CssNthEvaluator
        protected String getPseudoClass() {
            return "nth-of-type";
        }
    }

    public static class IsNthLastOfType extends CssNthEvaluator {
        public IsNthLastOfType(int i, int i2) {
            super(i, i2);
        }

        @Override // org.jsoup.select.Evaluator.CssNthEvaluator
        protected int calculatePosition(Element element, Element element2) {
            int i = 0;
            if (element2.parent() == null) {
                return 0;
            }
            for (Element elementNextElementSibling = element2; elementNextElementSibling != null; elementNextElementSibling = elementNextElementSibling.nextElementSibling()) {
                if (elementNextElementSibling.normalName().equals(element2.normalName())) {
                    i++;
                }
            }
            return i;
        }

        @Override // org.jsoup.select.Evaluator.CssNthEvaluator
        protected String getPseudoClass() {
            return "nth-last-of-type";
        }
    }

    public static final class IsFirstChild extends Evaluator {
        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            Element elementParent = element2.parent();
            return (elementParent == null || (elementParent instanceof Document) || element2 != elementParent.firstElementChild()) ? false : true;
        }

        public String toString() {
            return ":first-child";
        }
    }

    public static final class IsRoot extends Evaluator {
        @Override // org.jsoup.select.Evaluator
        protected int cost() {
            return 1;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            if (element instanceof Document) {
                element = element.firstElementChild();
            }
            return element2 == element;
        }

        public String toString() {
            return ":root";
        }
    }

    public static final class IsOnlyChild extends Evaluator {
        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            Element elementParent = element2.parent();
            return (elementParent == null || (elementParent instanceof Document) || !element2.siblingElements().isEmpty()) ? false : true;
        }

        public String toString() {
            return ":only-child";
        }
    }

    public static final class IsOnlyOfType extends Evaluator {
        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            Element elementParent = element2.parent();
            if (elementParent != null && !(elementParent instanceof Document)) {
                int i = 0;
                for (Element elementFirstElementChild = elementParent.firstElementChild(); elementFirstElementChild != null; elementFirstElementChild = elementFirstElementChild.nextElementSibling()) {
                    if (elementFirstElementChild.normalName().equals(element2.normalName())) {
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

    public static final class IsEmpty extends Evaluator {
        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            for (Node nodeFirstChild = element2.firstChild(); nodeFirstChild != null; nodeFirstChild = nodeFirstChild.nextSibling()) {
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

    public static abstract class IndexEvaluator extends Evaluator {
        final int index;

        public IndexEvaluator(int i) {
            this.index = i;
        }
    }

    public static final class ContainsText extends Evaluator {
        private final String searchText;

        @Override // org.jsoup.select.Evaluator
        protected int cost() {
            return 10;
        }

        public ContainsText(String str) {
            this.searchText = Normalizer.lowerCase(StringUtil.normaliseWhitespace(str));
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            return Normalizer.lowerCase(element2.text()).contains(this.searchText);
        }

        public String toString() {
            return String.format(":contains(%s)", this.searchText);
        }
    }

    public static final class ContainsWholeText extends Evaluator {
        private final String searchText;

        @Override // org.jsoup.select.Evaluator
        protected int cost() {
            return 10;
        }

        public ContainsWholeText(String str) {
            this.searchText = str;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            return element2.wholeText().contains(this.searchText);
        }

        public String toString() {
            return String.format(":containsWholeText(%s)", this.searchText);
        }
    }

    public static final class ContainsWholeOwnText extends Evaluator {
        private final String searchText;

        public ContainsWholeOwnText(String str) {
            this.searchText = str;
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            return element2.wholeOwnText().contains(this.searchText);
        }

        public String toString() {
            return String.format(":containsWholeOwnText(%s)", this.searchText);
        }
    }

    public static final class ContainsData extends Evaluator {
        private final String searchText;

        public ContainsData(String str) {
            this.searchText = Normalizer.lowerCase(str);
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            return Normalizer.lowerCase(element2.data()).contains(this.searchText);
        }

        public String toString() {
            return String.format(":containsData(%s)", this.searchText);
        }
    }

    public static final class ContainsOwnText extends Evaluator {
        private final String searchText;

        public ContainsOwnText(String str) {
            this.searchText = Normalizer.lowerCase(StringUtil.normaliseWhitespace(str));
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            return Normalizer.lowerCase(element2.ownText()).contains(this.searchText);
        }

        public String toString() {
            return String.format(":containsOwn(%s)", this.searchText);
        }
    }

    public static final class Matches extends Evaluator {
        private final Regex pattern;

        @Override // org.jsoup.select.Evaluator
        protected int cost() {
            return 8;
        }

        public Matches(Regex regex) {
            this.pattern = regex;
        }

        public Matches(Pattern pattern) {
            this(Regex.fromPattern(pattern));
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            return this.pattern.matcher(element2.text()).find();
        }

        public String toString() {
            return String.format(":matches(%s)", this.pattern);
        }
    }

    public static final class MatchesOwn extends Evaluator {
        private final Regex pattern;

        @Override // org.jsoup.select.Evaluator
        protected int cost() {
            return 7;
        }

        public MatchesOwn(Regex regex) {
            this.pattern = regex;
        }

        public MatchesOwn(Pattern pattern) {
            this(Regex.fromPattern(pattern));
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            return this.pattern.matcher(element2.ownText()).find();
        }

        public String toString() {
            return String.format(":matchesOwn(%s)", this.pattern);
        }
    }

    public static final class MatchesWholeText extends Evaluator {
        private final Regex pattern;

        @Override // org.jsoup.select.Evaluator
        protected int cost() {
            return 8;
        }

        public MatchesWholeText(Regex regex) {
            this.pattern = regex;
        }

        public MatchesWholeText(Pattern pattern) {
            this.pattern = Regex.fromPattern(pattern);
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            return this.pattern.matcher(element2.wholeText()).find();
        }

        public String toString() {
            return String.format(":matchesWholeText(%s)", this.pattern);
        }
    }

    public static final class MatchesWholeOwnText extends Evaluator {
        private final Regex pattern;

        @Override // org.jsoup.select.Evaluator
        protected int cost() {
            return 7;
        }

        public MatchesWholeOwnText(Regex regex) {
            this.pattern = regex;
        }

        public MatchesWholeOwnText(Pattern pattern) {
            this(Regex.fromPattern(pattern));
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            return this.pattern.matcher(element2.wholeOwnText()).find();
        }

        public String toString() {
            return String.format(":matchesWholeOwnText(%s)", this.pattern);
        }
    }

    @Deprecated
    public static final class MatchText extends Evaluator {
        private static boolean loggedError = false;

        @Override // org.jsoup.select.Evaluator
        protected int cost() {
            return -1;
        }

        public MatchText() {
            if (loggedError) {
                return;
            }
            loggedError = true;
            System.err.println("WARNING: :matchText selector is deprecated and will be removed in jsoup 1.24.1. Use Element#selectNodes(String, Class) with selector ::textnode and class TextNode instead.");
        }

        @Override // org.jsoup.select.Evaluator
        /* JADX INFO: renamed from: matches */
        public boolean m10844lambda$asPredicate$0$orgjsoupselectEvaluator(Element element, Element element2) {
            if (element2 instanceof PseudoTextElement) {
                return true;
            }
            for (TextNode textNode : element2.textNodes()) {
                PseudoTextElement pseudoTextElement = new PseudoTextElement(org.jsoup.parser.Tag.valueOf(element2.tagName(), element2.tag().namespace(), ParseSettings.preserveCase), element2.baseUri(), element2.attributes());
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
