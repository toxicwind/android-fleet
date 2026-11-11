package org.schabi.newpipe.extractor.linkhandler;

import j$.util.DesugarCollections;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public class ListLinkHandler extends LinkHandler {
    protected final List<String> contentFilters;
    protected final String sortFilter;

    public ListLinkHandler(String str, String str2, String str3, List<String> list, String str4) {
        super(str, str2, str3);
        this.contentFilters = DesugarCollections.unmodifiableList(list);
        this.sortFilter = str4;
    }

    public ListLinkHandler(ListLinkHandler listLinkHandler) {
        this(listLinkHandler.originalUrl, listLinkHandler.url, listLinkHandler.id, listLinkHandler.contentFilters, listLinkHandler.sortFilter);
    }

    public ListLinkHandler(LinkHandler linkHandler) {
        this(linkHandler.originalUrl, linkHandler.url, linkHandler.id, Collections.EMPTY_LIST, "");
    }

    public List<String> getContentFilters() {
        return this.contentFilters;
    }

    public String getSortFilter() {
        return this.sortFilter;
    }
}
