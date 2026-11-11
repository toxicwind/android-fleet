package org.schabi.newpipe.extractor.services.bandcamp.extractors;

import com.grack.nanojson.JsonObject;
import java.util.List;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.localization.DateWrapper;
import org.schabi.newpipe.extractor.stream.Description;

/* JADX INFO: loaded from: classes8.dex */
public class BandcampCommentsInfoItemExtractor implements CommentsInfoItemExtractor {
    private final JsonObject review;
    private final String url;

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public /* synthetic */ String getCommentId() {
        return CommentsInfoItemExtractor.CC.$default$getCommentId(this);
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public /* synthetic */ int getLikeCount() {
        return CommentsInfoItemExtractor.CC.$default$getLikeCount(this);
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public /* synthetic */ Page getReplies() {
        return CommentsInfoItemExtractor.CC.$default$getReplies(this);
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public /* synthetic */ int getReplyCount() {
        return CommentsInfoItemExtractor.CC.$default$getReplyCount(this);
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
    public /* synthetic */ String getTextualUploadDate() {
        return CommentsInfoItemExtractor.CC.$default$getTextualUploadDate(this);
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public /* synthetic */ DateWrapper getUploadDate() {
        return CommentsInfoItemExtractor.CC.$default$getUploadDate(this);
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public /* synthetic */ String getUploaderUrl() {
        return CommentsInfoItemExtractor.CC.$default$getUploaderUrl(this);
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public /* synthetic */ boolean hasCreatorReply() {
        return CommentsInfoItemExtractor.CC.$default$hasCreatorReply(this);
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

    public BandcampCommentsInfoItemExtractor(JsonObject jsonObject, String str) {
        this.review = jsonObject;
        this.url = str;
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getName() throws ParsingException {
        return getCommentText().getContent();
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getUrl() {
        return this.url;
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        return getUploaderAvatars();
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    @Nonnull
    public Description getCommentText() throws ParsingException {
        return new Description(this.review.getString("why"), 3);
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public String getUploaderName() throws ParsingException {
        return this.review.getString("name");
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    @Nonnull
    public List<Image> getUploaderAvatars() {
        return BandcampExtractorHelper.getImagesFromImageId(this.review.getLong("image_id"), false);
    }
}
