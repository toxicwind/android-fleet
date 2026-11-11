package org.schabi.newpipe.extractor.services.youtube.extractors;

import com.google.android.gms.actions.SearchIntents;
import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonBuilder;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonWriter;
import j$.util.Objects;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.MetaInfo;
import org.schabi.newpipe.extractor.MultiInfoItemsCollector;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.SearchQueryHandler;
import org.schabi.newpipe.extractor.localization.Localization;
import org.schabi.newpipe.extractor.localization.TimeAgoParser;
import org.schabi.newpipe.extractor.search.SearchExtractor;
import org.schabi.newpipe.extractor.services.youtube.YoutubeMetaInfoHelper;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;
import org.schabi.newpipe.extractor.services.youtube.linkHandler.YoutubeSearchQueryHandlerFactory;
import org.schabi.newpipe.extractor.utils.JsonUtils;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class YoutubeSearchExtractor extends SearchExtractor {
    private final boolean extractChannelResults;
    private final boolean extractPlaylistResults;
    private final boolean extractVideoResults;
    private JsonObject initialData;

    @Nullable
    private final String searchType;

    public YoutubeSearchExtractor(StreamingService streamingService, SearchQueryHandler searchQueryHandler) {
        super(streamingService, searchQueryHandler);
        List<String> contentFilters = searchQueryHandler.getContentFilters();
        String str = Utils.isNullOrEmpty(contentFilters) ? null : contentFilters.get(0);
        this.searchType = str;
        this.extractVideoResults = str == null || "all".equals(str) || "videos".equals(str);
        this.extractChannelResults = str == null || "all".equals(str) || "channels".equals(str);
        this.extractPlaylistResults = str == null || "all".equals(str) || "playlists".equals(str);
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        String searchString = super.getSearchString();
        Localization extractorLocalization = getExtractorLocalization();
        String searchParameter = YoutubeSearchQueryHandlerFactory.getSearchParameter(this.searchType);
        JsonBuilder<JsonObject> jsonBuilderValue = YoutubeParsingHelper.prepareDesktopJsonBuilder(extractorLocalization, getExtractorContentCountry()).value(SearchIntents.EXTRA_QUERY, searchString);
        if (!Utils.isNullOrEmpty(searchParameter)) {
            jsonBuilderValue.value("params", searchParameter);
        }
        this.initialData = YoutubeParsingHelper.getJsonPostResponse("search", JsonWriter.string(jsonBuilderValue.done()).getBytes(StandardCharsets.UTF_8), extractorLocalization);
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getUrl() throws ParsingException {
        return super.getUrl() + "&gl=" + getExtractorContentCountry().getCountryCode();
    }

    @Override // org.schabi.newpipe.extractor.search.SearchExtractor
    @Nonnull
    public String getSearchSuggestion() throws ParsingException {
        JsonObject object = this.initialData.getObject("contents").getObject("twoColumnSearchResultsRenderer").getObject("primaryContents").getObject("sectionListRenderer").getArray("contents").getObject(0).getObject("itemSectionRenderer");
        JsonObject object2 = object.getArray("contents").getObject(0).getObject("didYouMeanRenderer");
        if (!object2.isEmpty()) {
            return JsonUtils.getString(object2, "correctedQueryEndpoint.searchEndpoint.query");
        }
        return (String) Objects.requireNonNullElse(YoutubeParsingHelper.getTextFromObject(object.getArray("contents").getObject(0).getObject("showingResultsForRenderer").getObject("correctedQuery")), "");
    }

    @Override // org.schabi.newpipe.extractor.search.SearchExtractor
    public boolean isCorrectedSearch() {
        return !this.initialData.getObject("contents").getObject("twoColumnSearchResultsRenderer").getObject("primaryContents").getObject("sectionListRenderer").getArray("contents").getObject(0).getObject("itemSectionRenderer").getArray("contents").getObject(0).getObject("showingResultsForRenderer").isEmpty();
    }

    @Override // org.schabi.newpipe.extractor.search.SearchExtractor
    @Nonnull
    public List<MetaInfo> getMetaInfo() throws ParsingException {
        return YoutubeMetaInfoHelper.getMetaInfo(this.initialData.getObject("contents").getObject("twoColumnSearchResultsRenderer").getObject("primaryContents").getObject("sectionListRenderer").getArray("contents"));
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    @Nonnull
    public ListExtractor.InfoItemsPage<InfoItem> getInitialPage() throws IOException, ExtractionException {
        MultiInfoItemsCollector multiInfoItemsCollector = new MultiInfoItemsCollector(getServiceId());
        Page nextPageFrom = null;
        for (JsonObject jsonObject : this.initialData.getObject("contents").getObject("twoColumnSearchResultsRenderer").getObject("primaryContents").getObject("sectionListRenderer").getArray("contents")) {
            if (jsonObject.has("itemSectionRenderer")) {
                collectStreamsFrom(multiInfoItemsCollector, jsonObject.getObject("itemSectionRenderer").getArray("contents"));
            } else if (jsonObject.has("continuationItemRenderer")) {
                nextPageFrom = getNextPageFrom(jsonObject.getObject("continuationItemRenderer"));
            }
        }
        return new ListExtractor.InfoItemsPage<>(multiInfoItemsCollector, nextPageFrom);
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    public ListExtractor.InfoItemsPage<InfoItem> getPage(Page page) throws IOException, ExtractionException {
        if (page == null || Utils.isNullOrEmpty(page.getUrl())) {
            throw new IllegalArgumentException("Page doesn't contain an URL");
        }
        Localization extractorLocalization = getExtractorLocalization();
        MultiInfoItemsCollector multiInfoItemsCollector = new MultiInfoItemsCollector(getServiceId());
        JsonArray array = YoutubeParsingHelper.getJsonPostResponse("search", JsonWriter.string(YoutubeParsingHelper.prepareDesktopJsonBuilder(extractorLocalization, getExtractorContentCountry()).value("continuation", page.getId()).done()).getBytes(StandardCharsets.UTF_8), extractorLocalization).getArray("onResponseReceivedCommands").getObject(0).getObject("appendContinuationItemsAction").getArray("continuationItems");
        collectStreamsFrom(multiInfoItemsCollector, array.getObject(0).getObject("itemSectionRenderer").getArray("contents"));
        return new ListExtractor.InfoItemsPage<>(multiInfoItemsCollector, getNextPageFrom(array.getObject(1).getObject("continuationItemRenderer")));
    }

    private void collectStreamsFrom(MultiInfoItemsCollector multiInfoItemsCollector, @Nonnull JsonArray jsonArray) throws SearchExtractor.NothingFoundException {
        TimeAgoParser timeAgoParser = getTimeAgoParser();
        Iterator<Object> it = jsonArray.iterator();
        while (it.hasNext()) {
            JsonObject jsonObject = (JsonObject) it.next();
            if (jsonObject.has("backgroundPromoRenderer")) {
                throw new SearchExtractor.NothingFoundException(YoutubeParsingHelper.getTextFromObject(jsonObject.getObject("backgroundPromoRenderer").getObject("bodyText")));
            }
            if (jsonObject.has("videoRenderer") && this.extractVideoResults) {
                multiInfoItemsCollector.commit(new YoutubeStreamInfoItemExtractor(jsonObject.getObject("videoRenderer"), timeAgoParser));
            } else if (jsonObject.has("channelRenderer") && this.extractChannelResults) {
                multiInfoItemsCollector.commit(new YoutubeChannelInfoItemExtractor(jsonObject.getObject("channelRenderer")));
            } else if (jsonObject.has("playlistRenderer") && this.extractPlaylistResults) {
                multiInfoItemsCollector.commit(new YoutubePlaylistInfoItemExtractor(jsonObject.getObject("playlistRenderer")));
            } else if (jsonObject.has("showRenderer") && this.extractPlaylistResults) {
                multiInfoItemsCollector.commit(new YoutubeShowRendererInfoItemExtractor(jsonObject.getObject("showRenderer")));
            } else if (jsonObject.has("lockupViewModel")) {
                JsonObject object = jsonObject.getObject("lockupViewModel");
                String string = object.getString("contentType");
                if (("LOCKUP_CONTENT_TYPE_PLAYLIST".equals(string) || "LOCKUP_CONTENT_TYPE_PODCAST".equals(string)) && this.extractPlaylistResults) {
                    multiInfoItemsCollector.commit(new YoutubeMixOrPlaylistLockupInfoItemExtractor(object));
                } else if ("LOCKUP_CONTENT_TYPE_VIDEO".equals(string) && this.extractVideoResults) {
                    multiInfoItemsCollector.commit(new YoutubeStreamInfoItemLockupExtractor(object, timeAgoParser));
                }
            }
        }
    }

    @Nullable
    private Page getNextPageFrom(JsonObject jsonObject) {
        if (Utils.isNullOrEmpty(jsonObject)) {
            return null;
        }
        return new Page("https://www.youtube.com/youtubei/v1/search?prettyPrint=false", jsonObject.getObject("continuationEndpoint").getObject("continuationCommand").getString("token"));
    }
}
