package org.schabi.newpipe.extractor.services.youtube.extractors;

import com.grack.nanojson.JsonObject;
import j$.time.Instant;
import j$.time.LocalDateTime;
import j$.time.ZoneId;
import j$.time.format.DateTimeFormatter;
import j$.util.function.Function$CC;
import j$.util.function.Predicate$CC;
import j$.util.stream.Collectors;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.channel.tabs.ChannelTabs;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.localization.DateWrapper;
import org.schabi.newpipe.extractor.localization.TimeAgoParser;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;
import org.schabi.newpipe.extractor.services.youtube.linkHandler.YoutubeStreamLinkHandlerFactory;
import org.schabi.newpipe.extractor.stream.ContentAvailability;
import org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor;
import org.schabi.newpipe.extractor.stream.StreamType;
import org.schabi.newpipe.extractor.utils.JsonUtils;
import org.schabi.newpipe.extractor.utils.Parser;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class YoutubeStreamInfoItemExtractor implements StreamInfoItemExtractor {
    private static final Pattern ACCESSIBILITY_DATA_VIEW_COUNT_REGEX = Pattern.compile("([\\d,]+) views$");
    private static final String NO_VIEWS_LOWERCASE = "no views";
    private StreamType cachedStreamType;
    private Boolean isPremiere;
    private final TimeAgoParser timeAgoParser;
    private final JsonObject videoInfo;

    public YoutubeStreamInfoItemExtractor(JsonObject jsonObject, @Nullable TimeAgoParser timeAgoParser) {
        this.videoInfo = jsonObject;
        this.timeAgoParser = timeAgoParser;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public StreamType getStreamType() {
        StreamType streamType = this.cachedStreamType;
        if (streamType != null) {
            return streamType;
        }
        for (Object obj : this.videoInfo.getArray("badges")) {
            if (obj instanceof JsonObject) {
                JsonObject object = ((JsonObject) obj).getObject("metadataBadgeRenderer");
                if (object.getString("style", "").equals("BADGE_STYLE_TYPE_LIVE_NOW") || object.getString("label", "").equals("LIVE NOW")) {
                    StreamType streamType2 = StreamType.LIVE_STREAM;
                    this.cachedStreamType = streamType2;
                    return streamType2;
                }
            }
        }
        for (Object obj2 : this.videoInfo.getArray("thumbnailOverlays")) {
            if ((obj2 instanceof JsonObject) && ((JsonObject) obj2).getObject("thumbnailOverlayTimeStatusRenderer").getString("style", "").equalsIgnoreCase("LIVE")) {
                StreamType streamType3 = StreamType.LIVE_STREAM;
                this.cachedStreamType = streamType3;
                return streamType3;
            }
        }
        StreamType streamType4 = StreamType.VIDEO_STREAM;
        this.cachedStreamType = streamType4;
        return streamType4;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public boolean isAd() throws ParsingException {
        return isPremium() || getName().equals("[Private video]") || getName().equals("[Deleted video]");
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getUrl() throws ParsingException {
        try {
            return YoutubeStreamLinkHandlerFactory.getInstance().getUrl(this.videoInfo.getString(YoutubeParsingHelper.VIDEO_ID));
        } catch (Exception e) {
            throw new ParsingException("Could not get url", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getName() throws ParsingException {
        JsonObject object = this.videoInfo.getObject("title");
        String textFromObject = YoutubeParsingHelper.getTextFromObject(object);
        if (!Utils.isNullOrEmpty(textFromObject)) {
            return textFromObject;
        }
        if (!Utils.isNullOrEmpty(object) && !object.has("runs")) {
            return "";
        }
        throw new ParsingException("Could not get name");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public long getDuration() throws ParsingException {
        if (getStreamType() == StreamType.LIVE_STREAM) {
            return -1L;
        }
        String textFromObject = YoutubeParsingHelper.getTextFromObject(this.videoInfo.getObject("lengthText"));
        if (Utils.isNullOrEmpty(textFromObject)) {
            textFromObject = this.videoInfo.getString("lengthSeconds");
            if (Utils.isNullOrEmpty(textFromObject)) {
                Iterator it = ((List) this.videoInfo.getArray("thumbnailOverlays").streamAsJsonObjects().filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemExtractor$$ExternalSyntheticLambda2
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
                        return ((JsonObject) obj).has("thumbnailOverlayTimeStatusRenderer");
                    }
                }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemExtractor$$ExternalSyntheticLambda3
                    @Override // java.util.function.Function
                    public /* synthetic */ Function andThen(Function function) {
                        return Function$CC.$default$andThen(this, function);
                    }

                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return YoutubeParsingHelper.getTextFromObject(((JsonObject) obj).getObject("thumbnailOverlayTimeStatusRenderer").getObject("text"));
                    }

                    public /* synthetic */ Function compose(Function function) {
                        return Function$CC.$default$compose(this, function);
                    }
                }).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemExtractor$$ExternalSyntheticLambda4
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
                        return YoutubeStreamInfoItemExtractor.lambda$getDuration$2((String) obj);
                    }
                }).collect(Collectors.toList())).iterator();
                while (it.hasNext()) {
                    try {
                        return YoutubeParsingHelper.parseDurationString((String) it.next());
                    } catch (ParsingException unused) {
                    }
                }
            }
            if (Utils.isNullOrEmpty(textFromObject)) {
                if (this.isPremiere()) {
                    return -1L;
                }
                throw new ParsingException("Could not get duration");
            }
        }
        return YoutubeParsingHelper.parseDurationString(textFromObject);
    }

    static /* synthetic */ boolean lambda$getDuration$2(String str) {
        return !Utils.isNullOrEmpty(str);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public String getUploaderName() throws ParsingException {
        String textFromObject = YoutubeParsingHelper.getTextFromObject(this.videoInfo.getObject("longBylineText"));
        if (Utils.isNullOrEmpty(textFromObject)) {
            textFromObject = YoutubeParsingHelper.getTextFromObject(this.videoInfo.getObject("ownerText"));
            if (Utils.isNullOrEmpty(textFromObject)) {
                String textFromObject2 = YoutubeParsingHelper.getTextFromObject(this.videoInfo.getObject("shortBylineText"));
                if (Utils.isNullOrEmpty(textFromObject2)) {
                    throw new ParsingException("Could not get uploader name");
                }
                return textFromObject2;
            }
        }
        return textFromObject;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public String getUploaderUrl() throws ParsingException {
        String urlFromNavigationEndpoint = YoutubeParsingHelper.getUrlFromNavigationEndpoint(this.videoInfo.getObject("longBylineText").getArray("runs").getObject(0).getObject("navigationEndpoint"));
        if (Utils.isNullOrEmpty(urlFromNavigationEndpoint)) {
            urlFromNavigationEndpoint = YoutubeParsingHelper.getUrlFromNavigationEndpoint(this.videoInfo.getObject("ownerText").getArray("runs").getObject(0).getObject("navigationEndpoint"));
            if (Utils.isNullOrEmpty(urlFromNavigationEndpoint)) {
                String urlFromNavigationEndpoint2 = YoutubeParsingHelper.getUrlFromNavigationEndpoint(this.videoInfo.getObject("shortBylineText").getArray("runs").getObject(0).getObject("navigationEndpoint"));
                if (Utils.isNullOrEmpty(urlFromNavigationEndpoint2)) {
                    throw new ParsingException("Could not get uploader url");
                }
                return urlFromNavigationEndpoint2;
            }
        }
        return urlFromNavigationEndpoint;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    @Nonnull
    public List<Image> getUploaderAvatars() throws ParsingException {
        boolean zHas = this.videoInfo.has("channelThumbnailSupportedRenderers");
        JsonObject jsonObject = this.videoInfo;
        if (zHas) {
            return YoutubeParsingHelper.getImagesFromThumbnailsArray(JsonUtils.getArray(jsonObject, "channelThumbnailSupportedRenderers.channelThumbnailWithLinkRenderer.thumbnail.thumbnails"));
        }
        if (jsonObject.has("channelThumbnail")) {
            return YoutubeParsingHelper.getImagesFromThumbnailsArray(JsonUtils.getArray(this.videoInfo, "channelThumbnail.thumbnails"));
        }
        return Collections.EMPTY_LIST;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public boolean isUploaderVerified() throws ParsingException {
        return YoutubeParsingHelper.isVerified(this.videoInfo.getArray("ownerBadges"));
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    @Nullable
    public String getTextualUploadDate() throws ParsingException {
        if (getStreamType() == StreamType.LIVE_STREAM) {
            return null;
        }
        if (isPremiere()) {
            return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(LocalDateTime.ofInstant(getInstantFromPremiere(), ZoneId.systemDefault()));
        }
        String textFromObject = YoutubeParsingHelper.getTextFromObject(this.videoInfo.getObject("publishedTimeText"));
        if (Utils.isNullOrEmpty(textFromObject) && this.videoInfo.has("videoInfo")) {
            textFromObject = this.videoInfo.getObject("videoInfo").getArray("runs").getObject(2).getString("text");
        }
        if (Utils.isNullOrEmpty(textFromObject)) {
            return null;
        }
        return textFromObject;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    @Nullable
    public DateWrapper getUploadDate() throws ParsingException {
        if (getStreamType() == StreamType.LIVE_STREAM) {
            return null;
        }
        if (isPremiere()) {
            return new DateWrapper(getInstantFromPremiere());
        }
        String textualUploadDate = getTextualUploadDate();
        if (this.timeAgoParser == null || Utils.isNullOrEmpty(textualUploadDate)) {
            return null;
        }
        try {
            return this.timeAgoParser.parse(textualUploadDate);
        } catch (ParsingException e) {
            throw new ParsingException("Could not get upload date", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public long getViewCount() throws ParsingException {
        if (!isPremium() && !isPremiere()) {
            String textFromObject = YoutubeParsingHelper.getTextFromObject(this.videoInfo.getObject("viewCountText"));
            if (!Utils.isNullOrEmpty(textFromObject)) {
                try {
                    return getViewCountFromViewCountText(textFromObject, false);
                } catch (Exception unused) {
                }
            }
            if (getStreamType() != StreamType.LIVE_STREAM) {
                try {
                    return getViewCountFromAccessibilityData();
                } catch (Exception unused2) {
                }
            }
            if (this.videoInfo.has("videoInfo")) {
                try {
                    return getViewCountFromViewCountText(this.videoInfo.getObject("videoInfo").getArray("runs").getObject(0).getString("text", ""), true);
                } catch (Exception unused3) {
                }
            }
            if (this.videoInfo.has("shortViewCountText")) {
                try {
                    String textFromObject2 = YoutubeParsingHelper.getTextFromObject(this.videoInfo.getObject("shortViewCountText"));
                    if (!Utils.isNullOrEmpty(textFromObject2)) {
                        return getViewCountFromViewCountText(textFromObject2, true);
                    }
                } catch (Exception unused4) {
                }
            }
        }
        return -1L;
    }

    private long getViewCountFromViewCountText(@Nonnull String str, boolean z) throws NumberFormatException, ParsingException {
        if (str.toLowerCase().contains(NO_VIEWS_LOWERCASE)) {
            return 0L;
        }
        if (str.toLowerCase().contains("recommended")) {
            return -1L;
        }
        if (z) {
            return Utils.mixedNumberWordToLong(str);
        }
        return Long.parseLong(Utils.removeNonDigitCharacters(str));
    }

    private long getViewCountFromAccessibilityData() throws Parser.RegexException, NumberFormatException {
        String string = this.videoInfo.getObject("title").getObject("accessibility").getObject("accessibilityData").getString("label", "");
        if (string.toLowerCase().endsWith(NO_VIEWS_LOWERCASE)) {
            return 0L;
        }
        return Long.parseLong(Utils.removeNonDigitCharacters(Parser.matchGroup1(ACCESSIBILITY_DATA_VIEW_COUNT_REGEX, string)));
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        return YoutubeParsingHelper.getThumbnailsFromInfoItem(this.videoInfo);
    }

    private boolean isPremium() {
        Iterator<Object> it = this.videoInfo.getArray("badges").iterator();
        while (it.hasNext()) {
            if (((JsonObject) it.next()).getObject("metadataBadgeRenderer").getString("label", "").equals("Premium")) {
                return true;
            }
        }
        return false;
    }

    private boolean isPremiere() {
        if (this.isPremiere == null) {
            this.isPremiere = Boolean.valueOf(this.videoInfo.has("upcomingEventData"));
        }
        return this.isPremiere.booleanValue();
    }

    private Instant getInstantFromPremiere() throws ParsingException {
        String string = this.videoInfo.getObject("upcomingEventData").getString("startTime");
        try {
            return Instant.ofEpochSecond(Long.parseLong(string));
        } catch (Exception e) {
            throw new ParsingException("Could not parse date from premiere: \"" + string + "\"", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    @Nullable
    public String getShortDescription() throws ParsingException {
        boolean zHas = this.videoInfo.has("detailedMetadataSnippets");
        JsonObject jsonObject = this.videoInfo;
        if (zHas) {
            return YoutubeParsingHelper.getTextFromObject(jsonObject.getArray("detailedMetadataSnippets").getObject(0).getObject("snippetText"));
        }
        if (jsonObject.has("descriptionSnippet")) {
            return YoutubeParsingHelper.getTextFromObject(this.videoInfo.getObject("descriptionSnippet"));
        }
        return null;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public boolean isShortFormContent() throws ParsingException {
        try {
            String string = this.videoInfo.getObject("navigationEndpoint").getObject("commandMetadata").getObject("webCommandMetadata").getString("webPageType");
            boolean zHas = !Utils.isNullOrEmpty(string) && string.equals("WEB_PAGE_TYPE_SHORTS");
            if (!zHas) {
                zHas = this.videoInfo.getObject("navigationEndpoint").has("reelWatchEndpoint");
            }
            return (zHas || !this.videoInfo.has("thumbnailOverlays")) ? zHas : this.videoInfo.getArray("thumbnailOverlays").streamAsJsonObjects().filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemExtractor$$ExternalSyntheticLambda5
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
                    return ((JsonObject) obj).has("thumbnailOverlayTimeStatusRenderer");
                }
            }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemExtractor$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public /* synthetic */ Function andThen(Function function) {
                    return Function$CC.$default$andThen(this, function);
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((JsonObject) obj).getObject("thumbnailOverlayTimeStatusRenderer");
                }

                public /* synthetic */ Function compose(Function function) {
                    return Function$CC.$default$compose(this, function);
                }
            }).anyMatch(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemExtractor$$ExternalSyntheticLambda7
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
                    return YoutubeStreamInfoItemExtractor.lambda$isShortFormContent$5((JsonObject) obj);
                }
            });
        } catch (Exception e) {
            throw new ParsingException("Could not determine if this is short-form content", e);
        }
    }

    static /* synthetic */ boolean lambda$isShortFormContent$5(JsonObject jsonObject) {
        return jsonObject.getString("style", "").equalsIgnoreCase("SHORTS") || jsonObject.getObject("icon").getString("iconType", "").toLowerCase().contains(ChannelTabs.SHORTS);
    }

    private boolean isMembersOnly() {
        return this.videoInfo.getArray("badges").streamAsJsonObjects().map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemExtractor$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getObject("metadataBadgeRenderer").getString("style");
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).anyMatch(new YoutubeStreamInfoItemExtractor$$ExternalSyntheticLambda1("BADGE_STYLE_TYPE_MEMBERS_ONLY"));
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    @Nonnull
    public ContentAvailability getContentAvailability() throws ParsingException {
        if (isPremiere()) {
            return ContentAvailability.UPCOMING;
        }
        if (isMembersOnly()) {
            return ContentAvailability.MEMBERSHIP;
        }
        if (isPremium()) {
            return ContentAvailability.PAID;
        }
        return ContentAvailability.AVAILABLE;
    }
}
