package org.schabi.newpipe.extractor.services.soundcloud;

import androidx.autofill.HintConstants;
import androidx.mediarouter.media.GlobalMediaRouter;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import j$.time.OffsetDateTime;
import j$.time.format.DateTimeFormatter;
import j$.time.format.DateTimeParseException;
import j$.util.Collection;
import j$.util.function.Consumer$CC;
import j$.util.function.Function$CC;
import j$.util.stream.Collectors;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import kotlin.UByte$$ExternalSyntheticBackport0;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.MultiInfoItemsCollector;
import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.ServiceList;
import org.schabi.newpipe.extractor.channel.ChannelInfoItemsCollector;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.downloader.Response;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.exceptions.ReCaptchaException;
import org.schabi.newpipe.extractor.localization.DateWrapper;
import org.schabi.newpipe.extractor.services.soundcloud.extractors.SoundcloudChannelInfoItemExtractor;
import org.schabi.newpipe.extractor.services.soundcloud.extractors.SoundcloudLikesInfoItemExtractor;
import org.schabi.newpipe.extractor.services.soundcloud.extractors.SoundcloudPlaylistInfoItemExtractor;
import org.schabi.newpipe.extractor.services.soundcloud.extractors.SoundcloudStreamInfoItemExtractor;
import org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor;
import org.schabi.newpipe.extractor.stream.StreamInfoItemsCollector;
import org.schabi.newpipe.extractor.utils.ImageSuffix;
import org.schabi.newpipe.extractor.utils.JsonUtils;
import org.schabi.newpipe.extractor.utils.Parser;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public final class SoundcloudParsingHelper {
    public static final String SOUNDCLOUD_API_V2_URL = "https://api-v2.soundcloud.com/";
    private static String clientId;
    private static final List<ImageSuffix> ALBUMS_AND_ARTWORKS_IMAGE_SUFFIXES = UByte$$ExternalSyntheticBackport0.m((Object[]) new ImageSuffix[]{new ImageSuffix("mini", 16, 16, Image.ResolutionLevel.LOW), new ImageSuffix("t20x20", 20, 20, Image.ResolutionLevel.LOW), new ImageSuffix("small", 32, 32, Image.ResolutionLevel.LOW), new ImageSuffix("badge", 47, 47, Image.ResolutionLevel.LOW), new ImageSuffix("t50x50", 50, 50, Image.ResolutionLevel.LOW), new ImageSuffix("t60x60", 60, 60, Image.ResolutionLevel.LOW), new ImageSuffix("t67x67", 67, 67, Image.ResolutionLevel.LOW), new ImageSuffix("t80x80", 80, 80, Image.ResolutionLevel.LOW), new ImageSuffix("large", 100, 100, Image.ResolutionLevel.LOW), new ImageSuffix("t120x120", 120, 120, Image.ResolutionLevel.LOW), new ImageSuffix("t200x200", 200, 200, Image.ResolutionLevel.MEDIUM), new ImageSuffix("t240x240", 240, 240, Image.ResolutionLevel.MEDIUM), new ImageSuffix("t250x250", 250, 250, Image.ResolutionLevel.MEDIUM), new ImageSuffix("t300x300", 300, 300, Image.ResolutionLevel.MEDIUM), new ImageSuffix("t500x500", 500, 500, Image.ResolutionLevel.MEDIUM)});
    private static final List<ImageSuffix> VISUALS_IMAGE_SUFFIXES = UByte$$ExternalSyntheticBackport0.m(new Object[]{new ImageSuffix("t1240x260", 1240, GlobalMediaRouter.CallbackHandler.MSG_ROUTE_VOLUME_CHANGED, Image.ResolutionLevel.MEDIUM), new ImageSuffix("t2480x520", 2480, 520, Image.ResolutionLevel.MEDIUM)});
    private static final Pattern ON_URL_PATTERN = Pattern.compile("^https?://on.soundcloud.com/[0-9a-zA-Z]+$");

    private SoundcloudParsingHelper() {
    }

    public static synchronized String clientId() throws IOException, ExtractionException {
        if (!Utils.isNullOrEmpty(clientId)) {
            return clientId;
        }
        Downloader downloader = NewPipe.getDownloader();
        Elements elementsSelect = Jsoup.parse(downloader.get("https://soundcloud.com").responseBody()).select("script[src*=\"sndcdn.com/assets/\"][src$=\".js\"]");
        Collections.reverse(elementsSelect);
        Map<String, List<String>> mapM = UByte$$ExternalSyntheticBackport0.m(new Map.Entry[]{new AbstractMap.SimpleEntry("Range", UByte$$ExternalSyntheticBackport0.m(new Object[]{"bytes=0-50000"}))});
        Iterator it = elementsSelect.iterator();
        while (it.hasNext()) {
            String strAttr = ((Element) it.next()).attr("src");
            if (!Utils.isNullOrEmpty(strAttr)) {
                try {
                    String strMatchGroup1 = Parser.matchGroup1(",client_id:\"(.*?)\"", downloader.get(strAttr, mapM).responseBody());
                    clientId = strMatchGroup1;
                    return strMatchGroup1;
                } catch (Parser.RegexException unused) {
                    continue;
                }
            }
        }
        throw new ExtractionException("Couldn't extract client id");
    }

    @Nullable
    public static DateWrapper parseDate(String str) throws ParsingException {
        try {
            return DateWrapper.fromInstant(str);
        } catch (DateTimeParseException e) {
            try {
                return new DateWrapper(OffsetDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss +0000")));
            } catch (DateTimeParseException e2) {
                e2.addSuppressed(e);
                throw new ParsingException("Could not parse date: \"" + str + "\"", e2);
            }
        }
    }

    public static JsonObject resolveFor(@Nonnull Downloader downloader, String str) throws IOException, ExtractionException {
        try {
            return JsonParser.object().from(downloader.get("https://api-v2.soundcloud.com/resolve?url=" + Utils.encodeUrlUtf8(str) + "&client_id=" + clientId(), ServiceList.SoundCloud.getLocalization()).responseBody());
        } catch (JsonParserException e) {
            throw new ParsingException("Could not parse json response", e);
        }
    }

    public static String resolveUrlWithEmbedPlayer(String str) throws ReCaptchaException, IOException {
        return Jsoup.parse(NewPipe.getDownloader().get("https://w.soundcloud.com/player/?url=" + Utils.encodeUrlUtf8(str), ServiceList.SoundCloud.getLocalization()).responseBody()).select("link[rel=\"canonical\"]").first().attr("abs:href");
    }

    public static String resolveIdWithWidgetApi(String str) throws IOException, ParsingException {
        if (ON_URL_PATTERN.matcher(str).find()) {
            try {
                str = NewPipe.getDownloader().head(str).latestUrl().split("\\?")[0];
            } catch (ExtractionException e) {
                throw new ParsingException("Could not follow on.soundcloud.com redirect", e);
            }
        }
        if (str.charAt(str.length() - 1) == '/') {
            str = str.substring(0, str.length() - 1);
        }
        try {
            try {
                return String.valueOf(JsonUtils.getValue(JsonParser.object().from(NewPipe.getDownloader().get("https://api-widget.soundcloud.com/resolve?url=" + Utils.encodeUrlUtf8(Utils.stringToURL(Utils.removeMAndWWWFromUrl(str.toLowerCase())).toString()) + "&format=json&client_id=" + clientId(), ServiceList.SoundCloud.getLocalization()).responseBody()), "id"));
            } catch (JsonParserException e2) {
                throw new ParsingException("Could not parse JSON response", e2);
            } catch (ExtractionException e3) {
                throw new ParsingException("Could not resolve id with embedded player. ClientId not extracted", e3);
            }
        } catch (MalformedURLException unused) {
            throw new IllegalArgumentException("The given URL is not valid");
        }
    }

    public static String getUsersFromApiMinItems(int i, ChannelInfoItemsCollector channelInfoItemsCollector, String str) throws ReCaptchaException, IOException, ParsingException {
        String usersFromApi = getUsersFromApi(channelInfoItemsCollector, str);
        while (!usersFromApi.isEmpty() && channelInfoItemsCollector.getItems().size() < i) {
            usersFromApi = getUsersFromApi(channelInfoItemsCollector, usersFromApi);
        }
        return usersFromApi;
    }

    @Nonnull
    public static String getUsersFromApi(ChannelInfoItemsCollector channelInfoItemsCollector, String str) throws ReCaptchaException, IOException, ParsingException {
        try {
            JsonObject jsonObjectFrom = JsonParser.object().from(NewPipe.getDownloader().get(str, ServiceList.SoundCloud.getLocalization()).responseBody());
            for (Object obj : jsonObjectFrom.getArray("collection")) {
                if (obj instanceof JsonObject) {
                    channelInfoItemsCollector.commit(new SoundcloudChannelInfoItemExtractor((JsonObject) obj));
                }
            }
            return getNextPageUrl(jsonObjectFrom);
        } catch (JsonParserException e) {
            throw new ParsingException("Could not parse json response", e);
        }
    }

    public static String getStreamsFromApiMinItems(int i, StreamInfoItemsCollector streamInfoItemsCollector, String str) throws ReCaptchaException, IOException, ParsingException {
        String streamsFromApi = getStreamsFromApi(streamInfoItemsCollector, str);
        while (!streamsFromApi.isEmpty() && streamInfoItemsCollector.getItems().size() < i) {
            streamsFromApi = getStreamsFromApi(streamInfoItemsCollector, streamsFromApi);
        }
        return streamsFromApi;
    }

    @Nonnull
    public static String getStreamsFromApi(StreamInfoItemsCollector streamInfoItemsCollector, String str, boolean z) throws ReCaptchaException, IOException, ParsingException {
        Response response = NewPipe.getDownloader().get(str, ServiceList.SoundCloud.getLocalization());
        if (response.responseCode() >= 400) {
            throw new IOException("Could not get streams from API, HTTP " + response.responseCode());
        }
        try {
            JsonObject jsonObjectFrom = JsonParser.object().from(response.responseBody());
            for (Object obj : jsonObjectFrom.getArray("collection")) {
                if (obj instanceof JsonObject) {
                    JsonObject object = (JsonObject) obj;
                    if (z) {
                        object = object.getObject("track");
                    }
                    streamInfoItemsCollector.commit((StreamInfoItemExtractor) new SoundcloudStreamInfoItemExtractor(object));
                }
            }
            return getNextPageUrl(jsonObjectFrom);
        } catch (JsonParserException e) {
            throw new ParsingException("Could not parse json response", e);
        }
    }

    @Nonnull
    private static String getNextPageUrl(@Nonnull JsonObject jsonObject) {
        try {
            String string = jsonObject.getString("next_href");
            if (string.contains("client_id=")) {
                return string;
            }
            return string + "&client_id=" + clientId();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getStreamsFromApi(StreamInfoItemsCollector streamInfoItemsCollector, String str) throws ReCaptchaException, IOException, ParsingException {
        return getStreamsFromApi(streamInfoItemsCollector, str, false);
    }

    public static String getInfoItemsFromApi(final MultiInfoItemsCollector multiInfoItemsCollector, String str) throws ReCaptchaException, IOException, ParsingException {
        Response response = NewPipe.getDownloader().get(str, ServiceList.SoundCloud.getLocalization());
        if (response.responseCode() >= 400) {
            throw new IOException("Could not get streams from API, HTTP " + response.responseCode());
        }
        try {
            JsonObject jsonObjectFrom = JsonParser.object().from(response.responseBody());
            jsonObjectFrom.getArray("collection").streamAsJsonObjects().forEach(new Consumer() { // from class: org.schabi.newpipe.extractor.services.soundcloud.SoundcloudParsingHelper$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    SoundcloudParsingHelper.lambda$getInfoItemsFromApi$0(multiInfoItemsCollector, (JsonObject) obj);
                }

                public /* synthetic */ Consumer andThen(Consumer consumer) {
                    return Consumer$CC.$default$andThen(this, consumer);
                }
            });
            try {
                String string = jsonObjectFrom.getString("next_href");
                if (string.contains("client_id=")) {
                    return string;
                }
                return string + "&client_id=" + clientId();
            } catch (Exception unused) {
                return "";
            }
        } catch (JsonParserException e) {
            throw new ParsingException("Could not parse json response", e);
        }
    }

    static /* synthetic */ void lambda$getInfoItemsFromApi$0(MultiInfoItemsCollector multiInfoItemsCollector, JsonObject jsonObject) {
        Object soundcloudPlaylistInfoItemExtractor;
        String string = jsonObject.getString("kind", "");
        string.hashCode();
        switch (string) {
            case "like":
                JsonObject object = jsonObject.getObject("playlist", null);
                if (object == null) {
                    soundcloudPlaylistInfoItemExtractor = new SoundcloudLikesInfoItemExtractor(jsonObject);
                } else {
                    soundcloudPlaylistInfoItemExtractor = new SoundcloudPlaylistInfoItemExtractor(object);
                }
                multiInfoItemsCollector.commit(soundcloudPlaylistInfoItemExtractor);
                break;
            case "user":
                multiInfoItemsCollector.commit(new SoundcloudChannelInfoItemExtractor(jsonObject));
                break;
            case "track":
                multiInfoItemsCollector.commit(new SoundcloudStreamInfoItemExtractor(jsonObject));
                break;
            case "playlist":
                multiInfoItemsCollector.commit(new SoundcloudPlaylistInfoItemExtractor(jsonObject));
                break;
        }
    }

    @Nonnull
    public static String getUploaderUrl(JsonObject jsonObject) {
        return Utils.replaceHttpWithHttps(jsonObject.getObject("user").getString("permalink_url", ""));
    }

    @Nonnull
    public static String getAvatarUrl(JsonObject jsonObject) {
        return Utils.replaceHttpWithHttps(jsonObject.getObject("user").getString("avatar_url", ""));
    }

    @Nonnull
    public static String getUploaderName(JsonObject jsonObject) {
        return jsonObject.getObject("user").getString(HintConstants.AUTOFILL_HINT_USERNAME, "");
    }

    @Nonnull
    public static List<Image> getAllImagesFromTrackObject(@Nonnull JsonObject jsonObject) throws ParsingException {
        String string = jsonObject.getString("artwork_url");
        if (string != null) {
            return getAllImagesFromArtworkOrAvatarUrl(string);
        }
        String string2 = jsonObject.getObject("user").getString("avatar_url");
        if (string2 != null) {
            return getAllImagesFromArtworkOrAvatarUrl(string2);
        }
        throw new ParsingException("Could not get track or track user's thumbnails");
    }

    @Nonnull
    public static List<Image> getAllImagesFromArtworkOrAvatarUrl(@Nullable String str) {
        if (Utils.isNullOrEmpty(str)) {
            return Collections.EMPTY_LIST;
        }
        return getAllImagesFromImageUrlReturned(str.replace("-large.", "-%s."), ALBUMS_AND_ARTWORKS_IMAGE_SUFFIXES);
    }

    @Nonnull
    public static List<Image> getAllImagesFromVisualUrl(@Nullable String str) {
        if (Utils.isNullOrEmpty(str)) {
            return Collections.EMPTY_LIST;
        }
        return getAllImagesFromImageUrlReturned(str.replace("-original.", "-%s."), VISUALS_IMAGE_SUFFIXES);
    }

    private static List<Image> getAllImagesFromImageUrlReturned(@Nonnull final String str, @Nonnull List<ImageSuffix> list) {
        return (List) Collection.EL.stream(list).map(new Function() { // from class: org.schabi.newpipe.extractor.services.soundcloud.SoundcloudParsingHelper$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return SoundcloudParsingHelper.lambda$getAllImagesFromImageUrlReturned$1(str, (ImageSuffix) obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).collect(Collectors.toUnmodifiableList());
    }

    static /* synthetic */ Image lambda$getAllImagesFromImageUrlReturned$1(String str, ImageSuffix imageSuffix) {
        return new Image(String.format(str, imageSuffix.getSuffix()), imageSuffix.getHeight(), imageSuffix.getWidth(), imageSuffix.getResolutionLevel());
    }
}
