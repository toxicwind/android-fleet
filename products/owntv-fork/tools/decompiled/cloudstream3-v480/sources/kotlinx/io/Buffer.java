package kotlinx.io;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.common.base.Ascii;
import io.ktor.http.ContentDisposition;
import java.io.EOFException;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Typography;
import kotlinx.io.unsafe.SegmentReadContext;
import kotlinx.io.unsafe.UnsafeBufferOperations;
import kotlinx.io.unsafe.UnsafeBufferOperationsKt;

/* JADX INFO: compiled from: Buffer.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u000f\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\t\bF¢\u0006\u0004\b\u0003\u0010\u0004J\n\u0010\u001d\u001a\u00020\u001eH\u0096\u0080\u0004J\u0012\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0011H\u0096\u0080\u0004J\u0012\u0010\"\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\u0011H\u0096\u0080\u0004J\n\u0010#\u001a\u00020$H\u0096\u0080\u0004J\n\u0010%\u001a\u00020&H\u0096\u0080\u0004J\n\u0010'\u001a\u00020(H\u0096\u0080\u0004J\n\u0010)\u001a\u00020\u0011H\u0096\u0080\u0004J\u0012\u0010*\u001a\u00020+2\u0006\u0010!\u001a\u00020\u0011H\u0082\u0080\u0004J\n\u0010,\u001a\u00020 H\u0097\u0080\u0004J\n\u0010-\u001a\u00020 H\u0096\u0080\u0004J\n\u0010.\u001a\u00020 H\u0096\u0080\u0004J&\u0010/\u001a\u00020 2\u0006\u00100\u001a\u00020\u00002\b\b\u0002\u00101\u001a\u00020\u00112\b\b\u0002\u00102\u001a\u00020\u0011H\u0086\u0080\u0004J\u000f\u00103\u001a\u00020\u0011H\u0080\u0080\u0004¢\u0006\u0002\b4J\u0012\u00105\u001a\u00020$2\u0006\u00106\u001a\u00020\u0011H\u0086\u0082\u0004J\n\u00107\u001a\u00020 H\u0086\u0080\u0004J\u0012\u00108\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0011H\u0096\u0080\u0004J\"\u00109\u001a\u00020(2\u0006\u0010:\u001a\u00020;2\u0006\u00101\u001a\u00020(2\u0006\u00102\u001a\u00020(H\u0096\u0080\u0004J\u001a\u00109\u001a\u00020\u00112\u0006\u0010:\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u0011H\u0096\u0080\u0004J\u001a\u0010<\u001a\u00020 2\u0006\u0010:\u001a\u00020=2\u0006\u0010!\u001a\u00020\u0011H\u0096\u0080\u0004J\u0012\u0010>\u001a\u00020\u00112\u0006\u0010:\u001a\u00020=H\u0096\u0080\bJ\n\u0010?\u001a\u00020\u0001H\u0096\u0080\u0004J\u0012\u0010@\u001a\u00020\u00062\u0006\u0010A\u001a\u00020(H\u0081\u0080\u0004J\"\u0010B\u001a\u00020 2\u0006\u0010C\u001a\u00020;2\u0006\u00101\u001a\u00020(2\u0006\u00102\u001a\u00020(H\u0096\u0080\u0004J\u001a\u0010B\u001a\u00020 2\u0006\u0010C\u001a\u00020D2\u0006\u0010!\u001a\u00020\u0011H\u0096\u0080\u0004J\u001a\u0010B\u001a\u00020 2\u0006\u0010C\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u0011H\u0096\u0080\u0004J\u0012\u0010E\u001a\u00020\u00112\u0006\u0010C\u001a\u00020DH\u0096\u0080\bJ\u0012\u0010F\u001a\u00020 2\u0006\u0010G\u001a\u00020$H\u0096\u0080\u0004J\u0012\u0010H\u001a\u00020 2\u0006\u0010I\u001a\u00020&H\u0096\u0080\u0004J\u0012\u0010J\u001a\u00020 2\u0006\u0010K\u001a\u00020(H\u0096\u0080\u0004J\u0012\u0010L\u001a\u00020 2\u0006\u0010M\u001a\u00020\u0011H\u0096\u0080\u0004J\n\u0010N\u001a\u00020\u0000H\u0086\u0080\u0004J\b\u0010O\u001a\u00020 H\u0016J\n\u0010P\u001a\u00020QH\u0096\u0080\u0004J\u000f\u0010R\u001a\u00020 H\u0080\u0080\u0004¢\u0006\u0002\bSJ\n\u0010T\u001a\u00020 H\u0081\u0080\u0004J\u001c\u0010U\u001a\u00020 2\u0006\u0010V\u001a\u00020\u00062\b\b\u0002\u0010W\u001a\u00020\u001eH\u0082\u0088\u0004R'\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0000@\u0000X\u0081\u008e\b¢\u0006\u0014\n\u0000\u0012\u0004\b\u0007\u0010\u0004\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR'\u0010\f\u001a\u0004\u0018\u00010\u00068\u0000@\u0000X\u0081\u008e\b¢\u0006\u0014\n\u0000\u0012\u0004\b\r\u0010\u0004\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000bR\u0015\u0010\u0010\u001a\u00020\u00118FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R%\u0010\u0014\u001a\u00020\u00118\u0000@\u0000X\u0081\u008e\b¢\u0006\u0014\n\u0000\u0012\u0004\b\u0015\u0010\u0004\u001a\u0004\b\u0016\u0010\u0013\"\u0004\b\u0017\u0010\u0018R\u001b\u0010\u0019\u001a\u00020\u00008VX\u0097\u0084\b¢\u0006\f\u0012\u0004\b\u001a\u0010\u0004\u001a\u0004\b\u001b\u0010\u001c¨\u0006X"}, d2 = {"Lkotlinx/io/Buffer;", "Lkotlinx/io/Source;", "Lkotlinx/io/Sink;", "<init>", "()V", TtmlNode.TAG_HEAD, "Lkotlinx/io/Segment;", "getHead$annotations", "getHead", "()Lkotlinx/io/Segment;", "setHead", "(Lkotlinx/io/Segment;)V", "tail", "getTail$annotations", "getTail", "setTail", ContentDisposition.Parameters.Size, "", "getSize", "()J", "sizeMut", "getSizeMut$annotations", "getSizeMut", "setSizeMut", "(J)V", "buffer", "getBuffer$annotations", "getBuffer", "()Lkotlinx/io/Buffer;", "exhausted", "", "require", "", "byteCount", "request", "readByte", "", "readShort", "", "readInt", "", "readLong", "throwEof", "", "hintEmit", "emit", "flush", "copyTo", "out", "startIndex", "endIndex", "completeSegmentByteCount", "completeSegmentByteCount$kotlinx_io_core", "get", "position", "clear", "skip", "readAtMostTo", "sink", "", "readTo", "Lkotlinx/io/RawSink;", "transferTo", "peek", "writableSegment", "minimumCapacity", "write", "source", "Lkotlinx/io/RawSource;", "transferFrom", "writeByte", "byte", "writeShort", "short", "writeInt", "int", "writeLong", "long", "copy", "close", "toString", "", "recycleHead", "recycleHead$kotlinx_io_core", "recycleTail", "pushSegment", "newTail", "tryCompact", "kotlinx-io-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Buffer implements Source, Sink {
    private Segment head;
    private long sizeMut;
    private Segment tail;

    public static /* synthetic */ void getBuffer$annotations() {
    }

    public static /* synthetic */ void getHead$annotations() {
    }

    public static /* synthetic */ void getSizeMut$annotations() {
    }

    public static /* synthetic */ void getTail$annotations() {
    }

    @Override // kotlinx.io.RawSource, java.lang.AutoCloseable
    public void close() {
    }

    @Override // kotlinx.io.Sink
    public void emit() {
    }

    @Override // kotlinx.io.Sink, kotlinx.io.RawSink, java.io.Flushable
    public void flush() {
    }

    @Override // kotlinx.io.Source, kotlinx.io.Sink
    public Buffer getBuffer() {
        return this;
    }

    @Override // kotlinx.io.Sink
    public void hintEmit() {
    }

    public final /* synthetic */ Segment getHead() {
        return this.head;
    }

    public final /* synthetic */ void setHead(Segment segment) {
        this.head = segment;
    }

    public final /* synthetic */ Segment getTail() {
        return this.tail;
    }

    public final /* synthetic */ void setTail(Segment segment) {
        this.tail = segment;
    }

    /* JADX INFO: renamed from: getSize, reason: from getter */
    public final long getSizeMut() {
        return this.sizeMut;
    }

    public final /* synthetic */ long getSizeMut() {
        return this.sizeMut;
    }

    public final /* synthetic */ void setSizeMut(long j) {
        this.sizeMut = j;
    }

    @Override // kotlinx.io.Source
    public boolean exhausted() {
        return getSizeMut() == 0;
    }

    @Override // kotlinx.io.Source
    public void require(long byteCount) throws EOFException {
        if (byteCount < 0) {
            throw new IllegalArgumentException(("byteCount: " + byteCount).toString());
        }
        if (getSizeMut() >= byteCount) {
            return;
        }
        throw new EOFException("Buffer doesn't contain required number of bytes (size: " + getSizeMut() + ", required: " + byteCount + ')');
    }

    @Override // kotlinx.io.Source
    public boolean request(long byteCount) {
        if (byteCount >= 0) {
            return getSizeMut() >= byteCount;
        }
        throw new IllegalArgumentException(("byteCount: " + byteCount + " < 0").toString());
    }

    @Override // kotlinx.io.Source
    public byte readByte() throws EOFException {
        Segment segment = this.head;
        if (segment == null) {
            throwEof(1L);
            throw new KotlinNothingValueException();
        }
        int size = segment.getSize();
        if (size == 0) {
            recycleHead$kotlinx_io_core();
            return readByte();
        }
        byte byte$kotlinx_io_core = segment.readByte$kotlinx_io_core();
        this.sizeMut--;
        if (size == 1) {
            recycleHead$kotlinx_io_core();
        }
        return byte$kotlinx_io_core;
    }

    @Override // kotlinx.io.Source
    public short readShort() throws EOFException {
        Segment segment = this.head;
        if (segment == null) {
            throwEof(2L);
            throw new KotlinNothingValueException();
        }
        int size = segment.getSize();
        if (size < 2) {
            require(2L);
            if (size == 0) {
                recycleHead$kotlinx_io_core();
                return readShort();
            }
            return (short) ((readByte() & 255) | ((readByte() & 255) << 8));
        }
        short short$kotlinx_io_core = segment.readShort$kotlinx_io_core();
        this.sizeMut -= 2;
        if (size == 2) {
            recycleHead$kotlinx_io_core();
        }
        return short$kotlinx_io_core;
    }

    @Override // kotlinx.io.Source
    public int readInt() throws EOFException {
        Segment segment = this.head;
        if (segment == null) {
            throwEof(4L);
            throw new KotlinNothingValueException();
        }
        int size = segment.getSize();
        if (size < 4) {
            require(4L);
            if (size == 0) {
                recycleHead$kotlinx_io_core();
                return readInt();
            }
            return (readShort() & UShort.MAX_VALUE) | (readShort() << 16);
        }
        int int$kotlinx_io_core = segment.readInt$kotlinx_io_core();
        this.sizeMut -= 4;
        if (size == 4) {
            recycleHead$kotlinx_io_core();
        }
        return int$kotlinx_io_core;
    }

    @Override // kotlinx.io.Source
    public long readLong() throws EOFException {
        Segment segment = this.head;
        if (segment == null) {
            throwEof(8L);
            throw new KotlinNothingValueException();
        }
        int size = segment.getSize();
        if (size < 8) {
            require(8L);
            if (size == 0) {
                recycleHead$kotlinx_io_core();
                return readLong();
            }
            return (((long) readInt()) << 32) | (((long) readInt()) & 4294967295L);
        }
        long long$kotlinx_io_core = segment.readLong$kotlinx_io_core();
        this.sizeMut -= 8;
        if (size == 8) {
            recycleHead$kotlinx_io_core();
        }
        return long$kotlinx_io_core;
    }

    private final Void throwEof(long byteCount) throws EOFException {
        throw new EOFException("Buffer doesn't contain required number of bytes (size: " + getSizeMut() + ", required: " + byteCount + ')');
    }

    public static /* synthetic */ void copyTo$default(Buffer buffer, Buffer buffer2, long j, long j2, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        long j3 = j;
        if ((i & 4) != 0) {
            j2 = buffer.getSizeMut();
        }
        buffer.copyTo(buffer2, j3, j2);
    }

    public final void copyTo(Buffer out, long startIndex, long endIndex) {
        Intrinsics.checkNotNullParameter(out, "out");
        _UtilKt.checkBounds(getSizeMut(), startIndex, endIndex);
        if (startIndex == endIndex) {
            return;
        }
        long limit = endIndex - startIndex;
        out.sizeMut += limit;
        Segment next = this.head;
        long limit2 = startIndex;
        while (true) {
            Intrinsics.checkNotNull(next);
            if (limit2 < next.getLimit() - next.getPos()) {
                break;
            }
            limit2 -= (long) (next.getLimit() - next.getPos());
            next = next.getNext();
        }
        while (limit > 0) {
            Intrinsics.checkNotNull(next);
            Segment segmentSharedCopy$kotlinx_io_core = next.sharedCopy$kotlinx_io_core();
            segmentSharedCopy$kotlinx_io_core.setPos(segmentSharedCopy$kotlinx_io_core.getPos() + ((int) limit2));
            segmentSharedCopy$kotlinx_io_core.setLimit(Math.min(segmentSharedCopy$kotlinx_io_core.getPos() + ((int) limit), segmentSharedCopy$kotlinx_io_core.getLimit()));
            if (out.getHead() == null) {
                out.setHead(segmentSharedCopy$kotlinx_io_core);
                out.setTail(segmentSharedCopy$kotlinx_io_core);
            } else {
                Segment tail = out.getTail();
                Intrinsics.checkNotNull(tail);
                out.setTail(tail.push$kotlinx_io_core(segmentSharedCopy$kotlinx_io_core));
            }
            limit -= (long) (segmentSharedCopy$kotlinx_io_core.getLimit() - segmentSharedCopy$kotlinx_io_core.getPos());
            next = next.getNext();
            limit2 = 0;
        }
    }

    public final long completeSegmentByteCount$kotlinx_io_core() {
        long sizeMut = getSizeMut();
        if (sizeMut == 0) {
            return 0L;
        }
        Segment segment = this.tail;
        Intrinsics.checkNotNull(segment);
        return (segment.getLimit() >= 8192 || !segment.owner) ? sizeMut : sizeMut - ((long) (segment.getLimit() - segment.getPos()));
    }

    public final byte get(long position) {
        long j = 0;
        if (position < 0 || position >= getSizeMut()) {
            throw new IndexOutOfBoundsException("position (" + position + ") is not within the range [0..size(" + getSizeMut() + "))");
        }
        if (position == 0) {
            Segment segment = this.head;
            Intrinsics.checkNotNull(segment);
            return segment.getUnchecked$kotlinx_io_core(0);
        }
        if (getHead() == null) {
            Segment segment2 = null;
            Intrinsics.checkNotNull(null);
            segment2.getUnchecked$kotlinx_io_core((int) (position - (-1)));
            throw null;
        }
        if (getSizeMut() - position < position) {
            Segment tail = getTail();
            long sizeMut = getSizeMut();
            while (tail != null && sizeMut > position) {
                sizeMut -= (long) (tail.getLimit() - tail.getPos());
                if (sizeMut <= position) {
                    break;
                }
                tail = tail.getPrev();
            }
            Intrinsics.checkNotNull(tail);
            return tail.getUnchecked$kotlinx_io_core((int) (position - sizeMut));
        }
        Segment head = getHead();
        while (head != null) {
            long limit = ((long) (head.getLimit() - head.getPos())) + j;
            if (limit > position) {
                break;
            }
            head = head.getNext();
            j = limit;
        }
        Intrinsics.checkNotNull(head);
        return head.getUnchecked$kotlinx_io_core((int) (position - j));
    }

    public final void clear() throws EOFException {
        skip(getSizeMut());
    }

    @Override // kotlinx.io.Source
    public void skip(long byteCount) throws EOFException {
        if (byteCount < 0) {
            throw new IllegalArgumentException(("byteCount (" + byteCount + ") < 0").toString());
        }
        long j = byteCount;
        while (j > 0) {
            Segment segment = this.head;
            if (segment == null) {
                throw new EOFException("Buffer exhausted before skipping " + byteCount + " bytes.");
            }
            int iMin = (int) Math.min(j, segment.getLimit() - segment.getPos());
            long j2 = iMin;
            this.sizeMut -= j2;
            j -= j2;
            segment.setPos(segment.getPos() + iMin);
            if (segment.getPos() == segment.getLimit()) {
                recycleHead$kotlinx_io_core();
            }
        }
    }

    @Override // kotlinx.io.Source
    public int readAtMostTo(byte[] sink, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        _UtilKt.checkBounds(sink.length, startIndex, endIndex);
        Segment segment = this.head;
        if (segment == null) {
            return -1;
        }
        int iMin = Math.min(endIndex - startIndex, segment.getSize());
        segment.readTo$kotlinx_io_core(sink, startIndex, startIndex + iMin);
        this.sizeMut -= (long) iMin;
        if (SegmentKt.isEmpty(segment)) {
            recycleHead$kotlinx_io_core();
        }
        return iMin;
    }

    @Override // kotlinx.io.RawSource
    public long readAtMostTo(Buffer sink, long byteCount) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (byteCount < 0) {
            throw new IllegalArgumentException(("byteCount (" + byteCount + ") < 0").toString());
        }
        if (getSizeMut() == 0) {
            return -1L;
        }
        if (byteCount > getSizeMut()) {
            byteCount = getSizeMut();
        }
        sink.write(this, byteCount);
        return byteCount;
    }

    @Override // kotlinx.io.Source
    public void readTo(RawSink sink, long byteCount) throws EOFException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (byteCount < 0) {
            throw new IllegalArgumentException(("byteCount (" + byteCount + ") < 0").toString());
        }
        if (getSizeMut() < byteCount) {
            sink.write(this, getSizeMut());
            throw new EOFException("Buffer exhausted before writing " + byteCount + " bytes. Only " + getSizeMut() + " bytes were written.");
        }
        sink.write(this, byteCount);
    }

    @Override // kotlinx.io.Source
    public long transferTo(RawSink sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        long sizeMut = getSizeMut();
        if (sizeMut > 0) {
            sink.write(this, sizeMut);
        }
        return sizeMut;
    }

    @Override // kotlinx.io.Source
    public Source peek() {
        return CoreKt.buffered(new PeekSource(this));
    }

    public final /* synthetic */ Segment writableSegment(int minimumCapacity) {
        if (minimumCapacity < 1 || minimumCapacity > 8192) {
            throw new IllegalArgumentException(("unexpected capacity (" + minimumCapacity + "), should be in range [1, 8192]").toString());
        }
        Segment segment = this.tail;
        if (segment == null) {
            Segment segmentTake = SegmentPool.take();
            this.head = segmentTake;
            this.tail = segmentTake;
            return segmentTake;
        }
        Intrinsics.checkNotNull(segment);
        if (segment.getLimit() + minimumCapacity <= 8192 && segment.owner) {
            return segment;
        }
        Segment segmentPush$kotlinx_io_core = segment.push$kotlinx_io_core(SegmentPool.take());
        this.tail = segmentPush$kotlinx_io_core;
        return segmentPush$kotlinx_io_core;
    }

    @Override // kotlinx.io.Sink
    public void write(byte[] source, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(source, "source");
        _UtilKt.checkBounds(source.length, startIndex, endIndex);
        int i = startIndex;
        while (i < endIndex) {
            Segment segmentWritableSegment = writableSegment(1);
            int iMin = Math.min(endIndex - i, segmentWritableSegment.getRemainingCapacity()) + i;
            segmentWritableSegment.write$kotlinx_io_core(source, i, iMin);
            i = iMin;
        }
        this.sizeMut += (long) (endIndex - startIndex);
    }

    @Override // kotlinx.io.Sink
    public void write(RawSource source, long byteCount) throws EOFException {
        Intrinsics.checkNotNullParameter(source, "source");
        if (byteCount < 0) {
            throw new IllegalArgumentException(("byteCount (" + byteCount + ") < 0").toString());
        }
        long j = byteCount;
        while (j > 0) {
            long atMostTo = source.readAtMostTo(this, j);
            if (atMostTo == -1) {
                throw new EOFException("Source exhausted before reading " + byteCount + " bytes. Only " + (byteCount - j) + " were read.");
            }
            j -= atMostTo;
        }
    }

    @Override // kotlinx.io.RawSink
    public void write(Buffer source, long byteCount) {
        Intrinsics.checkNotNullParameter(source, "source");
        if (source == this) {
            throw new IllegalArgumentException("source == this".toString());
        }
        _UtilKt.checkOffsetAndCount(source.sizeMut, 0L, byteCount);
        while (byteCount > 0) {
            Segment segment = source.head;
            Intrinsics.checkNotNull(segment);
            if (byteCount < segment.getSize()) {
                Segment segment2 = this.tail;
                if (segment2 != null && segment2.owner) {
                    if ((((long) segment2.getLimit()) + byteCount) - ((long) (segment2.getShared$kotlinx_io_core() ? 0 : segment2.getPos())) <= 8192) {
                        Segment segment3 = source.head;
                        Intrinsics.checkNotNull(segment3);
                        segment3.writeTo$kotlinx_io_core(segment2, (int) byteCount);
                        source.sizeMut -= byteCount;
                        this.sizeMut += byteCount;
                        return;
                    }
                }
                Segment segment4 = source.head;
                Intrinsics.checkNotNull(segment4);
                source.head = segment4.split$kotlinx_io_core((int) byteCount);
            }
            Segment segment5 = source.head;
            Intrinsics.checkNotNull(segment5);
            long size = segment5.getSize();
            Segment segmentPop$kotlinx_io_core = segment5.pop$kotlinx_io_core();
            source.head = segmentPop$kotlinx_io_core;
            if (segmentPop$kotlinx_io_core == null) {
                source.tail = null;
            }
            if (getHead() == null) {
                setHead(segment5);
                setTail(segment5);
            } else {
                Segment tail = getTail();
                Intrinsics.checkNotNull(tail);
                setTail(tail.push$kotlinx_io_core(segment5).compact$kotlinx_io_core());
                Segment tail2 = getTail();
                Intrinsics.checkNotNull(tail2);
                if (tail2.getPrev() == null) {
                    setHead(getTail());
                }
            }
            source.sizeMut -= size;
            this.sizeMut += size;
            byteCount -= size;
        }
    }

    @Override // kotlinx.io.Sink
    public long transferFrom(RawSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        long j = 0;
        while (true) {
            long atMostTo = source.readAtMostTo(this, 8192L);
            if (atMostTo == -1) {
                return j;
            }
            j += atMostTo;
        }
    }

    @Override // kotlinx.io.Sink
    public void writeByte(byte b) {
        writableSegment(1).writeByte$kotlinx_io_core(b);
        this.sizeMut++;
    }

    @Override // kotlinx.io.Sink
    public void writeShort(short s) {
        writableSegment(2).writeShort$kotlinx_io_core(s);
        this.sizeMut += 2;
    }

    @Override // kotlinx.io.Sink
    public void writeInt(int i) {
        writableSegment(4).writeInt$kotlinx_io_core(i);
        this.sizeMut += 4;
    }

    @Override // kotlinx.io.Sink
    public void writeLong(long j) {
        writableSegment(8).writeLong$kotlinx_io_core(j);
        this.sizeMut += 8;
    }

    public final Buffer copy() {
        Buffer buffer = new Buffer();
        if (getSizeMut() == 0) {
            return buffer;
        }
        Segment segment = this.head;
        Intrinsics.checkNotNull(segment);
        Segment segmentSharedCopy$kotlinx_io_core = segment.sharedCopy$kotlinx_io_core();
        buffer.head = segmentSharedCopy$kotlinx_io_core;
        buffer.tail = segmentSharedCopy$kotlinx_io_core;
        for (Segment next = segment.getNext(); next != null; next = next.getNext()) {
            Segment segment2 = buffer.tail;
            Intrinsics.checkNotNull(segment2);
            buffer.tail = segment2.push$kotlinx_io_core(next.sharedCopy$kotlinx_io_core());
        }
        buffer.sizeMut = getSizeMut();
        return buffer;
    }

    public String toString() {
        if (getSizeMut() == 0) {
            return "Buffer(size=0)";
        }
        int iMin = (int) Math.min(64L, getSizeMut());
        StringBuilder sb = new StringBuilder((iMin * 2) + (getSizeMut() > 64 ? 1 : 0));
        UnsafeBufferOperations unsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
        int i = 0;
        for (Segment head = getHead(); head != null; head = head.getNext()) {
            SegmentReadContext segmentReadContext = UnsafeBufferOperationsKt.SegmentReadContextImpl;
            int i2 = 0;
            while (i < iMin && i2 < head.getSize()) {
                int i3 = i2 + 1;
                byte unchecked = segmentReadContext.getUnchecked(head, i2);
                i++;
                sb.append(_UtilKt.getHEX_DIGIT_CHARS()[(unchecked >> 4) & 15]);
                sb.append(_UtilKt.getHEX_DIGIT_CHARS()[unchecked & Ascii.SI]);
                i2 = i3;
            }
        }
        if (getSizeMut() > 64) {
            sb.append(Typography.ellipsis);
        }
        return "Buffer(size=" + getSizeMut() + " hex=" + ((Object) sb) + ')';
    }

    public final void recycleHead$kotlinx_io_core() {
        Segment segment = this.head;
        Intrinsics.checkNotNull(segment);
        Segment next = segment.getNext();
        this.head = next;
        if (next == null) {
            this.tail = null;
        } else {
            next.setPrev(null);
        }
        segment.setNext(null);
        SegmentPool.recycle(segment);
    }

    public final /* synthetic */ void recycleTail() {
        Segment segment = this.tail;
        Intrinsics.checkNotNull(segment);
        Segment prev = segment.getPrev();
        this.tail = prev;
        if (prev == null) {
            this.head = null;
        } else {
            prev.setNext(null);
        }
        segment.setPrev(null);
        SegmentPool.recycle(segment);
    }

    private final void pushSegment(Segment newTail, boolean tryCompact) {
        if (getHead() == null) {
            setHead(newTail);
            setTail(newTail);
            return;
        }
        if (tryCompact) {
            Segment tail = getTail();
            Intrinsics.checkNotNull(tail);
            setTail(tail.push$kotlinx_io_core(newTail).compact$kotlinx_io_core());
            Segment tail2 = getTail();
            Intrinsics.checkNotNull(tail2);
            if (tail2.getPrev() == null) {
                setHead(getTail());
                return;
            }
            return;
        }
        Segment tail3 = getTail();
        Intrinsics.checkNotNull(tail3);
        setTail(tail3.push$kotlinx_io_core(newTail));
    }

    static /* synthetic */ void pushSegment$default(Buffer buffer, Segment segment, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if (buffer.getHead() == null) {
            buffer.setHead(segment);
            buffer.setTail(segment);
            return;
        }
        if (z) {
            Segment tail = buffer.getTail();
            Intrinsics.checkNotNull(tail);
            buffer.setTail(tail.push$kotlinx_io_core(segment).compact$kotlinx_io_core());
            Segment tail2 = buffer.getTail();
            Intrinsics.checkNotNull(tail2);
            if (tail2.getPrev() == null) {
                buffer.setHead(buffer.getTail());
                return;
            }
            return;
        }
        Segment tail3 = buffer.getTail();
        Intrinsics.checkNotNull(tail3);
        buffer.setTail(tail3.push$kotlinx_io_core(segment));
    }
}
