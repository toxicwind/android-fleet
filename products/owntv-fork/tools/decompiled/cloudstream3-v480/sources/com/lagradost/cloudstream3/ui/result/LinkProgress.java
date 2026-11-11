package com.lagradost.cloudstream3.ui.result;

import kotlin.Metadata;

/* JADX INFO: compiled from: ResultViewModel2.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/LinkProgress;", "", "linksLoaded", "", "subsLoaded", "<init>", "(II)V", "getLinksLoaded", "()I", "getSubsLoaded", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class LinkProgress {
    private final int linksLoaded;
    private final int subsLoaded;

    public static /* synthetic */ LinkProgress copy$default(LinkProgress linkProgress, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = linkProgress.linksLoaded;
        }
        if ((i3 & 2) != 0) {
            i2 = linkProgress.subsLoaded;
        }
        return linkProgress.copy(i, i2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getLinksLoaded() {
        return this.linksLoaded;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getSubsLoaded() {
        return this.subsLoaded;
    }

    public final LinkProgress copy(int linksLoaded, int subsLoaded) {
        return new LinkProgress(linksLoaded, subsLoaded);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LinkProgress)) {
            return false;
        }
        LinkProgress linkProgress = (LinkProgress) other;
        return this.linksLoaded == linkProgress.linksLoaded && this.subsLoaded == linkProgress.subsLoaded;
    }

    public int hashCode() {
        return (this.linksLoaded * 31) + this.subsLoaded;
    }

    public String toString() {
        return "LinkProgress(linksLoaded=" + this.linksLoaded + ", subsLoaded=" + this.subsLoaded + ')';
    }

    public LinkProgress(int i, int i2) {
        this.linksLoaded = i;
        this.subsLoaded = i2;
    }

    public final int getLinksLoaded() {
        return this.linksLoaded;
    }

    public final int getSubsLoaded() {
        return this.subsLoaded;
    }
}
