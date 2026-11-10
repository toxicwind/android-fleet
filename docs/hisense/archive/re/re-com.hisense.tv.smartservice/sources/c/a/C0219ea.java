package c.a;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.io.BaseEncoding;
import com.google.common.io.ByteStreams;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Locale;

/* JADX INFO: renamed from: c.a.ea, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0219ea {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b<String> f1798a = new C0217da();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final BaseEncoding f1799b = BaseEncoding.BASE64.omitPadding();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object[] f1800c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1801d;

    /* JADX INFO: renamed from: c.a.ea$a */
    private static class a<T> extends e<T> {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final b<T> f1802f;

        public /* synthetic */ a(String str, boolean z, b bVar, C0215ca c0215ca) {
            super(str, z, bVar, null);
            Preconditions.checkArgument(!str.endsWith("-bin"), "ASCII header is named %s.  Only binary headers may end with %s", str, "-bin");
            Preconditions.checkNotNull(bVar, "marshaller");
            this.f1802f = bVar;
        }

        @Override // c.a.C0219ea.e
        public byte[] a(T t) {
            return this.f1802f.a(t).getBytes(Charsets.US_ASCII);
        }

        @Override // c.a.C0219ea.e
        public T a(byte[] bArr) {
            return this.f1802f.a(new String(bArr, Charsets.US_ASCII));
        }
    }

    /* JADX INFO: renamed from: c.a.ea$b */
    public interface b<T> {
        T a(String str);

        String a(T t);
    }

    /* JADX INFO: renamed from: c.a.ea$c */
    public interface c<T> {
    }

    /* JADX INFO: renamed from: c.a.ea$d */
    public interface d<T> {
        InputStream a(T t);
    }

    /* JADX INFO: renamed from: c.a.ea$e */
    public static abstract class e<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final BitSet f1803a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f1804b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final String f1805c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final byte[] f1806d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final Object f1807e;

        static {
            BitSet bitSet = new BitSet(127);
            bitSet.set(45);
            bitSet.set(95);
            bitSet.set(46);
            for (char c2 = '0'; c2 <= '9'; c2 = (char) (c2 + 1)) {
                bitSet.set(c2);
            }
            for (char c3 = 'a'; c3 <= 'z'; c3 = (char) (c3 + 1)) {
                bitSet.set(c3);
            }
            f1803a = bitSet;
        }

        public /* synthetic */ e(String str, boolean z, Object obj, C0215ca c0215ca) {
            Preconditions.checkNotNull(str, "name");
            this.f1804b = str;
            String lowerCase = this.f1804b.toLowerCase(Locale.ROOT);
            Preconditions.checkNotNull(lowerCase, "name");
            Preconditions.checkArgument(!lowerCase.isEmpty(), "token must have at least 1 tchar");
            for (int i = 0; i < lowerCase.length(); i++) {
                char cCharAt = lowerCase.charAt(i);
                if (!z || cCharAt != ':' || i != 0) {
                    Preconditions.checkArgument(f1803a.get(cCharAt), "Invalid character '%s' in key name '%s'", cCharAt, (Object) lowerCase);
                }
            }
            this.f1805c = lowerCase;
            this.f1806d = this.f1805c.getBytes(Charsets.US_ASCII);
            this.f1807e = obj;
        }

        public static <T> e<T> a(String str, b<T> bVar) {
            return new a(str, false, bVar, null);
        }

        public abstract T a(byte[] bArr);

        public boolean a() {
            return false;
        }

        public abstract byte[] a(T t);

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.f1805c.equals(((e) obj).f1805c);
        }

        public final int hashCode() {
            return this.f1805c.hashCode();
        }

        public String toString() {
            return b.a.a.a.a.a(b.a.a.a.a.a("Key{name='"), this.f1805c, "'}");
        }

        public static <T> e<T> a(String str, boolean z, h<T> hVar) {
            return new g(str, z, hVar, null);
        }
    }

    /* JADX INFO: renamed from: c.a.ea$g */
    private static final class g<T> extends e<T> {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final h<T> f1811f;

        public /* synthetic */ g(String str, boolean z, h hVar, C0215ca c0215ca) {
            super(str, z, hVar, null);
            Preconditions.checkArgument(!str.endsWith("-bin"), "ASCII header is named %s.  Only binary headers may end with %s", str, "-bin");
            Preconditions.checkNotNull(hVar, "marshaller");
            this.f1811f = hVar;
        }

        @Override // c.a.C0219ea.e
        public byte[] a(T t) {
            return this.f1811f.a(t);
        }

        @Override // c.a.C0219ea.e
        public T a(byte[] bArr) {
            return this.f1811f.a(bArr);
        }
    }

    /* JADX INFO: renamed from: c.a.ea$h */
    interface h<T> {
        T a(byte[] bArr);

        byte[] a(T t);
    }

    public C0219ea(byte[]... bArr) {
        this.f1801d = bArr.length / 2;
        this.f1800c = bArr;
    }

    public final void a(int i, Object obj) {
        if (this.f1800c instanceof byte[][]) {
            a(a());
        }
        this.f1800c[(i * 2) + 1] = obj;
    }

    public final byte[] b(int i) {
        return (byte[]) this.f1800c[i * 2];
    }

    public final Object c(int i) {
        return this.f1800c[(i * 2) + 1];
    }

    public final byte[] d(int i) {
        Object obj = this.f1800c[(i * 2) + 1];
        return obj instanceof byte[] ? (byte[]) obj : ((f) obj).a();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Metadata(");
        for (int i = 0; i < this.f1801d; i++) {
            if (i != 0) {
                sb.append(',');
            }
            String str = new String(b(i), Charsets.US_ASCII);
            sb.append(str);
            sb.append('=');
            if (str.endsWith("-bin")) {
                sb.append(f1799b.encode(d(i)));
            } else {
                sb.append(new String(d(i), Charsets.US_ASCII));
            }
        }
        sb.append(')');
        return sb.toString();
    }

    public final boolean b() {
        return this.f1801d == 0;
    }

    public <T> T b(e<T> eVar) {
        int i = this.f1801d;
        do {
            i--;
            if (i < 0) {
                return null;
            }
        } while (!Arrays.equals(eVar.f1806d, b(i)));
        Object obj = this.f1800c[(i * 2) + 1];
        if (obj instanceof byte[]) {
            return eVar.a((byte[]) obj);
        }
        return (T) ((f) obj).a(eVar);
    }

    public final int a() {
        Object[] objArr = this.f1800c;
        if (objArr != null) {
            return objArr.length;
        }
        return 0;
    }

    public C0219ea() {
    }

    public <T> void a(e<T> eVar, T t) {
        Preconditions.checkNotNull(eVar, "key");
        Preconditions.checkNotNull(t, "value");
        int i = this.f1801d * 2;
        if (i == 0 || i == a()) {
            a(Math.max(this.f1801d * 2 * 2, 8));
        }
        int i2 = this.f1801d;
        this.f1800c[i2 * 2] = eVar.f1806d;
        this.f1800c[(i2 * 2) + 1] = eVar.a(t);
        this.f1801d++;
    }

    /* JADX INFO: renamed from: c.a.ea$f */
    static final class f<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final d<T> f1808a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final T f1809b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public volatile byte[] f1810c;

        public byte[] a() {
            if (this.f1810c == null) {
                synchronized (this) {
                    if (this.f1810c == null) {
                        this.f1810c = C0219ea.a(this.f1808a.a(this.f1809b));
                    }
                }
            }
            return this.f1810c;
        }

        public <T2> T2 a(e<T2> eVar) {
            eVar.a();
            return eVar.a(a());
        }
    }

    public final void a(int i) {
        Object[] objArr = new Object[i];
        if (!b()) {
            System.arraycopy(this.f1800c, 0, objArr, 0, this.f1801d * 2);
        }
        this.f1800c = objArr;
    }

    public <T> void a(e<T> eVar) {
        if (b()) {
            return;
        }
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = this.f1801d;
            if (i < i3) {
                if (!Arrays.equals(eVar.f1806d, b(i))) {
                    this.f1800c[i2 * 2] = b(i);
                    a(i2, c(i));
                    i2++;
                }
                i++;
            } else {
                Arrays.fill(this.f1800c, i2 * 2, i3 * 2, (Object) null);
                this.f1801d = i2;
                return;
            }
        }
    }

    public void a(C0219ea c0219ea) {
        if (c0219ea.b()) {
            return;
        }
        int iA = a() - (this.f1801d * 2);
        if (b() || iA < c0219ea.f1801d * 2) {
            a((this.f1801d * 2) + (c0219ea.f1801d * 2));
        }
        System.arraycopy(c0219ea.f1800c, 0, this.f1800c, this.f1801d * 2, c0219ea.f1801d * 2);
        this.f1801d += c0219ea.f1801d;
    }

    public static /* synthetic */ byte[] a(InputStream inputStream) {
        try {
            return ByteStreams.toByteArray(inputStream);
        } catch (IOException e2) {
            throw new RuntimeException("failure reading serialized stream", e2);
        }
    }
}
