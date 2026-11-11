package j$.util.function;

import j$.util.Objects;
import java.util.function.Function;

/* JADX INFO: renamed from: j$.util.function.Function$-CC, reason: invalid class name */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class Function$CC {
    public static /* synthetic */ Object lambda$identity$2(Object obj) {
        return obj;
    }

    public static Function $default$compose(final Function function, final Function function2) {
        Objects.requireNonNull(function2);
        return new Function() { // from class: j$.util.function.Function$2
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function3) {
                return Function$CC.$default$andThen(this, function3);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return function.apply(function2.apply(obj));
            }

            public /* synthetic */ Function compose(Function function3) {
                return Function$CC.$default$compose(this, function3);
            }
        };
    }

    public static Function $default$andThen(final Function function, final Function function2) {
        Objects.requireNonNull(function2);
        return new Function() { // from class: j$.util.function.Function$1
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function3) {
                return Function$CC.$default$andThen(this, function3);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return function2.apply(function.apply(obj));
            }

            public /* synthetic */ Function compose(Function function3) {
                return Function$CC.$default$compose(this, function3);
            }
        };
    }

    public static <T> Function<T, T> identity() {
        return new Function() { // from class: j$.util.function.Function$0
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Function$CC.lambda$identity$2(obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        };
    }
}
