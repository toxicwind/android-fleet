package org.schabi.newpipe.extractor.comments;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.localization.DateWrapper;
import org.schabi.newpipe.extractor.stream.Description;

/* JADX INFO: loaded from: classes8.dex */
public class CommentsInfoItem extends InfoItem {
    public static final int NO_LIKE_COUNT = -1;
    public static final int NO_STREAM_POSITION = -1;
    public static final int UNKNOWN_REPLY_COUNT = -1;
    private String commentId;

    @Nonnull
    private Description commentText;
    private boolean creatorReply;
    private boolean heartedByUploader;
    private boolean isChannelOwner;
    private int likeCount;
    private boolean pinned;

    @Nullable
    private Page replies;
    private int replyCount;
    private int streamPosition;
    private String textualLikeCount;
    private String textualUploadDate;

    @Nullable
    private DateWrapper uploadDate;

    @Nonnull
    private List<Image> uploaderAvatars;
    private String uploaderName;
    private String uploaderUrl;
    private boolean uploaderVerified;

    public CommentsInfoItem(int i, String str, String str2) {
        super(InfoItem.InfoType.COMMENT, i, str, str2);
        this.commentText = Description.EMPTY_DESCRIPTION;
        this.uploaderAvatars = Collections.EMPTY_LIST;
    }

    public String getCommentId() {
        return this.commentId;
    }

    public void setCommentId(String str) {
        this.commentId = str;
    }

    @Nonnull
    public Description getCommentText() {
        return this.commentText;
    }

    public void setCommentText(@Nonnull Description description) {
        this.commentText = description;
    }

    public String getUploaderName() {
        return this.uploaderName;
    }

    public void setUploaderName(String str) {
        this.uploaderName = str;
    }

    @Nonnull
    public List<Image> getUploaderAvatars() {
        return this.uploaderAvatars;
    }

    public void setUploaderAvatars(@Nonnull List<Image> list) {
        this.uploaderAvatars = list;
    }

    public String getUploaderUrl() {
        return this.uploaderUrl;
    }

    public void setUploaderUrl(String str) {
        this.uploaderUrl = str;
    }

    public String getTextualUploadDate() {
        return this.textualUploadDate;
    }

    public void setTextualUploadDate(String str) {
        this.textualUploadDate = str;
    }

    @Nullable
    public DateWrapper getUploadDate() {
        return this.uploadDate;
    }

    public void setUploadDate(@Nullable DateWrapper dateWrapper) {
        this.uploadDate = dateWrapper;
    }

    public int getLikeCount() {
        return this.likeCount;
    }

    public void setLikeCount(int i) {
        this.likeCount = i;
    }

    public String getTextualLikeCount() {
        return this.textualLikeCount;
    }

    public void setTextualLikeCount(String str) {
        this.textualLikeCount = str;
    }

    public void setHeartedByUploader(boolean z) {
        this.heartedByUploader = z;
    }

    public boolean isHeartedByUploader() {
        return this.heartedByUploader;
    }

    public boolean isPinned() {
        return this.pinned;
    }

    public void setPinned(boolean z) {
        this.pinned = z;
    }

    public void setUploaderVerified(boolean z) {
        this.uploaderVerified = z;
    }

    public boolean isUploaderVerified() {
        return this.uploaderVerified;
    }

    public void setStreamPosition(int i) {
        this.streamPosition = i;
    }

    public int getStreamPosition() {
        return this.streamPosition;
    }

    public void setReplyCount(int i) {
        this.replyCount = i;
    }

    public int getReplyCount() {
        return this.replyCount;
    }

    public void setReplies(@Nullable Page page) {
        this.replies = page;
    }

    @Nullable
    public Page getReplies() {
        return this.replies;
    }

    public void setChannelOwner(boolean z) {
        this.isChannelOwner = z;
    }

    public boolean isChannelOwner() {
        return this.isChannelOwner;
    }

    public void setCreatorReply(boolean z) {
        this.creatorReply = z;
    }

    public boolean hasCreatorReply() {
        return this.creatorReply;
    }
}
