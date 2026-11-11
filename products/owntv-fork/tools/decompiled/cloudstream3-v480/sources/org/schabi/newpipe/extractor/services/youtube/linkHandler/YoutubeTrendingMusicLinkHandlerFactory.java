package org.schabi.newpipe.extractor.services.youtube.linkHandler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public final class YoutubeTrendingMusicLinkHandlerFactory extends ListLinkHandlerFactory {
    public static final YoutubeTrendingMusicLinkHandlerFactory INSTANCE = new YoutubeTrendingMusicLinkHandlerFactory();
    public static final String KIOSK_ID = "trending_music";
    private static final String PATH = "/charts/TrendingVideos";

    private YoutubeTrendingMusicLinkHandlerFactory() {
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String getUrl(String str, List<String> list, String str2) throws UnsupportedOperationException, ParsingException {
        return "https://charts.youtube.com/charts/TrendingVideos/RightNow";
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public String getId(String str) throws UnsupportedOperationException, ParsingException {
        return KIOSK_ID;
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public boolean onAcceptUrl(String str) throws ParsingException {
        try {
            URL urlStringToURL = Utils.stringToURL(str);
            return Utils.isHTTP(urlStringToURL) && "charts.youtube.com".equals(urlStringToURL.getHost().toLowerCase(Locale.ROOT)) && urlStringToURL.getPath().startsWith(PATH);
        } catch (MalformedURLException unused) {
            return false;
        }
    }
}
