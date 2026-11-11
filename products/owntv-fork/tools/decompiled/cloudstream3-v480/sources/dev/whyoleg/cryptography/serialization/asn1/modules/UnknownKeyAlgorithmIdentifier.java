package dev.whyoleg.cryptography.serialization.asn1.modules;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KeyAlgorithmIdentifier.kt */
/* JADX INFO: loaded from: classes7.dex */
@Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated without replacement.")
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0001\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0017\u0010\u0002\u001a\u00020\u0003X\u0096\u0084\b¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\t\u001a\u0004\u0018\u00010\n8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/modules/UnknownKeyAlgorithmIdentifier;", "Ldev/whyoleg/cryptography/serialization/asn1/modules/AlgorithmIdentifier;", "algorithm", "Ldev/whyoleg/cryptography/serialization/asn1/ObjectIdentifier;", "<init>", "(Ljava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getAlgorithm-STa95mE", "()Ljava/lang/String;", "Ljava/lang/String;", "parameters", "", "getParameters", "()Ljava/lang/Void;", "cryptography-serialization-asn1-modules"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class UnknownKeyAlgorithmIdentifier implements AlgorithmIdentifier {
    private final String algorithm;

    public /* synthetic */ UnknownKeyAlgorithmIdentifier(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    @Override // dev.whyoleg.cryptography.serialization.asn1.modules.AlgorithmIdentifier
    public Void getParameters() {
        return null;
    }

    private UnknownKeyAlgorithmIdentifier(String algorithm) {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        this.algorithm = algorithm;
    }

    @Override // dev.whyoleg.cryptography.serialization.asn1.modules.AlgorithmIdentifier
    /* JADX INFO: renamed from: getAlgorithm-STa95mE, reason: from getter */
    public String getAlgorithm() {
        return this.algorithm;
    }
}
