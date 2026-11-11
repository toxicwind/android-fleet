package com.lagradost.cloudstream3.utils.videoskip;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.ui.result.ResultEpisode;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import com.lagradost.nicehttp.ResponseParser;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* JADX INFO: compiled from: TheIntroDBSkip.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002\u0019\u001aB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0096@¢\u0006\u0002\u0010\u0018R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0007¨\u0006\u001b"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/TheIntroDBSkip;", "Lcom/lagradost/cloudstream3/utils/videoskip/SkipAPI;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "supportedTypes", "", "Lcom/lagradost/cloudstream3/TvType;", "getSupportedTypes", "()Ljava/util/Set;", "mainUrl", "getMainUrl", "stamps", "", "Lcom/lagradost/cloudstream3/utils/videoskip/SkipStamp;", "data", "Lcom/lagradost/cloudstream3/LoadResponse;", "episode", "Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "episodeDurationMs", "", "(Lcom/lagradost/cloudstream3/LoadResponse;Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Root", "Stamp", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class TheIntroDBSkip extends SkipAPI {
    private final String name = "TheIntroDB";
    private final Set<TvType> supportedTypes = SetsKt.setOf((Object[]) new TvType[]{TvType.TvSeries, TvType.Cartoon, TvType.Anime, TvType.Movie, TvType.AsianDrama});
    private final String mainUrl = "https://api.theintrodb.org";

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.videoskip.TheIntroDBSkip$stamps$1, reason: invalid class name */
    /* JADX INFO: compiled from: TheIntroDBSkip.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.videoskip.TheIntroDBSkip", f = "TheIntroDBSkip.kt", i = {0, 0, 0, 0, 0}, l = {40}, m = "stamps", n = {"data", "episode", "idSuffix", ImagesContract.URL, "episodeDurationMs"}, nl = {75}, s = {"L$0", "L$1", "L$2", "L$3", "J$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TheIntroDBSkip.this.stamps(null, null, 0L, this);
        }
    }

    @Override // com.lagradost.cloudstream3.utils.videoskip.SkipAPI
    public String getName() {
        return this.name;
    }

    @Override // com.lagradost.cloudstream3.utils.videoskip.SkipAPI
    public Set<TvType> getSupportedTypes() {
        return this.supportedTypes;
    }

    public final String getMainUrl() {
        return this.mainUrl;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0068  */
    /* JADX WARN: Code duplicated, block: B:20:0x0070  */
    /* JADX WARN: Code duplicated, block: B:23:0x0088  */
    /* JADX WARN: Code duplicated, block: B:25:0x009f  */
    /* JADX WARN: Code duplicated, block: B:27:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:30:0x0120 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:31:0x0121  */
    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    /* JADX WARN: Instruction removed from duplicated block: B:20:0x0070, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:23:0x0088, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:27:0x00a5, please report this as an issue */
    @Override // com.lagradost.cloudstream3.utils.videoskip.SkipAPI
    public Object stamps(LoadResponse loadResponse, ResultEpisode resultEpisode, long j, Continuation<? super List<SkipStamp>> continuation) {
        AnonymousClass1 anonymousClass1;
        String imdbId;
        String str;
        Integer season;
        String str2;
        char c;
        Object obj;
        long j2;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object obj2 = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj2);
            String tMDbId = LoadResponse.INSTANCE.getTMDbId(loadResponse);
            if (tMDbId != null) {
                str = "tmdb_id=" + tMDbId;
                if (str == null) {
                    imdbId = LoadResponse.INSTANCE.getImdbId(loadResponse);
                    if (imdbId != null) {
                        str = "imdb_id=" + imdbId;
                        if (LoadResponse.INSTANCE.isMovie(loadResponse)) {
                            str2 = this.mainUrl + "/v2/media?" + str;
                        } else {
                            season = resultEpisode.getSeason();
                            if (season != null) {
                                str2 = this.mainUrl + "/v2/media?" + str + "&season=" + season.intValue() + "&episode=" + resultEpisode.getEpisode();
                            }
                        }
                        String str3 = str2;
                        Requests app2 = MainActivityKt.getApp();
                        anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(loadResponse);
                        anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(resultEpisode);
                        anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(str);
                        anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(str3);
                        anonymousClass1.J$0 = j;
                        anonymousClass1.label = 1;
                        AnonymousClass1 anonymousClass2 = anonymousClass1;
                        c = 1;
                        obj = Requests.get$default(app2, str3, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass2, 4094, null);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        j2 = j;
                        obj2 = obj;
                    }
                } else {
                    if (LoadResponse.INSTANCE.isMovie(loadResponse)) {
                        str2 = this.mainUrl + "/v2/media?" + str;
                    } else {
                        season = resultEpisode.getSeason();
                        if (season != null) {
                            str2 = this.mainUrl + "/v2/media?" + str + "&season=" + season.intValue() + "&episode=" + resultEpisode.getEpisode();
                        }
                    }
                    String str4 = str2;
                    Requests app3 = MainActivityKt.getApp();
                    anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(loadResponse);
                    anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(resultEpisode);
                    anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(str);
                    anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(str4);
                    anonymousClass1.J$0 = j;
                    anonymousClass1.label = 1;
                    AnonymousClass1 anonymousClass3 = anonymousClass1;
                    c = 1;
                    obj = Requests.get$default(app3, str4, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass3, 4094, null);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    j2 = j;
                    obj2 = obj;
                }
            } else {
                imdbId = LoadResponse.INSTANCE.getImdbId(loadResponse);
                if (imdbId != null) {
                    str = "imdb_id=" + imdbId;
                    if (LoadResponse.INSTANCE.isMovie(loadResponse)) {
                        str2 = this.mainUrl + "/v2/media?" + str;
                    } else {
                        season = resultEpisode.getSeason();
                        if (season != null) {
                            str2 = this.mainUrl + "/v2/media?" + str + "&season=" + season.intValue() + "&episode=" + resultEpisode.getEpisode();
                        }
                    }
                    String str5 = str2;
                    Requests app4 = MainActivityKt.getApp();
                    anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(loadResponse);
                    anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(resultEpisode);
                    anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(str);
                    anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(str5);
                    anonymousClass1.J$0 = j;
                    anonymousClass1.label = 1;
                    AnonymousClass1 anonymousClass4 = anonymousClass1;
                    c = 1;
                    obj = Requests.get$default(app4, str5, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass4, 4094, null);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    j2 = j;
                    obj2 = obj;
                }
            }
            return null;
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        j2 = anonymousClass1.J$0;
        ResultKt.throwOnFailure(obj2);
        c = 1;
        NiceResponse niceResponse = (NiceResponse) obj2;
        ResponseParser parser = niceResponse.getParser();
        Intrinsics.checkNotNull(parser);
        Root root = (Root) parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(Root.class));
        Pair[] pairArr = new Pair[4];
        pairArr[0] = TuplesKt.to(root.getIntro(), SkipType.Intro);
        pairArr[c] = TuplesKt.to(root.getCredits(), SkipType.Credits);
        pairArr[2] = TuplesKt.to(root.getRecap(), SkipType.Recap);
        pairArr[3] = TuplesKt.to(root.getPreview(), SkipType.Preview);
        ArrayList arrayList = new ArrayList(4);
        for (int i2 = 0; i2 < 4; i2++) {
            Pair pair = pairArr[i2];
            List list = (List) pair.component1();
            SkipType skipType = (SkipType) pair.component2();
            List<Stamp> list2 = list;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            for (Stamp stamp : list2) {
                Long startMs = stamp.getStartMs();
                long jLongValue = startMs != null ? startMs.longValue() : 0L;
                Long endMs = stamp.getEndMs();
                arrayList2.add(new SkipStamp(skipType, jLongValue, endMs != null ? endMs.longValue() : j2, null, 8, null));
            }
            arrayList.add(arrayList2);
        }
        return CollectionsKt.flatten(arrayList);
    }

    /* JADX INFO: compiled from: TheIntroDBSkip.kt */
    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 82\u00020\u0001:\u000278B[\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0003\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0003\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0003\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0003\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\f\u0010\rBm\b\u0010\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\f\u0010\u0012J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0005HÆ\u0003J\u000f\u0010%\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\u000f\u0010&\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\u000f\u0010'\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J]\u0010)\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\u000e\b\u0003\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0003\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0003\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0003\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001J\u0014\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010-\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010.\u001a\u00020\u0005HÖ\u0081\u0004J%\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u00002\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u000205H\u0001¢\u0006\u0002\b6R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0014\u001a\u0004\b\u0018\u0010\u0019R\"\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0014\u001a\u0004\b\u001b\u0010\u001cR\"\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u0014\u001a\u0004\b\u001e\u0010\u001cR\"\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001f\u0010\u0014\u001a\u0004\b \u0010\u001cR\"\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b!\u0010\u0014\u001a\u0004\b\"\u0010\u001c¨\u00069"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/TheIntroDBSkip$Root;", "", "tmdbId", "", "type", "", "intro", "", "Lcom/lagradost/cloudstream3/utils/videoskip/TheIntroDBSkip$Stamp;", "recap", "credits", "preview", "<init>", "(JLjava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IJLjava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getTmdbId$annotations", "()V", "getTmdbId", "()J", "getType$annotations", "getType", "()Ljava/lang/String;", "getIntro$annotations", "getIntro", "()Ljava/util/List;", "getRecap$annotations", "getRecap", "getCredits$annotations", "getCredits", "getPreview$annotations", "getPreview", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Root {

        @JsonProperty("credits")
        private final List<Stamp> credits;

        @JsonProperty("intro")
        private final List<Stamp> intro;

        @JsonProperty("preview")
        private final List<Stamp> preview;

        @JsonProperty("recap")
        private final List<Stamp> recap;

        @JsonProperty("tmdb_id")
        private final long tmdbId;

        @JsonProperty("type")
        private final String type;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.utils.videoskip.TheIntroDBSkip$Root$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TheIntroDBSkip.Root._childSerializers$_anonymous_();
            }
        }), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.utils.videoskip.TheIntroDBSkip$Root$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TheIntroDBSkip.Root._childSerializers$_anonymous_$0();
            }
        }), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.utils.videoskip.TheIntroDBSkip$Root$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TheIntroDBSkip.Root._childSerializers$_anonymous_$1();
            }
        }), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.utils.videoskip.TheIntroDBSkip$Root$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TheIntroDBSkip.Root._childSerializers$_anonymous_$2();
            }
        })};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(TheIntroDBSkip$Stamp$$serializer.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$0() {
            return new ArrayListSerializer(TheIntroDBSkip$Stamp$$serializer.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$1() {
            return new ArrayListSerializer(TheIntroDBSkip$Stamp$$serializer.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$2() {
            return new ArrayListSerializer(TheIntroDBSkip$Stamp$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Root copy$default(Root root, long j, String str, List list, List list2, List list3, List list4, int i, Object obj) {
            if ((i & 1) != 0) {
                j = root.tmdbId;
            }
            long j2 = j;
            if ((i & 2) != 0) {
                str = root.type;
            }
            String str2 = str;
            if ((i & 4) != 0) {
                list = root.intro;
            }
            List list5 = list;
            if ((i & 8) != 0) {
                list2 = root.recap;
            }
            List list6 = list2;
            if ((i & 16) != 0) {
                list3 = root.credits;
            }
            List list7 = list3;
            if ((i & 32) != 0) {
                list4 = root.preview;
            }
            return root.copy(j2, str2, list5, list6, list7, list4);
        }

        @SerialName("credits")
        public static /* synthetic */ void getCredits$annotations() {
        }

        @SerialName("intro")
        public static /* synthetic */ void getIntro$annotations() {
        }

        @SerialName("preview")
        public static /* synthetic */ void getPreview$annotations() {
        }

        @SerialName("recap")
        public static /* synthetic */ void getRecap$annotations() {
        }

        @SerialName("tmdb_id")
        public static /* synthetic */ void getTmdbId$annotations() {
        }

        @SerialName("type")
        public static /* synthetic */ void getType$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getTmdbId() {
            return this.tmdbId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getType() {
            return this.type;
        }

        public final List<Stamp> component3() {
            return this.intro;
        }

        public final List<Stamp> component4() {
            return this.recap;
        }

        public final List<Stamp> component5() {
            return this.credits;
        }

        public final List<Stamp> component6() {
            return this.preview;
        }

        public final Root copy(@JsonProperty("tmdb_id") long tmdbId, @JsonProperty("type") String type, @JsonProperty("intro") List<Stamp> intro, @JsonProperty("recap") List<Stamp> recap, @JsonProperty("credits") List<Stamp> credits, @JsonProperty("preview") List<Stamp> preview) {
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(intro, "intro");
            Intrinsics.checkNotNullParameter(recap, "recap");
            Intrinsics.checkNotNullParameter(credits, "credits");
            Intrinsics.checkNotNullParameter(preview, "preview");
            return new Root(tmdbId, type, intro, recap, credits, preview);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Root)) {
                return false;
            }
            Root root = (Root) other;
            return this.tmdbId == root.tmdbId && Intrinsics.areEqual(this.type, root.type) && Intrinsics.areEqual(this.intro, root.intro) && Intrinsics.areEqual(this.recap, root.recap) && Intrinsics.areEqual(this.credits, root.credits) && Intrinsics.areEqual(this.preview, root.preview);
        }

        public int hashCode() {
            return (((((((((NextAiring$$ExternalSyntheticBackport0.m(this.tmdbId) * 31) + this.type.hashCode()) * 31) + this.intro.hashCode()) * 31) + this.recap.hashCode()) * 31) + this.credits.hashCode()) * 31) + this.preview.hashCode();
        }

        public String toString() {
            return "Root(tmdbId=" + this.tmdbId + ", type=" + this.type + ", intro=" + this.intro + ", recap=" + this.recap + ", credits=" + this.credits + ", preview=" + this.preview + ')';
        }

        /* JADX INFO: compiled from: TheIntroDBSkip.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/TheIntroDBSkip$Root$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/videoskip/TheIntroDBSkip$Root;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Root> serializer() {
                return TheIntroDBSkip$Root$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Root(int i, long j, String str, List list, List list2, List list3, List list4, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, TheIntroDBSkip$Root$$serializer.INSTANCE.getDescriptor());
            }
            this.tmdbId = j;
            this.type = str;
            if ((i & 4) == 0) {
                this.intro = CollectionsKt.emptyList();
            } else {
                this.intro = list;
            }
            if ((i & 8) == 0) {
                this.recap = CollectionsKt.emptyList();
            } else {
                this.recap = list2;
            }
            if ((i & 16) == 0) {
                this.credits = CollectionsKt.emptyList();
            } else {
                this.credits = list3;
            }
            if ((i & 32) == 0) {
                this.preview = CollectionsKt.emptyList();
            } else {
                this.preview = list4;
            }
        }

        public Root(@JsonProperty("tmdb_id") long j, @JsonProperty("type") String type, @JsonProperty("intro") List<Stamp> intro, @JsonProperty("recap") List<Stamp> recap, @JsonProperty("credits") List<Stamp> credits, @JsonProperty("preview") List<Stamp> preview) {
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(intro, "intro");
            Intrinsics.checkNotNullParameter(recap, "recap");
            Intrinsics.checkNotNullParameter(credits, "credits");
            Intrinsics.checkNotNullParameter(preview, "preview");
            this.tmdbId = j;
            this.type = type;
            this.intro = intro;
            this.recap = recap;
            this.credits = credits;
            this.preview = preview;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Root self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            output.encodeLongElement(serialDesc, 0, self.tmdbId);
            output.encodeStringElement(serialDesc, 1, self.type);
            if (output.shouldEncodeElementDefault(serialDesc, 2) || !Intrinsics.areEqual(self.intro, CollectionsKt.emptyList())) {
                output.encodeSerializableElement(serialDesc, 2, lazyArr[2].getValue(), self.intro);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 3) || !Intrinsics.areEqual(self.recap, CollectionsKt.emptyList())) {
                output.encodeSerializableElement(serialDesc, 3, lazyArr[3].getValue(), self.recap);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 4) || !Intrinsics.areEqual(self.credits, CollectionsKt.emptyList())) {
                output.encodeSerializableElement(serialDesc, 4, lazyArr[4].getValue(), self.credits);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 5) && Intrinsics.areEqual(self.preview, CollectionsKt.emptyList())) {
                return;
            }
            output.encodeSerializableElement(serialDesc, 5, lazyArr[5].getValue(), self.preview);
        }

        public final long getTmdbId() {
            return this.tmdbId;
        }

        public final String getType() {
            return this.type;
        }

        public /* synthetic */ Root(long j, String str, List list, List list2, List list3, List list4, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(j, str, (i & 4) != 0 ? CollectionsKt.emptyList() : list, (i & 8) != 0 ? CollectionsKt.emptyList() : list2, (i & 16) != 0 ? CollectionsKt.emptyList() : list3, (i & 32) != 0 ? CollectionsKt.emptyList() : list4);
        }

        public final List<Stamp> getIntro() {
            return this.intro;
        }

        public final List<Stamp> getRecap() {
            return this.recap;
        }

        public final List<Stamp> getCredits() {
            return this.credits;
        }

        public final List<Stamp> getPreview() {
            return this.preview;
        }
    }

    /* JADX INFO: compiled from: TheIntroDBSkip.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 &2\u00020\u0001:\u0002%&B\u001f\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006B/\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0005\u0010\u000bJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000fJ&\u0010\u0015\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J%\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0001¢\u0006\u0002\b$R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0010\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0010\u0012\u0004\b\u0011\u0010\r\u001a\u0004\b\u0012\u0010\u000f¨\u0006'"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/TheIntroDBSkip$Stamp;", "", "startMs", "", "endMs", "<init>", "(Ljava/lang/Long;Ljava/lang/Long;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Long;Ljava/lang/Long;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getStartMs$annotations", "()V", "getStartMs", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getEndMs$annotations", "getEndMs", "component1", "component2", "copy", "(Ljava/lang/Long;Ljava/lang/Long;)Lcom/lagradost/cloudstream3/utils/videoskip/TheIntroDBSkip$Stamp;", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Stamp {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("end_ms")
        private final Long endMs;

        @JsonProperty("start_ms")
        private final Long startMs;

        public static /* synthetic */ Stamp copy$default(Stamp stamp, Long l, Long l2, int i, Object obj) {
            if ((i & 1) != 0) {
                l = stamp.startMs;
            }
            if ((i & 2) != 0) {
                l2 = stamp.endMs;
            }
            return stamp.copy(l, l2);
        }

        @SerialName("end_ms")
        public static /* synthetic */ void getEndMs$annotations() {
        }

        @SerialName("start_ms")
        public static /* synthetic */ void getStartMs$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Long getStartMs() {
            return this.startMs;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Long getEndMs() {
            return this.endMs;
        }

        public final Stamp copy(@JsonProperty("start_ms") Long startMs, @JsonProperty("end_ms") Long endMs) {
            return new Stamp(startMs, endMs);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Stamp)) {
                return false;
            }
            Stamp stamp = (Stamp) other;
            return Intrinsics.areEqual(this.startMs, stamp.startMs) && Intrinsics.areEqual(this.endMs, stamp.endMs);
        }

        public int hashCode() {
            Long l = this.startMs;
            int iHashCode = (l == null ? 0 : l.hashCode()) * 31;
            Long l2 = this.endMs;
            return iHashCode + (l2 != null ? l2.hashCode() : 0);
        }

        public String toString() {
            return "Stamp(startMs=" + this.startMs + ", endMs=" + this.endMs + ')';
        }

        /* JADX INFO: compiled from: TheIntroDBSkip.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/TheIntroDBSkip$Stamp$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/videoskip/TheIntroDBSkip$Stamp;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Stamp> serializer() {
                return TheIntroDBSkip$Stamp$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Stamp(int i, Long l, Long l2, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, TheIntroDBSkip$Stamp$$serializer.INSTANCE.getDescriptor());
            }
            this.startMs = l;
            this.endMs = l2;
        }

        public Stamp(@JsonProperty("start_ms") Long l, @JsonProperty("end_ms") Long l2) {
            this.startMs = l;
            this.endMs = l2;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Stamp self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, LongSerializer.INSTANCE, self.startMs);
            output.encodeNullableSerializableElement(serialDesc, 1, LongSerializer.INSTANCE, self.endMs);
        }

        public final Long getStartMs() {
            return this.startMs;
        }

        public final Long getEndMs() {
            return this.endMs;
        }
    }
}
