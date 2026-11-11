package j$.util;

import java.io.Serializable;
import java.util.Collections;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/* JADX INFO: loaded from: classes3.dex */
public interface Comparator<T> {

    /* JADX INFO: renamed from: j$.util.Comparator$-EL, reason: invalid class name */
    public final /* synthetic */ class EL {
        public static /* synthetic */ java.util.Comparator reversed(java.util.Comparator comparator) {
            return comparator instanceof Comparator ? ((Comparator) comparator).reversed() : Collections.reverseOrder(comparator);
        }

        public static /* synthetic */ java.util.Comparator thenComparing(java.util.Comparator comparator, java.util.Comparator comparator2) {
            return comparator instanceof Comparator ? ((Comparator) comparator).thenComparing(comparator2) : CC.$default$thenComparing(comparator, comparator2);
        }
    }

    /* JADX INFO: renamed from: j$.util.Comparator$0, reason: invalid class name */
    public final /* synthetic */ class AnonymousClass0 implements java.util.Comparator, Serializable {
        public final /* synthetic */ ToIntFunction f$0;

        public /* synthetic */ AnonymousClass0(ToIntFunction toIntFunction) {
            this.f$0 = toIntFunction;
        }

        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return CC.lambda$comparingInt$7b0bb60$1(this.f$0, obj, obj2);
        }
    }

    /* JADX INFO: renamed from: j$.util.Comparator$1, reason: invalid class name */
    public final /* synthetic */ class AnonymousClass1 implements java.util.Comparator, Serializable {
        public final /* synthetic */ java.util.Comparator f$0;
        public final /* synthetic */ java.util.Comparator f$1;

        public /* synthetic */ AnonymousClass1(java.util.Comparator comparator, java.util.Comparator comparator2) {
            this.f$0 = comparator;
            this.f$1 = comparator2;
        }

        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return CC.$private$lambda$thenComparing$36697e65$1(this.f$0, this.f$1, obj, obj2);
        }
    }

    /* JADX INFO: renamed from: j$.util.Comparator$2, reason: invalid class name */
    public final /* synthetic */ class AnonymousClass2 implements java.util.Comparator, Serializable {
        public final /* synthetic */ ToDoubleFunction f$0;

        public /* synthetic */ AnonymousClass2(ToDoubleFunction toDoubleFunction) {
            this.f$0 = toDoubleFunction;
        }

        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return CC.lambda$comparingDouble$8dcf42ea$1(this.f$0, obj, obj2);
        }
    }

    /* JADX INFO: renamed from: j$.util.Comparator$3, reason: invalid class name */
    public final /* synthetic */ class AnonymousClass3 implements java.util.Comparator, Serializable {
        public final /* synthetic */ Function f$0;

        public /* synthetic */ AnonymousClass3(Function function) {
            this.f$0 = function;
        }

        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return CC.lambda$comparing$77a9974f$1(this.f$0, obj, obj2);
        }
    }

    /* JADX INFO: renamed from: j$.util.Comparator$4, reason: invalid class name */
    public final /* synthetic */ class AnonymousClass4 implements java.util.Comparator, Serializable {
        public final /* synthetic */ ToLongFunction f$0;

        public /* synthetic */ AnonymousClass4(ToLongFunction toLongFunction) {
            this.f$0 = toLongFunction;
        }

        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return CC.lambda$comparingLong$6043328a$1(this.f$0, obj, obj2);
        }
    }

    /* JADX INFO: renamed from: j$.util.Comparator$5, reason: invalid class name */
    public final /* synthetic */ class AnonymousClass5 implements java.util.Comparator, Serializable {
        public final /* synthetic */ java.util.Comparator f$0;
        public final /* synthetic */ Function f$1;

        public /* synthetic */ AnonymousClass5(java.util.Comparator comparator, Function function) {
            this.f$0 = comparator;
            this.f$1 = function;
        }

        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return CC.lambda$comparing$ea9a8b3a$1(this.f$0, this.f$1, obj, obj2);
        }
    }

    java.util.Comparator<T> reversed();

    java.util.Comparator<T> thenComparing(java.util.Comparator<? super T> comparator);

    <U extends Comparable<? super U>> java.util.Comparator<T> thenComparing(Function<? super T, ? extends U> function);

    <U> java.util.Comparator<T> thenComparing(Function<? super T, ? extends U> function, java.util.Comparator<? super U> comparator);

    java.util.Comparator<T> thenComparingDouble(ToDoubleFunction<? super T> toDoubleFunction);

    java.util.Comparator<T> thenComparingInt(ToIntFunction<? super T> toIntFunction);

    java.util.Comparator<T> thenComparingLong(ToLongFunction<? super T> toLongFunction);

    /* JADX INFO: renamed from: j$.util.Comparator$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static java.util.Comparator $default$thenComparing(java.util.Comparator comparator, java.util.Comparator comparator2) {
            Objects.requireNonNull(comparator2);
            return new AnonymousClass1(comparator, comparator2);
        }

        public static /* synthetic */ int $private$lambda$thenComparing$36697e65$1(java.util.Comparator comparator, java.util.Comparator comparator2, Object obj, Object obj2) {
            int iCompare = comparator.compare(obj, obj2);
            return iCompare != 0 ? iCompare : comparator2.compare(obj, obj2);
        }

        public static java.util.Comparator reverseOrder() {
            return Collections.reverseOrder();
        }

        public static java.util.Comparator naturalOrder() {
            return Comparators$NaturalOrderComparator.INSTANCE;
        }

        public static <T> java.util.Comparator<T> nullsLast(java.util.Comparator<? super T> comparator) {
            return new Comparators$NullComparator(false, comparator);
        }

        public static <T, U> java.util.Comparator<T> comparing(Function<? super T, ? extends U> function, java.util.Comparator<? super U> comparator) {
            Objects.requireNonNull(function);
            Objects.requireNonNull(comparator);
            return new AnonymousClass5(comparator, function);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ int lambda$comparing$ea9a8b3a$1(java.util.Comparator comparator, Function function, Object obj, Object obj2) {
            return comparator.compare(function.apply(obj), function.apply(obj2));
        }

        public static <T, U extends Comparable<? super U>> java.util.Comparator<T> comparing(Function<? super T, ? extends U> function) {
            Objects.requireNonNull(function);
            return new AnonymousClass3(function);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ int lambda$comparing$77a9974f$1(Function function, Object obj, Object obj2) {
            return ((Comparable) function.apply(obj)).compareTo(function.apply(obj2));
        }

        public static <T> java.util.Comparator<T> comparingInt(ToIntFunction<? super T> toIntFunction) {
            Objects.requireNonNull(toIntFunction);
            return new AnonymousClass0(toIntFunction);
        }

        public static /* synthetic */ int lambda$comparingInt$7b0bb60$1(ToIntFunction toIntFunction, Object obj, Object obj2) {
            return Integer.compare(toIntFunction.applyAsInt(obj), toIntFunction.applyAsInt(obj2));
        }

        public static java.util.Comparator comparingLong(ToLongFunction toLongFunction) {
            Objects.requireNonNull(toLongFunction);
            return new AnonymousClass4(toLongFunction);
        }

        public static /* synthetic */ int lambda$comparingLong$6043328a$1(ToLongFunction toLongFunction, Object obj, Object obj2) {
            return Long.compare(toLongFunction.applyAsLong(obj), toLongFunction.applyAsLong(obj2));
        }

        public static <T> java.util.Comparator<T> comparingDouble(ToDoubleFunction<? super T> toDoubleFunction) {
            Objects.requireNonNull(toDoubleFunction);
            return new AnonymousClass2(toDoubleFunction);
        }

        public static /* synthetic */ int lambda$comparingDouble$8dcf42ea$1(ToDoubleFunction toDoubleFunction, Object obj, Object obj2) {
            return Double.compare(toDoubleFunction.applyAsDouble(obj), toDoubleFunction.applyAsDouble(obj2));
        }
    }
}
