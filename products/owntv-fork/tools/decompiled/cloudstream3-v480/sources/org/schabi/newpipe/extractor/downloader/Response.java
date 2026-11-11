package org.schabi.newpipe.extractor.downloader;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes8.dex */
public class Response {
    private final String latestUrl;
    private final String responseBody;
    private final int responseCode;
    private final Map<String, List<String>> responseHeaders;
    private final String responseMessage;

    public Response(int i, String str, @Nullable Map<String, List<String>> map, @Nullable String str2, @Nullable String str3) {
        this.responseCode = i;
        this.responseMessage = str;
        this.responseHeaders = map == null ? Collections.EMPTY_MAP : map;
        this.responseBody = str2 == null ? "" : str2;
        this.latestUrl = str3;
    }

    public int responseCode() {
        return this.responseCode;
    }

    public String responseMessage() {
        return this.responseMessage;
    }

    public Map<String, List<String>> responseHeaders() {
        return this.responseHeaders;
    }

    @Nonnull
    public String responseBody() {
        return this.responseBody;
    }

    @Nonnull
    public String latestUrl() {
        return this.latestUrl;
    }

    @Nullable
    public String getHeader(String str) {
        for (Map.Entry<String, List<String>> entry : this.responseHeaders.entrySet()) {
            String key = entry.getKey();
            if (key != null && key.equalsIgnoreCase(str) && !entry.getValue().isEmpty()) {
                return entry.getValue().get(0);
            }
        }
        return null;
    }
}
