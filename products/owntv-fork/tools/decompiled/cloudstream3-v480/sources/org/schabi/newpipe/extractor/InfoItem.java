package org.schabi.newpipe.extractor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes8.dex */
public abstract class InfoItem implements Serializable {
    private final InfoType infoType;
    private final String name;
    private final int serviceId;

    @Nonnull
    private List<Image> thumbnails = Collections.EMPTY_LIST;
    private final String url;

    public enum InfoType {
        STREAM,
        PLAYLIST,
        CHANNEL,
        COMMENT
    }

    public InfoItem(InfoType infoType, int i, String str, String str2) {
        this.infoType = infoType;
        this.serviceId = i;
        this.url = str;
        this.name = str2;
    }

    public InfoType getInfoType() {
        return this.infoType;
    }

    public int getServiceId() {
        return this.serviceId;
    }

    public String getUrl() {
        return this.url;
    }

    public String getName() {
        return this.name;
    }

    public void setThumbnails(@Nonnull List<Image> list) {
        this.thumbnails = list;
    }

    @Nonnull
    public List<Image> getThumbnails() {
        return this.thumbnails;
    }

    public String toString() {
        return getClass().getSimpleName() + "[url=\"" + this.url + "\", name=\"" + this.name + "\"]";
    }
}
