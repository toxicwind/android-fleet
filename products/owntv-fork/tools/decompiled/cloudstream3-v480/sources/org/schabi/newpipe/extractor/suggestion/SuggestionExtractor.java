package org.schabi.newpipe.extractor.suggestion;

import java.io.IOException;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.localization.ContentCountry;
import org.schabi.newpipe.extractor.localization.Localization;

/* JADX INFO: loaded from: classes8.dex */
public abstract class SuggestionExtractor {

    @Nullable
    private ContentCountry forcedContentCountry;

    @Nullable
    private Localization forcedLocalization;
    private final StreamingService service;

    public abstract List<String> suggestionList(String str) throws IOException, ExtractionException;

    public SuggestionExtractor(StreamingService streamingService) {
        this.service = streamingService;
    }

    public int getServiceId() {
        return this.service.getServiceId();
    }

    public StreamingService getService() {
        return this.service;
    }

    public void forceLocalization(@Nullable Localization localization) {
        this.forcedLocalization = localization;
    }

    public void forceContentCountry(@Nullable ContentCountry contentCountry) {
        this.forcedContentCountry = contentCountry;
    }

    @Nonnull
    public Localization getExtractorLocalization() {
        Localization localization = this.forcedLocalization;
        return localization == null ? getService().getLocalization() : localization;
    }

    @Nonnull
    public ContentCountry getExtractorContentCountry() {
        ContentCountry contentCountry = this.forcedContentCountry;
        return contentCountry == null ? getService().getContentCountry() : contentCountry;
    }
}
