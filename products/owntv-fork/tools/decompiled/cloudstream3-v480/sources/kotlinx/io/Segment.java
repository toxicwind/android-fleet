package kotlinx.io;

import com.lagradost.cloudstream3.actions.temp.CloudStreamPackage;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Segment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b(\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b*\u0018\u0000 k2\u00020\u0001:\u0001kB\t\bR¢\u0006\u0004\b\u0002\u0010\u0003B3\bR\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\u0002\u0010\rJ\u000f\u0010(\u001a\u00020\u0000H\u0080\u0080\u0004¢\u0006\u0002\b)J\u0011\u0010*\u001a\u0004\u0018\u00010\u0000H\u0080\u0080\u0004¢\u0006\u0002\b+J\u0017\u0010,\u001a\u00020\u00002\u0006\u0010-\u001a\u00020\u0000H\u0080\u0080\u0004¢\u0006\u0002\b.J\u0017\u0010/\u001a\u00020\u00002\u0006\u00100\u001a\u00020\u0007H\u0080\u0080\u0004¢\u0006\u0002\b1J\u000f\u00102\u001a\u00020\u0000H\u0080\u0080\u0004¢\u0006\u0002\b3J\u0017\u00104\u001a\u0002052\u0006\u00106\u001a\u000207H\u0080\u0080\u0004¢\u0006\u0002\b8J\u0017\u00109\u001a\u0002052\u0006\u0010:\u001a\u00020;H\u0080\u0080\u0004¢\u0006\u0002\b<J\u0017\u0010=\u001a\u0002052\u0006\u0010>\u001a\u00020\u0007H\u0080\u0080\u0004¢\u0006\u0002\b?J\u0017\u0010@\u001a\u0002052\u0006\u0010A\u001a\u00020BH\u0080\u0080\u0004¢\u0006\u0002\bCJ\u000f\u0010D\u001a\u000207H\u0080\u0080\u0004¢\u0006\u0002\bEJ\u000f\u0010F\u001a\u00020;H\u0080\u0080\u0004¢\u0006\u0002\bGJ\u000f\u0010H\u001a\u00020\u0007H\u0080\u0080\u0004¢\u0006\u0002\bIJ\u000f\u0010J\u001a\u00020BH\u0080\u0080\u0004¢\u0006\u0002\bKJ\u001f\u0010L\u001a\u0002052\u0006\u0010M\u001a\u00020\u00002\u0006\u00100\u001a\u00020\u0007H\u0080\u0080\u0004¢\u0006\u0002\bNJ'\u0010O\u001a\u0002052\u0006\u0010P\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020\u00072\u0006\u0010R\u001a\u00020\u0007H\u0080\u0080\u0004¢\u0006\u0002\bSJ'\u0010T\u001a\u0002052\u0006\u0010U\u001a\u00020\u00052\u0006\u0010V\u001a\u00020\u00072\u0006\u0010W\u001a\u00020\u0007H\u0080\u0080\u0004¢\u0006\u0002\bXJ\u0012\u0010]\u001a\u00020\u00052\u0006\u0010^\u001a\u00020\fH\u0081\u0080\u0004J\u001a\u0010_\u001a\u0002052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u0007H\u0081\u0080\u0004J\u0017\u0010a\u001a\u0002072\u0006\u0010b\u001a\u00020\u0007H\u0080\u0080\u0004¢\u0006\u0002\bcJ\u001f\u0010d\u001a\u0002052\u0006\u0010b\u001a\u00020\u00072\u0006\u0010e\u001a\u000207H\u0080\u0080\u0004¢\u0006\u0002\bfJ'\u0010d\u001a\u0002052\u0006\u0010b\u001a\u00020\u00072\u0006\u0010g\u001a\u0002072\u0006\u0010h\u001a\u000207H\u0080\u0080\u0004¢\u0006\u0002\bfJ/\u0010d\u001a\u0002052\u0006\u0010b\u001a\u00020\u00072\u0006\u0010g\u001a\u0002072\u0006\u0010h\u001a\u0002072\u0006\u0010i\u001a\u000207H\u0080\u0080\u0004¢\u0006\u0002\bfJ7\u0010d\u001a\u0002052\u0006\u0010b\u001a\u00020\u00072\u0006\u0010g\u001a\u0002072\u0006\u0010h\u001a\u0002072\u0006\u0010i\u001a\u0002072\u0006\u0010j\u001a\u000207H\u0080\u0080\u0004¢\u0006\u0002\bfR\u000f\u0010\u0004\u001a\u00020\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000R%\u0010\u0006\u001a\u00020\u00078\u0000@\u0000X\u0081\u008e\b¢\u0006\u0014\n\u0000\u0012\u0004\b\u000e\u0010\u0003\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R%\u0010\b\u001a\u00020\u00078\u0000@\u0000X\u0081\u008e\b¢\u0006\u0014\n\u0000\u0012\u0004\b\u0013\u0010\u0003\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R\u0015\u0010\u0016\u001a\u00020\f8@X\u0080\u0084\b¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u001d\u0010\u0019\u001a\u0004\u0018\u00010\nX\u0080\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0013\u0010\u000b\u001a\u00020\f8\u0000@\u0000X\u0081\u008e\b¢\u0006\u0002\n\u0000R'\u0010\u001e\u001a\u0004\u0018\u00010\u00008\u0000@\u0000X\u0081\u008e\b¢\u0006\u0014\n\u0000\u0012\u0004\b\u001f\u0010\u0003\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R'\u0010$\u001a\u0004\u0018\u00010\u00008\u0000@\u0000X\u0081\u008e\b¢\u0006\u0014\n\u0000\u0012\u0004\b%\u0010\u0003\u001a\u0004\b&\u0010!\"\u0004\b'\u0010#R\u0015\u0010Y\u001a\u00020\u00078FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\bZ\u0010\u0010R\u0015\u0010[\u001a\u00020\u00078FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\\\u0010\u0010¨\u0006l"}, d2 = {"Lkotlinx/io/Segment;", "", "<init>", "()V", "data", "", CloudStreamPackage.POSITION_EXTRA, "", "limit", "shareToken", "Lkotlinx/io/SegmentCopyTracker;", "owner", "", "([BIILkotlinx/io/SegmentCopyTracker;Z)V", "getPos$annotations", "getPos", "()I", "setPos", "(I)V", "getLimit$annotations", "getLimit", "setLimit", "shared", "getShared$kotlinx_io_core", "()Z", "copyTracker", "getCopyTracker$kotlinx_io_core", "()Lkotlinx/io/SegmentCopyTracker;", "setCopyTracker$kotlinx_io_core", "(Lkotlinx/io/SegmentCopyTracker;)V", "next", "getNext$annotations", "getNext", "()Lkotlinx/io/Segment;", "setNext", "(Lkotlinx/io/Segment;)V", "prev", "getPrev$annotations", "getPrev", "setPrev", "sharedCopy", "sharedCopy$kotlinx_io_core", "pop", "pop$kotlinx_io_core", "push", "segment", "push$kotlinx_io_core", "split", "byteCount", "split$kotlinx_io_core", "compact", "compact$kotlinx_io_core", "writeByte", "", "byte", "", "writeByte$kotlinx_io_core", "writeShort", "short", "", "writeShort$kotlinx_io_core", "writeInt", "int", "writeInt$kotlinx_io_core", "writeLong", "long", "", "writeLong$kotlinx_io_core", "readByte", "readByte$kotlinx_io_core", "readShort", "readShort$kotlinx_io_core", "readInt", "readInt$kotlinx_io_core", "readLong", "readLong$kotlinx_io_core", "writeTo", "sink", "writeTo$kotlinx_io_core", "readTo", "dst", "dstStartOffset", "dstEndOffset", "readTo$kotlinx_io_core", "write", "src", "srcStartOffset", "srcEndOffset", "write$kotlinx_io_core", ContentDisposition.Parameters.Size, "getSize", "remainingCapacity", "getRemainingCapacity", "dataAsByteArray", "readOnly", "writeBackData", "bytesToCommit", "getUnchecked", "index", "getUnchecked$kotlinx_io_core", "setUnchecked", ES6Iterator.VALUE_PROPERTY, "setUnchecked$kotlinx_io_core", "b0", "b1", "b2", "b3", "Companion", "kotlinx-io-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Segment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int SHARE_MINIMUM = 1024;
    public static final int SIZE = 8192;
    private SegmentCopyTracker copyTracker;
    private final byte[] data;
    private int limit;
    private Segment next;
    public boolean owner;
    private int pos;
    private Segment prev;

    public /* synthetic */ Segment(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public /* synthetic */ Segment(byte[] bArr, int i, int i2, SegmentCopyTracker segmentCopyTracker, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(bArr, i, i2, segmentCopyTracker, z);
    }

    public static /* synthetic */ void getLimit$annotations() {
    }

    public static /* synthetic */ void getNext$annotations() {
    }

    public static /* synthetic */ void getPos$annotations() {
    }

    public static /* synthetic */ void getPrev$annotations() {
    }

    public final /* synthetic */ void writeBackData(byte[] data, int bytesToCommit) {
        Intrinsics.checkNotNullParameter(data, "data");
    }

    public final /* synthetic */ int getPos() {
        return this.pos;
    }

    public final /* synthetic */ void setPos(int i) {
        this.pos = i;
    }

    public final /* synthetic */ int getLimit() {
        return this.limit;
    }

    public final /* synthetic */ void setLimit(int i) {
        this.limit = i;
    }

    public final boolean getShared$kotlinx_io_core() {
        SegmentCopyTracker segmentCopyTracker = this.copyTracker;
        if (segmentCopyTracker != null) {
            return segmentCopyTracker.getShared();
        }
        return false;
    }

    /* JADX INFO: renamed from: getCopyTracker$kotlinx_io_core, reason: from getter */
    public final SegmentCopyTracker getCopyTracker() {
        return this.copyTracker;
    }

    public final void setCopyTracker$kotlinx_io_core(SegmentCopyTracker segmentCopyTracker) {
        this.copyTracker = segmentCopyTracker;
    }

    public final /* synthetic */ Segment getNext() {
        return this.next;
    }

    public final /* synthetic */ void setNext(Segment segment) {
        this.next = segment;
    }

    public final /* synthetic */ Segment getPrev() {
        return this.prev;
    }

    public final /* synthetic */ void setPrev(Segment segment) {
        this.prev = segment;
    }

    private Segment() {
        this.data = new byte[8192];
        this.owner = true;
        this.copyTracker = null;
    }

    private Segment(byte[] bArr, int i, int i2, SegmentCopyTracker segmentCopyTracker, boolean z) {
        this.data = bArr;
        this.pos = i;
        this.limit = i2;
        this.copyTracker = segmentCopyTracker;
        this.owner = z;
    }

    public final Segment sharedCopy$kotlinx_io_core() {
        SegmentCopyTracker segmentCopyTrackerTracker = this.copyTracker;
        if (segmentCopyTrackerTracker == null) {
            segmentCopyTrackerTracker = SegmentPool.tracker();
            this.copyTracker = segmentCopyTrackerTracker;
        }
        SegmentCopyTracker segmentCopyTracker = segmentCopyTrackerTracker;
        byte[] bArr = this.data;
        int i = this.pos;
        int i2 = this.limit;
        segmentCopyTracker.addCopy();
        Unit unit = Unit.INSTANCE;
        return new Segment(bArr, i, i2, segmentCopyTracker, false);
    }

    public final Segment pop$kotlinx_io_core() {
        Segment segment = this.next;
        Segment segment2 = this.prev;
        if (segment2 != null) {
            Intrinsics.checkNotNull(segment2);
            segment2.next = this.next;
        }
        Segment segment3 = this.next;
        if (segment3 != null) {
            Intrinsics.checkNotNull(segment3);
            segment3.prev = this.prev;
        }
        this.next = null;
        this.prev = null;
        return segment;
    }

    public final Segment push$kotlinx_io_core(Segment segment) {
        Intrinsics.checkNotNullParameter(segment, "segment");
        segment.prev = this;
        segment.next = this.next;
        Segment segment2 = this.next;
        if (segment2 != null) {
            Intrinsics.checkNotNull(segment2);
            segment2.prev = segment;
        }
        this.next = segment;
        return segment;
    }

    public final Segment split$kotlinx_io_core(int byteCount) {
        Segment segmentTake;
        if (byteCount <= 0 || byteCount > this.limit - this.pos) {
            throw new IllegalArgumentException("byteCount out of range".toString());
        }
        if (byteCount >= 1024) {
            segmentTake = sharedCopy$kotlinx_io_core();
        } else {
            segmentTake = SegmentPool.take();
            byte[] bArr = this.data;
            byte[] bArr2 = segmentTake.data;
            int i = this.pos;
            ArraysKt.copyInto$default(bArr, bArr2, 0, i, i + byteCount, 2, (Object) null);
        }
        segmentTake.limit = segmentTake.pos + byteCount;
        this.pos += byteCount;
        Segment segment = this.prev;
        if (segment != null) {
            Intrinsics.checkNotNull(segment);
            segment.push$kotlinx_io_core(segmentTake);
            return segmentTake;
        }
        segmentTake.next = this;
        this.prev = segmentTake;
        return segmentTake;
    }

    public final Segment compact$kotlinx_io_core() {
        int i;
        Segment segment = this.prev;
        if (segment == null) {
            throw new IllegalStateException("cannot compact".toString());
        }
        Intrinsics.checkNotNull(segment);
        if (segment.owner) {
            int i2 = this.limit - this.pos;
            Segment segment2 = this.prev;
            Intrinsics.checkNotNull(segment2);
            int i3 = 8192 - segment2.limit;
            Segment segment3 = this.prev;
            Intrinsics.checkNotNull(segment3);
            if (segment3.getShared$kotlinx_io_core()) {
                i = 0;
            } else {
                Segment segment4 = this.prev;
                Intrinsics.checkNotNull(segment4);
                i = segment4.pos;
            }
            if (i2 <= i3 + i) {
                Segment segment5 = this.prev;
                Intrinsics.checkNotNull(segment5);
                writeTo$kotlinx_io_core(segment5, i2);
                if (pop$kotlinx_io_core() != null) {
                    throw new IllegalStateException("Check failed.");
                }
                SegmentPool.recycle(this);
                return segment5;
            }
        }
        return this;
    }

    public final void writeByte$kotlinx_io_core(byte b) {
        byte[] bArr = this.data;
        int i = this.limit;
        this.limit = i + 1;
        bArr[i] = b;
    }

    public final void writeShort$kotlinx_io_core(short s) {
        byte[] bArr = this.data;
        int i = this.limit;
        bArr[i] = (byte) ((s >>> 8) & 255);
        bArr[i + 1] = (byte) (s & 255);
        this.limit = i + 2;
    }

    public final void writeInt$kotlinx_io_core(int i) {
        byte[] bArr = this.data;
        int i2 = this.limit;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        bArr[i2 + 1] = (byte) ((i >>> 16) & 255);
        bArr[i2 + 2] = (byte) ((i >>> 8) & 255);
        bArr[i2 + 3] = (byte) (i & 255);
        this.limit = i2 + 4;
    }

    public final void writeLong$kotlinx_io_core(long j) {
        byte[] bArr = this.data;
        int i = this.limit;
        bArr[i] = (byte) ((j >>> 56) & 255);
        bArr[i + 1] = (byte) ((j >>> 48) & 255);
        bArr[i + 2] = (byte) ((j >>> 40) & 255);
        bArr[i + 3] = (byte) ((j >>> 32) & 255);
        bArr[i + 4] = (byte) ((j >>> 24) & 255);
        bArr[i + 5] = (byte) ((j >>> 16) & 255);
        bArr[i + 6] = (byte) ((j >>> 8) & 255);
        bArr[i + 7] = (byte) (j & 255);
        this.limit = i + 8;
    }

    public final byte readByte$kotlinx_io_core() {
        byte[] bArr = this.data;
        int i = this.pos;
        this.pos = i + 1;
        return bArr[i];
    }

    public final short readShort$kotlinx_io_core() {
        byte[] bArr = this.data;
        int i = this.pos;
        int i2 = (bArr[i] & 255) << 8;
        short s = (short) ((bArr[i + 1] & 255) | i2);
        this.pos = i + 2;
        return s;
    }

    public final int readInt$kotlinx_io_core() {
        byte[] bArr = this.data;
        int i = this.pos;
        int i2 = ((bArr[i + 1] & 255) << 16) | ((bArr[i] & 255) << 24);
        int i3 = i2 | ((bArr[i + 2] & 255) << 8);
        int i4 = (bArr[i + 3] & 255) | i3;
        this.pos = i + 4;
        return i4;
    }

    public final long readLong$kotlinx_io_core() {
        byte[] bArr = this.data;
        int i = this.pos;
        long j = ((((long) bArr[i]) & 255) << 56) | ((((long) bArr[i + 1]) & 255) << 48) | ((((long) bArr[i + 2]) & 255) << 40) | ((((long) bArr[i + 3]) & 255) << 32) | ((((long) bArr[i + 4]) & 255) << 24) | ((((long) bArr[i + 5]) & 255) << 16) | ((((long) bArr[i + 6]) & 255) << 8);
        long j2 = j | (255 & ((long) bArr[i + 7]));
        this.pos = i + 8;
        return j2;
    }

    public final void writeTo$kotlinx_io_core(Segment sink, int byteCount) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (!sink.owner) {
            throw new IllegalStateException("only owner can write".toString());
        }
        if (sink.limit + byteCount > 8192) {
            if (sink.getShared$kotlinx_io_core()) {
                throw new IllegalArgumentException();
            }
            int i = sink.limit;
            int i2 = sink.pos;
            if ((i + byteCount) - i2 > 8192) {
                throw new IllegalArgumentException();
            }
            byte[] bArr = sink.data;
            ArraysKt.copyInto$default(bArr, bArr, 0, i2, i, 2, (Object) null);
            sink.limit -= sink.pos;
            sink.pos = 0;
        }
        byte[] bArr2 = this.data;
        byte[] bArr3 = sink.data;
        int i3 = sink.limit;
        int i4 = this.pos;
        ArraysKt.copyInto(bArr2, bArr3, i3, i4, i4 + byteCount);
        sink.limit += byteCount;
        this.pos += byteCount;
    }

    public final void readTo$kotlinx_io_core(byte[] dst, int dstStartOffset, int dstEndOffset) {
        Intrinsics.checkNotNullParameter(dst, "dst");
        int i = dstEndOffset - dstStartOffset;
        byte[] bArr = this.data;
        int i2 = this.pos;
        ArraysKt.copyInto(bArr, dst, dstStartOffset, i2, i2 + i);
        this.pos += i;
    }

    public final void write$kotlinx_io_core(byte[] src, int srcStartOffset, int srcEndOffset) {
        Intrinsics.checkNotNullParameter(src, "src");
        ArraysKt.copyInto(src, this.data, this.limit, srcStartOffset, srcEndOffset);
        this.limit += srcEndOffset - srcStartOffset;
    }

    public final int getSize() {
        return this.limit - this.pos;
    }

    public final int getRemainingCapacity() {
        return this.data.length - this.limit;
    }

    public final /* synthetic */ byte[] dataAsByteArray(boolean readOnly) {
        return this.data;
    }

    public final byte getUnchecked$kotlinx_io_core(int index) {
        return this.data[this.pos + index];
    }

    public final void setUnchecked$kotlinx_io_core(int index, byte value) {
        this.data[this.limit + index] = value;
    }

    public final void setUnchecked$kotlinx_io_core(int index, byte b0, byte b1) {
        byte[] bArr = this.data;
        int i = this.limit + index;
        bArr[i] = b0;
        bArr[i + 1] = b1;
    }

    public final void setUnchecked$kotlinx_io_core(int index, byte b0, byte b1, byte b2) {
        byte[] bArr = this.data;
        int i = this.limit + index;
        bArr[i] = b0;
        bArr[i + 1] = b1;
        bArr[i + 2] = b2;
    }

    public final void setUnchecked$kotlinx_io_core(int index, byte b0, byte b1, byte b2, byte b3) {
        byte[] bArr = this.data;
        int i = this.limit + index;
        bArr[i] = b0;
        bArr[i + 1] = b1;
        bArr[i + 2] = b2;
        bArr[i + 3] = b3;
    }

    /* JADX INFO: compiled from: Segment.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0007\u001a\u00020\bH\u0080\u0080\u0004¢\u0006\u0002\b\tJ9\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0080\u0080\u0004¢\u0006\u0002\b\tR\u000f\u0010\u0004\u001a\u00020\u0005X\u0080Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0006\u001a\u00020\u0005X\u0080Ô\b¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lkotlinx/io/Segment$Companion;", "", "<init>", "()V", "SIZE", "", "SHARE_MINIMUM", "new", "Lkotlinx/io/Segment;", "new$kotlinx_io_core", "data", "", CloudStreamPackage.POSITION_EXTRA, "limit", "copyTracker", "Lkotlinx/io/SegmentCopyTracker;", "owner", "", "kotlinx-io-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final /* synthetic */ Segment new$kotlinx_io_core() {
            return new Segment(null);
        }

        public final /* synthetic */ Segment new$kotlinx_io_core(byte[] data, int pos, int limit, SegmentCopyTracker copyTracker, boolean owner) {
            Intrinsics.checkNotNullParameter(data, "data");
            return new Segment(data, pos, limit, copyTracker, owner, null);
        }
    }
}
