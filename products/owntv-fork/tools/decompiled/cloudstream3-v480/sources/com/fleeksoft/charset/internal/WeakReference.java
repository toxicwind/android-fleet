package com.fleeksoft.charset.internal;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WeakReference.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0081@\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\b\u0010\u0005J\u0014\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0002HÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004R\u0010\u0010\u0003\u001a\u00028\u0000X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006\u0088\u0001\u0003\u0092\u0001\u0002H\u0001¨\u0006\u0010"}, d2 = {"Lcom/fleeksoft/charset/internal/WeakReference;", ExifInterface.GPS_DIRECTION_TRUE, "", "referred", "constructor-impl", "(Ljava/lang/Object;)Ljava/lang/Object;", "Ljava/lang/Object;", "get", "get-impl", "equals", "", "other", "hashCode", "", "toString", "", "charset_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@JvmInline
public final class WeakReference<T> {
    private final T referred;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ WeakReference m8166boximpl(Object obj) {
        return new WeakReference(obj);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static <T> Object m8167constructorimpl(T t) {
        return t;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m8168equalsimpl(Object obj, Object obj2) {
        return (obj2 instanceof WeakReference) && Intrinsics.areEqual(obj, ((WeakReference) obj2).getReferred());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m8169equalsimpl0(Object obj, Object obj2) {
        return Intrinsics.areEqual(obj, obj2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: get-impl, reason: not valid java name */
    public static final T m8170getimpl(Object obj) {
        return obj;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m8171hashCodeimpl(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m8172toStringimpl(Object obj) {
        return "WeakReference(referred=" + obj + ")";
    }

    public boolean equals(Object other) {
        return m8168equalsimpl(this.referred, other);
    }

    public int hashCode() {
        return m8171hashCodeimpl(this.referred);
    }

    public String toString() {
        return m8172toStringimpl(this.referred);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ Object getReferred() {
        return this.referred;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private /* synthetic */ WeakReference(Object obj) {
        this.referred = obj;
    }
}
