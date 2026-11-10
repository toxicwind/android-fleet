package c.a.b;

import c.a.InterfaceC0231o;
import c.a.InterfaceC0233q;
import com.google.common.base.Preconditions;
import com.google.common.io.ByteStreams;
import com.google.protobuf.MessageLite;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class Gc implements InterfaceC0099cb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f977a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Ud f979c;
    public final Vd h;
    public final Ld i;
    public boolean j;
    public int k;
    public long m;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f978b = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public InterfaceC0233q f980d = InterfaceC0231o.b.f1883a;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f981e = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final b f982f = new b(null);
    public final ByteBuffer g = ByteBuffer.allocate(5);
    public int l = -1;

    public interface c {
    }

    public Gc(c cVar, Vd vd, Ld ld) {
        Preconditions.checkNotNull(cVar, "sink");
        this.f977a = cVar;
        Preconditions.checkNotNull(vd, "bufferAllocator");
        this.h = vd;
        Preconditions.checkNotNull(ld, "statsTraceCtx");
        this.i = ld;
    }

    public final int b(InputStream inputStream, int i) throws IOException {
        if (i == -1) {
            a aVar = new a(null);
            int iA = a(inputStream, aVar);
            int i2 = this.f978b;
            if (i2 >= 0 && iA > i2) {
                throw c.a.za.i.b(String.format("message too large %d > %d", Integer.valueOf(iA), Integer.valueOf(this.f978b))).b();
            }
            a(aVar, false);
            return iA;
        }
        this.m = i;
        int i3 = this.f978b;
        if (i3 >= 0 && i > i3) {
            throw c.a.za.i.b(String.format("message too large %d > %d", Integer.valueOf(i), Integer.valueOf(this.f978b))).b();
        }
        this.g.clear();
        this.g.put((byte) 0).putInt(i);
        if (this.f979c == null) {
            this.f979c = ((c.a.c.x) this.h).a(this.g.position() + i);
        }
        a(this.g.array(), 0, this.g.position());
        return a(inputStream, this.f982f);
    }

    @Override // c.a.b.InterfaceC0099cb
    public void close() {
        if (this.j) {
            return;
        }
        this.j = true;
        Ud ud = this.f979c;
        if (ud != null && ((c.a.c.w) ud).f1752c == 0) {
            this.f979c = null;
        }
        a(true, true);
    }

    @Override // c.a.b.InterfaceC0099cb
    public void d(int i) {
        Preconditions.checkState(this.f978b == -1, "max size already set");
        this.f978b = i;
    }

    @Override // c.a.b.InterfaceC0099cb
    public void flush() {
        Ud ud = this.f979c;
        if (ud == null || ((c.a.c.w) ud).f1752c <= 0) {
            return;
        }
        a(false, true);
    }

    @Override // c.a.b.InterfaceC0099cb
    public boolean isClosed() {
        return this.j;
    }

    @Override // c.a.b.InterfaceC0099cb
    public InterfaceC0099cb a(InterfaceC0233q interfaceC0233q) {
        Preconditions.checkNotNull(interfaceC0233q, "Can't pass an empty compressor");
        this.f980d = interfaceC0233q;
        return this;
    }

    private class b extends OutputStream {
        public /* synthetic */ b(Fc fc) {
        }

        @Override // java.io.OutputStream
        public void write(int i) {
            Gc.this.a(new byte[]{(byte) i}, 0, 1);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) {
            Gc.this.a(bArr, i, i2);
        }
    }

    @Override // c.a.b.InterfaceC0099cb
    public InterfaceC0099cb a(boolean z) {
        this.f981e = z;
        return this;
    }

    public final int a(InputStream inputStream, int i) throws IOException {
        a aVar = new a(null);
        OutputStream outputStreamA = this.f980d.a(aVar);
        try {
            int iA = a(inputStream, outputStreamA);
            outputStreamA.close();
            int i2 = this.f978b;
            if (i2 >= 0 && iA > i2) {
                throw c.a.za.i.b(String.format("message too large %d > %d", Integer.valueOf(iA), Integer.valueOf(this.f978b))).b();
            }
            a(aVar, true);
            return iA;
        } catch (Throwable th) {
            outputStreamA.close();
            throw th;
        }
    }

    public final void a(a aVar, boolean z) {
        int iA = a.a(aVar);
        this.g.clear();
        this.g.put(z ? (byte) 1 : (byte) 0).putInt(iA);
        Ud udA = ((c.a.c.x) this.h).a(5);
        ((c.a.c.w) udA).a(this.g.array(), 0, this.g.position());
        if (iA == 0) {
            this.f979c = udA;
            return;
        }
        ((AbstractC0087a) this.f977a).a(udA, false, false, this.k - 1);
        this.k = 1;
        List list = aVar.f983a;
        for (int i = 0; i < list.size() - 1; i++) {
            ((AbstractC0087a) this.f977a).a((Ud) list.get(i), false, false, 0);
        }
        this.f979c = (Ud) list.get(list.size() - 1);
        this.m = iA;
    }

    private final class a extends OutputStream {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<Ud> f983a = new ArrayList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Ud f984b;

        public /* synthetic */ a(Fc fc) {
        }

        public static /* synthetic */ int a(a aVar) {
            Iterator<Ud> it = aVar.f983a.iterator();
            int i = 0;
            while (it.hasNext()) {
                i += ((c.a.c.w) it.next()).f1752c;
            }
            return i;
        }

        @Override // java.io.OutputStream
        public void write(int i) {
            Ud ud = this.f984b;
            int i2 = 1;
            if (ud != null && ((c.a.c.w) ud).f1751b > 0) {
                c.a.c.w wVar = (c.a.c.w) ud;
                wVar.f1750a.writeByte((int) ((byte) i));
                wVar.f1751b--;
                wVar.f1752c++;
                return;
            }
            int i3 = 0;
            byte[] bArr = {(byte) i};
            if (this.f984b == null) {
                this.f984b = ((c.a.c.x) Gc.this.h).a(1);
                this.f983a.add(this.f984b);
            }
            while (i2 > 0) {
                int iMin = Math.min(i2, ((c.a.c.w) this.f984b).f1751b);
                if (iMin == 0) {
                    this.f984b = ((c.a.c.x) Gc.this.h).a(Math.max(i2, ((c.a.c.w) this.f984b).f1752c * 2));
                    this.f983a.add(this.f984b);
                } else {
                    ((c.a.c.w) this.f984b).a(bArr, i3, iMin);
                    i3 += iMin;
                    i2 -= iMin;
                }
            }
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) {
            if (this.f984b == null) {
                this.f984b = ((c.a.c.x) Gc.this.h).a(i2);
                this.f983a.add(this.f984b);
            }
            while (i2 > 0) {
                int iMin = Math.min(i2, ((c.a.c.w) this.f984b).f1751b);
                if (iMin == 0) {
                    this.f984b = ((c.a.c.x) Gc.this.h).a(Math.max(i2, ((c.a.c.w) this.f984b).f1752c * 2));
                    this.f983a.add(this.f984b);
                } else {
                    ((c.a.c.w) this.f984b).a(bArr, i, iMin);
                    i += iMin;
                    i2 -= iMin;
                }
            }
        }
    }

    public static int a(InputStream inputStream, OutputStream outputStream) throws IOException {
        if (inputStream instanceof c.a.A) {
            c.a.d.a.a aVar = (c.a.d.a.a) inputStream;
            MessageLite messageLite = aVar.f1764a;
            if (messageLite != null) {
                int serializedSize = messageLite.getSerializedSize();
                aVar.f1764a.writeTo(outputStream);
                aVar.f1764a = null;
                return serializedSize;
            }
            ByteArrayInputStream byteArrayInputStream = aVar.f1766c;
            if (byteArrayInputStream == null) {
                return 0;
            }
            int iA = (int) c.a.d.a.b.a(byteArrayInputStream, outputStream);
            aVar.f1766c = null;
            return iA;
        }
        long jCopy = ByteStreams.copy(inputStream, outputStream);
        Preconditions.checkArgument(jCopy <= 2147483647L, "Message size overflow: %s", jCopy);
        return (int) jCopy;
    }

    public final void a(byte[] bArr, int i, int i2) {
        while (i2 > 0) {
            Ud ud = this.f979c;
            if (ud != null && ((c.a.c.w) ud).f1751b == 0) {
                a(false, false);
            }
            if (this.f979c == null) {
                this.f979c = ((c.a.c.x) this.h).a(i2);
            }
            int iMin = Math.min(i2, ((c.a.c.w) this.f979c).f1751b);
            ((c.a.c.w) this.f979c).a(bArr, i, iMin);
            i += iMin;
            i2 -= iMin;
        }
    }

    @Override // c.a.b.InterfaceC0099cb
    public void a(InputStream inputStream) {
        int iB;
        if (!this.j) {
            this.k++;
            this.l++;
            this.m = 0L;
            Ld ld = this.i;
            int i = this.l;
            for (c.a.Ca ca : ld.f1069a) {
                ca.b(i);
            }
            boolean z = this.f981e && this.f980d != InterfaceC0231o.b.f1883a;
            try {
                int iAvailable = ((inputStream instanceof c.a.S) || (inputStream instanceof ByteArrayInputStream)) ? inputStream.available() : -1;
                if (iAvailable != 0 && z) {
                    iB = a(inputStream, iAvailable);
                } else {
                    iB = b(inputStream, iAvailable);
                }
                if (iAvailable != -1 && iB != iAvailable) {
                    throw c.a.za.k.b(String.format("Message length inaccurate %s != %s", Integer.valueOf(iB), Integer.valueOf(iAvailable))).b();
                }
                long j = iB;
                for (c.a.Ca ca2 : this.i.f1069a) {
                    ca2.c(j);
                }
                Ld ld2 = this.i;
                long j2 = this.m;
                for (c.a.Ca ca3 : ld2.f1069a) {
                    ca3.d(j2);
                }
                this.i.a(this.l, this.m, j);
                return;
            } catch (IOException e2) {
                throw c.a.za.k.b("Failed to frame message").b(e2).b();
            } catch (RuntimeException e3) {
                throw c.a.za.k.b("Failed to frame message").b(e3).b();
            }
        }
        throw new IllegalStateException("Framer already closed");
    }

    public final void a(boolean z, boolean z2) {
        Ud ud = this.f979c;
        this.f979c = null;
        ((AbstractC0087a) this.f977a).a(ud, z, z2, this.k);
        this.k = 0;
    }
}
