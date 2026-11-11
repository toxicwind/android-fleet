package com.lagradost.cloudstream3.network;

import android.content.Context;
import androidx.preference.PreferenceManager;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager;
import com.lagradost.nicehttp.Requests;
import com.lagradost.nicehttp.UtilsKt;
import java.io.File;
import java.security.Security;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Cache;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import org.conscrypt.Conscrypt;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: compiled from: RequestsHelper.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007\u001a\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001a\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007\u001a.\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\n2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\n\"\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"initClient", "", "Lcom/lagradost/nicehttp/Requests;", "context", "Landroid/content/Context;", "ignoreSSL", "", "buildDefaultClient", "Lokhttp3/OkHttpClient;", "DEFAULT_HEADERS", "", "", "getHeaders", "Lokhttp3/Headers;", "headers", "cookie", "app"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class RequestsHelperKt {
    private static final Map<String, String> DEFAULT_HEADERS = MapsKt.mapOf(TuplesKt.to("user-agent", MainAPIKt.USER_AGENT));

    public static final void initClient(Requests requests, Context context) {
        Intrinsics.checkNotNullParameter(requests, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        requests.setBaseClient(buildDefaultClient(context));
    }

    public static /* synthetic */ void initClient$default(Requests requests, Context context, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        initClient(requests, context, z);
    }

    public static final void initClient(Requests requests, Context context, boolean z) {
        Intrinsics.checkNotNullParameter(requests, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        requests.setBaseClient(buildDefaultClient(context, z));
    }

    public static final OkHttpClient buildDefaultClient(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return buildDefaultClient(context, false);
    }

    public static /* synthetic */ OkHttpClient buildDefaultClient$default(Context context, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return buildDefaultClient(context, z);
    }

    public static final OkHttpClient buildDefaultClient(Context context, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.network.RequestsHelperKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Integer.valueOf(Security.insertProviderAt(Conscrypt.newProvider(), 1));
            }
        });
        int i = PreferenceManager.getDefaultSharedPreferences(context).getInt(context.getString(R.string.dns_pref), 0);
        OkHttpClient.Builder builderFollowSslRedirects = new OkHttpClient.Builder().followRedirects(true).followSslRedirects(true);
        if (z) {
            UtilsKt.ignoreAllSSLErrors(builderFollowSslRedirects);
        }
        OkHttpClient.Builder builderCache = builderFollowSslRedirects.cache(new Cache(new File(context.getCacheDir(), "http_cache"), VideoDownloadManager.DOWNLOAD_PARTIAL_MIN_SIZE));
        switch (i) {
            case 1:
                DohProvidersKt.addGoogleDns(builderCache);
                break;
            case 2:
                DohProvidersKt.addCloudFlareDns(builderCache);
                break;
            case 4:
                DohProvidersKt.addAdGuardDns(builderCache);
                break;
            case 5:
                DohProvidersKt.addDNSWatchDns(builderCache);
                break;
            case 6:
                DohProvidersKt.addQuad9Dns(builderCache);
                break;
            case 7:
                DohProvidersKt.addDnsSbDns(builderCache);
                break;
            case 8:
                DohProvidersKt.addCanadianShieldDns(builderCache);
                break;
        }
        return builderCache.build();
    }

    public static final Headers getHeaders(Map<String, String> headers, Map<String, String> cookie) {
        Map mapEmptyMap;
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(cookie, "cookie");
        if (!cookie.isEmpty()) {
            mapEmptyMap = MapsKt.mapOf(TuplesKt.to("Cookie", CollectionsKt.joinToString$default(cookie.entrySet(), Stream.ID_UNKNOWN, null, null, 0, null, new Function1() { // from class: com.lagradost.cloudstream3.network.RequestsHelperKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return RequestsHelperKt.getHeaders$lambda$0((Map.Entry) obj);
                }
            }, 30, null)));
        } else {
            mapEmptyMap = MapsKt.emptyMap();
        }
        return Headers.INSTANCE.of(MapsKt.plus(MapsKt.plus(DEFAULT_HEADERS, headers), mapEmptyMap));
    }

    static final CharSequence getHeaders$lambda$0(Map.Entry it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return ((String) it.getKey()) + '=' + ((String) it.getValue()) + ';';
    }
}
