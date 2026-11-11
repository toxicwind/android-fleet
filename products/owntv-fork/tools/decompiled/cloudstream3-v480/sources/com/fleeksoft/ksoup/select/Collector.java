package com.fleeksoft.ksoup.select;

import androidx.exifinterface.media.ExifInterface;
import com.fleeksoft.ksoup.nodes.Element;
import com.fleeksoft.ksoup.nodes.Node;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: Collector.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u001c\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ4\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u000b\"\b\b\u0000\u0010\u000e*\u00020\u000f2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u0011J\u0018\u0010\u0012\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ5\u0010\u0013\u001a\u0004\u0018\u0001H\u000e\"\b\b\u0000\u0010\u000e*\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u0011¢\u0006\u0002\u0010\u0014J4\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u0016\"\b\b\u0000\u0010\u000e*\u00020\u000f2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u0011¨\u0006\u0017"}, d2 = {"Lcom/fleeksoft/ksoup/select/Collector;", "", "<init>", "()V", "collect", "Lcom/fleeksoft/ksoup/select/Elements;", "eval", "Lcom/fleeksoft/ksoup/select/Evaluator;", "root", "Lcom/fleeksoft/ksoup/nodes/Element;", "stream", "Lkotlin/sequences/Sequence;", "evaluator", "streamNodes", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/fleeksoft/ksoup/nodes/Node;", "type", "Lkotlin/reflect/KClass;", "findFirst", "findFirstNode", "(Lcom/fleeksoft/ksoup/select/Evaluator;Lcom/fleeksoft/ksoup/nodes/Element;Lkotlin/reflect/KClass;)Lcom/fleeksoft/ksoup/nodes/Node;", "collectNodes", "Lcom/fleeksoft/ksoup/select/Nodes;", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Collector {
    public static final Collector INSTANCE = new Collector();

    private Collector() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Elements collect(Evaluator eval, Element root) {
        Sequence<Element> sequenceStream;
        Intrinsics.checkNotNullParameter(eval, "eval");
        Intrinsics.checkNotNullParameter(root, "root");
        if (eval.wantsNodes()) {
            sequenceStream = streamNodes(eval, root, Reflection.getOrCreateKotlinClass(Element.class));
        } else {
            sequenceStream = stream(eval, root);
        }
        Elements elements = (Elements) SequencesKt.toCollection(sequenceStream, new Elements(null, 1, 0 == true ? 1 : 0));
        eval.reset();
        return elements;
    }

    public final Sequence<Element> stream(Evaluator evaluator, Element root) {
        Intrinsics.checkNotNullParameter(evaluator, "evaluator");
        Intrinsics.checkNotNullParameter(root, "root");
        evaluator.reset();
        return SequencesKt.filter(root.stream(), evaluator.asPredicate(root));
    }

    public final <T extends Node> Sequence<T> streamNodes(Evaluator evaluator, Element root, KClass<T> type) {
        Intrinsics.checkNotNullParameter(evaluator, "evaluator");
        Intrinsics.checkNotNullParameter(root, "root");
        Intrinsics.checkNotNullParameter(type, "type");
        evaluator.reset();
        return SequencesKt.filter(root.nodeStream(type), evaluator.asNodePredicate(root));
    }

    public final Element findFirst(Evaluator eval, Element root) {
        Intrinsics.checkNotNullParameter(eval, "eval");
        Intrinsics.checkNotNullParameter(root, "root");
        Element element = (Element) SequencesKt.firstOrNull(stream(eval, root));
        eval.reset();
        return element;
    }

    public final <T extends Node> T findFirstNode(Evaluator eval, Element root, KClass<T> type) {
        Intrinsics.checkNotNullParameter(eval, "eval");
        Intrinsics.checkNotNullParameter(root, "root");
        Intrinsics.checkNotNullParameter(type, "type");
        T t = (T) SequencesKt.firstOrNull(streamNodes(eval, root, type));
        eval.reset();
        return t;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T extends Node> Nodes<T> collectNodes(Evaluator evaluator, Element root, KClass<T> type) {
        Intrinsics.checkNotNullParameter(evaluator, "evaluator");
        Intrinsics.checkNotNullParameter(root, "root");
        Intrinsics.checkNotNullParameter(type, "type");
        return (Nodes) SequencesKt.toCollection(streamNodes(evaluator, root, type), new Nodes(null, 1, 0 == true ? 1 : 0));
    }
}
