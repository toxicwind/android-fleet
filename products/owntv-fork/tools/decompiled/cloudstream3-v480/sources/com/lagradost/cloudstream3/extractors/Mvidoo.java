package com.lagradost.cloudstream3.extractors;

import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorApiKt;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function1;
import kotlin.text.CharsKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Mvidoo.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u000e\u001a\u00020\u0005*\u00020\u0005H\u0002JH\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00100\u00142\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00100\u0014H\u0096@¢\u0006\u0002\u0010\u0018R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Mvidoo;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "mainUrl", "getMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "decodeHex", "getUrl", "", ImagesContract.URL, "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class Mvidoo extends ExtractorApi {
    private final String name = "Mvidoo";
    private final String mainUrl = "https://mvidoo.com";
    private final boolean requiresReferer = true;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Mvidoo$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: Mvidoo.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Mvidoo", f = "Mvidoo.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {29, 34}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "document", "data", "link", "$i$a$-let-Mvidoo$getUrl$2"}, nl = {30, 33}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "I$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
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
            return Mvidoo.getUrl$suspendImpl(Mvidoo.this, (String) null, (String) null, (Function1<? super SubtitleFile, Unit>) null, (Function1<? super ExtractorLink, Unit>) null, (Continuation<? super Unit>) this);
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

    private final String decodeHex(String str) {
        if (str.length() % 2 != 0) {
            throw new IllegalArgumentException("Must have an even length".toString());
        }
        List<String> listChunked = StringsKt.chunked(str, 2);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listChunked, 10));
        Iterator<T> it = listChunked.iterator();
        while (it.hasNext()) {
            arrayList.add(Byte.valueOf((byte) Integer.parseInt((String) it.next(), CharsKt.checkRadix(16))));
        }
        return StringsKt.decodeToString(CollectionsKt.toByteArray(arrayList));
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    static /* synthetic */ Object getUrl$suspendImpl(Mvidoo mvidoo, String str, String str2, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        Function1<? super ExtractorLink, Unit> function3;
        Mvidoo mvidoo2;
        String str3;
        String str4;
        Object obj;
        Function1<? super SubtitleFile, Unit> function4;
        List<String> groupValues;
        String str5;
        String strRemoveSurrounding;
        String strReplace$default;
        String strReplace$default2;
        List listSplit$default;
        String strJoinToString$default;
        List<String> groupValues2;
        String str6;
        Function1<? super ExtractorLink, Unit> function5;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = mvidoo.new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = mvidoo.new AnonymousClass1(continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object obj2 = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass2.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj2);
            Requests app2 = MainActivityKt.getApp();
            anonymousClass2.L$0 = mvidoo;
            anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str);
            anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str2);
            anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function1);
            function3 = function2;
            anonymousClass2.L$4 = function3;
            anonymousClass2.label = 1;
            Object obj3 = Requests.get$default(app2, str, null, str2, null, null, false, 0, null, 0L, null, false, null, anonymousClass2, 4090, null);
            if (obj3 != coroutine_suspended) {
                anonymousClass2 = anonymousClass2;
                mvidoo2 = mvidoo;
                str3 = str;
                str4 = str2;
                obj = obj3;
                function4 = function1;
            }
            anonymousClass2 = anonymousClass2;
            return coroutine_suspended;
        }
        if (i == 1) {
            Function1<? super ExtractorLink, Unit> function6 = (Function1) anonymousClass2.L$4;
            function4 = (Function1) anonymousClass2.L$3;
            str4 = (String) anonymousClass2.L$2;
            str3 = (String) anonymousClass2.L$1;
            mvidoo2 = (Mvidoo) anonymousClass2.L$0;
            ResultKt.throwOnFailure(obj2);
            function3 = function6;
            obj = obj2;
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = anonymousClass2.I$0;
            function5 = (Function1) anonymousClass2.L$8;
            ResultKt.throwOnFailure(obj2);
        }
        function5.invoke(obj2);
        return Unit.INSTANCE;
        String text = ((NiceResponse) obj).getText();
        MatchResult matchResultFind$default = Regex.find$default(new Regex("\\{var\\s*[^\\s]+\\s*=\\s*(\\[[^]]+])"), text, 0, r4, null);
        if (matchResultFind$default != null && (groupValues = matchResultFind$default.getGroupValues()) != null && (str5 = groupValues.get(1)) != null && (strRemoveSurrounding = StringsKt.removeSurrounding(str5, (CharSequence) "[", (CharSequence) "]")) != null && (strReplace$default = StringsKt.replace$default(strRemoveSurrounding, "\"", "", false, 4, (Object) null)) != null && (strReplace$default2 = StringsKt.replace$default(strReplace$default, "\\x", "", false, 4, (Object) null)) != null && (listSplit$default = StringsKt.split$default((CharSequence) strReplace$default2, new String[]{","}, false, 0, 6, (Object) null)) != null) {
            List list = listSplit$default;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(mvidoo2.decodeHex((String) it.next()));
            }
            List listReversed = CollectionsKt.reversed(arrayList);
            if (listReversed != null && (strJoinToString$default = CollectionsKt.joinToString$default(listReversed, "", null, null, 0, null, null, 62, null)) != null) {
                MatchResult matchResultFind$default2 = Regex.find$default(new Regex("source\\s*src=\"([^\"]+)"), strJoinToString$default, 0, r4, null);
                if (matchResultFind$default2 != null && (groupValues2 = matchResultFind$default2.getGroupValues()) != null && (str6 = groupValues2.get(1)) != null) {
                    Function1<? super SubtitleFile, Unit> function7 = function4;
                    String name = mvidoo2.getName();
                    String name2 = mvidoo2.getName();
                    Mvidoo$getUrl$2$1 mvidoo$getUrl$2$1 = new Mvidoo$getUrl$2$1(mvidoo2, null);
                    anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(mvidoo2);
                    anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str3);
                    anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str4);
                    anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function7);
                    anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(function3);
                    anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(text);
                    anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(strJoinToString$default);
                    anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(str6);
                    anonymousClass2.L$8 = function3;
                    anonymousClass2.I$0 = 0;
                    anonymousClass2.label = 2;
                    Object objNewExtractorLink$default = ExtractorApiKt.newExtractorLink$default(name, name2, str6, null, mvidoo$getUrl$2$1, anonymousClass2, 8, null);
                    if (objNewExtractorLink$default != coroutine_suspended) {
                        Function1<? super ExtractorLink, Unit> function8 = function3;
                        obj2 = objNewExtractorLink$default;
                        function5 = function8;
                        function5.invoke(obj2);
                    }
                    anonymousClass2 = anonymousClass2;
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
        }
        return Unit.INSTANCE;
    }
}
