package org.schabi.newpipe.extractor.channel.tabs;

import java.io.IOException;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.ListInfo;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.utils.ExtractorHelper;

/* JADX INFO: loaded from: classes8.dex */
public class ChannelTabInfo extends ListInfo<InfoItem> {
    public ChannelTabInfo(int i, @Nonnull ListLinkHandler listLinkHandler) {
        super(i, listLinkHandler, listLinkHandler.getContentFilters().get(0));
    }

    @Nonnull
    public static ChannelTabInfo getInfo(@Nonnull StreamingService streamingService, @Nonnull ListLinkHandler listLinkHandler) throws IOException, ExtractionException {
        ChannelTabExtractor channelTabExtractor = streamingService.getChannelTabExtractor(listLinkHandler);
        channelTabExtractor.fetchPage();
        return getInfo(channelTabExtractor);
    }

    @Nonnull
    public static ChannelTabInfo getInfo(@Nonnull ChannelTabExtractor channelTabExtractor) {
        ChannelTabInfo channelTabInfo = new ChannelTabInfo(channelTabExtractor.getServiceId(), channelTabExtractor.getLinkHandler());
        try {
            channelTabInfo.setOriginalUrl(channelTabExtractor.getOriginalUrl());
        } catch (Exception e) {
            channelTabInfo.addError(e);
        }
        ListExtractor.InfoItemsPage itemsPageOrLogError = ExtractorHelper.getItemsPageOrLogError(channelTabInfo, channelTabExtractor);
        channelTabInfo.setRelatedItems(itemsPageOrLogError.getItems());
        channelTabInfo.setNextPage(itemsPageOrLogError.getNextPage());
        return channelTabInfo;
    }

    public static ListExtractor.InfoItemsPage<InfoItem> getMoreItems(@Nonnull StreamingService streamingService, @Nonnull ListLinkHandler listLinkHandler, @Nonnull Page page) throws IOException, ExtractionException {
        return streamingService.getChannelTabExtractor(listLinkHandler).getPage(page);
    }
}
