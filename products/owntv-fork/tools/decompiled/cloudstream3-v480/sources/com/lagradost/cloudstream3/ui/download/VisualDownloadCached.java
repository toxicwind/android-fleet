package com.lagradost.cloudstream3.ui.download;

import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.utils.downloader.DownloadObjects;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0013\u0014B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0018\u0010\u000e\u001a\u00020\u000fX¦\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\u0010\"\u0004\b\u0011\u0010\u0012\u0082\u0001\u0002\u0015\u0016¨\u0006\u0017"}, d2 = {"Lcom/lagradost/cloudstream3/ui/download/VisualDownloadCached;", "", "<init>", "()V", "currentBytes", "", "getCurrentBytes", "()J", "totalBytes", "getTotalBytes", "data", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadCached;", "getData", "()Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadCached;", "isSelected", "", "()Z", "setSelected", "(Z)V", "Child", "Header", "Lcom/lagradost/cloudstream3/ui/download/VisualDownloadCached$Child;", "Lcom/lagradost/cloudstream3/ui/download/VisualDownloadCached$Header;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class VisualDownloadCached {
    public /* synthetic */ VisualDownloadCached(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract long getCurrentBytes();

    public abstract DownloadObjects.DownloadCached getData();

    public abstract long getTotalBytes();

    public abstract boolean isSelected();

    public abstract void setSelected(boolean z);

    private VisualDownloadCached() {
    }

    /* JADX INFO: compiled from: DownloadAdapter.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0016\u001a\u00020\bHÆ\u0003J1\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0014\u0010\u0018\u001a\u00020\b2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u001eHÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0007\u001a\u00020\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, d2 = {"Lcom/lagradost/cloudstream3/ui/download/VisualDownloadCached$Child;", "Lcom/lagradost/cloudstream3/ui/download/VisualDownloadCached;", "currentBytes", "", "totalBytes", "data", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadEpisodeCached;", "isSelected", "", "<init>", "(JJLcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadEpisodeCached;Z)V", "getCurrentBytes", "()J", "getTotalBytes", "getData", "()Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadEpisodeCached;", "()Z", "setSelected", "(Z)V", "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Child extends VisualDownloadCached {
        private final long currentBytes;
        private final DownloadObjects.DownloadEpisodeCached data;
        private boolean isSelected;
        private final long totalBytes;

        public static /* synthetic */ Child copy$default(Child child, long j, long j2, DownloadObjects.DownloadEpisodeCached downloadEpisodeCached, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                j = child.currentBytes;
            }
            long j3 = j;
            if ((i & 2) != 0) {
                j2 = child.totalBytes;
            }
            long j4 = j2;
            if ((i & 4) != 0) {
                downloadEpisodeCached = child.data;
            }
            DownloadObjects.DownloadEpisodeCached downloadEpisodeCached2 = downloadEpisodeCached;
            if ((i & 8) != 0) {
                z = child.isSelected;
            }
            return child.copy(j3, j4, downloadEpisodeCached2, z);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getCurrentBytes() {
            return this.currentBytes;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getTotalBytes() {
            return this.totalBytes;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final DownloadObjects.DownloadEpisodeCached getData() {
            return this.data;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getIsSelected() {
            return this.isSelected;
        }

        public final Child copy(long currentBytes, long totalBytes, DownloadObjects.DownloadEpisodeCached data, boolean isSelected) {
            Intrinsics.checkNotNullParameter(data, "data");
            return new Child(currentBytes, totalBytes, data, isSelected);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Child)) {
                return false;
            }
            Child child = (Child) other;
            return this.currentBytes == child.currentBytes && this.totalBytes == child.totalBytes && Intrinsics.areEqual(this.data, child.data) && this.isSelected == child.isSelected;
        }

        public int hashCode() {
            return (((((NextAiring$$ExternalSyntheticBackport0.m(this.currentBytes) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.totalBytes)) * 31) + this.data.hashCode()) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.isSelected);
        }

        public String toString() {
            return "Child(currentBytes=" + this.currentBytes + ", totalBytes=" + this.totalBytes + ", data=" + this.data + ", isSelected=" + this.isSelected + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Child(long j, long j2, DownloadObjects.DownloadEpisodeCached data, boolean z) {
            super(null);
            Intrinsics.checkNotNullParameter(data, "data");
            this.currentBytes = j;
            this.totalBytes = j2;
            this.data = data;
            this.isSelected = z;
        }

        @Override // com.lagradost.cloudstream3.ui.download.VisualDownloadCached
        public long getCurrentBytes() {
            return this.currentBytes;
        }

        @Override // com.lagradost.cloudstream3.ui.download.VisualDownloadCached
        public long getTotalBytes() {
            return this.totalBytes;
        }

        @Override // com.lagradost.cloudstream3.ui.download.VisualDownloadCached
        public DownloadObjects.DownloadEpisodeCached getData() {
            return this.data;
        }

        @Override // com.lagradost.cloudstream3.ui.download.VisualDownloadCached
        public boolean isSelected() {
            return this.isSelected;
        }

        @Override // com.lagradost.cloudstream3.ui.download.VisualDownloadCached
        public void setSelected(boolean z) {
            this.isSelected = z;
        }
    }

    /* JADX INFO: compiled from: DownloadAdapter.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u001a\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0006HÆ\u0003J\t\u0010 \u001a\u00020\bHÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\nHÆ\u0003J\t\u0010\"\u001a\u00020\fHÆ\u0003J\t\u0010#\u001a\u00020\fHÆ\u0003JQ\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\fHÆ\u0001J\u0014\u0010%\u001a\u00020\b2\b\u0010&\u001a\u0004\u0018\u00010'HÖ\u0083\u0004J\n\u0010(\u001a\u00020\fHÖ\u0081\u0004J\n\u0010)\u001a\u00020*HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0007\u001a\u00020\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\r\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001b¨\u0006+"}, d2 = {"Lcom/lagradost/cloudstream3/ui/download/VisualDownloadCached$Header;", "Lcom/lagradost/cloudstream3/ui/download/VisualDownloadCached;", "currentBytes", "", "totalBytes", "data", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadHeaderCached;", "isSelected", "", "child", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadEpisodeCached;", "currentOngoingDownloads", "", "totalDownloads", "<init>", "(JJLcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadHeaderCached;ZLcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadEpisodeCached;II)V", "getCurrentBytes", "()J", "getTotalBytes", "getData", "()Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadHeaderCached;", "()Z", "setSelected", "(Z)V", "getChild", "()Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadEpisodeCached;", "getCurrentOngoingDownloads", "()I", "getTotalDownloads", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Header extends VisualDownloadCached {
        private final DownloadObjects.DownloadEpisodeCached child;
        private final long currentBytes;
        private final int currentOngoingDownloads;
        private final DownloadObjects.DownloadHeaderCached data;
        private boolean isSelected;
        private final long totalBytes;
        private final int totalDownloads;

        public static /* synthetic */ Header copy$default(Header header, long j, long j2, DownloadObjects.DownloadHeaderCached downloadHeaderCached, boolean z, DownloadObjects.DownloadEpisodeCached downloadEpisodeCached, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                j = header.currentBytes;
            }
            long j3 = j;
            if ((i3 & 2) != 0) {
                j2 = header.totalBytes;
            }
            long j4 = j2;
            if ((i3 & 4) != 0) {
                downloadHeaderCached = header.data;
            }
            return header.copy(j3, j4, downloadHeaderCached, (i3 & 8) != 0 ? header.isSelected : z, (i3 & 16) != 0 ? header.child : downloadEpisodeCached, (i3 & 32) != 0 ? header.currentOngoingDownloads : i, (i3 & 64) != 0 ? header.totalDownloads : i2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getCurrentBytes() {
            return this.currentBytes;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getTotalBytes() {
            return this.totalBytes;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final DownloadObjects.DownloadHeaderCached getData() {
            return this.data;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getIsSelected() {
            return this.isSelected;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final DownloadObjects.DownloadEpisodeCached getChild() {
            return this.child;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final int getCurrentOngoingDownloads() {
            return this.currentOngoingDownloads;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final int getTotalDownloads() {
            return this.totalDownloads;
        }

        public final Header copy(long currentBytes, long totalBytes, DownloadObjects.DownloadHeaderCached data, boolean isSelected, DownloadObjects.DownloadEpisodeCached child, int currentOngoingDownloads, int totalDownloads) {
            Intrinsics.checkNotNullParameter(data, "data");
            return new Header(currentBytes, totalBytes, data, isSelected, child, currentOngoingDownloads, totalDownloads);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Header)) {
                return false;
            }
            Header header = (Header) other;
            return this.currentBytes == header.currentBytes && this.totalBytes == header.totalBytes && Intrinsics.areEqual(this.data, header.data) && this.isSelected == header.isSelected && Intrinsics.areEqual(this.child, header.child) && this.currentOngoingDownloads == header.currentOngoingDownloads && this.totalDownloads == header.totalDownloads;
        }

        public int hashCode() {
            int iM = ((((((NextAiring$$ExternalSyntheticBackport0.m(this.currentBytes) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.totalBytes)) * 31) + this.data.hashCode()) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.isSelected)) * 31;
            DownloadObjects.DownloadEpisodeCached downloadEpisodeCached = this.child;
            return ((((iM + (downloadEpisodeCached == null ? 0 : downloadEpisodeCached.hashCode())) * 31) + this.currentOngoingDownloads) * 31) + this.totalDownloads;
        }

        public String toString() {
            return "Header(currentBytes=" + this.currentBytes + ", totalBytes=" + this.totalBytes + ", data=" + this.data + ", isSelected=" + this.isSelected + ", child=" + this.child + ", currentOngoingDownloads=" + this.currentOngoingDownloads + ", totalDownloads=" + this.totalDownloads + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Header(long j, long j2, DownloadObjects.DownloadHeaderCached data, boolean z, DownloadObjects.DownloadEpisodeCached downloadEpisodeCached, int i, int i2) {
            super(null);
            Intrinsics.checkNotNullParameter(data, "data");
            this.currentBytes = j;
            this.totalBytes = j2;
            this.data = data;
            this.isSelected = z;
            this.child = downloadEpisodeCached;
            this.currentOngoingDownloads = i;
            this.totalDownloads = i2;
        }

        @Override // com.lagradost.cloudstream3.ui.download.VisualDownloadCached
        public long getCurrentBytes() {
            return this.currentBytes;
        }

        @Override // com.lagradost.cloudstream3.ui.download.VisualDownloadCached
        public long getTotalBytes() {
            return this.totalBytes;
        }

        @Override // com.lagradost.cloudstream3.ui.download.VisualDownloadCached
        public DownloadObjects.DownloadHeaderCached getData() {
            return this.data;
        }

        @Override // com.lagradost.cloudstream3.ui.download.VisualDownloadCached
        public boolean isSelected() {
            return this.isSelected;
        }

        @Override // com.lagradost.cloudstream3.ui.download.VisualDownloadCached
        public void setSelected(boolean z) {
            this.isSelected = z;
        }

        public final DownloadObjects.DownloadEpisodeCached getChild() {
            return this.child;
        }

        public final int getCurrentOngoingDownloads() {
            return this.currentOngoingDownloads;
        }

        public final int getTotalDownloads() {
            return this.totalDownloads;
        }
    }
}
