package c.a.b;

import androidx.core.graphics.PaintCompat;
import c.a.AbstractC0230n;
import c.a.C0219ea;
import c.a.C0220f;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.mediatek.twoworlds.tv.model.MtkTvRatingConvert2Goo;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: renamed from: c.a.b.mb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0149mb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f1358a = Logger.getLogger(C0149mb.class.getName());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C0219ea.e<Long> f1359b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final C0219ea.e<String> f1360c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final C0219ea.e<byte[]> f1361d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final C0219ea.e<String> f1362e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final C0219ea.e<byte[]> f1363f;
    public static final C0219ea.e<String> g;
    public static final C0219ea.e<String> h;
    public static final C0219ea.e<String> i;
    public static final long j;
    public static final c.a.oa k;
    public static final C0220f.a<Boolean> l;
    public static final AbstractC0230n m;
    public static final Id.b<Executor> n;
    public static final Id.b<ScheduledExecutorService> o;
    public static final Supplier<Stopwatch> p;

    /* JADX INFO: renamed from: c.a.b.mb$a */
    private static final class a implements c.a.P.a<byte[]> {
        public /* synthetic */ a(C0109eb c0109eb) {
        }

        @Override // c.a.C0219ea.h
        public Object a(byte[] bArr) {
            return bArr;
        }

        @Override // c.a.C0219ea.h
        public byte[] a(Object obj) {
            return (byte[]) obj;
        }
    }

    /* JADX INFO: renamed from: c.a.b.mb$b */
    public enum b {
        NO_ERROR(0, c.a.za.l),
        PROTOCOL_ERROR(1, c.a.za.k),
        INTERNAL_ERROR(2, c.a.za.k),
        FLOW_CONTROL_ERROR(3, c.a.za.k),
        SETTINGS_TIMEOUT(4, c.a.za.k),
        STREAM_CLOSED(5, c.a.za.k),
        FRAME_SIZE_ERROR(6, c.a.za.k),
        REFUSED_STREAM(7, c.a.za.l),
        CANCEL(8, c.a.za.f1937d),
        COMPRESSION_ERROR(9, c.a.za.k),
        CONNECT_ERROR(10, c.a.za.k),
        ENHANCE_YOUR_CALM(11, c.a.za.i.b("Bandwidth exhausted")),
        INADEQUATE_SECURITY(12, c.a.za.g.b("Permission denied as protocol is not secure enough to call")),
        HTTP_1_1_REQUIRED(13, c.a.za.f1938e);

        public static final b[] o;
        public final int q;
        public final c.a.za r;

        static {
            b[] bVarArrValues = values();
            b[] bVarArr = new b[((int) bVarArrValues[bVarArrValues.length - 1].a()) + 1];
            for (b bVar : bVarArrValues) {
                bVarArr[(int) bVar.a()] = bVar;
            }
            o = bVarArr;
        }

        b(int i, c.a.za zaVar) {
            this.q = i;
            StringBuilder sbA = b.a.a.a.a.a("HTTP/2 error code: ");
            sbA.append(name());
            String string = sbA.toString();
            this.r = zaVar.b(zaVar.q != null ? b.a.a.a.a.a(b.a.a.a.a.b(string, " ("), zaVar.q, ")") : string);
        }

        public long a() {
            return this.q;
        }
    }

    static {
        Charset.forName("US-ASCII");
        f1359b = C0219ea.e.a("grpc-timeout", new c());
        f1360c = C0219ea.e.a("grpc-encoding", C0219ea.f1798a);
        C0109eb c0109eb = null;
        f1361d = c.a.P.a("grpc-accept-encoding", new a(c0109eb));
        f1362e = C0219ea.e.a("content-encoding", C0219ea.f1798a);
        f1363f = c.a.P.a("accept-encoding", new a(c0109eb));
        g = C0219ea.e.a("content-type", C0219ea.f1798a);
        h = C0219ea.e.a("te", C0219ea.f1798a);
        i = C0219ea.e.a("user-agent", C0219ea.f1798a);
        Splitter.on(',').trimResults();
        j = TimeUnit.SECONDS.toNanos(20L);
        TimeUnit.HOURS.toNanos(2L);
        TimeUnit.SECONDS.toNanos(20L);
        k = new Sc();
        l = C0220f.a.a("io.grpc.internal.CALL_OPTIONS_RPC_OWNED_BY_BALANCER");
        m = new C0114fb();
        n = new C0119gb();
        o = new C0124hb();
        p = new C0129ib();
    }

    public static AbstractC0230n[] a(C0220f c0220f, C0219ea c0219ea, int i2, boolean z) {
        List<AbstractC0230n.a> list = c0220f.h;
        AbstractC0230n[] abstractC0230nArr = new AbstractC0230n[list.size() + 1];
        AbstractC0230n.c.a aVarA = AbstractC0230n.c.a();
        aVarA.a(c0220f);
        aVarA.f1881c = i2;
        aVarA.f1882d = z;
        AbstractC0230n.c cVarA = aVarA.a();
        for (int i3 = 0; i3 < list.size(); i3++) {
            abstractC0230nArr[i3] = a(list.get(i3), cVarA, c0219ea);
        }
        abstractC0230nArr[abstractC0230nArr.length - 1] = m;
        return abstractC0230nArr;
    }

    public static boolean b(String str) {
        if (str == null || 16 > str.length()) {
            return false;
        }
        String lowerCase = str.toLowerCase();
        if (!lowerCase.startsWith("application/grpc")) {
            return false;
        }
        if (lowerCase.length() == 16) {
            return true;
        }
        char cCharAt = lowerCase.charAt(16);
        return cCharAt == '+' || cCharAt == ';';
    }

    public static boolean a(C0220f c0220f) {
        return !Boolean.TRUE.equals(c0220f.a(l));
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0029  */
    /* JADX WARN: Code duplicated, block: B:25:0x0035  */
    public static c.a.za a(int i2) {
        c.a.za.a aVar;
        if ((i2 >= 100 && i2 < 200) || i2 == 400) {
            aVar = c.a.za.a.INTERNAL;
        } else if (i2 == 401) {
            aVar = c.a.za.a.UNAUTHENTICATED;
        } else if (i2 == 403) {
            aVar = c.a.za.a.PERMISSION_DENIED;
        } else if (i2 == 404) {
            aVar = c.a.za.a.UNIMPLEMENTED;
        } else if (i2 == 429) {
            aVar = c.a.za.a.UNAVAILABLE;
        } else if (i2 != 431) {
            switch (i2) {
                case 502:
                case 503:
                case 504:
                    aVar = c.a.za.a.UNAVAILABLE;
                    break;
                default:
                    aVar = c.a.za.a.UNKNOWN;
                    break;
            }
        } else {
            aVar = c.a.za.a.INTERNAL;
        }
        return aVar.a().b("HTTP status code " + i2);
    }

    /* JADX INFO: renamed from: c.a.b.mb$c */
    @VisibleForTesting
    static class c implements C0219ea.b<Long> {
        @Override // c.a.C0219ea.b
        public String a(Long l) {
            Long l2 = l;
            TimeUnit timeUnit = TimeUnit.NANOSECONDS;
            if (l2.longValue() < 0) {
                throw new IllegalArgumentException("Timeout too small");
            }
            if (l2.longValue() < 100000000) {
                return l2 + GoogleApiAvailabilityLight.TRACKING_SOURCE_NOTIFICATION;
            }
            if (l2.longValue() < 100000000000L) {
                return timeUnit.toMicros(l2.longValue()) + "u";
            }
            if (l2.longValue() < 100000000000000L) {
                return timeUnit.toMillis(l2.longValue()) + PaintCompat.EM_STRING;
            }
            if (l2.longValue() < 100000000000000000L) {
                return timeUnit.toSeconds(l2.longValue()) + MtkTvRatingConvert2Goo.SUB_RATING_STR_S;
            }
            if (l2.longValue() < 6000000000000000000L) {
                return timeUnit.toMinutes(l2.longValue()) + MtkTvRatingConvert2Goo.RATING_STR_M;
            }
            return timeUnit.toHours(l2.longValue()) + "H";
        }

        @Override // c.a.C0219ea.b
        public Long a(String str) {
            Preconditions.checkArgument(str.length() > 0, "empty timeout");
            Preconditions.checkArgument(str.length() <= 9, "bad timeout format");
            long j = Long.parseLong(str.substring(0, str.length() - 1));
            char cCharAt = str.charAt(str.length() - 1);
            if (cCharAt == 'H') {
                return Long.valueOf(TimeUnit.HOURS.toNanos(j));
            }
            if (cCharAt == 'M') {
                return Long.valueOf(TimeUnit.MINUTES.toNanos(j));
            }
            if (cCharAt == 'S') {
                return Long.valueOf(TimeUnit.SECONDS.toNanos(j));
            }
            if (cCharAt == 'u') {
                return Long.valueOf(TimeUnit.MICROSECONDS.toNanos(j));
            }
            if (cCharAt == 'm') {
                return Long.valueOf(TimeUnit.MILLISECONDS.toNanos(j));
            }
            if (cCharAt == 'n') {
                return Long.valueOf(j);
            }
            throw new IllegalArgumentException(String.format("Invalid timeout unit: %s", Character.valueOf(cCharAt)));
        }
    }

    public static String a(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        if (str2 != null) {
            sb.append(str2);
            sb.append(Ascii.CASE_MASK);
        }
        sb.append("grpc-java-");
        sb.append(str);
        sb.append('/');
        sb.append("1.41.0");
        return sb.toString();
    }

    public static URI a(String str) {
        Preconditions.checkNotNull(str, "authority");
        try {
            return new URI(null, str, null, null, null);
        } catch (URISyntaxException e2) {
            throw new IllegalArgumentException(b.a.a.a.a.a("Invalid authority: ", str), e2);
        }
    }

    public static ThreadFactory a(String str, boolean z) {
        return new ThreadFactoryBuilder().setDaemon(z).setNameFormat(str).build();
    }

    public static String a(InetSocketAddress inetSocketAddress) {
        try {
            return (String) InetSocketAddress.class.getMethod("getHostString", new Class[0]).invoke(inetSocketAddress, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return inetSocketAddress.getHostName();
        }
    }

    public static O a(c.a.U.d dVar, boolean z) {
        O oB;
        c.a.U.g gVar = dVar.f849b;
        if (gVar != null) {
            C0130ic.m mVar = (C0130ic.m) gVar;
            Preconditions.checkState(mVar.h, "Subchannel is not started");
            oB = mVar.g.b();
        } else {
            oB = null;
        }
        if (oB != null) {
            AbstractC0230n.a aVar = dVar.f850c;
            return aVar == null ? oB : new C0134jb(aVar, oB);
        }
        if (!dVar.f851d.c()) {
            if (dVar.f852e) {
                return new Ua(dVar.f851d, N.a.DROPPED);
            }
            if (!z) {
                return new Ua(dVar.f851d, N.a.PROCESSED);
            }
        }
        return null;
    }

    @VisibleForTesting
    public static AbstractC0230n a(AbstractC0230n.a aVar, AbstractC0230n.c cVar, C0219ea c0219ea) {
        if (aVar instanceof AbstractC0230n.b) {
            return ((C0156nd) aVar).f1382a;
        }
        return new C0144lb(aVar, cVar, c0219ea);
    }

    public static void a(Nd.a aVar) {
        while (true) {
            InputStream next = aVar.next();
            if (next == null) {
                return;
            } else {
                a(next);
            }
        }
    }

    public static void a(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e2) {
            f1358a.log(Level.WARNING, "exception caught in closeQuietly", (Throwable) e2);
        }
    }
}
