package com.lagradost.nicehttp;

import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;
import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* JADX INFO: compiled from: Requests.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\b\u0016\u0018\u0000 U2\u00020\u0001:\u0001UB\u0089\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u0012\u0010\u0013Jø\u0001\u00104\u001a\u0002052\u0006\u00106\u001a\u00020\u00062\u0006\u00107\u001a\u00020\u00062\u0014\b\u0002\u00108\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u00062\u0014\b\u0002\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\u0014\b\u0002\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\u0016\b\u0002\u0010<\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0010\b\u0002\u0010=\u001a\n\u0012\u0004\u0012\u00020?\u0018\u00010>2\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010A\u001a\u0004\u0018\u00010B2\b\b\u0002\u0010C\u001a\u00020D2\b\b\u0002\u0010E\u001a\u00020\u000b2\b\b\u0002\u0010F\u001a\u00020\r2\b\b\u0002\u0010G\u001a\u00020\u000f2\n\b\u0002\u0010H\u001a\u0004\u0018\u00010I2\b\b\u0002\u0010J\u001a\u00020D2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096@¢\u0006\u0002\u0010KJ®\u0001\u0010L\u001a\u0002052\u0006\u00107\u001a\u00020\u00062\u0014\b\u0002\u00108\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u00062\u0014\b\u0002\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\u0014\b\u0002\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010C\u001a\u00020D2\b\b\u0002\u0010E\u001a\u00020\u000b2\b\b\u0002\u0010F\u001a\u00020\r2\b\b\u0002\u0010G\u001a\u00020\u000f2\n\b\u0002\u0010H\u001a\u0004\u0018\u00010I2\b\b\u0002\u0010J\u001a\u00020D2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0086@¢\u0006\u0002\u0010MJð\u0001\u0010N\u001a\u0002052\u0006\u00107\u001a\u00020\u00062\u0014\b\u0002\u00108\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u00062\u0014\b\u0002\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\u0014\b\u0002\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\u0016\b\u0002\u0010<\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0010\b\u0002\u0010=\u001a\n\u0012\u0004\u0012\u00020?\u0018\u00010>2\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010A\u001a\u0004\u0018\u00010B2\b\b\u0002\u0010C\u001a\u00020D2\b\b\u0002\u0010E\u001a\u00020\u000b2\b\b\u0002\u0010F\u001a\u00020\r2\b\b\u0002\u0010G\u001a\u00020\u000f2\n\b\u0002\u0010H\u001a\u0004\u0018\u00010I2\b\b\u0002\u0010J\u001a\u00020D2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0086@¢\u0006\u0002\u0010OJð\u0001\u0010P\u001a\u0002052\u0006\u00107\u001a\u00020\u00062\u0014\b\u0002\u00108\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u00062\u0014\b\u0002\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\u0014\b\u0002\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\u0016\b\u0002\u0010<\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0010\b\u0002\u0010=\u001a\n\u0012\u0004\u0012\u00020?\u0018\u00010>2\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010A\u001a\u0004\u0018\u00010B2\b\b\u0002\u0010C\u001a\u00020D2\b\b\u0002\u0010E\u001a\u00020\u000b2\b\b\u0002\u0010F\u001a\u00020\r2\b\b\u0002\u0010G\u001a\u00020\u000f2\n\b\u0002\u0010H\u001a\u0004\u0018\u00010I2\b\b\u0002\u0010J\u001a\u00020D2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0086@¢\u0006\u0002\u0010OJð\u0001\u0010Q\u001a\u0002052\u0006\u00107\u001a\u00020\u00062\u0014\b\u0002\u00108\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u00062\u0014\b\u0002\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\u0014\b\u0002\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\u0016\b\u0002\u0010<\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0010\b\u0002\u0010=\u001a\n\u0012\u0004\u0012\u00020?\u0018\u00010>2\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010A\u001a\u0004\u0018\u00010B2\b\b\u0002\u0010C\u001a\u00020D2\b\b\u0002\u0010E\u001a\u00020\u000b2\b\b\u0002\u0010F\u001a\u00020\r2\b\b\u0002\u0010G\u001a\u00020\u000f2\n\b\u0002\u0010H\u001a\u0004\u0018\u00010I2\b\b\u0002\u0010J\u001a\u00020D2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0086@¢\u0006\u0002\u0010OJ®\u0001\u0010R\u001a\u0002052\u0006\u00107\u001a\u00020\u00062\u0014\b\u0002\u00108\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u00062\u0014\b\u0002\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\u0014\b\u0002\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010C\u001a\u00020D2\b\b\u0002\u0010E\u001a\u00020\u000b2\b\b\u0002\u0010F\u001a\u00020\r2\b\b\u0002\u0010G\u001a\u00020\u000f2\n\b\u0002\u0010H\u001a\u0004\u0018\u00010I2\b\b\u0002\u0010J\u001a\u00020D2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0086@¢\u0006\u0002\u0010MJð\u0001\u0010S\u001a\u0002052\u0006\u00107\u001a\u00020\u00062\u0014\b\u0002\u00108\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u00062\u0014\b\u0002\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\u0014\b\u0002\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\u0016\b\u0002\u0010<\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0010\b\u0002\u0010=\u001a\n\u0012\u0004\u0012\u00020?\u0018\u00010>2\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010A\u001a\u0004\u0018\u00010B2\b\b\u0002\u0010C\u001a\u00020D2\b\b\u0002\u0010E\u001a\u00020\u000b2\b\b\u0002\u0010F\u001a\u00020\r2\b\b\u0002\u0010G\u001a\u00020\u000f2\n\b\u0002\u0010H\u001a\u0004\u0018\u00010I2\b\b\u0002\u0010J\u001a\u00020D2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0086@¢\u0006\u0002\u0010OJð\u0001\u0010T\u001a\u0002052\u0006\u00107\u001a\u00020\u00062\u0014\b\u0002\u00108\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u00062\u0014\b\u0002\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\u0014\b\u0002\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\u0016\b\u0002\u0010<\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0010\b\u0002\u0010=\u001a\n\u0012\u0004\u0012\u00020?\u0018\u00010>2\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010A\u001a\u0004\u0018\u00010B2\b\b\u0002\u0010C\u001a\u00020D2\b\b\u0002\u0010E\u001a\u00020\u000b2\b\b\u0002\u0010F\u001a\u00020\r2\b\b\u0002\u0010G\u001a\u00020\u000f2\n\b\u0002\u0010H\u001a\u0004\u0018\u00010I2\b\b\u0002\u0010J\u001a\u00020D2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0086@¢\u0006\u0002\u0010OR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R&\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR&\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0019\"\u0004\b!\u0010\u001bR&\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0019\"\u0004\b#\u0010\u001bR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103¨\u0006V"}, d2 = {"Lcom/lagradost/nicehttp/Requests;", "", "baseClient", "Lokhttp3/OkHttpClient;", "defaultHeaders", "", "", "defaultReferer", "defaultData", "defaultCookies", "defaultCacheTime", "", "defaultCacheTimeUnit", "Ljava/util/concurrent/TimeUnit;", "defaultTimeOut", "", "responseParser", "Lcom/lagradost/nicehttp/ResponseParser;", "<init>", "(Lokhttp3/OkHttpClient;Ljava/util/Map;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;ILjava/util/concurrent/TimeUnit;JLcom/lagradost/nicehttp/ResponseParser;)V", "getBaseClient", "()Lokhttp3/OkHttpClient;", "setBaseClient", "(Lokhttp3/OkHttpClient;)V", "getDefaultHeaders", "()Ljava/util/Map;", "setDefaultHeaders", "(Ljava/util/Map;)V", "getDefaultReferer", "()Ljava/lang/String;", "setDefaultReferer", "(Ljava/lang/String;)V", "getDefaultData", "setDefaultData", "getDefaultCookies", "setDefaultCookies", "getDefaultCacheTime", "()I", "setDefaultCacheTime", "(I)V", "getDefaultCacheTimeUnit", "()Ljava/util/concurrent/TimeUnit;", "setDefaultCacheTimeUnit", "(Ljava/util/concurrent/TimeUnit;)V", "getDefaultTimeOut", "()J", "setDefaultTimeOut", "(J)V", "getResponseParser", "()Lcom/lagradost/nicehttp/ResponseParser;", "setResponseParser", "(Lcom/lagradost/nicehttp/ResponseParser;)V", "custom", "Lcom/lagradost/nicehttp/NiceResponse;", "method", ImagesContract.URL, "headers", "referer", "params", "cookies", "data", "files", "", "Lcom/lagradost/nicehttp/NiceFile;", "json", "requestBody", "Lokhttp3/RequestBody;", "allowRedirects", "", "cacheTime", "cacheUnit", "timeout", "interceptor", "Lokhttp3/Interceptor;", "verify", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/List;Ljava/lang/Object;Lokhttp3/RequestBody;ZILjava/util/concurrent/TimeUnit;JLokhttp3/Interceptor;ZLcom/lagradost/nicehttp/ResponseParser;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "get", "(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;ZILjava/util/concurrent/TimeUnit;JLokhttp3/Interceptor;ZLcom/lagradost/nicehttp/ResponseParser;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "post", "(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/List;Ljava/lang/Object;Lokhttp3/RequestBody;ZILjava/util/concurrent/TimeUnit;JLokhttp3/Interceptor;ZLcom/lagradost/nicehttp/ResponseParser;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "put", "delete", TtmlNode.TAG_HEAD, "patch", "options", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class Requests {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private OkHttpClient baseClient;
    private int defaultCacheTime;
    private TimeUnit defaultCacheTimeUnit;
    private Map<String, String> defaultCookies;
    private Map<String, String> defaultData;
    private Map<String, String> defaultHeaders;
    private String defaultReferer;
    private long defaultTimeOut;
    private ResponseParser responseParser;

    /* JADX INFO: renamed from: com.lagradost.nicehttp.Requests$custom$1, reason: invalid class name */
    /* JADX INFO: compiled from: Requests.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.nicehttp.Requests", f = "Requests.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {353}, m = "custom$suspendImpl", n = {"$this", "method", ImagesContract.URL, "headers", "referer", "params", "cookies", "data", "files", "json", "requestBody", "cacheUnit", "interceptor", "responseParser", "client", "request", "this_$iv", "$this$await$iv", "allowRedirects", "cacheTime", "timeout", "verify", "$i$f$await", "$i$f$suspendCancellableCoroutine"}, nl = {367}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16", "L$17", "Z$0", "I$0", "J$0", "Z$1", "I$1", "I$2"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        long J$0;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$15;
        Object L$16;
        Object L$17;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        boolean Z$0;
        boolean Z$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Requests.custom$suspendImpl(Requests.this, null, null, null, null, null, null, null, null, null, null, false, 0, null, 0L, null, false, null, this);
        }
    }

    public Requests() {
        this(null, null, null, null, null, 0, null, 0L, null, 511, null);
    }

    public Object custom(String str, String str2, Map<String, String> map, String str3, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, List<NiceFile> list, Object obj, RequestBody requestBody, boolean z, int i, TimeUnit timeUnit, long j, Interceptor interceptor, boolean z2, ResponseParser responseParser, Continuation<? super NiceResponse> continuation) {
        return custom$suspendImpl(this, str, str2, map, str3, map2, map3, map4, list, obj, requestBody, z, i, timeUnit, j, interceptor, z2, responseParser, continuation);
    }

    public Requests(OkHttpClient baseClient, Map<String, String> defaultHeaders, String str, Map<String, String> defaultData, Map<String, String> defaultCookies, int i, TimeUnit defaultCacheTimeUnit, long j, ResponseParser responseParser) {
        Intrinsics.checkNotNullParameter(baseClient, "baseClient");
        Intrinsics.checkNotNullParameter(defaultHeaders, "defaultHeaders");
        Intrinsics.checkNotNullParameter(defaultData, "defaultData");
        Intrinsics.checkNotNullParameter(defaultCookies, "defaultCookies");
        Intrinsics.checkNotNullParameter(defaultCacheTimeUnit, "defaultCacheTimeUnit");
        this.baseClient = baseClient;
        this.defaultHeaders = defaultHeaders;
        this.defaultReferer = str;
        this.defaultData = defaultData;
        this.defaultCookies = defaultCookies;
        this.defaultCacheTime = i;
        this.defaultCacheTimeUnit = defaultCacheTimeUnit;
        this.defaultTimeOut = j;
        this.responseParser = responseParser;
    }

    public /* synthetic */ Requests(OkHttpClient okHttpClient, Map map, String str, Map map2, Map map3, int i, TimeUnit timeUnit, long j, ResponseParser responseParser, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? new OkHttpClient() : okHttpClient, (i2 & 2) != 0 ? MapsKt.mapOf(TuplesKt.to("user-agent", "NiceHttp")) : map, (i2 & 4) != 0 ? null : str, (i2 & 8) != 0 ? MapsKt.emptyMap() : map2, (i2 & 16) != 0 ? MapsKt.emptyMap() : map3, (i2 & 32) != 0 ? 0 : i, (i2 & 64) != 0 ? TimeUnit.MINUTES : timeUnit, (i2 & 128) != 0 ? 0L : j, (i2 & 256) != 0 ? null : responseParser);
    }

    public final OkHttpClient getBaseClient() {
        return this.baseClient;
    }

    public final void setBaseClient(OkHttpClient okHttpClient) {
        Intrinsics.checkNotNullParameter(okHttpClient, "<set-?>");
        this.baseClient = okHttpClient;
    }

    public final Map<String, String> getDefaultHeaders() {
        return this.defaultHeaders;
    }

    public final void setDefaultHeaders(Map<String, String> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.defaultHeaders = map;
    }

    public final String getDefaultReferer() {
        return this.defaultReferer;
    }

    public final void setDefaultReferer(String str) {
        this.defaultReferer = str;
    }

    public final Map<String, String> getDefaultData() {
        return this.defaultData;
    }

    public final void setDefaultData(Map<String, String> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.defaultData = map;
    }

    public final Map<String, String> getDefaultCookies() {
        return this.defaultCookies;
    }

    public final void setDefaultCookies(Map<String, String> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.defaultCookies = map;
    }

    public final int getDefaultCacheTime() {
        return this.defaultCacheTime;
    }

    public final void setDefaultCacheTime(int i) {
        this.defaultCacheTime = i;
    }

    public final TimeUnit getDefaultCacheTimeUnit() {
        return this.defaultCacheTimeUnit;
    }

    public final void setDefaultCacheTimeUnit(TimeUnit timeUnit) {
        Intrinsics.checkNotNullParameter(timeUnit, "<set-?>");
        this.defaultCacheTimeUnit = timeUnit;
    }

    public final long getDefaultTimeOut() {
        return this.defaultTimeOut;
    }

    public final void setDefaultTimeOut(long j) {
        this.defaultTimeOut = j;
    }

    public final ResponseParser getResponseParser() {
        return this.responseParser;
    }

    public final void setResponseParser(ResponseParser responseParser) {
        this.responseParser = responseParser;
    }

    /* JADX INFO: compiled from: Requests.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0086H¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/lagradost/nicehttp/Requests$Companion;", "", "<init>", "()V", "await", "Lokhttp3/Response;", "Lokhttp3/Call;", "(Lokhttp3/Call;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final Object await$$forInline(Call call, Continuation<? super Response> continuation) {
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
            cancellableContinuationImpl.initCancellability();
            CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
            ContinuationCallback continuationCallback = new ContinuationCallback(call, cancellableContinuationImpl2);
            call.enqueue(continuationCallback);
            cancellableContinuationImpl2.invokeOnCancellation(continuationCallback);
            Unit unit = Unit.INSTANCE;
            Object result = cancellableContinuationImpl.getResult();
            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return result;
        }

        public final Object await(Call call, Continuation<? super Response> continuation) {
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
            cancellableContinuationImpl.initCancellability();
            CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
            ContinuationCallback continuationCallback = new ContinuationCallback(call, cancellableContinuationImpl2);
            call.enqueue(continuationCallback);
            cancellableContinuationImpl2.invokeOnCancellation(continuationCallback);
            Object result = cancellableContinuationImpl.getResult();
            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return result;
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0020  */
    static /* synthetic */ Object custom$suspendImpl(Requests requests, String str, String str2, Map<String, String> map, String str3, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, List<NiceFile> list, Object obj, RequestBody requestBody, boolean z, int i, TimeUnit timeUnit, long j, Interceptor interceptor, boolean z2, ResponseParser responseParser, Continuation<? super NiceResponse> continuation) {
        AnonymousClass1 anonymousClass1;
        ResponseParser responseParser2;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = requests.new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = requests.new AnonymousClass1(continuation);
        }
        Object result = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(result);
            OkHttpClient.Builder builderCallTimeout = requests.baseClient.newBuilder().followRedirects(z).followSslRedirects(z).addNetworkInterceptor(new CacheInterceptor()).callTimeout(j, TimeUnit.SECONDS);
            if (j > 0) {
                builderCallTimeout.connectTimeout(j, TimeUnit.SECONDS).writeTimeout(j, TimeUnit.SECONDS).readTimeout(j, TimeUnit.SECONDS);
            }
            if (!z2) {
                UtilsKt.ignoreAllSSLErrors(builderCallTimeout);
            }
            if (interceptor != null) {
                builderCallTimeout.addInterceptor(interceptor);
            }
            Request requestRequestCreator = RequestsKt.requestCreator(str, str2, MapsKt.plus(requests.defaultHeaders, map), str3 == null ? requests.defaultReferer : str3, map2, MapsKt.plus(requests.defaultCookies, map3), map4, list, obj, requestBody, Boxing.boxInt(i), timeUnit, responseParser);
            Companion companion = INSTANCE;
            Call callNewCall = builderCallTimeout.build().newCall(requestRequestCreator);
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(requests);
            anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(str);
            anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(str2);
            anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(map);
            anonymousClass1.L$4 = SpillingKt.nullOutSpilledVariable(str3);
            anonymousClass1.L$5 = SpillingKt.nullOutSpilledVariable(map2);
            anonymousClass1.L$6 = SpillingKt.nullOutSpilledVariable(map3);
            anonymousClass1.L$7 = SpillingKt.nullOutSpilledVariable(map4);
            anonymousClass1.L$8 = SpillingKt.nullOutSpilledVariable(list);
            anonymousClass1.L$9 = SpillingKt.nullOutSpilledVariable(obj);
            anonymousClass1.L$10 = SpillingKt.nullOutSpilledVariable(requestBody);
            anonymousClass1.L$11 = SpillingKt.nullOutSpilledVariable(timeUnit);
            anonymousClass1.L$12 = SpillingKt.nullOutSpilledVariable(interceptor);
            responseParser2 = responseParser;
            anonymousClass1.L$13 = responseParser2;
            anonymousClass1.L$14 = SpillingKt.nullOutSpilledVariable(builderCallTimeout);
            anonymousClass1.L$15 = SpillingKt.nullOutSpilledVariable(requestRequestCreator);
            anonymousClass1.L$16 = SpillingKt.nullOutSpilledVariable(companion);
            anonymousClass1.L$17 = callNewCall;
            anonymousClass1.Z$0 = z;
            anonymousClass1.I$0 = i;
            anonymousClass1.J$0 = j;
            anonymousClass1.Z$1 = z2;
            anonymousClass1.I$1 = 0;
            anonymousClass1.I$2 = 0;
            anonymousClass1.label = 1;
            AnonymousClass1 anonymousClass2 = anonymousClass1;
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(anonymousClass2), 1);
            cancellableContinuationImpl.initCancellability();
            CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
            ContinuationCallback continuationCallback = new ContinuationCallback(callNewCall, cancellableContinuationImpl2);
            callNewCall.enqueue(continuationCallback);
            cancellableContinuationImpl2.invokeOnCancellation(continuationCallback);
            result = cancellableContinuationImpl.getResult();
            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(anonymousClass2);
            }
            if (result == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i3 = anonymousClass1.I$2;
            int i4 = anonymousClass1.I$1;
            boolean z3 = anonymousClass1.Z$1;
            long j2 = anonymousClass1.J$0;
            int i5 = anonymousClass1.I$0;
            boolean z4 = anonymousClass1.Z$0;
            responseParser2 = (ResponseParser) anonymousClass1.L$13;
            Object obj2 = anonymousClass1.L$9;
            ResultKt.throwOnFailure(result);
        }
        return new NiceResponse((Response) result, responseParser2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object custom$default(Requests requests, String str, String str2, Map map, String str3, Map map2, Map map3, Map map4, List list, Object obj, RequestBody requestBody, boolean z, int i, TimeUnit timeUnit, long j, Interceptor interceptor, boolean z2, ResponseParser responseParser, Continuation continuation, int i2, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: custom");
        }
        return requests.custom(str, str2, (i2 & 4) != 0 ? MapsKt.emptyMap() : map, (i2 & 8) != 0 ? null : str3, (i2 & 16) != 0 ? MapsKt.emptyMap() : map2, (i2 & 32) != 0 ? MapsKt.emptyMap() : map3, (i2 & 64) != 0 ? requests.defaultData : map4, (i2 & 128) != 0 ? null : list, (i2 & 256) != 0 ? null : obj, (i2 & 512) != 0 ? null : requestBody, (i2 & 1024) != 0 ? true : z, (i2 & 2048) != 0 ? requests.defaultCacheTime : i, (i2 & 4096) != 0 ? requests.defaultCacheTimeUnit : timeUnit, (i2 & 8192) != 0 ? requests.defaultTimeOut : j, (i2 & 16384) != 0 ? null : interceptor, (32768 & i2) != 0 ? true : z2, (i2 & 65536) != 0 ? requests.responseParser : responseParser, continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object get$default(Requests requests, String str, Map map, String str2, Map map2, Map map3, boolean z, int i, TimeUnit timeUnit, long j, Interceptor interceptor, boolean z2, ResponseParser responseParser, Continuation continuation, int i2, Object obj) {
        if (obj == null) {
            return requests.get(str, (i2 & 2) != 0 ? MapsKt.emptyMap() : map, (i2 & 4) != 0 ? null : str2, (i2 & 8) != 0 ? MapsKt.emptyMap() : map2, (i2 & 16) != 0 ? MapsKt.emptyMap() : map3, (i2 & 32) != 0 ? true : z, (i2 & 64) != 0 ? requests.defaultCacheTime : i, (i2 & 128) != 0 ? requests.defaultCacheTimeUnit : timeUnit, (i2 & 256) != 0 ? requests.defaultTimeOut : j, (i2 & 512) == 0 ? interceptor : null, (i2 & 1024) == 0 ? z2 : true, (i2 & 2048) != 0 ? requests.responseParser : responseParser, continuation);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: get");
    }

    public final Object get(String str, Map<String, String> map, String str2, Map<String, String> map2, Map<String, String> map3, boolean z, int i, TimeUnit timeUnit, long j, Interceptor interceptor, boolean z2, ResponseParser responseParser, Continuation<? super NiceResponse> continuation) {
        return custom(coil3.network.internal.UtilsKt.HTTP_METHOD_GET, str, map, str2, map2, map3, null, null, null, null, z, i, timeUnit, j, interceptor, z2, responseParser, continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object post$default(Requests requests, String str, Map map, String str2, Map map2, Map map3, Map map4, List list, Object obj, RequestBody requestBody, boolean z, int i, TimeUnit timeUnit, long j, Interceptor interceptor, boolean z2, ResponseParser responseParser, Continuation continuation, int i2, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: post");
        }
        Map mapEmptyMap = (i2 & 2) != 0 ? MapsKt.emptyMap() : map;
        String str3 = (i2 & 4) != 0 ? null : str2;
        Map mapEmptyMap2 = (i2 & 8) != 0 ? MapsKt.emptyMap() : map2;
        return requests.post(str, mapEmptyMap, str3, mapEmptyMap2, (i2 & 16) != 0 ? MapsKt.emptyMap() : map3, (i2 & 32) != 0 ? requests.defaultData : map4, (i2 & 64) != 0 ? null : list, (i2 & 128) != 0 ? null : obj, (i2 & 256) != 0 ? null : requestBody, (i2 & 512) != 0 ? true : z, (i2 & 1024) != 0 ? requests.defaultCacheTime : i, (i2 & 2048) != 0 ? requests.defaultCacheTimeUnit : timeUnit, (i2 & 4096) != 0 ? requests.defaultTimeOut : j, (i2 & 8192) != 0 ? null : interceptor, (i2 & 16384) != 0 ? true : z2, (i2 & 32768) != 0 ? requests.responseParser : responseParser, continuation);
    }

    public final Object post(String str, Map<String, String> map, String str2, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, List<NiceFile> list, Object obj, RequestBody requestBody, boolean z, int i, TimeUnit timeUnit, long j, Interceptor interceptor, boolean z2, ResponseParser responseParser, Continuation<? super NiceResponse> continuation) {
        return custom(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST, str, map, str2, map2, map3, map4, list, obj, requestBody, z, i, timeUnit, j, interceptor, z2, responseParser, continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object put$default(Requests requests, String str, Map map, String str2, Map map2, Map map3, Map map4, List list, Object obj, RequestBody requestBody, boolean z, int i, TimeUnit timeUnit, long j, Interceptor interceptor, boolean z2, ResponseParser responseParser, Continuation continuation, int i2, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: put");
        }
        Map mapEmptyMap = (i2 & 2) != 0 ? MapsKt.emptyMap() : map;
        String str3 = (i2 & 4) != 0 ? null : str2;
        Map mapEmptyMap2 = (i2 & 8) != 0 ? MapsKt.emptyMap() : map2;
        return requests.put(str, mapEmptyMap, str3, mapEmptyMap2, (i2 & 16) != 0 ? MapsKt.emptyMap() : map3, (i2 & 32) != 0 ? requests.defaultData : map4, (i2 & 64) != 0 ? null : list, (i2 & 128) != 0 ? null : obj, (i2 & 256) != 0 ? null : requestBody, (i2 & 512) != 0 ? true : z, (i2 & 1024) != 0 ? requests.defaultCacheTime : i, (i2 & 2048) != 0 ? requests.defaultCacheTimeUnit : timeUnit, (i2 & 4096) != 0 ? requests.defaultTimeOut : j, (i2 & 8192) != 0 ? null : interceptor, (i2 & 16384) != 0 ? true : z2, (i2 & 32768) != 0 ? requests.responseParser : responseParser, continuation);
    }

    public final Object put(String str, Map<String, String> map, String str2, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, List<NiceFile> list, Object obj, RequestBody requestBody, boolean z, int i, TimeUnit timeUnit, long j, Interceptor interceptor, boolean z2, ResponseParser responseParser, Continuation<? super NiceResponse> continuation) {
        return custom("PUT", str, map, str2, map2, map3, map4, list, obj, requestBody, z, i, timeUnit, j, interceptor, z2, responseParser, continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object delete$default(Requests requests, String str, Map map, String str2, Map map2, Map map3, Map map4, List list, Object obj, RequestBody requestBody, boolean z, int i, TimeUnit timeUnit, long j, Interceptor interceptor, boolean z2, ResponseParser responseParser, Continuation continuation, int i2, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: delete");
        }
        Map mapEmptyMap = (i2 & 2) != 0 ? MapsKt.emptyMap() : map;
        String str3 = (i2 & 4) != 0 ? null : str2;
        Map mapEmptyMap2 = (i2 & 8) != 0 ? MapsKt.emptyMap() : map2;
        return requests.delete(str, mapEmptyMap, str3, mapEmptyMap2, (i2 & 16) != 0 ? MapsKt.emptyMap() : map3, (i2 & 32) != 0 ? requests.defaultData : map4, (i2 & 64) != 0 ? null : list, (i2 & 128) != 0 ? null : obj, (i2 & 256) != 0 ? null : requestBody, (i2 & 512) != 0 ? true : z, (i2 & 1024) != 0 ? requests.defaultCacheTime : i, (i2 & 2048) != 0 ? requests.defaultCacheTimeUnit : timeUnit, (i2 & 4096) != 0 ? requests.defaultTimeOut : j, (i2 & 8192) != 0 ? null : interceptor, (i2 & 16384) != 0 ? true : z2, (i2 & 32768) != 0 ? requests.responseParser : responseParser, continuation);
    }

    public final Object delete(String str, Map<String, String> map, String str2, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, List<NiceFile> list, Object obj, RequestBody requestBody, boolean z, int i, TimeUnit timeUnit, long j, Interceptor interceptor, boolean z2, ResponseParser responseParser, Continuation<? super NiceResponse> continuation) {
        return custom("DELETE", str, map, str2, map2, map3, map4, list, obj, requestBody, z, i, timeUnit, j, interceptor, z2, responseParser, continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object head$default(Requests requests, String str, Map map, String str2, Map map2, Map map3, boolean z, int i, TimeUnit timeUnit, long j, Interceptor interceptor, boolean z2, ResponseParser responseParser, Continuation continuation, int i2, Object obj) {
        if (obj == null) {
            return requests.head(str, (i2 & 2) != 0 ? MapsKt.emptyMap() : map, (i2 & 4) != 0 ? null : str2, (i2 & 8) != 0 ? MapsKt.emptyMap() : map2, (i2 & 16) != 0 ? MapsKt.emptyMap() : map3, (i2 & 32) != 0 ? true : z, (i2 & 64) != 0 ? requests.defaultCacheTime : i, (i2 & 128) != 0 ? requests.defaultCacheTimeUnit : timeUnit, (i2 & 256) != 0 ? requests.defaultTimeOut : j, (i2 & 512) == 0 ? interceptor : null, (i2 & 1024) == 0 ? z2 : true, (i2 & 2048) != 0 ? requests.responseParser : responseParser, continuation);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: head");
    }

    public final Object head(String str, Map<String, String> map, String str2, Map<String, String> map2, Map<String, String> map3, boolean z, int i, TimeUnit timeUnit, long j, Interceptor interceptor, boolean z2, ResponseParser responseParser, Continuation<? super NiceResponse> continuation) {
        return custom("HEAD", str, map, str2, map2, map3, null, null, null, null, z, i, timeUnit, j, interceptor, z2, responseParser, continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object patch$default(Requests requests, String str, Map map, String str2, Map map2, Map map3, Map map4, List list, Object obj, RequestBody requestBody, boolean z, int i, TimeUnit timeUnit, long j, Interceptor interceptor, boolean z2, ResponseParser responseParser, Continuation continuation, int i2, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: patch");
        }
        Map mapEmptyMap = (i2 & 2) != 0 ? MapsKt.emptyMap() : map;
        String str3 = (i2 & 4) != 0 ? null : str2;
        Map mapEmptyMap2 = (i2 & 8) != 0 ? MapsKt.emptyMap() : map2;
        return requests.patch(str, mapEmptyMap, str3, mapEmptyMap2, (i2 & 16) != 0 ? MapsKt.emptyMap() : map3, (i2 & 32) != 0 ? requests.defaultData : map4, (i2 & 64) != 0 ? null : list, (i2 & 128) != 0 ? null : obj, (i2 & 256) != 0 ? null : requestBody, (i2 & 512) != 0 ? true : z, (i2 & 1024) != 0 ? requests.defaultCacheTime : i, (i2 & 2048) != 0 ? requests.defaultCacheTimeUnit : timeUnit, (i2 & 4096) != 0 ? requests.defaultTimeOut : j, (i2 & 8192) != 0 ? null : interceptor, (i2 & 16384) != 0 ? true : z2, (i2 & 32768) != 0 ? requests.responseParser : responseParser, continuation);
    }

    public final Object patch(String str, Map<String, String> map, String str2, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, List<NiceFile> list, Object obj, RequestBody requestBody, boolean z, int i, TimeUnit timeUnit, long j, Interceptor interceptor, boolean z2, ResponseParser responseParser, Continuation<? super NiceResponse> continuation) {
        return custom("PATCH", str, map, str2, map2, map3, map4, list, obj, requestBody, z, i, timeUnit, j, interceptor, z2, responseParser, continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object options$default(Requests requests, String str, Map map, String str2, Map map2, Map map3, Map map4, List list, Object obj, RequestBody requestBody, boolean z, int i, TimeUnit timeUnit, long j, Interceptor interceptor, boolean z2, ResponseParser responseParser, Continuation continuation, int i2, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: options");
        }
        Map mapEmptyMap = (i2 & 2) != 0 ? MapsKt.emptyMap() : map;
        String str3 = (i2 & 4) != 0 ? null : str2;
        Map mapEmptyMap2 = (i2 & 8) != 0 ? MapsKt.emptyMap() : map2;
        return requests.options(str, mapEmptyMap, str3, mapEmptyMap2, (i2 & 16) != 0 ? MapsKt.emptyMap() : map3, (i2 & 32) != 0 ? requests.defaultData : map4, (i2 & 64) != 0 ? null : list, (i2 & 128) != 0 ? null : obj, (i2 & 256) != 0 ? null : requestBody, (i2 & 512) != 0 ? true : z, (i2 & 1024) != 0 ? requests.defaultCacheTime : i, (i2 & 2048) != 0 ? requests.defaultCacheTimeUnit : timeUnit, (i2 & 4096) != 0 ? requests.defaultTimeOut : j, (i2 & 8192) != 0 ? null : interceptor, (i2 & 16384) != 0 ? true : z2, (i2 & 32768) != 0 ? requests.responseParser : responseParser, continuation);
    }

    public final Object options(String str, Map<String, String> map, String str2, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, List<NiceFile> list, Object obj, RequestBody requestBody, boolean z, int i, TimeUnit timeUnit, long j, Interceptor interceptor, boolean z2, ResponseParser responseParser, Continuation<? super NiceResponse> continuation) {
        return custom("OPTIONS", str, map, str2, map2, map3, map4, list, obj, requestBody, z, i, timeUnit, j, interceptor, z2, responseParser, continuation);
    }
}
