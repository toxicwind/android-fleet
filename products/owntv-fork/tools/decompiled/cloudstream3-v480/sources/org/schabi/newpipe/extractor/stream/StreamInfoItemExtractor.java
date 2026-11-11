package org.schabi.newpipe.extractor.stream;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.InfoItemExtractor;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.localization.DateWrapper;

/* JADX INFO: loaded from: classes8.dex */
public interface StreamInfoItemExtractor extends InfoItemExtractor {
    @Nonnull
    ContentAvailability getContentAvailability() throws ParsingException;

    long getDuration() throws ParsingException;

    @Nullable
    String getShortDescription() throws ParsingException;

    StreamType getStreamType() throws ParsingException;

    @Nullable
    String getTextualUploadDate() throws ParsingException;

    @Nullable
    DateWrapper getUploadDate() throws ParsingException;

    @Nonnull
    List<Image> getUploaderAvatars() throws ParsingException;

    String getUploaderName() throws ParsingException;

    String getUploaderUrl() throws ParsingException;

    long getViewCount() throws ParsingException;

    boolean isAd() throws ParsingException;

    boolean isShortFormContent() throws ParsingException;

    boolean isUploaderVerified() throws ParsingException;

    /* JADX INFO: renamed from: org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        @Nullable
        public static String $default$getShortDescription(StreamInfoItemExtractor _this) throws ParsingException {
            return null;
        }

        public static boolean $default$isShortFormContent(StreamInfoItemExtractor _this) throws ParsingException {
            return false;
        }
    }
}
