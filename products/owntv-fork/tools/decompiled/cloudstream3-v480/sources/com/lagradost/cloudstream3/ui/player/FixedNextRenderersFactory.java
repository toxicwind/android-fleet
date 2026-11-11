package com.lagradost.cloudstream3.ui.player;

import android.content.Context;
import android.os.Looper;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.text.TextOutput;
import androidx.media3.exoplayer.text.TextRenderer;
import io.github.anilbeesetti.nextlib.media3ext.ffdecoder.NextRenderersFactory;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FixedNextRenderersFactory.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J@\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0016\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u000fj\b\u0012\u0004\u0012\u00020\u0010`\u0011H\u0014¨\u0006\u0012"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/FixedNextRenderersFactory;", "Lio/github/anilbeesetti/nextlib/media3ext/ffdecoder/NextRenderersFactory;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "buildTextRenderers", "", "output", "Landroidx/media3/exoplayer/text/TextOutput;", "outputLooper", "Landroid/os/Looper;", "extensionRendererMode", "", "out", "Ljava/util/ArrayList;", "Landroidx/media3/exoplayer/Renderer;", "Lkotlin/collections/ArrayList;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class FixedNextRenderersFactory extends NextRenderersFactory {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FixedNextRenderersFactory(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // io.github.anilbeesetti.nextlib.media3ext.ffdecoder.NextRenderersFactory, androidx.media3.exoplayer.DefaultRenderersFactory
    protected void buildTextRenderers(Context context, TextOutput output, Looper outputLooper, int extensionRendererMode, ArrayList<Renderer> out) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(outputLooper, "outputLooper");
        Intrinsics.checkNotNullParameter(out, "out");
        out.add(new TextRenderer(output, outputLooper));
    }
}
