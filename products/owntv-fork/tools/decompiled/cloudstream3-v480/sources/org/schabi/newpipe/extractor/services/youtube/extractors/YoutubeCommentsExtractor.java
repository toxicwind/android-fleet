package org.schabi.newpipe.extractor.services.youtube.extractors;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonWriter;
import j$.util.function.Function$CC;
import j$.util.function.Predicate$CC;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.localization.Localization;
import org.schabi.newpipe.extractor.localization.TimeAgoParser;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;
import org.schabi.newpipe.extractor.utils.JsonUtils;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class YoutubeCommentsExtractor extends CommentsExtractor {
    private static final String COMMENT_RENDERER_KEY = "commentRenderer";
    private static final String COMMENT_VIEW_MODEL_KEY = "commentViewModel";
    private JsonObject ajaxJson;
    private boolean commentsDisabled;

    public YoutubeCommentsExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler) {
        super(streamingService, listLinkHandler);
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    @Nonnull
    public ListExtractor.InfoItemsPage<CommentsInfoItem> getInitialPage() throws IOException, ExtractionException {
        if (this.commentsDisabled) {
            return getInfoItemsPageForDisabledComments();
        }
        return extractComments(this.ajaxJson);
    }

    @Nullable
    private String findInitialCommentsToken(JsonObject jsonObject) {
        JsonArray jsonContents = getJsonContents(jsonObject);
        if (jsonContents == null) {
            return null;
        }
        String str = (String) jsonContents.streamAsJsonObjects().filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeCommentsExtractor$$ExternalSyntheticLambda2
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
                return YoutubeCommentsExtractor.lambda$findInitialCommentsToken$0((JsonObject) obj);
            }
        }).findFirst().map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeCommentsExtractor$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return YoutubeCommentsExtractor.lambda$findInitialCommentsToken$1((JsonObject) obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).orElse(null);
        this.commentsDisabled = str == null;
        return str;
    }

    static /* synthetic */ boolean lambda$findInitialCommentsToken$0(JsonObject jsonObject) {
        try {
            return "comments-section".equals(JsonUtils.getString(jsonObject, "itemSectionRenderer.targetId"));
        } catch (ParsingException unused) {
            return false;
        }
    }

    static /* synthetic */ String lambda$findInitialCommentsToken$1(JsonObject jsonObject) {
        try {
            return JsonUtils.getString(jsonObject.getObject("itemSectionRenderer").getArray("contents").getObject(0), "continuationItemRenderer.continuationEndpoint.continuationCommand.token");
        } catch (ParsingException unused) {
            return null;
        }
    }

    @Nullable
    private JsonArray getJsonContents(JsonObject jsonObject) {
        try {
            return JsonUtils.getArray(jsonObject, "contents.twoColumnWatchNextResults.results.results.contents");
        } catch (ParsingException unused) {
            return null;
        }
    }

    @Nonnull
    private JsonObject getMutationPayloadFromEntityKey(@Nonnull JsonArray jsonArray, @Nonnull final String str) throws ParsingException {
        return jsonArray.streamAsJsonObjects().filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeCommentsExtractor$$ExternalSyntheticLambda0
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
                return str.equals(((JsonObject) obj).getString("entityKey"));
            }
        }).findFirst().orElseThrow(new Supplier() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeCommentsExtractor$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return YoutubeCommentsExtractor.lambda$getMutationPayloadFromEntityKey$3();
            }
        }).getObject("payload");
    }

    static /* synthetic */ ParsingException lambda$getMutationPayloadFromEntityKey$3() {
        return new ParsingException("Could not get comment entity payload mutation");
    }

    @Nonnull
    private ListExtractor.InfoItemsPage<CommentsInfoItem> getInfoItemsPageForDisabledComments() {
        return new ListExtractor.InfoItemsPage<>(Collections.EMPTY_LIST, null, Collections.EMPTY_LIST);
    }

    @Nullable
    private Page getNextPage(@Nonnull JsonObject jsonObject) throws ExtractionException {
        String str;
        JsonArray array = jsonObject.getArray("onResponseReceivedEndpoints");
        if (array.isEmpty()) {
            return null;
        }
        try {
            JsonObject object = array.getObject(array.size() - 1);
            JsonArray array2 = object.getObject("reloadContinuationItemsCommand", object.getObject("appendContinuationItemsAction")).getArray("continuationItems");
            if (array2.isEmpty()) {
                return null;
            }
            JsonObject object2 = array2.getObject(array2.size() - 1).getObject("continuationItemRenderer");
            if (object2.has("button")) {
                str = "button.buttonRenderer.command.continuationCommand.token";
            } else {
                str = "continuationEndpoint.continuationCommand.token";
            }
            return getNextPage(JsonUtils.getString(object2, str));
        } catch (Exception unused) {
            return null;
        }
    }

    @Nonnull
    private Page getNextPage(String str) throws ParsingException {
        return new Page(getUrl(), str);
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    public ListExtractor.InfoItemsPage<CommentsInfoItem> getPage(Page page) throws IOException, ExtractionException {
        if (this.commentsDisabled) {
            return getInfoItemsPageForDisabledComments();
        }
        if (page == null || Utils.isNullOrEmpty(page.getId())) {
            throw new IllegalArgumentException("Page doesn't have the continuation.");
        }
        Localization extractorLocalization = getExtractorLocalization();
        return extractComments(YoutubeParsingHelper.getJsonPostResponse("next", JsonWriter.string(YoutubeParsingHelper.prepareDesktopJsonBuilder(extractorLocalization, getExtractorContentCountry()).value("continuation", page.getId()).done()).getBytes(StandardCharsets.UTF_8), extractorLocalization));
    }

    private ListExtractor.InfoItemsPage<CommentsInfoItem> extractComments(JsonObject jsonObject) throws ExtractionException {
        CommentsInfoItemsCollector commentsInfoItemsCollector = new CommentsInfoItemsCollector(getServiceId());
        collectCommentsFrom(commentsInfoItemsCollector, jsonObject);
        return new ListExtractor.InfoItemsPage<>(commentsInfoItemsCollector, getNextPage(jsonObject));
    }

    private void collectCommentsFrom(@Nonnull CommentsInfoItemsCollector commentsInfoItemsCollector, @Nonnull JsonObject jsonObject) throws ParsingException {
        String str;
        JsonArray array = jsonObject.getArray("onResponseReceivedEndpoints");
        if (array.isEmpty()) {
            return;
        }
        JsonObject object = array.getObject(array.size() - 1);
        if (object.has("reloadContinuationItemsCommand")) {
            str = "reloadContinuationItemsCommand.continuationItems";
        } else if (!object.has("appendContinuationItemsAction")) {
            return;
        } else {
            str = "appendContinuationItemsAction.continuationItems";
        }
        try {
            JsonArray jsonArray = new JsonArray(JsonUtils.getArray(object, str));
            int size = jsonArray.size() - 1;
            if (!jsonArray.isEmpty() && jsonArray.getObject(size).has("continuationItemRenderer")) {
                jsonArray.remove(size);
            }
            JsonArray array2 = jsonObject.getObject("frameworkUpdates").getObject("entityBatchUpdate").getArray("mutations");
            String url = getUrl();
            TimeAgoParser timeAgoParser = getTimeAgoParser();
            for (Object obj : jsonArray) {
                if (obj instanceof JsonObject) {
                    collectCommentItem(array2, (JsonObject) obj, commentsInfoItemsCollector, url, timeAgoParser);
                }
            }
        } catch (Exception unused) {
        }
    }

    private void collectCommentItem(@Nonnull JsonArray jsonArray, @Nonnull JsonObject jsonObject, @Nonnull CommentsInfoItemsCollector commentsInfoItemsCollector, @Nonnull String str, @Nonnull TimeAgoParser timeAgoParser) throws ParsingException {
        if (jsonObject.has("commentThreadRenderer")) {
            JsonObject object = jsonObject.getObject("commentThreadRenderer");
            if (object.has(COMMENT_VIEW_MODEL_KEY)) {
                JsonObject object2 = object.getObject(COMMENT_VIEW_MODEL_KEY).getObject(COMMENT_VIEW_MODEL_KEY);
                commentsInfoItemsCollector.commit((CommentsInfoItemExtractor) new YoutubeCommentsEUVMInfoItemExtractor(object2, object.getObject("replies").getObject("commentRepliesRenderer"), getMutationPayloadFromEntityKey(jsonArray, object2.getString("commentKey", "")).getObject("commentEntityPayload"), getMutationPayloadFromEntityKey(jsonArray, object2.getString("toolbarStateKey", "")).getObject("engagementToolbarStateEntityPayload"), str, timeAgoParser));
                return;
            } else {
                if (object.has("comment")) {
                    commentsInfoItemsCollector.commit((CommentsInfoItemExtractor) new YoutubeCommentsInfoItemExtractor(object.getObject("comment").getObject(COMMENT_RENDERER_KEY), object.getObject("replies").getObject("commentRepliesRenderer"), str, timeAgoParser));
                    return;
                }
                return;
            }
        }
        if (jsonObject.has(COMMENT_VIEW_MODEL_KEY)) {
            JsonObject object3 = jsonObject.getObject(COMMENT_VIEW_MODEL_KEY);
            commentsInfoItemsCollector.commit((CommentsInfoItemExtractor) new YoutubeCommentsEUVMInfoItemExtractor(object3, null, getMutationPayloadFromEntityKey(jsonArray, object3.getString("commentKey", "")).getObject("commentEntityPayload"), getMutationPayloadFromEntityKey(jsonArray, object3.getString("toolbarStateKey", "")).getObject("engagementToolbarStateEntityPayload"), str, timeAgoParser));
        } else if (jsonObject.has(COMMENT_RENDERER_KEY)) {
            commentsInfoItemsCollector.commit((CommentsInfoItemExtractor) new YoutubeCommentsInfoItemExtractor(jsonObject.getObject(COMMENT_RENDERER_KEY), null, str, timeAgoParser));
        }
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        Localization extractorLocalization = getExtractorLocalization();
        String strFindInitialCommentsToken = findInitialCommentsToken(YoutubeParsingHelper.getJsonPostResponse("next", JsonWriter.string(YoutubeParsingHelper.prepareDesktopJsonBuilder(extractorLocalization, getExtractorContentCountry()).value(YoutubeParsingHelper.VIDEO_ID, getId()).done()).getBytes(StandardCharsets.UTF_8), extractorLocalization));
        if (strFindInitialCommentsToken == null) {
            return;
        }
        this.ajaxJson = YoutubeParsingHelper.getJsonPostResponse("next", JsonWriter.string(YoutubeParsingHelper.prepareDesktopJsonBuilder(extractorLocalization, getExtractorContentCountry()).value("continuation", strFindInitialCommentsToken).done()).getBytes(StandardCharsets.UTF_8), extractorLocalization);
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsExtractor
    public boolean isCommentsDisabled() {
        return this.commentsDisabled;
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsExtractor
    public int getCommentsCount() throws ExtractionException {
        assertPageFetched();
        if (this.commentsDisabled) {
            return -1;
        }
        try {
            return Integer.parseInt(Utils.removeNonDigitCharacters(YoutubeParsingHelper.getTextFromObject(this.ajaxJson.getArray("onResponseReceivedEndpoints").getObject(0).getObject("reloadContinuationItemsCommand").getArray("continuationItems").getObject(0).getObject("commentsHeaderRenderer").getObject("countText"))));
        } catch (Exception e) {
            throw new ExtractionException("Unable to get comments count", e);
        }
    }
}
