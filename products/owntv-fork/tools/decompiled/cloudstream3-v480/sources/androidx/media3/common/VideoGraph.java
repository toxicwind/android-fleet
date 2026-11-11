package androidx.media3.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.Surface;
import androidx.media3.common.util.TimestampIterator;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes4.dex */
public interface VideoGraph {

    public interface Factory {
        VideoGraph create(Context context, ColorInfo colorInfo, DebugViewProvider debugViewProvider, Listener listener, Executor executor, long j, boolean z);

        boolean supportsMultipleInputs();
    }

    public interface Listener {

        /* JADX INFO: renamed from: androidx.media3.common.VideoGraph$Listener$-CC, reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$onEnded(Listener _this, long j) {
            }

            public static void $default$onError(Listener _this, VideoFrameProcessingException videoFrameProcessingException) {
            }

            public static void $default$onOutputFrameAvailableForRendering(Listener _this, long j, boolean z) {
            }

            public static void $default$onOutputFrameRateChanged(Listener _this, float f) {
            }

            public static void $default$onOutputSizeChanged(Listener _this, int i, int i2) {
            }
        }

        void onEnded(long j);

        void onError(VideoFrameProcessingException videoFrameProcessingException);

        void onOutputFrameAvailableForRendering(long j, boolean z);

        void onOutputFrameRateChanged(float f);

        void onOutputSizeChanged(int i, int i2);
    }

    void flush();

    Surface getInputSurface(int i);

    int getPendingInputFrameCount(int i);

    boolean hasProducedFrameWithTimestampZero();

    void initialize() throws VideoFrameProcessingException;

    boolean queueInputBitmap(int i, Bitmap bitmap, TimestampIterator timestampIterator);

    boolean queueInputTexture(int i, int i2, long j);

    void redraw();

    void registerInput(int i) throws VideoFrameProcessingException;

    boolean registerInputFrame(int i);

    void registerInputStream(int i, int i2, Format format, List<Effect> list, long j);

    void release();

    void renderOutputFrame(long j);

    void setCompositionEffects(List<Effect> list);

    void setCompositorSettings(VideoCompositorSettings videoCompositorSettings);

    void setOnInputFrameProcessedListener(int i, OnInputFrameProcessedListener onInputFrameProcessedListener);

    void setOnInputSurfaceReadyListener(int i, Runnable runnable);

    void setOutputSurfaceInfo(SurfaceInfo surfaceInfo);

    void signalEndOfInput(int i);
}
