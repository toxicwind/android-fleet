package org.schabi.newpipe.extractor.comments;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.InfoItemExtractor;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.localization.DateWrapper;
import org.schabi.newpipe.extractor.stream.Description;

/* JADX INFO: loaded from: classes8.dex */
public interface CommentsInfoItemExtractor extends InfoItemExtractor {
    String getCommentId() throws ParsingException;

    @Nonnull
    Description getCommentText() throws ParsingException;

    int getLikeCount() throws ParsingException;

    @Nullable
    Page getReplies() throws ParsingException;

    int getReplyCount() throws ParsingException;

    int getStreamPosition() throws ParsingException;

    String getTextualLikeCount() throws ParsingException;

    String getTextualUploadDate() throws ParsingException;

    @Nullable
    DateWrapper getUploadDate() throws ParsingException;

    @Nonnull
    List<Image> getUploaderAvatars() throws ParsingException;

    String getUploaderName() throws ParsingException;

    String getUploaderUrl() throws ParsingException;

    boolean hasCreatorReply() throws ParsingException;

    boolean isChannelOwner() throws ParsingException;

    boolean isHeartedByUploader() throws ParsingException;

    boolean isPinned() throws ParsingException;

    boolean isUploaderVerified() throws ParsingException;

    /* JADX INFO: renamed from: org.schabi.newpipe.extractor.comments.CommentsInfoItemExtractor$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static int $default$getLikeCount(CommentsInfoItemExtractor _this) throws ParsingException {
            return -1;
        }

        @Nullable
        public static Page $default$getReplies(CommentsInfoItemExtractor _this) throws ParsingException {
            return null;
        }

        public static int $default$getReplyCount(CommentsInfoItemExtractor _this) throws ParsingException {
            return -1;
        }

        public static int $default$getStreamPosition(CommentsInfoItemExtractor _this) throws ParsingException {
            return -1;
        }

        @Nullable
        public static DateWrapper $default$getUploadDate(CommentsInfoItemExtractor _this) throws ParsingException {
            return null;
        }

        public static boolean $default$hasCreatorReply(CommentsInfoItemExtractor _this) throws ParsingException {
            return false;
        }

        public static boolean $default$isChannelOwner(CommentsInfoItemExtractor _this) throws ParsingException {
            return false;
        }

        public static boolean $default$isHeartedByUploader(CommentsInfoItemExtractor _this) throws ParsingException {
            return false;
        }

        public static boolean $default$isPinned(CommentsInfoItemExtractor _this) throws ParsingException {
            return false;
        }

        public static boolean $default$isUploaderVerified(CommentsInfoItemExtractor _this) throws ParsingException {
            return false;
        }

        public static String $default$getTextualLikeCount(CommentsInfoItemExtractor _this) throws ParsingException {
            return "";
        }

        public static String $default$getTextualUploadDate(CommentsInfoItemExtractor _this) throws ParsingException {
            return "";
        }

        public static String $default$getCommentId(CommentsInfoItemExtractor _this) throws ParsingException {
            return "";
        }

        public static String $default$getUploaderUrl(CommentsInfoItemExtractor _this) throws ParsingException {
            return "";
        }

        public static String $default$getUploaderName(CommentsInfoItemExtractor _this) throws ParsingException {
            return "";
        }
    }
}
