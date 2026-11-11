package com.lagradost.cloudstream3.ui.result;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.mediarouter.media.GlobalMediaRouter;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.ParCollectionsKt;
import com.lagradost.cloudstream3.Score;
import com.lagradost.cloudstream3.mvvm.Resource;
import com.lagradost.cloudstream3.syncproviders.AccountManager;
import com.lagradost.cloudstream3.syncproviders.SyncAPI;
import com.lagradost.cloudstream3.syncproviders.SyncRepo;
import com.lagradost.cloudstream3.ui.SyncWatchType;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.SyncUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: SyncViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 I2\u00020\u0001:\u0001IB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00160\u0018J\u000e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0002J\u0006\u0010\u001f\u001a\u00020 J\u0018\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00162\u0006\u0010$\u001a\u00020\u0016H\u0002J\u001c\u0010%\u001a\u00020\"2\u0014\u0010&\u001a\u0010\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0018J\u0012\u0010'\u001a\u00020\"2\b\u0010$\u001a\u0004\u0018\u00010\u0016H\u0002J\u0012\u0010(\u001a\u00020\"2\b\u0010$\u001a\u0004\u0018\u00010\u0016H\u0002J\u0010\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\u0016J\u000e\u00102\u001a\u00020 2\u0006\u00103\u001a\u000204J\u000e\u00105\u001a\u00020 2\u0006\u00106\u001a\u000204J\u0010\u00107\u001a\u00020 2\b\u00108\u001a\u0004\u0018\u000109J\u000e\u0010:\u001a\u00020 2\u0006\u0010;\u001a\u000204J\u0006\u0010<\u001a\u000200J\u000e\u0010=\u001a\u00020 2\u0006\u0010>\u001a\u000204J\u001e\u0010?\u001a\u0002002\u0014\u0010@\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00110AH\u0002J\u0006\u0010B\u001a\u000200J\b\u0010C\u001a\u000200H\u0002J\u0010\u0010D\u001a\u0004\u0018\u00010\u00162\u0006\u0010D\u001a\u00020\u0016J\u0016\u0010E\u001a\u00020 2\u0006\u0010D\u001a\u00020\u00162\u0006\u0010F\u001a\u00020\u0016J\u0006\u0010G\u001a\u00020 J\u0006\u0010H\u001a\u00020 R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u001c\u0010\b\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\f\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n0\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u0012\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\n0\r¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u001a\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00160\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a0\r¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u000fR \u0010)\u001a\b\u0012\u0004\u0012\u00020\u00160*X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.¨\u0006J"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/SyncViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "repos", "", "Lcom/lagradost/cloudstream3/syncproviders/SyncRepo;", "[Lcom/lagradost/cloudstream3/syncproviders/SyncRepo;", "_metaResponse", "Landroidx/lifecycle/MutableLiveData;", "Lcom/lagradost/cloudstream3/mvvm/Resource;", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$SyncResult;", TtmlNode.TAG_METADATA, "Landroidx/lifecycle/LiveData;", "getMetadata", "()Landroidx/lifecycle/LiveData;", "_userDataResponse", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$AbstractSyncStatus;", "userData", "getUserData", "syncs", "", "", "getSyncs", "", "_currentSynced", "", "Lcom/lagradost/cloudstream3/ui/result/CurrentSynced;", "synced", "getSynced", "getMissing", "updateSynced", "", "addSync", "", "idPrefix", "id", "addSyncs", "map", "setMalId", "setAniListId", "hasAddedFromUrl", "Ljava/util/HashSet;", "getHasAddedFromUrl", "()Ljava/util/HashSet;", "setHasAddedFromUrl", "(Ljava/util/HashSet;)V", "addFromUrl", "Lkotlinx/coroutines/Job;", ImagesContract.URL, "setEpisodesDelta", "delta", "", "setEpisodes", "episodes", "setScore", "score", "Lcom/lagradost/cloudstream3/Score;", "setStatus", "which", "publishUserData", "modifyMaxEpisode", "episodeNum", "modifyData", "update", "Lkotlin/Function1;", "updateUserData", "updateMetadata", "syncName", "setSync", "syncId", "clear", "updateMetaAndUser", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SyncViewModel extends ViewModel {
    public static final String TAG = "SYNCVM";
    private final MutableLiveData<List<CurrentSynced>> _currentSynced;
    private final MutableLiveData<Resource<SyncAPI.SyncResult>> _metaResponse;
    private final MutableLiveData<Resource<SyncAPI.AbstractSyncStatus>> _userDataResponse;
    private HashSet<String> hasAddedFromUrl;
    private final LiveData<Resource<SyncAPI.SyncResult>> metadata;
    private final SyncRepo[] repos = AccountManager.INSTANCE.getSyncApis();
    private final LiveData<List<CurrentSynced>> synced;
    private final Map<String, String> syncs;
    private final LiveData<Resource<SyncAPI.AbstractSyncStatus>> userData;

    public SyncViewModel() {
        MutableLiveData<Resource<SyncAPI.SyncResult>> mutableLiveData = new MutableLiveData<>(null);
        this._metaResponse = mutableLiveData;
        this.metadata = mutableLiveData;
        MutableLiveData<Resource<SyncAPI.AbstractSyncStatus>> mutableLiveData2 = new MutableLiveData<>(null);
        this._userDataResponse = mutableLiveData2;
        this.userData = mutableLiveData2;
        this.syncs = new LinkedHashMap();
        MutableLiveData<List<CurrentSynced>> mutableLiveData3 = new MutableLiveData<>(getMissing());
        this._currentSynced = mutableLiveData3;
        this.synced = mutableLiveData3;
        this.hasAddedFromUrl = new HashSet<>();
    }

    public final LiveData<Resource<SyncAPI.SyncResult>> getMetadata() {
        return this.metadata;
    }

    public final LiveData<Resource<SyncAPI.AbstractSyncStatus>> getUserData() {
        return this.userData;
    }

    public final Map<String, String> getSyncs() {
        return this.syncs;
    }

    public final LiveData<List<CurrentSynced>> getSynced() {
        return this.synced;
    }

    private final List<CurrentSynced> getMissing() {
        SyncRepo[] syncRepoArr = this.repos;
        ArrayList arrayList = new ArrayList(syncRepoArr.length);
        for (SyncRepo syncRepo : syncRepoArr) {
            arrayList.add(new CurrentSynced(syncRepo.getName(), syncRepo.getIdPrefix(), this.syncs.containsKey(syncRepo.getIdPrefix()), syncRepo.authUser() != null, syncRepo.getIcon()));
        }
        return arrayList;
    }

    public final void updateSynced() {
        Log.i(TAG, "updateSynced");
        this._currentSynced.postValue(getMissing());
    }

    private final boolean addSync(String idPrefix, String id) {
        if (Intrinsics.areEqual(this.syncs.get(idPrefix), id)) {
            return false;
        }
        Log.i(TAG, "addSync " + idPrefix + " = " + id);
        this.syncs.put(idPrefix, id);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean setMalId(String id) {
        String idPrefix = AccountManager.INSTANCE.getMalApi().getIdPrefix();
        if (id == null) {
            return false;
        }
        return addSync(idPrefix, id);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean setAniListId(String id) {
        String idPrefix = AccountManager.INSTANCE.getAniListApi().getIdPrefix();
        if (id == null) {
            return false;
        }
        return addSync(idPrefix, id);
    }

    public final HashSet<String> getHasAddedFromUrl() {
        return this.hasAddedFromUrl;
    }

    public final void setHasAddedFromUrl(HashSet<String> hashSet) {
        Intrinsics.checkNotNullParameter(hashSet, "<set-?>");
        this.hasAddedFromUrl = hashSet;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.SyncViewModel$addFromUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: SyncViewModel.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/result/SyncViewModel;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.SyncViewModel$addFromUrl$1", f = "SyncViewModel.kt", i = {}, l = {116}, m = "invokeSuspend", n = {}, nl = {117}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, SyncViewModel, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $url;
        int label;
        final /* synthetic */ SyncViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, SyncViewModel syncViewModel, Continuation<? super AnonymousClass1> continuation) {
            super(3, continuation);
            this.$url = str;
            this.this$0 = syncViewModel;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, SyncViewModel syncViewModel, Continuation<? super Unit> continuation) {
            return new AnonymousClass1(this.$url, this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Log.i(SyncViewModel.TAG, "addFromUrl = " + this.$url);
                if (this.$url == null || this.this$0.getHasAddedFromUrl().contains(this.$url)) {
                    return Unit.INSTANCE;
                }
                if (!StringsKt.startsWith$default(this.$url, "http", false, 2, (Object) null)) {
                    return Unit.INSTANCE;
                }
                this.label = 1;
                obj = SyncUtil.INSTANCE.getIdsFromUrl(this.$url, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            Pair pair = (Pair) obj;
            if (pair != null) {
                SyncViewModel syncViewModel = this.this$0;
                String str = this.$url;
                String str2 = (String) pair.component1();
                String str3 = (String) pair.component2();
                syncViewModel.getHasAddedFromUrl().add(str);
                syncViewModel.setMalId(str2);
                syncViewModel.setAniListId(str3);
                syncViewModel.updateSynced();
                if (str2 != null || str3 != null) {
                    Log.i(SyncViewModel.TAG, "addFromUrl->updateMetaAndUser " + str2 + ' ' + str3);
                    syncViewModel.updateMetaAndUser();
                }
            }
            return Unit.INSTANCE;
        }
    }

    public final Job addFromUrl(String url) {
        return Coroutines.INSTANCE.ioSafe(this, new AnonymousClass1(url, this, null));
    }

    public final void setEpisodesDelta(int delta) {
        Integer watchedEpisodes;
        Log.i(TAG, "setEpisodesDelta = " + delta);
        Resource<SyncAPI.AbstractSyncStatus> value = this.userData.getValue();
        if (!(value instanceof Resource.Success) || (watchedEpisodes = ((SyncAPI.AbstractSyncStatus) ((Resource.Success) value).getValue()).getWatchedEpisodes()) == null) {
            return;
        }
        setEpisodes(watchedEpisodes.intValue() + delta);
    }

    public final void setEpisodes(int episodes) {
        Integer totalEpisodes;
        int iIntValue;
        Log.i(TAG, "setEpisodes = " + episodes);
        if (episodes < 0) {
            return;
        }
        Resource<SyncAPI.SyncResult> value = this.metadata.getValue();
        if ((value instanceof Resource.Success) && (totalEpisodes = ((SyncAPI.SyncResult) ((Resource.Success) value).getValue()).getTotalEpisodes()) != null && episodes > (iIntValue = totalEpisodes.intValue())) {
            setEpisodes(iIntValue);
            return;
        }
        Resource<SyncAPI.AbstractSyncStatus> value2 = this.userData.getValue();
        if (value2 instanceof Resource.Success) {
            Resource.Success success = (Resource.Success) value2;
            ((SyncAPI.AbstractSyncStatus) success.getValue()).setWatchedEpisodes(Integer.valueOf(episodes));
            this._userDataResponse.postValue(new Resource.Success(success.getValue()));
        }
    }

    public final void setScore(Score score) {
        Log.i(TAG, "setScore = " + score);
        Resource<SyncAPI.AbstractSyncStatus> value = this.userData.getValue();
        if (value instanceof Resource.Success) {
            Resource.Success success = (Resource.Success) value;
            ((SyncAPI.AbstractSyncStatus) success.getValue()).setScore(score);
            this._userDataResponse.postValue(new Resource.Success(success.getValue()));
        }
    }

    public final void setStatus(int which) {
        Log.i(TAG, "setStatus = " + which);
        if (which < -1 || which > 5) {
            return;
        }
        Resource<SyncAPI.AbstractSyncStatus> value = this.userData.getValue();
        if (value instanceof Resource.Success) {
            Resource.Success success = (Resource.Success) value;
            ((SyncAPI.AbstractSyncStatus) success.getValue()).setStatus(SyncWatchType.INSTANCE.fromInternalId(Integer.valueOf(which)));
            this._userDataResponse.postValue(new Resource.Success(success.getValue()));
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.SyncViewModel$publishUserData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SyncViewModel.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/result/SyncViewModel;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.SyncViewModel$publishUserData$1", f = "SyncViewModel.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {188}, m = "invokeSuspend", n = {"user", "$this$forEach$iv", "element$iv", "prefix", "id", "$i$f$forEach", "$i$a$-forEach-SyncViewModel$publishUserData$1$1"}, nl = {189}, s = {"L$0", "L$1", "L$4", "L$5", "L$6", "I$0", "I$1"}, v = 2)
    static final class C08981 extends SuspendLambda implements Function3<CoroutineScope, SyncViewModel, Continuation<? super Unit>, Object> {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;

        C08981(Continuation<? super C08981> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, SyncViewModel syncViewModel, Continuation<? super Unit> continuation) {
            return SyncViewModel.this.new C08981(continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:15:0x009f  */
        /* JADX WARN: Code duplicated, block: B:18:0x00ae A[LOOP:0: B:14:0x009d->B:18:0x00ae, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:21:0x00b5  */
        /* JADX WARN: Code duplicated, block: B:23:0x00e8 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:29:0x00ab A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:30:0x00b2 A[SYNTHETIC] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x00e6 -> B:24:0x00e9). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:15:0x009f
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r18) {
            /*
                Method dump skipped, instruction units count: 246
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.ui.result.SyncViewModel.C08981.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final Job publishUserData() {
        return Coroutines.INSTANCE.ioSafe(this, new C08981(null));
    }

    public final void modifyMaxEpisode(final int episodeNum) {
        Log.i(TAG, "modifyMaxEpisode = " + episodeNum);
        modifyData(new Function1() { // from class: com.lagradost.cloudstream3.ui.result.SyncViewModel$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SyncViewModel.modifyMaxEpisode$lambda$0(episodeNum, (SyncAPI.AbstractSyncStatus) obj);
            }
        });
    }

    static final SyncAPI.AbstractSyncStatus modifyMaxEpisode$lambda$0(int i, SyncAPI.AbstractSyncStatus status) {
        Intrinsics.checkNotNullParameter(status, "status");
        Integer watchedEpisodes = status.getWatchedEpisodes();
        if (watchedEpisodes == null) {
            return null;
        }
        status.setWatchedEpisodes(Integer.valueOf(Math.max(i, watchedEpisodes.intValue())));
        return status;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.SyncViewModel$modifyData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SyncViewModel.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/result/SyncViewModel;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.SyncViewModel$modifyData$1", f = "SyncViewModel.kt", i = {}, l = {208}, m = "invokeSuspend", n = {}, nl = {216}, s = {}, v = 2)
    static final class C08971 extends SuspendLambda implements Function3<CoroutineScope, SyncViewModel, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<SyncAPI.AbstractSyncStatus, SyncAPI.AbstractSyncStatus> $update;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C08971(Function1<? super SyncAPI.AbstractSyncStatus, ? extends SyncAPI.AbstractSyncStatus> function1, Continuation<? super C08971> continuation) {
            super(3, continuation);
            this.$update = function1;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, SyncViewModel syncViewModel, Continuation<? super Unit> continuation) {
            return SyncViewModel.this.new C08971(this.$update, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.SyncViewModel$modifyData$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SyncViewModel.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010&\n\u0002\u0010\u000e\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004H\n"}, d2 = {"<anonymous>", "Lkotlin/Result;", "", "<destruct>", "", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.SyncViewModel$modifyData$1$1", f = "SyncViewModel.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, l = {211, 213}, m = "invokeSuspend", n = {"<destruct>", "prefix", "id", "repo", "$i$a$-let-SyncViewModel$modifyData$1$1$2", "<destruct>", "prefix", "id", "repo", "result", "$i$a$-let-SyncViewModel$modifyData$1$1$2"}, nl = {211, 209}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0"}, v = 2)
        static final class C01771 extends SuspendLambda implements Function2<Map.Entry<? extends String, ? extends String>, Continuation<? super Result<? extends Boolean>>, Object> {
            final /* synthetic */ Function1<SyncAPI.AbstractSyncStatus, SyncAPI.AbstractSyncStatus> $update;
            int I$0;
            /* synthetic */ Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            Object L$4;
            int label;
            final /* synthetic */ SyncViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C01771(SyncViewModel syncViewModel, Function1<? super SyncAPI.AbstractSyncStatus, ? extends SyncAPI.AbstractSyncStatus> function1, Continuation<? super C01771> continuation) {
                super(2, continuation);
                this.this$0 = syncViewModel;
                this.$update = function1;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C01771 c01771 = new C01771(this.this$0, this.$update, continuation);
                c01771.L$0 = obj;
                return c01771;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(Map.Entry<? extends String, ? extends String> entry, Continuation<? super Result<? extends Boolean>> continuation) {
                return invoke2((Map.Entry<String, String>) entry, (Continuation<? super Result<Boolean>>) continuation);
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(Map.Entry<String, String> entry, Continuation<? super Result<Boolean>> continuation) {
                return ((C01771) create(entry, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code restructure failed: missing block: B:32:0x00fe, code lost:
            
                if (r12 == r1) goto L33;
             */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r13) {
                /*
                    Method dump skipped, instruction units count: 263
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.ui.result.SyncViewModel.C08971.C01771.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (ParCollectionsKt.amap(SyncViewModel.this.syncs, new C01771(SyncViewModel.this, this.$update, null), this) == coroutine_suspended) {
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
    }

    private final Job modifyData(Function1<? super SyncAPI.AbstractSyncStatus, ? extends SyncAPI.AbstractSyncStatus> update) {
        return Coroutines.INSTANCE.ioSafe(this, new C08971(update, null));
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.SyncViewModel$updateUserData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SyncViewModel.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/result/SyncViewModel;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.SyncViewModel$updateUserData$1", f = "SyncViewModel.kt", i = {0, 0, 0}, l = {224}, m = "invokeSuspend", n = {"prefix", "id", "$i$a$-firstNotNullOfOrNull-SyncViewModel$updateUserData$1$status$1"}, nl = {223}, s = {"L$2", "L$3", "I$0"}, v = 2)
    static final class C09001 extends SuspendLambda implements Function3<CoroutineScope, SyncViewModel, Continuation<? super Unit>, Object> {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        C09001(Continuation<? super C09001> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, SyncViewModel syncViewModel, Continuation<? super Unit> continuation) {
            return SyncViewModel.this.new C09001(continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:13:0x007a  */
        /* JADX WARN: Code duplicated, block: B:16:0x0087 A[LOOP:0: B:12:0x0078->B:16:0x0087, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:36:0x008b A[EDGE_INSN: B:36:0x008b->B:18:0x008b BREAK  A[LOOP:0: B:12:0x0078->B:16:0x0087], SYNTHETIC] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x00a5 -> B:22:0x00a8). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00b2 -> B:27:0x00b3). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r13) {
            /*
                Method dump skipped, instruction units count: 216
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.ui.result.SyncViewModel.C09001.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final Job updateUserData() {
        return Coroutines.INSTANCE.ioSafe(this, new C09001(null));
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.SyncViewModel$updateMetadata$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SyncViewModel.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/result/SyncViewModel;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.SyncViewModel$updateMetadata$1", f = "SyncViewModel.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {257}, m = "invokeSuspend", n = {"lastError", "current", "$this$forEach$iv", "element$iv", "prefix", "id", "repo", "$i$f$forEach", "$i$a$-forEach-SyncViewModel$updateMetadata$1$2", "$i$a$-let-SyncViewModel$updateMetadata$1$2$2"}, nl = {GlobalMediaRouter.CallbackHandler.MSG_ROUTE_REMOVED}, s = {"L$0", "L$1", "L$2", "L$5", "L$6", "L$7", "L$8", "I$0", "I$1", "I$2"}, v = 2)
    static final class C08991 extends SuspendLambda implements Function3<CoroutineScope, SyncViewModel, Continuation<? super Unit>, Object> {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        int label;

        C08991(Continuation<? super C08991> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, SyncViewModel syncViewModel, Continuation<? super Unit> continuation) {
            return SyncViewModel.this.new C08991(continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:27:0x00fb  */
        /* JADX WARN: Code duplicated, block: B:30:0x010a A[LOOP:0: B:26:0x00f9->B:30:0x010a, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:53:0x0107 A[SYNTHETIC] */
        /* JADX WARN: Type inference failed for: r0v4, types: [T, com.lagradost.cloudstream3.mvvm.Resource$Failure] */
        /* JADX WARN: Type inference failed for: r4v10, types: [T, com.lagradost.cloudstream3.mvvm.Resource] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x015e -> B:36:0x0161). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:46:0x018a -> B:47:0x018b). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r18) {
            /*
                Method dump skipped, instruction units count: 419
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.ui.result.SyncViewModel.C08991.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    private final Job updateMetadata() {
        return Coroutines.INSTANCE.ioSafe(this, new C08991(null));
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0076  */
    /* JADX WARN: Code duplicated, block: B:32:0x007b A[RETURN] */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final String syncName(String syncName) {
        Intrinsics.checkNotNullParameter(syncName, "syncName");
        switch (syncName.hashCode()) {
            case 76088:
                if (syncName.equals("MAL")) {
                    syncName = AccountManager.INSTANCE.getMalApi().getIdPrefix();
                }
                break;
            case 72507288:
                if (syncName.equals("Kitsu")) {
                    syncName = AccountManager.INSTANCE.getKitsuApi().getIdPrefix();
                }
                break;
            case 79888472:
                if (syncName.equals("Simkl")) {
                    syncName = AccountManager.INSTANCE.getSimklApi().getIdPrefix();
                }
                break;
            case 806742138:
                if (syncName.equals("AniList")) {
                    syncName = AccountManager.INSTANCE.getAniListApi().getIdPrefix();
                }
                break;
        }
        for (SyncRepo syncRepo : this.repos) {
            if (Intrinsics.areEqual(syncRepo.getIdPrefix(), syncName)) {
                if (syncRepo != null) {
                    return syncRepo.getIdPrefix();
                }
                return null;
            }
        }
        syncRepo = null;
        if (syncRepo != null) {
            return syncRepo.getIdPrefix();
        }
        return null;
    }

    public final void setSync(String syncName, String syncId) {
        Intrinsics.checkNotNullParameter(syncName, "syncName");
        Intrinsics.checkNotNullParameter(syncId, "syncId");
        this.syncs.clear();
        this.syncs.put(syncName, syncId);
    }

    public final void clear() {
        this.syncs.clear();
        this._metaResponse.postValue(null);
        this._currentSynced.postValue(getMissing());
        this._userDataResponse.postValue(null);
    }

    public final void updateMetaAndUser() {
        this._userDataResponse.postValue(new Resource.Loading(null, 1, null));
        this._metaResponse.postValue(new Resource.Loading(null, 1, null));
        Log.i(TAG, "updateMetaAndUser");
        updateMetadata();
        updateUserData();
    }

    public final boolean addSyncs(Map<String, String> map) {
        if (map == null) {
            return false;
        }
        while (true) {
            boolean z = false;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (addSync(entry.getKey(), entry.getValue()) || z) {
                    z = true;
                }
            }
            return z;
        }
    }
}
