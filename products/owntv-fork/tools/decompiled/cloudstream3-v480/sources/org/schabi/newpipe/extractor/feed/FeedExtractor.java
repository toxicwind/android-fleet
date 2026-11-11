package org.schabi.newpipe.extractor.feed;

import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.stream.StreamInfoItem;

/* JADX INFO: loaded from: classes8.dex */
public abstract class FeedExtractor extends ListExtractor<StreamInfoItem> {
    public FeedExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler) {
        super(streamingService, listLinkHandler);
    }
}
