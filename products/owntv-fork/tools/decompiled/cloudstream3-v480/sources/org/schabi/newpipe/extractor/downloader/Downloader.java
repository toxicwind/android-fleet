package org.schabi.newpipe.extractor.downloader;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.exceptions.ReCaptchaException;
import org.schabi.newpipe.extractor.localization.Localization;

/* JADX INFO: loaded from: classes8.dex */
public abstract class Downloader {
    public abstract Response execute(@Nonnull Request request) throws ReCaptchaException, IOException;

    public Response get(String str) throws ReCaptchaException, IOException {
        return get(str, null, NewPipe.getPreferredLocalization());
    }

    public Response get(String str, Localization localization) throws ReCaptchaException, IOException {
        return get(str, null, localization);
    }

    public Response get(String str, @Nullable Map<String, List<String>> map) throws ReCaptchaException, IOException {
        return get(str, map, NewPipe.getPreferredLocalization());
    }

    public Response get(String str, @Nullable Map<String, List<String>> map, Localization localization) throws ReCaptchaException, IOException {
        return execute(Request.newBuilder().get(str).headers(map).localization(localization).build());
    }

    public Response head(String str) throws ReCaptchaException, IOException {
        return head(str, null);
    }

    public Response head(String str, @Nullable Map<String, List<String>> map) throws ReCaptchaException, IOException {
        return execute(Request.newBuilder().head(str).headers(map).build());
    }

    public Response post(String str, @Nullable Map<String, List<String>> map, @Nullable byte[] bArr) throws ReCaptchaException, IOException {
        return post(str, map, bArr, NewPipe.getPreferredLocalization());
    }

    public Response post(String str, @Nullable Map<String, List<String>> map, @Nullable byte[] bArr, Localization localization) throws ReCaptchaException, IOException {
        return execute(Request.newBuilder().post(str, bArr).headers(map).localization(localization).build());
    }

    public Response postWithContentType(String str, @Nullable Map<String, List<String>> map, @Nullable byte[] bArr, Localization localization, String str2) throws ReCaptchaException, IOException {
        HashMap map2 = new HashMap();
        if (map != null) {
            map2.putAll(map);
        }
        map2.put("Content-Type", Collections.singletonList(str2));
        return post(str, map2, bArr, localization);
    }

    public Response postWithContentType(String str, @Nullable Map<String, List<String>> map, @Nullable byte[] bArr, String str2) throws ReCaptchaException, IOException {
        return postWithContentType(str, map, bArr, NewPipe.getPreferredLocalization(), str2);
    }

    public Response postWithContentTypeJson(String str, @Nullable Map<String, List<String>> map, @Nullable byte[] bArr, Localization localization) throws ReCaptchaException, IOException {
        return postWithContentType(str, map, bArr, localization, "application/json");
    }

    public Response postWithContentTypeJson(String str, @Nullable Map<String, List<String>> map, @Nullable byte[] bArr) throws ReCaptchaException, IOException {
        return postWithContentTypeJson(str, map, bArr, NewPipe.getPreferredLocalization());
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
