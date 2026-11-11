package com.fleeksoft.charset;

import com.fleeksoft.io.ByteBufferFactory;
import io.ktor.http.auth.HttpAuthHeader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: compiled from: CharsetExt.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0012\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u001a\u001b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005¢\u0006\u0002\u0010\u0006\u001a/\u0010\u0007\u001a\u00020\u0002*\u00020\u00012\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"toByteArray", "", "", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "Lcom/fleeksoft/charset/Charset;", "(Ljava/lang/String;Ljava/nio/charset/Charset;)[B", "decodeToString", DebugKt.DEBUG_PROPERTY_VALUE_OFF, "", "len", "([BLjava/nio/charset/Charset;II)Ljava/lang/String;", "charset_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class CharsetExtKt {
    public static final byte[] toByteArray(String str, Charset charset) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        ByteBuffer byteBufferEncode = charset.encode(str);
        byte[] bArr = null;
        if (byteBufferEncode.hasArray() && byteBufferEncode.arrayOffset() == 0) {
            byte[] bArrArray = byteBufferEncode.array();
            if (bArrArray.length == byteBufferEncode.remaining()) {
                bArr = bArrArray;
            }
        }
        if (bArr != null) {
            return bArr;
        }
        byte[] bArr2 = new byte[byteBufferEncode.remaining()];
        byteBufferEncode.get(bArr2);
        return bArr2;
    }

    public static /* synthetic */ String decodeToString$default(byte[] bArr, Charset charset, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        return decodeToString(bArr, charset, i, i2);
    }

    public static final String decodeToString(byte[] bArr, Charset charset, int i, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        String string = charset.decode(ByteBufferFactory.INSTANCE.wrap(bArr, i, i2)).toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }
}
