package org.schabi.newpipe.extractor.services.bandcamp.linkHandler;

import java.util.List;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.channel.tabs.ChannelTabs;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.exceptions.UnsupportedTabException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory;

/* JADX INFO: loaded from: classes8.dex */
public final class BandcampChannelTabLinkHandlerFactory extends ListLinkHandlerFactory {
    private static final BandcampChannelTabLinkHandlerFactory INSTANCE = new BandcampChannelTabLinkHandlerFactory();

    private BandcampChannelTabLinkHandlerFactory() {
    }

    public static BandcampChannelTabLinkHandlerFactory getInstance() {
        return INSTANCE;
    }

    @Nonnull
    public static String getUrlSuffix(@Nonnull String str) throws UnsupportedTabException {
        str.hashCode();
        if (str.equals(ChannelTabs.ALBUMS)) {
            return "/album";
        }
        if (str.equals("tracks")) {
            return "/track";
        }
        throw new UnsupportedTabException(str);
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public String getId(String str) throws UnsupportedOperationException, ParsingException {
        return BandcampChannelLinkHandlerFactory.getInstance().getId(str);
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String getUrl(String str, List<String> list, String str2) throws UnsupportedOperationException, ParsingException {
        return BandcampChannelLinkHandlerFactory.getInstance().getUrl(str) + getUrlSuffix(list.get(0));
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public boolean onAcceptUrl(String str) throws ParsingException {
        return BandcampChannelLinkHandlerFactory.getInstance().onAcceptUrl(str);
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String[] getAvailableContentFilter() {
        return new String[]{"tracks", ChannelTabs.ALBUMS};
    }
}
