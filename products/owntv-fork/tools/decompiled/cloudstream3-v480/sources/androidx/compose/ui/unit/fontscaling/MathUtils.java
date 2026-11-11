package androidx.compose.ui.unit.fontscaling;

import androidx.media3.exoplayer.upstream.CmcdData;
import kotlin.Metadata;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: MathUtils.android.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\r\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005J\u001e\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005J.\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005¨\u0006\u0012"}, d2 = {"Landroidx/compose/ui/unit/fontscaling/MathUtils;", "", "<init>", "()V", "lerp", "", "start", "stop", "amount", "lerpInv", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", ES6Iterator.VALUE_PROPERTY, "constrainedMap", "rangeMin", "rangeMax", "valueMin", "valueMax", "ui-unit"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class MathUtils {
    public static final int $stable = 0;
    public static final MathUtils INSTANCE = new MathUtils();

    public final float lerp(float start, float stop, float amount) {
        return start + ((stop - start) * amount);
    }

    public final float lerpInv(float a, float b, float value) {
        if (a == b) {
            return 0.0f;
        }
        return (value - a) / (b - a);
    }

    private MathUtils() {
    }

    public final float constrainedMap(float rangeMin, float rangeMax, float valueMin, float valueMax, float value) {
        return lerp(rangeMin, rangeMax, Math.max(0.0f, Math.min(1.0f, lerpInv(valueMin, valueMax, value))));
    }
}
