package org.schabi.newpipe.extractor.services.bandcamp.linkHandler;

import java.util.List;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.SearchQueryHandlerFactory;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public final class BandcampSearchQueryHandlerFactory extends SearchQueryHandlerFactory {
    private static final BandcampSearchQueryHandlerFactory INSTANCE = new BandcampSearchQueryHandlerFactory();

    private BandcampSearchQueryHandlerFactory() {
    }

    public static BandcampSearchQueryHandlerFactory getInstance() {
        return INSTANCE;
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.SearchQueryHandlerFactory, org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public String getUrl(String str, List<String> list, String str2) throws UnsupportedOperationException, ParsingException {
        return "https://bandcamp.com/search?q=" + Utils.encodeUrlUtf8(str) + "&page=1";
    }
}
