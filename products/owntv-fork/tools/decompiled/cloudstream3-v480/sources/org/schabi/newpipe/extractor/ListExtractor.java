package org.schabi.newpipe.extractor;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;

/* JADX INFO: loaded from: classes8.dex */
public abstract class ListExtractor<R extends InfoItem> extends Extractor {
    public static final long ITEM_COUNT_INFINITE = -2;
    public static final long ITEM_COUNT_MORE_THAN_100 = -3;
    public static final long ITEM_COUNT_UNKNOWN = -1;

    @Nonnull
    public abstract InfoItemsPage<R> getInitialPage() throws IOException, ExtractionException;

    public abstract InfoItemsPage<R> getPage(Page page) throws IOException, ExtractionException;

    public ListExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler) {
        super(streamingService, listLinkHandler);
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public ListLinkHandler getLinkHandler() {
        return (ListLinkHandler) super.getLinkHandler();
    }

    public static class InfoItemsPage<T extends InfoItem> {
        private static final InfoItemsPage<InfoItem> EMPTY = new InfoItemsPage<>(Collections.EMPTY_LIST, null, Collections.EMPTY_LIST);
        private final List<Throwable> errors;
        private final List<T> itemsList;

        @Nullable
        private final Page nextPage;

        public static <T extends InfoItem> InfoItemsPage<T> emptyPage() {
            return (InfoItemsPage<T>) EMPTY;
        }

        public InfoItemsPage(InfoItemsCollector<T, ?> infoItemsCollector, @Nullable Page page) {
            this(infoItemsCollector.getItems(), page, infoItemsCollector.getErrors());
        }

        public InfoItemsPage(List<T> list, @Nullable Page page, List<Throwable> list2) {
            this.itemsList = list;
            this.nextPage = page;
            this.errors = list2;
        }

        public boolean hasNextPage() {
            return Page.isValid(this.nextPage);
        }

        public List<T> getItems() {
            return this.itemsList;
        }

        @Nullable
        public Page getNextPage() {
            return this.nextPage;
        }

        public List<Throwable> getErrors() {
            return this.errors;
        }
    }
}
