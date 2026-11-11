package dev.whyoleg.cryptography.serialization.asn1.modules;

import dev.whyoleg.cryptography.serialization.asn1.BitArray;
import dev.whyoleg.cryptography.serialization.asn1.BitArray$$serializer;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.ContextualSerializer;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* JADX INFO: compiled from: KeyInfo.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u001c2\u00020\u0001:\u0002\u001b\u001cB\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B/\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ%\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0001¢\u0006\u0002\b\u001aR\u001d\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0084\b¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\u0004\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001d"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/modules/SubjectPublicKeyInfo;", "", "algorithm", "Ldev/whyoleg/cryptography/serialization/asn1/modules/AlgorithmIdentifier;", "subjectPublicKey", "Ldev/whyoleg/cryptography/serialization/asn1/BitArray;", "<init>", "(Ldev/whyoleg/cryptography/serialization/asn1/modules/AlgorithmIdentifier;Ldev/whyoleg/cryptography/serialization/asn1/BitArray;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILdev/whyoleg/cryptography/serialization/asn1/modules/AlgorithmIdentifier;Ldev/whyoleg/cryptography/serialization/asn1/BitArray;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getAlgorithm$annotations", "()V", "getAlgorithm", "()Ldev/whyoleg/cryptography/serialization/asn1/modules/AlgorithmIdentifier;", "getSubjectPublicKey", "()Ldev/whyoleg/cryptography/serialization/asn1/BitArray;", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$cryptography_serialization_asn1_modules", "$serializer", "Companion", "cryptography-serialization-asn1-modules"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Serializable
public final class SubjectPublicKeyInfo {
    private final AlgorithmIdentifier algorithm;
    private final BitArray subjectPublicKey;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<KSerializer<Object>>[] $childSerializers = {LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: dev.whyoleg.cryptography.serialization.asn1.modules.SubjectPublicKeyInfo$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return SubjectPublicKeyInfo._childSerializers$_anonymous_();
        }
    }), null};

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
        return new ContextualSerializer(Reflection.getOrCreateKotlinClass(AlgorithmIdentifier.class), DefaultAlgorithmIdentifierSerializer.INSTANCE, new KSerializer[0]);
    }

    public static /* synthetic */ void getAlgorithm$annotations() {
    }

    /* JADX INFO: compiled from: KeyInfo.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/modules/SubjectPublicKeyInfo$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Ldev/whyoleg/cryptography/serialization/asn1/modules/SubjectPublicKeyInfo;", "cryptography-serialization-asn1-modules"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<SubjectPublicKeyInfo> serializer() {
            return SubjectPublicKeyInfo$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ SubjectPublicKeyInfo(int i, AlgorithmIdentifier algorithmIdentifier, BitArray bitArray, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, SubjectPublicKeyInfo$$serializer.INSTANCE.getDescriptor());
        }
        this.algorithm = algorithmIdentifier;
        this.subjectPublicKey = bitArray;
    }

    public SubjectPublicKeyInfo(AlgorithmIdentifier algorithm, BitArray subjectPublicKey) {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        Intrinsics.checkNotNullParameter(subjectPublicKey, "subjectPublicKey");
        this.algorithm = algorithm;
        this.subjectPublicKey = subjectPublicKey;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$cryptography_serialization_asn1_modules(SubjectPublicKeyInfo self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeSerializableElement(serialDesc, 0, $childSerializers[0].getValue(), self.algorithm);
        output.encodeSerializableElement(serialDesc, 1, BitArray$$serializer.INSTANCE, self.subjectPublicKey);
    }

    public final AlgorithmIdentifier getAlgorithm() {
        return this.algorithm;
    }

    public final BitArray getSubjectPublicKey() {
        return this.subjectPublicKey;
    }
}
