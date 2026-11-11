package io.github.anilbeesetti.nextlib.media3ext.renderer;

import androidx.media3.exoplayer.ExoPlayer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: NextTextRenderer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0006\"(\u0010\u0002\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018G@GX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\"(\u0010\t\u001a\u00020\b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\b8G@GX\u0086\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {ES6Iterator.VALUE_PROPERTY, "", "subtitleDelayMilliseconds", "Landroidx/media3/exoplayer/ExoPlayer;", "getSubtitleDelayMilliseconds", "(Landroidx/media3/exoplayer/ExoPlayer;)J", "setSubtitleDelayMilliseconds", "(Landroidx/media3/exoplayer/ExoPlayer;J)V", "", "subtitleSpeed", "getSubtitleSpeed", "(Landroidx/media3/exoplayer/ExoPlayer;)F", "setSubtitleSpeed", "(Landroidx/media3/exoplayer/ExoPlayer;F)V", "media3ext"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class NextTextRendererKt {
    public static final long getSubtitleDelayMilliseconds(ExoPlayer exoPlayer) {
        Intrinsics.checkNotNullParameter(exoPlayer, "<this>");
        OffsetRenderer offsetRenderer = OffsetRendererKt.getOffsetRenderer(exoPlayer, 3);
        if (offsetRenderer == null) {
            return 0L;
        }
        return offsetRenderer.getSyncOffsetMilliseconds();
    }

    public static final void setSubtitleDelayMilliseconds(ExoPlayer exoPlayer, long j) {
        Intrinsics.checkNotNullParameter(exoPlayer, "<this>");
        OffsetRenderer offsetRenderer = OffsetRendererKt.getOffsetRenderer(exoPlayer, 3);
        if (offsetRenderer == null) {
            return;
        }
        offsetRenderer.setSyncOffsetMilliseconds(j);
    }

    public static final float getSubtitleSpeed(ExoPlayer exoPlayer) {
        Intrinsics.checkNotNullParameter(exoPlayer, "<this>");
        OffsetRenderer offsetRenderer = OffsetRendererKt.getOffsetRenderer(exoPlayer, 3);
        if (offsetRenderer == null) {
            return 1.0f;
        }
        return offsetRenderer.getSyncSpeedMultiplier();
    }

    public static final void setSubtitleSpeed(ExoPlayer exoPlayer, float f) {
        Intrinsics.checkNotNullParameter(exoPlayer, "<this>");
        OffsetRenderer offsetRenderer = OffsetRendererKt.getOffsetRenderer(exoPlayer, 3);
        if (offsetRenderer == null) {
            return;
        }
        offsetRenderer.setSyncSpeedMultiplier(f);
    }
}
