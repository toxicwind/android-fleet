package com.fleeksoft.ksoup.internal;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WeakReference.native.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u000b\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\fR\u001f\u0010\u0006\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00018\u00008\u00000\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lcom/fleeksoft/ksoup/internal/WeakReference;", ExifInterface.GPS_DIRECTION_TRUE, "", "referred", "<init>", "(Ljava/lang/Object;)V", "weekRefValue", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "getWeekRefValue", "()Ljava/lang/ref/WeakReference;", "get", "()Ljava/lang/Object;", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class WeakReference<T> {
    private final java.lang.ref.WeakReference<T> weekRefValue;

    public WeakReference(T referred) {
        Intrinsics.checkNotNullParameter(referred, "referred");
        this.weekRefValue = new java.lang.ref.WeakReference<>(referred);
    }

    public final java.lang.ref.WeakReference<T> getWeekRefValue() {
        return this.weekRefValue;
    }

    public final T get() {
        return this.weekRefValue.get();
    }
}
