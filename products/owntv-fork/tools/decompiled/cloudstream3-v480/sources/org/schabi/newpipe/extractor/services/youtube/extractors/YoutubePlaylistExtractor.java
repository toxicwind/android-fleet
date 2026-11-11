package org.schabi.newpipe.extractor.services.youtube.extractors;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonWriter;
import j$.util.Base64;
import j$.util.function.Consumer$CC;
import j$.util.function.Function$CC;
import j$.util.function.Predicate$CC;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import kotlin.UByte$$ExternalSyntheticBackport0;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.localization.Localization;
import org.schabi.newpipe.extractor.localization.TimeAgoParser;
import org.schabi.newpipe.extractor.playlist.PlaylistExtractor;
import org.schabi.newpipe.extractor.playlist.PlaylistInfo;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;
import org.schabi.newpipe.extractor.services.youtube.protos.playlist.PlaylistProtobufContinuation;
import org.schabi.newpipe.extractor.stream.Description;
import org.schabi.newpipe.extractor.stream.StreamInfoItem;
import org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor;
import org.schabi.newpipe.extractor.stream.StreamInfoItemsCollector;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class YoutubePlaylistExtractor extends PlaylistExtractor {
    private static final String APPEND_CONTINUATION_ITEMS_ACTION = "appendContinuationItemsAction";
    private static final String BROWSE_ENDPOINT = "browse";
    private static final String COMMAND_EXECUTOR_COMMAND = "commandExecutorCommand";
    private static final String CONTINUATION_COMMAND = "continuationCommand";
    private static final String CONTINUATION_ITEMS = "continuationItems";
    private static final String HEADER = "header";
    private static final String LOCKUP_VIEW_MODEL = "lockupViewModel";
    private static final String MICROFORMAT = "microformat";
    private static final String ON_RESPONSE_RECEIVED_ACTIONS = "onResponseReceivedActions";
    private static final String PLAYLIST_CONTINUATION_PROPERTIES_BASE64 = "CADCBgIIAA%3D%3D";
    private static final String PLAYLIST_VIDEO_RENDERER = "playlistVideoRenderer";
    private static final String REEL_ITEM_RENDERER = "reelItemRenderer";
    private static final String RICH_ITEM_RENDERER = "richItemRenderer";
    private static final String SIDEBAR = "sidebar";
    private static final String THUMBNAIL = "thumbnail";
    private static final String THUMBNAILS = "thumbnails";
    private static final String TITLE = "title";
    private static final String VIDEO_OWNER_RENDERER = "videoOwnerRenderer";
    private JsonObject browseMetadataResponse;
    private JsonObject initialBrowseContinuationResponse;
    private Boolean isCoursePlaylist;
    private boolean isNewPlaylistInterface;
    private JsonObject playlistHeader;
    private JsonObject playlistInfo;
    private JsonObject uploaderInfo;

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    public boolean isUploaderVerified() throws ParsingException {
        return false;
    }

    public YoutubePlaylistExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler) {
        super(streamingService, listLinkHandler);
        this.isCoursePlaylist = null;
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        String id = getId();
        Localization extractorLocalization = getExtractorLocalization();
        JsonObject jsonPostResponse = YoutubeParsingHelper.getJsonPostResponse(BROWSE_ENDPOINT, UByte$$ExternalSyntheticBackport0.m(new Object[]{"$fields=sidebar,header,microformat,alerts"}), JsonWriter.string(YoutubeParsingHelper.prepareDesktopJsonBuilder(extractorLocalization, getExtractorContentCountry()).value("browseId", "VL" + id).value("params", "wgYCCAA%3D").done()).getBytes(StandardCharsets.UTF_8), extractorLocalization);
        this.browseMetadataResponse = jsonPostResponse;
        YoutubeParsingHelper.defaultAlertsCheck(jsonPostResponse);
        this.isNewPlaylistInterface = checkIfResponseIsNewPlaylistInterface();
        this.initialBrowseContinuationResponse = YoutubeParsingHelper.getJsonPostResponse(BROWSE_ENDPOINT, JsonWriter.string(YoutubeParsingHelper.prepareDesktopJsonBuilder(extractorLocalization, getExtractorContentCountry()).value("continuation", Utils.encodeUrlUtf8(Base64.getUrlEncoder().encodeToString(PlaylistProtobufContinuation.PlaylistContinuation.newBuilder().setParameters(PlaylistProtobufContinuation.ContinuationParams.newBuilder().setBrowseId("VL" + id).setPlaylistId(id).setContinuationProperties(PLAYLIST_CONTINUATION_PROPERTIES_BASE64).build()).build().toByteArray()))).done()).getBytes(StandardCharsets.UTF_8), extractorLocalization);
    }

    private boolean checkIfResponseIsNewPlaylistInterface() {
        return this.browseMetadataResponse.has(HEADER) && !this.browseMetadataResponse.has(SIDEBAR);
    }

    @Nonnull
    private JsonObject getUploaderInfo() throws ParsingException {
        if (this.uploaderInfo == null) {
            this.uploaderInfo = (JsonObject) this.browseMetadataResponse.getObject(SIDEBAR).getObject("playlistSidebarRenderer").getArray("items").streamAsJsonObjects().filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubePlaylistExtractor$$ExternalSyntheticLambda3
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
                    return ((JsonObject) obj).getObject("playlistSidebarSecondaryInfoRenderer").getObject("videoOwner").has(YoutubePlaylistExtractor.VIDEO_OWNER_RENDERER);
                }
            }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubePlaylistExtractor$$ExternalSyntheticLambda4
                @Override // java.util.function.Function
                public /* synthetic */ Function andThen(Function function) {
                    return Function$CC.$default$andThen(this, function);
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((JsonObject) obj).getObject("playlistSidebarSecondaryInfoRenderer").getObject("videoOwner").getObject(YoutubePlaylistExtractor.VIDEO_OWNER_RENDERER);
                }

                public /* synthetic */ Function compose(Function function) {
                    return Function$CC.$default$compose(this, function);
                }
            }).findFirst().orElseThrow(new Supplier() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubePlaylistExtractor$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return YoutubePlaylistExtractor.lambda$getUploaderInfo$2();
                }
            });
        }
        return this.uploaderInfo;
    }

    static /* synthetic */ ParsingException lambda$getUploaderInfo$2() {
        return new ParsingException("Could not get uploader info");
    }

    @Nonnull
    private JsonObject getPlaylistInfo() throws ParsingException {
        if (this.playlistInfo == null) {
            this.playlistInfo = (JsonObject) this.browseMetadataResponse.getObject(SIDEBAR).getObject("playlistSidebarRenderer").getArray("items").streamAsJsonObjects().filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubePlaylistExtractor$$ExternalSyntheticLambda6
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
                    return ((JsonObject) obj).has("playlistSidebarPrimaryInfoRenderer");
                }
            }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubePlaylistExtractor$$ExternalSyntheticLambda7
                @Override // java.util.function.Function
                public /* synthetic */ Function andThen(Function function) {
                    return Function$CC.$default$andThen(this, function);
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((JsonObject) obj).getObject("playlistSidebarPrimaryInfoRenderer");
                }

                public /* synthetic */ Function compose(Function function) {
                    return Function$CC.$default$compose(this, function);
                }
            }).findFirst().orElseThrow(new Supplier() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubePlaylistExtractor$$ExternalSyntheticLambda8
                @Override // java.util.function.Supplier
                public final Object get() {
                    return YoutubePlaylistExtractor.lambda$getPlaylistInfo$5();
                }
            });
        }
        return this.playlistInfo;
    }

    static /* synthetic */ ParsingException lambda$getPlaylistInfo$5() {
        return new ParsingException("Could not get playlist info");
    }

    @Nonnull
    private JsonObject getPlaylistHeader() {
        if (this.playlistHeader == null) {
            this.playlistHeader = this.browseMetadataResponse.getObject(HEADER).getObject("playlistHeaderRenderer");
        }
        return this.playlistHeader;
    }

    private boolean isCoursePlaylist() {
        if (this.isCoursePlaylist == null) {
            this.isCoursePlaylist = Boolean.valueOf(getPlaylistHeader().getObject("onDescriptionTap").getObject(COMMAND_EXECUTOR_COMMAND).getArray("commands").streamAsJsonObjects().anyMatch(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubePlaylistExtractor$$ExternalSyntheticLambda2
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
                    return "engagement-panel-course-metadata".equals(((JsonObject) obj).getObject("showEngagementPanelEndpoint").getObject("identifier").getString("tag"));
                }
            }));
        }
        return this.isCoursePlaylist.booleanValue();
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getName() throws ParsingException {
        String textFromObject = YoutubeParsingHelper.getTextFromObject(getPlaylistInfo().getObject("title"));
        return !Utils.isNullOrEmpty(textFromObject) ? textFromObject : this.browseMetadataResponse.getObject(MICROFORMAT).getObject("microformatDataRenderer").getString("title");
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        JsonArray array;
        if (this.isNewPlaylistInterface) {
            array = getPlaylistHeader().getObject("playlistHeaderBanner").getObject("heroPlaylistThumbnailRenderer").getObject(THUMBNAIL).getArray(THUMBNAILS);
        } else {
            array = this.playlistInfo.getObject("thumbnailRenderer").getObject("playlistVideoThumbnailRenderer").getObject(THUMBNAIL).getArray(THUMBNAILS);
        }
        if (!Utils.isNullOrEmpty(array)) {
            return YoutubeParsingHelper.getImagesFromThumbnailsArray(array);
        }
        JsonArray array2 = this.browseMetadataResponse.getObject(MICROFORMAT).getObject("microformatDataRenderer").getObject(THUMBNAIL).getArray(THUMBNAILS);
        if (!Utils.isNullOrEmpty(array2)) {
            return YoutubeParsingHelper.getImagesFromThumbnailsArray(array2);
        }
        throw new ParsingException("Could not get playlist thumbnails");
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    public String getUploaderUrl() throws ParsingException {
        JsonObject object;
        try {
            if (this.isNewPlaylistInterface) {
                object = getPlaylistHeader().getObject("ownerText").getArray("runs").getObject(0).getObject("navigationEndpoint");
            } else {
                object = getUploaderInfo().getObject("navigationEndpoint");
            }
            return YoutubeParsingHelper.getUrlFromNavigationEndpoint(object);
        } catch (Exception e) {
            throw new ParsingException("Could not get playlist uploader url", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    public String getUploaderName() throws ParsingException {
        JsonObject object;
        try {
            if (this.isNewPlaylistInterface) {
                object = getPlaylistHeader().getObject("ownerText");
            } else {
                object = getUploaderInfo().getObject("title");
            }
            return YoutubeParsingHelper.getTextFromObject(object);
        } catch (Exception e) {
            throw new ParsingException("Could not get playlist uploader name", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    @Nonnull
    public List<Image> getUploaderAvatars() throws ParsingException {
        if (this.isNewPlaylistInterface) {
            return Collections.EMPTY_LIST;
        }
        try {
            return YoutubeParsingHelper.getImagesFromThumbnailsArray(getUploaderInfo().getObject(THUMBNAIL).getArray(THUMBNAILS));
        } catch (Exception e) {
            throw new ParsingException("Could not get playlist uploader avatars", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    public long getStreamCount() throws ParsingException {
        String textFromObject;
        String textFromObject2;
        if (this.isNewPlaylistInterface) {
            String textFromObject3 = YoutubeParsingHelper.getTextFromObject(getPlaylistHeader().getObject("numVideosText"));
            if (textFromObject3 != null) {
                try {
                    return Long.parseLong(Utils.removeNonDigitCharacters(textFromObject3));
                } catch (NumberFormatException unused) {
                }
            }
            String textFromObject4 = YoutubeParsingHelper.getTextFromObject(getPlaylistHeader().getArray("byline").getObject(0).getObject("text"));
            if (textFromObject4 != null) {
                try {
                    return Long.parseLong(Utils.removeNonDigitCharacters(textFromObject4));
                } catch (NumberFormatException unused2) {
                }
            }
        }
        JsonArray array = (this.isNewPlaylistInterface ? getPlaylistHeader() : getPlaylistInfo()).getArray("briefStats");
        if (!array.isEmpty() && (textFromObject2 = YoutubeParsingHelper.getTextFromObject(array.getObject(0))) != null) {
            return Long.parseLong(Utils.removeNonDigitCharacters(textFromObject2));
        }
        JsonArray array2 = (this.isNewPlaylistInterface ? getPlaylistHeader() : getPlaylistInfo()).getArray("stats");
        if (array2.isEmpty() || (textFromObject = YoutubeParsingHelper.getTextFromObject(array2.getObject(0))) == null) {
            return -1L;
        }
        return Long.parseLong(Utils.removeNonDigitCharacters(textFromObject));
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    @Nonnull
    public Description getDescription() throws ParsingException {
        return new Description(YoutubeParsingHelper.getTextFromObject(getPlaylistInfo().getObject("description"), true), 1);
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    @Nonnull
    public ListExtractor.InfoItemsPage<StreamInfoItem> getInitialPage() throws IOException, ExtractionException {
        StreamInfoItemsCollector streamInfoItemsCollector = new StreamInfoItemsCollector(getServiceId());
        JsonArray array = this.initialBrowseContinuationResponse.getArray(ON_RESPONSE_RECEIVED_ACTIONS).getObject(0).getObject("reloadContinuationItemsCommand").getArray(CONTINUATION_ITEMS);
        if (array.isEmpty()) {
            array = this.initialBrowseContinuationResponse.getArray(ON_RESPONSE_RECEIVED_ACTIONS).getObject(0).getObject(APPEND_CONTINUATION_ITEMS_ACTION).getArray(CONTINUATION_ITEMS);
        }
        collectStreamsFrom(streamInfoItemsCollector, array);
        return new ListExtractor.InfoItemsPage<>(streamInfoItemsCollector, getNextPageFrom(array));
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    public ListExtractor.InfoItemsPage<StreamInfoItem> getPage(Page page) throws IOException, ExtractionException {
        if (page == null || Utils.isNullOrEmpty(page.getUrl())) {
            throw new IllegalArgumentException("Page doesn't contain an URL");
        }
        StreamInfoItemsCollector streamInfoItemsCollector = new StreamInfoItemsCollector(getServiceId());
        JsonArray array = YoutubeParsingHelper.getJsonPostResponse(BROWSE_ENDPOINT, page.getBody(), getExtractorLocalization()).getArray(ON_RESPONSE_RECEIVED_ACTIONS).getObject(0).getObject(APPEND_CONTINUATION_ITEMS_ACTION).getArray(CONTINUATION_ITEMS);
        collectStreamsFrom(streamInfoItemsCollector, array);
        return new ListExtractor.InfoItemsPage<>(streamInfoItemsCollector, getNextPageFrom(array));
    }

    @Nullable
    private Page getNextPageFrom(JsonArray jsonArray) throws IOException, ExtractionException {
        String string;
        if (Utils.isNullOrEmpty(jsonArray)) {
            return null;
        }
        JsonObject object = jsonArray.getObject(jsonArray.size() - 1);
        if (object.has("continuationItemRenderer")) {
            JsonObject object2 = object.getObject("continuationItemRenderer").getObject("continuationEndpoint");
            if (object2.has(COMMAND_EXECUTOR_COMMAND)) {
                object2 = object2.getObject(COMMAND_EXECUTOR_COMMAND).getArray("commands").streamAsJsonObjects().filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubePlaylistExtractor$$ExternalSyntheticLambda1
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
                        return ((JsonObject) obj).has(YoutubePlaylistExtractor.CONTINUATION_COMMAND);
                    }
                }).findFirst().orElse(new JsonObject());
            }
            string = object2.getObject(CONTINUATION_COMMAND).getString("token");
        } else {
            if (!object.has("continuationItemViewModel")) {
                return null;
            }
            string = object.getObject("continuationItemViewModel").getObject(CONTINUATION_COMMAND).getObject("innertubeCommand").getObject(CONTINUATION_COMMAND).getString("token");
        }
        if (Utils.isNullOrEmpty(string)) {
            return null;
        }
        return new Page("https://www.youtube.com/youtubei/v1/browse?prettyPrint=false", JsonWriter.string(YoutubeParsingHelper.prepareDesktopJsonBuilder(getExtractorLocalization(), getExtractorContentCountry()).value("continuation", string).done()).getBytes(StandardCharsets.UTF_8));
    }

    private void collectStreamsFrom(@Nonnull final StreamInfoItemsCollector streamInfoItemsCollector, @Nonnull JsonArray jsonArray) {
        final TimeAgoParser timeAgoParser = getTimeAgoParser();
        final boolean zIsCoursePlaylist = isCoursePlaylist();
        jsonArray.streamAsJsonObjects().forEach(new Consumer() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubePlaylistExtractor$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m10977x86f3e650(streamInfoItemsCollector, timeAgoParser, zIsCoursePlaylist, this, (JsonObject) obj);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$collectStreamsFrom$8$org-schabi-newpipe-extractor-services-youtube-extractors-YoutubePlaylistExtractor, reason: not valid java name */
    /* synthetic */ void m10977x86f3e650(StreamInfoItemsCollector streamInfoItemsCollector, TimeAgoParser timeAgoParser, final boolean z, final PlaylistExtractor playlistExtractor, JsonObject jsonObject) {
        if (jsonObject.has(PLAYLIST_VIDEO_RENDERER)) {
            streamInfoItemsCollector.commit((StreamInfoItemExtractor) new YoutubeStreamInfoItemExtractor(jsonObject.getObject(PLAYLIST_VIDEO_RENDERER), timeAgoParser) { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubePlaylistExtractor.1
                @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemExtractor, org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
                public String getUploaderName() throws ParsingException {
                    if (z) {
                        return playlistExtractor.getUploaderName();
                    }
                    return super.getUploaderName();
                }

                @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemExtractor, org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
                public String getUploaderUrl() throws ParsingException {
                    if (z) {
                        return playlistExtractor.getUploaderUrl();
                    }
                    return super.getUploaderUrl();
                }
            });
            return;
        }
        if (jsonObject.has(RICH_ITEM_RENDERER)) {
            JsonObject object = jsonObject.getObject(RICH_ITEM_RENDERER);
            if (object.has("content")) {
                JsonObject object2 = object.getObject("content");
                if (object2.has(REEL_ITEM_RENDERER)) {
                    streamInfoItemsCollector.commit((StreamInfoItemExtractor) new YoutubeReelInfoItemExtractor(object2.getObject(REEL_ITEM_RENDERER)));
                    return;
                }
                return;
            }
            return;
        }
        if (jsonObject.has(LOCKUP_VIEW_MODEL)) {
            streamInfoItemsCollector.commit((StreamInfoItemExtractor) new YoutubeStreamInfoItemLockupExtractor(jsonObject.getObject(LOCKUP_VIEW_MODEL), timeAgoParser) { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubePlaylistExtractor.2
                @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemLockupExtractor
                public boolean isChannelOrCoursePlaylistLockupItem() {
                    return z;
                }

                @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemLockupExtractor, org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
                public String getUploaderName() throws ParsingException {
                    if (z) {
                        return playlistExtractor.getUploaderName();
                    }
                    return super.getUploaderName();
                }

                @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemLockupExtractor, org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
                public String getUploaderUrl() throws ParsingException {
                    if (z) {
                        return playlistExtractor.getUploaderUrl();
                    }
                    return super.getUploaderUrl();
                }
            });
        }
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    @Nonnull
    public PlaylistInfo.PlaylistType getPlaylistType() throws ParsingException {
        return YoutubeParsingHelper.extractPlaylistTypeFromPlaylistUrl(getUrl());
    }
}
