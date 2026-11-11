package com.lagradost.cloudstream3.ui.player;

import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.ui.APIRepository;
import com.lagradost.cloudstream3.ui.result.ResultEpisode;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.ExtractorLinkType;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: RepoLinkGenerator.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 %2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001%B!\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0002\u0010\u0014Jl\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\"\u0010\u001a\u001a\u001e\u0012\u0014\u0012\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u001d\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u001c\u0012\u0004\u0012\u00020\u001f0\u001b2\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u001f0\u001b2\u0006\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\fH\u0096@¢\u0006\u0002\u0010$R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\fX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\fX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000e¨\u0006&"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/RepoLinkGenerator;", "Lcom/lagradost/cloudstream3/ui/player/VideoGenerator;", "Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "episodes", "", "page", "Lcom/lagradost/cloudstream3/LoadResponse;", "<init>", "(Ljava/util/List;Lcom/lagradost/cloudstream3/LoadResponse;)V", "getPage", "()Lcom/lagradost/cloudstream3/LoadResponse;", "hasCache", "", "getHasCache", "()Z", "canSkipLoading", "getCanSkipLoading", "getId", "", "index", "(I)Ljava/lang/Integer;", "generateLinks", "clearCache", "sourceTypes", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;", "callback", "Lkotlin/Function1;", "Lkotlin/Pair;", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "Lcom/lagradost/cloudstream3/ui/player/ExtractorUri;", "", "subtitleCallback", "Lcom/lagradost/cloudstream3/ui/player/SubtitleData;", TypedValues.CycleType.S_WAVE_OFFSET, "isCasting", "(ZLjava/util/Set;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;IZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class RepoLinkGenerator extends VideoGenerator<ResultEpisode> {
    public static final String TAG = "RepoLink";
    private final boolean canSkipLoading;
    private final boolean hasCache;
    private final LoadResponse page;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final HashMap<Pair<String, Integer>, Cache> cache = new HashMap<>();

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.RepoLinkGenerator$generateLinks$1, reason: invalid class name */
    /* JADX INFO: compiled from: RepoLinkGenerator.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.RepoLinkGenerator", f = "RepoLinkGenerator.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {109}, m = "generateLinks", n = {"sourceTypes", "callback", "subtitleCallback", "current", "currentCache", "currentLinksUrls", "currentSubsUrls", "lastCountedSuffix", "clearCache", TypedValues.CycleType.S_WAVE_OFFSET, "isCasting"}, nl = {107}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "Z$0", "I$0", "Z$1"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        boolean Z$0;
        boolean Z$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RepoLinkGenerator.this.generateLinks(false, null, null, null, 0, false, this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RepoLinkGenerator(List<ResultEpisode> episodes, LoadResponse loadResponse) {
        super(episodes);
        Intrinsics.checkNotNullParameter(episodes, "episodes");
        this.page = loadResponse;
        this.hasCache = true;
        this.canSkipLoading = true;
    }

    public /* synthetic */ RepoLinkGenerator(List list, LoadResponse loadResponse, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i & 2) != 0 ? null : loadResponse);
    }

    public final LoadResponse getPage() {
        return this.page;
    }

    /* JADX INFO: compiled from: RepoLinkGenerator.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000RE\u0010\u0006\u001a6\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\b\u0012\u0004\u0012\u00020\n0\u0007j\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\b\u0012\u0004\u0012\u00020\n`\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/RepoLinkGenerator$Companion;", "", "<init>", "()V", "TAG", "", "cache", "Ljava/util/HashMap;", "Lkotlin/Pair;", "", "Lcom/lagradost/cloudstream3/ui/player/Cache;", "Lkotlin/collections/HashMap;", "getCache", "()Ljava/util/HashMap;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final HashMap<Pair<String, Integer>, Cache> getCache() {
            return RepoLinkGenerator.cache;
        }
    }

    @Override // com.lagradost.cloudstream3.ui.player.VideoGenerator
    public boolean getHasCache() {
        return this.hasCache;
    }

    @Override // com.lagradost.cloudstream3.ui.player.VideoGenerator
    public boolean getCanSkipLoading() {
        return this.canSkipLoading;
    }

    @Override // com.lagradost.cloudstream3.ui.player.VideoGenerator
    public Integer getId(int index) {
        ResultEpisode resultEpisode = (ResultEpisode) CollectionsKt.getOrNull(getVideos(), index);
        if (resultEpisode != null) {
            return Integer.valueOf(resultEpisode.getId());
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:39:0x014a A[Catch: all -> 0x026d, TryCatch #2 {, blocks: (B:27:0x00df, B:31:0x00f4, B:36:0x013a, B:37:0x0144, B:39:0x014a, B:41:0x0161, B:42:0x016a, B:43:0x0174, B:45:0x017a, B:47:0x0194, B:52:0x01af, B:53:0x01bc, B:55:0x01c6, B:58:0x01cc, B:33:0x0109, B:35:0x0115), top: B:86:0x00df }] */
    /* JADX WARN: Code duplicated, block: B:45:0x017a A[Catch: all -> 0x026d, TryCatch #2 {, blocks: (B:27:0x00df, B:31:0x00f4, B:36:0x013a, B:37:0x0144, B:39:0x014a, B:41:0x0161, B:42:0x016a, B:43:0x0174, B:45:0x017a, B:47:0x0194, B:52:0x01af, B:53:0x01bc, B:55:0x01c6, B:58:0x01cc, B:33:0x0109, B:35:0x0115), top: B:86:0x00df }] */
    /* JADX WARN: Code duplicated, block: B:47:0x0194 A[Catch: all -> 0x026d, TryCatch #2 {, blocks: (B:27:0x00df, B:31:0x00f4, B:36:0x013a, B:37:0x0144, B:39:0x014a, B:41:0x0161, B:42:0x016a, B:43:0x0174, B:45:0x017a, B:47:0x0194, B:52:0x01af, B:53:0x01bc, B:55:0x01c6, B:58:0x01cc, B:33:0x0109, B:35:0x0115), top: B:86:0x00df }] */
    /* JADX WARN: Code duplicated, block: B:49:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:50:0x01a7  */
    /* JADX WARN: Code duplicated, block: B:51:0x01aa  */
    /* JADX WARN: Code duplicated, block: B:55:0x01c6 A[Catch: all -> 0x026d, TRY_LEAVE, TryCatch #2 {, blocks: (B:27:0x00df, B:31:0x00f4, B:36:0x013a, B:37:0x0144, B:39:0x014a, B:41:0x0161, B:42:0x016a, B:43:0x0174, B:45:0x017a, B:47:0x0194, B:52:0x01af, B:53:0x01bc, B:55:0x01c6, B:58:0x01cc, B:33:0x0109, B:35:0x0115), top: B:86:0x00df }] */
    /* JADX WARN: Code duplicated, block: B:58:0x01cc A[Catch: all -> 0x026d, TRY_ENTER, TRY_LEAVE, TryCatch #2 {, blocks: (B:27:0x00df, B:31:0x00f4, B:36:0x013a, B:37:0x0144, B:39:0x014a, B:41:0x0161, B:42:0x016a, B:43:0x0174, B:45:0x017a, B:47:0x0194, B:52:0x01af, B:53:0x01bc, B:55:0x01c6, B:58:0x01cc, B:33:0x0109, B:35:0x0115), top: B:86:0x00df }] */
    /* JADX WARN: Code duplicated, block: B:62:0x01dd  */
    /* JADX WARN: Code duplicated, block: B:64:0x023a A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:74:0x0265  */
    /* JADX WARN: Code duplicated, block: B:7:0x0024  */
    /* JADX WARN: Code duplicated, block: B:88:0x0161 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:90:0x0144 A[SYNTHETIC] */
    @Override // com.lagradost.cloudstream3.ui.player.VideoGenerator
    public Object generateLinks(boolean z, final Set<? extends ExtractorLinkType> set, final Function1<? super Pair<? extends ExtractorLink, ExtractorUri>, Unit> function1, final Function1<? super SubtitleData, Unit> function2, int i, boolean z2, Continuation<? super Boolean> continuation) {
        AnonymousClass1 anonymousClass1;
        RepoLinkGenerator repoLinkGenerator;
        final Cache cache2;
        ResultEpisode resultEpisode;
        Object obj;
        MainAPI apiFromNameNull;
        ConcurrentHashMap concurrentHashMap;
        String originalName;
        Object obj2;
        ResultEpisode resultEpisode2;
        Object obj3;
        AtomicInteger atomicInteger;
        Object objPutIfAbsent;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
                repoLinkGenerator = this;
            } else {
                repoLinkGenerator = this;
                anonymousClass1 = repoLinkGenerator.new AnonymousClass1(continuation);
            }
        } else {
            repoLinkGenerator = this;
            anonymousClass1 = repoLinkGenerator.new AnonymousClass1(continuation);
        }
        Object objLoadLinks = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objLoadLinks);
            ResultEpisode resultEpisode3 = (ResultEpisode) CollectionsKt.getOrNull(repoLinkGenerator.getVideos(), i);
            if (resultEpisode3 == null) {
                return Boxing.boxBoolean(false);
            }
            HashMap<Pair<String, Integer>, Cache> map = cache;
            synchronized (map) {
                cache2 = map.get(TuplesKt.to(resultEpisode3.getApiName(), Boxing.boxInt(resultEpisode3.getId())));
                if (cache2 == null) {
                    Cache cache3 = new Cache(new LinkedHashSet(), new LinkedHashSet(), APIHolder.INSTANCE.getUnixTime(), false);
                    map.put(TuplesKt.to(resultEpisode3.getApiName(), Boxing.boxInt(resultEpisode3.getId())), cache3);
                    cache2 = cache3;
                }
            }
            final ConcurrentHashMap.KeySetView keySetViewNewKeySet = ConcurrentHashMap.newKeySet();
            final ConcurrentHashMap.KeySetView keySetViewNewKeySet2 = ConcurrentHashMap.newKeySet();
            final ConcurrentHashMap concurrentHashMap2 = new ConcurrentHashMap();
            synchronized (cache2) {
                if (APIHolder.INSTANCE.getUnixTime() - cache2.getLastCachedTimestamp() <= 1200 && !z) {
                    if (!cache2.getLinkCache().isEmpty()) {
                        Log.d(TAG, "Resumed previous loading from " + (APIHolder.INSTANCE.getUnixTime() - cache2.getLastCachedTimestamp()) + "s ago");
                    }
                    for (ExtractorLink extractorLink : cache2.getLinkCache()) {
                        keySetViewNewKeySet.add(extractorLink.getUrl());
                        if (set.contains(extractorLink.getType())) {
                            function1.invoke(TuplesKt.to(extractorLink, null));
                        }
                    }
                    for (SubtitleData subtitleData : cache2.getSubtitleCache()) {
                        keySetViewNewKeySet2.add(subtitleData.getUrl());
                        concurrentHashMap = concurrentHashMap2;
                        originalName = subtitleData.getOriginalName();
                        obj2 = concurrentHashMap.get(originalName);
                        if (obj2 == null) {
                            resultEpisode2 = resultEpisode3;
                            obj3 = coroutine_suspended;
                            atomicInteger = new AtomicInteger(0);
                            objPutIfAbsent = concurrentHashMap.putIfAbsent(originalName, atomicInteger);
                            if (objPutIfAbsent == null) {
                                obj2 = atomicInteger;
                            } else {
                                obj2 = objPutIfAbsent;
                            }
                        } else {
                            resultEpisode2 = resultEpisode3;
                            obj3 = coroutine_suspended;
                        }
                        ((AtomicInteger) obj2).incrementAndGet();
                        function2.invoke(subtitleData);
                        resultEpisode3 = resultEpisode2;
                        coroutine_suspended = obj3;
                    }
                    resultEpisode = resultEpisode3;
                    obj = coroutine_suspended;
                    if (cache2.getSaturated()) {
                        return Boxing.boxBoolean(r11);
                    }
                    Unit unit = Unit.INSTANCE;
                    apiFromNameNull = APIHolder.INSTANCE.getApiFromNameNull(resultEpisode.getApiName());
                    if (apiFromNameNull != null) {
                        throw new Exception("This provider does not exist");
                    }
                    APIRepository aPIRepository = new APIRepository(apiFromNameNull);
                    String data = resultEpisode.getData();
                    Function1<? super SubtitleFile, Unit> function3 = new Function1() { // from class: com.lagradost.cloudstream3.ui.player.RepoLinkGenerator$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj4) {
                            return RepoLinkGenerator.generateLinks$lambda$2(keySetViewNewKeySet2, concurrentHashMap2, cache2, function2, (SubtitleFile) obj4);
                        }
                    };
                    Function1<? super ExtractorLink, Unit> function4 = new Function1() { // from class: com.lagradost.cloudstream3.ui.player.RepoLinkGenerator$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj4) {
                            return RepoLinkGenerator.generateLinks$lambda$3(keySetViewNewKeySet, cache2, set, function1, (ExtractorLink) obj4);
                        }
                    };
                    anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(set);
                    anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(function1);
                    anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(function2);
                    anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(resultEpisode);
                    anonymousClass1.L$4 = cache2;
                    anonymousClass1.L$5 = SpillingKt.nullOutSpilledVariable(keySetViewNewKeySet);
                    anonymousClass1.L$6 = SpillingKt.nullOutSpilledVariable(keySetViewNewKeySet2);
                    anonymousClass1.L$7 = SpillingKt.nullOutSpilledVariable(concurrentHashMap2);
                    anonymousClass1.Z$0 = z;
                    anonymousClass1.I$0 = i;
                    anonymousClass1.Z$1 = z2;
                    anonymousClass1.label = 1;
                    objLoadLinks = aPIRepository.loadLinks(data, z2, function3, function4, anonymousClass1);
                    if (objLoadLinks == obj) {
                        return obj;
                    }
                } else {
                    cache2.getLinkCache().clear();
                    cache2.getSubtitleCache().clear();
                    cache2.setSaturated(false);
                }
                while (r8.hasNext()) {
                    keySetViewNewKeySet.add(extractorLink.getUrl());
                    if (set.contains(extractorLink.getType())) {
                        function1.invoke(TuplesKt.to(extractorLink, null));
                    }
                }
                while (r8.hasNext()) {
                    keySetViewNewKeySet2.add(subtitleData.getUrl());
                    concurrentHashMap = concurrentHashMap2;
                    originalName = subtitleData.getOriginalName();
                    obj2 = concurrentHashMap.get(originalName);
                    if (obj2 == null) {
                        resultEpisode2 = resultEpisode3;
                        obj3 = coroutine_suspended;
                        atomicInteger = new AtomicInteger(0);
                        objPutIfAbsent = concurrentHashMap.putIfAbsent(originalName, atomicInteger);
                        if (objPutIfAbsent == null) {
                            obj2 = atomicInteger;
                        } else {
                            obj2 = objPutIfAbsent;
                        }
                    } else {
                        resultEpisode2 = resultEpisode3;
                        obj3 = coroutine_suspended;
                    }
                    ((AtomicInteger) obj2).incrementAndGet();
                    function2.invoke(subtitleData);
                    resultEpisode3 = resultEpisode2;
                    coroutine_suspended = obj3;
                }
                resultEpisode = resultEpisode3;
                obj = coroutine_suspended;
                if (cache2.getSaturated()) {
                    return Boxing.boxBoolean(r11);
                }
                Unit unit2 = Unit.INSTANCE;
                apiFromNameNull = APIHolder.INSTANCE.getApiFromNameNull(resultEpisode.getApiName());
                if (apiFromNameNull != null) {
                    throw new Exception("This provider does not exist");
                }
                APIRepository aPIRepository2 = new APIRepository(apiFromNameNull);
                String data2 = resultEpisode.getData();
                Function1<? super SubtitleFile, Unit> function5 = new Function1() { // from class: com.lagradost.cloudstream3.ui.player.RepoLinkGenerator$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj4) {
                        return RepoLinkGenerator.generateLinks$lambda$2(keySetViewNewKeySet2, concurrentHashMap2, cache2, function2, (SubtitleFile) obj4);
                    }
                };
                Function1<? super ExtractorLink, Unit> function6 = new Function1() { // from class: com.lagradost.cloudstream3.ui.player.RepoLinkGenerator$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj4) {
                        return RepoLinkGenerator.generateLinks$lambda$3(keySetViewNewKeySet, cache2, set, function1, (ExtractorLink) obj4);
                    }
                };
                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(set);
                anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(function1);
                anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(function2);
                anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(resultEpisode);
                anonymousClass1.L$4 = cache2;
                anonymousClass1.L$5 = SpillingKt.nullOutSpilledVariable(keySetViewNewKeySet);
                anonymousClass1.L$6 = SpillingKt.nullOutSpilledVariable(keySetViewNewKeySet2);
                anonymousClass1.L$7 = SpillingKt.nullOutSpilledVariable(concurrentHashMap2);
                anonymousClass1.Z$0 = z;
                anonymousClass1.I$0 = i;
                anonymousClass1.Z$1 = z2;
                anonymousClass1.label = 1;
                objLoadLinks = aPIRepository2.loadLinks(data2, z2, function5, function6, anonymousClass1);
                if (objLoadLinks == obj) {
                    return obj;
                }
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            boolean z3 = anonymousClass1.Z$1;
            int i3 = anonymousClass1.I$0;
            boolean z4 = anonymousClass1.Z$0;
            Cache cache4 = (Cache) anonymousClass1.L$4;
            ResultKt.throwOnFailure(objLoadLinks);
            cache2 = cache4;
        }
        boolean zBooleanValue = ((Boolean) objLoadLinks).booleanValue();
        synchronized (cache2) {
            cache2.setSaturated(!cache2.getLinkCache().isEmpty());
            cache2.setLastCachedTimestamp(APIHolder.INSTANCE.getUnixTime());
            Unit unit3 = Unit.INSTANCE;
        }
        return Boxing.boxBoolean(zBooleanValue);
    }

    static final Unit generateLinks$lambda$2(ConcurrentHashMap.KeySetView keySetView, ConcurrentHashMap concurrentHashMap, Cache cache2, Function1 function1, SubtitleFile file) {
        Object objPutIfAbsent;
        Intrinsics.checkNotNullParameter(file, "file");
        Log.d(TAG, "Loaded SubtitleFile: " + file);
        SubtitleData subtitleData = PlayerSubtitleHelper.INSTANCE.getSubtitleData(file);
        if (StringsKt.isBlank(subtitleData.getUrl()) || !keySetView.add(subtitleData.getUrl())) {
            return Unit.INSTANCE;
        }
        String string = StringsKt.trim((CharSequence) AppContextUtils.INSTANCE.html(subtitleData.getOriginalName()).toString()).toString();
        ConcurrentHashMap concurrentHashMap2 = concurrentHashMap;
        Object atomicInteger = concurrentHashMap2.get(string);
        if (atomicInteger == null && (objPutIfAbsent = concurrentHashMap2.putIfAbsent(string, (atomicInteger = new AtomicInteger(0)))) != null) {
            atomicInteger = objPutIfAbsent;
        }
        SubtitleData subtitleDataCopy$default = SubtitleData.copy$default(subtitleData, string, String.valueOf(((AtomicInteger) atomicInteger).incrementAndGet()), null, null, null, null, null, 124, null);
        synchronized (cache2) {
            if (cache2.getSubtitleCache().add(subtitleDataCopy$default)) {
                function1.invoke(subtitleDataCopy$default);
                cache2.setLastCachedTimestamp(APIHolder.INSTANCE.getUnixTime());
            }
            Unit unit = Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    static final Unit generateLinks$lambda$3(ConcurrentHashMap.KeySetView keySetView, Cache cache2, Set set, Function1 function1, ExtractorLink link) {
        Intrinsics.checkNotNullParameter(link, "link");
        Log.d(TAG, "Loaded ExtractorLink: " + link);
        if (StringsKt.isBlank(link.getUrl()) || !keySetView.add(link.getUrl())) {
            return Unit.INSTANCE;
        }
        synchronized (cache2) {
            if (cache2.getLinkCache().add(link)) {
                if (set.contains(link.getType())) {
                    function1.invoke(new Pair(link, null));
                }
                cache2.getLinkCache().add(link);
                cache2.setLastCachedTimestamp(APIHolder.INSTANCE.getUnixTime());
            }
            Unit unit = Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }
}
