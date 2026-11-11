package kotlinx.io.unsafe;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlinx.io.Segment;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: UnsafeBufferOperations.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH¦\u0080\u0004J*\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH¦\u0080\u0004J2\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tH¦\u0080\u0004J:\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\tH¦\u0080\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Lkotlinx/io/unsafe/SegmentWriteContext;", "", "setUnchecked", "", "segment", "Lkotlinx/io/Segment;", TypedValues.CycleType.S_WAVE_OFFSET, "", ES6Iterator.VALUE_PROPERTY, "", "b0", "b1", "b2", "b3", "kotlinx-io-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface SegmentWriteContext {
    void setUnchecked(Segment segment, int offset, byte value);

    void setUnchecked(Segment segment, int offset, byte b0, byte b1);

    void setUnchecked(Segment segment, int offset, byte b0, byte b1, byte b2);

    void setUnchecked(Segment segment, int offset, byte b0, byte b1, byte b2, byte b3);
}
