package com.lagradost.cloudstream3.ui.download.button;

import androidx.core.app.NotificationCompat;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: BaseFetchButton.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0018\u00010\bj\u0004\u0018\u0001`\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\u0011\u0010\u001f\u001a\n\u0018\u00010\bj\u0004\u0018\u0001`\tHÆ\u0003J9\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u0010\b\u0002\u0010\u0007\u001a\n\u0018\u00010\bj\u0004\u0018\u0001`\tHÆ\u0001J\u0014\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010$\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010%\u001a\u00020&HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0013R\"\u0010\u0007\u001a\n\u0018\u00010\bj\u0004\u0018\u0001`\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0011¨\u0006'"}, d2 = {"Lcom/lagradost/cloudstream3/ui/download/button/DownloadMetadata;", "", "id", "", "downloadedLength", "", "totalLength", NotificationCompat.CATEGORY_STATUS, "Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$DownloadType;", "Lcom/lagradost/cloudstream3/ui/download/button/DownloadStatusTell;", "<init>", "(IJJLcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$DownloadType;)V", "getId", "()I", "setId", "(I)V", "getDownloadedLength", "()J", "setDownloadedLength", "(J)V", "getTotalLength", "setTotalLength", "getStatus", "()Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$DownloadType;", "setStatus", "(Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$DownloadType;)V", "progressPercentage", "getProgressPercentage", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class DownloadMetadata {
    private long downloadedLength;
    private int id;
    private VideoDownloadManager.DownloadType status;
    private long totalLength;

    public static /* synthetic */ DownloadMetadata copy$default(DownloadMetadata downloadMetadata, int i, long j, long j2, VideoDownloadManager.DownloadType downloadType, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = downloadMetadata.id;
        }
        if ((i2 & 2) != 0) {
            j = downloadMetadata.downloadedLength;
        }
        if ((i2 & 4) != 0) {
            j2 = downloadMetadata.totalLength;
        }
        if ((i2 & 8) != 0) {
            downloadType = downloadMetadata.status;
        }
        VideoDownloadManager.DownloadType downloadType2 = downloadType;
        return downloadMetadata.copy(i, j, j2, downloadType2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getDownloadedLength() {
        return this.downloadedLength;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getTotalLength() {
        return this.totalLength;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final VideoDownloadManager.DownloadType getStatus() {
        return this.status;
    }

    public final DownloadMetadata copy(int id, long downloadedLength, long totalLength, VideoDownloadManager.DownloadType status) {
        return new DownloadMetadata(id, downloadedLength, totalLength, status);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DownloadMetadata)) {
            return false;
        }
        DownloadMetadata downloadMetadata = (DownloadMetadata) other;
        return this.id == downloadMetadata.id && this.downloadedLength == downloadMetadata.downloadedLength && this.totalLength == downloadMetadata.totalLength && this.status == downloadMetadata.status;
    }

    public int hashCode() {
        int iM = ((((this.id * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.downloadedLength)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.totalLength)) * 31;
        VideoDownloadManager.DownloadType downloadType = this.status;
        return iM + (downloadType == null ? 0 : downloadType.hashCode());
    }

    public String toString() {
        return "DownloadMetadata(id=" + this.id + ", downloadedLength=" + this.downloadedLength + ", totalLength=" + this.totalLength + ", status=" + this.status + ')';
    }

    public DownloadMetadata(int i, long j, long j2, VideoDownloadManager.DownloadType downloadType) {
        this.id = i;
        this.downloadedLength = j;
        this.totalLength = j2;
        this.status = downloadType;
    }

    public /* synthetic */ DownloadMetadata(int i, long j, long j2, VideoDownloadManager.DownloadType downloadType, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, j, j2, (i2 & 8) != 0 ? null : downloadType);
    }

    public final int getId() {
        return this.id;
    }

    public final void setId(int i) {
        this.id = i;
    }

    public final long getDownloadedLength() {
        return this.downloadedLength;
    }

    public final void setDownloadedLength(long j) {
        this.downloadedLength = j;
    }

    public final long getTotalLength() {
        return this.totalLength;
    }

    public final void setTotalLength(long j) {
        this.totalLength = j;
    }

    public final VideoDownloadManager.DownloadType getStatus() {
        return this.status;
    }

    public final void setStatus(VideoDownloadManager.DownloadType downloadType) {
        this.status = downloadType;
    }

    public final long getProgressPercentage() {
        long j = this.downloadedLength;
        if (j < 1024) {
            return 0L;
        }
        return Math.max(0L, Math.min(100L, (j * 100) / (this.totalLength + 1)));
    }
}
