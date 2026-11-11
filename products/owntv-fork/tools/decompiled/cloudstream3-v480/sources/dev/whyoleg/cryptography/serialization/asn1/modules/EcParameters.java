package dev.whyoleg.cryptography.serialization.asn1.modules;

import dev.whyoleg.cryptography.serialization.asn1.ObjectIdentifier;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;

/* JADX INFO: compiled from: EC.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0087@\u0018\u0000 \u00112\u00020\u0001:\u0002\u0010\u0011B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004R\u0017\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002¨\u0006\u0012"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/modules/EcParameters;", "", "namedCurve", "Ldev/whyoleg/cryptography/serialization/asn1/ObjectIdentifier;", "constructor-impl", "(Ljava/lang/String;)Ljava/lang/String;", "getNamedCurve-STa95mE", "()Ljava/lang/String;", "Ljava/lang/String;", "equals", "", "other", "hashCode", "", "toString", "", "$serializer", "Companion", "cryptography-serialization-asn1-modules"}, k = 1, mv = {2, 3, 0}, xi = 48)
@JvmInline
@Serializable
public final class EcParameters {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String namedCurve;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ EcParameters m8777boximpl(String str) {
        return new EcParameters(str);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static String m8778constructorimpl(String namedCurve) {
        Intrinsics.checkNotNullParameter(namedCurve, "namedCurve");
        return namedCurve;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m8779equalsimpl(String str, Object obj) {
        return (obj instanceof EcParameters) && ObjectIdentifier.m8758equalsimpl0(str, ((EcParameters) obj).m8784unboximpl());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m8780equalsimpl0(String str, String str2) {
        return ObjectIdentifier.m8758equalsimpl0(str, str2);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m8781hashCodeimpl(String str) {
        return ObjectIdentifier.m8759hashCodeimpl(str);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m8782toStringimpl(String str) {
        return "EcParameters(namedCurve=" + ((Object) ObjectIdentifier.m8760toStringimpl(str)) + ')';
    }

    public boolean equals(Object other) {
        return m8779equalsimpl(this.namedCurve, other);
    }

    public int hashCode() {
        return m8781hashCodeimpl(this.namedCurve);
    }

    public String toString() {
        return m8782toStringimpl(this.namedCurve);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ String m8784unboximpl() {
        return this.namedCurve;
    }

    /* JADX INFO: compiled from: EC.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/modules/EcParameters$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Ldev/whyoleg/cryptography/serialization/asn1/modules/EcParameters;", "cryptography-serialization-asn1-modules"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<EcParameters> serializer() {
            return EcParameters$$serializer.INSTANCE;
        }
    }

    private /* synthetic */ EcParameters(String str) {
        this.namedCurve = str;
    }

    /* JADX INFO: renamed from: getNamedCurve-STa95mE, reason: not valid java name and from getter */
    public final String getNamedCurve() {
        return this.namedCurve;
    }
}
