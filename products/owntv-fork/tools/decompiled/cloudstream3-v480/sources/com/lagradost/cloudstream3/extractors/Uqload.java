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
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;

/* JADX INFO: compiled from: Uqload.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00140\u00182\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00140\u0018H\u0096@¢\u0006\u0002\u0010\u001cR\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u0014\u0010\r\u001a\u00020\u000eX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Uqload;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "mainUrl", "getMainUrl", "setMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "srcRegex", "Lkotlin/text/Regex;", "getUrl", "", ImagesContract.URL, "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class Uqload extends ExtractorApi {
    private String name = "Uqload";
    private String mainUrl = "https://www.uqload.com";
    private final boolean requiresReferer = true;
    private final Regex srcRegex = new Regex("sources:.*\"(.*?)\".*");

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Uqload$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: Uqload.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Uqload", f = "Uqload.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {41, 45}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "$this$getUrl_u24lambda_u240", "link", "$i$a$-with-Uqload$getUrl$2", "$i$a$-let-Uqload$getUrl$2$1"}, nl = {42, 44}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "I$0", "I$1"}, v = 2)
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
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Uqload.getUrl$suspendImpl(Uqload.this, (String) null, (String) null, (Function1<? super SubtitleFile, Unit>) null, (Function1<? super ExtractorLink, Unit>) null, (Continuation<? super Unit>) this);
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

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    static /* synthetic */ Object getUrl$suspendImpl(Uqload uqload, String str, String str2, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        Function1<? super ExtractorLink, Unit> function3;
        Uqload uqload2;
        String str3;
        String str4;
        Object obj;
        Function1<? super SubtitleFile, Unit> function4;
        List<String> groupValues;
        String str5;
        Function1<? super ExtractorLink, Unit> function5;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = uqload.new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = uqload.new AnonymousClass1(continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object obj2 = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass2.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj2);
            Requests app2 = MainActivityKt.getApp();
            anonymousClass2.L$0 = uqload;
            anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str);
            anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str2);
            anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function1);
            function3 = function2;
            anonymousClass2.L$4 = function3;
            anonymousClass2.label = 1;
            Object obj3 = Requests.get$default(app2, str, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass2, 4094, null);
            if (obj3 != coroutine_suspended) {
                anonymousClass2 = anonymousClass2;
                uqload2 = uqload;
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
            uqload2 = (Uqload) anonymousClass2.L$0;
            ResultKt.throwOnFailure(obj2);
            function3 = function6;
            obj = obj2;
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = anonymousClass2.I$1;
            int i3 = anonymousClass2.I$0;
            function5 = (Function1) anonymousClass2.L$7;
            ResultKt.throwOnFailure(obj2);
        }
        function5.invoke(obj2);
        return Unit.INSTANCE;
        NiceResponse niceResponse = (NiceResponse) obj;
        MatchResult matchResultFind$default = Regex.find$default(uqload2.srcRegex, niceResponse.getText(), 0, r4, null);
        if (matchResultFind$default != null && (groupValues = matchResultFind$default.getGroupValues()) != null && (str5 = groupValues.get(1)) != null) {
            Function1<? super SubtitleFile, Unit> function7 = function4;
            String name = uqload2.getName();
            String name2 = uqload2.getName();
            Uqload$getUrl$2$1$1 uqload$getUrl$2$1$1 = new Uqload$getUrl$2$1$1(uqload2, null);
            anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(uqload2);
            anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str3);
            anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str4);
            anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function7);
            anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(function3);
            anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(niceResponse);
            anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(str5);
            anonymousClass2.L$7 = function3;
            anonymousClass2.I$0 = 0;
            anonymousClass2.I$1 = 0;
            anonymousClass2.label = 2;
            Object objNewExtractorLink$default = ExtractorApiKt.newExtractorLink$default(name, name2, str5, null, uqload$getUrl$2$1$1, anonymousClass2, 8, null);
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
