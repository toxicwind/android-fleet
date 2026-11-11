package dev.whyoleg.cryptography.providers.base.algorithms;

import dev.whyoleg.cryptography.CryptographyProviderApi;
import dev.whyoleg.cryptography.bigint.BigInt;
import dev.whyoleg.cryptography.serialization.asn1.Der;
import dev.whyoleg.cryptography.serialization.asn1.modules.DssSignatureValue;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseDss.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H\u0087\u0080\u0004\u001a*\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003H\u0087\u0080\u0004¨\u0006\t"}, d2 = {"convertDssDerSignatureToRaw", "", "orderSize", "", "derSignature", "convertDssRawSignatureToDer", "rawSignature", "startIndex", "endIndex", "cryptography-provider-base"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class BaseDssKt {
    @CryptographyProviderApi
    public static final byte[] convertDssDerSignatureToRaw(int i, byte[] derSignature) {
        Intrinsics.checkNotNullParameter(derSignature, "derSignature");
        DssSignatureValue dssSignatureValue = (DssSignatureValue) Der.INSTANCE.decodeFromByteArray(DssSignatureValue.INSTANCE.serializer(), derSignature);
        byte[] bArrMagnitudeToByteArray = dssSignatureValue.getR().magnitudeToByteArray();
        byte[] bArrMagnitudeToByteArray2 = dssSignatureValue.getS().magnitudeToByteArray();
        int i2 = i * 2;
        byte[] bArr = new byte[i2];
        ArraysKt.copyInto$default(bArrMagnitudeToByteArray, bArr, i - bArrMagnitudeToByteArray.length, 0, 0, 12, (Object) null);
        ArraysKt.copyInto$default(bArrMagnitudeToByteArray2, bArr, i2 - bArrMagnitudeToByteArray2.length, 0, 0, 12, (Object) null);
        return bArr;
    }

    @CryptographyProviderApi
    public static final byte[] convertDssRawSignatureToDer(int i, byte[] rawSignature, int i2, int i3) {
        Intrinsics.checkNotNullParameter(rawSignature, "rawSignature");
        int i4 = i3 - i2;
        int i5 = i * 2;
        if (i4 != i5) {
            throw new IllegalStateException(("Expected signature size " + i5 + ", received: " + i4).toString());
        }
        int i6 = i + i2;
        return Der.INSTANCE.encodeToByteArray(DssSignatureValue.INSTANCE.serializer(), new DssSignatureValue(BigInt.INSTANCE.fromMagnitude(1, ArraysKt.copyOfRange(rawSignature, i2, i6)), BigInt.INSTANCE.fromMagnitude(1, ArraysKt.copyOfRange(rawSignature, i6, i3))));
    }
}
