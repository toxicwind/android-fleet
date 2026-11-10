package okhttp3.internal.http2;

import b.a.a.a.a;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class StreamResetException extends IOException {
    public final ErrorCode errorCode;

    public StreamResetException(ErrorCode errorCode) {
        super(a.a("stream was reset: ", errorCode));
        this.errorCode = errorCode;
    }
}
