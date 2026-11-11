package org.jsoup.internal;

import j$.util.function.Function$CC;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/* JADX INFO: loaded from: classes8.dex */
@Deprecated
public final class Functions {
    private static final Function ListFunction = new Function() { // from class: org.jsoup.internal.Functions$$ExternalSyntheticLambda0
        @Override // java.util.function.Function
        public /* synthetic */ Function andThen(Function function) {
            return Function$CC.$default$andThen(this, function);
        }

        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return Functions.lambda$static$0(obj);
        }

        public /* synthetic */ Function compose(Function function) {
            return Function$CC.$default$compose(this, function);
        }
    };
    private static final Function SetFunction = new Function() { // from class: org.jsoup.internal.Functions$$ExternalSyntheticLambda1
        @Override // java.util.function.Function
        public /* synthetic */ Function andThen(Function function) {
            return Function$CC.$default$andThen(this, function);
        }

        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return Functions.lambda$static$1(obj);
        }

        public /* synthetic */ Function compose(Function function) {
            return Function$CC.$default$compose(this, function);
        }
    };
    private static final Function MapFunction = new Function() { // from class: org.jsoup.internal.Functions$$ExternalSyntheticLambda2
        @Override // java.util.function.Function
        public /* synthetic */ Function andThen(Function function) {
            return Function$CC.$default$andThen(this, function);
        }

        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return Functions.lambda$static$2(obj);
        }

        public /* synthetic */ Function compose(Function function) {
            return Function$CC.$default$compose(this, function);
        }
    };
    private static final Function IdentityMapFunction = new Function() { // from class: org.jsoup.internal.Functions$$ExternalSyntheticLambda3
        @Override // java.util.function.Function
        public /* synthetic */ Function andThen(Function function) {
            return Function$CC.$default$andThen(this, function);
        }

        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return Functions.lambda$static$3(obj);
        }

        public /* synthetic */ Function compose(Function function) {
            return Function$CC.$default$compose(this, function);
        }
    };

    static /* synthetic */ Object lambda$static$0(Object obj) {
        return new ArrayList();
    }

    static /* synthetic */ Object lambda$static$1(Object obj) {
        return new HashSet();
    }

    static /* synthetic */ Object lambda$static$2(Object obj) {
        return new HashMap();
    }

    static /* synthetic */ Object lambda$static$3(Object obj) {
        return new IdentityHashMap();
    }

    private Functions() {
    }

    public static <T, U> Function<T, List<U>> listFunction() {
        return ListFunction;
    }

    public static <T, U> Function<T, Set<U>> setFunction() {
        return SetFunction;
    }

    public static <T, K, V> Function<T, Map<K, V>> mapFunction() {
        return MapFunction;
    }

    public static <T, K, V> Function<T, IdentityHashMap<K, V>> identityMapFunction() {
        return IdentityMapFunction;
    }
}
