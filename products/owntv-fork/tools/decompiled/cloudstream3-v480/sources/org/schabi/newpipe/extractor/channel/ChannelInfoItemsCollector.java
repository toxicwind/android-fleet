package org.schabi.newpipe.extractor.channel;

import org.schabi.newpipe.extractor.InfoItemsCollector;
import org.schabi.newpipe.extractor.exceptions.ParsingException;

/* JADX INFO: loaded from: classes8.dex */
public final class ChannelInfoItemsCollector extends InfoItemsCollector<ChannelInfoItem, ChannelInfoItemExtractor> {
    public ChannelInfoItemsCollector(int i) {
        super(i);
    }

    @Override // org.schabi.newpipe.extractor.Collector
    public ChannelInfoItem extract(ChannelInfoItemExtractor channelInfoItemExtractor) throws ParsingException {
        ChannelInfoItem channelInfoItem = new ChannelInfoItem(getServiceId(), channelInfoItemExtractor.getUrl(), channelInfoItemExtractor.getName());
        try {
            channelInfoItem.setSubscriberCount(channelInfoItemExtractor.getSubscriberCount());
        } catch (Exception e) {
            addError(e);
        }
        try {
            channelInfoItem.setStreamCount(channelInfoItemExtractor.getStreamCount());
        } catch (Exception e2) {
            addError(e2);
        }
        try {
            channelInfoItem.setThumbnails(channelInfoItemExtractor.getThumbnails());
        } catch (Exception e3) {
            addError(e3);
        }
        try {
            channelInfoItem.setDescription(channelInfoItemExtractor.getDescription());
        } catch (Exception e4) {
            addError(e4);
        }
        try {
            channelInfoItem.setVerified(channelInfoItemExtractor.isVerified());
        } catch (Exception e5) {
            addError(e5);
        }
        return channelInfoItem;
    }
}
