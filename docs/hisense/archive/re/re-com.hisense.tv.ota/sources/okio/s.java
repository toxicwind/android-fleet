package okio;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
final class s extends C0069d {
    final /* synthetic */ Socket k;

    s(Socket socket) {
        this.k = socket;
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
        Level level;
        StringBuilder sb;
        Logger logger;
        Throwable th;
        try {
            this.k.close();
        } catch (AssertionError e) {
            if (!t.e(e)) {
                throw e;
            }
            Logger logger2 = t.f275a;
            level = Level.WARNING;
            sb = new StringBuilder();
            th = e;
            logger = logger2;
            sb.append("Failed to close timed out socket ");
            sb.append(this.k);
            logger.log(level, sb.toString(), th);
        } catch (Exception e2) {
            Logger logger3 = t.f275a;
            level = Level.WARNING;
            sb = new StringBuilder();
            th = e2;
            logger = logger3;
            sb.append("Failed to close timed out socket ");
            sb.append(this.k);
            logger.log(level, sb.toString(), th);
        }
    }
}
