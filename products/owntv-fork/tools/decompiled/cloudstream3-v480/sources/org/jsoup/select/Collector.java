package org.jsoup.select;

import j$.util.stream.Collectors;
import j$.util.stream.Stream;
import java.util.function.Supplier;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

/* JADX INFO: loaded from: classes8.dex */
public class Collector {
    private Collector() {
    }

    public static Elements collect(Evaluator evaluator, Element element) {
        Stream<Element> stream;
        if (evaluator.wantsNodes()) {
            stream = streamNodes(evaluator, element, Element.class);
        } else {
            stream = stream(evaluator, element);
        }
        Elements elements = (Elements) stream.collect(Collectors.toCollection(new Collector$$ExternalSyntheticLambda1()));
        evaluator.reset();
        return elements;
    }

    public static Stream<Element> stream(Evaluator evaluator, Element element) {
        evaluator.reset();
        return element.stream().filter(evaluator.asPredicate(element));
    }

    public static <T extends Node> Stream<T> streamNodes(Evaluator evaluator, Element element, Class<T> cls) {
        evaluator.reset();
        return element.nodeStream(cls).filter(evaluator.asNodePredicate(element));
    }

    public static Element findFirst(Evaluator evaluator, Element element) {
        Element elementOrElse = stream(evaluator, element).findFirst().orElse(null);
        evaluator.reset();
        return elementOrElse;
    }

    public static <T extends Node> T findFirstNode(Evaluator evaluator, Element element, Class<T> cls) {
        T t = (T) streamNodes(evaluator, element, cls).findFirst().orElse(null);
        evaluator.reset();
        return t;
    }

    public static <T extends Node> Nodes<T> collectNodes(Evaluator evaluator, Element element, Class<T> cls) {
        return (Nodes) streamNodes(evaluator, element, cls).collect(Collectors.toCollection(new Supplier() { // from class: org.jsoup.select.Collector$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return new Nodes();
            }
        }));
    }
}
