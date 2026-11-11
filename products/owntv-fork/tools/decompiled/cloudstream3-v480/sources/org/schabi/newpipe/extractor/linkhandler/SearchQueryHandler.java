package org.schabi.newpipe.extractor.linkhandler;

import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public class SearchQueryHandler extends ListLinkHandler {
    public SearchQueryHandler(String str, String str2, String str3, List<String> list, String str4) {
        super(str, str2, str3, list, str4);
    }

    public SearchQueryHandler(ListLinkHandler listLinkHandler) {
        this(listLinkHandler.originalUrl, listLinkHandler.url, listLinkHandler.id, listLinkHandler.contentFilters, listLinkHandler.sortFilter);
    }

    public String getSearchString() {
        return getId();
    }
}
