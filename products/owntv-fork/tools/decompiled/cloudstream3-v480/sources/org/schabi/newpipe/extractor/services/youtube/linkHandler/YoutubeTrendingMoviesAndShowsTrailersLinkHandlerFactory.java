package org.schabi.newpipe.extractor.services.youtube.linkHandler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public final class YoutubeTrendingMoviesAndShowsTrailersLinkHandlerFactory extends ListLinkHandlerFactory {
    public static final YoutubeTrendingMoviesAndShowsTrailersLinkHandlerFactory INSTANCE = new YoutubeTrendingMoviesAndShowsTrailersLinkHandlerFactory();
    public static final String KIOSK_ID = "trending_movies_and_shows";
    private static final String PATH = "/charts/TrendingTrailers";

    private YoutubeTrendingMoviesAndShowsTrailersLinkHandlerFactory() {
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String getUrl(String str, List<String> list, String str2) throws UnsupportedOperationException, ParsingException {
        return "https://charts.youtube.com/charts/TrendingTrailers";
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public String getId(String str) throws UnsupportedOperationException, ParsingException {
        return KIOSK_ID;
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public boolean onAcceptUrl(String str) throws ParsingException {
        try {
            URL urlStringToURL = Utils.stringToURL(str);
            return Utils.isHTTP(urlStringToURL) && "charts.youtube.com".equals(urlStringToURL.getHost().toLowerCase(Locale.ROOT)) && PATH.equals(urlStringToURL.getPath());
        } catch (MalformedURLException unused) {
            return false;
        }
    }
}
