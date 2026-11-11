package org.schabi.newpipe.extractor.services.youtube.extractors;

import com.grack.nanojson.JsonObject;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.localization.DateWrapper;
import org.schabi.newpipe.extractor.localization.TimeAgoParser;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;
import org.schabi.newpipe.extractor.stream.Description;
import org.schabi.newpipe.extractor.utils.JsonUtils;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class YoutubeCommentsInfoItemExtractor implements CommentsInfoItemExtractor {

    @Nonnull
    private final JsonObject commentRenderer;

    @Nullable
    private final JsonObject commentRepliesRenderer;

    @Nonnull
    private final TimeAgoParser timeAgoParser;

    @Nonnull
    private final String url;

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public /* synthetic */ int getStreamPosition() {
        return CommentsInfoItemExtractor.CC.$default$getStreamPosition(this);
    }

    public YoutubeCommentsInfoItemExtractor(@Nonnull JsonObject jsonObject, @Nullable JsonObject jsonObject2, @Nonnull String str, @Nonnull TimeAgoParser timeAgoParser) {
        this.commentRenderer = jsonObject;
        this.commentRepliesRenderer = jsonObject2;
        this.url = str;
        this.timeAgoParser = timeAgoParser;
    }

    @Nonnull
    private List<Image> getAuthorThumbnails() throws ParsingException {
        try {
            return YoutubeParsingHelper.getImagesFromThumbnailsArray(JsonUtils.getArray(this.commentRenderer, "authorThumbnail.thumbnails"));
        } catch (Exception e) {
            throw new ParsingException("Could not get author thumbnails", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    @Nonnull
    public String getUrl() throws ParsingException {
        return this.url;
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        return getAuthorThumbnails();
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getName() throws ParsingException {
        try {
            return YoutubeParsingHelper.getTextFromObject(JsonUtils.getObject(this.commentRenderer, "authorText"));
        } catch (Exception unused) {
            return "";
        }
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public String getTextualUploadDate() throws ParsingException {
        try {
            return YoutubeParsingHelper.getTextFromObject(JsonUtils.getObject(this.commentRenderer, "publishedTimeText"));
        } catch (Exception e) {
            throw new ParsingException("Could not get publishedTimeText", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    @Nullable
    public DateWrapper getUploadDate() throws ParsingException {
        String textualUploadDate = getTextualUploadDate();
        if (textualUploadDate == null || textualUploadDate.isEmpty()) {
            return null;
        }
        return this.timeAgoParser.parse(textualUploadDate);
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public int getLikeCount() throws ParsingException {
        try {
            String strRemoveNonDigitCharacters = Utils.removeNonDigitCharacters(JsonUtils.getString(this.commentRenderer, "actionButtons.commentActionButtonsRenderer.likeButton.toggleButtonRenderer.accessibilityData.accessibilityData.label"));
            try {
                if (Utils.isBlank(strRemoveNonDigitCharacters)) {
                    return 0;
                }
                return Integer.parseInt(strRemoveNonDigitCharacters);
            } catch (Exception e) {
                throw new ParsingException("Unexpected error while parsing like count as Integer", e);
            }
        } catch (Exception unused) {
            String textualLikeCount = getTextualLikeCount();
            try {
                if (Utils.isBlank(textualLikeCount)) {
                    return 0;
                }
                return (int) Utils.mixedNumberWordToLong(textualLikeCount);
            } catch (Exception e2) {
                throw new ParsingException("Unexpected error while converting textual like count to like count", e2);
            }
        }
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public String getTextualLikeCount() throws ParsingException {
        try {
            if (!this.commentRenderer.has("voteCount")) {
                return "";
            }
            JsonObject object = JsonUtils.getObject(this.commentRenderer, "voteCount");
            return object.isEmpty() ? "" : YoutubeParsingHelper.getTextFromObject(object);
        } catch (Exception e) {
            throw new ParsingException("Could not get the vote count", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    @Nonnull
    public Description getCommentText() throws ParsingException {
        try {
            JsonObject object = JsonUtils.getObject(this.commentRenderer, "contentText");
            if (object.isEmpty()) {
                return Description.EMPTY_DESCRIPTION;
            }
            return new Description(Utils.removeUTF8BOM(YoutubeParsingHelper.getTextFromObject(object, true)), 1);
        } catch (Exception e) {
            throw new ParsingException("Could not get comment text", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public String getCommentId() throws ParsingException {
        try {
            return JsonUtils.getString(this.commentRenderer, "commentId");
        } catch (Exception e) {
            throw new ParsingException("Could not get comment id", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    @Nonnull
    public List<Image> getUploaderAvatars() throws ParsingException {
        return getAuthorThumbnails();
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public boolean isHeartedByUploader() {
        return this.commentRenderer.getObject("actionButtons").getObject("commentActionButtonsRenderer").has("creatorHeart");
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public boolean isPinned() {
        return this.commentRenderer.has("pinnedCommentBadge");
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public boolean isUploaderVerified() throws ParsingException {
        return this.commentRenderer.has("authorCommentBadge");
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public String getUploaderName() throws ParsingException {
        try {
            return YoutubeParsingHelper.getTextFromObject(JsonUtils.getObject(this.commentRenderer, "authorText"));
        } catch (Exception unused) {
            return "";
        }
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public String getUploaderUrl() throws ParsingException {
        try {
            return "https://www.youtube.com/channel/" + JsonUtils.getString(this.commentRenderer, "authorEndpoint.browseEndpoint.browseId");
        } catch (Exception unused) {
            return "";
        }
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public int getReplyCount() {
        if (this.commentRenderer.has("replyCount")) {
            return this.commentRenderer.getInt("replyCount");
        }
        return -1;
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public Page getReplies() {
        JsonObject jsonObject = this.commentRepliesRenderer;
        if (jsonObject == null) {
            return null;
        }
        try {
            return new Page(this.url, JsonUtils.getString(JsonUtils.getArray(jsonObject, "contents").getObject(0), "continuationItemRenderer.continuationEndpoint.continuationCommand.token"));
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public boolean isChannelOwner() {
        return this.commentRenderer.getBoolean("authorIsChannelOwner");
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public boolean hasCreatorReply() {
        JsonObject jsonObject = this.commentRepliesRenderer;
        if (jsonObject == null) {
            return false;
        }
        return jsonObject.has("viewRepliesCreatorThumbnail");
    }
}
