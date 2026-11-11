package com.lagradost.cloudstream3.ui.download.button;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.ui.download.DownloadClickEvent;
import com.lagradost.cloudstream3.utils.downloader.DownloadObjects;
import com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadButton.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\n\u001a\u00020\u000bH\u0014J\u0018\u0010\f\u001a\u00020\u000b2\u000e\u0010\r\u001a\n\u0018\u00010\u000ej\u0004\u0018\u0001`\u000fH\u0016J.\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\t2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u000b0\u0015H\u0016J\u0010\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u0019H\u0017R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/lagradost/cloudstream3/ui/download/button/DownloadButton;", "Lcom/lagradost/cloudstream3/ui/download/button/PieFetchButton;", "context", "Landroid/content/Context;", "attributeSet", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "mainText", "Landroid/widget/TextView;", "onAttachedToWindow", "", "setStatus", NotificationCompat.CATEGORY_STATUS, "Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$DownloadType;", "Lcom/lagradost/cloudstream3/ui/download/button/DownloadStatusTell;", "setDefaultClickListener", "card", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadEpisodeCached;", "textView", "callback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/ui/download/DownloadClickEvent;", "updateViewOnDownload", TtmlNode.TAG_METADATA, "Lcom/lagradost/cloudstream3/ui/download/button/DownloadMetadata;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DownloadButton extends PieFetchButton {
    private TextView mainText;

    /* JADX INFO: compiled from: DownloadButton.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[VideoDownloadManager.DownloadType.values().length];
            try {
                iArr[VideoDownloadManager.DownloadType.IsPaused.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[VideoDownloadManager.DownloadType.IsDownloading.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[VideoDownloadManager.DownloadType.IsDone.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DownloadButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attributeSet");
    }

    @Override // com.lagradost.cloudstream3.ui.download.button.PieFetchButton, com.lagradost.cloudstream3.ui.download.button.BaseFetchButton, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setProgressText((TextView) findViewById(R.id.result_movie_download_text_precentage));
        this.mainText = (TextView) findViewById(R.id.result_movie_download_text);
        setStatus(null);
    }

    @Override // com.lagradost.cloudstream3.ui.download.button.PieFetchButton, com.lagradost.cloudstream3.ui.download.button.BaseFetchButton
    public void setStatus(final VideoDownloadManager.DownloadType status) {
        TextView textView = this.mainText;
        if (textView != null) {
            textView.post(new Runnable() { // from class: com.lagradost.cloudstream3.ui.download.button.DownloadButton$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DownloadButton.setStatus$lambda$0(status, this);
                }
            });
        }
        super.setStatus(status);
    }

    static final void setStatus$lambda$0(VideoDownloadManager.DownloadType downloadType, DownloadButton downloadButton) {
        int i;
        int i2 = downloadType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[downloadType.ordinal()];
        if (i2 == 1) {
            i = R.string.download_paused;
        } else if (i2 == 2) {
            i = R.string.downloading;
        } else if (i2 == 3) {
            i = R.string.downloaded;
        } else {
            i = R.string.download;
        }
        TextView textView = downloadButton.mainText;
        if (textView != null) {
            textView.setText(i);
        }
    }

    @Override // com.lagradost.cloudstream3.ui.download.button.PieFetchButton
    public void setDefaultClickListener(DownloadObjects.DownloadEpisodeCached card, TextView textView, Function1<? super DownloadClickEvent, Unit> callback) {
        Intrinsics.checkNotNullParameter(card, "card");
        Intrinsics.checkNotNullParameter(callback, "callback");
        View viewFindViewById = findViewById(R.id.download_movie_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        setDefaultClickListener(viewFindViewById, textView, card, callback);
    }

    @Override // com.lagradost.cloudstream3.ui.download.button.PieFetchButton, com.lagradost.cloudstream3.ui.download.button.BaseFetchButton
    public void updateViewOnDownload(DownloadMetadata metadata) {
        TextView progressText;
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        super.updateViewOnDownload(metadata);
        boolean z = metadata.getProgressPercentage() > 0;
        TextView progressText2 = getProgressText();
        if (progressText2 != null) {
            progressText2.setVisibility(z ? 0 : 8);
        }
        if (!z || (progressText = getProgressText()) == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(metadata.getProgressPercentage());
        sb.append('%');
        progressText.setText(sb.toString());
    }
}
