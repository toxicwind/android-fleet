package c.a.c;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public final class e implements c.a.c.a.a.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f1685a = Logger.getLogger(r.class.getName());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f1686b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c.a.c.a.a.c f1687c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final s f1688d;

    interface a {
    }

    @VisibleForTesting
    public e(a aVar, c.a.c.a.a.c cVar, s sVar) {
        Preconditions.checkNotNull(aVar, "transportExceptionHandler");
        this.f1686b = aVar;
        Preconditions.checkNotNull(cVar, "frameWriter");
        this.f1687c = cVar;
        Preconditions.checkNotNull(sVar, "frameLogger");
        this.f1688d = sVar;
    }

    @Override // c.a.c.a.a.c
    public void a(c.a.c.a.a.i iVar) {
        s sVar = this.f1688d;
        s.a aVar = s.a.OUTBOUND;
        if (sVar.a()) {
            sVar.f1731a.log(sVar.f1732b, aVar + " SETTINGS: ack=true");
        }
        try {
            this.f1687c.a(iVar);
        } catch (IOException e2) {
            ((r) this.f1686b).a(e2);
        }
    }

    @Override // c.a.c.a.a.c
    public void b(c.a.c.a.a.i iVar) {
        this.f1688d.a(s.a.OUTBOUND, iVar);
        try {
            this.f1687c.b(iVar);
        } catch (IOException e2) {
            ((r) this.f1686b).a(e2);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            this.f1687c.close();
        } catch (IOException e2) {
            f1685a.log(e2.getClass().equals(IOException.class) ? Level.FINE : Level.INFO, "Failed closing connection", (Throwable) e2);
        }
    }

    @Override // c.a.c.a.a.c
    public void connectionPreface() {
        try {
            this.f1687c.connectionPreface();
        } catch (IOException e2) {
            ((r) this.f1686b).a(e2);
        }
    }

    @Override // c.a.c.a.a.c
    public void data(boolean z, int i, d.g gVar, int i2) {
        s sVar = this.f1688d;
        s.a aVar = s.a.OUTBOUND;
        gVar.a();
        sVar.a(aVar, i, gVar, i2, z);
        try {
            this.f1687c.data(z, i, gVar, i2);
        } catch (IOException e2) {
            ((r) this.f1686b).a(e2);
        }
    }

    @Override // c.a.c.a.a.c
    public void flush() {
        try {
            this.f1687c.flush();
        } catch (IOException e2) {
            ((r) this.f1686b).a(e2);
        }
    }

    @Override // c.a.c.a.a.c
    public int maxDataLength() {
        return this.f1687c.maxDataLength();
    }

    @Override // c.a.c.a.a.c
    public void ping(boolean z, int i, int i2) {
        if (z) {
            s sVar = this.f1688d;
            s.a aVar = s.a.OUTBOUND;
            long j = (4294967295L & ((long) i2)) | (((long) i) << 32);
            if (sVar.a()) {
                sVar.f1731a.log(sVar.f1732b, aVar + " PING: ack=true bytes=" + j);
            }
        } else {
            this.f1688d.a(s.a.OUTBOUND, (4294967295L & ((long) i2)) | (((long) i) << 32));
        }
        try {
            this.f1687c.ping(z, i, i2);
        } catch (IOException e2) {
            ((r) this.f1686b).a(e2);
        }
    }

    @Override // c.a.c.a.a.c
    public void windowUpdate(int i, long j) {
        this.f1688d.a(s.a.OUTBOUND, i, j);
        try {
            this.f1687c.windowUpdate(i, j);
        } catch (IOException e2) {
            ((r) this.f1686b).a(e2);
        }
    }

    @Override // c.a.c.a.a.c
    public void a(boolean z, boolean z2, int i, int i2, List<c.a.c.a.a.d> list) {
        try {
            this.f1687c.a(z, z2, i, i2, list);
        } catch (IOException e2) {
            ((r) this.f1686b).a(e2);
        }
    }

    @Override // c.a.c.a.a.c
    public void a(int i, c.a.c.a.a.a aVar) {
        this.f1688d.a(s.a.OUTBOUND, i, aVar);
        try {
            this.f1687c.a(i, aVar);
        } catch (IOException e2) {
            ((r) this.f1686b).a(e2);
        }
    }

    @Override // c.a.c.a.a.c
    public void a(int i, c.a.c.a.a.a aVar, byte[] bArr) {
        this.f1688d.a(s.a.OUTBOUND, i, aVar, d.j.a(bArr));
        try {
            this.f1687c.a(i, aVar, bArr);
            this.f1687c.flush();
        } catch (IOException e2) {
            ((r) this.f1686b).a(e2);
        }
    }
}
