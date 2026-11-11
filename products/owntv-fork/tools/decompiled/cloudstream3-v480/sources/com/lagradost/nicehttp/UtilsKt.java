package com.lagradost.nicehttp;

import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.File;
import java.net.URI;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import okhttp3.CacheControl;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import org.json.JSONArray;
import org.json.JSONObject;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: compiled from: Utils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aT\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00012\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u001a8\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00022\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\b\u001a\n\u0010\u0015\u001a\u00020\u0016*\u00020\u0016\u001a\u001e\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\b*\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0002\u001a\u0018\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002H\u0000\u001a&\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u00022\u0014\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020\bH\u0000\u001a\u0018\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0000\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"mustHaveBody", "", "", "cantHaveBody", "getData", "Lokhttp3/RequestBody;", "method", "data", "", "files", "Lcom/lagradost/nicehttp/NiceFile;", "json", "", "requestBody", "responseParser", "Lcom/lagradost/nicehttp/ResponseParser;", "getHeaders", "Lokhttp3/Headers;", "headers", "referer", "cookie", "ignoreAllSSLErrors", "Lokhttp3/OkHttpClient$Builder;", "getCookies", "cookieKey", "appendUri", "uri", "appendQuery", "addParamsToUrl", ImagesContract.URL, "params", "getCache", "Lokhttp3/CacheControl;", "cacheTime", "", "cacheUnit", "Ljava/util/concurrent/TimeUnit;", "library"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class UtilsKt {
    private static final List<String> mustHaveBody = CollectionsKt.listOf((Object[]) new String[]{HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST, "PUT"});
    private static final List<String> cantHaveBody = CollectionsKt.listOf((Object[]) new String[]{coil3.network.internal.UtilsKt.HTTP_METHOD_GET, "HEAD"});

    static final boolean ignoreAllSSLErrors$lambda$1(String str, SSLSession sSLSession) {
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final RequestBody getData(String method, Map<String, String> map, List<NiceFile> list, Object obj, RequestBody requestBody, ResponseParser responseParser) {
        MultipartBody multipartBodyBuild;
        String string;
        Intrinsics.checkNotNullParameter(method, "method");
        List<String> list2 = cantHaveBody;
        String upperCase = method.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        boolean zContains = list2.contains(upperCase);
        Charset charset = null;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        Object[] objArr3 = 0;
        if (zContains) {
            return null;
        }
        if (requestBody != null) {
            return requestBody;
        }
        int i = 1;
        if (map != null && !map.isEmpty()) {
            FormBody.Builder builder = new FormBody.Builder(charset, i, objArr3 == true ? 1 : 0);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builder.addEncoded(entry.getKey(), entry.getValue());
            }
            multipartBodyBuild = builder.build();
        } else if (obj != null) {
            if (obj instanceof JSONObject) {
                string = ((JSONObject) obj).toString();
            } else if (obj instanceof JSONArray) {
                string = ((JSONArray) obj).toString();
            } else if (obj instanceof String) {
                string = (String) obj;
            } else if (obj instanceof JsonAsString) {
                string = ((JsonAsString) obj).getString();
            } else if (responseParser != null) {
                string = responseParser.writeValueAsString(obj);
            } else {
                string = obj.toString();
            }
            Intrinsics.checkNotNull(string);
            multipartBodyBuild = RequestBody.INSTANCE.create(string, MediaType.INSTANCE.parse(obj instanceof String ? RequestBodyTypes.TEXT : RequestBodyTypes.JSON));
        } else {
            List<NiceFile> list3 = list;
            if (list3 == null || list3.isEmpty()) {
                multipartBodyBuild = null;
            } else {
                MultipartBody.Builder type = new MultipartBody.Builder(null, 1, null).setType(MultipartBody.FORM);
                for (NiceFile niceFile : list) {
                    if (niceFile.getFile() != null) {
                        String name = niceFile.getName();
                        String fileName = niceFile.getFileName();
                        RequestBody.Companion companion = RequestBody.INSTANCE;
                        File file = niceFile.getFile();
                        String fileType = niceFile.getFileType();
                        type.addFormDataPart(name, fileName, companion.create(file, fileType != null ? MediaType.INSTANCE.parse(fileType) : null));
                    } else {
                        type.addFormDataPart(niceFile.getName(), niceFile.getFileName());
                    }
                }
                multipartBodyBuild = type.build();
            }
        }
        if (multipartBodyBuild != null) {
            return multipartBodyBuild;
        }
        List<String> list4 = mustHaveBody;
        String upperCase2 = method.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase2, "toUpperCase(...)");
        if (list4.contains(upperCase2)) {
            return new FormBody.Builder(objArr2 == true ? 1 : 0, i, objArr == true ? 1 : 0).build();
        }
        return null;
    }

    public static final Headers getHeaders(Map<String, String> headers, String str, Map<String, String> cookie) {
        Map mapEmptyMap;
        Map mapEmptyMap2;
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(cookie, "cookie");
        if (str == null || (mapEmptyMap = MapsKt.mapOf(TuplesKt.to("referer", str))) == null) {
            mapEmptyMap = MapsKt.emptyMap();
        }
        if (!cookie.isEmpty()) {
            mapEmptyMap2 = MapsKt.mapOf(TuplesKt.to("Cookie", CollectionsKt.joinToString$default(cookie.entrySet(), Stream.ID_UNKNOWN, null, null, 0, null, new Function1() { // from class: com.lagradost.nicehttp.UtilsKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return UtilsKt.getHeaders$lambda$1((Map.Entry) obj);
                }
            }, 30, null)));
        } else {
            mapEmptyMap2 = MapsKt.emptyMap();
        }
        Map mapPlus = MapsKt.plus(MapsKt.plus(headers, mapEmptyMap2), mapEmptyMap);
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(mapPlus.size()));
        for (Map.Entry entry : mapPlus.entrySet()) {
            String lowerCase = ((String) entry.getKey()).toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            linkedHashMap.put(lowerCase, entry.getValue());
        }
        return Headers.INSTANCE.of(linkedHashMap);
    }

    static final CharSequence getHeaders$lambda$1(Map.Entry it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return ((String) it.getKey()) + '=' + ((String) it.getValue()) + ';';
    }

    public static final OkHttpClient.Builder ignoreAllSSLErrors(OkHttpClient.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        X509TrustManager x509TrustManager = new X509TrustManager() { // from class: com.lagradost.nicehttp.UtilsKt$ignoreAllSSLErrors$naiveTrustManager$1
            @Override // javax.net.ssl.X509TrustManager
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
                Intrinsics.checkNotNullParameter(certs, "certs");
                Intrinsics.checkNotNullParameter(authType, "authType");
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
                Intrinsics.checkNotNullParameter(certs, "certs");
                Intrinsics.checkNotNullParameter(authType, "authType");
            }

            @Override // javax.net.ssl.X509TrustManager
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        SSLContext sSLContext = SSLContext.getInstance("SSL");
        sSLContext.init(null, new TrustManager[]{x509TrustManager}, new SecureRandom());
        SSLSocketFactory socketFactory = sSLContext.getSocketFactory();
        Intrinsics.checkNotNull(socketFactory);
        builder.sslSocketFactory(socketFactory, x509TrustManager);
        builder.hostnameVerifier(new HostnameVerifier() { // from class: com.lagradost.nicehttp.UtilsKt$$ExternalSyntheticLambda1
            @Override // javax.net.ssl.HostnameVerifier
            public final boolean verify(String str, SSLSession sSLSession) {
                return UtilsKt.ignoreAllSSLErrors$lambda$1(str, sSLSession);
            }
        });
        return builder;
    }

    public static final Map<String, String> getCookies(Headers headers, String cookieKey) {
        String string;
        String string2;
        Intrinsics.checkNotNullParameter(headers, "<this>");
        Intrinsics.checkNotNullParameter(cookieKey, "cookieKey");
        ArrayList arrayList = new ArrayList();
        for (Pair<? extends String, ? extends String> pair : headers) {
            if (StringsKt.equals(pair.getFirst(), cookieKey, true)) {
                arrayList.add(pair);
            }
        }
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList3.add(StringsKt.substringBefore$default((String) ((Pair) it.next()).getSecond(), ";", (String) null, 2, (Object) null));
        }
        ArrayList arrayList4 = arrayList3;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList4, 10)), 16));
        Iterator it2 = arrayList4.iterator();
        while (it2.hasNext()) {
            List listSplit$default = StringsKt.split$default((CharSequence) it2.next(), new String[]{"="}, false, 0, 6, (Object) null);
            String str = (String) CollectionsKt.getOrNull(listSplit$default, 0);
            String str2 = "";
            if (str == null || (string = StringsKt.trim((CharSequence) str).toString()) == null) {
                string = "";
            }
            String str3 = (String) CollectionsKt.getOrNull(listSplit$default, 1);
            if (str3 != null && (string2 = StringsKt.trim((CharSequence) str3).toString()) != null) {
                str2 = string2;
            }
            Pair pair2 = TuplesKt.to(string, str2);
            linkedHashMap.put(pair2.getFirst(), pair2.getSecond());
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            if (!StringsKt.isBlank((CharSequence) entry.getKey()) && !StringsKt.isBlank((CharSequence) entry.getValue())) {
                linkedHashMap2.put(entry.getKey(), entry.getValue());
            }
        }
        return linkedHashMap2;
    }

    public static final String appendUri(String uri, String appendQuery) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(appendQuery, "appendQuery");
        URI uri2 = new URI(uri);
        String scheme = uri2.getScheme();
        String authority = uri2.getAuthority();
        String path = uri2.getPath();
        if (uri2.getQuery() != null) {
            appendQuery = uri2.getQuery() + Typography.amp + appendQuery;
        }
        String string = new URI(scheme, authority, path, appendQuery, uri2.getFragment()).toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    public static final CacheControl getCache(int i, TimeUnit cacheUnit) {
        Intrinsics.checkNotNullParameter(cacheUnit, "cacheUnit");
        return new CacheControl.Builder().maxAge(i, cacheUnit).build();
    }

    public static final String addParamsToUrl(String url, Map<String, String> params) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(params, "params");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String value = entry.getValue();
            if (value != null) {
                url = appendUri(url, entry.getKey() + '=' + value);
            }
        }
        return url;
    }
}
