package com.lagradost.cloudstream3.ui.player;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PreviewGenerator.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\u001a\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\u001a\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0004*\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"MAX_LOD", "", "MIN_LOD", "rescale", "Landroid/graphics/Bitmap;", "image", "params", "Lcom/lagradost/cloudstream3/ui/player/ImageParams;", "Landroid/media/MediaMetadataRetriever;", "timeUs", "", "app"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class PreviewGeneratorKt {
    public static final int MAX_LOD = 6;
    public static final int MIN_LOD = 3;

    private static final Bitmap rescale(Bitmap bitmap, ImageParams imageParams) {
        if (bitmap.getWidth() <= imageParams.getWidth() && bitmap.getHeight() <= imageParams.getHeight()) {
            return bitmap;
        }
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, imageParams.getWidth(), imageParams.getHeight(), true);
        if (!Intrinsics.areEqual(bitmapCreateScaledBitmap, bitmap)) {
            bitmap.recycle();
        }
        return bitmapCreateScaledBitmap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Bitmap image(MediaMetadataRetriever mediaMetadataRetriever, long j, ImageParams imageParams) {
        if (Build.VERSION.SDK_INT >= 27) {
            return mediaMetadataRetriever.getScaledFrameAtTime(j, 2, imageParams.getWidth(), imageParams.getHeight());
        }
        Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime(j);
        if (frameAtTime == null) {
            return null;
        }
        return rescale(frameAtTime, imageParams);
    }
}
