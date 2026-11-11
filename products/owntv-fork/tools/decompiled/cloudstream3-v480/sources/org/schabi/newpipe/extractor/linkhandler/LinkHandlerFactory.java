package org.schabi.newpipe.extractor.linkhandler;

import j$.util.Objects;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public abstract class LinkHandlerFactory {
    public abstract String getId(String str) throws UnsupportedOperationException, ParsingException;

    public abstract String getUrl(String str) throws UnsupportedOperationException, ParsingException;

    public abstract boolean onAcceptUrl(String str) throws ParsingException;

    public String getUrl(String str, String str2) throws UnsupportedOperationException, ParsingException {
        return getUrl(str);
    }

    public LinkHandler fromUrl(String str) throws ParsingException {
        if (Utils.isNullOrEmpty(str)) {
            throw new IllegalArgumentException("The url is null or empty");
        }
        String strFollowGoogleRedirectIfNeeded = Utils.followGoogleRedirectIfNeeded(str);
        return fromUrl(strFollowGoogleRedirectIfNeeded, Utils.getBaseUrl(strFollowGoogleRedirectIfNeeded));
    }

    public LinkHandler fromUrl(String str, String str2) throws ParsingException {
        Objects.requireNonNull(str, "URL cannot be null");
        if (!acceptUrl(str)) {
            throw new ParsingException("URL not accepted: " + str);
        }
        String id = getId(str);
        return new LinkHandler(str, getUrl(id, str2), id);
    }

    public LinkHandler fromId(String str) throws ParsingException {
        Objects.requireNonNull(str, "ID cannot be null");
        String url = getUrl(str);
        return new LinkHandler(url, url, str);
    }

    public LinkHandler fromId(String str, String str2) throws ParsingException {
        Objects.requireNonNull(str, "ID cannot be null");
        String url = getUrl(str, str2);
        return new LinkHandler(url, url, str);
    }

    public boolean acceptUrl(String str) throws ParsingException {
        return onAcceptUrl(str);
    }
}
