package com.lagradost.cloudstream3.extractors;

import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.net.HttpHeaders;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorApiKt;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.ExtractorLinkType;
import com.lagradost.cloudstream3.utils.Qualities;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: CloudMailRuExtractor.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000f0\u00132\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u000f0\u0013H\u0096@¢\u0006\u0002\u0010\u0017R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/CloudMailRu;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "mainUrl", "getMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "getUrl", "", ImagesContract.URL, "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class CloudMailRu extends ExtractorApi {
    private final boolean requiresReferer;
    private final String name = "CloudMailRu";
    private final String mainUrl = "https://cloud.mail.ru";

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.CloudMailRu$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: CloudMailRuExtractor.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.CloudMailRu", f = "CloudMailRuExtractor.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {32, 40}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "headers", "vidId", "vidIdEnc", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "headers", "vidId", "vidIdEnc", "videoReq", "regex", "videoMatch", "videoUrl"}, nl = {33, 39}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
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
            return CloudMailRu.getUrl$suspendImpl(CloudMailRu.this, (String) null, (String) null, (Function1<? super SubtitleFile, Unit>) null, (Function1<? super ExtractorLink, Unit>) null, (Continuation<? super Unit>) this);
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
    static /* synthetic */ Object getUrl$suspendImpl(CloudMailRu cloudMailRu, String str, String str2, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        byte[] bArrEncodeToByteArray;
        Continuation continuation2;
        Object obj;
        AnonymousClass1 anonymousClass2;
        CloudMailRu cloudMailRu2;
        String str3;
        String str4;
        Function1<? super SubtitleFile, Unit> function3;
        Function1<? super ExtractorLink, Unit> function4;
        Map map;
        Object obj2;
        String str5;
        Function1<? super ExtractorLink, Unit> function5;
        List<String> groupValues;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = cloudMailRu.new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = cloudMailRu.new AnonymousClass1(continuation);
        }
        Object objNewExtractorLink = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objNewExtractorLink);
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to("Accept", "*/*"), TuplesKt.to("Connection", "keep-alive"), TuplesKt.to(HttpHeaders.SEC_FETCH_DEST, "empty"), TuplesKt.to(HttpHeaders.SEC_FETCH_MODE, "cors"), TuplesKt.to(HttpHeaders.SEC_FETCH_SITE, "cross-site"), TuplesKt.to("Origin", cloudMailRu.getMainUrl()), TuplesKt.to("User-Agent", MainAPIKt.USER_AGENT));
            bArrEncodeToByteArray = StringsKt.encodeToByteArray(StringsKt.substringAfter$default(str, "public/", (String) null, 2, (Object) null));
            String strBase64Encode = MainAPIKt.base64Encode(bArrEncodeToByteArray);
            Requests app2 = MainActivityKt.getApp();
            anonymousClass1.L$0 = cloudMailRu;
            anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(str);
            anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(str2);
            anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(function1);
            anonymousClass1.L$4 = function2;
            anonymousClass1.L$5 = mapMapOf;
            anonymousClass1.L$6 = SpillingKt.nullOutSpilledVariable(bArrEncodeToByteArray);
            anonymousClass1.L$7 = strBase64Encode;
            anonymousClass1.label = 1;
            continuation2 = null;
            AnonymousClass1 anonymousClass3 = anonymousClass1;
            obj = coroutine_suspended;
            Object obj3 = Requests.get$default(app2, str, mapMapOf, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass3, 4092, null);
            anonymousClass2 = anonymousClass3;
            if (obj3 != obj) {
                cloudMailRu2 = cloudMailRu;
                str3 = str;
                str4 = str2;
                function3 = function1;
                function4 = function2;
                map = mapMapOf;
                obj2 = obj3;
                str5 = strBase64Encode;
            }
            return obj;
        }
        if (i == 1) {
            String str6 = (String) anonymousClass1.L$7;
            byte[] bArr = (byte[]) anonymousClass1.L$6;
            map = (Map) anonymousClass1.L$5;
            function4 = (Function1) anonymousClass1.L$4;
            function3 = (Function1) anonymousClass1.L$3;
            str4 = (String) anonymousClass1.L$2;
            str3 = (String) anonymousClass1.L$1;
            cloudMailRu2 = (CloudMailRu) anonymousClass1.L$0;
            ResultKt.throwOnFailure(objNewExtractorLink);
            bArrEncodeToByteArray = bArr;
            anonymousClass2 = anonymousClass1;
            obj = coroutine_suspended;
            str5 = str6;
            obj2 = objNewExtractorLink;
            continuation2 = null;
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            function5 = (Function1) anonymousClass1.L$12;
            ResultKt.throwOnFailure(objNewExtractorLink);
        }
        function5.invoke(objNewExtractorLink);
        return Unit.INSTANCE;
        String text = ((NiceResponse) obj2).getText();
        Regex regex = new Regex("videowl_view\":\\{\"count\":\"1\",\"url\":\"([^\"]*)\"\\}", (Set<? extends RegexOption>) SetsKt.setOf(RegexOption.IGNORE_CASE));
        MatchResult matchResultFind$default = Regex.find$default(regex, text, 0, 2, continuation2);
        String strValueOf = String.valueOf((matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null) ? continuation2 : groupValues.get(1));
        String str7 = strValueOf + "/0p/" + str5 + ".m3u8?double_encode=1";
        String name = cloudMailRu2.getName();
        String name2 = cloudMailRu2.getName();
        ExtractorLinkType infer_type = ExtractorApiKt.getINFER_TYPE();
        AnonymousClass2 anonymousClass4 = cloudMailRu2.new AnonymousClass2(map, continuation2);
        anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(cloudMailRu2);
        anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str3);
        anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str4);
        anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function3);
        anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(function4);
        anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(map);
        anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(bArrEncodeToByteArray);
        anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(str5);
        anonymousClass2.L$8 = SpillingKt.nullOutSpilledVariable(text);
        anonymousClass2.L$9 = SpillingKt.nullOutSpilledVariable(regex);
        anonymousClass2.L$10 = SpillingKt.nullOutSpilledVariable(strValueOf);
        anonymousClass2.L$11 = SpillingKt.nullOutSpilledVariable(str7);
        anonymousClass2.L$12 = function4;
        anonymousClass2.label = 2;
        objNewExtractorLink = ExtractorApiKt.newExtractorLink(name, name2, str7, infer_type, anonymousClass4, anonymousClass2);
        if (objNewExtractorLink != obj) {
            function5 = function4;
            function5.invoke(objNewExtractorLink);
            return Unit.INSTANCE;
        }
        return obj;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.CloudMailRu$getUrl$2, reason: invalid class name */
    /* JADX INFO: compiled from: CloudMailRuExtractor.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.CloudMailRu$getUrl$2", f = "CloudMailRuExtractor.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ Map<String, String> $headers;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Map<String, String> map, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$headers = map;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = CloudMailRu.this.new AnonymousClass2(this.$headers, continuation);
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
            extractorLink.setReferer(CloudMailRu.this.getMainUrl() + '/');
            extractorLink.setQuality(Qualities.Unknown.getValue());
            extractorLink.setHeaders(this.$headers);
            return Unit.INSTANCE;
        }
    }
}
