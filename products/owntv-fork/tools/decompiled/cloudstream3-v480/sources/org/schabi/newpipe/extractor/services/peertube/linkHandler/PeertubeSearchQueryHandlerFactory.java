package org.schabi.newpipe.extractor.services.peertube.linkHandler;

import java.util.List;
import org.schabi.newpipe.extractor.ServiceList;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.SearchQueryHandlerFactory;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public final class PeertubeSearchQueryHandlerFactory extends SearchQueryHandlerFactory {
    public static final String CHANNELS = "channels";
    private static final PeertubeSearchQueryHandlerFactory INSTANCE = new PeertubeSearchQueryHandlerFactory();
    public static final String PLAYLISTS = "playlists";
    public static final String SEARCH_ENDPOINT_CHANNELS = "/api/v1/search/video-channels";
    public static final String SEARCH_ENDPOINT_PLAYLISTS = "/api/v1/search/video-playlists";
    public static final String SEARCH_ENDPOINT_VIDEOS = "/api/v1/search/videos";
    public static final String SEPIA_BASE_URL = "https://sepiasearch.org";
    public static final String SEPIA_VIDEOS = "sepia_videos";
    public static final String VIDEOS = "videos";

    private PeertubeSearchQueryHandlerFactory() {
    }

    public static PeertubeSearchQueryHandlerFactory getInstance() {
        return INSTANCE;
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.SearchQueryHandlerFactory, org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String getUrl(String str, List<String> list, String str2) throws UnsupportedOperationException, ParsingException {
        String baseUrl;
        if (!list.isEmpty() && list.get(0).startsWith("sepia_")) {
            baseUrl = SEPIA_BASE_URL;
        } else {
            baseUrl = ServiceList.PeerTube.getBaseUrl();
        }
        return getUrl(str, list, str2, baseUrl);
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String getUrl(String str, List<String> list, String str2, String str3) throws UnsupportedOperationException, ParsingException {
        String str4;
        if (list.isEmpty() || list.get(0).equals("videos") || list.get(0).equals(SEPIA_VIDEOS)) {
            str4 = SEARCH_ENDPOINT_VIDEOS;
        } else if (list.get(0).equals("channels")) {
            str4 = SEARCH_ENDPOINT_CHANNELS;
        } else {
            str4 = SEARCH_ENDPOINT_PLAYLISTS;
        }
        return str3 + str4 + "?search=" + Utils.encodeUrlUtf8(str);
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String[] getAvailableContentFilter() {
        return new String[]{"videos", "playlists", "channels", SEPIA_VIDEOS};
    }
}
