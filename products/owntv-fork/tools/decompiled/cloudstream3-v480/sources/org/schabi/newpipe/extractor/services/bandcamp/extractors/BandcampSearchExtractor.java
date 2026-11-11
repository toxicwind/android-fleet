package org.schabi.newpipe.extractor.services.bandcamp.extractors;

import androidx.media3.extractor.text.ttml.TtmlNode;
import j$.util.Collection;
import j$.util.function.Function$CC;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import javax.annotation.Nonnull;
import kotlin.UByte$$ExternalSyntheticBackport0;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Collector$$ExternalSyntheticLambda1;
import org.jsoup.select.Elements;
import org.jsoup.select.Elements$$ExternalSyntheticLambda1;
import org.schabi.newpipe.extractor.InfoItem;
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
import org.schabi.newpipe.extractor.services.bandcamp.extractors.streaminfoitem.BandcampSearchStreamInfoItemExtractor;

/* JADX INFO: loaded from: classes8.dex */
public class BandcampSearchExtractor extends SearchExtractor {
    @Override // org.schabi.newpipe.extractor.search.SearchExtractor
    public boolean isCorrectedSearch() {
        return false;
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
    }

    public BandcampSearchExtractor(StreamingService streamingService, SearchQueryHandler searchQueryHandler) {
        super(streamingService, searchQueryHandler);
    }

    @Override // org.schabi.newpipe.extractor.search.SearchExtractor
    @Nonnull
    public String getSearchSuggestion() {
        return "";
    }

    @Override // org.schabi.newpipe.extractor.search.SearchExtractor
    @Nonnull
    public List<MetaInfo> getMetaInfo() throws ParsingException {
        return Collections.EMPTY_LIST;
    }

    /* JADX WARN: Code duplicated, block: B:38:0x0109  */
    /* JADX WARN: Instruction removed from duplicated block: B:38:0x0109, please report this as an issue */
    @Override // org.schabi.newpipe.extractor.ListExtractor
    public ListExtractor.InfoItemsPage<InfoItem> getPage(Page page) throws IOException, ExtractionException {
        MultiInfoItemsCollector multiInfoItemsCollector = new MultiInfoItemsCollector(getServiceId());
        Document document = Jsoup.parse(getDownloader().get(page.getUrl(), UByte$$ExternalSyntheticBackport0.m(new Map.Entry[]{new AbstractMap.SimpleEntry("Cookie", UByte$$ExternalSyntheticBackport0.m(new Object[]{"identity"}))})).responseBody());
        Iterator it = document.getElementsByClass("searchresult").iterator();
        while (true) {
            if (it.hasNext()) {
                Element element = (Element) it.next();
                String str = (String) Collection.EL.stream(element.getElementsByClass("result-info")).flatMap(new Function() { // from class: org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampSearchExtractor$$ExternalSyntheticLambda2
                    @Override // java.util.function.Function
                    public /* synthetic */ Function andThen(Function function) {
                        return Function$CC.$default$andThen(this, function);
                    }

                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return Collection.EL.stream(((Element) obj).getElementsByClass("itemtype"));
                    }

                    public /* synthetic */ Function compose(Function function) {
                        return Function$CC.$default$compose(this, function);
                    }
                }).map(new Elements$$ExternalSyntheticLambda1()).findFirst().orElse("");
                str.hashCode();
                switch (str) {
                    case "ALBUM":
                        multiInfoItemsCollector.commit(new BandcampPlaylistInfoItemExtractor(element));
                        break;
                    case "TRACK":
                        multiInfoItemsCollector.commit(new BandcampSearchStreamInfoItemExtractor(element, null));
                        break;
                    case "ARTIST":
                        multiInfoItemsCollector.commit(new BandcampChannelInfoItemExtractor(element));
                        break;
                }
            } else {
                Elements elementsByClass = document.getElementsByClass("pagelist");
                if (elementsByClass.isEmpty()) {
                    return new ListExtractor.InfoItemsPage<>(multiInfoItemsCollector, null);
                }
                Elements elements = (Elements) Collection.EL.stream(elementsByClass).map(new Function() { // from class: org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampSearchExtractor$$ExternalSyntheticLambda3
                    @Override // java.util.function.Function
                    public /* synthetic */ Function andThen(Function function) {
                        return Function$CC.$default$andThen(this, function);
                    }

                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((Element) obj).getElementsByTag("li");
                    }

                    public /* synthetic */ Function compose(Function function) {
                        return Function$CC.$default$compose(this, function);
                    }
                }).findFirst().orElseGet(new Collector$$ExternalSyntheticLambda1());
                for (int i = 0; i < elements.size(); i++) {
                    if (!((Element) elements.get(i)).getElementsByTag(TtmlNode.TAG_SPAN).isEmpty()) {
                        return new ListExtractor.InfoItemsPage<>(multiInfoItemsCollector, new Page(r3 < elements.size() ? page.getUrl().substring(0, page.getUrl().length() - 1) + (r3 + 1) : null));
                    }
                }
                if (r3 < elements.size()) {
                }
                return new ListExtractor.InfoItemsPage<>(multiInfoItemsCollector, new Page(r3 < elements.size() ? page.getUrl().substring(0, page.getUrl().length() - 1) + (r3 + 1) : null));
            }
        }
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    @Nonnull
    public ListExtractor.InfoItemsPage<InfoItem> getInitialPage() throws IOException, ExtractionException {
        return getPage(new Page(getUrl()));
    }
}
