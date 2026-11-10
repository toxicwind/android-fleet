package c.a.c;

import com.google.common.base.Preconditions;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.EnumMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Logger f1731a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Level f1732b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public enum a {
        INBOUND,
        OUTBOUND
    }

    private enum b {
        HEADER_TABLE_SIZE(1),
        ENABLE_PUSH(2),
        MAX_CONCURRENT_STREAMS(4),
        MAX_FRAME_SIZE(5),
        MAX_HEADER_LIST_SIZE(6),
        INITIAL_WINDOW_SIZE(7);

        public final int h;

        b(int i) {
            this.h = i;
        }

        public int a() {
            return this.h;
        }
    }

    public s(Level level, Class<?> cls) {
        Logger logger = Logger.getLogger(cls.getName());
        Preconditions.checkNotNull(level, FirebaseAnalytics.Param.LEVEL);
        this.f1732b = level;
        Preconditions.checkNotNull(logger, "logger");
        this.f1731a = logger;
    }

    public static String a(d.g gVar) {
        long j = gVar.f2102c;
        if (j <= 64) {
            return gVar.q().b();
        }
        int iMin = (int) Math.min(j, 64L);
        StringBuilder sb = new StringBuilder();
        sb.append((iMin == 0 ? d.j.f2110b : new d.A(gVar, iMin)).b());
        sb.append("...");
        return sb.toString();
    }

    public final boolean a() {
        return this.f1731a.isLoggable(this.f1732b);
    }

    public void a(a aVar, int i, d.g gVar, int i2, boolean z) {
        if (a()) {
            this.f1731a.log(this.f1732b, aVar + " DATA: streamId=" + i + " endStream=" + z + " length=" + i2 + " bytes=" + a(gVar));
        }
    }

    public void a(a aVar, int i, c.a.c.a.a.a aVar2) {
        if (a()) {
            this.f1731a.log(this.f1732b, aVar + " RST_STREAM: streamId=" + i + " errorCode=" + aVar2);
        }
    }

    public void a(a aVar, c.a.c.a.a.i iVar) {
        if (a()) {
            Logger logger = this.f1731a;
            Level level = this.f1732b;
            StringBuilder sb = new StringBuilder();
            sb.append(aVar);
            sb.append(" SETTINGS: ack=false settings=");
            EnumMap enumMap = new EnumMap(b.class);
            for (b bVar : b.values()) {
                if (iVar.a(bVar.a())) {
                    enumMap.put(bVar, Integer.valueOf(iVar.f1622d[bVar.a()]));
                }
            }
            sb.append(enumMap.toString());
            logger.log(level, sb.toString());
        }
    }

    public void a(a aVar, long j) {
        if (a()) {
            this.f1731a.log(this.f1732b, aVar + " PING: ack=false bytes=" + j);
        }
    }

    public void a(a aVar, int i, c.a.c.a.a.a aVar2, d.j jVar) {
        if (a()) {
            Logger logger = this.f1731a;
            Level level = this.f1732b;
            StringBuilder sb = new StringBuilder();
            sb.append(aVar);
            sb.append(" GO_AWAY: lastStreamId=");
            sb.append(i);
            sb.append(" errorCode=");
            sb.append(aVar2);
            sb.append(" length=");
            sb.append(jVar.g());
            sb.append(" bytes=");
            d.g gVar = new d.g();
            gVar.a(jVar);
            sb.append(a(gVar));
            logger.log(level, sb.toString());
        }
    }

    public void a(a aVar, int i, long j) {
        if (a()) {
            this.f1731a.log(this.f1732b, aVar + " WINDOW_UPDATE: streamId=" + i + " windowSizeIncrement=" + j);
        }
    }
}
