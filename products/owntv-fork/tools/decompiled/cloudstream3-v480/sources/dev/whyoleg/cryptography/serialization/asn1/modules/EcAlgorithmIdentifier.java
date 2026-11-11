package dev.whyoleg.cryptography.serialization.asn1.modules;

import dev.whyoleg.cryptography.serialization.asn1.ObjectIdentifier;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: EC.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0013\bF\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0019\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0084\b¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0015\u0010\t\u001a\u00020\n8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007¨\u0006\f"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/modules/EcAlgorithmIdentifier;", "Ldev/whyoleg/cryptography/serialization/asn1/modules/AlgorithmIdentifier;", "parameters", "Ldev/whyoleg/cryptography/serialization/asn1/modules/EcParameters;", "<init>", "(Ljava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getParameters-g4m2kjQ", "()Ljava/lang/String;", "Ljava/lang/String;", "algorithm", "Ldev/whyoleg/cryptography/serialization/asn1/ObjectIdentifier;", "getAlgorithm-STa95mE", "cryptography-serialization-asn1-modules"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class EcAlgorithmIdentifier implements AlgorithmIdentifier {
    private final String parameters;

    public /* synthetic */ EcAlgorithmIdentifier(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    private EcAlgorithmIdentifier(String str) {
        this.parameters = str;
    }

    @Override // dev.whyoleg.cryptography.serialization.asn1.modules.AlgorithmIdentifier
    public /* bridge */ /* synthetic */ Object getParameters() {
        String parameters = getParameters();
        if (parameters != null) {
            return EcParameters.m8777boximpl(parameters);
        }
        return null;
    }

    /* JADX INFO: renamed from: getParameters-g4m2kjQ, reason: not valid java name and from getter */
    public String getParameters() {
        return this.parameters;
    }

    @Override // dev.whyoleg.cryptography.serialization.asn1.modules.AlgorithmIdentifier
    /* JADX INFO: renamed from: getAlgorithm-STa95mE */
    public String getAlgorithm() {
        return ECKt.getEC(ObjectIdentifier.INSTANCE);
    }
}
