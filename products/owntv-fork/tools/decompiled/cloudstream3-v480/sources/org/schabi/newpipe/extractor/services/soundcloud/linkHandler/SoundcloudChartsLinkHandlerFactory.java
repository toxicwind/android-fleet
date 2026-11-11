package org.schabi.newpipe.extractor.services.soundcloud.linkHandler;

import java.util.List;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory;
import org.schabi.newpipe.extractor.utils.Parser;

/* JADX INFO: loaded from: classes8.dex */
public final class SoundcloudChartsLinkHandlerFactory extends ListLinkHandlerFactory {
    private static final SoundcloudChartsLinkHandlerFactory INSTANCE = new SoundcloudChartsLinkHandlerFactory();
    private static final String URL_PATTERN = "^https?://(www\\.|m\\.)?soundcloud.com/charts(/top|/new)?/?([#?].*)?$";

    private SoundcloudChartsLinkHandlerFactory() {
    }

    public static SoundcloudChartsLinkHandlerFactory getInstance() {
        return INSTANCE;
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public String getId(String str) throws UnsupportedOperationException, ParsingException {
        return "New & hot";
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String getUrl(String str, List<String> list, String str2) throws UnsupportedOperationException, ParsingException {
        return "https://soundcloud.com/charts/new";
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public boolean onAcceptUrl(String str) {
        return Parser.isMatch(URL_PATTERN, str.toLowerCase());
    }
}
