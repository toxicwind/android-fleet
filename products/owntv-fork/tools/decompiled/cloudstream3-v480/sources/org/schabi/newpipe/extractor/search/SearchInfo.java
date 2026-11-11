package org.schabi.newpipe.extractor.search;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.ListInfo;
import org.schabi.newpipe.extractor.MetaInfo;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.linkhandler.SearchQueryHandler;
import org.schabi.newpipe.extractor.utils.ExtractorHelper;

/* JADX INFO: loaded from: classes8.dex */
public class SearchInfo extends ListInfo<InfoItem> {
    private boolean isCorrectedSearch;
    private List<MetaInfo> metaInfo;
    private final String searchString;
    private String searchSuggestion;

    public SearchInfo(int i, SearchQueryHandler searchQueryHandler, String str) {
        super(i, searchQueryHandler, "Search");
        this.metaInfo = Collections.EMPTY_LIST;
        this.searchString = str;
    }

    public static SearchInfo getInfo(StreamingService streamingService, SearchQueryHandler searchQueryHandler) throws IOException, ExtractionException {
        SearchExtractor searchExtractor = streamingService.getSearchExtractor(searchQueryHandler);
        searchExtractor.fetchPage();
        return getInfo(searchExtractor);
    }

    public static SearchInfo getInfo(SearchExtractor searchExtractor) throws IOException, ExtractionException {
        SearchInfo searchInfo = new SearchInfo(searchExtractor.getServiceId(), searchExtractor.getLinkHandler(), searchExtractor.getSearchString());
        try {
            searchInfo.setOriginalUrl(searchExtractor.getOriginalUrl());
        } catch (Exception e) {
            searchInfo.addError(e);
        }
        try {
            searchInfo.setSearchSuggestion(searchExtractor.getSearchSuggestion());
        } catch (Exception e2) {
            searchInfo.addError(e2);
        }
        try {
            searchInfo.setIsCorrectedSearch(searchExtractor.isCorrectedSearch());
        } catch (Exception e3) {
            searchInfo.addError(e3);
        }
        try {
            searchInfo.setMetaInfo(searchExtractor.getMetaInfo());
        } catch (Exception e4) {
            searchInfo.addError(e4);
        }
        ListExtractor.InfoItemsPage itemsPageOrLogError = ExtractorHelper.getItemsPageOrLogError(searchInfo, searchExtractor);
        searchInfo.setRelatedItems(itemsPageOrLogError.getItems());
        searchInfo.setNextPage(itemsPageOrLogError.getNextPage());
        return searchInfo;
    }

    public static ListExtractor.InfoItemsPage<InfoItem> getMoreItems(StreamingService streamingService, SearchQueryHandler searchQueryHandler, Page page) throws IOException, ExtractionException {
        return streamingService.getSearchExtractor(searchQueryHandler).getPage(page);
    }

    public String getSearchString() {
        return this.searchString;
    }

    public String getSearchSuggestion() {
        return this.searchSuggestion;
    }

    public boolean isCorrectedSearch() {
        return this.isCorrectedSearch;
    }

    public void setIsCorrectedSearch(boolean z) {
        this.isCorrectedSearch = z;
    }

    public void setSearchSuggestion(String str) {
        this.searchSuggestion = str;
    }

    @Nonnull
    public List<MetaInfo> getMetaInfo() {
        return this.metaInfo;
    }

    public void setMetaInfo(@Nonnull List<MetaInfo> list) {
        this.metaInfo = list;
    }
}
