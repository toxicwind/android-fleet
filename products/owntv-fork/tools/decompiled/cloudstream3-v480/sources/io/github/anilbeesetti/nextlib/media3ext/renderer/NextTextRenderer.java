package io.github.anilbeesetti.nextlib.media3ext.renderer;

import android.os.Looper;
import androidx.media3.common.Format;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Clock;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.MediaClock;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.RendererConfiguration;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.SampleStream;
import androidx.media3.exoplayer.text.SubtitleDecoderFactory;
import androidx.media3.exoplayer.text.TextOutput;
import androidx.media3.exoplayer.text.TextRenderer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NextTextRenderer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J\b\u0010\u0014\u001a\u00020\u0010H\u0016J\t\u0010\u0015\u001a\u00020\u0010H\u0096\u0001J\u008d\u0001\u0010\u0016\u001a\u00020\u00102\u000b\u0010\u0017\u001a\u00070\u0018¢\u0006\u0002\b\u00192.\u0010\u001a\u001a*\u0012\t\u0012\u00070\u001c¢\u0006\u0002\b\u0019 \u001d*\u0014\u0012\u000b\b\u0001\u0012\u00070\u001c¢\u0006\u0002\b\u00190\u001b¢\u0006\u0002\b\u00190\u001b¢\u0006\u0002\b\u00192\u000b\u0010\u001e\u001a\u00070\u001f¢\u0006\u0002\b\u00192\u0006\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020\u00122\u000b\u0010&\u001a\u00070'¢\u0006\u0002\b\u0019H\u0096\u0001¢\u0006\u0002\u0010(J\u000e\u0010)\u001a\u00070*¢\u0006\u0002\b\u0019H\u0096\u0001J\u0010\u0010+\u001a\t\u0018\u00010,¢\u0006\u0002\b\u0019H\u0097\u0001J\u000e\u0010-\u001a\u00070.¢\u0006\u0002\b\u0019H\u0096\u0001J\t\u0010/\u001a\u00020\u0012H\u0096\u0001J\t\u00100\u001a\u000201H\u0096\u0001J\u0010\u00102\u001a\t\u0018\u00010\u001f¢\u0006\u0002\b\u0019H\u0097\u0001J\t\u00103\u001a\u000201H\u0096\u0001J\"\u00104\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u0002012\u000f\b\u0001\u0010\u001a\u001a\t\u0018\u000105¢\u0006\u0002\b\u0019H\u0096\u0001J\t\u00106\u001a\u00020\"H\u0096\u0001J+\u00107\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u0002012\u000b\u0010\u001a\u001a\u000708¢\u0006\u0002\b\u00192\u000b\u0010\u001e\u001a\u000709¢\u0006\u0002\b\u0019H\u0096\u0001J\t\u0010:\u001a\u00020\"H\u0096\u0001J\t\u0010;\u001a\u00020\"H\u0096\u0001J\t\u0010<\u001a\u00020\"H\u0096\u0001J\t\u0010=\u001a\u00020\u0010H\u0096\u0001Jh\u0010>\u001a\u00020\u00102.\u0010\u0017\u001a*\u0012\t\u0012\u00070\u001c¢\u0006\u0002\b\u0019 \u001d*\u0014\u0012\u000b\b\u0001\u0012\u00070\u001c¢\u0006\u0002\b\u00190\u001b¢\u0006\u0002\b\u00190\u001b¢\u0006\u0002\b\u00192\u000b\u0010\u001a\u001a\u00070\u001f¢\u0006\u0002\b\u00192\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u00122\u000b\u0010!\u001a\u00070'¢\u0006\u0002\b\u0019H\u0096\u0001¢\u0006\u0002\u0010?J\t\u0010@\u001a\u00020\u0010H\u0096\u0001J\u0019\u0010A\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\"H\u0096\u0001J\t\u0010B\u001a\u00020\u0010H\u0096\u0001J\u0016\u0010C\u001a\u00020\u00102\u000b\u0010\u0017\u001a\u00070D¢\u0006\u0002\b\u0019H\u0096\u0001J\t\u0010E\u001a\u00020\u0010H\u0096\u0001J\t\u0010F\u001a\u00020\u0010H\u0096\u0001R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006G"}, d2 = {"Lio/github/anilbeesetti/nextlib/media3ext/renderer/NextTextRenderer;", "Landroidx/media3/exoplayer/Renderer;", "Lio/github/anilbeesetti/nextlib/media3ext/renderer/OffsetRenderer;", "output", "Landroidx/media3/exoplayer/text/TextOutput;", "outputLooper", "Landroid/os/Looper;", "decoderFactory", "Landroidx/media3/exoplayer/text/SubtitleDecoderFactory;", "delegate", "Landroidx/media3/exoplayer/text/TextRenderer;", "<init>", "(Landroidx/media3/exoplayer/text/TextOutput;Landroid/os/Looper;Landroidx/media3/exoplayer/text/SubtitleDecoderFactory;Landroidx/media3/exoplayer/text/TextRenderer;)V", "getDelegate", "()Landroidx/media3/exoplayer/text/TextRenderer;", "render", "", "positionUs", "", "elapsedRealtimeUs", "release", "disable", "enable", "p0", "Landroidx/media3/exoplayer/RendererConfiguration;", "Lkotlin/jvm/internal/EnhancedNullability;", "p1", "", "Landroidx/media3/common/Format;", "kotlin.jvm.PlatformType", "p2", "Landroidx/media3/exoplayer/source/SampleStream;", "p3", "p4", "", "p5", "p6", "p7", "p8", "Landroidx/media3/exoplayer/source/MediaSource$MediaPeriodId;", "(Landroidx/media3/exoplayer/RendererConfiguration;[Landroidx/media3/common/Format;Landroidx/media3/exoplayer/source/SampleStream;JZZJJLandroidx/media3/exoplayer/source/MediaSource$MediaPeriodId;)V", "getCapabilities", "Landroidx/media3/exoplayer/RendererCapabilities;", "getMediaClock", "Landroidx/media3/exoplayer/MediaClock;", "getName", "", "getReadingPositionUs", "getState", "", "getStream", "getTrackType", "handleMessage", "", "hasReadStreamToEnd", "init", "Landroidx/media3/exoplayer/analytics/PlayerId;", "Landroidx/media3/common/util/Clock;", "isCurrentStreamFinal", "isEnded", "isReady", "maybeThrowStreamError", "replaceStream", "([Landroidx/media3/common/Format;Landroidx/media3/exoplayer/source/SampleStream;JJLandroidx/media3/exoplayer/source/MediaSource$MediaPeriodId;)V", "reset", "resetPosition", "setCurrentStreamFinal", "setTimeline", "Landroidx/media3/common/Timeline;", "start", "stop", "media3ext"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NextTextRenderer extends OffsetRenderer implements Renderer {
    private final TextRenderer delegate;

    @Override // androidx.media3.exoplayer.Renderer
    public void disable() {
        this.delegate.disable();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void enable(RendererConfiguration p0, Format[] p1, SampleStream p2, long p3, boolean p4, boolean p5, long p6, long p7, MediaSource.MediaPeriodId p8) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        Intrinsics.checkNotNullParameter(p2, "p2");
        Intrinsics.checkNotNullParameter(p8, "p8");
        this.delegate.enable(p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public /* synthetic */ void enableMayRenderStartOfStream() {
        Renderer.CC.$default$enableMayRenderStartOfStream(this);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public RendererCapabilities getCapabilities() {
        RendererCapabilities capabilities = this.delegate.getCapabilities();
        Intrinsics.checkNotNullExpressionValue(capabilities, "getCapabilities(...)");
        return capabilities;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public /* synthetic */ long getDurationToProgressUs(long j, long j2) {
        return Renderer.CC.$default$getDurationToProgressUs(this, j, j2);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public MediaClock getMediaClock() {
        return this.delegate.getMediaClock();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public String getName() {
        String name = this.delegate.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        return name;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public long getReadingPositionUs() {
        return this.delegate.getReadingPositionUs();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public int getState() {
        return this.delegate.getState();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public SampleStream getStream() {
        return this.delegate.getStream();
    }

    @Override // androidx.media3.exoplayer.Renderer, androidx.media3.exoplayer.RendererCapabilities
    public int getTrackType() {
        return this.delegate.getTrackType();
    }

    @Override // androidx.media3.exoplayer.PlayerMessage.Target
    public void handleMessage(int p0, Object p1) {
        this.delegate.handleMessage(p0, p1);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean hasReadStreamToEnd() {
        return this.delegate.hasReadStreamToEnd();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void init(int p0, PlayerId p1, Clock p2) {
        Intrinsics.checkNotNullParameter(p1, "p1");
        Intrinsics.checkNotNullParameter(p2, "p2");
        this.delegate.init(p0, p1, p2);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isCurrentStreamFinal() {
        return this.delegate.isCurrentStreamFinal();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isEnded() {
        return this.delegate.isEnded();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isReady() {
        return this.delegate.isReady();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void maybeThrowStreamError() {
        this.delegate.maybeThrowStreamError();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void replaceStream(Format[] p0, SampleStream p1, long p2, long p3, MediaSource.MediaPeriodId p4) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        Intrinsics.checkNotNullParameter(p4, "p4");
        this.delegate.replaceStream(p0, p1, p2, p3, p4);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void reset() {
        this.delegate.reset();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void resetPosition(long p0, boolean p1) {
        this.delegate.resetPosition(p0, p1);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void setCurrentStreamFinal() {
        this.delegate.setCurrentStreamFinal();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public /* synthetic */ void setPlaybackSpeed(float f, float f2) throws ExoPlaybackException {
        Renderer.CC.$default$setPlaybackSpeed(this, f, f2);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void setTimeline(Timeline p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.delegate.setTimeline(p0);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void start() {
        this.delegate.start();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void stop() {
        this.delegate.stop();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public /* synthetic */ boolean supportsResetPositionWithoutKeyFrameReset(long j) {
        return Renderer.CC.$default$supportsResetPositionWithoutKeyFrameReset(this, j);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ NextTextRenderer(TextOutput textOutput, Looper looper, SubtitleDecoderFactory DEFAULT, TextRenderer textRenderer, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 4) != 0) {
            DEFAULT = SubtitleDecoderFactory.DEFAULT;
            Intrinsics.checkNotNullExpressionValue(DEFAULT, "DEFAULT");
        }
        this(textOutput, looper, DEFAULT, (i & 8) != 0 ? new TextRenderer(textOutput, looper, DEFAULT) : textRenderer);
    }

    public final TextRenderer getDelegate() {
        return this.delegate;
    }

    public NextTextRenderer(TextOutput output, Looper looper, SubtitleDecoderFactory decoderFactory, TextRenderer delegate) {
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(decoderFactory, "decoderFactory");
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.delegate = delegate;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void render(long positionUs, long elapsedRealtimeUs) {
        this.delegate.render(getOffsetAdjustedPositionUs(positionUs), elapsedRealtimeUs);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void release() {
        this.delegate.release();
    }
}
