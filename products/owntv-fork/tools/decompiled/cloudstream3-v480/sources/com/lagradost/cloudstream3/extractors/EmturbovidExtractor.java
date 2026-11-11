package com.lagradost.cloudstream3.extractors;

import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorApiKt;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.ExtractorLinkType;
import com.lagradost.cloudstream3.utils.Qualities;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: EmturbovidExtractor.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00122\u0006\u0010\u0014\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005H\u0096@¢\u0006\u0002\u0010\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u0014\u0010\r\u001a\u00020\u000eX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0017"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/EmturbovidExtractor;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "mainUrl", "getMainUrl", "setMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "getUrl", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", ImagesContract.URL, "referer", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class EmturbovidExtractor extends ExtractorApi {
    private final boolean requiresReferer;
    private String name = "Emturbovid";
    private String mainUrl = "https://emturbovid.com";

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.EmturbovidExtractor$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: EmturbovidExtractor.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.EmturbovidExtractor", f = "EmturbovidExtractor.kt", i = {0, 0, 0, 1, 1, 1, 1, 1, 1, 1}, l = {16, 29}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "$this", ImagesContract.URL, "referer", "response", "playerScript", "sources", "m3u8Url"}, nl = {20, 28}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"}, v = 2)
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
            return EmturbovidExtractor.getUrl$suspendImpl(EmturbovidExtractor.this, (String) null, (String) null, (Continuation<? super List<? extends ExtractorLink>>) this);
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

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    static /* synthetic */ Object getUrl$suspendImpl(EmturbovidExtractor emturbovidExtractor, String str, String str2, Continuation<? super List<? extends ExtractorLink>> continuation) {
        AnonymousClass1 anonymousClass1;
        String str3;
        int i;
        String str4;
        Object obj;
        String str5;
        List list;
        List list2;
        EmturbovidExtractor emturbovidExtractor2 = emturbovidExtractor;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = emturbovidExtractor2.new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = emturbovidExtractor2.new AnonymousClass1(continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object objNewExtractorLink = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass2.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objNewExtractorLink);
            Requests app2 = MainActivityKt.getApp();
            if (str2 == null) {
                str3 = emturbovidExtractor2.getMainUrl() + '/';
            } else {
                str3 = str2;
            }
            anonymousClass2.L$0 = emturbovidExtractor2;
            anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str);
            anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str2);
            anonymousClass2.label = 1;
            i = 2;
            Object obj2 = Requests.get$default(app2, str, null, str3, null, null, false, 0, null, 0L, null, false, null, anonymousClass2, 4090, null);
            anonymousClass2 = anonymousClass2;
            if (obj2 != coroutine_suspended) {
                str4 = str2;
                obj = obj2;
                str5 = str;
            }
            return coroutine_suspended;
        }
        if (i2 == 1) {
            String str6 = (String) anonymousClass2.L$2;
            str5 = (String) anonymousClass2.L$1;
            EmturbovidExtractor emturbovidExtractor3 = (EmturbovidExtractor) anonymousClass2.L$0;
            ResultKt.throwOnFailure(objNewExtractorLink);
            str4 = str6;
            emturbovidExtractor2 = emturbovidExtractor3;
            obj = objNewExtractorLink;
            i = 2;
        } else {
            if (i2 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            list = (List) anonymousClass2.L$7;
            list2 = (List) anonymousClass2.L$5;
            ResultKt.throwOnFailure(objNewExtractorLink);
        }
        list.add(objNewExtractorLink);
        return list2;
        NiceResponse niceResponse = (NiceResponse) obj;
        String strHtml = niceResponse.getDocument().selectXpath("//script[contains(text(),'var urlPlay')]").html();
        Intrinsics.checkNotNullExpressionValue(strHtml, "html(...)");
        ArrayList arrayList = new ArrayList();
        if (StringsKt.isBlank(strHtml)) {
            return arrayList;
        }
        String strSubstringBefore$default = StringsKt.substringBefore$default(StringsKt.substringAfter$default(strHtml, "var urlPlay = '", (String) null, i, (Object) null), "'", (String) null, i, (Object) null);
        String str7 = str5;
        String name = emturbovidExtractor2.getName();
        String str8 = str4;
        String name2 = emturbovidExtractor2.getName();
        ExtractorLinkType extractorLinkType = ExtractorLinkType.M3U8;
        AnonymousClass2 anonymousClass3 = emturbovidExtractor2.new AnonymousClass2(null);
        anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(emturbovidExtractor2);
        anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str7);
        anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str8);
        anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(niceResponse);
        anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(strHtml);
        anonymousClass2.L$5 = arrayList;
        anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(strSubstringBefore$default);
        anonymousClass2.L$7 = arrayList;
        anonymousClass2.label = i;
        objNewExtractorLink = ExtractorApiKt.newExtractorLink(name, name2, strSubstringBefore$default, extractorLinkType, anonymousClass3, anonymousClass2);
        if (objNewExtractorLink != coroutine_suspended) {
            list = arrayList;
            list2 = list;
            list.add(objNewExtractorLink);
            return list2;
        }
        return coroutine_suspended;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.EmturbovidExtractor$getUrl$2, reason: invalid class name */
    /* JADX INFO: compiled from: EmturbovidExtractor.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.EmturbovidExtractor$getUrl$2", f = "EmturbovidExtractor.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = EmturbovidExtractor.this.new AnonymousClass2(continuation);
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
            extractorLink.setReferer(EmturbovidExtractor.this.getMainUrl() + '/');
            extractorLink.setQuality(Qualities.Unknown.getValue());
            return Unit.INSTANCE;
        }
    }
}
