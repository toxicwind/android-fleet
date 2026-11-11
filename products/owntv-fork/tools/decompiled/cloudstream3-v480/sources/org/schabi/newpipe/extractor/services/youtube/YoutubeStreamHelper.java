package org.schabi.newpipe.extractor.services.youtube;

import androidx.exifinterface.media.ExifInterface;
import com.grack.nanojson.JsonBuilder;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import kotlin.UByte$$ExternalSyntheticBackport0;
import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.localization.ContentCountry;
import org.schabi.newpipe.extractor.localization.Localization;
import org.schabi.newpipe.extractor.utils.JsonUtils;

/* JADX INFO: loaded from: classes8.dex */
public final class YoutubeStreamHelper {
    private static final String BASE_YT_DESKTOP_WATCH_URL = "https://www.youtube.com/watch?v=";
    private static final String PLAYER = "player";
    private static final String PO_TOKEN = "poToken";
    private static final String SERVICE_INTEGRITY_DIMENSIONS = "serviceIntegrityDimensions";

    private YoutubeStreamHelper() {
    }

    @Nonnull
    public static JsonObject getWebMetadataPlayerResponse(@Nonnull Localization localization, @Nonnull ContentCountry contentCountry, @Nonnull String str) throws IOException, ExtractionException {
        InnertubeClientRequestInfo innertubeClientRequestInfoOfWebClient = InnertubeClientRequestInfo.ofWebClient();
        innertubeClientRequestInfoOfWebClient.clientInfo.clientVersion = YoutubeParsingHelper.getClientVersion();
        Map<String, List<String>> youTubeHeaders = YoutubeParsingHelper.getYouTubeHeaders();
        innertubeClientRequestInfoOfWebClient.clientInfo.visitorData = YoutubeParsingHelper.getVisitorDataFromInnertube(innertubeClientRequestInfoOfWebClient, localization, contentCountry, youTubeHeaders, YoutubeParsingHelper.YOUTUBEI_V1_URL, null, false);
        JsonBuilder<JsonObject> jsonBuilderPrepareJsonBuilder = YoutubeParsingHelper.prepareJsonBuilder(localization, contentCountry, innertubeClientRequestInfoOfWebClient, null);
        addVideoIdCpnAndOkChecks(jsonBuilderPrepareJsonBuilder, str, null);
        return JsonUtils.toJsonObject(YoutubeParsingHelper.getValidJsonResponseBody(NewPipe.getDownloader().postWithContentTypeJson("https://www.youtube.com/youtubei/v1/player?prettyPrint=false&$fields=microformat,videoDetails.thumbnail.thumbnails,videoDetails.videoId", youTubeHeaders, JsonWriter.string(jsonBuilderPrepareJsonBuilder.done()).getBytes(StandardCharsets.UTF_8), localization)));
    }

    @Nonnull
    public static JsonObject getWebEmbeddedPlayerResponse(@Nonnull Localization localization, @Nonnull ContentCountry contentCountry, @Nonnull String str, @Nonnull String str2, @Nullable PoTokenResult poTokenResult, int i) throws IOException, ExtractionException {
        Localization localization2;
        ContentCountry contentCountry2;
        String visitorDataFromInnertube;
        InnertubeClientRequestInfo innertubeClientRequestInfoOfWebEmbeddedPlayerClient = InnertubeClientRequestInfo.ofWebEmbeddedPlayerClient();
        HashMap map = new HashMap(YoutubeParsingHelper.getClientHeaders("56", "1.20260122.01.00"));
        map.putAll(YoutubeParsingHelper.getOriginReferrerHeaders("https://www.youtube.com"));
        String str3 = BASE_YT_DESKTOP_WATCH_URL + str;
        InnertubeClientRequestInfo.ClientInfo clientInfo = innertubeClientRequestInfoOfWebEmbeddedPlayerClient.clientInfo;
        if (poTokenResult == null) {
            localization2 = localization;
            contentCountry2 = contentCountry;
            visitorDataFromInnertube = YoutubeParsingHelper.getVisitorDataFromInnertube(innertubeClientRequestInfoOfWebEmbeddedPlayerClient, localization2, contentCountry2, map, YoutubeParsingHelper.YOUTUBEI_V1_URL, str3, false);
        } else {
            localization2 = localization;
            contentCountry2 = contentCountry;
            visitorDataFromInnertube = poTokenResult.visitorData;
        }
        clientInfo.visitorData = visitorDataFromInnertube;
        JsonBuilder<JsonObject> jsonBuilderPrepareJsonBuilder = YoutubeParsingHelper.prepareJsonBuilder(localization2, contentCountry2, innertubeClientRequestInfoOfWebEmbeddedPlayerClient, str3);
        addVideoIdCpnAndOkChecks(jsonBuilderPrepareJsonBuilder, str, str2);
        addPlaybackContext(jsonBuilderPrepareJsonBuilder, str3, i);
        if (poTokenResult != null) {
            addPoToken(jsonBuilderPrepareJsonBuilder, poTokenResult.playerRequestPoToken);
        }
        return JsonUtils.toJsonObject(YoutubeParsingHelper.getValidJsonResponseBody(NewPipe.getDownloader().postWithContentTypeJson("https://www.youtube.com/youtubei/v1/player?prettyPrint=false", map, JsonWriter.string(jsonBuilderPrepareJsonBuilder.done()).getBytes(StandardCharsets.UTF_8), localization2)));
    }

