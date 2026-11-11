package dev.whyoleg.cryptography.serialization.asn1.modules;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;

/* JADX INFO: compiled from: EC.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0087@\u0018\u0000 \u00102\u00020\u0001:\u0002\u000f\u0010B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000b\u001a\u00020\fHÖ\u0081\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002¨\u0006\u0011"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/modules/EcPoint;", "", "bytes", "", "constructor-impl", "([B)[B", "getBytes", "()[B", "equals", "", "other", "hashCode", "", "toString", "", "$serializer", "Companion", "cryptography-serialization-asn1-modules"}, k = 1, mv = {2, 3, 0}, xi = 48)
@JvmInline
@Serializable
public final class EcPoint {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final byte[] bytes;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ EcPoint m8787boximpl(byte[] bArr) {
        return new EcPoint(bArr);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static byte[] m8788constructorimpl(byte[] bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        return bytes;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m8789equalsimpl(byte[] bArr, Object obj) {
        return (obj instanceof EcPoint) && Intrinsics.areEqual(bArr, ((EcPoint) obj).m8793unboximpl());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m8790equalsimpl0(byte[] bArr, byte[] bArr2) {
        return Intrinsics.areEqual(bArr, bArr2);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m8791hashCodeimpl(byte[] bArr) {
        return Arrays.hashCode(bArr);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m8792toStringimpl(byte[] bArr) {
        return "EcPoint(bytes=" + Arrays.toString(bArr) + ')';
    }

    public boolean equals(Object other) {
        return m8789equalsimpl(this.bytes, other);
    }

    public int hashCode() {
        return m8791hashCodeimpl(this.bytes);
    }

    public String toString() {
        return m8792toStringimpl(this.bytes);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ byte[] m8793unboximpl() {
        return this.bytes;
    }

    /* JADX INFO: compiled from: EC.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/modules/EcPoint$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Ldev/whyoleg/cryptography/serialization/asn1/modules/EcPoint;", "cryptography-serialization-asn1-modules"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<EcPoint> serializer() {
            return EcPoint$$serializer.INSTANCE;
        }
    }

    private /* synthetic */ EcPoint(byte[] bArr) {
        this.bytes = bArr;
    }

    public final byte[] getBytes() {
        return this.bytes;
    }
}
