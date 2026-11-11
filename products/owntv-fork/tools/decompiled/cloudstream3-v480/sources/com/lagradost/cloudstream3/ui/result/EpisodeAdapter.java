package com.lagradost.cloudstream3.ui.result;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import coil3.Image;
import coil3.request.ImageRequest;
import coil3.util.CoilUtils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.Score;
import com.lagradost.cloudstream3.actions.VideoClickActionHolder;
import com.lagradost.cloudstream3.databinding.ResultEpisodeBinding;
import com.lagradost.cloudstream3.databinding.ResultEpisodeLargeBinding;
import com.lagradost.cloudstream3.syncproviders.AccountManager;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.BaseAdapterKt;
import com.lagradost.cloudstream3.ui.BaseDiffCallback;
import com.lagradost.cloudstream3.ui.NoStateAdapter;
import com.lagradost.cloudstream3.ui.ViewHolderState;
import com.lagradost.cloudstream3.ui.download.DownloadClickEvent;
import com.lagradost.cloudstream3.ui.download.button.PieFetchButton;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.ImageLoader;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UIHelper;
import com.lagradost.cloudstream3.utils.downloader.DownloadObjects;
import com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: EpisodeAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001a2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001aB7\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0004\b\u000b\u0010\fJ\u0016\u0010\r\u001a\u00020\b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002H\u0016J\u001e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0012H\u0016J&\u0010\u0018\u001a\u00020\b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u0012H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/EpisodeAdapter;", "Lcom/lagradost/cloudstream3/ui/NoStateAdapter;", "Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "hasDownloadSupport", "", "clickCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/ui/result/EpisodeClickEvent;", "", "downloadClickCallback", "Lcom/lagradost/cloudstream3/ui/download/DownloadClickEvent;", "<init>", "(ZLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "onClearView", "holder", "Lcom/lagradost/cloudstream3/ui/ViewHolderState;", "", "customContentViewType", "", "item", "onCreateCustomContent", "parent", "Landroid/view/ViewGroup;", "viewType", "onBindContent", "position", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class EpisodeAdapter extends NoStateAdapter<ResultEpisode> {
    public static final int HAS_NO_POSTER = 1;
    public static final int HAS_POSTER = 0;
    private final Function1<EpisodeClickEvent, Unit> clickCallback;
    private final Function1<DownloadClickEvent, Unit> downloadClickCallback;
    private final boolean hasDownloadSupport;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Pair<WeakHashMap<Context, RecyclerView.RecycledViewPool>, Function1<RecyclerView.RecycledViewPool, Unit>> sharedPool = BaseAdapterKt.newSharedPool(new Function1() { // from class: com.lagradost.cloudstream3.ui.result.EpisodeAdapter$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return EpisodeAdapter.sharedPool$lambda$0((RecyclerView.RecycledViewPool) obj);
        }
    });

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public EpisodeAdapter(boolean z, Function1<? super EpisodeClickEvent, Unit> clickCallback, Function1<? super DownloadClickEvent, Unit> downloadClickCallback) {
        super(new BaseDiffCallback(new Function2() { // from class: com.lagradost.cloudstream3.ui.result.EpisodeAdapter$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(EpisodeAdapter._init_$lambda$0((ResultEpisode) obj, (ResultEpisode) obj2));
            }
        }, new Function2() { // from class: com.lagradost.cloudstream3.ui.result.EpisodeAdapter$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(EpisodeAdapter._init_$lambda$1((ResultEpisode) obj, (ResultEpisode) obj2));
            }
        }));
        Intrinsics.checkNotNullParameter(clickCallback, "clickCallback");
        Intrinsics.checkNotNullParameter(downloadClickCallback, "downloadClickCallback");
        this.hasDownloadSupport = z;
        this.clickCallback = clickCallback;
        this.downloadClickCallback = downloadClickCallback;
    }

    static final boolean _init_$lambda$0(ResultEpisode a, ResultEpisode b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return a.getId() == b.getId();
    }

    static final boolean _init_$lambda$1(ResultEpisode a, ResultEpisode b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return Intrinsics.areEqual(a, b);
    }

    /* JADX INFO: compiled from: EpisodeAdapter.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R:\u0010\n\u001a+\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\r0\f\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\u0002\b\u00100\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/EpisodeAdapter$Companion;", "", "<init>", "()V", "HAS_POSTER", "", "HAS_NO_POSTER", "getPlayerAction", "context", "Landroid/content/Context;", "sharedPool", "Lkotlin/Pair;", "Ljava/util/WeakHashMap;", "Landroidx/recyclerview/widget/RecyclerView$RecycledViewPool;", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "getSharedPool", "()Lkotlin/Pair;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getPlayerAction(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Integer numUniqueIdToId = VideoClickActionHolder.INSTANCE.uniqueIdToId(PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.player_default_key), ""));
            if (numUniqueIdToId != null) {
                return numUniqueIdToId.intValue();
            }
            return 1;
        }

        public final Pair<WeakHashMap<Context, RecyclerView.RecycledViewPool>, Function1<RecyclerView.RecycledViewPool, Unit>> getSharedPool() {
            return EpisodeAdapter.sharedPool;
        }
    }

    static final Unit sharedPool$lambda$0(RecyclerView.RecycledViewPool newSharedPool) {
        Intrinsics.checkNotNullParameter(newSharedPool, "$this$newSharedPool");
        newSharedPool.setMaxRecycledViews(268435456, 10);
        newSharedPool.setMaxRecycledViews(268435457, 10);
        return Unit.INSTANCE;
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onClearView(ViewHolderState<Object> holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (holder.itemView.hasFocus()) {
            holder.itemView.clearFocus();
        }
        ViewBinding view = holder.getView();
        if (view instanceof ResultEpisodeLargeBinding) {
            BaseAdapter.INSTANCE.clearImage(((ResultEpisodeLargeBinding) view).episodePoster);
        }
        super.onClearView(holder);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public int customContentViewType(ResultEpisode item) {
        Intrinsics.checkNotNullParameter(item, "item");
        String poster = item.getPoster();
        if (poster != null && !StringsKt.isBlank(poster)) {
            return 0;
        }
        String description = item.getDescription();
        return (description == null || StringsKt.isBlank(description)) ? 1 : 0;
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public ViewHolderState<Object> onCreateCustomContent(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (viewType == 0) {
            ResultEpisodeLargeBinding resultEpisodeLargeBindingInflate = ResultEpisodeLargeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            Intrinsics.checkNotNullExpressionValue(resultEpisodeLargeBindingInflate, "inflate(...)");
            return new ViewHolderState<>(resultEpisodeLargeBindingInflate);
        }
        if (viewType == 1) {
            ResultEpisodeBinding resultEpisodeBindingInflate = ResultEpisodeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            Intrinsics.checkNotNullExpressionValue(resultEpisodeBindingInflate, "inflate(...)");
            return new ViewHolderState<>(resultEpisodeBindingInflate);
        }
        throw new NotImplementedError(null, 1, null);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onBindContent(ViewHolderState<Object> holder, final ResultEpisode item, final int position) {
        StringBuilder sb;
        StringBuilder sb2;
        String str;
        String string;
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        View itemView = holder.itemView;
        Intrinsics.checkNotNullExpressionValue(itemView, "itemView");
        ViewBinding view = holder.getView();
        if (view instanceof ResultEpisodeLargeBinding) {
            int toPx = Globals.INSTANCE.isLayout(6) ? UIHelper.INSTANCE.getToPx(400) : -1;
            final ResultEpisodeLargeBinding resultEpisodeLargeBinding = (ResultEpisodeLargeBinding) view;
            resultEpisodeLargeBinding.episodeLinHolder.getLayoutParams().width = toPx;
            resultEpisodeLargeBinding.episodeHolderLarge.getLayoutParams().width = toPx;
            resultEpisodeLargeBinding.episodeHolder.getLayoutParams().width = toPx;
            if (Globals.INSTANCE.isLayout(5) && CommonActivity.INSTANCE.getAppliedTheme() == R.style.AmoledMode) {
                resultEpisodeLargeBinding.episodeHolderLarge.setRadius(0.0f);
                LinearLayout episodeHolder = resultEpisodeLargeBinding.episodeHolder;
                Intrinsics.checkNotNullExpressionValue(episodeHolder, "episodeHolder");
                episodeHolder.setPadding(0, 0, 0, 0);
            }
            PieFetchButton downloadButton = resultEpisodeLargeBinding.downloadButton;
            Intrinsics.checkNotNullExpressionValue(downloadButton, "downloadButton");
            downloadButton.setVisibility(this.hasDownloadSupport ? 0 : 8);
            resultEpisodeLargeBinding.downloadButton.setDefaultClickListener(new DownloadObjects.DownloadEpisodeCached(item.getName(), item.getPoster(), item.getEpisode(), item.getSeason(), item.getParentId(), item.getScore(), item.getDescription(), System.currentTimeMillis(), item.getId()), null, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.EpisodeAdapter$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return EpisodeAdapter.onBindContent$lambda$0$0(this.f$0, position, item, (DownloadClickEvent) obj);
                }
            });
            VideoDownloadManager.DownloadType downloadType = VideoDownloadManager.INSTANCE.getDownloadStatus().get(Integer.valueOf(item.getId()));
            resultEpisodeLargeBinding.downloadButton.resetView();
            resultEpisodeLargeBinding.downloadButton.setPersistentId(item.getId());
            resultEpisodeLargeBinding.downloadButton.setStatus(downloadType);
            if (item.getName() == null) {
                sb2 = new StringBuilder();
                sb2.append(resultEpisodeLargeBinding.episodeText.getContext().getString(R.string.episode));
                sb2.append(' ');
                sb2.append(item.getEpisode());
            } else {
                sb2 = new StringBuilder();
                sb2.append(item.getEpisode());
                sb2.append(". ");
                sb2.append(item.getName());
            }
            String string2 = sb2.toString();
            MaterialButton episodeFiller = resultEpisodeLargeBinding.episodeFiller;
            Intrinsics.checkNotNullExpressionValue(episodeFiller, "episodeFiller");
            episodeFiller.setVisibility(Intrinsics.areEqual((Object) item.isFiller(), (Object) true) ? 0 : 8);
            resultEpisodeLargeBinding.episodeText.setText(string2);
            resultEpisodeLargeBinding.episodeText.setSelected(true);
            if (item.getVideoWatchState() == VideoWatchState.Watched) {
                resultEpisodeLargeBinding.episodePlayIcon.setImageResource(R.drawable.ic_baseline_check_24);
                CircularProgressIndicator episodeProgress = resultEpisodeLargeBinding.episodeProgress;
                Intrinsics.checkNotNullExpressionValue(episodeProgress, "episodeProgress");
                episodeProgress.setVisibility(8);
                Unit unit = Unit.INSTANCE;
            } else {
                long displayPosition = ResultFragmentKt.getDisplayPosition(item);
                int duration = (int) (item.getDuration() / 1000);
                int i = (int) (displayPosition / 1000);
                if (displayPosition >= item.getDuration() && displayPosition > 0) {
                    resultEpisodeLargeBinding.episodePlayIcon.setImageResource(R.drawable.ic_baseline_check_24);
                    CircularProgressIndicator episodeProgress2 = resultEpisodeLargeBinding.episodeProgress;
                    Intrinsics.checkNotNullExpressionValue(episodeProgress2, "episodeProgress");
                    episodeProgress2.setVisibility(8);
                    Unit unit2 = Unit.INSTANCE;
                } else {
                    resultEpisodeLargeBinding.episodePlayIcon.setImageResource(R.drawable.netflix_play);
                    CircularProgressIndicator circularProgressIndicator = resultEpisodeLargeBinding.episodeProgress;
                    circularProgressIndicator.setMax(duration);
                    circularProgressIndicator.setProgress(i);
                    Intrinsics.checkNotNull(circularProgressIndicator);
                    circularProgressIndicator.setVisibility((displayPosition > 0L ? 1 : (displayPosition == 0L ? 0 : -1)) > 0 ? 0 : 8);
                    Intrinsics.checkNotNull(circularProgressIndicator);
                }
            }
            String poster = item.getPoster();
            boolean z = poster == null || StringsKt.isBlank(poster);
            if (!z) {
                final boolean z2 = item.getAirDate() != null && APIHolder.INSTANCE.getUnixTimeMS() < item.getAirDate().longValue();
                ImageLoader imageLoader = ImageLoader.INSTANCE;
                ImageView episodePoster = resultEpisodeLargeBinding.episodePoster;
                Intrinsics.checkNotNullExpressionValue(episodePoster, "episodePoster");
                ImageLoader.loadImage$default(imageLoader, episodePoster, item.getPoster(), (Map) null, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.EpisodeAdapter$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return EpisodeAdapter.onBindContent$lambda$0$2(z2, resultEpisodeLargeBinding, (ImageRequest.Builder) obj);
                    }
                }, 2, (Object) null);
            } else {
                ImageView episodePoster2 = resultEpisodeLargeBinding.episodePoster;
                Intrinsics.checkNotNullExpressionValue(episodePoster2, "episodePoster");
                CoilUtils.dispose(episodePoster2);
            }
            ImageView episodePoster3 = resultEpisodeLargeBinding.episodePoster;
            Intrinsics.checkNotNullExpressionValue(episodePoster3, "episodePoster");
            episodePoster3.setVisibility(!z ? 0 : 8);
            Score score = item.getScore();
            Float fValueOf = score != null ? Float.valueOf(score.toFloat(10)) : null;
            if (fValueOf == null || fValueOf.floatValue() <= 0.1d) {
                resultEpisodeLargeBinding.episodeRating.setText("");
            } else {
                TextView textView = resultEpisodeLargeBinding.episodeRating;
                Context context = resultEpisodeLargeBinding.episodeRating.getContext();
                if (context == null || (string = context.getString(R.string.rated_format)) == null) {
                    str = null;
                } else {
                    str = String.format(string, Arrays.copyOf(new Object[]{fValueOf}, 1));
                    Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                }
                textView.setText(str);
            }
            TextView episodeRating = resultEpisodeLargeBinding.episodeRating;
            Intrinsics.checkNotNullExpressionValue(episodeRating, "episodeRating");
            TextView textView2 = episodeRating;
            CharSequence text = resultEpisodeLargeBinding.episodeRating.getText();
            textView2.setVisibility(text == null || StringsKt.isBlank(text) ? 8 : 0);
            final TextView textView3 = resultEpisodeLargeBinding.episodeDescript;
            textView3.setText(AppContextUtils.INSTANCE.html(item.getDescription()));
            Intrinsics.checkNotNull(textView3);
            TextView textView4 = textView3;
            CharSequence text2 = textView3.getText();
            textView4.setVisibility(text2 == null || StringsKt.isBlank(text2) ? 8 : 0);
            final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            textView3.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.EpisodeAdapter$$ExternalSyntheticLambda6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    EpisodeAdapter.onBindContent$lambda$0$3$0(this.f$0, position, item, booleanRef, textView3, view2);
                }
            });
            if (item.getAirDate() == null) {
                ImageView episodeUpcomingIcon = resultEpisodeLargeBinding.episodeUpcomingIcon;
                Intrinsics.checkNotNullExpressionValue(episodeUpcomingIcon, "episodeUpcomingIcon");
                episodeUpcomingIcon.setVisibility(8);
                ShapeableImageView episodePlayIcon = resultEpisodeLargeBinding.episodePlayIcon;
                Intrinsics.checkNotNullExpressionValue(episodePlayIcon, "episodePlayIcon");
                episodePlayIcon.setVisibility(0);
                TextView episodeDate = resultEpisodeLargeBinding.episodeDate;
                Intrinsics.checkNotNullExpressionValue(episodeDate, "episodeDate");
                episodeDate.setVisibility(8);
            } else if (APIHolder.INSTANCE.getUnixTimeMS() < item.getAirDate().longValue()) {
                CircularProgressIndicator episodeProgress3 = resultEpisodeLargeBinding.episodeProgress;
                Intrinsics.checkNotNullExpressionValue(episodeProgress3, "episodeProgress");
                episodeProgress3.setVisibility(8);
                ShapeableImageView episodePlayIcon2 = resultEpisodeLargeBinding.episodePlayIcon;
                Intrinsics.checkNotNullExpressionValue(episodePlayIcon2, "episodePlayIcon");
                episodePlayIcon2.setVisibility(8);
                ImageView episodeUpcomingIcon2 = resultEpisodeLargeBinding.episodeUpcomingIcon;
                Intrinsics.checkNotNullExpressionValue(episodeUpcomingIcon2, "episodeUpcomingIcon");
                episodeUpcomingIcon2.setVisibility(z ? 0 : 8);
                TextUtilKt.setText(resultEpisodeLargeBinding.episodeDate, TextUtilKt.txt(R.string.episode_upcoming_format, AccountManager.INSTANCE.secondsToReadable((int) ((item.getAirDate().longValue() - APIHolder.INSTANCE.getUnixTimeMS()) / 1000), "")));
            } else {
                ShapeableImageView episodePlayIcon3 = resultEpisodeLargeBinding.episodePlayIcon;
                Intrinsics.checkNotNullExpressionValue(episodePlayIcon3, "episodePlayIcon");
                episodePlayIcon3.setVisibility(0);
                ImageView episodeUpcomingIcon3 = resultEpisodeLargeBinding.episodeUpcomingIcon;
                Intrinsics.checkNotNullExpressionValue(episodeUpcomingIcon3, "episodeUpcomingIcon");
                episodeUpcomingIcon3.setVisibility(8);
                String str2 = SimpleDateFormat.getDateInstance(1, Locale.getDefault()).format(new Date(item.getAirDate().longValue()));
                TextView textView5 = resultEpisodeLargeBinding.episodeDate;
                Intrinsics.checkNotNull(str2);
                TextUtilKt.setText(textView5, TextUtilKt.txt(str2));
            }
            TextView textView6 = resultEpisodeLargeBinding.episodeRuntime;
            Integer runTime = item.getRunTime();
            TextUtilKt.setText(textView6, TextUtilKt.txtNull(runTime != null ? AccountManager.INSTANCE.secondsToReadable((int) (((long) runTime.intValue()) * 60), "") : null));
            if (Globals.INSTANCE.isLayout(5)) {
                resultEpisodeLargeBinding.episodePoster.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.EpisodeAdapter$$ExternalSyntheticLambda7
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        EpisodeAdapter.onBindContent$lambda$0$6(this.f$0, position, item, view2);
                    }
                });
                resultEpisodeLargeBinding.episodePoster.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.result.EpisodeAdapter$$ExternalSyntheticLambda8
                    @Override // android.view.View.OnLongClickListener
                    public final boolean onLongClick(View view2) {
                        return EpisodeAdapter.onBindContent$lambda$0$7(this.f$0, position, item, view2);
                    }
                });
            }
            itemView.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.EpisodeAdapter$$ExternalSyntheticLambda9
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.clickCallback.invoke(new EpisodeClickEvent(Integer.valueOf(position), 11, item));
                }
            });
            if (Globals.INSTANCE.isLayout(2)) {
                itemView.setFocusable(true);
                itemView.setFocusableInTouchMode(true);
            }
            itemView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.result.EpisodeAdapter$$ExternalSyntheticLambda10
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view2) {
                    return EpisodeAdapter.onBindContent$lambda$2(this.f$0, position, item, view2);
                }
            });
            return;
        }
        if (view instanceof ResultEpisodeBinding) {
            ResultEpisodeBinding resultEpisodeBinding = (ResultEpisodeBinding) view;
            resultEpisodeBinding.episodeHolder.getLayoutParams().width = Globals.INSTANCE.isLayout(6) ? UIHelper.INSTANCE.getToPx(400) : -1;
            PieFetchButton downloadButton2 = resultEpisodeBinding.downloadButton;
            Intrinsics.checkNotNullExpressionValue(downloadButton2, "downloadButton");
            downloadButton2.setVisibility(this.hasDownloadSupport ? 0 : 8);
            resultEpisodeBinding.downloadButton.setDefaultClickListener(new DownloadObjects.DownloadEpisodeCached(item.getName(), item.getPoster(), item.getEpisode(), item.getSeason(), item.getParentId(), item.getScore(), item.getDescription(), System.currentTimeMillis(), item.getId()), null, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.EpisodeAdapter$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return EpisodeAdapter.onBindContent$lambda$4$0(this.f$0, position, item, (DownloadClickEvent) obj);
                }
            });
            VideoDownloadManager.DownloadType downloadType2 = VideoDownloadManager.INSTANCE.getDownloadStatus().get(Integer.valueOf(item.getId()));
            resultEpisodeBinding.downloadButton.resetView();
            resultEpisodeBinding.downloadButton.setPersistentId(item.getId());
            resultEpisodeBinding.downloadButton.setStatus(downloadType2);
            if (item.getName() == null) {
                sb = new StringBuilder();
                sb.append(resultEpisodeBinding.episodeText.getContext().getString(R.string.episode));
                sb.append(' ');
                sb.append(item.getEpisode());
            } else {
                sb = new StringBuilder();
                sb.append(item.getEpisode());
                sb.append(". ");
                sb.append(item.getName());
            }
            String string3 = sb.toString();
            MaterialButton episodeFiller2 = resultEpisodeBinding.episodeFiller;
            Intrinsics.checkNotNullExpressionValue(episodeFiller2, "episodeFiller");
            episodeFiller2.setVisibility(Intrinsics.areEqual((Object) item.isFiller(), (Object) true) ? 0 : 8);
            resultEpisodeBinding.episodeText.setText(string3);
            resultEpisodeBinding.episodeText.setSelected(true);
            if (item.getVideoWatchState() == VideoWatchState.Watched) {
                resultEpisodeBinding.episodePlayIcon.setImageResource(R.drawable.ic_baseline_check_24);
                CircularProgressIndicator episodeProgress4 = resultEpisodeBinding.episodeProgress;
                Intrinsics.checkNotNullExpressionValue(episodeProgress4, "episodeProgress");
                episodeProgress4.setVisibility(8);
                Unit unit3 = Unit.INSTANCE;
            } else {
                long displayPosition2 = ResultFragmentKt.getDisplayPosition(item);
                int duration2 = (int) (item.getDuration() / 1000);
                int i2 = (int) (displayPosition2 / 1000);
                if (displayPosition2 >= item.getDuration() && displayPosition2 > 0) {
                    resultEpisodeBinding.episodePlayIcon.setImageResource(R.drawable.ic_baseline_check_24);
                    CircularProgressIndicator episodeProgress5 = resultEpisodeBinding.episodeProgress;
                    Intrinsics.checkNotNullExpressionValue(episodeProgress5, "episodeProgress");
                    episodeProgress5.setVisibility(8);
                    Unit unit4 = Unit.INSTANCE;
                } else {
                    resultEpisodeBinding.episodePlayIcon.setImageResource(R.drawable.play_button_transparent);
                    CircularProgressIndicator circularProgressIndicator2 = resultEpisodeBinding.episodeProgress;
                    circularProgressIndicator2.setMax(duration2);
                    circularProgressIndicator2.setProgress(i2);
                    Intrinsics.checkNotNull(circularProgressIndicator2);
                    circularProgressIndicator2.setVisibility((displayPosition2 > 0L ? 1 : (displayPosition2 == 0L ? 0 : -1)) > 0 ? 0 : 8);
                    Intrinsics.checkNotNull(circularProgressIndicator2);
                }
            }
            itemView.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.EpisodeAdapter$$ExternalSyntheticLambda12
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    EpisodeAdapter.onBindContent$lambda$4$2(this.f$0, position, item, view2);
                }
            });
            if (Globals.INSTANCE.isLayout(2)) {
                itemView.setFocusable(true);
                itemView.setFocusableInTouchMode(true);
            }
            itemView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.result.EpisodeAdapter$$ExternalSyntheticLambda13
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view2) {
                    return EpisodeAdapter.onBindContent$lambda$4$3(this.f$0, position, item, view2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindContent$lambda$0$0(EpisodeAdapter episodeAdapter, int i, ResultEpisode resultEpisode, DownloadClickEvent it) {
        Intrinsics.checkNotNullParameter(it, "it");
        int action = it.getAction();
        if (action == 4) {
            episodeAdapter.clickCallback.invoke(new EpisodeClickEvent(Integer.valueOf(i), 6, resultEpisode));
        } else if (action == 5) {
            episodeAdapter.clickCallback.invoke(new EpisodeClickEvent(Integer.valueOf(i), 7, resultEpisode));
        } else {
            episodeAdapter.downloadClickCallback.invoke(it);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindContent$lambda$0$2(boolean z, final ResultEpisodeLargeBinding resultEpisodeLargeBinding, final ImageRequest.Builder loadImage) {
        Intrinsics.checkNotNullParameter(loadImage, "$this$loadImage");
        if (z) {
            loadImage.error(new Function1() { // from class: com.lagradost.cloudstream3.ui.result.EpisodeAdapter$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return EpisodeAdapter.onBindContent$lambda$0$2$0(loadImage, resultEpisodeLargeBinding, (ImageRequest) obj);
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Image onBindContent$lambda$0$2$0(ImageRequest.Builder builder, ResultEpisodeLargeBinding resultEpisodeLargeBinding, ImageRequest it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Coroutines.INSTANCE.main(builder, new EpisodeAdapter$onBindContent$1$3$1$1(resultEpisodeLargeBinding, null));
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindContent$lambda$0$3$0(EpisodeAdapter episodeAdapter, int i, ResultEpisode resultEpisode, Ref.BooleanRef booleanRef, TextView textView, View view) {
        if (Globals.INSTANCE.isLayout(2)) {
            episodeAdapter.clickCallback.invoke(new EpisodeClickEvent(Integer.valueOf(i), 15, resultEpisode));
        } else {
            booleanRef.element = !booleanRef.element;
            textView.setMaxLines(booleanRef.element ? Integer.MAX_VALUE : 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindContent$lambda$0$6(EpisodeAdapter episodeAdapter, int i, ResultEpisode resultEpisode, View view) {
        episodeAdapter.clickCallback.invoke(new EpisodeClickEvent(Integer.valueOf(i), 11, resultEpisode));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindContent$lambda$0$7(EpisodeAdapter episodeAdapter, int i, ResultEpisode resultEpisode, View view) {
        episodeAdapter.clickCallback.invoke(new EpisodeClickEvent(Integer.valueOf(i), 12, resultEpisode));
        return true;
    }

    static final boolean onBindContent$lambda$2(EpisodeAdapter episodeAdapter, int i, ResultEpisode resultEpisode, View view) {
        episodeAdapter.clickCallback.invoke(new EpisodeClickEvent(Integer.valueOf(i), 10, resultEpisode));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindContent$lambda$4$0(EpisodeAdapter episodeAdapter, int i, ResultEpisode resultEpisode, DownloadClickEvent it) {
        Intrinsics.checkNotNullParameter(it, "it");
        int action = it.getAction();
        if (action == 4) {
            episodeAdapter.clickCallback.invoke(new EpisodeClickEvent(Integer.valueOf(i), 6, resultEpisode));
        } else if (action == 5) {
            episodeAdapter.clickCallback.invoke(new EpisodeClickEvent(Integer.valueOf(i), 7, resultEpisode));
        } else {
            episodeAdapter.downloadClickCallback.invoke(it);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindContent$lambda$4$2(EpisodeAdapter episodeAdapter, int i, ResultEpisode resultEpisode, View view) {
        episodeAdapter.clickCallback.invoke(new EpisodeClickEvent(Integer.valueOf(i), 11, resultEpisode));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindContent$lambda$4$3(EpisodeAdapter episodeAdapter, int i, ResultEpisode resultEpisode, View view) {
        episodeAdapter.clickCallback.invoke(new EpisodeClickEvent(Integer.valueOf(i), 10, resultEpisode));
        return true;
    }
}
