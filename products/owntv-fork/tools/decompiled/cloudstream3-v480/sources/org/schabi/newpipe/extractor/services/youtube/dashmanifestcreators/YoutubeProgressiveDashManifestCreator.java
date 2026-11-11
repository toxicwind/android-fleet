package org.schabi.newpipe.extractor.services.youtube.dashmanifestcreators;

import j$.util.Objects;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.services.youtube.ItagItem;
import org.schabi.newpipe.extractor.utils.ManifestCreatorCache;
import org.schabi.newpipe.extractor.utils.Pair;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: classes8.dex */
public final class YoutubeProgressiveDashManifestCreator {
    private static final ManifestCreatorCache<String, String> PROGRESSIVE_STREAMS_CACHE = new ManifestCreatorCache<>();

    private YoutubeProgressiveDashManifestCreator() {
    }

    @Nonnull
    public static String fromProgressiveStreamingUrl(@Nonnull String str, @Nonnull ItagItem itagItem, long j) throws CreationException {
        ManifestCreatorCache<String, String> manifestCreatorCache = PROGRESSIVE_STREAMS_CACHE;
        if (manifestCreatorCache.containsKey(str)) {
            return (String) ((Pair) Objects.requireNonNull(manifestCreatorCache.get(str))).getSecond();
        }
        long approxDurationMs = itagItem.getApproxDurationMs();
        if (approxDurationMs == -1) {
            if (j <= 0) {
                throw CreationException.couldNotAddElement(YoutubeDashManifestCreatorsUtils.MPD, "the duration of the stream could not be determined and durationSecondsFallback is <= 0");
            }
            approxDurationMs = 1000 * j;
        }
        Document documentGenerateDocumentAndDoCommonElementsGeneration = YoutubeDashManifestCreatorsUtils.generateDocumentAndDoCommonElementsGeneration(itagItem, approxDurationMs);
        generateBaseUrlElement(documentGenerateDocumentAndDoCommonElementsGeneration, str);
        generateSegmentBaseElement(documentGenerateDocumentAndDoCommonElementsGeneration, itagItem);
        generateInitializationElement(documentGenerateDocumentAndDoCommonElementsGeneration, itagItem);
        return YoutubeDashManifestCreatorsUtils.buildAndCacheResult(str, documentGenerateDocumentAndDoCommonElementsGeneration, manifestCreatorCache);
    }

    @Nonnull
    public static ManifestCreatorCache<String, String> getCache() {
        return PROGRESSIVE_STREAMS_CACHE;
    }

    private static void generateBaseUrlElement(@Nonnull Document document, @Nonnull String str) throws CreationException {
        try {
            Element element = (Element) document.getElementsByTagName(YoutubeDashManifestCreatorsUtils.REPRESENTATION).item(0);
            Element elementCreateElement = document.createElement(YoutubeDashManifestCreatorsUtils.BASE_URL);
            elementCreateElement.setTextContent(str);
            element.appendChild(elementCreateElement);
        } catch (DOMException e) {
            throw CreationException.couldNotAddElement(YoutubeDashManifestCreatorsUtils.BASE_URL, e);
        }
    }

    private static void generateSegmentBaseElement(@Nonnull Document document, @Nonnull ItagItem itagItem) throws CreationException {
        try {
            Element element = (Element) document.getElementsByTagName(YoutubeDashManifestCreatorsUtils.REPRESENTATION).item(0);
            Element elementCreateElement = document.createElement(YoutubeDashManifestCreatorsUtils.SEGMENT_BASE);
            String str = itagItem.getIndexStart() + "-" + itagItem.getIndexEnd();
            if (itagItem.getIndexStart() < 0 || itagItem.getIndexEnd() < 0) {
                throw CreationException.couldNotAddElement(YoutubeDashManifestCreatorsUtils.SEGMENT_BASE, "ItagItem's indexStart or indexEnd are < 0: " + str);
            }
            YoutubeDashManifestCreatorsUtils.setAttribute(elementCreateElement, document, "indexRange", str);
            element.appendChild(elementCreateElement);
        } catch (DOMException e) {
            throw CreationException.couldNotAddElement(YoutubeDashManifestCreatorsUtils.SEGMENT_BASE, e);
        }
    }

    private static void generateInitializationElement(@Nonnull Document document, @Nonnull ItagItem itagItem) throws CreationException {
        try {
            Element element = (Element) document.getElementsByTagName(YoutubeDashManifestCreatorsUtils.SEGMENT_BASE).item(0);
            Element elementCreateElement = document.createElement(YoutubeDashManifestCreatorsUtils.INITIALIZATION);
            String str = itagItem.getInitStart() + "-" + itagItem.getInitEnd();
            if (itagItem.getInitStart() < 0 || itagItem.getInitEnd() < 0) {
                throw CreationException.couldNotAddElement(YoutubeDashManifestCreatorsUtils.INITIALIZATION, "ItagItem's initStart and/or initEnd are/is < 0: " + str);
            }
            YoutubeDashManifestCreatorsUtils.setAttribute(elementCreateElement, document, "range", str);
            element.appendChild(elementCreateElement);
        } catch (DOMException e) {
            throw CreationException.couldNotAddElement(YoutubeDashManifestCreatorsUtils.INITIALIZATION, e);
        }
    }
}
