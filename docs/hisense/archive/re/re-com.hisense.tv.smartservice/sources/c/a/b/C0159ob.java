package c.a.b;

import com.google.common.base.Preconditions;
import java.io.Closeable;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import java.util.zip.ZipException;

/* JADX INFO: renamed from: c.a.b.ob, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0159ob implements Closeable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1390e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1391f;
    public Inflater g;
    public int j;
    public int k;
    public long l;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final W f1386a = new W();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CRC32 f1387b = new CRC32();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a f1388c = new a(null);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final byte[] f1389d = new byte[512];
    public b h = b.HEADER;
    public boolean i = false;
    public int m = 0;
    public int n = 0;
    public boolean o = true;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c.a.b.ob$a */
    class a {
        public /* synthetic */ a(C0154nb c0154nb) {
        }

        public final int a() {
            int unsignedByte;
            if (C0159ob.this.f1391f - C0159ob.this.f1390e > 0) {
                unsignedByte = C0159ob.this.f1389d[C0159ob.this.f1390e] & 255;
                C0159ob.a(C0159ob.this, 1);
            } else {
                unsignedByte = C0159ob.this.f1386a.readUnsignedByte();
            }
            C0159ob.this.f1387b.update(unsignedByte);
            C0159ob.b(C0159ob.this, 1);
            return unsignedByte;
        }

        public final int c() {
            return (C0159ob.this.f1391f - C0159ob.this.f1390e) + C0159ob.this.f1386a.h;
        }

        public final int b() {
            return a() | (a() << 8);
        }

        public static /* synthetic */ void a(a aVar, int i) {
            int i2;
            int i3 = C0159ob.this.f1391f - C0159ob.this.f1390e;
            if (i3 > 0) {
                int iMin = Math.min(i3, i);
                C0159ob.this.f1387b.update(C0159ob.this.f1389d, C0159ob.this.f1390e, iMin);
                C0159ob.a(C0159ob.this, iMin);
                i2 = i - iMin;
            } else {
                i2 = i;
            }
            if (i2 > 0) {
                byte[] bArr = new byte[512];
                int i4 = 0;
                while (i4 < i2) {
                    int iMin2 = Math.min(i2 - i4, bArr.length);
                    C0159ob.this.f1386a.a(bArr, 0, iMin2);
                    C0159ob.this.f1387b.update(bArr, 0, iMin2);
                    i4 += iMin2;
                }
            }
            C0159ob.b(C0159ob.this, i);
        }

        public static /* synthetic */ boolean a(a aVar) {
            while (aVar.c() > 0) {
                if (aVar.a() == 0) {
                    return true;
                }
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c.a.b.ob$b */
    enum b {
        HEADER,
        HEADER_EXTRA_LEN,
        HEADER_EXTRA,
        HEADER_NAME,
        HEADER_COMMENT,
        HEADER_CRC,
        INITIALIZE_INFLATER,
        INFLATING,
        INFLATER_NEEDS_INPUT,
        TRAILER
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.i) {
            return;
        }
        this.i = true;
        this.f1386a.close();
        Inflater inflater = this.g;
        if (inflater != null) {
            inflater.end();
            this.g = null;
        }
    }

    public final boolean p() throws ZipException {
        if (this.g != null && this.f1388c.c() <= 18) {
            this.g.end();
            this.g = null;
        }
        if (this.f1388c.c() < 8) {
            return false;
        }
        long value = this.f1387b.getValue();
        a aVar = this.f1388c;
        if (value == (((long) aVar.b()) | (((long) aVar.b()) << 16))) {
            long j = this.l;
            a aVar2 = this.f1388c;
            if (j == ((((long) aVar2.b()) << 16) | ((long) aVar2.b()))) {
                this.f1387b.reset();
                this.h = b.HEADER;
                return true;
            }
        }
        throw new ZipException("Corrupt GZIP trailer");
    }

    public static /* synthetic */ int a(C0159ob c0159ob, int i) {
        int i2 = c0159ob.f1390e + i;
        c0159ob.f1390e = i2;
        return i2;
    }

    public static /* synthetic */ int b(C0159ob c0159ob, int i) {
        int i2 = c0159ob.m + i;
        c0159ob.m = i2;
        return i2;
    }

    public int b(byte[] bArr, int i, int i2) throws ZipException, DataFormatException {
        int i3;
        boolean zP;
        int i4;
        Preconditions.checkState(!this.i, "GzipInflatingBuffer is closed");
        boolean z = false;
        int i5 = 0;
        while (true) {
            i3 = i5;
            zP = true;
            while (true) {
                if (zP && (i4 = i2 - i3) > 0) {
                    switch (this.h) {
                        case HEADER:
                            if (this.f1388c.c() >= 10) {
                                if (this.f1388c.b() == 35615) {
                                    if (this.f1388c.a() == 8) {
                                        this.j = this.f1388c.a();
                                        a.a(this.f1388c, 6);
                                        this.h = b.HEADER_EXTRA_LEN;
                                        break;
                                    }
                                    throw new ZipException("Unsupported compression method");
                                }
                                throw new ZipException("Not in GZIP format");
                            }
                            zP = false;
                            break;
                        case HEADER_EXTRA_LEN:
                            if ((this.j & 4) != 4) {
                                this.h = b.HEADER_NAME;
                            }
                            if (this.f1388c.c() >= 2) {
                                this.k = this.f1388c.b();
                                this.h = b.HEADER_EXTRA;
                            }
                            zP = false;
                            break;
                            break;
                        case HEADER_EXTRA:
                            int iC = this.f1388c.c();
                            int i6 = this.k;
                            if (iC >= i6) {
                                a.a(this.f1388c, i6);
                                this.h = b.HEADER_NAME;
                            }
                            zP = false;
                            break;
                        case HEADER_NAME:
                            if ((this.j & 8) != 8) {
                                this.h = b.HEADER_COMMENT;
                            }
                            if (a.a(this.f1388c)) {
                                this.h = b.HEADER_COMMENT;
                            }
                            zP = false;
                            break;
                            break;
                        case HEADER_COMMENT:
                            if ((this.j & 16) != 16) {
                                this.h = b.HEADER_CRC;
                            }
                            if (a.a(this.f1388c)) {
                                this.h = b.HEADER_CRC;
                            }
                            zP = false;
                            break;
                            break;
                        case HEADER_CRC:
                            if ((this.j & 2) != 2) {
                                this.h = b.INITIALIZE_INFLATER;
                            }
                            if (this.f1388c.c() >= 2) {
                                if ((((int) this.f1387b.getValue()) & 65535) == this.f1388c.b()) {
                                    this.h = b.INITIALIZE_INFLATER;
                                    break;
                                }
                                throw new ZipException("Corrupt GZIP header");
                            }
                            zP = false;
                            break;
                            break;
                        case INITIALIZE_INFLATER:
                            Inflater inflater = this.g;
                            if (inflater == null) {
                                this.g = new Inflater(true);
                            } else {
                                inflater.reset();
                            }
                            this.f1387b.reset();
                            int i7 = this.f1391f;
                            int i8 = this.f1390e;
                            int i9 = i7 - i8;
                            if (i9 > 0) {
                                this.g.setInput(this.f1389d, i8, i9);
                                this.h = b.INFLATING;
                                break;
                            }
                            this.h = b.INFLATER_NEEDS_INPUT;
                            break;
                        case INFLATING:
                            int i10 = i + i3;
                            Preconditions.checkState(this.g != null, "inflater is null");
                            try {
                                int totalIn = this.g.getTotalIn();
                                int iInflate = this.g.inflate(bArr, i10, i4);
                                int totalIn2 = this.g.getTotalIn() - totalIn;
                                this.m += totalIn2;
                                this.n += totalIn2;
                                this.f1390e += totalIn2;
                                this.f1387b.update(bArr, i10, iInflate);
                                if (this.g.finished()) {
                                    this.l = this.g.getBytesWritten() & 4294967295L;
                                    this.h = b.TRAILER;
                                } else if (this.g.needsInput()) {
                                    this.h = b.INFLATER_NEEDS_INPUT;
                                }
                                i3 += iInflate;
                                if (this.h != b.TRAILER) {
                                }
                                zP = p();
                            } catch (DataFormatException e2) {
                                StringBuilder sbA = b.a.a.a.a.a("Inflater data format exception: ");
                                sbA.append(e2.getMessage());
                                throw new DataFormatException(sbA.toString());
                            }
                            break;
                        case INFLATER_NEEDS_INPUT:
                            Preconditions.checkState(this.g != null, "inflater is null");
                            Preconditions.checkState(this.f1390e == this.f1391f, "inflaterInput has unconsumed bytes");
                            int iMin = Math.min(this.f1386a.h, 512);
                            if (iMin != 0) {
                                this.f1390e = 0;
                                this.f1391f = iMin;
                                this.f1386a.a(this.f1389d, this.f1390e, iMin);
                                this.g.setInput(this.f1389d, this.f1390e, iMin);
                                this.h = b.INFLATING;
                            }
                            zP = false;
                            break;
                        case TRAILER:
                            zP = p();
                            break;
                        default:
                            StringBuilder sbA2 = b.a.a.a.a.a("Invalid state: ");
                            sbA2.append(this.h);
                            throw new AssertionError(sbA2.toString());
                    }
                }
            }
            i5 = i3;
        }
        if (!zP || (this.h == b.HEADER && this.f1388c.c() < 10)) {
            z = true;
        }
        this.o = z;
        return i3;
    }
}
