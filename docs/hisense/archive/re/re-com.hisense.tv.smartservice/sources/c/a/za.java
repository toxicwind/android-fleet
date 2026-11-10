package c.a;

import com.google.common.base.Ascii;
import com.google.common.base.Charsets;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.ju.lib.datacommunication.network.http.address.ThirdAddressResponse;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes2.dex */
public final class za {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f1934a = Boolean.parseBoolean(System.getProperty("io.grpc.Status.failOnEqualsForTest", "false"));

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final List<za> f1935b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final za f1936c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final za f1937d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final za f1938e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final za f1939f;
    public static final za g;
    public static final za h;
    public static final za i;
    public static final za j;
    public static final za k;
    public static final za l;
    public static final C0219ea.e<za> m;
    public static final C0219ea.h<String> n;
    public static final C0219ea.e<String> o;
    public final a p;
    public final String q;
    public final Throwable r;

    static {
        TreeMap treeMap = new TreeMap();
        a[] aVarArrValues = a.values();
        int length = aVarArrValues.length;
        int i2 = 0;
        while (true) {
            ya yaVar = null;
            if (i2 >= length) {
                f1935b = Collections.unmodifiableList(new ArrayList(treeMap.values()));
                f1936c = a.OK.a();
                f1937d = a.CANCELLED.a();
                f1938e = a.UNKNOWN.a();
                a.INVALID_ARGUMENT.a();
                f1939f = a.DEADLINE_EXCEEDED.a();
                a.NOT_FOUND.a();
                a.ALREADY_EXISTS.a();
                g = a.PERMISSION_DENIED.a();
                h = a.UNAUTHENTICATED.a();
                i = a.RESOURCE_EXHAUSTED.a();
                a.FAILED_PRECONDITION.a();
                a.ABORTED.a();
                a.OUT_OF_RANGE.a();
                j = a.UNIMPLEMENTED.a();
                k = a.INTERNAL.a();
                l = a.UNAVAILABLE.a();
                a.DATA_LOSS.a();
                m = C0219ea.e.a("grpc-status", false, new b(yaVar));
                n = new c(yaVar);
                o = C0219ea.e.a("grpc-message", false, n);
                return;
            }
            a aVar = aVarArrValues[i2];
            za zaVar = (za) treeMap.put(Integer.valueOf(aVar.s), new za(aVar, null, null));
            if (zaVar != null) {
                StringBuilder sbA = b.a.a.a.a.a("Code value duplication between ");
                sbA.append(zaVar.p.name());
                sbA.append(" & ");
                sbA.append(aVar.name());
                throw new IllegalStateException(sbA.toString());
            }
            i2++;
        }
    }

    public za(a aVar, String str, Throwable th) {
        Preconditions.checkNotNull(aVar, ThirdAddressResponse.CODE);
        this.p = aVar;
        this.q = str;
        this.r = th;
    }

    public static za a(int i2) {
        if (i2 >= 0 && i2 <= f1935b.size()) {
            return f1935b.get(i2);
        }
        return f1938e.b("Unknown code " + i2);
    }

    public za b(Throwable th) {
        return Objects.equal(this.r, th) ? this : new za(this.p, this.q, th);
    }

    public boolean c() {
        return a.OK == this.p;
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        MoreObjects.ToStringHelper toStringHelperAdd = MoreObjects.toStringHelper(this).add(ThirdAddressResponse.CODE, this.p.name()).add("description", this.q);
        Throwable th = this.r;
        Object stackTraceAsString = th;
        if (th != null) {
            stackTraceAsString = Throwables.getStackTraceAsString(th);
        }
        return toStringHelperAdd.add("cause", stackTraceAsString).toString();
    }

    public enum a {
        OK(0),
        CANCELLED(1),
        UNKNOWN(2),
        INVALID_ARGUMENT(3),
        DEADLINE_EXCEEDED(4),
        NOT_FOUND(5),
        ALREADY_EXISTS(6),
        PERMISSION_DENIED(7),
        RESOURCE_EXHAUSTED(8),
        FAILED_PRECONDITION(9),
        ABORTED(10),
        OUT_OF_RANGE(11),
        UNIMPLEMENTED(12),
        INTERNAL(13),
        UNAVAILABLE(14),
        DATA_LOSS(15),
        UNAUTHENTICATED(16);

        public final int s;
        public final byte[] t;

        a(int i) {
            this.s = i;
            this.t = Integer.toString(i).getBytes(Charsets.US_ASCII);
        }

        public za a() {
            return za.f1935b.get(this.s);
        }
    }

    private static final class b implements C0219ea.h<za> {
        public /* synthetic */ b(ya yaVar) {
        }

        @Override // c.a.C0219ea.h
        public byte[] a(za zaVar) {
            return zaVar.p.t;
        }

