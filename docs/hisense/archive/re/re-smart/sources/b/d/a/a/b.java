package b.d.a.a;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.io.Writer;
import java.lang.ref.SoftReference;

/* JADX INFO: loaded from: classes.dex */
public class b implements n, Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f71a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f72b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f73c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final m f74d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final ThreadLocal<SoftReference<b.d.a.a.e.a>> f75e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final transient b.d.a.a.d.b f76f = b.d.a.a.d.b.a();
    public final transient b.d.a.a.d.a g;
    public int h;
    public int i;
    public int j;
    public m k;

    public enum a {
        INTERN_FIELD_NAMES(true),
        CANONICALIZE_FIELD_NAMES(true),
        FAIL_ON_SYMBOL_HASH_OVERFLOW(true);


        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final boolean f81e;

        a(boolean z) {
            this.f81e = z;
        }

        public boolean a(int i) {
            return (i & (1 << ordinal())) != 0;
        }

        public int a() {
            return 1 << ordinal();
        }
    }

    static {
        int iA = 0;
        for (a aVar : a.values()) {
            if (aVar.f81e) {
                iA |= aVar.a();
            }
        }
        f71a = iA;
        int i = 0;
        for (g.a aVar2 : g.a.values()) {
            if (aVar2.l) {
                i |= aVar2.m;
            }
        }
        f72b = i;
        int i2 = 0;
        for (d.a aVar3 : d.a.values()) {
            if (aVar3.l) {
                i2 |= aVar3.m;
            }
        }
        f73c = i2;
        f74d = b.d.a.a.e.c.f203a;
        f75e = new ThreadLocal<>();
    }

    public b() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.g = new b.d.a.a.d.a(64, true, (((int) jCurrentTimeMillis) + ((int) (jCurrentTimeMillis >>> 32))) | 1, true);
        this.h = f71a;
        this.i = f72b;
        this.j = f73c;
        this.k = f74d;
    }

    public g a(Reader reader) {
        return new b.d.a.a.c.f(a((Object) reader, false), this.i, reader, this.f76f.c(this.h));
    }

    public final Writer b(Writer writer, b.d.a.a.b.c cVar) {
        return writer;
    }

    public g a(byte[] bArr) {
        return new b.d.a.a.c.a(a((Object) bArr, true), bArr, 0, bArr.length).a(this.i, null, this.g, this.f76f, this.h);
    }

    public g a(String str) {
        int length = str.length();
        if (length <= 32768) {
            b.d.a.a.b.c cVarA = a((Object) str, true);
            cVarA.a((Object) cVarA.g);
            char[] cArrA = cVarA.f91d.a(0, length);
            cVarA.g = cArrA;
            str.getChars(0, length, cArrA, 0);
            return a(cArrA, 0, length, cVarA, true);
        }
        return a(new StringReader(str));
    }

    public g a(char[] cArr) {
        return a(cArr, 0, cArr.length, a((Object) cArr, true), false);
    }

    public d a(OutputStream outputStream) {
        Writer outputStreamWriter;
        b.d.a.a.a aVar = b.d.a.a.a.UTF8;
        b.d.a.a.b.c cVarA = a((Object) outputStream, false);
        cVarA.a(aVar);
        b.d.a.a.a aVar2 = b.d.a.a.a.UTF8;
        if (aVar == aVar2) {
            b.d.a.a.c.g gVar = new b.d.a.a.c.g(cVarA, this.j, outputStream);
            m mVar = this.k;
            if (mVar == f74d) {
                return gVar;
            }
            gVar.a(mVar);
            return gVar;
        }
        if (aVar == aVar2) {
            outputStreamWriter = new b.d.a.a.b.j(cVarA, outputStream);
        } else {
            outputStreamWriter = new OutputStreamWriter(outputStream, aVar.a());
        }
        b(outputStreamWriter, cVarA);
        return a(outputStreamWriter, cVarA);
    }

    public g a(InputStream inputStream, b.d.a.a.b.c cVar) {
        return new b.d.a.a.c.a(cVar, inputStream).a(this.i, null, this.g, this.f76f, this.h);
    }

    public g a(char[] cArr, int i, int i2, b.d.a.a.b.c cVar, boolean z) {
        return new b.d.a.a.c.f(cVar, this.i, null, this.f76f.c(this.h), cArr, i, i + i2, z);
    }

    public d a(Writer writer, b.d.a.a.b.c cVar) {
        b.d.a.a.c.i iVar = new b.d.a.a.c.i(cVar, this.j, writer);
        m mVar = this.k;
        if (mVar != f74d) {
            iVar.j = mVar;
        }
        return iVar;
    }

    public b.d.a.a.b.c a(Object obj, boolean z) {
        SoftReference<b.d.a.a.e.a> softReference = f75e.get();
        b.d.a.a.e.a aVar = softReference == null ? null : softReference.get();
        if (aVar == null) {
            aVar = new b.d.a.a.e.a();
            f75e.set(new SoftReference<>(aVar));
        }
        return new b.d.a.a.b.c(aVar, obj, z);
    }
}
