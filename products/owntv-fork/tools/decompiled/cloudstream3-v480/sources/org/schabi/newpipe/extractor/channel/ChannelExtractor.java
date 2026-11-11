package org.schabi.newpipe.extractor.channel;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.Extractor;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;

/* JADX INFO: loaded from: classes8.dex */
public abstract class ChannelExtractor extends Extractor {
    public static final long UNKNOWN_SUBSCRIBER_COUNT = -1;

    @Nonnull
    public abstract List<Image> getAvatars() throws ParsingException;

    @Nonnull
    public abstract List<Image> getBanners() throws ParsingException;

    public abstract String getDescription() throws ParsingException;

    public abstract String getFeedUrl() throws ParsingException;

    @Nonnull
    public abstract List<Image> getParentChannelAvatars() throws ParsingException;

    public abstract String getParentChannelName() throws ParsingException;

    public abstract String getParentChannelUrl() throws ParsingException;

    public abstract long getSubscriberCount() throws ParsingException;

    @Nonnull
    public abstract List<ListLinkHandler> getTabs() throws ParsingException;

    public abstract boolean isVerified() throws ParsingException;

    protected ChannelExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler) {
        super(streamingService, listLinkHandler);
    }

    @Nonnull
    public List<String> getTags() throws ParsingException {
        return Collections.EMPTY_LIST;
    }
}
