package com.lagradost.nicehttp;

import com.google.android.gms.common.internal.ImagesContract;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Request;
import okhttp3.RequestBody;

/* JADX INFO: compiled from: Requests.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aÏ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00062\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00062\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00062\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\u0002\u0010\u0018¨\u0006\u0019"}, d2 = {"requestCreator", "Lokhttp3/Request;", "method", "", ImagesContract.URL, "headers", "", "referer", "params", "cookies", "data", "files", "", "Lcom/lagradost/nicehttp/NiceFile;", "json", "", "requestBody", "Lokhttp3/RequestBody;", "cacheTime", "", "cacheUnit", "Ljava/util/concurrent/TimeUnit;", "responseParser", "Lcom/lagradost/nicehttp/ResponseParser;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/List;Ljava/lang/Object;Lokhttp3/RequestBody;Ljava/lang/Integer;Ljava/util/concurrent/TimeUnit;Lcom/lagradost/nicehttp/ResponseParser;)Lokhttp3/Request;", "library"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class RequestsKt {
    public static final Request requestCreator(String method, String url, Map<String, String> headers, String str, Map<String, String> params, Map<String, String> cookies, Map<String, String> map, List<NiceFile> list, Object obj, RequestBody requestBody, Integer num, TimeUnit timeUnit, ResponseParser responseParser) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(cookies, "cookies");
        Request.Builder builderMethod = new Request.Builder().url(UtilsKt.addParamsToUrl(url, params)).headers(UtilsKt.getHeaders(headers, str, cookies)).method(method, UtilsKt.getData(method, map, list, obj, requestBody, responseParser));
        if (num != null && timeUnit != null) {
            builderMethod.cacheControl(UtilsKt.getCache(num.intValue(), timeUnit));
        }
        return builderMethod.build();
    }
}
