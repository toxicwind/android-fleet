package androidx.compose.ui.text.input;

import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;

/* JADX INFO: compiled from: EditingBuffer.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"updateRangeAfterDelete", "Landroidx/compose/ui/text/TextRange;", TypedValues.AttributesType.S_TARGET, "deleted", "updateRangeAfterDelete-pWDy79M", "(JJ)J", "ui-text"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class EditingBufferKt {
    /* JADX INFO: renamed from: updateRangeAfterDelete-pWDy79M, reason: not valid java name */
    public static final long m6509updateRangeAfterDeletepWDy79M(long j, long j2) {
        int iM6340getLengthimpl;
        int iM6342getMinimpl = TextRange.m6342getMinimpl(j);
        int iM6341getMaximpl = TextRange.m6341getMaximpl(j);
        if (TextRange.m6346intersects5zctL8(j2, j)) {
            if (TextRange.m6334contains5zctL8(j2, j)) {
                iM6342getMinimpl = TextRange.m6342getMinimpl(j2);
                iM6341getMaximpl = iM6342getMinimpl;
            } else {
                if (TextRange.m6334contains5zctL8(j, j2)) {
                    iM6340getLengthimpl = TextRange.m6340getLengthimpl(j2);
                } else if (TextRange.m6335containsimpl(j2, iM6342getMinimpl)) {
                    iM6342getMinimpl = TextRange.m6342getMinimpl(j2);
                    iM6340getLengthimpl = TextRange.m6340getLengthimpl(j2);
                } else {
                    iM6341getMaximpl = TextRange.m6342getMinimpl(j2);
                }
                iM6341getMaximpl -= iM6340getLengthimpl;
            }
        } else if (iM6341getMaximpl > TextRange.m6342getMinimpl(j2)) {
            iM6342getMinimpl -= TextRange.m6340getLengthimpl(j2);
            iM6340getLengthimpl = TextRange.m6340getLengthimpl(j2);
            iM6341getMaximpl -= iM6340getLengthimpl;
        }
        return TextRangeKt.TextRange(iM6342getMinimpl, iM6341getMaximpl);
    }
}
