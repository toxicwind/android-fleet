package com.lagradost.cloudstream3;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MainAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J-\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0014\u0010\u0016\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0011¨\u0006\u001b"}, d2 = {"Lcom/lagradost/cloudstream3/HomePageList;", "", "name", "", "list", "", "Lcom/lagradost/cloudstream3/SearchResponse;", "isHorizontalImages", "", "<init>", "(Ljava/lang/String;Ljava/util/List;Z)V", "getName", "()Ljava/lang/String;", "getList", "()Ljava/util/List;", "setList", "(Ljava/util/List;)V", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class HomePageList {
    private final boolean isHorizontalImages;
    private List<? extends SearchResponse> list;
    private final String name;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ HomePageList copy$default(HomePageList homePageList, String str, List list, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = homePageList.name;
        }
        if ((i & 2) != 0) {
            list = homePageList.list;
        }
        if ((i & 4) != 0) {
            z = homePageList.isHorizontalImages;
        }
        return homePageList.copy(str, list, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final List<SearchResponse> component2() {
        return this.list;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsHorizontalImages() {
        return this.isHorizontalImages;
    }

    public final HomePageList copy(String name, List<? extends SearchResponse> list, boolean isHorizontalImages) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(list, "list");
        return new HomePageList(name, list, isHorizontalImages);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HomePageList)) {
            return false;
        }
        HomePageList homePageList = (HomePageList) other;
        return Intrinsics.areEqual(this.name, homePageList.name) && Intrinsics.areEqual(this.list, homePageList.list) && this.isHorizontalImages == homePageList.isHorizontalImages;
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + this.list.hashCode()) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.isHorizontalImages);
    }

    public String toString() {
        return "HomePageList(name=" + this.name + ", list=" + this.list + ", isHorizontalImages=" + this.isHorizontalImages + ')';
    }

    public HomePageList(String name, List<? extends SearchResponse> list, boolean z) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(list, "list");
        this.name = name;
        this.list = list;
        this.isHorizontalImages = z;
    }

    public /* synthetic */ HomePageList(String str, List list, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, list, (i & 4) != 0 ? false : z);
    }

    public final String getName() {
        return this.name;
    }

    public final List<SearchResponse> getList() {
        return this.list;
    }

    public final void setList(List<? extends SearchResponse> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.list = list;
    }

    public final boolean isHorizontalImages() {
        return this.isHorizontalImages;
    }
}
