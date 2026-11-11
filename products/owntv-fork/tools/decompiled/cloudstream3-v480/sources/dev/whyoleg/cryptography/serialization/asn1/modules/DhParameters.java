package dev.whyoleg.cryptography.serialization.asn1.modules;

import androidx.media3.extractor.text.ttml.TtmlNode;
import dev.whyoleg.cryptography.bigint.BigInt;
import dev.whyoleg.cryptography.bigint.BigIntAsStringSerializer;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* JADX INFO: compiled from: DH.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u001c2\u00020\u0001:\u0002\u001b\u001cB%\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bB9\b\u0010\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0007\u0010\fJ%\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0001¢\u0006\u0002\b\u001aR\u0015\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0015\u0010\u0004\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u0084\b¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001d"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/modules/DhParameters;", "", "prime", "Ldev/whyoleg/cryptography/bigint/BigInt;", TtmlNode.RUBY_BASE, "privateValueLength", "", "<init>", "(Ldev/whyoleg/cryptography/bigint/BigInt;Ldev/whyoleg/cryptography/bigint/BigInt;Ljava/lang/Integer;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILdev/whyoleg/cryptography/bigint/BigInt;Ldev/whyoleg/cryptography/bigint/BigInt;Ljava/lang/Integer;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getPrime", "()Ldev/whyoleg/cryptography/bigint/BigInt;", "getBase", "getPrivateValueLength", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$cryptography_serialization_asn1_modules", "$serializer", "Companion", "cryptography-serialization-asn1-modules"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Serializable
public final class DhParameters {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final BigInt base;
    private final BigInt prime;
    private final Integer privateValueLength;

    /* JADX INFO: compiled from: DH.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/modules/DhParameters$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Ldev/whyoleg/cryptography/serialization/asn1/modules/DhParameters;", "cryptography-serialization-asn1-modules"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<DhParameters> serializer() {
            return DhParameters$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ DhParameters(int i, BigInt bigInt, BigInt bigInt2, Integer num, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, DhParameters$$serializer.INSTANCE.getDescriptor());
        }
        this.prime = bigInt;
        this.base = bigInt2;
        if ((i & 4) == 0) {
            this.privateValueLength = null;
        } else {
            this.privateValueLength = num;
        }
    }

    public DhParameters(BigInt prime, BigInt base, Integer num) {
        Intrinsics.checkNotNullParameter(prime, "prime");
        Intrinsics.checkNotNullParameter(base, "base");
        this.prime = prime;
        this.base = base;
        this.privateValueLength = num;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$cryptography_serialization_asn1_modules(DhParameters self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeSerializableElement(serialDesc, 0, BigIntAsStringSerializer.INSTANCE, self.prime);
        output.encodeSerializableElement(serialDesc, 1, BigIntAsStringSerializer.INSTANCE, self.base);
        if (!output.shouldEncodeElementDefault(serialDesc, 2) && self.privateValueLength == null) {
            return;
        }
        output.encodeNullableSerializableElement(serialDesc, 2, IntSerializer.INSTANCE, self.privateValueLength);
    }

    public /* synthetic */ DhParameters(BigInt bigInt, BigInt bigInt2, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(bigInt, bigInt2, (i & 4) != 0 ? null : num);
    }

    public final BigInt getPrime() {
        return this.prime;
    }

    public final BigInt getBase() {
        return this.base;
    }

    public final Integer getPrivateValueLength() {
        return this.privateValueLength;
    }
}
