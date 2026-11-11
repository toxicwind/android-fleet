package com.lagradost.cloudstream3.utils.videoskip;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.AnimeLoadResponse;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.ui.result.ResultEpisode;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import com.lagradost.nicehttp.ResponseParser;
import io.ktor.http.ContentType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ResultKt;
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
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: AniSkip.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0003\u0017\u0018\u0019B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0096@¢\u0006\u0002\u0010\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u001a"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AniSkip;", "Lcom/lagradost/cloudstream3/utils/videoskip/SkipAPI;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "supportedTypes", "", "Lcom/lagradost/cloudstream3/TvType;", "getSupportedTypes", "()Ljava/util/Set;", "stamps", "", "Lcom/lagradost/cloudstream3/utils/videoskip/SkipStamp;", "data", "Lcom/lagradost/cloudstream3/LoadResponse;", "episode", "Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "episodeDurationMs", "", "(Lcom/lagradost/cloudstream3/LoadResponse;Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "AniSkipResponse", "Stamp", "AniSkipInterval", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AniSkip extends SkipAPI {
    private final String name = "AniSkip";
    private final Set<TvType> supportedTypes = SetsKt.setOf((Object[]) new TvType[]{TvType.Anime, TvType.OVA});

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.videoskip.AniSkip$stamps$1, reason: invalid class name */
    /* JADX INFO: compiled from: AniSkip.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.videoskip.AniSkip", f = "AniSkip.kt", i = {0, 0, 0, 0, 0}, l = {30}, m = "stamps", n = {"data", "episode", ImagesContract.URL, "episodeDurationMs", "malId"}, nl = {75}, s = {"L$0", "L$1", "L$2", "J$0", "I$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AniSkip.this.stamps(null, null, 0L, this);
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

    /* JADX WARN: Code duplicated, block: B:58:0x0157  */
    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    @Override // com.lagradost.cloudstream3.utils.videoskip.SkipAPI
    public Object stamps(LoadResponse loadResponse, ResultEpisode resultEpisode, long j, Continuation<? super List<SkipStamp>> continuation) {
        AnonymousClass1 anonymousClass1;
        String malId;
        Integer intOrNull;
        SkipType skipType;
        SkipType skipType2;
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
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if ((loadResponse instanceof AnimeLoadResponse) && (malId = LoadResponse.INSTANCE.getMalId(loadResponse)) != null && (intOrNull = StringsKt.toIntOrNull(malId)) != null) {
                int iIntValue = intOrNull.intValue();
                String str = "https://api.aniskip.com/v2/skip-times/" + iIntValue + '/' + resultEpisode.getEpisode() + "?types[]=ed&types[]=mixed-ed&types[]=mixed-op&types[]=op&types[]=recap&episodeLength=" + (j / 1000);
                Requests app2 = MainActivityKt.getApp();
                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(loadResponse);
                anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(resultEpisode);
                anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(str);
                anonymousClass1.J$0 = j;
                anonymousClass1.I$0 = iIntValue;
                anonymousClass1.label = 1;
                obj = Requests.get$default(app2, str, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass1, 4094, null);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return null;
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        int i2 = anonymousClass1.I$0;
        long j2 = anonymousClass1.J$0;
        ResultKt.throwOnFailure(obj);
        NiceResponse niceResponse = (NiceResponse) obj;
        ResponseParser parser = niceResponse.getParser();
        Intrinsics.checkNotNull(parser);
        List<Stamp> results = ((AniSkipResponse) parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(AniSkipResponse.class))).getResults();
        if (results != null) {
            ArrayList arrayList = new ArrayList();
            for (Stamp stamp : results) {
                String skipType3 = stamp.getSkipType();
                int iHashCode = skipType3.hashCode();
                if (iHashCode != -1076796495) {
                    if (iHashCode != -1076796173) {
                        if (iHashCode != 3231) {
                            if (iHashCode != 3553) {
                                if (iHashCode == 108388543 && skipType3.equals("recap")) {
                                    skipType = SkipType.Recap;
                                    skipType2 = skipType;
                                } else {
                                    skipType2 = null;
                                }
                            } else if (skipType3.equals("op")) {
                                skipType = SkipType.Opening;
                                skipType2 = skipType;
                            } else {
                                skipType2 = null;
                            }
                        } else if (skipType3.equals("ed")) {
                            skipType = SkipType.Ending;
                            skipType2 = skipType;
                        } else {
                            skipType2 = null;
                        }
                    } else if (skipType3.equals("mixed-op")) {
                        skipType = SkipType.MixedOpening;
                        skipType2 = skipType;
                    } else {
                        skipType2 = null;
                    }
                } else if (skipType3.equals("mixed-ed")) {
                    skipType = SkipType.MixedEnding;
                    skipType2 = skipType;
                } else {
                    skipType2 = null;
                }
                SkipStamp skipStamp = skipType2 == null ? null : new SkipStamp(skipType2, (long) (stamp.getInterval().getStartTime() * 1000.0d), (long) (stamp.getInterval().getEndTime() * 1000.0d), null, 8, null);
                if (skipStamp != null) {
                    arrayList.add(skipStamp);
                }
            }
            return arrayList;
        }
        return null;
    }

    /* JADX INFO: compiled from: AniSkip.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 02\u00020\u0001:\u0002/0B9\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0001\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0001\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fBE\b\u0010\u0012\u0006\u0010\r\u001a\u00020\n\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u000b\u0010\u0010J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\bHÆ\u0003J\t\u0010!\u001a\u00020\nHÆ\u0003J;\u0010\"\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\u0010\b\u0003\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0003\u0010\t\u001a\u00020\nHÆ\u0001J\u0014\u0010#\u001a\u00020\u00032\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010%\u001a\u00020\nHÖ\u0081\u0004J\n\u0010&\u001a\u00020\bHÖ\u0081\u0004J%\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0001¢\u0006\u0002\b.R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R$\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0012\u001a\u0004\b\u0016\u0010\u0017R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0012\u001a\u0004\b\u0019\u0010\u001aR\u001c\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u0012\u001a\u0004\b\u001c\u0010\u001d¨\u00061"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AniSkip$AniSkipResponse;", "", "found", "", "results", "", "Lcom/lagradost/cloudstream3/utils/videoskip/AniSkip$Stamp;", ContentType.Message.TYPE, "", "statusCode", "", "<init>", "(ZLjava/util/List;Ljava/lang/String;I)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IZLjava/util/List;Ljava/lang/String;ILkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getFound$annotations", "()V", "getFound", "()Z", "getResults$annotations", "getResults", "()Ljava/util/List;", "getMessage$annotations", "getMessage", "()Ljava/lang/String;", "getStatusCode$annotations", "getStatusCode", "()I", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class AniSkipResponse {

        @JsonProperty("found")
        private final boolean found;

        @JsonProperty(ContentType.Message.TYPE)
        private final String message;

        @JsonProperty("results")
        private final List<Stamp> results;

        @JsonProperty("statusCode")
        private final int statusCode;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.utils.videoskip.AniSkip$AniSkipResponse$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AniSkip.AniSkipResponse._childSerializers$_anonymous_();
            }
        }), null, null};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(AniSkip$Stamp$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ AniSkipResponse copy$default(AniSkipResponse aniSkipResponse, boolean z, List list, String str, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                z = aniSkipResponse.found;
            }
            if ((i2 & 2) != 0) {
                list = aniSkipResponse.results;
            }
            if ((i2 & 4) != 0) {
                str = aniSkipResponse.message;
            }
            if ((i2 & 8) != 0) {
                i = aniSkipResponse.statusCode;
            }
            return aniSkipResponse.copy(z, list, str, i);
        }

        @SerialName("found")
        public static /* synthetic */ void getFound$annotations() {
        }

        @SerialName(ContentType.Message.TYPE)
        public static /* synthetic */ void getMessage$annotations() {
        }

        @SerialName("results")
        public static /* synthetic */ void getResults$annotations() {
        }

        @SerialName("statusCode")
        public static /* synthetic */ void getStatusCode$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getFound() {
            return this.found;
        }

        public final List<Stamp> component2() {
            return this.results;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getStatusCode() {
            return this.statusCode;
        }

        public final AniSkipResponse copy(@JsonProperty("found") boolean found, @JsonProperty("results") List<Stamp> results, @JsonProperty(ContentType.Message.TYPE) String message, @JsonProperty("statusCode") int statusCode) {
            return new AniSkipResponse(found, results, message, statusCode);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AniSkipResponse)) {
                return false;
            }
            AniSkipResponse aniSkipResponse = (AniSkipResponse) other;
            return this.found == aniSkipResponse.found && Intrinsics.areEqual(this.results, aniSkipResponse.results) && Intrinsics.areEqual(this.message, aniSkipResponse.message) && this.statusCode == aniSkipResponse.statusCode;
        }

        public int hashCode() {
            int iM = NextAiring$$ExternalSyntheticBackport0.m(this.found) * 31;
            List<Stamp> list = this.results;
            int iHashCode = (iM + (list == null ? 0 : list.hashCode())) * 31;
            String str = this.message;
            return ((iHashCode + (str != null ? str.hashCode() : 0)) * 31) + this.statusCode;
        }

        public String toString() {
            return "AniSkipResponse(found=" + this.found + ", results=" + this.results + ", message=" + this.message + ", statusCode=" + this.statusCode + ')';
        }

        /* JADX INFO: compiled from: AniSkip.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AniSkip$AniSkipResponse$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/videoskip/AniSkip$AniSkipResponse;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<AniSkipResponse> serializer() {
                return AniSkip$AniSkipResponse$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ AniSkipResponse(int i, boolean z, List list, String str, int i2, SerializationConstructorMarker serializationConstructorMarker) {
            if (15 != (i & 15)) {
                PluginExceptionsKt.throwMissingFieldException(i, 15, AniSkip$AniSkipResponse$$serializer.INSTANCE.getDescriptor());
            }
            this.found = z;
            this.results = list;
            this.message = str;
            this.statusCode = i2;
        }

        public AniSkipResponse(@JsonProperty("found") boolean z, @JsonProperty("results") List<Stamp> list, @JsonProperty(ContentType.Message.TYPE) String str, @JsonProperty("statusCode") int i) {
            this.found = z;
            this.results = list;
            this.message = str;
            this.statusCode = i;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(AniSkipResponse self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            output.encodeBooleanElement(serialDesc, 0, self.found);
            output.encodeNullableSerializableElement(serialDesc, 1, lazyArr[1].getValue(), self.results);
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.message);
            output.encodeIntElement(serialDesc, 3, self.statusCode);
        }

        public final boolean getFound() {
            return this.found;
        }

        public final List<Stamp> getResults() {
            return this.results;
        }

        public final String getMessage() {
            return this.message;
        }

        public final int getStatusCode() {
            return this.statusCode;
        }
    }

    /* JADX INFO: compiled from: AniSkip.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 /2\u00020\u0001:\u0002./B/\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nBA\b\u0010\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\t\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\bHÆ\u0003J1\u0010 \u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0014\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010$\u001a\u00020\fHÖ\u0081\u0004J\n\u0010%\u001a\u00020\u0005HÖ\u0081\u0004J%\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u00002\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0001¢\u0006\u0002\b-R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0011\u001a\u0004\b\u0018\u0010\u0016R\u001c\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u0011\u001a\u0004\b\u001a\u0010\u001b¨\u00060"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AniSkip$Stamp;", "", "interval", "Lcom/lagradost/cloudstream3/utils/videoskip/AniSkip$AniSkipInterval;", "skipType", "", "skipId", "episodeLength", "", "<init>", "(Lcom/lagradost/cloudstream3/utils/videoskip/AniSkip$AniSkipInterval;Ljava/lang/String;Ljava/lang/String;D)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/utils/videoskip/AniSkip$AniSkipInterval;Ljava/lang/String;Ljava/lang/String;DLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getInterval$annotations", "()V", "getInterval", "()Lcom/lagradost/cloudstream3/utils/videoskip/AniSkip$AniSkipInterval;", "getSkipType$annotations", "getSkipType", "()Ljava/lang/String;", "getSkipId$annotations", "getSkipId", "getEpisodeLength$annotations", "getEpisodeLength", "()D", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Stamp {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("episodeLength")
        private final double episodeLength;

        @JsonProperty("interval")
        private final AniSkipInterval interval;

        @JsonProperty("skipId")
        private final String skipId;

        @JsonProperty("skipType")
        private final String skipType;

        public static /* synthetic */ Stamp copy$default(Stamp stamp, AniSkipInterval aniSkipInterval, String str, String str2, double d, int i, Object obj) {
            if ((i & 1) != 0) {
                aniSkipInterval = stamp.interval;
            }
            if ((i & 2) != 0) {
                str = stamp.skipType;
            }
            if ((i & 4) != 0) {
                str2 = stamp.skipId;
            }
            if ((i & 8) != 0) {
                d = stamp.episodeLength;
            }
            String str3 = str2;
            return stamp.copy(aniSkipInterval, str, str3, d);
        }

        @SerialName("episodeLength")
        public static /* synthetic */ void getEpisodeLength$annotations() {
        }

        @SerialName("interval")
        public static /* synthetic */ void getInterval$annotations() {
        }

        @SerialName("skipId")
        public static /* synthetic */ void getSkipId$annotations() {
        }

        @SerialName("skipType")
        public static /* synthetic */ void getSkipType$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final AniSkipInterval getInterval() {
            return this.interval;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getSkipType() {
            return this.skipType;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getSkipId() {
            return this.skipId;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final double getEpisodeLength() {
            return this.episodeLength;
        }

        public final Stamp copy(@JsonProperty("interval") AniSkipInterval interval, @JsonProperty("skipType") String skipType, @JsonProperty("skipId") String skipId, @JsonProperty("episodeLength") double episodeLength) {
            Intrinsics.checkNotNullParameter(interval, "interval");
            Intrinsics.checkNotNullParameter(skipType, "skipType");
            Intrinsics.checkNotNullParameter(skipId, "skipId");
            return new Stamp(interval, skipType, skipId, episodeLength);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Stamp)) {
                return false;
            }
            Stamp stamp = (Stamp) other;
            return Intrinsics.areEqual(this.interval, stamp.interval) && Intrinsics.areEqual(this.skipType, stamp.skipType) && Intrinsics.areEqual(this.skipId, stamp.skipId) && Double.compare(this.episodeLength, stamp.episodeLength) == 0;
        }

        public int hashCode() {
            return (((((this.interval.hashCode() * 31) + this.skipType.hashCode()) * 31) + this.skipId.hashCode()) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.episodeLength);
        }

        public String toString() {
            return "Stamp(interval=" + this.interval + ", skipType=" + this.skipType + ", skipId=" + this.skipId + ", episodeLength=" + this.episodeLength + ')';
        }

        /* JADX INFO: compiled from: AniSkip.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AniSkip$Stamp$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/videoskip/AniSkip$Stamp;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Stamp> serializer() {
                return AniSkip$Stamp$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Stamp(int i, AniSkipInterval aniSkipInterval, String str, String str2, double d, SerializationConstructorMarker serializationConstructorMarker) {
            if (15 != (i & 15)) {
                PluginExceptionsKt.throwMissingFieldException(i, 15, AniSkip$Stamp$$serializer.INSTANCE.getDescriptor());
            }
            this.interval = aniSkipInterval;
            this.skipType = str;
            this.skipId = str2;
            this.episodeLength = d;
        }

        public Stamp(@JsonProperty("interval") AniSkipInterval interval, @JsonProperty("skipType") String skipType, @JsonProperty("skipId") String skipId, @JsonProperty("episodeLength") double d) {
            Intrinsics.checkNotNullParameter(interval, "interval");
            Intrinsics.checkNotNullParameter(skipType, "skipType");
            Intrinsics.checkNotNullParameter(skipId, "skipId");
            this.interval = interval;
            this.skipType = skipType;
            this.skipId = skipId;
            this.episodeLength = d;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Stamp self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeSerializableElement(serialDesc, 0, AniSkip$AniSkipInterval$$serializer.INSTANCE, self.interval);
            output.encodeStringElement(serialDesc, 1, self.skipType);
            output.encodeStringElement(serialDesc, 2, self.skipId);
            output.encodeDoubleElement(serialDesc, 3, self.episodeLength);
        }

        public final AniSkipInterval getInterval() {
            return this.interval;
        }

        public final String getSkipType() {
            return this.skipType;
        }

        public final String getSkipId() {
            return this.skipId;
        }

        public final double getEpisodeLength() {
            return this.episodeLength;
        }
    }

    /* JADX INFO: compiled from: AniSkip.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 $2\u00020\u0001:\u0002#$B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B+\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0005\u0010\u000bJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u0014\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004J%\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0001¢\u0006\u0002\b\"R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u000f¨\u0006%"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AniSkip$AniSkipInterval;", "", "startTime", "", "endTime", "<init>", "(DD)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IDDLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getStartTime$annotations", "()V", "getStartTime", "()D", "getEndTime$annotations", "getEndTime", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class AniSkipInterval {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("endTime")
        private final double endTime;

        @JsonProperty("startTime")
        private final double startTime;

        public static /* synthetic */ AniSkipInterval copy$default(AniSkipInterval aniSkipInterval, double d, double d2, int i, Object obj) {
            if ((i & 1) != 0) {
                d = aniSkipInterval.startTime;
            }
            if ((i & 2) != 0) {
                d2 = aniSkipInterval.endTime;
            }
            return aniSkipInterval.copy(d, d2);
        }

        @SerialName("endTime")
        public static /* synthetic */ void getEndTime$annotations() {
        }

        @SerialName("startTime")
        public static /* synthetic */ void getStartTime$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final double getStartTime() {
            return this.startTime;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final double getEndTime() {
            return this.endTime;
        }

        public final AniSkipInterval copy(@JsonProperty("startTime") double startTime, @JsonProperty("endTime") double endTime) {
            return new AniSkipInterval(startTime, endTime);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AniSkipInterval)) {
                return false;
            }
            AniSkipInterval aniSkipInterval = (AniSkipInterval) other;
            return Double.compare(this.startTime, aniSkipInterval.startTime) == 0 && Double.compare(this.endTime, aniSkipInterval.endTime) == 0;
        }

        public int hashCode() {
            return (NextAiring$$ExternalSyntheticBackport0.m(this.startTime) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.endTime);
        }

        public String toString() {
            return "AniSkipInterval(startTime=" + this.startTime + ", endTime=" + this.endTime + ')';
        }

        /* JADX INFO: compiled from: AniSkip.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AniSkip$AniSkipInterval$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/videoskip/AniSkip$AniSkipInterval;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<AniSkipInterval> serializer() {
                return AniSkip$AniSkipInterval$$serializer.INSTANCE;
            }
        }

        public AniSkipInterval(@JsonProperty("startTime") double d, @JsonProperty("endTime") double d2) {
            this.startTime = d;
            this.endTime = d2;
        }

        public /* synthetic */ AniSkipInterval(int i, double d, double d2, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, AniSkip$AniSkipInterval$$serializer.INSTANCE.getDescriptor());
            }
            this.startTime = d;
            this.endTime = d2;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(AniSkipInterval self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeDoubleElement(serialDesc, 0, self.startTime);
            output.encodeDoubleElement(serialDesc, 1, self.endTime);
        }

        public final double getStartTime() {
            return this.startTime;
        }

        public final double getEndTime() {
            return this.endTime;
        }
    }
}
