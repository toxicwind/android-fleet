package org.schabi.newpipe.extractor.services.youtube.extractors;

import androidx.core.app.NotificationCompat;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.common.internal.ImagesContract;
import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonWriter;
import j$.time.LocalDate;
import j$.time.format.DateTimeFormatter;
import j$.time.format.DateTimeParseException;
import j$.util.Objects;
import j$.util.Optional;
import j$.util.function.Consumer$CC;
import j$.util.function.Function$CC;
import j$.util.function.Predicate$CC;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import kotlin.UByte$$ExternalSyntheticBackport0;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.InfoItemExtractor;
import org.schabi.newpipe.extractor.MediaFormat;
import org.schabi.newpipe.extractor.MetaInfo;
import org.schabi.newpipe.extractor.MultiInfoItemsCollector;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.AccountTerminatedException;
import org.schabi.newpipe.extractor.exceptions.AgeRestrictedContentException;
import org.schabi.newpipe.extractor.exceptions.ContentNotAvailableException;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.GeographicRestrictionException;
import org.schabi.newpipe.extractor.exceptions.PaidContentException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.exceptions.PrivateContentException;
import org.schabi.newpipe.extractor.exceptions.SignInConfirmNotBotException;
import org.schabi.newpipe.extractor.exceptions.YoutubeMusicPremiumContentException;
import org.schabi.newpipe.extractor.linkhandler.LinkHandler;
import org.schabi.newpipe.extractor.localization.ContentCountry;
import org.schabi.newpipe.extractor.localization.DateWrapper;
import org.schabi.newpipe.extractor.localization.Localization;
import org.schabi.newpipe.extractor.localization.TimeAgoParser;
import org.schabi.newpipe.extractor.localization.TimeAgoPatternsManager;
import org.schabi.newpipe.extractor.services.youtube.ItagItem;
import org.schabi.newpipe.extractor.services.youtube.PoTokenProvider;
import org.schabi.newpipe.extractor.services.youtube.PoTokenResult;
import org.schabi.newpipe.extractor.services.youtube.YoutubeDescriptionHelper;
import org.schabi.newpipe.extractor.services.youtube.YoutubeJavaScriptPlayerManager;
import org.schabi.newpipe.extractor.services.youtube.YoutubeMetaInfoHelper;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;
import org.schabi.newpipe.extractor.services.youtube.YoutubeStreamHelper;
import org.schabi.newpipe.extractor.services.youtube.linkHandler.YoutubeChannelLinkHandlerFactory;
import org.schabi.newpipe.extractor.stream.AudioStream;
import org.schabi.newpipe.extractor.stream.DeliveryMethod;
import org.schabi.newpipe.extractor.stream.Description;
import org.schabi.newpipe.extractor.stream.Frameset;
import org.schabi.newpipe.extractor.stream.Stream;
import org.schabi.newpipe.extractor.stream.StreamExtractor;
import org.schabi.newpipe.extractor.stream.StreamSegment;
import org.schabi.newpipe.extractor.stream.StreamType;
import org.schabi.newpipe.extractor.stream.SubtitlesStream;
import org.schabi.newpipe.extractor.stream.VideoStream;
import org.schabi.newpipe.extractor.utils.JsonUtils;
import org.schabi.newpipe.extractor.utils.LocaleCompat;
import org.schabi.newpipe.extractor.utils.Pair;
import org.schabi.newpipe.extractor.utils.Parser;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class YoutubeStreamExtractor extends StreamExtractor {
    private static final String ADAPTIVE_FORMATS = "adaptiveFormats";
    private static final String CAPTIONS = "captions";
    private static final String CIPHER = "cipher";
    private static final String FORMATS = "formats";
    private static final String NEXT = "next";
    private static final String PLAYABILITY_STATUS = "playabilityStatus";
    private static final String PLAYER_CAPTIONS_TRACKLIST_RENDERER = "playerCaptionsTracklistRenderer";
    private static final String PREMIERED = "Premiered ";
    private static final String PREMIERED_ON = "Premiered on ";
    private static final String SIGNATURE_CIPHER = "signatureCipher";
    private static final String STREAMING_DATA = "streamingData";
    private static final String THUMBNAIL = "thumbnail";
    private static final String THUMBNAILS = "thumbnails";
    private static final String TITLE = "title";
    private static final String VIDEO_DETAILS = "videoDetails";
    private static boolean fetchIosClient;

    @Nullable
    private static PoTokenProvider poTokenProvider;
    private int ageLimit;
    private String androidCpn;

    @Nullable
    private JsonObject androidStreamingData;

    @Nullable
    private String androidStreamingUrlsPoToken;
    private String iosCpn;

    @Nullable
    private JsonObject iosStreamingData;

    @Nullable
    private String iosStreamingUrlsPoToken;
    private JsonObject nextResponse;
    private JsonObject playerCaptionsTracklistRenderer;
    private JsonObject playerMicroFormatRenderer;
    private JsonObject playerResponse;
    private StreamType streamType;
    private JsonArray thumbnailsArray;
    private JsonObject videoPrimaryInfoRenderer;
    private JsonObject videoSecondaryInfoRenderer;
    private String visionOsCpn;

    @Nullable
    private JsonObject visionOsStreamingData;

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public Locale getLanguageInfo() {
        return null;
    }

    public YoutubeStreamExtractor(StreamingService streamingService, LinkHandler linkHandler) {
        super(streamingService, linkHandler);
        this.ageLimit = -1;
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getName() throws ParsingException {
        assertPageFetched();
        String string = this.playerResponse.getObject(VIDEO_DETAILS).getString("title");
        if (!Utils.isNullOrEmpty(string)) {
            return string;
        }
        String textFromObject = YoutubeParsingHelper.getTextFromObject(getVideoPrimaryInfoRenderer().getObject("title"));
        if (Utils.isNullOrEmpty(textFromObject)) {
            throw new ParsingException("Could not get name");
        }
        return textFromObject;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nullable
    public String getTextualUploadDate() {
        String textFromObject;
        String string = this.playerMicroFormatRenderer.getString("uploadDate", "");
        if (string.isEmpty()) {
            string = this.playerMicroFormatRenderer.getString("publishDate", "");
        }
        if (!string.isEmpty()) {
            return string;
        }
        JsonObject object = this.playerMicroFormatRenderer.getObject("liveBroadcastDetails");
        String string2 = object.getString("endTimestamp", "");
        if (string2.isEmpty()) {
            string2 = object.getString("startTimestamp", "");
        }
        if (!string2.isEmpty()) {
            return string2;
        }
        if (getStreamType() == StreamType.LIVE_STREAM || (textFromObject = YoutubeParsingHelper.getTextFromObject(getVideoPrimaryInfoRenderer().getObject("dateText"))) == null) {
            return null;
        }
        if (textFromObject.startsWith(PREMIERED_ON)) {
            return textFromObject.substring(13);
        }
        return textFromObject.startsWith(PREMIERED) ? textFromObject.substring(10) : textFromObject;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public DateWrapper getUploadDate() throws ParsingException {
        final String textualUploadDate = getTextualUploadDate();
        try {
            try {
                return DateWrapper.fromOffsetDateTime(textualUploadDate);
            } catch (ParsingException unused) {
                return (DateWrapper) parseOptionalDate(textualUploadDate, "MMM dd, yyyy").or(new Supplier() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamExtractor$$ExternalSyntheticLambda16
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return this.f$0.m10981x7bb6f152(textualUploadDate);
                    }
                }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamExtractor$$ExternalSyntheticLambda17
                    @Override // java.util.function.Function
                    public /* synthetic */ Function andThen(Function function) {
                        return Function$CC.$default$andThen(this, function);
                    }

                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return YoutubeStreamExtractor.lambda$getUploadDate$1((LocalDate) obj);
                    }

                    public /* synthetic */ Function compose(Function function) {
                        return Function$CC.$default$compose(this, function);
                    }
                }).orElseThrow(new Supplier() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamExtractor$$ExternalSyntheticLambda18
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return YoutubeStreamExtractor.lambda$getUploadDate$2(textualUploadDate);
                    }
                });
            }
        } catch (ParsingException unused2) {
            return TimeAgoPatternsManager.getTimeAgoParserFor(new Localization("en")).parse(textualUploadDate);
        }
    }

    /* JADX INFO: renamed from: lambda$getUploadDate$0$org-schabi-newpipe-extractor-services-youtube-extractors-YoutubeStreamExtractor, reason: not valid java name */
    /* synthetic */ Optional m10981x7bb6f152(String str) {
        return parseOptionalDate(str, "dd MMM yyyy");
    }

    static /* synthetic */ DateWrapper lambda$getUploadDate$1(LocalDate localDate) {
        return new DateWrapper(localDate.atStartOfDay(), true);
    }

    static /* synthetic */ ParsingException lambda$getUploadDate$2(String str) {
        return new ParsingException("Could not parse upload date \"" + str + "\"");
    }

    private Optional<LocalDate> parseOptionalDate(String str, String str2) {
        try {
            return Optional.of(LocalDate.parse(str, DateTimeFormatter.ofPattern(str2, Locale.ENGLISH)));
        } catch (DateTimeParseException unused) {
            return Optional.empty();
        }
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        assertPageFetched();
        try {
            return YoutubeParsingHelper.getImagesFromThumbnailsArray(this.thumbnailsArray);
        } catch (Exception unused) {
            throw new ParsingException("Could not get thumbnails");
        }
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public Description getDescription() throws ParsingException {
        assertPageFetched();
        String textFromObject = YoutubeParsingHelper.getTextFromObject(getVideoSecondaryInfoRenderer().getObject("description"), true);
        if (!Utils.isNullOrEmpty(textFromObject)) {
            return new Description(textFromObject, 1);
        }
        String strAttributedDescriptionToHtml = YoutubeDescriptionHelper.attributedDescriptionToHtml(getVideoSecondaryInfoRenderer().getObject("attributedDescription"));
        if (!Utils.isNullOrEmpty(strAttributedDescriptionToHtml)) {
            return new Description(strAttributedDescriptionToHtml, 1);
        }
        String string = this.playerResponse.getObject(VIDEO_DETAILS).getString("shortDescription");
        if (string == null) {
            string = YoutubeParsingHelper.getTextFromObject(this.playerMicroFormatRenderer.getObject("description"));
        }
        return new Description(string, 3);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public int getAgeLimit() throws ParsingException {
        int i = this.ageLimit;
        if (i != -1) {
            return i;
        }
        int i2 = getVideoSecondaryInfoRenderer().getObject("metadataRowContainer").getObject("metadataRowContainerRenderer").getArray("rows").streamAsJsonObjects().flatMap(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamExtractor$$ExternalSyntheticLambda26
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getObject("metadataRowRenderer").getArray("contents").streamAsJsonObjects();
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).flatMap(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamExtractor$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getArray("runs").streamAsJsonObjects();
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamExtractor$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getString("text", "");
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).anyMatch(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamExtractor$$ExternalSyntheticLambda2
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
                return ((String) obj).contains("Age-restricted");
            }
        }) ? 18 : 0;
        this.ageLimit = i2;
        return i2;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public long getLength() throws ParsingException {
        assertPageFetched();
        try {
            return Long.parseLong(this.playerResponse.getObject(VIDEO_DETAILS).getString("lengthSeconds"));
        } catch (Exception unused) {
            return getDurationFromFirstAdaptiveFormat(Arrays.asList(this.androidStreamingData, this.iosStreamingData));
        }
    }

    private int getDurationFromFirstAdaptiveFormat(@Nonnull List<JsonObject> list) throws ParsingException {
        Iterator<JsonObject> it = list.iterator();
        while (it.hasNext()) {
            JsonArray array = it.next().getArray(ADAPTIVE_FORMATS);
            if (!array.isEmpty()) {
                try {
                    return Math.round(Long.parseLong(array.getObject(0).getString("approxDurationMs")) / 1000.0f);
                } catch (NumberFormatException unused) {
                    continue;
                }
            }
        }
        throw new ParsingException("Could not get duration");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public long getTimeStamp() throws ParsingException {
        long timestampSeconds = getTimestampSeconds("((#|&|\\?)t=\\d*h?\\d*m?\\d+s?)");
        if (timestampSeconds == -2) {
            return 0L;
        }
        return timestampSeconds;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public long getViewCount() throws ParsingException {
        String textFromObject = YoutubeParsingHelper.getTextFromObject(getVideoPrimaryInfoRenderer().getObject("viewCount").getObject("videoViewCountRenderer").getObject("viewCount"));
        if (Utils.isNullOrEmpty(textFromObject)) {
            textFromObject = this.playerResponse.getObject(VIDEO_DETAILS).getString("viewCount");
            if (Utils.isNullOrEmpty(textFromObject)) {
                throw new ParsingException("Could not get view count");
            }
        }
        if (textFromObject.toLowerCase().contains("no views")) {
            return 0L;
        }
        return Long.parseLong(Utils.removeNonDigitCharacters(textFromObject));
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public long getLikeCount() throws ParsingException {
        assertPageFetched();
        if (!this.playerResponse.getObject(VIDEO_DETAILS).getBoolean("allowRatings")) {
            return -1L;
        }
        JsonArray array = getVideoPrimaryInfoRenderer().getObject("videoActions").getObject("menuRenderer").getArray("topLevelButtons");
        try {
            try {
                return parseLikeCountFromLikeButtonViewModel(array);
            } catch (ParsingException unused) {
                return parseLikeCountFromLikeButtonRenderer(array);
            }
        } catch (ParsingException e) {
            throw new ParsingException("Could not get like count", e);
        }
    }

    private static long parseLikeCountFromLikeButtonRenderer(@Nonnull JsonArray jsonArray) throws ParsingException {
        String string = null;
        JsonObject jsonObject = (JsonObject) jsonArray.streamAsJsonObjects().map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamExtractor$$ExternalSyntheticLambda8
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getObject("segmentedLikeDislikeButtonRenderer").getObject("likeButton").getObject("toggleButtonRenderer");
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamExtractor$$ExternalSyntheticLambda9
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
                return YoutubeStreamExtractor.lambda$parseLikeCountFromLikeButtonRenderer$8((JsonObject) obj);
            }
        }).findFirst().orElse(null);
        if (jsonObject != null) {
            String string2 = jsonObject.getObject("accessibilityData").getObject("accessibilityData").getString("label");
            if (string2 == null) {
                string2 = jsonObject.getObject("accessibility").getString("label");
            }
            string = string2 == null ? jsonObject.getObject("defaultText").getObject("accessibility").getObject("accessibilityData").getString("label") : string2;
            if (string != null && string.toLowerCase().contains("no likes")) {
                return 0L;
            }
        }
        if (string == null) {
            throw new ParsingException("Could not get like count from accessibility data");
        }
        try {
            return Long.parseLong(Utils.removeNonDigitCharacters(string));
        } catch (NumberFormatException e) {
            throw new ParsingException("Could not parse \"" + string + "\" as a long", e);
        }
    }

    static /* synthetic */ boolean lambda$parseLikeCountFromLikeButtonRenderer$8(JsonObject jsonObject) {
        return !Utils.isNullOrEmpty(jsonObject);
    }

    private static long parseLikeCountFromLikeButtonViewModel(@Nonnull JsonArray jsonArray) throws ParsingException {
        JsonObject jsonObject = (JsonObject) jsonArray.streamAsJsonObjects().map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamExtractor$$ExternalSyntheticLambda5
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getObject("segmentedLikeDislikeButtonViewModel").getObject("likeButtonViewModel").getObject("likeButtonViewModel").getObject("toggleButtonViewModel").getObject("toggleButtonViewModel").getObject("defaultButtonViewModel").getObject("buttonViewModel");
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamExtractor$$ExternalSyntheticLambda6
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
                return YoutubeStreamExtractor.lambda$parseLikeCountFromLikeButtonViewModel$10((JsonObject) obj);
            }
        }).findFirst().orElse(null);
        if (jsonObject == null) {
            throw new ParsingException("Could not find buttonViewModel object");
        }
        String string = jsonObject.getString("accessibilityText");
        if (string == null) {
            throw new ParsingException("Could not find buttonViewModel's accessibilityText string");
        }
        try {
            return Long.parseLong(Utils.removeNonDigitCharacters(string));
        } catch (NumberFormatException e) {
            throw new ParsingException("Could not parse \"" + string + "\" as a long", e);
        }
    }

    static /* synthetic */ boolean lambda$parseLikeCountFromLikeButtonViewModel$10(JsonObject jsonObject) {
        return !Utils.isNullOrEmpty(jsonObject);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getUploaderUrl() throws ParsingException {
        assertPageFetched();
        String string = this.playerResponse.getObject(VIDEO_DETAILS).getString("channelId");
        if (!Utils.isNullOrEmpty(string)) {
            return YoutubeChannelLinkHandlerFactory.getInstance().getUrl("channel/" + string);
        }
        throw new ParsingException("Could not get uploader url");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getUploaderName() throws ParsingException {
        assertPageFetched();
        String string = this.playerResponse.getObject(VIDEO_DETAILS).getString("author");
        if (Utils.isNullOrEmpty(string)) {
            throw new ParsingException("Could not get uploader name");
        }
        return string;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public boolean isUploaderVerified() throws ParsingException {
        JsonObject object = getVideoSecondaryInfoRenderer().getObject("owner").getObject("videoOwnerRenderer");
        if (object.has("badges")) {
            return YoutubeParsingHelper.isVerified(object.getArray("badges"));
        }
        JsonObject firstCollaborator = YoutubeParsingHelper.getFirstCollaborator(object.getObject("navigationEndpoint"));
        if (firstCollaborator == null) {
            return false;
        }
        return YoutubeParsingHelper.hasArtistOrVerifiedIconBadgeAttachment(firstCollaborator.getObject("title").getArray("attachmentRuns"));
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public List<Image> getUploaderAvatars() throws ParsingException {
        List<Image> imagesFromThumbnailsArray;
        assertPageFetched();
        JsonObject object = getVideoSecondaryInfoRenderer().getObject("owner").getObject("videoOwnerRenderer");
        if (object.has("avatarStack")) {
            imagesFromThumbnailsArray = YoutubeParsingHelper.getImagesFromThumbnailsArray(object.getObject("avatarStack").getObject("avatarStackViewModel").getArray("avatars").getObject(0).getObject("avatarViewModel").getObject("image").getArray("sources"));
        } else {
            imagesFromThumbnailsArray = YoutubeParsingHelper.getImagesFromThumbnailsArray(object.getObject(THUMBNAIL).getArray(THUMBNAILS));
        }
        if (imagesFromThumbnailsArray.isEmpty() && this.ageLimit == 0) {
            throw new ParsingException("Could not get uploader avatars");
        }
        return imagesFromThumbnailsArray;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public long getUploaderSubscriberCount() throws ParsingException {
        String textFromObject;
        JsonObject object = JsonUtils.getObject(this.videoSecondaryInfoRenderer, "owner.videoOwnerRenderer");
        if (object.has("subscriberCountText")) {
            textFromObject = YoutubeParsingHelper.getTextFromObject(object.getObject("subscriberCountText"));
        } else {
            textFromObject = YoutubeParsingHelper.getFirstCollaborator(object.getObject("navigationEndpoint")).getObject(MediaTrack.ROLE_SUBTITLE).getString("content").split("•")[1];
        }
        if (Utils.isNullOrEmpty(textFromObject)) {
            return -1L;
        }
        try {
            return Utils.mixedNumberWordToLong(textFromObject);
        } catch (NumberFormatException e) {
            throw new ParsingException("Could not get uploader subscriber count", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getDashMpdUrl() throws ParsingException {
        assertPageFetched();
        return getManifestUrl("dash", UByte$$ExternalSyntheticBackport0.m(new Object[]{new Pair(this.androidStreamingData, this.androidStreamingUrlsPoToken)}), "mpd_version=7");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getHlsUrl() throws ParsingException {
        assertPageFetched();
        return getManifestUrl("hls", UByte$$ExternalSyntheticBackport0.m(new Object[]{new Pair(this.visionOsStreamingData, null), new Pair(this.iosStreamingData, this.iosStreamingUrlsPoToken), new Pair(this.androidStreamingData, this.androidStreamingUrlsPoToken)}), "");
    }

    @Nonnull
    private static String getManifestUrl(@Nonnull String str, @Nonnull List<Pair<JsonObject, String>> list, @Nonnull String str2) {
        String str3 = str + "ManifestUrl";
        for (Pair<JsonObject, String> pair : list) {
            if (pair.getFirst() != null) {
                String string = ((JsonObject) pair.getFirst()).getString(str3);
                if (!Utils.isNullOrEmpty(string)) {
                    if (pair.getSecond() == null) {
                        return string + "?" + str2;
                    }
                    return string + "?pot=" + ((String) pair.getSecond()) + "&" + str2;
                }
            }
        }
        return "";
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public List<AudioStream> getAudioStreams() throws ExtractionException {
        assertPageFetched();
        return getItags(ADAPTIVE_FORMATS, ItagItem.ItagType.AUDIO, getAudioStreamBuilderHelper(), "audio");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public List<VideoStream> getVideoStreams() throws ExtractionException {
        assertPageFetched();
        return getItags(FORMATS, ItagItem.ItagType.VIDEO, getVideoStreamBuilderHelper(false), "video");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public List<VideoStream> getVideoOnlyStreams() throws ExtractionException {
        assertPageFetched();
        return getItags(ADAPTIVE_FORMATS, ItagItem.ItagType.VIDEO_ONLY, getVideoStreamBuilderHelper(true), "video-only");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public List<SubtitlesStream> getSubtitlesDefault() throws ParsingException {
        return getSubtitles(MediaFormat.TTML);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public List<SubtitlesStream> getSubtitles(MediaFormat mediaFormat) throws ParsingException {
        assertPageFetched();
        ArrayList arrayList = new ArrayList();
        JsonArray array = this.playerCaptionsTracklistRenderer.getArray("captionTracks");
        for (int i = 0; i < array.size(); i++) {
            String string = array.getObject(i).getString("languageCode");
            String string2 = array.getObject(i).getString("baseUrl");
            String string3 = array.getObject(i).getString("vssId");
            if (string != null && string2 != null && string3 != null) {
                boolean zStartsWith = string3.startsWith("a.");
                String strReplaceAll = string2.replaceAll("&fmt=[^&]*", "").replaceAll("&tlang=[^&]*", "");
                arrayList.add(new SubtitlesStream.Builder().setContent(strReplaceAll + "&fmt=" + mediaFormat.getSuffix(), true).setMediaFormat(mediaFormat).setLanguageCode(string).setAutoGenerated(zStartsWith).build());
            }
        }
        return arrayList;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public StreamType getStreamType() {
        assertPageFetched();
        return this.streamType;
    }

    private void setStreamType() {
        if (this.playerResponse.getObject(PLAYABILITY_STATUS).has("liveStreamability")) {
            this.streamType = StreamType.LIVE_STREAM;
        } else if (this.playerResponse.getObject(VIDEO_DETAILS).getBoolean("isPostLiveDvr", false)) {
            this.streamType = StreamType.POST_LIVE_STREAM;
        } else {
            this.streamType = StreamType.VIDEO_STREAM;
        }
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nullable
    public MultiInfoItemsCollector getRelatedItems() throws ExtractionException {
        assertPageFetched();
        if (getAgeLimit() != 0) {
            return null;
        }
        try {
            final MultiInfoItemsCollector multiInfoItemsCollector = new MultiInfoItemsCollector(getServiceId());
            JsonArray array = this.nextResponse.getObject("contents").getObject("twoColumnWatchNextResults").getObject("secondaryResults").getObject("secondaryResults").getArray("results");
            final TimeAgoParser timeAgoParser = getTimeAgoParser();
            array.streamAsJsonObjects().map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamExtractor$$ExternalSyntheticLambda10
                @Override // java.util.function.Function
                public /* synthetic */ Function andThen(Function function) {
                    return Function$CC.$default$andThen(this, function);
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return YoutubeStreamExtractor.lambda$getRelatedItems$11(timeAgoParser, (JsonObject) obj);
                }

                public /* synthetic */ Function compose(Function function) {
                    return Function$CC.$default$compose(this, function);
                }
            }).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamExtractor$$ExternalSyntheticLambda11
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
                    return Objects.nonNull((InfoItemExtractor) obj);
                }
            }).forEach(new Consumer() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamExtractor$$ExternalSyntheticLambda12
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    multiInfoItemsCollector.commit((InfoItemExtractor) obj);
                }

                public /* synthetic */ Consumer andThen(Consumer consumer) {
                    return Consumer$CC.$default$andThen(this, consumer);
                }
            });
            return multiInfoItemsCollector;
        } catch (Exception e) {
            throw new ParsingException("Could not get related videos", e);
        }
    }

    static /* synthetic */ InfoItemExtractor lambda$getRelatedItems$11(TimeAgoParser timeAgoParser, JsonObject jsonObject) {
        if (jsonObject.has("compactVideoRenderer")) {
            return new YoutubeStreamInfoItemExtractor(jsonObject.getObject("compactVideoRenderer"), timeAgoParser);
        }
        if (jsonObject.has("compactRadioRenderer")) {
            return new YoutubeMixOrPlaylistInfoItemExtractor(jsonObject.getObject("compactRadioRenderer"));
        }
        if (jsonObject.has("compactPlaylistRenderer")) {
            return new YoutubeMixOrPlaylistInfoItemExtractor(jsonObject.getObject("compactPlaylistRenderer"));
        }
        if (!jsonObject.has("lockupViewModel")) {
            return null;
        }
        JsonObject object = jsonObject.getObject("lockupViewModel");
        String string = object.getString("contentType");
        if ("LOCKUP_CONTENT_TYPE_PLAYLIST".equals(string) || "LOCKUP_CONTENT_TYPE_PODCAST".equals(string)) {
            return new YoutubeMixOrPlaylistLockupInfoItemExtractor(object);
        }
        if ("LOCKUP_CONTENT_TYPE_VIDEO".equals(string)) {
            return new YoutubeStreamInfoItemLockupExtractor(object, timeAgoParser);
        }
        return null;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public String getErrorMessage() {
        try {
            return YoutubeParsingHelper.getTextFromObject(this.playerResponse.getObject(PLAYABILITY_STATUS).getObject("errorScreen").getObject("playerErrorMessageRenderer").getObject("reason"));
        } catch (NullPointerException unused) {
            return null;
        }
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        String id = getId();
        Localization extractorLocalization = getExtractorLocalization();
        ContentCountry extractorContentCountry = getExtractorContentCountry();
        PoTokenProvider poTokenProvider2 = poTokenProvider;
        boolean z = poTokenProvider2 == null;
        fetchAndroidClient(extractorLocalization, extractorContentCountry, id, z ? null : poTokenProvider2.getAndroidClientPoToken(id));
        setStreamType();
        if (fetchIosClient) {
            fetchIosClient(extractorLocalization, extractorContentCountry, id, z ? null : poTokenProvider2.getIosClientPoToken(id));
        }
        fetchVisionOsClient(extractorLocalization, extractorContentCountry, id);
        fetchWebClientMetadataAndSetThumbnails(extractorLocalization, extractorContentCountry, id);
        this.nextResponse = YoutubeParsingHelper.getJsonPostResponse("next", JsonWriter.string(YoutubeParsingHelper.prepareDesktopJsonBuilder(extractorLocalization, extractorContentCountry).value(YoutubeParsingHelper.VIDEO_ID, id).value(YoutubeParsingHelper.CONTENT_CHECK_OK, true).value(YoutubeParsingHelper.RACY_CHECK_OK, true).done()).getBytes(StandardCharsets.UTF_8), extractorLocalization);
    }

    private static void checkPlayabilityStatus(@Nonnull JsonObject jsonObject) throws ParsingException {
        String string = jsonObject.getString(NotificationCompat.CATEGORY_STATUS);
        if (string == null || string.equalsIgnoreCase("ok")) {
            return;
        }
        String string2 = jsonObject.getString("reason");
        if (string2 != null) {
            if (string.equalsIgnoreCase("login_required")) {
                if (string2.contains("inappropriate for some users")) {
                    throw new AgeRestrictedContentException("This age-restricted video cannot be watched anonymously");
                }
                if (string2.contains("private")) {
                    throw new PrivateContentException("This video is private");
                }
                if (string2.contains("a bot")) {
                    throw new SignInConfirmNotBotException("YouTube probably temporarily blocked anonymous watch access with this IP , got error " + string + ": \"" + string2 + "\"");
                }
            }
            if (string.equalsIgnoreCase("unplayable") || string.equalsIgnoreCase(MediaRouteProviderProtocol.SERVICE_DATA_ERROR)) {
                if (string2.contains("Music Premium")) {
                    throw new YoutubeMusicPremiumContentException();
                }
                if (string2.contains("payment")) {
                    throw new PaidContentException("This video is a paid video");
                }
                if (string2.contains("members")) {
                    throw new PaidContentException("This video is only available for members of the channel of this video");
                }
                if (string2.contains("country")) {
                    throw new GeographicRestrictionException("This video is not available in client's country.");
                }
                if (string2.contains("closed") || string2.contains("terminated")) {
                    throw new AccountTerminatedException(string2);
                }
            }
        }
        throw new ContentNotAvailableException("Got error " + string + ": \"" + string2 + "\"");
    }

    private void fetchAndroidClient(@Nonnull Localization localization, @Nonnull ContentCountry contentCountry, @Nonnull String str, @Nullable PoTokenResult poTokenResult) throws IOException, ExtractionException {
        String strGenerateContentPlaybackNonce = YoutubeParsingHelper.generateContentPlaybackNonce();
        this.androidCpn = strGenerateContentPlaybackNonce;
        if (poTokenResult == null) {
            this.playerResponse = YoutubeStreamHelper.getAndroidReelPlayerResponse(contentCountry, localization, str, strGenerateContentPlaybackNonce);
        } else {
            this.playerResponse = YoutubeStreamHelper.getAndroidPlayerResponse(contentCountry, localization, str, strGenerateContentPlaybackNonce, poTokenResult);
        }
        checkPlayabilityStatus(this.playerResponse.getObject(PLAYABILITY_STATUS));
        if (isPlayerResponseNotValid(this.playerResponse, str)) {
            throw new ExtractionException("ANDROID player response is not valid");
        }
        this.androidStreamingData = this.playerResponse.getObject(STREAMING_DATA);
        this.playerCaptionsTracklistRenderer = this.playerResponse.getObject(CAPTIONS).getObject(PLAYER_CAPTIONS_TRACKLIST_RENDERER);
        if (poTokenResult != null) {
            this.androidStreamingUrlsPoToken = poTokenResult.streamingDataPoToken;
        }
    }

    private void fetchIosClient(@Nonnull Localization localization, @Nonnull ContentCountry contentCountry, @Nonnull String str, @Nullable PoTokenResult poTokenResult) {
        try {
            String strGenerateContentPlaybackNonce = YoutubeParsingHelper.generateContentPlaybackNonce();
            this.iosCpn = strGenerateContentPlaybackNonce;
            JsonObject iosPlayerResponse = YoutubeStreamHelper.getIosPlayerResponse(contentCountry, localization, str, strGenerateContentPlaybackNonce, poTokenResult);
            if (isPlayerResponseNotValid(iosPlayerResponse, str)) {
                return;
            }
            this.iosStreamingData = iosPlayerResponse.getObject(STREAMING_DATA);
            if (Utils.isNullOrEmpty(this.playerCaptionsTracklistRenderer)) {
                this.playerCaptionsTracklistRenderer = iosPlayerResponse.getObject(CAPTIONS).getObject(PLAYER_CAPTIONS_TRACKLIST_RENDERER);
            }
            if (poTokenResult != null) {
                this.iosStreamingUrlsPoToken = poTokenResult.streamingDataPoToken;
            }
        } catch (Exception unused) {
        }
    }

    private void fetchVisionOsClient(@Nonnull Localization localization, @Nonnull ContentCountry contentCountry, @Nonnull String str) {
        try {
            String strGenerateContentPlaybackNonce = YoutubeParsingHelper.generateContentPlaybackNonce();
            this.visionOsCpn = strGenerateContentPlaybackNonce;
            JsonObject visionOsPlayerResponse = YoutubeStreamHelper.getVisionOsPlayerResponse(contentCountry, localization, str, strGenerateContentPlaybackNonce);
            if (isPlayerResponseNotValid(visionOsPlayerResponse, str)) {
                return;
            }
            this.visionOsStreamingData = visionOsPlayerResponse.getObject(STREAMING_DATA);
            if (Utils.isNullOrEmpty(this.playerCaptionsTracklistRenderer)) {
                this.playerCaptionsTracklistRenderer = visionOsPlayerResponse.getObject(CAPTIONS).getObject(PLAYER_CAPTIONS_TRACKLIST_RENDERER);
            }
        } catch (Exception unused) {
        }
    }

    private void fetchWebClientMetadataAndSetThumbnails(@Nonnull Localization localization, @Nonnull ContentCountry contentCountry, @Nonnull String str) {
        try {
            JsonObject webMetadataPlayerResponse = YoutubeStreamHelper.getWebMetadataPlayerResponse(localization, contentCountry, str);
            if (isPlayerResponseNotValid(webMetadataPlayerResponse, str)) {
                return;
            }
            this.playerMicroFormatRenderer = webMetadataPlayerResponse.getObject("microformat").getObject("playerMicroformatRenderer");
            JsonObject object = webMetadataPlayerResponse.getObject(VIDEO_DETAILS).getObject(THUMBNAIL);
            if (object.containsKey(THUMBNAILS)) {
                this.thumbnailsArray = object.getArray(THUMBNAILS);
            } else {
                this.thumbnailsArray = this.playerResponse.getObject(VIDEO_DETAILS).getObject(THUMBNAIL).getArray(THUMBNAILS);
            }
        } catch (Exception unused) {
            this.playerMicroFormatRenderer = new JsonObject();
            this.thumbnailsArray = this.playerResponse.getObject(VIDEO_DETAILS).getObject(THUMBNAIL).getArray(THUMBNAILS);
        }
    }

    private static boolean isPlayerResponseNotValid(@Nonnull JsonObject jsonObject, @Nonnull String str) {
        return !str.equals(jsonObject.getObject(VIDEO_DETAILS).getString(YoutubeParsingHelper.VIDEO_ID));
    }

    @Nonnull
    private JsonObject getVideoPrimaryInfoRenderer() {
        JsonObject jsonObject = this.videoPrimaryInfoRenderer;
        if (jsonObject != null) {
            return jsonObject;
        }
        JsonObject videoInfoRenderer = getVideoInfoRenderer("videoPrimaryInfoRenderer");
        this.videoPrimaryInfoRenderer = videoInfoRenderer;
        return videoInfoRenderer;
    }

    @Nonnull
    private JsonObject getVideoSecondaryInfoRenderer() {
        JsonObject jsonObject = this.videoSecondaryInfoRenderer;
        if (jsonObject != null) {
            return jsonObject;
        }
        JsonObject videoInfoRenderer = getVideoInfoRenderer("videoSecondaryInfoRenderer");
        this.videoSecondaryInfoRenderer = videoInfoRenderer;
        return videoInfoRenderer;
    }

    @Nonnull
    private JsonObject getVideoInfoRenderer(@Nonnull final String str) {
        return (JsonObject) this.nextResponse.getObject("contents").getObject("twoColumnWatchNextResults").getObject("results").getObject("results").getArray("contents").streamAsJsonObjects().filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamExtractor$$ExternalSyntheticLambda20
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
                return ((JsonObject) obj).has(str);
            }
        }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamExtractor$$ExternalSyntheticLambda21
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getObject(str);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).findFirst().orElse(new JsonObject());
    }

    @Nonnull
    private <T extends Stream> List<T> getItags(final String str, final ItagItem.ItagType itagType, Function<ItagInfo, T> function, String str2) throws ParsingException {
        try {
            final String id = getId();
            final ArrayList arrayList = new ArrayList();
            j$.util.stream.Stream.CC.of((Object[]) new Pair[]{new Pair(this.androidStreamingData, new Pair(this.androidCpn, this.androidStreamingUrlsPoToken)), new Pair(this.visionOsStreamingData, new Pair(this.visionOsCpn, null)), new Pair(this.iosStreamingData, new Pair(this.iosCpn, this.iosStreamingUrlsPoToken))}).flatMap(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamExtractor$$ExternalSyntheticLambda14
                @Override // java.util.function.Function
                public /* synthetic */ Function andThen(Function function2) {
                    return Function$CC.$default$andThen(this, function2);
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.f$0.m10979xa60269bc(id, str, itagType, (Pair) obj);
                }

                public /* synthetic */ Function compose(Function function2) {
                    return Function$CC.$default$compose(this, function2);
                }
            }).map(function).forEachOrdered(new Consumer() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamExtractor$$ExternalSyntheticLambda15
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    YoutubeStreamExtractor.lambda$getItags$15(arrayList, (Stream) obj);
                }

                public /* synthetic */ Consumer andThen(Consumer consumer) {
                    return Consumer$CC.$default$andThen(this, consumer);
                }
            });
            return arrayList;
        } catch (Exception e) {
            throw new ParsingException("Could not get " + str2 + " streams", e);
        }
    }

    /* JADX INFO: renamed from: lambda$getItags$14$org-schabi-newpipe-extractor-services-youtube-extractors-YoutubeStreamExtractor, reason: not valid java name */
    /* synthetic */ j$.util.stream.Stream m10979xa60269bc(String str, String str2, ItagItem.ItagType itagType, Pair pair) {
        return getStreamsFromStreamingDataKey(str, (JsonObject) pair.getFirst(), str2, itagType, (String) ((Pair) pair.getSecond()).getFirst(), (String) ((Pair) pair.getSecond()).getSecond());
    }

    static /* synthetic */ void lambda$getItags$15(List list, Stream stream) {
        if (Stream.containSimilarStream(stream, list)) {
            return;
        }
        list.add(stream);
    }

    @Nonnull
    private Function<ItagInfo, AudioStream> getAudioStreamBuilderHelper() {
        return new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamExtractor$$ExternalSyntheticLambda22
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.m10978xeea17b((ItagInfo) obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        };
    }

    /* JADX INFO: renamed from: lambda$getAudioStreamBuilderHelper$16$org-schabi-newpipe-extractor-services-youtube-extractors-YoutubeStreamExtractor, reason: not valid java name */
    /* synthetic */ AudioStream m10978xeea17b(ItagInfo itagInfo) {
        ItagItem itagItem = itagInfo.getItagItem();
        AudioStream.Builder itagItem2 = new AudioStream.Builder().setId(String.valueOf(itagItem.id)).setContent(itagInfo.getContent(), itagInfo.getIsUrl()).setMediaFormat(itagItem.getMediaFormat()).setAverageBitrate(itagItem.getAverageBitrate()).setAudioTrackId(itagItem.getAudioTrackId()).setAudioTrackName(itagItem.getAudioTrackName()).setAudioLocale(itagItem.getAudioLocale()).setAudioTrackType(itagItem.getAudioTrackType()).setItagItem(itagItem);
        if (this.streamType == StreamType.LIVE_STREAM || this.streamType == StreamType.POST_LIVE_STREAM || !itagInfo.getIsUrl()) {
            itagItem2.setDeliveryMethod(DeliveryMethod.DASH);
        }
        return itagItem2.build();
    }

    @Nonnull
    private Function<ItagInfo, VideoStream> getVideoStreamBuilderHelper(final boolean z) {
        return new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamExtractor$$ExternalSyntheticLambda13
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.m10982x5e362461(z, (ItagInfo) obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        };
    }

    /* JADX INFO: renamed from: lambda$getVideoStreamBuilderHelper$17$org-schabi-newpipe-extractor-services-youtube-extractors-YoutubeStreamExtractor, reason: not valid java name */
    /* synthetic */ VideoStream m10982x5e362461(boolean z, ItagInfo itagInfo) {
        ItagItem itagItem = itagInfo.getItagItem();
        VideoStream.Builder itagItem2 = new VideoStream.Builder().setId(String.valueOf(itagItem.id)).setContent(itagInfo.getContent(), itagInfo.getIsUrl()).setMediaFormat(itagItem.getMediaFormat()).setIsVideoOnly(z).setItagItem(itagItem);
        String resolutionString = itagItem.getResolutionString();
        if (resolutionString == null) {
            resolutionString = "";
        }
        itagItem2.setResolution(resolutionString);
        if (this.streamType != StreamType.VIDEO_STREAM || !itagInfo.getIsUrl()) {
            itagItem2.setDeliveryMethod(DeliveryMethod.DASH);
        }
        return itagItem2.build();
    }

    @Nonnull
    private j$.util.stream.Stream<ItagInfo> getStreamsFromStreamingDataKey(final String str, JsonObject jsonObject, String str2, @Nonnull final ItagItem.ItagType itagType, @Nonnull final String str3, @Nullable final String str4) {
        if (jsonObject == null || !jsonObject.has(str2)) {
            return j$.util.stream.Stream.CC.empty();
        }
        return jsonObject.getArray(str2).streamAsJsonObjects().map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamExtractor$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.m10980xa1d35d52(itagType, str, str3, str4, (JsonObject) obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamExtractor$$ExternalSyntheticLambda4
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
                return Objects.nonNull((ItagInfo) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$getStreamsFromStreamingDataKey$18$org-schabi-newpipe-extractor-services-youtube-extractors-YoutubeStreamExtractor, reason: not valid java name */
    /* synthetic */ ItagInfo m10980xa1d35d52(ItagItem.ItagType itagType, String str, String str2, String str3, JsonObject jsonObject) {
        try {
            ItagItem itag = ItagItem.getItag(jsonObject.getInt("itag"));
            if (itag.itagType == itagType) {
                return buildAndAddItagInfoToList(str, jsonObject, itag, itag.itagType, str2, str3);
            }
            return null;
        } catch (ExtractionException unused) {
            return null;
        }
    }

    private ItagInfo buildAndAddItagInfoToList(@Nonnull String str, @Nonnull JsonObject jsonObject, @Nonnull final ItagItem itagItem, @Nonnull ItagItem.ItagType itagType, @Nonnull String str2, @Nullable String str3) throws Exception {
        String string;
        if (jsonObject.has(ImagesContract.URL)) {
            string = jsonObject.getString(ImagesContract.URL);
        } else {
            String string2 = jsonObject.getString(CIPHER, jsonObject.getString(SIGNATURE_CIPHER));
            if (Utils.isNullOrEmpty(string2)) {
                return null;
            }
            Map<String, String> mapCompatParseMap = Parser.compatParseMap(string2);
            String strDeobfuscateSignature = YoutubeJavaScriptPlayerManager.deobfuscateSignature(str, (String) j$.util.Map.EL.getOrDefault(mapCompatParseMap, CmcdData.STREAMING_FORMAT_SS, ""));
            string = mapCompatParseMap.get(ImagesContract.URL) + "&" + mapCompatParseMap.get("sp") + "=" + strDeobfuscateSignature;
        }
        String str4 = YoutubeJavaScriptPlayerManager.getUrlWithThrottlingParameterDeobfuscated(str, string) + "&cpn=" + str2;
        if (str3 != null) {
            str4 = str4 + "&pot=" + str3;
        }
        JsonObject object = jsonObject.getObject("initRange");
        JsonObject object2 = jsonObject.getObject("indexRange");
        String string3 = jsonObject.getString("mimeType", "");
        String str5 = string3.contains("codecs") ? string3.split("\"")[1] : "";
        itagItem.setBitrate(jsonObject.getInt("bitrate"));
        itagItem.setWidth(jsonObject.getInt("width"));
        itagItem.setHeight(jsonObject.getInt("height"));
        itagItem.setInitStart(Integer.parseInt(object.getString("start", "-1")));
        itagItem.setInitEnd(Integer.parseInt(object.getString(TtmlNode.END, "-1")));
        itagItem.setIndexStart(Integer.parseInt(object2.getString("start", "-1")));
        itagItem.setIndexEnd(Integer.parseInt(object2.getString(TtmlNode.END, "-1")));
        itagItem.setQuality(jsonObject.getString("quality"));
        itagItem.setCodec(str5);
        itagItem.setIsDrc(Boolean.valueOf(jsonObject.getBoolean("isDrc", false)));
        itagItem.setLastModified(Long.parseLong(jsonObject.getString("lastModified", "-1")));
        itagItem.setXtags(jsonObject.getString("xtags"));
        if (this.streamType == StreamType.LIVE_STREAM || this.streamType == StreamType.POST_LIVE_STREAM) {
            itagItem.setTargetDurationSec(jsonObject.getInt("targetDurationSec"));
        }
        if (itagType == ItagItem.ItagType.VIDEO || itagType == ItagItem.ItagType.VIDEO_ONLY) {
            itagItem.setFps(jsonObject.getInt("fps"));
        } else if (itagType == ItagItem.ItagType.AUDIO) {
            itagItem.setSampleRate(Integer.parseInt(jsonObject.getString("audioSampleRate")));
            itagItem.setAudioChannels(jsonObject.getInt("audioChannels", 2));
            String string4 = jsonObject.getObject("audioTrack").getString("id");
            if (!Utils.isNullOrEmpty(string4)) {
                itagItem.setAudioTrackId(string4);
                int iIndexOf = string4.indexOf(".");
                if (iIndexOf != -1) {
                    Optional<Locale> optionalForLanguageTag = LocaleCompat.forLanguageTag(string4.substring(0, iIndexOf));
                    Objects.requireNonNull(itagItem);
                    optionalForLanguageTag.ifPresent(new Consumer() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamExtractor$$ExternalSyntheticLambda7
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            itagItem.setAudioLocale((Locale) obj);
                        }

                        public /* synthetic */ Consumer andThen(Consumer consumer) {
                            return Consumer$CC.$default$andThen(this, consumer);
                        }
                    });
                }
                itagItem.setAudioTrackType(YoutubeParsingHelper.extractAudioTrackType(itagItem.getXtags()));
            }
            itagItem.setAudioTrackName(jsonObject.getObject("audioTrack").getString("displayName"));
        }
        itagItem.setContentLength(Long.parseLong(jsonObject.getString("contentLength", String.valueOf(-1L))));
        itagItem.setApproxDurationMs(Long.parseLong(jsonObject.getString("approxDurationMs", String.valueOf(-1L))));
        ItagInfo itagInfo = new ItagInfo(str4, itagItem);
        if (this.streamType == StreamType.VIDEO_STREAM) {
            itagInfo.setIsUrl(!jsonObject.getString("type", "").equalsIgnoreCase("FORMAT_STREAM_TYPE_OTF"));
            return itagInfo;
        }
        itagInfo.setIsUrl(this.streamType != StreamType.POST_LIVE_STREAM);
        return itagInfo;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public List<Frameset> getFrames() throws ExtractionException {
        List listSingletonList;
        String str = "playerLiveStoryboardSpecRenderer";
        try {
            JsonObject object = this.playerResponse.getObject("storyboards");
            if (!object.has("playerLiveStoryboardSpecRenderer")) {
                str = "playerStoryboardSpecRenderer";
            }
            JsonObject object2 = object.getObject(str);
            if (object2 == null) {
                return Collections.EMPTY_LIST;
            }
            String string = object2.getString("spec");
            if (string == null) {
                return Collections.EMPTY_LIST;
            }
            String[] strArrSplit = string.split("\\|");
            String str2 = strArrSplit[0];
            ArrayList arrayList = new ArrayList(strArrSplit.length - 1);
            for (int i = 1; i < strArrSplit.length; i++) {
                String[] strArrSplit2 = strArrSplit[i].split("#");
                if (strArrSplit2.length == 8 && Integer.parseInt(strArrSplit2[5]) != 0) {
                    int i2 = Integer.parseInt(strArrSplit2[2]);
                    int i3 = Integer.parseInt(strArrSplit2[3]);
                    int i4 = Integer.parseInt(strArrSplit2[4]);
                    String str3 = str2.replace("$L", String.valueOf(i - 1)).replace("$N", strArrSplit2[6]) + "&sigh=" + strArrSplit2[7];
                    if (str3.contains("$M")) {
                        int iCeil = (int) Math.ceil(((double) i2) / ((double) (i3 * i4)));
                        listSingletonList = new ArrayList(iCeil);
                        for (int i5 = 0; i5 < iCeil; i5++) {
                            listSingletonList.add(str3.replace("$M", String.valueOf(i5)));
                        }
                    } else {
                        listSingletonList = Collections.singletonList(str3);
                    }
                    arrayList.add(new Frameset(listSingletonList, Integer.parseInt(strArrSplit2[0]), Integer.parseInt(strArrSplit2[1]), i2, Integer.parseInt(strArrSplit2[5]), i3, i4));
                }
            }
            return arrayList;
        } catch (Exception e) {
            throw new ExtractionException("Could not get frames", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public StreamExtractor.Privacy getPrivacy() {
        if (this.playerMicroFormatRenderer.getBoolean("isUnlisted") || getVideoPrimaryInfoRenderer().getArray("badges").streamAsJsonObjects().anyMatch(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamExtractor$$ExternalSyntheticLambda19
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
                return "PRIVACY_UNLISTED".equals(((JsonObject) obj).getObject("metadataBadgeRenderer").getObject("icon").getString("iconType"));
            }
        })) {
            return StreamExtractor.Privacy.UNLISTED;
        }
        return StreamExtractor.Privacy.PUBLIC;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getCategory() {
        return this.playerMicroFormatRenderer.getString("category", "");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getLicence() throws ParsingException {
        JsonObject object = getVideoSecondaryInfoRenderer().getObject("metadataRowContainer").getObject("metadataRowContainerRenderer").getArray("rows").getObject(0).getObject("metadataRowRenderer");
        String textFromObject = YoutubeParsingHelper.getTextFromObject(object.getArray("contents").getObject(0));
        return (textFromObject == null || !"Licence".equals(YoutubeParsingHelper.getTextFromObject(object.getObject("title")))) ? "YouTube licence" : textFromObject;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public List<String> getTags() {
        return JsonUtils.getStringListFromJsonArray(this.playerResponse.getObject(VIDEO_DETAILS).getArray("keywords"));
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public List<StreamSegment> getStreamSegments() throws ParsingException {
        if (!this.nextResponse.has("engagementPanels")) {
            return Collections.EMPTY_LIST;
        }
        JsonArray jsonArray = (JsonArray) this.nextResponse.getArray("engagementPanels").streamAsJsonObjects().filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamExtractor$$ExternalSyntheticLambda23
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
                return "engagement-panel-macro-markers-description-chapters".equals(((JsonObject) obj).getObject("engagementPanelSectionListRenderer").getString("panelIdentifier"));
            }
        }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamExtractor$$ExternalSyntheticLambda24
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getObject("engagementPanelSectionListRenderer").getObject("content").getObject("macroMarkersListRenderer").getArray("contents");
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).findFirst().orElse(null);
        if (jsonArray == null) {
            return Collections.EMPTY_LIST;
        }
        long length = getLength();
        ArrayList arrayList = new ArrayList();
        for (JsonObject jsonObject : jsonArray.streamAsJsonObjects().map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamExtractor$$ExternalSyntheticLambda25
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getObject("macroMarkersListItemRenderer");
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        })) {
            int i = jsonObject.getObject("onTap").getObject("watchEndpoint").getInt("startTimeSeconds", -1);
            if (i == -1) {
                throw new ParsingException("Could not get stream segment start time.");
            }
            if (i > length) {
                break;
            }
            String textFromObject = YoutubeParsingHelper.getTextFromObject(jsonObject.getObject("title"));
            if (Utils.isNullOrEmpty(textFromObject)) {
                throw new ParsingException("Could not get stream segment title.");
            }
            StreamSegment streamSegment = new StreamSegment(textFromObject, i);
            streamSegment.setUrl(getUrl() + "?t=" + i);
            if (jsonObject.has(THUMBNAIL)) {
                JsonArray array = jsonObject.getObject(THUMBNAIL).getArray(THUMBNAILS);
                if (!array.isEmpty()) {
                    streamSegment.setPreviewUrl(YoutubeParsingHelper.fixThumbnailUrl(array.getObject(array.size() - 1).getString(ImagesContract.URL)));
                }
            }
            arrayList.add(streamSegment);
        }
        return arrayList;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public List<MetaInfo> getMetaInfo() throws ParsingException {
        return YoutubeMetaInfoHelper.getMetaInfo(this.nextResponse.getObject("contents").getObject("twoColumnWatchNextResults").getObject("results").getObject("results").getArray("contents"));
    }

    public static void setPoTokenProvider(@Nullable PoTokenProvider poTokenProvider2) {
        poTokenProvider = poTokenProvider2;
    }

    public static void setFetchIosClient(boolean z) {
        fetchIosClient = z;
    }
}
