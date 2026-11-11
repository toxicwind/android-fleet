package com.fleeksoft.ksoup.exception;

import io.ktor.http.ContentType;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UncheckedIOException.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0015\b\u0016\u0012\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\u0006\u0010\nJ\b\u0010\u000b\u001a\u0004\u0018\u00010\f¨\u0006\r"}, d2 = {"Lcom/fleeksoft/ksoup/exception/UncheckedIOException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "cause", "Ljava/io/IOException;", "Lcom/fleeksoft/io/exception/IOException;", "<init>", "(Ljava/io/IOException;)V", ContentType.Message.TYPE, "", "(Ljava/lang/String;)V", "ioException", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class UncheckedIOException extends Exception {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UncheckedIOException(IOException cause) {
        super(cause);
        Intrinsics.checkNotNullParameter(cause, "cause");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UncheckedIOException(String message) {
        super(new IOException(message));
        Intrinsics.checkNotNullParameter(message, "message");
    }

    public final Throwable ioException() {
        return getCause();
    }
}
