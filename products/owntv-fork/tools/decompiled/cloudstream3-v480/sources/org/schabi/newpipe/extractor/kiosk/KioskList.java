package org.schabi.newpipe.extractor.kiosk;

import j$.util.Collection;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory;
import org.schabi.newpipe.extractor.localization.ContentCountry;
import org.schabi.newpipe.extractor.localization.Localization;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class KioskList {

    @Nullable
    private ContentCountry forcedContentCountry;

    @Nullable
    private Localization forcedLocalization;
    private final StreamingService service;
    private final HashMap<String, KioskEntry> kioskList = new HashMap<>();
    private String defaultKiosk = null;

    public interface KioskExtractorFactory {
        KioskExtractor createNewKiosk(StreamingService streamingService, String str, String str2) throws IOException, ExtractionException;
    }

    private static class KioskEntry {
        final KioskExtractorFactory extractorFactory;
        final ListLinkHandlerFactory handlerFactory;

        KioskEntry(KioskExtractorFactory kioskExtractorFactory, ListLinkHandlerFactory listLinkHandlerFactory) {
            this.extractorFactory = kioskExtractorFactory;
            this.handlerFactory = listLinkHandlerFactory;
        }
    }

    public KioskList(StreamingService streamingService) {
        this.service = streamingService;
    }

    public void addKioskEntry(KioskExtractorFactory kioskExtractorFactory, ListLinkHandlerFactory listLinkHandlerFactory, String str) throws Exception {
        if (this.kioskList.get(str) != null) {
            throw new Exception("Kiosk with type " + str + " already exists.");
        }
        this.kioskList.put(str, new KioskEntry(kioskExtractorFactory, listLinkHandlerFactory));
    }

    public void setDefaultKiosk(String str) {
        this.defaultKiosk = str;
    }

    public KioskExtractor getDefaultKioskExtractor() throws IOException, ExtractionException {
        return getDefaultKioskExtractor(null);
    }

    public KioskExtractor getDefaultKioskExtractor(Page page) throws IOException, ExtractionException {
        return getDefaultKioskExtractor(page, NewPipe.getPreferredLocalization());
    }

    public KioskExtractor getDefaultKioskExtractor(Page page, Localization localization) throws IOException, ExtractionException {
        if (!Utils.isNullOrEmpty(this.defaultKiosk)) {
            return getExtractorById(this.defaultKiosk, page, localization);
        }
        String str = (String) Collection.EL.stream(this.kioskList.keySet()).findAny().orElse(null);
        if (str != null) {
            return getExtractorById(str, page, localization);
        }
        return null;
    }

    public String getDefaultKioskId() {
        return this.defaultKiosk;
    }

    public KioskExtractor getExtractorById(String str, Page page) throws IOException, ExtractionException {
        return getExtractorById(str, page, NewPipe.getPreferredLocalization());
    }

    public KioskExtractor getExtractorById(String str, Page page, Localization localization) throws IOException, ExtractionException {
        KioskEntry kioskEntry = this.kioskList.get(str);
        if (kioskEntry == null) {
            throw new ExtractionException("No kiosk found with the type: " + str);
        }
        KioskExtractor kioskExtractorCreateNewKiosk = kioskEntry.extractorFactory.createNewKiosk(this.service, kioskEntry.handlerFactory.fromId(str).getUrl(), str);
        Localization localization2 = this.forcedLocalization;
        if (localization2 != null) {
            kioskExtractorCreateNewKiosk.forceLocalization(localization2);
        }
        ContentCountry contentCountry = this.forcedContentCountry;
        if (contentCountry != null) {
            kioskExtractorCreateNewKiosk.forceContentCountry(contentCountry);
        }
        return kioskExtractorCreateNewKiosk;
    }

    public Set<String> getAvailableKiosks() {
        return this.kioskList.keySet();
    }

    public KioskExtractor getExtractorByUrl(String str, Page page) throws IOException, ExtractionException {
        return getExtractorByUrl(str, page, NewPipe.getPreferredLocalization());
    }

    public KioskExtractor getExtractorByUrl(String str, Page page, Localization localization) throws IOException, ExtractionException {
        Iterator<Map.Entry<String, KioskEntry>> it = this.kioskList.entrySet().iterator();
        while (it.hasNext()) {
            KioskEntry value = it.next().getValue();
            if (value.handlerFactory.acceptUrl(str)) {
                return getExtractorById(value.handlerFactory.getId(str), page, localization);
            }
        }
        throw new ExtractionException("Could not find a kiosk that fits to the url: " + str);
    }

    public ListLinkHandlerFactory getListLinkHandlerFactoryByType(String str) {
        return this.kioskList.get(str).handlerFactory;
    }

    public void forceLocalization(@Nullable Localization localization) {
        this.forcedLocalization = localization;
    }

    public void forceContentCountry(@Nullable ContentCountry contentCountry) {
        this.forcedContentCountry = contentCountry;
    }
}
