package org.schabi.newpipe.extractor.subscription;

import j$.util.DesugarCollections;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;

/* JADX INFO: loaded from: classes8.dex */
public abstract class SubscriptionExtractor {
    protected final StreamingService service;
    private final List<ContentSource> supportedSources;

    public enum ContentSource {
        CHANNEL_URL,
        INPUT_STREAM
    }

    @Nullable
    public abstract String getRelatedUrl();

    public static class InvalidSourceException extends ParsingException {
        public InvalidSourceException() {
            this(null, null);
        }

        public InvalidSourceException(@Nullable String str) {
            this(str, null);
        }

        public InvalidSourceException(Throwable th) {
            this(null, th);
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public InvalidSourceException(@Nullable String str, Throwable th) {
            String str2;
            if (str == null) {
                str2 = "";
            } else {
                str2 = " (" + str + ")";
            }
            super("Not a valid source" + str2, th);
        }
    }

    public SubscriptionExtractor(StreamingService streamingService, List<ContentSource> list) {
        this.service = streamingService;
        this.supportedSources = DesugarCollections.unmodifiableList(list);
    }

    public List<ContentSource> getSupportedSources() {
        return this.supportedSources;
    }

    public List<SubscriptionItem> fromChannelUrl(String str) throws IOException, ExtractionException {
        throw new UnsupportedOperationException("Service " + this.service.getServiceInfo().getName() + " doesn't support extracting from a channel url");
    }

    public List<SubscriptionItem> fromInputStream(@Nonnull InputStream inputStream) throws ExtractionException {
        throw new UnsupportedOperationException("Service " + this.service.getServiceInfo().getName() + " doesn't support extracting from an InputStream");
    }

    public List<SubscriptionItem> fromInputStream(@Nonnull InputStream inputStream, @Nonnull String str) throws ExtractionException {
        throw new UnsupportedOperationException("Service " + this.service.getServiceInfo().getName() + " doesn't support extracting from an InputStream");
    }
}
