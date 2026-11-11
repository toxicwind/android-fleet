package org.schabi.newpipe.extractor.services.youtube.linkHandler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public final class YoutubeLiveLinkHandlerFactory extends ListLinkHandlerFactory {
    public static final YoutubeLiveLinkHandlerFactory INSTANCE = new YoutubeLiveLinkHandlerFactory();
    public static final String KIOSK_ID = "live";
    private static final String LIVE_CHANNEL_PATH = "/channel/UC4R8DWoMoI7CAwX8_LjQHig/livetab";
    private static final String LIVE_CHANNEL_TAB_PARAMS = "ss=CKEK";

    private YoutubeLiveLinkHandlerFactory() {
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String getUrl(String str, List<String> list, String str2) throws UnsupportedOperationException, ParsingException {
        return "https://www.youtube.com/channel/UC4R8DWoMoI7CAwX8_LjQHig/livetab?ss=CKEK";
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public String getId(String str) throws UnsupportedOperationException, ParsingException {
        return "live";
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public boolean onAcceptUrl(String str) {
        try {
            URL urlStringToURL = Utils.stringToURL(str);
            if (Utils.isHTTP(urlStringToURL)) {
                return (YoutubeParsingHelper.isYoutubeURL(urlStringToURL) || YoutubeParsingHelper.isInvidiousURL(urlStringToURL)) && LIVE_CHANNEL_PATH.equals(urlStringToURL.getPath()) && LIVE_CHANNEL_TAB_PARAMS.equals(urlStringToURL.getQuery());
            }
            return false;
        } catch (MalformedURLException unused) {
            return false;
        }
    }
}
