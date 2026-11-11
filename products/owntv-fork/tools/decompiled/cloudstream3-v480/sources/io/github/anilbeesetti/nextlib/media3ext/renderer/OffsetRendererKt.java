package io.github.anilbeesetti.nextlib.media3ext.renderer;

import androidx.media3.exoplayer.ExoPlayer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OffsetRenderer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0016\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0001¨\u0006\u0005"}, d2 = {"getOffsetRenderer", "Lio/github/anilbeesetti/nextlib/media3ext/renderer/OffsetRenderer;", "Landroidx/media3/exoplayer/ExoPlayer;", "trackType", "", "media3ext"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class OffsetRendererKt {
    public static final OffsetRenderer getOffsetRenderer(ExoPlayer exoPlayer, int i) {
        Intrinsics.checkNotNullParameter(exoPlayer, "<this>");
        int rendererCount = exoPlayer.getRendererCount();
        for (int i2 = 0; i2 < rendererCount; i2++) {
            if (exoPlayer.getRendererType(i2) == i) {
                Object renderer = exoPlayer.getRenderer(i2);
                if (renderer instanceof OffsetRenderer) {
                    return (OffsetRenderer) renderer;
                }
                return null;
            }
        }
        return null;
    }
}
