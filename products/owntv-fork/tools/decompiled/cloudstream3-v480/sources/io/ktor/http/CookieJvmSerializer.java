package io.ktor.http;

import io.ktor.utils.io.JvmSerializer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Cookie.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0005\bÀ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\t\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lio/ktor/http/CookieJvmSerializer;", "Lio/ktor/utils/io/JvmSerializer;", "Lio/ktor/http/Cookie;", "<init>", "()V", ES6Iterator.VALUE_PROPERTY, "", "jvmSerialize", "(Lio/ktor/http/Cookie;)[B", "jvmDeserialize", "([B)Lio/ktor/http/Cookie;", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CookieJvmSerializer implements JvmSerializer<Cookie> {
    public static final CookieJvmSerializer INSTANCE = new CookieJvmSerializer();

    private CookieJvmSerializer() {
    }

    @Override // io.ktor.utils.io.JvmSerializer
    public byte[] jvmSerialize(Cookie value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return StringsKt.encodeToByteArray(CookieKt.renderSetCookieHeader(value));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.ktor.utils.io.JvmSerializer
    public Cookie jvmDeserialize(byte[] value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return CookieKt.parseServerSetCookieHeader(StringsKt.decodeToString(value));
    }
}
