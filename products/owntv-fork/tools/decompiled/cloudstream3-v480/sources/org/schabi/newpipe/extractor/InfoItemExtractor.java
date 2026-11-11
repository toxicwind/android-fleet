package org.schabi.newpipe.extractor;

import java.util.List;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.exceptions.ParsingException;

/* JADX INFO: loaded from: classes8.dex */
public interface InfoItemExtractor {
    String getName() throws ParsingException;

    @Nonnull
    List<Image> getThumbnails() throws ParsingException;

    String getUrl() throws ParsingException;
}
