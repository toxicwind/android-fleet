package com.lagradost.cloudstream3.ui.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.actions.SearchIntents;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import com.lagradost.nicehttp.ResponseParser;
import com.uwetrottmann.tmdb2.Tmdb;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
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

/* JADX INFO: compiled from: SearchSuggestionApi.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\u000f\u0010B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b2\u0006\u0010\t\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\nJ\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\b2\u0006\u0010\f\u001a\u00020\rH\u0082@¢\u0006\u0002\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/lagradost/cloudstream3/ui/search/SearchSuggestionApi;", "", "<init>", "()V", "TMDB_API_URL", "", "TMDB_API_KEY", "getSuggestions", "", SearchIntents.EXTRA_QUERY, "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "parseSuggestions", "response", "Lcom/lagradost/nicehttp/NiceResponse;", "(Lcom/lagradost/nicehttp/NiceResponse;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "TmdbSearchResult", "TmdbSearchItem", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SearchSuggestionApi {
    public static final SearchSuggestionApi INSTANCE = new SearchSuggestionApi();
    private static final String TMDB_API_KEY = "e6333b32409e02a4a6eba6fb7ff866bb";
    private static final String TMDB_API_URL = "https://api.themoviedb.org/3/search/multi";

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.search.SearchSuggestionApi$getSuggestions$1, reason: invalid class name */
    /* JADX INFO: compiled from: SearchSuggestionApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.search.SearchSuggestionApi", f = "SearchSuggestionApi.kt", i = {0, 1, 1}, l = {43, 53}, m = "getSuggestions", n = {SearchIntents.EXTRA_QUERY, SearchIntents.EXTRA_QUERY, "response"}, nl = {53, 54}, s = {"L$0", "L$0", "L$1"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SearchSuggestionApi.this.getSuggestions(null, this);
        }
    }

    private SearchSuggestionApi() {
    }

    /* JADX INFO: compiled from: SearchSuggestionApi.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 !2\u00020\u0001:\u0002 !B\u0019\u0012\u0010\b\u0001\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006B+\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0005\u0010\u000bJ\u0011\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u001b\u0010\u0011\u001a\u00020\u00002\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J%\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0001¢\u0006\u0002\b\u001fR$\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\""}, d2 = {"Lcom/lagradost/cloudstream3/ui/search/SearchSuggestionApi$TmdbSearchResult;", "", "results", "", "Lcom/lagradost/cloudstream3/ui/search/SearchSuggestionApi$TmdbSearchItem;", "<init>", "(Ljava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getResults$annotations", "()V", "getResults", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class TmdbSearchResult {

        @JsonProperty("results")
        private final List<TmdbSearchItem> results;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.ui.search.SearchSuggestionApi$TmdbSearchResult$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SearchSuggestionApi.TmdbSearchResult._childSerializers$_anonymous_();
            }
        })};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(SearchSuggestionApi$TmdbSearchItem$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ TmdbSearchResult copy$default(TmdbSearchResult tmdbSearchResult, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = tmdbSearchResult.results;
            }
            return tmdbSearchResult.copy(list);
        }

        @SerialName("results")
        public static /* synthetic */ void getResults$annotations() {
        }

        public final List<TmdbSearchItem> component1() {
            return this.results;
        }

        public final TmdbSearchResult copy(@JsonProperty("results") List<TmdbSearchItem> results) {
            return new TmdbSearchResult(results);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof TmdbSearchResult) && Intrinsics.areEqual(this.results, ((TmdbSearchResult) other).results);
        }

        public int hashCode() {
            List<TmdbSearchItem> list = this.results;
            if (list == null) {
                return 0;
            }
            return list.hashCode();
        }

        public String toString() {
            return "TmdbSearchResult(results=" + this.results + ')';
        }

        /* JADX INFO: compiled from: SearchSuggestionApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/ui/search/SearchSuggestionApi$TmdbSearchResult$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/ui/search/SearchSuggestionApi$TmdbSearchResult;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<TmdbSearchResult> serializer() {
                return SearchSuggestionApi$TmdbSearchResult$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ TmdbSearchResult(int i, List list, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, SearchSuggestionApi$TmdbSearchResult$$serializer.INSTANCE.getDescriptor());
            }
            this.results = list;
        }

        public TmdbSearchResult(@JsonProperty("results") List<TmdbSearchItem> list) {
            this.results = list;
        }

        public final List<TmdbSearchItem> getResults() {
            return this.results;
        }
    }

    /* JADX INFO: compiled from: SearchSuggestionApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 /2\u00020\u0001:\u0002./BC\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\b\u0010\tBM\b\u0010\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\b\u0010\u000eJ\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003JE\u0010 \u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010$\u001a\u00020\u000bHÖ\u0081\u0004J\n\u0010%\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u00002\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0001¢\u0006\u0002\b-R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0014\u0010\u0012R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0016\u0010\u0012R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0010\u001a\u0004\b\u0018\u0010\u0012R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u0010\u001a\u0004\b\u001a\u0010\u0012¨\u00060"}, d2 = {"Lcom/lagradost/cloudstream3/ui/search/SearchSuggestionApi$TmdbSearchItem;", "", "mediaType", "", "title", "name", "originalTitle", "originalName", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getMediaType$annotations", "()V", "getMediaType", "()Ljava/lang/String;", "getTitle$annotations", "getTitle", "getName$annotations", "getName", "getOriginalTitle$annotations", "getOriginalTitle", "getOriginalName$annotations", "getOriginalName", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class TmdbSearchItem {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("media_type")
        private final String mediaType;

        @JsonProperty("name")
        private final String name;

        @JsonProperty("original_name")
        private final String originalName;

        @JsonProperty("original_title")
        private final String originalTitle;

        @JsonProperty("title")
        private final String title;

        public static /* synthetic */ TmdbSearchItem copy$default(TmdbSearchItem tmdbSearchItem, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
            if ((i & 1) != 0) {
                str = tmdbSearchItem.mediaType;
            }
            if ((i & 2) != 0) {
                str2 = tmdbSearchItem.title;
            }
            if ((i & 4) != 0) {
                str3 = tmdbSearchItem.name;
            }
            if ((i & 8) != 0) {
                str4 = tmdbSearchItem.originalTitle;
            }
            if ((i & 16) != 0) {
                str5 = tmdbSearchItem.originalName;
            }
            String str6 = str5;
            String str7 = str3;
            return tmdbSearchItem.copy(str, str2, str7, str4, str6);
        }

        @SerialName("media_type")
        public static /* synthetic */ void getMediaType$annotations() {
        }

        @SerialName("name")
        public static /* synthetic */ void getName$annotations() {
        }

        @SerialName("original_name")
        public static /* synthetic */ void getOriginalName$annotations() {
        }

        @SerialName("original_title")
        public static /* synthetic */ void getOriginalTitle$annotations() {
        }

        @SerialName("title")
        public static /* synthetic */ void getTitle$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getMediaType() {
            return this.mediaType;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getOriginalTitle() {
            return this.originalTitle;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getOriginalName() {
            return this.originalName;
        }

        public final TmdbSearchItem copy(@JsonProperty("media_type") String mediaType, @JsonProperty("title") String title, @JsonProperty("name") String name, @JsonProperty("original_title") String originalTitle, @JsonProperty("original_name") String originalName) {
            return new TmdbSearchItem(mediaType, title, name, originalTitle, originalName);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TmdbSearchItem)) {
                return false;
            }
            TmdbSearchItem tmdbSearchItem = (TmdbSearchItem) other;
            return Intrinsics.areEqual(this.mediaType, tmdbSearchItem.mediaType) && Intrinsics.areEqual(this.title, tmdbSearchItem.title) && Intrinsics.areEqual(this.name, tmdbSearchItem.name) && Intrinsics.areEqual(this.originalTitle, tmdbSearchItem.originalTitle) && Intrinsics.areEqual(this.originalName, tmdbSearchItem.originalName);
        }

        public int hashCode() {
            String str = this.mediaType;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.title;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.name;
            int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.originalTitle;
            int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.originalName;
            return iHashCode4 + (str5 != null ? str5.hashCode() : 0);
        }

        public String toString() {
            return "TmdbSearchItem(mediaType=" + this.mediaType + ", title=" + this.title + ", name=" + this.name + ", originalTitle=" + this.originalTitle + ", originalName=" + this.originalName + ')';
        }

        /* JADX INFO: compiled from: SearchSuggestionApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/ui/search/SearchSuggestionApi$TmdbSearchItem$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/ui/search/SearchSuggestionApi$TmdbSearchItem;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<TmdbSearchItem> serializer() {
                return SearchSuggestionApi$TmdbSearchItem$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ TmdbSearchItem(int i, String str, String str2, String str3, String str4, String str5, SerializationConstructorMarker serializationConstructorMarker) {
            if (31 != (i & 31)) {
                PluginExceptionsKt.throwMissingFieldException(i, 31, SearchSuggestionApi$TmdbSearchItem$$serializer.INSTANCE.getDescriptor());
            }
            this.mediaType = str;
            this.title = str2;
            this.name = str3;
            this.originalTitle = str4;
            this.originalName = str5;
        }

        public TmdbSearchItem(@JsonProperty("media_type") String str, @JsonProperty("title") String str2, @JsonProperty("name") String str3, @JsonProperty("original_title") String str4, @JsonProperty("original_name") String str5) {
            this.mediaType = str;
            this.title = str2;
            this.name = str3;
            this.originalTitle = str4;
            this.originalName = str5;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(TmdbSearchItem self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.mediaType);
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.title);
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.name);
            output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.originalTitle);
            output.encodeNullableSerializableElement(serialDesc, 4, StringSerializer.INSTANCE, self.originalName);
        }

        public final String getMediaType() {
            return this.mediaType;
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getName() {
            return this.name;
        }

        public final String getOriginalTitle() {
            return this.originalTitle;
        }

        public final String getOriginalName() {
            return this.originalName;
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    public final Object getSuggestions(String str, Continuation<? super List<String>> continuation) {
        AnonymousClass1 anonymousClass1;
        Object obj;
        int i;
        Object obj2;
        AnonymousClass1 anonymousClass2;
        String str2 = str;
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
        Object obj3 = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass1.label;
        try {
            if (i2 != 0) {
                if (i2 == 1) {
                    str2 = (String) anonymousClass1.L$0;
                    ResultKt.throwOnFailure(obj3);
                    obj2 = obj3;
                    obj = coroutine_suspended;
                    anonymousClass2 = anonymousClass1;
                    i = 2;
                } else {
                    if (i2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj3);
                }
                return (List) obj3;
            }
            ResultKt.throwOnFailure(obj3);
            if (StringsKt.isBlank(str2) || str2.length() < 2) {
                return CollectionsKt.emptyList();
            }
            obj = coroutine_suspended;
            Requests app2 = MainActivityKt.getApp();
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to(Tmdb.PARAM_API_KEY, TMDB_API_KEY), TuplesKt.to(SearchIntents.EXTRA_QUERY, str2), TuplesKt.to("language", "en-US"));
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(str2);
            anonymousClass1.label = 1;
            AnonymousClass1 anonymousClass3 = anonymousClass1;
            i = 2;
            obj2 = Requests.get$default(app2, TMDB_API_URL, null, null, mapMapOf, null, false, 1440, null, 0L, null, false, null, anonymousClass3, 4022, null);
            anonymousClass2 = anonymousClass3;
            if (obj2 == obj) {
            }
            return obj;
            NiceResponse niceResponse = (NiceResponse) obj2;
            anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(str2);
            anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(niceResponse);
            anonymousClass2.label = i;
            Object suggestions = parseSuggestions(niceResponse, anonymousClass2);
            if (suggestions != obj) {
                obj3 = suggestions;
                return (List) obj3;
            }
            return obj;
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
            return CollectionsKt.emptyList();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object parseSuggestions(NiceResponse niceResponse, Continuation<? super List<String>> continuation) {
        List listTake;
        try {
            ResponseParser parser = niceResponse.getParser();
            Intrinsics.checkNotNull(parser);
            List<TmdbSearchItem> results = ((TmdbSearchResult) parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(TmdbSearchResult.class))).getResults();
            if (results != null) {
                ArrayList<TmdbSearchItem> arrayList = new ArrayList();
                for (Object obj : results) {
                    TmdbSearchItem tmdbSearchItem = (TmdbSearchItem) obj;
                    if (Intrinsics.areEqual(tmdbSearchItem.getMediaType(), "movie") || Intrinsics.areEqual(tmdbSearchItem.getMediaType(), "tv")) {
                        arrayList.add(obj);
                    }
                }
                ArrayList arrayList2 = new ArrayList();
                for (TmdbSearchItem tmdbSearchItem2 : arrayList) {
                    String title = tmdbSearchItem2.getTitle();
                    if (title == null) {
                        title = tmdbSearchItem2.getName();
                    }
                    if (title != null) {
                        arrayList2.add(title);
                    }
                }
                List listDistinct = CollectionsKt.distinct(arrayList2);
                if (listDistinct != null && (listTake = CollectionsKt.take(listDistinct, 10)) != null) {
                    return listTake;
                }
            }
            return CollectionsKt.emptyList();
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
            return CollectionsKt.emptyList();
        }
    }
}
