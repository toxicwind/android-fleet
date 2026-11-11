package org.schabi.newpipe.extractor.services.peertube.linkHandler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.ServiceList;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory;
import org.schabi.newpipe.extractor.utils.Parser;

/* JADX INFO: loaded from: classes8.dex */
public final class PeertubeChannelLinkHandlerFactory extends ListLinkHandlerFactory {
    public static final String API_ENDPOINT = "/api/v1/";
    private static final String ID_PATTERN = "((accounts|a)|(video-channels|c))/([^/?&#]*)";
    private static final String ID_URL_PATTERN = "/((accounts|a)|(video-channels|c))/([^/?&#]*)";
    private static final PeertubeChannelLinkHandlerFactory INSTANCE = new PeertubeChannelLinkHandlerFactory();

    private PeertubeChannelLinkHandlerFactory() {
    }

    public static PeertubeChannelLinkHandlerFactory getInstance() {
        return INSTANCE;
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public String getId(String str) throws UnsupportedOperationException, ParsingException {
        return fixId(Parser.matchGroup(ID_URL_PATTERN, str, 0));
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String getUrl(String str, List<String> list, String str2) throws UnsupportedOperationException, ParsingException {
        return getUrl(str, list, str2, ServiceList.PeerTube.getBaseUrl());
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String getUrl(String str, List<String> list, String str2, String str3) throws UnsupportedOperationException, ParsingException {
        if (str.matches(ID_PATTERN)) {
            return str3 + "/" + fixId(str);
        }
        return str3 + "/accounts/" + str;
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public boolean onAcceptUrl(String str) {
        try {
            new URL(str);
            return str.contains("/accounts/") || str.contains("/a/") || str.contains("/video-channels/") || str.contains("/c/");
        } catch (MalformedURLException unused) {
            return false;
        }
    }

    private String fixId(@Nonnull String str) {
        if (str.startsWith("/")) {
            str = str.substring(1);
        }
        if (str.startsWith("a/")) {
            return "accounts" + str.substring(1);
        }
        if (!str.startsWith("c/")) {
            return str;
        }
        return "video-channels" + str.substring(1);
    }
}
