package org.schabi.newpipe.extractor.kiosk;

import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;

/* JADX INFO: loaded from: classes8.dex */
public abstract class KioskExtractor<T extends InfoItem> extends ListExtractor<T> {
    private final String id;

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public abstract String getName() throws ParsingException;

    public KioskExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler, String str) {
        super(streamingService, listLinkHandler);
        this.id = str;
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getId() {
        return this.id;
    }
}
