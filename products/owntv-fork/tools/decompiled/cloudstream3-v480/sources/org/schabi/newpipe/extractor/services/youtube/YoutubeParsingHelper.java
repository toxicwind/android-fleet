package org.schabi.newpipe.extractor.services.youtube;

import androidx.core.app.NotificationCompat;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.protobuf.InvalidProtocolBufferException;
import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonBuilder;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import com.grack.nanojson.JsonStringWriter;
import com.grack.nanojson.JsonWriter;
import j$.util.Base64;
import j$.util.Collection;
import j$.util.Optional;
import j$.util.function.Function$CC;
import j$.util.function.Predicate$CC;
import j$.util.stream.Collectors;
import j$.util.stream.Stream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import kotlin.UByte$$ExternalSyntheticBackport0;
import org.jsoup.nodes.Entities;
import org.mozilla.javascript.ES6Iterator;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.downloader.Response;
import org.schabi.newpipe.extractor.exceptions.AccountTerminatedException;
import org.schabi.newpipe.extractor.exceptions.ContentNotAvailableException;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.exceptions.ReCaptchaException;
import org.schabi.newpipe.extractor.localization.ContentCountry;
import org.schabi.newpipe.extractor.localization.Localization;
import org.schabi.newpipe.extractor.playlist.PlaylistInfo;
import org.schabi.newpipe.extractor.services.youtube.protos.video.Xtags;
import org.schabi.newpipe.extractor.stream.AudioTrackType;
import org.schabi.newpipe.extractor.utils.JsonUtils;
import org.schabi.newpipe.extractor.utils.Parser;
import org.schabi.newpipe.extractor.utils.RandomStringFromAlphabetGenerator;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public final class YoutubeParsingHelper {
    public static final String CONTENT_CHECK_OK = "contentCheckOk";
    private static final String CONTENT_PLAYBACK_NONCE_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";
    public static final String CPN = "cpn";
    public static final String DISABLE_PRETTY_PRINT_PARAMETER = "prettyPrint=false";
    private static final String FEED_BASE_CHANNEL_ID = "https://www.youtube.com/feeds/videos.xml?channel_id=";
    private static final String FEED_BASE_USER = "https://www.youtube.com/feeds/videos.xml?user=";
    public static final String RACY_CHECK_OK = "racyCheckOk";
    public static final String VIDEO_ID = "videoId";
    public static final String YOUTUBEI_V1_GAPIS_URL = "https://youtubei.googleapis.com/youtubei/v1/";
    public static final String YOUTUBEI_V1_URL = "https://www.youtube.com/youtubei/v1/";
    private static final String YOUTUBE_MUSIC_URL = "https://music.youtube.com";
    private static String clientVersion = null;
    private static boolean clientVersionExtracted = false;
    private static String youtubeMusicClientVersion;
    private static Optional<Boolean> hardcodedClientVersionValid = Optional.empty();
    private static final String[] INNERTUBE_CONTEXT_CLIENT_VERSION_REGEXES = {"INNERTUBE_CONTEXT_CLIENT_VERSION\":\"([0-9\\.]+?)\"", "innertube_context_client_version\":\"([0-9\\.]+?)\"", "client.version=([0-9\\.]+)"};
    private static final String[] INITIAL_DATA_REGEXES = {"window\\[\"ytInitialData\"\\]\\s*=\\s*(\\{.*?\\});", "var\\s*ytInitialData\\s*=\\s*(\\{.*?\\});"};
    private static Random numberGenerator = new Random();
    private static final Pattern C_WEB_PATTERN = Pattern.compile("&c=WEB");
    private static final Pattern C_WEB_EMBEDDED_PLAYER_PATTERN = Pattern.compile("&c=WEB_EMBEDDED_PLAYER");
    private static final Pattern C_ANDROID_PATTERN = Pattern.compile("&c=ANDROID");
    private static final Pattern C_IOS_PATTERN = Pattern.compile("&c=IOS");
    private static final Pattern C_VISIONOS_PATTERN = Pattern.compile("&c=VISIONOS");
    private static final Set<String> GOOGLE_URLS = UByte$$ExternalSyntheticBackport0.m9013m(new Object[]{"google.", "m.google.", "www.google."});
    private static final Set<String> INVIDIOUS_URLS = UByte$$ExternalSyntheticBackport0.m9013m((Object[]) new String[]{"invidio.us", "dev.invidio.us", "www.invidio.us", "redirect.invidious.io", "invidious.snopyta.org", "yewtu.be", "tube.connect.cafe", "tubus.eduvid.org", "invidious.kavin.rocks", "invidious.site", "invidious-us.kavin.rocks", "piped.kavin.rocks", "vid.mint.lgbt", "invidiou.site", "invidious.fdn.fr", "invidious.048596.xyz", "invidious.zee.li", "vid.puffyan.us", "ytprivate.com", "invidious.namazso.eu", "invidious.silkky.cloud", "ytb.trom.tf", "invidious.exonip.de", "inv.riverside.rocks", "invidious.blamefran.net", "y.com.cm", "invidious.moomoo.me", "yt.cyberhost.uk"});
    private static final Set<String> YOUTUBE_URLS = UByte$$ExternalSyntheticBackport0.m9013m(new Object[]{"youtube.com", "www.youtube.com", "m.youtube.com", "music.youtube.com"});
    private static boolean consentAccepted = false;

    private YoutubeParsingHelper() {
    }

    public static boolean isGoogleURL(String str) {
        try {
            final URL url = new URL(extractCachedUrlIfNeeded(str));
            return Collection.EL.stream(GOOGLE_URLS).anyMatch(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper$$ExternalSyntheticLambda15
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
                    return url.getHost().startsWith((String) obj);
                }
            });
        } catch (MalformedURLException unused) {
            return false;
        }
    }

    public static boolean isYoutubeURL(@Nonnull URL url) {
        return YOUTUBE_URLS.contains(url.getHost().toLowerCase(Locale.ROOT));
    }

    public static boolean isYoutubeServiceURL(@Nonnull URL url) {
        String host = url.getHost();
        return host.equalsIgnoreCase("www.youtube-nocookie.com") || host.equalsIgnoreCase("youtu.be");
    }

    public static boolean isHooktubeURL(@Nonnull URL url) {
        return url.getHost().equalsIgnoreCase("hooktube.com");
    }

    public static boolean isInvidiousURL(@Nonnull URL url) {
        return INVIDIOUS_URLS.contains(url.getHost().toLowerCase(Locale.ROOT));
    }

    public static boolean isY2ubeURL(@Nonnull URL url) {
        return url.getHost().equalsIgnoreCase("y2u.be");
    }

    public static int parseDurationString(@Nonnull String str) throws NumberFormatException, ParsingException {
        String[] strArrSplit;
        if (!str.matches(".*\\d.*") && !str.equalsIgnoreCase("SHORTS")) {
            throw new ParsingException("Error duration string contains no digits: " + str);
        }
        if (str.contains(":")) {
            strArrSplit = str.split(":");
        } else {
            strArrSplit = str.split("\\.");
        }
        int[] iArr = {24, 60, 60, 1};
        int length = 4 - strArrSplit.length;
        if (length < 0) {
            throw new ParsingException("Error duration string with unknown format: " + str);
        }
        int iConvertDurationToInt = 0;
        for (int i = 0; i < strArrSplit.length; i++) {
            iConvertDurationToInt = (iConvertDurationToInt + convertDurationToInt(strArrSplit[i])) * iArr[i + length];
        }
        return iConvertDurationToInt;
    }

    private static int convertDurationToInt(String str) {
        if (str != null && !str.isEmpty()) {
            try {
                return Integer.parseInt(Utils.removeNonDigitCharacters(str));
            } catch (NumberFormatException unused) {
            }
        }
        return 0;
    }

    @Nonnull
    public static String getFeedUrlFrom(@Nonnull String str) {
        if (str.startsWith("user/")) {
            return FEED_BASE_USER + str.replace("user/", "");
        }
        if (str.startsWith("channel/")) {
            return FEED_BASE_CHANNEL_ID + str.replace("channel/", "");
        }
        return FEED_BASE_CHANNEL_ID + str;
    }

    public static boolean isYoutubeMixId(@Nonnull String str) {
        return str.startsWith("RD");
    }

    public static boolean isYoutubeMyMixId(@Nonnull String str) {
        return str.startsWith("RDMM");
    }

    public static boolean isYoutubeMusicMixId(@Nonnull String str) {
        return str.startsWith("RDAMVM") || str.startsWith("RDCLAK");
    }

    public static boolean isYoutubeGenreMixId(@Nonnull String str) {
        return str.startsWith("RDGMEM");
    }

    @Nonnull
    public static String extractVideoIdFromMixId(String str) throws ParsingException {
        if (Utils.isNullOrEmpty(str)) {
            throw new ParsingException("Video id could not be determined from empty playlist id");
        }
        if (isYoutubeMyMixId(str)) {
            return str.substring(4);
        }
        if (isYoutubeMusicMixId(str)) {
            return str.substring(6);
        }
        if (isYoutubeGenreMixId(str)) {
            throw new ParsingException("Video id could not be determined from genre mix id: " + str);
        }
        if (isYoutubeMixId(str)) {
            if (str.length() != 13) {
                throw new ParsingException("Video id could not be determined from mix id: " + str);
            }
            return str.substring(2);
        }
        throw new ParsingException("Video id could not be determined from playlist id: " + str);
    }

    @Nonnull
    public static PlaylistInfo.PlaylistType extractPlaylistTypeFromPlaylistId(String str) throws ParsingException {
        if (Utils.isNullOrEmpty(str)) {
            throw new ParsingException("Could not extract playlist type from empty playlist id");
        }
        if (isYoutubeMusicMixId(str)) {
            return PlaylistInfo.PlaylistType.MIX_MUSIC;
        }
        if (isYoutubeGenreMixId(str)) {
            return PlaylistInfo.PlaylistType.MIX_GENRE;
        }
        if (isYoutubeMixId(str)) {
            return PlaylistInfo.PlaylistType.MIX_STREAM;
        }
        return PlaylistInfo.PlaylistType.NORMAL;
    }

    public static PlaylistInfo.PlaylistType extractPlaylistTypeFromPlaylistUrl(String str) throws ParsingException {
        try {
            return extractPlaylistTypeFromPlaylistId(Utils.getQueryValue(Utils.stringToURL(str), "list"));
        } catch (MalformedURLException e) {
            throw new ParsingException("Could not extract playlist type from malformed url", e);
        }
    }

    private static JsonObject getInitialData(String str) throws ParsingException {
        try {
            return JsonParser.object().from(Utils.getStringResultFromRegexArray(str, INITIAL_DATA_REGEXES, 1));
        } catch (JsonParserException | Parser.RegexException e) {
            throw new ParsingException("Could not get ytInitialData", e);
        }
    }

    public static boolean isHardcodedClientVersionValid() throws IOException, ExtractionException {
        if (hardcodedClientVersionValid.isPresent()) {
            return hardcodedClientVersionValid.get().booleanValue();
        }
        boolean z = false;
        Response responsePostWithContentTypeJson = NewPipe.getDownloader().postWithContentTypeJson("https://www.youtube.com/youtubei/v1/guide?prettyPrint=false", getClientHeaders("1", "2.20260120.01.00"), ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) JsonWriter.string().object()).object("context")).object("client")).value("hl", "en-GB")).value("gl", "GB")).value("clientName", "WEB")).value("clientVersion", "2.20260120.01.00")).value("platform", "DESKTOP")).value("utcOffsetMinutes", 0)).end()).object("request")).array("internalExperimentFlags")).end()).value("useSsl", true)).end()).object("user")).value("lockedSafetyMode", false)).end()).end()).value("fetchLiveState", true)).end()).done().getBytes(StandardCharsets.UTF_8));
        String strResponseBody = responsePostWithContentTypeJson.responseBody();
        int iResponseCode = responsePostWithContentTypeJson.responseCode();
        if (strResponseBody.length() > 5000 && iResponseCode == 200) {
            z = true;
        }
        Optional<Boolean> optionalOf = Optional.of(Boolean.valueOf(z));
        hardcodedClientVersionValid = optionalOf;
        return optionalOf.get().booleanValue();
    }

    private static void extractClientVersionFromSwJs() throws IOException, ExtractionException {
        if (clientVersionExtracted) {
            return;
        }
        try {
            clientVersion = Utils.getStringResultFromRegexArray(NewPipe.getDownloader().get("https://www.youtube.com/sw.js", getOriginReferrerHeaders("https://www.youtube.com")).responseBody(), INNERTUBE_CONTEXT_CLIENT_VERSION_REGEXES, 1);
            clientVersionExtracted = true;
        } catch (Parser.RegexException e) {
            throw new ParsingException("Could not extract YouTube WEB InnerTube client version from sw.js", e);
        }
    }

    private static void extractClientVersionFromHtmlSearchResultsPage() throws IOException, ExtractionException {
        if (clientVersionExtracted) {
            return;
        }
        String strResponseBody = NewPipe.getDownloader().get("https://www.youtube.com/results?search_query=&ucbcb=1", getCookieHeader()).responseBody();
        Stream<JsonObject> streamStreamAsJsonObjects = getInitialData(strResponseBody).getObject("responseContext").getArray("serviceTrackingParams").streamAsJsonObjects();
        String clientVersionFromServiceTrackingParam = getClientVersionFromServiceTrackingParam(streamStreamAsJsonObjects, "CSI", "cver");
        clientVersion = clientVersionFromServiceTrackingParam;
        if (clientVersionFromServiceTrackingParam == null) {
            try {
                clientVersion = Utils.getStringResultFromRegexArray(strResponseBody, INNERTUBE_CONTEXT_CLIENT_VERSION_REGEXES, 1);
            } catch (Parser.RegexException unused) {
            }
        }
        if (Utils.isNullOrEmpty(clientVersion)) {
            clientVersion = getClientVersionFromServiceTrackingParam(streamStreamAsJsonObjects, "ECATCHER", "client.version");
        }
        if (clientVersion == null) {
            throw new ParsingException("Could not extract YouTube WEB InnerTube client version from HTML search results page");
        }
        clientVersionExtracted = true;
    }

    @Nullable
    private static String getClientVersionFromServiceTrackingParam(@Nonnull Stream<JsonObject> stream, @Nonnull final String str, @Nonnull final String str2) {
        return (String) stream.filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper$$ExternalSyntheticLambda7
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
                return ((JsonObject) obj).getString(NotificationCompat.CATEGORY_SERVICE, "").equals(str);
            }
        }).flatMap(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper$$ExternalSyntheticLambda8
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getArray("params").streamAsJsonObjects();
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper$$ExternalSyntheticLambda9
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
                return ((JsonObject) obj).getString("key", "").equals(str2);
            }
        }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper$$ExternalSyntheticLambda10
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getString(ES6Iterator.VALUE_PROPERTY);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper$$ExternalSyntheticLambda11
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
                return YoutubeParsingHelper.lambda$getClientVersionFromServiceTrackingParam$5((String) obj);
            }
        }).findFirst().orElse(null);
    }

    static /* synthetic */ boolean lambda$getClientVersionFromServiceTrackingParam$5(String str) {
        return !Utils.isNullOrEmpty(str);
    }

    public static String getClientVersion() throws IOException, ExtractionException {
        if (!Utils.isNullOrEmpty(clientVersion)) {
            return clientVersion;
        }
        try {
            extractClientVersionFromSwJs();
        } catch (Exception unused) {
            extractClientVersionFromHtmlSearchResultsPage();
        }
        if (clientVersionExtracted) {
            return clientVersion;
        }
        if (isHardcodedClientVersionValid()) {
            clientVersion = "2.20260120.01.00";
            return "2.20260120.01.00";
        }
        throw new ExtractionException("Could not get YouTube WEB client version");
    }

    public static void resetClientVersion() {
        clientVersion = null;
        clientVersionExtracted = false;
    }

    public static void setNumberGenerator(Random random) {
        numberGenerator = random;
    }

    public static boolean isHardcodedYoutubeMusicClientVersionValid() throws ReCaptchaException, IOException {
        byte[] bytes = ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) JsonWriter.string().object()).object("context")).object("client")).value("clientName", "WEB_REMIX")).value("clientVersion", "1.20260121.03.00")).value("hl", "en-GB")).value("gl", "GB")).value("platform", "DESKTOP")).value("utcOffsetMinutes", 0)).end()).object("request")).array("internalExperimentFlags")).end()).value("useSsl", true)).end()).object("user")).value("lockedSafetyMode", false)).end()).end()).value(TvContractCompat.PARAM_INPUT, "")).end()).done().getBytes(StandardCharsets.UTF_8);
        HashMap map = new HashMap(getOriginReferrerHeaders(YOUTUBE_MUSIC_URL));
        map.putAll(getClientHeaders("67", "2.20260120.01.00"));
        Response responsePostWithContentTypeJson = NewPipe.getDownloader().postWithContentTypeJson("https://music.youtube.com/youtubei/v1/music/get_search_suggestions?prettyPrint=false", map, bytes);
        return responsePostWithContentTypeJson.responseBody().length() > 500 && responsePostWithContentTypeJson.responseCode() == 200;
    }

    public static String getYoutubeMusicClientVersion() throws ReCaptchaException, Parser.RegexException, IOException {
        if (!Utils.isNullOrEmpty(youtubeMusicClientVersion)) {
            return youtubeMusicClientVersion;
        }
        if (isHardcodedYoutubeMusicClientVersionValid()) {
            youtubeMusicClientVersion = "1.20260121.03.00";
            return "1.20260121.03.00";
        }
        try {
            youtubeMusicClientVersion = Utils.getStringResultFromRegexArray(NewPipe.getDownloader().get("https://music.youtube.com/sw.js", getOriginReferrerHeaders(YOUTUBE_MUSIC_URL)).responseBody(), INNERTUBE_CONTEXT_CLIENT_VERSION_REGEXES, 1);
        } catch (Exception unused) {
            youtubeMusicClientVersion = Utils.getStringResultFromRegexArray(NewPipe.getDownloader().get("https://music.youtube.com/?ucbcb=1", getCookieHeader()).responseBody(), INNERTUBE_CONTEXT_CLIENT_VERSION_REGEXES, 1);
        }
        return youtubeMusicClientVersion;
    }

    @Nullable
    public static String getUrlFromNavigationEndpoint(@Nonnull JsonObject jsonObject) {
        if (jsonObject.has("urlEndpoint")) {
            String string = jsonObject.getObject("urlEndpoint").getString(ImagesContract.URL);
            if (string.startsWith("https://www.youtube.com/redirect?")) {
                string = string.substring(23);
            }
            if (string.startsWith("/redirect?")) {
                for (String str : string.substring(10).split("&")) {
                    if (str.split("=")[0].equals("q")) {
                        return Utils.decodeUrlUtf8(str.split("=")[1]);
                    }
                }
            } else {
                if (string.startsWith("http")) {
                    return string;
                }
                if (string.startsWith("/channel") || string.startsWith("/user") || string.startsWith("/watch")) {
                    return "https://www.youtube.com" + string;
                }
            }
        }
        if (jsonObject.has("browseEndpoint")) {
            JsonObject object = jsonObject.getObject("browseEndpoint");
            String string2 = object.getString("canonicalBaseUrl");
            String string3 = object.getString("browseId");
            if (string3 != null) {
                if (string3.startsWith("UC")) {
                    return "https://www.youtube.com/channel/" + string3;
                }
                if (string3.startsWith("VL")) {
                    return "https://www.youtube.com/playlist?list=" + string3.substring(2);
                }
            }
            if (!Utils.isNullOrEmpty(string2)) {
                return "https://www.youtube.com" + string2;
            }
        }
        if (jsonObject.has("watchEndpoint")) {
            StringBuilder sb = new StringBuilder("https://www.youtube.com/watch?v=");
            sb.append(jsonObject.getObject("watchEndpoint").getString(VIDEO_ID));
            if (jsonObject.getObject("watchEndpoint").has("playlistId")) {
                sb.append("&list=");
                sb.append(jsonObject.getObject("watchEndpoint").getString("playlistId"));
            }
            if (jsonObject.getObject("watchEndpoint").has("startTimeSeconds")) {
                sb.append("&t=");
                sb.append(jsonObject.getObject("watchEndpoint").getInt("startTimeSeconds"));
            }
            return sb.toString();
        }
        if (jsonObject.has("watchPlaylistEndpoint")) {
            return "https://www.youtube.com/playlist?list=" + jsonObject.getObject("watchPlaylistEndpoint").getString("playlistId");
        }
        if (jsonObject.has("showDialogCommand")) {
            try {
                return getUrlFromNavigationEndpoint(JsonUtils.getObject(JsonUtils.getArray(jsonObject, "showDialogCommand.panelLoadingStrategy.inlineContent.dialogViewModel.customContent.listViewModel.listItems").getObject(0), "listItemViewModel.rendererContext.commandContext.onTap.innertubeCommand"));
            } catch (ParsingException unused) {
            }
        }
        if (!jsonObject.has("commandMetadata")) {
            return null;
        }
        JsonObject object2 = jsonObject.getObject("commandMetadata").getObject("webCommandMetadata");
        if (!object2.has(ImagesContract.URL)) {
            return null;
        }
        return "https://www.youtube.com" + object2.getString(ImagesContract.URL);
    }

    @Nullable
    public static String getTextFromObject(JsonObject jsonObject, boolean z) {
        if (Utils.isNullOrEmpty(jsonObject)) {
            return null;
        }
        if (jsonObject.has("simpleText")) {
            return jsonObject.getString("simpleText");
        }
        JsonArray<JsonObject> array = jsonObject.getArray("runs");
        if (array.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (JsonObject jsonObject2 : array) {
            String string = jsonObject2.getString("text");
            if (z) {
                if (jsonObject2.has("navigationEndpoint")) {
                    String urlFromNavigationEndpoint = getUrlFromNavigationEndpoint(jsonObject2.getObject("navigationEndpoint"));
                    if (!Utils.isNullOrEmpty(urlFromNavigationEndpoint)) {
                        string = "<a href=\"" + Entities.escape(urlFromNavigationEndpoint) + "\">" + Entities.escape(string) + "</a>";
                    }
                }
                boolean z2 = jsonObject2.has(TtmlNode.BOLD) && jsonObject2.getBoolean(TtmlNode.BOLD);
                boolean z3 = jsonObject2.has("italics") && jsonObject2.getBoolean("italics");
                boolean z4 = jsonObject2.has("strikethrough") && jsonObject2.getBoolean("strikethrough");
                if (z2) {
                    sb.append("<b>");
                }
                if (z3) {
                    sb.append("<i>");
                }
                if (z4) {
                    sb.append("<s>");
                }
                sb.append(string);
                if (z4) {
                    sb.append("</s>");
                }
                if (z3) {
                    sb.append("</i>");
                }
                if (z2) {
                    sb.append("</b>");
                }
            } else {
                sb.append(string);
            }
        }
        String string2 = sb.toString();
        return z ? string2.replaceAll("\\n", "<br>").replaceAll(" {2}", " &nbsp;") : string2;
    }

    @Nonnull
    public static String getTextFromObjectOrThrow(JsonObject jsonObject, String str) throws ParsingException {
        String textFromObject = getTextFromObject(jsonObject);
        if (textFromObject != null) {
            return textFromObject;
        }
        throw new ParsingException("Could not extract text: " + str);
    }

    @Nullable
    public static String getTextFromObject(JsonObject jsonObject) {
        return getTextFromObject(jsonObject, false);
    }

    @Nullable
    public static String getUrlFromObject(JsonObject jsonObject) {
        if (Utils.isNullOrEmpty(jsonObject)) {
            return null;
        }
        JsonArray array = jsonObject.getArray("runs");
        if (array.isEmpty()) {
            return null;
        }
        Iterator<Object> it = array.iterator();
        while (it.hasNext()) {
            String urlFromNavigationEndpoint = getUrlFromNavigationEndpoint(((JsonObject) it.next()).getObject("navigationEndpoint"));
            if (!Utils.isNullOrEmpty(urlFromNavigationEndpoint)) {
                return urlFromNavigationEndpoint;
            }
        }
        return null;
    }

    @Nullable
    public static String getTextAtKey(@Nonnull JsonObject jsonObject, String str) {
        if (jsonObject.isString(str)) {
            return jsonObject.getString(str);
        }
        return getTextFromObject(jsonObject.getObject(str));
    }

    public static String fixThumbnailUrl(@Nonnull String str) {
        if (str.startsWith("//")) {
            str = str.substring(2);
        }
        if (str.startsWith(Utils.HTTP)) {
            return Utils.replaceHttpWithHttps(str);
        }
        if (str.startsWith(Utils.HTTPS)) {
            return str;
        }
        return Utils.HTTPS + str;
    }

    @Nonnull
    public static List<Image> getThumbnailsFromInfoItem(@Nonnull JsonObject jsonObject) throws ParsingException {
        try {
            return getImagesFromThumbnailsArray(jsonObject.getObject("thumbnail").getArray("thumbnails"));
        } catch (Exception e) {
            throw new ParsingException("Could not get thumbnails from InfoItem", e);
        }
    }

    @Nonnull
    public static List<Image> getImagesFromThumbnailsArray(@Nonnull JsonArray jsonArray) {
        return (List) jsonArray.streamAsJsonObjects().filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper$$ExternalSyntheticLambda5
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
                return YoutubeParsingHelper.lambda$getImagesFromThumbnailsArray$6((JsonObject) obj);
            }
        }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper$$ExternalSyntheticLambda6
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return YoutubeParsingHelper.lambda$getImagesFromThumbnailsArray$7((JsonObject) obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).collect(Collectors.toUnmodifiableList());
    }

    static /* synthetic */ boolean lambda$getImagesFromThumbnailsArray$6(JsonObject jsonObject) {
        return !Utils.isNullOrEmpty(jsonObject.getString(ImagesContract.URL));
    }

    static /* synthetic */ Image lambda$getImagesFromThumbnailsArray$7(JsonObject jsonObject) {
        int i = jsonObject.getInt("height", -1);
        return new Image(fixThumbnailUrl(jsonObject.getString(ImagesContract.URL)), i, jsonObject.getInt("width", -1), Image.ResolutionLevel.fromHeight(i));
    }

    @Nonnull
    public static String getValidJsonResponseBody(@Nonnull Response response) throws MalformedURLException, ParsingException {
        if (response.responseCode() == 404) {
            throw new ContentNotAvailableException("Not found (\"" + response.responseCode() + org.schabi.newpipe.extractor.stream.Stream.ID_UNKNOWN + response.responseMessage() + "\")");
        }
        String strResponseBody = response.responseBody();
        if (strResponseBody.length() < 50) {
            throw new ParsingException("JSON response is too short");
        }
        URL url = new URL(response.latestUrl());
        if (url.getHost().equalsIgnoreCase("www.youtube.com")) {
            String path = url.getPath();
            if (path.equalsIgnoreCase("/oops") || path.equalsIgnoreCase("/error")) {
                throw new ContentNotAvailableException("Content unavailable");
            }
        }
        String header = response.getHeader("Content-Type");
        if (header == null || !header.toLowerCase().contains("text/html")) {
            return strResponseBody;
        }
        throw new ParsingException("Got HTML document, expected JSON response (latest url was: \"" + response.latestUrl() + "\")");
    }

    public static JsonObject getJsonPostResponse(@Nonnull String str, byte[] bArr, @Nonnull Localization localization) throws IOException, ExtractionException {
        Map<String, List<String>> youTubeHeaders = getYouTubeHeaders();
        return JsonUtils.toJsonObject(getValidJsonResponseBody(NewPipe.getDownloader().postWithContentTypeJson(YOUTUBEI_V1_URL + str + "?prettyPrint=false", youTubeHeaders, bArr, localization)));
    }

    public static JsonObject getJsonPostResponse(@Nonnull String str, @Nonnull List<String> list, byte[] bArr, @Nonnull Localization localization) throws IOException, ExtractionException {
        String str2;
        Map<String, List<String>> youTubeHeaders = getYouTubeHeaders();
        if (list.isEmpty()) {
            str2 = "?prettyPrint=false";
        } else {
            str2 = "?" + UByte$$ExternalSyntheticBackport0.m((CharSequence) "&", (Iterable) list) + "&prettyPrint=false";
        }
        return JsonUtils.toJsonObject(getValidJsonResponseBody(NewPipe.getDownloader().postWithContentTypeJson(YOUTUBEI_V1_URL + str + str2, youTubeHeaders, bArr, localization)));
    }

    @Nonnull
    public static JsonBuilder<JsonObject> prepareDesktopJsonBuilder(@Nonnull Localization localization, @Nonnull ContentCountry contentCountry) throws IOException, ExtractionException {
        return JsonObject.builder().object("context").object("client").value("hl", localization.getLocalizationCode()).value("gl", contentCountry.getCountryCode()).value("clientName", "WEB").value("clientVersion", getClientVersion()).value("originalUrl", "https://www.youtube.com").value("platform", "DESKTOP").value("utcOffsetMinutes", 0).end().object("request").array("internalExperimentFlags").end().value("useSsl", true).end().object("user").value("lockedSafetyMode", false).end().end();
    }

    @Nonnull
    public static String getAndroidUserAgent(@Nullable Localization localization) {
        if (localization == null) {
            localization = Localization.DEFAULT;
        }
        return "com.google.android.youtube/21.03.36 (Linux; U; Android 15; " + localization.getCountryCode() + ") gzip";
    }

    @Nonnull
    public static String getIosUserAgent(@Nullable Localization localization) {
        if (localization == null) {
            localization = Localization.DEFAULT;
        }
        return "com.google.ios.youtube/21.03.2(iPhone16,2; U; CPU iOS 18_7_2 like Mac OS X; " + localization.getCountryCode() + ")";
    }

    @Nonnull
    public static String getVisionOsUserAgent(@Nullable Localization localization) {
        if (localization == null) {
            localization = Localization.DEFAULT;
        }
        return "com.google.visionos.youtube/1.02(RealityDevice14,1; U; CPU visionOS 25_6_0 like Mac OS X; " + localization.getCountryCode() + ")";
    }

    @Nonnull
    public static Map<String, List<String>> getYoutubeMusicHeaders() {
        HashMap map = new HashMap(getOriginReferrerHeaders(YOUTUBE_MUSIC_URL));
        map.putAll(getClientHeaders("67", youtubeMusicClientVersion));
        return map;
    }

    public static Map<String, List<String>> getYouTubeHeaders() throws IOException, ExtractionException {
        Map<String, List<String>> clientInfoHeaders = getClientInfoHeaders();
        clientInfoHeaders.put("Cookie", UByte$$ExternalSyntheticBackport0.m(new Object[]{generateConsentCookie()}));
        return clientInfoHeaders;
    }

    public static Map<String, List<String>> getClientInfoHeaders() throws IOException, ExtractionException {
        HashMap map = new HashMap(getOriginReferrerHeaders("https://www.youtube.com"));
        map.putAll(getClientHeaders("1", getClientVersion()));
        return map;
    }

    public static Map<String, List<String>> getOriginReferrerHeaders(@Nonnull String str) {
        List listM = UByte$$ExternalSyntheticBackport0.m(new Object[]{str});
        return UByte$$ExternalSyntheticBackport0.m(new Map.Entry[]{new AbstractMap.SimpleEntry("Origin", listM), new AbstractMap.SimpleEntry("Referer", listM)});
    }

    public static Map<String, List<String>> getClientHeaders(@Nonnull String str, @Nonnull String str2) {
        return UByte$$ExternalSyntheticBackport0.m(new Map.Entry[]{new AbstractMap.SimpleEntry("X-YouTube-Client-Name", UByte$$ExternalSyntheticBackport0.m(new Object[]{str})), new AbstractMap.SimpleEntry("X-YouTube-Client-Version", UByte$$ExternalSyntheticBackport0.m(new Object[]{str2}))});
    }

    public static Map<String, List<String>> getCookieHeader() {
        return UByte$$ExternalSyntheticBackport0.m(new Map.Entry[]{new AbstractMap.SimpleEntry("Cookie", UByte$$ExternalSyntheticBackport0.m(new Object[]{generateConsentCookie()}))});
    }

    @Nonnull
    public static String generateConsentCookie() {
        String str;
        if (isConsentAccepted()) {
            str = "CAISAiAD";
        } else {
            str = "CAE=";
        }
        return "SOCS=".concat(str);
    }

    public static String extractCookieValue(String str, @Nonnull Response response) {
        List<String> list = response.responseHeaders().get("set-cookie");
        String strSubstring = "";
        if (list == null) {
            return "";
        }
        for (String str2 : list) {
            int iIndexOf = str2.indexOf(str);
            if (iIndexOf != -1) {
                strSubstring = str2.substring(str.length() + iIndexOf + 1, str2.indexOf(";", iIndexOf));
            }
        }
        return strSubstring;
    }

    public static void defaultAlertsCheck(@Nonnull JsonObject jsonObject) throws ParsingException {
        JsonArray array = jsonObject.getArray("alerts");
        if (Utils.isNullOrEmpty(array)) {
            return;
        }
        JsonObject object = array.getObject(0).getObject("alertRenderer");
        String textFromObject = getTextFromObject(object.getObject("text"));
        if (object.getString("type", "").equalsIgnoreCase(MediaError.ERROR_TYPE_ERROR)) {
            if (textFromObject != null && (textFromObject.contains("This account has been terminated") || textFromObject.contains("This channel was removed"))) {
                if (textFromObject.matches(".*violat(ed|ion|ing).*") || textFromObject.contains("infringement")) {
                    throw new AccountTerminatedException(textFromObject, AccountTerminatedException.Reason.VIOLATION);
                }
                throw new AccountTerminatedException(textFromObject);
            }
            throw new ContentNotAvailableException("Got error: \"" + textFromObject + "\"");
        }
    }

    public static String extractCachedUrlIfNeeded(String str) {
        if (str == null) {
            return null;
        }
        return str.contains("webcache.googleusercontent.com") ? str.split("cache:")[1] : str;
    }

    public static boolean isVerified(JsonArray jsonArray) {
        if (Utils.isNullOrEmpty(jsonArray)) {
            return false;
        }
        Iterator<Object> it = jsonArray.iterator();
        while (it.hasNext()) {
            String string = ((JsonObject) it.next()).getObject("metadataBadgeRenderer").getString("style");
            if (string != null && (string.equals("BADGE_STYLE_TYPE_VERIFIED") || string.equals("BADGE_STYLE_TYPE_VERIFIED_ARTIST"))) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasArtistOrVerifiedIconBadgeAttachment(@Nonnull JsonArray jsonArray) {
        return jsonArray.streamAsJsonObjects().anyMatch(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper$$ExternalSyntheticLambda4
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
                return ((JsonObject) obj).getObject("element").getObject("type").getObject("imageType").getObject("image").getArray("sources").streamAsJsonObjects().anyMatch(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper$$ExternalSyntheticLambda14
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
                    public final boolean test(Object obj2) {
                        return YoutubeParsingHelper.lambda$hasArtistOrVerifiedIconBadgeAttachment$8((JsonObject) obj2);
                    }
                });
            }
        });
    }

    static /* synthetic */ boolean lambda$hasArtistOrVerifiedIconBadgeAttachment$8(JsonObject jsonObject) {
        String string = jsonObject.getObject("clientResource").getString("imageName");
        return "CHECK_CIRCLE_FILLED".equals(string) || "AUDIO_BADGE".equals(string) || "MUSIC_FILLED".equals(string);
    }

    @Nonnull
    public static String generateContentPlaybackNonce() {
        return RandomStringFromAlphabetGenerator.generate(CONTENT_PLAYBACK_NONCE_ALPHABET, 16, numberGenerator);
    }

    @Nonnull
    public static String generateTParameter() {
        return RandomStringFromAlphabetGenerator.generate(CONTENT_PLAYBACK_NONCE_ALPHABET, 12, numberGenerator);
    }

    public static boolean isWebStreamingUrl(@Nonnull String str) {
        return Parser.isMatch(C_WEB_PATTERN, str);
    }

    public static boolean isWebEmbeddedPlayerStreamingUrl(@Nonnull String str) {
        return Parser.isMatch(C_WEB_EMBEDDED_PLAYER_PATTERN, str);
    }

    public static boolean isAndroidStreamingUrl(@Nonnull String str) {
        return Parser.isMatch(C_ANDROID_PATTERN, str);
    }

    public static boolean isIosStreamingUrl(@Nonnull String str) {
        return Parser.isMatch(C_IOS_PATTERN, str);
    }

    public static boolean isVisionOsStreamingUrl(@Nonnull String str) {
        return Parser.isMatch(C_VISIONOS_PATTERN, str);
    }

    public static void setConsentAccepted(boolean z) {
        consentAccepted = z;
    }

    public static boolean isConsentAccepted() {
        return consentAccepted;
    }

    @Nullable
    public static AudioTrackType extractAudioTrackType(@Nullable String str) {
        if (str == null) {
            return null;
        }
        try {
            String str2 = (String) Collection.EL.stream(Xtags.XTags.parseFrom(Base64.getUrlDecoder().decode(str)).getXtagsList()).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper$$ExternalSyntheticLambda12
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
                    return "acont".equals(((Xtags.KeyValuePair) obj).getKey());
                }
            }).findFirst().map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper$$ExternalSyntheticLambda13
                @Override // java.util.function.Function
                public /* synthetic */ Function andThen(Function function) {
                    return Function$CC.$default$andThen(this, function);
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((Xtags.KeyValuePair) obj).getValue();
                }

                public /* synthetic */ Function compose(Function function) {
                    return Function$CC.$default$compose(this, function);
                }
            }).orElse(null);
            if (str2 == null) {
                return null;
            }
            str2.hashCode();
            switch (str2) {
                case "descriptive":
                    return AudioTrackType.DESCRIPTIVE;
                case "dubbed":
                case "dubbed-auto":
                    return AudioTrackType.DUBBED;
                case "secondary":
                    return AudioTrackType.SECONDARY;
                case "original":
                    return AudioTrackType.ORIGINAL;
                default:
                    return null;
            }
        } catch (InvalidProtocolBufferException unused) {
            return null;
        }
    }

    @Nonnull
    public static String getVisitorDataFromInnertube(@Nonnull InnertubeClientRequestInfo innertubeClientRequestInfo, @Nonnull Localization localization, @Nonnull ContentCountry contentCountry, @Nonnull Map<String, List<String>> map, @Nonnull String str, @Nullable String str2, boolean z) throws IOException, ExtractionException {
        byte[] bytes = JsonWriter.string(prepareJsonBuilder(localization, contentCountry, innertubeClientRequestInfo, str2).done()).getBytes(StandardCharsets.UTF_8);
        String string = JsonUtils.toJsonObject(getValidJsonResponseBody(NewPipe.getDownloader().postWithContentTypeJson(str + (z ? "guide" : "visitor_id") + "?prettyPrint=false", map, bytes))).getObject("responseContext").getString("visitorData");
        if (Utils.isNullOrEmpty(string)) {
            throw new ParsingException("Could not get visitorData");
        }
        return string;
    }

    @Nonnull
    public static JsonBuilder<JsonObject> prepareJsonBuilder(@Nonnull Localization localization, @Nonnull ContentCountry contentCountry, @Nonnull InnertubeClientRequestInfo innertubeClientRequestInfo, @Nullable String str) {
        JsonBuilder<JsonObject> jsonBuilderValue = JsonObject.builder().object("context").object("client").value("clientName", innertubeClientRequestInfo.clientInfo.clientName).value("clientVersion", innertubeClientRequestInfo.clientInfo.clientVersion);
        if (innertubeClientRequestInfo.clientInfo.clientScreen != null) {
            jsonBuilderValue.value("clientScreen", innertubeClientRequestInfo.clientInfo.clientScreen);
        }
        if (innertubeClientRequestInfo.deviceInfo.platform != null) {
            jsonBuilderValue.value("platform", innertubeClientRequestInfo.deviceInfo.platform);
        }
        if (innertubeClientRequestInfo.clientInfo.visitorData != null) {
            jsonBuilderValue.value("visitorData", innertubeClientRequestInfo.clientInfo.visitorData);
        }
        if (innertubeClientRequestInfo.deviceInfo.deviceMake != null) {
            jsonBuilderValue.value("deviceMake", innertubeClientRequestInfo.deviceInfo.deviceMake);
        }
        if (innertubeClientRequestInfo.deviceInfo.deviceModel != null) {
            jsonBuilderValue.value("deviceModel", innertubeClientRequestInfo.deviceInfo.deviceModel);
        }
        if (innertubeClientRequestInfo.deviceInfo.osName != null) {
            jsonBuilderValue.value("osName", innertubeClientRequestInfo.deviceInfo.osName);
        }
        if (innertubeClientRequestInfo.deviceInfo.osVersion != null) {
            jsonBuilderValue.value("osVersion", innertubeClientRequestInfo.deviceInfo.osVersion);
        }
        if (innertubeClientRequestInfo.deviceInfo.androidSdkVersion > 0) {
            jsonBuilderValue.value("androidSdkVersion", innertubeClientRequestInfo.deviceInfo.androidSdkVersion);
        }
        jsonBuilderValue.value("hl", localization.getLocalizationCode()).value("gl", contentCountry.getCountryCode()).value("utcOffsetMinutes", 0).end();
        if (str != null) {
            jsonBuilderValue.object("thirdParty").value("embedUrl", str).end();
        }
        jsonBuilderValue.object("request").array("internalExperimentFlags").end().value("useSsl", true).end().object("user").value("lockedSafetyMode", false).end().end();
        return jsonBuilderValue;
    }

    @Nullable
    public static JsonObject getFirstCollaborator(JsonObject jsonObject) throws ParsingException {
        try {
            return JsonUtils.getArray(jsonObject, "showDialogCommand.panelLoadingStrategy.inlineContent.dialogViewModel.customContent.listViewModel.listItems").getObject(0).getObject("listItemViewModel");
        } catch (ParsingException unused) {
            return null;
        }
    }
}
