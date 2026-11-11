package dev.whyoleg.cryptography.serialization.asn1;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: ObjectIdentifier.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0087@\u0018\u0000 \u000e2\u00020\u0001:\u0002\u000e\u000fB\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000b\u001a\u00020\fHÖ\u0081\u0004J\n\u0010\r\u001a\u00020\u0003HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002¨\u0006\u0010"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/ObjectIdentifier;", "", ES6Iterator.VALUE_PROPERTY, "", "constructor-impl", "(Ljava/lang/String;)Ljava/lang/String;", "getValue", "()Ljava/lang/String;", "equals", "", "other", "hashCode", "", "toString", "Companion", "$serializer", "cryptography-serialization-asn1"}, k = 1, mv = {2, 3, 0}, xi = 48)
@JvmInline
@Serializable
public final class ObjectIdentifier {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String value;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ ObjectIdentifier m8755boximpl(String str) {
        return new ObjectIdentifier(str);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static String m8756constructorimpl(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return value;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m8757equalsimpl(String str, Object obj) {
        return (obj instanceof ObjectIdentifier) && Intrinsics.areEqual(str, ((ObjectIdentifier) obj).m8761unboximpl());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m8758equalsimpl0(String str, String str2) {
        return Intrinsics.areEqual(str, str2);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m8759hashCodeimpl(String str) {
        return str.hashCode();
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m8760toStringimpl(String str) {
        return "ObjectIdentifier(value=" + str + ')';
    }

    public boolean equals(Object other) {
        return m8757equalsimpl(this.value, other);
    }

    public int hashCode() {
        return m8759hashCodeimpl(this.value);
    }

    public String toString() {
        return m8760toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ String m8761unboximpl() {
        return this.value;
    }

    /* JADX INFO: compiled from: ObjectIdentifier.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/ObjectIdentifier$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Ldev/whyoleg/cryptography/serialization/asn1/ObjectIdentifier;", "cryptography-serialization-asn1"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<ObjectIdentifier> serializer() {
            return ObjectIdentifier$$serializer.INSTANCE;
        }
    }

    private /* synthetic */ ObjectIdentifier(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }
}
