package io.ktor.utils.io.jvm.nio;

import androidx.collection.SieveCacheKt;
import androidx.tvprovider.media.tv.TvContractCompat;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.Buffer;
import kotlinx.io.RawSource;
import kotlinx.io.Segment;
import kotlinx.io.SegmentKt;
import kotlinx.io.unsafe.UnsafeBufferOperations;

/* JADX INFO: compiled from: Reading.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0012\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J!\u0010\n\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0096\u0080\u0004¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0011\u0010\u0010\u001a\u00020\u000fH\u0096\u0080\u0004¢\u0006\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0012¨\u0006\u0013"}, d2 = {"Lio/ktor/utils/io/jvm/nio/ReadableByteChannelSource;", "Lkotlinx/io/RawSource;", "Ljava/nio/channels/ReadableByteChannel;", TvContractCompat.PARAM_CHANNEL, "<init>", "(Ljava/nio/channels/ReadableByteChannel;)V", "Lkotlinx/io/Buffer;", "sink", "", "byteCount", "readAtMostTo", "(Lkotlinx/io/Buffer;J)J", "", "close", "()V", "", "toString", "()Ljava/lang/String;", "Ljava/nio/channels/ReadableByteChannel;", "ktor-io"}, k = 1, mv = {2, 3, 0}, xi = 48)
class ReadableByteChannelSource implements RawSource {
    private final ReadableByteChannel channel;

    public ReadableByteChannelSource(ReadableByteChannel channel) {
        Intrinsics.checkNotNullParameter(channel, "channel");
        this.channel = channel;
    }

    @Override // kotlinx.io.RawSource
    public long readAtMostTo(Buffer sink, long byteCount) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (byteCount <= 0) {
            return 0L;
        }
        int iMin = (int) Math.min(byteCount, SieveCacheKt.NodeLinkMask);
        UnsafeBufferOperations unsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
        Segment segmentWritableSegment = sink.writableSegment(1);
        byte[] bArrDataAsByteArray = segmentWritableSegment.dataAsByteArray(false);
        int limit = segmentWritableSegment.getLimit();
        int i = this.channel.read(ByteBuffer.wrap(bArrDataAsByteArray, limit, Math.min(iMin, bArrDataAsByteArray.length - limit)));
        int iMax = Math.max(i, 0);
        if (iMax == 1) {
            segmentWritableSegment.writeBackData(bArrDataAsByteArray, iMax);
            segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + iMax);
            sink.setSizeMut(sink.getSizeMut() + ((long) iMax));
        } else {
            if (iMax < 0 || iMax > segmentWritableSegment.getRemainingCapacity()) {
                throw new IllegalStateException(("Invalid number of bytes written: " + iMax + ". Should be in 0.." + segmentWritableSegment.getRemainingCapacity()).toString());
            }
            if (iMax != 0) {
                segmentWritableSegment.writeBackData(bArrDataAsByteArray, iMax);
                segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + iMax);
                sink.setSizeMut(sink.getSizeMut() + ((long) iMax));
            } else if (SegmentKt.isEmpty(segmentWritableSegment)) {
                sink.recycleTail();
            }
        }
        return i;
    }

    @Override // kotlinx.io.RawSource, java.lang.AutoCloseable
    public void close() {
        this.channel.close();
    }

    public String toString() {
        return "ReadableByteChannelSource(" + this.channel + ')';
    }
}