    public static JsonObject getAndroidPlayerResponse(@Nonnull ContentCountry contentCountry, @Nonnull Localization localization, @Nonnull String str, @Nonnull String str2, @Nonnull PoTokenResult poTokenResult) throws IOException, ExtractionException {
        InnertubeClientRequestInfo innertubeClientRequestInfoOfAndroidClient = InnertubeClientRequestInfo.ofAndroidClient();
        innertubeClientRequestInfoOfAndroidClient.clientInfo.visitorData = poTokenResult.visitorData;
        Map<String, List<String>> mobileClientHeaders = getMobileClientHeaders(YoutubeParsingHelper.getAndroidUserAgent(localization));
        JsonBuilder<JsonObject> jsonBuilderPrepareJsonBuilder = YoutubeParsingHelper.prepareJsonBuilder(localization, contentCountry, innertubeClientRequestInfoOfAndroidClient, null);
        addVideoIdCpnAndOkChecks(jsonBuilderPrepareJsonBuilder, str, str2);
        addPoToken(jsonBuilderPrepareJsonBuilder, poTokenResult.playerRequestPoToken);
        return JsonUtils.toJsonObject(YoutubeParsingHelper.getValidJsonResponseBody(NewPipe.getDownloader().postWithContentTypeJson("https://youtubei.googleapis.com/youtubei/v1/player?prettyPrint=false&t=" + YoutubeParsingHelper.generateTParameter() + "&id=" + str, mobileClientHeaders, JsonWriter.string(jsonBuilderPrepareJsonBuilder.done()).getBytes(StandardCharsets.UTF_8), localization)));
    }

    public static JsonObject getAndroidReelPlayerResponse(@Nonnull ContentCountry contentCountry, @Nonnull Localization localization, @Nonnull String str, @Nonnull String str2) throws IOException, ExtractionException {
        InnertubeClientRequestInfo innertubeClientRequestInfoOfAndroidClient = InnertubeClientRequestInfo.ofAndroidClient();
        Map<String, List<String>> mobileClientHeaders = getMobileClientHeaders(YoutubeParsingHelper.getAndroidUserAgent(localization));
        innertubeClientRequestInfoOfAndroidClient.clientInfo.visitorData = YoutubeParsingHelper.getVisitorDataFromInnertube(innertubeClientRequestInfoOfAndroidClient, localization, contentCountry, mobileClientHeaders, YoutubeParsingHelper.YOUTUBEI_V1_GAPIS_URL, null, false);
        JsonBuilder<JsonObject> jsonBuilderPrepareJsonBuilder = YoutubeParsingHelper.prepareJsonBuilder(localization, contentCountry, innertubeClientRequestInfoOfAndroidClient, null);
        jsonBuilderPrepareJsonBuilder.object("playerRequest");
        addVideoIdCpnAndOkChecks(jsonBuilderPrepareJsonBuilder, str, str2);
        jsonBuilderPrepareJsonBuilder.end().value("disablePlayerResponse", false);
        return JsonUtils.toJsonObject(YoutubeParsingHelper.getValidJsonResponseBody(NewPipe.getDownloader().postWithContentTypeJson("https://youtubei.googleapis.com/youtubei/v1/reel/reel_item_watch?prettyPrint=false&t=" + YoutubeParsingHelper.generateTParameter() + "&id=" + str + "&$fields=playerResponse", mobileClientHeaders, JsonWriter.string(jsonBuilderPrepareJsonBuilder.done()).getBytes(StandardCharsets.UTF_8), localization))).getObject("playerResponse");
    }

