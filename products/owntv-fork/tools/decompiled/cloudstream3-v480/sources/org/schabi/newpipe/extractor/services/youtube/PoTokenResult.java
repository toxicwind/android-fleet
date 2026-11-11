package org.schabi.newpipe.extractor.services.youtube;

import j$.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes8.dex */
public final class PoTokenResult {

    @Nonnull
    public final String playerRequestPoToken;

    @Nullable
    public final String streamingDataPoToken;

    @Nonnull
    public final String visitorData;

    public PoTokenResult(@Nonnull String str, @Nonnull String str2, @Nullable String str3) {
        this.visitorData = (String) Objects.requireNonNull(str);
        this.playerRequestPoToken = (String) Objects.requireNonNull(str2);
        this.streamingDataPoToken = str3;
    }
}
