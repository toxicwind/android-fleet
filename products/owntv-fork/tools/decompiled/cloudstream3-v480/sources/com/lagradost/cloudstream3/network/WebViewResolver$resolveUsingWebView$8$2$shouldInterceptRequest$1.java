package com.lagradost.cloudstream3.network;

import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.lagradost.cloudstream3.utils.AtomicMutableList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.Request;

/* JADX INFO: compiled from: WebViewResolver.android.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroid/webkit/WebResourceResponse;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.network.WebViewResolver$resolveUsingWebView$8$2$shouldInterceptRequest$1", f = "WebViewResolver.android.kt", i = {0, 0, 1, 1}, l = {228, 233}, m = "invokeSuspend", n = {"webViewUrl", "blacklistedFiles", "webViewUrl", "blacklistedFiles"}, nl = {231, 236}, s = {"L$0", "L$1", "L$0", "L$1"}, v = 2)
final class WebViewResolver$resolveUsingWebView$8$2$shouldInterceptRequest$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super WebResourceResponse>, Object> {
    final /* synthetic */ AtomicMutableList<Request> $extraRequestList;
    final /* synthetic */ Ref.ObjectRef<Request> $fixedRequest;
    final /* synthetic */ WebResourceRequest $request;
    final /* synthetic */ Function1<Request, Boolean> $requestCallBack;
    final /* synthetic */ Ref.BooleanRef $shouldExit;
    final /* synthetic */ WebView $view;
    final /* synthetic */ Ref.ObjectRef<WebView> $webView;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ WebViewResolver this$0;
    final /* synthetic */ WebViewResolver.AnonymousClass8.AnonymousClass2 this$1;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    WebViewResolver$resolveUsingWebView$8$2$shouldInterceptRequest$1(WebResourceRequest webResourceRequest, WebViewResolver webViewResolver, Ref.ObjectRef<Request> objectRef, AtomicMutableList<Request> atomicMutableList, WebViewResolver.AnonymousClass8.AnonymousClass2 anonymousClass2, WebView webView, Function1<? super Request, Boolean> function1, Ref.ObjectRef<WebView> objectRef2, Ref.BooleanRef booleanRef, Continuation<? super WebViewResolver$resolveUsingWebView$8$2$shouldInterceptRequest$1> continuation) {
        super(2, continuation);
        this.$request = webResourceRequest;
        this.this$0 = webViewResolver;
        this.$fixedRequest = objectRef;
        this.$extraRequestList = atomicMutableList;
        this.this$1 = anonymousClass2;
        this.$view = webView;
        this.$requestCallBack = function1;
        this.$webView = objectRef2;
        this.$shouldExit = booleanRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new WebViewResolver$resolveUsingWebView$8$2$shouldInterceptRequest$1(this.$request, this.this$0, this.$fixedRequest, this.$extraRequestList, this.this$1, this.$view, this.$requestCallBack, this.$webView, this.$shouldExit, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super WebResourceResponse> continuation) {
        return ((WebViewResolver$resolveUsingWebView$8$2$shouldInterceptRequest$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:77:0x0260, code lost:
    
        if (r0 == r1) goto L78;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r36) {
        /*
            Method dump skipped, instruction units count: 645
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.network.WebViewResolver$resolveUsingWebView$8$2$shouldInterceptRequest$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    static final Unit invokeSuspend$lambda$0(WebView webView, final WebViewResolver webViewResolver) {
        webView.evaluateJavascript(webViewResolver.getScript(), new ValueCallback() { // from class: com.lagradost.cloudstream3.network.WebViewResolver$resolveUsingWebView$8$2$shouldInterceptRequest$1$$ExternalSyntheticLambda0
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                WebViewResolver$resolveUsingWebView$8$2$shouldInterceptRequest$1.invokeSuspend$lambda$0$0(webViewResolver, (String) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invokeSuspend$lambda$0$0(WebViewResolver webViewResolver, String str) {
        Function1<String, Unit> scriptCallback = webViewResolver.getScriptCallback();
        if (scriptCallback != null) {
            Intrinsics.checkNotNull(str);
            scriptCallback.invoke(str);
        }
    }
}
