package com.lagradost.cloudstream3.ui.search;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchSuggestionAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/lagradost/cloudstream3/ui/search/SearchSuggestionCallback;", "", "suggestion", "", "clickAction", "", "<init>", "(Ljava/lang/String;I)V", "getSuggestion", "()Ljava/lang/String;", "getClickAction", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SearchSuggestionCallback {
    private final int clickAction;
    private final String suggestion;

    public static /* synthetic */ SearchSuggestionCallback copy$default(SearchSuggestionCallback searchSuggestionCallback, String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = searchSuggestionCallback.suggestion;
        }
        if ((i2 & 2) != 0) {
            i = searchSuggestionCallback.clickAction;
        }
        return searchSuggestionCallback.copy(str, i);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getSuggestion() {
        return this.suggestion;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getClickAction() {
        return this.clickAction;
    }

    public final SearchSuggestionCallback copy(String suggestion, int clickAction) {
        Intrinsics.checkNotNullParameter(suggestion, "suggestion");
        return new SearchSuggestionCallback(suggestion, clickAction);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SearchSuggestionCallback)) {
            return false;
        }
        SearchSuggestionCallback searchSuggestionCallback = (SearchSuggestionCallback) other;
        return Intrinsics.areEqual(this.suggestion, searchSuggestionCallback.suggestion) && this.clickAction == searchSuggestionCallback.clickAction;
    }

    public int hashCode() {
        return (this.suggestion.hashCode() * 31) + this.clickAction;
    }

    public String toString() {
        return "SearchSuggestionCallback(suggestion=" + this.suggestion + ", clickAction=" + this.clickAction + ')';
    }

    public SearchSuggestionCallback(String suggestion, int i) {
        Intrinsics.checkNotNullParameter(suggestion, "suggestion");
        this.suggestion = suggestion;
        this.clickAction = i;
    }

    public final String getSuggestion() {
        return this.suggestion;
    }

    public final int getClickAction() {
        return this.clickAction;
    }
}
