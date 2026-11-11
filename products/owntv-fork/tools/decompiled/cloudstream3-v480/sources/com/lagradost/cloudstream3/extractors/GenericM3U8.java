package com.lagradost.cloudstream3.extractors;

import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.network.WebViewResolver;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.M3u8Helper;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import java.util.ArrayList;
import java.util.Iterator;
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
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: GenericM3U8.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005H\u0096@¢\u0006\u0002\u0010\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u0014\u0010\r\u001a\u00020\u000eX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0017"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/GenericM3U8;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "mainUrl", "getMainUrl", "setMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "getUrl", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", ImagesContract.URL, "referer", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class GenericM3U8 extends ExtractorApi {
    private final boolean requiresReferer;
    private String name = "Upstream";
    private String mainUrl = "https://upstream.to";

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.GenericM3U8$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: GenericM3U8.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.GenericM3U8", f = "GenericM3U8.kt", i = {0, 0, 0, 1, 1, 1, 1, 1}, l = {16, 23}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "$this", ImagesContract.URL, "referer", "response", "sources"}, nl = {21, 28}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return GenericM3U8.getUrl$suspendImpl(GenericM3U8.this, (String) null, (String) null, (Continuation<? super List<? extends ExtractorLink>>) this);
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

    /* JADX WARN: Code duplicated, block: B:29:0x0121 A[LOOP:0: B:27:0x011b->B:29:0x0121, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    static /* synthetic */ Object getUrl$suspendImpl(GenericM3U8 genericM3U8, String str, String str2, Continuation<? super List<? extends ExtractorLink>> continuation) {
        AnonymousClass1 anonymousClass1;
        int i;
        String str3;
        Object obj;
        String str4;
        List list;
        Iterator it;
        GenericM3U8 genericM3U9 = genericM3U8;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = genericM3U9.new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = genericM3U9.new AnonymousClass1(continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object objGenerateM3u8$default = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass2.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objGenerateM3u8$default);
            Requests app2 = MainActivityKt.getApp();
            WebViewResolver webViewResolver = new WebViewResolver(new Regex("master\\.m3u8"), null, null, false, null, null, 0L, 126, null);
            anonymousClass2.L$0 = genericM3U9;
            anonymousClass2.L$1 = str;
            anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str2);
            anonymousClass2.label = 1;
            i = 2;
            Object obj2 = Requests.get$default(app2, str, null, null, null, null, false, 0, null, 0L, webViewResolver, false, null, anonymousClass2, 3582, null);
            if (obj2 != coroutine_suspended) {
                anonymousClass2 = anonymousClass2;
                str3 = str;
                obj = obj2;
                str4 = str2;
            }
            anonymousClass2 = anonymousClass2;
            return coroutine_suspended;
        }
        if (i2 == 1) {
            String str5 = (String) anonymousClass2.L$2;
            String str6 = (String) anonymousClass2.L$1;
            GenericM3U8 genericM3U10 = (GenericM3U8) anonymousClass2.L$0;
            ResultKt.throwOnFailure(objGenerateM3u8$default);
            str3 = str6;
            str4 = str5;
            genericM3U9 = genericM3U10;
            obj = objGenerateM3u8$default;
            i = 2;
        } else {
            if (i2 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            list = (List) anonymousClass2.L$4;
            ResultKt.throwOnFailure(objGenerateM3u8$default);
        }
        it = ((Iterable) objGenerateM3u8$default).iterator();
        while (it.hasNext()) {
            list.add((ExtractorLink) it.next());
        }
        return list;
        NiceResponse niceResponse = (NiceResponse) obj;
        ArrayList arrayList = new ArrayList();
        if (!StringsKt.contains$default((CharSequence) niceResponse.getUrl(), (CharSequence) "m3u8", false, i, (Object) null)) {
            return arrayList;
        }
        String str7 = str4;
        M3u8Helper.Companion companion = M3u8Helper.INSTANCE;
        String name = genericM3U9.getName();
        String url = niceResponse.getUrl();
        Map map = MapsKt.toMap(niceResponse.getHeaders());
        anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(genericM3U9);
        anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str3);
        anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str7);
        anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(niceResponse);
        anonymousClass2.L$4 = arrayList;
        anonymousClass2.label = i;
        objGenerateM3u8$default = M3u8Helper.Companion.generateM3u8$default(companion, name, url, str3, null, map, null, anonymousClass2, 40, null);
        if (objGenerateM3u8$default != coroutine_suspended) {
            list = arrayList;
            it = ((Iterable) objGenerateM3u8$default).iterator();
            while (it.hasNext()) {
                list.add((ExtractorLink) it.next());
            }
            return list;
        }
        anonymousClass2 = anonymousClass2;
        return coroutine_suspended;
    }
}
