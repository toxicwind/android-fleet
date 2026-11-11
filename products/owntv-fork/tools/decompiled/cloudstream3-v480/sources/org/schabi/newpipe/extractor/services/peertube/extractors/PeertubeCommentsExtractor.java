package org.schabi.newpipe.extractor.services.peertube.extractors;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.comments.CommentsExtractor;
import org.schabi.newpipe.extractor.comments.CommentsInfoItem;
import org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor;
import org.schabi.newpipe.extractor.comments.CommentsInfoItemsCollector;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.downloader.Response;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class PeertubeCommentsExtractor extends CommentsExtractor {
    static final String CHILDREN = "children";
    private static final String IS_DELETED = "isDeleted";
    private static final String TOTAL = "total";
    private Boolean isReply;

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) {
    }

    public PeertubeCommentsExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler) {
        super(streamingService, listLinkHandler);
        this.isReply = null;
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    @Nonnull
    public ListExtractor.InfoItemsPage<CommentsInfoItem> getInitialPage() throws IOException, ExtractionException {
        if (isReply()) {
            return getPage(new Page(getOriginalUrl()));
        }
        return getPage(new Page(getUrl() + "?start=0&count=12"));
    }

    private boolean isReply() throws ParsingException {
        if (this.isReply == null) {
            if (getOriginalUrl().contains("/videos/watch/")) {
                this.isReply = false;
            } else {
                this.isReply = Boolean.valueOf(getOriginalUrl().contains("/comment-threads/"));
            }
        }
        return this.isReply.booleanValue();
    }

    private void collectCommentsFrom(@Nonnull CommentsInfoItemsCollector commentsInfoItemsCollector, @Nonnull JsonObject jsonObject) throws ParsingException {
        for (Object obj : jsonObject.getArray("data")) {
            if (obj instanceof JsonObject) {
                JsonObject jsonObject2 = (JsonObject) obj;
                if (!jsonObject2.getBoolean(IS_DELETED)) {
                    commentsInfoItemsCollector.commit((CommentsInfoItemExtractor) new PeertubeCommentsInfoItemExtractor(jsonObject2, null, getUrl(), getBaseUrl(), isReply()));
                }
            }
        }
    }

    private void collectRepliesFrom(@Nonnull CommentsInfoItemsCollector commentsInfoItemsCollector, @Nonnull JsonObject jsonObject) throws ParsingException {
        for (Object obj : jsonObject.getArray(CHILDREN)) {
            if (obj instanceof JsonObject) {
                JsonObject jsonObject2 = (JsonObject) obj;
                JsonObject object = jsonObject2.getObject("comment");
                JsonArray array = jsonObject2.getArray(CHILDREN);
                if (!object.getBoolean(IS_DELETED)) {
                    commentsInfoItemsCollector.commit((CommentsInfoItemExtractor) new PeertubeCommentsInfoItemExtractor(object, array, getUrl(), getBaseUrl(), isReply()));
                }
            }
        }
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    public ListExtractor.InfoItemsPage<CommentsInfoItem> getPage(Page page) throws IOException, ExtractionException {
        long size;
        JsonObject jsonObjectFrom;
        if (page == null || Utils.isNullOrEmpty(page.getUrl())) {
            throw new IllegalArgumentException("Page doesn't contain an URL");
        }
        CommentsInfoItemsCollector commentsInfoItemsCollector = new CommentsInfoItemsCollector(getServiceId());
        if (page.getBody() == null) {
            Response response = getDownloader().get(page.getUrl());
            if (response == null || Utils.isBlank(response.responseBody())) {
                jsonObjectFrom = null;
            } else {
                try {
                    jsonObjectFrom = JsonParser.object().from(response.responseBody());
                } catch (Exception e) {
                    throw new ParsingException("Could not parse json data for comments info", e);
                }
            }
            if (jsonObjectFrom != null) {
                PeertubeParsingHelper.validate(jsonObjectFrom);
                if (isReply() || jsonObjectFrom.has(CHILDREN)) {
                    size = jsonObjectFrom.getArray(CHILDREN).size();
                    collectRepliesFrom(commentsInfoItemsCollector, jsonObjectFrom);
                } else {
                    size = jsonObjectFrom.getLong(TOTAL);
                    collectCommentsFrom(commentsInfoItemsCollector, jsonObjectFrom);
                }
            } else {
                throw new ExtractionException("Unable to get PeerTube kiosk info");
            }
        } else {
            try {
                JsonObject jsonObjectFrom2 = JsonParser.object().from(new String(page.getBody(), StandardCharsets.UTF_8));
                this.isReply = true;
                size = jsonObjectFrom2.getArray(CHILDREN).size();
                collectRepliesFrom(commentsInfoItemsCollector, jsonObjectFrom2);
            } catch (JsonParserException e2) {
                throw new ParsingException("Could not parse json data for nested comments  info", e2);
            }
        }
        return new ListExtractor.InfoItemsPage<>(commentsInfoItemsCollector, PeertubeParsingHelper.getNextPage(page.getUrl(), size));
    }
}
