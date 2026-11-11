package dev.whyoleg.cryptography.serialization.asn1.modules;

import dev.whyoleg.cryptography.serialization.asn1.ObjectIdentifier;
import kotlin.Metadata;

/* JADX INFO: compiled from: DH.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0013\bF\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0017\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0015\u0010\b\u001a\u00020\t8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/modules/DhAlgorithmIdentifier;", "Ldev/whyoleg/cryptography/serialization/asn1/modules/AlgorithmIdentifier;", "parameters", "Ldev/whyoleg/cryptography/serialization/asn1/modules/DhParameters;", "<init>", "(Ldev/whyoleg/cryptography/serialization/asn1/modules/DhParameters;)V", "getParameters", "()Ldev/whyoleg/cryptography/serialization/asn1/modules/DhParameters;", "algorithm", "Ldev/whyoleg/cryptography/serialization/asn1/ObjectIdentifier;", "getAlgorithm-STa95mE", "()Ljava/lang/String;", "cryptography-serialization-asn1-modules"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DhAlgorithmIdentifier implements AlgorithmIdentifier {
    private final DhParameters parameters;

    public DhAlgorithmIdentifier(DhParameters dhParameters) {
        this.parameters = dhParameters;
    }

    @Override // dev.whyoleg.cryptography.serialization.asn1.modules.AlgorithmIdentifier
    public DhParameters getParameters() {
        return this.parameters;
    }

    @Override // dev.whyoleg.cryptography.serialization.asn1.modules.AlgorithmIdentifier
    /* JADX INFO: renamed from: getAlgorithm-STa95mE */
    public String getAlgorithm() {
        return DHKt.getDH(ObjectIdentifier.INSTANCE);
    }
}
