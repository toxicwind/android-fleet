package com.lagradost.cloudstream3.network;

import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.nicehttp.RequestsKt;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: compiled from: WebViewResolver.android.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0004*\u00020\u0005¨\u0006\u0006"}, d2 = {"toRequest", "Lokhttp3/Request;", "Landroid/webkit/WebResourceRequest;", "toWebResourceResponse", "Landroid/webkit/WebResourceResponse;", "Lokhttp3/Response;", "library"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class WebViewResolver_androidKt {
    public static final Request toRequest(final WebResourceRequest webResourceRequest) {
        Intrinsics.checkNotNullParameter(webResourceRequest, "<this>");
        final String string = webResourceRequest.getUrl().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return (Request) ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.network.WebViewResolver_androidKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return WebViewResolver_androidKt.toRequest$lambda$0(webResourceRequest, string);
            }
        });
    }

    static final Request toRequest$lambda$0(WebResourceRequest webResourceRequest, String str) {
        String method = webResourceRequest.getMethod();
        Intrinsics.checkNotNullExpressionValue(method, "getMethod(...)");
        Map<String, String> requestHeaders = webResourceRequest.getRequestHeaders();
        Intrinsics.checkNotNullExpressionValue(requestHeaders, "getRequestHeaders(...)");
        return RequestsKt.requestCreator(method, str, (8176 & 4) != 0 ? MapsKt.emptyMap() : requestHeaders, (8176 & 8) != 0 ? null : null, (8176 & 16) != 0 ? MapsKt.emptyMap() : null, (8176 & 32) != 0 ? MapsKt.emptyMap() : null, (8176 & 64) != 0 ? null : null, (8176 & 128) != 0 ? null : null, (8176 & 256) != 0 ? null : null, (8176 & 512) != 0 ? null : null, (8176 & 1024) != 0 ? null : null, (8176 & 2048) != 0 ? null : null, (8176 & 4096) != 0 ? null : null);
    }

    public static final WebResourceResponse toWebResourceResponse(Response response) {
        List<String> groupValues;
        String str;
        List<String> groupValues2;
        String str2;
        Intrinsics.checkNotNullParameter(response, "<this>");
        String str3 = null;
        String strHeader$default = Response.header$default(response, "Content-Type", null, 2, null);
        Regex regex = new Regex("(.*);(?:.*charset=(.*)(?:|;)|)");
        if (strHeader$default != null) {
            MatchResult matchResultFind$default = Regex.find$default(regex, strHeader$default, 0, 2, null);
            if (matchResultFind$default != null && (groupValues2 = matchResultFind$default.getGroupValues()) != null && (str2 = (String) CollectionsKt.getOrNull(groupValues2, 1)) != null) {
                String str4 = str2;
                if (StringsKt.isBlank(str4)) {
                    str4 = null;
                }
                String str5 = str4;
                if (str5 != null) {
                    strHeader$default = str5;
                }
            }
            if (matchResultFind$default != null && (groupValues = matchResultFind$default.getGroupValues()) != null && (str = (String) CollectionsKt.getOrNull(groupValues, 2)) != null) {
                String str6 = str;
                str3 = StringsKt.isBlank(str6) ? null : str6;
            }
            return new WebResourceResponse(strHeader$default, str3, response.body().byteStream());
        }
        return new WebResourceResponse("application/octet-stream", null, response.body().byteStream());
    }
}
