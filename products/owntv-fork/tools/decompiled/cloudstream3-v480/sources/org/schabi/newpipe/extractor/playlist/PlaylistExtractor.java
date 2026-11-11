package org.schabi.newpipe.extractor.playlist;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.stream.Description;
import org.schabi.newpipe.extractor.stream.StreamInfoItem;

/* JADX INFO: loaded from: classes8.dex */
public abstract class PlaylistExtractor extends ListExtractor<StreamInfoItem> {
    @Nonnull
    public abstract Description getDescription() throws ParsingException;

    public abstract long getStreamCount() throws ParsingException;

    @Nonnull
    public abstract List<Image> getUploaderAvatars() throws ParsingException;

    public abstract String getUploaderName() throws ParsingException;

    public abstract String getUploaderUrl() throws ParsingException;

    public abstract boolean isUploaderVerified() throws ParsingException;

    public PlaylistExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler) {
        super(streamingService, listLinkHandler);
    }

    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        return Collections.EMPTY_LIST;
    }

    @Nonnull
    public List<Image> getBanners() throws ParsingException {
        return Collections.EMPTY_LIST;
    }

    @Nonnull
    public String getSubChannelName() throws ParsingException {
        return "";
    }

    @Nonnull
    public String getSubChannelUrl() throws ParsingException {
        return "";
    }

    @Nonnull
    public List<Image> getSubChannelAvatars() throws ParsingException {
        return Collections.EMPTY_LIST;
    }

    public PlaylistInfo.PlaylistType getPlaylistType() throws ParsingException {
        return PlaylistInfo.PlaylistType.NORMAL;
    }
}
