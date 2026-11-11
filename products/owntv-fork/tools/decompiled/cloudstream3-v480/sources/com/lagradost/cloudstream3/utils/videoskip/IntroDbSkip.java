package com.lagradost.cloudstream3.utils.videoskip;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.ui.result.ResultEpisode;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import com.lagradost.nicehttp.ResponseParser;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.DoubleSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: IntroDbSkip.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002\u0017\u0018B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0096@¢\u0006\u0002\u0010\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/IntroDbSkip;", "Lcom/lagradost/cloudstream3/utils/videoskip/SkipAPI;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "supportedTypes", "", "Lcom/lagradost/cloudstream3/TvType;", "getSupportedTypes", "()Ljava/util/Set;", "stamps", "", "Lcom/lagradost/cloudstream3/utils/videoskip/SkipStamp;", "data", "Lcom/lagradost/cloudstream3/LoadResponse;", "episode", "Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "episodeDurationMs", "", "(Lcom/lagradost/cloudstream3/LoadResponse;Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "IntroDbResponse", "Segment", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class IntroDbSkip extends SkipAPI {
    private final String name = "IntroDb";
    private final Set<TvType> supportedTypes = SetsKt.setOf((Object[]) new TvType[]{TvType.TvSeries, TvType.AsianDrama});

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.videoskip.IntroDbSkip$stamps$1, reason: invalid class name */
    /* JADX INFO: compiled from: IntroDbSkip.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.videoskip.IntroDbSkip", f = "IntroDbSkip.kt", i = {0, 0, 0, 0, 0, 0}, l = {27}, m = "stamps", n = {"data", "episode", "imdbId", ImagesContract.URL, "episodeDurationMs", "season"}, nl = {82}, s = {"L$0", "L$1", "L$2", "L$3", "J$0", "I$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
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
            return IntroDbSkip.this.stamps(null, null, 0L, this);
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

    /* JADX WARN: Code duplicated, block: B:30:0x0119  */
    /* JADX WARN: Code duplicated, block: B:38:0x0144  */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    @Override // com.lagradost.cloudstream3.utils.videoskip.SkipAPI
    public Object stamps(LoadResponse loadResponse, ResultEpisode resultEpisode, long j, Continuation<? super List<SkipStamp>> continuation) {
        AnonymousClass1 anonymousClass1;
        char c;
        SkipStamp skipStamp;
        SkipStamp skipStamp2;
        Long startMs;
        Long startMs2;
        Long startMs3;
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
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        SkipStamp skipStamp3 = null;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Integer season = resultEpisode.getSeason();
            if (season == null) {
                return null;
            }
            int iIntValue = season.intValue();
            String imdbId = LoadResponse.INSTANCE.getImdbId(loadResponse);
            if (imdbId == null) {
                return null;
            }
            String str = "https://api.introdb.app/segments?imdb_id=" + imdbId + "&season=" + iIntValue + "&episode=" + resultEpisode.getEpisode();
            Requests app2 = MainActivityKt.getApp();
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(loadResponse);
            anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(resultEpisode);
            anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(imdbId);
            anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(str);
            anonymousClass1.J$0 = j;
            anonymousClass1.I$0 = iIntValue;
            anonymousClass1.label = 1;
            AnonymousClass1 anonymousClass2 = anonymousClass1;
            c = 1;
            Object obj2 = Requests.get$default(app2, str, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass2, 4094, null);
            if (obj2 == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = obj2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = anonymousClass1.I$0;
            long j2 = anonymousClass1.J$0;
            ResultKt.throwOnFailure(obj);
            c = 1;
        }
        NiceResponse niceResponse = (NiceResponse) obj;
        ResponseParser parser = niceResponse.getParser();
        Intrinsics.checkNotNull(parser);
        IntroDbResponse introDbResponse = (IntroDbResponse) parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(IntroDbResponse.class));
        SkipStamp[] skipStampArr = new SkipStamp[3];
        Segment intro = introDbResponse.getIntro();
        if (intro == null || (startMs3 = intro.getStartMs()) == null) {
            skipStamp = null;
        } else {
            long jLongValue = startMs3.longValue();
            Long endMs = intro.getEndMs();
            if (endMs != null) {
                skipStamp = new SkipStamp(SkipType.Opening, jLongValue, endMs.longValue(), null, 8, null);
            } else {
                skipStamp = null;
            }
        }
        skipStampArr[0] = skipStamp;
        Segment recap = introDbResponse.getRecap();
        if (recap == null || (startMs2 = recap.getStartMs()) == null) {
            skipStamp2 = null;
        } else {
            long jLongValue2 = startMs2.longValue();
            Long endMs2 = recap.getEndMs();
            if (endMs2 != null) {
                skipStamp2 = new SkipStamp(SkipType.Recap, jLongValue2, endMs2.longValue(), null, 8, null);
            } else {
                skipStamp2 = null;
            }
        }
        skipStampArr[c] = skipStamp2;
        Segment outro = introDbResponse.getOutro();
        if (outro != null && (startMs = outro.getStartMs()) != null) {
            long jLongValue3 = startMs.longValue();
            Long endMs3 = outro.getEndMs();
            if (endMs3 != null) {
                skipStamp3 = new SkipStamp(SkipType.Ending, jLongValue3, endMs3.longValue(), null, 8, null);
            }
        }
        skipStampArr[2] = skipStamp3;
        return CollectionsKt.listOfNotNull((Object[]) skipStampArr);
    }

    /* JADX INFO: compiled from: IntroDbSkip.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 82\u00020\u0001:\u000278BO\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\u000b\u0010\fBW\b\u0010\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u000b\u0010\u0010J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010#\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0017J\u0010\u0010$\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0017J\u000b\u0010%\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\bHÆ\u0003JV\u0010(\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010)J\u0014\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010-\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010.\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u00002\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u000205H\u0001¢\u0006\u0002\b6R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0018\u0012\u0004\b\u0015\u0010\u0012\u001a\u0004\b\u0016\u0010\u0017R \u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0018\u0012\u0004\b\u0019\u0010\u0012\u001a\u0004\b\u001a\u0010\u0017R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u0012\u001a\u0004\b\u001c\u0010\u001dR\u001e\u0010\t\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001e\u0010\u0012\u001a\u0004\b\u001f\u0010\u001dR\u001e\u0010\n\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b \u0010\u0012\u001a\u0004\b!\u0010\u001d¨\u00069"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/IntroDbSkip$IntroDbResponse;", "", "imdbId", "", "season", "", "episode", "intro", "Lcom/lagradost/cloudstream3/utils/videoskip/IntroDbSkip$Segment;", "recap", "outro", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/utils/videoskip/IntroDbSkip$Segment;Lcom/lagradost/cloudstream3/utils/videoskip/IntroDbSkip$Segment;Lcom/lagradost/cloudstream3/utils/videoskip/IntroDbSkip$Segment;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/utils/videoskip/IntroDbSkip$Segment;Lcom/lagradost/cloudstream3/utils/videoskip/IntroDbSkip$Segment;Lcom/lagradost/cloudstream3/utils/videoskip/IntroDbSkip$Segment;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getImdbId$annotations", "()V", "getImdbId", "()Ljava/lang/String;", "getSeason$annotations", "getSeason", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getEpisode$annotations", "getEpisode", "getIntro$annotations", "getIntro", "()Lcom/lagradost/cloudstream3/utils/videoskip/IntroDbSkip$Segment;", "getRecap$annotations", "getRecap", "getOutro$annotations", "getOutro", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/utils/videoskip/IntroDbSkip$Segment;Lcom/lagradost/cloudstream3/utils/videoskip/IntroDbSkip$Segment;Lcom/lagradost/cloudstream3/utils/videoskip/IntroDbSkip$Segment;)Lcom/lagradost/cloudstream3/utils/videoskip/IntroDbSkip$IntroDbResponse;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class IntroDbResponse {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("episode")
        private final Integer episode;

        @JsonProperty("imdb_id")
        private final String imdbId;

        @JsonProperty("intro")
        private final Segment intro;

        @JsonProperty("outro")
        private final Segment outro;

        @JsonProperty("recap")
        private final Segment recap;

        @JsonProperty("season")
        private final Integer season;

        public static /* synthetic */ IntroDbResponse copy$default(IntroDbResponse introDbResponse, String str, Integer num, Integer num2, Segment segment, Segment segment2, Segment segment3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = introDbResponse.imdbId;
            }
            if ((i & 2) != 0) {
                num = introDbResponse.season;
            }
            if ((i & 4) != 0) {
                num2 = introDbResponse.episode;
            }
            if ((i & 8) != 0) {
                segment = introDbResponse.intro;
            }
            if ((i & 16) != 0) {
                segment2 = introDbResponse.recap;
            }
            if ((i & 32) != 0) {
                segment3 = introDbResponse.outro;
            }
            Segment segment4 = segment2;
            Segment segment5 = segment3;
            return introDbResponse.copy(str, num, num2, segment, segment4, segment5);
        }

        @SerialName("episode")
        public static /* synthetic */ void getEpisode$annotations() {
        }

        @SerialName("imdb_id")
        public static /* synthetic */ void getImdbId$annotations() {
        }

        @SerialName("intro")
        public static /* synthetic */ void getIntro$annotations() {
        }

        @SerialName("outro")
        public static /* synthetic */ void getOutro$annotations() {
        }

        @SerialName("recap")
        public static /* synthetic */ void getRecap$annotations() {
        }

        @SerialName("season")
        public static /* synthetic */ void getSeason$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getImdbId() {
            return this.imdbId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getSeason() {
            return this.season;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Integer getEpisode() {
            return this.episode;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Segment getIntro() {
            return this.intro;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Segment getRecap() {
            return this.recap;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Segment getOutro() {
            return this.outro;
        }

        public final IntroDbResponse copy(@JsonProperty("imdb_id") String imdbId, @JsonProperty("season") Integer season, @JsonProperty("episode") Integer episode, @JsonProperty("intro") Segment intro, @JsonProperty("recap") Segment recap, @JsonProperty("outro") Segment outro) {
            return new IntroDbResponse(imdbId, season, episode, intro, recap, outro);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof IntroDbResponse)) {
                return false;
            }
            IntroDbResponse introDbResponse = (IntroDbResponse) other;
            return Intrinsics.areEqual(this.imdbId, introDbResponse.imdbId) && Intrinsics.areEqual(this.season, introDbResponse.season) && Intrinsics.areEqual(this.episode, introDbResponse.episode) && Intrinsics.areEqual(this.intro, introDbResponse.intro) && Intrinsics.areEqual(this.recap, introDbResponse.recap) && Intrinsics.areEqual(this.outro, introDbResponse.outro);
        }

        public int hashCode() {
            String str = this.imdbId;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            Integer num = this.season;
            int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
            Integer num2 = this.episode;
            int iHashCode3 = (iHashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
            Segment segment = this.intro;
            int iHashCode4 = (iHashCode3 + (segment == null ? 0 : segment.hashCode())) * 31;
            Segment segment2 = this.recap;
            int iHashCode5 = (iHashCode4 + (segment2 == null ? 0 : segment2.hashCode())) * 31;
            Segment segment3 = this.outro;
            return iHashCode5 + (segment3 != null ? segment3.hashCode() : 0);
        }

        public String toString() {
            return "IntroDbResponse(imdbId=" + this.imdbId + ", season=" + this.season + ", episode=" + this.episode + ", intro=" + this.intro + ", recap=" + this.recap + ", outro=" + this.outro + ')';
        }

        /* JADX INFO: compiled from: IntroDbSkip.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/IntroDbSkip$IntroDbResponse$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/videoskip/IntroDbSkip$IntroDbResponse;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<IntroDbResponse> serializer() {
                return IntroDbSkip$IntroDbResponse$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ IntroDbResponse(int i, String str, Integer num, Integer num2, Segment segment, Segment segment2, Segment segment3, SerializationConstructorMarker serializationConstructorMarker) {
            if (63 != (i & 63)) {
                PluginExceptionsKt.throwMissingFieldException(i, 63, IntroDbSkip$IntroDbResponse$$serializer.INSTANCE.getDescriptor());
            }
            this.imdbId = str;
            this.season = num;
            this.episode = num2;
            this.intro = segment;
            this.recap = segment2;
            this.outro = segment3;
        }

        public IntroDbResponse(@JsonProperty("imdb_id") String str, @JsonProperty("season") Integer num, @JsonProperty("episode") Integer num2, @JsonProperty("intro") Segment segment, @JsonProperty("recap") Segment segment2, @JsonProperty("outro") Segment segment3) {
            this.imdbId = str;
            this.season = num;
            this.episode = num2;
            this.intro = segment;
            this.recap = segment2;
            this.outro = segment3;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(IntroDbResponse self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.imdbId);
            output.encodeNullableSerializableElement(serialDesc, 1, IntSerializer.INSTANCE, self.season);
            output.encodeNullableSerializableElement(serialDesc, 2, IntSerializer.INSTANCE, self.episode);
            output.encodeNullableSerializableElement(serialDesc, 3, IntroDbSkip$Segment$$serializer.INSTANCE, self.intro);
            output.encodeNullableSerializableElement(serialDesc, 4, IntroDbSkip$Segment$$serializer.INSTANCE, self.recap);
            output.encodeNullableSerializableElement(serialDesc, 5, IntroDbSkip$Segment$$serializer.INSTANCE, self.outro);
        }

        public final String getImdbId() {
            return this.imdbId;
        }

        public final Integer getSeason() {
            return this.season;
        }

        public final Integer getEpisode() {
            return this.episode;
        }

        public final Segment getIntro() {
            return this.intro;
        }

        public final Segment getRecap() {
            return this.recap;
        }

        public final Segment getOutro() {
            return this.outro;
        }
    }

    /* JADX INFO: compiled from: IntroDbSkip.kt */
    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 @2\u00020\u0001:\u0002?@B[\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\r\u0010\u000eBa\b\u0010\u0012\u0006\u0010\u000f\u001a\u00020\n\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\r\u0010\u0012J\u0010\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0016J\u0010\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0016J\u0010\u0010+\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u001cJ\u0010\u0010,\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u001cJ\u0010\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0016J\u0010\u0010.\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010$J\u000b\u0010/\u001a\u0004\u0018\u00010\fHÆ\u0003Jb\u00100\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\fHÆ\u0001¢\u0006\u0002\u00101J\u0014\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00105\u001a\u00020\nHÖ\u0081\u0004J\n\u00106\u001a\u00020\fHÖ\u0081\u0004J%\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\u00002\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020=H\u0001¢\u0006\u0002\b>R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0017\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0017\u0012\u0004\b\u0018\u0010\u0014\u001a\u0004\b\u0019\u0010\u0016R \u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u001d\u0012\u0004\b\u001a\u0010\u0014\u001a\u0004\b\u001b\u0010\u001cR \u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u001d\u0012\u0004\b\u001e\u0010\u0014\u001a\u0004\b\u001f\u0010\u001cR \u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0017\u0012\u0004\b \u0010\u0014\u001a\u0004\b!\u0010\u0016R \u0010\t\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010%\u0012\u0004\b\"\u0010\u0014\u001a\u0004\b#\u0010$R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b&\u0010\u0014\u001a\u0004\b'\u0010(¨\u0006A"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/IntroDbSkip$Segment;", "", "startSec", "", "endSec", "startMs", "", "endMs", "confidence", "submissionCount", "", "updatedAt", "", "<init>", "(Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Double;Ljava/lang/Double;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getStartSec$annotations", "()V", "getStartSec", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getEndSec$annotations", "getEndSec", "getStartMs$annotations", "getStartMs", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getEndMs$annotations", "getEndMs", "getConfidence$annotations", "getConfidence", "getSubmissionCount$annotations", "getSubmissionCount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getUpdatedAt$annotations", "getUpdatedAt", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/String;)Lcom/lagradost/cloudstream3/utils/videoskip/IntroDbSkip$Segment;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Segment {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("confidence")
        private final Double confidence;

        @JsonProperty("end_ms")
        private final Long endMs;

        @JsonProperty("end_sec")
        private final Double endSec;

        @JsonProperty("start_ms")
        private final Long startMs;

        @JsonProperty("start_sec")
        private final Double startSec;

        @JsonProperty("submission_count")
        private final Integer submissionCount;

        @JsonProperty("updated_at")
        private final String updatedAt;

        public static /* synthetic */ Segment copy$default(Segment segment, Double d, Double d2, Long l, Long l2, Double d3, Integer num, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                d = segment.startSec;
            }
            if ((i & 2) != 0) {
                d2 = segment.endSec;
            }
            if ((i & 4) != 0) {
                l = segment.startMs;
            }
            if ((i & 8) != 0) {
                l2 = segment.endMs;
            }
            if ((i & 16) != 0) {
                d3 = segment.confidence;
            }
            if ((i & 32) != 0) {
                num = segment.submissionCount;
            }
            if ((i & 64) != 0) {
                str = segment.updatedAt;
            }
            Integer num2 = num;
            String str2 = str;
            Double d4 = d3;
            Long l3 = l;
            return segment.copy(d, d2, l3, l2, d4, num2, str2);
        }

        @SerialName("confidence")
        public static /* synthetic */ void getConfidence$annotations() {
        }

        @SerialName("end_ms")
        public static /* synthetic */ void getEndMs$annotations() {
        }

        @SerialName("end_sec")
        public static /* synthetic */ void getEndSec$annotations() {
        }

        @SerialName("start_ms")
        public static /* synthetic */ void getStartMs$annotations() {
        }

        @SerialName("start_sec")
        public static /* synthetic */ void getStartSec$annotations() {
        }

        @SerialName("submission_count")
        public static /* synthetic */ void getSubmissionCount$annotations() {
        }

        @SerialName("updated_at")
        public static /* synthetic */ void getUpdatedAt$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Double getStartSec() {
            return this.startSec;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Double getEndSec() {
            return this.endSec;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Long getStartMs() {
            return this.startMs;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Long getEndMs() {
            return this.endMs;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Double getConfidence() {
            return this.confidence;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Integer getSubmissionCount() {
            return this.submissionCount;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getUpdatedAt() {
            return this.updatedAt;
        }

        public final Segment copy(@JsonProperty("start_sec") Double startSec, @JsonProperty("end_sec") Double endSec, @JsonProperty("start_ms") Long startMs, @JsonProperty("end_ms") Long endMs, @JsonProperty("confidence") Double confidence, @JsonProperty("submission_count") Integer submissionCount, @JsonProperty("updated_at") String updatedAt) {
            return new Segment(startSec, endSec, startMs, endMs, confidence, submissionCount, updatedAt);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Segment)) {
                return false;
            }
            Segment segment = (Segment) other;
            return Intrinsics.areEqual((Object) this.startSec, (Object) segment.startSec) && Intrinsics.areEqual((Object) this.endSec, (Object) segment.endSec) && Intrinsics.areEqual(this.startMs, segment.startMs) && Intrinsics.areEqual(this.endMs, segment.endMs) && Intrinsics.areEqual((Object) this.confidence, (Object) segment.confidence) && Intrinsics.areEqual(this.submissionCount, segment.submissionCount) && Intrinsics.areEqual(this.updatedAt, segment.updatedAt);
        }

        public int hashCode() {
            Double d = this.startSec;
            int iHashCode = (d == null ? 0 : d.hashCode()) * 31;
            Double d2 = this.endSec;
            int iHashCode2 = (iHashCode + (d2 == null ? 0 : d2.hashCode())) * 31;
            Long l = this.startMs;
            int iHashCode3 = (iHashCode2 + (l == null ? 0 : l.hashCode())) * 31;
            Long l2 = this.endMs;
            int iHashCode4 = (iHashCode3 + (l2 == null ? 0 : l2.hashCode())) * 31;
            Double d3 = this.confidence;
            int iHashCode5 = (iHashCode4 + (d3 == null ? 0 : d3.hashCode())) * 31;
            Integer num = this.submissionCount;
            int iHashCode6 = (iHashCode5 + (num == null ? 0 : num.hashCode())) * 31;
            String str = this.updatedAt;
            return iHashCode6 + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "Segment(startSec=" + this.startSec + ", endSec=" + this.endSec + ", startMs=" + this.startMs + ", endMs=" + this.endMs + ", confidence=" + this.confidence + ", submissionCount=" + this.submissionCount + ", updatedAt=" + this.updatedAt + ')';
        }

        /* JADX INFO: compiled from: IntroDbSkip.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/IntroDbSkip$Segment$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/videoskip/IntroDbSkip$Segment;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Segment> serializer() {
                return IntroDbSkip$Segment$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Segment(int i, Double d, Double d2, Long l, Long l2, Double d3, Integer num, String str, SerializationConstructorMarker serializationConstructorMarker) {
            if (127 != (i & 127)) {
                PluginExceptionsKt.throwMissingFieldException(i, 127, IntroDbSkip$Segment$$serializer.INSTANCE.getDescriptor());
            }
            this.startSec = d;
            this.endSec = d2;
            this.startMs = l;
            this.endMs = l2;
            this.confidence = d3;
            this.submissionCount = num;
            this.updatedAt = str;
        }

        public Segment(@JsonProperty("start_sec") Double d, @JsonProperty("end_sec") Double d2, @JsonProperty("start_ms") Long l, @JsonProperty("end_ms") Long l2, @JsonProperty("confidence") Double d3, @JsonProperty("submission_count") Integer num, @JsonProperty("updated_at") String str) {
            this.startSec = d;
            this.endSec = d2;
            this.startMs = l;
            this.endMs = l2;
            this.confidence = d3;
            this.submissionCount = num;
            this.updatedAt = str;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Segment self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, DoubleSerializer.INSTANCE, self.startSec);
            output.encodeNullableSerializableElement(serialDesc, 1, DoubleSerializer.INSTANCE, self.endSec);
            output.encodeNullableSerializableElement(serialDesc, 2, LongSerializer.INSTANCE, self.startMs);
            output.encodeNullableSerializableElement(serialDesc, 3, LongSerializer.INSTANCE, self.endMs);
            output.encodeNullableSerializableElement(serialDesc, 4, DoubleSerializer.INSTANCE, self.confidence);
            output.encodeNullableSerializableElement(serialDesc, 5, IntSerializer.INSTANCE, self.submissionCount);
            output.encodeNullableSerializableElement(serialDesc, 6, StringSerializer.INSTANCE, self.updatedAt);
        }

        public final Double getStartSec() {
            return this.startSec;
        }

        public final Double getEndSec() {
            return this.endSec;
        }

        public final Long getStartMs() {
            return this.startMs;
        }

        public final Long getEndMs() {
            return this.endMs;
        }

        public final Double getConfidence() {
            return this.confidence;
        }

        public final Integer getSubmissionCount() {
            return this.submissionCount;
        }

        public final String getUpdatedAt() {
            return this.updatedAt;
        }
    }
}
