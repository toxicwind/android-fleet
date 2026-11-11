package dev.whyoleg.cryptography.providers.base;

import dev.whyoleg.cryptography.CryptographyProviderApi;
import io.ktor.http.ContentDisposition;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: bytes.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u001a\"\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0087\u0080\u0004\u001a\u0016\u0010\f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\r\u001a\u00020\tH\u0087\u0080\u0004\"\u001d\u0010\u0000\u001a\u00020\u00018\u0006X\u0087\u0084\b¢\u0006\u000e\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u000e"}, d2 = {"EmptyByteArray", "", "getEmptyByteArray$annotations", "()V", "getEmptyByteArray", "()[B", "checkBounds", "", ContentDisposition.Parameters.Size, "", "startIndex", "endIndex", "ensureSizeExactly", "expectedSize", "cryptography-provider-base"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class BytesKt {
    private static final byte[] EmptyByteArray = new byte[0];

    @CryptographyProviderApi
    public static /* synthetic */ void getEmptyByteArray$annotations() {
    }

    public static final byte[] getEmptyByteArray() {
        return EmptyByteArray;
    }

    @CryptographyProviderApi
    public static final void checkBounds(int i, int i2, int i3) {
        if (i2 < 0 || i3 > i) {
            throw new IndexOutOfBoundsException("startIndex (" + i2 + ") and endIndex (" + i3 + ") are not within the range [0..size(" + i + "))");
        }
        if (i2 <= i3) {
            return;
        }
        throw new IllegalArgumentException("startIndex (" + i2 + ") > endIndex (" + i3 + ')');
    }

    @CryptographyProviderApi
    public static final byte[] ensureSizeExactly(byte[] bArr, int i) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (bArr.length == i) {
            return bArr;
        }
        byte[] bArrCopyOf = Arrays.copyOf(bArr, i);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
        return bArrCopyOf;
    }
}
