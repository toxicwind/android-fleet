package kotlinx.io;

import kotlin.Metadata;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: -JvmPlatform.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\"\u0015\u0010\u0004\u001a\u00020\u0005X\u0080\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0006*\n\u0010\u0000\"\u00020\u00012\u00020\u0001*\n\u0010\u0002\"\u00020\u00032\u00020\u0003¨\u0006\u0007"}, d2 = {"IOException", "Ljava/io/IOException;", "EOFException", "Ljava/io/EOFException;", "isWindows", "", "()Z", "kotlinx-io-core"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class _JvmPlatformKt {
    private static final boolean isWindows;

    static {
        String property = System.getProperty("os.name");
        isWindows = property != null ? StringsKt.startsWith$default(property, "Windows", false, 2, (Object) null) : false;
    }

    public static final boolean isWindows() {
        return isWindows;
    }
}
