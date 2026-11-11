package com.lagradost.cloudstream3.extractors;

import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.M3u8Helper;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import java.util.Iterator;
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
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import org.jsoup.select.Elements;

/* JADX INFO: compiled from: Moviehab.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00052\b\u0010\u0014\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00120\u00162\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00120\u0016H\u0096@¢\u0006\u0002\u0010\u001aR\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u0014\u0010\r\u001a\u00020\u000eX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Moviehab;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "mainUrl", "getMainUrl", "setMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "getUrl", "", ImagesContract.URL, "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class Moviehab extends ExtractorApi {
    private final boolean requiresReferer;
    private String name = "Moviehab";
    private String mainUrl = "https://play.moviehab.com";

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Moviehab$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: Moviehab.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Moviehab", f = "Moviehab.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, l = {25, 28, 29, 37}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "res", "it", "$i$a$-let-Moviehab$getUrl$2", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "res", "it", "link", "$i$a$-let-Moviehab$getUrl$2", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "res", "it", "link", "sub", "$i$a$-let-Moviehab$getUrl$2", "$i$a$-let-Moviehab$getUrl$2$1"}, nl = {26, 29, 33, 36}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "I$0", "I$1"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
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
            return Moviehab.getUrl$suspendImpl(Moviehab.this, (String) null, (String) null, (Function1<? super SubtitleFile, Unit>) null, (Function1<? super ExtractorLink, Unit>) null, (Continuation<? super Unit>) this);
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

    public void setMainUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mainUrl = str;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public boolean getRequiresReferer() {
        return this.requiresReferer;
    }

    /* JADX WARN: Code duplicated, block: B:31:0x020d  */
    /* JADX WARN: Code duplicated, block: B:35:0x0223 A[LOOP:0: B:33:0x021d->B:35:0x0223, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:38:0x0240  */
    /* JADX WARN: Code duplicated, block: B:44:0x02cd  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    static /* synthetic */ Object getUrl$suspendImpl(Moviehab moviehab, String str, String str2, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        char c;
        String str3;
        String str4;
        Function1<? super ExtractorLink, Unit> function3;
        Object obj;
        Function1<? super SubtitleFile, Unit> function4;
        Moviehab moviehab2;
        int i;
        Moviehab moviehab3;
        Function1<? super ExtractorLink, Unit> function5;
        NiceResponse niceResponse;
        Elements elements;
        Function1<? super SubtitleFile, Unit> function6;
        int i2;
        String url;
        NiceResponse niceResponse2;
        Function1<? super SubtitleFile, Unit> function7;
        Function1<? super ExtractorLink, Unit> function8;
        String str5;
        Moviehab moviehab4;
        Function1<? super ExtractorLink, Unit> function9;
        NiceResponse niceResponse3;
        String str6;
        Elements elements2;
        Function1<? super SubtitleFile, Unit> function10;
        Iterator it;
        String str7;
        MatchResult matchResultFind$default;
        Function1<? super SubtitleFile, Unit> function11;
        List<String> groupValues;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = moviehab.new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = moviehab.new AnonymousClass1(continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object objGenerateM3u8$default = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = anonymousClass2.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(objGenerateM3u8$default);
            Requests app2 = MainActivityKt.getApp();
            anonymousClass2.L$0 = moviehab;
            anonymousClass2.L$1 = str;
            anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str2);
            anonymousClass2.L$3 = function1;
            anonymousClass2.L$4 = function2;
            anonymousClass2.label = 1;
            c = '/';
            Object obj2 = Requests.get$default(app2, str, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass2, 4094, null);
            if (obj2 != coroutine_suspended) {
                anonymousClass2 = anonymousClass2;
                str3 = str;
                str4 = str2;
                function3 = function2;
                obj = obj2;
                function4 = function1;
                moviehab2 = moviehab;
            }
            anonymousClass2 = anonymousClass2;
            return coroutine_suspended;
        }
        if (i3 == 1) {
            Function1<? super ExtractorLink, Unit> function12 = (Function1) anonymousClass2.L$4;
            function4 = (Function1) anonymousClass2.L$3;
            String str8 = (String) anonymousClass2.L$2;
            String str9 = (String) anonymousClass2.L$1;
            Moviehab moviehab5 = (Moviehab) anonymousClass2.L$0;
            ResultKt.throwOnFailure(objGenerateM3u8$default);
            obj = objGenerateM3u8$default;
            str4 = str8;
            str3 = str9;
            moviehab2 = moviehab5;
            function3 = function12;
            c = '/';
        } else {
            if (i3 == 2) {
                int i4 = anonymousClass2.I$0;
                Elements elements3 = (Elements) anonymousClass2.L$6;
                NiceResponse niceResponse4 = (NiceResponse) anonymousClass2.L$5;
                Function1<? super ExtractorLink, Unit> function13 = (Function1) anonymousClass2.L$4;
                Function1<? super SubtitleFile, Unit> function14 = (Function1) anonymousClass2.L$3;
                String str10 = (String) anonymousClass2.L$2;
                String str11 = (String) anonymousClass2.L$1;
                moviehab3 = (Moviehab) anonymousClass2.L$0;
                ResultKt.throwOnFailure(objGenerateM3u8$default);
                function5 = function13;
                str4 = str10;
                str3 = str11;
                i2 = i4;
                elements = elements3;
                niceResponse = niceResponse4;
                i = 2;
                function6 = function14;
                url = ((NiceResponse) objGenerateM3u8$default).getUrl();
                M3u8Helper.Companion companion = M3u8Helper.INSTANCE;
                String name = moviehab3.getName();
                anonymousClass2.L$0 = moviehab3;
                anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str3);
                anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str4);
                anonymousClass2.L$3 = function6;
                anonymousClass2.L$4 = function5;
                anonymousClass2.L$5 = niceResponse;
                anonymousClass2.L$6 = elements;
                anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(url);
                anonymousClass2.I$0 = i2;
                anonymousClass2.label = 3;
                niceResponse2 = niceResponse;
                function7 = function6;
                function8 = function5;
                objGenerateM3u8$default = M3u8Helper.Companion.generateM3u8$default(companion, name, url, str3, null, null, null, anonymousClass2, 56, null);
                if (objGenerateM3u8$default != coroutine_suspended) {
                    str5 = url;
                    moviehab4 = moviehab3;
                    function9 = function8;
                    niceResponse3 = niceResponse2;
                    str6 = str3;
                    elements2 = elements;
                    function10 = function7;
                    it = ((Iterable) objGenerateM3u8$default).iterator();
                    while (it.hasNext()) {
                        function9.invoke(it.next());
                    }
                    str7 = null;
                    matchResultFind$default = Regex.find$default(new Regex("src[\"|'],\\s[\"|'](\\S+)[\"|']\\)"), niceResponse3.getText(), 0, i, null);
                    if (matchResultFind$default != null) {
                        str7 = groupValues.get(1);
                    }
                    String strAttr = elements2.select("track").attr("label");
                    Intrinsics.checkNotNullExpressionValue(strAttr, "attr(...)");
                    String str12 = moviehab4.getMainUrl() + '/' + str7;
                    anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(moviehab4);
                    anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str6);
                    anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str4);
                    anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function10);
                    anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(function9);
                    anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(niceResponse3);
                    anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(elements2);
                    anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(str5);
                    anonymousClass2.L$8 = SpillingKt.nullOutSpilledVariable(str7);
                    anonymousClass2.L$9 = function10;
                    anonymousClass2.I$0 = i2;
                    anonymousClass2.I$1 = 0;
                    anonymousClass2.label = 4;
                    objGenerateM3u8$default = MainAPIKt.newSubtitleFile$default(strAttr, str12, null, anonymousClass2, 4, null);
                    if (objGenerateM3u8$default != coroutine_suspended) {
                        function11 = function10;
                    }
                }
                anonymousClass2 = anonymousClass2;
                return coroutine_suspended;
            }
            if (i3 == 3) {
                int i5 = anonymousClass2.I$0;
                str5 = (String) anonymousClass2.L$7;
                elements2 = (Elements) anonymousClass2.L$6;
                niceResponse3 = (NiceResponse) anonymousClass2.L$5;
                function9 = (Function1) anonymousClass2.L$4;
                function10 = (Function1) anonymousClass2.L$3;
                String str13 = (String) anonymousClass2.L$2;
                str6 = (String) anonymousClass2.L$1;
                moviehab4 = (Moviehab) anonymousClass2.L$0;
                ResultKt.throwOnFailure(objGenerateM3u8$default);
                str4 = str13;
                i2 = i5;
                i = 2;
                it = ((Iterable) objGenerateM3u8$default).iterator();
                while (it.hasNext()) {
                    function9.invoke(it.next());
                }
                str7 = null;
                matchResultFind$default = Regex.find$default(new Regex("src[\"|'],\\s[\"|'](\\S+)[\"|']\\)"), niceResponse3.getText(), 0, i, null);
                if (matchResultFind$default != null && (groupValues = matchResultFind$default.getGroupValues()) != null) {
                    str7 = groupValues.get(1);
                }
                String strAttr2 = elements2.select("track").attr("label");
                Intrinsics.checkNotNullExpressionValue(strAttr2, "attr(...)");
                String str14 = moviehab4.getMainUrl() + '/' + str7;
                anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(moviehab4);
                anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str6);
                anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str4);
                anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function10);
                anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(function9);
                anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(niceResponse3);
                anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(elements2);
                anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(str5);
                anonymousClass2.L$8 = SpillingKt.nullOutSpilledVariable(str7);
                anonymousClass2.L$9 = function10;
                anonymousClass2.I$0 = i2;
                anonymousClass2.I$1 = 0;
                anonymousClass2.label = 4;
                objGenerateM3u8$default = MainAPIKt.newSubtitleFile$default(strAttr2, str14, null, anonymousClass2, 4, null);
                if (objGenerateM3u8$default != coroutine_suspended) {
                    function11 = function10;
                }
                anonymousClass2 = anonymousClass2;
                return coroutine_suspended;
            }
            if (i3 != 4) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i6 = anonymousClass2.I$1;
            int i7 = anonymousClass2.I$0;
            function11 = (Function1) anonymousClass2.L$9;
            ResultKt.throwOnFailure(objGenerateM3u8$default);
        }
        function11.invoke(objGenerateM3u8$default);
        return Unit.INSTANCE;
        NiceResponse niceResponse5 = (NiceResponse) obj;
        Elements elementsSelect = niceResponse5.getDocument().select("video#player");
        Requests app3 = MainActivityKt.getApp();
        String str15 = moviehab2.getMainUrl() + c + elementsSelect.select("source").attr("src");
        anonymousClass2.L$0 = moviehab2;
        anonymousClass2.L$1 = str3;
        anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str4);
        anonymousClass2.L$3 = function4;
        anonymousClass2.L$4 = function3;
        anonymousClass2.L$5 = niceResponse5;
        anonymousClass2.L$6 = elementsSelect;
        anonymousClass2.I$0 = 0;
        anonymousClass2.label = 2;
        Function1<? super SubtitleFile, Unit> function15 = function4;
        Function1<? super ExtractorLink, Unit> function16 = function3;
        AnonymousClass1 anonymousClass3 = anonymousClass2;
        i = 2;
        Object obj3 = Requests.get$default(app3, str15, null, str3, null, null, false, 0, null, 0L, null, false, null, anonymousClass3, 4090, null);
        anonymousClass2 = anonymousClass3;
        if (obj3 != coroutine_suspended) {
            moviehab3 = moviehab2;
            objGenerateM3u8$default = obj3;
            function5 = function16;
            niceResponse = niceResponse5;
            elements = elementsSelect;
            function6 = function15;
            i2 = 0;
            url = ((NiceResponse) objGenerateM3u8$default).getUrl();
            M3u8Helper.Companion companion2 = M3u8Helper.INSTANCE;
            String name2 = moviehab3.getName();
            anonymousClass2.L$0 = moviehab3;
            anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str3);
            anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str4);
            anonymousClass2.L$3 = function6;
            anonymousClass2.L$4 = function5;
            anonymousClass2.L$5 = niceResponse;
            anonymousClass2.L$6 = elements;
            anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(url);
            anonymousClass2.I$0 = i2;
            anonymousClass2.label = 3;
            niceResponse2 = niceResponse;
            function7 = function6;
            function8 = function5;
            objGenerateM3u8$default = M3u8Helper.Companion.generateM3u8$default(companion2, name2, url, str3, null, null, null, anonymousClass2, 56, null);
            if (objGenerateM3u8$default != coroutine_suspended) {
                str5 = url;
                moviehab4 = moviehab3;
                function9 = function8;
                niceResponse3 = niceResponse2;
                str6 = str3;
                elements2 = elements;
                function10 = function7;
                it = ((Iterable) objGenerateM3u8$default).iterator();
                while (it.hasNext()) {
                    function9.invoke(it.next());
                }
                str7 = null;
                matchResultFind$default = Regex.find$default(new Regex("src[\"|'],\\s[\"|'](\\S+)[\"|']\\)"), niceResponse3.getText(), 0, i, null);
                if (matchResultFind$default != null) {
                    str7 = groupValues.get(1);
                }
                String strAttr3 = elements2.select("track").attr("label");
                Intrinsics.checkNotNullExpressionValue(strAttr3, "attr(...)");
                String str16 = moviehab4.getMainUrl() + '/' + str7;
                anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(moviehab4);
                anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str6);
                anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str4);
                anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function10);
                anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(function9);
                anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(niceResponse3);
                anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(elements2);
                anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(str5);
                anonymousClass2.L$8 = SpillingKt.nullOutSpilledVariable(str7);
                anonymousClass2.L$9 = function10;
                anonymousClass2.I$0 = i2;
                anonymousClass2.I$1 = 0;
                anonymousClass2.label = 4;
                objGenerateM3u8$default = MainAPIKt.newSubtitleFile$default(strAttr3, str16, null, anonymousClass2, 4, null);
                if (objGenerateM3u8$default != coroutine_suspended) {
                    function11 = function10;
                    function11.invoke(objGenerateM3u8$default);
                    return Unit.INSTANCE;
                }
            }
        }
        anonymousClass2 = anonymousClass2;
        return coroutine_suspended;
    }
}
