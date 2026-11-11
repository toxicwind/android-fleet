package org.schabi.newpipe.extractor.services.youtube.linkHandler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public final class YoutubeTrendingLinkHandlerFactory extends ListLinkHandlerFactory {
    public static final YoutubeTrendingLinkHandlerFactory INSTANCE = new YoutubeTrendingLinkHandlerFactory();

    private YoutubeTrendingLinkHandlerFactory() {
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String getUrl(String str, List<String> list, String str2) throws UnsupportedOperationException, ParsingException {
        return "https://www.youtube.com/feed/trending";
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public String getId(String str) throws UnsupportedOperationException, ParsingException {
        return "Trending";
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public boolean onAcceptUrl(String str) {
        try {
            URL urlStringToURL = Utils.stringToURL(str);
            String path = urlStringToURL.getPath();
            if (Utils.isHTTP(urlStringToURL)) {
                return (YoutubeParsingHelper.isYoutubeURL(urlStringToURL) || YoutubeParsingHelper.isInvidiousURL(urlStringToURL)) && path.equals("/feed/trending");
            }
            return false;
        } catch (MalformedURLException unused) {
            return false;
        }
    }
}
