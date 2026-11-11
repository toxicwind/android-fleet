package com.lagradost.cloudstream3.ui.download.queue;

import com.lagradost.cloudstream3.utils.downloader.DownloadObjects;
import kotlin.Metadata;

/* JADX INFO: compiled from: DownloadQueueAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/lagradost/cloudstream3/ui/download/queue/DownloadAdapterItem;", "", "item", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadQueueWrapper;", "<init>", "(Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadQueueWrapper;)V", "getItem", "()Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadQueueWrapper;", "isSeparator", "", "()Z", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DownloadAdapterItem {
    private final boolean isSeparator;
    private final DownloadObjects.DownloadQueueWrapper item;

    public DownloadAdapterItem(DownloadObjects.DownloadQueueWrapper downloadQueueWrapper) {
        this.item = downloadQueueWrapper;
        this.isSeparator = downloadQueueWrapper == null;
    }

    public final DownloadObjects.DownloadQueueWrapper getItem() {
        return this.item;
    }

    /* JADX INFO: renamed from: isSeparator, reason: from getter */
    public final boolean getIsSeparator() {
        return this.isSeparator;
    }
}
