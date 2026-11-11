package dev.whyoleg.cryptography.serialization.asn1;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ByteArraySerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* JADX INFO: compiled from: BitArray.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00192\u00020\u0001:\u0002\u0018\u0019B\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B-\b\u0010\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0006\u0010\u000bJ%\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0001¢\u0006\u0002\b\u0017R\u0015\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0004\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/BitArray;", "", "unusedBits", "", "byteArray", "", "<init>", "(I[B)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(II[BLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getUnusedBits", "()I", "getByteArray", "()[B", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$cryptography_serialization_asn1", "$serializer", "Companion", "cryptography-serialization-asn1"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Serializable
public final class BitArray {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final byte[] byteArray;
    private final int unusedBits;

    /* JADX INFO: compiled from: BitArray.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/BitArray$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Ldev/whyoleg/cryptography/serialization/asn1/BitArray;", "cryptography-serialization-asn1"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<BitArray> serializer() {
            return BitArray$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ BitArray(int i, int i2, byte[] bArr, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, BitArray$$serializer.INSTANCE.getDescriptor());
        }
        this.unusedBits = i2;
        this.byteArray = bArr;
        if (bArr.length == 0) {
            if (i2 != 0) {
                throw new IllegalStateException("empty array couldn't have unused bits".toString());
            }
        } else {
            if (i2 <= Integer.numberOfTrailingZeros(ArraysKt.last(bArr) | 256)) {
                return;
            }
            throw new IllegalStateException(("At least " + i2 + " last bits should be unused").toString());
        }
    }

    public BitArray(int i, byte[] byteArray) {
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
        this.unusedBits = i;
        this.byteArray = byteArray;
        if (byteArray.length == 0) {
            if (i != 0) {
                throw new IllegalStateException("empty array couldn't have unused bits".toString());
            }
        } else {
            if (i <= Integer.numberOfTrailingZeros(ArraysKt.last(byteArray) | 256)) {
                return;
            }
            throw new IllegalStateException(("At least " + i + " last bits should be unused").toString());
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$cryptography_serialization_asn1(BitArray self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeIntElement(serialDesc, 0, self.unusedBits);
        output.encodeSerializableElement(serialDesc, 1, ByteArraySerializer.INSTANCE, self.byteArray);
    }

    public final int getUnusedBits() {
        return this.unusedBits;
    }

    public final byte[] getByteArray() {
        return this.byteArray;
    }
}
