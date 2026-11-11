package com.lagradost.nicehttp;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: compiled from: NiceResponse.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\"!\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\"!\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0007¨\u0006\b"}, d2 = {"cookies", "", "", "Lokhttp3/Response;", "getCookies", "(Lokhttp3/Response;)Ljava/util/Map;", "Lokhttp3/Request;", "(Lokhttp3/Request;)Ljava/util/Map;", "library"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class NiceResponseKt {
    public static final Map<String, String> getCookies(Response response) {
        Intrinsics.checkNotNullParameter(response, "<this>");
        return UtilsKt.getCookies(response.headers(), "set-cookie");
    }

    public static final Map<String, String> getCookies(Request request) {
        Intrinsics.checkNotNullParameter(request, "<this>");
        return UtilsKt.getCookies(request.headers(), "Cookie");
    }
}
