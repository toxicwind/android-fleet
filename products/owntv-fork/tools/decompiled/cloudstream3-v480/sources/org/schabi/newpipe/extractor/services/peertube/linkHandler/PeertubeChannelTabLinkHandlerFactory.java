package org.schabi.newpipe.extractor.services.peertube.linkHandler;

import java.util.List;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.exceptions.UnsupportedTabException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory;

/* JADX INFO: loaded from: classes8.dex */
public final class PeertubeChannelTabLinkHandlerFactory extends ListLinkHandlerFactory {
    private static final PeertubeChannelTabLinkHandlerFactory INSTANCE = new PeertubeChannelTabLinkHandlerFactory();

    private PeertubeChannelTabLinkHandlerFactory() {
    }

    public static PeertubeChannelTabLinkHandlerFactory getInstance() {
        return INSTANCE;
    }

    @Nonnull
    public static String getUrlSuffix(@Nonnull String str) throws UnsupportedTabException {
        str.hashCode();
        switch (str) {
            case "playlists":
                return "/video-playlists";
            case "videos":
                return "/videos";
            case "channels":
                return "/video-channels";
            default:
                throw new UnsupportedTabException(str);
        }
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public String getId(String str) throws UnsupportedOperationException, ParsingException {
        return PeertubeChannelLinkHandlerFactory.getInstance().getId(str);
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String getUrl(String str, List<String> list, String str2) throws UnsupportedOperationException, ParsingException {
        return PeertubeChannelLinkHandlerFactory.getInstance().getUrl(str) + getUrlSuffix(list.get(0));
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String getUrl(String str, List<String> list, String str2, String str3) throws UnsupportedOperationException, ParsingException {
        return PeertubeChannelLinkHandlerFactory.getInstance().getUrl(str, null, null, str3) + getUrlSuffix(list.get(0));
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public boolean onAcceptUrl(String str) throws ParsingException {
        return PeertubeChannelLinkHandlerFactory.getInstance().onAcceptUrl(str);
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String[] getAvailableContentFilter() {
        return new String[]{"videos", "channels", "playlists"};
    }
}
