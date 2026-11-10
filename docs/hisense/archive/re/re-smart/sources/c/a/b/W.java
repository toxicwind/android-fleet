package c.a.b;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.InvalidMarkException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class W extends AbstractC0102d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a<Void> f1173a = new Q();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a<Void> f1174b = new S();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a<byte[]> f1175c = new T();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a<ByteBuffer> f1176d = new U();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b<OutputStream> f1177e = new V();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Deque<Tc> f1178f;
    public Deque<Tc> g;
    public int h;
    public boolean i;

    /* JADX INFO: Access modifiers changed from: private */
    interface a<T> extends b<T> {
    }

    /* JADX INFO: Access modifiers changed from: private */
    interface b<T> {
        int a(Tc tc, int i, T t, int i2);
    }

    public W(int i) {
        this.f1178f = new ArrayDeque(i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0063, code lost:
    
        r1 = r1;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [c.a.b.Tc] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7, types: [c.a.b.Tc] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2, types: [c.a.b.W] */
    /* JADX WARN: Type inference failed for: r1v3, types: [c.a.b.W] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    @Override // c.a.b.Tc
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public c.a.b.Tc a(int r7) {
        /*
            r6 = this;
            if (r7 > 0) goto L5
            c.a.b.Tc r7 = c.a.b.Vc.f1167a
            return r7
        L5:
            int r0 = r6.m()
            if (r0 < r7) goto L66
            int r0 = r6.h
            int r0 = r0 - r7
            r6.h = r0
            r0 = 0
            r1 = r0
        L12:
            java.util.Deque<c.a.b.Tc> r2 = r6.f1178f
            java.lang.Object r2 = r2.peek()
            c.a.b.Tc r2 = (c.a.b.Tc) r2
            int r3 = r2.m()
            if (r3 <= r7) goto L26
            c.a.b.Tc r7 = r2.a(r7)
            r2 = 0
            goto L3e
        L26:
            boolean r4 = r6.i
            if (r4 == 0) goto L32
            c.a.b.Tc r2 = r2.a(r3)
            r6.a()
            goto L3a
        L32:
            java.util.Deque<c.a.b.Tc> r2 = r6.f1178f
            java.lang.Object r2 = r2.poll()
            c.a.b.Tc r2 = (c.a.b.Tc) r2
        L3a:
            int r7 = r7 - r3
            r5 = r2
            r2 = r7
            r7 = r5
        L3e:
            if (r0 != 0) goto L42
            r0 = r7
            goto L61
        L42:
            if (r1 != 0) goto L5e
            c.a.b.W r1 = new c.a.b.W
            r3 = 2
            if (r2 != 0) goto L4a
            goto L57
        L4a:
            java.util.Deque<c.a.b.Tc> r4 = r6.f1178f
            int r4 = r4.size()
            int r4 = r4 + r3
            r3 = 16
            int r3 = java.lang.Math.min(r4, r3)
        L57:
            r1.<init>(r3)
            r1.a(r0)
            r0 = r1
        L5e:
            r1.a(r7)
        L61:
            if (r2 > 0) goto L64
            return r0
        L64:
            r7 = r2
            goto L12
        L66:
            java.lang.IndexOutOfBoundsException r7 = new java.lang.IndexOutOfBoundsException
            r7.<init>()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.b.W.a(int):c.a.b.Tc");
    }

    @Override // c.a.b.AbstractC0102d, c.a.b.Tc, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        while (!this.f1178f.isEmpty()) {
            this.f1178f.remove().close();
        }
        if (this.g != null) {
            while (!this.g.isEmpty()) {
                this.g.remove().close();
            }
        }
    }

    @Override // c.a.b.Tc
    public int m() {
        return this.h;
    }

    @Override // c.a.b.AbstractC0102d, c.a.b.Tc
    public boolean markSupported() {
        Iterator<Tc> it = this.f1178f.iterator();
        while (it.hasNext()) {
            if (!it.next().markSupported()) {
                return false;
            }
        }
        return true;
    }

    @Override // c.a.b.AbstractC0102d, c.a.b.Tc
    public void o() {
        if (this.g == null) {
            this.g = new ArrayDeque(Math.min(this.f1178f.size(), 16));
        }
        while (!this.g.isEmpty()) {
            this.g.remove().close();
        }
        this.i = true;
        Tc tcPeek = this.f1178f.peek();
        if (tcPeek != null) {
            tcPeek.o();
        }
    }

    @Override // c.a.b.Tc
    public int readUnsignedByte() {
        return a((a<Object>) f1173a, 1, (Object) null, 0);
    }

    @Override // c.a.b.AbstractC0102d, c.a.b.Tc
    public void reset() {
        if (!this.i) {
            throw new InvalidMarkException();
        }
        Tc tcPeek = this.f1178f.peek();
        if (tcPeek != null) {
            int iM = tcPeek.m();
            tcPeek.reset();
            this.h = (tcPeek.m() - iM) + this.h;
        }
        while (true) {
            Tc tcPollLast = this.g.pollLast();
            if (tcPollLast == null) {
                return;
            }
            tcPollLast.reset();
            this.f1178f.addFirst(tcPollLast);
            this.h = tcPollLast.m() + this.h;
        }
    }

    @Override // c.a.b.Tc
    public void skipBytes(int i) {
        a((a<Object>) f1174b, i, (Object) null, 0);
    }

    public W() {
        this.f1178f = new ArrayDeque();
    }

    public void a(Tc tc) {
        boolean z = this.i && this.f1178f.isEmpty();
        if (!(tc instanceof W)) {
            this.f1178f.add(tc);
            this.h = tc.m() + this.h;
        } else {
            W w = (W) tc;
            while (!w.f1178f.isEmpty()) {
                this.f1178f.add(w.f1178f.remove());
            }
            this.h += w.h;
            w.h = 0;
            w.close();
        }
        if (z) {
            this.f1178f.peek().o();
        }
    }

    public final <T> int a(b<T> bVar, int i, T t, int i2) {
        if (this.h >= i) {
            if (!this.f1178f.isEmpty() && this.f1178f.peek().m() == 0) {
                a();
            }
            while (i > 0 && !this.f1178f.isEmpty()) {
                Tc tcPeek = this.f1178f.peek();
                int iMin = Math.min(i, tcPeek.m());
                i2 = bVar.a(tcPeek, iMin, t, i2);
                i -= iMin;
                this.h -= iMin;
                if (this.f1178f.peek().m() == 0) {
                    a();
                }
            }
            if (i <= 0) {
                return i2;
            }
            throw new AssertionError("Failed executing read operation");
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // c.a.b.Tc
    public void a(byte[] bArr, int i, int i2) {
        a(f1175c, i2, bArr, i);
    }

    @Override // c.a.b.Tc
    public void a(ByteBuffer byteBuffer) {
        a(f1176d, byteBuffer.remaining(), byteBuffer, 0);
    }

    @Override // c.a.b.Tc
    public void a(OutputStream outputStream, int i) {
        a(f1177e, i, outputStream, 0);
    }

    public final <T> int a(a<T> aVar, int i, T t, int i2) {
        try {
            return a((b) aVar, i, (Object) t, i2);
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }

    public final void a() {
        if (this.i) {
            this.g.add(this.f1178f.remove());
            Tc tcPeek = this.f1178f.peek();
            if (tcPeek != null) {
                tcPeek.o();
                return;
            }
            return;
        }
        this.f1178f.remove().close();
    }
}
