package okhttp3.internal;

import java.io.EOFException;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSource;

/* JADX INFO: compiled from: IsProbablyUtf8.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0000¨\u0006\u0005"}, d2 = {"isProbablyUtf8", "", "Lokio/BufferedSource;", "codePointLimit", "", "okhttp"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class IsProbablyUtf8Kt {
    public static /* synthetic */ boolean isProbablyUtf8$default(BufferedSource bufferedSource, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = Long.MAX_VALUE;
        }
        return isProbablyUtf8(bufferedSource, j);
    }

    public static final boolean isProbablyUtf8(BufferedSource bufferedSource, long j) throws IOException {
        Intrinsics.checkNotNullParameter(bufferedSource, "<this>");
        try {
            BufferedSource bufferedSourcePeek = bufferedSource.peek();
            for (long j2 = 0; j2 < j && !bufferedSourcePeek.exhausted(); j2++) {
                int utf8CodePoint = bufferedSourcePeek.readUtf8CodePoint();
                if (Character.isISOControl(utf8CodePoint) && !Character.isWhitespace(utf8CodePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }
}
