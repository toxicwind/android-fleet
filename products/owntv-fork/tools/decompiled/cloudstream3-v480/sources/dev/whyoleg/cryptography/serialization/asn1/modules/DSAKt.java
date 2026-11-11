package dev.whyoleg.cryptography.serialization.asn1.modules;

import dev.whyoleg.cryptography.serialization.asn1.ObjectIdentifier;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DSA.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0019\u0010\u0000\u001a\u00020\u0001*\u00020\u00028FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"DSA", "Ldev/whyoleg/cryptography/serialization/asn1/ObjectIdentifier;", "Ldev/whyoleg/cryptography/serialization/asn1/ObjectIdentifier$Companion;", "getDSA", "(Ldev/whyoleg/cryptography/serialization/asn1/ObjectIdentifier$Companion;)Ljava/lang/String;", "cryptography-serialization-asn1-modules"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class DSAKt {
    public static final String getDSA(ObjectIdentifier.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return ObjectIdentifier.m8756constructorimpl("1.2.840.10040.4.1");
    }
}
