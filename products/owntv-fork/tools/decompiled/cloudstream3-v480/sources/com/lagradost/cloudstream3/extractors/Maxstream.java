package com.lagradost.cloudstream3.extractors;

import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.JsUnpacker;
import com.lagradost.cloudstream3.utils.M3u8Helper;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchGroup;
import kotlin.text.MatchGroupCollection;
import kotlin.text.MatchResult;
import kotlin.text.Regex;

/* JADX INFO: compiled from: Maxstream.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00122\u0006\u0010\u0014\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005H\u0096@¢\u0006\u0002\u0010\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u0014\u0010\r\u001a\u00020\u000eX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0017"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Maxstream;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "mainUrl", "getMainUrl", "setMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "getUrl", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", ImagesContract.URL, "referer", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class Maxstream extends ExtractorApi {
    private final boolean requiresReferer;
    private String name = "Maxstream";
    private String mainUrl = "https://maxstream.video/";

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Maxstream$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: Maxstream.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Maxstream", f = "Maxstream.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1}, l = {13, 18}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "extractedLinksList", "$this", ImagesContract.URL, "referer", "extractedLinksList", "response", "jstounpack", "unpacjed", "extractedUrl"}, nl = {14, 23}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
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

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Maxstream.getUrl$suspendImpl(Maxstream.this, (String) null, (String) null, (Continuation<? super List<? extends ExtractorLink>>) this);
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

    /* JADX WARN: Code duplicated, block: B:41:0x0172  */
    /* JADX WARN: Code duplicated, block: B:45:0x0183 A[LOOP:0: B:43:0x017d->B:45:0x0183, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    static /* synthetic */ Object getUrl$suspendImpl(Maxstream maxstream, String str, String str2, Continuation<? super List<? extends ExtractorLink>> continuation) {
        AnonymousClass1 anonymousClass1;
        ArrayList arrayList;
        Maxstream maxstream2;
        String str3;
        Object obj;
        String str4;
        int i;
        String value;
        String strUnpack;
        Object objGenerateM3u8$default;
        List list;
        MatchResult matchResultFind$default;
        MatchGroupCollection groups;
        MatchGroup matchGroup;
        MatchGroupCollection groups2;
        Iterator it;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = maxstream.new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = maxstream.new AnonymousClass1(continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object obj2 = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass2.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj2);
            arrayList = new ArrayList();
            Requests app2 = MainActivityKt.getApp();
            anonymousClass2.L$0 = maxstream;
            anonymousClass2.L$1 = str;
            anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str2);
            anonymousClass2.L$3 = arrayList;
            anonymousClass2.label = 1;
            Object obj3 = Requests.get$default(app2, str, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass2, 4094, null);
            if (obj3 != coroutine_suspended) {
                anonymousClass2 = anonymousClass2;
                maxstream2 = maxstream;
                str3 = str;
                obj = obj3;
                str4 = str2;
            }
            anonymousClass2 = anonymousClass2;
            return coroutine_suspended;
        }
        if (i2 == 1) {
            List list2 = (List) anonymousClass2.L$3;
            str4 = (String) anonymousClass2.L$2;
            str3 = (String) anonymousClass2.L$1;
            maxstream2 = (Maxstream) anonymousClass2.L$0;
            ResultKt.throwOnFailure(obj2);
            arrayList = list2;
            obj = obj2;
        } else {
            if (i2 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            list = (List) anonymousClass2.L$3;
            ResultKt.throwOnFailure(obj2);
        }
        it = ((Iterable) obj2).iterator();
        while (it.hasNext()) {
            list.add((ExtractorLink) it.next());
        }
        return list;
        String text = ((NiceResponse) obj).getText();
        String value2 = null;
        MatchResult matchResultFind$default2 = Regex.find$default(new Regex("cript\">eval((.|\\n)*?)</script>"), text, 0, r4, null);
        if (matchResultFind$default2 != null && (groups2 = matchResultFind$default2.getGroups()) != null) {
            i = 1;
            MatchGroup matchGroup2 = groups2.get(1);
            if (matchGroup2 != null) {
                value = matchGroup2.getValue();
            }
            strUnpack = new JsUnpacker(value).unpack();
            if (strUnpack != null && (matchResultFind$default = Regex.find$default(new Regex("src:\"((.|\\n)*?)\",type"), strUnpack, 0, 2, null)) != null && (groups = matchResultFind$default.getGroups()) != null && (matchGroup = groups.get(i)) != null) {
                value2 = matchGroup.getValue();
            }
            String strValueOf = String.valueOf(value2);
            String str5 = str4;
            M3u8Helper.Companion companion = M3u8Helper.INSTANCE;
            String name = maxstream2.getName();
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to("referer", str3));
            anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(maxstream2);
            anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str3);
            anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str5);
            anonymousClass2.L$3 = arrayList;
            anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(text);
            anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(value);
            anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(strUnpack);
            anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(strValueOf);
            anonymousClass2.label = r4;
            objGenerateM3u8$default = M3u8Helper.Companion.generateM3u8$default(companion, name, strValueOf, str3, null, mapMapOf, null, anonymousClass2, 40, null);
            if (objGenerateM3u8$default != coroutine_suspended) {
                List list3 = arrayList;
                obj2 = objGenerateM3u8$default;
                list = list3;
                it = ((Iterable) obj2).iterator();
                while (it.hasNext()) {
                    list.add((ExtractorLink) it.next());
                }
                return list;
            }
            anonymousClass2 = anonymousClass2;
            return coroutine_suspended;
        }
        i = 1;
        value = null;
        strUnpack = new JsUnpacker(value).unpack();
        if (strUnpack != null) {
            value2 = matchGroup.getValue();
        }
        String strValueOf2 = String.valueOf(value2);
        String str6 = str4;
        M3u8Helper.Companion companion2 = M3u8Helper.INSTANCE;
        String name2 = maxstream2.getName();
        Map mapMapOf2 = MapsKt.mapOf(TuplesKt.to("referer", str3));
        anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(maxstream2);
        anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str3);
        anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str6);
        anonymousClass2.L$3 = arrayList;
        anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(text);
        anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(value);
        anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(strUnpack);
        anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(strValueOf2);
        anonymousClass2.label = r4;
        objGenerateM3u8$default = M3u8Helper.Companion.generateM3u8$default(companion2, name2, strValueOf2, str3, null, mapMapOf2, null, anonymousClass2, 40, null);
        if (objGenerateM3u8$default != coroutine_suspended) {
            List list4 = arrayList;
            obj2 = objGenerateM3u8$default;
            list = list4;
            it = ((Iterable) obj2).iterator();
            while (it.hasNext()) {
                list.add((ExtractorLink) it.next());
            }
            return list;
        }
        anonymousClass2 = anonymousClass2;
        return coroutine_suspended;
    }
}
