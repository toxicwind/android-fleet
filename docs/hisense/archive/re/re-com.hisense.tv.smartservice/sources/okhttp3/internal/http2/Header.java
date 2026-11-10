package okhttp3.internal.http2;

import com.google.firebase.installations.Utils;
import d.j;
import okhttp3.Headers;
import okhttp3.internal.Util;

/* JADX INFO: loaded from: classes2.dex */
public final class Header {
    public final int hpackSize;
    public final j name;
    public final j value;
    public static final j PSEUDO_PREFIX = j.d(Utils.APP_ID_IDENTIFICATION_SUBSTRING);
    public static final String RESPONSE_STATUS_UTF8 = ":status";
    public static final j RESPONSE_STATUS = j.d(RESPONSE_STATUS_UTF8);
    public static final String TARGET_METHOD_UTF8 = ":method";
    public static final j TARGET_METHOD = j.d(TARGET_METHOD_UTF8);
    public static final String TARGET_PATH_UTF8 = ":path";
    public static final j TARGET_PATH = j.d(TARGET_PATH_UTF8);
    public static final String TARGET_SCHEME_UTF8 = ":scheme";
    public static final j TARGET_SCHEME = j.d(TARGET_SCHEME_UTF8);
    public static final String TARGET_AUTHORITY_UTF8 = ":authority";
    public static final j TARGET_AUTHORITY = j.d(TARGET_AUTHORITY_UTF8);

    interface Listener {
        void onHeaders(Headers headers);
    }

    public Header(String str, String str2) {
        this(j.d(str), j.d(str2));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Header)) {
            return false;
        }
        Header header = (Header) obj;
        return this.name.equals(header.name) && this.value.equals(header.value);
    }

    public int hashCode() {
        return this.value.hashCode() + ((this.name.hashCode() + 527) * 31);
    }

    public String toString() {
        return Util.format("%s: %s", this.name.j(), this.value.j());
    }

    public Header(j jVar, String str) {
        this(jVar, j.d(str));
    }

    public Header(j jVar, j jVar2) {
        this.name = jVar;
        this.value = jVar2;
        this.hpackSize = jVar2.g() + jVar.g() + 32;
    }
}
