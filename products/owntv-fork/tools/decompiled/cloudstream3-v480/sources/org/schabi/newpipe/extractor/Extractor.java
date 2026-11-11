package org.schabi.newpipe.extractor;

import j$.util.Objects;
import java.io.IOException;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.LinkHandler;
import org.schabi.newpipe.extractor.localization.ContentCountry;
import org.schabi.newpipe.extractor.localization.Localization;
import org.schabi.newpipe.extractor.localization.TimeAgoParser;
import org.schabi.newpipe.extractor.utils.ExtractorLogger;

/* JADX INFO: loaded from: classes8.dex */
public abstract class Extractor {
    private final LinkHandler linkHandler;
    private final StreamingService service;
    private final String TAG = getClass().getSimpleName() + "@" + hashCode();

    @Nullable
    private Localization forcedLocalization = null;

    @Nullable
    private ContentCountry forcedContentCountry = null;
    private boolean pageFetched = false;
    private final Downloader downloader = (Downloader) Objects.requireNonNull(NewPipe.getDownloader(), "downloader is null");

    @Nonnull
    public abstract String getName() throws ParsingException;

    public abstract void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException;

    protected Extractor(StreamingService streamingService, LinkHandler linkHandler) {
        this.service = (StreamingService) Objects.requireNonNull(streamingService, "service is null");
        this.linkHandler = (LinkHandler) Objects.requireNonNull(linkHandler, "LinkHandler is null");
    }

    @Nonnull
    public LinkHandler getLinkHandler() {
        return this.linkHandler;
    }

    public void fetchPage() throws IOException, ExtractionException {
        ExtractorLogger.d(this.TAG, "base fetchPage called");
        if (this.pageFetched) {
            ExtractorLogger.d(this.TAG, "Page already fetched; returning");
        } else {
            onFetchPage(this.downloader);
            this.pageFetched = true;
        }
    }

    protected void assertPageFetched() {
        if (!this.pageFetched) {
            throw new IllegalStateException("Page is not fetched. Make sure you call fetchPage()");
        }
    }

    protected boolean isPageFetched() {
        return this.pageFetched;
    }

    @Nonnull
    public String getId() throws ParsingException {
        return this.linkHandler.getId();
    }

    @Nonnull
    public String getOriginalUrl() throws ParsingException {
        return this.linkHandler.getOriginalUrl();
    }

    @Nonnull
    public String getUrl() throws ParsingException {
        return this.linkHandler.getUrl();
    }

    @Nonnull
    public String getBaseUrl() throws ParsingException {
        return this.linkHandler.getBaseUrl();
    }

    @Nonnull
    public StreamingService getService() {
        return this.service;
    }

    public int getServiceId() {
        return this.service.getServiceId();
    }

    public Downloader getDownloader() {
        return this.downloader;
    }

    public void forceLocalization(Localization localization) {
        this.forcedLocalization = localization;
    }

    public void forceContentCountry(ContentCountry contentCountry) {
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

    @Nonnull
    public TimeAgoParser getTimeAgoParser() {
        return getService().getTimeAgoParser(getExtractorLocalization());
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
