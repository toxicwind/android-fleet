package java.util.function;

/* JADX INFO: loaded from: classes3.dex */
public interface Function<T, R> {
    <V> Function<T, V> andThen(Function<? super R, ? extends V> function);

    R apply(T t);
}
