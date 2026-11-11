package dev.whyoleg.cryptography.serialization.asn1.modules;

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
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* JADX INFO: compiled from: RSA.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 '2\u00020\u0001:\u0002&'BQ\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005¢\u0006\u0004\b\r\u0010\u000eBs\b\u0010\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\r\u0010\u0012J%\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0001¢\u0006\u0002\b%R\u0015\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0015\u0010\u0004\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0015\u0010\u0006\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0015\u0010\u0007\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0015\u0010\b\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0015\u0010\t\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0015\u0010\n\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016R\u0015\u0010\u000b\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0016R\u0015\u0010\f\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0016¨\u0006("}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/modules/RsaPrivateKey;", "", "version", "", "modulus", "Ldev/whyoleg/cryptography/bigint/BigInt;", "publicExponent", "privateExponent", "prime1", "prime2", "exponent1", "exponent2", "coefficient", "<init>", "(ILdev/whyoleg/cryptography/bigint/BigInt;Ldev/whyoleg/cryptography/bigint/BigInt;Ldev/whyoleg/cryptography/bigint/BigInt;Ldev/whyoleg/cryptography/bigint/BigInt;Ldev/whyoleg/cryptography/bigint/BigInt;Ldev/whyoleg/cryptography/bigint/BigInt;Ldev/whyoleg/cryptography/bigint/BigInt;Ldev/whyoleg/cryptography/bigint/BigInt;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IILdev/whyoleg/cryptography/bigint/BigInt;Ldev/whyoleg/cryptography/bigint/BigInt;Ldev/whyoleg/cryptography/bigint/BigInt;Ldev/whyoleg/cryptography/bigint/BigInt;Ldev/whyoleg/cryptography/bigint/BigInt;Ldev/whyoleg/cryptography/bigint/BigInt;Ldev/whyoleg/cryptography/bigint/BigInt;Ldev/whyoleg/cryptography/bigint/BigInt;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getVersion", "()I", "getModulus", "()Ldev/whyoleg/cryptography/bigint/BigInt;", "getPublicExponent", "getPrivateExponent", "getPrime1", "getPrime2", "getExponent1", "getExponent2", "getCoefficient", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$cryptography_serialization_asn1_modules", "$serializer", "Companion", "cryptography-serialization-asn1-modules"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Serializable
public final class RsaPrivateKey {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final BigInt coefficient;
    private final BigInt exponent1;
    private final BigInt exponent2;
    private final BigInt modulus;
    private final BigInt prime1;
    private final BigInt prime2;
    private final BigInt privateExponent;
    private final BigInt publicExponent;
    private final int version;

    /* JADX INFO: compiled from: RSA.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/modules/RsaPrivateKey$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Ldev/whyoleg/cryptography/serialization/asn1/modules/RsaPrivateKey;", "cryptography-serialization-asn1-modules"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<RsaPrivateKey> serializer() {
            return RsaPrivateKey$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ RsaPrivateKey(int i, int i2, BigInt bigInt, BigInt bigInt2, BigInt bigInt3, BigInt bigInt4, BigInt bigInt5, BigInt bigInt6, BigInt bigInt7, BigInt bigInt8, SerializationConstructorMarker serializationConstructorMarker) {
        if (511 != (i & 511)) {
            PluginExceptionsKt.throwMissingFieldException(i, 511, RsaPrivateKey$$serializer.INSTANCE.getDescriptor());
        }
        this.version = i2;
        this.modulus = bigInt;
        this.publicExponent = bigInt2;
        this.privateExponent = bigInt3;
        this.prime1 = bigInt4;
        this.prime2 = bigInt5;
        this.exponent1 = bigInt6;
        this.exponent2 = bigInt7;
        this.coefficient = bigInt8;
    }

    public RsaPrivateKey(int i, BigInt modulus, BigInt publicExponent, BigInt privateExponent, BigInt prime1, BigInt prime2, BigInt exponent1, BigInt exponent2, BigInt coefficient) {
        Intrinsics.checkNotNullParameter(modulus, "modulus");
        Intrinsics.checkNotNullParameter(publicExponent, "publicExponent");
        Intrinsics.checkNotNullParameter(privateExponent, "privateExponent");
        Intrinsics.checkNotNullParameter(prime1, "prime1");
        Intrinsics.checkNotNullParameter(prime2, "prime2");
        Intrinsics.checkNotNullParameter(exponent1, "exponent1");
        Intrinsics.checkNotNullParameter(exponent2, "exponent2");
        Intrinsics.checkNotNullParameter(coefficient, "coefficient");
        this.version = i;
        this.modulus = modulus;
        this.publicExponent = publicExponent;
        this.privateExponent = privateExponent;
        this.prime1 = prime1;
        this.prime2 = prime2;
        this.exponent1 = exponent1;
        this.exponent2 = exponent2;
        this.coefficient = coefficient;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$cryptography_serialization_asn1_modules(RsaPrivateKey self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeIntElement(serialDesc, 0, self.version);
        output.encodeSerializableElement(serialDesc, 1, BigIntAsStringSerializer.INSTANCE, self.modulus);
        output.encodeSerializableElement(serialDesc, 2, BigIntAsStringSerializer.INSTANCE, self.publicExponent);
        output.encodeSerializableElement(serialDesc, 3, BigIntAsStringSerializer.INSTANCE, self.privateExponent);
        output.encodeSerializableElement(serialDesc, 4, BigIntAsStringSerializer.INSTANCE, self.prime1);
        output.encodeSerializableElement(serialDesc, 5, BigIntAsStringSerializer.INSTANCE, self.prime2);
        output.encodeSerializableElement(serialDesc, 6, BigIntAsStringSerializer.INSTANCE, self.exponent1);
        output.encodeSerializableElement(serialDesc, 7, BigIntAsStringSerializer.INSTANCE, self.exponent2);
        output.encodeSerializableElement(serialDesc, 8, BigIntAsStringSerializer.INSTANCE, self.coefficient);
    }

    public final int getVersion() {
        return this.version;
    }

    public final BigInt getModulus() {
        return this.modulus;
    }

    public final BigInt getPublicExponent() {
        return this.publicExponent;
    }

    public final BigInt getPrivateExponent() {
        return this.privateExponent;
    }

    public final BigInt getPrime1() {
        return this.prime1;
    }

    public final BigInt getPrime2() {
        return this.prime2;
    }

    public final BigInt getExponent1() {
        return this.exponent1;
    }

    public final BigInt getExponent2() {
        return this.exponent2;
    }

    public final BigInt getCoefficient() {
        return this.coefficient;
    }
}
