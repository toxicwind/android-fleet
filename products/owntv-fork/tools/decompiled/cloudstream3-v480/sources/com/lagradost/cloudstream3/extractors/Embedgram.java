package com.lagradost.cloudstream3.extractors;

import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorApiKt;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jsoup.nodes.Document;

/* JADX INFO: compiled from: Embedgram.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000f0\u00132\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u000f0\u0013H\u0096@¢\u0006\u0002\u0010\u0017R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Embedgram;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "mainUrl", "getMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "getUrl", "", ImagesContract.URL, "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class Embedgram extends ExtractorApi {
    private final String name = "Embedgram";
    private final String mainUrl = "https://embedgram.com";
    private final boolean requiresReferer = true;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Embedgram$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: Embedgram.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Embedgram", f = "Embedgram.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1}, l = {22, 26}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "document", "link", "quality"}, nl = {23, 25}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7"}, v = 2)
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
            return Embedgram.getUrl$suspendImpl(Embedgram.this, (String) null, (String) null, (Function1<? super SubtitleFile, Unit>) null, (Function1<? super ExtractorLink, Unit>) null, (Continuation<? super Unit>) this);
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
    static /* synthetic */ Object getUrl$suspendImpl(Embedgram embedgram, String str, String str2, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        Function1<? super ExtractorLink, Unit> function3;
        Embedgram embedgram2;
        String str3;
        String str4;
        Object obj;
        Function1<? super SubtitleFile, Unit> function4;
        Function1<? super ExtractorLink, Unit> function5;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = embedgram.new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = embedgram.new AnonymousClass1(continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object obj2 = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass2.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj2);
            Requests app2 = MainActivityKt.getApp();
            anonymousClass2.L$0 = embedgram;
            anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str);
            anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str2);
            anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function1);
            function3 = function2;
            anonymousClass2.L$4 = function3;
            anonymousClass2.label = 1;
            Object obj3 = Requests.get$default(app2, str, null, str2, null, null, false, 0, null, 0L, null, false, null, anonymousClass2, 4090, null);
            if (obj3 != coroutine_suspended) {
                anonymousClass2 = anonymousClass2;
                embedgram2 = embedgram;
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
            embedgram2 = (Embedgram) anonymousClass2.L$0;
            ResultKt.throwOnFailure(obj2);
            function3 = function6;
            obj = obj2;
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            function5 = (Function1) anonymousClass2.L$8;
            ResultKt.throwOnFailure(obj2);
        }
        function5.invoke(obj2);
        return Unit.INSTANCE;
        Document document = ((NiceResponse) obj).getDocument();
        String strAttr = document.select("video source:last-child").attr("src");
        Intrinsics.checkNotNullExpressionValue(strAttr, "attr(...)");
        String strAttr2 = document.select("video source:last-child").attr("title");
        Intrinsics.checkNotNullExpressionValue(strAttr2, "attr(...)");
        Function1<? super SubtitleFile, Unit> function7 = function4;
        String name = embedgram2.getName();
        String name2 = embedgram2.getName();
        String str5 = str4;
        String strHttpsify = ExtractorApiKt.httpsify(strAttr);
        AnonymousClass2 anonymousClass3 = embedgram2.new AnonymousClass2(strAttr2, null);
        anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(embedgram2);
        anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str3);
        anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str5);
        anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function7);
        anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(function3);
        anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(document);
        anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(strAttr);
        anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(strAttr2);
        anonymousClass2.L$8 = function3;
        anonymousClass2.label = 2;
        Object objNewExtractorLink$default = ExtractorApiKt.newExtractorLink$default(name, name2, strHttpsify, null, anonymousClass3, anonymousClass2, 8, null);
        if (objNewExtractorLink$default != coroutine_suspended) {
            Function1<? super ExtractorLink, Unit> function8 = function3;
            obj2 = objNewExtractorLink$default;
            function5 = function8;
            function5.invoke(obj2);
            return Unit.INSTANCE;
        }
        anonymousClass2 = anonymousClass2;
        return coroutine_suspended;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Embedgram$getUrl$2, reason: invalid class name */
    /* JADX INFO: compiled from: Embedgram.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Embedgram$getUrl$2", f = "Embedgram.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $quality;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$quality = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = Embedgram.this.new AnonymousClass2(this.$quality, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(extractorLink, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            ExtractorLink extractorLink = (ExtractorLink) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            extractorLink.setReferer(Embedgram.this.getMainUrl() + '/');
            extractorLink.setQuality(ExtractorApiKt.getQualityFromName(this.$quality));
            extractorLink.setHeaders(MapsKt.mapOf(TuplesKt.to("Range", "bytes=0-")));
            return Unit.INSTANCE;
        }
    }
}
