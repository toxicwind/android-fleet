package androidx.compose.ui.platform;

import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: ViewConfiguration.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0012\u0010\b\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0005R\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0005R\u0014\u0010\u0013\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\rR\u0014\u0010\u0015\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\rR\u0014\u0010\u0017\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\rø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0019À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/platform/ViewConfiguration;", "", "longPressTimeoutMillis", "", "getLongPressTimeoutMillis", "()J", "doubleTapTimeoutMillis", "getDoubleTapTimeoutMillis", "doubleTapMinTimeMillis", "getDoubleTapMinTimeMillis", "touchSlop", "", "getTouchSlop", "()F", "handwritingSlop", "getHandwritingSlop", "minimumTouchTargetSize", "Landroidx/compose/ui/unit/DpSize;", "getMinimumTouchTargetSize-MYxV2XQ", "maximumFlingVelocity", "getMaximumFlingVelocity", "minimumFlingVelocity", "getMinimumFlingVelocity", "handwritingGestureLineMargin", "getHandwritingGestureLineMargin", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface ViewConfiguration {
    long getDoubleTapMinTimeMillis();

    long getDoubleTapTimeoutMillis();

    float getHandwritingGestureLineMargin();

    float getHandwritingSlop();

    long getLongPressTimeoutMillis();

    float getMaximumFlingVelocity();

    float getMinimumFlingVelocity();

    /* JADX INFO: renamed from: getMinimumTouchTargetSize-MYxV2XQ */
    long mo5797getMinimumTouchTargetSizeMYxV2XQ();

    float getTouchSlop();

    /* JADX INFO: renamed from: androidx.compose.ui.platform.ViewConfiguration$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: ViewConfiguration.kt */
    public final /* synthetic */ class CC {
        public static float $default$getHandwritingGestureLineMargin(ViewConfiguration _this) {
            return 16.0f;
        }

        public static float $default$getHandwritingSlop(ViewConfiguration _this) {
            return 2.0f;
        }

        public static float $default$getMaximumFlingVelocity(ViewConfiguration _this) {
            return Float.MAX_VALUE;
        }

        public static float $default$getMinimumFlingVelocity(ViewConfiguration _this) {
            return 0.0f;
        }

        /* JADX INFO: renamed from: $default$getMinimumTouchTargetSize-MYxV2XQ, reason: not valid java name */
        public static long m6092$default$getMinimumTouchTargetSizeMYxV2XQ(ViewConfiguration _this) {
            return DpKt.m6912DpSizeYgX7TsA(Dp.m6890constructorimpl(48.0f), Dp.m6890constructorimpl(48.0f));
        }
    }

    /* JADX INFO: compiled from: ViewConfiguration.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static float getHandwritingSlop(ViewConfiguration viewConfiguration) {
            return CC.$default$getHandwritingSlop(viewConfiguration);
        }

        @Deprecated
        /* JADX INFO: renamed from: getMinimumTouchTargetSize-MYxV2XQ, reason: not valid java name */
        public static long m6094getMinimumTouchTargetSizeMYxV2XQ(ViewConfiguration viewConfiguration) {
            return CC.m6092$default$getMinimumTouchTargetSizeMYxV2XQ(viewConfiguration);
        }

        @Deprecated
        public static float getMaximumFlingVelocity(ViewConfiguration viewConfiguration) {
            return CC.$default$getMaximumFlingVelocity(viewConfiguration);
        }

        @Deprecated
        public static float getMinimumFlingVelocity(ViewConfiguration viewConfiguration) {
            return CC.$default$getMinimumFlingVelocity(viewConfiguration);
        }

        @Deprecated
        public static float getHandwritingGestureLineMargin(ViewConfiguration viewConfiguration) {
            return CC.$default$getHandwritingGestureLineMargin(viewConfiguration);
        }
    }
}
