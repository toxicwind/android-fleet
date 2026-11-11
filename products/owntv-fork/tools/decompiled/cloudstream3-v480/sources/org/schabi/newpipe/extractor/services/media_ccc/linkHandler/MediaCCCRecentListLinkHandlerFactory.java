package org.schabi.newpipe.extractor.services.media_ccc.linkHandler;

import java.util.List;
import java.util.regex.Pattern;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory;
import org.schabi.newpipe.extractor.services.media_ccc.extractors.MediaCCCRecentKiosk;

/* JADX INFO: loaded from: classes8.dex */
public final class MediaCCCRecentListLinkHandlerFactory extends ListLinkHandlerFactory {
    private static final MediaCCCRecentListLinkHandlerFactory INSTANCE = new MediaCCCRecentListLinkHandlerFactory();
    private static final String PATTERN = "^(https?://)?media\\.ccc\\.de/recent/?$";

    private MediaCCCRecentListLinkHandlerFactory() {
    }

    public static MediaCCCRecentListLinkHandlerFactory getInstance() {
        return INSTANCE;
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public String getId(String str) throws UnsupportedOperationException, ParsingException {
        return MediaCCCRecentKiosk.KIOSK_ID;
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public boolean onAcceptUrl(String str) {
        return Pattern.matches(PATTERN, str);
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String getUrl(String str, List<String> list, String str2) throws UnsupportedOperationException, ParsingException {
        return "https://media.ccc.de/recent";
    }
}
