package com.lagradost.cloudstream3.ui.player;

import androidx.media3.common.Format;
import androidx.media3.exoplayer.text.SubtitleDecoderFactory;
import androidx.media3.extractor.text.SubtitleDecoder;
import java.lang.ref.WeakReference;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: CustomSubtitleDecoderFactory.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u0007H\u0016R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/CustomSubtitleDecoderFactory;", "Landroidx/media3/exoplayer/text/SubtitleDecoderFactory;", "<init>", "()V", "supportsFormat", "", "format", "Landroidx/media3/common/Format;", "latestDecoder", "Ljava/lang/ref/WeakReference;", "Lcom/lagradost/cloudstream3/ui/player/CustomDecoder;", "getSubtitleCues", "", "Lcom/lagradost/cloudstream3/ui/player/SubtitleCue;", "createDecoder", "Landroidx/media3/extractor/text/SubtitleDecoder;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CustomSubtitleDecoderFactory implements SubtitleDecoderFactory {
    private WeakReference<CustomDecoder> latestDecoder;

    @Override // androidx.media3.exoplayer.text.SubtitleDecoderFactory
    public boolean supportsFormat(Format format) {
        Intrinsics.checkNotNullParameter(format, "format");
        return CollectionsKt.contains(CollectionsKt.listOf((Object[]) new String[]{"text/vtt", "text/x-ssa", "application/ttml+xml", "application/x-mp4-vtt", "application/x-subrip", "application/x-quicktime-tx3g", "application/dvbsubs", "application/pgs"}), format.sampleMimeType);
    }

    public final List<SubtitleCue> getSubtitleCues() {
        CustomDecoder customDecoder;
        WeakReference<CustomDecoder> weakReference = this.latestDecoder;
        if (weakReference == null || (customDecoder = weakReference.get()) == null) {
            return null;
        }
        return customDecoder.getCurrentSubtitleCues();
    }

    @Override // androidx.media3.exoplayer.text.SubtitleDecoderFactory
    public SubtitleDecoder createDecoder(Format format) {
        Intrinsics.checkNotNullParameter(format, "format");
        CustomDecoder customDecoder = new CustomDecoder(format);
        this.latestDecoder = new WeakReference<>(customDecoder);
        return new DelegatingSubtitleDecoder(Reflection.getOrCreateKotlinClass(customDecoder.getClass()).getSimpleName() + "Decoder", customDecoder);
    }
}
