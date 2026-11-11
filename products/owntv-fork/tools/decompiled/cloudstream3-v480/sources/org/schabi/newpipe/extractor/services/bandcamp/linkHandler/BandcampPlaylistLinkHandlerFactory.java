package org.schabi.newpipe.extractor.services.bandcamp.linkHandler;

import java.util.List;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory;
import org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampExtractorHelper;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public final class BandcampPlaylistLinkHandlerFactory extends ListLinkHandlerFactory {
    private static final BandcampPlaylistLinkHandlerFactory INSTANCE = new BandcampPlaylistLinkHandlerFactory();

    private BandcampPlaylistLinkHandlerFactory() {
    }

    public static BandcampPlaylistLinkHandlerFactory getInstance() {
        return INSTANCE;
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public String getId(String str) throws UnsupportedOperationException, ParsingException {
        return getUrl(str);
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String getUrl(String str, List<String> list, String str2) throws UnsupportedOperationException, ParsingException {
        return Utils.replaceHttpWithHttps(str);
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public boolean onAcceptUrl(String str) throws ParsingException {
        if (str.toLowerCase().matches("https?://.+\\..+/album/.+")) {
            return BandcampExtractorHelper.isArtistDomain(str);
        }
        return false;
    }
}
