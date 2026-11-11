package org.schabi.newpipe.extractor.playlist;

import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.stream.Description;

/* JADX INFO: loaded from: classes8.dex */
public class PlaylistInfoItem extends InfoItem {
    private Description description;
    private PlaylistInfo.PlaylistType playlistType;
    private long streamCount;
    private String uploaderName;
    private String uploaderUrl;
    private boolean uploaderVerified;

    public PlaylistInfoItem(int i, String str, String str2) {
        super(InfoItem.InfoType.PLAYLIST, i, str, str2);
        this.streamCount = 0L;
    }

    public String getUploaderName() {
        return this.uploaderName;
    }

    public void setUploaderName(String str) {
        this.uploaderName = str;
    }

    @Nullable
    public String getUploaderUrl() {
        return this.uploaderUrl;
    }

    public void setUploaderUrl(@Nullable String str) {
        this.uploaderUrl = str;
    }

    public boolean isUploaderVerified() {
        return this.uploaderVerified;
    }

    public void setUploaderVerified(boolean z) {
        this.uploaderVerified = z;
    }

    public long getStreamCount() {
        return this.streamCount;
    }

    public void setStreamCount(long j) {
        this.streamCount = j;
    }

    public Description getDescription() {
        return this.description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public PlaylistInfo.PlaylistType getPlaylistType() {
        return this.playlistType;
    }

    public void setPlaylistType(PlaylistInfo.PlaylistType playlistType) {
        this.playlistType = playlistType;
    }
}
