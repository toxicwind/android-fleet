package com.lagradost.cloudstream3.utils.videoskip;

import androidx.mediarouter.media.GlobalMediaRouter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.gms.actions.SearchIntents;
import com.lagradost.cloudstream3.AnimeLoadResponse;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.TvSeriesLoadResponse;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.syncproviders.AccountManager;
import com.lagradost.cloudstream3.syncproviders.AuthData;
import com.lagradost.cloudstream3.syncproviders.AuthToken;
import com.lagradost.cloudstream3.syncproviders.PlainAuthRepo;
import com.lagradost.cloudstream3.ui.result.ResultEpisode;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import com.lagradost.nicehttp.ResponseParser;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.Regex;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: AnimeSkip.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 $2\u00020\u0001:\u0007$%&'()*B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0096@¢\u0006\u0004\b\f\u0010\rR\u001a\u0010\u000f\u001a\u00020\u000e8\u0016X\u0096D¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R \u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00138\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u001a\u001a\u00020\u00198\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR#\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u001f0\u001e8\u0006¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#¨\u0006+"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip;", "Lcom/lagradost/cloudstream3/utils/videoskip/SkipAPI;", "<init>", "()V", "Lcom/lagradost/cloudstream3/LoadResponse;", "data", "Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "episode", "", "episodeDurationMs", "", "Lcom/lagradost/cloudstream3/utils/videoskip/SkipStamp;", "stamps", "(Lcom/lagradost/cloudstream3/LoadResponse;Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "name", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "", "Lcom/lagradost/cloudstream3/TvType;", "supportedTypes", "Ljava/util/Set;", "getSupportedTypes", "()Ljava/util/Set;", "Lcom/lagradost/cloudstream3/syncproviders/PlainAuthRepo;", "auth", "Lcom/lagradost/cloudstream3/syncproviders/PlainAuthRepo;", "getAuth", "()Lcom/lagradost/cloudstream3/syncproviders/PlainAuthRepo;", "j$/util/concurrent/ConcurrentHashMap", "Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip$Data;", "cache", "Lj$/util/concurrent/ConcurrentHashMap;", "getCache", "()Lj$/util/concurrent/ConcurrentHashMap;", "Companion", "Root", "Data", "SearchShow", "Episode", "Timestamp", "Type", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AnimeSkip extends SkipAPI {
    public static final int MIN_LENGTH = 4;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Regex strip = new Regex("[ :\\-.!]");
    private static final Regex asciiRegex = new Regex("[^a-zA-Z0-9 ]");
    private final String name = "AniSkip";
    private final Set<TvType> supportedTypes = SetsKt.setOf((Object[]) new TvType[]{TvType.Anime, TvType.OVA});
    private final PlainAuthRepo auth = new PlainAuthRepo(AccountManager.INSTANCE.getAnimeSkipApi());
    private final ConcurrentHashMap<String, Data> cache = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.videoskip.AnimeSkip$stamps$1, reason: invalid class name */
    /* JADX INFO: compiled from: AnimeSkip.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.videoskip.AnimeSkip", f = "AnimeSkip.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {GlobalMediaRouter.CallbackHandler.MSG_ROUTE_ANOTHER_SELECTED}, m = "stamps", n = {"data", "episode", "clientId", SearchIntents.EXTRA_QUERY, "$this$stamps_u24lambda_u240", "episodeDurationMs", "$i$a$-run-AnimeSkip$stamps$root$1"}, nl = {273}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "J$0", "I$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnimeSkip.this.stamps(null, null, 0L, this);
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

    public final PlainAuthRepo getAuth() {
        return this.auth;
    }

    /* JADX INFO: compiled from: AnimeSkip.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\tJ\u0012\u0010\f\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip$Companion;", "", "<init>", "()V", "MIN_LENGTH", "", "strip", "Lkotlin/text/Regex;", "stripName", "", "name", "asciiRegex", "asciiName", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String stripName(String name) {
            if (name == null) {
                return null;
            }
            String strReplace = AnimeSkip.strip.replace(name, "");
            if (strReplace == null) {
                return null;
            }
            String lowerCase = strReplace.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            return lowerCase;
        }

        public final String asciiName(String name) {
            if (name == null) {
                return null;
            }
            String strReplace = AnimeSkip.asciiRegex.replace(name, "");
            if (strReplace == null) {
                return null;
            }
            String lowerCase = strReplace.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            return lowerCase;
        }
    }

    /* JADX INFO: compiled from: AnimeSkip.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip$Root;", "", "data", "Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip$Data;", "<init>", "(Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip$Data;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip$Data;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getData$annotations", "()V", "getData", "()Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip$Data;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Root {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("data")
        private final Data data;

        public static /* synthetic */ Root copy$default(Root root, Data data, int i, Object obj) {
            if ((i & 1) != 0) {
                data = root.data;
            }
            return root.copy(data);
        }

        @SerialName("data")
        public static /* synthetic */ void getData$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Data getData() {
            return this.data;
        }

        public final Root copy(@JsonProperty("data") Data data) {
            Intrinsics.checkNotNullParameter(data, "data");
            return new Root(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Root) && Intrinsics.areEqual(this.data, ((Root) other).data);
        }

        public int hashCode() {
            return this.data.hashCode();
        }

        public String toString() {
            return "Root(data=" + this.data + ')';
        }

        /* JADX INFO: compiled from: AnimeSkip.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip$Root$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip$Root;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Root> serializer() {
                return AnimeSkip$Root$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Root(int i, Data data, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AnimeSkip$Root$$serializer.INSTANCE.getDescriptor());
            }
            this.data = data;
        }

        public Root(@JsonProperty("data") Data data) {
            Intrinsics.checkNotNullParameter(data, "data");
            this.data = data;
        }

        public final Data getData() {
            return this.data;
        }
    }

    /* JADX INFO: compiled from: AnimeSkip.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 !2\u00020\u0001:\u0002 !B\u0017\u0012\u000e\b\u0001\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006B+\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0005\u0010\u000bJ\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\u0011\u001a\u00020\u00002\u000e\b\u0003\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J%\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0001¢\u0006\u0002\b\u001fR\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\""}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip$Data;", "", "searchShows", "", "Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip$SearchShow;", "<init>", "(Ljava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getSearchShows$annotations", "()V", "getSearchShows", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Data {

        @JsonProperty("searchShows")
        private final List<SearchShow> searchShows;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.utils.videoskip.AnimeSkip$Data$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AnimeSkip.Data._childSerializers$_anonymous_();
            }
        })};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(AnimeSkip$SearchShow$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Data copy$default(Data data, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = data.searchShows;
            }
            return data.copy(list);
        }

        @SerialName("searchShows")
        public static /* synthetic */ void getSearchShows$annotations() {
        }

        public final List<SearchShow> component1() {
            return this.searchShows;
        }

        public final Data copy(@JsonProperty("searchShows") List<SearchShow> searchShows) {
            Intrinsics.checkNotNullParameter(searchShows, "searchShows");
            return new Data(searchShows);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Data) && Intrinsics.areEqual(this.searchShows, ((Data) other).searchShows);
        }

        public int hashCode() {
            return this.searchShows.hashCode();
        }

        public String toString() {
            return "Data(searchShows=" + this.searchShows + ')';
        }

        /* JADX INFO: compiled from: AnimeSkip.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip$Data$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip$Data;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Data> serializer() {
                return AnimeSkip$Data$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Data(int i, List list, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AnimeSkip$Data$$serializer.INSTANCE.getDescriptor());
            }
            this.searchShows = list;
        }

        public Data(@JsonProperty("searchShows") List<SearchShow> searchShows) {
            Intrinsics.checkNotNullParameter(searchShows, "searchShows");
            this.searchShows = searchShows;
        }

        public final List<SearchShow> getSearchShows() {
            return this.searchShows;
        }
    }

    /* JADX INFO: compiled from: AnimeSkip.kt */
    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 :2\u00020\u0001:\u00029:BK\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0001\u0010\b\u001a\u00020\t\u0012\u000e\b\u0001\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0004\b\r\u0010\u000eBW\b\u0010\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\r\u0010\u0013J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0006HÆ\u0003J\t\u0010(\u001a\u00020\u0006HÆ\u0003J\t\u0010)\u001a\u00020\tHÆ\u0003J\u000f\u0010*\u001a\b\u0012\u0004\u0012\u00020\f0\u000bHÆ\u0003JM\u0010+\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00062\b\b\u0003\u0010\u0007\u001a\u00020\u00062\b\b\u0003\u0010\b\u001a\u00020\t2\u000e\b\u0003\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bHÆ\u0001J\u0014\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010/\u001a\u00020\u0010HÖ\u0081\u0004J\n\u00100\u001a\u00020\u0003HÖ\u0081\u0004J%\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u00002\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u000207H\u0001¢\u0006\u0002\b8R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0015\u001a\u0004\b\u0019\u0010\u0017R\u001c\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0015\u001a\u0004\b\u001b\u0010\u001cR\u001c\u0010\u0007\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u0015\u001a\u0004\b\u001e\u0010\u001cR\u001c\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001f\u0010\u0015\u001a\u0004\b \u0010!R\"\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\"\u0010\u0015\u001a\u0004\b#\u0010$¨\u0006;"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip$SearchShow;", "", "name", "", "originalName", "seasonCount", "", "episodeCount", "baseDuration", "", "episodes", "", "Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip$Episode;", "<init>", "(Ljava/lang/String;Ljava/lang/String;JJDLjava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;JJDLjava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getName$annotations", "()V", "getName", "()Ljava/lang/String;", "getOriginalName$annotations", "getOriginalName", "getSeasonCount$annotations", "getSeasonCount", "()J", "getEpisodeCount$annotations", "getEpisodeCount", "getBaseDuration$annotations", "getBaseDuration", "()D", "getEpisodes$annotations", "getEpisodes", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class SearchShow {

        @JsonProperty("baseDuration")
        private final double baseDuration;

        @JsonProperty("episodeCount")
        private final long episodeCount;

        @JsonProperty("episodes")
        private final List<Episode> episodes;

        @JsonProperty("name")
        private final String name;

        @JsonProperty("originalName")
        private final String originalName;

        @JsonProperty("seasonCount")
        private final long seasonCount;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, null, null, null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.utils.videoskip.AnimeSkip$SearchShow$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AnimeSkip.SearchShow._childSerializers$_anonymous_();
            }
        })};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(AnimeSkip$Episode$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SearchShow copy$default(SearchShow searchShow, String str, String str2, long j, long j2, double d, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = searchShow.name;
            }
            if ((i & 2) != 0) {
                str2 = searchShow.originalName;
            }
            if ((i & 4) != 0) {
                j = searchShow.seasonCount;
            }
            if ((i & 8) != 0) {
                j2 = searchShow.episodeCount;
            }
            if ((i & 16) != 0) {
                d = searchShow.baseDuration;
            }
            if ((i & 32) != 0) {
                list = searchShow.episodes;
            }
            List list2 = list;
            double d2 = d;
            long j3 = j2;
            return searchShow.copy(str, str2, j, j3, d2, list2);
        }

        @SerialName("baseDuration")
        public static /* synthetic */ void getBaseDuration$annotations() {
        }

        @SerialName("episodeCount")
        public static /* synthetic */ void getEpisodeCount$annotations() {
        }

        @SerialName("episodes")
        public static /* synthetic */ void getEpisodes$annotations() {
        }

        @SerialName("name")
        public static /* synthetic */ void getName$annotations() {
        }

        @SerialName("originalName")
        public static /* synthetic */ void getOriginalName$annotations() {
        }

        @SerialName("seasonCount")
        public static /* synthetic */ void getSeasonCount$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getOriginalName() {
            return this.originalName;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final long getSeasonCount() {
            return this.seasonCount;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final long getEpisodeCount() {
            return this.episodeCount;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final double getBaseDuration() {
            return this.baseDuration;
        }

        public final List<Episode> component6() {
            return this.episodes;
        }

        public final SearchShow copy(@JsonProperty("name") String name, @JsonProperty("originalName") String originalName, @JsonProperty("seasonCount") long seasonCount, @JsonProperty("episodeCount") long episodeCount, @JsonProperty("baseDuration") double baseDuration, @JsonProperty("episodes") List<Episode> episodes) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(episodes, "episodes");
            return new SearchShow(name, originalName, seasonCount, episodeCount, baseDuration, episodes);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SearchShow)) {
                return false;
            }
            SearchShow searchShow = (SearchShow) other;
            return Intrinsics.areEqual(this.name, searchShow.name) && Intrinsics.areEqual(this.originalName, searchShow.originalName) && this.seasonCount == searchShow.seasonCount && this.episodeCount == searchShow.episodeCount && Double.compare(this.baseDuration, searchShow.baseDuration) == 0 && Intrinsics.areEqual(this.episodes, searchShow.episodes);
        }

        public int hashCode() {
            int iHashCode = this.name.hashCode() * 31;
            String str = this.originalName;
            return ((((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.seasonCount)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.episodeCount)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.baseDuration)) * 31) + this.episodes.hashCode();
        }

        public String toString() {
            return "SearchShow(name=" + this.name + ", originalName=" + this.originalName + ", seasonCount=" + this.seasonCount + ", episodeCount=" + this.episodeCount + ", baseDuration=" + this.baseDuration + ", episodes=" + this.episodes + ')';
        }

        /* JADX INFO: compiled from: AnimeSkip.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip$SearchShow$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip$SearchShow;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<SearchShow> serializer() {
                return AnimeSkip$SearchShow$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ SearchShow(int i, String str, String str2, long j, long j2, double d, List list, SerializationConstructorMarker serializationConstructorMarker) {
            if (63 != (i & 63)) {
                PluginExceptionsKt.throwMissingFieldException(i, 63, AnimeSkip$SearchShow$$serializer.INSTANCE.getDescriptor());
            }
            this.name = str;
            this.originalName = str2;
            this.seasonCount = j;
            this.episodeCount = j2;
            this.baseDuration = d;
            this.episodes = list;
        }

        public SearchShow(@JsonProperty("name") String name, @JsonProperty("originalName") String str, @JsonProperty("seasonCount") long j, @JsonProperty("episodeCount") long j2, @JsonProperty("baseDuration") double d, @JsonProperty("episodes") List<Episode> episodes) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(episodes, "episodes");
            this.name = name;
            this.originalName = str;
            this.seasonCount = j;
            this.episodeCount = j2;
            this.baseDuration = d;
            this.episodes = episodes;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(SearchShow self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            output.encodeStringElement(serialDesc, 0, self.name);
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.originalName);
            output.encodeLongElement(serialDesc, 2, self.seasonCount);
            output.encodeLongElement(serialDesc, 3, self.episodeCount);
            output.encodeDoubleElement(serialDesc, 4, self.baseDuration);
            output.encodeSerializableElement(serialDesc, 5, lazyArr[5].getValue(), self.episodes);
        }

        public final String getName() {
            return this.name;
        }

        public final String getOriginalName() {
            return this.originalName;
        }

        public final long getSeasonCount() {
            return this.seasonCount;
        }

        public final long getEpisodeCount() {
            return this.episodeCount;
        }

        public final double getBaseDuration() {
            return this.baseDuration;
        }

        public final List<Episode> getEpisodes() {
            return this.episodes;
        }
    }

    /* JADX INFO: compiled from: AnimeSkip.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 .2\u00020\u0001:\u0002-.B;\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0001\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nBI\b\u0010\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\t\u0010\u000fJ\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J=\u0010\u001f\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\u000e\b\u0003\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001J\u0014\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010#\u001a\u00020\fHÖ\u0081\u0004J\n\u0010$\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00002\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0001¢\u0006\u0002\b,R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0013R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0017\u0010\u0013R\"\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0011\u001a\u0004\b\u0019\u0010\u001a¨\u0006/"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip$Episode;", "", "number", "", "absoluteNumber", "season", "timestamps", "", "Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip$Timestamp;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getNumber$annotations", "()V", "getNumber", "()Ljava/lang/String;", "getAbsoluteNumber$annotations", "getAbsoluteNumber", "getSeason$annotations", "getSeason", "getTimestamps$annotations", "getTimestamps", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Episode {

        @JsonProperty("absoluteNumber")
        private final String absoluteNumber;

        @JsonProperty("number")
        private final String number;

        @JsonProperty("season")
        private final String season;

        @JsonProperty("timestamps")
        private final List<Timestamp> timestamps;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.utils.videoskip.AnimeSkip$Episode$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AnimeSkip.Episode._childSerializers$_anonymous_();
            }
        })};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(AnimeSkip$Timestamp$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Episode copy$default(Episode episode, String str, String str2, String str3, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = episode.number;
            }
            if ((i & 2) != 0) {
                str2 = episode.absoluteNumber;
            }
            if ((i & 4) != 0) {
                str3 = episode.season;
            }
            if ((i & 8) != 0) {
                list = episode.timestamps;
            }
            return episode.copy(str, str2, str3, list);
        }

        @SerialName("absoluteNumber")
        public static /* synthetic */ void getAbsoluteNumber$annotations() {
        }

        @SerialName("number")
        public static /* synthetic */ void getNumber$annotations() {
        }

        @SerialName("season")
        public static /* synthetic */ void getSeason$annotations() {
        }

        @SerialName("timestamps")
        public static /* synthetic */ void getTimestamps$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getNumber() {
            return this.number;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getAbsoluteNumber() {
            return this.absoluteNumber;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getSeason() {
            return this.season;
        }

        public final List<Timestamp> component4() {
            return this.timestamps;
        }

        public final Episode copy(@JsonProperty("number") String number, @JsonProperty("absoluteNumber") String absoluteNumber, @JsonProperty("season") String season, @JsonProperty("timestamps") List<Timestamp> timestamps) {
            Intrinsics.checkNotNullParameter(timestamps, "timestamps");
            return new Episode(number, absoluteNumber, season, timestamps);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Episode)) {
                return false;
            }
            Episode episode = (Episode) other;
            return Intrinsics.areEqual(this.number, episode.number) && Intrinsics.areEqual(this.absoluteNumber, episode.absoluteNumber) && Intrinsics.areEqual(this.season, episode.season) && Intrinsics.areEqual(this.timestamps, episode.timestamps);
        }

        public int hashCode() {
            String str = this.number;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.absoluteNumber;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.season;
            return ((iHashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31) + this.timestamps.hashCode();
        }

        public String toString() {
            return "Episode(number=" + this.number + ", absoluteNumber=" + this.absoluteNumber + ", season=" + this.season + ", timestamps=" + this.timestamps + ')';
        }

        /* JADX INFO: compiled from: AnimeSkip.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip$Episode$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip$Episode;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Episode> serializer() {
                return AnimeSkip$Episode$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Episode(int i, String str, String str2, String str3, List list, SerializationConstructorMarker serializationConstructorMarker) {
            if (15 != (i & 15)) {
                PluginExceptionsKt.throwMissingFieldException(i, 15, AnimeSkip$Episode$$serializer.INSTANCE.getDescriptor());
            }
            this.number = str;
            this.absoluteNumber = str2;
            this.season = str3;
            this.timestamps = list;
        }

        public Episode(@JsonProperty("number") String str, @JsonProperty("absoluteNumber") String str2, @JsonProperty("season") String str3, @JsonProperty("timestamps") List<Timestamp> timestamps) {
            Intrinsics.checkNotNullParameter(timestamps, "timestamps");
            this.number = str;
            this.absoluteNumber = str2;
            this.season = str3;
            this.timestamps = timestamps;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Episode self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.number);
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.absoluteNumber);
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.season);
            output.encodeSerializableElement(serialDesc, 3, lazyArr[3].getValue(), self.timestamps);
        }

        public final String getNumber() {
            return this.number;
        }

        public final String getAbsoluteNumber() {
            return this.absoluteNumber;
        }

        public final String getSeason() {
            return this.season;
        }

        public final List<Timestamp> getTimestamps() {
            return this.timestamps;
        }
    }

    /* JADX INFO: compiled from: AnimeSkip.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 &2\u00020\u0001:\u0002%&B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B-\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0016\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J%\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0001¢\u0006\u0002\b$R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0013¨\u0006'"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip$Timestamp;", "", "at", "", "type", "Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip$Type;", "<init>", "(DLcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip$Type;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IDLcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip$Type;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getAt$annotations", "()V", "getAt", "()D", "getType$annotations", "getType", "()Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip$Type;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Timestamp {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("at")
        private final double at;

        @JsonProperty("type")
        private final Type type;

        public static /* synthetic */ Timestamp copy$default(Timestamp timestamp, double d, Type type, int i, Object obj) {
            if ((i & 1) != 0) {
                d = timestamp.at;
            }
            if ((i & 2) != 0) {
                type = timestamp.type;
            }
            return timestamp.copy(d, type);
        }

        @SerialName("at")
        public static /* synthetic */ void getAt$annotations() {
        }

        @SerialName("type")
        public static /* synthetic */ void getType$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final double getAt() {
            return this.at;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Type getType() {
            return this.type;
        }

        public final Timestamp copy(@JsonProperty("at") double at, @JsonProperty("type") Type type) {
            Intrinsics.checkNotNullParameter(type, "type");
            return new Timestamp(at, type);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Timestamp)) {
                return false;
            }
            Timestamp timestamp = (Timestamp) other;
            return Double.compare(this.at, timestamp.at) == 0 && Intrinsics.areEqual(this.type, timestamp.type);
        }

        public int hashCode() {
            return (NextAiring$$ExternalSyntheticBackport0.m(this.at) * 31) + this.type.hashCode();
        }

        public String toString() {
            return "Timestamp(at=" + this.at + ", type=" + this.type + ')';
        }

        /* JADX INFO: compiled from: AnimeSkip.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip$Timestamp$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip$Timestamp;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Timestamp> serializer() {
                return AnimeSkip$Timestamp$$serializer.INSTANCE;
            }
        }

        public Timestamp(@JsonProperty("at") double d, @JsonProperty("type") Type type) {
            Intrinsics.checkNotNullParameter(type, "type");
            this.at = d;
            this.type = type;
        }

        public /* synthetic */ Timestamp(int i, double d, Type type, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, AnimeSkip$Timestamp$$serializer.INSTANCE.getDescriptor());
            }
            this.at = d;
            this.type = type;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Timestamp self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeDoubleElement(serialDesc, 0, self.at);
            output.encodeSerializableElement(serialDesc, 1, AnimeSkip$Type$$serializer.INSTANCE, self.type);
        }

        public final double getAt() {
            return this.at;
        }

        public final Type getType() {
            return this.type;
        }
    }

    /* JADX INFO: compiled from: AnimeSkip.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \u001f2\u00020\u0001:\u0002\u001e\u001fB\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0001¢\u0006\u0002\b\u001dR\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip$Type;", "", "name", "", "<init>", "(Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getName$annotations", "()V", "getName", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Type {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("name")
        private final String name;

        public static /* synthetic */ Type copy$default(Type type, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = type.name;
            }
            return type.copy(str);
        }

        @SerialName("name")
        public static /* synthetic */ void getName$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        public final Type copy(@JsonProperty("name") String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            return new Type(name);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Type) && Intrinsics.areEqual(this.name, ((Type) other).name);
        }

        public int hashCode() {
            return this.name.hashCode();
        }

        public String toString() {
            return "Type(name=" + this.name + ')';
        }

        /* JADX INFO: compiled from: AnimeSkip.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip$Type$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkip$Type;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Type> serializer() {
                return AnimeSkip$Type$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Type(int i, String str, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AnimeSkip$Type$$serializer.INSTANCE.getDescriptor());
            }
            this.name = str;
        }

        public Type(@JsonProperty("name") String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            this.name = name;
        }

        public final String getName() {
            return this.name;
        }
    }

    public final ConcurrentHashMap<String, Data> getCache() {
        return this.cache;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:100:0x02ae  */
    /* JADX WARN: Code duplicated, block: B:105:0x02c6  */
    /* JADX WARN: Code duplicated, block: B:106:0x02c9  */
    /* JADX WARN: Code duplicated, block: B:108:0x02cd  */
    /* JADX WARN: Code duplicated, block: B:110:0x02d3  */
    /* JADX WARN: Code duplicated, block: B:111:0x02dc  */
    /* JADX WARN: Code duplicated, block: B:115:0x02fe  */
    /* JADX WARN: Code duplicated, block: B:120:0x031b  */
    /* JADX WARN: Code duplicated, block: B:126:0x0327  */
    /* JADX WARN: Code duplicated, block: B:129:0x0337  */
    /* JADX WARN: Code duplicated, block: B:134:0x034e  */
    /* JADX WARN: Code duplicated, block: B:135:0x0350  */
    /* JADX WARN: Code duplicated, block: B:137:0x0354 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:138:0x0355  */
    /* JADX WARN: Code duplicated, block: B:142:0x036a  */
    /* JADX WARN: Code duplicated, block: B:144:0x037d  */
    /* JADX WARN: Code duplicated, block: B:145:0x0396  */
    /* JADX WARN: Code duplicated, block: B:149:0x03a8  */
    /* JADX WARN: Code duplicated, block: B:150:0x03ab  */
    /* JADX WARN: Code duplicated, block: B:153:0x03b4  */
    /* JADX WARN: Code duplicated, block: B:154:0x03b7  */
    /* JADX WARN: Code duplicated, block: B:155:0x03ba  */
    /* JADX WARN: Code duplicated, block: B:156:0x03bd  */
    /* JADX WARN: Code duplicated, block: B:159:0x03c6  */
    /* JADX WARN: Code duplicated, block: B:160:0x03c9  */
    /* JADX WARN: Code duplicated, block: B:163:0x03d2  */
    /* JADX WARN: Code duplicated, block: B:164:0x03d5  */
    /* JADX WARN: Code duplicated, block: B:166:0x03db  */
    /* JADX WARN: Code duplicated, block: B:169:0x03e4  */
    /* JADX WARN: Code duplicated, block: B:170:0x03e7  */
    /* JADX WARN: Code duplicated, block: B:173:0x03f0  */
    /* JADX WARN: Code duplicated, block: B:174:0x03f3  */
    /* JADX WARN: Code duplicated, block: B:177:0x03fc  */
    /* JADX WARN: Code duplicated, block: B:179:0x0400  */
    /* JADX WARN: Code duplicated, block: B:182:0x0406 A[LOOP:4: B:140:0x0364->B:182:0x0406, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:184:0x0418  */
    /* JADX WARN: Code duplicated, block: B:195:0x0264 A[EDGE_INSN: B:195:0x0264->B:83:0x0264 BREAK  A[LOOP:0: B:57:0x01ed->B:201:0x01ed], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:196:0x0224 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:197:0x0262 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:198:0x0264 A[EDGE_INSN: B:198:0x0264->B:83:0x0264 BREAK  A[LOOP:0: B:57:0x01ed->B:201:0x01ed], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:199:0x0256 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:200:0x0264 A[EDGE_INSN: B:200:0x0264->B:83:0x0264 BREAK  A[LOOP:0: B:57:0x01ed->B:201:0x01ed], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:202:0x01ed A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:203:0x01ed A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:204:0x01ed A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:205:0x0297 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:208:0x02c0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:211:0x0416 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:215:0x0320 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:220:0x0349 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:59:0x01f3  */
    /* JADX WARN: Code duplicated, block: B:61:0x0213 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:62:0x0215  */
    /* JADX WARN: Code duplicated, block: B:63:0x021a  */
    /* JADX WARN: Code duplicated, block: B:66:0x021f  */
    /* JADX WARN: Code duplicated, block: B:71:0x0245 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:72:0x0247  */
    /* JADX WARN: Code duplicated, block: B:73:0x024c  */
    /* JADX WARN: Code duplicated, block: B:78:0x0258  */
    /* JADX WARN: Code duplicated, block: B:79:0x025d  */
    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    /* JADX WARN: Code duplicated, block: B:85:0x0268 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:86:0x0269  */
    /* JADX WARN: Code duplicated, block: B:88:0x026d  */
    /* JADX WARN: Code duplicated, block: B:91:0x0285  */
    /* JADX WARN: Code duplicated, block: B:97:0x029e  */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.lagradost.cloudstream3.utils.videoskip.SkipAPI
    public Object stamps(LoadResponse loadResponse, ResultEpisode resultEpisode, long j, Continuation<? super List<SkipStamp>> continuation) throws JsonProcessingException {
        AnonymousClass1 anonymousClass1;
        AuthToken token;
        String payload;
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Data data;
        int i;
        Episode episode;
        ResultEpisode resultEpisode2;
        long j2;
        long j3;
        Iterator<T> it;
        Object next;
        SearchShow searchShow;
        Episode episode2;
        Integer season;
        Object objValueOf;
        String strValueOf;
        Iterator<T> it2;
        Object next2;
        Episode episode3;
        Iterator<T> it3;
        Object next3;
        Episode episode4;
        int i2;
        ArrayList arrayList;
        Iterator<Timestamp> it4;
        SkipStamp skipStamp;
        long at;
        long j4;
        String name;
        SkipType skipType;
        SkipType skipType2;
        String str;
        String strValueOf2;
        Iterator<T> it5;
        Object next4;
        Episode episode5;
        Iterator<T> it6;
        Object next5;
        SearchShow searchShow2;
        Companion companion;
        String strAsciiName;
        String strAsciiName2;
        String strStripName;
        String strStripName2;
        String strStripName3;
        int length;
        int length2;
        int length3;
        AnimeSkip animeSkip = this;
        LoadResponse loadResponse2 = loadResponse;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = animeSkip.new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = animeSkip.new AnonymousClass1(continuation);
        }
        Object objPost$default = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = anonymousClass1.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(objPost$default);
            AppUtils appUtils = AppUtils.INSTANCE;
            AuthData authData = animeSkip.auth.authData();
            if (authData == null || (token = authData.getToken()) == null || (payload = token.getPayload()) == null) {
                return null;
            }
            try {
                Result.Companion companion2 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(AnimeSkipAuth.Payload.INSTANCE.serializer());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                try {
                    Result.Companion companion4 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(AnimeSkipAuth.Payload.class), null, 2, null));
                } catch (Throwable th2) {
                    Result.Companion companion5 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                }
            }
            if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                objM8936constructorimpl = null;
            }
            KSerializer kSerializer = (KSerializer) objM8936constructorimpl;
            if (kSerializer != null) {
                try {
                    objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, payload);
                } catch (SerializationException e) {
                    ArchComponentExtKt.logError(e);
                    objDecodeFromString = MainAPIKt.getMapper().readValue(payload, new TypeReference<AnimeSkipAuth.Payload>() { // from class: com.lagradost.cloudstream3.utils.videoskip.AnimeSkip$stamps$$inlined$parseJson$1
                    });
                } catch (Throwable unused) {
                    objDecodeFromString = MainAPIKt.getMapper().readValue(payload, new TypeReference<AnimeSkipAuth.Payload>() { // from class: com.lagradost.cloudstream3.utils.videoskip.AnimeSkip$stamps$$inlined$parseJson$1
                    });
                }
            } else {
                objDecodeFromString = MainAPIKt.getMapper().readValue(payload, new TypeReference<AnimeSkipAuth.Payload>() { // from class: com.lagradost.cloudstream3.utils.videoskip.AnimeSkip$stamps$$inlined$parseJson$1
                });
            }
            String clientId = ((AnimeSkipAuth.Payload) objDecodeFromString).getClientId();
            if (!(loadResponse2 instanceof AnimeLoadResponse) && !(loadResponse2 instanceof TvSeriesLoadResponse)) {
                return null;
            }
            String str2 = "{\n  searchShows(search: \"" + loadResponse2.getName() + "\", limit: 1) {\n    name\n    originalName\n    seasonCount\n    episodeCount\n    episodes {\n      number\n      absoluteNumber\n      season\n      baseDuration\n      timestamps {\n        at\n        type {\n          name\n        }\n      }\n    }\n  }\n}";
            data = animeSkip.cache.get(loadResponse2.getName());
            if (data == null) {
                Requests app2 = MainActivityKt.getApp();
                Map mapMapOf = MapsKt.mapOf(TuplesKt.to(SearchIntents.EXTRA_QUERY, str2));
                Map mapMapOf2 = MapsKt.mapOf(TuplesKt.to("Accept", "*/*"), TuplesKt.to("content-type", "application/json"), TuplesKt.to("X-Client-ID", clientId));
                anonymousClass1.L$0 = loadResponse2;
                anonymousClass1.L$1 = resultEpisode;
                anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(clientId);
                anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(str2);
                anonymousClass1.L$4 = animeSkip;
                anonymousClass1.J$0 = j;
                anonymousClass1.I$0 = 0;
                anonymousClass1.label = 1;
                i = 0;
                episode = null;
                objPost$default = Requests.post$default(app2, "https://api.anime-skip.com/graphql", mapMapOf2, null, null, null, null, null, mapMapOf, null, false, 0, null, 0L, null, false, null, anonymousClass1, 65404, null);
                if (objPost$default == coroutine_suspended) {
                    return coroutine_suspended;
                }
                resultEpisode2 = resultEpisode;
                j3 = j;
            } else {
                i = 0;
                episode = null;
                resultEpisode2 = resultEpisode;
                j2 = j;
            }
            it = data.getSearchShows().iterator();
            while (true) {
                if (it.hasNext()) {
                    next = episode;
                    break;
                }
                next = it.next();
                searchShow2 = (SearchShow) next;
                companion = INSTANCE;
                strAsciiName = companion.asciiName(loadResponse2.getName());
                strAsciiName2 = companion.asciiName(searchShow2.getName());
                if (!Intrinsics.areEqual(strAsciiName, strAsciiName2)) {
                    if (strAsciiName != null) {
                        length3 = strAsciiName.length();
                    } else {
                        length3 = i;
                    }
                    if (length3 > 4) {
                        break;
                    }
                    if (!(loadResponse2 instanceof AnimeLoadResponse)) {
                        strStripName = companion.stripName(searchShow2.getOriginalName());
                        AnimeLoadResponse animeLoadResponse = (AnimeLoadResponse) loadResponse2;
                        strStripName2 = companion.stripName(animeLoadResponse.getJapName());
                        strStripName3 = companion.stripName(animeLoadResponse.getEngName());
                        if (Intrinsics.areEqual(strStripName, strStripName2)) {
                            if (strStripName != null) {
                                length2 = strStripName.length();
                            } else {
                                length2 = i;
                            }
                            if (length2 <= 4) {
                                break;
                                break;
                            }
                        }
                        if (Intrinsics.areEqual(strAsciiName2, strStripName3)) {
                            if (strAsciiName2 != null) {
                                length = strAsciiName2.length();
                            } else {
                                length = i;
                            }
                            if (length > 4) {
                                break;
                                break;
                            }
                        } else {
                            continue;
                        }
                    }
                } else if (!(loadResponse2 instanceof AnimeLoadResponse)) {
                    strStripName = companion.stripName(searchShow2.getOriginalName());
                    AnimeLoadResponse animeLoadResponse2 = (AnimeLoadResponse) loadResponse2;
                    strStripName2 = companion.stripName(animeLoadResponse2.getJapName());
                    strStripName3 = companion.stripName(animeLoadResponse2.getEngName());
                    if (Intrinsics.areEqual(strStripName, strStripName2)) {
                        if (strStripName != null) {
                            length2 = strStripName.length();
                        } else {
                            length2 = i;
                        }
                        if (length2 <= 4) {
                            break;
                        }
                    }
                    if (Intrinsics.areEqual(strAsciiName2, strStripName3)) {
                        if (strAsciiName2 != null) {
                            length = strAsciiName2.length();
                        } else {
                            length = i;
                        }
                        if (length > 4) {
                            break;
                        }
                    } else {
                        continue;
                    }
                }
            }
            searchShow = (SearchShow) next;
            if (searchShow == null) {
                return episode;
            }
            if (loadResponse2 instanceof AnimeLoadResponse) {
                strValueOf2 = String.valueOf(resultEpisode2.getEpisode());
                it5 = searchShow.getEpisodes().iterator();
                do {
                    if (it5.hasNext()) {
                        next4 = episode;
                        break;
                    }
                    next4 = it5.next();
                } while (!Intrinsics.areEqual(((Episode) next4).getAbsoluteNumber(), strValueOf2));
                episode5 = (Episode) next4;
                if (episode5 == null) {
                    it6 = searchShow.getEpisodes().iterator();
                    do {
                        if (it6.hasNext()) {
                            next5 = episode;
                            break;
                        }
                        next5 = it6.next();
                    } while (!Intrinsics.areEqual(((Episode) next5).getNumber(), strValueOf2));
                    episode2 = (Episode) next5;
                } else {
                    episode2 = episode5;
                }
            } else if (loadResponse2 instanceof TvSeriesLoadResponse) {
                season = resultEpisode2.getSeason();
                if (season != null) {
                    objValueOf = String.valueOf(season.intValue());
                } else {
                    objValueOf = episode;
                }
                String strValueOf3 = String.valueOf(resultEpisode2.getEpisode());
                strValueOf = String.valueOf(resultEpisode2.getTotalEpisodeIndex());
                it2 = searchShow.getEpisodes().iterator();
                do {
                    if (it2.hasNext()) {
                        next2 = episode;
                        break;
                    }
                    next2 = it2.next();
                    episode4 = (Episode) next2;
                    if (Intrinsics.areEqual(episode4.getSeason(), objValueOf) || !Intrinsics.areEqual(episode4.getNumber(), strValueOf3)) {
                        i2 = i;
                    } else {
                        i2 = 1;
                    }
                } while (i2 == 0);
                episode3 = (Episode) next2;
                if (episode3 == null) {
                    it3 = searchShow.getEpisodes().iterator();
                    do {
                        if (it3.hasNext()) {
                            next3 = episode;
                            break;
                        }
                        next3 = it3.next();
                    } while (!Intrinsics.areEqual(((Episode) next3).getAbsoluteNumber(), strValueOf));
                    episode2 = (Episode) next3;
                } else {
                    episode2 = episode3;
                }
            } else {
                episode2 = episode;
            }
            if (episode2 == null) {
                return episode;
            }
            arrayList = new ArrayList();
            it4 = episode2.getTimestamps().iterator();
            while (true) {
                skipStamp = episode;
                while (true) {
                    if (it4.hasNext()) {
                        if (skipStamp != 0) {
                            Boxing.boxBoolean(arrayList.add(SkipStamp.copy$default(skipStamp, null, 0L, j2, null, 11, null)));
                        }
                        return arrayList;
                    }
                    Timestamp next6 = it4.next();
                    at = (long) (next6.getAt() * 1000.0d);
                    if (skipStamp != 0) {
                        j4 = at;
                        Boxing.boxBoolean(arrayList.add(SkipStamp.copy$default(skipStamp, null, 0L, at, null, 11, null)));
                    } else {
                        j4 = at;
                    }
                    name = next6.getType().getName();
                    switch (name.hashCode()) {
                        case -1730381515:
                            if (name.equals("Mixed Credits")) {
                                skipType = episode;
                            } else {
                                skipType2 = SkipType.MixedEnding;
                                skipType = skipType2;
                            }
                            break;
                        case -1601680262:
                            if (name.equals("Credits")) {
                                skipType = episode;
                            } else {
                                skipType2 = SkipType.Credits;
                                skipType = skipType2;
                            }
                            break;
                        case -91645012:
                            if (name.equals("New Intro")) {
                                skipType = episode;
                            } else {
                                skipType2 = SkipType.Intro;
                                skipType = skipType2;
                            }
                            break;
                        case 39268123:
                            str = "Branding";
                            name.equals(str);
                            skipType = episode;
                            break;
                        case 64874895:
                            str = "Canon";
                            name.equals(str);
                            skipType = episode;
                            break;
                        case 70809164:
                            if (name.equals("Intro")) {
                                skipType = episode;
                            } else {
                                skipType2 = SkipType.Intro;
                                skipType = skipType2;
                            }
                            break;
                        case 78835871:
                            if (name.equals("Recap")) {
                                skipType = episode;
                            } else {
                                skipType2 = SkipType.Recap;
                                skipType = skipType2;
                            }
                            break;
                        case 160452632:
                            str = "Title Card";
                            name.equals(str);
                            skipType = episode;
                            break;
                        case 269306229:
                            str = "Transition";
                            name.equals(str);
                            skipType = episode;
                            break;
                        case 1346468776:
                            if (name.equals("Preview")) {
                                skipType = episode;
                            } else {
                                skipType2 = SkipType.Preview;
                                skipType = skipType2;
                            }
                            break;
                        case 2104334736:
                            str = "Filler";
                            name.equals(str);
                            skipType = episode;
                            break;
                        default:
                            skipType = episode;
                            break;
                    }
                    if (skipType == 0) {
                        break;
                    }
                    skipStamp = new SkipStamp(skipType, j4, 0L, null, 8, null);
                }
            }
        } else {
            if (i3 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i4 = anonymousClass1.I$0;
            long j5 = anonymousClass1.J$0;
            AnimeSkip animeSkip2 = (AnimeSkip) anonymousClass1.L$4;
            resultEpisode2 = (ResultEpisode) anonymousClass1.L$1;
            LoadResponse loadResponse3 = (LoadResponse) anonymousClass1.L$0;
            ResultKt.throwOnFailure(objPost$default);
            i = 0;
            episode = null;
            loadResponse2 = loadResponse3;
            animeSkip = animeSkip2;
            j3 = j5;
        }
        NiceResponse niceResponse = (NiceResponse) objPost$default;
        ResponseParser parser = niceResponse.getParser();
        Intrinsics.checkNotNull(parser);
        data = ((Root) parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(Root.class))).getData();
        animeSkip.cache.put(loadResponse2.getName(), data);
        j2 = j3;
        it = data.getSearchShows().iterator();
        while (true) {
            if (it.hasNext()) {
                next = episode;
                break;
            }
            next = it.next();
            searchShow2 = (SearchShow) next;
            companion = INSTANCE;
            strAsciiName = companion.asciiName(loadResponse2.getName());
            strAsciiName2 = companion.asciiName(searchShow2.getName());
            if (!Intrinsics.areEqual(strAsciiName, strAsciiName2)) {
                if (strAsciiName != null) {
                    length3 = strAsciiName.length();
                } else {
                    length3 = i;
                }
                if (length3 > 4) {
                    break;
                    break;
                }
                if (!(loadResponse2 instanceof AnimeLoadResponse)) {
                    strStripName = companion.stripName(searchShow2.getOriginalName());
                    AnimeLoadResponse animeLoadResponse3 = (AnimeLoadResponse) loadResponse2;
                    strStripName2 = companion.stripName(animeLoadResponse3.getJapName());
                    strStripName3 = companion.stripName(animeLoadResponse3.getEngName());
                    if (Intrinsics.areEqual(strStripName, strStripName2)) {
                        if (strStripName != null) {
                            length2 = strStripName.length();
                        } else {
                            length2 = i;
                        }
                        if (length2 <= 4) {
                            break;
                            break;
                        }
                    }
                    if (Intrinsics.areEqual(strAsciiName2, strStripName3)) {
                        if (strAsciiName2 != null) {
                            length = strAsciiName2.length();
                        } else {
                            length = i;
                        }
                        if (length > 4) {
                            break;
                            break;
                        }
                    } else {
                        continue;
                    }
                }
            } else if (!(loadResponse2 instanceof AnimeLoadResponse)) {
                strStripName = companion.stripName(searchShow2.getOriginalName());
                AnimeLoadResponse animeLoadResponse4 = (AnimeLoadResponse) loadResponse2;
                strStripName2 = companion.stripName(animeLoadResponse4.getJapName());
                strStripName3 = companion.stripName(animeLoadResponse4.getEngName());
                if (Intrinsics.areEqual(strStripName, strStripName2)) {
                    if (strStripName != null) {
                        length2 = strStripName.length();
                    } else {
                        length2 = i;
                    }
                    if (length2 <= 4) {
                        break;
                        break;
                    }
                }
                if (Intrinsics.areEqual(strAsciiName2, strStripName3)) {
                    if (strAsciiName2 != null) {
                        length = strAsciiName2.length();
                    } else {
                        length = i;
                    }
                    if (length > 4) {
                        break;
                        break;
                    }
                } else {
                    continue;
                }
            }
        }
        searchShow = (SearchShow) next;
        if (searchShow == null) {
            return episode;
        }
        if (loadResponse2 instanceof AnimeLoadResponse) {
            strValueOf2 = String.valueOf(resultEpisode2.getEpisode());
            it5 = searchShow.getEpisodes().iterator();
            do {
                if (it5.hasNext()) {
                    next4 = episode;
                    break;
                }
                next4 = it5.next();
            } while (!Intrinsics.areEqual(((Episode) next4).getAbsoluteNumber(), strValueOf2));
            episode5 = (Episode) next4;
            if (episode5 == null) {
                it6 = searchShow.getEpisodes().iterator();
                do {
                    if (it6.hasNext()) {
                        next5 = episode;
                        break;
                    }
                    next5 = it6.next();
                } while (!Intrinsics.areEqual(((Episode) next5).getNumber(), strValueOf2));
                episode2 = (Episode) next5;
            } else {
                episode2 = episode5;
            }
        } else if (loadResponse2 instanceof TvSeriesLoadResponse) {
            season = resultEpisode2.getSeason();
            if (season != null) {
                objValueOf = String.valueOf(season.intValue());
            } else {
                objValueOf = episode;
            }
            String strValueOf4 = String.valueOf(resultEpisode2.getEpisode());
            strValueOf = String.valueOf(resultEpisode2.getTotalEpisodeIndex());
            it2 = searchShow.getEpisodes().iterator();
            do {
                if (it2.hasNext()) {
                    next2 = episode;
                    break;
                }
                next2 = it2.next();
                episode4 = (Episode) next2;
                if (Intrinsics.areEqual(episode4.getSeason(), objValueOf)) {
                    i2 = i;
                } else {
                    i2 = i;
                }
            } while (i2 == 0);
            episode3 = (Episode) next2;
            if (episode3 == null) {
                it3 = searchShow.getEpisodes().iterator();
                do {
                    if (it3.hasNext()) {
                        next3 = episode;
                        break;
                    }
                    next3 = it3.next();
                } while (!Intrinsics.areEqual(((Episode) next3).getAbsoluteNumber(), strValueOf));
                episode2 = (Episode) next3;
            } else {
                episode2 = episode3;
            }
        } else {
            episode2 = episode;
        }
        if (episode2 == null) {
            return episode;
        }
        arrayList = new ArrayList();
        it4 = episode2.getTimestamps().iterator();
        while (true) {
            skipStamp = episode;
            while (true) {
                if (it4.hasNext()) {
                    if (skipStamp != 0) {
                        Boxing.boxBoolean(arrayList.add(SkipStamp.copy$default(skipStamp, null, 0L, j2, null, 11, null)));
                    }
                    return arrayList;
                }
                Timestamp next7 = it4.next();
                at = (long) (next7.getAt() * 1000.0d);
                if (skipStamp != 0) {
                    j4 = at;
                    Boxing.boxBoolean(arrayList.add(SkipStamp.copy$default(skipStamp, null, 0L, at, null, 11, null)));
                } else {
                    j4 = at;
                }
                name = next7.getType().getName();
                switch (name.hashCode()) {
                    case -1730381515:
                        if (name.equals("Mixed Credits")) {
                            skipType2 = SkipType.MixedEnding;
                            skipType = skipType2;
                        } else {
                            skipType = episode;
                        }
                        break;
                    case -1601680262:
                        if (name.equals("Credits")) {
                            skipType2 = SkipType.Credits;
                            skipType = skipType2;
                        } else {
                            skipType = episode;
                        }
                        break;
                    case -91645012:
                        if (name.equals("New Intro")) {
                            skipType2 = SkipType.Intro;
                            skipType = skipType2;
                        } else {
                            skipType = episode;
                        }
                        break;
                    case 39268123:
                        str = "Branding";
                        name.equals(str);
                        skipType = episode;
                        break;
                    case 64874895:
                        str = "Canon";
                        name.equals(str);
                        skipType = episode;
                        break;
                    case 70809164:
                        if (name.equals("Intro")) {
                            skipType2 = SkipType.Intro;
                            skipType = skipType2;
                        } else {
                            skipType = episode;
                        }
                        break;
                    case 78835871:
                        if (name.equals("Recap")) {
                            skipType2 = SkipType.Recap;
                            skipType = skipType2;
                        } else {
                            skipType = episode;
                        }
                        break;
                    case 160452632:
                        str = "Title Card";
                        name.equals(str);
                        skipType = episode;
                        break;
                    case 269306229:
                        str = "Transition";
                        name.equals(str);
                        skipType = episode;
                        break;
                    case 1346468776:
                        if (name.equals("Preview")) {
                            skipType2 = SkipType.Preview;
                            skipType = skipType2;
                        } else {
                            skipType = episode;
                        }
                        break;
                    case 2104334736:
                        str = "Filler";
                        name.equals(str);
                        skipType = episode;
                        break;
                    default:
                        skipType = episode;
                        break;
                }
                if (skipType == 0) {
                    break;
                }
                skipStamp = new SkipStamp(skipType, j4, 0L, null, 8, null);
            }
        }
    }
}
