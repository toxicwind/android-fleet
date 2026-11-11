package dev.whyoleg.cryptography.serialization.asn1.modules;

import dev.whyoleg.cryptography.serialization.asn1.ObjectIdentifier;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DH.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0019\u0010\u0000\u001a\u00020\u0001*\u00020\u00028FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"DH", "Ldev/whyoleg/cryptography/serialization/asn1/ObjectIdentifier;", "Ldev/whyoleg/cryptography/serialization/asn1/ObjectIdentifier$Companion;", "getDH", "(Ldev/whyoleg/cryptography/serialization/asn1/ObjectIdentifier$Companion;)Ljava/lang/String;", "cryptography-serialization-asn1-modules"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class DHKt {
    public static final String getDH(ObjectIdentifier.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return ObjectIdentifier.m8756constructorimpl("1.2.840.113549.1.3.1");
    }
}
