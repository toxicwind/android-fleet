package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
public final class StreamSupport {
    public static <T> Stream<T> stream(Spliterator<T> spliterator, boolean z) {
        Objects.requireNonNull(spliterator);
        return new ReferencePipeline.Head(spliterator, StreamOpFlag.fromCharacteristics(spliterator), z);
    }

    public static <T> Stream<T> stream(Supplier<? extends Spliterator<T>> supplier, int i, boolean z) {
        Objects.requireNonNull(supplier);
        return new ReferencePipeline.Head(supplier, StreamOpFlag.fromCharacteristics(i), z);
    }

    public static IntStream intStream(Spliterator.OfInt ofInt, boolean z) {
        return new IntPipeline.Head(ofInt, StreamOpFlag.fromCharacteristics(ofInt), z);
    }

    public static LongStream longStream(Spliterator.OfLong ofLong, boolean z) {
        return new LongPipeline.Head(ofLong, StreamOpFlag.fromCharacteristics(ofLong), z);
    }

    public static DoubleStream doubleStream(Spliterator.OfDouble ofDouble, boolean z) {
        return new DoublePipeline.Head(ofDouble, StreamOpFlag.fromCharacteristics(ofDouble), z);
    }
}
