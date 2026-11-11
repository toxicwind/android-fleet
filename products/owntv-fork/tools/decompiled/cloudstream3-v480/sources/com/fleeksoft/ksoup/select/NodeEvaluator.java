package com.fleeksoft.ksoup.select;

import com.fleeksoft.ksoup.internal.Normalizer;
import com.fleeksoft.ksoup.internal.StringUtil;
import com.fleeksoft.ksoup.nodes.Element;
import com.fleeksoft.ksoup.nodes.LeafNode;
import com.fleeksoft.ksoup.nodes.Node;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: NodeEvaluator.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u0001:\u0004\u000f\u0010\u0011\u0012B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH$J\b\u0010\u000e\u001a\u00020\u0005H\u0016¨\u0006\u0013"}, d2 = {"Lcom/fleeksoft/ksoup/select/NodeEvaluator;", "Lcom/fleeksoft/ksoup/select/Evaluator;", "<init>", "()V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "leafNode", "Lcom/fleeksoft/ksoup/nodes/LeafNode;", "evaluateMatch", "node", "Lcom/fleeksoft/ksoup/nodes/Node;", "wantsNodes", "InstanceType", "ContainsValue", "BlankValue", "MatchesValue", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class NodeEvaluator extends Evaluator {
    protected abstract boolean evaluateMatch(Node node);

    @Override // com.fleeksoft.ksoup.select.Evaluator
    public boolean wantsNodes() {
        return true;
    }

    protected NodeEvaluator() {
    }

    @Override // com.fleeksoft.ksoup.select.Evaluator
    public boolean matches(Element root, Element element) {
        Intrinsics.checkNotNullParameter(root, "root");
        Intrinsics.checkNotNullParameter(element, "element");
        return evaluateMatch(element);
    }

    @Override // com.fleeksoft.ksoup.select.Evaluator
    public boolean matches(Element root, LeafNode leafNode) {
        Intrinsics.checkNotNullParameter(root, "root");
        Intrinsics.checkNotNullParameter(leafNode, "leafNode");
        return evaluateMatch(leafNode);
    }

    /* JADX INFO: compiled from: NodeEvaluator.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0004H\u0014J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\n\u0010\u0012\u001a\u00020\u0006H\u0096\u0080\u0004R\u0019\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0013"}, d2 = {"Lcom/fleeksoft/ksoup/select/NodeEvaluator$InstanceType;", "Lcom/fleeksoft/ksoup/select/NodeEvaluator;", "type", "Lkotlin/reflect/KClass;", "Lcom/fleeksoft/ksoup/nodes/Node;", "selector", "", "<init>", "(Lkotlin/reflect/KClass;Ljava/lang/String;)V", "getType", "()Lkotlin/reflect/KClass;", "getSelector", "()Ljava/lang/String;", "evaluateMatch", "", "node", "cost", "", "toString", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class InstanceType extends NodeEvaluator {
        private final String selector;
        private final KClass<? extends Node> type;

        @Override // com.fleeksoft.ksoup.select.Evaluator
        /* JADX INFO: renamed from: cost */
        public int get_cost() {
            return 1;
        }

        public InstanceType(KClass<? extends Node> type, String selector) {
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(selector, "selector");
            this.type = type;
            this.selector = selector;
        }

        public final KClass<? extends Node> getType() {
            return this.type;
        }

        public final String getSelector() {
            return this.selector;
        }

        @Override // com.fleeksoft.ksoup.select.NodeEvaluator
        protected boolean evaluateMatch(Node node) {
            Intrinsics.checkNotNullParameter(node, "node");
            return this.type.isInstance(node);
        }

        public String toString() {
            return "::" + this.selector;
        }
    }

    /* JADX INFO: compiled from: NodeEvaluator.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0014J\b\u0010\n\u001a\u00020\u000bH\u0016J\n\u0010\f\u001a\u00020\u0003H\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/fleeksoft/ksoup/select/NodeEvaluator$ContainsValue;", "Lcom/fleeksoft/ksoup/select/NodeEvaluator;", "searchText", "", "<init>", "(Ljava/lang/String;)V", "evaluateMatch", "", "node", "Lcom/fleeksoft/ksoup/nodes/Node;", "cost", "", "toString", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class ContainsValue extends NodeEvaluator {
        private final String searchText;

        @Override // com.fleeksoft.ksoup.select.Evaluator
        /* JADX INFO: renamed from: cost */
        public int get_cost() {
            return 6;
        }

        public ContainsValue(String searchText) {
            Intrinsics.checkNotNullParameter(searchText, "searchText");
            this.searchText = Normalizer.INSTANCE.lowerCase(StringUtil.INSTANCE.normaliseWhitespace(searchText));
        }

        @Override // com.fleeksoft.ksoup.select.NodeEvaluator
        protected boolean evaluateMatch(Node node) {
            Intrinsics.checkNotNullParameter(node, "node");
            return StringsKt.contains$default((CharSequence) Normalizer.INSTANCE.lowerCase(node.nodeValue()), (CharSequence) this.searchText, false, 2, (Object) null);
        }

        public String toString() {
            return ":contains(" + this.searchText + ")";
        }
    }

    /* JADX INFO: compiled from: NodeEvaluator.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0014J\b\u0010\b\u001a\u00020\tH\u0016J\n\u0010\n\u001a\u00020\u000bH\u0096\u0080\u0004¨\u0006\f"}, d2 = {"Lcom/fleeksoft/ksoup/select/NodeEvaluator$BlankValue;", "Lcom/fleeksoft/ksoup/select/NodeEvaluator;", "<init>", "()V", "evaluateMatch", "", "node", "Lcom/fleeksoft/ksoup/nodes/Node;", "cost", "", "toString", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class BlankValue extends NodeEvaluator {
        @Override // com.fleeksoft.ksoup.select.Evaluator
        /* JADX INFO: renamed from: cost */
        public int get_cost() {
            return 4;
        }

        @Override // com.fleeksoft.ksoup.select.NodeEvaluator
        protected boolean evaluateMatch(Node node) {
            Intrinsics.checkNotNullParameter(node, "node");
            return StringUtil.INSTANCE.isBlank(node.nodeValue());
        }

        public String toString() {
            return ":blank";
        }
    }

    /* JADX INFO: compiled from: NodeEvaluator.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0014J\b\u0010\n\u001a\u00020\u000bH\u0016J\n\u0010\f\u001a\u00020\rH\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/fleeksoft/ksoup/select/NodeEvaluator$MatchesValue;", "Lcom/fleeksoft/ksoup/select/NodeEvaluator;", "pattern", "Lkotlin/text/Regex;", "<init>", "(Lkotlin/text/Regex;)V", "evaluateMatch", "", "node", "Lcom/fleeksoft/ksoup/nodes/Node;", "cost", "", "toString", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class MatchesValue extends NodeEvaluator {
        private final Regex pattern;

        @Override // com.fleeksoft.ksoup.select.Evaluator
        /* JADX INFO: renamed from: cost */
        public int get_cost() {
            return 8;
        }

        public MatchesValue(Regex pattern) {
            Intrinsics.checkNotNullParameter(pattern, "pattern");
            this.pattern = pattern;
        }

        @Override // com.fleeksoft.ksoup.select.NodeEvaluator
        protected boolean evaluateMatch(Node node) {
            Intrinsics.checkNotNullParameter(node, "node");
            return Regex.find$default(this.pattern, node.nodeValue(), 0, 2, null) != null;
        }

        public String toString() {
            return ":matches(" + this.pattern.getPattern() + ")";
        }
    }
}
