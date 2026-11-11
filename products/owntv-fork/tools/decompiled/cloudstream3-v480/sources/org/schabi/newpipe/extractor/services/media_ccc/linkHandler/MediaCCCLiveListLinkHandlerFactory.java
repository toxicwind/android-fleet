package org.schabi.newpipe.extractor.services.media_ccc.linkHandler;

import java.util.List;
import java.util.regex.Pattern;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory;

/* JADX INFO: loaded from: classes8.dex */
public final class MediaCCCLiveListLinkHandlerFactory extends ListLinkHandlerFactory {
    private static final MediaCCCLiveListLinkHandlerFactory INSTANCE = new MediaCCCLiveListLinkHandlerFactory();
    private static final String STREAM_PATTERN = "^(?:https?://)?media\\.ccc\\.de/live$";

    private MediaCCCLiveListLinkHandlerFactory() {
    }

    public static MediaCCCLiveListLinkHandlerFactory getInstance() {
        return INSTANCE;
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public String getId(String str) throws UnsupportedOperationException, ParsingException {
        return "live";
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public boolean onAcceptUrl(String str) throws ParsingException {
        return Pattern.matches(STREAM_PATTERN, str);
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String getUrl(String str, List<String> list, String str2) throws UnsupportedOperationException, ParsingException {
        return "https://media.ccc.de/live";
    }
}
