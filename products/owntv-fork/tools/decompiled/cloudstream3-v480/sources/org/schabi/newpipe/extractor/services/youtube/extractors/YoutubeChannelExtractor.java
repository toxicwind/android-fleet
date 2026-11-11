package org.schabi.newpipe.extractor.services.youtube.extractors;

import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.common.internal.ImagesContract;
import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonArray$$ExternalSyntheticLambda0;
import com.grack.nanojson.JsonObject;
import j$.util.Collection;
import j$.util.DesugarCollections;
import j$.util.Optional;
import j$.util.function.Consumer$CC;
import j$.util.function.Function$CC;
import j$.util.function.Predicate$CC;
import j$.util.stream.Collectors;
import java.io.IOException;
import java.util.ArrayList;
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
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.channel.ChannelExtractor;
import org.schabi.newpipe.extractor.channel.tabs.ChannelTabExtractor;
import org.schabi.newpipe.extractor.channel.tabs.ChannelTabs;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.linkhandler.ReadyChannelTabListLinkHandler;
import org.schabi.newpipe.extractor.services.youtube.YoutubeChannelHelper;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;
import org.schabi.newpipe.extractor.services.youtube.linkHandler.YoutubeChannelLinkHandlerFactory;
import org.schabi.newpipe.extractor.services.youtube.linkHandler.YoutubeChannelTabLinkHandlerFactory;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class YoutubeChannelExtractor extends ChannelExtractor {
    private static final String AVATAR = "avatar";
    private static final String BANNER = "banner";
    private static final String CONTENT = "content";
    private static final String CONTENTS = "contents";
    private static final String CONTENT_PREVIEW_IMAGE_VIEW_MODEL = "contentPreviewImageViewModel";
    private static final String IMAGE = "image";
    private static final String METADATA = "metadata";
    private static final String PAGE_HEADER_VIEW_MODEL = "pageHeaderViewModel";
    private static final String SOURCES = "sources";
    private static final String TAB_RENDERER = "tabRenderer";
    private static final String THUMBNAILS = "thumbnails";

    @Nullable
    private JsonObject channelAgeGateRenderer;

    @Nullable
    private YoutubeChannelHelper.ChannelHeader channelHeader;
    private String channelId;
    private JsonObject jsonResponse;

    public YoutubeChannelExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler) {
        super(streamingService, listLinkHandler);
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        YoutubeChannelHelper.ChannelResponseData channelResponse = YoutubeChannelHelper.getChannelResponse(YoutubeChannelHelper.resolveChannelId(super.getId()), "EgZ2aWRlb3PyBgQKAjoA", getExtractorLocalization(), getExtractorContentCountry());
        JsonObject jsonObject = channelResponse.jsonResponse;
        this.jsonResponse = jsonObject;
        this.channelHeader = YoutubeChannelHelper.getChannelHeader(jsonObject);
        this.channelId = channelResponse.channelId;
        this.channelAgeGateRenderer = YoutubeChannelHelper.getChannelAgeGateRenderer(this.jsonResponse);
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getUrl() throws ParsingException {
        try {
            return YoutubeChannelLinkHandlerFactory.getInstance().getUrl("channel/" + getId());
        } catch (ParsingException unused) {
            return super.getUrl();
        }
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getId() throws ParsingException {
        assertPageFetched();
        return YoutubeChannelHelper.getChannelId(this.channelHeader, this.jsonResponse, this.channelId);
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getName() throws ParsingException {
        assertPageFetched();
        return YoutubeChannelHelper.getChannelName(this.channelHeader, this.channelAgeGateRenderer, this.jsonResponse);
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    @Nonnull
    public List<Image> getAvatars() throws ParsingException {
        assertPageFetched();
        JsonObject jsonObject = this.channelAgeGateRenderer;
        if (jsonObject != null) {
            return (List) Optional.ofNullable(jsonObject.getObject(AVATAR).getArray(THUMBNAILS)).map(new YoutubeChannelExtractor$$ExternalSyntheticLambda11()).orElseThrow(new Supplier() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelExtractor$$ExternalSyntheticLambda2
                @Override // java.util.function.Supplier
                public final Object get() {
                    return YoutubeChannelExtractor.lambda$getAvatars$0();
                }
            });
        }
        return (List) Optional.ofNullable(this.channelHeader).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelExtractor$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return YoutubeChannelExtractor.lambda$getAvatars$1((YoutubeChannelHelper.ChannelHeader) obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).map(new YoutubeChannelExtractor$$ExternalSyntheticLambda11()).orElseThrow(new Supplier() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelExtractor$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return YoutubeChannelExtractor.lambda$getAvatars$2();
            }
        });
    }

    static /* synthetic */ ParsingException lambda$getAvatars$0() {
        return new ParsingException("Could not get avatars");
    }

    /* JADX INFO: renamed from: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelExtractor$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$schabi$newpipe$extractor$services$youtube$YoutubeChannelHelper$ChannelHeader$HeaderType;

        static {
            int[] iArr = new int[YoutubeChannelHelper.ChannelHeader.HeaderType.values().length];
            $SwitchMap$org$schabi$newpipe$extractor$services$youtube$YoutubeChannelHelper$ChannelHeader$HeaderType = iArr;
            try {
                iArr[YoutubeChannelHelper.ChannelHeader.HeaderType.PAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$schabi$newpipe$extractor$services$youtube$YoutubeChannelHelper$ChannelHeader$HeaderType[YoutubeChannelHelper.ChannelHeader.HeaderType.INTERACTIVE_TABBED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$schabi$newpipe$extractor$services$youtube$YoutubeChannelHelper$ChannelHeader$HeaderType[YoutubeChannelHelper.ChannelHeader.HeaderType.C4_TABBED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$schabi$newpipe$extractor$services$youtube$YoutubeChannelHelper$ChannelHeader$HeaderType[YoutubeChannelHelper.ChannelHeader.HeaderType.CAROUSEL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    static /* synthetic */ JsonArray lambda$getAvatars$1(YoutubeChannelHelper.ChannelHeader channelHeader) {
        int i = AnonymousClass1.$SwitchMap$org$schabi$newpipe$extractor$services$youtube$YoutubeChannelHelper$ChannelHeader$HeaderType[channelHeader.headerType.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return channelHeader.json.getObject("boxArt").getArray(THUMBNAILS);
            }
            return channelHeader.json.getObject(AVATAR).getArray(THUMBNAILS);
        }
        JsonObject object = channelHeader.json.getObject(CONTENT).getObject(PAGE_HEADER_VIEW_MODEL).getObject("image");
        if (object.has(CONTENT_PREVIEW_IMAGE_VIEW_MODEL)) {
            return object.getObject(CONTENT_PREVIEW_IMAGE_VIEW_MODEL).getObject("image").getArray(SOURCES);
        }
        if (object.has("decoratedAvatarViewModel")) {
            return object.getObject("decoratedAvatarViewModel").getObject(AVATAR).getObject("avatarViewModel").getObject("image").getArray(SOURCES);
        }
        return new JsonArray();
    }

    static /* synthetic */ ParsingException lambda$getAvatars$2() {
        return new ParsingException("Could not get avatars");
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    @Nonnull
    public List<Image> getBanners() {
        assertPageFetched();
        if (this.channelAgeGateRenderer != null) {
            return Collections.EMPTY_LIST;
        }
        return (List) Optional.ofNullable(this.channelHeader).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelExtractor$$ExternalSyntheticLambda10
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return YoutubeChannelExtractor.lambda$getBanners$3((YoutubeChannelHelper.ChannelHeader) obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).map(new YoutubeChannelExtractor$$ExternalSyntheticLambda11()).orElse(Collections.EMPTY_LIST);
    }

    static /* synthetic */ JsonArray lambda$getBanners$3(YoutubeChannelHelper.ChannelHeader channelHeader) {
        if (channelHeader.headerType == YoutubeChannelHelper.ChannelHeader.HeaderType.PAGE) {
            JsonObject object = channelHeader.json.getObject(CONTENT).getObject(PAGE_HEADER_VIEW_MODEL);
            if (object.has(BANNER)) {
                return object.getObject(BANNER).getObject("imageBannerViewModel").getObject("image").getArray(SOURCES);
            }
            return new JsonArray();
        }
        return channelHeader.json.getObject(BANNER).getArray(THUMBNAILS);
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    public String getFeedUrl() throws ParsingException {
        try {
            return YoutubeParsingHelper.getFeedUrlFrom(getId());
        } catch (Exception e) {
            throw new ParsingException("Could not get feed URL", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    public long getSubscriberCount() throws ParsingException {
        YoutubeChannelHelper.ChannelHeader channelHeader;
        JsonObject object;
        assertPageFetched();
        if (this.channelAgeGateRenderer != null || (channelHeader = this.channelHeader) == null || channelHeader.headerType == YoutubeChannelHelper.ChannelHeader.HeaderType.INTERACTIVE_TABBED) {
            return -1L;
        }
        JsonObject jsonObject = this.channelHeader.json;
        if (this.channelHeader.headerType == YoutubeChannelHelper.ChannelHeader.HeaderType.PAGE) {
            return getSubscriberCountFromPageChannelHeader(jsonObject);
        }
        if (jsonObject.has("subscriberCountText")) {
            object = jsonObject.getObject("subscriberCountText");
        } else {
            object = jsonObject.has(MediaTrack.ROLE_SUBTITLE) ? jsonObject.getObject(MediaTrack.ROLE_SUBTITLE) : null;
        }
        if (object != null) {
            try {
                return Utils.mixedNumberWordToLong(YoutubeParsingHelper.getTextFromObject(object));
            } catch (NumberFormatException e) {
                throw new ParsingException("Could not get subscriber count", e);
            }
        }
        return -1L;
    }

    private long getSubscriberCountFromPageChannelHeader(@Nonnull JsonObject jsonObject) throws ParsingException {
        JsonObject object = jsonObject.getObject(CONTENT).getObject(PAGE_HEADER_VIEW_MODEL).getObject("metadata");
        if (!object.has("contentMetadataViewModel")) {
            return -1L;
        }
        JsonArray array = object.getObject("contentMetadataViewModel").getArray("metadataRows");
        JsonArray array2 = array.getObject(Math.max(0, array.size() - 1)).getArray("metadataParts");
        if (array2.size() < 2) {
            return -1L;
        }
        try {
            return Utils.mixedNumberWordToLong(array2.getObject(0).getObject("text").getString(CONTENT));
        } catch (NumberFormatException e) {
            throw new ParsingException("Could not get subscriber count", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    public String getDescription() throws ParsingException {
        assertPageFetched();
        if (this.channelAgeGateRenderer != null) {
            return null;
        }
        try {
            YoutubeChannelHelper.ChannelHeader channelHeader = this.channelHeader;
            if (channelHeader != null && channelHeader.headerType == YoutubeChannelHelper.ChannelHeader.HeaderType.INTERACTIVE_TABBED) {
                return YoutubeParsingHelper.getTextFromObject(this.channelHeader.json.getObject("description"));
            }
            return this.jsonResponse.getObject("metadata").getObject("channelMetadataRenderer").getString("description");
        } catch (Exception e) {
            throw new ParsingException("Could not get channel description", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    public String getParentChannelName() {
        return "";
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    public String getParentChannelUrl() {
        return "";
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    @Nonnull
    public List<Image> getParentChannelAvatars() {
        return Collections.EMPTY_LIST;
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    public boolean isVerified() throws ParsingException {
        assertPageFetched();
        if (this.channelAgeGateRenderer != null) {
            return false;
        }
        YoutubeChannelHelper.ChannelHeader channelHeader = this.channelHeader;
        if (channelHeader == null) {
            throw new ParsingException("Could not get channel verified status, no channel header has been extracted");
        }
        return YoutubeChannelHelper.isChannelVerified(channelHeader);
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    @Nonnull
    public List<ListLinkHandler> getTabs() throws ParsingException {
        assertPageFetched();
        if (this.channelAgeGateRenderer == null) {
            return getTabsForNonAgeRestrictedChannels();
        }
        return getTabsForAgeRestrictedChannels();
    }

    @Nonnull
    private List<ListLinkHandler> getTabsForNonAgeRestrictedChannels() throws ParsingException {
        JsonArray array = this.jsonResponse.getObject(CONTENTS).getObject("twoColumnBrowseResultsRenderer").getArray("tabs");
        final ArrayList arrayList = new ArrayList();
        final Consumer consumer = new Consumer() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelExtractor$$ExternalSyntheticLambda6
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m10974x17074068(arrayList, (String) obj);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer2) {
                return Consumer$CC.$default$andThen(this, consumer2);
            }
        };
        final String name = getName();
        final String url = getUrl();
        final String id = getId();
        array.streamAsJsonObjects().filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelExtractor$$ExternalSyntheticLambda7
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
                return ((JsonObject) obj).has(YoutubeChannelExtractor.TAB_RENDERER);
            }
        }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelExtractor$$ExternalSyntheticLambda8
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getObject(YoutubeChannelExtractor.TAB_RENDERER);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).forEach(new Consumer() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelExtractor$$ExternalSyntheticLambda9
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m10975xa31b7ec5(arrayList, name, id, url, consumer, (JsonObject) obj);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer2) {
                return Consumer$CC.$default$andThen(this, consumer2);
            }
        });
        return DesugarCollections.unmodifiableList(arrayList);
    }

    /* JADX INFO: renamed from: lambda$getTabsForNonAgeRestrictedChannels$4$org-schabi-newpipe-extractor-services-youtube-extractors-YoutubeChannelExtractor, reason: not valid java name */
    /* synthetic */ void m10974x17074068(List list, String str) {
        try {
            list.add(YoutubeChannelTabLinkHandlerFactory.getInstance().fromQuery(this.channelId, UByte$$ExternalSyntheticBackport0.m(new Object[]{str}), ""));
        } catch (ParsingException unused) {
        }
    }

    /* JADX INFO: renamed from: lambda$getTabsForNonAgeRestrictedChannels$7$org-schabi-newpipe-extractor-services-youtube-extractors-YoutubeChannelExtractor, reason: not valid java name */
    /* synthetic */ void m10975xa31b7ec5(List list, String str, String str2, String str3, Consumer consumer, JsonObject jsonObject) {
        String string = jsonObject.getObject("endpoint").getObject("commandMetadata").getObject("webCommandMetadata").getString(ImagesContract.URL);
        if (string != null) {
            String[] strArrSplit = string.split("/");
            if (strArrSplit.length == 0) {
                return;
            }
            byte b = 1;
            String str4 = strArrSplit[strArrSplit.length - 1];
            YoutubeChannelHelper.ChannelHeader channelHeader = this.channelHeader == null ? null : new YoutubeChannelHelper.ChannelHeader(this.channelHeader.json, this.channelHeader.headerType);
            str4.hashCode();
            switch (str4.hashCode()) {
                case -1881890573:
                    b = !str4.equals("streams") ? (byte) -1 : (byte) 0;
                    break;
                case -1865828127:
                    if (!str4.equals("playlists")) {
                        b = -1;
                    }
                    break;
                case -903148681:
                    b = !str4.equals(ChannelTabs.SHORTS) ? (byte) -1 : (byte) 2;
                    break;
                case -816678056:
                    b = !str4.equals("videos") ? (byte) -1 : (byte) 3;
                    break;
                case -551298740:
                    b = !str4.equals("releases") ? (byte) -1 : (byte) 4;
                    break;
                default:
                    b = -1;
                    break;
            }
            switch (b) {
                case 0:
                    consumer.accept(ChannelTabs.LIVESTREAMS);
                    break;
                case 1:
                    consumer.accept("playlists");
                    break;
                case 2:
                    consumer.accept(ChannelTabs.SHORTS);
                    break;
                case 3:
                    list.add(0, new ReadyChannelTabListLinkHandler(string, this.channelId, "videos", new YoutubeChannelExtractor$$ExternalSyntheticLambda5(jsonObject, channelHeader, str, str2, str3)));
                    break;
                case 4:
                    consumer.accept(ChannelTabs.ALBUMS);
                    break;
            }
        }
    }

    static /* synthetic */ ChannelTabExtractor lambda$getTabsForNonAgeRestrictedChannels$3bf8a84$1(JsonObject jsonObject, YoutubeChannelHelper.ChannelHeader channelHeader, String str, String str2, String str3, StreamingService streamingService, ListLinkHandler listLinkHandler) {
        return new YoutubeChannelTabExtractor.VideosTabExtractor(streamingService, listLinkHandler, jsonObject, channelHeader, str, str2, str3);
    }

    @Nonnull
    private List<ListLinkHandler> getTabsForAgeRestrictedChannels() throws ParsingException {
        final ArrayList arrayList = new ArrayList();
        final String url = getUrl();
        Consumer consumer = new Consumer() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelExtractor$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m10973x354ee2db(arrayList, url, (String) obj);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer2) {
                return Consumer$CC.$default$andThen(this, consumer2);
            }
        };
        consumer.accept("videos");
        consumer.accept(ChannelTabs.SHORTS);
        consumer.accept(ChannelTabs.LIVESTREAMS);
        return DesugarCollections.unmodifiableList(arrayList);
    }

    /* JADX INFO: renamed from: lambda$getTabsForAgeRestrictedChannels$8$org-schabi-newpipe-extractor-services-youtube-extractors-YoutubeChannelExtractor, reason: not valid java name */
    /* synthetic */ void m10973x354ee2db(List list, String str, String str2) {
        list.add(new ReadyChannelTabListLinkHandler(str + "/" + str2, this.channelId, str2, new YoutubeChannelExtractor$$ExternalSyntheticLambda12()));
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelExtractor
    @Nonnull
    public List<String> getTags() throws ParsingException {
        assertPageFetched();
        if (this.channelAgeGateRenderer != null) {
            return Collections.EMPTY_LIST;
        }
        return (List) Collection.EL.stream(this.jsonResponse.getObject("microformat").getObject("microformatDataRenderer").getArray("tags")).filter(new JsonArray$$ExternalSyntheticLambda0(String.class)).map(new YoutubeChannelExtractor$$ExternalSyntheticLambda1(String.class)).collect(Collectors.toUnmodifiableList());
    }
}
