package com.lagradost.cloudstream3.extractors;

import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.M3u8Helper;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jsoup.nodes.Document;

/* JADX INFO: compiled from: Sendvid.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00110\u00152\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00110\u0015H\u0096@¢\u0006\u0002\u0010\u0019R\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0014\u0010\f\u001a\u00020\rX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Sendvid;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "mainUrl", "getMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "getUrl", "", ImagesContract.URL, "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class Sendvid extends ExtractorApi {
    private final boolean requiresReferer;
    private String name = "Sendvid";
    private final String mainUrl = "https://sendvid.com";

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Sendvid$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: Sendvid.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Sendvid", f = "Sendvid.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1}, l = {18, 21}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "doc", "urlString"}, nl = {19, 25}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Sendvid.getUrl$suspendImpl(Sendvid.this, (String) null, (String) null, (Function1<? super SubtitleFile, Unit>) null, (Function1<? super ExtractorLink, Unit>) null, (Continuation<? super Unit>) this);
        }
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public Object getUrl(String str, String str2, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Unit> continuation) {
        return getUrl$suspendImpl(this, str, str2, function1, function2, continuation);
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

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public boolean getRequiresReferer() {
        return this.requiresReferer;
    }

    /* JADX WARN: Code duplicated, block: B:29:0x013a A[LOOP:0: B:27:0x0134->B:29:0x013a, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    static /* synthetic */ Object getUrl$suspendImpl(Sendvid sendvid, String str, String str2, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        Function1<? super ExtractorLink, Unit> function3;
        int i;
        Sendvid sendvid2;
        String str3;
        String str4;
        Object obj;
        Function1<? super SubtitleFile, Unit> function4;
        Function1<? super ExtractorLink, Unit> function5;
        Iterator it;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = sendvid.new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = sendvid.new AnonymousClass1(continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object obj2 = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass2.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj2);
            Requests app2 = MainActivityKt.getApp();
            anonymousClass2.L$0 = sendvid;
            anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str);
            anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str2);
            anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function1);
            function3 = function2;
            anonymousClass2.L$4 = function3;
            anonymousClass2.label = 1;
            i = 2;
            Object obj3 = Requests.get$default(app2, str, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass2, 4094, null);
            if (obj3 != coroutine_suspended) {
                anonymousClass2 = anonymousClass2;
                sendvid2 = sendvid;
                str3 = str;
                str4 = str2;
                obj = obj3;
                function4 = function1;
            }
            anonymousClass2 = anonymousClass2;
            return coroutine_suspended;
        }
        if (i2 == 1) {
            Function1<? super ExtractorLink, Unit> function6 = (Function1) anonymousClass2.L$4;
            function4 = (Function1) anonymousClass2.L$3;
            str4 = (String) anonymousClass2.L$2;
            str3 = (String) anonymousClass2.L$1;
            sendvid2 = (Sendvid) anonymousClass2.L$0;
            ResultKt.throwOnFailure(obj2);
            function3 = function6;
            i = 2;
            obj = obj2;
        } else {
            if (i2 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            function5 = (Function1) anonymousClass2.L$4;
            ResultKt.throwOnFailure(obj2);
        }
        it = ((Iterable) obj2).iterator();
        while (it.hasNext()) {
            function5.invoke(it.next());
        }
        return Unit.INSTANCE;
        Document document = ((NiceResponse) obj).getDocument();
        String strAttr = document.select("head meta[property=og:video:secure_url]").attr("content");
        Intrinsics.checkNotNullExpressionValue(strAttr, "attr(...)");
        if (StringsKt.contains$default((CharSequence) strAttr, (CharSequence) "m3u8", false, i, (Object) null)) {
            Function1<? super SubtitleFile, Unit> function7 = function4;
            M3u8Helper.Companion companion = M3u8Helper.INSTANCE;
            String str5 = str4;
            String name = sendvid2.getName();
            String str6 = str3;
            String mainUrl = sendvid2.getMainUrl();
            anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(sendvid2);
            anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str6);
            anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str5);
            anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function7);
            anonymousClass2.L$4 = function3;
            anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(document);
            anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(strAttr);
            anonymousClass2.label = i;
            Object objGenerateM3u8$default = M3u8Helper.Companion.generateM3u8$default(companion, name, strAttr, mainUrl, null, null, null, anonymousClass2, 56, null);
            if (objGenerateM3u8$default != coroutine_suspended) {
                Function1<? super ExtractorLink, Unit> function8 = function3;
                obj2 = objGenerateM3u8$default;
                function5 = function8;
                it = ((Iterable) obj2).iterator();
                while (it.hasNext()) {
                    function5.invoke(it.next());
                }
            }
            anonymousClass2 = anonymousClass2;
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
