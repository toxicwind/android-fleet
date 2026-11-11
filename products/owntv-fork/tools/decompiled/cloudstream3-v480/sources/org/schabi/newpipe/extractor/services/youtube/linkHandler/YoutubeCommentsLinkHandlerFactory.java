package org.schabi.newpipe.extractor.services.youtube.linkHandler;

import java.util.List;
import org.schabi.newpipe.extractor.exceptions.FoundAdException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory;

/* JADX INFO: loaded from: classes8.dex */
public final class YoutubeCommentsLinkHandlerFactory extends ListLinkHandlerFactory {
    private static final YoutubeCommentsLinkHandlerFactory INSTANCE = new YoutubeCommentsLinkHandlerFactory();

    private YoutubeCommentsLinkHandlerFactory() {
    }

    public static YoutubeCommentsLinkHandlerFactory getInstance() {
        return INSTANCE;
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory, org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public String getUrl(String str) throws UnsupportedOperationException, ParsingException {
        return "https://www.youtube.com/watch?v=" + str;
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public String getId(String str) throws UnsupportedOperationException, ParsingException {
        return YoutubeStreamLinkHandlerFactory.getInstance().getId(str);
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public boolean onAcceptUrl(String str) throws FoundAdException {
        try {
            getId(str);
            return true;
        } catch (FoundAdException e) {
            throw e;
        } catch (ParsingException unused) {
            return false;
        }
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String getUrl(String str, List<String> list, String str2) throws UnsupportedOperationException, ParsingException {
        return getUrl(str);
    }
}
