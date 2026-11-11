package com.lagradost.cloudstream3.metaproviders;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.ErrorLoadingException;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MovieLoadResponse;
import com.lagradost.cloudstream3.MovieSearchResponse;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.ParCollectionsKt;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.SearchResponseList;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.AtomicList;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
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
import kotlinx.serialization.internal.PairSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: CrossTmdbProvider.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u00014B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H\u0002JF\u0010 \u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\u00102\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020&0$2\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020&0$H\u0096@¢\u0006\u0002\u0010)J \u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010,\u001a\u00020\u00052\u0006\u0010-\u001a\u00020.H\u0096@¢\u0006\u0002\u0010/J\u0018\u00100\u001a\u0004\u0018\u0001012\u0006\u00102\u001a\u00020\u0005H\u0096@¢\u0006\u0002\u00103R\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u001a\u0010\f\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0007\"\u0004\b\u000e\u0010\tR\u0014\u0010\u000f\u001a\u00020\u0010X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u0010X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u001a\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f¨\u00065"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/CrossTmdbProvider;", "Lcom/lagradost/cloudstream3/metaproviders/TmdbProvider;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "apiName", "getApiName", "lang", "getLang", "setLang", "useMetaLoadResponse", "", "getUseMetaLoadResponse", "()Z", "usesWebView", "getUsesWebView", "supportedTypes", "", "Lcom/lagradost/cloudstream3/TvType;", "getSupportedTypes", "()Ljava/util/Set;", "filterName", "validApis", "Lcom/lagradost/cloudstream3/utils/AtomicList;", "Lcom/lagradost/cloudstream3/MainAPI;", "getValidApis", "()Lcom/lagradost/cloudstream3/utils/AtomicList;", "loadLinks", "data", "isCasting", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;ZLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "search", "Lcom/lagradost/cloudstream3/SearchResponseList;", SearchIntents.EXTRA_QUERY, "page", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "load", "Lcom/lagradost/cloudstream3/LoadResponse;", ImagesContract.URL, "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "CrossMetaData", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CrossTmdbProvider extends TmdbProvider {
    private String name = "MultiMovie";
    private final String apiName = "MultiMovie";
    private String lang = "en";
    private final boolean useMetaLoadResponse = true;
    private final boolean usesWebView = true;
    private final Set<TvType> supportedTypes = SetsKt.setOf(TvType.Movie);

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.metaproviders.CrossTmdbProvider$load$1, reason: invalid class name */
    /* JADX INFO: compiled from: CrossTmdbProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.CrossTmdbProvider", f = "CrossTmdbProvider.kt", i = {0, 1, 1, 1, 1}, l = {76, 82}, m = "load", n = {ImagesContract.URL, ImagesContract.URL, "$this$load_u24lambda_u240", "matchName", "$i$a$-apply-CrossTmdbProvider$load$base$1"}, nl = {77, 112}, s = {"L$0", "L$0", "L$2", "L$3", "I$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
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
            return CrossTmdbProvider.this.load(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.metaproviders.CrossTmdbProvider$loadLinks$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CrossTmdbProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.CrossTmdbProvider", f = "CrossTmdbProvider.kt", i = {0, 0, 0, 0, 0, 0}, l = {52}, m = "loadLinks", n = {"data", "subtitleCallback", "callback", "metaData", "isCasting", "$i$a$-let-CrossTmdbProvider$loadLinks$2"}, nl = {62}, s = {"L$0", "L$1", "L$2", "L$3", "Z$0", "I$0"}, v = 2)
    static final class C07251 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C07251(Continuation<? super C07251> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CrossTmdbProvider.this.loadLinks(null, false, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.metaproviders.CrossTmdbProvider$search$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CrossTmdbProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.CrossTmdbProvider", f = "CrossTmdbProvider.kt", i = {0, 0}, l = {69}, m = "search", n = {SearchIntents.EXTRA_QUERY, "page"}, nl = {70}, s = {"L$0", "I$0"}, v = 2)
    static final class C07261 extends ContinuationImpl {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C07261(Continuation<? super C07261> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CrossTmdbProvider.this.search(null, 0, this);
        }
    }

    @Override // com.lagradost.cloudstream3.MainAPI
    public String getName() {
        return this.name;
    }

    @Override // com.lagradost.cloudstream3.MainAPI
    public void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    @Override // com.lagradost.cloudstream3.metaproviders.TmdbProvider
    public String getApiName() {
        return this.apiName;
    }

    @Override // com.lagradost.cloudstream3.MainAPI
    public String getLang() {
        return this.lang;
    }

    @Override // com.lagradost.cloudstream3.MainAPI
    public void setLang(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.lang = str;
    }

    @Override // com.lagradost.cloudstream3.metaproviders.TmdbProvider
    public boolean getUseMetaLoadResponse() {
        return this.useMetaLoadResponse;
    }

    @Override // com.lagradost.cloudstream3.MainAPI
    public boolean getUsesWebView() {
        return this.usesWebView;
    }

    @Override // com.lagradost.cloudstream3.MainAPI
    public Set<TvType> getSupportedTypes() {
        return this.supportedTypes;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String filterName(String name) {
        return new Regex("[^a-zA-Z0-9-]").replace(name, "");
    }

    static final boolean _get_validApis_$lambda$0(CrossTmdbProvider crossTmdbProvider, MainAPI it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Intrinsics.areEqual(it.getLang(), crossTmdbProvider.getLang()) && it.getClass() != crossTmdbProvider.getClass();
    }

    private final AtomicList<MainAPI> getValidApis() {
        return APIHolder.INSTANCE.getApis().filter(new Function1() { // from class: com.lagradost.cloudstream3.metaproviders.CrossTmdbProvider$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(CrossTmdbProvider._get_validApis_$lambda$0(this.f$0, (MainAPI) obj));
            }
        });
    }

    /* JADX INFO: compiled from: CrossTmdbProvider.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 %2\u00020\u0001:\u0002$%B/\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u001c\b\u0003\u0010\u0004\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006\u0018\u00010\u0005¢\u0006\u0004\b\b\u0010\tB?\b\u0010\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u001a\u0010\u0004\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006\u0018\u00010\u0005\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\b\u0010\u000eJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u0016\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006\u0018\u00010\u0005HÆ\u0003J1\u0010\u0017\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\u001c\b\u0003\u0010\u0004\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u0018\u001a\u00020\u00032\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u000bHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u0007HÖ\u0081\u0004J%\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0001¢\u0006\u0002\b#R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0002\u0010\u0011R0\u0010\u0004\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0010\u001a\u0004\b\u0013\u0010\u0014¨\u0006&"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/CrossTmdbProvider$CrossMetaData;", "", "isSuccess", "", "movies", "", "Lkotlin/Pair;", "", "<init>", "(ZLjava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IZLjava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "isSuccess$annotations", "()V", "()Z", "getMovies$annotations", "getMovies", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$library", "$serializer", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class CrossMetaData {

        @JsonProperty("isSuccess")
        private final boolean isSuccess;

        @JsonProperty("movies")
        private final List<Pair<String, String>> movies;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.metaproviders.CrossTmdbProvider$CrossMetaData$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return CrossTmdbProvider.CrossMetaData._childSerializers$_anonymous_();
            }
        })};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(new PairSerializer(StringSerializer.INSTANCE, StringSerializer.INSTANCE));
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ CrossMetaData copy$default(CrossMetaData crossMetaData, boolean z, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                z = crossMetaData.isSuccess;
            }
            if ((i & 2) != 0) {
                list = crossMetaData.movies;
            }
            return crossMetaData.copy(z, list);
        }

        @SerialName("movies")
        public static /* synthetic */ void getMovies$annotations() {
        }

        @SerialName("isSuccess")
        public static /* synthetic */ void isSuccess$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getIsSuccess() {
            return this.isSuccess;
        }

        public final List<Pair<String, String>> component2() {
            return this.movies;
        }

        public final CrossMetaData copy(@JsonProperty("isSuccess") boolean isSuccess, @JsonProperty("movies") List<Pair<String, String>> movies) {
            return new CrossMetaData(isSuccess, movies);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CrossMetaData)) {
                return false;
            }
            CrossMetaData crossMetaData = (CrossMetaData) other;
            return this.isSuccess == crossMetaData.isSuccess && Intrinsics.areEqual(this.movies, crossMetaData.movies);
        }

        public int hashCode() {
            int iM = NextAiring$$ExternalSyntheticBackport0.m(this.isSuccess) * 31;
            List<Pair<String, String>> list = this.movies;
            return iM + (list == null ? 0 : list.hashCode());
        }

        public String toString() {
            return "CrossMetaData(isSuccess=" + this.isSuccess + ", movies=" + this.movies + ')';
        }

        /* JADX INFO: compiled from: CrossTmdbProvider.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/CrossTmdbProvider$CrossMetaData$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/metaproviders/CrossTmdbProvider$CrossMetaData;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<CrossMetaData> serializer() {
                return CrossTmdbProvider$CrossMetaData$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ CrossMetaData(int i, boolean z, List list, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, CrossTmdbProvider$CrossMetaData$$serializer.INSTANCE.getDescriptor());
            }
            this.isSuccess = z;
            if ((i & 2) == 0) {
                this.movies = null;
            } else {
                this.movies = list;
            }
        }

        public CrossMetaData(@JsonProperty("isSuccess") boolean z, @JsonProperty("movies") List<Pair<String, String>> list) {
            this.isSuccess = z;
            this.movies = list;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$library(CrossMetaData self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            output.encodeBooleanElement(serialDesc, 0, self.isSuccess);
            if (!output.shouldEncodeElementDefault(serialDesc, 1) && self.movies == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 1, lazyArr[1].getValue(), self.movies);
        }

        public /* synthetic */ CrossMetaData(boolean z, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(z, (i & 2) != 0 ? null : list);
        }

        public final boolean isSuccess() {
            return this.isSuccess;
        }

        public final List<Pair<String, String>> getMovies() {
            return this.movies;
        }
    }

    /* JADX WARN: Code duplicated, block: B:30:0x009c  */
    /* JADX WARN: Code duplicated, block: B:37:0x00b2 A[Catch: Exception -> 0x004f, TRY_LEAVE, TryCatch #0 {Exception -> 0x004f, blocks: (B:21:0x006a, B:28:0x0096, B:31:0x009d, B:37:0x00b2, B:36:0x00ad, B:27:0x008c, B:20:0x0060, B:33:0x00a1, B:24:0x0071, B:17:0x0052), top: B:56:0x004d, inners: #2, #3, #4, #5 }] */
    /* JADX WARN: Code duplicated, block: B:61:0x00a1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // com.lagradost.cloudstream3.MainAPI
    public Object loadLinks(String str, boolean z, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Boolean> continuation) {
        C07251 c07251;
        Object objDecodeFromString;
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        if (continuation instanceof C07251) {
            c07251 = (C07251) continuation;
            if ((c07251.label & Integer.MIN_VALUE) != 0) {
                c07251.label -= Integer.MIN_VALUE;
            } else {
                c07251 = new C07251(continuation);
            }
        } else {
            c07251 = new C07251(continuation);
        }
        Object objAmap = c07251.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07251.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objAmap);
            AppUtils appUtils = AppUtils.INSTANCE;
            try {
                if (str == null) {
                    objDecodeFromString = null;
                } else {
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(CrossMetaData.INSTANCE.serializer());
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            try {
                                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, str);
                            } catch (SerializationException e) {
                                ArchComponentExtKt.logError(e);
                                objDecodeFromString = MainAPIKt.getMapper().readValue(str, new TypeReference<CrossMetaData>() { // from class: com.lagradost.cloudstream3.metaproviders.CrossTmdbProvider$loadLinks$$inlined$tryParseJson$1
                                });
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(str, new TypeReference<CrossMetaData>() { // from class: com.lagradost.cloudstream3.metaproviders.CrossTmdbProvider$loadLinks$$inlined$tryParseJson$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(str, new TypeReference<CrossMetaData>() { // from class: com.lagradost.cloudstream3.metaproviders.CrossTmdbProvider$loadLinks$$inlined$tryParseJson$1
                            });
                        }
                    } else {
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(CrossMetaData.class), null, 2, null));
                        } catch (Throwable th2) {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, str);
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(str, new TypeReference<CrossMetaData>() { // from class: com.lagradost.cloudstream3.metaproviders.CrossTmdbProvider$loadLinks$$inlined$tryParseJson$1
                            });
                        }
                    }
                }
            } catch (Exception unused2) {
            }
            CrossMetaData crossMetaData = (CrossMetaData) objDecodeFromString;
            if (crossMetaData != null) {
                if (!crossMetaData.isSuccess()) {
                    return Boxing.boxBoolean(false);
                }
                List<Pair<String, String>> movies = crossMetaData.getMovies();
                if (movies != null) {
                    CrossTmdbProvider$loadLinks$2$1 crossTmdbProvider$loadLinks$2$1 = new CrossTmdbProvider$loadLinks$2$1(z, function1, function2, null);
                    c07251.L$0 = SpillingKt.nullOutSpilledVariable(str);
                    c07251.L$1 = SpillingKt.nullOutSpilledVariable(function1);
                    c07251.L$2 = SpillingKt.nullOutSpilledVariable(function2);
                    c07251.L$3 = SpillingKt.nullOutSpilledVariable(crossMetaData);
                    c07251.Z$0 = z;
                    c07251.I$0 = 0;
                    c07251.label = 1;
                    objAmap = ParCollectionsKt.amap(movies, crossTmdbProvider$loadLinks$2$1, c07251);
                    if (objAmap == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Boxing.boxBoolean(true);
            }
            return Boxing.boxBoolean(false);
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        int i2 = c07251.I$0;
        boolean z2 = c07251.Z$0;
        ResultKt.throwOnFailure(objAmap);
        return Boxing.boxBoolean(true);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // com.lagradost.cloudstream3.metaproviders.TmdbProvider, com.lagradost.cloudstream3.MainAPI
    public Object search(String str, int i, Continuation<? super SearchResponseList> continuation) {
        C07261 c07261;
        List<SearchResponse> items;
        if (continuation instanceof C07261) {
            c07261 = (C07261) continuation;
            if ((c07261.label & Integer.MIN_VALUE) != 0) {
                c07261.label -= Integer.MIN_VALUE;
            } else {
                c07261 = new C07261(continuation);
            }
        } else {
            c07261 = new C07261(continuation);
        }
        Object objSearch = c07261.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c07261.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objSearch);
            c07261.L$0 = SpillingKt.nullOutSpilledVariable(str);
            c07261.I$0 = i;
            c07261.label = 1;
            objSearch = super.search(str, i, c07261);
            if (objSearch == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i3 = c07261.I$0;
            ResultKt.throwOnFailure(objSearch);
        }
        SearchResponseList searchResponseList = (SearchResponseList) objSearch;
        if (searchResponseList == null || (items = searchResponseList.getItems()) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : items) {
            if (obj instanceof MovieSearchResponse) {
                arrayList.add(obj);
            }
        }
        return MainAPIKt.toNewSearchResponseList$default(arrayList, null, 1, null);
    }

    /* JADX WARN: Code duplicated, block: B:40:0x00ee A[LOOP:0: B:38:0x00e8->B:40:0x00ee, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // com.lagradost.cloudstream3.metaproviders.TmdbProvider, com.lagradost.cloudstream3.MainAPI
    public Object load(String str, Continuation<? super LoadResponse> continuation) throws ErrorLoadingException {
        AnonymousClass1 anonymousClass1;
        ArrayList arrayList;
        LoadResponse loadResponse;
        LoadResponse loadResponse2;
        ArrayList arrayList2;
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
        Object objLoad = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objLoad);
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(str);
            anonymousClass1.label = 1;
            objLoad = super.load(str, anonymousClass1);
            if (objLoad != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i == 1) {
            str = (String) anonymousClass1.L$0;
            ResultKt.throwOnFailure(objLoad);
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = anonymousClass1.I$0;
            loadResponse2 = (LoadResponse) anonymousClass1.L$2;
            loadResponse = (LoadResponse) anonymousClass1.L$1;
            ResultKt.throwOnFailure(objLoad);
        }
        List listFilterNotNull = CollectionsKt.filterNotNull((Iterable) objLoad);
        MovieLoadResponse movieLoadResponse = (MovieLoadResponse) loadResponse2;
        AppUtils appUtils = AppUtils.INSTANCE;
        List<MovieLoadResponse> list = listFilterNotNull;
        arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (MovieLoadResponse movieLoadResponse2 : list) {
            arrayList2.add(TuplesKt.to(movieLoadResponse2.getApiName(), movieLoadResponse2.getDataUrl()));
        }
        movieLoadResponse.setDataUrl(appUtils.toJson(new CrossMetaData(true, arrayList2)));
        return loadResponse;
        LoadResponse loadResponse3 = (LoadResponse) objLoad;
        if (loadResponse3 == null) {
            return null;
        }
        List<SearchResponse> recommendations = loadResponse3.getRecommendations();
        if (recommendations != null) {
            ArrayList arrayList3 = new ArrayList();
            for (Object obj : recommendations) {
                if (obj instanceof MovieSearchResponse) {
                    arrayList3.add(obj);
                }
            }
            arrayList = arrayList3;
        } else {
            arrayList = null;
        }
        loadResponse3.setRecommendations(arrayList);
        String strFilterName = filterName(loadResponse3.getName());
        if (loadResponse3 instanceof MovieLoadResponse) {
            AtomicList<MainAPI> validApis = getValidApis();
            CrossTmdbProvider$load$base$1$data$1 crossTmdbProvider$load$base$1$data$1 = new CrossTmdbProvider$load$base$1$data$1(loadResponse3, this, strFilterName, null);
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(str);
            anonymousClass1.L$1 = loadResponse3;
            anonymousClass1.L$2 = loadResponse3;
            anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(strFilterName);
            anonymousClass1.I$0 = 0;
            anonymousClass1.label = 2;
            Object objAmap = ParCollectionsKt.amap(validApis, crossTmdbProvider$load$base$1$data$1, anonymousClass1);
            if (objAmap != coroutine_suspended) {
                loadResponse = loadResponse3;
                objLoad = objAmap;
                loadResponse2 = loadResponse;
                List listFilterNotNull2 = CollectionsKt.filterNotNull((Iterable) objLoad);
                MovieLoadResponse movieLoadResponse3 = (MovieLoadResponse) loadResponse2;
                AppUtils appUtils2 = AppUtils.INSTANCE;
                List<MovieLoadResponse> list2 = listFilterNotNull2;
                arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                while (r11.hasNext()) {
                    arrayList2.add(TuplesKt.to(movieLoadResponse2.getApiName(), movieLoadResponse2.getDataUrl()));
                }
                movieLoadResponse3.setDataUrl(appUtils2.toJson(new CrossMetaData(true, arrayList2)));
                return loadResponse;
            }
            return coroutine_suspended;
        }
        throw new ErrorLoadingException("Nothing besides movies are implemented for this provider");
    }
}
