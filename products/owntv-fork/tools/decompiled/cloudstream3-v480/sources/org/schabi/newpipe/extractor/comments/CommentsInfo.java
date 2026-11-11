package org.schabi.newpipe.extractor.comments;

import java.io.IOException;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.ListInfo;
import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.utils.ExtractorHelper;

/* JADX INFO: loaded from: classes8.dex */
public final class CommentsInfo extends ListInfo<CommentsInfoItem> {
    private int commentsCount;
    private boolean commentsDisabled;
    private transient CommentsExtractor commentsExtractor;

    private CommentsInfo(int i, ListLinkHandler listLinkHandler, String str) {
        super(i, listLinkHandler, str);
        this.commentsDisabled = false;
    }

    public static CommentsInfo getInfo(String str) throws IOException, ExtractionException {
        return getInfo(NewPipe.getServiceByUrl(str), str);
    }

    public static CommentsInfo getInfo(StreamingService streamingService, String str) throws IOException, ExtractionException {
        return getInfo(streamingService.getCommentsExtractor(str));
    }

    public static CommentsInfo getInfo(CommentsExtractor commentsExtractor) throws IOException, ExtractionException {
        if (commentsExtractor == null) {
            return null;
        }
        commentsExtractor.fetchPage();
        CommentsInfo commentsInfo = new CommentsInfo(commentsExtractor.getServiceId(), commentsExtractor.getLinkHandler(), commentsExtractor.getName());
        commentsInfo.setCommentsExtractor(commentsExtractor);
        ListExtractor.InfoItemsPage itemsPageOrLogError = ExtractorHelper.getItemsPageOrLogError(commentsInfo, commentsExtractor);
        commentsInfo.setCommentsDisabled(commentsExtractor.isCommentsDisabled());
        commentsInfo.setRelatedItems(itemsPageOrLogError.getItems());
        try {
            commentsInfo.setCommentsCount(commentsExtractor.getCommentsCount());
        } catch (Exception e) {
            commentsInfo.addError(e);
        }
        commentsInfo.setNextPage(itemsPageOrLogError.getNextPage());
        return commentsInfo;
    }

    public static ListExtractor.InfoItemsPage<CommentsInfoItem> getMoreItems(CommentsInfo commentsInfo, Page page) throws IOException, ExtractionException {
        return getMoreItems(NewPipe.getService(commentsInfo.getServiceId()), commentsInfo.getUrl(), page);
    }

    public static ListExtractor.InfoItemsPage<CommentsInfoItem> getMoreItems(StreamingService streamingService, CommentsInfo commentsInfo, Page page) throws IOException, ExtractionException {
        return getMoreItems(streamingService, commentsInfo.getUrl(), page);
    }

    public static ListExtractor.InfoItemsPage<CommentsInfoItem> getMoreItems(StreamingService streamingService, String str, Page page) throws IOException, ExtractionException {
        return streamingService.getCommentsExtractor(str).getPage(page);
    }

    public CommentsExtractor getCommentsExtractor() {
        return this.commentsExtractor;
    }

    public void setCommentsExtractor(CommentsExtractor commentsExtractor) {
        this.commentsExtractor = commentsExtractor;
    }

    public boolean isCommentsDisabled() {
        return this.commentsDisabled;
    }

    public void setCommentsDisabled(boolean z) {
        this.commentsDisabled = z;
    }

    public int getCommentsCount() {
        return this.commentsCount;
    }

    public void setCommentsCount(int i) {
        this.commentsCount = i;
    }
}
