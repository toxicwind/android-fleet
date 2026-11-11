package org.schabi.newpipe.extractor.services.youtube;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes8.dex */
public interface PoTokenProvider {
    @Nullable
    PoTokenResult getAndroidClientPoToken(String str);

    @Nullable
    PoTokenResult getIosClientPoToken(String str);

    @Nullable
    PoTokenResult getWebClientPoToken(String str);

    @Nullable
    PoTokenResult getWebEmbedClientPoToken(String str);
}
