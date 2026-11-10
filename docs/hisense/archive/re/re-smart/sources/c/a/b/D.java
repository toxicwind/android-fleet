package c.a.b;

import c.a.AbstractC0225i;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public final class D {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f927a = Logger.getLogger(AbstractC0225i.class.getName());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f928b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c.a.O f929c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Collection<c.a.J> f930d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f931e;

    public D(c.a.O o, int i, long j, String str) {
        Preconditions.checkNotNull(str, "description");
        Preconditions.checkNotNull(o, "logId");
        this.f929c = o;
        if (i > 0) {
            this.f930d = new C(this, i);
        } else {
            this.f930d = null;
        }
        String strA = b.a.a.a.a.a(str, " created");
        c.a.J.a aVar = c.a.J.a.CT_INFO;
        Long lValueOf = Long.valueOf(j);
        Preconditions.checkNotNull(strA, "description");
        Preconditions.checkNotNull(aVar, "severity");
        Preconditions.checkNotNull(lValueOf, "timestampNanos");
        Preconditions.checkState(true, "at least one of channelRef and subchannelRef must be null");
        a(new c.a.J(strA, aVar, lValueOf.longValue(), null, null, null));
    }

    public static /* synthetic */ int a(D d2) {
        int i = d2.f931e;
        d2.f931e = i + 1;
        return i;
    }

    public void b(c.a.J j) {
        synchronized (this.f928b) {
            if (this.f930d != null) {
                this.f930d.add(j);
            }
        }
    }

    public void a(c.a.J j) {
        Level level;
        int iOrdinal = j.f818b.ordinal();
        if (iOrdinal != 2) {
            level = iOrdinal != 3 ? Level.FINEST : Level.FINE;
        } else {
            level = Level.FINER;
        }
        b(j);
        a(this.f929c, level, j.f817a);
    }

    public boolean a() {
        boolean z;
        synchronized (this.f928b) {
            z = this.f930d != null;
        }
        return z;
    }

    public static void a(c.a.O o, Level level, String str) {
        if (f927a.isLoggable(level)) {
            LogRecord logRecord = new LogRecord(level, "[" + o + "] " + str);
            logRecord.setLoggerName(f927a.getName());
            logRecord.setSourceClassName(f927a.getName());
            logRecord.setSourceMethodName("log");
            f927a.log(logRecord);
        }
    }
}
