package com.lagradost.cloudstream3.extractors;

import coil3.util.UtilsKt;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.ErrorLoadingException;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorApiKt;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import com.lagradost.nicehttp.ResponseParser;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SobreatsesuypExtractor.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001:\u0001\u0018B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000f0\u00132\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u000f0\u0013H\u0096@¢\u0006\u0002\u0010\u0017R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Sobreatsesuyp;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "mainUrl", "getMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "getUrl", "", ImagesContract.URL, "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "SobreatsesuypVideoData", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class Sobreatsesuyp extends ExtractorApi {
    private final String name = "Sobreatsesuyp";
    private final String mainUrl = "https://sobreatsesuyp.com";
    private final boolean requiresReferer = true;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Sobreatsesuyp$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: SobreatsesuypExtractor.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Sobreatsesuyp", f = "SobreatsesuypExtractor.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, l = {17, 21, 34, 37}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "extRef", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "extRef", "videoReq", UtilsKt.SCHEME_FILE, "postLink", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "extRef", "videoReq", UtilsKt.SCHEME_FILE, "postLink", "rawList", "postJson", "item", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "extRef", "videoReq", UtilsKt.SCHEME_FILE, "postLink", "rawList", "postJson", "item", "videoData"}, nl = {19, 53, 36, 36}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$12", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$12", "L$13"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
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
            return Sobreatsesuyp.getUrl$suspendImpl(Sobreatsesuyp.this, (String) null, (String) null, (Function1<? super SubtitleFile, Unit>) null, (Function1<? super ExtractorLink, Unit>) null, (Continuation<? super Unit>) this);
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

    /* JADX WARN: Code duplicated, block: B:45:0x029a  */
    /* JADX WARN: Code duplicated, block: B:48:0x02ba  */
    /* JADX WARN: Code duplicated, block: B:50:0x02d1  */
    /* JADX WARN: Code duplicated, block: B:51:0x02d5  */
    /* JADX WARN: Code duplicated, block: B:54:0x02e0  */
    /* JADX WARN: Code duplicated, block: B:55:0x02e3  */
    /* JADX WARN: Code duplicated, block: B:60:0x02fa  */
    /* JADX WARN: Code duplicated, block: B:62:0x0306  */
    /* JADX WARN: Code duplicated, block: B:72:0x044f  */
    /* JADX WARN: Code duplicated, block: B:77:0x0480  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Code duplicated, block: B:85:0x046b A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:72:0x044f -> B:73:0x045b). Please report as a decompilation issue!!! */
    static /* synthetic */ Object getUrl$suspendImpl(Sobreatsesuyp sobreatsesuyp, String str, String str2, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Unit> continuation) throws ErrorLoadingException {
        AnonymousClass1 anonymousClass1;
        String str3;
        int i;
        Object obj;
        Object obj2;
        AnonymousClass1 anonymousClass2;
        String str4;
        String str5;
        Function1<? super SubtitleFile, Unit> function3;
        Function1<? super ExtractorLink, Unit> function4;
        Object obj3;
        Sobreatsesuyp sobreatsesuyp2;
        List<String> groupValues;
        String str6;
        String str7;
        String str8;
        int i2;
        Object objPost$default;
        AnonymousClass1 anonymousClass3;
        Function1<? super ExtractorLink, Unit> function5;
        String str9;
        String str10;
        Function1<? super SubtitleFile, Unit> function6;
        String str11;
        Sobreatsesuyp sobreatsesuyp3;
        Object safe;
        List list;
        ArrayList arrayList;
        ArrayList arrayList2;
        Iterator it;
        AnonymousClass1 anonymousClass4;
        Object obj4;
        String str12;
        Object obj5;
        String str13;
        String str14;
        String str15;
        List list2;
        String str16;
        List list3;
        String str17;
        Function1<? super SubtitleFile, Unit> function7;
        SobreatsesuypVideoData sobreatsesuypVideoData;
        AnonymousClass1 anonymousClass5;
        String str18;
        Sobreatsesuyp sobreatsesuyp4;
        Iterator it2;
        Function1<? super ExtractorLink, Unit> function8;
        String str19;
        Sobreatsesuyp sobreatsesuyp5;
        Object objNewExtractorLink$default;
        Sobreatsesuyp sobreatsesuyp6;
        AnonymousClass1 anonymousClass6;
        List list4;
        String str20;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = sobreatsesuyp.new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = sobreatsesuyp.new AnonymousClass1(continuation);
        }
        Object obj6 = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = anonymousClass1.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj6);
            str3 = str2 == null ? "" : str2;
            Requests app2 = MainActivityKt.getApp();
            anonymousClass1.L$0 = sobreatsesuyp;
            anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(str);
            anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(str2);
            anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(function1);
            anonymousClass1.L$4 = function2;
            anonymousClass1.L$5 = str3;
            anonymousClass1.label = 1;
            AnonymousClass1 anonymousClass7 = anonymousClass1;
            i = 2;
            obj = coroutine_suspended;
            obj2 = null;
            Object obj7 = Requests.get$default(app2, str, null, str3, null, null, false, 0, null, 0L, null, false, null, anonymousClass7, 4090, null);
            anonymousClass2 = anonymousClass7;
            if (obj7 != obj) {
                str4 = str;
                str5 = str2;
                function3 = function1;
                function4 = function2;
                obj3 = obj7;
                sobreatsesuyp2 = sobreatsesuyp;
            }
            anonymousClass3 = anonymousClass1;
            return obj;
        }
        if (i3 == 1) {
            String str21 = (String) anonymousClass1.L$5;
            Function1<? super ExtractorLink, Unit> function9 = (Function1) anonymousClass1.L$4;
            Function1<? super SubtitleFile, Unit> function10 = (Function1) anonymousClass1.L$3;
            String str22 = (String) anonymousClass1.L$2;
            String str23 = (String) anonymousClass1.L$1;
            Sobreatsesuyp sobreatsesuyp7 = (Sobreatsesuyp) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj6);
            function3 = function10;
            str5 = str22;
            str4 = str23;
            str3 = str21;
            function4 = function9;
            i = 2;
            obj3 = obj6;
            anonymousClass2 = anonymousClass1;
            obj = coroutine_suspended;
            sobreatsesuyp2 = sobreatsesuyp7;
            obj2 = null;
        } else {
            if (i3 == 2) {
                String str24 = (String) anonymousClass1.L$8;
                String str25 = (String) anonymousClass1.L$7;
                str11 = (String) anonymousClass1.L$6;
                str3 = (String) anonymousClass1.L$5;
                function5 = (Function1) anonymousClass1.L$4;
                function6 = (Function1) anonymousClass1.L$3;
                str10 = (String) anonymousClass1.L$2;
                str9 = (String) anonymousClass1.L$1;
                sobreatsesuyp3 = (Sobreatsesuyp) anonymousClass1.L$0;
                ResultKt.throwOnFailure(obj6);
                anonymousClass3 = anonymousClass1;
                obj = coroutine_suspended;
                str7 = str25;
                str8 = str24;
                objPost$default = obj6;
                i2 = 1;
                NiceResponse niceResponse = (NiceResponse) objPost$default;
                try {
                    ResponseParser parser = niceResponse.getParser();
                    Intrinsics.checkNotNull(parser);
                    safe = parser.parseSafe(niceResponse.getText(), Reflection.getOrCreateKotlinClass(List.class));
                } catch (Exception e) {
                    e.printStackTrace();
                    safe = null;
                }
                list = (List) safe;
                if (list != null) {
                    throw new ErrorLoadingException("Post link not found");
                }
                List listDrop = CollectionsKt.drop(list, i2);
                arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listDrop, 10));
                for (Object obj8 : listDrop) {
                    Intrinsics.checkNotNull(obj8, "null cannot be cast to non-null type kotlin.collections.Map<*, *>");
                    Map map = (Map) obj8;
                    obj4 = map.get("title");
                    if (obj4 instanceof String) {
                        str12 = (String) obj4;
                    } else {
                        str12 = null;
                    }
                    obj5 = map.get(UtilsKt.SCHEME_FILE);
                    if (obj5 instanceof String) {
                        str13 = (String) obj5;
                    } else {
                        str13 = null;
                    }
                    arrayList.add(new SobreatsesuypVideoData(str12, str13));
                }
                arrayList2 = arrayList;
                it = arrayList2.iterator();
                anonymousClass4 = anonymousClass3;
                while (it.hasNext()) {
                    sobreatsesuypVideoData = (SobreatsesuypVideoData) it.next();
                    if (sobreatsesuypVideoData.getFile() == null && sobreatsesuypVideoData.getTitle() != null) {
                        Requests app3 = MainActivityKt.getApp();
                        StringBuilder sb = new StringBuilder();
                        sb.append(sobreatsesuyp3.getMainUrl());
                        sb.append("/playlist/");
                        String str26 = str8;
                        String strSubstring = sobreatsesuypVideoData.getFile().substring(1);
                        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                        sb.append(strSubstring);
                        sb.append(".txt");
                        String string = sb.toString();
                        anonymousClass4.L$0 = sobreatsesuyp3;
                        anonymousClass4.L$1 = SpillingKt.nullOutSpilledVariable(str9);
                        anonymousClass4.L$2 = SpillingKt.nullOutSpilledVariable(str10);
                        anonymousClass4.L$3 = SpillingKt.nullOutSpilledVariable(function6);
                        anonymousClass4.L$4 = function5;
                        anonymousClass4.L$5 = str3;
                        anonymousClass4.L$6 = SpillingKt.nullOutSpilledVariable(str11);
                        anonymousClass4.L$7 = SpillingKt.nullOutSpilledVariable(str7);
                        anonymousClass4.L$8 = SpillingKt.nullOutSpilledVariable(str26);
                        anonymousClass4.L$9 = SpillingKt.nullOutSpilledVariable(list);
                        anonymousClass4.L$10 = SpillingKt.nullOutSpilledVariable(arrayList2);
                        anonymousClass4.L$11 = it;
                        anonymousClass4.L$12 = sobreatsesuypVideoData;
                        anonymousClass4.L$13 = null;
                        anonymousClass4.L$14 = null;
                        anonymousClass4.label = 3;
                        AnonymousClass1 anonymousClass8 = anonymousClass4;
                        String str27 = str3;
                        Object objPost$default2 = Requests.post$default(app3, string, null, str27, null, null, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass8, 65530, null);
                        if (objPost$default2 != obj) {
                            it2 = it;
                            obj6 = objPost$default2;
                            str15 = str26;
                            list2 = list;
                            str14 = str11;
                            function8 = function5;
                            function7 = function6;
                            str17 = str10;
                            str16 = str9;
                            sobreatsesuyp4 = sobreatsesuyp3;
                            list3 = arrayList2;
                            str18 = str27;
                            anonymousClass5 = anonymousClass8;
                            String text = ((NiceResponse) obj6).getText();
                            String name = sobreatsesuyp4.getName();
                            String str28 = sobreatsesuyp4.getName() + " - " + sobreatsesuypVideoData.getTitle();
                            AnonymousClass2 anonymousClass9 = new AnonymousClass2(str18, null);
                            anonymousClass5.L$0 = sobreatsesuyp4;
                            anonymousClass5.L$1 = SpillingKt.nullOutSpilledVariable(str16);
                            anonymousClass5.L$2 = SpillingKt.nullOutSpilledVariable(str17);
                            anonymousClass5.L$3 = SpillingKt.nullOutSpilledVariable(function7);
                            anonymousClass5.L$4 = function8;
                            anonymousClass5.L$5 = str18;
                            anonymousClass5.L$6 = SpillingKt.nullOutSpilledVariable(str14);
                            anonymousClass5.L$7 = SpillingKt.nullOutSpilledVariable(str7);
                            anonymousClass5.L$8 = SpillingKt.nullOutSpilledVariable(str15);
                            anonymousClass5.L$9 = SpillingKt.nullOutSpilledVariable(list2);
                            anonymousClass5.L$10 = SpillingKt.nullOutSpilledVariable(list3);
                            anonymousClass5.L$11 = it2;
                            anonymousClass5.L$12 = SpillingKt.nullOutSpilledVariable(sobreatsesuypVideoData);
                            anonymousClass5.L$13 = SpillingKt.nullOutSpilledVariable(text);
                            anonymousClass5.L$14 = function8;
                            anonymousClass5.label = 4;
                            str19 = str18;
                            sobreatsesuyp5 = sobreatsesuyp4;
                            objNewExtractorLink$default = ExtractorApiKt.newExtractorLink$default(name, str28, text, null, anonymousClass9, anonymousClass5, 8, null);
                            if (objNewExtractorLink$default != obj) {
                                function5 = function8;
                                sobreatsesuyp6 = sobreatsesuyp5;
                                obj6 = objNewExtractorLink$default;
                                anonymousClass6 = anonymousClass5;
                                function6 = function7;
                                str10 = str17;
                                list4 = list2;
                                str20 = str15;
                                str3 = str19;
                            }
                        }
                        anonymousClass3 = anonymousClass1;
                        return obj;
                    }
                    str8 = str8;
                    str3 = str3;
                    anonymousClass4 = anonymousClass4;
                }
                return Unit.INSTANCE;
            }
            if (i3 == 3) {
                SobreatsesuypVideoData sobreatsesuypVideoData2 = (SobreatsesuypVideoData) anonymousClass1.L$12;
                Iterator it3 = (Iterator) anonymousClass1.L$11;
                List list5 = (List) anonymousClass1.L$10;
                List list6 = (List) anonymousClass1.L$9;
                String str29 = (String) anonymousClass1.L$8;
                String str30 = (String) anonymousClass1.L$7;
                String str31 = (String) anonymousClass1.L$6;
                String str32 = (String) anonymousClass1.L$5;
                Function1<? super ExtractorLink, Unit> function11 = (Function1) anonymousClass1.L$4;
                Function1<? super SubtitleFile, Unit> function12 = (Function1) anonymousClass1.L$3;
                String str33 = (String) anonymousClass1.L$2;
                String str34 = (String) anonymousClass1.L$1;
                Sobreatsesuyp sobreatsesuyp8 = (Sobreatsesuyp) anonymousClass1.L$0;
                ResultKt.throwOnFailure(obj6);
                list3 = list5;
                list2 = list6;
                str15 = str29;
                str7 = str30;
                str14 = str31;
                str18 = str32;
                str16 = str34;
                sobreatsesuyp4 = sobreatsesuyp8;
                anonymousClass5 = anonymousClass1;
                obj = coroutine_suspended;
                it2 = it3;
                function7 = function12;
                str17 = str33;
                sobreatsesuypVideoData = sobreatsesuypVideoData2;
                function8 = function11;
                String text2 = ((NiceResponse) obj6).getText();
                String name2 = sobreatsesuyp4.getName();
                String str210 = sobreatsesuyp4.getName() + " - " + sobreatsesuypVideoData.getTitle();
                AnonymousClass2 anonymousClass10 = new AnonymousClass2(str18, null);
                anonymousClass5.L$0 = sobreatsesuyp4;
                anonymousClass5.L$1 = SpillingKt.nullOutSpilledVariable(str16);
                anonymousClass5.L$2 = SpillingKt.nullOutSpilledVariable(str17);
                anonymousClass5.L$3 = SpillingKt.nullOutSpilledVariable(function7);
                anonymousClass5.L$4 = function8;
                anonymousClass5.L$5 = str18;
                anonymousClass5.L$6 = SpillingKt.nullOutSpilledVariable(str14);
                anonymousClass5.L$7 = SpillingKt.nullOutSpilledVariable(str7);
                anonymousClass5.L$8 = SpillingKt.nullOutSpilledVariable(str15);
                anonymousClass5.L$9 = SpillingKt.nullOutSpilledVariable(list2);
                anonymousClass5.L$10 = SpillingKt.nullOutSpilledVariable(list3);
                anonymousClass5.L$11 = it2;
                anonymousClass5.L$12 = SpillingKt.nullOutSpilledVariable(sobreatsesuypVideoData);
                anonymousClass5.L$13 = SpillingKt.nullOutSpilledVariable(text2);
                anonymousClass5.L$14 = function8;
                anonymousClass5.label = 4;
                str19 = str18;
                sobreatsesuyp5 = sobreatsesuyp4;
                objNewExtractorLink$default = ExtractorApiKt.newExtractorLink$default(name2, str210, text2, null, anonymousClass10, anonymousClass5, 8, null);
                if (objNewExtractorLink$default != obj) {
                    function5 = function8;
                    sobreatsesuyp6 = sobreatsesuyp5;
                    obj6 = objNewExtractorLink$default;
                    anonymousClass6 = anonymousClass5;
                    function6 = function7;
                    str10 = str17;
                    list4 = list2;
                    str20 = str15;
                    str3 = str19;
                }
                anonymousClass3 = anonymousClass1;
                return obj;
            }
            if (i3 != 4) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            function8 = (Function1) anonymousClass1.L$14;
            Iterator it4 = (Iterator) anonymousClass1.L$11;
            List list7 = (List) anonymousClass1.L$10;
            list4 = (List) anonymousClass1.L$9;
            str20 = (String) anonymousClass1.L$8;
            String str35 = (String) anonymousClass1.L$7;
            String str36 = (String) anonymousClass1.L$6;
            String str37 = (String) anonymousClass1.L$5;
            Function1<? super ExtractorLink, Unit> function13 = (Function1) anonymousClass1.L$4;
            Function1<? super SubtitleFile, Unit> function14 = (Function1) anonymousClass1.L$3;
            String str38 = (String) anonymousClass1.L$2;
            String str39 = (String) anonymousClass1.L$1;
            Sobreatsesuyp sobreatsesuyp9 = (Sobreatsesuyp) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj6);
            list3 = list7;
            str16 = str39;
            str7 = str35;
            str14 = str36;
            function5 = function13;
            str10 = str38;
            sobreatsesuyp6 = sobreatsesuyp9;
            str3 = str37;
            function6 = function14;
            anonymousClass6 = anonymousClass1;
            obj = coroutine_suspended;
            it2 = it4;
        }
        function8.invoke(obj6);
        it = it2;
        anonymousClass4 = anonymousClass6;
        list = list4;
        str8 = str20;
        sobreatsesuyp3 = sobreatsesuyp6;
        arrayList2 = list3;
        str9 = str16;
        str11 = str14;
        while (it.hasNext()) {
            sobreatsesuypVideoData = (SobreatsesuypVideoData) it.next();
            if (sobreatsesuypVideoData.getFile() == null) {
            }
            str8 = str8;
            str3 = str3;
            anonymousClass4 = anonymousClass4;
        }
        return Unit.INSTANCE;
        String text3 = ((NiceResponse) obj3).getText();
        MatchResult matchResultFind$default = Regex.find$default(new Regex("file\\\":\\\"([^\\\"]+)"), text3, 0, i, obj2);
        if (matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null || (str6 = groupValues.get(1)) == null) {
            throw new ErrorLoadingException("File not found");
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(sobreatsesuyp2.getMainUrl());
        sb2.append('/');
        str7 = str6;
        sb2.append(StringsKt.replace$default(str6, "\\", "", false, 4, (Object) null));
        String string2 = sb2.toString();
        Requests app4 = MainActivityKt.getApp();
        anonymousClass2.L$0 = sobreatsesuyp2;
        anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str4);
        anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str5);
        anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function3);
        anonymousClass2.L$4 = function4;
        anonymousClass2.L$5 = str3;
        anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(text3);
        anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(str7);
        anonymousClass2.L$8 = SpillingKt.nullOutSpilledVariable(string2);
        anonymousClass2.label = i;
        Function1<? super ExtractorLink, Unit> function15 = function4;
        AnonymousClass1 anonymousClass11 = anonymousClass2;
        str8 = string2;
        Sobreatsesuyp sobreatsesuyp10 = sobreatsesuyp2;
        i2 = 1;
        objPost$default = Requests.post$default(app4, str8, null, str3, null, null, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass11, 65530, null);
        if (objPost$default != obj) {
            anonymousClass3 = anonymousClass11;
            function5 = function15;
            str9 = str4;
            str10 = str5;
            function6 = function3;
            str11 = text3;
            sobreatsesuyp3 = sobreatsesuyp10;
            NiceResponse niceResponse2 = (NiceResponse) objPost$default;
            ResponseParser parser2 = niceResponse2.getParser();
            Intrinsics.checkNotNull(parser2);
            safe = parser2.parseSafe(niceResponse2.getText(), Reflection.getOrCreateKotlinClass(List.class));
            list = (List) safe;
            if (list != null) {
                throw new ErrorLoadingException("Post link not found");
            }
            List listDrop2 = CollectionsKt.drop(list, i2);
            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listDrop2, 10));
            while (r3.hasNext()) {
                Intrinsics.checkNotNull(obj8, "null cannot be cast to non-null type kotlin.collections.Map<*, *>");
                Map map2 = (Map) obj8;
                obj4 = map2.get("title");
                if (obj4 instanceof String) {
                    str12 = (String) obj4;
                } else {
                    str12 = null;
                }
                obj5 = map2.get(UtilsKt.SCHEME_FILE);
                if (obj5 instanceof String) {
                    str13 = (String) obj5;
                } else {
                    str13 = null;
                }
                arrayList.add(new SobreatsesuypVideoData(str12, str13));
            }
            arrayList2 = arrayList;
            it = arrayList2.iterator();
            anonymousClass4 = anonymousClass3;
            while (it.hasNext()) {
                sobreatsesuypVideoData = (SobreatsesuypVideoData) it.next();
                if (sobreatsesuypVideoData.getFile() == null) {
                }
                str8 = str8;
                str3 = str3;
                anonymousClass4 = anonymousClass4;
            }
            return Unit.INSTANCE;
        }
        anonymousClass3 = anonymousClass11;
        return obj;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Sobreatsesuyp$getUrl$2, reason: invalid class name */
    /* JADX INFO: compiled from: SobreatsesuypExtractor.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Sobreatsesuyp$getUrl$2", f = "SobreatsesuypExtractor.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $extRef;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$extRef = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$extRef, continuation);
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
            extractorLink.setReferer(this.$extRef);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: compiled from: SobreatsesuypExtractor.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\f\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Sobreatsesuyp$SobreatsesuypVideoData;", "", "title", "", UtilsKt.SCHEME_FILE, "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getTitle", "()Ljava/lang/String;", "getFile", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SobreatsesuypVideoData {

        @JsonProperty(UtilsKt.SCHEME_FILE)
        private final String file;

        @JsonProperty("title")
        private final String title;

        /* JADX WARN: Multi-variable type inference failed */
        public SobreatsesuypVideoData() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ SobreatsesuypVideoData copy$default(SobreatsesuypVideoData sobreatsesuypVideoData, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = sobreatsesuypVideoData.title;
            }
            if ((i & 2) != 0) {
                str2 = sobreatsesuypVideoData.file;
            }
            return sobreatsesuypVideoData.copy(str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getFile() {
            return this.file;
        }

        public final SobreatsesuypVideoData copy(@JsonProperty("title") String title, @JsonProperty(UtilsKt.SCHEME_FILE) String file) {
            return new SobreatsesuypVideoData(title, file);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SobreatsesuypVideoData)) {
                return false;
            }
            SobreatsesuypVideoData sobreatsesuypVideoData = (SobreatsesuypVideoData) other;
            return Intrinsics.areEqual(this.title, sobreatsesuypVideoData.title) && Intrinsics.areEqual(this.file, sobreatsesuypVideoData.file);
        }

        public int hashCode() {
            String str = this.title;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.file;
            return iHashCode + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "SobreatsesuypVideoData(title=" + this.title + ", file=" + this.file + ')';
        }

        public SobreatsesuypVideoData(@JsonProperty("title") String str, @JsonProperty(UtilsKt.SCHEME_FILE) String str2) {
            this.title = str;
            this.file = str2;
        }

        public /* synthetic */ SobreatsesuypVideoData(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2);
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getFile() {
            return this.file;
        }
    }
}
