package com.lagradost.cloudstream3.network;

import android.content.Context;
import android.webkit.WebView;
import com.lagradost.cloudstream3.utils.Coroutines;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: WebViewResolver.android.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.network.WebViewResolver$Companion$getWebViewUserAgent$1$1", f = "WebViewResolver.android.kt", i = {0}, l = {56}, m = "invokeSuspend", n = {"$this$runBlocking"}, nl = {60}, s = {"L$0"}, v = 2)
final class WebViewResolver$Companion$getWebViewUserAgent$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    final /* synthetic */ Context $ctx;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    WebViewResolver$Companion$getWebViewUserAgent$1$1(Context context, Continuation<? super WebViewResolver$Companion$getWebViewUserAgent$1$1> continuation) {
        super(2, continuation);
        this.$ctx = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        WebViewResolver$Companion$getWebViewUserAgent$1$1 webViewResolver$Companion$getWebViewUserAgent$1$1 = new WebViewResolver$Companion$getWebViewUserAgent$1$1(this.$ctx, continuation);
        webViewResolver$Companion$getWebViewUserAgent$1$1.L$0 = obj;
        return webViewResolver$Companion$getWebViewUserAgent$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return ((WebViewResolver$Companion$getWebViewUserAgent$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.network.WebViewResolver$Companion$getWebViewUserAgent$1$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: WebViewResolver.android.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;", "it"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.network.WebViewResolver$Companion$getWebViewUserAgent$1$1$1", f = "WebViewResolver.android.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, CoroutineScope, Continuation<? super String>, Object> {
        final /* synthetic */ Context $ctx;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Context context, Continuation<? super AnonymousClass1> continuation) {
            super(3, continuation);
            this.$ctx = context;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, CoroutineScope coroutineScope2, Continuation<? super String> continuation) {
            return new AnonymousClass1(this.$ctx, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            String userAgentString = new WebView(this.$ctx).getSettings().getUserAgentString();
            WebViewResolver.INSTANCE.setWebViewUserAgent(userAgentString);
            return userAgentString;
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return obj;
        }
        ResultKt.throwOnFailure(obj);
        this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
        this.label = 1;
        Object objMainWork = Coroutines.INSTANCE.mainWork(coroutineScope, new AnonymousClass1(this.$ctx, null), this);
        return objMainWork == coroutine_suspended ? coroutine_suspended : objMainWork;
    }
}
