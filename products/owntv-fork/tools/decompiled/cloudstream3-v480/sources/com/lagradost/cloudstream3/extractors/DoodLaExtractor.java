package com.lagradost.cloudstream3.extractors;

import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorApiKt;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import io.ktor.http.URLUtilsKt;
import io.ktor.http.Url;
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
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: DoodExtractor.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00130\u00172\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00130\u0017H\u0096@¢\u0006\u0002\u0010\u001bJ\b\u0010\u001c\u001a\u00020\u0005H\u0002J\u0010\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0005H\u0002R\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u0014\u0010\r\u001a\u00020\u000eX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/DoodLaExtractor;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "mainUrl", "getMainUrl", "setMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "alphabet", "getUrl", "", ImagesContract.URL, "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createHashTable", "getBaseUrl", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class DoodLaExtractor extends ExtractorApi {
    private final boolean requiresReferer;
    private String name = "DoodStream";
    private String mainUrl = "https://dood.la";
    private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.DoodLaExtractor$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: DoodExtractor.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.DoodLaExtractor", f = "DoodExtractor.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {110, 114, 121}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "embedUrl", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "embedUrl", "req", "host", "response0", "md5", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "embedUrl", "req", "host", "response0", "md5", "trueUrl", "quality"}, nl = {111, 117, 120}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11"}, v = 2)
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
            return DoodLaExtractor.getUrl$suspendImpl(DoodLaExtractor.this, (String) null, (String) null, (Function1<? super SubtitleFile, Unit>) null, (Function1<? super ExtractorLink, Unit>) null, (Continuation<? super Unit>) this);
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

    /* JADX WARN: Code duplicated, block: B:36:0x0259  */
    /* JADX WARN: Code duplicated, block: B:40:0x02c4  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v26 */
    /* JADX WARN: Type inference failed for: r0v30 */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object, java.lang.String, kotlin.coroutines.Continuation] */
    static /* synthetic */ Object getUrl$suspendImpl(DoodLaExtractor doodLaExtractor, String str, String str2, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        int i;
        Object obj;
        String str3;
        String str4;
        Function1<? super SubtitleFile, Unit> function3;
        Object obj2;
        String str5;
        DoodLaExtractor doodLaExtractor2;
        Function1<? super ExtractorLink, Unit> function4;
        String value;
        int i2;
        String str6;
        String str7;
        Function1<? super SubtitleFile, Unit> function5;
        String str8;
        String str9;
        NiceResponse niceResponse;
        Function1<? super ExtractorLink, Unit> function6;
        DoodLaExtractor doodLaExtractor3;
        String str10;
        Object obj3;
        StringBuilder sb;
        ?? r0;
        MatchResult matchResultFind$default;
        String str11;
        Function1<? super ExtractorLink, Unit> function7;
        List<String> groupValues;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = doodLaExtractor.new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = doodLaExtractor.new AnonymousClass1(continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object objNewExtractorLink$default = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = anonymousClass2.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(objNewExtractorLink$default);
            String strReplace$default = StringsKt.replace$default(str, "/d/", "/e/", false, 4, (Object) null);
            Requests app2 = MainActivityKt.getApp();
            anonymousClass2.L$0 = doodLaExtractor;
            anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str);
            anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str2);
            anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function1);
            anonymousClass2.L$4 = function2;
            anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(strReplace$default);
            anonymousClass2.label = 1;
            i = 0;
            obj = null;
            Object obj4 = Requests.get$default(app2, strReplace$default, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass2, 4094, null);
            if (obj4 != coroutine_suspended) {
                anonymousClass2 = anonymousClass2;
                str3 = str;
                str4 = str2;
                function3 = function1;
                obj2 = obj4;
                str5 = strReplace$default;
                doodLaExtractor2 = doodLaExtractor;
                function4 = function2;
            }
            anonymousClass2 = anonymousClass2;
            return coroutine_suspended;
        }
        if (i3 == 1) {
            String str12 = (String) anonymousClass2.L$5;
            Function1<? super ExtractorLink, Unit> function8 = (Function1) anonymousClass2.L$4;
            Function1<? super SubtitleFile, Unit> function9 = (Function1) anonymousClass2.L$3;
            String str13 = (String) anonymousClass2.L$2;
            String str14 = (String) anonymousClass2.L$1;
            DoodLaExtractor doodLaExtractor4 = (DoodLaExtractor) anonymousClass2.L$0;
            ResultKt.throwOnFailure(objNewExtractorLink$default);
            str5 = str12;
            function3 = function9;
            str4 = str13;
            str3 = str14;
            obj = null;
            obj2 = objNewExtractorLink$default;
            function4 = function8;
            doodLaExtractor2 = doodLaExtractor4;
            i = 0;
        } else {
            if (i3 == 2) {
                StringBuilder sb2 = (StringBuilder) anonymousClass2.L$10;
                String str15 = (String) anonymousClass2.L$9;
                String str16 = (String) anonymousClass2.L$8;
                str10 = (String) anonymousClass2.L$7;
                niceResponse = (NiceResponse) anonymousClass2.L$6;
                str9 = (String) anonymousClass2.L$5;
                function6 = (Function1) anonymousClass2.L$4;
                function5 = (Function1) anonymousClass2.L$3;
                str7 = (String) anonymousClass2.L$2;
                String str17 = (String) anonymousClass2.L$1;
                DoodLaExtractor doodLaExtractor5 = (DoodLaExtractor) anonymousClass2.L$0;
                ResultKt.throwOnFailure(objNewExtractorLink$default);
                str3 = str17;
                obj3 = objNewExtractorLink$default;
                i2 = 2;
                doodLaExtractor3 = doodLaExtractor5;
                str8 = str16;
                str6 = str15;
                sb = sb2;
                r0 = 0;
                sb.append(((NiceResponse) obj3).getText());
                sb.append(doodLaExtractor3.createHashTable());
                sb.append("?token=");
                sb.append(StringsKt.substringAfterLast$default(str6, "/", (String) r0, i2, (Object) r0));
                String string = sb.toString();
                matchResultFind$default = Regex.find$default(new Regex("\\d{3,4}[pP]"), StringsKt.substringBefore$default(StringsKt.substringAfter$default(str8, "<title>", (String) r0, i2, (Object) r0), "</title>", (String) r0, i2, (Object) r0), 0, i2, r0);
                if (matchResultFind$default != null || (groupValues = matchResultFind$default.getGroupValues()) == null) {
                    str11 = r0;
                } else {
                    str11 = (String) CollectionsKt.getOrNull(groupValues, 0);
                }
                String name = doodLaExtractor3.getName();
                String name2 = doodLaExtractor3.getName();
                AnonymousClass2 anonymousClass3 = doodLaExtractor3.new AnonymousClass2(str11, r0);
                anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(doodLaExtractor3);
                anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str3);
                anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str7);
                anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function5);
                anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(function6);
                anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(str9);
                anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(niceResponse);
                anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(str10);
                anonymousClass2.L$8 = SpillingKt.nullOutSpilledVariable(str8);
                anonymousClass2.L$9 = SpillingKt.nullOutSpilledVariable(str6);
                anonymousClass2.L$10 = SpillingKt.nullOutSpilledVariable(string);
                anonymousClass2.L$11 = SpillingKt.nullOutSpilledVariable(str11);
                anonymousClass2.L$12 = function6;
                anonymousClass2.label = 3;
                objNewExtractorLink$default = ExtractorApiKt.newExtractorLink$default(name, name2, string, null, anonymousClass3, anonymousClass2, 8, null);
                if (objNewExtractorLink$default != coroutine_suspended) {
                    function7 = function6;
                }
                anonymousClass2 = anonymousClass2;
                return coroutine_suspended;
            }
            if (i3 != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            function7 = (Function1) anonymousClass2.L$12;
            ResultKt.throwOnFailure(objNewExtractorLink$default);
        }
        function7.invoke(objNewExtractorLink$default);
        return Unit.INSTANCE;
        NiceResponse niceResponse2 = (NiceResponse) obj2;
        String baseUrl = doodLaExtractor2.getBaseUrl(niceResponse2.getUrl());
        String text = niceResponse2.getText();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(baseUrl);
        MatchResult matchResultFind$default2 = Regex.find$default(new Regex("/pass_md5/[^']*"), text, i, 2, obj);
        if (matchResultFind$default2 == null || (value = matchResultFind$default2.getValue()) == null) {
            return Unit.INSTANCE;
        }
        sb3.append(value);
        String string2 = sb3.toString();
        StringBuilder sb4 = new StringBuilder();
        Requests app3 = MainActivityKt.getApp();
        String url = niceResponse2.getUrl();
        anonymousClass2.L$0 = doodLaExtractor2;
        anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str3);
        anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str4);
        anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function3);
        anonymousClass2.L$4 = function4;
        anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(str5);
        anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(niceResponse2);
        anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(baseUrl);
        anonymousClass2.L$8 = text;
        anonymousClass2.L$9 = string2;
        anonymousClass2.L$10 = sb4;
        anonymousClass2.label = 2;
        Function1<? super ExtractorLink, Unit> function10 = function4;
        AnonymousClass1 anonymousClass4 = anonymousClass2;
        DoodLaExtractor doodLaExtractor6 = doodLaExtractor2;
        i2 = 2;
        Object obj5 = Requests.get$default(app3, string2, null, url, null, null, false, 0, null, 0L, null, false, null, anonymousClass4, 4090, null);
        anonymousClass2 = anonymousClass4;
        if (obj5 != coroutine_suspended) {
            str6 = string2;
            str7 = str4;
            function5 = function3;
            str8 = text;
            str9 = str5;
            niceResponse = niceResponse2;
            function6 = function10;
            doodLaExtractor3 = doodLaExtractor6;
            str10 = baseUrl;
            obj3 = obj5;
            sb = sb4;
            r0 = obj;
            sb.append(((NiceResponse) obj3).getText());
            sb.append(doodLaExtractor3.createHashTable());
            sb.append("?token=");
            sb.append(StringsKt.substringAfterLast$default(str6, "/", (String) r0, i2, (Object) r0));
            String string3 = sb.toString();
            matchResultFind$default = Regex.find$default(new Regex("\\d{3,4}[pP]"), StringsKt.substringBefore$default(StringsKt.substringAfter$default(str8, "<title>", (String) r0, i2, (Object) r0), "</title>", (String) r0, i2, (Object) r0), 0, i2, r0);
            if (matchResultFind$default != null) {
                str11 = r0;
            } else {
                str11 = r0;
            }
            String name3 = doodLaExtractor3.getName();
            String name4 = doodLaExtractor3.getName();
            AnonymousClass2 anonymousClass5 = doodLaExtractor3.new AnonymousClass2(str11, r0);
            anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(doodLaExtractor3);
            anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str3);
            anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str7);
            anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function5);
            anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(function6);
            anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(str9);
            anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(niceResponse);
            anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(str10);
            anonymousClass2.L$8 = SpillingKt.nullOutSpilledVariable(str8);
            anonymousClass2.L$9 = SpillingKt.nullOutSpilledVariable(str6);
            anonymousClass2.L$10 = SpillingKt.nullOutSpilledVariable(string3);
            anonymousClass2.L$11 = SpillingKt.nullOutSpilledVariable(str11);
            anonymousClass2.L$12 = function6;
            anonymousClass2.label = 3;
            objNewExtractorLink$default = ExtractorApiKt.newExtractorLink$default(name3, name4, string3, null, anonymousClass5, anonymousClass2, 8, null);
            if (objNewExtractorLink$default != coroutine_suspended) {
                function7 = function6;
                function7.invoke(objNewExtractorLink$default);
                return Unit.INSTANCE;
            }
        }
        anonymousClass2 = anonymousClass2;
        return coroutine_suspended;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.DoodLaExtractor$getUrl$2, reason: invalid class name */
    /* JADX INFO: compiled from: DoodExtractor.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.DoodLaExtractor$getUrl$2", f = "DoodExtractor.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
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
            AnonymousClass2 anonymousClass2 = DoodLaExtractor.this.new AnonymousClass2(this.$quality, continuation);
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
            extractorLink.setReferer(DoodLaExtractor.this.getMainUrl() + '/');
            extractorLink.setQuality(ExtractorApiKt.getQualityFromName(this.$quality));
            return Unit.INSTANCE;
        }
    }

    private final String createHashTable() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(StringsKt.random(this.alphabet, Random.INSTANCE));
        }
        return sb.toString();
    }

    private final String getBaseUrl(String url) {
        Url Url = URLUtilsKt.Url(url);
        return Url.getProtocol().getName() + "://" + Url.getHost();
    }
}
