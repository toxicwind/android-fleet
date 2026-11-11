package kotlinx.io.bytestring;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.HexExtensionsKt;
import kotlin.text.HexFormat;

/* JADX INFO: compiled from: Hex.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0086\u0080\u0004\u001a,\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0086\u0080\u0004\u001a\u0018\u0010\b\u001a\u00020\u0002*\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0086\u0080\u0004¨\u0006\t"}, d2 = {"toHexString", "", "Lkotlinx/io/bytestring/ByteString;", "format", "Lkotlin/text/HexFormat;", "startIndex", "", "endIndex", "hexToByteString", "kotlinx-io-bytestring"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class HexKt {
    public static /* synthetic */ String toHexString$default(ByteString byteString, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            hexFormat = HexFormat.INSTANCE.getDefault();
        }
        return toHexString(byteString, hexFormat);
    }

    public static final String toHexString(ByteString byteString, HexFormat format) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.toHexString(byteString.getData(), 0, byteString.getData().length, format);
    }

    public static /* synthetic */ String toHexString$default(ByteString byteString, int i, int i2, HexFormat hexFormat, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = byteString.getSize();
        }
        if ((i3 & 4) != 0) {
            hexFormat = HexFormat.INSTANCE.getDefault();
        }
        return toHexString(byteString, i, i2, hexFormat);
    }

    public static final String toHexString(ByteString byteString, int i, int i2, HexFormat format) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.toHexString(byteString.getData(), i, i2, format);
    }

    public static /* synthetic */ ByteString hexToByteString$default(String str, HexFormat hexFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            hexFormat = HexFormat.INSTANCE.getDefault();
        }
        return hexToByteString(str, hexFormat);
    }

    public static final ByteString hexToByteString(String str, HexFormat format) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return ByteString.INSTANCE.wrap$kotlinx_io_bytestring(HexExtensionsKt.hexToByteArray(str, format));
    }
}