        @Override // c.a.C0219ea.h
        public za a(byte[] bArr) {
            return za.a(bArr);
        }
    }

    public za b(String str) {
        return Objects.equal(this.q, str) ? this : new za(this.p, str, this.r);
    }

    public static /* synthetic */ za a(byte[] bArr) {
        int i2;
        int i3;
        char c2 = 0;
        if (bArr.length == 1 && bArr[0] == 48) {
            return f1936c;
        }
        int length = bArr.length;
        if (length != 1) {
            if (length == 2 && bArr[0] >= 48 && bArr[0] <= 57) {
                i2 = 0 + ((bArr[0] - 48) * 10);
                c2 = 1;
            }
            za zaVar = f1938e;
            StringBuilder sbA = b.a.a.a.a.a("Unknown code ");
            sbA.append(new String(bArr, Charsets.US_ASCII));
            return zaVar.b(sbA.toString());
        }
        i2 = 0;
        if (bArr[c2] >= 48 && bArr[c2] <= 57 && (i3 = (bArr[c2] - 48) + i2) < f1935b.size()) {
            return f1935b.get(i3);
        }
        za zaVar2 = f1938e;
        StringBuilder sbA2 = b.a.a.a.a.a("Unknown code ");
        sbA2.append(new String(bArr, Charsets.US_ASCII));
        return zaVar2.b(sbA2.toString());
    }

    public Ba b() {
        return new Ba(this, null);
    }

    public static za a(Throwable th) {
        Preconditions.checkNotNull(th, "t");
        for (Throwable cause = th; cause != null; cause = cause.getCause()) {
            if (cause instanceof Aa) {
                return ((Aa) cause).f779a;
            }
            if (cause instanceof Ba) {
                return ((Ba) cause).f786a;
            }
        }
        return f1938e.b(th);
    }

    private static final class c implements C0219ea.h<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final byte[] f1946a = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70};

        public /* synthetic */ c(ya yaVar) {
        }

        public static boolean a(byte b2) {
            return b2 < 32 || b2 >= 126 || b2 == 37;
        }

        @Override // c.a.C0219ea.h
        public byte[] a(String str) {
            byte[] bytes = str.getBytes(Charsets.UTF_8);
            int i = 0;
            while (i < bytes.length) {
                if (a(bytes[i])) {
                    byte[] bArr = new byte[((bytes.length - i) * 3) + i];
                    if (i != 0) {
                        System.arraycopy(bytes, 0, bArr, 0, i);
                    }
                    int i2 = i;
                    while (i < bytes.length) {
                        byte b2 = bytes[i];
                        if (a(b2)) {
                            bArr[i2] = 37;
                            byte[] bArr2 = f1946a;
                            bArr[i2 + 1] = bArr2[(b2 >> 4) & 15];
                            bArr[i2 + 2] = bArr2[b2 & Ascii.SI];
                            i2 += 3;
                        } else {
                            bArr[i2] = b2;
                            i2++;
                        }
                        i++;
                    }
                    return Arrays.copyOf(bArr, i2);
                }
                i++;
            }
            return bytes;
        }

        @Override // c.a.C0219ea.h
        public String a(byte[] bArr) {
            for (int i = 0; i < bArr.length; i++) {
                byte b2 = bArr[i];
                if (b2 < 32 || b2 >= 126 || (b2 == 37 && i + 2 < bArr.length)) {
                    ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bArr.length);
                    int i2 = 0;
                    while (i2 < bArr.length) {
                        if (bArr[i2] == 37 && i2 + 2 < bArr.length) {
                            try {
                                byteBufferAllocate.put((byte) Integer.parseInt(new String(bArr, i2 + 1, 2, Charsets.US_ASCII), 16));
                                i2 += 3;
                            } catch (NumberFormatException unused) {
                                byteBufferAllocate.put(bArr[i2]);
                                i2++;
                            }
                        }
                        byteBufferAllocate.put(bArr[i2]);
                        i2++;
                    }
                    return new String(byteBufferAllocate.array(), 0, byteBufferAllocate.position(), Charsets.UTF_8);
                }
            }
            return new String(bArr, 0);
        }
    }

    public static String a(za zaVar) {
        if (zaVar.q == null) {
            return zaVar.p.toString();
        }
        return zaVar.p + ": " + zaVar.q;
    }

    public za a(String str) {
        if (str == null) {
            return this;
        }
        if (this.q == null) {
            return new za(this.p, str, this.r);
        }
        return new za(this.p, b.a.a.a.a.a(new StringBuilder(), this.q, "\n", str), this.r);
    }

    public Ba a(C0219ea c0219ea) {
        return new Ba(this, c0219ea);
    }

    public Aa a() {
        return new Aa(this);
    }
}
