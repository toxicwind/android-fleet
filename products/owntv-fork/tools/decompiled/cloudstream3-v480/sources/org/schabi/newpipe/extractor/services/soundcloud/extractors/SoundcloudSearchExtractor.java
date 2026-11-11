package org.schabi.newpipe.extractor.services.soundcloud.extractors;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import j$.util.function.IntUnaryOperator$CC;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.function.IntUnaryOperator;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.InfoItemExtractor;
import org.schabi.newpipe.extractor.InfoItemsCollector;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.MetaInfo;
import org.schabi.newpipe.extractor.MultiInfoItemsCollector;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.SearchQueryHandler;
import org.schabi.newpipe.extractor.search.SearchExtractor;
import org.schabi.newpipe.extractor.utils.Parser;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class SoundcloudSearchExtractor extends SearchExtractor {
    private static final String COLLECTION = "collection";
    private static final String TOTAL_RESULTS = "total_results";
    private JsonObject initialSearchObject;

    static /* synthetic */ int lambda$getInitialPage$0(int i) {
        return 10;
    }

    static /* synthetic */ int lambda$getPage$1(int i) {
        return i + 10;
    }

    @Override // org.schabi.newpipe.extractor.search.SearchExtractor
    public boolean isCorrectedSearch() {
        return false;
    }

    public SoundcloudSearchExtractor(StreamingService streamingService, SearchQueryHandler searchQueryHandler) {
        super(streamingService, searchQueryHandler);
    }

    @Override // org.schabi.newpipe.extractor.search.SearchExtractor
    @Nonnull
    public String getSearchSuggestion() {
        return "";
    }

    @Override // org.schabi.newpipe.extractor.search.SearchExtractor
    @Nonnull
    public List<MetaInfo> getMetaInfo() {
        return Collections.EMPTY_LIST;
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    @Nonnull
    public ListExtractor.InfoItemsPage<InfoItem> getInitialPage() throws IOException, ExtractionException {
        if (this.initialSearchObject.getInt(TOTAL_RESULTS) > 10) {
            return new ListExtractor.InfoItemsPage<>(collectItems(this.initialSearchObject.getArray(COLLECTION)), getNextPageFromCurrentUrl(getUrl(), new IntUnaryOperator() { // from class: org.schabi.newpipe.extractor.services.soundcloud.extractors.SoundcloudSearchExtractor$$ExternalSyntheticLambda1
                public /* synthetic */ IntUnaryOperator andThen(IntUnaryOperator intUnaryOperator) {
                    return IntUnaryOperator$CC.$default$andThen(this, intUnaryOperator);
                }

                @Override // java.util.function.IntUnaryOperator
                public final int applyAsInt(int i) {
                    return SoundcloudSearchExtractor.lambda$getInitialPage$0(i);
                }

                public /* synthetic */ IntUnaryOperator compose(IntUnaryOperator intUnaryOperator) {
                    return IntUnaryOperator$CC.$default$compose(this, intUnaryOperator);
                }
            }));
        }
        return new ListExtractor.InfoItemsPage<>(collectItems(this.initialSearchObject.getArray(COLLECTION)), null);
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    public ListExtractor.InfoItemsPage<InfoItem> getPage(Page page) throws IOException, ExtractionException {
        if (page == null || Utils.isNullOrEmpty(page.getUrl())) {
            throw new IllegalArgumentException("Page doesn't contain an URL");
        }
        try {
            JsonObject jsonObjectFrom = JsonParser.object().from(getDownloader().get(page.getUrl(), getExtractorLocalization()).responseBody());
            JsonArray array = jsonObjectFrom.getArray(COLLECTION);
            if (getOffsetFromUrl(page.getUrl()) + 10 < jsonObjectFrom.getInt(TOTAL_RESULTS)) {
                return new ListExtractor.InfoItemsPage<>(collectItems(array), getNextPageFromCurrentUrl(page.getUrl(), new IntUnaryOperator() { // from class: org.schabi.newpipe.extractor.services.soundcloud.extractors.SoundcloudSearchExtractor$$ExternalSyntheticLambda0
                    public /* synthetic */ IntUnaryOperator andThen(IntUnaryOperator intUnaryOperator) {
                        return IntUnaryOperator$CC.$default$andThen(this, intUnaryOperator);
                    }

                    @Override // java.util.function.IntUnaryOperator
                    public final int applyAsInt(int i) {
                        return SoundcloudSearchExtractor.lambda$getPage$1(i);
                    }

                    public /* synthetic */ IntUnaryOperator compose(IntUnaryOperator intUnaryOperator) {
                        return IntUnaryOperator$CC.$default$compose(this, intUnaryOperator);
                    }
                }));
            }
            return new ListExtractor.InfoItemsPage<>(collectItems(array), null);
        } catch (JsonParserException e) {
            throw new ParsingException("Could not parse json response", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        try {
            JsonObject jsonObjectFrom = JsonParser.object().from(getDownloader().get(getUrl(), getExtractorLocalization()).responseBody());
            this.initialSearchObject = jsonObjectFrom;
            if (jsonObjectFrom.getArray(COLLECTION).isEmpty()) {
                throw new SearchExtractor.NothingFoundException("Nothing found");
            }
        } catch (JsonParserException e) {
            throw new ParsingException("Could not parse json response", e);
        }
    }

    private InfoItemsCollector<InfoItem, InfoItemExtractor> collectItems(JsonArray jsonArray) {
        MultiInfoItemsCollector multiInfoItemsCollector = new MultiInfoItemsCollector(getServiceId());
        for (Object obj : jsonArray) {
            if (obj instanceof JsonObject) {
                JsonObject jsonObject = (JsonObject) obj;
                String string = jsonObject.getString("kind", "");
                string.hashCode();
                switch (string) {
                    case "user":
                        multiInfoItemsCollector.commit(new SoundcloudChannelInfoItemExtractor(jsonObject));
                        break;
                    case "track":
                        multiInfoItemsCollector.commit(new SoundcloudStreamInfoItemExtractor(jsonObject));
                        break;
                    case "playlist":
                        multiInfoItemsCollector.commit(new SoundcloudPlaylistInfoItemExtractor(jsonObject));
                        break;
                }
            }
        }
        return multiInfoItemsCollector;
    }

    private Page getNextPageFromCurrentUrl(String str, IntUnaryOperator intUnaryOperator) throws ParsingException {
        int offsetFromUrl = getOffsetFromUrl(str);
        return new Page(str.replace("&offset=" + offsetFromUrl, "&offset=" + intUnaryOperator.applyAsInt(offsetFromUrl)));
    }

    private int getOffsetFromUrl(String str) throws ParsingException {
        try {
            return Integer.parseInt(Parser.compatParseMap(new URL(str).getQuery()).get(TypedValues.CycleType.S_WAVE_OFFSET));
        } catch (MalformedURLException e) {
            throw new ParsingException("Could not get offset from page URL", e);
        }
    }
}
