package org.schabi.newpipe.extractor.services.soundcloud.extractors;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.comments.CommentsExtractor;
import org.schabi.newpipe.extractor.comments.CommentsInfoItem;
import org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor;
import org.schabi.newpipe.extractor.comments.CommentsInfoItemsCollector;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.exceptions.ReCaptchaException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class SoundcloudCommentsExtractor extends CommentsExtractor {
    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) {
    }

    public SoundcloudCommentsExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler) {
        super(streamingService, listLinkHandler);
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    @Nonnull
    public ListExtractor.InfoItemsPage<CommentsInfoItem> getInitialPage() throws IOException, ExtractionException {
        return getPage(getUrl());
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    public ListExtractor.InfoItemsPage<CommentsInfoItem> getPage(Page page) throws IOException, ExtractionException {
        if (page == null || Utils.isNullOrEmpty(page.getUrl())) {
            return ListExtractor.InfoItemsPage.emptyPage();
        }
        return getPage(page.getUrl());
    }

    @Nonnull
    private ListExtractor.InfoItemsPage<CommentsInfoItem> getPage(@Nonnull String str) throws ReCaptchaException, IOException, ParsingException {
        try {
            JsonObject jsonObjectFrom = JsonParser.object().from(NewPipe.getDownloader().get(str).responseBody());
            CommentsInfoItemsCollector commentsInfoItemsCollector = new CommentsInfoItemsCollector(getServiceId());
            collectStreamsFrom(commentsInfoItemsCollector, jsonObjectFrom.getArray("collection"));
            String string = jsonObjectFrom.getString("next_href");
            return new ListExtractor.InfoItemsPage<>(commentsInfoItemsCollector, Utils.isNullOrEmpty(string) ? null : new Page(string));
        } catch (JsonParserException e) {
            throw new ParsingException("Could not parse json", e);
        }
    }

    private void collectStreamsFrom(CommentsInfoItemsCollector commentsInfoItemsCollector, JsonArray jsonArray) throws ParsingException {
        String url = getUrl();
        Iterator<Object> it = jsonArray.iterator();
        while (it.hasNext()) {
            commentsInfoItemsCollector.commit((CommentsInfoItemExtractor) new SoundcloudCommentsInfoItemExtractor((JsonObject) it.next(), url));
        }
    }
}
