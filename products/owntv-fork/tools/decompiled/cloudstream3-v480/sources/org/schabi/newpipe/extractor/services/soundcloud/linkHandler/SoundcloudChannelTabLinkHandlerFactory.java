package org.schabi.newpipe.extractor.services.soundcloud.linkHandler;

import java.util.List;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.channel.tabs.ChannelTabs;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.exceptions.UnsupportedTabException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory;

/* JADX INFO: loaded from: classes8.dex */
public final class SoundcloudChannelTabLinkHandlerFactory extends ListLinkHandlerFactory {
    private static final SoundcloudChannelTabLinkHandlerFactory INSTANCE = new SoundcloudChannelTabLinkHandlerFactory();

    private SoundcloudChannelTabLinkHandlerFactory() {
    }

    public static SoundcloudChannelTabLinkHandlerFactory getInstance() {
        return INSTANCE;
    }

    @Nonnull
    public static String getUrlSuffix(String str) throws UnsupportedOperationException {
        str.hashCode();
        switch (str) {
            case "playlists":
                return "/sets";
            case "albums":
                return "/albums";
            case "tracks":
                return "/tracks";
            case "likes":
                return "/likes";
            default:
                throw new UnsupportedTabException(str);
        }
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public String getId(String str) throws ParsingException {
        return SoundcloudChannelLinkHandlerFactory.getInstance().getId(str);
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String getUrl(String str, List<String> list, String str2) throws ParsingException {
        return SoundcloudChannelLinkHandlerFactory.getInstance().getUrl(str) + getUrlSuffix(list.get(0));
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public boolean onAcceptUrl(String str) throws ParsingException {
        return SoundcloudChannelLinkHandlerFactory.getInstance().onAcceptUrl(str);
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String[] getAvailableContentFilter() {
        return new String[]{"tracks", "playlists", ChannelTabs.ALBUMS, ChannelTabs.LIKES};
    }
}
