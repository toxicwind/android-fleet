package org.schabi.newpipe.extractor.channel.tabs;

import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;

/* JADX INFO: loaded from: classes8.dex */
public abstract class ChannelTabExtractor extends ListExtractor<InfoItem> {
    protected ChannelTabExtractor(@Nonnull StreamingService streamingService, @Nonnull ListLinkHandler listLinkHandler) {
        super(streamingService, listLinkHandler);
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getName() {
        return getLinkHandler().getContentFilters().get(0);
    }
}
