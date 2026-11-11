package com.fleeksoft.ksoup.select;

import androidx.core.app.NotificationCompat;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.fleeksoft.ksoup.helper.Validate;
import com.fleeksoft.ksoup.nodes.Element;
import com.fleeksoft.ksoup.parser.TokenQueue;
import com.google.android.gms.actions.SearchIntents;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: Selector.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\tJ\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\r2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\tJ\u001c\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000fJ$\u0010\u0010\u001a\u00020\u00052\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\u00122\u000e\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0012J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u001e\u0010\u0014\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0015\u001a\u00020\u00072\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000fJ\u000e\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0007J\u000e\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0007J\u000e\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u0007¨\u0006\u001c"}, d2 = {"Lcom/fleeksoft/ksoup/select/Selector;", "", "<init>", "()V", "select", "Lcom/fleeksoft/ksoup/select/Elements;", SearchIntents.EXTRA_QUERY, "", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "evaluator", "Lcom/fleeksoft/ksoup/select/Evaluator;", "selectStream", "Lkotlin/sequences/Sequence;", "roots", "", "filterOut", "elements", "", "outs", "selectFirst", "cssQuery", "escapeCssIdentifier", TvContractCompat.PARAM_INPUT, "unescapeCssIdentifier", "evaluatorOf", "css", "SelectorParseException", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Selector {
    public static final Selector INSTANCE = new Selector();

    private Selector() {
    }

    public final Elements select(String query, Element root) {
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(root, "root");
        Validate.INSTANCE.notEmpty(query);
        return select(evaluatorOf(query), root);
    }

    public final Elements select(Evaluator evaluator, Element root) {
        Intrinsics.checkNotNullParameter(evaluator, "evaluator");
        Intrinsics.checkNotNullParameter(root, "root");
        return Collector.INSTANCE.collect(evaluator, root);
    }

    public final Sequence<Element> selectStream(String query, Element root) {
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(root, "root");
        Validate.INSTANCE.notEmpty(query);
        return selectStream(evaluatorOf(query), root);
    }

    public final Sequence<Element> selectStream(Evaluator evaluator, Element root) {
        Intrinsics.checkNotNullParameter(evaluator, "evaluator");
        Intrinsics.checkNotNullParameter(root, "root");
        return Collector.INSTANCE.stream(evaluator, root);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Elements select(String query, Iterable<? extends Element> roots) {
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(roots, "roots");
        Validate.INSTANCE.notEmpty(query);
        Evaluator evaluatorEvaluatorOf = evaluatorOf(query);
        Elements elements = new Elements(null, 1, 0 == true ? 1 : 0);
        HashSet hashSet = new HashSet();
        Iterator<? extends Element> it = roots.iterator();
        while (it.hasNext()) {
            Iterator it2 = SequencesKt.filter(selectStream(evaluatorEvaluatorOf, it.next()), new AnonymousClass1(hashSet)).iterator();
            while (it2.hasNext()) {
                elements.add((Element) it2.next());
            }
        }
        return elements;
    }

    /* JADX INFO: renamed from: com.fleeksoft.ksoup.select.Selector$select$1, reason: invalid class name */
    /* JADX INFO: compiled from: Selector.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Element, Boolean> {
        AnonymousClass1(Object obj) {
            super(1, obj, HashSet.class, "add", "add(Ljava/lang/Object;)Z", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Boolean invoke(Element element) {
            return Boolean.valueOf(((HashSet) this.receiver).add(element));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Elements filterOut(Collection<? extends Element> elements, Collection<? extends Element> outs) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        Intrinsics.checkNotNullParameter(outs, "outs");
        Elements elements2 = new Elements(null, 1, 0 == true ? 1 : 0);
        for (Element element : elements) {
            Iterator<? extends Element> it = outs.iterator();
            do {
                if (!it.hasNext()) {
                    elements2.add(element);
                    break;
                }
            } while (!element.equals(it.next()));
        }
        return elements2;
    }

    public final Element selectFirst(String cssQuery, Element root) {
        Intrinsics.checkNotNullParameter(cssQuery, "cssQuery");
        Intrinsics.checkNotNullParameter(root, "root");
        Validate.INSTANCE.notEmpty(cssQuery);
        return Collector.INSTANCE.findFirst(evaluatorOf(cssQuery), root);
    }

    public final Element selectFirst(String cssQuery, Iterable<? extends Element> roots) {
        Intrinsics.checkNotNullParameter(cssQuery, "cssQuery");
        Intrinsics.checkNotNullParameter(roots, "roots");
        Validate.INSTANCE.notEmpty(cssQuery);
        Evaluator evaluatorEvaluatorOf = evaluatorOf(cssQuery);
        Iterator<? extends Element> it = roots.iterator();
        while (it.hasNext()) {
            Element elementFindFirst = Collector.INSTANCE.findFirst(evaluatorEvaluatorOf, it.next());
            if (elementFindFirst != null) {
                return elementFindFirst;
            }
        }
        return null;
    }

    public final String escapeCssIdentifier(String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        return TokenQueue.INSTANCE.escapeCssIdentifier(input);
    }

    public final String unescapeCssIdentifier(String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        TokenQueue tokenQueue = new TokenQueue(input);
        try {
            String strConsumeCssIdentifier = tokenQueue.consumeCssIdentifier();
            AutoCloseableKt.closeFinally(tokenQueue, null);
            return strConsumeCssIdentifier;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                AutoCloseableKt.closeFinally(tokenQueue, th);
                throw th2;
            }
        }
    }

    public final Evaluator evaluatorOf(String css) {
        Intrinsics.checkNotNullParameter(css, "css");
        return QueryParser.INSTANCE.parse(css);
    }

    /* JADX INFO: compiled from: Selector.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0013\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006B\u001d\b\u0016\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\t¨\u0006\n"}, d2 = {"Lcom/fleeksoft/ksoup/select/Selector$SelectorParseException;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", NotificationCompat.CATEGORY_MESSAGE, "", "<init>", "(Ljava/lang/String;)V", "cause", "", "(Ljava/lang/Throwable;Ljava/lang/String;)V", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class SelectorParseException extends IllegalStateException {
        public SelectorParseException(String str) {
            super(str);
        }

        public SelectorParseException(Throwable th, String str) {
            super(str, th);
        }
    }
}
