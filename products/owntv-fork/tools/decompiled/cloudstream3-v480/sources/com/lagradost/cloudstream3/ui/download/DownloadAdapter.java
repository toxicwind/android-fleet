package com.lagradost.cloudstream3.ui.download;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.DiffUtil;
import androidx.viewbinding.ViewBinding;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.DownloadChildEpisodeBinding;
import com.lagradost.cloudstream3.databinding.DownloadHeaderEpisodeBinding;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.ui.NoStateAdapter;
import com.lagradost.cloudstream3.ui.ViewHolderState;
import com.lagradost.cloudstream3.ui.download.button.PieFetchButton;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.ImageLoader;
import com.lagradost.cloudstream3.utils.downloader.DownloadObjects;
import com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager;
import java.util.Arrays;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: DownloadAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 -2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002-.BI\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00060\u0004\u0012\u0018\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00060\n¢\u0006\u0004\b\r\u0010\u000eJ\u001a\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002J\u001c\u0010\u0015\u001a\u00020\u0006*\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u001c\u0010\u0019\u001a\u00020\u0006*\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u001a\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u001bH\u0002J\u001e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u000bH\u0016J&\u0010\"\u001a\u00020\u00062\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010$\u001a\u00020\u00022\u0006\u0010%\u001a\u00020\u000bH\u0016J\u0010\u0010&\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020\u0002H\u0016J\u0010\u0010'\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\fH\u0007J\u0018\u0010)\u001a\u00020\u00062\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u000bH\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00060\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/lagradost/cloudstream3/ui/download/DownloadAdapter;", "Lcom/lagradost/cloudstream3/ui/NoStateAdapter;", "Lcom/lagradost/cloudstream3/ui/download/VisualDownloadCached;", "onHeaderClickEvent", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/ui/download/DownloadHeaderClickEvent;", "", "onItemClickEvent", "Lcom/lagradost/cloudstream3/ui/download/DownloadClickEvent;", "onItemSelectionChanged", "Lkotlin/Function2;", "", "", "<init>", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)V", "isMultiDeleteState", "bindHeader", "binding", "Landroidx/viewbinding/ViewBinding;", "card", "Lcom/lagradost/cloudstream3/ui/download/VisualDownloadCached$Header;", "handleChildDownload", "Lcom/lagradost/cloudstream3/databinding/DownloadHeaderEpisodeBinding;", "formattedSize", "", "handleParentDownload", "bindChild", "Lcom/lagradost/cloudstream3/ui/download/VisualDownloadCached$Child;", "onCreateCustomContent", "Lcom/lagradost/cloudstream3/ui/ViewHolderState;", "", "parent", "Landroid/view/ViewGroup;", "viewType", "onBindContent", "holder", "item", "position", "customContentViewType", "setIsMultiDeleteState", ES6Iterator.VALUE_PROPERTY, "toggleIsChecked", "checkbox", "Landroid/widget/CheckBox;", "itemId", "Companion", "DiffCallback", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DownloadAdapter extends NoStateAdapter<VisualDownloadCached> {
    private static final int VIEW_TYPE_CHILD = 1;
    private static final int VIEW_TYPE_HEADER = 0;
    private boolean isMultiDeleteState;
    private final Function1<DownloadHeaderClickEvent, Unit> onHeaderClickEvent;
    private final Function1<DownloadClickEvent, Unit> onItemClickEvent;
    private final Function2<Integer, Boolean, Unit> onItemSelectionChanged;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DownloadAdapter(Function1<? super DownloadHeaderClickEvent, Unit> onHeaderClickEvent, Function1<? super DownloadClickEvent, Unit> onItemClickEvent, Function2<? super Integer, ? super Boolean, Unit> onItemSelectionChanged) {
        super(new DiffCallback());
        Intrinsics.checkNotNullParameter(onHeaderClickEvent, "onHeaderClickEvent");
        Intrinsics.checkNotNullParameter(onItemClickEvent, "onItemClickEvent");
        Intrinsics.checkNotNullParameter(onItemSelectionChanged, "onItemSelectionChanged");
        this.onHeaderClickEvent = onHeaderClickEvent;
        this.onItemClickEvent = onItemClickEvent;
        this.onItemSelectionChanged = onItemSelectionChanged;
    }

    private final void bindHeader(ViewBinding binding, VisualDownloadCached.Header card) {
        if (!(binding instanceof DownloadHeaderEpisodeBinding) || card == null) {
            return;
        }
        final DownloadObjects.DownloadHeaderCached data = card.getData();
        final DownloadHeaderEpisodeBinding downloadHeaderEpisodeBinding = (DownloadHeaderEpisodeBinding) binding;
        CardView cardView = downloadHeaderEpisodeBinding.episodeHolder;
        if (this.isMultiDeleteState) {
            cardView.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadAdapter$$ExternalSyntheticLambda12
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DownloadAdapter.bindHeader$lambda$0$0$0(this.f$0, downloadHeaderEpisodeBinding, data, view);
                }
            });
            cardView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadAdapter$$ExternalSyntheticLambda13
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    return DownloadAdapter.bindHeader$lambda$0$0$1(this.f$0, downloadHeaderEpisodeBinding, data, view);
                }
            });
        } else {
            cardView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadAdapter$$ExternalSyntheticLambda14
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    return DownloadAdapter.bindHeader$lambda$0$0$2(this.f$0, data, view);
                }
            });
        }
        ImageView imageView = downloadHeaderEpisodeBinding.downloadHeaderPoster;
        ImageLoader imageLoader = ImageLoader.INSTANCE;
        Intrinsics.checkNotNull(imageView);
        ImageLoader.loadImage$default(imageLoader, imageView, data.getPoster(), (Map) null, (Function1) null, 6, (Object) null);
        if (this.isMultiDeleteState) {
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadAdapter$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DownloadAdapter.bindHeader$lambda$0$1$0(this.f$0, downloadHeaderEpisodeBinding, data, view);
                }
            });
        } else {
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadAdapter$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DownloadAdapter.bindHeader$lambda$0$1$1(this.f$0, data, view);
                }
            });
        }
        imageView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadAdapter$$ExternalSyntheticLambda3
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return DownloadAdapter.bindHeader$lambda$0$1$2(this.f$0, downloadHeaderEpisodeBinding, data, view);
            }
        });
        downloadHeaderEpisodeBinding.downloadHeaderTitle.setText(data.getName());
        String shortFileSize = Formatter.formatShortFileSize(downloadHeaderEpisodeBinding.getRoot().getContext(), card.getTotalBytes());
        if (card.getChild() != null) {
            Intrinsics.checkNotNull(shortFileSize);
            handleChildDownload(downloadHeaderEpisodeBinding, card, shortFileSize);
        } else {
            Intrinsics.checkNotNull(shortFileSize);
            handleParentDownload(downloadHeaderEpisodeBinding, card, shortFileSize);
        }
        if (this.isMultiDeleteState) {
            downloadHeaderEpisodeBinding.deleteCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadAdapter$$ExternalSyntheticLambda4
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    DownloadAdapter.bindHeader$lambda$0$2(this.f$0, data, compoundButton, z);
                }
            });
        } else {
            downloadHeaderEpisodeBinding.deleteCheckbox.setOnCheckedChangeListener(null);
        }
        CheckBox checkBox = downloadHeaderEpisodeBinding.deleteCheckbox;
        Intrinsics.checkNotNull(checkBox);
        checkBox.setVisibility(this.isMultiDeleteState ? 0 : 8);
        checkBox.setChecked(card.isSelected());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindHeader$lambda$0$0$0(DownloadAdapter downloadAdapter, DownloadHeaderEpisodeBinding downloadHeaderEpisodeBinding, DownloadObjects.DownloadHeaderCached downloadHeaderCached, View view) {
        CheckBox deleteCheckbox = downloadHeaderEpisodeBinding.deleteCheckbox;
        Intrinsics.checkNotNullExpressionValue(deleteCheckbox, "deleteCheckbox");
        downloadAdapter.toggleIsChecked(deleteCheckbox, downloadHeaderCached.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean bindHeader$lambda$0$0$1(DownloadAdapter downloadAdapter, DownloadHeaderEpisodeBinding downloadHeaderEpisodeBinding, DownloadObjects.DownloadHeaderCached downloadHeaderCached, View view) {
        CheckBox deleteCheckbox = downloadHeaderEpisodeBinding.deleteCheckbox;
        Intrinsics.checkNotNullExpressionValue(deleteCheckbox, "deleteCheckbox");
        downloadAdapter.toggleIsChecked(deleteCheckbox, downloadHeaderCached.getId());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean bindHeader$lambda$0$0$2(DownloadAdapter downloadAdapter, DownloadObjects.DownloadHeaderCached downloadHeaderCached, View view) {
        downloadAdapter.onItemSelectionChanged.invoke(Integer.valueOf(downloadHeaderCached.getId()), true);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindHeader$lambda$0$1$0(DownloadAdapter downloadAdapter, DownloadHeaderEpisodeBinding downloadHeaderEpisodeBinding, DownloadObjects.DownloadHeaderCached downloadHeaderCached, View view) {
        CheckBox deleteCheckbox = downloadHeaderEpisodeBinding.deleteCheckbox;
        Intrinsics.checkNotNullExpressionValue(deleteCheckbox, "deleteCheckbox");
        downloadAdapter.toggleIsChecked(deleteCheckbox, downloadHeaderCached.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindHeader$lambda$0$1$1(DownloadAdapter downloadAdapter, DownloadObjects.DownloadHeaderCached downloadHeaderCached, View view) {
        downloadAdapter.onHeaderClickEvent.invoke(new DownloadHeaderClickEvent(1, downloadHeaderCached));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean bindHeader$lambda$0$1$2(DownloadAdapter downloadAdapter, DownloadHeaderEpisodeBinding downloadHeaderEpisodeBinding, DownloadObjects.DownloadHeaderCached downloadHeaderCached, View view) {
        CheckBox deleteCheckbox = downloadHeaderEpisodeBinding.deleteCheckbox;
        Intrinsics.checkNotNullExpressionValue(deleteCheckbox, "deleteCheckbox");
        downloadAdapter.toggleIsChecked(deleteCheckbox, downloadHeaderCached.getId());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindHeader$lambda$0$2(DownloadAdapter downloadAdapter, DownloadObjects.DownloadHeaderCached downloadHeaderCached, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(compoundButton, "<unused var>");
        downloadAdapter.onItemSelectionChanged.invoke(Integer.valueOf(downloadHeaderCached.getId()), Boolean.valueOf(z));
    }

    private final void handleChildDownload(DownloadHeaderEpisodeBinding downloadHeaderEpisodeBinding, final VisualDownloadCached.Header header, String str) {
        if (header.getChild() == null) {
            return;
        }
        ImageView downloadHeaderGotoChild = downloadHeaderEpisodeBinding.downloadHeaderGotoChild;
        Intrinsics.checkNotNullExpressionValue(downloadHeaderGotoChild, "downloadHeaderGotoChild");
        downloadHeaderGotoChild.setVisibility(8);
        DataStoreHelper.PosDur viewPos = DataStoreHelper.INSTANCE.getViewPos(Integer.valueOf(header.getData().getId()));
        FrameLayout watchProgressContainer = downloadHeaderEpisodeBinding.watchProgressContainer;
        Intrinsics.checkNotNullExpressionValue(watchProgressContainer, "watchProgressContainer");
        watchProgressContainer.setVisibility(0);
        CircularProgressIndicator circularProgressIndicator = downloadHeaderEpisodeBinding.downloadHeaderEpisodeProgress;
        Intrinsics.checkNotNull(circularProgressIndicator);
        CircularProgressIndicator circularProgressIndicator2 = circularProgressIndicator;
        circularProgressIndicator2.setVisibility(viewPos != null ? 0 : 8);
        if (viewPos != null) {
            int duration = (int) (viewPos.getDuration() / 1000);
            int position = (int) (viewPos.getPosition() / 1000);
            if (duration > 0 && position >= ((int) (((double) duration) * 0.95d))) {
                downloadHeaderEpisodeBinding.playIcon.setImageResource(R.drawable.ic_baseline_check_24);
                circularProgressIndicator2.setVisibility(8);
            } else {
                downloadHeaderEpisodeBinding.playIcon.setImageResource(R.drawable.netflix_play);
                circularProgressIndicator.setMax(duration);
                circularProgressIndicator.setProgress(position);
                circularProgressIndicator2.setVisibility(0);
            }
        }
        downloadHeaderEpisodeBinding.downloadButton.resetView();
        VideoDownloadManager.DownloadType status = downloadHeaderEpisodeBinding.downloadButton.getStatus(header.getChild().getId(), header.getCurrentBytes(), header.getTotalBytes());
        Drawable drawable = null;
        if (status == VideoDownloadManager.DownloadType.IsDone) {
            downloadHeaderEpisodeBinding.downloadButton.setProgress(header.getCurrentBytes(), header.getTotalBytes());
            downloadHeaderEpisodeBinding.downloadButton.applyMetaData(header.getChild().getId(), header.getCurrentBytes(), header.getTotalBytes());
            downloadHeaderEpisodeBinding.downloadButton.setDoSetProgress(false);
            ContentLoadingProgressBar progressBar = downloadHeaderEpisodeBinding.downloadButton.getProgressBar();
            Integer drawableFromStatus = downloadHeaderEpisodeBinding.downloadButton.getDrawableFromStatus(status);
            if (drawableFromStatus != null) {
                drawable = ContextCompat.getDrawable(downloadHeaderEpisodeBinding.downloadButton.getContext(), drawableFromStatus.intValue());
            }
            progressBar.setProgressDrawable(drawable);
            downloadHeaderEpisodeBinding.downloadHeaderInfo.setText(str);
        } else {
            Integer drawableFromStatus2 = downloadHeaderEpisodeBinding.downloadButton.getDrawableFromStatus(status);
            if (drawableFromStatus2 != null) {
                drawable = ContextCompat.getDrawable(downloadHeaderEpisodeBinding.downloadButton.getContext(), drawableFromStatus2.intValue());
            }
            downloadHeaderEpisodeBinding.downloadButton.getStatusView().setImageDrawable(drawable);
            downloadHeaderEpisodeBinding.downloadButton.getProgressBar().setProgressDrawable(ContextCompat.getDrawable(downloadHeaderEpisodeBinding.downloadButton.getContext(), downloadHeaderEpisodeBinding.downloadButton.getProgressDrawable()));
        }
        TextView downloadHeaderInfo = downloadHeaderEpisodeBinding.downloadHeaderInfo;
        Intrinsics.checkNotNullExpressionValue(downloadHeaderInfo, "downloadHeaderInfo");
        downloadHeaderInfo.setVisibility(0);
        downloadHeaderEpisodeBinding.downloadButton.setDefaultClickListener(header.getChild(), downloadHeaderEpisodeBinding.downloadHeaderInfo, this.onItemClickEvent);
        PieFetchButton downloadButton = downloadHeaderEpisodeBinding.downloadButton;
        Intrinsics.checkNotNullExpressionValue(downloadButton, "downloadButton");
        downloadButton.setVisibility(this.isMultiDeleteState ? 8 : 0);
        if (this.isMultiDeleteState) {
            return;
        }
        downloadHeaderEpisodeBinding.episodeHolder.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadAdapter$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onItemClickEvent.invoke(new DownloadClickEvent(0, header.getChild()));
            }
        });
    }

    private final void handleParentDownload(DownloadHeaderEpisodeBinding downloadHeaderEpisodeBinding, final VisualDownloadCached.Header header, String str) {
        downloadHeaderEpisodeBinding.downloadButton.resetViewData();
        FrameLayout watchProgressContainer = downloadHeaderEpisodeBinding.watchProgressContainer;
        Intrinsics.checkNotNullExpressionValue(watchProgressContainer, "watchProgressContainer");
        watchProgressContainer.setVisibility(8);
        PieFetchButton downloadButton = downloadHeaderEpisodeBinding.downloadButton;
        Intrinsics.checkNotNullExpressionValue(downloadButton, "downloadButton");
        downloadButton.setVisibility(8);
        CircularProgressIndicator downloadHeaderEpisodeProgress = downloadHeaderEpisodeBinding.downloadHeaderEpisodeProgress;
        Intrinsics.checkNotNullExpressionValue(downloadHeaderEpisodeProgress, "downloadHeaderEpisodeProgress");
        downloadHeaderEpisodeProgress.setVisibility(8);
        ImageView downloadHeaderGotoChild = downloadHeaderEpisodeBinding.downloadHeaderGotoChild;
        Intrinsics.checkNotNullExpressionValue(downloadHeaderGotoChild, "downloadHeaderGotoChild");
        downloadHeaderGotoChild.setVisibility(this.isMultiDeleteState ? 8 : 0);
        try {
            TextView downloadHeaderInfo = downloadHeaderEpisodeBinding.downloadHeaderInfo;
            Intrinsics.checkNotNullExpressionValue(downloadHeaderInfo, "downloadHeaderInfo");
            downloadHeaderInfo.setVisibility(0);
            TextView textView = downloadHeaderEpisodeBinding.downloadHeaderInfo;
            String string = downloadHeaderEpisodeBinding.downloadHeaderInfo.getContext().getString(R.string.extra_info_format);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            String str2 = String.format(string, Arrays.copyOf(new Object[]{Integer.valueOf(header.getTotalDownloads()), downloadHeaderEpisodeBinding.downloadHeaderInfo.getContext().getResources().getQuantityString(R.plurals.episodes, header.getTotalDownloads()), str}, 3));
            Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
            textView.setText(str2);
        } catch (Exception e) {
            downloadHeaderEpisodeBinding.downloadHeaderInfo.setText((CharSequence) null);
            ArchComponentExtKt.logError(e);
        }
        if (this.isMultiDeleteState) {
            return;
        }
        downloadHeaderEpisodeBinding.episodeHolder.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onHeaderClickEvent.invoke(new DownloadHeaderClickEvent(0, header.getData()));
            }
        });
    }

    private final void bindChild(ViewBinding binding, VisualDownloadCached.Child card) {
        Drawable drawable;
        Drawable drawable2;
        if (!(binding instanceof DownloadChildEpisodeBinding) || card == null) {
            return;
        }
        final DownloadObjects.DownloadEpisodeCached data = card.getData();
        final DownloadChildEpisodeBinding downloadChildEpisodeBinding = (DownloadChildEpisodeBinding) binding;
        DataStoreHelper.PosDur viewPos = DataStoreHelper.INSTANCE.getViewPos(Integer.valueOf(data.getId()));
        CircularProgressIndicator circularProgressIndicator = downloadChildEpisodeBinding.downloadChildEpisodeProgress;
        Intrinsics.checkNotNull(circularProgressIndicator);
        CircularProgressIndicator circularProgressIndicator2 = circularProgressIndicator;
        circularProgressIndicator2.setVisibility(viewPos != null ? 0 : 8);
        if (viewPos != null) {
            int duration = (int) (viewPos.getDuration() / 1000);
            int position = (int) (viewPos.getPosition() / 1000);
            if (duration > 0 && position >= ((int) (((double) duration) * 0.95d))) {
                downloadChildEpisodeBinding.downloadChildEpisodePlay.setImageResource(R.drawable.ic_baseline_check_24);
                circularProgressIndicator2.setVisibility(8);
            } else {
                downloadChildEpisodeBinding.downloadChildEpisodePlay.setImageResource(R.drawable.play_button_transparent);
                circularProgressIndicator.setMax(duration);
                circularProgressIndicator.setProgress(position);
                circularProgressIndicator2.setVisibility(0);
            }
        }
        downloadChildEpisodeBinding.downloadButton.resetView();
        VideoDownloadManager.DownloadType status = downloadChildEpisodeBinding.downloadButton.getStatus(data.getId(), card.getCurrentBytes(), card.getTotalBytes());
        if (status == VideoDownloadManager.DownloadType.IsDone) {
            downloadChildEpisodeBinding.downloadButton.setProgress(card.getCurrentBytes(), card.getTotalBytes());
            downloadChildEpisodeBinding.downloadButton.applyMetaData(data.getId(), card.getCurrentBytes(), card.getTotalBytes());
            downloadChildEpisodeBinding.downloadButton.setDoSetProgress(false);
            ContentLoadingProgressBar progressBar = downloadChildEpisodeBinding.downloadButton.getProgressBar();
            Integer drawableFromStatus = downloadChildEpisodeBinding.downloadButton.getDrawableFromStatus(status);
            if (drawableFromStatus != null) {
                drawable2 = ContextCompat.getDrawable(downloadChildEpisodeBinding.downloadButton.getContext(), drawableFromStatus.intValue());
            } else {
                drawable2 = null;
            }
            progressBar.setProgressDrawable(drawable2);
            downloadChildEpisodeBinding.downloadChildEpisodeTextExtra.setText(Formatter.formatShortFileSize(downloadChildEpisodeBinding.downloadChildEpisodeTextExtra.getContext(), card.getTotalBytes()));
        } else {
            Integer drawableFromStatus2 = downloadChildEpisodeBinding.downloadButton.getDrawableFromStatus(status);
            if (drawableFromStatus2 != null) {
                drawable = ContextCompat.getDrawable(downloadChildEpisodeBinding.downloadButton.getContext(), drawableFromStatus2.intValue());
            } else {
                drawable = null;
            }
            downloadChildEpisodeBinding.downloadButton.getStatusView().setImageDrawable(drawable);
            downloadChildEpisodeBinding.downloadButton.getProgressBar().setProgressDrawable(ContextCompat.getDrawable(downloadChildEpisodeBinding.downloadButton.getContext(), downloadChildEpisodeBinding.downloadButton.getProgressDrawable()));
        }
        downloadChildEpisodeBinding.downloadButton.setDefaultClickListener(data, downloadChildEpisodeBinding.downloadChildEpisodeTextExtra, this.onItemClickEvent);
        PieFetchButton downloadButton = downloadChildEpisodeBinding.downloadButton;
        Intrinsics.checkNotNullExpressionValue(downloadButton, "downloadButton");
        downloadButton.setVisibility(!this.isMultiDeleteState ? 0 : 8);
        TextView textView = downloadChildEpisodeBinding.downloadChildEpisodeText;
        AppContextUtils appContextUtils = AppContextUtils.INSTANCE;
        Context context = textView.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        textView.setText(appContextUtils.getNameFull(context, data.getName(), Integer.valueOf(data.getEpisode()), data.getSeason()));
        textView.setSelected(true);
        downloadChildEpisodeBinding.downloadChildEpisodeHolder.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadAdapter$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DownloadAdapter.bindChild$lambda$0$4(this.f$0, data, view);
            }
        });
        CardView cardView = downloadChildEpisodeBinding.downloadChildEpisodeHolder;
        if (this.isMultiDeleteState) {
            cardView.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadAdapter$$ExternalSyntheticLambda7
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DownloadAdapter.bindChild$lambda$0$5$0(this.f$0, downloadChildEpisodeBinding, data, view);
                }
            });
            cardView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadAdapter$$ExternalSyntheticLambda8
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    return DownloadAdapter.bindChild$lambda$0$5$1(this.f$0, downloadChildEpisodeBinding, data, view);
                }
            });
        } else {
            cardView.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadAdapter$$ExternalSyntheticLambda9
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DownloadAdapter.bindChild$lambda$0$5$2(this.f$0, data, view);
                }
            });
            cardView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadAdapter$$ExternalSyntheticLambda10
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    return DownloadAdapter.bindChild$lambda$0$5$3(this.f$0, data, view);
                }
            });
        }
        if (this.isMultiDeleteState) {
            downloadChildEpisodeBinding.deleteCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadAdapter$$ExternalSyntheticLambda11
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    DownloadAdapter.bindChild$lambda$0$6(this.f$0, data, compoundButton, z);
                }
            });
        } else {
            downloadChildEpisodeBinding.deleteCheckbox.setOnCheckedChangeListener(null);
        }
        CheckBox checkBox = downloadChildEpisodeBinding.deleteCheckbox;
        Intrinsics.checkNotNull(checkBox);
        checkBox.setVisibility(this.isMultiDeleteState ? 0 : 8);
        checkBox.setChecked(card.isSelected());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindChild$lambda$0$4(DownloadAdapter downloadAdapter, DownloadObjects.DownloadEpisodeCached downloadEpisodeCached, View view) {
        downloadAdapter.onItemClickEvent.invoke(new DownloadClickEvent(0, downloadEpisodeCached));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindChild$lambda$0$5$0(DownloadAdapter downloadAdapter, DownloadChildEpisodeBinding downloadChildEpisodeBinding, DownloadObjects.DownloadEpisodeCached downloadEpisodeCached, View view) {
        CheckBox deleteCheckbox = downloadChildEpisodeBinding.deleteCheckbox;
        Intrinsics.checkNotNullExpressionValue(deleteCheckbox, "deleteCheckbox");
        downloadAdapter.toggleIsChecked(deleteCheckbox, downloadEpisodeCached.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean bindChild$lambda$0$5$1(DownloadAdapter downloadAdapter, DownloadChildEpisodeBinding downloadChildEpisodeBinding, DownloadObjects.DownloadEpisodeCached downloadEpisodeCached, View view) {
        CheckBox deleteCheckbox = downloadChildEpisodeBinding.deleteCheckbox;
        Intrinsics.checkNotNullExpressionValue(deleteCheckbox, "deleteCheckbox");
        downloadAdapter.toggleIsChecked(deleteCheckbox, downloadEpisodeCached.getId());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindChild$lambda$0$5$2(DownloadAdapter downloadAdapter, DownloadObjects.DownloadEpisodeCached downloadEpisodeCached, View view) {
        downloadAdapter.onItemClickEvent.invoke(new DownloadClickEvent(0, downloadEpisodeCached));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean bindChild$lambda$0$5$3(DownloadAdapter downloadAdapter, DownloadObjects.DownloadEpisodeCached downloadEpisodeCached, View view) {
        downloadAdapter.onItemSelectionChanged.invoke(Integer.valueOf(downloadEpisodeCached.getId()), true);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindChild$lambda$0$6(DownloadAdapter downloadAdapter, DownloadObjects.DownloadEpisodeCached downloadEpisodeCached, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(compoundButton, "<unused var>");
        downloadAdapter.onItemSelectionChanged.invoke(Integer.valueOf(downloadEpisodeCached.getId()), Boolean.valueOf(z));
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public ViewHolderState<Object> onCreateCustomContent(ViewGroup parent, int viewType) {
        DownloadHeaderEpisodeBinding downloadHeaderEpisodeBinding;
        Intrinsics.checkNotNullParameter(parent, "parent");
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(parent.getContext());
        if (viewType == 0) {
            DownloadHeaderEpisodeBinding downloadHeaderEpisodeBindingInflate = DownloadHeaderEpisodeBinding.inflate(layoutInflaterFrom, parent, false);
            Intrinsics.checkNotNullExpressionValue(downloadHeaderEpisodeBindingInflate, "inflate(...)");
            downloadHeaderEpisodeBinding = downloadHeaderEpisodeBindingInflate;
        } else if (viewType == 1) {
            DownloadChildEpisodeBinding downloadChildEpisodeBindingInflate = DownloadChildEpisodeBinding.inflate(layoutInflaterFrom, parent, false);
            Intrinsics.checkNotNullExpressionValue(downloadChildEpisodeBindingInflate, "inflate(...)");
            downloadHeaderEpisodeBinding = downloadChildEpisodeBindingInflate;
        } else {
            throw new IllegalArgumentException("Invalid view type");
        }
        return new ViewHolderState<>(downloadHeaderEpisodeBinding);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onBindContent(ViewHolderState<Object> holder, VisualDownloadCached item, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        ViewBinding view = holder.getView();
        if (view instanceof DownloadHeaderEpisodeBinding) {
            bindHeader(view, item instanceof VisualDownloadCached.Header ? (VisualDownloadCached.Header) item : null);
        } else if (view instanceof DownloadChildEpisodeBinding) {
            bindChild(view, item instanceof VisualDownloadCached.Child ? (VisualDownloadCached.Child) item : null);
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public int customContentViewType(VisualDownloadCached item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item instanceof VisualDownloadCached.Child) {
            return 1;
        }
        if (item instanceof VisualDownloadCached.Header) {
            return 0;
        }
        throw new NoWhenBranchMatchedException();
    }

    public final void setIsMultiDeleteState(boolean value) {
        if (this.isMultiDeleteState == value) {
            return;
        }
        this.isMultiDeleteState = value;
        notifyDataSetChanged();
    }

    private final void toggleIsChecked(CheckBox checkbox, int itemId) {
        boolean z = !checkbox.isChecked();
        checkbox.setChecked(z);
        this.onItemSelectionChanged.invoke(Integer.valueOf(itemId), Boolean.valueOf(z));
    }

    /* JADX INFO: compiled from: DownloadAdapter.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0016J\u0018\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"Lcom/lagradost/cloudstream3/ui/download/DownloadAdapter$DiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/lagradost/cloudstream3/ui/download/VisualDownloadCached;", "<init>", "()V", "areItemsTheSame", "", "oldItem", "newItem", "areContentsTheSame", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class DiffCallback extends DiffUtil.ItemCallback<VisualDownloadCached> {
        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areItemsTheSame(VisualDownloadCached oldItem, VisualDownloadCached newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return oldItem.getData().getId() == newItem.getData().getId();
        }

        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areContentsTheSame(VisualDownloadCached oldItem, VisualDownloadCached newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem, newItem);
        }
    }
}
