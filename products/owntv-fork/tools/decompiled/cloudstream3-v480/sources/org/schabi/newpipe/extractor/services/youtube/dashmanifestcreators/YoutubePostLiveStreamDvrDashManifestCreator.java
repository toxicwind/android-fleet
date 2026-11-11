package org.schabi.newpipe.extractor.services.youtube.dashmanifestcreators;

import androidx.exifinterface.media.ExifInterface;
import j$.util.Objects;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.downloader.Response;
import org.schabi.newpipe.extractor.services.youtube.DeliveryType;
import org.schabi.newpipe.extractor.services.youtube.ItagItem;
import org.schabi.newpipe.extractor.utils.ManifestCreatorCache;
import org.schabi.newpipe.extractor.utils.Pair;
import org.schabi.newpipe.extractor.utils.Utils;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: classes8.dex */
public final class YoutubePostLiveStreamDvrDashManifestCreator {
    private static final ManifestCreatorCache<String, String> POST_LIVE_DVR_STREAMS_CACHE = new ManifestCreatorCache<>();

    private YoutubePostLiveStreamDvrDashManifestCreator() {
    }

    @Nonnull
    public static String fromPostLiveStreamDvrStreamingUrl(@Nonnull String str, @Nonnull ItagItem itagItem, int i, long j) throws CreationException {
        ManifestCreatorCache<String, String> manifestCreatorCache = POST_LIVE_DVR_STREAMS_CACHE;
        if (manifestCreatorCache.containsKey(str)) {
            return (String) ((Pair) Objects.requireNonNull(manifestCreatorCache.get(str))).getSecond();
        }
        if (i <= 0) {
            throw new CreationException("targetDurationSec value is <= 0: " + i);
        }
        try {
            Response initializationResponse = YoutubeDashManifestCreatorsUtils.getInitializationResponse(str, itagItem, DeliveryType.LIVE);
            String strReplace = initializationResponse.latestUrl().replace(YoutubeDashManifestCreatorsUtils.SQ_0, "").replace(YoutubeDashManifestCreatorsUtils.RN_0, "").replace(YoutubeDashManifestCreatorsUtils.ALR_YES, "");
            int iResponseCode = initializationResponse.responseCode();
            if (iResponseCode != 200) {
                throw new CreationException("Could not get the initialization sequence: response code " + iResponseCode);
            }
            Map<String, List<String>> mapResponseHeaders = initializationResponse.responseHeaders();
            String str2 = mapResponseHeaders.get("X-Head-Time-Millis").get(0);
            String str3 = mapResponseHeaders.get("X-Head-Seqnum").get(0);
            if (Utils.isNullOrEmpty(str3)) {
                throw new CreationException("Could not get the number of segments");
            }
            try {
                j = Long.parseLong(str2);
            } catch (NumberFormatException unused) {
            }
            Document documentGenerateDocumentAndDoCommonElementsGeneration = YoutubeDashManifestCreatorsUtils.generateDocumentAndDoCommonElementsGeneration(itagItem, j);
            YoutubeDashManifestCreatorsUtils.generateSegmentTemplateElement(documentGenerateDocumentAndDoCommonElementsGeneration, strReplace, DeliveryType.LIVE);
            YoutubeDashManifestCreatorsUtils.generateSegmentTimelineElement(documentGenerateDocumentAndDoCommonElementsGeneration);
            generateSegmentElementForPostLiveDvrStreams(documentGenerateDocumentAndDoCommonElementsGeneration, i, str3);
            return YoutubeDashManifestCreatorsUtils.buildAndCacheResult(str, documentGenerateDocumentAndDoCommonElementsGeneration, POST_LIVE_DVR_STREAMS_CACHE);
        } catch (IndexOutOfBoundsException e) {
            throw new CreationException("Could not get the value of the X-Head-Time-Millis or the X-Head-Seqnum header", e);
        }
    }

    @Nonnull
    public static ManifestCreatorCache<String, String> getCache() {
        return POST_LIVE_DVR_STREAMS_CACHE;
    }

    private static void generateSegmentElementForPostLiveDvrStreams(@Nonnull Document document, int i, @Nonnull String str) throws CreationException {
        try {
            Element element = (Element) document.getElementsByTagName(YoutubeDashManifestCreatorsUtils.SEGMENT_TIMELINE).item(0);
            Element elementCreateElement = document.createElement(ExifInterface.LATITUDE_SOUTH);
            YoutubeDashManifestCreatorsUtils.setAttribute(elementCreateElement, document, "d", String.valueOf(i * 1000));
            YoutubeDashManifestCreatorsUtils.setAttribute(elementCreateElement, document, "r", str);
            element.appendChild(elementCreateElement);
        } catch (DOMException e) {
            throw CreationException.couldNotAddElement("segment (S)", e);
        }
    }
}
