package com.lagradost.cloudstream3.extractors;

import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.JsHunter;
import com.lagradost.cloudstream3.utils.M3u8Helper;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/* JADX INFO: compiled from: StreamSilk.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00110\u00152\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00110\u0015H\u0096@¢\u0006\u0002\u0010\u0019R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/StreamSilk;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "mainUrl", "getMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "srcRegex", "Lkotlin/text/Regex;", "getUrl", "", ImagesContract.URL, "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class StreamSilk extends ExtractorApi {
    private final String name = "StreamSilk";
    private final String mainUrl = "https://streamsilk.com";
    private final boolean requiresReferer = true;
    private final Regex srcRegex = new Regex("var urlPlay =\\s*\"(.*?m3u8.*?)\"");

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.StreamSilk$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: StreamSilk.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.StreamSilk", f = "StreamSilk.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {20, 31}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "response", "hunted", "script", "link", "headers", "$i$a$-let-StreamSilk$getUrl$3", "$i$a$-let-StreamSilk$getUrl$3$1", "$i$a$-let-StreamSilk$getUrl$3$1$1"}, nl = {23, 36}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "I$0", "I$1", "I$2"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return StreamSilk.getUrl$suspendImpl(StreamSilk.this, (String) null, (String) null, (Function1<? super SubtitleFile, Unit>) null, (Function1<? super ExtractorLink, Unit>) null, (Continuation<? super Unit>) this);
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

    /* JADX WARN: Code duplicated, block: B:48:0x020d A[LOOP:0: B:46:0x0207->B:48:0x020d, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    static /* synthetic */ Object getUrl$suspendImpl(StreamSilk streamSilk, String str, String str2, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        int i;
        String str3;
        String str4;
        Function1<? super ExtractorLink, Unit> function3;
        Object obj;
        Function1<? super SubtitleFile, Unit> function4;
        Object next;
        String strHtml;
        String strDehunt;
        MatchResult matchResultFind$default;
        List<String> groupValues;
        String str5;
        String string;
        Function1<? super ExtractorLink, Unit> function5;
        String strHtml2;
        Iterator it;
        StreamSilk streamSilk2 = streamSilk;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = streamSilk2.new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = streamSilk2.new AnonymousClass1(continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object objGenerateM3u8$default = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass2.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objGenerateM3u8$default);
            Requests app2 = MainActivityKt.getApp();
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to("Accept", "*/*"));
            anonymousClass2.L$0 = streamSilk2;
            anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str);
            anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str2);
            anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function1);
            anonymousClass2.L$4 = function2;
            anonymousClass2.label = 1;
            i = 2;
            Object obj2 = Requests.get$default(app2, str, mapMapOf, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass2, 4092, null);
            if (obj2 != coroutine_suspended) {
                anonymousClass2 = anonymousClass2;
                str3 = str;
                str4 = str2;
                function3 = function2;
                obj = obj2;
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
            StreamSilk streamSilk3 = (StreamSilk) anonymousClass2.L$0;
            ResultKt.throwOnFailure(objGenerateM3u8$default);
            function3 = function6;
            obj = objGenerateM3u8$default;
            i = 2;
            streamSilk2 = streamSilk3;
        } else {
            if (i2 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i3 = anonymousClass2.I$2;
            int i4 = anonymousClass2.I$1;
            int i5 = anonymousClass2.I$0;
            function5 = (Function1) anonymousClass2.L$4;
            ResultKt.throwOnFailure(objGenerateM3u8$default);
        }
        it = ((Iterable) objGenerateM3u8$default).iterator();
        while (it.hasNext()) {
            function5.invoke(it.next());
        }
        return Unit.INSTANCE;
        NiceResponse niceResponse = (NiceResponse) obj;
        Elements elementsSelect = niceResponse.getDocument().select("script");
        Intrinsics.checkNotNullExpressionValue(elementsSelect, "select(...)");
        Iterator<T> it2 = elementsSelect.iterator();
        do {
            if (!it2.hasNext()) {
                next = null;
                break;
            }
            next = it2.next();
            strHtml2 = ((Element) next).html();
            Intrinsics.checkNotNullExpressionValue(strHtml2, "html(...)");
        } while (!StringsKt.contains$default((CharSequence) strHtml2, (CharSequence) "h,u,n,t,e,r", false, i, (Object) null));
        Element element = (Element) next;
        if (element != null && (strHtml = element.html()) != null && (strDehunt = new JsHunter(strHtml).dehunt()) != null && (matchResultFind$default = Regex.find$default(streamSilk2.srcRegex, strDehunt, 0, i, null)) != null && (groupValues = matchResultFind$default.getGroupValues()) != null && (str5 = groupValues.get(1)) != null && (string = StringsKt.trim((CharSequence) str5).toString()) != null) {
            Pair[] pairArr = new Pair[3];
            pairArr[0] = TuplesKt.to("Origin", streamSilk2.getMainUrl());
            int i6 = i;
            pairArr[1] = TuplesKt.to("Referer", streamSilk2.getMainUrl() + '/');
            pairArr[i6] = TuplesKt.to("User-Agent", MainAPIKt.USER_AGENT);
            Map mapMapOf2 = MapsKt.mapOf(pairArr);
            Function1<? super SubtitleFile, Unit> function7 = function4;
            M3u8Helper.Companion companion = M3u8Helper.INSTANCE;
            String name = streamSilk2.getName();
            StringBuilder sb = new StringBuilder();
            StreamSilk streamSilk4 = streamSilk2;
            sb.append(streamSilk4.getMainUrl());
            sb.append('/');
            String string2 = sb.toString();
            anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(streamSilk4);
            anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str3);
            anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str4);
            anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function7);
            anonymousClass2.L$4 = function3;
            anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(niceResponse);
            anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(strHtml);
            anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(strDehunt);
            anonymousClass2.L$8 = SpillingKt.nullOutSpilledVariable(string);
            anonymousClass2.L$9 = SpillingKt.nullOutSpilledVariable(mapMapOf2);
            anonymousClass2.I$0 = 0;
            anonymousClass2.I$1 = 0;
            anonymousClass2.I$2 = 0;
            anonymousClass2.label = i6;
            objGenerateM3u8$default = M3u8Helper.Companion.generateM3u8$default(companion, name, string, string2, null, mapMapOf2, null, anonymousClass2, 40, null);
            if (objGenerateM3u8$default != coroutine_suspended) {
                function5 = function3;
                it = ((Iterable) objGenerateM3u8$default).iterator();
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
