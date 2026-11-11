package dev.whyoleg.cryptography.serialization.asn1.modules;

import dev.whyoleg.cryptography.serialization.asn1.ObjectIdentifier;
import kotlin.Metadata;

/* JADX INFO: compiled from: Ed.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\u0004\u0018\u00010\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0015\u0010\b\u001a\u00020\t8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/modules/Ed25519AlgorithmIdentifier;", "Ldev/whyoleg/cryptography/serialization/asn1/modules/AlgorithmIdentifier;", "<init>", "()V", "parameters", "", "getParameters", "()Ljava/lang/Void;", "algorithm", "Ldev/whyoleg/cryptography/serialization/asn1/ObjectIdentifier;", "getAlgorithm-STa95mE", "()Ljava/lang/String;", "cryptography-serialization-asn1-modules"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Ed25519AlgorithmIdentifier implements AlgorithmIdentifier {
    public static final Ed25519AlgorithmIdentifier INSTANCE = new Ed25519AlgorithmIdentifier();

    @Override // dev.whyoleg.cryptography.serialization.asn1.modules.AlgorithmIdentifier
    public Void getParameters() {
        return null;
    }

    private Ed25519AlgorithmIdentifier() {
    }

    @Override // dev.whyoleg.cryptography.serialization.asn1.modules.AlgorithmIdentifier
    /* JADX INFO: renamed from: getAlgorithm-STa95mE */
    public String mo8767getAlgorithmSTa95mE() {
        return EdKt.getEd25519(ObjectIdentifier.INSTANCE);
    }
}
