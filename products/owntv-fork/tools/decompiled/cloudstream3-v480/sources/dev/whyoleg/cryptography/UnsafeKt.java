package dev.whyoleg.cryptography;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.bytestring.ByteString;
import kotlinx.io.bytestring.unsafe.UnsafeByteStringOperations;

/* JADX INFO: compiled from: unsafe.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0080\u0088\u0004\u001a\u000e\u0010\u0003\u001a\u00020\u0002*\u00020\u0001H\u0080\u0088\u0004¨\u0006\u0004"}, d2 = {"asByteString", "Lkotlinx/io/bytestring/ByteString;", "", "asByteArray", "cryptography-core"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class UnsafeKt {
    public static final ByteString asByteString(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(bArr);
    }

    public static final byte[] asByteArray(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
        return byteString.getData();
    }
}
