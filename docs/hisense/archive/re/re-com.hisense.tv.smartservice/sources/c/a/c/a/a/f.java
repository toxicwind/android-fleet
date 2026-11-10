package c.a.c.a.a;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.installations.Utils;
import com.google.firebase.storage.network.NetworkRequest;
import d.C;
import d.C0246e;
import d.t;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import okhttp3.internal.http2.Http2Codec;

/* JADX INFO: loaded from: classes2.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d.j f1577a = d.j.d(Utils.APP_ID_IDENTIFICATION_SUBSTRING);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final d[] f1578b = {new d(d.f1570e, ""), new d(d.f1567b, NetworkRequest.GET), new d(d.f1567b, NetworkRequest.POST), new d(d.f1568c, "/"), new d(d.f1568c, "/index.html"), new d(d.f1569d, "http"), new d(d.f1569d, "https"), new d(d.f1566a, "200"), new d(d.f1566a, "204"), new d(d.f1566a, "206"), new d(d.f1566a, "304"), new d(d.f1566a, "400"), new d(d.f1566a, "404"), new d(d.f1566a, "500"), new d("accept-charset", ""), new d("accept-encoding", "gzip, deflate"), new d("accept-language", ""), new d("accept-ranges", ""), new d("accept", ""), new d("access-control-allow-origin", ""), new d("age", ""), new d("allow", ""), new d("authorization", ""), new d("cache-control", ""), new d("content-disposition", ""), new d("content-encoding", ""), new d("content-language", ""), new d("content-length", ""), new d("content-location", ""), new d("content-range", ""), new d("content-type", ""), new d("cookie", ""), new d("date", ""), new d("etag", ""), new d("expect", ""), new d("expires", ""), new d("from", ""), new d(Http2Codec.HOST, ""), new d("if-match", ""), new d("if-modified-since", ""), new d("if-none-match", ""), new d("if-range", ""), new d("if-unmodified-since", ""), new d("last-modified", ""), new d("link", ""), new d(FirebaseAnalytics.Param.LOCATION, ""), new d("max-forwards", ""), new d("proxy-authenticate", ""), new d("proxy-authorization", ""), new d("range", ""), new d("referer", ""), new d("refresh", ""), new d("retry-after", ""), new d("server", ""), new d("set-cookie", ""), new d("strict-transport-security", ""), new d(Http2Codec.TRANSFER_ENCODING, ""), new d("user-agent", ""), new d("vary", ""), new d("via", ""), new d("www-authenticate", "")};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Map<d.j, Integer> f1579c;

    static {
        int i = 0;
        LinkedHashMap linkedHashMap = new LinkedHashMap(f1578b.length);
        while (true) {
            d[] dVarArr = f1578b;
            if (i >= dVarArr.length) {
                f1579c = Collections.unmodifiableMap(linkedHashMap);
                return;
            } else {
                if (!linkedHashMap.containsKey(dVarArr[i].f1571f)) {
                    linkedHashMap.put(f1578b[i].f1571f, Integer.valueOf(i));
                }
                i++;
            }
        }
    }

    public static /* synthetic */ d.j a(d.j jVar) throws IOException {
        int iG = jVar.g();
        for (int i = 0; i < iG; i++) {
            byte bA = jVar.a(i);
            if (bA >= 65 && bA <= 90) {
                StringBuilder sbA = b.a.a.a.a.a("PROTOCOL_ERROR response malformed: mixed case name: ");
                sbA.append(jVar.j());
                throw new IOException(sbA.toString());
            }
        }
        return jVar;
    }

    static final class a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final d.i f1581b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1582c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1583d;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<d> f1580a = new ArrayList();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public d[] f1584e = new d[8];

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1585f = this.f1584e.length - 1;
        public int g = 0;
        public int h = 0;

        public a(int i, C c2) {
            this.f1582c = i;
            this.f1583d = i;
            this.f1581b = t.a(c2);
        }

        public final void a() {
            int i = this.f1583d;
            int i2 = this.h;
            if (i < i2) {
                if (i == 0) {
                    b();
                } else {
                    b(i2 - i);
                }
            }
        }

        public final void b() {
            Arrays.fill(this.f1584e, (Object) null);
            this.f1585f = this.f1584e.length - 1;
            this.g = 0;
            this.h = 0;
        }

        public final d.j c(int i) throws IOException {
            if (i >= 0 && i <= f.f1578b.length - 1) {
                return f.f1578b[i].f1571f;
            }
            int iA = a(i - f.f1578b.length);
            if (iA >= 0) {
                d[] dVarArr = this.f1584e;
                if (iA < dVarArr.length) {
                    return dVarArr[iA].f1571f;
                }
            }
            StringBuilder sbA = b.a.a.a.a.a("Header index too large ");
            sbA.append(i + 1);
            throw new IOException(sbA.toString());
        }

        public final boolean d(int i) {
            return i >= 0 && i <= f.f1578b.length - 1;
        }

        public d.j d() {
            int i = this.f1581b.readByte() & 255;
            boolean z = (i & 128) == 128;
            int iA = a(i, 127);
            if (z) {
                return d.j.a(h.f1614c.a(this.f1581b.b(iA)));
            }
            return this.f1581b.d(iA);
        }

        public final int a(int i) {
            return this.f1585f + 1 + i;
        }

        public final void a(int i, d dVar) {
            this.f1580a.add(dVar);
            int i2 = dVar.h;
            if (i != -1) {
                i2 -= this.f1584e[(this.f1585f + 1) + i].h;
            }
            int i3 = this.f1583d;
            if (i2 > i3) {
                b();
                return;
            }
            int iB = b((this.h + i2) - i3);
            if (i == -1) {
                int i4 = this.g + 1;
                d[] dVarArr = this.f1584e;
                if (i4 > dVarArr.length) {
                    d[] dVarArr2 = new d[dVarArr.length * 2];
                    System.arraycopy(dVarArr, 0, dVarArr2, dVarArr.length, dVarArr.length);
                    this.f1585f = this.f1584e.length - 1;
                    this.f1584e = dVarArr2;
                }
                int i5 = this.f1585f;
                this.f1585f = i5 - 1;
                this.f1584e[i5] = dVar;
                this.g++;
            } else {
                this.f1584e[this.f1585f + 1 + i + iB + i] = dVar;
            }
            this.h += i2;
        }

        public final int b(int i) {
            int i2 = 0;
            if (i > 0) {
                int length = this.f1584e.length;
                while (true) {
                    length--;
                    if (length < this.f1585f || i <= 0) {
                        break;
                    }
                    d[] dVarArr = this.f1584e;
                    i -= dVarArr[length].h;
                    this.h -= dVarArr[length].h;
                    this.g--;
                    i2++;
                }
                d[] dVarArr2 = this.f1584e;
                int i3 = this.f1585f;
                System.arraycopy(dVarArr2, i3 + 1, dVarArr2, i3 + 1 + i2, this.g);
                this.f1585f += i2;
            }
            return i2;
        }

        public List<d> c() {
            ArrayList arrayList = new ArrayList(this.f1580a);
            this.f1580a.clear();
            return arrayList;
        }

        public int a(int i, int i2) {
            int i3 = i & i2;
            if (i3 < i2) {
                return i3;
            }
            int i4 = 0;
            while (true) {
                int i5 = this.f1581b.readByte() & 255;
                if ((i5 & 128) == 0) {
                    return i2 + (i5 << i4);
                }
                i2 += (i5 & 127) << i4;
                i4 += 7;
            }
        }
    }

    static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final d.g f1586a;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1589d;
        public int g;
        public int i;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1588c = Integer.MAX_VALUE;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public d[] f1591f = new d[8];
        public int h = this.f1591f.length - 1;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1590e = 4096;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1587b = false;

        public b(d.g gVar) {
            this.f1586a = gVar;
        }

        public void a(int i, int i2, int i3) {
            if (i < i2) {
                this.f1586a.writeByte(i | i3);
                return;
            }
            this.f1586a.writeByte(i3 | i2);
            int i4 = i - i2;
            while (i4 >= 128) {
                this.f1586a.writeByte(128 | (i4 & 127));
                i4 >>>= 7;
            }
            this.f1586a.writeByte(i4);
        }

        public void a(d.j jVar) throws IOException {
            if (this.f1587b && h.f1614c.b(jVar.i()) < jVar.g()) {
                d.g gVar = new d.g();
                h.f1614c.a(jVar.i(), new C0246e(gVar));
                d.j jVarN = gVar.n();
                a(jVarN.g(), 127, 128);
                this.f1586a.a(jVarN);
                return;
            }
            a(jVar.g(), 127, 0);
            this.f1586a.a(jVar);
        }

        public final void a(d dVar) {
            int i = dVar.h;
            int i2 = this.f1590e;
            if (i > i2) {
                Arrays.fill(this.f1591f, (Object) null);
                this.h = this.f1591f.length - 1;
                this.g = 0;
                this.i = 0;
                return;
            }
            int i3 = (this.i + i) - i2;
            if (i3 > 0) {
                int i4 = 0;
                for (int length = this.f1591f.length - 1; length >= this.h && i3 > 0; length--) {
                    d[] dVarArr = this.f1591f;
                    i3 -= dVarArr[length].h;
                    this.i -= dVarArr[length].h;
                    this.g--;
                    i4++;
                }
                d[] dVarArr2 = this.f1591f;
                int i5 = this.h + 1;
                System.arraycopy(dVarArr2, i5, dVarArr2, i5 + i4, this.g);
                this.h += i4;
            }
            int i6 = this.g + 1;
            d[] dVarArr3 = this.f1591f;
            if (i6 > dVarArr3.length) {
                d[] dVarArr4 = new d[dVarArr3.length * 2];
                System.arraycopy(dVarArr3, 0, dVarArr4, dVarArr3.length, dVarArr3.length);
                this.h = this.f1591f.length - 1;
                this.f1591f = dVarArr4;
            }
            int i7 = this.h;
            this.h = i7 - 1;
            this.f1591f[i7] = dVar;
            this.g++;
            this.i += i;
        }
    }
}