    public static JsonObject getIosPlayerResponse(@Nonnull ContentCountry contentCountry, @Nonnull Localization localization, @Nonnull String str, @Nonnull String str2, @Nullable PoTokenResult poTokenResult) throws IOException, ExtractionException {
        ContentCountry contentCountry2;
        Localization localization2;
        String visitorDataFromInnertube;
        InnertubeClientRequestInfo innertubeClientRequestInfoOfIosClient = InnertubeClientRequestInfo.ofIosClient();
        Map<String, List<String>> mobileClientHeaders = getMobileClientHeaders(YoutubeParsingHelper.getIosUserAgent(localization));
        InnertubeClientRequestInfo.ClientInfo clientInfo = innertubeClientRequestInfoOfIosClient.clientInfo;
        if (poTokenResult == null) {
            contentCountry2 = contentCountry;
            localization2 = localization;
            visitorDataFromInnertube = YoutubeParsingHelper.getVisitorDataFromInnertube(innertubeClientRequestInfoOfIosClient, localization2, contentCountry2, mobileClientHeaders, YoutubeParsingHelper.YOUTUBEI_V1_URL, null, false);
        } else {
            contentCountry2 = contentCountry;
            localization2 = localization;
            visitorDataFromInnertube = poTokenResult.visitorData;
        }
        clientInfo.visitorData = visitorDataFromInnertube;
        JsonBuilder<JsonObject> jsonBuilderPrepareJsonBuilder = YoutubeParsingHelper.prepareJsonBuilder(localization2, contentCountry2, innertubeClientRequestInfoOfIosClient, null);
        addVideoIdCpnAndOkChecks(jsonBuilderPrepareJsonBuilder, str, str2);
        if (poTokenResult != null) {
            addPoToken(jsonBuilderPrepareJsonBuilder, poTokenResult.playerRequestPoToken);
        }
        return JsonUtils.toJsonObject(YoutubeParsingHelper.getValidJsonResponseBody(NewPipe.getDownloader().postWithContentTypeJson("https://youtubei.googleapis.com/youtubei/v1/player?prettyPrint=false&t=" + YoutubeParsingHelper.generateTParameter() + "&id=" + str, mobileClientHeaders, JsonWriter.string(jsonBuilderPrepareJsonBuilder.done()).getBytes(StandardCharsets.UTF_8), localization2)));
    }

    public static JsonObject getVisionOsPlayerResponse(@Nonnull ContentCountry contentCountry, @Nonnull Localization localization, @Nonnull String str, @Nonnull String str2) throws IOException, ExtractionException {
        InnertubeClientRequestInfo innertubeClientRequestInfoOfVisionOsClient = InnertubeClientRequestInfo.ofVisionOsClient();
        Map<String, List<String>> mobileClientHeaders = getMobileClientHeaders(YoutubeParsingHelper.getVisionOsUserAgent(localization));
        innertubeClientRequestInfoOfVisionOsClient.clientInfo.visitorData = YoutubeParsingHelper.getVisitorDataFromInnertube(innertubeClientRequestInfoOfVisionOsClient, localization, contentCountry, mobileClientHeaders, YoutubeParsingHelper.YOUTUBEI_V1_URL, null, false);
        JsonBuilder<JsonObject> jsonBuilderPrepareJsonBuilder = YoutubeParsingHelper.prepareJsonBuilder(localization, contentCountry, innertubeClientRequestInfoOfVisionOsClient, null);
        addVideoIdCpnAndOkChecks(jsonBuilderPrepareJsonBuilder, str, str2);
        return JsonUtils.toJsonObject(YoutubeParsingHelper.getValidJsonResponseBody(NewPipe.getDownloader().postWithContentTypeJson("https://youtubei.googleapis.com/youtubei/v1/player?prettyPrint=false&t=" + YoutubeParsingHelper.generateTParameter() + "&id=" + str, mobileClientHeaders, JsonWriter.string(jsonBuilderPrepareJsonBuilder.done()).getBytes(StandardCharsets.UTF_8), localization)));
    }

    private static void addVideoIdCpnAndOkChecks(@Nonnull JsonBuilder<JsonObject> jsonBuilder, @Nonnull String str, @Nullable String str2) {
        jsonBuilder.value(YoutubeParsingHelper.VIDEO_ID, str);
        if (str2 != null) {
            jsonBuilder.value(YoutubeParsingHelper.CPN, str2);
        }
        jsonBuilder.value(YoutubeParsingHelper.CONTENT_CHECK_OK, true).value(YoutubeParsingHelper.RACY_CHECK_OK, true);
    }

    private static void addPlaybackContext(@Nonnull JsonBuilder<JsonObject> jsonBuilder, @Nonnull String str, int i) {
        jsonBuilder.object("playbackContext").object("contentPlaybackContext").value("signatureTimestamp", i).value("referer", str).end().end();
    }

    private static void addPoToken(@Nonnull JsonBuilder<JsonObject> jsonBuilder, @Nonnull String str) {
        jsonBuilder.object(SERVICE_INTEGRITY_DIMENSIONS).value(PO_TOKEN, str).end();
    }

    @Nonnull
    private static Map<String, List<String>> getMobileClientHeaders(@Nonnull String str) {
        return UByte$$ExternalSyntheticBackport0.m(new Map.Entry[]{new AbstractMap.SimpleEntry("User-Agent", UByte$$ExternalSyntheticBackport0.m(new Object[]{str})), new AbstractMap.SimpleEntry("X-Goog-Api-Format-Version", UByte$$ExternalSyntheticBackport0.m(new Object[]{ExifInterface.GPS_MEASUREMENT_2D}))});
    }
}
