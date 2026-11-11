package org.schabi.newpipe.extractor.services.youtube.extractors.kiosk;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonWriter;
import j$.util.function.Consumer$CC;
import j$.util.function.Function$CC;
import j$.util.function.Predicate$CC;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.kiosk.KioskExtractor;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.localization.TimeAgoParser;
import org.schabi.newpipe.extractor.services.youtube.InnertubeClientRequestInfo;
import org.schabi.newpipe.extractor.services.youtube.YoutubeChannelHelper;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;
import org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemExtractor;
import org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamInfoItemLockupExtractor;
import org.schabi.newpipe.extractor.stream.StreamInfoItem;
import org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor;
import org.schabi.newpipe.extractor.stream.StreamInfoItemsCollector;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
abstract class YoutubeDesktopBaseKioskExtractor extends KioskExtractor<StreamInfoItem> {
    protected final String browseId;
    protected final String params;
    protected YoutubeChannelHelper.ChannelResponseData responseData;

    protected YoutubeDesktopBaseKioskExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler, String str, String str2, String str3) {
        super(streamingService, listLinkHandler, str);
        this.browseId = str2;
        this.params = str3;
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        this.responseData = YoutubeChannelHelper.getChannelResponse(this.browseId, this.params, getExtractorLocalization(), getExtractorContentCountry());
    }

    @Override // org.schabi.newpipe.extractor.kiosk.KioskExtractor, org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getName() throws ParsingException {
        return YoutubeChannelHelper.getChannelName(YoutubeChannelHelper.getChannelHeader(this.responseData.jsonResponse), null, this.responseData.jsonResponse);
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    @Nonnull
    public ListExtractor.InfoItemsPage<StreamInfoItem> getInitialPage() throws IOException, ExtractionException {
        JsonArray jsonArray;
        JsonObject object = this.responseData.jsonResponse.getObject("contents").getObject("twoColumnBrowseResultsRenderer").getArray("tabs").getObject(0).getObject("tabRenderer").getObject("content");
        if (object.has("sectionListRenderer")) {
            jsonArray = object.getObject("sectionListRenderer").getArray("contents").getObject(0).getObject("itemSectionRenderer").getArray("contents").getObject(0).getObject("shelfRenderer").getObject("content").getObject("gridRenderer").getArray("items");
        } else if (object.has("richGridRenderer")) {
            jsonArray = object.getObject("richGridRenderer").getArray("contents");
        } else {
            jsonArray = new JsonArray();
        }
        return collectStreamItems(jsonArray, this.responseData.jsonResponse.getObject("responseContext").getString("visitorData"));
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    public ListExtractor.InfoItemsPage<StreamInfoItem> getPage(Page page) throws IOException, ExtractionException {
        if (page == null || page.getBody() == null) {
            throw new IllegalArgumentException("Page is null or doesn't contain a body");
        }
        return collectStreamItems(((JsonObject) YoutubeParsingHelper.getJsonPostResponse("browse", page.getBody(), getExtractorLocalization()).getArray("onResponseReceivedActions").streamAsJsonObjects().filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.kiosk.YoutubeDesktopBaseKioskExtractor$$ExternalSyntheticLambda1
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
                return ((JsonObject) obj).has("appendContinuationItemsAction");
            }
        }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.kiosk.YoutubeDesktopBaseKioskExtractor$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getObject("appendContinuationItemsAction");
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).findFirst().orElse(new JsonObject())).getArray("continuationItems"), page.getId());
    }

    private ListExtractor.InfoItemsPage<StreamInfoItem> collectStreamItems(@Nonnull JsonArray jsonArray, @Nullable String str) throws IOException, ExtractionException {
        final StreamInfoItemsCollector streamInfoItemsCollector = new StreamInfoItemsCollector(getServiceId());
        Page nextPageFrom = null;
        if (!jsonArray.isEmpty()) {
            final TimeAgoParser timeAgoParser = getTimeAgoParser();
            jsonArray.streamAsJsonObjects().forEachOrdered(new Consumer() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.kiosk.YoutubeDesktopBaseKioskExtractor$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    YoutubeDesktopBaseKioskExtractor.lambda$collectStreamItems$2(streamInfoItemsCollector, timeAgoParser, (JsonObject) obj);
                }

                public /* synthetic */ Consumer andThen(Consumer consumer) {
                    return Consumer$CC.$default$andThen(this, consumer);
                }
            });
            JsonObject object = jsonArray.getObject(jsonArray.size() - 1);
            if (object.has("continuationItemRenderer")) {
                nextPageFrom = getNextPageFrom(object.getObject("continuationItemRenderer"), str);
            }
        }
        return new ListExtractor.InfoItemsPage<>(streamInfoItemsCollector, nextPageFrom);
    }

    static /* synthetic */ void lambda$collectStreamItems$2(StreamInfoItemsCollector streamInfoItemsCollector, TimeAgoParser timeAgoParser, JsonObject jsonObject) {
        if (jsonObject.has("richItemRenderer")) {
            JsonObject object = jsonObject.getObject("richItemRenderer").getObject("content");
            if (object.has("videoRenderer")) {
                streamInfoItemsCollector.commit((StreamInfoItemExtractor) new YoutubeStreamInfoItemExtractor(object.getObject("videoRenderer"), timeAgoParser));
                return;
            }
            return;
        }
        if (jsonObject.has("gridVideoRenderer")) {
            streamInfoItemsCollector.commit((StreamInfoItemExtractor) new YoutubeStreamInfoItemExtractor(jsonObject.getObject("gridVideoRenderer"), timeAgoParser));
        } else if (jsonObject.has("lockupViewModel")) {
            JsonObject object2 = jsonObject.getObject("lockupViewModel");
            if ("LOCKUP_CONTENT_TYPE_VIDEO".equals(object2.getString("contentType"))) {
                streamInfoItemsCollector.commit((StreamInfoItemExtractor) new YoutubeStreamInfoItemLockupExtractor(object2, timeAgoParser));
            }
        }
    }

    @Nullable
    private Page getNextPageFrom(@Nullable JsonObject jsonObject, @Nullable String str) throws IOException, ExtractionException {
        if (Utils.isNullOrEmpty(jsonObject)) {
            return null;
        }
        String string = jsonObject.getObject("continuationEndpoint").getObject("continuationCommand").getString("token");
        InnertubeClientRequestInfo innertubeClientRequestInfoOfWebClient = InnertubeClientRequestInfo.ofWebClient();
        innertubeClientRequestInfoOfWebClient.clientInfo.clientVersion = YoutubeParsingHelper.getClientVersion();
        innertubeClientRequestInfoOfWebClient.clientInfo.visitorData = str;
        return new Page("https://www.youtube.com/youtubei/v1/browse?prettyPrint=false", str, null, null, JsonWriter.string(YoutubeParsingHelper.prepareJsonBuilder(getExtractorLocalization(), getExtractorContentCountry(), innertubeClientRequestInfoOfWebClient, null).value("continuation", string).done()).getBytes(StandardCharsets.UTF_8));
    }
}
