package org.schabi.newpipe.extractor.services.media_ccc.linkHandler;

import java.util.List;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory;

/* JADX INFO: loaded from: classes8.dex */
public final class MediaCCCConferencesListLinkHandlerFactory extends ListLinkHandlerFactory {
    private static final MediaCCCConferencesListLinkHandlerFactory INSTANCE = new MediaCCCConferencesListLinkHandlerFactory();

    private MediaCCCConferencesListLinkHandlerFactory() {
    }

    public static MediaCCCConferencesListLinkHandlerFactory getInstance() {
        return INSTANCE;
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public String getId(String str) throws UnsupportedOperationException, ParsingException {
        return "conferences";
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String getUrl(String str, List<String> list, String str2) throws UnsupportedOperationException, ParsingException {
        return "https://media.ccc.de/public/conferences";
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public boolean onAcceptUrl(String str) {
        return str.equals("https://media.ccc.de/b/conferences") || str.equals("https://media.ccc.de/public/conferences") || str.equals("https://api.media.ccc.de/public/conferences");
    }
}
