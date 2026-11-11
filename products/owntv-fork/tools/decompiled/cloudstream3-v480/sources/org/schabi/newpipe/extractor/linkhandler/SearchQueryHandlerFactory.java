package org.schabi.newpipe.extractor.linkhandler;

import java.util.Collections;
import java.util.List;
import org.schabi.newpipe.extractor.exceptions.ParsingException;

/* JADX INFO: loaded from: classes8.dex */
public abstract class SearchQueryHandlerFactory extends ListLinkHandlerFactory {
    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public abstract String getUrl(String str, List<String> list, String str2) throws UnsupportedOperationException, ParsingException;

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public boolean onAcceptUrl(String str) {
        return false;
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public /* bridge */ /* synthetic */ ListLinkHandler fromQuery(String str, List list, String str2) throws ParsingException {
        return fromQuery(str, (List<String>) list, str2);
    }

    public String getSearchString(String str) {
        return "";
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory
    public String getId(String str) throws UnsupportedOperationException, ParsingException {
        return getSearchString(str);
    }

    @Override // org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory
    public SearchQueryHandler fromQuery(String str, List<String> list, String str2) throws ParsingException {
        return new SearchQueryHandler(super.fromQuery(str, list, str2));
    }

    public SearchQueryHandler fromQuery(String str) throws ParsingException {
        return fromQuery(str, Collections.EMPTY_LIST, "");
    }
}
