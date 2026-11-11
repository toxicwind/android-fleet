package dev.whyoleg.cryptography.bigint;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.HexExtensionsKt;
import kotlin.text.HexFormat;

/* JADX INFO: compiled from: hex.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0086\u0080\u0004\u001a\u0018\u0010\u0005\u001a\u00020\u0002*\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0086\u0080\u0004¨\u0006\u0006"}, d2 = {"toHexString", "", "Ldev/whyoleg/cryptography/bigint/BigInt;", "format", "Lkotlin/text/HexFormat;", "hexToBigInt", "cryptography-bigint"}, k = 5, mv = {2, 3, 0}, xi = 48, xs = "dev/whyoleg/cryptography/bigint/BigIntKt")
final /* synthetic */ class BigIntKt__HexKt {
    public static final String toHexString(BigInt bigInt, HexFormat format) {
        Intrinsics.checkNotNullParameter(bigInt, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.toHexString(BigIntKt.encodeToByteArray(bigInt), format);
    }

    public static /* synthetic */ String toHexString$default(BigInt bigInt, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            hexFormat = HexFormat.INSTANCE.getDefault();
        }
        return BigIntKt.toHexString(bigInt, hexFormat);
    }

    public static final BigInt hexToBigInt(String str, HexFormat format) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return BigIntKt.decodeToBigInt(HexExtensionsKt.hexToByteArray(str, format));
    }

    public static /* synthetic */ BigInt hexToBigInt$default(String str, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            hexFormat = HexFormat.INSTANCE.getDefault();
        }
        return BigIntKt.hexToBigInt(str, hexFormat);
    }
}
