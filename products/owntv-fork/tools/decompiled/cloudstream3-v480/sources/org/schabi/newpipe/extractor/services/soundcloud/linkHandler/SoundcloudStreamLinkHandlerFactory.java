package org.schabi.newpipe.extractor.services.soundcloud.linkHandler;

import java.util.regex.Pattern;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory;
import org.schabi.newpipe.extractor.services.soundcloud.SoundcloudParsingHelper;
import org.schabi.newpipe.extractor.utils.Parser;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public final class SoundcloudStreamLinkHandlerFactory extends LinkHandlerFactory {
    private static final String ON_URL_PATTERN = "^https?://on\\.soundcloud\\.com/[0-9a-zA-Z]+$";
    private static final SoundcloudStreamLinkHandlerFactory INSTANCE = new SoundcloudStreamLinkHandlerFactory();
    private static final Pattern URL_PATTERN = Pattern.compile("^https?://(?:www\\.|m\\.)?soundcloud.com/[0-9a-z_-]+/(?!(?:tracks|albums|sets|reposts|followers|following)/?$)[0-9a-z_-]+/?(?:[#?].*)?$|^https?://on\\.soundcloud\\.com/[0-9a-zA-Z]+$");
    private static final Pattern API_URL_PATTERN = Pattern.compile("^https?://api-v2\\.soundcloud.com/(tracks|albums|sets|reposts|followers|following)/([0-9a-z_-]+)/");

    private SoundcloudStreamLinkHandlerFactory() {
    }

    public static SoundcloudStreamLinkHandlerFactory getInstance() {
        return INSTANCE;
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public String getUrl(String str) throws UnsupportedOperationException, ParsingException {
        try {
            return SoundcloudParsingHelper.resolveUrlWithEmbedPlayer("https://api.soundcloud.com/tracks/" + str);
        } catch (Exception e) {
            throw new ParsingException(e.getMessage(), e);
        }
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public String getId(String str) throws UnsupportedOperationException, ParsingException {
        Pattern pattern = API_URL_PATTERN;
        if (Parser.isMatch(pattern, str)) {
            return Parser.matchGroup1(pattern, str);
        }
        Utils.checkUrl(URL_PATTERN, str);
        try {
            return SoundcloudParsingHelper.resolveIdWithWidgetApi(str);
        } catch (Exception e) {
            throw new ParsingException(e.getMessage(), e);
        }
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public boolean onAcceptUrl(String str) throws ParsingException {
        return Parser.isMatch(URL_PATTERN, str.toLowerCase());
    }
}
