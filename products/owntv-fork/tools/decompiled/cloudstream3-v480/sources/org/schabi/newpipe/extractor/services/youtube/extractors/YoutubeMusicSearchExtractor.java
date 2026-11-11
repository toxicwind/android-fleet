package org.schabi.newpipe.extractor.services.youtube.extractors;

import com.google.android.gms.actions.SearchIntents;
import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import com.grack.nanojson.JsonStringWriter;
import com.grack.nanojson.JsonWriter;
import j$.util.Collection;
import j$.util.function.Consumer$CC;
import j$.util.function.Function$CC;
import j$.util.function.Predicate$CC;
import j$.util.stream.Collectors;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
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
import org.schabi.newpipe.extractor.search.SearchExtractor;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;
import org.schabi.newpipe.extractor.services.youtube.linkHandler.YoutubeSearchQueryHandlerFactory;
import org.schabi.newpipe.extractor.utils.JsonUtils;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class YoutubeMusicSearchExtractor extends SearchExtractor {
    private List<JsonObject> cachedItemSectionRendererContents;
    private JsonObject initialData;

    public YoutubeMusicSearchExtractor(StreamingService streamingService, SearchQueryHandler searchQueryHandler) {
        super(streamingService, searchQueryHandler);
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        String str;
        String str2 = getLinkHandler().getContentFilters().get(0);
        str2.hashCode();
        switch (str2) {
            case "music_playlists":
                str = "Eg-KAQwIABAAGAAgACgBMABqChAEEAUQAxAKEAk%3D";
                break;
            case "music_artists":
                str = "Eg-KAQwIABAAGAAgASgAMABqChAEEAUQAxAKEAk%3D";
                break;
            case "music_albums":
                str = "Eg-KAQwIABAAGAEgACgAMABqChAEEAUQAxAKEAk%3D";
                break;
            case "music_songs":
                str = "Eg-KAQwIARAAGAAgACgAMABqChAEEAUQAxAKEAk%3D";
                break;
            case "music_videos":
                str = "Eg-KAQwIABABGAAgACgAMABqChAEEAUQAxAKEAk%3D";
                break;
            default:
                str = null;
                break;
        }
        try {
            this.initialData = JsonParser.object().from(YoutubeParsingHelper.getValidJsonResponseBody(getDownloader().postWithContentTypeJson("https://music.youtube.com/youtubei/v1/search?prettyPrint=false", YoutubeParsingHelper.getYoutubeMusicHeaders(), ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) JsonWriter.string().object()).object("context")).object("client")).value("clientName", "WEB_REMIX")).value("clientVersion", YoutubeParsingHelper.getYoutubeMusicClientVersion())).value("hl", "en-GB")).value("gl", getExtractorContentCountry().getCountryCode())).value("platform", "DESKTOP")).value("utcOffsetMinutes", 0)).end()).object("request")).array("internalExperimentFlags")).end()).value("useSsl", true)).end()).object("user")).value("lockedSafetyMode", false)).end()).end()).value(SearchIntents.EXTRA_QUERY, getSearchString())).value("params", str)).end()).done().getBytes(StandardCharsets.UTF_8))));
        } catch (JsonParserException e) {
            throw new ParsingException("Could not parse JSON", e);
        }
    }

    private List<JsonObject> getItemSectionRendererContents() {
        List<JsonObject> list = this.cachedItemSectionRendererContents;
        if (list != null) {
            return list;
        }
        List<JsonObject> list2 = (List) this.initialData.getObject("contents").getObject("tabbedSearchResultsRenderer").getArray("tabs").getObject(0).getObject("tabRenderer").getObject("content").getObject("sectionListRenderer").getArray("contents").streamAsJsonObjects().map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeMusicSearchExtractor$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getObject("itemSectionRenderer");
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeMusicSearchExtractor$$ExternalSyntheticLambda4
            public /* synthetic */ Predicate and(Predicate predicate) {
                return Predicate$CC.$default$and(this, predicate);
            }

            public /* synthetic */ Predicate negate() {
                return Predicate$CC.$default$negate(this);
            }

            public /* synthetic */ Predicate or(Predicate predicate) {
                return Predicate$CC.$default$or(this, predicate);
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return YoutubeMusicSearchExtractor.lambda$getItemSectionRendererContents$1((JsonObject) obj);
            }
        }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeMusicSearchExtractor$$ExternalSyntheticLambda5
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getArray("contents").getObject(0);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).collect(Collectors.toList());
        this.cachedItemSectionRendererContents = list2;
        return list2;
    }

    static /* synthetic */ boolean lambda$getItemSectionRendererContents$1(JsonObject jsonObject) {
        return !jsonObject.isEmpty();
    }

    @Override // org.schabi.newpipe.extractor.search.SearchExtractor
    @Nonnull
    public String getSearchSuggestion() throws ParsingException {
        for (JsonObject jsonObject : getItemSectionRendererContents()) {
            JsonObject object = jsonObject.getObject("didYouMeanRenderer");
            if (!object.isEmpty()) {
                return YoutubeParsingHelper.getTextFromObject(object.getObject("correctedQuery"));
            }
            JsonObject object2 = jsonObject.getObject("showingResultsForRenderer");
            if (!object2.isEmpty()) {
                return JsonUtils.getString(object2, "correctedQueryEndpoint.searchEndpoint.query");
            }
        }
        return "";
    }

    @Override // org.schabi.newpipe.extractor.search.SearchExtractor
    public boolean isCorrectedSearch() throws ParsingException {
        return Collection.EL.stream(getItemSectionRendererContents()).anyMatch(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeMusicSearchExtractor$$ExternalSyntheticLambda0
            public /* synthetic */ Predicate and(Predicate predicate) {
                return Predicate$CC.$default$and(this, predicate);
            }

            public /* synthetic */ Predicate negate() {
                return Predicate$CC.$default$negate(this);
            }

            public /* synthetic */ Predicate or(Predicate predicate) {
                return Predicate$CC.$default$or(this, predicate);
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((JsonObject) obj).has("showingResultsForRenderer");
            }
        });
    }

    @Override // org.schabi.newpipe.extractor.search.SearchExtractor
    @Nonnull
    public List<MetaInfo> getMetaInfo() {
        return Collections.EMPTY_LIST;
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    @Nonnull
    public ListExtractor.InfoItemsPage<InfoItem> getInitialPage() throws IOException, ExtractionException {
        MultiInfoItemsCollector multiInfoItemsCollector = new MultiInfoItemsCollector(getServiceId());
        Page nextPageFrom = null;
        for (JsonObject jsonObject : JsonUtils.getArray(JsonUtils.getArray(this.initialData, "contents.tabbedSearchResultsRenderer.tabs").getObject(0), "tabRenderer.content.sectionListRenderer.contents")) {
            if (jsonObject.has("musicShelfRenderer")) {
                JsonObject object = jsonObject.getObject("musicShelfRenderer");
                collectMusicStreamsFrom(multiInfoItemsCollector, object.getArray("contents"));
                nextPageFrom = getNextPageFrom(object.getArray("continuations"));
            }
        }
        return new ListExtractor.InfoItemsPage<>(multiInfoItemsCollector, nextPageFrom);
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    public ListExtractor.InfoItemsPage<InfoItem> getPage(Page page) throws IOException, ExtractionException {
        if (page == null || Utils.isNullOrEmpty(page.getUrl())) {
            throw new IllegalArgumentException("Page doesn't contain an URL");
        }
        MultiInfoItemsCollector multiInfoItemsCollector = new MultiInfoItemsCollector(getServiceId());
        try {
            JsonObject object = JsonParser.object().from(YoutubeParsingHelper.getValidJsonResponseBody(getDownloader().postWithContentTypeJson(page.getUrl(), YoutubeParsingHelper.getYoutubeMusicHeaders(), ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) JsonWriter.string().object()).object("context")).object("client")).value("clientName", "WEB_REMIX")).value("clientVersion", YoutubeParsingHelper.getYoutubeMusicClientVersion())).value("hl", "en-GB")).value("gl", getExtractorContentCountry().getCountryCode())).value("platform", "DESKTOP")).value("utcOffsetMinutes", 0)).end()).object("request")).array("internalExperimentFlags")).end()).value("useSsl", true)).end()).object("user")).value("lockedSafetyMode", false)).end()).end()).end()).done().getBytes(StandardCharsets.UTF_8)))).getObject("continuationContents").getObject("musicShelfContinuation");
            collectMusicStreamsFrom(multiInfoItemsCollector, object.getArray("contents"));
            return new ListExtractor.InfoItemsPage<>(multiInfoItemsCollector, getNextPageFrom(object.getArray("continuations")));
        } catch (JsonParserException e) {
            throw new ParsingException("Could not parse JSON", e);
        }
    }

    private void collectMusicStreamsFrom(final MultiInfoItemsCollector multiInfoItemsCollector, @Nonnull JsonArray jsonArray) {
        final String str = getLinkHandler().getContentFilters().get(0);
        jsonArray.streamAsJsonObjects().map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeMusicSearchExtractor$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getObject("musicResponsiveListItemRenderer", null);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).filter(new YoutubeCommentsEUVMInfoItemExtractor$$ExternalSyntheticLambda1()).forEachOrdered(new Consumer() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeMusicSearchExtractor$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                YoutubeMusicSearchExtractor.lambda$collectMusicStreamsFrom$5(str, multiInfoItemsCollector, (JsonObject) obj);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        });
    }

    static /* synthetic */ void lambda$collectMusicStreamsFrom$5(String str, MultiInfoItemsCollector multiInfoItemsCollector, JsonObject jsonObject) {
        if (jsonObject.getString("musicItemRendererDisplayPolicy", "").equals("MUSIC_ITEM_RENDERER_DISPLAY_POLICY_GREY_OUT")) {
            return;
        }
        byte b = 1;
        JsonArray array = jsonObject.getArray("flexColumns").getObject(1).getObject("musicResponsiveListItemFlexColumnRenderer").getObject("text").getArray("runs");
        str.hashCode();
        switch (str.hashCode()) {
            case -1778518201:
                b = !str.equals(YoutubeSearchQueryHandlerFactory.MUSIC_PLAYLISTS) ? (byte) -1 : (byte) 0;
                break;
            case -566908430:
                if (!str.equals(YoutubeSearchQueryHandlerFactory.MUSIC_ARTISTS)) {
                    b = -1;
                }
                break;
            case 1499667262:
                b = !str.equals(YoutubeSearchQueryHandlerFactory.MUSIC_ALBUMS) ? (byte) -1 : (byte) 2;
                break;
            case 1589120868:
                b = !str.equals(YoutubeSearchQueryHandlerFactory.MUSIC_SONGS) ? (byte) -1 : (byte) 3;
                break;
            case 2098153138:
                b = !str.equals(YoutubeSearchQueryHandlerFactory.MUSIC_VIDEOS) ? (byte) -1 : (byte) 4;
                break;
            default:
                b = -1;
                break;
        }
        switch (b) {
            case 0:
            case 2:
                multiInfoItemsCollector.commit(new YoutubeMusicAlbumOrPlaylistInfoItemExtractor(jsonObject, array, str));
                break;
            case 1:
                multiInfoItemsCollector.commit(new YoutubeMusicArtistInfoItemExtractor(jsonObject));
                break;
            case 3:
            case 4:
                multiInfoItemsCollector.commit(new YoutubeMusicSongOrVideoInfoItemExtractor(jsonObject, array, str));
                break;
        }
    }

    @Nullable
    private Page getNextPageFrom(JsonArray jsonArray) {
        if (Utils.isNullOrEmpty(jsonArray)) {
            return null;
        }
        String string = jsonArray.getObject(0).getObject("nextContinuationData").getString("continuation");
        return new Page("https://music.youtube.com/youtubei/v1/search?ctoken=" + string + "&continuation=" + string + "&prettyPrint=false");
    }
}
