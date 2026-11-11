package com.lagradost.cloudstream3.network;

import android.webkit.WebView;
import com.lagradost.api.Log;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: WebViewResolver.android.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/lagradost/cloudstream3/network/WebViewResolver;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.network.WebViewResolver$resolveUsingWebView$destroyWebView$1", f = "WebViewResolver.android.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class WebViewResolver$resolveUsingWebView$destroyWebView$1 extends SuspendLambda implements Function2<WebViewResolver, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.BooleanRef $shouldExit;
    final /* synthetic */ Ref.ObjectRef<WebView> $webView;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    WebViewResolver$resolveUsingWebView$destroyWebView$1(Ref.ObjectRef<WebView> objectRef, Ref.BooleanRef booleanRef, Continuation<? super WebViewResolver$resolveUsingWebView$destroyWebView$1> continuation) {
        super(2, continuation);
        this.$webView = objectRef;
        this.$shouldExit = booleanRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new WebViewResolver$resolveUsingWebView$destroyWebView$1(this.$webView, this.$shouldExit, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(WebViewResolver webViewResolver, Continuation<? super Unit> continuation) {
        return ((WebViewResolver$resolveUsingWebView$destroyWebView$1) create(webViewResolver, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        WebView webView = this.$webView.element;
        if (webView != null) {
            webView.stopLoading();
        }
        WebView webView2 = this.$webView.element;
        if (webView2 != null) {
            webView2.destroy();
        }
        this.$webView.element = null;
        this.$shouldExit.element = true;
        Log.INSTANCE.i("WebViewResolver", "Destroyed webview");
        return Unit.INSTANCE;
    }
}
