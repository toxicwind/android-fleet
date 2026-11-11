package org.schabi.newpipe.extractor.stream;

import java.io.Serializable;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes8.dex */
public class StreamSegment implements Serializable {
    private String channelName;

    @Nullable
    private String previewUrl = null;
    private int startTimeSeconds;
    private String title;

    @Nullable
    public String url;

    public StreamSegment(String str, int i) {
        this.title = str;
        this.startTimeSeconds = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public int getStartTimeSeconds() {
        return this.startTimeSeconds;
    }

    public void setStartTimeSeconds(int i) {
        this.startTimeSeconds = i;
    }

    @Nullable
    public String getChannelName() {
        return this.channelName;
    }

    public void setChannelName(@Nullable String str) {
        this.channelName = str;
    }

    @Nullable
    public String getUrl() {
        return this.url;
    }

    public void setUrl(@Nullable String str) {
        this.url = str;
    }

    @Nullable
    public String getPreviewUrl() {
        return this.previewUrl;
    }

    public void setPreviewUrl(@Nullable String str) {
        this.previewUrl = str;
    }
}
