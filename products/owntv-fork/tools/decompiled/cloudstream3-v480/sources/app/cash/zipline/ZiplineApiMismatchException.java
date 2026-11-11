package app.cash.zipline;

import io.ktor.http.ContentType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ZiplineApiMismatchException.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000 \t2\u00060\u0001j\u0002`\u0002:\u0001\tB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0015\u0010\u0003\u001a\u00020\u0004X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Lapp/cash/zipline/ZiplineApiMismatchException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", ContentType.Message.TYPE, "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "Companion", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ZiplineApiMismatchException extends Exception {
    public static final String UNKNOWN_FUNCTION = "<unknown function>";
    public static final String UNKNOWN_SERVICE = "<unknown service>";
    private final String message;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ZiplineApiMismatchException(String message) {
        super(message);
        Intrinsics.checkNotNullParameter(message, "message");
        this.message = message;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }
}
