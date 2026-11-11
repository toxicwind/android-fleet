package com.fleeksoft.ksoup.select;

import com.fleeksoft.ksoup.internal.StringUtil;
import com.fleeksoft.ksoup.nodes.Element;
import com.fleeksoft.ksoup.nodes.LeafNode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CombiningEvaluator.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\b&\u0018\u00002\u00020\u0001:\u0002 !B\t\b\u0000¢\u0006\u0004\b\u0002\u0010\u0003B\u0017\b\u0010\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0004\b\u0002\u0010\u0006J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0001H\u0016J\b\u0010\u001d\u001a\u00020\u001bH\u0016J\b\u0010\u001e\u001a\u00020\u000eH\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0006\u0010\u001f\u001a\u00020\u001bR!\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u0007j\b\u0012\u0004\u0012\u00020\u0001`\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR!\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u0007j\b\u0012\u0004\u0012\u00020\u0001`\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u001a\u0010\r\u001a\u00020\u000eX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0014\u001a\u00020\u00158\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006\""}, d2 = {"Lcom/fleeksoft/ksoup/select/CombiningEvaluator;", "Lcom/fleeksoft/ksoup/select/Evaluator;", "<init>", "()V", "evaluators", "", "(Ljava/util/Collection;)V", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getEvaluators", "()Ljava/util/ArrayList;", "sortedEvaluators", "getSortedEvaluators", "num", "", "getNum", "()I", "setNum", "(I)V", "_cost", "wantsNodes", "", "getWantsNodes", "()Z", "setWantsNodes", "(Z)V", "add", "", "e", "reset", "cost", "updateEvaluators", "And", "Or", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class CombiningEvaluator extends Evaluator {
    private int _cost;
    private final ArrayList<Evaluator> evaluators;
    private int num;
    private final ArrayList<Evaluator> sortedEvaluators;
    private boolean wantsNodes;

    public CombiningEvaluator() {
        this.evaluators = new ArrayList<>();
        this.sortedEvaluators = new ArrayList<>();
    }

    public final ArrayList<Evaluator> getEvaluators() {
        return this.evaluators;
    }

    public final ArrayList<Evaluator> getSortedEvaluators() {
        return this.sortedEvaluators;
    }

    protected final int getNum() {
        return this.num;
    }

    protected final void setNum(int i) {
        this.num = i;
    }

    public final boolean getWantsNodes() {
        return this.wantsNodes;
    }

    public final void setWantsNodes(boolean z) {
        this.wantsNodes = z;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CombiningEvaluator(Collection<? extends Evaluator> evaluators) {
        this();
        Intrinsics.checkNotNullParameter(evaluators, "evaluators");
        this.evaluators.addAll(evaluators);
        updateEvaluators();
    }

    public void add(Evaluator e) {
        Intrinsics.checkNotNullParameter(e, "e");
        this.evaluators.add(e);
        updateEvaluators();
    }

    @Override // com.fleeksoft.ksoup.select.Evaluator
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

    @Override // com.fleeksoft.ksoup.select.Evaluator
    /* JADX INFO: renamed from: cost, reason: from getter */
    public int get_cost() {
        return this._cost;
    }

    @Override // com.fleeksoft.ksoup.select.Evaluator
    public boolean wantsNodes() {
        return this.wantsNodes;
    }

    public final void updateEvaluators() {
        this.num = this.evaluators.size();
        this._cost = 0;
        Iterator<Evaluator> it = this.evaluators.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            Evaluator next = it.next();
            Intrinsics.checkNotNullExpressionValue(next, "next(...)");
            this._cost += next.get_cost();
        }
        this.sortedEvaluators.clear();
        this.sortedEvaluators.addAll(this.evaluators);
        ArrayList<Evaluator> arrayList = this.sortedEvaluators;
        final Function2 function2 = new Function2() { // from class: com.fleeksoft.ksoup.select.CombiningEvaluator$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(CombiningEvaluator.updateEvaluators$lambda$0((Evaluator) obj, (Evaluator) obj2));
            }
        };
        CollectionsKt.sortWith(arrayList, new Comparator() { // from class: com.fleeksoft.ksoup.select.CombiningEvaluator$$ExternalSyntheticLambda1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ((Number) function2.invoke(obj, obj2)).intValue();
            }
        });
        Iterator<Evaluator> it2 = this.evaluators.iterator();
        Intrinsics.checkNotNullExpressionValue(it2, "iterator(...)");
        while (it2.hasNext()) {
            Evaluator next2 = it2.next();
            Intrinsics.checkNotNullExpressionValue(next2, "next(...)");
            if (next2.wantsNodes()) {
                this.wantsNodes = true;
                return;
            }
        }
    }

    static final int updateEvaluators$lambda$0(Evaluator evaluator, Evaluator evaluator2) {
        return evaluator.get_cost() - evaluator2.get_cost();
    }

    /* JADX INFO: compiled from: CombiningEvaluator.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u001d\b\u0016\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0007\"\u00020\u0004¢\u0006\u0004\b\u0005\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\n\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004¨\u0006\u0012"}, d2 = {"Lcom/fleeksoft/ksoup/select/CombiningEvaluator$And;", "Lcom/fleeksoft/ksoup/select/CombiningEvaluator;", "evaluators", "", "Lcom/fleeksoft/ksoup/select/Evaluator;", "<init>", "(Ljava/util/Collection;)V", "", "([Lcom/fleeksoft/ksoup/select/Evaluator;)V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "leafNode", "Lcom/fleeksoft/ksoup/nodes/LeafNode;", "toString", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class And extends CombiningEvaluator {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public And(Collection<? extends Evaluator> evaluators) {
            super(evaluators);
            Intrinsics.checkNotNullParameter(evaluators, "evaluators");
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public And(Evaluator... evaluators) {
            this(ArraysKt.toList(evaluators));
            Intrinsics.checkNotNullParameter(evaluators, "evaluators");
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            int num = getNum();
            for (int i = 0; i < num; i++) {
                Evaluator evaluator = getSortedEvaluators().get(i);
                Intrinsics.checkNotNullExpressionValue(evaluator, "get(...)");
                if (!evaluator.matches(root, element)) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, LeafNode leafNode) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(leafNode, "leafNode");
            int num = getNum();
            for (int i = 0; i < num; i++) {
                Evaluator evaluator = getSortedEvaluators().get(i);
                Intrinsics.checkNotNullExpressionValue(evaluator, "get(...)");
                if (!evaluator.matches(root, leafNode)) {
                    return false;
                }
            }
            return true;
        }

        public String toString() {
            return StringUtil.INSTANCE.join(getEvaluators(), "");
        }
    }

    /* JADX INFO: compiled from: CombiningEvaluator.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u001d\b\u0010\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0007\"\u00020\u0004¢\u0006\u0004\b\u0005\u0010\bB\t\b\u0010¢\u0006\u0004\b\u0005\u0010\tJ\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\n\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004¨\u0006\u0013"}, d2 = {"Lcom/fleeksoft/ksoup/select/CombiningEvaluator$Or;", "Lcom/fleeksoft/ksoup/select/CombiningEvaluator;", "evaluators", "", "Lcom/fleeksoft/ksoup/select/Evaluator;", "<init>", "(Ljava/util/Collection;)V", "", "([Lcom/fleeksoft/ksoup/select/Evaluator;)V", "()V", "matches", "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "element", "leafNode", "Lcom/fleeksoft/ksoup/nodes/LeafNode;", "toString", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Or extends CombiningEvaluator {
        public Or(Collection<? extends Evaluator> evaluators) {
            Intrinsics.checkNotNullParameter(evaluators, "evaluators");
            if (getNum() > 1) {
                getEvaluators().add(new And(evaluators));
            } else {
                getEvaluators().addAll(evaluators);
            }
            updateEvaluators();
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Or(Evaluator... evaluators) {
            this(ArraysKt.toList(evaluators));
            Intrinsics.checkNotNullParameter(evaluators, "evaluators");
        }

        public Or() {
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(element, "element");
            int num = getNum();
            for (int i = 0; i < num; i++) {
                Evaluator evaluator = getSortedEvaluators().get(i);
                Intrinsics.checkNotNullExpressionValue(evaluator, "get(...)");
                if (evaluator.matches(root, element)) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.fleeksoft.ksoup.select.Evaluator
        public boolean matches(Element root, LeafNode leafNode) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(leafNode, "leafNode");
            int num = getNum();
            for (int i = 0; i < num; i++) {
                Evaluator evaluator = getSortedEvaluators().get(i);
                Intrinsics.checkNotNullExpressionValue(evaluator, "get(...)");
                if (evaluator.matches(root, leafNode)) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return StringUtil.INSTANCE.join(getEvaluators(), ", ");
        }
    }
}
