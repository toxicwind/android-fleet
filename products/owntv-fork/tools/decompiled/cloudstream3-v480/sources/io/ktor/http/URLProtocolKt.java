package io.ktor.http;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: URLProtocol.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\u0011\u0010\u0002\u001a\u00020\u0001*\u00020\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0011\u0010\u0004\u001a\u00020\u0001*\u00020\u0000¢\u0006\u0004\b\u0004\u0010\u0003¨\u0006\u0005"}, d2 = {"Lio/ktor/http/URLProtocol;", "", "isWebsocket", "(Lio/ktor/http/URLProtocol;)Z", "isSecure", "ktor-http"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class URLProtocolKt {
    public static final boolean isWebsocket(URLProtocol uRLProtocol) {
        Intrinsics.checkNotNullParameter(uRLProtocol, "<this>");
        return Intrinsics.areEqual(uRLProtocol.getName(), "ws") || Intrinsics.areEqual(uRLProtocol.getName(), "wss");
    }

    public static final boolean isSecure(URLProtocol uRLProtocol) {
        Intrinsics.checkNotNullParameter(uRLProtocol, "<this>");
        return Intrinsics.areEqual(uRLProtocol.getName(), "https") || Intrinsics.areEqual(uRLProtocol.getName(), "wss");
    }
}
