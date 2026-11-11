package org.schabi.newpipe.extractor.channel;

import org.schabi.newpipe.extractor.InfoItem;

/* JADX INFO: loaded from: classes8.dex */
public class ChannelInfoItem extends InfoItem {
    private String description;
    private long streamCount;
    private long subscriberCount;
    private boolean verified;

    public ChannelInfoItem(int i, String str, String str2) {
        super(InfoItem.InfoType.CHANNEL, i, str, str2);
        this.subscriberCount = -1L;
        this.streamCount = -1L;
        this.verified = false;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public long getSubscriberCount() {
        return this.subscriberCount;
    }

    public void setSubscriberCount(long j) {
        this.subscriberCount = j;
    }

    public long getStreamCount() {
        return this.streamCount;
    }

    public void setStreamCount(long j) {
        this.streamCount = j;
    }

    public boolean isVerified() {
        return this.verified;
    }

    public void setVerified(boolean z) {
        this.verified = z;
    }
}
