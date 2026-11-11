package kotlinx.io;

import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: JvmCore.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0086\u0080\u0004\u001a\u000e\u0010\u0003\u001a\u00020\u0004*\u00020\u0005H\u0086\u0080\u0004\"\u001d\u0010\u0006\u001a\u00020\u0007*\u00060\bj\u0002`\t8@X\u0080\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\n\"\u0015\u0010\u000b\u001a\u00020\fX\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"asSink", "Lkotlinx/io/RawSink;", "Ljava/io/OutputStream;", "asSource", "Lkotlinx/io/RawSource;", "Ljava/io/InputStream;", "isAndroidGetsocknameError", "", "Ljava/lang/AssertionError;", "Lkotlin/AssertionError;", "(Ljava/lang/AssertionError;)Z", "SystemLineSeparator", "", "getSystemLineSeparator", "()Ljava/lang/String;", "kotlinx-io-core"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class JvmCoreKt {
    private static final String SystemLineSeparator;

    public static final RawSink asSink(OutputStream outputStream) {
        Intrinsics.checkNotNullParameter(outputStream, "<this>");
        return new OutputStreamSink(outputStream);
    }

    public static final RawSource asSource(InputStream inputStream) {
        Intrinsics.checkNotNullParameter(inputStream, "<this>");
        return new InputStreamSource(inputStream);
    }

    public static final boolean isAndroidGetsocknameError(AssertionError assertionError) {
        Intrinsics.checkNotNullParameter(assertionError, "<this>");
        if (assertionError.getCause() != null) {
            String message = assertionError.getMessage();
            if (message != null ? StringsKt.contains$default((CharSequence) message, (CharSequence) "getsockname failed", false, 2, (Object) null) : false) {
                return true;
            }
        }
        return false;
    }

    static {
        String strLineSeparator = System.lineSeparator();
        Intrinsics.checkNotNullExpressionValue(strLineSeparator, "lineSeparator(...)");
        SystemLineSeparator = strLineSeparator;
    }

    public static final String getSystemLineSeparator() {
        return SystemLineSeparator;
    }
}
