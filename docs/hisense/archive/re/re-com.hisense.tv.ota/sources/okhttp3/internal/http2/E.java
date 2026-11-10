package okhttp3.internal.http2;

import java.io.IOException;
import java.net.SocketTimeoutException;
import okio.C0069d;

/* JADX INFO: loaded from: classes.dex */
class E extends C0069d {
    final /* synthetic */ F k;

    E(F f) {
        this.k = f;
    }

    @Override // okio.C0069d
    protected IOException o(IOException iOException) {
        SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
        if (iOException != null) {
            socketTimeoutException.initCause(iOException);
        }
        return socketTimeoutException;
    }

    @Override // okio.C0069d
    protected void t() {
        this.k.h(ErrorCode.CANCEL);
        this.k.d.h0();
    }

    public void u() {
        if (n()) {
            throw o(null);
        }
    }
}
