package com.lagradost.cloudstream3.ui.download;

import com.lagradost.cloudstream3.utils.downloader.DownloadObjects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/lagradost/cloudstream3/ui/download/DownloadHeaderClickEvent;", "", "action", "", "data", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadHeaderCached;", "<init>", "(ILcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadHeaderCached;)V", "getAction", "()I", "getData", "()Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadHeaderCached;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class DownloadHeaderClickEvent {
    private final int action;
    private final DownloadObjects.DownloadHeaderCached data;

    public static /* synthetic */ DownloadHeaderClickEvent copy$default(DownloadHeaderClickEvent downloadHeaderClickEvent, int i, DownloadObjects.DownloadHeaderCached downloadHeaderCached, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = downloadHeaderClickEvent.action;
        }
        if ((i2 & 2) != 0) {
            downloadHeaderCached = downloadHeaderClickEvent.data;
        }
        return downloadHeaderClickEvent.copy(i, downloadHeaderCached);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getAction() {
        return this.action;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final DownloadObjects.DownloadHeaderCached getData() {
        return this.data;
    }

    public final DownloadHeaderClickEvent copy(int action, DownloadObjects.DownloadHeaderCached data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return new DownloadHeaderClickEvent(action, data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DownloadHeaderClickEvent)) {
            return false;
        }
        DownloadHeaderClickEvent downloadHeaderClickEvent = (DownloadHeaderClickEvent) other;
        return this.action == downloadHeaderClickEvent.action && Intrinsics.areEqual(this.data, downloadHeaderClickEvent.data);
    }

    public int hashCode() {
        return (this.action * 31) + this.data.hashCode();
    }

    public String toString() {
        return "DownloadHeaderClickEvent(action=" + this.action + ", data=" + this.data + ')';
    }

    public DownloadHeaderClickEvent(int i, DownloadObjects.DownloadHeaderCached data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.action = i;
        this.data = data;
    }

    public final int getAction() {
        return this.action;
    }

    public final DownloadObjects.DownloadHeaderCached getData() {
        return this.data;
    }
}
