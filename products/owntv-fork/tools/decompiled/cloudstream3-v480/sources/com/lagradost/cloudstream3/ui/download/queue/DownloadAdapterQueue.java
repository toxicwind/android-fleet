package com.lagradost.cloudstream3.ui.download.queue;

import com.lagradost.cloudstream3.utils.downloader.DownloadObjects;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadQueueViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J)\u0010\r\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0014\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u0015"}, d2 = {"Lcom/lagradost/cloudstream3/ui/download/queue/DownloadAdapterQueue;", "", "currentDownloads", "", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadQueueWrapper;", "queue", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "getCurrentDownloads", "()Ljava/util/List;", "getQueue", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class DownloadAdapterQueue {
    private final List<DownloadObjects.DownloadQueueWrapper> currentDownloads;
    private final List<DownloadObjects.DownloadQueueWrapper> queue;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DownloadAdapterQueue copy$default(DownloadAdapterQueue downloadAdapterQueue, List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = downloadAdapterQueue.currentDownloads;
        }
        if ((i & 2) != 0) {
            list2 = downloadAdapterQueue.queue;
        }
        return downloadAdapterQueue.copy(list, list2);
    }

    public final List<DownloadObjects.DownloadQueueWrapper> component1() {
        return this.currentDownloads;
    }

    public final List<DownloadObjects.DownloadQueueWrapper> component2() {
        return this.queue;
    }

    public final DownloadAdapterQueue copy(List<DownloadObjects.DownloadQueueWrapper> currentDownloads, List<DownloadObjects.DownloadQueueWrapper> queue) {
        Intrinsics.checkNotNullParameter(currentDownloads, "currentDownloads");
        Intrinsics.checkNotNullParameter(queue, "queue");
        return new DownloadAdapterQueue(currentDownloads, queue);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DownloadAdapterQueue)) {
            return false;
        }
        DownloadAdapterQueue downloadAdapterQueue = (DownloadAdapterQueue) other;
        return Intrinsics.areEqual(this.currentDownloads, downloadAdapterQueue.currentDownloads) && Intrinsics.areEqual(this.queue, downloadAdapterQueue.queue);
    }

    public int hashCode() {
        return (this.currentDownloads.hashCode() * 31) + this.queue.hashCode();
    }

    public String toString() {
        return "DownloadAdapterQueue(currentDownloads=" + this.currentDownloads + ", queue=" + this.queue + ')';
    }

    public DownloadAdapterQueue(List<DownloadObjects.DownloadQueueWrapper> currentDownloads, List<DownloadObjects.DownloadQueueWrapper> queue) {
        Intrinsics.checkNotNullParameter(currentDownloads, "currentDownloads");
        Intrinsics.checkNotNullParameter(queue, "queue");
        this.currentDownloads = currentDownloads;
        this.queue = queue;
    }

    public final List<DownloadObjects.DownloadQueueWrapper> getCurrentDownloads() {
        return this.currentDownloads;
    }

    public final List<DownloadObjects.DownloadQueueWrapper> getQueue() {
        return this.queue;
    }
}
