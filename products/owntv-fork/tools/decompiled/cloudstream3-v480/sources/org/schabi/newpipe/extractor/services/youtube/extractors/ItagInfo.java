package org.schabi.newpipe.extractor.services.youtube.extractors;

import java.io.Serializable;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.services.youtube.ItagItem;

/* JADX INFO: loaded from: classes8.dex */
final class ItagInfo implements Serializable {

    @Nonnull
    private final String content;
    private boolean isUrl;

    @Nonnull
    private final ItagItem itagItem;

    ItagInfo(@Nonnull String str, @Nonnull ItagItem itagItem) {
        this.content = str;
        this.itagItem = itagItem;
    }

    void setIsUrl(boolean z) {
        this.isUrl = z;
    }

    @Nonnull
    String getContent() {
        return this.content;
    }

    @Nonnull
    ItagItem getItagItem() {
        return this.itagItem;
    }

    boolean getIsUrl() {
        return this.isUrl;
    }
}
