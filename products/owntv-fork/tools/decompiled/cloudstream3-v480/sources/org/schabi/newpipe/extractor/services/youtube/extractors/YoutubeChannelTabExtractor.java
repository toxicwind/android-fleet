package org.schabi.newpipe.extractor.services.youtube.extractors;

import com.google.android.gms.common.internal.ImagesContract;
import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonWriter;
import j$.util.Optional;
import j$.util.function.BiFunction$CC;
import j$.util.function.Function$CC;
import j$.util.function.Predicate$CC;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import kotlin.UByte$$ExternalSyntheticBackport0;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.MultiInfoItemsCollector;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.channel.tabs.ChannelTabExtractor;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.localization.TimeAgoParser;
import org.schabi.newpipe.extractor.services.youtube.YoutubeChannelHelper;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;
import org.schabi.newpipe.extractor.services.youtube.linkHandler.YoutubeChannelTabLinkHandlerFactory;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class YoutubeChannelTabExtractor extends ChannelTabExtractor {

    @Nullable
    protected YoutubeChannelHelper.ChannelHeader channelHeader;
    private String channelId;
    private JsonObject jsonResponse;

    /* JADX INFO: Access modifiers changed from: private */
    enum VerifiedStatus {
        VERIFIED,
        UNVERIFIED,
        UNKNOWN
    }

    static /* synthetic */ Optional lambda$collectItemsFrom$7(Optional optional) {
        return optional;
    }

    public YoutubeChannelTabExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler) {
        super(streamingService, listLinkHandler);
    }

    @Nonnull
    private String getChannelTabsParameters() throws ParsingException {
        String name = getName();
        name.hashCode();
        switch (name) {
            case "playlists":
                return "EglwbGF5bGlzdHPyBgQKAkIA";
            case "albums":
                return "EghyZWxlYXNlc_IGBQoDsgEA";
            case "shorts":
                return "EgZzaG9ydHPyBgUKA5oBAA%3D%3D";
            case "videos":
                return "EgZ2aWRlb3PyBgQKAjoA";
            case "livestreams":
                return "EgdzdHJlYW1z8gYECgJ6AA%3D%3D";
            default:
                throw new ParsingException("Unsupported channel tab: " + name);
        }
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        YoutubeChannelHelper.ChannelResponseData channelResponse = YoutubeChannelHelper.getChannelResponse(YoutubeChannelHelper.resolveChannelId(super.getId()), getChannelTabsParameters(), getExtractorLocalization(), getExtractorContentCountry());
        JsonObject jsonObject = channelResponse.jsonResponse;
        this.jsonResponse = jsonObject;
        this.channelHeader = YoutubeChannelHelper.getChannelHeader(jsonObject);
        this.channelId = channelResponse.channelId;
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getUrl() throws ParsingException {
        try {
            return YoutubeChannelTabLinkHandlerFactory.getInstance().getUrl("channel/" + getId(), UByte$$ExternalSyntheticBackport0.m(new Object[]{getName()}), "");
        } catch (ParsingException unused) {
            return super.getUrl();
        }
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getId() throws ParsingException {
        return YoutubeChannelHelper.getChannelId(this.channelHeader, this.jsonResponse, this.channelId);
    }

    protected String getChannelName() throws ParsingException {
        return YoutubeChannelHelper.getChannelName(this.channelHeader, YoutubeChannelHelper.getChannelAgeGateRenderer(this.jsonResponse), this.jsonResponse);
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    @Nonnull
    public ListExtractor.InfoItemsPage<InfoItem> getInitialPage() throws IOException, ExtractionException {
        JsonArray jsonArray;
        VerifiedStatus verifiedStatus;
        MultiInfoItemsCollector multiInfoItemsCollector = new MultiInfoItemsCollector(getServiceId());
        JsonArray jsonArray2 = new JsonArray();
        Optional<JsonObject> tabData = getTabData();
        if (tabData.isPresent()) {
            JsonObject object = tabData.get().getObject("content");
            JsonArray array = object.getObject("sectionListRenderer").getArray("contents").getObject(0).getObject("itemSectionRenderer").getArray("contents").getObject(0).getObject("gridRenderer").getArray("items");
            if (array.isEmpty()) {
                array = object.getObject("richGridRenderer").getArray("contents");
                if (array.isEmpty()) {
                    jsonArray2 = object.getObject("sectionListRenderer").getArray("contents");
                    jsonArray = jsonArray2;
                }
            }
            jsonArray = array;
        } else {
            jsonArray = jsonArray2;
        }
        YoutubeChannelHelper.ChannelHeader channelHeader = this.channelHeader;
        if (channelHeader == null) {
            verifiedStatus = VerifiedStatus.UNKNOWN;
        } else if (YoutubeChannelHelper.isChannelVerified(channelHeader)) {
            verifiedStatus = VerifiedStatus.VERIFIED;
        } else {
            verifiedStatus = VerifiedStatus.UNVERIFIED;
        }
        VerifiedStatus verifiedStatus2 = verifiedStatus;
        String channelName = getChannelName();
        String url = getUrl();
        return new ListExtractor.InfoItemsPage<>(multiInfoItemsCollector, getNextPageFrom(collectItemsFrom(multiInfoItemsCollector, jsonArray, verifiedStatus2, channelName, url).orElse(null), UByte$$ExternalSyntheticBackport0.m(new Object[]{channelName, url, verifiedStatus2.toString()})));
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    public ListExtractor.InfoItemsPage<InfoItem> getPage(Page page) throws IOException, ExtractionException {
        if (page == null || Utils.isNullOrEmpty(page.getUrl())) {
            throw new IllegalArgumentException("Page doesn't contain an URL");
        }
        List<String> ids = page.getIds();
        MultiInfoItemsCollector multiInfoItemsCollector = new MultiInfoItemsCollector(getServiceId());
        return new ListExtractor.InfoItemsPage<>(multiInfoItemsCollector, getNextPageFrom(collectItemsFrom(multiInfoItemsCollector, ((JsonObject) YoutubeParsingHelper.getJsonPostResponse("browse", page.getBody(), getExtractorLocalization()).getArray("onResponseReceivedActions").streamAsJsonObjects().filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelTabExtractor$$ExternalSyntheticLambda8
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
                return ((JsonObject) obj).has("appendContinuationItemsAction");
            }
        }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelTabExtractor$$ExternalSyntheticLambda9
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getObject("appendContinuationItemsAction");
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).findFirst().orElse(new JsonObject())).getArray("continuationItems"), ids).orElse(null), ids));
    }

    Optional<JsonObject> getTabData() {
        final String urlSuffix = YoutubeChannelTabLinkHandlerFactory.getUrlSuffix(getName());
        return this.jsonResponse.getObject("contents").getObject("twoColumnBrowseResultsRenderer").getArray("tabs").streamAsJsonObjects().filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelTabExtractor$$ExternalSyntheticLambda3
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
                return ((JsonObject) obj).has("tabRenderer");
            }
        }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelTabExtractor$$ExternalSyntheticLambda4
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getObject("tabRenderer");
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelTabExtractor$$ExternalSyntheticLambda5
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
                return ((JsonObject) obj).getObject("endpoint").getObject("commandMetadata").getObject("webCommandMetadata").getString(ImagesContract.URL, "").endsWith(urlSuffix);
            }
        }).findFirst().filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelTabExtractor$$ExternalSyntheticLambda6
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
                return YoutubeChannelTabExtractor.lambda$getTabData$5((JsonObject) obj);
            }
        });
    }

    static /* synthetic */ boolean lambda$getTabData$5(JsonObject jsonObject) {
        JsonArray array = jsonObject.getObject("content").getObject("sectionListRenderer").getArray("contents").getObject(0).getObject("itemSectionRenderer").getArray("contents");
        return (array.size() == 1 && array.getObject(0).has("messageRenderer")) ? false : true;
    }

    private Optional<JsonObject> collectItemsFrom(@Nonnull MultiInfoItemsCollector multiInfoItemsCollector, @Nonnull JsonArray jsonArray, @Nonnull List<String> list) {
        VerifiedStatus verifiedStatus;
        String str;
        String str2;
        VerifiedStatus verifiedStatusValueOf;
        if (list.size() >= 3) {
            String str3 = list.get(0);
            String str4 = list.get(1);
            try {
                verifiedStatusValueOf = VerifiedStatus.valueOf(list.get(2));
            } catch (IllegalArgumentException unused) {
                verifiedStatusValueOf = VerifiedStatus.UNKNOWN;
            }
            verifiedStatus = verifiedStatusValueOf;
            str = str3;
            str2 = str4;
        } else {
            verifiedStatus = VerifiedStatus.UNKNOWN;
            str = null;
            str2 = null;
        }
        return collectItemsFrom(multiInfoItemsCollector, jsonArray, verifiedStatus, str, str2);
    }

    private Optional<JsonObject> collectItemsFrom(@Nonnull final MultiInfoItemsCollector multiInfoItemsCollector, @Nonnull JsonArray jsonArray, @Nonnull final VerifiedStatus verifiedStatus, @Nullable final String str, @Nullable final String str2) {
        return (Optional) jsonArray.streamAsJsonObjects().map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelTabExtractor$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.m10976xc09b75b(multiInfoItemsCollector, verifiedStatus, str, str2, (JsonObject) obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).reduce(Optional.empty(), new BinaryOperator() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelTabExtractor$$ExternalSyntheticLambda2
            public /* synthetic */ BiFunction andThen(Function function) {
                return BiFunction$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((Optional) obj).or(new Supplier() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelTabExtractor$$ExternalSyntheticLambda7
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return YoutubeChannelTabExtractor.lambda$collectItemsFrom$7(optional);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: collectItem, reason: merged with bridge method [inline-methods] */
    public Optional<JsonObject> m10976xc09b75b(@Nonnull MultiInfoItemsCollector multiInfoItemsCollector, @Nonnull JsonObject jsonObject, @Nonnull VerifiedStatus verifiedStatus, @Nullable String str, @Nullable String str2) {
        TimeAgoParser timeAgoParser = getTimeAgoParser();
        if (jsonObject.has("richItemRenderer")) {
            JsonObject object = jsonObject.getObject("richItemRenderer").getObject("content");
            if (object.has("videoRenderer")) {
                commitVideo(multiInfoItemsCollector, timeAgoParser, object.getObject("videoRenderer"), verifiedStatus, str, str2);
            } else if (object.has("reelItemRenderer")) {
                commitReel(multiInfoItemsCollector, object.getObject("reelItemRenderer"), verifiedStatus, str, str2);
            } else if (object.has("shortsLockupViewModel")) {
                commitShortsLockup(multiInfoItemsCollector, object.getObject("shortsLockupViewModel"), verifiedStatus, str, str2);
            } else if (object.has("playlistRenderer")) {
                commitPlaylist(multiInfoItemsCollector, object.getObject("playlistRenderer"), verifiedStatus, str, str2);
            } else if (object.has("lockupViewModel")) {
                commitLockup(multiInfoItemsCollector, verifiedStatus, str, str2, timeAgoParser, object);
            }
        } else if (jsonObject.has("gridVideoRenderer")) {
            commitVideo(multiInfoItemsCollector, timeAgoParser, jsonObject.getObject("gridVideoRenderer"), verifiedStatus, str, str2);
        } else if (jsonObject.has("gridPlaylistRenderer")) {
            commitPlaylist(multiInfoItemsCollector, jsonObject.getObject("gridPlaylistRenderer"), verifiedStatus, str, str2);
        } else if (jsonObject.has("gridShowRenderer")) {
            multiInfoItemsCollector.commit(new YoutubeGridShowRendererChannelInfoItemExtractor(jsonObject.getObject("gridShowRenderer"), verifiedStatus, str, str2));
        } else {
            if (jsonObject.has("shelfRenderer")) {
                return m10976xc09b75b(multiInfoItemsCollector, jsonObject.getObject("shelfRenderer").getObject("content"), verifiedStatus, str, str2);
            }
            if (jsonObject.has("itemSectionRenderer")) {
                return collectItemsFrom(multiInfoItemsCollector, jsonObject.getObject("itemSectionRenderer").getArray("contents"), verifiedStatus, str, str2);
            }
            if (jsonObject.has("horizontalListRenderer")) {
                return collectItemsFrom(multiInfoItemsCollector, jsonObject.getObject("horizontalListRenderer").getArray("items"), verifiedStatus, str, str2);
            }
            if (jsonObject.has("expandedShelfContentsRenderer")) {
                return collectItemsFrom(multiInfoItemsCollector, jsonObject.getObject("expandedShelfContentsRenderer").getArray("items"), verifiedStatus, str, str2);
            }
            if (jsonObject.has("lockupViewModel")) {
                commitLockup(multiInfoItemsCollector, verifiedStatus, str, str2, timeAgoParser, jsonObject);
            } else if (jsonObject.has("continuationItemRenderer")) {
                return Optional.ofNullable(jsonObject.getObject("continuationItemRenderer"));
            }
        }
        return Optional.empty();
    }

    private void commitLockup(@Nonnull MultiInfoItemsCollector multiInfoItemsCollector, @Nonnull VerifiedStatus verifiedStatus, @Nullable String str, @Nullable String str2, @Nonnull TimeAgoParser timeAgoParser, @Nonnull JsonObject jsonObject) {
        JsonObject object = jsonObject.getObject("lockupViewModel");
        String string = object.getString("contentType");
        if ("LOCKUP_CONTENT_TYPE_PLAYLIST".equals(string) || "LOCKUP_CONTENT_TYPE_PODCAST".equals(string)) {
            commitPlaylistLockup(multiInfoItemsCollector, object, verifiedStatus, str, str2);
        } else if ("LOCKUP_CONTENT_TYPE_VIDEO".equals(string)) {
            commitVideoLockup(multiInfoItemsCollector, timeAgoParser, object, verifiedStatus, str, str2);
        }
    }

    private static void commitReel(@Nonnull MultiInfoItemsCollector multiInfoItemsCollector, @Nonnull JsonObject jsonObject, @Nonnull final VerifiedStatus verifiedStatus, @Nullable final String str, @Nullable final String str2) {
        multiInfoItemsCollector.commit(new YoutubeReelInfoItemExtractor(jsonObject) { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelTabExtractor.1
            @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeReelInfoItemExtractor, org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
            public String getUploaderName() throws ParsingException {
                return Utils.isNullOrEmpty(str) ? super.getUploaderName() : str;
            }

            @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeReelInfoItemExtractor, org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
            public String getUploaderUrl() throws ParsingException {
                return Utils.isNullOrEmpty(str2) ? super.getUploaderName() : str2;
            }

            @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeReelInfoItemExtractor, org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
            public boolean isUploaderVerified() {
                return verifiedStatus == VerifiedStatus.VERIFIED;
            }
        });
    }

    private static void commitShortsLockup(@Nonnull MultiInfoItemsCollector multiInfoItemsCollector, @Nonnull JsonObject jsonObject, @Nonnull final VerifiedStatus verifiedStatus, @Nullable final String str, @Nullable final String str2) {
        multiInfoItemsCollector.commit(new YoutubeShortsLockupInfoItemExtractor(jsonObject) { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelTabExtractor.2
            @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeShortsLockupInfoItemExtractor, org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
            public String getUploaderName() throws ParsingException {
                return Utils.isNullOrEmpty(str) ? super.getUploaderName() : str;
            }

            @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeShortsLockupInfoItemExtractor, org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
            public String getUploaderUrl() throws ParsingException {
                return Utils.isNullOrEmpty(str2) ? super.getUploaderName() : str2;
            }

            @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeShortsLockupInfoItemExtractor, org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
            public boolean isUploaderVerified() {
                return verifiedStatus == VerifiedStatus.VERIFIED;
            }
        });
    }

    private static void commitVideoLockup(@Nonnull MultiInfoItemsCollector multiInfoItemsCollector, @Nonnull TimeAgoParser timeAgoParser, @Nonnull JsonObject jsonObject, @Nonnull final VerifiedStatus verifiedStatus, @Nullable final String str, @Nullable final String str2) {
        multiInfoItemsCollector.commit(new YoutubeStreamInfoItemLockupExtractor(jsonObject, timeAgoParser) { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelTabExtractor.3
            @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemLockupExtractor
            protected boolean isChannelOrCoursePlaylistLockupItem() {
                return true;
            }

            @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemLockupExtractor, org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
            @Nonnull
            public List<Image> getUploaderAvatars() throws ParsingException {
                return Collections.EMPTY_LIST;
            }

            @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemLockupExtractor, org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
            public String getUploaderName() throws ParsingException {
                return Utils.isNullOrEmpty(str) ? super.getUploaderName() : str;
            }

            @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemLockupExtractor, org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
            public String getUploaderUrl() throws ParsingException {
                return Utils.isNullOrEmpty(str2) ? super.getUploaderName() : str2;
            }

            @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemLockupExtractor, org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
            public boolean isUploaderVerified() {
                return verifiedStatus == VerifiedStatus.VERIFIED;
            }
        });
    }

    private void commitPlaylistLockup(@Nonnull MultiInfoItemsCollector multiInfoItemsCollector, @Nonnull JsonObject jsonObject, @Nonnull final VerifiedStatus verifiedStatus, @Nullable final String str, @Nullable final String str2) {
        multiInfoItemsCollector.commit(new YoutubeMixOrPlaylistLockupInfoItemExtractor(jsonObject) { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelTabExtractor.4
            @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeMixOrPlaylistLockupInfoItemExtractor, org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
            public String getUploaderName() throws ParsingException {
                return Utils.isNullOrEmpty(str) ? super.getUploaderName() : str;
            }

            @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeMixOrPlaylistLockupInfoItemExtractor, org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
            public String getUploaderUrl() throws ParsingException {
                return Utils.isNullOrEmpty(str2) ? super.getUploaderName() : str2;
            }

            @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeMixOrPlaylistLockupInfoItemExtractor, org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
            public boolean isUploaderVerified() throws ParsingException {
                int i = AnonymousClass7.$SwitchMap$org$schabi$newpipe$extractor$services$youtube$extractors$YoutubeChannelTabExtractor$VerifiedStatus[verifiedStatus.ordinal()];
                if (i == 1) {
                    return true;
                }
                if (i != 2) {
                    return super.isUploaderVerified();
                }
                return false;
            }
        });
    }

    /* JADX INFO: renamed from: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelTabExtractor$7, reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$org$schabi$newpipe$extractor$services$youtube$extractors$YoutubeChannelTabExtractor$VerifiedStatus;

        static {
            int[] iArr = new int[VerifiedStatus.values().length];
            $SwitchMap$org$schabi$newpipe$extractor$services$youtube$extractors$YoutubeChannelTabExtractor$VerifiedStatus = iArr;
            try {
                iArr[VerifiedStatus.VERIFIED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$schabi$newpipe$extractor$services$youtube$extractors$YoutubeChannelTabExtractor$VerifiedStatus[VerifiedStatus.UNVERIFIED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private void commitVideo(@Nonnull MultiInfoItemsCollector multiInfoItemsCollector, @Nonnull TimeAgoParser timeAgoParser, @Nonnull JsonObject jsonObject, @Nonnull final VerifiedStatus verifiedStatus, @Nullable final String str, @Nullable final String str2) {
        multiInfoItemsCollector.commit(new YoutubeStreamInfoItemExtractor(jsonObject, timeAgoParser) { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelTabExtractor.5
            @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemExtractor, org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
            public String getUploaderName() throws ParsingException {
                return Utils.isNullOrEmpty(str) ? super.getUploaderName() : str;
            }

            @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemExtractor, org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
            public String getUploaderUrl() throws ParsingException {
                return Utils.isNullOrEmpty(str2) ? super.getUploaderName() : str2;
            }

            @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemExtractor, org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
            public boolean isUploaderVerified() throws ParsingException {
                int i = AnonymousClass7.$SwitchMap$org$schabi$newpipe$extractor$services$youtube$extractors$YoutubeChannelTabExtractor$VerifiedStatus[verifiedStatus.ordinal()];
                if (i == 1) {
                    return true;
                }
                if (i != 2) {
                    return super.isUploaderVerified();
                }
                return false;
            }
        });
    }

    private void commitPlaylist(@Nonnull MultiInfoItemsCollector multiInfoItemsCollector, @Nonnull JsonObject jsonObject, @Nonnull final VerifiedStatus verifiedStatus, @Nullable final String str, @Nullable final String str2) {
        multiInfoItemsCollector.commit(new YoutubePlaylistInfoItemExtractor(jsonObject) { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelTabExtractor.6
            @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubePlaylistInfoItemExtractor, org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
            public String getUploaderName() throws ParsingException {
                return Utils.isNullOrEmpty(str) ? super.getUploaderName() : str;
            }

            @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubePlaylistInfoItemExtractor, org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
            public String getUploaderUrl() throws ParsingException {
                return Utils.isNullOrEmpty(str2) ? super.getUploaderName() : str2;
            }

            @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubePlaylistInfoItemExtractor, org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
            public boolean isUploaderVerified() throws ParsingException {
                int i = AnonymousClass7.$SwitchMap$org$schabi$newpipe$extractor$services$youtube$extractors$YoutubeChannelTabExtractor$VerifiedStatus[verifiedStatus.ordinal()];
                if (i == 1) {
                    return true;
                }
                if (i != 2) {
                    return super.isUploaderVerified();
                }
                return false;
            }
        });
    }

    @Nullable
    private Page getNextPageFrom(JsonObject jsonObject, List<String> list) throws IOException, ExtractionException {
        if (Utils.isNullOrEmpty(jsonObject)) {
            return null;
        }
        return new Page("https://www.youtube.com/youtubei/v1/browse?prettyPrint=false", null, list, null, JsonWriter.string(YoutubeParsingHelper.prepareDesktopJsonBuilder(getExtractorLocalization(), getExtractorContentCountry()).value("continuation", jsonObject.getObject("continuationEndpoint").getObject("continuationCommand").getString("token")).done()).getBytes(StandardCharsets.UTF_8));
    }

    public static final class VideosTabExtractor extends YoutubeChannelTabExtractor {
        private final String channelId;
        private final String channelName;
        private final String channelUrl;
        private final JsonObject tabRenderer;

        @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelTabExtractor, org.schabi.newpipe.extractor.Extractor
        public void onFetchPage(@Nonnull Downloader downloader) {
        }

        VideosTabExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler, JsonObject jsonObject, @Nullable YoutubeChannelHelper.ChannelHeader channelHeader, String str, String str2, String str3) {
            super(streamingService, listLinkHandler);
            this.channelHeader = channelHeader;
            this.tabRenderer = jsonObject;
            this.channelId = str2;
            this.channelName = str;
            this.channelUrl = str3;
        }

        @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelTabExtractor, org.schabi.newpipe.extractor.Extractor
        @Nonnull
        public String getId() throws ParsingException {
            return this.channelId;
        }

        @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelTabExtractor, org.schabi.newpipe.extractor.Extractor
        @Nonnull
        public String getUrl() throws ParsingException {
            return this.channelUrl;
        }

        @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelTabExtractor
        protected String getChannelName() {
            return this.channelName;
        }

        @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelTabExtractor
        Optional<JsonObject> getTabData() {
            return Optional.of(this.tabRenderer);
        }
    }

    private static final class YoutubeGridShowRendererChannelInfoItemExtractor extends YoutubeBaseShowInfoItemExtractor {

        @Nullable
        private final String channelName;

        @Nullable
        private final String channelUrl;

        @Nonnull
        private final VerifiedStatus verifiedStatus;

        private YoutubeGridShowRendererChannelInfoItemExtractor(@Nonnull JsonObject jsonObject, @Nonnull VerifiedStatus verifiedStatus, @Nullable String str, @Nullable String str2) {
            super(jsonObject);
            this.verifiedStatus = verifiedStatus;
            this.channelName = str;
            this.channelUrl = str2;
        }

        @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
        public String getUploaderName() {
            return this.channelName;
        }

        @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
        public String getUploaderUrl() {
            return this.channelUrl;
        }

        @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
        public boolean isUploaderVerified() throws ParsingException {
            int i = AnonymousClass7.$SwitchMap$org$schabi$newpipe$extractor$services$youtube$extractors$YoutubeChannelTabExtractor$VerifiedStatus[this.verifiedStatus.ordinal()];
            if (i == 1) {
                return true;
            }
            if (i == 2) {
                return false;
            }
            throw new ParsingException("Could not get uploader verification status");
        }
    }
}
