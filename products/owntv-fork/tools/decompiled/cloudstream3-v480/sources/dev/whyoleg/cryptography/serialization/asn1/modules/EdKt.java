package dev.whyoleg.cryptography.serialization.asn1.modules;

import dev.whyoleg.cryptography.serialization.asn1.ObjectIdentifier;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Ed.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\"\u0019\u0010\u0000\u001a\u00020\u0001*\u00020\u00028FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0019\u0010\u0005\u001a\u00020\u0001*\u00020\u00028FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004\"\u0019\u0010\u0007\u001a\u00020\u0001*\u00020\u00028FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\b\u0010\u0004\"\u0019\u0010\t\u001a\u00020\u0001*\u00020\u00028FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\n\u0010\u0004¨\u0006\u000b"}, d2 = {"X25519", "Ldev/whyoleg/cryptography/serialization/asn1/ObjectIdentifier;", "Ldev/whyoleg/cryptography/serialization/asn1/ObjectIdentifier$Companion;", "getX25519", "(Ldev/whyoleg/cryptography/serialization/asn1/ObjectIdentifier$Companion;)Ljava/lang/String;", "X448", "getX448", "Ed25519", "getEd25519", "Ed448", "getEd448", "cryptography-serialization-asn1-modules"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class EdKt {
    public static final String getX25519(ObjectIdentifier.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return ObjectIdentifier.m8756constructorimpl("1.3.101.110");
    }

    public static final String getX448(ObjectIdentifier.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return ObjectIdentifier.m8756constructorimpl("1.3.101.111");
    }

    public static final String getEd25519(ObjectIdentifier.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return ObjectIdentifier.m8756constructorimpl("1.3.101.112");
    }

    public static final String getEd448(ObjectIdentifier.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return ObjectIdentifier.m8756constructorimpl("1.3.101.113");
    }
}
