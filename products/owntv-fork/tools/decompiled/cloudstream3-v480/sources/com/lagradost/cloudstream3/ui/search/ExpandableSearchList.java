package com.lagradost.cloudstream3.ui.search;

import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.SearchResponse;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003J-\u0010\u001a\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0014\u0010\u001b\u001a\u00020\b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\u0006HÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u001fHÖ\u0081\u0004R \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/ui/search/ExpandableSearchList;", "", "list", "", "Lcom/lagradost/cloudstream3/SearchResponse;", "currentPage", "", "hasNext", "", "<init>", "(Ljava/util/List;IZ)V", "getList", "()Ljava/util/List;", "setList", "(Ljava/util/List;)V", "getCurrentPage", "()I", "setCurrentPage", "(I)V", "getHasNext", "()Z", "setHasNext", "(Z)V", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ExpandableSearchList {
    private int currentPage;
    private boolean hasNext;
    private List<? extends SearchResponse> list;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ExpandableSearchList copy$default(ExpandableSearchList expandableSearchList, List list, int i, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = expandableSearchList.list;
        }
        if ((i2 & 2) != 0) {
            i = expandableSearchList.currentPage;
        }
        if ((i2 & 4) != 0) {
            z = expandableSearchList.hasNext;
        }
        return expandableSearchList.copy(list, i, z);
    }

    public final List<SearchResponse> component1() {
        return this.list;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getCurrentPage() {
        return this.currentPage;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getHasNext() {
        return this.hasNext;
    }

    public final ExpandableSearchList copy(List<? extends SearchResponse> list, int currentPage, boolean hasNext) {
        Intrinsics.checkNotNullParameter(list, "list");
        return new ExpandableSearchList(list, currentPage, hasNext);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ExpandableSearchList)) {
            return false;
        }
        ExpandableSearchList expandableSearchList = (ExpandableSearchList) other;
        return Intrinsics.areEqual(this.list, expandableSearchList.list) && this.currentPage == expandableSearchList.currentPage && this.hasNext == expandableSearchList.hasNext;
    }

    public int hashCode() {
        return (((this.list.hashCode() * 31) + this.currentPage) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.hasNext);
    }

    public String toString() {
        return "ExpandableSearchList(list=" + this.list + ", currentPage=" + this.currentPage + ", hasNext=" + this.hasNext + ')';
    }

    public ExpandableSearchList(List<? extends SearchResponse> list, int i, boolean z) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.list = list;
        this.currentPage = i;
        this.hasNext = z;
    }

    public final int getCurrentPage() {
        return this.currentPage;
    }

    public final boolean getHasNext() {
        return this.hasNext;
    }

    public final List<SearchResponse> getList() {
        return this.list;
    }

    public final void setCurrentPage(int i) {
        this.currentPage = i;
    }

    public final void setHasNext(boolean z) {
        this.hasNext = z;
    }

    public final void setList(List<? extends SearchResponse> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.list = list;
    }
}
