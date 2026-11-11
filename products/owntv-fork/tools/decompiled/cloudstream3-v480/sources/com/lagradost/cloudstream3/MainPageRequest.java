package com.lagradost.cloudstream3;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MainAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lcom/lagradost/cloudstream3/MainPageRequest;", "", "name", "", "data", "horizontalImages", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Z)V", "getName", "()Ljava/lang/String;", "getData", "getHorizontalImages", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class MainPageRequest {
    private final String data;
    private final boolean horizontalImages;
    private final String name;

    public static /* synthetic */ MainPageRequest copy$default(MainPageRequest mainPageRequest, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = mainPageRequest.name;
        }
        if ((i & 2) != 0) {
            str2 = mainPageRequest.data;
        }
        if ((i & 4) != 0) {
            z = mainPageRequest.horizontalImages;
        }
        return mainPageRequest.copy(str, str2, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getData() {
        return this.data;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getHorizontalImages() {
        return this.horizontalImages;
    }

    public final MainPageRequest copy(String name, String data, boolean horizontalImages) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(data, "data");
        return new MainPageRequest(name, data, horizontalImages);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MainPageRequest)) {
            return false;
        }
        MainPageRequest mainPageRequest = (MainPageRequest) other;
        return Intrinsics.areEqual(this.name, mainPageRequest.name) && Intrinsics.areEqual(this.data, mainPageRequest.data) && this.horizontalImages == mainPageRequest.horizontalImages;
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + this.data.hashCode()) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.horizontalImages);
    }

    public String toString() {
        return "MainPageRequest(name=" + this.name + ", data=" + this.data + ", horizontalImages=" + this.horizontalImages + ')';
    }

    public MainPageRequest(String name, String data, boolean z) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(data, "data");
        this.name = name;
        this.data = data;
        this.horizontalImages = z;
    }

    public final String getName() {
        return this.name;
    }

    public final String getData() {
        return this.data;
    }

    public final boolean getHorizontalImages() {
        return this.horizontalImages;
    }
}
