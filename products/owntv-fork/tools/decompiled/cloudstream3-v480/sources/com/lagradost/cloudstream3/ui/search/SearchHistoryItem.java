package com.lagradost.cloudstream3.ui.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.TvType;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.EnumsKt;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* JADX INFO: compiled from: SearchHistoryAdaptor.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 02\u00020\u0001:\u0002/0B5\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0001\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\b\u0001\u0010\t\u001a\u00020\u0005¢\u0006\u0004\b\n\u0010\u000bBG\b\u0010\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\n\u0010\u0010J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J7\u0010!\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\u000e\b\u0003\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0003\u0010\t\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010%\u001a\u00020\rHÖ\u0081\u0004J\n\u0010&\u001a\u00020\u0005HÖ\u0081\u0004J%\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0001¢\u0006\u0002\b.R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0012\u001a\u0004\b\u0016\u0010\u0017R\"\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0012\u001a\u0004\b\u0019\u0010\u001aR\u001c\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u0012\u001a\u0004\b\u001c\u0010\u0017¨\u00061"}, d2 = {"Lcom/lagradost/cloudstream3/ui/search/SearchHistoryItem;", "", "searchedAt", "", "searchText", "", "type", "", "Lcom/lagradost/cloudstream3/TvType;", "key", "<init>", "(JLjava/lang/String;Ljava/util/List;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IJLjava/lang/String;Ljava/util/List;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getSearchedAt$annotations", "()V", "getSearchedAt", "()J", "getSearchText$annotations", "getSearchText", "()Ljava/lang/String;", "getType$annotations", "getType", "()Ljava/util/List;", "getKey$annotations", "getKey", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Serializable
public final /* data */ class SearchHistoryItem {

    @JsonProperty("key")
    private final String key;

    @JsonProperty("searchText")
    private final String searchText;

    @JsonProperty("searchedAt")
    private final long searchedAt;

    @JsonProperty("type")
    private final List<TvType> type;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.ui.search.SearchHistoryItem$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return SearchHistoryItem._childSerializers$_anonymous_();
        }
    }), null};

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
        return new ArrayListSerializer(EnumsKt.createSimpleEnumSerializer("com.lagradost.cloudstream3.TvType", TvType.values()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SearchHistoryItem copy$default(SearchHistoryItem searchHistoryItem, long j, String str, List list, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = searchHistoryItem.searchedAt;
        }
        long j2 = j;
        if ((i & 2) != 0) {
            str = searchHistoryItem.searchText;
        }
        String str3 = str;
        if ((i & 4) != 0) {
            list = searchHistoryItem.type;
        }
        List list2 = list;
        if ((i & 8) != 0) {
            str2 = searchHistoryItem.key;
        }
        return searchHistoryItem.copy(j2, str3, list2, str2);
    }

    @SerialName("key")
    public static /* synthetic */ void getKey$annotations() {
    }

    @SerialName("searchText")
    public static /* synthetic */ void getSearchText$annotations() {
    }

    @SerialName("searchedAt")
    public static /* synthetic */ void getSearchedAt$annotations() {
    }

    @SerialName("type")
    public static /* synthetic */ void getType$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getSearchedAt() {
        return this.searchedAt;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getSearchText() {
        return this.searchText;
    }

    public final List<TvType> component3() {
        return this.type;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getKey() {
        return this.key;
    }

    public final SearchHistoryItem copy(@JsonProperty("searchedAt") long searchedAt, @JsonProperty("searchText") String searchText, @JsonProperty("type") List<? extends TvType> type, @JsonProperty("key") String key) {
        Intrinsics.checkNotNullParameter(searchText, "searchText");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(key, "key");
        return new SearchHistoryItem(searchedAt, searchText, type, key);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SearchHistoryItem)) {
            return false;
        }
        SearchHistoryItem searchHistoryItem = (SearchHistoryItem) other;
        return this.searchedAt == searchHistoryItem.searchedAt && Intrinsics.areEqual(this.searchText, searchHistoryItem.searchText) && Intrinsics.areEqual(this.type, searchHistoryItem.type) && Intrinsics.areEqual(this.key, searchHistoryItem.key);
    }

    public int hashCode() {
        return (((((NextAiring$$ExternalSyntheticBackport0.m(this.searchedAt) * 31) + this.searchText.hashCode()) * 31) + this.type.hashCode()) * 31) + this.key.hashCode();
    }

    public String toString() {
        return "SearchHistoryItem(searchedAt=" + this.searchedAt + ", searchText=" + this.searchText + ", type=" + this.type + ", key=" + this.key + ')';
    }

    /* JADX INFO: compiled from: SearchHistoryAdaptor.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/ui/search/SearchHistoryItem$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/ui/search/SearchHistoryItem;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<SearchHistoryItem> serializer() {
            return SearchHistoryItem$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ SearchHistoryItem(int i, long j, String str, List list, String str2, SerializationConstructorMarker serializationConstructorMarker) {
        if (15 != (i & 15)) {
            PluginExceptionsKt.throwMissingFieldException(i, 15, SearchHistoryItem$$serializer.INSTANCE.getDescriptor());
        }
        this.searchedAt = j;
        this.searchText = str;
        this.type = list;
        this.key = str2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SearchHistoryItem(@JsonProperty("searchedAt") long j, @JsonProperty("searchText") String searchText, @JsonProperty("type") List<? extends TvType> type, @JsonProperty("key") String key) {
        Intrinsics.checkNotNullParameter(searchText, "searchText");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(key, "key");
        this.searchedAt = j;
        this.searchText = searchText;
        this.type = type;
        this.key = key;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$app(SearchHistoryItem self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
        output.encodeLongElement(serialDesc, 0, self.searchedAt);
        output.encodeStringElement(serialDesc, 1, self.searchText);
        output.encodeSerializableElement(serialDesc, 2, lazyArr[2].getValue(), self.type);
        output.encodeStringElement(serialDesc, 3, self.key);
    }

    public final long getSearchedAt() {
        return this.searchedAt;
    }

    public final String getSearchText() {
        return this.searchText;
    }

    public final List<TvType> getType() {
        return this.type;
    }

    public final String getKey() {
        return this.key;
    }
}
