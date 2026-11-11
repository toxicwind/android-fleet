package com.lagradost.cloudstream3.ui.player;

import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.mediarouter.media.GlobalMediaRouter;
import coil3.util.UtilsKt;
import com.google.android.gms.cast.MediaTrack;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.mvvm.Resource;
import com.lagradost.cloudstream3.ui.result.ResultEpisode;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.ExtractorLinkType;
import com.lagradost.cloudstream3.utils.videoskip.SkipAPI;
import com.lagradost.cloudstream3.utils.videoskip.VideoSkipStamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import org.mozilla.javascript.ES6Iterator;
import org.mozilla.universalchardet.prober.HebrewProber;

/* JADX INFO: compiled from: PlayerGeneratorViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 `2\u00020\u0001:\u0001`B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010.\u001a\u00020&2\u0017\u0010/\u001a\u0013\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u001100¢\u0006\u0002\b1J\u0015\u0010=\u001a\u00020&2\b\u0010>\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010?J\u0006\u0010@\u001a\u00020&J\u0006\u0010A\u001a\u00020&J\r\u0010B\u001a\u0004\u0018\u000108¢\u0006\u0002\u0010CJ\r\u0010D\u001a\u0004\u0018\u000108¢\u0006\u0002\u0010CJ\u0006\u0010E\u001a\u00020&J\u000e\u0010F\u001a\u00020&2\u0006\u0010G\u001a\u00020\u000bJ\u001a\u0010H\u001a\u00020&2\n\u0010I\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010G\u001a\u00020\u000bJ\u0014\u0010J\u001a\u00020&2\f\u0010K\u001a\b\u0012\u0004\u0012\u00020!0\u0018J\u000e\u0010O\u001a\u00020&2\u0006\u0010P\u001a\u00020QJ\u000e\u0010[\u001a\u0002082\u0006\u0010\\\u001a\u00020!J\u0016\u0010]\u001a\u00020&2\u000e\b\u0002\u0010^\u001a\b\u0012\u0004\u0012\u00020_0\u0018R \u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R0\u0010\u0015\u001a$\u0012 \u0012\u001e\u0012\u001a\u0012\u0018\u0012\u0014\u0012\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u00190\u00180\u00170\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R3\u0010\u001c\u001a$\u0012 \u0012\u001e\u0012\u001a\u0012\u0018\u0012\u0014\u0012\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u00190\u00180\u00170\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR \u0010 \u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u00180\u00170\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\"\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u00180\u00170\u001d¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001fR \u0010$\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0%0\u00170\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010'\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0%0\u00170\u001d¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001fR \u0010)\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\u00170\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010,\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\u00170\u001d¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001fR\u0016\u00102\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u00103\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u001d¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u001fR\u0012\u00105\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0004\n\u0002\u00106R\u001a\u00107\u001a\u000208X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u0010\u0010L\u001a\u0004\u0018\u00010MX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010N\u001a\u0004\u0018\u00010MX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010R\u001a\b\u0012\u0004\u0012\u00020S0*X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR\u001a\u0010X\u001a\u000208X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010:\"\u0004\bZ\u0010<¨\u0006a"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/PlayerGeneratorViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "generator", "Lcom/lagradost/cloudstream3/ui/player/VideoGenerator;", "getGenerator", "()Lcom/lagradost/cloudstream3/ui/player/VideoGenerator;", "setGenerator", "(Lcom/lagradost/cloudstream3/ui/player/VideoGenerator;)V", "episodeIndex", "", "getEpisodeIndex", "()I", "setEpisodeIndex", "(I)V", ES6Iterator.VALUE_PROPERTY, "Lcom/lagradost/cloudstream3/ui/player/VideoState;", "state", "getState", "()Lcom/lagradost/cloudstream3/ui/player/VideoState;", "_currentLinks", "Landroidx/lifecycle/MutableLiveData;", "Lcom/lagradost/cloudstream3/ui/player/VideoLive;", "", "Lkotlin/Pair;", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "Lcom/lagradost/cloudstream3/ui/player/ExtractorUri;", "currentLinks", "Landroidx/lifecycle/LiveData;", "getCurrentLinks", "()Landroidx/lifecycle/LiveData;", "_currentSubtitles", "Lcom/lagradost/cloudstream3/ui/player/SubtitleData;", "currentSubtitles", "getCurrentSubtitles", "_loadingLinks", "Lcom/lagradost/cloudstream3/mvvm/Resource;", "", "loadingLinks", "getLoadingLinks", "_currentStamps", "", "Lcom/lagradost/cloudstream3/utils/videoskip/VideoSkipStamp;", "currentStamps", "getCurrentStamps", "modifyState", "op", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "_currentSubtitleYear", "currentSubtitleYear", "getCurrentSubtitleYear", "currentLoadingEpisodeId", "Ljava/lang/Integer;", "forceClearCache", "", "getForceClearCache", "()Z", "setForceClearCache", "(Z)V", "setSubtitleYear", "year", "(Ljava/lang/Integer;)V", "loadLinksPrev", "loadLinksNext", "hasNextEpisode", "()Ljava/lang/Boolean;", "hasPrevEpisode", "preLoadNextLinks", "loadThisEpisode", "index", "attachGenerator", "newGenerator", "addSubtitles", UtilsKt.SCHEME_FILE, "currentJob", "Lkotlinx/coroutines/Job;", "currentStampJob", "loadStamps", TypedValues.TransitionType.S_DURATION, "", "langFilterList", "", "getLangFilterList", "()Ljava/util/List;", "setLangFilterList", "(Ljava/util/List;)V", "filterSubByLang", "getFilterSubByLang", "setFilterSubByLang", "isValidSubtitle", MediaTrack.ROLE_SUBTITLE, "loadLinks", "sourceTypes", "Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PlayerGeneratorViewModel extends ViewModel {
    public static final String TAG = "PlayViewGen";
    private final MutableLiveData<VideoLive<Set<Pair<ExtractorLink, ExtractorUri>>>> _currentLinks;
    private final MutableLiveData<VideoLive<List<VideoSkipStamp>>> _currentStamps;
    private final MutableLiveData<Integer> _currentSubtitleYear;
    private final MutableLiveData<VideoLive<Set<SubtitleData>>> _currentSubtitles;
    private final MutableLiveData<VideoLive<Resource<Unit>>> _loadingLinks;
    private Job currentJob;
    private final LiveData<VideoLive<Set<Pair<ExtractorLink, ExtractorUri>>>> currentLinks;
    private Integer currentLoadingEpisodeId;
    private Job currentStampJob;
    private final LiveData<VideoLive<List<VideoSkipStamp>>> currentStamps;
    private final LiveData<Integer> currentSubtitleYear;
    private final LiveData<VideoLive<Set<SubtitleData>>> currentSubtitles;
    private volatile int episodeIndex;
    private boolean filterSubByLang;
    private boolean forceClearCache;
    private volatile VideoGenerator<?> generator;
    private List<String> langFilterList;
    private final LiveData<VideoLive<Resource<Unit>>> loadingLinks;
    private volatile VideoState state = new VideoState(null, null, null, null, null, 0, 31, null);

    public PlayerGeneratorViewModel() {
        MutableLiveData<VideoLive<Set<Pair<ExtractorLink, ExtractorUri>>>> mutableLiveData = new MutableLiveData<>(null);
        this._currentLinks = mutableLiveData;
        this.currentLinks = mutableLiveData;
        MutableLiveData<VideoLive<Set<SubtitleData>>> mutableLiveData2 = new MutableLiveData<>(null);
        this._currentSubtitles = mutableLiveData2;
        this.currentSubtitles = mutableLiveData2;
        MutableLiveData<VideoLive<Resource<Unit>>> mutableLiveData3 = new MutableLiveData<>();
        this._loadingLinks = mutableLiveData3;
        this.loadingLinks = mutableLiveData3;
        MutableLiveData<VideoLive<List<VideoSkipStamp>>> mutableLiveData4 = new MutableLiveData<>(null);
        this._currentStamps = mutableLiveData4;
        this.currentStamps = mutableLiveData4;
        MutableLiveData<Integer> mutableLiveData5 = new MutableLiveData<>(null);
        this._currentSubtitleYear = mutableLiveData5;
        this.currentSubtitleYear = mutableLiveData5;
        this.langFilterList = CollectionsKt.emptyList();
    }

    public final VideoGenerator<?> getGenerator() {
        return this.generator;
    }

    public final void setGenerator(VideoGenerator<?> videoGenerator) {
        this.generator = videoGenerator;
    }

    public final int getEpisodeIndex() {
        return this.episodeIndex;
    }

    public final void setEpisodeIndex(int i) {
        this.episodeIndex = i;
    }

    public final VideoState getState() {
        return this.state;
    }

    public final LiveData<VideoLive<Set<Pair<ExtractorLink, ExtractorUri>>>> getCurrentLinks() {
        return this.currentLinks;
    }

    public final LiveData<VideoLive<Set<SubtitleData>>> getCurrentSubtitles() {
        return this.currentSubtitles;
    }

    public final LiveData<VideoLive<Resource<Unit>>> getLoadingLinks() {
        return this.loadingLinks;
    }

    public final LiveData<VideoLive<List<VideoSkipStamp>>> getCurrentStamps() {
        return this.currentStamps;
    }

    public final synchronized void modifyState(Function1<? super VideoState, VideoState> op) {
        Intrinsics.checkNotNullParameter(op, "op");
        VideoState videoState = this.state;
        this.state = op.invoke(videoState);
        if (this.state.getInstance() != videoState.getInstance()) {
            this._currentSubtitles.postValue(new VideoLive<>(this.state.getSubtitles(), this.state.getInstance()));
            this._currentStamps.postValue(new VideoLive<>(this.state.getStamps(), this.state.getInstance()));
            this._currentLinks.postValue(new VideoLive<>(this.state.getLinks(), this.state.getInstance()));
            this._loadingLinks.postValue(new VideoLive<>(this.state.getLoading(), this.state.getInstance()));
            return;
        }
        if (this.state.getLinks() != videoState.getLinks()) {
            this._currentLinks.postValue(new VideoLive<>(this.state.getLinks(), this.state.getInstance()));
        }
        if (this.state.getStamps() != videoState.getStamps()) {
            this._currentStamps.postValue(new VideoLive<>(this.state.getStamps(), this.state.getInstance()));
        }
        if (this.state.getSubtitles() != videoState.getSubtitles()) {
            this._currentSubtitles.postValue(new VideoLive<>(this.state.getSubtitles(), this.state.getInstance()));
        }
        if (!Intrinsics.areEqual(this.state.getLoading(), videoState.getLoading())) {
            this._loadingLinks.postValue(new VideoLive<>(this.state.getLoading(), this.state.getInstance()));
        }
    }

    public final LiveData<Integer> getCurrentSubtitleYear() {
        return this.currentSubtitleYear;
    }

    public final boolean getForceClearCache() {
        return this.forceClearCache;
    }

    public final void setForceClearCache(boolean z) {
        this.forceClearCache = z;
    }

    public final void setSubtitleYear(Integer year) {
        this._currentSubtitleYear.postValue(year);
    }

    public final void loadLinksPrev() {
        Log.i(TAG, "loadLinksPrev");
        VideoGenerator<?> videoGenerator = this.generator;
        if (videoGenerator == null || !videoGenerator.hasPrev(this.episodeIndex)) {
            return;
        }
        this.episodeIndex++;
        loadLinks$default(this, null, 1, null);
    }

    public final void loadLinksNext() {
        Log.i(TAG, "loadLinksNext");
        VideoGenerator<?> videoGenerator = this.generator;
        if (videoGenerator == null || !videoGenerator.hasNext(this.episodeIndex)) {
            return;
        }
        this.episodeIndex++;
        loadLinks$default(this, null, 1, null);
    }

    public final Boolean hasNextEpisode() {
        VideoGenerator<?> videoGenerator = this.generator;
        if (videoGenerator != null) {
            return Boolean.valueOf(videoGenerator.hasNext(this.episodeIndex));
        }
        return null;
    }

    public final Boolean hasPrevEpisode() {
        VideoGenerator<?> videoGenerator = this.generator;
        if (videoGenerator != null) {
            return Boolean.valueOf(videoGenerator.hasPrev(this.episodeIndex));
        }
        return null;
    }

    public final void preLoadNextLinks() {
        VideoGenerator<?> videoGenerator = this.generator;
        Integer id = videoGenerator != null ? videoGenerator.getId(this.episodeIndex) : null;
        if (Intrinsics.areEqual(id, this.currentLoadingEpisodeId)) {
            return;
        }
        Log.i(TAG, "preLoadNextLinks");
        Job job = this.currentJob;
        if (job != null) {
            Job.CC.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.currentLoadingEpisodeId = id;
        this.currentJob = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C08601(id, null), 3, null);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.PlayerGeneratorViewModel$preLoadNextLinks$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlayerGeneratorViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.PlayerGeneratorViewModel$preLoadNextLinks$1", f = "PlayerGeneratorViewModel.kt", i = {}, l = {HebrewProber.FINAL_TSADI}, m = "invokeSuspend", n = {}, nl = {GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED}, s = {}, v = 2)
    static final class C08601 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Integer $id;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08601(Integer num, Continuation<? super C08601> continuation) {
            super(2, continuation);
            this.$id = num;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PlayerGeneratorViewModel.this.new C08601(this.$id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08601) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            VideoGenerator<?> generator;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    VideoGenerator<?> generator2 = PlayerGeneratorViewModel.this.getGenerator();
                    if (generator2 != null && generator2.getHasCache() && (generator = PlayerGeneratorViewModel.this.getGenerator()) != null && generator.hasNext(PlayerGeneratorViewModel.this.getEpisodeIndex())) {
                        this.label = 1;
                        if (ArchComponentExtKt.safeApiCall(new C01741(PlayerGeneratorViewModel.this, null), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                if (Intrinsics.areEqual(PlayerGeneratorViewModel.this.currentLoadingEpisodeId, this.$id)) {
                }
            } catch (Throwable th) {
                try {
                    ArchComponentExtKt.logError(th);
                } finally {
                    if (Intrinsics.areEqual(PlayerGeneratorViewModel.this.currentLoadingEpisodeId, this.$id)) {
                        PlayerGeneratorViewModel.this.currentLoadingEpisodeId = null;
                    }
                }
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.PlayerGeneratorViewModel$preLoadNextLinks$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: PlayerGeneratorViewModel.kt */
        @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u000b\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.PlayerGeneratorViewModel$preLoadNextLinks$1$1", f = "PlayerGeneratorViewModel.kt", i = {}, l = {HebrewProber.NORMAL_TSADI}, m = "invokeSuspend", n = {}, nl = {253}, s = {}, v = 2)
        static final class C01741 extends SuspendLambda implements Function1<Continuation<? super Boolean>, Object> {
            int label;
            final /* synthetic */ PlayerGeneratorViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01741(PlayerGeneratorViewModel playerGeneratorViewModel, Continuation<? super C01741> continuation) {
                super(1, continuation);
                this.this$0 = playerGeneratorViewModel;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Continuation<?> continuation) {
                return new C01741(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Continuation<? super Boolean> continuation) {
                return ((C01741) create(continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    VideoGenerator<?> generator = this.this$0.getGenerator();
                    if (generator == null) {
                        return null;
                    }
                    Set<ExtractorLinkType> loadtype_inapp = IGeneratorKt.getLOADTYPE_INAPP();
                    int episodeIndex = this.this$0.getEpisodeIndex() + 1;
                    this.label = 1;
                    obj = generator.generateLinks(false, loadtype_inapp, new Function1() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGeneratorViewModel$preLoadNextLinks$1$1$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return Unit.INSTANCE;
                        }
                    }, new Function1() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGeneratorViewModel$preLoadNextLinks$1$1$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return Unit.INSTANCE;
                        }
                    }, episodeIndex, false, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return (Boolean) obj;
            }
        }
    }

    public final void loadThisEpisode(int index) {
        this.episodeIndex = index;
        loadLinks$default(this, null, 1, null);
    }

    public final void attachGenerator(VideoGenerator<?> newGenerator, int index) {
        Intrinsics.checkNotNullParameter(newGenerator, "newGenerator");
        Log.i(TAG, "attachGenerator with generator=" + newGenerator + " and index=" + index);
        this.generator = newGenerator;
        this.episodeIndex = index;
    }

    public final void addSubtitles(Set<SubtitleData> file) {
        Intrinsics.checkNotNullParameter(file, "file");
        ArrayList arrayList = new ArrayList();
        for (Object obj : file) {
            if (isValidSubtitle((SubtitleData) obj)) {
                arrayList.add(obj);
            }
        }
        final ArrayList arrayList2 = arrayList;
        if (arrayList2.isEmpty()) {
            return;
        }
        modifyState(new Function1() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGeneratorViewModel$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return PlayerGeneratorViewModel.addSubtitles$lambda$0(arrayList2, (VideoState) obj2);
            }
        });
    }

    static final VideoState addSubtitles$lambda$0(List list, VideoState modifyState) {
        Intrinsics.checkNotNullParameter(modifyState, "$this$modifyState");
        return modifyState.addSubtitleData(list);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.PlayerGeneratorViewModel$loadStamps$1, reason: invalid class name */
    /* JADX INFO: compiled from: PlayerGeneratorViewModel.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/player/PlayerGeneratorViewModel;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.PlayerGeneratorViewModel$loadStamps$1", f = "PlayerGeneratorViewModel.kt", i = {0, 0, 0, 0}, l = {300}, m = "invokeSuspend", n = {"genState", "meta", "page", "id"}, nl = {308}, s = {"L$0", "L$1", "L$2", "L$3"}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, PlayerGeneratorViewModel, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $duration;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(long j, Continuation<? super AnonymousClass1> continuation) {
            super(3, continuation);
            this.$duration = j;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, PlayerGeneratorViewModel playerGeneratorViewModel, Continuation<? super Unit> continuation) {
            return PlayerGeneratorViewModel.this.new AnonymousClass1(this.$duration, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            final Integer num;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                GeneratorState generatorState = PlayerGeneratorViewModel.this.getState().getGeneratorState();
                if (generatorState == null) {
                    return Unit.INSTANCE;
                }
                Object meta = generatorState.getMeta();
                LoadResponse response = generatorState.getResponse();
                Integer id = generatorState.getId();
                if (response == null || !(meta instanceof ResultEpisode)) {
                    return Unit.INSTANCE;
                }
                SkipAPI.Companion companion = SkipAPI.INSTANCE;
                ResultEpisode resultEpisode = (ResultEpisode) meta;
                long j = this.$duration;
                Boolean boolHasNextEpisode = PlayerGeneratorViewModel.this.hasNextEpisode();
                boolean zBooleanValue = boolHasNextEpisode != null ? boolHasNextEpisode.booleanValue() : false;
                this.L$0 = SpillingKt.nullOutSpilledVariable(generatorState);
                this.L$1 = SpillingKt.nullOutSpilledVariable(meta);
                this.L$2 = SpillingKt.nullOutSpilledVariable(response);
                this.L$3 = id;
                this.label = 1;
                obj = companion.videoStamps(response, resultEpisode, j, zBooleanValue, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                num = id;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                num = (Integer) this.L$3;
                ResultKt.throwOnFailure(obj);
            }
            final List list = (List) obj;
            PlayerGeneratorViewModel.this.modifyState(new Function1() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGeneratorViewModel$loadStamps$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return PlayerGeneratorViewModel.AnonymousClass1.invokeSuspend$lambda$0(num, list, (VideoState) obj2);
                }
            });
            return Unit.INSTANCE;
        }

        static final VideoState invokeSuspend$lambda$0(Integer num, List list, VideoState videoState) {
            GeneratorState generatorState = videoState.getGeneratorState();
            return !Intrinsics.areEqual(num, generatorState != null ? generatorState.getId() : null) ? videoState : videoState.setVideoSkipStamp(list);
        }
    }

    public final void loadStamps(long duration) {
        this.currentStampJob = Coroutines.INSTANCE.ioSafe(this, new AnonymousClass1(duration, null));
    }

    public final List<String> getLangFilterList() {
        return this.langFilterList;
    }

    public final void setLangFilterList(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.langFilterList = list;
    }

    public final boolean getFilterSubByLang() {
        return this.filterSubByLang;
    }

    public final void setFilterSubByLang(boolean z) {
        this.filterSubByLang = z;
    }

    public final boolean isValidSubtitle(SubtitleData subtitle) {
        Intrinsics.checkNotNullParameter(subtitle, "subtitle");
        if (this.langFilterList.isEmpty() || !this.filterSubByLang || subtitle.getOrigin() != SubtitleOrigin.URL) {
            return true;
        }
        List<String> list = this.langFilterList;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (StringsKt.contains((CharSequence) subtitle.getOriginalName(), (CharSequence) it.next(), true)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void loadLinks$default(PlayerGeneratorViewModel playerGeneratorViewModel, Set set, int i, Object obj) {
        if ((i & 1) != 0) {
            set = IGeneratorKt.getLOADTYPE_INAPP();
        }
        playerGeneratorViewModel.loadLinks(set);
    }

    public final void loadLinks(Set<? extends ExtractorLinkType> sourceTypes) {
        Intrinsics.checkNotNullParameter(sourceTypes, "sourceTypes");
        Log.i(TAG, "loadLinks with generator=" + this.generator + " and index=" + this.episodeIndex);
        Job job = this.currentJob;
        if (job != null) {
            Job.CC.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        final int i = this.episodeIndex;
        modifyState(new Function1() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGeneratorViewModel$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PlayerGeneratorViewModel.loadLinks$lambda$0(this.f$0, i, (VideoState) obj);
            }
        });
        this.currentJob = ArchComponentExtKt.launchSafe$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass2(sourceTypes, i, null), 3, null);
    }

    static final VideoState loadLinks$lambda$0(PlayerGeneratorViewModel playerGeneratorViewModel, int i, VideoState modifyState) {
        GeneratorState generatorState;
        Intrinsics.checkNotNullParameter(modifyState, "$this$modifyState");
        Resource.Loading loading = new Resource.Loading(null, 1, null);
        VideoGenerator<?> videoGenerator = playerGeneratorViewModel.generator;
        if (videoGenerator != null) {
            Object orNull = CollectionsKt.getOrNull(videoGenerator.getVideos(), i);
            Object orNull2 = CollectionsKt.getOrNull(videoGenerator.getVideos(), i + 1);
            Integer id = videoGenerator.getId(i);
            RepoLinkGenerator repoLinkGenerator = videoGenerator instanceof RepoLinkGenerator ? (RepoLinkGenerator) videoGenerator : null;
            generatorState = new GeneratorState(orNull, orNull2, videoGenerator.getVideos(), repoLinkGenerator != null ? repoLinkGenerator.getPage() : null, i, id);
        } else {
            generatorState = null;
        }
        return new VideoState(null, null, null, loading, generatorState, modifyState.getInstance() + 1, 7, null);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.PlayerGeneratorViewModel$loadLinks$2, reason: invalid class name */
    /* JADX INFO: compiled from: PlayerGeneratorViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.PlayerGeneratorViewModel$loadLinks$2", f = "PlayerGeneratorViewModel.kt", i = {0}, l = {361}, m = "invokeSuspend", n = {"$this$launchSafe"}, nl = {382}, s = {"L$0"}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $index;
        final /* synthetic */ Set<ExtractorLinkType> $sourceTypes;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(Set<? extends ExtractorLinkType> set, int i, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$sourceTypes = set;
            this.$index = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = PlayerGeneratorViewModel.this.new AnonymousClass2(this.$sourceTypes, this.$index, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.L$0 = coroutineScope;
                this.label = 1;
                obj = ArchComponentExtKt.safeApiCall(new PlayerGeneratorViewModel$loadLinks$2$loadingState$1(PlayerGeneratorViewModel.this, this.$sourceTypes, this.$index, coroutineScope, null), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            final Resource resource = (Resource) obj;
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
                return Unit.INSTANCE;
            }
            PlayerGeneratorViewModel.this.modifyState(new Function1() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGeneratorViewModel$loadLinks$2$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return PlayerGeneratorViewModel.AnonymousClass2.invokeSuspend$lambda$0(coroutineScope, resource, (VideoState) obj2);
                }
            });
            return Unit.INSTANCE;
        }

        static final VideoState invokeSuspend$lambda$0(CoroutineScope coroutineScope, Resource resource, VideoState videoState) {
            return (CoroutineScopeKt.isActive(coroutineScope) && (videoState.getLoading() instanceof Resource.Loading)) ? VideoState.copy$default(videoState, null, null, null, resource, null, 0, 55, null) : videoState;
        }
    }
}
