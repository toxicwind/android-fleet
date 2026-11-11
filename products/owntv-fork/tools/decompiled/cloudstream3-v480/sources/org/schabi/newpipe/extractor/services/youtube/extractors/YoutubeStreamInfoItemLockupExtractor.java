package org.schabi.newpipe.extractor.services.youtube.extractors;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ImagesContract;
import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import j$.time.LocalDateTime;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import j$.time.format.DateTimeFormatter;
import j$.time.format.DateTimeParseException;
import j$.util.function.Function$CC;
import j$.util.function.Predicate$CC;
import j$.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.localization.DateWrapper;
import org.schabi.newpipe.extractor.localization.TimeAgoParser;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;
import org.schabi.newpipe.extractor.services.youtube.linkHandler.YoutubeChannelLinkHandlerFactory;
import org.schabi.newpipe.extractor.services.youtube.linkHandler.YoutubeStreamLinkHandlerFactory;
import org.schabi.newpipe.extractor.stream.ContentAvailability;
import org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor;
import org.schabi.newpipe.extractor.stream.StreamType;
import org.schabi.newpipe.extractor.utils.JsonUtils;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class YoutubeStreamInfoItemLockupExtractor implements StreamInfoItemExtractor {
    private static final String NO_VIEWS_LOWERCASE = "no views";
    private static final DateTimeFormatter PREMIERES_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm");
    private static final String PREMIERES_LIVES_TEXT = "Scheduled for ";
    private static final String PREMIERES_VIDEOS_TEXT = "Premieres ";
    private ChannelImageViewModel cachedChannelImageViewModel;
    private String cachedDateText;
    private final JsonArray cachedMetadataRows;
    private String cachedName;
    private StreamType cachedStreamType;
    private final JsonObject lockupViewModel;
    private final TimeAgoParser timeAgoParser;

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public /* synthetic */ String getShortDescription() {
        return StreamInfoItemExtractor.CC.$default$getShortDescription(this);
    }

    protected boolean isChannelOrCoursePlaylistLockupItem() {
        return false;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public /* synthetic */ boolean isShortFormContent() {
        return StreamInfoItemExtractor.CC.$default$isShortFormContent(this);
    }

    public YoutubeStreamInfoItemLockupExtractor(@Nonnull JsonObject jsonObject, @Nullable TimeAgoParser timeAgoParser) {
        this.lockupViewModel = jsonObject;
        this.timeAgoParser = timeAgoParser;
        this.cachedMetadataRows = jsonObject.getObject(TtmlNode.TAG_METADATA).getObject("lockupMetadataViewModel").getObject(TtmlNode.TAG_METADATA).getObject("contentMetadataViewModel").getArray("metadataRows");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public StreamType getStreamType() throws ParsingException {
        if (this.cachedStreamType == null) {
            this.cachedStreamType = determineStreamType();
        }
        return this.cachedStreamType;
    }

    private StreamType determineStreamType() throws ParsingException {
        JsonArray array = JsonUtils.getArray(this.lockupViewModel, "contentImage.thumbnailViewModel.overlays");
        if (array.streamAsJsonObjects().flatMap(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemLockupExtractor$$ExternalSyntheticLambda7
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getObject("thumbnailOverlayBadgeViewModel").getArray("thumbnailBadges").streamAsJsonObjects();
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemLockupExtractor$$ExternalSyntheticLambda8
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getObject("thumbnailBadgeViewModel");
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).anyMatch(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemLockupExtractor$$ExternalSyntheticLambda9
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
                return YoutubeStreamInfoItemLockupExtractor.lambda$determineStreamType$3((JsonObject) obj);
            }
        })) {
            return StreamType.LIVE_STREAM;
        }
        if (array.streamAsJsonObjects().flatMap(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemLockupExtractor$$ExternalSyntheticLambda10
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getObject("thumbnailBottomOverlayViewModel").getArray("badges").streamAsJsonObjects();
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemLockupExtractor$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getObject("thumbnailBadgeViewModel");
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).anyMatch(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemLockupExtractor$$ExternalSyntheticLambda2
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
                return "THUMBNAIL_OVERLAY_BADGE_STYLE_LIVE".equals(((JsonObject) obj).getString("badgeStyle"));
            }
        })) {
            return StreamType.LIVE_STREAM;
        }
        return StreamType.VIDEO_STREAM;
    }

    static /* synthetic */ boolean lambda$determineStreamType$3(JsonObject jsonObject) {
        if ("THUMBNAIL_OVERLAY_BADGE_STYLE_LIVE".equals(jsonObject.getString("badgeStyle"))) {
            return true;
        }
        return jsonObject.getObject("icon").getArray("sources").streamAsJsonObjects().map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemLockupExtractor$$ExternalSyntheticLambda6
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getObject("clientResource").getString("imageName");
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).anyMatch(new YoutubeStreamInfoItemExtractor$$ExternalSyntheticLambda1("LIVE"));
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public boolean isAd() throws ParsingException {
        String name = getName();
        return "[Private video]".equals(name) || "[Deleted video]".equals(name);
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getUrl() throws ParsingException {
        try {
            String string = this.lockupViewModel.getString("contentId");
            if (Utils.isNullOrEmpty(string)) {
                string = JsonUtils.getString(this.lockupViewModel, "rendererContext.commandContext.onTap.innertubeCommand.watchEndpoint.videoId");
            }
            return YoutubeStreamLinkHandlerFactory.getInstance().getUrl(string);
        } catch (Exception e) {
            throw new ParsingException("Could not get url", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getName() throws ParsingException {
        String str = this.cachedName;
        if (str != null) {
            return str;
        }
        String string = JsonUtils.getString(this.lockupViewModel, "metadata.lockupMetadataViewModel.title.content");
        if (!Utils.isNullOrEmpty(string)) {
            this.cachedName = string;
            return string;
        }
        throw new ParsingException("Could not get name");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public long getDuration() throws ParsingException {
        if (isLive() || isPremiere()) {
            return -1L;
        }
        List list = (List) JsonUtils.getArray(this.lockupViewModel, "contentImage.thumbnailViewModel.overlays").streamAsJsonObjects().flatMap(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemLockupExtractor$$ExternalSyntheticLambda4
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getObject("thumbnailBottomOverlayViewModel").getArray("badges").streamAsJsonObjects();
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemLockupExtractor$$ExternalSyntheticLambda5
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getObject("thumbnailBadgeViewModel").getString("text");
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).collect(Collectors.toList());
        if (list.isEmpty()) {
            return -1L;
        }
        Iterator it = list.iterator();
        ParsingException e = null;
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str != null && str.matches(".*\\d.*")) {
                try {
                    return YoutubeParsingHelper.parseDurationString(str);
                } catch (ParsingException e2) {
                    e = e2;
                }
            }
        }
        if (e == null) {
            return -1L;
        }
        throw new ParsingException("Could not get duration", e);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public String getUploaderName() throws ParsingException {
        List<JsonArray> metadataPartsFromMetadataRows = getMetadataPartsFromMetadataRows();
        if (metadataPartsFromMetadataRows.isEmpty()) {
            throw new ParsingException("Could not get uploader name: no metadata row");
        }
        String textContentFromMetadataPart = getTextContentFromMetadataPart(metadataPartsFromMetadataRows.get(0).getObject(0));
        if (Utils.isNullOrEmpty(textContentFromMetadataPart)) {
            throw new ParsingException("Could not get uploader name");
        }
        return textContentFromMetadataPart;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public String getUploaderUrl() throws ParsingException {
        JsonObject object = channelImageViewModel().forUploaderUrlExtraction().getObject("rendererContext").getObject("commandContext").getObject("onTap").getObject("innertubeCommand");
        JsonObject object2 = object.getObject("browseEndpoint");
        String string = object2.getString("browseId");
        if (string != null && string.startsWith("UC")) {
            return YoutubeChannelLinkHandlerFactory.getInstance().getUrl("channel/" + string);
        }
        String string2 = object2.getString("canonicalBaseUrl");
        if (!Utils.isNullOrEmpty(string2)) {
            return resolveUploaderUrlFromRelativeUrl(string2);
        }
        String string3 = object.getObject("commandMetadata").getObject("webCommandMetadata").getString(ImagesContract.URL);
        if (!Utils.isNullOrEmpty(string3)) {
            return resolveUploaderUrlFromRelativeUrl(string3);
        }
        throw new ParsingException("Could not get uploader url");
    }

    private String resolveUploaderUrlFromRelativeUrl(@Nonnull String str) throws ParsingException {
        YoutubeChannelLinkHandlerFactory youtubeChannelLinkHandlerFactory = YoutubeChannelLinkHandlerFactory.getInstance();
        if (str.startsWith("/")) {
            str = str.substring(1);
        }
        return youtubeChannelLinkHandlerFactory.getUrl(str);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    @Nonnull
    public List<Image> getUploaderAvatars() throws ParsingException {
        return YoutubeParsingHelper.getImagesFromThumbnailsArray(JsonUtils.getArray(channelImageViewModel().forAvatarExtraction(), "avatarViewModel.image.sources"));
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public boolean isUploaderVerified() throws ParsingException {
        List<JsonArray> metadataPartsFromMetadataRows = getMetadataPartsFromMetadataRows();
        if (metadataPartsFromMetadataRows.isEmpty()) {
            throw new ParsingException("Could not get uploader verified status: no metadata row");
        }
        return YoutubeParsingHelper.hasArtistOrVerifiedIconBadgeAttachment(metadataPartsFromMetadataRows.get(0).getObject(0).getObject("text").getArray("attachmentRuns"));
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    @Nullable
    public String getTextualUploadDate() throws ParsingException {
        if (isLive()) {
            return null;
        }
        String dateText = getDateText();
        return isPremiere() ? getDateFromPremiere(dateText) : dateText;
    }

    @Nonnull
    private String getDateFromPremiere(@Nonnull String str) {
        return str.replace(PREMIERES_VIDEOS_TEXT, "").replace(PREMIERES_LIVES_TEXT, "");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    @Nullable
    public DateWrapper getUploadDate() throws ParsingException {
        String textualUploadDate;
        if (this.timeAgoParser == null || (textualUploadDate = getTextualUploadDate()) == null) {
            return null;
        }
        if (isPremiere()) {
            try {
                return new DateWrapper(LocalDateTime.parse(getDateFromPremiere(getDateText()), PREMIERES_DATE_FORMATTER).atZone((ZoneId) ZoneOffset.UTC).toInstant(), false);
            } catch (DateTimeParseException e) {
                throw new ParsingException("Could not parse premiere upload date", e);
            }
        }
        return this.timeAgoParser.parse(textualUploadDate);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public long getViewCount() throws ParsingException {
        if (isChannelsMembersOnlyOrFirst()) {
            return -1L;
        }
        List<JsonArray> metadataPartsFromMetadataRows = getMetadataPartsFromMetadataRows();
        if (metadataPartsFromMetadataRows.isEmpty()) {
            if (isLive() && isChannelOrCoursePlaylistLockupItem()) {
                return 0L;
            }
            throw new ParsingException("Could not get view count: no metadata part from metadata rows");
        }
        if (isPremiere()) {
            return -1L;
        }
        if (isLive() && metadataPartsFromMetadataRows.size() == 1 && !isChannelOrCoursePlaylistLockupItem()) {
            return 0L;
        }
        JsonArray jsonArray = metadataPartsFromMetadataRows.get(metadataPartsFromMetadataRows.size() - 1);
        if (jsonArray.isEmpty()) {
            throw new ParsingException("Could not get view count: no metadata part in the metadata parts array");
        }
        String textContentFromMetadataPart = getTextContentFromMetadataPart(jsonArray.getObject(0));
        if (Utils.isNullOrEmpty(textContentFromMetadataPart)) {
            throw new ParsingException("Could not get view count");
        }
        return getViewCountFromViewCountText(textContentFromMetadataPart);
    }

    private long getViewCountFromViewCountText(@Nonnull String str) throws NumberFormatException, ParsingException {
        if (str.toLowerCase().contains(NO_VIEWS_LOWERCASE)) {
            return 0L;
        }
        if (str.toLowerCase().contains("recommended")) {
            return -1L;
        }
        return Utils.mixedNumberWordToLong(str);
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        return YoutubeParsingHelper.getImagesFromThumbnailsArray(JsonUtils.getArray(this.lockupViewModel, "contentImage.thumbnailViewModel.image.sources"));
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    @Nonnull
    public ContentAvailability getContentAvailability() throws ParsingException {
        if (isChannelsMembersOnlyOrFirst()) {
            return ContentAvailability.MEMBERSHIP;
        }
        if (isLive()) {
            return ContentAvailability.AVAILABLE;
        }
        if (isPremiere()) {
            return ContentAvailability.UPCOMING;
        }
        return ContentAvailability.AVAILABLE;
    }

    private ChannelImageViewModel channelImageViewModel() throws ParsingException {
        if (this.cachedChannelImageViewModel == null) {
            this.cachedChannelImageViewModel = determineChannelImageViewModel();
        }
        return this.cachedChannelImageViewModel;
    }

    @Nonnull
    private ChannelImageViewModel determineChannelImageViewModel() throws ParsingException {
        JsonObject object = this.lockupViewModel.getObject(TtmlNode.TAG_METADATA).getObject("lockupMetadataViewModel").getObject("image");
        JsonObject object2 = object.getObject("decoratedAvatarViewModel", null);
        if (object2 != null) {
            return new SingleChannelImageViewModel(object2);
        }
        JsonObject object3 = object.getObject("avatarStackViewModel", null);
        if (object3 != null) {
            return new MultiChannelImageViewModel(object3);
        }
        throw new ParsingException("Failed to determine channel image view model");
    }

    @Nullable
    private String getTextContentFromMetadataPart(@Nonnull JsonObject jsonObject) {
        return jsonObject.getObject("text").getString("content");
    }

    private boolean isLive() throws ParsingException {
        return getStreamType() != StreamType.VIDEO_STREAM;
    }

    private boolean isChannelsMembersOnlyOrFirst() {
        return this.cachedMetadataRows.streamAsJsonObjects().flatMap(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemLockupExtractor$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getArray("badges").streamAsJsonObjects();
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemLockupExtractor$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getObject("badgeViewModel").getString("badgeStyle");
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).anyMatch(new YoutubeStreamInfoItemExtractor$$ExternalSyntheticLambda1("BADGE_MEMBERS_ONLY"));
    }

    private boolean isPremiere() throws ParsingException {
        String dateText = getDateText();
        return dateText.contains(PREMIERES_VIDEOS_TEXT) || dateText.contains(PREMIERES_LIVES_TEXT);
    }

    private String getDateText() throws ParsingException {
        String str = this.cachedDateText;
        if (str != null) {
            return str;
        }
        List<JsonArray> metadataPartsFromMetadataRows = getMetadataPartsFromMetadataRows();
        if (metadataPartsFromMetadataRows.isEmpty()) {
            throw new ParsingException("Could not get date text: no metadata part from metadata rows");
        }
        JsonArray jsonArray = metadataPartsFromMetadataRows.get(metadataPartsFromMetadataRows.size() - 1);
        if (jsonArray.isEmpty()) {
            throw new ParsingException("Could not get date text: no metadata part in the metadata parts array");
        }
        String textContentFromMetadataPart = getTextContentFromMetadataPart(jsonArray.getObject(jsonArray.size() - 1));
        this.cachedDateText = textContentFromMetadataPart;
        return textContentFromMetadataPart;
    }

    @Nonnull
    private List<JsonArray> getMetadataPartsFromMetadataRows() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.cachedMetadataRows.size(); i++) {
            JsonObject object = this.cachedMetadataRows.getObject(i);
            if (object.has("metadataParts")) {
                arrayList.add(object.getArray("metadataParts"));
            }
        }
        return arrayList;
    }

    static abstract class ChannelImageViewModel {
        protected JsonObject viewModel;

        public abstract JsonObject forAvatarExtraction();

        public abstract JsonObject forUploaderUrlExtraction();

        protected ChannelImageViewModel(JsonObject jsonObject) {
            this.viewModel = jsonObject;
        }
    }

    static class SingleChannelImageViewModel extends ChannelImageViewModel {
        SingleChannelImageViewModel(JsonObject jsonObject) {
            super(jsonObject);
        }

        @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemLockupExtractor.ChannelImageViewModel
        public JsonObject forUploaderUrlExtraction() {
            return this.viewModel;
        }

        @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemLockupExtractor.ChannelImageViewModel
        public JsonObject forAvatarExtraction() {
            return this.viewModel.getObject("avatar");
        }
    }

    static class MultiChannelImageViewModel extends ChannelImageViewModel {
        MultiChannelImageViewModel(JsonObject jsonObject) {
            super(jsonObject);
        }

        @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemLockupExtractor.ChannelImageViewModel
        public JsonObject forUploaderUrlExtraction() {
            return (JsonObject) this.viewModel.getObject("rendererContext").getObject("commandContext").getObject("onTap").getObject("innertubeCommand").getObject("showDialogCommand").getObject("panelLoadingStrategy").getObject("inlineContent").getObject("dialogViewModel").getObject("customContent").getObject("listViewModel").getArray("listItems").streamAsJsonObjects().map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemLockupExtractor$MultiChannelImageViewModel$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public /* synthetic */ Function andThen(Function function) {
                    return Function$CC.$default$andThen(this, function);
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((JsonObject) obj).getObject("listItemViewModel");
                }

                public /* synthetic */ Function compose(Function function) {
                    return Function$CC.$default$compose(this, function);
                }
            }).findFirst().orElse(null);
        }

        @Override // org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemLockupExtractor.ChannelImageViewModel
        public JsonObject forAvatarExtraction() {
            return this.viewModel.getArray("avatars").getObject(0);
        }
    }
}
