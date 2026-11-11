package org.schabi.newpipe.extractor.linkhandler;

import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public abstract class ListLinkHandlerFactory extends LinkHandlerFactory {
    public abstract String getUrl(String str, List<String> list, String str2) throws UnsupportedOperationException, ParsingException;

    public String getUrl(String str, List<String> list, String str2, String str3) throws UnsupportedOperationException, ParsingException {
        return getUrl(str, list, str2);
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public ListLinkHandler fromUrl(String str) throws ParsingException {
        String strFollowGoogleRedirectIfNeeded = Utils.followGoogleRedirectIfNeeded(str);
        return fromUrl(strFollowGoogleRedirectIfNeeded, Utils.getBaseUrl(strFollowGoogleRedirectIfNeeded));
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public ListLinkHandler fromUrl(String str, String str2) throws ParsingException {
        Objects.requireNonNull(str, "URL may not be null");
        return new ListLinkHandler(super.fromUrl(str, str2));
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public ListLinkHandler fromId(String str) throws ParsingException {
        return new ListLinkHandler(super.fromId(str));
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public ListLinkHandler fromId(String str, String str2) throws ParsingException {
        return new ListLinkHandler(super.fromId(str, str2));
    }

    public ListLinkHandler fromQuery(String str, List<String> list, String str2) throws ParsingException {
        String url = getUrl(str, list, str2);
        return new ListLinkHandler(url, url, str, list, str2);
    }

    public ListLinkHandler fromQuery(String str, List<String> list, String str2, String str3) throws ParsingException {
        String url = getUrl(str, list, str2, str3);
        return new ListLinkHandler(url, url, str, list, str2);
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public String getUrl(String str) throws UnsupportedOperationException, ParsingException {
        return getUrl(str, new ArrayList(0), "");
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public String getUrl(String str, String str2) throws ParsingException {
        return getUrl(str, new ArrayList(0), "", str2);
    }

    public String[] getAvailableContentFilter() {
        return new String[0];
    }

    public String[] getAvailableSortFilter() {
        return new String[0];
    }
}
