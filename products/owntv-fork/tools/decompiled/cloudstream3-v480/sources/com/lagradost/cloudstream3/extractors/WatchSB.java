package com.lagradost.cloudstream3.extractors;

import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.network.WebViewResolver;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.M3u8Helper;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

/* JADX INFO: compiled from: WatchSB.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005H\u0096@¢\u0006\u0002\u0010\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u0014\u0010\r\u001a\u00020\u000eX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0017"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/WatchSB;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "mainUrl", "getMainUrl", "setMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "getUrl", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", ImagesContract.URL, "referer", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class WatchSB extends ExtractorApi {
    private final boolean requiresReferer;
    private String name = "WatchSB";
    private String mainUrl = "https://watchsb.com";

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.WatchSB$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: WatchSB.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.WatchSB", f = "WatchSB.kt", i = {0, 0, 0, 1, 1, 1, 1}, l = {15, 21}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "$this", ImagesContract.URL, "referer", "response"}, nl = {21, -1}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WatchSB.getUrl$suspendImpl(WatchSB.this, (String) null, (String) null, (Continuation<? super List<? extends ExtractorLink>>) this);
        }
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public Object getUrl(String str, String str2, Continuation<? super List<? extends ExtractorLink>> continuation) {
        return getUrl$suspendImpl(this, str, str2, continuation);
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public String getMainUrl() {
        return this.mainUrl;
    }

    public void setMainUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mainUrl = str;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public boolean getRequiresReferer() {
        return this.requiresReferer;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    static /* synthetic */ Object getUrl$suspendImpl(WatchSB watchSB, String str, String str2, Continuation<? super List<? extends ExtractorLink>> continuation) {
        AnonymousClass1 anonymousClass1;
        int i;
        String str3;
        Object obj;
        String str4;
        WatchSB watchSB2 = watchSB;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = watchSB2.new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = watchSB2.new AnonymousClass1(continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object obj2 = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass2.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj2);
            Requests app2 = MainActivityKt.getApp();
            WebViewResolver webViewResolver = new WebViewResolver(new Regex("master\\.m3u8"), null, null, false, null, null, 0L, 126, null);
            anonymousClass2.L$0 = watchSB2;
            anonymousClass2.L$1 = str;
            anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str2);
            anonymousClass2.label = 1;
            i = 2;
            Object obj3 = Requests.get$default(app2, str, null, null, null, null, false, 0, null, 0L, webViewResolver, false, null, anonymousClass2, 3582, null);
            if (obj3 != coroutine_suspended) {
                anonymousClass2 = anonymousClass2;
                str3 = str;
                obj = obj3;
                str4 = str2;
            }
            anonymousClass2 = anonymousClass2;
            return coroutine_suspended;
        }
        if (i2 != 1) {
            if (i2 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj2);
            return obj2;
        }
        String str5 = (String) anonymousClass2.L$2;
        String str6 = (String) anonymousClass2.L$1;
        WatchSB watchSB3 = (WatchSB) anonymousClass2.L$0;
        ResultKt.throwOnFailure(obj2);
        obj = obj2;
        i = 2;
        str3 = str6;
        str4 = str5;
        watchSB2 = watchSB3;
        NiceResponse niceResponse = (NiceResponse) obj;
        String str7 = str4;
        M3u8Helper.Companion companion = M3u8Helper.INSTANCE;
        String name = watchSB2.getName();
        String url = niceResponse.getUrl();
        Map map = MapsKt.toMap(niceResponse.getHeaders());
        anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(watchSB2);
        anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str3);
        anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str7);
        anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(niceResponse);
        anonymousClass2.label = i;
        Object objGenerateM3u8$default = M3u8Helper.Companion.generateM3u8$default(companion, name, url, str3, null, map, null, anonymousClass2, 40, null);
        if (objGenerateM3u8$default != coroutine_suspended) {
            return objGenerateM3u8$default;
        }
        anonymousClass2 = anonymousClass2;
        return coroutine_suspended;
    }
}
