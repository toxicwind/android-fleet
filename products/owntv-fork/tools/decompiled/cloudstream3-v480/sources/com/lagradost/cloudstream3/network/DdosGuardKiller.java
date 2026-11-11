package com.lagradost.cloudstream3.network;

import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.NiceResponseKt;
import com.lagradost.nicehttp.Requests;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: compiled from: DdosGuardKiller.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0016\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013H\u0082@¢\u0006\u0002\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010\u0006\u001a\u001a\u0012\u0004\u0012\u00020\b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/lagradost/cloudstream3/network/DdosGuardKiller;", "Lokhttp3/Interceptor;", "alwaysBypass", "", "<init>", "(Z)V", "savedCookiesMap", "", "", "", "getSavedCookiesMap", "()Ljava/util/Map;", "ddosBypassPath", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "bypassDdosGuard", "request", "Lokhttp3/Request;", "(Lokhttp3/Request;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DdosGuardKiller implements Interceptor {
    private final boolean alwaysBypass;
    private String ddosBypassPath;
    private final Map<String, Map<String, String>> savedCookiesMap = new LinkedHashMap();

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.network.DdosGuardKiller$bypassDdosGuard$1, reason: invalid class name */
    /* JADX INFO: compiled from: DdosGuardKiller.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.network.DdosGuardKiller", f = "DdosGuardKiller.kt", i = {0, 1, 1, 1}, l = {35, 45}, m = "bypassDdosGuard", n = {"request", "request", "it", "$i$a$-let-DdosGuardKiller$bypassDdosGuard$cookies$1"}, nl = {37, 46}, s = {"L$0", "L$0", "L$1", "I$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
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
            return DdosGuardKiller.this.bypassDdosGuard(null, this);
        }
    }

    public DdosGuardKiller(boolean z) {
        this.alwaysBypass = z;
    }

    public final Map<String, Map<String, String>> getSavedCookiesMap() {
        return this.savedCookiesMap;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.network.DdosGuardKiller$intercept$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DdosGuardKiller.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lokhttp3/Response;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.network.DdosGuardKiller$intercept$1", f = "DdosGuardKiller.kt", i = {0, 1, 1}, l = {25, 29}, m = "invokeSuspend", n = {"request", "request", "response"}, nl = {27, 30}, s = {"L$0", "L$0", "L$1"}, v = 2)
    static final class C07431 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Response>, Object> {
        final /* synthetic */ Interceptor.Chain $chain;
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ DdosGuardKiller this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07431(Interceptor.Chain chain, DdosGuardKiller ddosGuardKiller, Continuation<? super C07431> continuation) {
            super(2, continuation);
            this.$chain = chain;
            this.this$0 = ddosGuardKiller;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C07431(this.$chain, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Response> continuation) {
            return ((C07431) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:20:0x0075, code lost:
        
            if (r7 == r0) goto L21;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) throws java.io.IOException {
            /*
                r6 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r6.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L2a
                if (r1 == r3) goto L22
                if (r1 != r2) goto L1a
                java.lang.Object r0 = r6.L$1
                okhttp3.Response r0 = (okhttp3.Response) r0
                java.lang.Object r6 = r6.L$0
                okhttp3.Request r6 = (okhttp3.Request) r6
                kotlin.ResultKt.throwOnFailure(r7)
                goto L78
            L1a:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L22:
                java.lang.Object r6 = r6.L$0
                okhttp3.Request r6 = (okhttp3.Request) r6
                kotlin.ResultKt.throwOnFailure(r7)
                return r7
            L2a:
                kotlin.ResultKt.throwOnFailure(r7)
                okhttp3.Interceptor$Chain r7 = r6.$chain
                okhttp3.Request r7 = r7.request()
                com.lagradost.cloudstream3.network.DdosGuardKiller r1 = r6.this$0
                boolean r1 = com.lagradost.cloudstream3.network.DdosGuardKiller.access$getAlwaysBypass$p(r1)
                if (r1 == 0) goto L50
                com.lagradost.cloudstream3.network.DdosGuardKiller r1 = r6.this$0
                r2 = r6
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
                r6.L$0 = r4
                r6.label = r3
                java.lang.Object r6 = com.lagradost.cloudstream3.network.DdosGuardKiller.access$bypassDdosGuard(r1, r7, r2)
                if (r6 != r0) goto L4f
                goto L77
            L4f:
                return r6
            L50:
                okhttp3.Interceptor$Chain r1 = r6.$chain
                okhttp3.Response r1 = r1.proceed(r7)
                int r3 = r1.code()
                r4 = 403(0x193, float:5.65E-43)
                if (r3 != r4) goto L7b
                com.lagradost.cloudstream3.network.DdosGuardKiller r3 = r6.this$0
                r4 = r6
                kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
                r6.L$0 = r5
                java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r1)
                r6.L$1 = r1
                r6.label = r2
                java.lang.Object r7 = com.lagradost.cloudstream3.network.DdosGuardKiller.access$bypassDdosGuard(r3, r7, r4)
                if (r7 != r0) goto L78
            L77:
                return r0
            L78:
                okhttp3.Response r7 = (okhttp3.Response) r7
                return r7
            L7b:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.network.DdosGuardKiller.C07431.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        return (Response) BuildersKt__BuildersKt.runBlocking$default(null, new C07431(chain, this, null), 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:31:0x0102  */
    /* JADX WARN: Code duplicated, block: B:33:0x0126  */
    /* JADX WARN: Code duplicated, block: B:37:0x016e  */
    /* JADX WARN: Code duplicated, block: B:39:0x0184  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public final Object bypassDdosGuard(Request request, Continuation<? super Response> continuation) {
        AnonymousClass1 anonymousClass1;
        AnonymousClass1 anonymousClass2;
        Object obj;
        int i;
        int i2;
        DdosGuardKiller ddosGuardKiller;
        String str;
        Request request2;
        Object obj2;
        Request request3;
        Regex regex;
        Map<String, String> cookies;
        String str2;
        Request request4;
        DdosGuardKiller ddosGuardKiller2;
        Object obj3;
        Request request5;
        List<String> groupValues;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object obj4 = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = anonymousClass1.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj4);
            String str3 = this.ddosBypassPath;
            if (str3 != null) {
                anonymousClass2 = anonymousClass1;
                obj = coroutine_suspended;
                i = 2;
                i2 = 0;
                ddosGuardKiller = this;
                str = str3;
                request2 = request;
                ddosGuardKiller.ddosBypassPath = str;
                cookies = this.savedCookiesMap.get(request2.url().host());
                if (cookies == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(request2.url().scheme());
                    sb.append("://");
                    sb.append(request2.url().host());
                    str2 = this.ddosBypassPath;
                    if (str2 == null) {
                        str2 = "";
                    }
                    sb.append(str2);
                    String string = sb.toString();
                    Requests requests = new Requests(null, null, null, null, null, 0, null, 0L, null, 511, null);
                    anonymousClass2.L$0 = request2;
                    anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(string);
                    anonymousClass2.L$2 = null;
                    anonymousClass2.I$0 = i2;
                    anonymousClass2.label = i;
                    request4 = request2;
                    ddosGuardKiller2 = this;
                    obj3 = Requests.get$default(requests, string, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass2, 4094, null);
                    if (obj3 != obj) {
                        request5 = request4;
                        cookies = ((NiceResponse) obj3).getCookies();
                        ddosGuardKiller2.savedCookiesMap.put(request5.url().host(), cookies);
                        request2 = request5;
                    }
                }
                return MainActivityKt.getApp().getBaseClient().newCall(request2.newBuilder().headers(RequestsHelperKt.getHeaders(MapsKt.toMap(request2.headers()), MapsKt.plus(cookies, NiceResponseKt.getCookies(request2)))).build()).execute();
            }
            Regex regex2 = new Regex("'(.*?)'");
            Requests app2 = MainActivityKt.getApp();
            anonymousClass1.L$0 = request;
            anonymousClass1.L$1 = this;
            anonymousClass1.L$2 = regex2;
            anonymousClass1.label = 1;
            AnonymousClass1 anonymousClass3 = anonymousClass1;
            obj = coroutine_suspended;
            i2 = 0;
            i = 2;
            obj2 = Requests.get$default(app2, "https://check.ddos-guard.net/check.js", null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass3, 4094, null);
            anonymousClass2 = anonymousClass3;
            if (obj2 != obj) {
                ddosGuardKiller = this;
                request3 = request;
                regex = regex2;
            }
            return obj;
        }
        if (i3 == 1) {
            Regex regex3 = (Regex) anonymousClass1.L$2;
            ddosGuardKiller = (DdosGuardKiller) anonymousClass1.L$1;
            request3 = (Request) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj4);
            obj2 = obj4;
            i2 = 0;
            regex = regex3;
            anonymousClass2 = anonymousClass1;
            obj = coroutine_suspended;
            i = 2;
        } else {
            if (i3 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i4 = anonymousClass1.I$0;
            request5 = (Request) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj4);
            obj3 = obj4;
            ddosGuardKiller2 = this;
        }
        cookies = ((NiceResponse) obj3).getCookies();
        ddosGuardKiller2.savedCookiesMap.put(request5.url().host(), cookies);
        request2 = request5;
        return MainActivityKt.getApp().getBaseClient().newCall(request2.newBuilder().headers(RequestsHelperKt.getHeaders(MapsKt.toMap(request2.headers()), MapsKt.plus(cookies, NiceResponseKt.getCookies(request2)))).build()).execute();
        MatchResult matchResultFind$default = Regex.find$default(regex, ((NiceResponse) obj2).getText(), i2, i, null);
        if (matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null) {
            request2 = request3;
            str = null;
        } else {
            str = groupValues.get(1);
            request2 = request3;
        }
        ddosGuardKiller.ddosBypassPath = str;
        cookies = this.savedCookiesMap.get(request2.url().host());
        if (cookies == null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(request2.url().scheme());
            sb2.append("://");
            sb2.append(request2.url().host());
            str2 = this.ddosBypassPath;
            if (str2 == null) {
                str2 = "";
            }
            sb2.append(str2);
            String string2 = sb2.toString();
            Requests requests2 = new Requests(null, null, null, null, null, 0, null, 0L, null, 511, null);
            anonymousClass2.L$0 = request2;
            anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(string2);
            anonymousClass2.L$2 = null;
            anonymousClass2.I$0 = i2;
            anonymousClass2.label = i;
            request4 = request2;
            ddosGuardKiller2 = this;
            obj3 = Requests.get$default(requests2, string2, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass2, 4094, null);
            if (obj3 != obj) {
                request5 = request4;
                cookies = ((NiceResponse) obj3).getCookies();
                ddosGuardKiller2.savedCookiesMap.put(request5.url().host(), cookies);
                request2 = request5;
            }
            return obj;
        }
        return MainActivityKt.getApp().getBaseClient().newCall(request2.newBuilder().headers(RequestsHelperKt.getHeaders(MapsKt.toMap(request2.headers()), MapsKt.plus(cookies, NiceResponseKt.getCookies(request2)))).build()).execute();
    }
}
