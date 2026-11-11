package com.fleeksoft.charset.internal;

import androidx.exifinterface.media.ExifInterface;
import co.touchlab.stately.concurrency.ThreadLocalKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: ThreadLocal.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u000b\u0010\f\u001a\u00028\u0000¢\u0006\u0002\u0010\rJ\u0013\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00028\u0000¢\u0006\u0002\u0010\u0011R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00028\u00000\nj\b\u0012\u0004\u0012\u00028\u0000`\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/fleeksoft/charset/internal/ThreadLocal;", ExifInterface.GPS_DIRECTION_TRUE, "", "defaultValue", "Lkotlin/Function0;", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "getDefaultValue", "()Lkotlin/jvm/functions/Function0;", "threadLocalRef", "Ljava/lang/ThreadLocal;", "Lco/touchlab/stately/concurrency/ThreadLocalRef;", "get", "()Ljava/lang/Object;", "setValue", "", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;)V", "charset_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ThreadLocal<T> {
    private final Function0<T> defaultValue;
    private final java.lang.ThreadLocal<T> threadLocalRef;

    /* JADX WARN: Multi-variable type inference failed */
    public ThreadLocal(Function0<? extends T> defaultValue) {
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        this.defaultValue = defaultValue;
        this.threadLocalRef = new java.lang.ThreadLocal<>();
    }

    public final Function0<T> getDefaultValue() {
        return this.defaultValue;
    }

    public final T get() {
        T t = this.threadLocalRef.get();
        if (t != null) {
            return t;
        }
        T tInvoke = this.defaultValue.invoke();
        ThreadLocalKt.setValue(this.threadLocalRef, tInvoke);
        return tInvoke;
    }

    public final void setValue(T value) {
        ThreadLocalKt.setValue(this.threadLocalRef, value);
    }
}
