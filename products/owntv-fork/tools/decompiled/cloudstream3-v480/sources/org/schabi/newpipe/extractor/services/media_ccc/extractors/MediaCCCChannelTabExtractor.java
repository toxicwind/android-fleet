package org.schabi.newpipe.extractor.services.media_ccc.extractors;

import com.grack.nanojson.JsonObject;
import j$.util.Objects;
import j$.util.function.Consumer$CC;
import java.io.IOException;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.MultiInfoItemsCollector;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.channel.tabs.ChannelTabExtractor;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.services.media_ccc.extractors.infoItems.MediaCCCStreamInfoItemExtractor;
import org.schabi.newpipe.extractor.services.media_ccc.linkHandler.MediaCCCSearchQueryHandlerFactory;

/* JADX INFO: loaded from: classes8.dex */
public class MediaCCCChannelTabExtractor extends ChannelTabExtractor {

    @Nullable
    private JsonObject conferenceData;

    public MediaCCCChannelTabExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler, @Nullable JsonObject jsonObject) {
        super(streamingService, listLinkHandler);
        this.conferenceData = jsonObject;
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        if (this.conferenceData == null) {
            this.conferenceData = MediaCCCConferenceExtractor.fetchConferenceData(downloader, getId());
        }
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    @Nonnull
    public ListExtractor.InfoItemsPage<InfoItem> getInitialPage() {
        final MultiInfoItemsCollector multiInfoItemsCollector = new MultiInfoItemsCollector(getServiceId());
        ((JsonObject) Objects.requireNonNull(this.conferenceData)).getArray(MediaCCCSearchQueryHandlerFactory.EVENTS).streamAsJsonObjects().forEach(new Consumer() { // from class: org.schabi.newpipe.extractor.services.media_ccc.extractors.MediaCCCChannelTabExtractor$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                multiInfoItemsCollector.commit(new MediaCCCStreamInfoItemExtractor((JsonObject) obj));
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        });
        return new ListExtractor.InfoItemsPage<>(multiInfoItemsCollector, null);
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    public ListExtractor.InfoItemsPage<InfoItem> getPage(Page page) {
        return ListExtractor.InfoItemsPage.emptyPage();
    }
}
