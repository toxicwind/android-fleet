package com.lagradost.cloudstream3.ui.player;

import android.graphics.Bitmap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: PreviewGenerator.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016R$\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u001aX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e¨\u0006\u001f"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/NoPreviewGenerator;", "Lcom/lagradost/cloudstream3/ui/player/IPreviewGenerator;", "<init>", "()V", "hasPreview", "", "getPreviewImage", "Landroid/graphics/Bitmap;", "fraction", "", "release", "", ES6Iterator.VALUE_PROPERTY, "Lcom/lagradost/cloudstream3/ui/player/ImageParams;", "params", "getParams", "()Lcom/lagradost/cloudstream3/ui/player/ImageParams;", "setParams", "(Lcom/lagradost/cloudstream3/ui/player/ImageParams;)V", "durationMs", "", "getDurationMs", "()J", "setDurationMs", "(J)V", "loadedImages", "", "getLoadedImages", "()I", "setLoadedImages", "(I)V", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class NoPreviewGenerator implements IPreviewGenerator {
    private long durationMs;
    private int loadedImages;

    @Override // com.lagradost.cloudstream3.ui.player.IPreviewGenerator
    public Bitmap getPreviewImage(float fraction) {
        return null;
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPreviewGenerator
    public boolean hasPreview() {
        return false;
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPreviewGenerator
    public void release() {
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPreviewGenerator
    public void setParams(ImageParams value) {
        Intrinsics.checkNotNullParameter(value, "value");
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPreviewGenerator
    public ImageParams getParams() {
        return new ImageParams(0, 0);
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPreviewGenerator
    public long getDurationMs() {
        return this.durationMs;
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPreviewGenerator
    public void setDurationMs(long j) {
        this.durationMs = j;
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPreviewGenerator
    public int getLoadedImages() {
        return this.loadedImages;
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPreviewGenerator
    public void setLoadedImages(int i) {
        this.loadedImages = i;
    }
}
