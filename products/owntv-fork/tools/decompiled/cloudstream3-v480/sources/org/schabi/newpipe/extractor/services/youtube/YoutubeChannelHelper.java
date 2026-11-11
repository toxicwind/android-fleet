package org.schabi.newpipe.extractor.services.youtube;

import androidx.core.app.NotificationCompat;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.google.android.gms.common.internal.ImagesContract;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonWriter;
import io.ktor.http.ContentType;
import j$.util.Optional;
import j$.util.function.Function$CC;
import j$.util.function.Predicate$CC;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.exceptions.ContentNotAvailableException;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.localization.ContentCountry;
import org.schabi.newpipe.extractor.localization.Localization;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public final class YoutubeChannelHelper {
    private static final String BROWSE_ENDPOINT = "browseEndpoint";
    private static final String BROWSE_ID = "browseId";
    private static final String C4_TABBED_HEADER_RENDERER = "c4TabbedHeaderRenderer";
    private static final String CAROUSEL_HEADER_RENDERER = "carouselHeaderRenderer";
    private static final String CONTENT = "content";
    private static final String CONTENTS = "contents";
    private static final String HEADER = "header";
    private static final String PAGE_HEADER_VIEW_MODEL = "pageHeaderViewModel";
    private static final String TAB_RENDERER = "tabRenderer";
    private static final String TITLE = "title";
    private static final String TOPIC_CHANNEL_DETAILS_RENDERER = "topicChannelDetailsRenderer";

    private YoutubeChannelHelper() {
    }

    @Nonnull
    public static String resolveChannelId(@Nonnull String str) throws IOException, ExtractionException {
        String[] strArrSplit = str.split("/");
        int i = 0;
        if (strArrSplit[0].startsWith("UC")) {
            return strArrSplit[0];
        }
        if (!strArrSplit[0].equals(TvContractCompat.PARAM_CHANNEL)) {
            String string = "https://www.youtube.com/" + str;
            JsonObject jsonObject = new JsonObject();
            String str2 = "";
            while (string != null && i < 3) {
                JsonObject jsonPostResponse = YoutubeParsingHelper.getJsonPostResponse("navigation/resolve_url", JsonWriter.string(YoutubeParsingHelper.prepareDesktopJsonBuilder(Localization.DEFAULT, ContentCountry.DEFAULT).value(ImagesContract.URL, string).done()).getBytes(StandardCharsets.UTF_8), Localization.DEFAULT);
                checkIfChannelResponseIsValid(jsonPostResponse);
                jsonObject = jsonPostResponse.getObject("endpoint");
                String string2 = jsonObject.getObject("commandMetadata").getObject("webCommandMetadata").getString("webPageType");
                i++;
                str2 = string2;
                string = "WEB_PAGE_TYPE_UNKNOWN".equals(string2) ? jsonObject.getObject("urlEndpoint").getString(ImagesContract.URL) : null;
            }
            String string3 = jsonObject.getObject(BROWSE_ENDPOINT).getString(BROWSE_ID, "");
            if (("WEB_PAGE_TYPE_BROWSE".equalsIgnoreCase(str2) || "WEB_PAGE_TYPE_CHANNEL".equalsIgnoreCase(str2)) && !string3.isEmpty()) {
                if (string3.startsWith("UC")) {
                    return string3;
                }
                throw new ExtractionException("Redirected id is not pointing to a channel");
            }
            if (strArrSplit.length < 2) {
                throw new ExtractionException("Failed to resolve channelId for " + str);
            }
        }
        return strArrSplit[1];
    }

    public static final class ChannelResponseData {

        @Nonnull
        public final String channelId;

        @Nonnull
        public final JsonObject jsonResponse;

        private ChannelResponseData(@Nonnull JsonObject jsonObject, @Nonnull String str) {
            this.jsonResponse = jsonObject;
            this.channelId = str;
        }
    }

    @Nonnull
    public static ChannelResponseData getChannelResponse(@Nonnull String str, @Nonnull String str2, @Nonnull Localization localization, @Nonnull ContentCountry contentCountry) throws IOException, ExtractionException {
        JsonObject jsonPostResponse;
        int i = 0;
        while (true) {
            if (i >= 3) {
                jsonPostResponse = null;
                break;
            }
            jsonPostResponse = YoutubeParsingHelper.getJsonPostResponse("browse", JsonWriter.string(YoutubeParsingHelper.prepareDesktopJsonBuilder(localization, contentCountry).value(BROWSE_ID, str).value("params", str2).done()).getBytes(StandardCharsets.UTF_8), localization);
            checkIfChannelResponseIsValid(jsonPostResponse);
            JsonObject object = jsonPostResponse.getArray("onResponseReceivedActions").getObject(0).getObject("navigateAction").getObject("endpoint");
            String string = object.getObject("commandMetadata").getObject("webCommandMetadata").getString("webPageType");
            String string2 = object.getObject(BROWSE_ENDPOINT).getString(BROWSE_ID, "");
            if ((!"WEB_PAGE_TYPE_BROWSE".equalsIgnoreCase(string) && !"WEB_PAGE_TYPE_CHANNEL".equalsIgnoreCase(string)) || string2.isEmpty()) {
                break;
            }
            if (!string2.startsWith("UC")) {
                throw new ExtractionException("Redirected id is not pointing to a channel");
            }
            i++;
            str = string2;
        }
        if (jsonPostResponse == null) {
            throw new ExtractionException("Got no channel response after 3 redirects");
        }
        YoutubeParsingHelper.defaultAlertsCheck(jsonPostResponse);
        return new ChannelResponseData(jsonPostResponse, str);
    }

    private static void checkIfChannelResponseIsValid(@Nonnull JsonObject jsonObject) throws ContentNotAvailableException {
        if (Utils.isNullOrEmpty(jsonObject.getObject(MediaRouteProviderProtocol.SERVICE_DATA_ERROR))) {
            return;
        }
        JsonObject object = jsonObject.getObject(MediaRouteProviderProtocol.SERVICE_DATA_ERROR);
        if (object.getInt("code") == 404) {
            throw new ContentNotAvailableException("This channel doesn't exist.");
        }
        throw new ContentNotAvailableException("Got error:\"" + object.getString(NotificationCompat.CATEGORY_STATUS) + "\": " + object.getString(ContentType.Message.TYPE));
    }

    public static final class ChannelHeader implements Serializable {
        public final HeaderType headerType;

        @Nonnull
        public final JsonObject json;

        public enum HeaderType {
            C4_TABBED,
            INTERACTIVE_TABBED,
            CAROUSEL,
            PAGE
        }

        public ChannelHeader(@Nonnull JsonObject jsonObject, HeaderType headerType) {
            this.json = jsonObject;
            this.headerType = headerType;
        }
    }

    @Nullable
    public static ChannelHeader getChannelHeader(@Nonnull JsonObject jsonObject) {
        JsonObject object = jsonObject.getObject(HEADER);
        if (object.has(C4_TABBED_HEADER_RENDERER)) {
            return (ChannelHeader) Optional.of(object.getObject(C4_TABBED_HEADER_RENDERER)).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeChannelHelper$$ExternalSyntheticLambda7
                @Override // java.util.function.Function
                public /* synthetic */ Function andThen(Function function) {
                    return Function$CC.$default$andThen(this, function);
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return YoutubeChannelHelper.lambda$getChannelHeader$0((JsonObject) obj);
                }

                public /* synthetic */ Function compose(Function function) {
                    return Function$CC.$default$compose(this, function);
                }
            }).orElse(null);
        }
        if (object.has(CAROUSEL_HEADER_RENDERER)) {
            return (ChannelHeader) object.getObject(CAROUSEL_HEADER_RENDERER).getArray(CONTENTS).streamAsJsonObjects().filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeChannelHelper$$ExternalSyntheticLambda8
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
                    return ((JsonObject) obj).has(YoutubeChannelHelper.TOPIC_CHANNEL_DETAILS_RENDERER);
                }
            }).findFirst().map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeChannelHelper$$ExternalSyntheticLambda9
                @Override // java.util.function.Function
                public /* synthetic */ Function andThen(Function function) {
                    return Function$CC.$default$andThen(this, function);
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((JsonObject) obj).getObject(YoutubeChannelHelper.TOPIC_CHANNEL_DETAILS_RENDERER);
                }

                public /* synthetic */ Function compose(Function function) {
                    return Function$CC.$default$compose(this, function);
                }
            }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeChannelHelper$$ExternalSyntheticLambda10
                @Override // java.util.function.Function
                public /* synthetic */ Function andThen(Function function) {
                    return Function$CC.$default$andThen(this, function);
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return YoutubeChannelHelper.lambda$getChannelHeader$3((JsonObject) obj);
                }

                public /* synthetic */ Function compose(Function function) {
                    return Function$CC.$default$compose(this, function);
                }
            }).orElse(null);
        }
        if (object.has("pageHeaderRenderer")) {
            return (ChannelHeader) Optional.of(object.getObject("pageHeaderRenderer")).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeChannelHelper$$ExternalSyntheticLambda11
                @Override // java.util.function.Function
                public /* synthetic */ Function andThen(Function function) {
                    return Function$CC.$default$andThen(this, function);
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return YoutubeChannelHelper.lambda$getChannelHeader$4((JsonObject) obj);
                }

                public /* synthetic */ Function compose(Function function) {
                    return Function$CC.$default$compose(this, function);
                }
            }).orElse(null);
        }
        if (object.has("interactiveTabbedHeaderRenderer")) {
            return (ChannelHeader) Optional.of(object.getObject("interactiveTabbedHeaderRenderer")).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeChannelHelper$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public /* synthetic */ Function andThen(Function function) {
                    return Function$CC.$default$andThen(this, function);
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return YoutubeChannelHelper.lambda$getChannelHeader$5((JsonObject) obj);
                }

                public /* synthetic */ Function compose(Function function) {
                    return Function$CC.$default$compose(this, function);
                }
            }).orElse(null);
        }
        return null;
    }

    static /* synthetic */ ChannelHeader lambda$getChannelHeader$0(JsonObject jsonObject) {
        return new ChannelHeader(jsonObject, ChannelHeader.HeaderType.C4_TABBED);
    }

    static /* synthetic */ ChannelHeader lambda$getChannelHeader$3(JsonObject jsonObject) {
        return new ChannelHeader(jsonObject, ChannelHeader.HeaderType.CAROUSEL);
    }

    static /* synthetic */ ChannelHeader lambda$getChannelHeader$4(JsonObject jsonObject) {
        return new ChannelHeader(jsonObject, ChannelHeader.HeaderType.PAGE);
    }

    static /* synthetic */ ChannelHeader lambda$getChannelHeader$5(JsonObject jsonObject) {
        return new ChannelHeader(jsonObject, ChannelHeader.HeaderType.INTERACTIVE_TABBED);
    }

    /* JADX INFO: renamed from: org.schabi.newpipe.extractor.services.youtube.YoutubeChannelHelper$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$schabi$newpipe$extractor$services$youtube$YoutubeChannelHelper$ChannelHeader$HeaderType;

        static {
            int[] iArr = new int[ChannelHeader.HeaderType.values().length];
            $SwitchMap$org$schabi$newpipe$extractor$services$youtube$YoutubeChannelHelper$ChannelHeader$HeaderType = iArr;
            try {
                iArr[ChannelHeader.HeaderType.CAROUSEL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$schabi$newpipe$extractor$services$youtube$YoutubeChannelHelper$ChannelHeader$HeaderType[ChannelHeader.HeaderType.PAGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$schabi$newpipe$extractor$services$youtube$YoutubeChannelHelper$ChannelHeader$HeaderType[ChannelHeader.HeaderType.INTERACTIVE_TABBED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$schabi$newpipe$extractor$services$youtube$YoutubeChannelHelper$ChannelHeader$HeaderType[ChannelHeader.HeaderType.C4_TABBED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static boolean isChannelVerified(@Nonnull ChannelHeader channelHeader) {
        int i = AnonymousClass1.$SwitchMap$org$schabi$newpipe$extractor$services$youtube$YoutubeChannelHelper$ChannelHeader$HeaderType[channelHeader.headerType.ordinal()];
        if (i == 1) {
            return true;
        }
        if (i != 2) {
            if (i == 3) {
                return channelHeader.json.has("autoGenerated");
            }
            return YoutubeParsingHelper.isVerified(channelHeader.json.getArray("badges"));
        }
        JsonObject object = channelHeader.json.getObject(CONTENT).getObject(PAGE_HEADER_VIEW_MODEL);
        boolean zHasArtistOrVerifiedIconBadgeAttachment = YoutubeParsingHelper.hasArtistOrVerifiedIconBadgeAttachment(object.getObject("title").getObject("dynamicTextViewModel").getObject("text").getArray("attachmentRuns"));
        if (zHasArtistOrVerifiedIconBadgeAttachment || !object.getObject("image").has("contentPreviewImageViewModel")) {
            return zHasArtistOrVerifiedIconBadgeAttachment;
        }
        return true;
    }

    @Nonnull
    public static String getChannelId(@Nullable ChannelHeader channelHeader, @Nonnull JsonObject jsonObject, @Nullable String str) throws ParsingException {
        if (channelHeader != null) {
            int i = AnonymousClass1.$SwitchMap$org$schabi$newpipe$extractor$services$youtube$YoutubeChannelHelper$ChannelHeader$HeaderType[channelHeader.headerType.ordinal()];
            if (i == 1) {
                String string = channelHeader.json.getObject(HEADER).getObject(CAROUSEL_HEADER_RENDERER).getArray(CONTENTS).streamAsJsonObjects().filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeChannelHelper$$ExternalSyntheticLambda0
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
                        return ((JsonObject) obj).has(YoutubeChannelHelper.TOPIC_CHANNEL_DETAILS_RENDERER);
                    }
                }).findFirst().orElse(new JsonObject()).getObject(TOPIC_CHANNEL_DETAILS_RENDERER).getObject("navigationEndpoint").getObject(BROWSE_ENDPOINT).getString(BROWSE_ID);
                if (!Utils.isNullOrEmpty(string)) {
                    return string;
                }
            } else if (i == 4) {
                String string2 = channelHeader.json.getObject(HEADER).getObject(C4_TABBED_HEADER_RENDERER).getString("channelId", "");
                if (!Utils.isNullOrEmpty(string2)) {
                    return string2;
                }
                String string3 = channelHeader.json.getObject("navigationEndpoint").getObject(BROWSE_ENDPOINT).getString(BROWSE_ID);
                if (!Utils.isNullOrEmpty(string3)) {
                    return string3;
                }
            }
        }
        String string4 = jsonObject.getObject(TtmlNode.TAG_METADATA).getObject("channelMetadataRenderer").getString("externalChannelId");
        if (!Utils.isNullOrEmpty(string4)) {
            return string4;
        }
        if (Utils.isNullOrEmpty(str)) {
            throw new ParsingException("Could not get channel ID");
        }
        return str;
    }

    @Nonnull
    public static String getChannelName(@Nullable ChannelHeader channelHeader, @Nullable JsonObject jsonObject, @Nonnull final JsonObject jsonObject2) throws ParsingException {
        if (jsonObject != null) {
            String string = jsonObject.getString("channelTitle");
            if (Utils.isNullOrEmpty(string)) {
                throw new ParsingException("Could not get channel name");
            }
            return string;
        }
        String string2 = jsonObject2.getObject(TtmlNode.TAG_METADATA).getObject("channelMetadataRenderer").getString("title");
        return !Utils.isNullOrEmpty(string2) ? string2 : (String) Optional.ofNullable(channelHeader).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeChannelHelper$$ExternalSyntheticLambda4
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return YoutubeChannelHelper.lambda$getChannelName$7((YoutubeChannelHelper.ChannelHeader) obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).or(new Supplier() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeChannelHelper$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return Optional.ofNullable(jsonObject2.getObject("microformat").getObject("microformatDataRenderer").getString("title"));
            }
        }).orElseThrow(new Supplier() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeChannelHelper$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return YoutubeChannelHelper.lambda$getChannelName$9();
            }
        });
    }

    static /* synthetic */ String lambda$getChannelName$7(ChannelHeader channelHeader) {
        JsonObject jsonObject = channelHeader.json;
        int i = AnonymousClass1.$SwitchMap$org$schabi$newpipe$extractor$services$youtube$YoutubeChannelHelper$ChannelHeader$HeaderType[channelHeader.headerType.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return jsonObject.getObject(CONTENT).getObject(PAGE_HEADER_VIEW_MODEL).getObject("title").getObject("dynamicTextViewModel").getObject("text").getString(CONTENT, jsonObject.getString("pageTitle"));
            }
            if (i != 3) {
                return jsonObject.getString("title");
            }
        }
        return YoutubeParsingHelper.getTextFromObject(jsonObject.getObject("title"));
    }

    static /* synthetic */ ParsingException lambda$getChannelName$9() {
        return new ParsingException("Could not get channel name");
    }

    @Nullable
    public static JsonObject getChannelAgeGateRenderer(@Nonnull JsonObject jsonObject) {
        return (JsonObject) jsonObject.getObject(CONTENTS).getObject("twoColumnBrowseResultsRenderer").getArray("tabs").streamAsJsonObjects().flatMap(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeChannelHelper$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getObject(YoutubeChannelHelper.TAB_RENDERER).getObject(YoutubeChannelHelper.CONTENT).getObject("sectionListRenderer").getArray(YoutubeChannelHelper.CONTENTS).streamAsJsonObjects();
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeChannelHelper$$ExternalSyntheticLambda2
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
                return ((JsonObject) obj).has("channelAgeGateRenderer");
            }
        }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeChannelHelper$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getObject("channelAgeGateRenderer");
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).findFirst().orElse(null);
    }
}
