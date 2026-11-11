package org.schabi.newpipe.extractor.services.youtube.extractors;

import com.grack.nanojson.JsonObject;
import j$.util.function.Function$CC;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.localization.DateWrapper;
import org.schabi.newpipe.extractor.localization.TimeAgoParser;
import org.schabi.newpipe.extractor.services.youtube.YoutubeDescriptionHelper;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;
import org.schabi.newpipe.extractor.stream.Description;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
class YoutubeCommentsEUVMInfoItemExtractor implements CommentsInfoItemExtractor {
    private static final String AUTHOR = "author";
    private static final String PROPERTIES = "properties";

    @Nonnull
    private final JsonObject commentEntityPayload;

    @Nullable
    private final JsonObject commentRepliesRenderer;

    @Nonnull
    private final JsonObject commentViewModel;

    @Nonnull
    private final JsonObject engagementToolbarStateEntityPayload;

    @Nonnull
    private final TimeAgoParser timeAgoParser;

    @Nonnull
    private final String videoUrl;

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public /* synthetic */ int getStreamPosition() {
        return CommentsInfoItemExtractor.CC.$default$getStreamPosition(this);
    }

    YoutubeCommentsEUVMInfoItemExtractor(@Nonnull JsonObject jsonObject, @Nullable JsonObject jsonObject2, @Nonnull JsonObject jsonObject3, @Nonnull JsonObject jsonObject4, @Nonnull String str, @Nonnull TimeAgoParser timeAgoParser) {
        this.commentViewModel = jsonObject;
        this.commentRepliesRenderer = jsonObject2;
        this.commentEntityPayload = jsonObject3;
        this.engagementToolbarStateEntityPayload = jsonObject4;
        this.videoUrl = str;
        this.timeAgoParser = timeAgoParser;
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getName() throws ParsingException {
        return getUploaderName();
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getUrl() throws ParsingException {
        return this.videoUrl;
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        return getUploaderAvatars();
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public int getLikeCount() throws ParsingException {
        String textualLikeCount = getTextualLikeCount();
        try {
            if (Utils.isBlank(textualLikeCount)) {
                return 0;
            }
            return (int) Utils.mixedNumberWordToLong(textualLikeCount);
        } catch (Exception e) {
            throw new ParsingException("Unexpected error while converting textual like count to like count", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public String getTextualLikeCount() {
        return this.commentEntityPayload.getObject("toolbar").getString("likeCountNotliked");
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public Description getCommentText() throws ParsingException {
        return new Description(YoutubeDescriptionHelper.attributedDescriptionToHtml(this.commentEntityPayload.getObject(PROPERTIES).getObject("content")), 1);
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public String getTextualUploadDate() throws ParsingException {
        return this.commentEntityPayload.getObject(PROPERTIES).getString("publishedTime");
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    @Nullable
    public DateWrapper getUploadDate() throws ParsingException {
        String textualUploadDate = getTextualUploadDate();
        if (Utils.isNullOrEmpty(textualUploadDate)) {
            return null;
        }
        return this.timeAgoParser.parse(textualUploadDate);
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public String getCommentId() throws ParsingException {
        String string = this.commentEntityPayload.getObject(PROPERTIES).getString("commentId");
        if (!Utils.isNullOrEmpty(string)) {
            return string;
        }
        String string2 = this.commentViewModel.getString("commentId");
        if (Utils.isNullOrEmpty(string2)) {
            throw new ParsingException("Could not get comment ID");
        }
        return string2;
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public String getUploaderUrl() throws ParsingException {
        JsonObject object = this.commentEntityPayload.getObject("author");
        String string = object.getString("channelId");
        if (Utils.isNullOrEmpty(string)) {
            string = object.getObject("channelCommand").getObject("innertubeCommand").getObject("browseEndpoint").getString("browseId");
            if (Utils.isNullOrEmpty(string)) {
                string = object.getObject("avatar").getObject("endpoint").getObject("innertubeCommand").getObject("browseEndpoint").getString("browseId");
                if (Utils.isNullOrEmpty(string)) {
                    throw new ParsingException("Could not get channel ID");
                }
            }
        }
        return "https://www.youtube.com/channel/" + string;
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public String getUploaderName() throws ParsingException {
        return this.commentEntityPayload.getObject("author").getString("displayName");
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    @Nonnull
    public List<Image> getUploaderAvatars() throws ParsingException {
        return YoutubeParsingHelper.getImagesFromThumbnailsArray(this.commentEntityPayload.getObject("avatar").getObject("image").getArray("sources"));
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public boolean isHeartedByUploader() {
        return "TOOLBAR_HEART_STATE_HEARTED".equals(this.engagementToolbarStateEntityPayload.getString("heartState"));
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public boolean isPinned() {
        return this.commentViewModel.has("pinnedText");
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public boolean isUploaderVerified() throws ParsingException {
        JsonObject object = this.commentEntityPayload.getObject("author");
        return object.getBoolean("isVerified") || object.getBoolean("isArtist");
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public int getReplyCount() throws ParsingException {
        String string = this.commentEntityPayload.getObject("toolbar").getString("replyCount");
        if (Utils.isNullOrEmpty(string)) {
            return 0;
        }
        return (int) Utils.mixedNumberWordToLong(string);
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    @Nullable
    public Page getReplies() throws ParsingException {
        if (Utils.isNullOrEmpty(this.commentRepliesRenderer)) {
            return null;
        }
        return new Page(this.videoUrl, (String) this.commentRepliesRenderer.getArray("contents").streamAsJsonObjects().map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeCommentsEUVMInfoItemExtractor$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getObject("continuationItemRenderer", null);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).filter(new YoutubeCommentsEUVMInfoItemExtractor$$ExternalSyntheticLambda1()).findFirst().map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeCommentsEUVMInfoItemExtractor$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getObject("continuationEndpoint").getObject("continuationCommand").getString("token");
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).orElseThrow(new Supplier() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeCommentsEUVMInfoItemExtractor$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return YoutubeCommentsEUVMInfoItemExtractor.lambda$getReplies$2();
            }
        }));
    }

    static /* synthetic */ ParsingException lambda$getReplies$2() {
        return new ParsingException("Could not get comment replies continuation");
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public boolean isChannelOwner() {
        return this.commentEntityPayload.getObject("author").getBoolean("isCreator");
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public boolean hasCreatorReply() {
        JsonObject jsonObject = this.commentRepliesRenderer;
        return jsonObject != null && jsonObject.has("viewRepliesCreatorThumbnail");
    }
}
