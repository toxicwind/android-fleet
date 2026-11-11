package org.schabi.newpipe.extractor.services.media_ccc.extractors;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import j$.util.Comparator;
import j$.util.function.Consumer$CC;
import j$.util.function.Function$CC;
import j$.util.function.Predicate$CC;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.kiosk.KioskExtractor;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.localization.DateWrapper;
import org.schabi.newpipe.extractor.services.media_ccc.linkHandler.MediaCCCSearchQueryHandlerFactory;
import org.schabi.newpipe.extractor.stream.StreamInfoItem;
import org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor;
import org.schabi.newpipe.extractor.stream.StreamInfoItemsCollector;

/* JADX INFO: loaded from: classes8.dex */
public class MediaCCCRecentKiosk extends KioskExtractor<StreamInfoItem> {
    public static final String KIOSK_ID = "recent";
    private JsonObject doc;

    public MediaCCCRecentKiosk(StreamingService streamingService, ListLinkHandler listLinkHandler, String str) {
        super(streamingService, listLinkHandler, str);
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        try {
            this.doc = JsonParser.object().from(downloader.get("https://api.media.ccc.de/public/events/recent", getExtractorLocalization()).responseBody());
        } catch (JsonParserException e) {
            throw new ExtractionException("Could not parse json.", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    @Nonnull
    public ListExtractor.InfoItemsPage<StreamInfoItem> getInitialPage() throws IOException, ExtractionException {
        JsonArray array = this.doc.getArray(MediaCCCSearchQueryHandlerFactory.EVENTS);
        final StreamInfoItemsCollector streamInfoItemsCollector = new StreamInfoItemsCollector(getServiceId(), Comparator.EL.reversed(Comparator.CC.comparing(new Function() { // from class: org.schabi.newpipe.extractor.services.media_ccc.extractors.MediaCCCRecentKiosk$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((StreamInfoItem) obj).getUploadDate();
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }, Comparator.CC.nullsLast(Comparator.CC.comparing(new Function() { // from class: org.schabi.newpipe.extractor.services.media_ccc.extractors.MediaCCCRecentKiosk$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((DateWrapper) obj).getInstant();
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        })))));
        array.streamAsJsonObjects().map(new Function() { // from class: org.schabi.newpipe.extractor.services.media_ccc.extractors.MediaCCCRecentKiosk$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new MediaCCCRecentKioskExtractor((JsonObject) obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.media_ccc.extractors.MediaCCCRecentKiosk$$ExternalSyntheticLambda3
            public /* synthetic */ Predicate and(Predicate predicate) {
                return Predicate$CC.$default$and(this, predicate);
            }

            public /* synthetic */ Predicate negate() {
                return Predicate$CC.$default$negate(this);
            }

            public /* synthetic */ Predicate or(Predicate predicate) {
                return Predicate$CC.$default$or(this, predicate);
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return MediaCCCRecentKiosk.lambda$getInitialPage$0((MediaCCCRecentKioskExtractor) obj);
            }
        }).forEach(new Consumer() { // from class: org.schabi.newpipe.extractor.services.media_ccc.extractors.MediaCCCRecentKiosk$$ExternalSyntheticLambda4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                streamInfoItemsCollector.commit((StreamInfoItemExtractor) obj);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        });
        return new ListExtractor.InfoItemsPage<>(streamInfoItemsCollector, null);
    }

    static /* synthetic */ boolean lambda$getInitialPage$0(MediaCCCRecentKioskExtractor mediaCCCRecentKioskExtractor) {
        return mediaCCCRecentKioskExtractor.getDuration() > 0;
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    public ListExtractor.InfoItemsPage<StreamInfoItem> getPage(Page page) throws IOException, ExtractionException {
        return ListExtractor.InfoItemsPage.emptyPage();
    }

    @Override // org.schabi.newpipe.extractor.kiosk.KioskExtractor, org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getName() throws ParsingException {
        return KIOSK_ID;
    }
}
