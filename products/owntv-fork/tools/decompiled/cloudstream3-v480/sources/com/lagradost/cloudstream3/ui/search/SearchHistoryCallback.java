package com.lagradost.cloudstream3.ui.search;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchHistoryAdaptor.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/lagradost/cloudstream3/ui/search/SearchHistoryCallback;", "", "item", "Lcom/lagradost/cloudstream3/ui/search/SearchHistoryItem;", "clickAction", "", "<init>", "(Lcom/lagradost/cloudstream3/ui/search/SearchHistoryItem;I)V", "getItem", "()Lcom/lagradost/cloudstream3/ui/search/SearchHistoryItem;", "getClickAction", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SearchHistoryCallback {
    private final int clickAction;
    private final SearchHistoryItem item;

    public static /* synthetic */ SearchHistoryCallback copy$default(SearchHistoryCallback searchHistoryCallback, SearchHistoryItem searchHistoryItem, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            searchHistoryItem = searchHistoryCallback.item;
        }
        if ((i2 & 2) != 0) {
            i = searchHistoryCallback.clickAction;
        }
        return searchHistoryCallback.copy(searchHistoryItem, i);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final SearchHistoryItem getItem() {
        return this.item;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getClickAction() {
        return this.clickAction;
    }

    public final SearchHistoryCallback copy(SearchHistoryItem item, int clickAction) {
        return new SearchHistoryCallback(item, clickAction);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SearchHistoryCallback)) {
            return false;
        }
        SearchHistoryCallback searchHistoryCallback = (SearchHistoryCallback) other;
        return Intrinsics.areEqual(this.item, searchHistoryCallback.item) && this.clickAction == searchHistoryCallback.clickAction;
    }

    public int hashCode() {
        SearchHistoryItem searchHistoryItem = this.item;
        return ((searchHistoryItem == null ? 0 : searchHistoryItem.hashCode()) * 31) + this.clickAction;
    }

    public String toString() {
        return "SearchHistoryCallback(item=" + this.item + ", clickAction=" + this.clickAction + ')';
    }

    public SearchHistoryCallback(SearchHistoryItem searchHistoryItem, int i) {
        this.item = searchHistoryItem;
        this.clickAction = i;
    }

    public final SearchHistoryItem getItem() {
        return this.item;
    }

    public final int getClickAction() {
        return this.clickAction;
    }
}
