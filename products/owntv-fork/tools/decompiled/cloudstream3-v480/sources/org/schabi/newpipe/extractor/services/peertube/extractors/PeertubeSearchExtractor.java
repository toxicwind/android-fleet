package org.schabi.newpipe.extractor.services.peertube.extractors;

import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.MetaInfo;
import org.schabi.newpipe.extractor.MultiInfoItemsCollector;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.downloader.Response;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.SearchQueryHandler;
import org.schabi.newpipe.extractor.search.SearchExtractor;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class PeertubeSearchExtractor extends SearchExtractor {
    private final boolean sepia;

    @Override // org.schabi.newpipe.extractor.search.SearchExtractor
    public boolean isCorrectedSearch() {
        return false;
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
    }

    public PeertubeSearchExtractor(StreamingService streamingService, SearchQueryHandler searchQueryHandler) {
        this(streamingService, searchQueryHandler, false);
    }

    public PeertubeSearchExtractor(StreamingService streamingService, SearchQueryHandler searchQueryHandler, boolean z) {
        super(streamingService, searchQueryHandler);
        this.sepia = z;
    }

    @Override // org.schabi.newpipe.extractor.search.SearchExtractor
    @Nonnull
    public String getSearchSuggestion() {
        return "";
    }

    @Override // org.schabi.newpipe.extractor.search.SearchExtractor
    @Nonnull
    public List<MetaInfo> getMetaInfo() {
        return Collections.EMPTY_LIST;
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    @Nonnull
    public ListExtractor.InfoItemsPage<InfoItem> getInitialPage() throws IOException, ExtractionException {
        return getPage(new Page(getUrl() + "&start=0&count=12"));
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    public ListExtractor.InfoItemsPage<InfoItem> getPage(Page page) throws IOException, ExtractionException {
        JsonObject jsonObjectFrom;
        if (page == null || Utils.isNullOrEmpty(page.getUrl())) {
            throw new IllegalArgumentException("Page doesn't contain an URL");
        }
        Response response = getDownloader().get(page.getUrl());
        if (response == null || Utils.isBlank(response.responseBody())) {
            jsonObjectFrom = null;
        } else {
            try {
                jsonObjectFrom = JsonParser.object().from(response.responseBody());
            } catch (Exception e) {
                throw new ParsingException("Could not parse json data for search info", e);
            }
        }
        if (jsonObjectFrom != null) {
            PeertubeParsingHelper.validate(jsonObjectFrom);
            long j = jsonObjectFrom.getLong("total");
            MultiInfoItemsCollector multiInfoItemsCollector = new MultiInfoItemsCollector(getServiceId());
            PeertubeParsingHelper.collectItemsFrom(multiInfoItemsCollector, jsonObjectFrom, getBaseUrl(), this.sepia);
            return new ListExtractor.InfoItemsPage<>(multiInfoItemsCollector, PeertubeParsingHelper.getNextPage(page.getUrl(), j));
        }
        throw new ExtractionException("Unable to get PeerTube search info");
    }
}
