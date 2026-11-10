package com.ju.lib.datacommunication.network.http.core.request;

import com.hmct.cloud.sdk.utils.Constants;
import com.ju.lib.datacommunication.network.http.core.HiRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class HiFormBody extends HiRequest.Body {
    public final String mContentType;
    public final List<UrlEncodeNameValuePair> mPairs;
    public final HashMap<String, String> pairsMaps;

    private static class UrlEncodeNameValuePair {
        public final String mName;
        public final String mValue;

        public UrlEncodeNameValuePair(String str, String str2) {
            try {
                this.mName = URLEncoder.encode(str, Charset.defaultCharset().name());
                this.mValue = URLEncoder.encode(str2 == null ? "" : str2, Charset.defaultCharset().name());
            } catch (UnsupportedEncodingException unused) {
                throw new IllegalArgumentException("name  = " + str + ", value = " + str2);
            }
        }

        public String getName() {
            return this.mName;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    @Override // com.ju.lib.datacommunication.network.http.core.HiRequest.Body
    public long contentLength() {
        int size = this.mPairs.size();
        long length = 0;
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                length++;
            }
            length += (long) (this.mPairs.get(i).getName().getBytes("UTF-8").length + 1 + this.mPairs.get(i).getValue().getBytes("UTF-8").length);
        }
        if (length <= 0) {
            return -1L;
        }
        return length;
    }

    @Override // com.ju.lib.datacommunication.network.http.core.HiRequest.Body
    public String contentType() {
        return this.mContentType;
    }

    public HashMap<String, String> getContent() {
        return this.pairsMaps;
    }

    @Override // com.ju.lib.datacommunication.network.http.core.HiRequest.Body
    public void writeTo(OutputStream outputStream) throws IOException {
        int size = this.mPairs.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                outputStream.write(38);
            }
            UrlEncodeNameValuePair urlEncodeNameValuePair = this.mPairs.get(i);
            outputStream.write(urlEncodeNameValuePair.getName().getBytes("UTF-8"));
            outputStream.write(61);
            outputStream.write(urlEncodeNameValuePair.getValue().getBytes("UTF-8"));
        }
    }

    public HiFormBody(Builder builder, HashMap<String, String> map) {
        this.mPairs = new ArrayList(builder.mPairs);
        this.pairsMaps = map;
        this.mContentType = builder.mContentType;
    }

    public static final class Builder {
        public final List<UrlEncodeNameValuePair> mPairs = new ArrayList();
        public final HashMap<String, String> pairsMap = new HashMap<>();
        public String mContentType = Constants.CONTENTTYPE_URLENCODED;

        public Builder add(Map<String, String> map) {
            this.pairsMap.putAll(map);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                this.mPairs.add(new UrlEncodeNameValuePair(entry.getKey(), entry.getValue()));
            }
            return this;
        }

        public HiFormBody build() {
            return new HiFormBody(this, this.pairsMap);
        }

        public Builder contentType(String str) {
            if (str != null) {
                this.mContentType = str;
            }
            return this;
        }

        public Builder add(String str, String str2) {
            this.pairsMap.put(str, str2);
            this.mPairs.add(new UrlEncodeNameValuePair(str, str2));
            return this;
        }
    }
}
