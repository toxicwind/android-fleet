package org.schabi.newpipe.extractor.playlist;

import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.InfoItemExtractor;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.stream.Description;

/* JADX INFO: loaded from: classes8.dex */
public interface PlaylistInfoItemExtractor extends InfoItemExtractor {
    @Nonnull
    Description getDescription() throws ParsingException;

    @Nonnull
    PlaylistInfo.PlaylistType getPlaylistType() throws ParsingException;

    long getStreamCount() throws ParsingException;

    String getUploaderName() throws ParsingException;

    String getUploaderUrl() throws ParsingException;

    boolean isUploaderVerified() throws ParsingException;

    /* JADX INFO: renamed from: org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
    }
}
