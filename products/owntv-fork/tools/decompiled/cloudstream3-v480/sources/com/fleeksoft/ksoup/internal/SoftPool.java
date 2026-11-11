package com.fleeksoft.ksoup.internal;

import androidx.exifinterface.media.ExifInterface;
import com.fleeksoft.ksoup.ported.ThreadLocal;
import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: SoftPool.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u0000 \u0015*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u0015B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u000b\u0010\f\u001a\u00028\u0000¢\u0006\u0002\u0010\rJ\u0013\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00028\u0000¢\u0006\u0002\u0010\u0011R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\t8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/fleeksoft/ksoup/internal/SoftPool;", ExifInterface.GPS_DIRECTION_TRUE, "", "initializer", "Lkotlin/Function0;", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "threadLocalStack", "Lcom/fleeksoft/ksoup/ported/ThreadLocal;", "Lkotlin/collections/ArrayDeque;", "getThreadLocalStack", "()Lcom/fleeksoft/ksoup/ported/ThreadLocal;", "borrow", "()Ljava/lang/Object;", "release", "", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;)V", "stack", "getStack", "()Lkotlin/collections/ArrayDeque;", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SoftPool<T> {
    public static final int MaxIdle = 12;
    private final Function0<T> initializer;
    private final ThreadLocal<ArrayDeque<T>> threadLocalStack;

    /* JADX WARN: Multi-variable type inference failed */
    public SoftPool(Function0<? extends T> initializer) {
        Intrinsics.checkNotNullParameter(initializer, "initializer");
        this.initializer = initializer;
        this.threadLocalStack = new ThreadLocal<>(new Function0() { // from class: com.fleeksoft.ksoup.internal.SoftPool$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SoftPool.threadLocalStack$lambda$0();
            }
        });
    }

    static final ArrayDeque threadLocalStack$lambda$0() {
        return new ArrayDeque();
    }

    public final ThreadLocal<ArrayDeque<T>> getThreadLocalStack() {
        return this.threadLocalStack;
    }

    public final T borrow() {
        ArrayDeque<T> stack = getStack();
        if (!stack.isEmpty()) {
            return stack.remove(0);
        }
        return this.initializer.invoke();
    }

    public final void release(T value) {
        ArrayDeque<T> stack = getStack();
        if (stack.size() < 12) {
            stack.addFirst(value);
        }
    }

    public final ArrayDeque<T> getStack() {
        return this.threadLocalStack.get();
    }
}
