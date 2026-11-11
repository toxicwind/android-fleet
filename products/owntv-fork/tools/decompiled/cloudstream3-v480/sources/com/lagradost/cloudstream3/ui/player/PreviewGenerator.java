package com.lagradost.cloudstream3.ui.player;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.ExtractorLinkType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: PreviewGenerator.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\f\u001a\u00020\bH\u0016J\u0012\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u000e\u0010'\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\bJ\u0016\u0010)\u001a\u00020\u00122\u0006\u0010*\u001a\u00020+2\u0006\u0010(\u001a\u00020\bJ\u001e\u0010)\u001a\u00020\u00122\u0006\u0010,\u001a\u00020-2\u0006\u0010*\u001a\u00020.2\u0006\u0010(\u001a\u00020\bR\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\u00018BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR$\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0014@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R$\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020\u001b8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R$\u0010\"\u001a\u00020!2\u0006\u0010\u001a\u001a\u00020!8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&¨\u0006/"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/PreviewGenerator;", "Lcom/lagradost/cloudstream3/ui/player/IPreviewGenerator;", "<init>", "()V", "currentGenerator", "lastGenerator", "dummy", "isSameLength", "", "backupGenerator", "getBackupGenerator", "()Lcom/lagradost/cloudstream3/ui/player/IPreviewGenerator;", "hasPreview", "getPreviewImage", "Landroid/graphics/Bitmap;", "fraction", "", "release", "", ES6Iterator.VALUE_PROPERTY, "Lcom/lagradost/cloudstream3/ui/player/ImageParams;", "params", "getParams", "()Lcom/lagradost/cloudstream3/ui/player/ImageParams;", "setParams", "(Lcom/lagradost/cloudstream3/ui/player/ImageParams;)V", "_", "", "durationMs", "getDurationMs", "()J", "setDurationMs", "(J)V", "", "loadedImages", "getLoadedImages", "()I", "setLoadedImages", "(I)V", "clear", "keepCache", "load", "link", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "context", "Landroid/content/Context;", "Lcom/lagradost/cloudstream3/ui/player/ExtractorUri;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PreviewGenerator implements IPreviewGenerator {
    private IPreviewGenerator currentGenerator = new NoPreviewGenerator();
    private IPreviewGenerator lastGenerator = new NoPreviewGenerator();
    private final IPreviewGenerator dummy = new NoPreviewGenerator();
    private ImageParams params = ImageParams.INSTANCE.getDEFAULT();

    /* JADX INFO: compiled from: PreviewGenerator.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ExtractorLinkType.values().length];
            try {
                iArr[ExtractorLinkType.M3U8.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ExtractorLinkType.VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPreviewGenerator
    public void setDurationMs(long j) {
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPreviewGenerator
    public void setLoadedImages(int i) {
    }

    private final boolean isSameLength() {
        return Math.abs(this.currentGenerator.getDurationMs() - this.lastGenerator.getDurationMs()) < 10000;
    }

    private final IPreviewGenerator getBackupGenerator() {
        if (this.currentGenerator.getDurationMs() == 0 || isSameLength()) {
            return this.lastGenerator;
        }
        return this.dummy;
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPreviewGenerator
    public boolean hasPreview() {
        return this.currentGenerator.hasPreview() || getBackupGenerator().hasPreview();
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPreviewGenerator
    public Bitmap getPreviewImage(float fraction) {
        try {
            Bitmap previewImage = this.currentGenerator.getPreviewImage(fraction);
            return previewImage == null ? getBackupGenerator().getPreviewImage(fraction) : previewImage;
        } catch (Throwable th) {
            ArchComponentExtKt.logError(th);
            return null;
        }
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPreviewGenerator
    public void release() {
        this.lastGenerator.release();
        this.currentGenerator.release();
        this.lastGenerator = new NoPreviewGenerator();
        this.currentGenerator = new NoPreviewGenerator();
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPreviewGenerator
    public ImageParams getParams() {
        return this.params;
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPreviewGenerator
    public void setParams(ImageParams value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.params = value;
        this.lastGenerator.setParams(value);
        getBackupGenerator().setParams(value);
        this.currentGenerator.setParams(value);
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPreviewGenerator
    public long getDurationMs() {
        return this.currentGenerator.getDurationMs();
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPreviewGenerator
    public int getLoadedImages() {
        return this.currentGenerator.getLoadedImages();
    }

    public final void clear(boolean keepCache) {
        if (keepCache) {
            if (!isSameLength() || this.currentGenerator.getLoadedImages() >= this.lastGenerator.getLoadedImages() || this.lastGenerator.getDurationMs() == 0) {
                this.lastGenerator.release();
                this.lastGenerator = this.currentGenerator;
                return;
            } else {
                this.currentGenerator.release();
                return;
            }
        }
        this.lastGenerator.release();
        this.lastGenerator = new NoPreviewGenerator();
        this.currentGenerator.release();
    }

    public final void load(ExtractorLink link, boolean keepCache) {
        Intrinsics.checkNotNullParameter(link, "link");
        clear(keepCache);
        int i = WhenMappings.$EnumSwitchMapping$0[link.getType().ordinal()];
        if (i == 1) {
            M3u8PreviewGenerator m3u8PreviewGenerator = new M3u8PreviewGenerator(getParams());
            m3u8PreviewGenerator.load(link.getUrl(), link.getAllHeaders());
            this.currentGenerator = m3u8PreviewGenerator;
        } else if (i == 2) {
            Mp4PreviewGenerator mp4PreviewGenerator = new Mp4PreviewGenerator(getParams());
            mp4PreviewGenerator.load(link.getUrl(), link.getAllHeaders());
            this.currentGenerator = mp4PreviewGenerator;
        } else {
            Log.i("PreviewImg", "unsupported format for " + link);
        }
    }

    public final void load(Context context, ExtractorUri link, boolean keepCache) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(link, "link");
        clear(keepCache);
        Mp4PreviewGenerator mp4PreviewGenerator = new Mp4PreviewGenerator(getParams());
        mp4PreviewGenerator.load(keepCache, context, link.getUri());
        this.currentGenerator = mp4PreviewGenerator;
    }
}
