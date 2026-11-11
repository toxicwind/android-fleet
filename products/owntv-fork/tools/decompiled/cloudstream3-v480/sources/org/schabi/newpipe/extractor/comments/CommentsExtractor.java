package org.schabi.newpipe.extractor.comments;

import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;

/* JADX INFO: loaded from: classes8.dex */
public abstract class CommentsExtractor extends ListExtractor<CommentsInfoItem> {
    public int getCommentsCount() throws ExtractionException {
        return -1;
    }

    public boolean isCommentsDisabled() throws ExtractionException {
        return false;
    }

    public CommentsExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler) {
        super(streamingService, listLinkHandler);
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getName() throws ParsingException {
        return "Comments";
    }
}
