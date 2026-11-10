package c.a.b;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/* JADX INFO: renamed from: c.a.b.bb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0094bb implements Tc {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Tc f1233a;

    public AbstractC0094bb(Tc tc) {
        Preconditions.checkNotNull(tc, "buf");
        this.f1233a = tc;
    }

    @Override // c.a.b.Tc
    public void a(byte[] bArr, int i, int i2) {
        this.f1233a.a(bArr, i, i2);
    }

    @Override // c.a.b.Tc
    public int m() {
        return this.f1233a.m();
    }

    @Override // c.a.b.Tc
    public boolean markSupported() {
        return this.f1233a.markSupported();
    }

    @Override // c.a.b.Tc
    public void o() {
        this.f1233a.o();
    }

    @Override // c.a.b.Tc
    public int readUnsignedByte() {
        return this.f1233a.readUnsignedByte();
    }

    @Override // c.a.b.Tc
    public void reset() {
        this.f1233a.reset();
    }

    @Override // c.a.b.Tc
    public void skipBytes(int i) {
        this.f1233a.skipBytes(i);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", this.f1233a).toString();
    }

    @Override // c.a.b.Tc
    public void a(ByteBuffer byteBuffer) {
        this.f1233a.a(byteBuffer);
    }

    @Override // c.a.b.Tc
    public void a(OutputStream outputStream, int i) {
        this.f1233a.a(outputStream, i);
    }

    @Override // c.a.b.Tc
    public Tc a(int i) {
        return this.f1233a.a(i);
    }
}
