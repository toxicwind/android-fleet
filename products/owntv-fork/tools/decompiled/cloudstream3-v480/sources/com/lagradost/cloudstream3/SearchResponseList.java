package com.lagradost.cloudstream3;

import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MainAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B!\b\u0007\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/lagradost/cloudstream3/SearchResponseList;", "", "items", "", "Lcom/lagradost/cloudstream3/SearchResponse;", "hasNext", "", "<init>", "(Ljava/util/List;Z)V", "getItems", "()Ljava/util/List;", "getHasNext", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SearchResponseList {
    private final boolean hasNext;
    private final List<SearchResponse> items;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SearchResponseList copy$default(SearchResponseList searchResponseList, List list, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            list = searchResponseList.items;
        }
        if ((i & 2) != 0) {
            z = searchResponseList.hasNext;
        }
        return searchResponseList.copy(list, z);
    }

    public final List<SearchResponse> component1() {
        return this.items;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getHasNext() {
        return this.hasNext;
    }

    public final SearchResponseList copy(List<? extends SearchResponse> items, boolean hasNext) {
        Intrinsics.checkNotNullParameter(items, "items");
        return new SearchResponseList(items, hasNext);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SearchResponseList)) {
            return false;
        }
        SearchResponseList searchResponseList = (SearchResponseList) other;
        return Intrinsics.areEqual(this.items, searchResponseList.items) && this.hasNext == searchResponseList.hasNext;
    }

    public int hashCode() {
        return (this.items.hashCode() * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.hasNext);
    }

    public String toString() {
        return "SearchResponseList(items=" + this.items + ", hasNext=" + this.hasNext + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use newSearchResponseList method")
    public SearchResponseList(List<? extends SearchResponse> items, boolean z) {
        Intrinsics.checkNotNullParameter(items, "items");
        this.items = items;
        this.hasNext = z;
    }

    public /* synthetic */ SearchResponseList(List list, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i & 2) != 0 ? false : z);
    }

    public final List<SearchResponse> getItems() {
        return this.items;
    }

    public final boolean getHasNext() {
        return this.hasNext;
    }
}
