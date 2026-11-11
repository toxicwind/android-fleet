package com.lagradost.cloudstream3.ui.download.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.services.DownloadQueueService;
import com.lagradost.cloudstream3.ui.download.DownloadClickEvent;
import com.lagradost.cloudstream3.utils.UIHelper;
import com.lagradost.cloudstream3.utils.downloader.DownloadObjects;
import com.lagradost.cloudstream3.utils.downloader.DownloadQueueManager;
import com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PieFetchButton.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 A2\u00020\u0001:\u0001AB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010)\u001a\u00020*H\u0016J\b\u0010+\u001a\u00020*H\u0014J6\u0010/\u001a\u00020*2\u0006\u00100\u001a\u00020\"2\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u0002042\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020*06H\u0004J.\u0010/\u001a\u00020*2\u0006\u00103\u001a\u0002042\b\u00101\u001a\u0004\u0018\u0001022\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020*06H\u0016J\u0018\u00108\u001a\u00020*2\u000e\u00109\u001a\n\u0018\u00010-j\u0004\u0018\u0001`.H\u0003J\u0018\u0010:\u001a\u00020*2\u000e\u00109\u001a\n\u0018\u00010-j\u0004\u0018\u0001`.H\u0016J\b\u0010;\u001a\u00020*H\u0016J\u0010\u0010<\u001a\u00020*2\u0006\u0010=\u001a\u00020>H\u0016J\u001f\u0010?\u001a\u0004\u0018\u00010\t2\u000e\u00109\u001a\n\u0018\u00010-j\u0004\u0018\u0001`.H\u0016¢\u0006\u0002\u0010@R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001e\u0010\u001b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u0010\n\u0002\u0010 \u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010!\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010#\u001a\u00020$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u0016\u0010,\u001a\n\u0018\u00010-j\u0004\u0018\u0001`.X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006B"}, d2 = {"Lcom/lagradost/cloudstream3/ui/download/button/PieFetchButton;", "Lcom/lagradost/cloudstream3/ui/download/button/BaseFetchButton;", "context", "Landroid/content/Context;", "attributeSet", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "waitingAnimation", "", "animateWaiting", "", "activeOutline", "nonActiveOutline", "iconInit", "iconError", "iconComplete", "iconActive", "iconWaiting", "iconRemoved", "iconPaused", "hideWhenIcon", "progressDrawable", "getProgressDrawable", "()I", "setProgressDrawable", "(I)V", "overrideLayout", "getOverrideLayout", "()Ljava/lang/Integer;", "setOverrideLayout", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "progressBarBackground", "Landroid/view/View;", "statusView", "Landroid/widget/ImageView;", "getStatusView", "()Landroid/widget/ImageView;", "setStatusView", "(Landroid/widget/ImageView;)V", "onInflate", "", "onAttachedToWindow", "currentStatus", "Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$DownloadType;", "Lcom/lagradost/cloudstream3/ui/download/button/DownloadStatusTell;", "setDefaultClickListener", "view", "textView", "Landroid/widget/TextView;", "card", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadEpisodeCached;", "callback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/ui/download/DownloadClickEvent;", "setStatusInternal", NotificationCompat.CATEGORY_STATUS, "setStatus", "resetView", "updateViewOnDownload", TtmlNode.TAG_METADATA, "Lcom/lagradost/cloudstream3/ui/download/button/DownloadMetadata;", "getDrawableFromStatus", "(Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$DownloadType;)Ljava/lang/Integer;", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class PieFetchButton extends BaseFetchButton {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Integer[] fillArray = {Integer.valueOf(R.drawable.circular_progress_bar_clockwise), Integer.valueOf(R.drawable.circular_progress_bar_counter_clockwise), Integer.valueOf(R.drawable.circular_progress_bar_small_to_large), Integer.valueOf(R.drawable.circular_progress_bar_top_to_bottom)};
    private int activeOutline;
    private boolean animateWaiting;
    private VideoDownloadManager.DownloadType currentStatus;
    private boolean hideWhenIcon;
    private int iconActive;
    private int iconComplete;
    private int iconError;
    private int iconInit;
    private int iconPaused;
    private int iconRemoved;
    private int iconWaiting;
    private int nonActiveOutline;
    private Integer overrideLayout;
    private View progressBarBackground;
    private int progressDrawable;
    private ImageView statusView;
    private int waitingAnimation;

    /* JADX INFO: compiled from: PieFetchButton.kt */
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
                iArr[VideoDownloadManager.DownloadType.IsPending.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[VideoDownloadManager.DownloadType.IsDownloading.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[VideoDownloadManager.DownloadType.IsFailed.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[VideoDownloadManager.DownloadType.IsDone.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[VideoDownloadManager.DownloadType.IsStopped.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public void onInflate() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PieFetchButton(Context context, AttributeSet attributeSet) throws Exception {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attributeSet");
        this.hideWhenIcon = true;
        int[] PieFetchButton = R.styleable.PieFetchButton;
        Intrinsics.checkNotNullExpressionValue(PieFetchButton, "PieFetchButton");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, PieFetchButton, 0, 0);
        try {
            Integer num = this.overrideLayout;
            inflate(num != null ? num.intValue() : typedArrayObtainStyledAttributes.getResourceId(R.styleable.PieFetchButton_download_layout, R.layout.download_button_view));
            this.animateWaiting = typedArrayObtainStyledAttributes.getBoolean(R.styleable.PieFetchButton_download_animate_waiting, true);
            this.hideWhenIcon = typedArrayObtainStyledAttributes.getBoolean(R.styleable.PieFetchButton_download_hide_when_icon, true);
            this.waitingAnimation = typedArrayObtainStyledAttributes.getResourceId(R.styleable.PieFetchButton_download_waiting_animation, R.anim.rotate_around_center_point);
            this.activeOutline = typedArrayObtainStyledAttributes.getResourceId(R.styleable.PieFetchButton_download_outline_active, R.drawable.circle_shape);
            this.nonActiveOutline = typedArrayObtainStyledAttributes.getResourceId(R.styleable.PieFetchButton_download_outline_non_active, R.drawable.circle_shape_dotted);
            this.iconInit = typedArrayObtainStyledAttributes.getResourceId(R.styleable.PieFetchButton_download_icon_init, R.drawable.netflix_download);
            this.iconError = typedArrayObtainStyledAttributes.getResourceId(R.styleable.PieFetchButton_download_icon_paused, R.drawable.download_icon_error);
            this.iconComplete = typedArrayObtainStyledAttributes.getResourceId(R.styleable.PieFetchButton_download_icon_complete, R.drawable.download_icon_done);
            this.iconPaused = typedArrayObtainStyledAttributes.getResourceId(R.styleable.PieFetchButton_download_icon_paused, 0);
            this.iconActive = typedArrayObtainStyledAttributes.getResourceId(R.styleable.PieFetchButton_download_icon_active, 0);
            this.iconWaiting = typedArrayObtainStyledAttributes.getResourceId(R.styleable.PieFetchButton_download_icon_waiting, 0);
            this.iconRemoved = typedArrayObtainStyledAttributes.getResourceId(R.styleable.PieFetchButton_download_icon_removed, R.drawable.netflix_download);
            this.progressDrawable = typedArrayObtainStyledAttributes.getResourceId(R.styleable.PieFetchButton_download_fill_override, fillArray[typedArrayObtainStyledAttributes.getInt(R.styleable.PieFetchButton_download_fill, 0)].intValue());
            typedArrayObtainStyledAttributes.recycle();
            View viewFindViewById = findViewById(R.id.progress_downloaded);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
            setProgressBar((ContentLoadingProgressBar) viewFindViewById);
            View viewFindViewById2 = findViewById(R.id.progress_downloaded_background);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
            this.progressBarBackground = viewFindViewById2;
            View viewFindViewById3 = findViewById(R.id.image_download_status);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
            this.statusView = (ImageView) viewFindViewById3;
            getProgressBar().setProgressDrawable(ContextCompat.getDrawable(context, this.progressDrawable));
            onInflate();
        } catch (Exception e) {
            typedArrayObtainStyledAttributes.recycle();
            Log.e("PieFetchButton", "Error inflating PieFetchButton, check that you have declared the required aria2c attrs: aria2c_icon_scale aria2c_icon_color aria2c_outline_color aria2c_fill_color");
            throw e;
        }
    }

    public final int getProgressDrawable() {
        return this.progressDrawable;
    }

    public final void setProgressDrawable(int i) {
        this.progressDrawable = i;
    }

    public final Integer getOverrideLayout() {
        return this.overrideLayout;
    }

    public final void setOverrideLayout(Integer num) {
        this.overrideLayout = num;
    }

    /* JADX INFO: compiled from: PieFetchButton.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0019\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Lcom/lagradost/cloudstream3/ui/download/button/PieFetchButton$Companion;", "", "<init>", "()V", "fillArray", "", "", "getFillArray", "()[Ljava/lang/Integer;", "[Ljava/lang/Integer;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Integer[] getFillArray() {
            return PieFetchButton.fillArray;
        }
    }

    public final ImageView getStatusView() {
        return this.statusView;
    }

    public final void setStatusView(ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "<set-?>");
        this.statusView = imageView;
    }

    @Override // com.lagradost.cloudstream3.ui.download.button.BaseFetchButton, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setStatusInternal(this.currentStatus);
    }

    protected final void setDefaultClickListener(View view, TextView textView, final DownloadObjects.DownloadEpisodeCached card, final Function1<? super DownloadClickEvent, Unit> callback) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(card, "card");
        Intrinsics.checkNotNullParameter(callback, "callback");
        setProgressText(textView);
        setPersistentId(card.getId());
        view.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.download.button.PieFetchButton$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                PieFetchButton.setDefaultClickListener$lambda$0(this.f$0, card, callback, view2);
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.download.button.PieFetchButton$$ExternalSyntheticLambda1
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view2) {
                return PieFetchButton.setDefaultClickListener$lambda$1(callback, card, view2);
            }
        });
    }

    static final void setDefaultClickListener$lambda$0(PieFetchButton pieFetchButton, final DownloadObjects.DownloadEpisodeCached downloadEpisodeCached, final Function1 function1, View view) {
        Pair pair;
        if (pieFetchButton.getIsZeroBytes()) {
            DownloadObjects.DownloadQueueWrapper[] value = DownloadQueueManager.INSTANCE.getQueue().getValue();
            List<VideoDownloadManager.EpisodeDownloadInstance> value2 = DownloadQueueService.INSTANCE.getDownloadInstances().getValue();
            int id = downloadEpisodeCached.getId();
            for (DownloadObjects.DownloadQueueWrapper downloadQueueWrapper : value) {
                if (downloadQueueWrapper.getId() == id) {
                    UIHelper uIHelper = UIHelper.INSTANCE;
                    Intrinsics.checkNotNull(view);
                    uIHelper.popupMenuNoIcons(view, CollectionsKt.arrayListOf(new Pair(6, Integer.valueOf(R.string.cancel))), new Function1() { // from class: com.lagradost.cloudstream3.ui.download.button.PieFetchButton$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return PieFetchButton.setDefaultClickListener$lambda$0$2(function1, downloadEpisodeCached, (MenuItem) obj);
                        }
                    });
                    return;
                }
            }
            List<VideoDownloadManager.EpisodeDownloadInstance> list = value2;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    if (((VideoDownloadManager.EpisodeDownloadInstance) it.next()).getDownloadQueueWrapper().getId() == id) {
                        UIHelper uIHelper2 = UIHelper.INSTANCE;
                        Intrinsics.checkNotNull(view);
                        uIHelper2.popupMenuNoIcons(view, CollectionsKt.arrayListOf(new Pair(6, Integer.valueOf(R.string.cancel))), new Function1() { // from class: com.lagradost.cloudstream3.ui.download.button.PieFetchButton$$ExternalSyntheticLambda4
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return PieFetchButton.setDefaultClickListener$lambda$0$2(function1, downloadEpisodeCached, (MenuItem) obj);
                            }
                        });
                        return;
                    }
                }
            }
            CloudStreamApp.INSTANCE.removeKey(VideoDownloadManager.KEY_RESUME_PACKAGES, String.valueOf(downloadEpisodeCached.getId()));
            function1.invoke(new DownloadClickEvent(4, downloadEpisodeCached));
            Unit unit = Unit.INSTANCE;
            return;
        }
        ArrayList arrayListArrayListOf = CollectionsKt.arrayListOf(new Pair(0, Integer.valueOf(R.string.popup_play_file)), new Pair(1, Integer.valueOf(R.string.popup_delete_file)));
        DownloadMetadata currentMetaData = pieFetchButton.getCurrentMetaData();
        if (currentMetaData.getProgressPercentage() < 98) {
            if (currentMetaData.getStatus() == VideoDownloadManager.DownloadType.IsDownloading) {
                pair = new Pair(3, Integer.valueOf(R.string.popup_pause_download));
            } else {
                pair = new Pair(2, Integer.valueOf(R.string.popup_resume_download));
            }
            arrayListArrayListOf.add(pair);
        }
        UIHelper uIHelper3 = UIHelper.INSTANCE;
        Intrinsics.checkNotNull(view);
        uIHelper3.popupMenuNoIcons(view, arrayListArrayListOf, new Function1() { // from class: com.lagradost.cloudstream3.ui.download.button.PieFetchButton$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PieFetchButton.setDefaultClickListener$lambda$0$4(function1, downloadEpisodeCached, (MenuItem) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setDefaultClickListener$lambda$0$2(Function1 function1, DownloadObjects.DownloadEpisodeCached downloadEpisodeCached, MenuItem popupMenuNoIcons) {
        Intrinsics.checkNotNullParameter(popupMenuNoIcons, "$this$popupMenuNoIcons");
        function1.invoke(new DownloadClickEvent(popupMenuNoIcons.getItemId(), downloadEpisodeCached));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setDefaultClickListener$lambda$0$4(Function1 function1, DownloadObjects.DownloadEpisodeCached downloadEpisodeCached, MenuItem popupMenuNoIcons) {
        Intrinsics.checkNotNullParameter(popupMenuNoIcons, "$this$popupMenuNoIcons");
        function1.invoke(new DownloadClickEvent(popupMenuNoIcons.getItemId(), downloadEpisodeCached));
        return Unit.INSTANCE;
    }

    static final boolean setDefaultClickListener$lambda$1(Function1 function1, DownloadObjects.DownloadEpisodeCached downloadEpisodeCached, View view) {
        function1.invoke(new DownloadClickEvent(5, downloadEpisodeCached));
        return true;
    }

    public void setDefaultClickListener(DownloadObjects.DownloadEpisodeCached card, TextView textView, Function1<? super DownloadClickEvent, Unit> callback) {
        Intrinsics.checkNotNullParameter(card, "card");
        Intrinsics.checkNotNullParameter(callback, "callback");
        setDefaultClickListener(this, textView, card, callback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setStatusInternal(VideoDownloadManager.DownloadType status) {
        Drawable drawable;
        boolean z = getIsZeroBytes() && status == VideoDownloadManager.DownloadType.IsDownloading;
        if (this.animateWaiting && (status == VideoDownloadManager.DownloadType.IsPending || z)) {
            this.progressBarBackground.startAnimation(AnimationUtils.loadAnimation(getContext(), this.waitingAnimation));
        } else {
            this.progressBarBackground.clearAnimation();
        }
        this.progressBarBackground.setBackground(ContextCompat.getDrawable(getContext(), (status != VideoDownloadManager.DownloadType.IsDownloading || z) ? this.nonActiveOutline : this.activeOutline));
        Integer drawableFromStatus = getDrawableFromStatus(status);
        if (drawableFromStatus != null) {
            drawable = ContextCompat.getDrawable(getContext(), drawableFromStatus.intValue());
        } else {
            drawable = null;
        }
        this.statusView.setImageDrawable(drawable);
        boolean z2 = drawable != null;
        this.statusView.setVisibility(z2 ? 0 : 8);
        boolean z3 = this.hideWhenIcon && z2;
        if (z3) {
            getProgressBar().clearAnimation();
            this.progressBarBackground.clearAnimation();
        }
        this.progressBarBackground.setVisibility(z3 ? 8 : 0);
        getProgressBar().setVisibility(z3 ? 8 : 0);
    }

    @Override // com.lagradost.cloudstream3.ui.download.button.BaseFetchButton
    public void setStatus(final VideoDownloadManager.DownloadType status) {
        this.currentStatus = status;
        if (Looper.getMainLooper().isCurrentThread()) {
            try {
                setStatusInternal(status);
                Unit unit = Unit.INSTANCE;
                return;
            } catch (Throwable th) {
                ArchComponentExtKt.logError(th);
                Boolean.valueOf(this.progressBarBackground.post(new Runnable() { // from class: com.lagradost.cloudstream3.ui.download.button.PieFetchButton$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.setStatusInternal(status);
                    }
                }));
                return;
            }
        }
        this.progressBarBackground.post(new Runnable() { // from class: com.lagradost.cloudstream3.ui.download.button.PieFetchButton$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.setStatusInternal(status);
            }
        });
    }

    @Override // com.lagradost.cloudstream3.ui.download.button.BaseFetchButton
    public void resetView() {
        setStatus(null);
        setCurrentMetaData(new DownloadMetadata(0, 0L, 0L, null));
        setZeroBytes(true);
        setDoSetProgress(true);
        getProgressBar().setProgress(0);
    }

    @Override // com.lagradost.cloudstream3.ui.download.button.BaseFetchButton
    public void updateViewOnDownload(DownloadMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        VideoDownloadManager.DownloadType status = metadata.getStatus();
        if (status == null) {
            resetView();
            return;
        }
        if (status == VideoDownloadManager.DownloadType.IsDone || (metadata.getDownloadedLength() > 1024 && metadata.getDownloadedLength() + 1024 >= metadata.getTotalLength())) {
            setStatus(VideoDownloadManager.DownloadType.IsDone);
        } else {
            setProgress(metadata.getDownloadedLength(), metadata.getTotalLength());
            setStatus(status);
        }
    }

    public Integer getDrawableFromStatus(VideoDownloadManager.DownloadType status) {
        int i;
        switch (status == null ? -1 : WhenMappings.$EnumSwitchMapping$0[status.ordinal()]) {
            case 1:
                i = this.iconPaused;
                break;
            case 2:
                i = this.iconWaiting;
                break;
            case 3:
                i = this.iconActive;
                break;
            case 4:
                i = this.iconError;
                break;
            case 5:
                i = this.iconComplete;
                break;
            case 6:
                i = this.iconRemoved;
                break;
            default:
                i = this.iconInit;
                break;
        }
        Integer numValueOf = Integer.valueOf(i);
        if (numValueOf.intValue() != 0) {
            return numValueOf;
        }
        return null;
    }
}
