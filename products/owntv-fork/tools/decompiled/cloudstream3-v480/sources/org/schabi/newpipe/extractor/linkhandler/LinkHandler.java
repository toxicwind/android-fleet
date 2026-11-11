package org.schabi.newpipe.extractor.linkhandler;

import java.io.Serializable;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class LinkHandler implements Serializable {
    protected final String id;
    protected final String originalUrl;
    protected final String url;

    public LinkHandler(String str, String str2, String str3) {
        this.originalUrl = str;
        this.url = str2;
        this.id = str3;
    }

    public LinkHandler(LinkHandler linkHandler) {
        this(linkHandler.originalUrl, linkHandler.url, linkHandler.id);
    }

    public String getOriginalUrl() {
        return this.originalUrl;
    }

    public String getUrl() {
        return this.url;
    }

    public String getId() {
        return this.id;
    }

    public String getBaseUrl() throws ParsingException {
        return Utils.getBaseUrl(this.url);
    }
}
