package org.schabi.newpipe.extractor.services.youtube.linkHandler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public final class YoutubeTrendingGamingVideosLinkHandlerFactory extends ListLinkHandlerFactory {
    public static final YoutubeTrendingGamingVideosLinkHandlerFactory INSTANCE = new YoutubeTrendingGamingVideosLinkHandlerFactory();
    public static final String KIOSK_ID = "trending_gaming";

    private YoutubeTrendingGamingVideosLinkHandlerFactory() {
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String getUrl(String str, List<String> list, String str2) throws UnsupportedOperationException, ParsingException {
        return "https://www.youtube.com/gaming/trending";
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public String getId(String str) throws UnsupportedOperationException, ParsingException {
        return KIOSK_ID;
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public boolean onAcceptUrl(String str) {
        try {
            URL urlStringToURL = Utils.stringToURL(str);
            if (Utils.isHTTP(urlStringToURL)) {
                return (YoutubeParsingHelper.isYoutubeURL(urlStringToURL) || YoutubeParsingHelper.isInvidiousURL(urlStringToURL)) && "/gaming/trending".equals(urlStringToURL.getPath());
            }
            return false;
        } catch (MalformedURLException unused) {
            return false;
        }
    }
}
