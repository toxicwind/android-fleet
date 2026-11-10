package d;

import com.ju.lib.datacommunication.network.http.dns.Tools;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public final class s extends C0244c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Socket f2128a;

    public s(Socket socket) {
        this.f2128a = socket;
    }

    @Override // d.C0244c
    public IOException newTimeoutException(IOException iOException) {
        SocketTimeoutException socketTimeoutException = new SocketTimeoutException(Tools.KEY_TIMEOUT);
        if (iOException != null) {
            socketTimeoutException.initCause(iOException);
        }
        return socketTimeoutException;
    }

    @Override // d.C0244c
    public void timedOut() {
        try {
            this.f2128a.close();
        } catch (AssertionError e2) {
            if (!t.a(e2)) {
                throw e2;
            }
            Logger logger = t.f2129a;
            Level level = Level.WARNING;
            StringBuilder sbA = b.a.a.a.a.a("Failed to close timed out socket ");
            sbA.append(this.f2128a);
            logger.log(level, sbA.toString(), (Throwable) e2);
        } catch (Exception e3) {
            Logger logger2 = t.f2129a;
            Level level2 = Level.WARNING;
            StringBuilder sbA2 = b.a.a.a.a.a("Failed to close timed out socket ");
            sbA2.append(this.f2128a);
            logger2.log(level2, sbA2.toString(), (Throwable) e3);
        }
    }
}
