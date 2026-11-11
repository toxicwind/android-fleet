package com.lagradost.cloudstream3.ui.player;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import androidx.preference.PreferenceManager;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.ui.settings.Globals;
import kotlin.Metadata;

/* JADX INFO: compiled from: PreviewGenerator.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\bf\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cJ\b\u0010\u0002\u001a\u00020\u0003H&J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&R\u0018\u0010\n\u001a\u00020\u000bX¦\u000e¢\u0006\f\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0010\u001a\u00020\u0011X¦\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0016\u001a\u00020\u0017X¦\u000e¢\u0006\f\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006\u001dÀ\u0006\u0003"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/IPreviewGenerator;", "", "hasPreview", "", "getPreviewImage", "Landroid/graphics/Bitmap;", "fraction", "", "release", "", "params", "Lcom/lagradost/cloudstream3/ui/player/ImageParams;", "getParams", "()Lcom/lagradost/cloudstream3/ui/player/ImageParams;", "setParams", "(Lcom/lagradost/cloudstream3/ui/player/ImageParams;)V", "durationMs", "", "getDurationMs", "()J", "setDurationMs", "(J)V", "loadedImages", "", "getLoadedImages", "()I", "setLoadedImages", "(I)V", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface IPreviewGenerator {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    long getDurationMs();

    int getLoadedImages();

    ImageParams getParams();

    Bitmap getPreviewImage(float fraction);

    boolean hasPreview();

    void release();

    void setDurationMs(long j);

    void setLoadedImages(int i);

    void setParams(ImageParams imageParams);

    /* JADX INFO: compiled from: PreviewGenerator.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u0006\u0010\u0006\u001a\u00020\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/IPreviewGenerator$Companion;", "", "<init>", "()V", "new", "Lcom/lagradost/cloudstream3/ui/player/IPreviewGenerator;", "empty", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        /* JADX INFO: renamed from: new, reason: not valid java name */
        public final IPreviewGenerator m8511new() {
            SharedPreferences defaultSharedPreferences;
            Context context = CloudStreamApp.INSTANCE.getContext();
            boolean z = false;
            if (context != null && (defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)) != null && !defaultSharedPreferences.getBoolean(context.getString(R.string.preview_seekbar_key), true)) {
                z = true;
            }
            if (Globals.INSTANCE.isLayout(2) || z) {
                return empty();
            }
            return new PreviewGenerator();
        }

        public final IPreviewGenerator empty() {
            return new NoPreviewGenerator();
        }
    }
}
