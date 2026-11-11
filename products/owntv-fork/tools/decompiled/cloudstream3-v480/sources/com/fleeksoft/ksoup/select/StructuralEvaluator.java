package com.fleeksoft.ksoup.select;

import com.fleeksoft.ksoup.internal.SoftPool;
import com.fleeksoft.ksoup.internal.StringUtil;
import com.fleeksoft.ksoup.nodes.Element;
import com.fleeksoft.ksoup.nodes.LeafNode;
import com.fleeksoft.ksoup.nodes.Node;
import com.fleeksoft.ksoup.nodes.NodeIterator;
import com.fleeksoft.ksoup.nodes.TextNode;
import com.fleeksoft.ksoup.ported.IdentityHashMap;
import com.fleeksoft.ksoup.ported.ThreadLocal;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: compiled from: StructuralEvaluator.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b&\u0018\u00002\u00020\u0001:\b\u001e\u001f !\"#$%B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0016\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0010J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0018\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0016J\u0018\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0018\u0010\u001c\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u0010H&R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR/\u0010\r\u001a \u0012\u001c\u0012\u001a\u0012\u0004\u0012\u00020\u0010\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\b0\u000f0\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006&"}, d2 = {"Lcom/fleeksoft/ksoup/select/StructuralEvaluator;", "Lcom/fleeksoft/ksoup/select/Evaluator;", "evaluator", "<init>", "(Lcom/fleeksoft/ksoup/select/Evaluator;)V", "getEvaluator", "()Lcom/fleeksoft/ksoup/select/Evaluator;", "wantsNodes", "", "getWantsNodes", "()Z", "setWantsNodes", "(Z)V", "threadMemo", "Lcom/fleeksoft/ksoup/ported/ThreadLocal;", "Lcom/fleeksoft/ksoup/ported/IdentityHashMap;", "Lcom/fleeksoft/ksoup/nodes/Node;", "getThreadMemo", "()Lcom/fleeksoft/ksoup/ported/ThreadLocal;", "memoMatches", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "reset", "", "matches", "leafNode", "Lcom/fleeksoft/ksoup/nodes/LeafNode;", "evaluateMatch", "node", "Root", "Has", "Is", "Not", "Ancestor", "ImmediateParentRun", "PreviousSibling", "ImmediatePreviousSibling", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class StructuralEvaluator extends Evaluator {
    private final Evaluator evaluator;
    private final ThreadLocal<IdentityHashMap<Node, IdentityHashMap<Node, Boolean>>> threadMemo;
    private boolean wantsNodes;

    public abstract boolean evaluateMatch(Element root, Node node);

    public StructuralEvaluator(Evaluator evaluator) {
        Intrinsics.checkNotNullParameter(evaluator, "evaluator");
        this.evaluator = evaluator;
        this.wantsNodes = evaluator.wantsNodes();
        this.threadMemo = new ThreadLocal<>(new Function0() { // from class: com.fleeksoft.ksoup.select.StructuralEvaluator$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return StructuralEvaluator.threadMemo$lambda$0();
            }
        });
    }

    public final Evaluator getEvaluator() {
        return this.evaluator;
    }

    public final boolean getWantsNodes() {
        return this.wantsNodes;
    }

    public final void setWantsNodes(boolean z) {
        this.wantsNodes = z;
    }

    @Override // com.fleeksoft.ksoup.select.Evaluator
    public boolean wantsNodes() {
        return this.wantsNodes;
    }

    public final ThreadLocal<IdentityHashMap<Node, IdentityHashMap<Node, Boolean>>> getThreadMemo() {
        return this.threadMemo;
    }

    static final IdentityHashMap threadMemo$lambda$0() {
        return new IdentityHashMap();
    }

    public final boolean memoMatches(Element root, Node element) {
        Intrinsics.checkNotNullParameter(root, "root");
        Intrinsics.checkNotNullParameter(element, "element");
        IdentityHashMap<Node, IdentityHashMap<Node, Boolean>> identityHashMap = this.threadMemo.get();
        IdentityHashMap<Node, Boolean> identityHashMap2 = identityHashMap.get(root);
        if (identityHashMap2 == null) {
            identityHashMap2 = new IdentityHashMap<>();
            identityHashMap.put(root, identityHashMap2);
        }
        IdentityHashMap<Node, Boolean> identityHashMap3 = identityHashMap2;
        Boolean boolValueOf = identityHashMap3.get(element);
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.evaluator.matches(root, element));
            identityHashMap3.put(element, boolValueOf);
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.fleeksoft.ksoup.select.Evaluator
    public void reset() {
        this.threadMemo.get().clear();
        this.evaluator.reset();
        super.reset();
    }

    @Override // com.fleeksoft.ksoup.select.Evaluator
    public boolean matches(Element root, Element element) {
        Intrinsics.checkNotNullParameter(root, "root");
        Intrinsics.checkNotNullParameter(element, "element");
        return evaluateMatch(root, element);
    }

    @Override // com.fleeksoft.ksoup.select.Evaluator
    public boolean matches(Element root, LeafNode leafNode) {
        Intrinsics.checkNotNullParameter(root, "root");
        Intrinsics.checkNotNullParameter(leafNode, "leafNode");
        return evaluateMatch(root, leafNode);
    }

    /* JADX INFO: compiled from: StructuralEvaluator.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\n\u0010\u000b\u001a\u00020\fH\u0096\u0080\u0004¨\u0006\r"}, d2 = {"Lcom/fleeksoft/ksoup/select/StructuralEvaluator$Root;", "Lcom/fleeksoft/ksoup/select/Evaluator;", "<init>", "()V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "cost", "", "toString", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Root extends Evaluator {
        @Override // com.fleeksoft.ksoup.select.Evaluator
        /* JADX INFO: renamed from: cost */
        public int get_cost() {
            return 1;
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            return root == element;
        }

        public String toString() {
            return ">";
        }
    }

    /* JADX INFO: compiled from: StructuralEvaluator.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0018\u0010\f\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0003H\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\n\u0010\u0013\u001a\u00020\u0014H\u0096\u0080\u0004R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/fleeksoft/ksoup/select/StructuralEvaluator$Has;", "Lcom/fleeksoft/ksoup/select/StructuralEvaluator;", "evaluator", "Lcom/fleeksoft/ksoup/select/Evaluator;", "<init>", "(Lcom/fleeksoft/ksoup/select/Evaluator;)V", "checkSiblings", "", "matches", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "evaluateMatch", "node", "Lcom/fleeksoft/ksoup/nodes/Node;", "evalWantsSiblings", "eval", "cost", "", "toString", "", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Has extends StructuralEvaluator {
        private static final SoftPool<NodeIterator<Node>> NodeIterPool = new SoftPool<>(new Function0() { // from class: com.fleeksoft.ksoup.select.StructuralEvaluator$Has$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return StructuralEvaluator.Has.NodeIterPool$lambda$0();
            }
        });
        private final boolean checkSiblings;

        @Override // com.fleeksoft.ksoup.select.StructuralEvaluator
        public boolean evaluateMatch(Element root, Node node) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(node, "node");
            return false;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Has(Evaluator evaluator) {
            super(evaluator);
            Intrinsics.checkNotNullParameter(evaluator, "evaluator");
            this.checkSiblings = evalWantsSiblings(evaluator);
        }

        static final NodeIterator NodeIterPool$lambda$0() {
            return new NodeIterator(new TextNode(""), Reflection.getOrCreateKotlinClass(Node.class));
        }

        @Override // com.fleeksoft.ksoup.select.StructuralEvaluator, com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            if (this.checkSiblings) {
                for (Element elementFirstElementSibling = element.firstElementSibling(); elementFirstElementSibling != null; elementFirstElementSibling = elementFirstElementSibling.nextElementSibling()) {
                    if (elementFirstElementSibling != element && getEvaluator().matches(element, elementFirstElementSibling)) {
                        return true;
                    }
                }
            }
            NodeIterator<Node> nodeIteratorBorrow = NodeIterPool.borrow();
            nodeIteratorBorrow.restart(element);
            while (nodeIteratorBorrow.hasNext()) {
                try {
                    Node next = nodeIteratorBorrow.next();
                    if (next != element && getEvaluator().matches(element, next)) {
                        return true;
                    }
                } finally {
                    NodeIterPool.release(nodeIteratorBorrow);
                }
            }
            NodeIterPool.release(nodeIteratorBorrow);
            return false;
        }

        private final boolean evalWantsSiblings(Evaluator eval) {
            if (!(eval instanceof CombiningEvaluator)) {
                return false;
            }
            Iterator<Evaluator> it = ((CombiningEvaluator) eval).getEvaluators().iterator();
            Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
            while (it.hasNext()) {
                Evaluator next = it.next();
                Intrinsics.checkNotNullExpressionValue(next, "next(...)");
                Evaluator evaluator = next;
                if ((evaluator instanceof PreviousSibling) || (evaluator instanceof ImmediatePreviousSibling)) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        /* JADX INFO: renamed from: cost */
        public int get_cost() {
            return getEvaluator().get_cost() * 10;
        }

        public String toString() {
            return ":has(" + getEvaluator() + ")";
        }
    }

    /* JADX INFO: compiled from: StructuralEvaluator.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\n\u0010\u000e\u001a\u00020\u000fH\u0096\u0080\u0004¨\u0006\u0010"}, d2 = {"Lcom/fleeksoft/ksoup/select/StructuralEvaluator$Is;", "Lcom/fleeksoft/ksoup/select/StructuralEvaluator;", "evaluator", "Lcom/fleeksoft/ksoup/select/Evaluator;", "<init>", "(Lcom/fleeksoft/ksoup/select/Evaluator;)V", "evaluateMatch", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "node", "Lcom/fleeksoft/ksoup/nodes/Node;", "cost", "", "toString", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Is extends StructuralEvaluator {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Is(Evaluator evaluator) {
            super(evaluator);
            Intrinsics.checkNotNullParameter(evaluator, "evaluator");
        }

        @Override // com.fleeksoft.ksoup.select.StructuralEvaluator
        public boolean evaluateMatch(Element root, Node node) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(node, "node");
            return getEvaluator().matches(root, node);
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        /* JADX INFO: renamed from: cost */
        public int get_cost() {
            return getEvaluator().get_cost() + 2;
        }

        public String toString() {
            return ":is(" + getEvaluator() + ")";
        }
    }

    /* JADX INFO: compiled from: StructuralEvaluator.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\n\u0010\u000e\u001a\u00020\u000fH\u0096\u0080\u0004¨\u0006\u0010"}, d2 = {"Lcom/fleeksoft/ksoup/select/StructuralEvaluator$Not;", "Lcom/fleeksoft/ksoup/select/StructuralEvaluator;", "evaluator", "Lcom/fleeksoft/ksoup/select/Evaluator;", "<init>", "(Lcom/fleeksoft/ksoup/select/Evaluator;)V", "evaluateMatch", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "node", "Lcom/fleeksoft/ksoup/nodes/Node;", "cost", "", "toString", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Not extends StructuralEvaluator {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Not(Evaluator evaluator) {
            super(evaluator);
            Intrinsics.checkNotNullParameter(evaluator, "evaluator");
        }

        @Override // com.fleeksoft.ksoup.select.StructuralEvaluator
        public boolean evaluateMatch(Element root, Node node) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(node, "node");
            return !memoMatches(root, node);
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        /* JADX INFO: renamed from: cost */
        public int get_cost() {
            return getEvaluator().get_cost() + 2;
        }

        public String toString() {
            return ":not(" + getEvaluator() + ")";
        }
    }

    /* JADX INFO: compiled from: StructuralEvaluator.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\n\u0010\u000e\u001a\u00020\u000fH\u0096\u0080\u0004¨\u0006\u0010"}, d2 = {"Lcom/fleeksoft/ksoup/select/StructuralEvaluator$Ancestor;", "Lcom/fleeksoft/ksoup/select/StructuralEvaluator;", "evaluator", "Lcom/fleeksoft/ksoup/select/Evaluator;", "<init>", "(Lcom/fleeksoft/ksoup/select/Evaluator;)V", "evaluateMatch", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "node", "Lcom/fleeksoft/ksoup/nodes/Node;", "cost", "", "toString", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Ancestor extends StructuralEvaluator {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Ancestor(Evaluator evaluator) {
            super(evaluator);
            Intrinsics.checkNotNullParameter(evaluator, "evaluator");
        }

        @Override // com.fleeksoft.ksoup.select.StructuralEvaluator
        public boolean evaluateMatch(Element root, Node node) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(node, "node");
            if (root == node) {
                return false;
            }
            for (Node nodeParent = node.parent(); nodeParent != null; nodeParent = nodeParent.parent()) {
                if (memoMatches(root, nodeParent)) {
                    return true;
                }
                if (nodeParent == root) {
                    break;
                }
            }
            return false;
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        /* JADX INFO: renamed from: cost */
        public int get_cost() {
            return getEvaluator().get_cost() * 8;
        }

        public String toString() {
            return getEvaluator() + Stream.ID_UNKNOWN;
        }
    }

    /* JADX INFO: compiled from: StructuralEvaluator.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u0003J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\fH\u0016J\b\u0010\u0016\u001a\u00020\u000eH\u0016J\n\u0010\u0017\u001a\u00020\u0018H\u0096\u0080\u0004R!\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0007j\b\u0012\u0004\u0012\u00020\u0003`\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/fleeksoft/ksoup/select/StructuralEvaluator$ImmediateParentRun;", "Lcom/fleeksoft/ksoup/select/StructuralEvaluator;", "evaluator", "Lcom/fleeksoft/ksoup/select/Evaluator;", "<init>", "(Lcom/fleeksoft/ksoup/select/Evaluator;)V", "evaluators", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getEvaluators", "()Ljava/util/ArrayList;", "_cost", "", "add", "", "evaluateMatch", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "node", "Lcom/fleeksoft/ksoup/nodes/Node;", "cost", "reset", "toString", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class ImmediateParentRun extends StructuralEvaluator {
        private int _cost;
        private final ArrayList<Evaluator> evaluators;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ImmediateParentRun(Evaluator evaluator) {
            super(evaluator);
            Intrinsics.checkNotNullParameter(evaluator, "evaluator");
            ArrayList<Evaluator> arrayList = new ArrayList<>();
            this.evaluators = arrayList;
            this._cost = 2;
            arrayList.add(evaluator);
            this._cost += evaluator.get_cost();
        }

        public final ArrayList<Evaluator> getEvaluators() {
            return this.evaluators;
        }

        public final void add(Evaluator evaluator) {
            Intrinsics.checkNotNullParameter(evaluator, "evaluator");
            this.evaluators.add(evaluator);
            this._cost += evaluator.get_cost();
            setWantsNodes(evaluator.wantsNodes() | getWantsNodes());
        }

        @Override // com.fleeksoft.ksoup.select.StructuralEvaluator
        public boolean evaluateMatch(Element root, Node node) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(node, "node");
            if (node == root) {
                return false;
            }
            int size = this.evaluators.size() - 1;
            if (size < 0) {
                return true;
            }
            while (true) {
                int i = size - 1;
                if (node == null) {
                    return false;
                }
                Evaluator evaluator = this.evaluators.get(size);
                Intrinsics.checkNotNullExpressionValue(evaluator, "get(...)");
                if (!evaluator.matches(root, node)) {
                    return false;
                }
                node = node.parent();
                if (i < 0) {
                    return true;
                }
                size = i;
            }
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        /* JADX INFO: renamed from: cost, reason: from getter */
        public int get_cost() {
            return this._cost;
        }

        @Override // com.fleeksoft.ksoup.select.StructuralEvaluator, com.fleeksoft.ksoup.select.Evaluator
        public void reset() {
            Iterator<Evaluator> it = this.evaluators.iterator();
            Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
            while (it.hasNext()) {
                Evaluator next = it.next();
                Intrinsics.checkNotNullExpressionValue(next, "next(...)");
                next.reset();
            }
            super.reset();
        }

        public String toString() {
            return StringUtil.INSTANCE.join(this.evaluators, " > ");
        }
    }

    /* JADX INFO: compiled from: StructuralEvaluator.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\n\u0010\u000e\u001a\u00020\u000fH\u0096\u0080\u0004¨\u0006\u0010"}, d2 = {"Lcom/fleeksoft/ksoup/select/StructuralEvaluator$PreviousSibling;", "Lcom/fleeksoft/ksoup/select/StructuralEvaluator;", "evaluator", "Lcom/fleeksoft/ksoup/select/Evaluator;", "<init>", "(Lcom/fleeksoft/ksoup/select/Evaluator;)V", "evaluateMatch", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "node", "Lcom/fleeksoft/ksoup/nodes/Node;", "cost", "", "toString", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class PreviousSibling extends StructuralEvaluator {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PreviousSibling(Evaluator evaluator) {
            super(evaluator);
            Intrinsics.checkNotNullParameter(evaluator, "evaluator");
        }

        @Override // com.fleeksoft.ksoup.select.StructuralEvaluator
        public boolean evaluateMatch(Element root, Node node) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(node, "node");
            if (root == node) {
                return false;
            }
            for (Element elementFirstSibling = node.firstSibling(); elementFirstSibling != null && elementFirstSibling != node; elementFirstSibling = elementFirstSibling.nextElementSibling()) {
                if (memoMatches(root, elementFirstSibling)) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        /* JADX INFO: renamed from: cost */
        public int get_cost() {
            return getEvaluator().get_cost() * 3;
        }

        public String toString() {
            return getEvaluator() + " ~ ";
        }
    }

    /* JADX INFO: compiled from: StructuralEvaluator.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\n\u0010\u000e\u001a\u00020\u000fH\u0096\u0080\u0004¨\u0006\u0010"}, d2 = {"Lcom/fleeksoft/ksoup/select/StructuralEvaluator$ImmediatePreviousSibling;", "Lcom/fleeksoft/ksoup/select/StructuralEvaluator;", "evaluator", "Lcom/fleeksoft/ksoup/select/Evaluator;", "<init>", "(Lcom/fleeksoft/ksoup/select/Evaluator;)V", "evaluateMatch", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "node", "Lcom/fleeksoft/ksoup/nodes/Node;", "cost", "", "toString", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class ImmediatePreviousSibling extends StructuralEvaluator {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ImmediatePreviousSibling(Evaluator evaluator) {
            super(evaluator);
            Intrinsics.checkNotNullParameter(evaluator, "evaluator");
        }

        @Override // com.fleeksoft.ksoup.select.StructuralEvaluator
        public boolean evaluateMatch(Element root, Node node) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(node, "node");
            if (root == node) {
                return false;
            }
            Element elementPreviousSibling = getWantsNodes() ? node.previousSibling() : node.previousElementSibling();
            return elementPreviousSibling != null && memoMatches(root, elementPreviousSibling);
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        /* JADX INFO: renamed from: cost */
        public int get_cost() {
            return getEvaluator().get_cost() + 2;
        }

        public String toString() {
            return getEvaluator() + " + ";
        }
    }
}
