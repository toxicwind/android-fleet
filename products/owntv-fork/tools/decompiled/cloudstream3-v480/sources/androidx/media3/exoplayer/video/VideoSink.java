package androidx.media3.exoplayer.video;

import android.graphics.Bitmap;
import android.view.Surface;
import androidx.media3.common.Effect;
import androidx.media3.common.Format;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.TimestampIterator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes4.dex */
public interface VideoSink {
    public static final int INPUT_TYPE_BITMAP = 2;
    public static final int INPUT_TYPE_SURFACE = 1;
    public static final int RELEASE_FIRST_FRAME_IMMEDIATELY = 0;
    public static final int RELEASE_FIRST_FRAME_WHEN_PREVIOUS_STREAM_PROCESSED = 2;
    public static final int RELEASE_FIRST_FRAME_WHEN_STARTED = 1;

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface FirstFrameReleaseInstruction {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface InputType {
    }

    public interface Listener {
        public static final Listener NO_OP = new Listener() { // from class: androidx.media3.exoplayer.video.VideoSink.Listener.1
            @Override // androidx.media3.exoplayer.video.VideoSink.Listener
            public /* synthetic */ void onError(VideoSinkException videoSinkException) {
                CC.$default$onError(this, videoSinkException);
            }

            @Override // androidx.media3.exoplayer.video.VideoSink.Listener
            public /* synthetic */ void onFirstFrameRendered() {
                CC.$default$onFirstFrameRendered(this);
            }

            @Override // androidx.media3.exoplayer.video.VideoSink.Listener
            public /* synthetic */ void onFrameAvailableForRendering() {
                CC.$default$onFrameAvailableForRendering(this);
            }

            @Override // androidx.media3.exoplayer.video.VideoSink.Listener
            public /* synthetic */ void onFrameDropped() {
                CC.$default$onFrameDropped(this);
            }

            @Override // androidx.media3.exoplayer.video.VideoSink.Listener
            public /* synthetic */ void onVideoSizeChanged(VideoSize videoSize) {
                CC.$default$onVideoSizeChanged(this, videoSize);
            }
        };

        /* JADX INFO: renamed from: androidx.media3.exoplayer.video.VideoSink$Listener$-CC, reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$onError(Listener _this, VideoSinkException videoSinkException) {
            }

            public static void $default$onFirstFrameRendered(Listener _this) {
            }

            public static void $default$onFrameAvailableForRendering(Listener _this) {
            }

            public static void $default$onFrameDropped(Listener _this) {
            }

            public static void $default$onVideoSizeChanged(Listener _this, VideoSize videoSize) {
            }
        }

        void onError(VideoSinkException videoSinkException);

        void onFirstFrameRendered();

        void onFrameAvailableForRendering();

        void onFrameDropped();

        void onVideoSizeChanged(VideoSize videoSize);
    }

    public interface VideoFrameHandler {
        void render(long j);

        void skip();
    }

    void allowReleaseFirstFrameBeforeStarted();

    void clearOutputSurfaceInfo();

    void flush(boolean z);

    Surface getInputSurface();

    boolean handleInputBitmap(Bitmap bitmap, TimestampIterator timestampIterator);

    boolean handleInputFrame(long j, VideoFrameHandler videoFrameHandler);

    boolean initialize(Format format) throws VideoSinkException;

    boolean isEnded();

    boolean isInitialized();

    boolean isReady(boolean z);

    void join(boolean z);

    void onInputStreamChanged(int i, Format format, long j, int i2, List<Effect> list);

    void redraw();

    void release();

    void render(long j, long j2) throws VideoSinkException;

    void setBufferTimestampAdjustmentUs(long j);

    void setChangeFrameRateStrategy(int i);

    void setListener(Listener listener, Executor executor);

    void setOutputSurfaceInfo(Surface surface, Size size);

    void setPlaybackSpeed(float f);

    void setVideoEffects(List<Effect> list);

    void setVideoFrameMetadataListener(VideoFrameMetadataListener videoFrameMetadataListener);

    void signalEndOfCurrentInputStream();

    void signalEndOfInput();

    void startRendering();

    void stopRendering();

    public static final class VideoSinkException extends Exception {
        public final Format format;

        public VideoSinkException(Throwable th, Format format) {
            super(th);
            this.format = format;
        }
    }
}
