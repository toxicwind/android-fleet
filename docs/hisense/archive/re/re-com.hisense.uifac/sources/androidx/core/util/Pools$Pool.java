package androidx.core.util;

/* JADX INFO: loaded from: classes.dex */
public interface Pools$Pool<T> {
    T acquire();

    boolean release(T t);
}
