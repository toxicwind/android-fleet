package kotlinx.io;

import java.io.IOException;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.unsafe.UnsafeBufferOperations;

/* JADX INFO: compiled from: JvmCore.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0012\u0018\u00002\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0096\u0080\u0004J\n\u0010\f\u001a\u00020\u0007H\u0096\u0080\u0004J\n\u0010\r\u001a\u00020\u0007H\u0096\u0080\u0004J\n\u0010\u000e\u001a\u00020\u000fH\u0096\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lkotlinx/io/OutputStreamSink;", "Lkotlinx/io/RawSink;", "out", "Ljava/io/OutputStream;", "<init>", "(Ljava/io/OutputStream;)V", "write", "", "source", "Lkotlinx/io/Buffer;", "byteCount", "", "flush", "close", "toString", "", "kotlinx-io-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
class OutputStreamSink implements RawSink {
    private final OutputStream out;

    public OutputStreamSink(OutputStream out) {
        Intrinsics.checkNotNullParameter(out, "out");
        this.out = out;
    }

    @Override // kotlinx.io.RawSink
    public void write(Buffer source, long byteCount) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        _UtilKt.checkOffsetAndCount(source.getSizeMut(), 0L, byteCount);
        while (byteCount > 0) {
            UnsafeBufferOperations unsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
            if (source.exhausted()) {
                throw new IllegalArgumentException("Buffer is empty".toString());
            }
            Segment head = source.getHead();
            Intrinsics.checkNotNull(head);
            byte[] bArrDataAsByteArray = head.dataAsByteArray(true);
            int pos = head.getPos();
            int iMin = (int) Math.min(byteCount, head.getLimit() - pos);
            this.out.write(bArrDataAsByteArray, pos, iMin);
            if (iMin != 0) {
                if (iMin < 0) {
                    throw new IllegalStateException("Returned negative read bytes count");
                }
                if (iMin > head.getSize()) {
                    throw new IllegalStateException("Returned too many bytes");
                }
                source.skip(iMin);
            }
            byteCount -= (long) iMin;
        }
    }

    @Override // kotlinx.io.RawSink, java.io.Flushable
    public void flush() throws IOException {
        this.out.flush();
    }

    @Override // kotlinx.io.RawSink, java.lang.AutoCloseable
    public void close() throws IOException {
        this.out.close();
    }

    public String toString() {
        return "RawSink(" + this.out + ')';
    }
}
