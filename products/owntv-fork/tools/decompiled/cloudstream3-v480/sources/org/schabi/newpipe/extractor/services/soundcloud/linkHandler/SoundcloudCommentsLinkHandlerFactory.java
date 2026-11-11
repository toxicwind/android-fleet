package org.schabi.newpipe.extractor.services.soundcloud.linkHandler;

import java.io.IOException;
import java.util.List;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory;
import org.schabi.newpipe.extractor.services.soundcloud.SoundcloudParsingHelper;

/* JADX INFO: loaded from: classes8.dex */
public final class SoundcloudCommentsLinkHandlerFactory extends ListLinkHandlerFactory {
    private static final SoundcloudCommentsLinkHandlerFactory INSTANCE = new SoundcloudCommentsLinkHandlerFactory();

    private SoundcloudCommentsLinkHandlerFactory() {
    }

    public static SoundcloudCommentsLinkHandlerFactory getInstance() {
        return INSTANCE;
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String getUrl(String str, List<String> list, String str2) throws UnsupportedOperationException, ParsingException {
        try {
            return "https://api-v2.soundcloud.com/tracks/" + str + "/comments?client_id=" + SoundcloudParsingHelper.clientId() + "&threaded=0&filter_replies=1";
        } catch (IOException | ExtractionException unused) {
            throw new ParsingException("Could not get comments");
        }
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public String getId(String str) throws UnsupportedOperationException, ParsingException {
        return SoundcloudStreamLinkHandlerFactory.getInstance().getId(str);
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public boolean onAcceptUrl(String str) {
        try {
            getId(str);
            return true;
        } catch (ParsingException unused) {
            return false;
        }
    }
}
