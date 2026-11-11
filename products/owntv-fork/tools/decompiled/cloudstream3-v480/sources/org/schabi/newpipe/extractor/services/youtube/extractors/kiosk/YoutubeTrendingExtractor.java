package org.schabi.newpipe.extractor.services.youtube.extractors.kiosk;

import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonWriter;
import j$.util.function.Consumer$CC;
import j$.util.function.Function$CC;
import j$.util.function.Predicate$CC;
import j$.util.stream.Stream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.kiosk.KioskExtractor;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.localization.TimeAgoParser;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;
import org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemExtractor;
import org.schabi.newpipe.extractor.stream.StreamInfoItem;
import org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor;
import org.schabi.newpipe.extractor.stream.StreamInfoItemsCollector;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class YoutubeTrendingExtractor extends KioskExtractor<StreamInfoItem> {
    public static final String KIOSK_ID = "Trending";
    private static final String VIDEOS_TAB_PARAMS = "4gIOGgxtb3N0X3BvcHVsYXI%3D";
    private JsonObject initialData;

    public YoutubeTrendingExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler, String str) {
        super(streamingService, listLinkHandler, str);
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        this.initialData = YoutubeParsingHelper.getJsonPostResponse("browse", JsonWriter.string(YoutubeParsingHelper.prepareDesktopJsonBuilder(getExtractorLocalization(), getExtractorContentCountry()).value("browseId", "FEtrending").value("params", VIDEOS_TAB_PARAMS).done()).getBytes(StandardCharsets.UTF_8), getExtractorLocalization());
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    public ListExtractor.InfoItemsPage<StreamInfoItem> getPage(Page page) {
        return ListExtractor.InfoItemsPage.emptyPage();
    }

    @Override // org.schabi.newpipe.extractor.kiosk.KioskExtractor, org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getName() throws ParsingException {
        String textAtKey;
        JsonObject object = this.initialData.getObject("header");
        if (object.has("feedTabbedHeaderRenderer")) {
            textAtKey = YoutubeParsingHelper.getTextAtKey(object.getObject("feedTabbedHeaderRenderer"), "title");
        } else if (object.has("c4TabbedHeaderRenderer")) {
            textAtKey = YoutubeParsingHelper.getTextAtKey(object.getObject("c4TabbedHeaderRenderer"), "title");
        } else {
            textAtKey = object.has("pageHeaderRenderer") ? YoutubeParsingHelper.getTextAtKey(object.getObject("pageHeaderRenderer"), "pageTitle") : null;
        }
        if (Utils.isNullOrEmpty(textAtKey)) {
            throw new ParsingException("Could not get Trending name");
        }
        return textAtKey;
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    @Nonnull
    public ListExtractor.InfoItemsPage<StreamInfoItem> getInitialPage() throws ParsingException {
        Stream streamFilter;
        final StreamInfoItemsCollector streamInfoItemsCollector = new StreamInfoItemsCollector(getServiceId());
        final TimeAgoParser timeAgoParser = getTimeAgoParser();
        JsonObject trendingTab = getTrendingTab();
        JsonObject object = trendingTab.getObject("content");
        boolean zEquals = trendingTab.getObject("endpoint").getObject("browseEndpoint").getString("params", "").equals(VIDEOS_TAB_PARAMS);
        if (object.has("richGridRenderer")) {
            object.getObject("richGridRenderer").getArray("contents").streamAsJsonObjects().filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.kiosk.YoutubeTrendingExtractor$$ExternalSyntheticLambda7
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
                    return ((JsonObject) obj).has("richItemRenderer");
                }
            }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.kiosk.YoutubeTrendingExtractor$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public /* synthetic */ Function andThen(Function function) {
                    return Function$CC.$default$andThen(this, function);
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((JsonObject) obj).getObject("richItemRenderer").getObject("content").getObject("videoRenderer");
                }

                public /* synthetic */ Function compose(Function function) {
                    return Function$CC.$default$compose(this, function);
                }
            }).forEachOrdered(new Consumer() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.kiosk.YoutubeTrendingExtractor$$ExternalSyntheticLambda9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    streamInfoItemsCollector.commit((StreamInfoItemExtractor) new YoutubeStreamInfoItemExtractor((JsonObject) obj, timeAgoParser));
                }

                public /* synthetic */ Consumer andThen(Consumer consumer) {
                    return Consumer$CC.$default$andThen(this, consumer);
                }
            });
        } else if (object.has("sectionListRenderer")) {
            Stream map = object.getObject("sectionListRenderer").getArray("contents").streamAsJsonObjects().flatMap(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.kiosk.YoutubeTrendingExtractor$$ExternalSyntheticLambda10
                @Override // java.util.function.Function
                public /* synthetic */ Function andThen(Function function) {
                    return Function$CC.$default$andThen(this, function);
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((JsonObject) obj).getObject("itemSectionRenderer").getArray("contents").streamAsJsonObjects();
                }

                public /* synthetic */ Function compose(Function function) {
                    return Function$CC.$default$compose(this, function);
                }
            }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.kiosk.YoutubeTrendingExtractor$$ExternalSyntheticLambda11
                @Override // java.util.function.Function
                public /* synthetic */ Function andThen(Function function) {
                    return Function$CC.$default$andThen(this, function);
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((JsonObject) obj).getObject("shelfRenderer");
                }

                public /* synthetic */ Function compose(Function function) {
                    return Function$CC.$default$compose(this, function);
                }
            });
            if (zEquals) {
                streamFilter = map.findFirst().stream();
            } else {
                streamFilter = map.filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.kiosk.YoutubeTrendingExtractor$$ExternalSyntheticLambda12
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
                        return YoutubeTrendingExtractor.lambda$getInitialPage$5((JsonObject) obj);
                    }
                });
            }
            streamFilter.flatMap(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.kiosk.YoutubeTrendingExtractor$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public /* synthetic */ Function andThen(Function function) {
                    return Function$CC.$default$andThen(this, function);
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((JsonObject) obj).getObject("content").getObject("expandedShelfContentsRenderer").getArray("items").streamAsJsonObjects();
                }

                public /* synthetic */ Function compose(Function function) {
                    return Function$CC.$default$compose(this, function);
                }
            }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.kiosk.YoutubeTrendingExtractor$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public /* synthetic */ Function andThen(Function function) {
                    return Function$CC.$default$andThen(this, function);
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((JsonObject) obj).getObject("videoRenderer");
                }

                public /* synthetic */ Function compose(Function function) {
                    return Function$CC.$default$compose(this, function);
                }
            }).forEachOrdered(new Consumer() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.kiosk.YoutubeTrendingExtractor$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    streamInfoItemsCollector.commit((StreamInfoItemExtractor) new YoutubeStreamInfoItemExtractor((JsonObject) obj, timeAgoParser));
                }

                public /* synthetic */ Consumer andThen(Consumer consumer) {
                    return Consumer$CC.$default$andThen(this, consumer);
                }
            });
        }
        return new ListExtractor.InfoItemsPage<>(streamInfoItemsCollector, null);
    }

    static /* synthetic */ boolean lambda$getInitialPage$5(JsonObject jsonObject) {
        return !jsonObject.has("title");
    }

    private JsonObject getTrendingTab() throws ParsingException {
        return (JsonObject) this.initialData.getObject("contents").getObject("twoColumnBrowseResultsRenderer").getArray("tabs").streamAsJsonObjects().map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.kiosk.YoutubeTrendingExtractor$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getObject("tabRenderer");
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.kiosk.YoutubeTrendingExtractor$$ExternalSyntheticLambda4
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
                return ((JsonObject) obj).getBoolean("selected");
            }
        }).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.kiosk.YoutubeTrendingExtractor$$ExternalSyntheticLambda5
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
                return ((JsonObject) obj).has("content");
            }
        }).findFirst().orElseThrow(new Supplier() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.kiosk.YoutubeTrendingExtractor$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return YoutubeTrendingExtractor.lambda$getTrendingTab$12();
            }
        });
    }

    static /* synthetic */ ParsingException lambda$getTrendingTab$12() {
        return new ParsingException("Could not get \"Now\" or \"Videos\" trending tab");
    }
}
