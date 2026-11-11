package org.schabi.newpipe.extractor.services.peertube.extractors;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonWriter;
import j$.util.Objects;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.jsoup.Jsoup;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.ServiceList;
import org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.localization.DateWrapper;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;
import org.schabi.newpipe.extractor.stream.Description;
import org.schabi.newpipe.extractor.utils.JsonUtils;

/* JADX INFO: loaded from: classes8.dex */
public class PeertubeCommentsInfoItemExtractor implements CommentsInfoItemExtractor {

    @Nonnull
    private final String baseUrl;

    @Nullable
    private final JsonArray children;
    private final boolean isReply;

    @Nonnull
    private final JsonObject item;
    private Integer replyCount;

    @Nonnull
    private final String url;

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public /* synthetic */ int getLikeCount() {
        return CommentsInfoItemExtractor.CC.$default$getLikeCount(this);
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public /* synthetic */ int getStreamPosition() {
        return CommentsInfoItemExtractor.CC.$default$getStreamPosition(this);
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public /* synthetic */ String getTextualLikeCount() {
        return CommentsInfoItemExtractor.CC.$default$getTextualLikeCount(this);
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public /* synthetic */ boolean isChannelOwner() {
        return CommentsInfoItemExtractor.CC.$default$isChannelOwner(this);
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public /* synthetic */ boolean isHeartedByUploader() {
        return CommentsInfoItemExtractor.CC.$default$isHeartedByUploader(this);
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public /* synthetic */ boolean isPinned() {
        return CommentsInfoItemExtractor.CC.$default$isPinned(this);
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public /* synthetic */ boolean isUploaderVerified() {
        return CommentsInfoItemExtractor.CC.$default$isUploaderVerified(this);
    }

    public PeertubeCommentsInfoItemExtractor(@Nonnull JsonObject jsonObject, @Nullable JsonArray jsonArray, @Nonnull String str, @Nonnull String str2, boolean z) {
        this.item = jsonObject;
        this.children = jsonArray;
        this.url = str;
        this.baseUrl = str2;
        this.isReply = z;
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getUrl() throws ParsingException {
        return this.url + "/" + getCommentId();
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    @Nonnull
    public List<Image> getThumbnails() {
        return getUploaderAvatars();
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getName() throws ParsingException {
        return JsonUtils.getString(this.item, "account.displayName");
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public String getTextualUploadDate() throws ParsingException {
        return JsonUtils.getString(this.item, "createdAt");
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public DateWrapper getUploadDate() throws ParsingException {
        return DateWrapper.fromInstant(getTextualUploadDate());
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    @Nonnull
    public Description getCommentText() throws ParsingException {
        String string = JsonUtils.getString(this.item, "text");
        try {
            return new Description(Jsoup.parse(string).body().text(), 3);
        } catch (Exception unused) {
            return new Description(string.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", ""), 3);
        }
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public String getCommentId() {
        return Objects.toString(Long.valueOf(this.item.getLong("id")), null);
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    @Nonnull
    public List<Image> getUploaderAvatars() {
        return PeertubeParsingHelper.getAvatarsFromOwnerAccountOrVideoChannelObject(this.baseUrl, this.item.getObject("account"));
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public String getUploaderName() throws ParsingException {
        return JsonUtils.getString(this.item, "account.name") + "@" + JsonUtils.getString(this.item, "account.host");
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public String getUploaderUrl() throws ParsingException {
        String string = JsonUtils.getString(this.item, "account.name");
        String string2 = JsonUtils.getString(this.item, "account.host");
        return ServiceList.PeerTube.getChannelLHFactory().fromId("accounts/" + string + "@" + string2, this.baseUrl).getUrl();
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    @Nullable
    public Page getReplies() throws ParsingException {
        JsonArray jsonArray;
        if (getReplyCount() == 0) {
            return null;
        }
        String string = JsonUtils.getNumber(this.item, "threadId").toString();
        String str = this.url + "/" + string;
        if (this.isReply && (jsonArray = this.children) != null && !jsonArray.isEmpty()) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.put("children", this.children);
            return new Page(str, string, JsonWriter.string(jsonObject).getBytes(StandardCharsets.UTF_8));
        }
        return new Page(str, string);
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public int getReplyCount() throws ParsingException {
        if (this.replyCount == null) {
            JsonArray jsonArray = this.children;
            if (jsonArray != null && !jsonArray.isEmpty()) {
                this.replyCount = Integer.valueOf(this.children.size());
            } else {
                this.replyCount = Integer.valueOf(JsonUtils.getNumber(this.item, "totalReplies").intValue());
            }
        }
        return this.replyCount.intValue();
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public boolean hasCreatorReply() {
        return this.item.has("totalRepliesFromVideoAuthor") && this.item.getInt("totalRepliesFromVideoAuthor") > 0;
    }
}
