package org.schabi.newpipe.extractor.services.bandcamp.linkHandler;

import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory;
import org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampExtractorHelper;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public final class BandcampStreamLinkHandlerFactory extends LinkHandlerFactory {
    private static final BandcampStreamLinkHandlerFactory INSTANCE = new BandcampStreamLinkHandlerFactory();

    private BandcampStreamLinkHandlerFactory() {
    }

    public static BandcampStreamLinkHandlerFactory getInstance() {
        return INSTANCE;
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public String getId(String str) throws UnsupportedOperationException, ParsingException {
        if (BandcampExtractorHelper.isRadioUrl(str)) {
            return str.split("bandcamp.com/\\?show=")[1];
        }
        return getUrl(str);
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public String getUrl(String str) throws UnsupportedOperationException, ParsingException {
        if (str.matches("\\d+")) {
            return "https://bandcamp.com/?show=" + str;
        }
        return Utils.replaceHttpWithHttps(str);
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public boolean onAcceptUrl(String str) throws ParsingException {
        if (BandcampExtractorHelper.isRadioUrl(str)) {
            return true;
        }
        if (str.toLowerCase().matches("https?://.+\\..+/track/.+")) {
            return BandcampExtractorHelper.isArtistDomain(str);
        }
        return false;
    }
}
