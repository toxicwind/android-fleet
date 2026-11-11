package kotlinx.io;

import java.io.EOFException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RealSink.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0096\u0080\u0004J\"\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0096\u0080\u0004J\u0012\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0013\u001a\u00020\u001bH\u0096\u0080\bJ\u001a\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u001b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0096\u0080\u0004J\u0012\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u001eH\u0096\u0080\u0004J\u0012\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020!H\u0096\u0080\u0004J\u0012\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\u0018H\u0096\u0080\u0004J\u0012\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020\u0015H\u0096\u0080\u0004J\n\u0010&\u001a\u00020\u0012H\u0097\u0080\u0004J\n\u0010'\u001a\u00020\u0012H\u0096\u0080\u0004J\n\u0010(\u001a\u00020\u0012H\u0096\u0080\u0004J\b\u0010)\u001a\u00020\u0012H\u0016J\n\u0010*\u001a\u00020+H\u0096\u0080\u0004J\n\u0010,\u001a\u00020\u0012H\u0082\u0088\u0004R\u0015\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\b\u001a\u00020\t8\u0006@\u0006X\u0087\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\n\u001a\u00020\u000bX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\u000b8VX\u0097\u0084\b¢\u0006\f\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010¨\u0006-"}, d2 = {"Lkotlinx/io/RealSink;", "Lkotlinx/io/Sink;", "sink", "Lkotlinx/io/RawSink;", "<init>", "(Lkotlinx/io/RawSink;)V", "getSink", "()Lkotlinx/io/RawSink;", "closed", "", "bufferField", "Lkotlinx/io/Buffer;", "buffer", "getBuffer$annotations", "()V", "getBuffer", "()Lkotlinx/io/Buffer;", "write", "", "source", "byteCount", "", "", "startIndex", "", "endIndex", "transferFrom", "Lkotlinx/io/RawSource;", "writeByte", "byte", "", "writeShort", "short", "", "writeInt", "int", "writeLong", "long", "hintEmit", "emit", "flush", "close", "toString", "", "checkNotClosed", "kotlinx-io-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class RealSink implements Sink {
    private final Buffer bufferField;
    public boolean closed;
    private final RawSink sink;

    public static /* synthetic */ void getBuffer$annotations() {
    }

    public RealSink(RawSink sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        this.sink = sink;
        this.bufferField = new Buffer();
    }

    public final RawSink getSink() {
        return this.sink;
    }

    @Override // kotlinx.io.Sink
    /* JADX INFO: renamed from: getBuffer, reason: from getter */
    public Buffer getBufferField() {
        return this.bufferField;
    }

    @Override // kotlinx.io.RawSink, java.lang.AutoCloseable
    public void close() throws Throwable {
        if (this.closed) {
            return;
        }
        if (this.bufferField.getSizeMut() > 0) {
            RawSink rawSink = this.sink;
            Buffer buffer = this.bufferField;
            rawSink.write(buffer, buffer.getSizeMut());
        }
        th = null;
        try {
            this.sink.close();
        } catch (Throwable th) {
            if (th == null) {
                th = th;
            }
        }
        this.closed = true;
        if (th != null) {
            throw th;
        }
    }

    public String toString() {
        return "buffered(" + this.sink + ')';
    }

    private final void checkNotClosed() {
        if (this.closed) {
            throw new IllegalStateException("Sink is closed.".toString());
        }
    }

    @Override // kotlinx.io.RawSink
    public void write(Buffer source, long byteCount) {
        Intrinsics.checkNotNullParameter(source, "source");
        if (this.closed) {
            throw new IllegalStateException("Sink is closed.".toString());
        }
        if (byteCount < 0) {
            throw new IllegalArgumentException(("byteCount: " + byteCount).toString());
        }
        this.bufferField.write(source, byteCount);
        hintEmit();
    }

    @Override // kotlinx.io.Sink
    public void write(byte[] source, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(source, "source");
        if (this.closed) {
            throw new IllegalStateException("Sink is closed.".toString());
        }
        _UtilKt.checkBounds(source.length, startIndex, endIndex);
        this.bufferField.write(source, startIndex, endIndex);
        hintEmit();
    }

    @Override // kotlinx.io.Sink
    public long transferFrom(RawSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        if (this.closed) {
            throw new IllegalStateException("Sink is closed.".toString());
        }
        long j = 0;
        while (true) {
            long atMostTo = source.readAtMostTo(this.bufferField, 8192L);
            if (atMostTo == -1) {
                return j;
            }
            j += atMostTo;
            hintEmit();
        }
    }

    @Override // kotlinx.io.Sink
    public void write(RawSource source, long byteCount) throws EOFException {
        Intrinsics.checkNotNullParameter(source, "source");
        if (this.closed) {
            throw new IllegalStateException("Sink is closed.".toString());
        }
        if (byteCount < 0) {
            throw new IllegalArgumentException(("byteCount: " + byteCount).toString());
        }
        long j = byteCount;
        while (j > 0) {
            long atMostTo = source.readAtMostTo(this.bufferField, j);
            if (atMostTo == -1) {
                throw new EOFException("Source exhausted before reading " + byteCount + " bytes from it (number of bytes read: " + (byteCount - j) + ").");
            }
            j -= atMostTo;
            hintEmit();
        }
    }

    @Override // kotlinx.io.Sink
    public void writeByte(byte b) {
        if (!this.closed) {
            this.bufferField.writeByte(b);
            hintEmit();
            return;
        }
        throw new IllegalStateException("Sink is closed.".toString());
    }

    @Override // kotlinx.io.Sink
    public void writeShort(short s) {
        if (!this.closed) {
            this.bufferField.writeShort(s);
            hintEmit();
            return;
        }
        throw new IllegalStateException("Sink is closed.".toString());
    }

    @Override // kotlinx.io.Sink
    public void writeInt(int i) {
        if (!this.closed) {
            this.bufferField.writeInt(i);
            hintEmit();
            return;
        }
        throw new IllegalStateException("Sink is closed.".toString());
    }

    @Override // kotlinx.io.Sink
    public void writeLong(long j) {
        if (!this.closed) {
            this.bufferField.writeLong(j);
            hintEmit();
            return;
        }
        throw new IllegalStateException("Sink is closed.".toString());
    }

    @Override // kotlinx.io.Sink
    public void hintEmit() {
        if (!this.closed) {
            long jCompleteSegmentByteCount$kotlinx_io_core = this.bufferField.completeSegmentByteCount$kotlinx_io_core();
            if (jCompleteSegmentByteCount$kotlinx_io_core > 0) {
                this.sink.write(this.bufferField, jCompleteSegmentByteCount$kotlinx_io_core);
                return;
            }
            return;
        }
        throw new IllegalStateException("Sink is closed.".toString());
    }

    @Override // kotlinx.io.Sink
    public void emit() {
        if (!this.closed) {
            long sizeMut = this.bufferField.getSizeMut();
            if (sizeMut > 0) {
                this.sink.write(this.bufferField, sizeMut);
                return;
            }
            return;
        }
        throw new IllegalStateException("Sink is closed.".toString());
    }

    @Override // kotlinx.io.Sink, kotlinx.io.RawSink, java.io.Flushable
    public void flush() {
        if (!this.closed) {
            if (this.bufferField.getSizeMut() > 0) {
                RawSink rawSink = this.sink;
                Buffer buffer = this.bufferField;
                rawSink.write(buffer, buffer.getSizeMut());
            }
            this.sink.flush();
            return;
        }
        throw new IllegalStateException("Sink is closed.".toString());
    }
}
