package com.lagradost.cloudstream3.extractors;

import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorApiKt;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.ExtractorLinkType;
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
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function1;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Vidsonic.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000f0\u00132\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u000f0\u0013H\u0096@¢\u0006\u0002\u0010\u0017R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Vidsonic;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "mainUrl", "getMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "getUrl", "", ImagesContract.URL, "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Vidsonic extends ExtractorApi {
    private final boolean requiresReferer;
    private final String name = "Vidsonic";
    private final String mainUrl = "https://vidsonic.net";

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Vidsonic$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: Vidsonic.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Vidsonic", f = "Vidsonic.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1}, l = {35, 53}, m = "getUrl", n = {ImagesContract.URL, "referer", "subtitleCallback", "callback", ImagesContract.URL, "referer", "subtitleCallback", "callback", "response", "encodedStreamUrl", "streamUrl"}, nl = {36, 52}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"}, v = 2)
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
            return Vidsonic.this.getUrl(null, null, null, null, this);
        }
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
    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public Object getUrl(String str, String str2, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        int i;
        Object obj;
        String str3;
        String str4;
        Function1<? super SubtitleFile, Unit> function3;
        Function1<? super ExtractorLink, Unit> function4;
        Function1<? super ExtractorLink, Unit> function5;
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
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object objNewExtractorLink$default = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass2.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objNewExtractorLink$default);
            Requests app2 = MainActivityKt.getApp();
            anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(str);
            anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str2);
            anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(function1);
            anonymousClass2.L$3 = function2;
            anonymousClass2.label = 1;
            i = 2;
            obj = Requests.get$default(app2, str, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass2, 4094, null);
            if (obj != coroutine_suspended) {
                anonymousClass2 = anonymousClass2;
                str3 = str;
                str4 = str2;
                function3 = function1;
                function4 = function2;
            }
            anonymousClass2 = anonymousClass2;
            return coroutine_suspended;
        }
        if (i2 == 1) {
            Function1<? super ExtractorLink, Unit> function6 = (Function1) anonymousClass2.L$3;
            function3 = (Function1) anonymousClass2.L$2;
            str4 = (String) anonymousClass2.L$1;
            str3 = (String) anonymousClass2.L$0;
            ResultKt.throwOnFailure(objNewExtractorLink$default);
            function4 = function6;
            obj = objNewExtractorLink$default;
            i = 2;
        } else {
            if (i2 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            function5 = (Function1) anonymousClass2.L$7;
            ResultKt.throwOnFailure(objNewExtractorLink$default);
        }
        function5.invoke(objNewExtractorLink$default);
        return Unit.INSTANCE;
        String text = ((NiceResponse) obj).getText();
        String strReplace$default = StringsKt.replace$default(StringsKt.substringBefore$default(StringsKt.substringAfter$default(text, "const _0x1 = ", (String) null, i, (Object) null), ";", (String) null, i, (Object) null), "'", "", false, 4, (Object) null);
        List<String> listChunked = StringsKt.chunked(StringsKt.replace$default(strReplace$default, "|", "", false, 4, (Object) null), i);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listChunked, 10));
        Iterator<T> it = listChunked.iterator();
        while (it.hasNext()) {
            arrayList.add(Boxing.boxChar((char) Integer.parseInt((String) it.next(), CharsKt.checkRadix(16))));
        }
        String string = StringsKt.reversed((CharSequence) CollectionsKt.joinToString$default(arrayList, "", null, null, 0, null, null, 62, null)).toString();
        String name = getName();
        String name2 = getName();
        ExtractorLinkType extractorLinkType = ExtractorLinkType.M3U8;
        anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(str3);
        anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str4);
        anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(function3);
        anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function4);
        anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(text);
        anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(strReplace$default);
        anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(string);
        anonymousClass2.L$7 = function4;
        anonymousClass2.label = i;
        objNewExtractorLink$default = ExtractorApiKt.newExtractorLink$default(name, name2, string, extractorLinkType, null, anonymousClass2, 16, null);
        if (objNewExtractorLink$default != coroutine_suspended) {
            function5 = function4;
            function5.invoke(objNewExtractorLink$default);
            return Unit.INSTANCE;
        }
        anonymousClass2 = anonymousClass2;
        return coroutine_suspended;
    }
}
