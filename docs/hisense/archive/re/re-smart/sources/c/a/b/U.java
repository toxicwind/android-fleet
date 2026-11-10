package c.a.b;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public class U implements W.a<ByteBuffer> {
    @Override // c.a.b.W.b
    public int a(Tc tc, int i, Object obj, int i2) {
        ByteBuffer byteBuffer = (ByteBuffer) obj;
        int iLimit = byteBuffer.limit();
        byteBuffer.limit(byteBuffer.position() + i);
        tc.a(byteBuffer);
        byteBuffer.limit(iLimit);
        return 0;
    }
}
