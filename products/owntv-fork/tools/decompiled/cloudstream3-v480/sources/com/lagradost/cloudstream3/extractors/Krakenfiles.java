package com.lagradost.cloudstream3.extractors;

import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorApiKt;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function1;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/* JADX INFO: compiled from: Krakenfiles.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000f0\u00132\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u000f0\u0013H\u0096@¢\u0006\u0002\u0010\u0017R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Krakenfiles;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "mainUrl", "getMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "getUrl", "", ImagesContract.URL, "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class Krakenfiles extends ExtractorApi {
    private final boolean requiresReferer;
    private final String name = "Krakenfiles";
    private final String mainUrl = "https://krakenfiles.com";

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Krakenfiles$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: Krakenfiles.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Krakenfiles", f = "Krakenfiles.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1}, l = {23, 27}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "id", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "id", "doc", "link"}, nl = {24, 26}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Krakenfiles.getUrl$suspendImpl(Krakenfiles.this, (String) null, (String) null, (Function1<? super SubtitleFile, Unit>) null, (Function1<? super ExtractorLink, Unit>) null, (Continuation<? super Unit>) this);
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

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public String getMainUrl() {
        return this.mainUrl;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public boolean getRequiresReferer() {
        return this.requiresReferer;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    static /* synthetic */ Object getUrl$suspendImpl(Krakenfiles krakenfiles, String str, String str2, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        String strAttr;
        int i;
        Krakenfiles krakenfiles2;
        String str3;
        String str4;
        Function1<? super SubtitleFile, Unit> function3;
        Object obj;
        String str5;
        Function1<? super ExtractorLink, Unit> function4;
        List<String> groupValues;
        Function1<? super ExtractorLink, Unit> function5;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = krakenfiles.new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = krakenfiles.new AnonymousClass1(continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object obj2 = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass2.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj2);
            MatchResult matchResultFind$default = Regex.find$default(new Regex("/(?:view|embed-video)/([\\da-zA-Z]+)"), str, 0, 2, null);
            String str6 = (matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null) ? null : groupValues.get(1);
            Requests app2 = MainActivityKt.getApp();
            String str7 = krakenfiles.getMainUrl() + "/embed-video/" + str6;
            anonymousClass2.L$0 = krakenfiles;
            anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str);
            anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str2);
            anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function1);
            anonymousClass2.L$4 = function2;
            anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(str6);
            anonymousClass2.label = 1;
            strAttr = null;
            i = 2;
            Object obj3 = Requests.get$default(app2, str7, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass2, 4094, null);
            anonymousClass2 = anonymousClass2;
            if (obj3 != coroutine_suspended) {
                krakenfiles2 = krakenfiles;
                str3 = str;
                str4 = str2;
                function3 = function1;
                obj = obj3;
                str5 = str6;
                function4 = function2;
            }
            return coroutine_suspended;
        }
        if (i2 == 1) {
            String str8 = (String) anonymousClass2.L$5;
            Function1<? super ExtractorLink, Unit> function6 = (Function1) anonymousClass2.L$4;
            function3 = (Function1) anonymousClass2.L$3;
            str4 = (String) anonymousClass2.L$2;
            str3 = (String) anonymousClass2.L$1;
            krakenfiles2 = (Krakenfiles) anonymousClass2.L$0;
            ResultKt.throwOnFailure(obj2);
            strAttr = null;
            obj = obj2;
            function4 = function6;
            str5 = str8;
            i = 2;
        } else {
            if (i2 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            function5 = (Function1) anonymousClass2.L$8;
            ResultKt.throwOnFailure(obj2);
        }
        function5.invoke(obj2);
        return Unit.INSTANCE;
        Document document = ((NiceResponse) obj).getDocument();
        Element elementSelectFirst = document.selectFirst("source");
        if (elementSelectFirst != null) {
            strAttr = elementSelectFirst.attr("src");
        }
        String str9 = str5;
        String name = krakenfiles2.getName();
        String name2 = krakenfiles2.getName();
        if (strAttr != null) {
            String strHttpsify = ExtractorApiKt.httpsify(strAttr);
            anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(krakenfiles2);
            anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str3);
            anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str4);
            anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function3);
            anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(function4);
            anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(str9);
            anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(document);
            anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(strAttr);
            anonymousClass2.L$8 = function4;
            anonymousClass2.label = i;
            Object objNewExtractorLink$default = ExtractorApiKt.newExtractorLink$default(name, name2, strHttpsify, null, null, anonymousClass2, 24, null);
            if (objNewExtractorLink$default != coroutine_suspended) {
                Function1<? super ExtractorLink, Unit> function7 = function4;
                obj2 = objNewExtractorLink$default;
                function5 = function7;
                function5.invoke(obj2);
                return Unit.INSTANCE;
            }
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
