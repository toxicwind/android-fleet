package org.schabi.newpipe.extractor.services.soundcloud.extractors;

import androidx.autofill.HintConstants;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.grack.nanojson.JsonObject;
import j$.util.Objects;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.localization.DateWrapper;
import org.schabi.newpipe.extractor.services.soundcloud.SoundcloudParsingHelper;
import org.schabi.newpipe.extractor.stream.Description;

/* JADX INFO: loaded from: classes8.dex */
public class SoundcloudCommentsInfoItemExtractor implements CommentsInfoItemExtractor {
    private final JsonObject json;
    private final String url;

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
    public /* synthetic */ String getTextualLikeCount() {
        return CommentsInfoItemExtractor.CC.$default$getTextualLikeCount(this);
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

    public SoundcloudCommentsInfoItemExtractor(JsonObject jsonObject, String str) {
        this.json = jsonObject;
        this.url = str;
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public String getCommentId() {
        return Objects.toString(Long.valueOf(this.json.getLong("id")), null);
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    @Nonnull
    public Description getCommentText() {
        return new Description(this.json.getString(TtmlNode.TAG_BODY), 3);
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public String getUploaderName() {
        return this.json.getObject("user").getString(HintConstants.AUTOFILL_HINT_USERNAME);
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    @Nonnull
    public List<Image> getUploaderAvatars() {
        return SoundcloudParsingHelper.getAllImagesFromArtworkOrAvatarUrl(this.json.getObject("user").getString("avatar_url"));
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public boolean isUploaderVerified() throws ParsingException {
        return this.json.getObject("user").getBoolean("verified");
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public int getStreamPosition() {
        return this.json.getInt("timestamp") / 1000;
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public String getUploaderUrl() {
        return this.json.getObject("user").getString("permalink_url");
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    public String getTextualUploadDate() {
        return this.json.getString("created_at");
    }

    @Override // org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor
    @Nullable
    public DateWrapper getUploadDate() throws ParsingException {
        return SoundcloudParsingHelper.parseDate(getTextualUploadDate());
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getName() throws ParsingException {
        return this.json.getObject("user").getString("permalink");
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getUrl() {
        return this.url;
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    @Nonnull
    public List<Image> getThumbnails() {
        return SoundcloudParsingHelper.getAllImagesFromArtworkOrAvatarUrl(this.json.getObject("user").getString("avatar_url"));
    }
}
