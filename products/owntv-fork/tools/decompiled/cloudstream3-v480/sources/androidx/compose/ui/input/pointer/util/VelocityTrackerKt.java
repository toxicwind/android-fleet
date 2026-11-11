package androidx.compose.ui.input.pointer.util;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.HistoricalChange;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.ranges.RangesKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: VelocityTracker.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\\\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0014\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\u001a1\u0010\u0004\u001a\u00020\u0005*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002¢\u0006\u0002\u0010\r\u001a\u0012\u0010\u000e\u001a\u00020\u0005*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011\u001a!\u0010\u000e\u001a\u00020\u0005*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0004\b\u0014\u0010\u0015\u001a#\u0010\u0016\u001a\u00020\u0005*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u0017\u0010\u0015\u001a#\u0010\u0018\u001a\u00020\u0005*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u0019\u0010\u0015\u001a2\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\u00012\b\b\u0002\u0010!\u001a\u00020\u001cH\u0000\u001a(\u0010\"\u001a\u00020\f2\u0006\u0010#\u001a\u00020\u001c2\u0006\u0010\t\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u00012\u0006\u0010$\u001a\u00020%H\u0002\u001a\u0011\u0010&\u001a\u00020\f2\u0006\u0010'\u001a\u00020\fH\u0082\b\u001a\u0014\u0010)\u001a\u00020\f*\u00020\u001c2\u0006\u0010*\u001a\u00020\u001cH\u0002\u001a\r\u0010+\u001a\u00020\f*\u00020\u001cH\u0082\b\u001a(\u0010,\u001a\f\u0012\b\u0012\u00060\u001cj\u0002`-0\u00062\u0006\u0010.\u001a\u00020\u00012\u0006\u0010/\u001a\u00020\u0001H\u0082\b¢\u0006\u0002\u00100\u001a,\u00101\u001a\u00020\f*\f\u0012\u0004\u0012\u00020\u001c0\u0006j\u0002`22\u0006\u00103\u001a\u00020\u00012\u0006\u00104\u001a\u00020\u0001H\u0082\n¢\u0006\u0002\u00105\u001a4\u0010\u0004\u001a\u00020\u0005*\f\u0012\u0004\u0012\u00020\u001c0\u0006j\u0002`22\u0006\u00103\u001a\u00020\u00012\u0006\u00104\u001a\u00020\u00012\u0006\u00106\u001a\u00020\fH\u0082\n¢\u0006\u0002\u00107\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001a\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000\"$\u00108\u001a\u00020%8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b9\u0010:\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>\"$\u0010?\u001a\u00020%8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b@\u0010:\u001a\u0004\bA\u0010<\"\u0004\bB\u0010>*\f\b\u0002\u0010(\"\u00020\u001c2\u00020\u001c*\u0018\b\u0002\u0010,\"\b\u0012\u0004\u0012\u00020\u001c0\u00062\b\u0012\u0004\u0012\u00020\u001c0\u0006¨\u0006C"}, d2 = {"AssumePointerMoveStoppedMilliseconds", "", "HistorySize", "HorizonMilliseconds", "set", "", "", "Landroidx/compose/ui/input/pointer/util/DataPointAtTime;", "index", "time", "", "dataPoint", "", "([Landroidx/compose/ui/input/pointer/util/DataPointAtTime;IJF)V", "addPointerInputChange", "Landroidx/compose/ui/input/pointer/util/VelocityTracker;", NotificationCompat.CATEGORY_EVENT, "Landroidx/compose/ui/input/pointer/PointerInputChange;", TypedValues.CycleType.S_WAVE_OFFSET, "Landroidx/compose/ui/geometry/Offset;", "addPointerInputChange-0AR0LA0", "(Landroidx/compose/ui/input/pointer/util/VelocityTracker;Landroidx/compose/ui/input/pointer/PointerInputChange;J)V", "addPointerInputChangeLegacy", "addPointerInputChangeLegacy-0AR0LA0", "addPointerInputChangeWithFix", "addPointerInputChangeWithFix-0AR0LA0", "DefaultWeight", "polyFitLeastSquares", "", "x", "y", "sampleCount", "degree", "coefficients", "calculateImpulseVelocity", "dataPoints", "isDataDifferential", "", "kineticEnergyToVelocity", "kineticEnergy", "Vector", TtmlNode.TEXT_EMPHASIS_MARK_DOT, CmcdData.OBJECT_TYPE_AUDIO_ONLY, "norm", "Matrix", "Landroidx/compose/ui/input/pointer/util/Vector;", "rows", "cols", "(II)[[F", "get", "Landroidx/compose/ui/input/pointer/util/Matrix;", "row", "col", "([[FII)F", ES6Iterator.VALUE_PROPERTY, "([[FIIF)V", "VelocityTrackerAddPointsFix", "getVelocityTrackerAddPointsFix$annotations", "()V", "getVelocityTrackerAddPointsFix", "()Z", "setVelocityTrackerAddPointsFix", "(Z)V", "VelocityTrackerStrategyUseImpulse", "getVelocityTrackerStrategyUseImpulse$annotations", "getVelocityTrackerStrategyUseImpulse", "setVelocityTrackerStrategyUseImpulse", "ui_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class VelocityTrackerKt {
    private static final int AssumePointerMoveStoppedMilliseconds = 40;
    private static final float DefaultWeight = 1.0f;
    private static final int HistorySize = 20;
    private static final int HorizonMilliseconds = 100;
    private static boolean VelocityTrackerAddPointsFix = true;
    private static boolean VelocityTrackerStrategyUseImpulse;

    public static /* synthetic */ void getVelocityTrackerAddPointsFix$annotations() {
    }

    public static /* synthetic */ void getVelocityTrackerStrategyUseImpulse$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void set(DataPointAtTime[] dataPointAtTimeArr, int i, long j, float f) {
        DataPointAtTime dataPointAtTime = dataPointAtTimeArr[i];
        if (dataPointAtTime == null) {
            dataPointAtTimeArr[i] = new DataPointAtTime(j, f);
        } else {
            dataPointAtTime.setTime(j);
            dataPointAtTime.setDataPoint(f);
        }
    }

    public static final void addPointerInputChange(VelocityTracker velocityTracker, PointerInputChange pointerInputChange) {
        m5554addPointerInputChange0AR0LA0(velocityTracker, pointerInputChange, Offset.INSTANCE.m3886getZeroF1C5BW0());
    }

    /* JADX INFO: renamed from: addPointerInputChange-0AR0LA0, reason: not valid java name */
    public static final void m5554addPointerInputChange0AR0LA0(VelocityTracker velocityTracker, PointerInputChange pointerInputChange, long j) {
        if (VelocityTrackerAddPointsFix) {
            m5556addPointerInputChangeWithFix0AR0LA0(velocityTracker, pointerInputChange, j);
        } else {
            m5555addPointerInputChangeLegacy0AR0LA0(velocityTracker, pointerInputChange, j);
        }
    }

    /* JADX INFO: renamed from: addPointerInputChangeLegacy-0AR0LA0, reason: not valid java name */
    private static final void m5555addPointerInputChangeLegacy0AR0LA0(VelocityTracker velocityTracker, PointerInputChange pointerInputChange, long j) {
        if (PointerEventKt.changedToDownIgnoreConsumed(pointerInputChange)) {
            velocityTracker.m5553setCurrentPointerPositionAccumulatork4lQ0M$ui_release(pointerInputChange.getPosition());
            velocityTracker.resetTracking();
        }
        long previousPosition = pointerInputChange.getPreviousPosition();
        List<HistoricalChange> historical = pointerInputChange.getHistorical();
        int size = historical.size();
        int i = 0;
        while (i < size) {
            HistoricalChange historicalChange = historical.get(i);
            long jM3874minusMKHz9U = Offset.m3874minusMKHz9U(historicalChange.getPosition(), previousPosition);
            long position = historicalChange.getPosition();
            velocityTracker.m5553setCurrentPointerPositionAccumulatork4lQ0M$ui_release(Offset.m3875plusMKHz9U(velocityTracker.getCurrentPointerPositionAccumulator(), jM3874minusMKHz9U));
            velocityTracker.m5549addPositionUv8p0NA(historicalChange.getUptimeMillis(), Offset.m3875plusMKHz9U(velocityTracker.getCurrentPointerPositionAccumulator(), j));
            i++;
            previousPosition = position;
        }
        velocityTracker.m5553setCurrentPointerPositionAccumulatork4lQ0M$ui_release(Offset.m3875plusMKHz9U(velocityTracker.getCurrentPointerPositionAccumulator(), Offset.m3874minusMKHz9U(pointerInputChange.getPosition(), previousPosition)));
        velocityTracker.m5549addPositionUv8p0NA(pointerInputChange.getUptimeMillis(), Offset.m3875plusMKHz9U(velocityTracker.getCurrentPointerPositionAccumulator(), j));
    }

    /* JADX INFO: renamed from: addPointerInputChangeWithFix-0AR0LA0, reason: not valid java name */
    private static final void m5556addPointerInputChangeWithFix0AR0LA0(VelocityTracker velocityTracker, PointerInputChange pointerInputChange, long j) {
        if (PointerEventKt.changedToDownIgnoreConsumed(pointerInputChange)) {
            velocityTracker.resetTracking();
        }
        if (!PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange)) {
            List<HistoricalChange> historical = pointerInputChange.getHistorical();
            int size = historical.size();
            for (int i = 0; i < size; i++) {
                HistoricalChange historicalChange = historical.get(i);
                velocityTracker.m5549addPositionUv8p0NA(historicalChange.getUptimeMillis(), Offset.m3875plusMKHz9U(historicalChange.getOriginalEventPosition(), j));
            }
            velocityTracker.m5549addPositionUv8p0NA(pointerInputChange.getUptimeMillis(), Offset.m3875plusMKHz9U(pointerInputChange.getOriginalEventPosition(), j));
        }
        if (PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange) && pointerInputChange.getUptimeMillis() - velocityTracker.getLastMoveEventTimeStamp() > 40) {
            velocityTracker.resetTracking();
        }
        velocityTracker.setLastMoveEventTimeStamp$ui_release(pointerInputChange.getUptimeMillis());
    }

    public static /* synthetic */ float[] polyFitLeastSquares$default(float[] fArr, float[] fArr2, int i, int i2, float[] fArr3, int i3, Object obj) {
        if ((i3 & 16) != 0) {
            fArr3 = new float[RangesKt.coerceAtLeast(i2 + 1, 0)];
        }
        return polyFitLeastSquares(fArr, fArr2, i, i2, fArr3);
    }

    public static final float[] polyFitLeastSquares(float[] fArr, float[] fArr2, int i, int i2, float[] fArr3) {
        int i3 = i2;
        if (i3 < 1) {
            InlineClassHelperKt.throwIllegalArgumentException("The degree must be at positive integer");
        }
        if (i == 0) {
            InlineClassHelperKt.throwIllegalArgumentException("At least one point must be provided");
        }
        if (i3 >= i) {
            i3 = i - 1;
        }
        int i4 = i3 + 1;
        float[][] fArr4 = new float[i4][];
        for (int i5 = 0; i5 < i4; i5++) {
            fArr4[i5] = new float[i];
        }
        for (int i6 = 0; i6 < i; i6++) {
            fArr4[0][i6] = 1.0f;
            for (int i7 = 1; i7 < i4; i7++) {
                fArr4[i7][i6] = fArr4[i7 - 1][i6] * fArr[i6];
            }
        }
        float[][] fArr5 = new float[i4][];
        for (int i8 = 0; i8 < i4; i8++) {
            fArr5[i8] = new float[i];
        }
        float[][] fArr6 = new float[i4][];
        for (int i9 = 0; i9 < i4; i9++) {
            fArr6[i9] = new float[i4];
        }
        int i10 = 0;
        while (i10 < i4) {
            float[] fArr7 = fArr5[i10];
            ArraysKt.copyInto(fArr4[i10], fArr7, 0, 0, i);
            for (int i11 = 0; i11 < i10; i11++) {
                float[] fArr8 = fArr5[i11];
                float fDot = dot(fArr7, fArr8);
                for (int i12 = 0; i12 < i; i12++) {
                    fArr7[i12] = fArr7[i12] - (fArr8[i12] * fDot);
                }
            }
            float fSqrt = (float) Math.sqrt(dot(fArr7, fArr7));
            if (fSqrt < 1.0E-6f) {
                fSqrt = 1.0E-6f;
            }
            float f = 1.0f / fSqrt;
            for (int i13 = 0; i13 < i; i13++) {
                fArr7[i13] = fArr7[i13] * f;
            }
            float[] fArr9 = fArr6[i10];
            int i14 = 0;
            while (i14 < i4) {
                fArr9[i14] = i14 < i10 ? 0.0f : dot(fArr7, fArr4[i14]);
                i14++;
            }
            i10++;
        }
        for (int i15 = i3; -1 < i15; i15--) {
            float fDot2 = dot(fArr5[i15], fArr2);
            float[] fArr10 = fArr6[i15];
            int i16 = i15 + 1;
            if (i16 <= i3) {
                int i17 = i3;
                while (true) {
                    fDot2 -= fArr10[i17] * fArr3[i17];
                    if (i17 != i16) {
                        i17--;
                    }
                }
            }
            fArr3[i15] = fDot2 / fArr10[i15];
        }
        return fArr3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calculateImpulseVelocity(float[] fArr, float[] fArr2, int i, boolean z) {
        int i2 = i - 1;
        float f = fArr2[i2];
        float fSignum = 0.0f;
        int i3 = i2;
        while (i3 > 0) {
            int i4 = i3 - 1;
            float f2 = fArr2[i4];
            if (f != f2) {
                float f3 = (z ? -fArr[i4] : fArr[i3] - fArr[i4]) / (f - f2);
                fSignum += (f3 - (Math.signum(fSignum) * ((float) Math.sqrt(2.0f * Math.abs(fSignum))))) * Math.abs(f3);
                if (i3 == i2) {
                    fSignum *= 0.5f;
                }
            }
            i3--;
            f = f2;
        }
        return Math.signum(fSignum) * ((float) Math.sqrt(2.0f * Math.abs(fSignum)));
    }

    private static final float kineticEnergyToVelocity(float f) {
        return Math.signum(f) * ((float) Math.sqrt(2.0f * Math.abs(f)));
    }

    private static final float dot(float[] fArr, float[] fArr2) {
        int length = fArr.length;
        float f = 0.0f;
        for (int i = 0; i < length; i++) {
            f += fArr[i] * fArr2[i];
        }
        return f;
    }

    private static final float norm(float[] fArr) {
        return (float) Math.sqrt(dot(fArr, fArr));
    }

    private static final float[][] Matrix(int i, int i2) {
        float[][] fArr = new float[i][];
        for (int i3 = 0; i3 < i; i3++) {
            fArr[i3] = new float[i2];
        }
        return fArr;
    }

    private static final float get(float[][] fArr, int i, int i2) {
        return fArr[i][i2];
    }

    private static final void set(float[][] fArr, int i, int i2, float f) {
        fArr[i][i2] = f;
    }

    public static final boolean getVelocityTrackerAddPointsFix() {
        return VelocityTrackerAddPointsFix;
    }

    public static final void setVelocityTrackerAddPointsFix(boolean z) {
        VelocityTrackerAddPointsFix = z;
    }

    public static final boolean getVelocityTrackerStrategyUseImpulse() {
        return VelocityTrackerStrategyUseImpulse;
    }

    public static final void setVelocityTrackerStrategyUseImpulse(boolean z) {
        VelocityTrackerStrategyUseImpulse = z;
    }
}
