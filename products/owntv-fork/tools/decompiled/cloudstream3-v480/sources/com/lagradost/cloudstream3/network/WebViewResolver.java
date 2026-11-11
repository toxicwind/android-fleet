package com.lagradost.cloudstream3.network;

import android.content.Context;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import coil3.network.internal.UtilsKt;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.api.ContextHelper_androidKt;
import com.lagradost.api.Log;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.mvvm.DebugException;
import com.lagradost.cloudstream3.utils.AppDebug;
import com.lagradost.cloudstream3.utils.AtomicMutableList;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.nicehttp.RequestsKt;
import io.ktor.util.date.GMTDateParser;
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
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Regex;
import kotlinx.coroutines.BuildersKt__Builders_concurrentKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: compiled from: WebViewResolver.android.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0005\u0018\u0000 02\u00020\u0001:\u00010Bc\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\r\u0018\u00010\f\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0016JV\u0010#\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010%\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\u00050$2\u0006\u0010&\u001a\u00020\u00072\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010(\u001a\u00020\u00072\u0014\b\u0002\u0010)\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\t0\fH\u0086@¢\u0006\u0002\u0010*Jl\u0010#\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010%\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\u00050$2\u0006\u0010&\u001a\u00020\u00072\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u00072\u0014\b\u0002\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070,2\b\b\u0002\u0010(\u001a\u00020\u00072\u0014\b\u0002\u0010)\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\t0\fH\u0086@¢\u0006\u0002\u0010-J@\u0010#\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010%\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\u00050$2\u0006\u0010.\u001a\u00020%2\u0014\b\u0002\u0010)\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\t0\fH\u0087@¢\u0006\u0002\u0010/R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u001f\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\r\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e¨\u00061"}, d2 = {"Lcom/lagradost/cloudstream3/network/WebViewResolver;", "Lokhttp3/Interceptor;", "interceptUrl", "Lkotlin/text/Regex;", "additionalUrls", "", "userAgent", "", "useOkhttp", "", "script", "scriptCallback", "Lkotlin/Function1;", "", "timeout", "", "<init>", "(Lkotlin/text/Regex;Ljava/util/List;Ljava/lang/String;ZLjava/lang/String;Lkotlin/jvm/functions/Function1;J)V", "getInterceptUrl", "()Lkotlin/text/Regex;", "getAdditionalUrls", "()Ljava/util/List;", "getUserAgent", "()Ljava/lang/String;", "getUseOkhttp", "()Z", "getScript", "getScriptCallback", "()Lkotlin/jvm/functions/Function1;", "getTimeout", "()J", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "resolveUsingWebView", "Lkotlin/Pair;", "Lokhttp3/Request;", ImagesContract.URL, "referer", "method", "requestCallBack", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "headers", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "request", "(Lokhttp3/Request;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class WebViewResolver implements Interceptor {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final long DEFAULT_TIMEOUT = 60000;
    private static final String TAG = "WebViewResolver";
    private static String webViewUserAgent;
    private final List<Regex> additionalUrls;
    private final Regex interceptUrl;
    private final String script;
    private final Function1<String, Unit> scriptCallback;
    private final long timeout;
    private final boolean useOkhttp;
    private final String userAgent;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.network.WebViewResolver$resolveUsingWebView$3, reason: invalid class name */
    /* JADX INFO: compiled from: WebViewResolver.android.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.network.WebViewResolver", f = "WebViewResolver.android.kt", i = {0, 0, 0, 0, 0}, l = {90}, m = "resolveUsingWebView", n = {ImagesContract.URL, "referer", "headers", "method", "requestCallBack"}, nl = {93}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"}, v = 2)
    static final class AnonymousClass3 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WebViewResolver.this.resolveUsingWebView(null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.network.WebViewResolver$resolveUsingWebView$6, reason: invalid class name */
    /* JADX INFO: compiled from: WebViewResolver.android.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.network.WebViewResolver", f = "WebViewResolver.android.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {272}, m = "resolveUsingWebView", n = {"request", "requestCallBack", ImagesContract.URL, "headers", "webView", "shouldExit", "fixedRequest", "extraRequestList", "loop", "totalTime", "delayTime"}, nl = {273}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "I$0", "J$0", "J$1"}, v = 2)
    static final class AnonymousClass6 extends ContinuationImpl {
        int I$0;
        long J$0;
        long J$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        AnonymousClass6(Continuation<? super AnonymousClass6> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WebViewResolver.this.resolveUsingWebView(null, null, this);
        }
    }

    static final boolean resolveUsingWebView$lambda$0(Request it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return false;
    }

    static final boolean resolveUsingWebView$lambda$1(Request it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return false;
    }

    static final boolean resolveUsingWebView$lambda$3(Request it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return false;
    }

    public /* synthetic */ WebViewResolver(Regex regex, List list, String str, boolean z, String str2, Function1 function1, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(regex, (i & 2) != 0 ? CollectionsKt.emptyList() : list, (i & 4) != 0 ? MainAPIKt.USER_AGENT : str, (i & 8) != 0 ? true : z, (i & 16) != 0 ? null : str2, (i & 32) == 0 ? function1 : null, (i & 64) != 0 ? DEFAULT_TIMEOUT : j);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object resolveUsingWebView$default(WebViewResolver webViewResolver, String str, String str2, String str3, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            str3 = UtilsKt.HTTP_METHOD_GET;
        }
        String str5 = str3;
        if ((i & 8) != 0) {
            function1 = new Function1() { // from class: com.lagradost.cloudstream3.network.WebViewResolver$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return Boolean.valueOf(WebViewResolver.resolveUsingWebView$lambda$0((Request) obj2));
                }
            };
        }
        return webViewResolver.resolveUsingWebView(str, str4, str5, function1, continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public WebViewResolver(Regex interceptUrl, List<Regex> additionalUrls, String str, boolean z, String str2, Function1<? super String, Unit> function1, long j) {
        Intrinsics.checkNotNullParameter(interceptUrl, "interceptUrl");
        Intrinsics.checkNotNullParameter(additionalUrls, "additionalUrls");
        this.interceptUrl = interceptUrl;
        this.additionalUrls = additionalUrls;
        this.userAgent = str;
        this.useOkhttp = z;
        this.script = str2;
        this.scriptCallback = function1;
        this.timeout = j;
    }

    public final Regex getInterceptUrl() {
        return this.interceptUrl;
    }

    public final List<Regex> getAdditionalUrls() {
        return this.additionalUrls;
    }

    public final String getUserAgent() {
        return this.userAgent;
    }

    public final boolean getUseOkhttp() {
        return this.useOkhttp;
    }

    public final String getScript() {
        return this.script;
    }

    public final Function1<String, Unit> getScriptCallback() {
        return this.scriptCallback;
    }

    public final long getTimeout() {
        return this.timeout;
    }

    /* JADX INFO: compiled from: WebViewResolver.android.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\b\u000fR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000bX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/lagradost/cloudstream3/network/WebViewResolver$Companion;", "", "<init>", "()V", "webViewUserAgent", "", "getWebViewUserAgent", "()Ljava/lang/String;", "setWebViewUserAgent", "(Ljava/lang/String;)V", "DEFAULT_TIMEOUT", "", "getDEFAULT_TIMEOUT", "()J", "TAG", "getWebViewUserAgent1", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getWebViewUserAgent() {
            return WebViewResolver.webViewUserAgent;
        }

        public final void setWebViewUserAgent(String str) {
            WebViewResolver.webViewUserAgent = str;
        }

        public final long getDEFAULT_TIMEOUT() {
            return WebViewResolver.DEFAULT_TIMEOUT;
        }

        public final String getWebViewUserAgent1() {
            String webViewUserAgent = getWebViewUserAgent();
            if (webViewUserAgent != null) {
                return webViewUserAgent;
            }
            Object context = ContextHelper_androidKt.getContext();
            Context context2 = context instanceof Context ? (Context) context : null;
            if (context2 != null) {
                return (String) BuildersKt__Builders_concurrentKt.runBlockingK$default(null, new WebViewResolver$Companion$getWebViewUserAgent$1$1(context2, null), 1, null);
            }
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object resolveUsingWebView$default(WebViewResolver webViewResolver, String str, String str2, Map map, String str3, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            map = MapsKt.emptyMap();
        }
        Map map2 = map;
        if ((i & 8) != 0) {
            str3 = UtilsKt.HTTP_METHOD_GET;
        }
        String str5 = str3;
        if ((i & 16) != 0) {
            function1 = new Function1() { // from class: com.lagradost.cloudstream3.network.WebViewResolver$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return Boolean.valueOf(WebViewResolver.resolveUsingWebView$lambda$1((Request) obj2));
                }
            };
        }
        return webViewResolver.resolveUsingWebView(str, str4, map2, str5, function1, continuation);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.network.WebViewResolver$intercept$1, reason: invalid class name */
    /* JADX INFO: compiled from: WebViewResolver.android.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lokhttp3/Response;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.network.WebViewResolver$intercept$1", f = "WebViewResolver.android.kt", i = {}, l = {69}, m = "invokeSuspend", n = {}, nl = {70}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Response>, Object> {
        final /* synthetic */ Interceptor.Chain $chain;
        final /* synthetic */ Request $request;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Request request, Interceptor.Chain chain, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$request = request;
            this.$chain = chain;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return WebViewResolver.this.new AnonymousClass1(this.$request, this.$chain, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Response> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = WebViewResolver.resolveUsingWebView$default(WebViewResolver.this, this.$request, null, this, 2, null);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            Request request = (Request) ((Pair) obj).getFirst();
            Interceptor.Chain chain = this.$chain;
            if (request == null) {
                request = this.$request;
            }
            return chain.proceed(request);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object resolveUsingWebView$default(WebViewResolver webViewResolver, Request request, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1() { // from class: com.lagradost.cloudstream3.network.WebViewResolver$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return Boolean.valueOf(WebViewResolver.resolveUsingWebView$lambda$3((Request) obj2));
                }
            };
        }
        return webViewResolver.resolveUsingWebView(request, function1, continuation);
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        return (Response) BuildersKt__Builders_concurrentKt.runBlockingK$default(null, new AnonymousClass1(chain.request(), chain, null), 1, null);
    }

    public final Object resolveUsingWebView(String str, String str2, String str3, Function1<? super Request, Boolean> function1, Continuation<? super Pair<Request, ? extends List<Request>>> continuation) {
        return resolveUsingWebView(str, str2, MapsKt.emptyMap(), str3, function1, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public final Object resolveUsingWebView(String str, String str2, Map<String, String> map, String str3, Function1<? super Request, Boolean> function1, Continuation<? super Pair<Request, ? extends List<Request>>> continuation) throws DebugException {
        AnonymousClass3 anonymousClass3;
        if (continuation instanceof AnonymousClass3) {
            anonymousClass3 = (AnonymousClass3) continuation;
            if ((anonymousClass3.label & Integer.MIN_VALUE) != 0) {
                anonymousClass3.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass3 = new AnonymousClass3(continuation);
            }
        } else {
            anonymousClass3 = new AnonymousClass3(continuation);
        }
        Object objResolveUsingWebView = anonymousClass3.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass3.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(objResolveUsingWebView);
                Request requestRequestCreator = RequestsKt.requestCreator(str3, str, (8176 & 4) != 0 ? MapsKt.emptyMap() : map, (8176 & 8) != 0 ? null : str2, (8176 & 16) != 0 ? MapsKt.emptyMap() : null, (8176 & 32) != 0 ? MapsKt.emptyMap() : null, (8176 & 64) != 0 ? null : null, (8176 & 128) != 0 ? null : null, (8176 & 256) != 0 ? null : null, (8176 & 512) != 0 ? null : null, (8176 & 1024) != 0 ? null : null, (8176 & 2048) != 0 ? null : null, (8176 & 4096) != 0 ? null : null);
                anonymousClass3.L$0 = SpillingKt.nullOutSpilledVariable(str);
                anonymousClass3.L$1 = SpillingKt.nullOutSpilledVariable(str2);
                anonymousClass3.L$2 = SpillingKt.nullOutSpilledVariable(map);
                anonymousClass3.L$3 = SpillingKt.nullOutSpilledVariable(str3);
                anonymousClass3.L$4 = SpillingKt.nullOutSpilledVariable(function1);
                anonymousClass3.label = 1;
                objResolveUsingWebView = resolveUsingWebView(requestRequestCreator, function1, anonymousClass3);
                if (objResolveUsingWebView == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(objResolveUsingWebView);
            }
            return (Pair) objResolveUsingWebView;
        } catch (IllegalArgumentException e) {
            ArchComponentExtKt.logError(e);
            if (!AppDebug.INSTANCE.isDebug()) {
                return TuplesKt.to(null, CollectionsKt.emptyList());
            }
            throw new DebugException("ILLEGAL URL IN resolveUsingWebView!");
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0114 -> B:27:0x0127). Please report as a decompilation issue!!! */
    public final Object resolveUsingWebView(Request request, Function1<? super Request, Boolean> function1, Continuation<? super Pair<Request, ? extends List<Request>>> continuation) {
        AnonymousClass6 anonymousClass6;
        int i;
        AtomicMutableList atomicMutableList;
        Ref.BooleanRef booleanRef;
        Ref.ObjectRef objectRef;
        long j;
        Ref.ObjectRef objectRef2;
        String str;
        Headers headers;
        long j2;
        int i2;
        Request request2;
        Headers headers2;
        Ref.ObjectRef objectRef3;
        String str2;
        AnonymousClass6 anonymousClass7;
        Ref.ObjectRef objectRef4;
        Function1<? super Request, Boolean> function2;
        int i3;
        Ref.BooleanRef booleanRef2;
        AtomicMutableList atomicMutableList2;
        long j3;
        long j4;
        if (continuation instanceof AnonymousClass6) {
            anonymousClass6 = (AnonymousClass6) continuation;
            if ((anonymousClass6.label & Integer.MIN_VALUE) != 0) {
                anonymousClass6.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass6 = new AnonymousClass6(continuation);
            }
        } else {
            anonymousClass6 = new AnonymousClass6(continuation);
        }
        AnonymousClass6 anonymousClass8 = anonymousClass6;
        Object obj = anonymousClass8.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i4 = anonymousClass8.label;
        if (i4 == 0) {
            ResultKt.throwOnFailure(obj);
            String url = request.url().getUrl();
            Headers headers3 = request.headers();
            Log.INSTANCE.i(TAG, "Initial web-view request: " + url);
            Ref.ObjectRef objectRef5 = new Ref.ObjectRef();
            Ref.BooleanRef booleanRef3 = new Ref.BooleanRef();
            Ref.ObjectRef objectRef6 = new Ref.ObjectRef();
            i = 0;
            AtomicMutableList atomicMutableListAtomicListOf = Coroutines.INSTANCE.atomicListOf(new Request[0]);
            Coroutines.INSTANCE.main(this, new AnonymousClass8(objectRef5, url, headers3, this, objectRef6, atomicMutableListAtomicListOf, function1, booleanRef3, null));
            atomicMutableList = atomicMutableListAtomicListOf;
            booleanRef = booleanRef3;
            objectRef = objectRef6;
            j = this.timeout;
            objectRef2 = objectRef5;
            str = url;
            headers = headers3;
            j2 = 100;
            if (i >= j / j2 && !booleanRef.element) {
                if (objectRef.element != 0) {
                    return TuplesKt.to(objectRef.element, atomicMutableList);
                }
                anonymousClass8.L$0 = SpillingKt.nullOutSpilledVariable(request);
                anonymousClass8.L$1 = SpillingKt.nullOutSpilledVariable(function1);
                anonymousClass8.L$2 = SpillingKt.nullOutSpilledVariable(str);
                anonymousClass8.L$3 = SpillingKt.nullOutSpilledVariable(headers);
                anonymousClass8.L$4 = objectRef2;
                anonymousClass8.L$5 = booleanRef;
                anonymousClass8.L$6 = objectRef;
                anonymousClass8.L$7 = atomicMutableList;
                anonymousClass8.I$0 = i;
                anonymousClass8.J$0 = j;
                anonymousClass8.J$1 = j2;
                i2 = 1;
                anonymousClass8.label = 1;
                if (DelayKt.delay(j2, anonymousClass8) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                request2 = request;
                headers2 = headers;
                objectRef3 = objectRef2;
                str2 = str;
                anonymousClass7 = anonymousClass8;
                objectRef4 = objectRef;
                long j5 = j2;
                function2 = function1;
                i3 = i;
                booleanRef2 = booleanRef;
                atomicMutableList2 = atomicMutableList;
                j3 = j;
                j4 = j5;
            } else {
                Log.INSTANCE.i(TAG, "Web-view timeout after " + (j / 1000) + GMTDateParser.SECONDS);
                resolveUsingWebView$destroyWebView(this, objectRef2, booleanRef);
                return TuplesKt.to(objectRef.element, atomicMutableList);
            }
        } else {
            if (i4 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            long j6 = anonymousClass8.J$1;
            long j7 = anonymousClass8.J$0;
            i3 = anonymousClass8.I$0;
            AtomicMutableList atomicMutableList3 = (AtomicMutableList) anonymousClass8.L$7;
            Ref.ObjectRef objectRef7 = (Ref.ObjectRef) anonymousClass8.L$6;
            Ref.BooleanRef booleanRef4 = (Ref.BooleanRef) anonymousClass8.L$5;
            Ref.ObjectRef objectRef8 = (Ref.ObjectRef) anonymousClass8.L$4;
            Headers headers4 = (Headers) anonymousClass8.L$3;
            String str3 = (String) anonymousClass8.L$2;
            Function1<? super Request, Boolean> function3 = (Function1) anonymousClass8.L$1;
            Request request3 = (Request) anonymousClass8.L$0;
            ResultKt.throwOnFailure(obj);
            objectRef3 = objectRef8;
            headers2 = headers4;
            anonymousClass7 = anonymousClass8;
            str2 = str3;
            objectRef4 = objectRef7;
            booleanRef2 = booleanRef4;
            atomicMutableList2 = atomicMutableList3;
            j3 = j7;
            j4 = j6;
            function2 = function3;
            request2 = request3;
            i2 = 1;
        }
        request = request2;
        function1 = function2;
        Ref.BooleanRef booleanRef5 = booleanRef2;
        i = i3 + i2;
        j2 = j4;
        j = j3;
        atomicMutableList = atomicMutableList2;
        objectRef = objectRef4;
        booleanRef = booleanRef5;
        anonymousClass8 = anonymousClass7;
        str = str2;
        objectRef2 = objectRef3;
        headers = headers2;
        if (i >= j / j2) {
        }
        Log.INSTANCE.i(TAG, "Web-view timeout after " + (j / 1000) + GMTDateParser.SECONDS);
        resolveUsingWebView$destroyWebView(this, objectRef2, booleanRef);
        return TuplesKt.to(objectRef.element, atomicMutableList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void resolveUsingWebView$destroyWebView(WebViewResolver webViewResolver, Ref.ObjectRef<WebView> objectRef, Ref.BooleanRef booleanRef) {
        Coroutines.INSTANCE.main(webViewResolver, new WebViewResolver$resolveUsingWebView$destroyWebView$1(objectRef, booleanRef, null));
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.network.WebViewResolver$resolveUsingWebView$8, reason: invalid class name */
    /* JADX INFO: compiled from: WebViewResolver.android.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/lagradost/cloudstream3/network/WebViewResolver;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.network.WebViewResolver$resolveUsingWebView$8", f = "WebViewResolver.android.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass8 extends SuspendLambda implements Function2<WebViewResolver, Continuation<? super Unit>, Object> {
        final /* synthetic */ AtomicMutableList<Request> $extraRequestList;
        final /* synthetic */ Ref.ObjectRef<Request> $fixedRequest;
        final /* synthetic */ Headers $headers;
        final /* synthetic */ Function1<Request, Boolean> $requestCallBack;
        final /* synthetic */ Ref.BooleanRef $shouldExit;
        final /* synthetic */ String $url;
        final /* synthetic */ Ref.ObjectRef<WebView> $webView;
        int label;
        final /* synthetic */ WebViewResolver this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass8(Ref.ObjectRef<WebView> objectRef, String str, Headers headers, WebViewResolver webViewResolver, Ref.ObjectRef<Request> objectRef2, AtomicMutableList<Request> atomicMutableList, Function1<? super Request, Boolean> function1, Ref.BooleanRef booleanRef, Continuation<? super AnonymousClass8> continuation) {
            super(2, continuation);
            this.$webView = objectRef;
            this.$url = str;
            this.$headers = headers;
            this.this$0 = webViewResolver;
            this.$fixedRequest = objectRef2;
            this.$extraRequestList = atomicMutableList;
            this.$requestCallBack = function1;
            this.$shouldExit = booleanRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass8(this.$webView, this.$url, this.$headers, this.this$0, this.$fixedRequest, this.$extraRequestList, this.$requestCallBack, this.$shouldExit, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(WebViewResolver webViewResolver, Continuation<? super Unit> continuation) {
            return ((AnonymousClass8) create(webViewResolver, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [T, android.webkit.WebView] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                Ref.ObjectRef<WebView> objectRef = this.$webView;
                Object context = ContextHelper_androidKt.getContext();
                Context context2 = context instanceof Context ? (Context) context : null;
                if (context2 != null) {
                    ?? webView = new WebView(context2);
                    WebViewResolver webViewResolver = this.this$0;
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.getSettings().setDomStorageEnabled(true);
                    WebViewResolver.INSTANCE.setWebViewUserAgent(webView.getSettings().getUserAgentString());
                    if (webViewResolver.getUserAgent() != null) {
                        webView.getSettings().setUserAgentString(webViewResolver.getUserAgent());
                    }
                    objectRef.element = webView;
                    WebView webView2 = this.$webView.element;
                    if (webView2 != null) {
                        webView2.setWebViewClient(new AnonymousClass2(this.this$0, this.$fixedRequest, this.$extraRequestList, this.$requestCallBack, this.$webView, this.$shouldExit));
                    }
                    WebView webView3 = this.$webView.element;
                    if (webView3 != null) {
                        webView3.loadUrl(this.$url, MapsKt.toMap(this.$headers));
                    }
                    return Unit.INSTANCE;
                }
                throw new RuntimeException("No base context in WebViewResolver");
            } catch (Exception e) {
                ArchComponentExtKt.logError(e);
            }
        }

        /* JADX INFO: renamed from: com.lagradost.cloudstream3.network.WebViewResolver$resolveUsingWebView$8$2, reason: invalid class name */
        /* JADX INFO: compiled from: WebViewResolver.android.kt */
        @Metadata(d1 = {"\u0000/\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J&\u0010\b\u001a\u00020\t2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0017¨\u0006\u000e"}, d2 = {"com/lagradost/cloudstream3/network/WebViewResolver$resolveUsingWebView$8$2", "Landroid/webkit/WebViewClient;", "shouldInterceptRequest", "Landroid/webkit/WebResourceResponse;", "view", "Landroid/webkit/WebView;", "request", "Landroid/webkit/WebResourceRequest;", "onReceivedSslError", "", "handler", "Landroid/webkit/SslErrorHandler;", MediaRouteProviderProtocol.SERVICE_DATA_ERROR, "Landroid/net/http/SslError;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class AnonymousClass2 extends WebViewClient {
            final /* synthetic */ AtomicMutableList<Request> $extraRequestList;
            final /* synthetic */ Ref.ObjectRef<Request> $fixedRequest;
            final /* synthetic */ Function1<Request, Boolean> $requestCallBack;
            final /* synthetic */ Ref.BooleanRef $shouldExit;
            final /* synthetic */ Ref.ObjectRef<WebView> $webView;
            final /* synthetic */ WebViewResolver this$0;

            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass2(WebViewResolver webViewResolver, Ref.ObjectRef<Request> objectRef, AtomicMutableList<Request> atomicMutableList, Function1<? super Request, Boolean> function1, Ref.ObjectRef<WebView> objectRef2, Ref.BooleanRef booleanRef) {
                this.this$0 = webViewResolver;
                this.$fixedRequest = objectRef;
                this.$extraRequestList = atomicMutableList;
                this.$requestCallBack = function1;
                this.$webView = objectRef2;
                this.$shouldExit = booleanRef;
            }

            @Override // android.webkit.WebViewClient
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(request, "request");
                return (WebResourceResponse) BuildersKt__Builders_concurrentKt.runBlockingK$default(null, new WebViewResolver$resolveUsingWebView$8$2$shouldInterceptRequest$1(request, this.this$0, this.$fixedRequest, this.$extraRequestList, this, view, this.$requestCallBack, this.$webView, this.$shouldExit, null), 1, null);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                if (handler != null) {
                    handler.proceed();
                }
            }
        }
    }
}
