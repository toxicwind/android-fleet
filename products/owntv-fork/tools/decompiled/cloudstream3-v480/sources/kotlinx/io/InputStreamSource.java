package kotlinx.io;

import androidx.tvprovider.media.tv.TvContractCompat;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.unsafe.UnsafeBufferOperations;

/* JADX INFO: compiled from: JvmCore.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0012\u0018\u00002\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H\u0096\u0080\u0004J\b\u0010\u000b\u001a\u00020\fH\u0016J\n\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lkotlinx/io/InputStreamSource;", "Lkotlinx/io/RawSource;", TvContractCompat.PARAM_INPUT, "Ljava/io/InputStream;", "<init>", "(Ljava/io/InputStream;)V", "readAtMostTo", "", "sink", "Lkotlinx/io/Buffer;", "byteCount", "close", "", "toString", "", "kotlinx-io-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
class InputStreamSource implements RawSource {
    private final InputStream input;

    public InputStreamSource(InputStream input) {
        Intrinsics.checkNotNullParameter(input, "input");
        this.input = input;
    }

    @Override // kotlinx.io.RawSource
    public long readAtMostTo(Buffer sink, long byteCount) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (byteCount == 0) {
            return 0L;
        }
        if (byteCount >= 0) {
            try {
                UnsafeBufferOperations unsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
                Segment segmentWritableSegment = sink.writableSegment(1);
                int i = 0;
                byte[] bArrDataAsByteArray = segmentWritableSegment.dataAsByteArray(false);
                int limit = segmentWritableSegment.getLimit();
                long j = this.input.read(bArrDataAsByteArray, limit, (int) Math.min(byteCount, bArrDataAsByteArray.length - limit));
                if (j != -1) {
                    i = (int) j;
                }
                if (i == 1) {
                    segmentWritableSegment.writeBackData(bArrDataAsByteArray, i);
                    segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + i);
                    sink.setSizeMut(sink.getSizeMut() + ((long) i));
                    return j;
                }
                if (i < 0 || i > segmentWritableSegment.getRemainingCapacity()) {
                    throw new IllegalStateException(("Invalid number of bytes written: " + i + ". Should be in 0.." + segmentWritableSegment.getRemainingCapacity()).toString());
                }
                if (i != 0) {
                    segmentWritableSegment.writeBackData(bArrDataAsByteArray, i);
                    segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + i);
                    sink.setSizeMut(sink.getSizeMut() + ((long) i));
                    return j;
                }
                if (SegmentKt.isEmpty(segmentWritableSegment)) {
                    sink.recycleTail();
                }
                return j;
            } catch (AssertionError e) {
                if (JvmCoreKt.isAndroidGetsocknameError(e)) {
                    throw new IOException(e);
                }
                throw e;
            }
        }
        throw new IllegalArgumentException(("byteCount (" + byteCount + ") < 0").toString());
    }

    @Override // kotlinx.io.RawSource, java.lang.AutoCloseable
    public void close() throws IOException {
        this.input.close();
    }

    public String toString() {
        return "RawSource(" + this.input + ')';
    }
}
