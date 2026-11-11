package com.lagradost.cloudstream3.network;

import android.util.Log;
import android.webkit.CookieManager;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.mvvm.DebugException;
import com.lagradost.cloudstream3.utils.AppDebug;
import com.lagradost.nicehttp.ContinuationCallback;
import com.lagradost.nicehttp.NiceResponseKt;
import com.lagradost.nicehttp.Requests;
import java.io.IOException;
import java.net.URI;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: compiled from: CloudflareKiller.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0006J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00020\u0006H\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J*\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0007H\u0082@¢\u0006\u0002\u0010\u0018J\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0014\u001a\u00020\u0015H\u0082@¢\u0006\u0002\u0010\u001aR)\u0010\u0004\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u001c"}, d2 = {"Lcom/lagradost/cloudstream3/network/CloudflareKiller;", "Lokhttp3/Interceptor;", "<init>", "()V", "savedCookies", "", "", "", "getSavedCookies", "()Ljava/util/Map;", "getCookieHeaders", "Lokhttp3/Headers;", ImagesContract.URL, "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "getWebViewCookie", "trySolveWithSavedCookies", "", "request", "Lokhttp3/Request;", "proceed", "cookies", "(Lokhttp3/Request;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "bypassCloudflare", "(Lokhttp3/Request;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CloudflareKiller implements Interceptor {
    public static final String TAG = "CloudflareKiller";
    private final Map<String, Map<String, String>> savedCookies;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final List<Integer> ERROR_CODES = CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(TypedValues.CycleType.TYPE_ALPHA), Integer.valueOf(TypedValues.PositionType.TYPE_PERCENT_WIDTH)});
    private static final List<String> CLOUDFLARE_SERVERS = CollectionsKt.listOf((Object[]) new String[]{"cloudflare-nginx", "cloudflare"});

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.network.CloudflareKiller$bypassCloudflare$1, reason: invalid class name */
    /* JADX INFO: compiled from: CloudflareKiller.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.network.CloudflareKiller", f = "CloudflareKiller.kt", i = {0, 0, 1, 1, 1}, l = {128, 136}, m = "bypassCloudflare", n = {"request", ImagesContract.URL, "request", ImagesContract.URL, "cookies"}, nl = {135, -1}, s = {"L$0", "L$1", "L$0", "L$1", "L$2"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CloudflareKiller.this.bypassCloudflare(null, this);
        }
    }

    public CloudflareKiller() {
        ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.network.CloudflareKiller$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return CloudflareKiller._init_$lambda$0();
            }
        });
        this.savedCookies = new LinkedHashMap();
    }

    /* JADX INFO: compiled from: CloudflareKiller.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u000b2\u0006\u0010\f\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/lagradost/cloudstream3/network/CloudflareKiller$Companion;", "", "<init>", "()V", "TAG", "", "ERROR_CODES", "", "", "CLOUDFLARE_SERVERS", "parseCookieMap", "", "cookie", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Map<String, String> parseCookieMap(String cookie) {
            String string;
            String string2;
            Intrinsics.checkNotNullParameter(cookie, "cookie");
            List listSplit$default = StringsKt.split$default((CharSequence) cookie, new String[]{";"}, false, 0, 6, (Object) null);
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(listSplit$default, 10)), 16));
            Iterator it = listSplit$default.iterator();
            while (it.hasNext()) {
                List listSplit$default2 = StringsKt.split$default((CharSequence) it.next(), new String[]{"="}, false, 0, 6, (Object) null);
                String str = (String) CollectionsKt.getOrNull(listSplit$default2, 0);
                String str2 = "";
                if (str == null || (string = StringsKt.trim((CharSequence) str).toString()) == null) {
                    string = "";
                }
                String str3 = (String) CollectionsKt.getOrNull(listSplit$default2, 1);
                if (str3 != null && (string2 = StringsKt.trim((CharSequence) str3).toString()) != null) {
                    str2 = string2;
                }
                Pair pair = TuplesKt.to(string, str2);
                linkedHashMap.put(pair.getFirst(), pair.getSecond());
            }
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                if (!StringsKt.isBlank((CharSequence) entry.getKey()) && !StringsKt.isBlank((CharSequence) entry.getValue())) {
                    linkedHashMap2.put(entry.getKey(), entry.getValue());
                }
            }
            return linkedHashMap2;
        }
    }

    static final Unit _init_$lambda$0() {
        CookieManager.getInstance().removeAllCookies(null);
        return Unit.INSTANCE;
    }

    public final Map<String, Map<String, String>> getSavedCookies() {
        return this.savedCookies;
    }

    public final Headers getCookieHeaders(String url) {
        Map mapEmptyMap;
        Intrinsics.checkNotNullParameter(url, "url");
        String webViewUserAgent = WebViewResolver.INSTANCE.getWebViewUserAgent();
        if (webViewUserAgent == null || (mapEmptyMap = MapsKt.mapOf(TuplesKt.to("user-agent", webViewUserAgent))) == null) {
            mapEmptyMap = MapsKt.emptyMap();
        }
        Map<String, String> mapEmptyMap2 = this.savedCookies.get(new URI(url).getHost());
        if (mapEmptyMap2 == null) {
            mapEmptyMap2 = MapsKt.emptyMap();
        }
        return RequestsHelperKt.getHeaders(mapEmptyMap, mapEmptyMap2);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.network.CloudflareKiller$intercept$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CloudflareKiller.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lokhttp3/Response;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.network.CloudflareKiller$intercept$1", f = "CloudflareKiller.kt", i = {0, 0, 0, 1, 1}, l = {64, 71}, m = "invokeSuspend", n = {"request", "cookies", "response", "request", "cookies"}, nl = {65, 75}, s = {"L$0", "L$1", "L$2", "L$0", "L$1"}, v = 2)
    static final class C07421 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Response>, Object> {
        final /* synthetic */ Interceptor.Chain $chain;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        final /* synthetic */ CloudflareKiller this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07421(Interceptor.Chain chain, CloudflareKiller cloudflareKiller, Continuation<? super C07421> continuation) {
            super(2, continuation);
            this.$chain = chain;
            this.this$0 = cloudflareKiller;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C07421(this.$chain, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Response> continuation) {
            return ((C07421) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws IOException {
            Request request;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Request request2 = this.$chain.request();
                Map<String, String> map = this.this$0.getSavedCookies().get(request2.url().host());
                if (map != null) {
                    this.L$0 = SpillingKt.nullOutSpilledVariable(request2);
                    this.L$1 = SpillingKt.nullOutSpilledVariable(map);
                    this.label = 2;
                    Object objProceed = this.this$0.proceed(request2, map, this);
                    if (objProceed != coroutine_suspended) {
                        return objProceed;
                    }
                } else {
                    Response responseProceed = this.$chain.proceed(request2);
                    if (!CollectionsKt.contains(CloudflareKiller.CLOUDFLARE_SERVERS, Response.header$default(responseProceed, "Server", null, 2, null)) || !CloudflareKiller.ERROR_CODES.contains(Boxing.boxInt(responseProceed.code()))) {
                        return responseProceed;
                    }
                    responseProceed.close();
                    this.L$0 = request2;
                    this.L$1 = SpillingKt.nullOutSpilledVariable(map);
                    this.L$2 = SpillingKt.nullOutSpilledVariable(responseProceed);
                    this.label = 1;
                    Object objBypassCloudflare = this.this$0.bypassCloudflare(request2, this);
                    if (objBypassCloudflare != coroutine_suspended) {
                        request = request2;
                        obj = objBypassCloudflare;
                    }
                }
                return coroutine_suspended;
            }
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return obj;
            }
            request = (Request) this.L$0;
            ResultKt.throwOnFailure(obj);
            Response response = (Response) obj;
            if (response != null) {
                Log.d(CloudflareKiller.TAG, "Succeeded bypassing cloudflare: " + request.url());
                return response;
            }
            if (AppDebug.INSTANCE.isDebug()) {
                ArchComponentExtKt.logError(new DebugException("Failed cloudflare at: " + request.url()));
            }
            return this.$chain.proceed(request);
        }
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        return (Response) BuildersKt__BuildersKt.runBlocking$default(null, new C07421(chain, this, null), 1, null);
    }

    private final String getWebViewCookie(final String url) {
        return (String) ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.network.CloudflareKiller$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return CloudflareKiller.getWebViewCookie$lambda$0(url);
            }
        });
    }

    static final String getWebViewCookie$lambda$0(String str) {
        CookieManager cookieManager = CookieManager.getInstance();
        if (cookieManager != null) {
            return cookieManager.getCookie(str);
        }
        return null;
    }

    private final boolean trySolveWithSavedCookies(Request request) {
        String webViewCookie = getWebViewCookie(request.url().getUrl());
        boolean zContains$default = false;
        if (webViewCookie != null && (zContains$default = StringsKt.contains$default((CharSequence) webViewCookie, (CharSequence) "cf_clearance", false, 2, (Object) null))) {
            this.savedCookies.put(request.url().host(), INSTANCE.parseCookieMap(webViewCookie));
        }
        return zContains$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object proceed(Request request, Map<String, String> map, Continuation<? super Response> continuation) {
        Map mapEmptyMap;
        String webViewUserAgent1 = WebViewResolver.INSTANCE.getWebViewUserAgent1();
        if (webViewUserAgent1 == null || (mapEmptyMap = MapsKt.mapOf(TuplesKt.to("user-agent", webViewUserAgent1))) == null) {
            mapEmptyMap = MapsKt.emptyMap();
        }
        Headers headers = RequestsHelperKt.getHeaders(MapsKt.plus(MapsKt.toMap(request.headers()), mapEmptyMap), MapsKt.plus(map, NiceResponseKt.getCookies(request)));
        Requests.Companion companion = Requests.INSTANCE;
        Call callNewCall = MainActivityKt.getApp().getBaseClient().newCall(request.newBuilder().headers(headers).build());
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        ContinuationCallback continuationCallback = new ContinuationCallback(callNewCall, cancellableContinuationImpl2);
        callNewCall.enqueue(continuationCallback);
        cancellableContinuationImpl2.invokeOnCancellation(continuationCallback);
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:26:0x00d4 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:28:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:31:0x00f1 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00bc, code lost:
    
        if (com.lagradost.cloudstream3.network.WebViewResolver.resolveUsingWebView$default(r13, r5, null, null, r8, r9, 6, null) == r3) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object bypassCloudflare(okhttp3.Request r25, kotlin.coroutines.Continuation<? super okhttp3.Response> r26) {
        /*
            Method dump skipped, instruction units count: 242
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.network.CloudflareKiller.bypassCloudflare(okhttp3.Request, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static final boolean bypassCloudflare$lambda$0(CloudflareKiller cloudflareKiller, Request request, Request it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return cloudflareKiller.trySolveWithSavedCookies(request);
    }
}
