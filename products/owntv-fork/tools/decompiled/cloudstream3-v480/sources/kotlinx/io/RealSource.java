package kotlinx.io;

import java.io.EOFException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RealSource.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\u0010\b\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0012H\u0096\u0080\u0004J\n\u0010\u0015\u001a\u00020\tH\u0096\u0080\u0004J\u0012\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u0012H\u0096\u0080\u0004J\u0012\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0012H\u0096\u0080\u0004J\n\u0010\u0019\u001a\u00020\u001aH\u0096\u0080\u0004J\"\u0010\u0011\u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001bH\u0096\u0080\u0004J\u001a\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020 2\u0006\u0010\u0014\u001a\u00020\u0012H\u0096\u0080\u0004J\u0012\u0010!\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020 H\u0096\u0080\bJ\n\u0010\"\u001a\u00020#H\u0096\u0080\u0004J\n\u0010$\u001a\u00020\u001bH\u0096\u0080\u0004J\n\u0010%\u001a\u00020\u0012H\u0096\u0080\u0004J\u0012\u0010&\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u0012H\u0096\u0080\u0004J\n\u0010'\u001a\u00020\u0001H\u0096\u0080\u0004J\b\u0010(\u001a\u00020\u0017H\u0016J\n\u0010)\u001a\u00020*H\u0096\u0080\u0004J\n\u0010+\u001a\u00020\u0017H\u0082\u0088\u0004R\u0015\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\b\u001a\u00020\t8\u0006@\u0006X\u0087\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\n\u001a\u00020\u000bX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\u000b8VX\u0097\u0084\b¢\u0006\f\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010¨\u0006,"}, d2 = {"Lkotlinx/io/RealSource;", "Lkotlinx/io/Source;", "source", "Lkotlinx/io/RawSource;", "<init>", "(Lkotlinx/io/RawSource;)V", "getSource", "()Lkotlinx/io/RawSource;", "closed", "", "bufferField", "Lkotlinx/io/Buffer;", "buffer", "getBuffer$annotations", "()V", "getBuffer", "()Lkotlinx/io/Buffer;", "readAtMostTo", "", "sink", "byteCount", "exhausted", "require", "", "request", "readByte", "", "", "", "startIndex", "endIndex", "readTo", "Lkotlinx/io/RawSink;", "transferTo", "readShort", "", "readInt", "readLong", "skip", "peek", "close", "toString", "", "checkNotClosed", "kotlinx-io-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class RealSource implements Source {
    private final Buffer bufferField;
    public boolean closed;
    private final RawSource source;

    public static /* synthetic */ void getBuffer$annotations() {
    }

    public RealSource(RawSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.source = source;
        this.bufferField = new Buffer();
    }

    public final RawSource getSource() {
        return this.source;
    }

    @Override // kotlinx.io.Source, kotlinx.io.Sink
    /* JADX INFO: renamed from: getBuffer, reason: from getter */
    public Buffer getBufferField() {
        return this.bufferField;
    }

    @Override // kotlinx.io.Source
    public void require(long byteCount) throws EOFException {
        if (request(byteCount)) {
            return;
        }
        throw new EOFException("Source doesn't contain required number of bytes (" + byteCount + ").");
    }

    @Override // kotlinx.io.Source
    public byte readByte() throws EOFException {
        require(1L);
        return this.bufferField.readByte();
    }

    @Override // kotlinx.io.Source
    public int readAtMostTo(byte[] sink, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        _UtilKt.checkBounds(sink.length, startIndex, endIndex);
        if (this.bufferField.getSizeMut() == 0 && this.source.readAtMostTo(this.bufferField, 8192L) == -1) {
            return -1;
        }
        return this.bufferField.readAtMostTo(sink, startIndex, ((int) Math.min(endIndex - startIndex, this.bufferField.getSizeMut())) + startIndex);
    }

    @Override // kotlinx.io.Source
    public void readTo(RawSink sink, long byteCount) throws EOFException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        try {
            require(byteCount);
            this.bufferField.readTo(sink, byteCount);
        } catch (EOFException e) {
            sink.write(this.bufferField, this.bufferField.getSizeMut());
            throw e;
        }
    }

    @Override // kotlinx.io.Source
    public long transferTo(RawSink sink) {
        Buffer buffer;
        Intrinsics.checkNotNullParameter(sink, "sink");
        long j = 0;
        while (true) {
            long atMostTo = this.source.readAtMostTo(this.bufferField, 8192L);
            buffer = this.bufferField;
            if (atMostTo == -1) {
                break;
            }
            long jCompleteSegmentByteCount$kotlinx_io_core = buffer.completeSegmentByteCount$kotlinx_io_core();
            if (jCompleteSegmentByteCount$kotlinx_io_core > 0) {
                j += jCompleteSegmentByteCount$kotlinx_io_core;
                sink.write(this.bufferField, jCompleteSegmentByteCount$kotlinx_io_core);
            }
        }
        if (buffer.getSizeMut() <= 0) {
            return j;
        }
        long sizeMut = j + this.bufferField.getSizeMut();
        Buffer buffer2 = this.bufferField;
        sink.write(buffer2, buffer2.getSizeMut());
        return sizeMut;
    }

    @Override // kotlinx.io.Source
    public short readShort() throws EOFException {
        require(2L);
        return this.bufferField.readShort();
    }

    @Override // kotlinx.io.Source
    public int readInt() throws EOFException {
        require(4L);
        return this.bufferField.readInt();
    }

    @Override // kotlinx.io.Source
    public long readLong() throws EOFException {
        require(8L);
        return this.bufferField.readLong();
    }

    @Override // kotlinx.io.RawSource, java.lang.AutoCloseable
    public void close() throws EOFException {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.source.close();
        this.bufferField.clear();
    }

    public String toString() {
        return "buffered(" + this.source + ')';
    }

    private final void checkNotClosed() {
        if (this.closed) {
            throw new IllegalStateException("Source is closed.".toString());
        }
    }

    @Override // kotlinx.io.RawSource
    public long readAtMostTo(Buffer sink, long byteCount) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (this.closed) {
            throw new IllegalStateException("Source is closed.".toString());
        }
        if (byteCount < 0) {
            throw new IllegalArgumentException(("byteCount: " + byteCount).toString());
        }
        if (this.bufferField.getSizeMut() == 0 && this.source.readAtMostTo(this.bufferField, 8192L) == -1) {
            return -1L;
        }
        return this.bufferField.readAtMostTo(sink, Math.min(byteCount, this.bufferField.getSizeMut()));
    }

    @Override // kotlinx.io.Source
    public boolean exhausted() {
        if (this.closed) {
            throw new IllegalStateException("Source is closed.".toString());
        }
        return this.bufferField.exhausted() && this.source.readAtMostTo(this.bufferField, 8192L) == -1;
    }

    @Override // kotlinx.io.Source
    public boolean request(long byteCount) {
        if (this.closed) {
            throw new IllegalStateException("Source is closed.".toString());
        }
        if (byteCount < 0) {
            throw new IllegalArgumentException(("byteCount: " + byteCount).toString());
        }
        while (this.bufferField.getSizeMut() < byteCount) {
            if (this.source.readAtMostTo(this.bufferField, 8192L) == -1) {
                return false;
            }
        }
        return true;
    }

    @Override // kotlinx.io.Source
    public void skip(long byteCount) throws EOFException {
        if (this.closed) {
            throw new IllegalStateException("Source is closed.".toString());
        }
        if (byteCount < 0) {
            throw new IllegalArgumentException(("byteCount: " + byteCount).toString());
        }
        long j = byteCount;
        while (j > 0) {
            if (this.bufferField.getSizeMut() == 0 && this.source.readAtMostTo(this.bufferField, 8192L) == -1) {
                throw new EOFException("Source exhausted before skipping " + byteCount + " bytes (only " + (j - byteCount) + " bytes were skipped).");
            }
            long jMin = Math.min(j, this.bufferField.getSizeMut());
            this.bufferField.skip(jMin);
            j -= jMin;
        }
    }

    @Override // kotlinx.io.Source
    public Source peek() {
        if (!this.closed) {
            return CoreKt.buffered(new PeekSource(this));
        }
        throw new IllegalStateException("Source is closed.".toString());
    }
}
