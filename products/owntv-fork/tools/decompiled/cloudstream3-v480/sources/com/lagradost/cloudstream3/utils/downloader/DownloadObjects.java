package com.lagradost.cloudstream3.utils.downloader;

import android.net.Uri;
import androidx.media3.extractor.text.ttml.TtmlNode;
import coil3.util.UtilsKt;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.Score;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.actions.temp.CloudStreamPackage;
import com.lagradost.cloudstream3.services.DownloadQueueService;
import com.lagradost.cloudstream3.ui.player.SubtitleData;
import com.lagradost.cloudstream3.ui.result.ResultEpisode;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.safefile.SafeFile;
import io.ktor.http.ContentDisposition;
import j$.util.Objects;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: DownloadObjects.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0012\bÆ\u0002\u0018\u00002\u00020\u0001:\u000f\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects;", "", "<init>", "()V", "DownloadQueueWrapper", "DownloadQueueItem", "DownloadCached", "DownloadEpisodeCached", "DownloadHeaderCached", "DownloadResumePackage", "DownloadItem", "DownloadEpisodeMetadata", "DownloadedFileInfo", "DownloadedFileInfoResult", "ResumeWatching", "DownloadStatus", "CreateNotificationMetadata", "StreamData", "LazyStreamDownloadResponse", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DownloadObjects {
    public static final DownloadObjects INSTANCE = new DownloadObjects();

    private DownloadObjects() {
    }

    /* JADX INFO: compiled from: DownloadObjects.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\f\u001a\u00020\rJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\u0016\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u0017\u001a\u00020\r2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u000e\u001a\u00020\u000f8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\u00020\u000f8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011¨\u0006\u001c"}, d2 = {"Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadQueueWrapper;", "", "resumePackage", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadResumePackage;", "downloadItem", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadQueueItem;", "<init>", "(Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadResumePackage;Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadQueueItem;)V", "getResumePackage", "()Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadResumePackage;", "getDownloadItem", "()Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadQueueItem;", "isCurrentlyDownloading", "", "id", "", "getId", "()I", "parentId", "getParentId", "component1", "component2", "copy", "equals", "other", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class DownloadQueueWrapper {

        @JsonProperty("downloadItem")
        private final DownloadQueueItem downloadItem;

        @JsonProperty("id")
        private final int id;

        @JsonProperty("parentId")
        private final int parentId;

        @JsonProperty("resumePackage")
        private final DownloadResumePackage resumePackage;

        public static /* synthetic */ DownloadQueueWrapper copy$default(DownloadQueueWrapper downloadQueueWrapper, DownloadResumePackage downloadResumePackage, DownloadQueueItem downloadQueueItem, int i, Object obj) {
            if ((i & 1) != 0) {
                downloadResumePackage = downloadQueueWrapper.resumePackage;
            }
            if ((i & 2) != 0) {
                downloadQueueItem = downloadQueueWrapper.downloadItem;
            }
            return downloadQueueWrapper.copy(downloadResumePackage, downloadQueueItem);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final DownloadResumePackage getResumePackage() {
            return this.resumePackage;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final DownloadQueueItem getDownloadItem() {
            return this.downloadItem;
        }

        public final DownloadQueueWrapper copy(@JsonProperty("resumePackage") DownloadResumePackage resumePackage, @JsonProperty("downloadItem") DownloadQueueItem downloadItem) {
            return new DownloadQueueWrapper(resumePackage, downloadItem);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DownloadQueueWrapper)) {
                return false;
            }
            DownloadQueueWrapper downloadQueueWrapper = (DownloadQueueWrapper) other;
            return Intrinsics.areEqual(this.resumePackage, downloadQueueWrapper.resumePackage) && Intrinsics.areEqual(this.downloadItem, downloadQueueWrapper.downloadItem);
        }

        public int hashCode() {
            DownloadResumePackage downloadResumePackage = this.resumePackage;
            int iHashCode = (downloadResumePackage == null ? 0 : downloadResumePackage.hashCode()) * 31;
            DownloadQueueItem downloadQueueItem = this.downloadItem;
            return iHashCode + (downloadQueueItem != null ? downloadQueueItem.hashCode() : 0);
        }

        public String toString() {
            return "DownloadQueueWrapper(resumePackage=" + this.resumePackage + ", downloadItem=" + this.downloadItem + ')';
        }

        public DownloadQueueWrapper(@JsonProperty("resumePackage") DownloadResumePackage downloadResumePackage, @JsonProperty("downloadItem") DownloadQueueItem downloadQueueItem) {
            int id;
            int parentId;
            DownloadItem item;
            DownloadEpisodeMetadata ep;
            DownloadItem item2;
            DownloadEpisodeMetadata ep2;
            this.resumePackage = downloadResumePackage;
            this.downloadItem = downloadQueueItem;
            if (downloadResumePackage == null || (item2 = downloadResumePackage.getItem()) == null || (ep2 = item2.getEp()) == null) {
                Intrinsics.checkNotNull(downloadQueueItem);
                id = downloadQueueItem.getEpisode().getId();
            } else {
                id = ep2.getId();
            }
            this.id = id;
            if (downloadResumePackage == null || (item = downloadResumePackage.getItem()) == null || (ep = item.getEp()) == null) {
                Intrinsics.checkNotNull(downloadQueueItem);
                parentId = downloadQueueItem.getEpisode().getParentId();
            } else {
                parentId = ep.getParentId();
            }
            this.parentId = parentId;
        }

        public final DownloadResumePackage getResumePackage() {
            return this.resumePackage;
        }

        public final DownloadQueueItem getDownloadItem() {
            return this.downloadItem;
        }

        public final boolean isCurrentlyDownloading() {
            List<VideoDownloadManager.EpisodeDownloadInstance> value = DownloadQueueService.INSTANCE.getDownloadInstances().getValue();
            if ((value instanceof Collection) && value.isEmpty()) {
                return false;
            }
            Iterator<T> it = value.iterator();
            while (it.hasNext()) {
                if (((VideoDownloadManager.EpisodeDownloadInstance) it.next()).getDownloadQueueWrapper().id == this.id) {
                    return true;
                }
            }
            return false;
        }

        public final int getId() {
            return this.id;
        }

        public final int getParentId() {
            return this.parentId;
        }
    }

    /* JADX INFO: compiled from: DownloadObjects.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0086\b\u0018\u00002\u00020\u0001B}\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\t\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0001\u0010\u000b\u001a\u00020\u0007\u0012\b\b\u0001\u0010\f\u001a\u00020\r\u0012\b\b\u0001\u0010\u000e\u001a\u00020\u0007\u0012\u0010\b\u0003\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010\u0012\u0010\b\u0003\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0010¢\u0006\u0004\b\u0014\u0010\u0015J\u0006\u0010%\u001a\u00020&J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0005HÆ\u0003J\t\u0010)\u001a\u00020\u0007HÆ\u0003J\t\u0010*\u001a\u00020\tHÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010,\u001a\u00020\u0007HÆ\u0003J\t\u0010-\u001a\u00020\rHÆ\u0003J\t\u0010.\u001a\u00020\u0007HÆ\u0003J\u0011\u0010/\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010HÆ\u0003J\u0011\u00100\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0010HÆ\u0003J\u007f\u00101\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00072\b\b\u0003\u0010\b\u001a\u00020\t2\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00072\b\b\u0003\u0010\u000b\u001a\u00020\u00072\b\b\u0003\u0010\f\u001a\u00020\r2\b\b\u0003\u0010\u000e\u001a\u00020\u00072\u0010\b\u0003\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00102\u0010\b\u0003\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0010HÆ\u0001J\u0014\u00102\u001a\u00020\u00052\b\u00103\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00104\u001a\u00020\rHÖ\u0081\u0004J\n\u00105\u001a\u00020\u0007HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0018R\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0018\u0010\n\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001aR\u0016\u0010\u000b\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u0016\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0016\u0010\u000e\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001aR\u001e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00108\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u001e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00108\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010#¨\u00066"}, d2 = {"Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadQueueItem;", "", "episode", "Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "isMovie", "", "resultName", "", "resultType", "Lcom/lagradost/cloudstream3/TvType;", "resultPoster", "apiName", "resultId", "", "resultUrl", CloudStreamPackage.LINKS_EXTRA, "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", CloudStreamPackage.SUBTITLE_EXTRA, "Lcom/lagradost/cloudstream3/ui/player/SubtitleData;", "<init>", "(Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;ZLjava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getEpisode", "()Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "()Z", "getResultName", "()Ljava/lang/String;", "getResultType", "()Lcom/lagradost/cloudstream3/TvType;", "getResultPoster", "getApiName", "getResultId", "()I", "getResultUrl", "getLinks", "()Ljava/util/List;", "getSubs", "toWrapper", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadQueueWrapper;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "equals", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class DownloadQueueItem {

        @JsonProperty("apiName")
        private final String apiName;

        @JsonProperty("episode")
        private final ResultEpisode episode;

        @JsonProperty("isMovie")
        private final boolean isMovie;

        @JsonProperty(CloudStreamPackage.LINKS_EXTRA)
        private final List<ExtractorLink> links;

        @JsonProperty("resultId")
        private final int resultId;

        @JsonProperty("resultName")
        private final String resultName;

        @JsonProperty("resultPoster")
        private final String resultPoster;

        @JsonProperty("resultType")
        private final TvType resultType;

        @JsonProperty("resultUrl")
        private final String resultUrl;

        @JsonProperty(CloudStreamPackage.SUBTITLE_EXTRA)
        private final List<SubtitleData> subs;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ DownloadQueueItem copy$default(DownloadQueueItem downloadQueueItem, ResultEpisode resultEpisode, boolean z, String str, TvType tvType, String str2, String str3, int i, String str4, List list, List list2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                resultEpisode = downloadQueueItem.episode;
            }
            if ((i2 & 2) != 0) {
                z = downloadQueueItem.isMovie;
            }
            if ((i2 & 4) != 0) {
                str = downloadQueueItem.resultName;
            }
            if ((i2 & 8) != 0) {
                tvType = downloadQueueItem.resultType;
            }
            if ((i2 & 16) != 0) {
                str2 = downloadQueueItem.resultPoster;
            }
            if ((i2 & 32) != 0) {
                str3 = downloadQueueItem.apiName;
            }
            if ((i2 & 64) != 0) {
                i = downloadQueueItem.resultId;
            }
            if ((i2 & 128) != 0) {
                str4 = downloadQueueItem.resultUrl;
            }
            if ((i2 & 256) != 0) {
                list = downloadQueueItem.links;
            }
            if ((i2 & 512) != 0) {
                list2 = downloadQueueItem.subs;
            }
            List list3 = list;
            List list4 = list2;
            int i3 = i;
            String str5 = str4;
            String str6 = str2;
            String str7 = str3;
            return downloadQueueItem.copy(resultEpisode, z, str, tvType, str6, str7, i3, str5, list3, list4);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final ResultEpisode getEpisode() {
            return this.episode;
        }

        public final List<SubtitleData> component10() {
            return this.subs;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsMovie() {
            return this.isMovie;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getResultName() {
            return this.resultName;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final TvType getResultType() {
            return this.resultType;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getResultPoster() {
            return this.resultPoster;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getApiName() {
            return this.apiName;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final int getResultId() {
            return this.resultId;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getResultUrl() {
            return this.resultUrl;
        }

        public final List<ExtractorLink> component9() {
            return this.links;
        }

        public final DownloadQueueItem copy(@JsonProperty("episode") ResultEpisode episode, @JsonProperty("isMovie") boolean isMovie, @JsonProperty("resultName") String resultName, @JsonProperty("resultType") TvType resultType, @JsonProperty("resultPoster") String resultPoster, @JsonProperty("apiName") String apiName, @JsonProperty("resultId") int resultId, @JsonProperty("resultUrl") String resultUrl, @JsonProperty(CloudStreamPackage.LINKS_EXTRA) List<? extends ExtractorLink> links, @JsonProperty(CloudStreamPackage.SUBTITLE_EXTRA) List<SubtitleData> subs) {
            Intrinsics.checkNotNullParameter(episode, "episode");
            Intrinsics.checkNotNullParameter(resultName, "resultName");
            Intrinsics.checkNotNullParameter(resultType, "resultType");
            Intrinsics.checkNotNullParameter(apiName, "apiName");
            Intrinsics.checkNotNullParameter(resultUrl, "resultUrl");
            return new DownloadQueueItem(episode, isMovie, resultName, resultType, resultPoster, apiName, resultId, resultUrl, links, subs);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DownloadQueueItem)) {
                return false;
            }
            DownloadQueueItem downloadQueueItem = (DownloadQueueItem) other;
            return Intrinsics.areEqual(this.episode, downloadQueueItem.episode) && this.isMovie == downloadQueueItem.isMovie && Intrinsics.areEqual(this.resultName, downloadQueueItem.resultName) && this.resultType == downloadQueueItem.resultType && Intrinsics.areEqual(this.resultPoster, downloadQueueItem.resultPoster) && Intrinsics.areEqual(this.apiName, downloadQueueItem.apiName) && this.resultId == downloadQueueItem.resultId && Intrinsics.areEqual(this.resultUrl, downloadQueueItem.resultUrl) && Intrinsics.areEqual(this.links, downloadQueueItem.links) && Intrinsics.areEqual(this.subs, downloadQueueItem.subs);
        }

        public int hashCode() {
            int iHashCode = ((((((this.episode.hashCode() * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.isMovie)) * 31) + this.resultName.hashCode()) * 31) + this.resultType.hashCode()) * 31;
            String str = this.resultPoster;
            int iHashCode2 = (((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.apiName.hashCode()) * 31) + this.resultId) * 31) + this.resultUrl.hashCode()) * 31;
            List<ExtractorLink> list = this.links;
            int iHashCode3 = (iHashCode2 + (list == null ? 0 : list.hashCode())) * 31;
            List<SubtitleData> list2 = this.subs;
            return iHashCode3 + (list2 != null ? list2.hashCode() : 0);
        }

        public String toString() {
            return "DownloadQueueItem(episode=" + this.episode + ", isMovie=" + this.isMovie + ", resultName=" + this.resultName + ", resultType=" + this.resultType + ", resultPoster=" + this.resultPoster + ", apiName=" + this.apiName + ", resultId=" + this.resultId + ", resultUrl=" + this.resultUrl + ", links=" + this.links + ", subs=" + this.subs + ')';
        }

        /* JADX WARN: Multi-variable type inference failed */
        public DownloadQueueItem(@JsonProperty("episode") ResultEpisode episode, @JsonProperty("isMovie") boolean z, @JsonProperty("resultName") String resultName, @JsonProperty("resultType") TvType resultType, @JsonProperty("resultPoster") String str, @JsonProperty("apiName") String apiName, @JsonProperty("resultId") int i, @JsonProperty("resultUrl") String resultUrl, @JsonProperty(CloudStreamPackage.LINKS_EXTRA) List<? extends ExtractorLink> list, @JsonProperty(CloudStreamPackage.SUBTITLE_EXTRA) List<SubtitleData> list2) {
            Intrinsics.checkNotNullParameter(episode, "episode");
            Intrinsics.checkNotNullParameter(resultName, "resultName");
            Intrinsics.checkNotNullParameter(resultType, "resultType");
            Intrinsics.checkNotNullParameter(apiName, "apiName");
            Intrinsics.checkNotNullParameter(resultUrl, "resultUrl");
            this.episode = episode;
            this.isMovie = z;
            this.resultName = resultName;
            this.resultType = resultType;
            this.resultPoster = str;
            this.apiName = apiName;
            this.resultId = i;
            this.resultUrl = resultUrl;
            this.links = list;
            this.subs = list2;
        }

        public /* synthetic */ DownloadQueueItem(ResultEpisode resultEpisode, boolean z, String str, TvType tvType, String str2, String str3, int i, String str4, List list, List list2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(resultEpisode, z, str, tvType, str2, str3, i, str4, (i2 & 256) != 0 ? null : list, (i2 & 512) != 0 ? null : list2);
        }

        public final ResultEpisode getEpisode() {
            return this.episode;
        }

        public final boolean isMovie() {
            return this.isMovie;
        }

        public final String getResultName() {
            return this.resultName;
        }

        public final TvType getResultType() {
            return this.resultType;
        }

        public final String getResultPoster() {
            return this.resultPoster;
        }

        public final String getApiName() {
            return this.apiName;
        }

        public final int getResultId() {
            return this.resultId;
        }

        public final String getResultUrl() {
            return this.resultUrl;
        }

        public final List<ExtractorLink> getLinks() {
            return this.links;
        }

        public final List<SubtitleData> getSubs() {
            return this.subs;
        }

        public final DownloadQueueWrapper toWrapper() {
            return new DownloadQueueWrapper(null, this);
        }
    }

    /* JADX INFO: compiled from: DownloadObjects.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0016\u0010\u0002\u001a\u00020\u00038\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadCached;", "", "id", "", "<init>", "(I)V", "getId", "()I", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static abstract class DownloadCached {

        @JsonProperty("id")
        private final int id;

        public DownloadCached(@JsonProperty("id") int i) {
            this.id = i;
        }

        public int getId() {
            return this.id;
        }
    }

    /* JADX INFO: compiled from: DownloadObjects.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b'\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001Bi\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0001\u0010\b\u001a\u00020\u0006\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0001\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u0006¢\u0006\u0004\b\u000f\u0010\u0010J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0006HÆ\u0003J\u0010\u0010,\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0017J\t\u0010-\u001a\u00020\u0006HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00100\u001a\u00020\rHÆ\u0003J\t\u00101\u001a\u00020\u0006HÆ\u0003Jr\u00102\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00062\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\b\u0003\u0010\b\u001a\u00020\u00062\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u0006HÆ\u0001¢\u0006\u0002\u00103J\u0014\u00104\u001a\u0002052\b\u00106\u001a\u0004\u0018\u000107HÖ\u0083\u0004J\n\u00108\u001a\u00020\u0006HÖ\u0081\u0004J\n\u00109\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\b\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R \u0010\t\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0012R\u0016\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0014\u0010\u000e\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0015R2\u0010#\u001a\u0004\u0018\u00010\u00062\b\u0010\"\u001a\u0004\u0018\u00010\u00068\u0006@FX\u0087\u000e¢\u0006\u0016\n\u0002\u0010\u0018\u0012\u0004\b$\u0010%\u001a\u0004\b&\u0010\u0017\"\u0004\b'\u0010(¨\u0006:"}, d2 = {"Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadEpisodeCached;", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadCached;", "name", "", "poster", "episode", "", "season", "parentId", "score", "Lcom/lagradost/cloudstream3/Score;", "description", "cacheTime", "", "id", "<init>", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;ILcom/lagradost/cloudstream3/Score;Ljava/lang/String;JI)V", "getName", "()Ljava/lang/String;", "getPoster", "getEpisode", "()I", "getSeason", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getParentId", "getScore", "()Lcom/lagradost/cloudstream3/Score;", "setScore", "(Lcom/lagradost/cloudstream3/Score;)V", "getDescription", "getCacheTime", "()J", "getId", ES6Iterator.VALUE_PROPERTY, "rating", "getRating$annotations", "()V", "getRating", "setRating", "(Ljava/lang/Integer;)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;ILcom/lagradost/cloudstream3/Score;Ljava/lang/String;JI)Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadEpisodeCached;", "equals", "", "other", "", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class DownloadEpisodeCached extends DownloadCached {

        @JsonProperty("cacheTime")
        private final long cacheTime;

        @JsonProperty("description")
        private final String description;

        @JsonProperty("episode")
        private final int episode;
        private final int id;

        @JsonProperty("name")
        private final String name;

        @JsonProperty("parentId")
        private final int parentId;

        @JsonProperty("poster")
        private final String poster;

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY, value = "rating")
        private Integer rating;

        @JsonProperty("score")
        private Score score;

        @JsonProperty("season")
        private final Integer season;

        public static /* synthetic */ DownloadEpisodeCached copy$default(DownloadEpisodeCached downloadEpisodeCached, String str, String str2, int i, Integer num, int i2, Score score, String str3, long j, int i3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                str = downloadEpisodeCached.name;
            }
            if ((i4 & 2) != 0) {
                str2 = downloadEpisodeCached.poster;
            }
            if ((i4 & 4) != 0) {
                i = downloadEpisodeCached.episode;
            }
            if ((i4 & 8) != 0) {
                num = downloadEpisodeCached.season;
            }
            if ((i4 & 16) != 0) {
                i2 = downloadEpisodeCached.parentId;
            }
            if ((i4 & 32) != 0) {
                score = downloadEpisodeCached.score;
            }
            if ((i4 & 64) != 0) {
                str3 = downloadEpisodeCached.description;
            }
            if ((i4 & 128) != 0) {
                j = downloadEpisodeCached.cacheTime;
            }
            if ((i4 & 256) != 0) {
                i3 = downloadEpisodeCached.id;
            }
            int i5 = i3;
            long j2 = j;
            Score score2 = score;
            String str4 = str3;
            int i6 = i2;
            int i7 = i;
            return downloadEpisodeCached.copy(str, str2, i7, num, i6, score2, str4, j2, i5);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "`rating` is the old scoring system, use score instead", replaceWith = @ReplaceWith(expression = "score", imports = {}))
        public static /* synthetic */ void getRating$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getPoster() {
            return this.poster;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getEpisode() {
            return this.episode;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Integer getSeason() {
            return this.season;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getParentId() {
            return this.parentId;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Score getScore() {
            return this.score;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getDescription() {
            return this.description;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final long getCacheTime() {
            return this.cacheTime;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final int getId() {
            return this.id;
        }

        public final DownloadEpisodeCached copy(@JsonProperty("name") String name, @JsonProperty("poster") String poster, @JsonProperty("episode") int episode, @JsonProperty("season") Integer season, @JsonProperty("parentId") int parentId, @JsonProperty("score") Score score, @JsonProperty("description") String description, @JsonProperty("cacheTime") long cacheTime, int id) {
            return new DownloadEpisodeCached(name, poster, episode, season, parentId, score, description, cacheTime, id);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DownloadEpisodeCached)) {
                return false;
            }
            DownloadEpisodeCached downloadEpisodeCached = (DownloadEpisodeCached) other;
            return Intrinsics.areEqual(this.name, downloadEpisodeCached.name) && Intrinsics.areEqual(this.poster, downloadEpisodeCached.poster) && this.episode == downloadEpisodeCached.episode && Intrinsics.areEqual(this.season, downloadEpisodeCached.season) && this.parentId == downloadEpisodeCached.parentId && Intrinsics.areEqual(this.score, downloadEpisodeCached.score) && Intrinsics.areEqual(this.description, downloadEpisodeCached.description) && this.cacheTime == downloadEpisodeCached.cacheTime && this.id == downloadEpisodeCached.id;
        }

        public int hashCode() {
            String str = this.name;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.poster;
            int iHashCode2 = (((iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31) + this.episode) * 31;
            Integer num = this.season;
            int iHashCode3 = (((iHashCode2 + (num == null ? 0 : num.hashCode())) * 31) + this.parentId) * 31;
            Score score = this.score;
            int data = (iHashCode3 + (score == null ? 0 : score.getData())) * 31;
            String str3 = this.description;
            return ((((data + (str3 != null ? str3.hashCode() : 0)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.cacheTime)) * 31) + this.id;
        }

        public String toString() {
            return "DownloadEpisodeCached(name=" + this.name + ", poster=" + this.poster + ", episode=" + this.episode + ", season=" + this.season + ", parentId=" + this.parentId + ", score=" + this.score + ", description=" + this.description + ", cacheTime=" + this.cacheTime + ", id=" + this.id + ')';
        }

        public DownloadEpisodeCached(@JsonProperty("name") String str, @JsonProperty("poster") String str2, @JsonProperty("episode") int i, @JsonProperty("season") Integer num, @JsonProperty("parentId") int i2, @JsonProperty("score") Score score, @JsonProperty("description") String str3, @JsonProperty("cacheTime") long j, int i3) {
            super(i3);
            this.name = str;
            this.poster = str2;
            this.episode = i;
            this.season = num;
            this.parentId = i2;
            this.score = score;
            this.description = str3;
            this.cacheTime = j;
            this.id = i3;
        }

        public /* synthetic */ DownloadEpisodeCached(String str, String str2, int i, Integer num, int i2, Score score, String str3, long j, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, i, num, i2, (i4 & 32) != 0 ? null : score, str3, j, i3);
        }

        public final String getName() {
            return this.name;
        }

        public final String getPoster() {
            return this.poster;
        }

        public final int getEpisode() {
            return this.episode;
        }

        public final Integer getSeason() {
            return this.season;
        }

        public final int getParentId() {
            return this.parentId;
        }

        public final Score getScore() {
            return this.score;
        }

        public final void setScore(Score score) {
            this.score = score;
        }

        public final String getDescription() {
            return this.description;
        }

        public final long getCacheTime() {
            return this.cacheTime;
        }

        @Override // com.lagradost.cloudstream3.utils.downloader.DownloadObjects.DownloadCached
        public int getId() {
            return this.id;
        }

        public final Integer getRating() {
            return this.rating;
        }

        public final void setRating(Integer num) {
            if (num != null) {
                this.score = Score.INSTANCE.fromOld(num);
            }
        }
    }

    /* JADX INFO: compiled from: DownloadObjects.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0001\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\nHÆ\u0003J\t\u0010 \u001a\u00020\fHÆ\u0003JQ\u0010!\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00062\b\b\u0003\u0010\u0007\u001a\u00020\u00032\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0014\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%HÖ\u0083\u0004J\n\u0010&\u001a\u00020\fHÖ\u0081\u0004J\n\u0010'\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0016\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006("}, d2 = {"Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadHeaderCached;", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadCached;", "apiName", "", ImagesContract.URL, "type", "Lcom/lagradost/cloudstream3/TvType;", "name", "poster", "cacheTime", "", "id", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Ljava/lang/String;JI)V", "getApiName", "()Ljava/lang/String;", "getUrl", "getType", "()Lcom/lagradost/cloudstream3/TvType;", "getName", "getPoster", "getCacheTime", "()J", "getId", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class DownloadHeaderCached extends DownloadCached {

        @JsonProperty("apiName")
        private final String apiName;

        @JsonProperty("cacheTime")
        private final long cacheTime;
        private final int id;

        @JsonProperty("name")
        private final String name;

        @JsonProperty("poster")
        private final String poster;

        @JsonProperty("type")
        private final TvType type;

        @JsonProperty(ImagesContract.URL)
        private final String url;

        public static /* synthetic */ DownloadHeaderCached copy$default(DownloadHeaderCached downloadHeaderCached, String str, String str2, TvType tvType, String str3, String str4, long j, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = downloadHeaderCached.apiName;
            }
            if ((i2 & 2) != 0) {
                str2 = downloadHeaderCached.url;
            }
            if ((i2 & 4) != 0) {
                tvType = downloadHeaderCached.type;
            }
            if ((i2 & 8) != 0) {
                str3 = downloadHeaderCached.name;
            }
            if ((i2 & 16) != 0) {
                str4 = downloadHeaderCached.poster;
            }
            if ((i2 & 32) != 0) {
                j = downloadHeaderCached.cacheTime;
            }
            if ((i2 & 64) != 0) {
                i = downloadHeaderCached.id;
            }
            int i3 = i;
            long j2 = j;
            String str5 = str4;
            TvType tvType2 = tvType;
            return downloadHeaderCached.copy(str, str2, tvType2, str3, str5, j2, i3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getApiName() {
            return this.apiName;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final TvType getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getPoster() {
            return this.poster;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final long getCacheTime() {
            return this.cacheTime;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final int getId() {
            return this.id;
        }

        public final DownloadHeaderCached copy(@JsonProperty("apiName") String apiName, @JsonProperty(ImagesContract.URL) String url, @JsonProperty("type") TvType type, @JsonProperty("name") String name, @JsonProperty("poster") String poster, @JsonProperty("cacheTime") long cacheTime, int id) {
            Intrinsics.checkNotNullParameter(apiName, "apiName");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(name, "name");
            return new DownloadHeaderCached(apiName, url, type, name, poster, cacheTime, id);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DownloadHeaderCached)) {
                return false;
            }
            DownloadHeaderCached downloadHeaderCached = (DownloadHeaderCached) other;
            return Intrinsics.areEqual(this.apiName, downloadHeaderCached.apiName) && Intrinsics.areEqual(this.url, downloadHeaderCached.url) && this.type == downloadHeaderCached.type && Intrinsics.areEqual(this.name, downloadHeaderCached.name) && Intrinsics.areEqual(this.poster, downloadHeaderCached.poster) && this.cacheTime == downloadHeaderCached.cacheTime && this.id == downloadHeaderCached.id;
        }

        public int hashCode() {
            int iHashCode = ((((((this.apiName.hashCode() * 31) + this.url.hashCode()) * 31) + this.type.hashCode()) * 31) + this.name.hashCode()) * 31;
            String str = this.poster;
            return ((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.cacheTime)) * 31) + this.id;
        }

        public String toString() {
            return "DownloadHeaderCached(apiName=" + this.apiName + ", url=" + this.url + ", type=" + this.type + ", name=" + this.name + ", poster=" + this.poster + ", cacheTime=" + this.cacheTime + ", id=" + this.id + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DownloadHeaderCached(@JsonProperty("apiName") String apiName, @JsonProperty(ImagesContract.URL) String url, @JsonProperty("type") TvType type, @JsonProperty("name") String name, @JsonProperty("poster") String str, @JsonProperty("cacheTime") long j, int i) {
            super(i);
            Intrinsics.checkNotNullParameter(apiName, "apiName");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(name, "name");
            this.apiName = apiName;
            this.url = url;
            this.type = type;
            this.name = name;
            this.poster = str;
            this.cacheTime = j;
            this.id = i;
        }

        public final String getApiName() {
            return this.apiName;
        }

        public final String getUrl() {
            return this.url;
        }

        public final TvType getType() {
            return this.type;
        }

        public final String getName() {
            return this.name;
        }

        public final String getPoster() {
            return this.poster;
        }

        public final long getCacheTime() {
            return this.cacheTime;
        }

        @Override // com.lagradost.cloudstream3.utils.downloader.DownloadObjects.DownloadCached
        public int getId() {
            return this.id;
        }
    }

    /* JADX INFO: compiled from: DownloadObjects.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\r\u001a\u00020\u000eJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000bJ$\u0010\u0011\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0012J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\u0019"}, d2 = {"Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadResumePackage;", "", "item", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadItem;", "linkIndex", "", "<init>", "(Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadItem;Ljava/lang/Integer;)V", "getItem", "()Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadItem;", "getLinkIndex", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "toWrapper", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadQueueWrapper;", "component1", "component2", "copy", "(Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadItem;Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadResumePackage;", "equals", "", "other", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class DownloadResumePackage {

        @JsonProperty("item")
        private final DownloadItem item;

        @JsonProperty("linkIndex")
        private final Integer linkIndex;

        public static /* synthetic */ DownloadResumePackage copy$default(DownloadResumePackage downloadResumePackage, DownloadItem downloadItem, Integer num, int i, Object obj) {
            if ((i & 1) != 0) {
                downloadItem = downloadResumePackage.item;
            }
            if ((i & 2) != 0) {
                num = downloadResumePackage.linkIndex;
            }
            return downloadResumePackage.copy(downloadItem, num);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final DownloadItem getItem() {
            return this.item;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getLinkIndex() {
            return this.linkIndex;
        }

        public final DownloadResumePackage copy(@JsonProperty("item") DownloadItem item, @JsonProperty("linkIndex") Integer linkIndex) {
            Intrinsics.checkNotNullParameter(item, "item");
            return new DownloadResumePackage(item, linkIndex);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DownloadResumePackage)) {
                return false;
            }
            DownloadResumePackage downloadResumePackage = (DownloadResumePackage) other;
            return Intrinsics.areEqual(this.item, downloadResumePackage.item) && Intrinsics.areEqual(this.linkIndex, downloadResumePackage.linkIndex);
        }

        public int hashCode() {
            int iHashCode = this.item.hashCode() * 31;
            Integer num = this.linkIndex;
            return iHashCode + (num == null ? 0 : num.hashCode());
        }

        public String toString() {
            return "DownloadResumePackage(item=" + this.item + ", linkIndex=" + this.linkIndex + ')';
        }

        public DownloadResumePackage(@JsonProperty("item") DownloadItem item, @JsonProperty("linkIndex") Integer num) {
            Intrinsics.checkNotNullParameter(item, "item");
            this.item = item;
            this.linkIndex = num;
        }

        public final DownloadItem getItem() {
            return this.item;
        }

        public final Integer getLinkIndex() {
            return this.linkIndex;
        }

        public final DownloadQueueWrapper toWrapper() {
            return new DownloadQueueWrapper(this, null);
        }
    }

    /* JADX INFO: compiled from: DownloadObjects.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\u000e\b\u0001\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\n\u0010\u000bJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J;\u0010\u0017\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00062\u000e\b\u0003\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0001J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadItem;", "", "source", "", "folder", "ep", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadEpisodeMetadata;", CloudStreamPackage.LINKS_EXTRA, "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadEpisodeMetadata;Ljava/util/List;)V", "getSource", "()Ljava/lang/String;", "getFolder", "getEp", "()Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadEpisodeMetadata;", "getLinks", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class DownloadItem {

        @JsonProperty("ep")
        private final DownloadEpisodeMetadata ep;

        @JsonProperty("folder")
        private final String folder;

        @JsonProperty(CloudStreamPackage.LINKS_EXTRA)
        private final List<ExtractorLink> links;

        @JsonProperty("source")
        private final String source;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ DownloadItem copy$default(DownloadItem downloadItem, String str, String str2, DownloadEpisodeMetadata downloadEpisodeMetadata, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = downloadItem.source;
            }
            if ((i & 2) != 0) {
                str2 = downloadItem.folder;
            }
            if ((i & 4) != 0) {
                downloadEpisodeMetadata = downloadItem.ep;
            }
            if ((i & 8) != 0) {
                list = downloadItem.links;
            }
            return downloadItem.copy(str, str2, downloadEpisodeMetadata, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getSource() {
            return this.source;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getFolder() {
            return this.folder;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final DownloadEpisodeMetadata getEp() {
            return this.ep;
        }

        public final List<ExtractorLink> component4() {
            return this.links;
        }

        public final DownloadItem copy(@JsonProperty("source") String source, @JsonProperty("folder") String folder, @JsonProperty("ep") DownloadEpisodeMetadata ep, @JsonProperty(CloudStreamPackage.LINKS_EXTRA) List<? extends ExtractorLink> links) {
            Intrinsics.checkNotNullParameter(ep, "ep");
            Intrinsics.checkNotNullParameter(links, "links");
            return new DownloadItem(source, folder, ep, links);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DownloadItem)) {
                return false;
            }
            DownloadItem downloadItem = (DownloadItem) other;
            return Intrinsics.areEqual(this.source, downloadItem.source) && Intrinsics.areEqual(this.folder, downloadItem.folder) && Intrinsics.areEqual(this.ep, downloadItem.ep) && Intrinsics.areEqual(this.links, downloadItem.links);
        }

        public int hashCode() {
            String str = this.source;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.folder;
            return ((((iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + this.ep.hashCode()) * 31) + this.links.hashCode();
        }

        public String toString() {
            return "DownloadItem(source=" + this.source + ", folder=" + this.folder + ", ep=" + this.ep + ", links=" + this.links + ')';
        }

        /* JADX WARN: Multi-variable type inference failed */
        public DownloadItem(@JsonProperty("source") String str, @JsonProperty("folder") String str2, @JsonProperty("ep") DownloadEpisodeMetadata ep, @JsonProperty(CloudStreamPackage.LINKS_EXTRA) List<? extends ExtractorLink> links) {
            Intrinsics.checkNotNullParameter(ep, "ep");
            Intrinsics.checkNotNullParameter(links, "links");
            this.source = str;
            this.folder = str2;
            this.ep = ep;
            this.links = links;
        }

        public final String getSource() {
            return this.source;
        }

        public final String getFolder() {
            return this.folder;
        }

        public final DownloadEpisodeMetadata getEp() {
            return this.ep;
        }

        public final List<ExtractorLink> getLinks() {
            return this.links;
        }
    }

    /* JADX INFO: compiled from: DownloadObjects.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Bm\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0006HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0019J\u0010\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0019J\u000b\u0010&\u001a\u0004\u0018\u00010\rHÆ\u0003Jt\u0010'\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00062\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\rHÆ\u0001¢\u0006\u0002\u0010(J\u0014\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010,\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010-\u001a\u00020\u0006HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u001a\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u001b\u0010\u0019R\u0018\u0010\f\u001a\u0004\u0018\u00010\r8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006."}, d2 = {"Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadEpisodeMetadata;", "", "id", "", "parentId", "mainName", "", "sourceApiName", "poster", "name", "season", "episode", "type", "Lcom/lagradost/cloudstream3/TvType;", "<init>", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/TvType;)V", "getId", "()I", "getParentId", "getMainName", "()Ljava/lang/String;", "getSourceApiName", "getPoster", "getName", "getSeason", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getEpisode", "getType", "()Lcom/lagradost/cloudstream3/TvType;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/TvType;)Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadEpisodeMetadata;", "equals", "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class DownloadEpisodeMetadata {

        @JsonProperty("episode")
        private final Integer episode;

        @JsonProperty("id")
        private final int id;

        @JsonProperty("mainName")
        private final String mainName;

        @JsonProperty("name")
        private final String name;

        @JsonProperty("parentId")
        private final int parentId;

        @JsonProperty("poster")
        private final String poster;

        @JsonProperty("season")
        private final Integer season;

        @JsonProperty("sourceApiName")
        private final String sourceApiName;

        @JsonProperty("type")
        private final TvType type;

        public static /* synthetic */ DownloadEpisodeMetadata copy$default(DownloadEpisodeMetadata downloadEpisodeMetadata, int i, int i2, String str, String str2, String str3, String str4, Integer num, Integer num2, TvType tvType, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = downloadEpisodeMetadata.id;
            }
            if ((i3 & 2) != 0) {
                i2 = downloadEpisodeMetadata.parentId;
            }
            if ((i3 & 4) != 0) {
                str = downloadEpisodeMetadata.mainName;
            }
            if ((i3 & 8) != 0) {
                str2 = downloadEpisodeMetadata.sourceApiName;
            }
            if ((i3 & 16) != 0) {
                str3 = downloadEpisodeMetadata.poster;
            }
            if ((i3 & 32) != 0) {
                str4 = downloadEpisodeMetadata.name;
            }
            if ((i3 & 64) != 0) {
                num = downloadEpisodeMetadata.season;
            }
            if ((i3 & 128) != 0) {
                num2 = downloadEpisodeMetadata.episode;
            }
            if ((i3 & 256) != 0) {
                tvType = downloadEpisodeMetadata.type;
            }
            Integer num3 = num2;
            TvType tvType2 = tvType;
            String str5 = str4;
            Integer num4 = num;
            String str6 = str3;
            String str7 = str;
            return downloadEpisodeMetadata.copy(i, i2, str7, str2, str6, str5, num4, num3, tvType2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getParentId() {
            return this.parentId;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getMainName() {
            return this.mainName;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getSourceApiName() {
            return this.sourceApiName;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getPoster() {
            return this.poster;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Integer getSeason() {
            return this.season;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Integer getEpisode() {
            return this.episode;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final TvType getType() {
            return this.type;
        }

        public final DownloadEpisodeMetadata copy(@JsonProperty("id") int id, @JsonProperty("parentId") int parentId, @JsonProperty("mainName") String mainName, @JsonProperty("sourceApiName") String sourceApiName, @JsonProperty("poster") String poster, @JsonProperty("name") String name, @JsonProperty("season") Integer season, @JsonProperty("episode") Integer episode, @JsonProperty("type") TvType type) {
            Intrinsics.checkNotNullParameter(mainName, "mainName");
            return new DownloadEpisodeMetadata(id, parentId, mainName, sourceApiName, poster, name, season, episode, type);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DownloadEpisodeMetadata)) {
                return false;
            }
            DownloadEpisodeMetadata downloadEpisodeMetadata = (DownloadEpisodeMetadata) other;
            return this.id == downloadEpisodeMetadata.id && this.parentId == downloadEpisodeMetadata.parentId && Intrinsics.areEqual(this.mainName, downloadEpisodeMetadata.mainName) && Intrinsics.areEqual(this.sourceApiName, downloadEpisodeMetadata.sourceApiName) && Intrinsics.areEqual(this.poster, downloadEpisodeMetadata.poster) && Intrinsics.areEqual(this.name, downloadEpisodeMetadata.name) && Intrinsics.areEqual(this.season, downloadEpisodeMetadata.season) && Intrinsics.areEqual(this.episode, downloadEpisodeMetadata.episode) && this.type == downloadEpisodeMetadata.type;
        }

        public int hashCode() {
            int iHashCode = ((((this.id * 31) + this.parentId) * 31) + this.mainName.hashCode()) * 31;
            String str = this.sourceApiName;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.poster;
            int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.name;
            int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
            Integer num = this.season;
            int iHashCode5 = (iHashCode4 + (num == null ? 0 : num.hashCode())) * 31;
            Integer num2 = this.episode;
            int iHashCode6 = (iHashCode5 + (num2 == null ? 0 : num2.hashCode())) * 31;
            TvType tvType = this.type;
            return iHashCode6 + (tvType != null ? tvType.hashCode() : 0);
        }

        public String toString() {
            return "DownloadEpisodeMetadata(id=" + this.id + ", parentId=" + this.parentId + ", mainName=" + this.mainName + ", sourceApiName=" + this.sourceApiName + ", poster=" + this.poster + ", name=" + this.name + ", season=" + this.season + ", episode=" + this.episode + ", type=" + this.type + ')';
        }

        public DownloadEpisodeMetadata(@JsonProperty("id") int i, @JsonProperty("parentId") int i2, @JsonProperty("mainName") String mainName, @JsonProperty("sourceApiName") String str, @JsonProperty("poster") String str2, @JsonProperty("name") String str3, @JsonProperty("season") Integer num, @JsonProperty("episode") Integer num2, @JsonProperty("type") TvType tvType) {
            Intrinsics.checkNotNullParameter(mainName, "mainName");
            this.id = i;
            this.parentId = i2;
            this.mainName = mainName;
            this.sourceApiName = str;
            this.poster = str2;
            this.name = str3;
            this.season = num;
            this.episode = num2;
            this.type = tvType;
        }

        public final int getId() {
            return this.id;
        }

        public final int getParentId() {
            return this.parentId;
        }

        public final String getMainName() {
            return this.mainName;
        }

        public final String getSourceApiName() {
            return this.sourceApiName;
        }

        public final String getPoster() {
            return this.poster;
        }

        public final String getName() {
            return this.name;
        }

        public final Integer getSeason() {
            return this.season;
        }

        public final Integer getEpisode() {
            return this.episode;
        }

        public final TvType getType() {
            return this.type;
        }
    }

    /* JADX INFO: compiled from: DownloadObjects.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u0015JP\u0010\u001d\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001¢\u0006\u0002\u0010\u001eJ\u0014\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\"\u001a\u00020\nHÖ\u0081\u0004J\n\u0010#\u001a\u00020\u0005HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u001a\u0010\t\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015¨\u0006$"}, d2 = {"Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadedFileInfo;", "", "totalBytes", "", "relativePath", "", "displayName", "extraInfo", "basePath", "linkHash", "", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "getTotalBytes", "()J", "getRelativePath", "()Ljava/lang/String;", "getDisplayName", "getExtraInfo", "getBasePath", "getLinkHash", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadedFileInfo;", "equals", "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class DownloadedFileInfo {

        @JsonProperty("basePath")
        private final String basePath;

        @JsonProperty("displayName")
        private final String displayName;

        @JsonProperty("extraInfo")
        private final String extraInfo;

        @JsonProperty("linkHash")
        private final Integer linkHash;

        @JsonProperty("relativePath")
        private final String relativePath;

        @JsonProperty("totalBytes")
        private final long totalBytes;

        public static /* synthetic */ DownloadedFileInfo copy$default(DownloadedFileInfo downloadedFileInfo, long j, String str, String str2, String str3, String str4, Integer num, int i, Object obj) {
            if ((i & 1) != 0) {
                j = downloadedFileInfo.totalBytes;
            }
            long j2 = j;
            if ((i & 2) != 0) {
                str = downloadedFileInfo.relativePath;
            }
            String str5 = str;
            if ((i & 4) != 0) {
                str2 = downloadedFileInfo.displayName;
            }
            String str6 = str2;
            if ((i & 8) != 0) {
                str3 = downloadedFileInfo.extraInfo;
            }
            String str7 = str3;
            if ((i & 16) != 0) {
                str4 = downloadedFileInfo.basePath;
            }
            String str8 = str4;
            if ((i & 32) != 0) {
                num = downloadedFileInfo.linkHash;
            }
            return downloadedFileInfo.copy(j2, str5, str6, str7, str8, num);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getTotalBytes() {
            return this.totalBytes;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getRelativePath() {
            return this.relativePath;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getDisplayName() {
            return this.displayName;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getExtraInfo() {
            return this.extraInfo;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getBasePath() {
            return this.basePath;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Integer getLinkHash() {
            return this.linkHash;
        }

        public final DownloadedFileInfo copy(@JsonProperty("totalBytes") long totalBytes, @JsonProperty("relativePath") String relativePath, @JsonProperty("displayName") String displayName, @JsonProperty("extraInfo") String extraInfo, @JsonProperty("basePath") String basePath, @JsonProperty("linkHash") Integer linkHash) {
            Intrinsics.checkNotNullParameter(relativePath, "relativePath");
            Intrinsics.checkNotNullParameter(displayName, "displayName");
            return new DownloadedFileInfo(totalBytes, relativePath, displayName, extraInfo, basePath, linkHash);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DownloadedFileInfo)) {
                return false;
            }
            DownloadedFileInfo downloadedFileInfo = (DownloadedFileInfo) other;
            return this.totalBytes == downloadedFileInfo.totalBytes && Intrinsics.areEqual(this.relativePath, downloadedFileInfo.relativePath) && Intrinsics.areEqual(this.displayName, downloadedFileInfo.displayName) && Intrinsics.areEqual(this.extraInfo, downloadedFileInfo.extraInfo) && Intrinsics.areEqual(this.basePath, downloadedFileInfo.basePath) && Intrinsics.areEqual(this.linkHash, downloadedFileInfo.linkHash);
        }

        public int hashCode() {
            int iM = ((((NextAiring$$ExternalSyntheticBackport0.m(this.totalBytes) * 31) + this.relativePath.hashCode()) * 31) + this.displayName.hashCode()) * 31;
            String str = this.extraInfo;
            int iHashCode = (iM + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.basePath;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            Integer num = this.linkHash;
            return iHashCode2 + (num != null ? num.hashCode() : 0);
        }

        public String toString() {
            return "DownloadedFileInfo(totalBytes=" + this.totalBytes + ", relativePath=" + this.relativePath + ", displayName=" + this.displayName + ", extraInfo=" + this.extraInfo + ", basePath=" + this.basePath + ", linkHash=" + this.linkHash + ')';
        }

        public DownloadedFileInfo(@JsonProperty("totalBytes") long j, @JsonProperty("relativePath") String relativePath, @JsonProperty("displayName") String displayName, @JsonProperty("extraInfo") String str, @JsonProperty("basePath") String str2, @JsonProperty("linkHash") Integer num) {
            Intrinsics.checkNotNullParameter(relativePath, "relativePath");
            Intrinsics.checkNotNullParameter(displayName, "displayName");
            this.totalBytes = j;
            this.relativePath = relativePath;
            this.displayName = displayName;
            this.extraInfo = str;
            this.basePath = str2;
            this.linkHash = num;
        }

        public /* synthetic */ DownloadedFileInfo(long j, String str, String str2, String str3, String str4, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(j, str, str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : str4, (i & 32) != 0 ? null : num);
        }

        public final long getTotalBytes() {
            return this.totalBytes;
        }

        public final String getRelativePath() {
            return this.relativePath;
        }

        public final String getDisplayName() {
            return this.displayName;
        }

        public final String getExtraInfo() {
            return this.extraInfo;
        }

        public final String getBasePath() {
            return this.basePath;
        }

        public final Integer getLinkHash() {
            return this.linkHash;
        }
    }

    /* JADX INFO: compiled from: DownloadObjects.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadedFileInfoResult;", "", "fileLength", "", "totalBytes", "path", "Landroid/net/Uri;", "<init>", "(JJLandroid/net/Uri;)V", "getFileLength", "()J", "getTotalBytes", "getPath", "()Landroid/net/Uri;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class DownloadedFileInfoResult {

        @JsonProperty("fileLength")
        private final long fileLength;

        @JsonProperty("path")
        private final Uri path;

        @JsonProperty("totalBytes")
        private final long totalBytes;

        public static /* synthetic */ DownloadedFileInfoResult copy$default(DownloadedFileInfoResult downloadedFileInfoResult, long j, long j2, Uri uri, int i, Object obj) {
            if ((i & 1) != 0) {
                j = downloadedFileInfoResult.fileLength;
            }
            long j3 = j;
            if ((i & 2) != 0) {
                j2 = downloadedFileInfoResult.totalBytes;
            }
            long j4 = j2;
            if ((i & 4) != 0) {
                uri = downloadedFileInfoResult.path;
            }
            return downloadedFileInfoResult.copy(j3, j4, uri);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getFileLength() {
            return this.fileLength;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getTotalBytes() {
            return this.totalBytes;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Uri getPath() {
            return this.path;
        }

        public final DownloadedFileInfoResult copy(@JsonProperty("fileLength") long fileLength, @JsonProperty("totalBytes") long totalBytes, @JsonProperty("path") Uri path) {
            Intrinsics.checkNotNullParameter(path, "path");
            return new DownloadedFileInfoResult(fileLength, totalBytes, path);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DownloadedFileInfoResult)) {
                return false;
            }
            DownloadedFileInfoResult downloadedFileInfoResult = (DownloadedFileInfoResult) other;
            return this.fileLength == downloadedFileInfoResult.fileLength && this.totalBytes == downloadedFileInfoResult.totalBytes && Intrinsics.areEqual(this.path, downloadedFileInfoResult.path);
        }

        public int hashCode() {
            return (((NextAiring$$ExternalSyntheticBackport0.m(this.fileLength) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.totalBytes)) * 31) + this.path.hashCode();
        }

        public String toString() {
            return "DownloadedFileInfoResult(fileLength=" + this.fileLength + ", totalBytes=" + this.totalBytes + ", path=" + this.path + ')';
        }

        public DownloadedFileInfoResult(@JsonProperty("fileLength") long j, @JsonProperty("totalBytes") long j2, @JsonProperty("path") Uri path) {
            Intrinsics.checkNotNullParameter(path, "path");
            this.fileLength = j;
            this.totalBytes = j2;
            this.path = path;
        }

        public final long getFileLength() {
            return this.fileLength;
        }

        public final long getTotalBytes() {
            return this.totalBytes;
        }

        public final Uri getPath() {
            return this.path;
        }
    }

    /* JADX INFO: compiled from: DownloadObjects.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0018\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b\u0012\b\b\u0001\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\t\u0010\u001b\u001a\u00020\bHÆ\u0003J\t\u0010\u001c\u001a\u00020\nHÆ\u0003JP\u0010\u001d\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\u0007\u001a\u00020\b2\b\b\u0003\u0010\t\u001a\u00020\nHÆ\u0001¢\u0006\u0002\u0010\u001eJ\u0014\u0010\u001f\u001a\u00020\n2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010!\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\"\u001a\u00020#HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0012\u0010\u0010R\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0013\u0010\u0010R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0016¨\u0006$"}, d2 = {"Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$ResumeWatching;", "", "parentId", "", "episodeId", "episode", "season", "updateTime", "", "isFromDownload", "", "<init>", "(ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;JZ)V", "getParentId", "()I", "getEpisodeId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getEpisode", "getSeason", "getUpdateTime", "()J", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;JZ)Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$ResumeWatching;", "equals", "other", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class ResumeWatching {

        @JsonProperty("episode")
        private final Integer episode;

        @JsonProperty("episodeId")
        private final Integer episodeId;

        @JsonProperty("isFromDownload")
        private final boolean isFromDownload;

        @JsonProperty("parentId")
        private final int parentId;

        @JsonProperty("season")
        private final Integer season;

        @JsonProperty("updateTime")
        private final long updateTime;

        public static /* synthetic */ ResumeWatching copy$default(ResumeWatching resumeWatching, int i, Integer num, Integer num2, Integer num3, long j, boolean z, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = resumeWatching.parentId;
            }
            if ((i2 & 2) != 0) {
                num = resumeWatching.episodeId;
            }
            if ((i2 & 4) != 0) {
                num2 = resumeWatching.episode;
            }
            if ((i2 & 8) != 0) {
                num3 = resumeWatching.season;
            }
            if ((i2 & 16) != 0) {
                j = resumeWatching.updateTime;
            }
            if ((i2 & 32) != 0) {
                z = resumeWatching.isFromDownload;
            }
            boolean z2 = z;
            long j2 = j;
            return resumeWatching.copy(i, num, num2, num3, j2, z2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getParentId() {
            return this.parentId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getEpisodeId() {
            return this.episodeId;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Integer getEpisode() {
            return this.episode;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Integer getSeason() {
            return this.season;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final long getUpdateTime() {
            return this.updateTime;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final boolean getIsFromDownload() {
            return this.isFromDownload;
        }

        public final ResumeWatching copy(@JsonProperty("parentId") int parentId, @JsonProperty("episodeId") Integer episodeId, @JsonProperty("episode") Integer episode, @JsonProperty("season") Integer season, @JsonProperty("updateTime") long updateTime, @JsonProperty("isFromDownload") boolean isFromDownload) {
            return new ResumeWatching(parentId, episodeId, episode, season, updateTime, isFromDownload);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ResumeWatching)) {
                return false;
            }
            ResumeWatching resumeWatching = (ResumeWatching) other;
            return this.parentId == resumeWatching.parentId && Intrinsics.areEqual(this.episodeId, resumeWatching.episodeId) && Intrinsics.areEqual(this.episode, resumeWatching.episode) && Intrinsics.areEqual(this.season, resumeWatching.season) && this.updateTime == resumeWatching.updateTime && this.isFromDownload == resumeWatching.isFromDownload;
        }

        public int hashCode() {
            int i = this.parentId * 31;
            Integer num = this.episodeId;
            int iHashCode = (i + (num == null ? 0 : num.hashCode())) * 31;
            Integer num2 = this.episode;
            int iHashCode2 = (iHashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
            Integer num3 = this.season;
            return ((((iHashCode2 + (num3 != null ? num3.hashCode() : 0)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.updateTime)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.isFromDownload);
        }

        public String toString() {
            return "ResumeWatching(parentId=" + this.parentId + ", episodeId=" + this.episodeId + ", episode=" + this.episode + ", season=" + this.season + ", updateTime=" + this.updateTime + ", isFromDownload=" + this.isFromDownload + ')';
        }

        public ResumeWatching(@JsonProperty("parentId") int i, @JsonProperty("episodeId") Integer num, @JsonProperty("episode") Integer num2, @JsonProperty("season") Integer num3, @JsonProperty("updateTime") long j, @JsonProperty("isFromDownload") boolean z) {
            this.parentId = i;
            this.episodeId = num;
            this.episode = num2;
            this.season = num3;
            this.updateTime = j;
            this.isFromDownload = z;
        }

        public final int getParentId() {
            return this.parentId;
        }

        public final Integer getEpisodeId() {
            return this.episodeId;
        }

        public final Integer getEpisode() {
            return this.episode;
        }

        public final Integer getSeason() {
            return this.season;
        }

        public final long getUpdateTime() {
            return this.updateTime;
        }

        public final boolean isFromDownload() {
            return this.isFromDownload;
        }
    }

    /* JADX INFO: compiled from: DownloadObjects.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00032\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadStatus;", "", "retrySame", "", "tryNext", "success", "<init>", "(ZZZ)V", "getRetrySame", "()Z", "getTryNext", "getSuccess", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class DownloadStatus {
        private final boolean retrySame;
        private final boolean success;
        private final boolean tryNext;

        public static /* synthetic */ DownloadStatus copy$default(DownloadStatus downloadStatus, boolean z, boolean z2, boolean z3, int i, Object obj) {
            if ((i & 1) != 0) {
                z = downloadStatus.retrySame;
            }
            if ((i & 2) != 0) {
                z2 = downloadStatus.tryNext;
            }
            if ((i & 4) != 0) {
                z3 = downloadStatus.success;
            }
            return downloadStatus.copy(z, z2, z3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getRetrySame() {
            return this.retrySame;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getTryNext() {
            return this.tryNext;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getSuccess() {
            return this.success;
        }

        public final DownloadStatus copy(boolean retrySame, boolean tryNext, boolean success) {
            return new DownloadStatus(retrySame, tryNext, success);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DownloadStatus)) {
                return false;
            }
            DownloadStatus downloadStatus = (DownloadStatus) other;
            return this.retrySame == downloadStatus.retrySame && this.tryNext == downloadStatus.tryNext && this.success == downloadStatus.success;
        }

        public int hashCode() {
            return (((NextAiring$$ExternalSyntheticBackport0.m(this.retrySame) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.tryNext)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.success);
        }

        public String toString() {
            return "DownloadStatus(retrySame=" + this.retrySame + ", tryNext=" + this.tryNext + ", success=" + this.success + ')';
        }

        public DownloadStatus(boolean z, boolean z2, boolean z3) {
            this.retrySame = z;
            this.tryNext = z2;
            this.success = z3;
        }

        public final boolean getRetrySame() {
            return this.retrySame;
        }

        public final boolean getTryNext() {
            return this.tryNext;
        }

        public final boolean getSuccess() {
            return this.success;
        }
    }

    /* JADX INFO: compiled from: DownloadObjects.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0012J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0012J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003JN\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\t\u001a\u00020\u0005HÆ\u0001¢\u0006\u0002\u0010\u001dJ\u0014\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010!\u001a\u00020\"HÖ\u0081\u0004J\n\u0010#\u001a\u00020$HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000f¨\u0006%"}, d2 = {"Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$CreateNotificationMetadata;", "", "type", "Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$DownloadType;", "bytesDownloaded", "", "bytesTotal", "hlsProgress", "hlsTotal", "bytesPerSecond", "<init>", "(Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$DownloadType;JJLjava/lang/Long;Ljava/lang/Long;J)V", "getType", "()Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$DownloadType;", "getBytesDownloaded", "()J", "getBytesTotal", "getHlsProgress", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getHlsTotal", "getBytesPerSecond", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$DownloadType;JJLjava/lang/Long;Ljava/lang/Long;J)Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$CreateNotificationMetadata;", "equals", "", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class CreateNotificationMetadata {
        private final long bytesDownloaded;
        private final long bytesPerSecond;
        private final long bytesTotal;
        private final Long hlsProgress;
        private final Long hlsTotal;
        private final VideoDownloadManager.DownloadType type;

        public static /* synthetic */ CreateNotificationMetadata copy$default(CreateNotificationMetadata createNotificationMetadata, VideoDownloadManager.DownloadType downloadType, long j, long j2, Long l, Long l2, long j3, int i, Object obj) {
            if ((i & 1) != 0) {
                downloadType = createNotificationMetadata.type;
            }
            if ((i & 2) != 0) {
                j = createNotificationMetadata.bytesDownloaded;
            }
            if ((i & 4) != 0) {
                j2 = createNotificationMetadata.bytesTotal;
            }
            if ((i & 8) != 0) {
                l = createNotificationMetadata.hlsProgress;
            }
            if ((i & 16) != 0) {
                l2 = createNotificationMetadata.hlsTotal;
            }
            if ((i & 32) != 0) {
                j3 = createNotificationMetadata.bytesPerSecond;
            }
            long j4 = j2;
            return createNotificationMetadata.copy(downloadType, j, j4, l, l2, j3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final VideoDownloadManager.DownloadType getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getBytesDownloaded() {
            return this.bytesDownloaded;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final long getBytesTotal() {
            return this.bytesTotal;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Long getHlsProgress() {
            return this.hlsProgress;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Long getHlsTotal() {
            return this.hlsTotal;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final long getBytesPerSecond() {
            return this.bytesPerSecond;
        }

        public final CreateNotificationMetadata copy(VideoDownloadManager.DownloadType type, long bytesDownloaded, long bytesTotal, Long hlsProgress, Long hlsTotal, long bytesPerSecond) {
            Intrinsics.checkNotNullParameter(type, "type");
            return new CreateNotificationMetadata(type, bytesDownloaded, bytesTotal, hlsProgress, hlsTotal, bytesPerSecond);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CreateNotificationMetadata)) {
                return false;
            }
            CreateNotificationMetadata createNotificationMetadata = (CreateNotificationMetadata) other;
            return this.type == createNotificationMetadata.type && this.bytesDownloaded == createNotificationMetadata.bytesDownloaded && this.bytesTotal == createNotificationMetadata.bytesTotal && Intrinsics.areEqual(this.hlsProgress, createNotificationMetadata.hlsProgress) && Intrinsics.areEqual(this.hlsTotal, createNotificationMetadata.hlsTotal) && this.bytesPerSecond == createNotificationMetadata.bytesPerSecond;
        }

        public int hashCode() {
            int iHashCode = ((((this.type.hashCode() * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.bytesDownloaded)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.bytesTotal)) * 31;
            Long l = this.hlsProgress;
            int iHashCode2 = (iHashCode + (l == null ? 0 : l.hashCode())) * 31;
            Long l2 = this.hlsTotal;
            return ((iHashCode2 + (l2 != null ? l2.hashCode() : 0)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.bytesPerSecond);
        }

        public String toString() {
            return "CreateNotificationMetadata(type=" + this.type + ", bytesDownloaded=" + this.bytesDownloaded + ", bytesTotal=" + this.bytesTotal + ", hlsProgress=" + this.hlsProgress + ", hlsTotal=" + this.hlsTotal + ", bytesPerSecond=" + this.bytesPerSecond + ')';
        }

        public CreateNotificationMetadata(VideoDownloadManager.DownloadType type, long j, long j2, Long l, Long l2, long j3) {
            Intrinsics.checkNotNullParameter(type, "type");
            this.type = type;
            this.bytesDownloaded = j;
            this.bytesTotal = j2;
            this.hlsProgress = l;
            this.hlsTotal = l2;
            this.bytesPerSecond = j3;
        }

        public /* synthetic */ CreateNotificationMetadata(VideoDownloadManager.DownloadType downloadType, long j, long j2, Long l, Long l2, long j3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(downloadType, j, j2, (i & 8) != 0 ? null : l, (i & 16) != 0 ? null : l2, j3);
        }

        public final VideoDownloadManager.DownloadType getType() {
            return this.type;
        }

        public final long getBytesDownloaded() {
            return this.bytesDownloaded;
        }

        public final long getBytesTotal() {
            return this.bytesTotal;
        }

        public final Long getHlsProgress() {
            return this.hlsProgress;
        }

        public final Long getHlsTotal() {
            return this.hlsTotal;
        }

        public final long getBytesPerSecond() {
            return this.bytesPerSecond;
        }
    }

    /* JADX INFO: compiled from: DownloadObjects.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\u000bJ\u0006\u0010\r\u001a\u00020\u000eJ\t\u0010\u0017\u001a\u00020\u0003HÂ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u001a\u001a\u00020\u000e2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\u001dHÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u001fHÖ\u0081\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u000f\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0011¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$StreamData;", "", "fileLength", "", UtilsKt.SCHEME_FILE, "Lcom/lagradost/safefile/SafeFile;", "<init>", "(JLcom/lagradost/safefile/SafeFile;)V", "getFile", "()Lcom/lagradost/safefile/SafeFile;", TtmlNode.TEXT_EMPHASIS_MARK_OPEN, "Ljava/io/OutputStream;", "openNew", "delete", "", "resume", "getResume", "()Z", "startAt", "getStartAt", "()J", "exists", "getExists", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class StreamData {
        private final SafeFile file;
        private final long fileLength;

        /* JADX INFO: renamed from: component1, reason: from getter */
        private final long getFileLength() {
            return this.fileLength;
        }

        public static /* synthetic */ StreamData copy$default(StreamData streamData, long j, SafeFile safeFile, int i, Object obj) {
            if ((i & 1) != 0) {
                j = streamData.fileLength;
            }
            if ((i & 2) != 0) {
                safeFile = streamData.file;
            }
            return streamData.copy(j, safeFile);
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final SafeFile getFile() {
            return this.file;
        }

        public final StreamData copy(long fileLength, SafeFile file) {
            Intrinsics.checkNotNullParameter(file, "file");
            return new StreamData(fileLength, file);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StreamData)) {
                return false;
            }
            StreamData streamData = (StreamData) other;
            return this.fileLength == streamData.fileLength && Intrinsics.areEqual(this.file, streamData.file);
        }

        public int hashCode() {
            return (NextAiring$$ExternalSyntheticBackport0.m(this.fileLength) * 31) + this.file.hashCode();
        }

        public String toString() {
            return "StreamData(fileLength=" + this.fileLength + ", file=" + this.file + ')';
        }

        public StreamData(long j, SafeFile file) {
            Intrinsics.checkNotNullParameter(file, "file");
            this.fileLength = j;
            this.file = file;
        }

        public final SafeFile getFile() {
            return this.file;
        }

        public final OutputStream open() throws IOException {
            return this.file.openOutputStreamOrThrow(getResume());
        }

        public final OutputStream openNew() throws IOException {
            return this.file.openOutputStreamOrThrow(false);
        }

        public final boolean delete() {
            return Intrinsics.areEqual((Object) this.file.delete(), (Object) true);
        }

        public final boolean getResume() {
            return this.fileLength > 0;
        }

        public final long getStartAt() {
            if (getResume()) {
                return this.fileLength;
            }
            return 0L;
        }

        public final boolean getExists() {
            return Intrinsics.areEqual((Object) this.file.exists(), (Object) true);
        }
    }

    /* JADX INFO: compiled from: DownloadObjects.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u0096\u0082\u0004J\n\u0010\u0015\u001a\u00020\u0016H\u0096\u0080\u0004J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J'\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u000e\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\f¨\u0006\u001b"}, d2 = {"Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$LazyStreamDownloadResponse;", "", "bytes", "", "startByte", "", "endByte", "<init>", "([BJJ)V", "getBytes", "()[B", "getStartByte", "()J", "getEndByte", ContentDisposition.Parameters.Size, "getSize", "toString", "", "equals", "", "other", "hashCode", "", "component1", "component2", "component3", "copy", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class LazyStreamDownloadResponse {
        private final byte[] bytes;
        private final long endByte;
        private final long startByte;

        public static /* synthetic */ LazyStreamDownloadResponse copy$default(LazyStreamDownloadResponse lazyStreamDownloadResponse, byte[] bArr, long j, long j2, int i, Object obj) {
            if ((i & 1) != 0) {
                bArr = lazyStreamDownloadResponse.bytes;
            }
            if ((i & 2) != 0) {
                j = lazyStreamDownloadResponse.startByte;
            }
            if ((i & 4) != 0) {
                j2 = lazyStreamDownloadResponse.endByte;
            }
            return lazyStreamDownloadResponse.copy(bArr, j, j2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final byte[] getBytes() {
            return this.bytes;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getStartByte() {
            return this.startByte;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final long getEndByte() {
            return this.endByte;
        }

        public final LazyStreamDownloadResponse copy(byte[] bytes, long startByte, long endByte) {
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            return new LazyStreamDownloadResponse(bytes, startByte, endByte);
        }

        public LazyStreamDownloadResponse(byte[] bytes, long j, long j2) {
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            this.bytes = bytes;
            this.startByte = j;
            this.endByte = j2;
        }

        public final byte[] getBytes() {
            return this.bytes;
        }

        public final long getStartByte() {
            return this.startByte;
        }

        public final long getEndByte() {
            return this.endByte;
        }

        public final long getSize() {
            return this.endByte - this.startByte;
        }

        public String toString() {
            return this.startByte + "->" + this.endByte;
        }

        public boolean equals(Object other) {
            if (!(other instanceof LazyStreamDownloadResponse)) {
                return false;
            }
            LazyStreamDownloadResponse lazyStreamDownloadResponse = (LazyStreamDownloadResponse) other;
            return lazyStreamDownloadResponse.startByte == this.startByte && lazyStreamDownloadResponse.endByte == this.endByte;
        }

        public int hashCode() {
            return Objects.hash(Long.valueOf(this.startByte), Long.valueOf(this.endByte));
        }
    }
}
