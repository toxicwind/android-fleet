package org.schabi.newpipe.extractor.services.peertube.linkHandler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.schabi.newpipe.extractor.ServiceList;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory;
import org.schabi.newpipe.extractor.utils.Parser;

/* JADX INFO: loaded from: classes8.dex */
public final class PeertubePlaylistLinkHandlerFactory extends ListLinkHandlerFactory {
    private static final String API_ID_PATTERN = "/video-playlists/([^/?&#]*)";
    private static final String ID_PATTERN = "(/videos/watch/playlist/|/w/p/)([^/?&#]*)";
    private static final PeertubePlaylistLinkHandlerFactory INSTANCE = new PeertubePlaylistLinkHandlerFactory();

    private PeertubePlaylistLinkHandlerFactory() {
    }

    public static PeertubePlaylistLinkHandlerFactory getInstance() {
        return INSTANCE;
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String getUrl(String str, List<String> list, String str2) throws UnsupportedOperationException, ParsingException {
        return getUrl(str, list, str2, ServiceList.PeerTube.getBaseUrl());
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String getUrl(String str, List<String> list, String str2, String str3) throws UnsupportedOperationException, ParsingException {
        return str3 + "/api/v1/video-playlists/" + str;
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public String getId(String str) throws UnsupportedOperationException, ParsingException {
        try {
            return Parser.matchGroup(ID_PATTERN, str, 2);
        } catch (ParsingException unused) {
            return Parser.matchGroup1(API_ID_PATTERN, str);
        }
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public boolean onAcceptUrl(String str) {
        try {
            new URL(str);
            getId(str);
            return true;
        } catch (MalformedURLException | ParsingException unused) {
            return false;
        }
    }
}
