package androidx.media3.common;

import android.util.Pair;

/* JADX INFO: loaded from: classes4.dex */
public interface OverlaySettings {
    public static final float DEFAULT_ALPHA_SCALE = 1.0f;
    public static final Pair<Float, Float> DEFAULT_BACKGROUND_FRAME_ANCHOR;
    public static final float DEFAULT_HDR_LUMINANCE_MULTIPLIER = 1.0f;
    public static final Pair<Float, Float> DEFAULT_OVERLAY_FRAME_ANCHOR;
    public static final float DEFAULT_ROTATION_DEGREES = 0.0f;
    public static final Pair<Float, Float> DEFAULT_SCALE;

    float getAlphaScale();

    Pair<Float, Float> getBackgroundFrameAnchor();

    float getHdrLuminanceMultiplier();

    Pair<Float, Float> getOverlayFrameAnchor();

    float getRotationDegrees();

    Pair<Float, Float> getScale();

    static {
        Float fValueOf = Float.valueOf(0.0f);
        DEFAULT_BACKGROUND_FRAME_ANCHOR = Pair.create(fValueOf, fValueOf);
        DEFAULT_OVERLAY_FRAME_ANCHOR = Pair.create(fValueOf, fValueOf);
        Float fValueOf2 = Float.valueOf(1.0f);
        DEFAULT_SCALE = Pair.create(fValueOf2, fValueOf2);
    }

    /* JADX INFO: renamed from: androidx.media3.common.OverlaySettings$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static float $default$getAlphaScale(OverlaySettings _this) {
            return 1.0f;
        }

        public static float $default$getHdrLuminanceMultiplier(OverlaySettings _this) {
            return 1.0f;
        }

        public static float $default$getRotationDegrees(OverlaySettings _this) {
            return 0.0f;
        }
    }
}
