package androidx.media3.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.Surface;
import androidx.media3.common.util.TimestampIterator;
import com.google.common.collect.ImmutableList;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes4.dex */
public interface VideoFrameProcessor {
    public static final long DROP_OUTPUT_FRAME = -2;
    public static final int INPUT_TYPE_BITMAP = 2;
    public static final int INPUT_TYPE_SURFACE = 1;
    public static final int INPUT_TYPE_SURFACE_AUTOMATIC_FRAME_REGISTRATION = 4;
    public static final int INPUT_TYPE_TEXTURE_ID = 3;
    public static final ImmutableList<Effect> REDRAW = ImmutableList.of(new Effect() { // from class: androidx.media3.common.VideoFrameProcessor.1
        @Override // androidx.media3.common.Effect
        public /* synthetic */ long getDurationAfterEffectApplied(long j) {
            return Effect.CC.$default$getDurationAfterEffectApplied(this, j);
        }
    });

    @Deprecated
    public static final long RENDER_OUTPUT_FRAME_IMMEDIATELY = -1;
    public static final long RENDER_OUTPUT_FRAME_WITH_PRESENTATION_TIME = -3;

    public interface Factory {
        VideoFrameProcessor create(Context context, DebugViewProvider debugViewProvider, ColorInfo colorInfo, boolean z, Executor executor, Listener listener) throws VideoFrameProcessingException;
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface InputType {
    }

    public interface Listener {

        /* JADX INFO: renamed from: androidx.media3.common.VideoFrameProcessor$Listener$-CC, reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$onEnded(Listener _this) {
            }

            public static void $default$onError(Listener _this, VideoFrameProcessingException videoFrameProcessingException) {
            }

            public static void $default$onInputStreamRegistered(Listener _this, int i, Format format, List list) {
            }

            public static void $default$onOutputFrameAvailableForRendering(Listener _this, long j, boolean z) {
            }

            public static void $default$onOutputFrameRateChanged(Listener _this, float f) {
            }

            public static void $default$onOutputSizeChanged(Listener _this, int i, int i2) {
            }
        }

        void onEnded();

        void onError(VideoFrameProcessingException videoFrameProcessingException);

        void onInputStreamRegistered(int i, Format format, List<Effect> list);

        void onOutputFrameAvailableForRendering(long j, boolean z);

        void onOutputFrameRateChanged(float f);

        void onOutputSizeChanged(int i, int i2);
    }

    void flush();

    Surface getInputSurface();

    int getPendingInputFrameCount();

    boolean queueInputBitmap(Bitmap bitmap, TimestampIterator timestampIterator);

    boolean queueInputTexture(int i, long j);

    void redraw();

    boolean registerInputFrame();

    void registerInputStream(int i, Format format, List<Effect> list, long j);

    void release();

    void renderOutputFrame(long j);

    void setOnInputFrameProcessedListener(OnInputFrameProcessedListener onInputFrameProcessedListener);

    void setOnInputSurfaceReadyListener(Runnable runnable);

    void setOutputSurfaceInfo(SurfaceInfo surfaceInfo);

    void signalEndOfInput();
}
