package org.schabi.newpipe.extractor.stream;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.localization.DateWrapper;

/* JADX INFO: loaded from: classes8.dex */
public class StreamInfoItem extends InfoItem {

    @Nonnull
    private ContentAvailability contentAvailability;
    private long duration;
    private String shortDescription;
    private boolean shortFormContent;
    private final StreamType streamType;
    private String textualUploadDate;

    @Nullable
    private DateWrapper uploadDate;

    @Nonnull
    private List<Image> uploaderAvatars;
    private String uploaderName;
    private String uploaderUrl;
    private boolean uploaderVerified;
    private long viewCount;

    public StreamInfoItem(int i, String str, String str2, StreamType streamType) {
        super(InfoItem.InfoType.STREAM, i, str, str2);
        this.viewCount = -1L;
        this.duration = -1L;
        this.uploaderUrl = null;
        this.uploaderAvatars = Collections.EMPTY_LIST;
        this.uploaderVerified = false;
        this.shortFormContent = false;
        this.contentAvailability = ContentAvailability.AVAILABLE;
        this.streamType = streamType;
    }

    public StreamType getStreamType() {
        return this.streamType;
    }

    public String getUploaderName() {
        return this.uploaderName;
    }

    public void setUploaderName(String str) {
        this.uploaderName = str;
    }

    public long getViewCount() {
        return this.viewCount;
    }

    public void setViewCount(long j) {
        this.viewCount = j;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public String getUploaderUrl() {
        return this.uploaderUrl;
    }

    public void setUploaderUrl(String str) {
        this.uploaderUrl = str;
    }

    @Nonnull
    public List<Image> getUploaderAvatars() {
        return this.uploaderAvatars;
    }

    public void setUploaderAvatars(@Nonnull List<Image> list) {
        this.uploaderAvatars = list;
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    public void setShortDescription(String str) {
        this.shortDescription = str;
    }

    @Nullable
    public String getTextualUploadDate() {
        return this.textualUploadDate;
    }

    public void setTextualUploadDate(String str) {
        this.textualUploadDate = str;
    }

    @Nullable
    public DateWrapper getUploadDate() {
        return this.uploadDate;
    }

    public void setUploadDate(@Nullable DateWrapper dateWrapper) {
        this.uploadDate = dateWrapper;
    }

    public boolean isUploaderVerified() {
        return this.uploaderVerified;
    }

    public void setUploaderVerified(boolean z) {
        this.uploaderVerified = z;
    }

    public boolean isShortFormContent() {
        return this.shortFormContent;
    }

    public void setShortFormContent(boolean z) {
        this.shortFormContent = z;
    }

    @Nonnull
    public ContentAvailability getContentAvailability() {
        return this.contentAvailability;
    }

    public void setContentAvailability(@Nonnull ContentAvailability contentAvailability) {
        this.contentAvailability = contentAvailability;
    }

    @Override // org.schabi.newpipe.extractor.InfoItem
    public String toString() {
        return "StreamInfoItem{streamType=" + this.streamType + ", uploaderName='" + this.uploaderName + "', textualUploadDate='" + this.textualUploadDate + "', viewCount=" + this.viewCount + ", duration=" + this.duration + ", uploaderUrl='" + this.uploaderUrl + "', infoType=" + getInfoType() + ", serviceId=" + getServiceId() + ", url='" + getUrl() + "', name='" + getName() + "', thumbnails='" + getThumbnails() + "', uploaderVerified='" + isUploaderVerified() + "'}";
    }
}
