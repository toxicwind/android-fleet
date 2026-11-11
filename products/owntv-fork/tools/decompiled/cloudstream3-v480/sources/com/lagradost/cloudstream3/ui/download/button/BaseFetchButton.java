package com.lagradost.cloudstream3.ui.download.button;

import android.content.Context;
import android.text.format.Formatter;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.downloader.DownloadObjects;
import com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: BaseFetchButton.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010 \u001a\u00020!2\b\b\u0001\u0010\"\u001a\u00020\tJ\b\u0010&\u001a\u00020!H\u0016J\u000e\u0010\f\u001a\u00020!2\u0006\u0010-\u001a\u00020\tJ\u0012\u0010.\u001a\u00020!2\b\u0010/\u001a\u0004\u0018\u000100H&J\"\u00101\u001a\u000600j\u0002`22\u0006\u0010-\u001a\u00020\t2\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u000204J\u001e\u00106\u001a\u00020!2\u0006\u0010-\u001a\u00020\t2\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u000204J\u0018\u00107\u001a\u00020!2\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u000204H\u0016J\u001a\u00108\u001a\u00020!2\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u0002000:J \u0010;\u001a\u00020!2\u0018\u00109\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u0002040<J\b\u0010=\u001a\u00020!H\u0014J\b\u0010>\u001a\u00020!H\u0014J\u0010\u0010?\u001a\u00020!2\u0006\u0010@\u001a\u00020(H&J\b\u0010A\u001a\u00020!H&R\u001e\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010#\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u001d\"\u0004\b%\u0010\u001fR\u001a\u0010'\u001a\u00020(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,¨\u0006B"}, d2 = {"Lcom/lagradost/cloudstream3/ui/download/button/BaseFetchButton;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attributeSet", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "persistentId", "", "getPersistentId", "()Ljava/lang/Integer;", "setPersistentId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "progressBar", "Landroidx/core/widget/ContentLoadingProgressBar;", "getProgressBar", "()Landroidx/core/widget/ContentLoadingProgressBar;", "setProgressBar", "(Landroidx/core/widget/ContentLoadingProgressBar;)V", "progressText", "Landroid/widget/TextView;", "getProgressText", "()Landroid/widget/TextView;", "setProgressText", "(Landroid/widget/TextView;)V", "isZeroBytes", "", "()Z", "setZeroBytes", "(Z)V", "inflate", "", TtmlNode.TAG_LAYOUT, "doSetProgress", "getDoSetProgress", "setDoSetProgress", "resetViewData", "currentMetaData", "Lcom/lagradost/cloudstream3/ui/download/button/DownloadMetadata;", "getCurrentMetaData", "()Lcom/lagradost/cloudstream3/ui/download/button/DownloadMetadata;", "setCurrentMetaData", "(Lcom/lagradost/cloudstream3/ui/download/button/DownloadMetadata;)V", "id", "setStatus", NotificationCompat.CATEGORY_STATUS, "Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$DownloadType;", "getStatus", "Lcom/lagradost/cloudstream3/ui/download/button/DownloadStatusTell;", "downloadedBytes", "", "totalBytes", "applyMetaData", "setProgress", "downloadStatusEvent", "data", "Lkotlin/Pair;", "downloadProgressEvent", "Lkotlin/Triple;", "onAttachedToWindow", "onDetachedFromWindow", "updateViewOnDownload", TtmlNode.TAG_METADATA, "resetView", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class BaseFetchButton extends FrameLayout {
    private DownloadMetadata currentMetaData;
    private boolean doSetProgress;
    private boolean isZeroBytes;
    private Integer persistentId;
    public ContentLoadingProgressBar progressBar;
    private TextView progressText;

    public abstract void resetView();

    public abstract void setStatus(VideoDownloadManager.DownloadType status);

    public abstract void updateViewOnDownload(DownloadMetadata metadata);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseFetchButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attributeSet");
        this.isZeroBytes = true;
        resetViewData();
        this.doSetProgress = true;
        this.currentMetaData = new DownloadMetadata(0, 0L, 0L, null);
    }

    public final Integer getPersistentId() {
        return this.persistentId;
    }

    public final void setPersistentId(Integer num) {
        this.persistentId = num;
    }

    public final ContentLoadingProgressBar getProgressBar() {
        ContentLoadingProgressBar contentLoadingProgressBar = this.progressBar;
        if (contentLoadingProgressBar != null) {
            return contentLoadingProgressBar;
        }
        Intrinsics.throwUninitializedPropertyAccessException("progressBar");
        return null;
    }

    public final void setProgressBar(ContentLoadingProgressBar contentLoadingProgressBar) {
        Intrinsics.checkNotNullParameter(contentLoadingProgressBar, "<set-?>");
        this.progressBar = contentLoadingProgressBar;
    }

    public final TextView getProgressText() {
        return this.progressText;
    }

    public final void setProgressText(TextView textView) {
        this.progressText = textView;
    }

    /* JADX INFO: renamed from: isZeroBytes, reason: from getter */
    protected final boolean getIsZeroBytes() {
        return this.isZeroBytes;
    }

    protected final void setZeroBytes(boolean z) {
        this.isZeroBytes = z;
    }

    public final void inflate(int layout) {
        FrameLayout.inflate(getContext(), layout, this);
    }

    public final boolean getDoSetProgress() {
        return this.doSetProgress;
    }

    public final void setDoSetProgress(boolean z) {
        this.doSetProgress = z;
    }

    public void resetViewData() {
        this.progressText = null;
        this.isZeroBytes = true;
        this.doSetProgress = true;
        this.persistentId = null;
    }

    public final DownloadMetadata getCurrentMetaData() {
        return this.currentMetaData;
    }

    public final void setCurrentMetaData(DownloadMetadata downloadMetadata) {
        Intrinsics.checkNotNullParameter(downloadMetadata, "<set-?>");
        this.currentMetaData = downloadMetadata;
    }

    public final void setPersistentId(int id) {
        this.persistentId = Integer.valueOf(id);
        this.currentMetaData.setId(id);
        if (this.doSetProgress) {
            Coroutines.INSTANCE.ioSafe(this, new C08391(getContext().getApplicationContext(), id, this, null));
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.download.button.BaseFetchButton$setPersistentId$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BaseFetchButton.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/download/button/BaseFetchButton;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.download.button.BaseFetchButton$setPersistentId$1", f = "BaseFetchButton.kt", i = {0, 0}, l = {83}, m = "invokeSuspend", n = {"$this$ioSafe", "savedData"}, nl = {92}, s = {"L$0", "L$1"}, v = 2)
    static final class C08391 extends SuspendLambda implements Function3<CoroutineScope, BaseFetchButton, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $appContext;
        final /* synthetic */ int $id;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ BaseFetchButton this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08391(Context context, int i, BaseFetchButton baseFetchButton, Continuation<? super C08391> continuation) {
            super(3, continuation);
            this.$appContext = context;
            this.$id = i;
            this.this$0 = baseFetchButton;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, BaseFetchButton baseFetchButton, Continuation<? super Unit> continuation) {
            C08391 c08391 = new C08391(this.$appContext, this.$id, this.this$0, continuation);
            c08391.L$0 = coroutineScope;
            return c08391.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                VideoDownloadManager videoDownloadManager = VideoDownloadManager.INSTANCE;
                Context context = this.$appContext;
                Intrinsics.checkNotNull(context);
                DownloadObjects.DownloadedFileInfoResult downloadFileInfo = videoDownloadManager.getDownloadFileInfo(context, this.$id);
                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.L$1 = SpillingKt.nullOutSpilledVariable(downloadFileInfo);
                this.label = 1;
                if (Coroutines.INSTANCE.mainWork(coroutineScope, new C01691(downloadFileInfo, this.this$0, this.$id, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.download.button.BaseFetchButton$setPersistentId$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: BaseFetchButton.kt */
        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.lagradost.cloudstream3.ui.download.button.BaseFetchButton$setPersistentId$1$1", f = "BaseFetchButton.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
        static final class C01691 extends SuspendLambda implements Function3<CoroutineScope, CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ int $id;
            final /* synthetic */ DownloadObjects.DownloadedFileInfoResult $savedData;
            int label;
            final /* synthetic */ BaseFetchButton this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01691(DownloadObjects.DownloadedFileInfoResult downloadedFileInfoResult, BaseFetchButton baseFetchButton, int i, Continuation<? super C01691> continuation) {
                super(3, continuation);
                this.$savedData = downloadedFileInfoResult;
                this.this$0 = baseFetchButton;
                this.$id = i;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(CoroutineScope coroutineScope, CoroutineScope coroutineScope2, Continuation<? super Unit> continuation) {
                return new C01691(this.$savedData, this.this$0, this.$id, continuation).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                DownloadObjects.DownloadedFileInfoResult downloadedFileInfoResult = this.$savedData;
                if (downloadedFileInfoResult != null) {
                    long fileLength = downloadedFileInfoResult.getFileLength();
                    long totalBytes = this.$savedData.getTotalBytes();
                    this.this$0.setProgress(fileLength, totalBytes);
                    this.this$0.applyMetaData(this.$id, fileLength, totalBytes);
                }
                return Unit.INSTANCE;
            }
        }
    }

    public final VideoDownloadManager.DownloadType getStatus(int id, long downloadedBytes, long totalBytes) {
        VideoDownloadManager.DownloadType downloadType = VideoDownloadManager.INSTANCE.getDownloadStatus().get(Integer.valueOf(id));
        if (downloadType != null) {
            return downloadType;
        }
        if (downloadedBytes > 1024 && downloadedBytes + 1024 >= totalBytes) {
            return VideoDownloadManager.DownloadType.IsDone;
        }
        return VideoDownloadManager.DownloadType.IsPaused;
    }

    public final void applyMetaData(int id, long downloadedBytes, long totalBytes) {
        VideoDownloadManager.DownloadType status = getStatus(id, downloadedBytes, totalBytes);
        DownloadMetadata downloadMetadata = this.currentMetaData;
        downloadMetadata.setId(id);
        downloadMetadata.setDownloadedLength(downloadedBytes);
        downloadMetadata.setTotalLength(totalBytes);
        downloadMetadata.setStatus(status);
        setStatus(status);
    }

    public void setProgress(final long downloadedBytes, final long totalBytes) {
        this.isZeroBytes = downloadedBytes == 0;
        getProgressBar().post(new Runnable() { // from class: com.lagradost.cloudstream3.ui.download.button.BaseFetchButton$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                BaseFetchButton.setProgress$lambda$0(this.f$0, downloadedBytes, totalBytes);
            }
        });
    }

    static final void setProgress$lambda$0(BaseFetchButton baseFetchButton, long j, long j2) {
        TextView textView;
        String str;
        String string;
        baseFetchButton.getProgressBar().setMax(10000);
        int i = (int) ((10000 * j) / (1 + j2));
        ProgressBarAnimation progressBarAnimation = new ProgressBarAnimation(baseFetchButton.getProgressBar(), baseFetchButton.getProgressBar().getProgress(), i);
        progressBarAnimation.setFillAfter(true);
        progressBarAnimation.setDuration(i > baseFetchButton.getProgressBar().getProgress() ? 100L : 0L);
        if (baseFetchButton.isZeroBytes) {
            TextView textView2 = baseFetchButton.progressText;
            if (textView2 != null) {
                textView2.setVisibility(8);
            }
        } else if (baseFetchButton.doSetProgress && (textView = baseFetchButton.progressText) != null) {
            String shortFileSize = Formatter.formatShortFileSize(textView.getContext(), j);
            String shortFileSize2 = Formatter.formatShortFileSize(textView.getContext(), j2);
            Context context = textView.getContext();
            if (context == null || (string = context.getString(R.string.download_size_format)) == null) {
                str = null;
            } else {
                str = String.format(string, Arrays.copyOf(new Object[]{shortFileSize, shortFileSize2}, 2));
                Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            }
            textView.setText(str);
        }
        baseFetchButton.getProgressBar().startAnimation(progressBarAnimation);
    }

    public final void downloadStatusEvent(Pair<Integer, ? extends VideoDownloadManager.DownloadType> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        int iIntValue = data.component1().intValue();
        VideoDownloadManager.DownloadType downloadTypeComponent2 = data.component2();
        Integer num = this.persistentId;
        if (num != null && iIntValue == num.intValue()) {
            this.currentMetaData.setStatus(downloadTypeComponent2);
            setStatus(downloadTypeComponent2);
        }
    }

    public final void downloadProgressEvent(Triple<Integer, Long, Long> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        int iIntValue = data.component1().intValue();
        long jLongValue = data.component2().longValue();
        long jLongValue2 = data.component3().longValue();
        Integer num = this.persistentId;
        if (num != null && iIntValue == num.intValue()) {
            this.currentMetaData.setDownloadedLength(jLongValue);
            this.currentMetaData.setTotalLength(jLongValue2);
            setProgress(jLongValue, jLongValue2);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.download.button.BaseFetchButton$onAttachedToWindow$1, reason: invalid class name */
    /* JADX INFO: compiled from: BaseFetchButton.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Pair<? extends Integer, ? extends VideoDownloadManager.DownloadType>, Unit> {
        AnonymousClass1(Object obj) {
            super(1, obj, BaseFetchButton.class, "downloadStatusEvent", "downloadStatusEvent(Lkotlin/Pair;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Pair<? extends Integer, ? extends VideoDownloadManager.DownloadType> pair) {
            invoke2((Pair<Integer, ? extends VideoDownloadManager.DownloadType>) pair);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Pair<Integer, ? extends VideoDownloadManager.DownloadType> p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            ((BaseFetchButton) this.receiver).downloadStatusEvent(p0);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        VideoDownloadManager.INSTANCE.getDownloadStatusEvent().plusAssign(new AnonymousClass1(this));
        VideoDownloadManager.INSTANCE.getDownloadProgressEvent().plusAssign(new AnonymousClass2(this));
        Integer num = this.persistentId;
        if (num != null) {
            setPersistentId(num.intValue());
        }
        super.onAttachedToWindow();
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.download.button.BaseFetchButton$onAttachedToWindow$2, reason: invalid class name */
    /* JADX INFO: compiled from: BaseFetchButton.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function1<Triple<? extends Integer, ? extends Long, ? extends Long>, Unit> {
        AnonymousClass2(Object obj) {
            super(1, obj, BaseFetchButton.class, "downloadProgressEvent", "downloadProgressEvent(Lkotlin/Triple;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Triple<? extends Integer, ? extends Long, ? extends Long> triple) {
            invoke2((Triple<Integer, Long, Long>) triple);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Triple<Integer, Long, Long> p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            ((BaseFetchButton) this.receiver).downloadProgressEvent(p0);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.download.button.BaseFetchButton$onDetachedFromWindow$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BaseFetchButton.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C08371 extends FunctionReferenceImpl implements Function1<Pair<? extends Integer, ? extends VideoDownloadManager.DownloadType>, Unit> {
        C08371(Object obj) {
            super(1, obj, BaseFetchButton.class, "downloadStatusEvent", "downloadStatusEvent(Lkotlin/Pair;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Pair<? extends Integer, ? extends VideoDownloadManager.DownloadType> pair) {
            invoke2((Pair<Integer, ? extends VideoDownloadManager.DownloadType>) pair);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Pair<Integer, ? extends VideoDownloadManager.DownloadType> p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            ((BaseFetchButton) this.receiver).downloadStatusEvent(p0);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        VideoDownloadManager.INSTANCE.getDownloadStatusEvent().minusAssign(new C08371(this));
        VideoDownloadManager.INSTANCE.getDownloadProgressEvent().minusAssign(new C08382(this));
        super.onDetachedFromWindow();
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.download.button.BaseFetchButton$onDetachedFromWindow$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BaseFetchButton.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C08382 extends FunctionReferenceImpl implements Function1<Triple<? extends Integer, ? extends Long, ? extends Long>, Unit> {
        C08382(Object obj) {
            super(1, obj, BaseFetchButton.class, "downloadProgressEvent", "downloadProgressEvent(Lkotlin/Triple;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Triple<? extends Integer, ? extends Long, ? extends Long> triple) {
            invoke2((Triple<Integer, Long, Long>) triple);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Triple<Integer, Long, Long> p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            ((BaseFetchButton) this.receiver).downloadProgressEvent(p0);
        }
    }
}
