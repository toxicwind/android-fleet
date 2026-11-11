package org.schabi.newpipe.extractor.search;

import java.util.List;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.MetaInfo;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.SearchQueryHandler;

/* JADX INFO: loaded from: classes8.dex */
public abstract class SearchExtractor extends ListExtractor<InfoItem> {
    @Nonnull
    public abstract List<MetaInfo> getMetaInfo() throws ParsingException;

    @Nonnull
    public abstract String getSearchSuggestion() throws ParsingException;

    public abstract boolean isCorrectedSearch() throws ParsingException;

    public static class NothingFoundException extends ExtractionException {
        public NothingFoundException(String str) {
            super(str);
        }
    }

    public SearchExtractor(StreamingService streamingService, SearchQueryHandler searchQueryHandler) {
        super(streamingService, searchQueryHandler);
    }

    public String getSearchString() {
        return getLinkHandler().getSearchString();
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor, org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public SearchQueryHandler getLinkHandler() {
        return (SearchQueryHandler) super.getLinkHandler();
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getName() {
        return getLinkHandler().getSearchString();
    }
}
