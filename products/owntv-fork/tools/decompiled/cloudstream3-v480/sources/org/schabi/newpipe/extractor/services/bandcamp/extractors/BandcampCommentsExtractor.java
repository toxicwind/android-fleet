package org.schabi.newpipe.extractor.services.bandcamp.extractors;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonStringWriter;
import com.grack.nanojson.JsonWriter;
import j$.util.function.BiFunction$CC;
import j$.util.function.Function$CC;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import kotlin.UByte$$ExternalSyntheticBackport0;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.schabi.newpipe.extractor.ListExtractor;
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
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;
import org.schabi.newpipe.extractor.utils.JsonUtils;

/* JADX INFO: loaded from: classes8.dex */
public class BandcampCommentsExtractor extends CommentsExtractor {
    private static final String REVIEWS_API_URL = "https://bandcamp.com/api/tralbumcollectors/2/reviews";
    private Document document;

    static /* synthetic */ String lambda$getNextPageToken$1(String str, String str2) {
        return str2;
    }

    public BandcampCommentsExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler) {
        super(streamingService, listLinkHandler);
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        this.document = Jsoup.parse(downloader.get(getLinkHandler().getUrl()).responseBody());
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    @Nonnull
    public ListExtractor.InfoItemsPage<CommentsInfoItem> getInitialPage() throws IOException, ExtractionException {
        CommentsInfoItemsCollector commentsInfoItemsCollector = new CommentsInfoItemsCollector(getServiceId());
        JsonObject jsonObject = JsonUtils.toJsonObject(this.document.getElementById("collectors-data").attr("data-blob"));
        JsonArray array = jsonObject.getArray("reviews");
        Iterator<Object> it = array.iterator();
        while (it.hasNext()) {
            commentsInfoItemsCollector.commit((CommentsInfoItemExtractor) new BandcampCommentsInfoItemExtractor((JsonObject) it.next(), getUrl()));
        }
        if (!jsonObject.getBoolean("more_reviews_available")) {
            return new ListExtractor.InfoItemsPage<>(commentsInfoItemsCollector, null);
        }
        return new ListExtractor.InfoItemsPage<>(commentsInfoItemsCollector, new Page((List<String>) UByte$$ExternalSyntheticBackport0.m(new Object[]{getTrackId(), getNextPageToken(array)})));
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    public ListExtractor.InfoItemsPage<CommentsInfoItem> getPage(Page page) throws IOException, ExtractionException {
        CommentsInfoItemsCollector commentsInfoItemsCollector = new CommentsInfoItemsCollector(getServiceId());
        List<String> ids = page.getIds();
        String str = ids.get(0);
        JsonObject jsonObjectFetchReviewsData = fetchReviewsData(str, ids.get(1));
        JsonArray array = jsonObjectFetchReviewsData.getArray("results");
        Iterator<Object> it = array.iterator();
        while (it.hasNext()) {
            commentsInfoItemsCollector.commit((CommentsInfoItemExtractor) new BandcampCommentsInfoItemExtractor((JsonObject) it.next(), getUrl()));
        }
        if (!jsonObjectFetchReviewsData.getBoolean("more_available")) {
            return new ListExtractor.InfoItemsPage<>(commentsInfoItemsCollector, null);
        }
        return new ListExtractor.InfoItemsPage<>(commentsInfoItemsCollector, new Page((List<String>) UByte$$ExternalSyntheticBackport0.m(new Object[]{str, getNextPageToken(array)})));
    }

    private JsonObject fetchReviewsData(String str, String str2) throws ParsingException {
        try {
            return JsonUtils.toJsonObject(getDownloader().postWithContentTypeJson(REVIEWS_API_URL, Collections.EMPTY_MAP, ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) ((JsonStringWriter) JsonWriter.string().object()).value("tralbum_type", "t")).value("tralbum_id", str)).value("token", str2)).value(PeertubeParsingHelper.COUNT_KEY, 7)).array("exclude_fan_ids")).end()).end()).done().getBytes(StandardCharsets.UTF_8)).responseBody());
        } catch (IOException | ReCaptchaException e) {
            throw new ParsingException("Could not fetch reviews", e);
        }
    }

    private String getNextPageToken(JsonArray jsonArray) throws ParsingException {
        return (String) jsonArray.streamAsJsonObjects().map(new Function() { // from class: org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampCommentsExtractor$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getString("token");
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).reduce(new BinaryOperator() { // from class: org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampCommentsExtractor$$ExternalSyntheticLambda2
            public /* synthetic */ BiFunction andThen(Function function) {
                return BiFunction$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return BandcampCommentsExtractor.lambda$getNextPageToken$1((String) obj, (String) obj2);
            }
        }).orElseThrow(new Supplier() { // from class: org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampCommentsExtractor$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return BandcampCommentsExtractor.lambda$getNextPageToken$2();
            }
        });
    }

    static /* synthetic */ ParsingException lambda$getNextPageToken$2() {
        return new ParsingException("Could not get token");
    }

    private String getTrackId() throws ParsingException {
        return Long.toString(JsonUtils.toJsonObject(this.document.selectFirst("meta[name=bc-page-properties]").attr("content")).getLong("item_id"));
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsExtractor
    public boolean isCommentsDisabled() throws ExtractionException {
        return BandcampExtractorHelper.isRadioUrl(getUrl());
    }
}
