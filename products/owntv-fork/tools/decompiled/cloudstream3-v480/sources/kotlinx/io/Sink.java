package kotlinx.io;

import kotlin.IgnorableReturnValue;
import kotlin.Metadata;

/* JADX INFO: compiled from: Sink.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001J&\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\rH¦\u0080\u0004J\u0012\u0010\u000f\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0011H§\u0080\bJ\u001a\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0010H¦\u0080\u0004J\u0012\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0015H¦\u0080\u0004J\u0012\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0018H¦\u0080\u0004J\u0012\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\rH¦\u0080\u0004J\u0012\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u0010H¦\u0080\u0004J\n\u0010\u001d\u001a\u00020\tH¦\u0080\u0004J\n\u0010\u001e\u001a\u00020\tH¦\u0080\u0004J\n\u0010\u001f\u001a\u00020\tH§\u0080\u0004R\u001b\u0010\u0002\u001a\u00020\u00038&X§\u0084\b¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0002\u0003 ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006!À\u0006\u0001"}, d2 = {"Lkotlinx/io/Sink;", "Lkotlinx/io/RawSink;", "buffer", "Lkotlinx/io/Buffer;", "getBuffer$annotations", "()V", "getBuffer", "()Lkotlinx/io/Buffer;", "write", "", "source", "", "startIndex", "", "endIndex", "transferFrom", "", "Lkotlinx/io/RawSource;", "byteCount", "writeByte", "byte", "", "writeShort", "short", "", "writeInt", "int", "writeLong", "long", "flush", "emit", "hintEmit", "Lkotlinx/io/RealSink;", "kotlinx-io-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface Sink extends RawSink {
    void emit();

    @Override // kotlinx.io.RawSink, java.io.Flushable
    void flush();

    Buffer getBuffer();

    void hintEmit();

    @IgnorableReturnValue
    long transferFrom(RawSource source);

    void write(RawSource source, long byteCount);

    void write(byte[] source, int startIndex, int endIndex);

    void writeByte(byte b);

    void writeInt(int i);

    void writeLong(long j);

    void writeShort(short s);

    /* JADX INFO: renamed from: kotlinx.io.Sink$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Sink.kt */
    public final /* synthetic */ class CC {
        public static /* synthetic */ void getBuffer$annotations() {
        }

        public static /* synthetic */ void write$default(Sink sink, byte[] bArr, int i, int i2, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: write");
            }
            if ((i3 & 2) != 0) {
                i = 0;
            }
            if ((i3 & 4) != 0) {
                i2 = bArr.length;
            }
            sink.write(bArr, i, i2);
        }
    }
}
