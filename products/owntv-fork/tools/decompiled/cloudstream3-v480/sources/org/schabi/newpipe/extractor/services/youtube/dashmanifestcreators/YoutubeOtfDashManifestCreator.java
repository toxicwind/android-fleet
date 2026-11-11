package org.schabi.newpipe.extractor.services.youtube.dashmanifestcreators;

import androidx.exifinterface.media.ExifInterface;
import j$.util.Objects;
import java.util.Arrays;
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
public final class YoutubeOtfDashManifestCreator {
    private static final ManifestCreatorCache<String, String> OTF_STREAMS_CACHE = new ManifestCreatorCache<>();

    private YoutubeOtfDashManifestCreator() {
    }

    @Nonnull
    public static String fromOtfStreamingUrl(@Nonnull String str, @Nonnull ItagItem itagItem, long j) throws CreationException {
        long streamDuration;
        ManifestCreatorCache<String, String> manifestCreatorCache = OTF_STREAMS_CACHE;
        if (manifestCreatorCache.containsKey(str)) {
            return (String) ((Pair) Objects.requireNonNull(manifestCreatorCache.get(str))).getSecond();
        }
        Response initializationResponse = YoutubeDashManifestCreatorsUtils.getInitializationResponse(str, itagItem, DeliveryType.OTF);
        String strReplace = initializationResponse.latestUrl().replace(YoutubeDashManifestCreatorsUtils.SQ_0, "").replace(YoutubeDashManifestCreatorsUtils.RN_0, "").replace(YoutubeDashManifestCreatorsUtils.ALR_YES, "");
        int iResponseCode = initializationResponse.responseCode();
        if (iResponseCode != 200) {
            throw new CreationException("Could not get the initialization URL: response code " + iResponseCode);
        }
        try {
            String[] strArrSplit = initializationResponse.responseBody().split("Segment-Durations-Ms: ")[1].split("\n")[0].split(",");
            int length = strArrSplit.length - 1;
            if (Utils.isBlank(strArrSplit[length])) {
                strArrSplit = (String[]) Arrays.copyOf(strArrSplit, length);
            }
            try {
                streamDuration = getStreamDuration(strArrSplit);
            } catch (CreationException unused) {
                streamDuration = j * 1000;
            }
            Document documentGenerateDocumentAndDoCommonElementsGeneration = YoutubeDashManifestCreatorsUtils.generateDocumentAndDoCommonElementsGeneration(itagItem, streamDuration);
            YoutubeDashManifestCreatorsUtils.generateSegmentTemplateElement(documentGenerateDocumentAndDoCommonElementsGeneration, strReplace, DeliveryType.OTF);
            YoutubeDashManifestCreatorsUtils.generateSegmentTimelineElement(documentGenerateDocumentAndDoCommonElementsGeneration);
            generateSegmentElementsForOtfStreams(strArrSplit, documentGenerateDocumentAndDoCommonElementsGeneration);
            return YoutubeDashManifestCreatorsUtils.buildAndCacheResult(str, documentGenerateDocumentAndDoCommonElementsGeneration, OTF_STREAMS_CACHE);
        } catch (Exception e) {
            throw new CreationException("Could not get segment durations", e);
        }
    }

    @Nonnull
    public static ManifestCreatorCache<String, String> getCache() {
        return OTF_STREAMS_CACHE;
    }

    private static void generateSegmentElementsForOtfStreams(@Nonnull String[] strArr, @Nonnull Document document) throws CreationException {
        try {
            Element element = (Element) document.getElementsByTagName(YoutubeDashManifestCreatorsUtils.SEGMENT_TIMELINE).item(0);
            for (String str : strArr) {
                Element elementCreateElement = document.createElement(ExifInterface.LATITUDE_SOUTH);
                String[] strArrSplit = str.split("\\(r=");
                Integer.parseInt(strArrSplit[0]);
                if (strArrSplit.length > 1) {
                    YoutubeDashManifestCreatorsUtils.setAttribute(elementCreateElement, document, "r", String.valueOf(Integer.parseInt(Utils.removeNonDigitCharacters(strArrSplit[1]))));
                }
                YoutubeDashManifestCreatorsUtils.setAttribute(elementCreateElement, document, "d", strArrSplit[0]);
                element.appendChild(elementCreateElement);
            }
        } catch (IllegalStateException | IndexOutOfBoundsException | NumberFormatException | DOMException e) {
            throw CreationException.couldNotAddElement("segment (S)", e);
        }
    }

    private static long getStreamDuration(@Nonnull String[] strArr) throws CreationException {
        try {
            long j = 0;
            for (String str : strArr) {
                String[] strArrSplit = str.split("\\(r=");
                long j2 = strArrSplit.length > 1 ? Long.parseLong(Utils.removeNonDigitCharacters(strArrSplit[1])) : 0L;
                long j3 = Integer.parseInt(strArrSplit[0]);
                j += j3 + (j2 * j3);
            }
            return j;
        } catch (NumberFormatException e) {
            throw new CreationException("Could not get stream length from sequences list", e);
        }
    }
}
