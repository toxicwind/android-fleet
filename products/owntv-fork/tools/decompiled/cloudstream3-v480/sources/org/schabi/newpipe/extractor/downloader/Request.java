package org.schabi.newpipe.extractor.downloader;

import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import coil3.network.internal.UtilsKt;
import j$.util.DesugarCollections;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.localization.Localization;

/* JADX INFO: loaded from: classes8.dex */
public class Request {

    @Nullable
    private final byte[] dataToSend;
    private final Map<String, List<String>> headers;
    private final String httpMethod;

    @Nullable
    private final Localization localization;
    private final String url;

    public Request(String str, String str2, @Nullable Map<String, List<String>> map, @Nullable byte[] bArr, @Nullable Localization localization, boolean z) {
        this.httpMethod = (String) Objects.requireNonNull(str, "Request's httpMethod is null");
        this.url = (String) Objects.requireNonNull(str2, "Request's url is null");
        this.dataToSend = bArr;
        this.localization = localization;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (map != null) {
            linkedHashMap.putAll(map);
        }
        if (z && localization != null) {
            linkedHashMap.putAll(getHeadersFromLocalization(localization));
        }
        this.headers = DesugarCollections.unmodifiableMap(linkedHashMap);
    }

    private Request(Builder builder) {
        this(builder.httpMethod, builder.url, builder.headers, builder.dataToSend, builder.localization, builder.automaticLocalizationHeader);
    }

    public String httpMethod() {
        return this.httpMethod;
    }

    public String url() {
        return this.url;
    }

    public Map<String, List<String>> headers() {
        return this.headers;
    }

    @Nullable
    public byte[] dataToSend() {
        return this.dataToSend;
    }

    @Nullable
    public Localization localization() {
        return this.localization;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private byte[] dataToSend;
        private String httpMethod;
        private Localization localization;
        private String url;
        private final Map<String, List<String>> headers = new LinkedHashMap();
        private boolean automaticLocalizationHeader = true;

        public Builder httpMethod(String str) {
            this.httpMethod = str;
            return this;
        }

        public Builder url(String str) {
            this.url = str;
            return this;
        }

        public Builder headers(@Nullable Map<String, List<String>> map) {
            this.headers.clear();
            if (map != null) {
                this.headers.putAll(map);
            }
            return this;
        }

        public Builder dataToSend(byte[] bArr) {
            this.dataToSend = bArr;
            return this;
        }

        public Builder localization(Localization localization) {
            this.localization = localization;
            return this;
        }

        public Builder automaticLocalizationHeader(boolean z) {
            this.automaticLocalizationHeader = z;
            return this;
        }

        public Request build() {
            return new Request(this);
        }

        public Builder get(String str) {
            this.httpMethod = UtilsKt.HTTP_METHOD_GET;
            this.url = str;
            return this;
        }

        public Builder head(String str) {
            this.httpMethod = "HEAD";
            this.url = str;
            return this;
        }

        public Builder post(String str, @Nullable byte[] bArr) {
            this.httpMethod = HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST;
            this.url = str;
            this.dataToSend = bArr;
            return this;
        }

        public Builder setHeaders(String str, List<String> list) {
            this.headers.remove(str);
            this.headers.put(str, list);
            return this;
        }

        public Builder addHeaders(String str, List<String> list) {
            List<String> arrayList = this.headers.get(str);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
            }
            arrayList.addAll(list);
            this.headers.put(str, list);
            return this;
        }

        public Builder setHeader(String str, String str2) {
            return setHeaders(str, Collections.singletonList(str2));
        }

        public Builder addHeader(String str, String str2) {
            return addHeaders(str, Collections.singletonList(str2));
        }
    }

    @Nonnull
    public static Map<String, List<String>> getHeadersFromLocalization(@Nullable Localization localization) {
        if (localization == null) {
            return Collections.EMPTY_MAP;
        }
        String languageCode = localization.getLanguageCode();
        if (!localization.getCountryCode().isEmpty()) {
            languageCode = localization.getLocalizationCode() + ", " + languageCode + ";q=0.9";
        }
        return Collections.singletonMap("Accept-Language", Collections.singletonList(languageCode));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            Request request = (Request) obj;
            if (this.httpMethod.equals(request.httpMethod) && this.url.equals(request.url) && this.headers.equals(request.headers) && Arrays.equals(this.dataToSend, request.dataToSend) && Objects.equals(this.localization, request.localization)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (Objects.hash(this.httpMethod, this.url, this.headers, this.localization) * 31) + Arrays.hashCode(this.dataToSend);
    }
}
