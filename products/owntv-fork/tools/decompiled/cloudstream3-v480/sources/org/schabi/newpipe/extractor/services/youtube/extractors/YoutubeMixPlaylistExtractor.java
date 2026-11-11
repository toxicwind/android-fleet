package org.schabi.newpipe.extractor.services.youtube.extractors;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonBuilder;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonWriter;
import j$.util.Collection;
import j$.util.Objects;
import j$.util.function.Consumer$CC;
import j$.util.function.Function$CC;
import j$.util.stream.Collectors;
import j$.util.stream.Stream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import kotlin.UByte$$ExternalSyntheticBackport0;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.downloader.Response;
import org.schabi.newpipe.extractor.exceptions.ContentNotAvailableException;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.localization.Localization;
import org.schabi.newpipe.extractor.localization.TimeAgoParser;
import org.schabi.newpipe.extractor.playlist.PlaylistExtractor;
import org.schabi.newpipe.extractor.playlist.PlaylistInfo;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;
import org.schabi.newpipe.extractor.stream.Description;
import org.schabi.newpipe.extractor.stream.StreamInfoItem;
import org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor;
import org.schabi.newpipe.extractor.stream.StreamInfoItemsCollector;
import org.schabi.newpipe.extractor.utils.ImageSuffix;
import org.schabi.newpipe.extractor.utils.JsonUtils;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class YoutubeMixPlaylistExtractor extends PlaylistExtractor {
    public static final String COOKIE_NAME = "VISITOR_INFO1_LIVE";
    private static final List<ImageSuffix> IMAGE_URL_SUFFIXES_AND_RESOLUTIONS = UByte$$ExternalSyntheticBackport0.m(new Object[]{new ImageSuffix("default.jpg", 90, 120, Image.ResolutionLevel.LOW), new ImageSuffix("mqdefault.jpg", 180, 320, Image.ResolutionLevel.MEDIUM), new ImageSuffix("hqdefault.jpg", 360, 480, Image.ResolutionLevel.MEDIUM)});
    private String cookieValue;
    private JsonObject initialData;
    private JsonObject playlistData;

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    public long getStreamCount() {
        return -2L;
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    public boolean isUploaderVerified() throws ParsingException {
        return false;
    }

    public YoutubeMixPlaylistExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler) {
        super(streamingService, listLinkHandler);
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        Localization extractorLocalization = getExtractorLocalization();
        URL urlStringToURL = Utils.stringToURL(getUrl());
        String id = getId();
        String queryValue = Utils.getQueryValue(urlStringToURL, "v");
        String queryValue2 = Utils.getQueryValue(urlStringToURL, "index");
        JsonBuilder<JsonObject> jsonBuilderValue = YoutubeParsingHelper.prepareDesktopJsonBuilder(extractorLocalization, getExtractorContentCountry()).value("playlistId", id);
        if (queryValue != null) {
            jsonBuilderValue.value(YoutubeParsingHelper.VIDEO_ID, queryValue);
        }
        if (queryValue2 != null) {
            jsonBuilderValue.value("playlistIndex", Integer.parseInt(queryValue2));
        }
        byte[] bytes = JsonWriter.string(jsonBuilderValue.done()).getBytes(StandardCharsets.UTF_8);
        Response responsePostWithContentTypeJson = getDownloader().postWithContentTypeJson("https://www.youtube.com/youtubei/v1/next?prettyPrint=false", YoutubeParsingHelper.getYouTubeHeaders(), bytes, extractorLocalization);
        JsonObject jsonObject = JsonUtils.toJsonObject(YoutubeParsingHelper.getValidJsonResponseBody(responsePostWithContentTypeJson));
        this.initialData = jsonObject;
        JsonObject object = jsonObject.getObject("contents").getObject("twoColumnWatchNextResults").getObject("playlist").getObject("playlist");
        this.playlistData = object;
        if (Utils.isNullOrEmpty(object)) {
            ExtractionException extractionException = new ExtractionException("Could not get playlistData");
            if (!YoutubeParsingHelper.isConsentAccepted()) {
                throw new ContentNotAvailableException("Consent is required in some countries to view Mix playlists", extractionException);
            }
            throw extractionException;
        }
        this.cookieValue = YoutubeParsingHelper.extractCookieValue(COOKIE_NAME, responsePostWithContentTypeJson);
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getName() throws ParsingException {
        String textAtKey = YoutubeParsingHelper.getTextAtKey(this.playlistData, "title");
        if (Utils.isNullOrEmpty(textAtKey)) {
            throw new ParsingException("Could not get playlist name");
        }
        return textAtKey;
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        try {
            return getThumbnailsFromPlaylistId(this.playlistData.getString("playlistId"));
        } catch (Exception e) {
            try {
                return getThumbnailsFromVideoId(this.initialData.getObject("currentVideoEndpoint").getObject("watchEndpoint").getString(YoutubeParsingHelper.VIDEO_ID));
            } catch (Exception unused) {
                throw new ParsingException("Could not get playlist thumbnails", e);
            }
        }
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    public String getUploaderUrl() {
        return "";
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    public String getUploaderName() {
        return "YouTube";
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    @Nonnull
    public List<Image> getUploaderAvatars() {
        return Collections.EMPTY_LIST;
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    @Nonnull
    public Description getDescription() throws ParsingException {
        return Description.EMPTY_DESCRIPTION;
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    @Nonnull
    public ListExtractor.InfoItemsPage<StreamInfoItem> getInitialPage() throws IOException, ExtractionException {
        StreamInfoItemsCollector streamInfoItemsCollector = new StreamInfoItemsCollector(getServiceId());
        collectStreamsFrom(streamInfoItemsCollector, this.playlistData.getArray("contents"));
        HashMap map = new HashMap();
        map.put(COOKIE_NAME, this.cookieValue);
        return new ListExtractor.InfoItemsPage<>(streamInfoItemsCollector, getNextPageFrom(this.playlistData, map));
    }

    @Nonnull
    private Page getNextPageFrom(@Nonnull JsonObject jsonObject, Map<String, String> map) throws IOException, ExtractionException {
        JsonObject jsonObject2 = (JsonObject) jsonObject.getArray("contents").get(jsonObject.getArray("contents").size() - 1);
        if (jsonObject2 == null || jsonObject2.getObject("playlistPanelVideoRenderer") == null) {
            throw new ExtractionException("Could not extract next page url");
        }
        JsonObject object = jsonObject2.getObject("playlistPanelVideoRenderer").getObject("navigationEndpoint").getObject("watchEndpoint");
        String string = object.getString("playlistId");
        String string2 = object.getString(YoutubeParsingHelper.VIDEO_ID);
        return new Page("https://www.youtube.com/youtubei/v1/next?prettyPrint=false", null, null, map, JsonWriter.string(YoutubeParsingHelper.prepareDesktopJsonBuilder(getExtractorLocalization(), getExtractorContentCountry()).value(YoutubeParsingHelper.VIDEO_ID, string2).value("playlistId", string).value("playlistIndex", object.getInt("index")).value("params", object.getString("params")).done()).getBytes(StandardCharsets.UTF_8));
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    public ListExtractor.InfoItemsPage<StreamInfoItem> getPage(Page page) throws IOException, ExtractionException {
        if (page == null || Utils.isNullOrEmpty(page.getUrl())) {
            throw new IllegalArgumentException("Page doesn't contain an URL");
        }
        if (!page.getCookies().containsKey(COOKIE_NAME)) {
            throw new IllegalArgumentException("Cookie 'VISITOR_INFO1_LIVE' is missing");
        }
        StreamInfoItemsCollector streamInfoItemsCollector = new StreamInfoItemsCollector(getServiceId());
        JsonObject object = JsonUtils.toJsonObject(YoutubeParsingHelper.getValidJsonResponseBody(getDownloader().postWithContentTypeJson(page.getUrl(), YoutubeParsingHelper.getYouTubeHeaders(), page.getBody(), getExtractorLocalization()))).getObject("contents").getObject("twoColumnWatchNextResults").getObject("playlist").getObject("playlist");
        JsonArray array = object.getArray("contents");
        collectStreamsFrom(streamInfoItemsCollector, array.subList(object.getInt("currentIndex") + 1, array.size()));
        return new ListExtractor.InfoItemsPage<>(streamInfoItemsCollector, getNextPageFrom(object, page.getCookies()));
    }

    private void collectStreamsFrom(@Nonnull final StreamInfoItemsCollector streamInfoItemsCollector, @Nullable List<Object> list) {
        if (list == null) {
            return;
        }
        final TimeAgoParser timeAgoParser = getTimeAgoParser();
        Stream<R> map = new JsonArray(list).streamAsJsonObjects().map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeMixPlaylistExtractor$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return YoutubeMixPlaylistExtractor.lambda$collectStreamsFrom$0(timeAgoParser, (JsonObject) obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        });
        Objects.requireNonNull(streamInfoItemsCollector);
        map.forEachOrdered(new Consumer() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeMixPlaylistExtractor$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                streamInfoItemsCollector.commit((StreamInfoItemExtractor) obj);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        });
    }

    static /* synthetic */ YoutubeStreamInfoItemExtractor lambda$collectStreamsFrom$0(TimeAgoParser timeAgoParser, JsonObject jsonObject) {
        return new YoutubeStreamInfoItemExtractor(jsonObject.getObject("playlistPanelVideoRenderer"), timeAgoParser);
    }

    @Nonnull
    private List<Image> getThumbnailsFromPlaylistId(@Nonnull String str) throws ParsingException {
        return getThumbnailsFromVideoId(YoutubeParsingHelper.extractVideoIdFromMixId(str));
    }

    @Nonnull
    private List<Image> getThumbnailsFromVideoId(@Nonnull String str) {
        final String str2 = "https://i.ytimg.com/vi/" + str + "/";
        return (List) Collection.EL.stream(IMAGE_URL_SUFFIXES_AND_RESOLUTIONS).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeMixPlaylistExtractor$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return YoutubeMixPlaylistExtractor.lambda$getThumbnailsFromVideoId$1(str2, (ImageSuffix) obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).collect(Collectors.toUnmodifiableList());
    }

    static /* synthetic */ Image lambda$getThumbnailsFromVideoId$1(String str, ImageSuffix imageSuffix) {
        return new Image(str + imageSuffix.getSuffix(), imageSuffix.getHeight(), imageSuffix.getWidth(), imageSuffix.getResolutionLevel());
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    @Nonnull
    public PlaylistInfo.PlaylistType getPlaylistType() throws ParsingException {
        return YoutubeParsingHelper.extractPlaylistTypeFromPlaylistId(this.playlistData.getString("playlistId"));
    }
}
