package com.lagradost.cloudstream3.extractors;

import androidx.media3.extractor.WavUtil;
import coil3.util.UtilsKt;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorApiKt;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.ExtractorLinkType;
import com.lagradost.cloudstream3.utils.Qualities;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import com.lagradost.nicehttp.ResponseParser;
import io.ktor.http.ContentDisposition;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
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
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Gofile.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001:\u0005\"#$%&B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00100\u00142\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00100\u0014H\u0096@¢\u0006\u0002\u0010\u0018J\u0012\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0005H\u0002J\u0010\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020!H\u0002R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Gofile;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "mainUrl", "getMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "mainApi", "getUrl", "", ImagesContract.URL, "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getQuality", "", "str", "roundTo2Decimals", ES6Iterator.VALUE_PROPERTY, "", "formatBytes", "bytes", "", "AccountResponse", "AccountData", "GofileResponse", "GofileData", "GofileFile", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class Gofile extends ExtractorApi {
    private final boolean requiresReferer;
    private final String name = "Gofile";
    private final String mainUrl = "https://gofile.io";
    private final String mainApi = "https://api.gofile.io";

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Gofile$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: Gofile.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Gofile", f = "Gofile.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, l = {27, 31, 39, 53}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "id", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "id", "token", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "id", "token", "globalRes", "wt", "headers", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "id", "token", "globalRes", "wt", "headers", "parsedResponse", "childrenMap", UtilsKt.SCHEME_FILE, "fileName", "formattedSize", ContentDisposition.Parameters.Size}, nl = {29, 32, 42, 52}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$13", "L$14", "L$15", "J$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        long J$0;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$15;
        Object L$16;
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
            return Gofile.getUrl$suspendImpl(Gofile.this, (String) null, (String) null, (Function1<? super SubtitleFile, Unit>) null, (Function1<? super ExtractorLink, Unit>) null, (Continuation<? super Unit>) this);
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

    /* JADX WARN: Code duplicated, block: B:72:0x0399  */
    /* JADX WARN: Code duplicated, block: B:74:0x03ad  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:90:0x048e -> B:91:0x0498). Please report as a decompilation issue!!! */
    static /* synthetic */ Object getUrl$suspendImpl(Gofile gofile, String str, String str2, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        List<String> groupValues;
        String str3;
        int i;
        Object obj;
        AnonymousClass1 anonymousClass2;
        Gofile gofile2;
        Object obj2;
        String str4;
        String str5;
        Function1<? super SubtitleFile, Unit> function3;
        Function1<? super ExtractorLink, Unit> function4;
        String str6;
        NiceResponse niceResponse;
        Object safe;
        AccountData data;
        String token;
        Object obj3;
        AnonymousClass1 anonymousClass3;
        String str7;
        Function1<? super ExtractorLink, Unit> function5;
        Gofile gofile3;
        String text;
        MatchResult matchResultFind$default;
        List<String> groupValues2;
        String str8;
        AnonymousClass1 anonymousClass4;
        Map map;
        String str9;
        String str10;
        String str11;
        String str12;
        Function1<? super SubtitleFile, Unit> function6;
        String str13;
        Function1<? super ExtractorLink, Unit> function7;
        Gofile gofile4;
        Object safe2;
        GofileResponse gofileResponse;
        GofileData data2;
        Map<String, GofileFile> children;
        String str14;
        Function1<? super SubtitleFile, Unit> function8;
        GofileResponse gofileResponse2;
        Map map2;
        String str15;
        Gofile gofile5;
        String str16;
        String str17;
        Map<String, GofileFile> map3;
        Iterator<Map.Entry<String, GofileFile>> it;
        AnonymousClass1 anonymousClass5;
        String link;
        String str18;
        Map<String, GofileFile> map4;
        AnonymousClass1 anonymousClass6;
        Iterator<Map.Entry<String, GofileFile>> it2;
        Object obj4;
        Gofile gofile6;
        Function1<? super ExtractorLink, Unit> function9;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = gofile.new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = gofile.new AnonymousClass1(continuation);
        }
        Object obj5 = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass1.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj5);
                MatchResult matchResultFind$default2 = Regex.find$default(new Regex("/(?:\\?c=|d/)([\\da-zA-Z-]+)"), str, 0, 2, null);
                if (matchResultFind$default2 == null || (groupValues = matchResultFind$default2.getGroupValues()) == null || (str3 = groupValues.get(1)) == null) {
                    return Unit.INSTANCE;
                }
                Requests app2 = MainActivityKt.getApp();
                String str19 = gofile.mainApi + "/accounts";
                anonymousClass1.L$0 = gofile;
                anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(str);
                anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(str2);
                anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(function1);
                anonymousClass1.L$4 = function2;
                anonymousClass1.L$5 = str3;
                anonymousClass1.label = 1;
                i = 2;
                AnonymousClass1 anonymousClass7 = anonymousClass1;
                obj = coroutine_suspended;
                Object objPost$default = Requests.post$default(app2, str19, null, null, null, null, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass7, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE, null);
                anonymousClass2 = anonymousClass7;
                if (objPost$default != obj) {
                    gofile2 = gofile;
                    obj2 = objPost$default;
                    str4 = str;
                    str5 = str2;
                    function3 = function1;
                    function4 = function2;
                    str6 = str3;
                }
                return obj;
            }
            if (i2 == 1) {
                String str20 = (String) anonymousClass1.L$5;
                Function1<? super ExtractorLink, Unit> function10 = (Function1) anonymousClass1.L$4;
                Function1<? super SubtitleFile, Unit> function11 = (Function1) anonymousClass1.L$3;
                String str21 = (String) anonymousClass1.L$2;
                String str22 = (String) anonymousClass1.L$1;
                Gofile gofile7 = (Gofile) anonymousClass1.L$0;
                ResultKt.throwOnFailure(obj5);
                str6 = str20;
                obj2 = obj5;
                function3 = function11;
                str5 = str21;
                i = 2;
                str4 = str22;
                function4 = function10;
                anonymousClass2 = anonymousClass1;
                obj = coroutine_suspended;
                gofile2 = gofile7;
            } else {
                if (i2 == 2) {
                    token = (String) anonymousClass1.L$6;
                    String str23 = (String) anonymousClass1.L$5;
                    Function1<? super ExtractorLink, Unit> function12 = (Function1) anonymousClass1.L$4;
                    Function1<? super SubtitleFile, Unit> function13 = (Function1) anonymousClass1.L$3;
                    String str24 = (String) anonymousClass1.L$2;
                    String str25 = (String) anonymousClass1.L$1;
                    Gofile gofile8 = (Gofile) anonymousClass1.L$0;
                    ResultKt.throwOnFailure(obj5);
                    function5 = function12;
                    function3 = function13;
                    str5 = str24;
                    str4 = str25;
                    gofile3 = gofile8;
                    str7 = str23;
                    anonymousClass3 = anonymousClass1;
                    obj = coroutine_suspended;
                    obj3 = obj5;
                    i = 2;
                    text = ((NiceResponse) obj3).getText();
                    matchResultFind$default = Regex.find$default(new Regex("appdata\\.wt\\s*=\\s*[\\\"']([^\\\"']+)[\\\"']"), text, 0, i, null);
                    if (matchResultFind$default != null || (groupValues2 = matchResultFind$default.getGroupValues()) == null || (str8 = groupValues2.get(1)) == null) {
                        return Unit.INSTANCE;
                    }
                    Pair[] pairArr = new Pair[i];
                    pairArr[0] = TuplesKt.to("Authorization", "Bearer " + token);
                    pairArr[1] = TuplesKt.to("X-Website-Token", str8);
                    Map mapMapOf = MapsKt.mapOf(pairArr);
                    Requests app3 = MainActivityKt.getApp();
                    String str26 = gofile3.mainApi + "/contents/" + str7 + "?contentFilter=&page=1&pageSize=1000&sortField=name&sortDirection=1";
                    anonymousClass3.L$0 = gofile3;
                    anonymousClass3.L$1 = SpillingKt.nullOutSpilledVariable(str4);
                    anonymousClass3.L$2 = SpillingKt.nullOutSpilledVariable(str5);
                    anonymousClass3.L$3 = SpillingKt.nullOutSpilledVariable(function3);
                    anonymousClass3.L$4 = function5;
                    anonymousClass3.L$5 = SpillingKt.nullOutSpilledVariable(str7);
                    anonymousClass3.L$6 = token;
                    anonymousClass3.L$7 = SpillingKt.nullOutSpilledVariable(text);
                    anonymousClass3.L$8 = SpillingKt.nullOutSpilledVariable(str8);
                    anonymousClass3.L$9 = SpillingKt.nullOutSpilledVariable(mapMapOf);
                    anonymousClass3.label = 3;
                    Function1<? super ExtractorLink, Unit> function14 = function5;
                    Gofile gofile9 = gofile3;
                    anonymousClass4 = anonymousClass3;
                    String str27 = str7;
                    map = mapMapOf;
                    Object obj6 = Requests.get$default(app3, str26, map, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass4, 4092, null);
                    if (obj6 != obj) {
                        str9 = token;
                        str10 = str27;
                        obj5 = obj6;
                        str11 = str4;
                        str12 = str5;
                        function6 = function3;
                        str13 = str8;
                        function7 = function14;
                        gofile4 = gofile9;
                        NiceResponse niceResponse2 = (NiceResponse) obj5;
                        ResponseParser parser = niceResponse2.getParser();
                        Intrinsics.checkNotNull(parser);
                        safe2 = parser.parseSafe(niceResponse2.getText(), Reflection.getOrCreateKotlinClass(GofileResponse.class));
                        gofileResponse = (GofileResponse) safe2;
                        if (gofileResponse != null) {
                        }
                        return Unit.INSTANCE;
                    }
                    return obj;
                }
                if (i2 == 3) {
                    Map map5 = (Map) anonymousClass1.L$9;
                    str13 = (String) anonymousClass1.L$8;
                    String str28 = (String) anonymousClass1.L$7;
                    str9 = (String) anonymousClass1.L$6;
                    str10 = (String) anonymousClass1.L$5;
                    function7 = (Function1) anonymousClass1.L$4;
                    function6 = (Function1) anonymousClass1.L$3;
                    str12 = (String) anonymousClass1.L$2;
                    str11 = (String) anonymousClass1.L$1;
                    gofile4 = (Gofile) anonymousClass1.L$0;
                    ResultKt.throwOnFailure(obj5);
                    anonymousClass4 = anonymousClass1;
                    obj = coroutine_suspended;
                    text = str28;
                    map = map5;
                    NiceResponse niceResponse3 = (NiceResponse) obj5;
                    try {
                        ResponseParser parser2 = niceResponse3.getParser();
                        Intrinsics.checkNotNull(parser2);
                        safe2 = parser2.parseSafe(niceResponse3.getText(), Reflection.getOrCreateKotlinClass(GofileResponse.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                        safe2 = null;
                    }
                    gofileResponse = (GofileResponse) safe2;
                    if (gofileResponse != null || (data2 = gofileResponse.getData()) == null || (children = data2.getChildren()) == null) {
                        return Unit.INSTANCE;
                    }
                    str14 = str10;
                    function8 = function6;
                    gofileResponse2 = gofileResponse;
                    map2 = map;
                    str15 = str12;
                    gofile5 = gofile4;
                    str16 = str13;
                    str17 = str9;
                    map3 = children;
                    it = children.entrySet().iterator();
                    anonymousClass5 = anonymousClass4;
                    while (it.hasNext()) {
                        GofileFile value = it.next().getValue();
                        link = value.getLink();
                        if (link == null && link.length() != 0 && Intrinsics.areEqual(value.getType(), UtilsKt.SCHEME_FILE)) {
                            String name = value.getName();
                            if (name == null) {
                                name = "";
                            }
                            Long size = value.getSize();
                            long jLongValue = size != null ? size.longValue() : 0L;
                            str18 = str15;
                            map4 = map3;
                            long j = jLongValue;
                            GofileResponse gofileResponse3 = gofileResponse2;
                            String bytes = gofile5.formatBytes(j);
                            Map map6 = map2;
                            String str29 = str16;
                            String str30 = "[Gofile] " + name + " [" + bytes + AbstractJsonLexerKt.END_LIST;
                            String link2 = value.getLink();
                            ExtractorLinkType extractorLinkType = ExtractorLinkType.VIDEO;
                            AnonymousClass2 anonymousClass8 = gofile5.new AnonymousClass2(name, str17, null);
                            anonymousClass5.L$0 = gofile5;
                            anonymousClass5.L$1 = SpillingKt.nullOutSpilledVariable(str11);
                            anonymousClass5.L$2 = SpillingKt.nullOutSpilledVariable(str18);
                            anonymousClass5.L$3 = SpillingKt.nullOutSpilledVariable(function8);
                            anonymousClass5.L$4 = function7;
                            anonymousClass5.L$5 = SpillingKt.nullOutSpilledVariable(str14);
                            anonymousClass5.L$6 = str17;
                            anonymousClass5.L$7 = SpillingKt.nullOutSpilledVariable(text);
                            anonymousClass5.L$8 = SpillingKt.nullOutSpilledVariable(str29);
                            anonymousClass5.L$9 = SpillingKt.nullOutSpilledVariable(map6);
                            anonymousClass5.L$10 = SpillingKt.nullOutSpilledVariable(gofileResponse3);
                            anonymousClass5.L$11 = SpillingKt.nullOutSpilledVariable(map4);
                            anonymousClass5.L$12 = it;
                            anonymousClass5.L$13 = SpillingKt.nullOutSpilledVariable(value);
                            anonymousClass5.L$14 = SpillingKt.nullOutSpilledVariable(name);
                            anonymousClass5.L$15 = SpillingKt.nullOutSpilledVariable(bytes);
                            anonymousClass5.L$16 = function7;
                            anonymousClass5.J$0 = j;
                            anonymousClass5.label = 4;
                            AnonymousClass1 anonymousClass9 = anonymousClass5;
                            Object objNewExtractorLink = ExtractorApiKt.newExtractorLink("Gofile", str30, link2, extractorLinkType, anonymousClass8, anonymousClass9);
                            anonymousClass6 = anonymousClass9;
                            if (objNewExtractorLink != obj) {
                                it2 = it;
                                gofileResponse2 = gofileResponse3;
                                map2 = map6;
                                str16 = str29;
                                obj4 = objNewExtractorLink;
                                gofile6 = gofile5;
                                function9 = function7;
                            }
                            return obj;
                        }
                        anonymousClass5 = anonymousClass5;
                        str15 = str15;
                        gofileResponse2 = gofileResponse2;
                        map2 = map2;
                        map3 = map3;
                        str16 = str16;
                        while (it.hasNext()) {
                            GofileFile value2 = it.next().getValue();
                            link = value2.getLink();
                            if (link == null) {
                            }
                            anonymousClass5 = anonymousClass5;
                            str15 = str15;
                            gofileResponse2 = gofileResponse2;
                            map2 = map2;
                            map3 = map3;
                            str16 = str16;
                        }
                    }
                    return Unit.INSTANCE;
                }
                if (i2 != 4) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                long j2 = anonymousClass1.J$0;
                Function1<? super ExtractorLink, Unit> function15 = (Function1) anonymousClass1.L$16;
                Iterator<Map.Entry<String, GofileFile>> it3 = (Iterator) anonymousClass1.L$12;
                Map<String, GofileFile> map7 = (Map) anonymousClass1.L$11;
                gofileResponse2 = (GofileResponse) anonymousClass1.L$10;
                map2 = (Map) anonymousClass1.L$9;
                str16 = (String) anonymousClass1.L$8;
                String str31 = (String) anonymousClass1.L$7;
                str17 = (String) anonymousClass1.L$6;
                str14 = (String) anonymousClass1.L$5;
                Function1<? super ExtractorLink, Unit> function16 = (Function1) anonymousClass1.L$4;
                function8 = (Function1) anonymousClass1.L$3;
                String str32 = (String) anonymousClass1.L$2;
                String str33 = (String) anonymousClass1.L$1;
                Gofile gofile10 = (Gofile) anonymousClass1.L$0;
                ResultKt.throwOnFailure(obj5);
                map4 = map7;
                text = str31;
                it2 = it3;
                str11 = str33;
                function7 = function16;
                obj4 = obj5;
                str18 = str32;
                gofile6 = gofile10;
                anonymousClass6 = anonymousClass1;
                obj = coroutine_suspended;
                function9 = function15;
            }
            function9.invoke(obj4);
            gofile5 = gofile6;
            anonymousClass5 = anonymousClass6;
            it = it2;
            str15 = str18;
            map3 = map4;
            while (it.hasNext()) {
                GofileFile value3 = it.next().getValue();
                link = value3.getLink();
                if (link == null) {
                }
                anonymousClass5 = anonymousClass5;
                str15 = str15;
                gofileResponse2 = gofileResponse2;
                map2 = map2;
                map3 = map3;
                str16 = str16;
            }
            return Unit.INSTANCE;
            ResponseParser parser3 = niceResponse.getParser();
            Intrinsics.checkNotNull(parser3);
            safe = parser3.parseSafe(niceResponse.getText(), Reflection.getOrCreateKotlinClass(AccountResponse.class));
        } catch (Exception e2) {
            e2.printStackTrace();
            safe = null;
        }
        niceResponse = (NiceResponse) obj2;
        AccountResponse accountResponse = (AccountResponse) safe;
        if (accountResponse == null || (data = accountResponse.getData()) == null || (token = data.getToken()) == null) {
            return Unit.INSTANCE;
        }
        Requests app4 = MainActivityKt.getApp();
        String str34 = gofile2.getMainUrl() + "/dist/js/config.js";
        anonymousClass2.L$0 = gofile2;
        anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str4);
        anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str5);
        anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function3);
        anonymousClass2.L$4 = function4;
        anonymousClass2.L$5 = str6;
        anonymousClass2.L$6 = token;
        anonymousClass2.label = i;
        Function1<? super ExtractorLink, Unit> function17 = function4;
        String str35 = str6;
        Gofile gofile11 = gofile2;
        AnonymousClass1 anonymousClass10 = anonymousClass2;
        obj3 = Requests.get$default(app4, str34, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass10, 4094, null);
        anonymousClass3 = anonymousClass10;
        if (obj3 != obj) {
            str7 = str35;
            function5 = function17;
            gofile3 = gofile11;
            text = ((NiceResponse) obj3).getText();
            matchResultFind$default = Regex.find$default(new Regex("appdata\\.wt\\s*=\\s*[\\\"']([^\\\"']+)[\\\"']"), text, 0, i, null);
            if (matchResultFind$default != null) {
            }
            return Unit.INSTANCE;
        }
        return obj;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Gofile$getUrl$2, reason: invalid class name */
    /* JADX INFO: compiled from: Gofile.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Gofile$getUrl$2", f = "Gofile.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $fileName;
        final /* synthetic */ String $token;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, String str2, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$fileName = str;
            this.$token = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = Gofile.this.new AnonymousClass2(this.$fileName, this.$token, continuation);
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
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                extractorLink.setQuality(Gofile.this.getQuality(this.$fileName));
                extractorLink.setHeaders(MapsKt.mapOf(TuplesKt.to("Cookie", "accountToken=" + this.$token)));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getQuality(String str) {
        List<String> groupValues;
        String str2;
        Integer intOrNull;
        Regex regex = new Regex("(\\d{3,4})[pP]");
        if (str == null) {
            str = "";
        }
        MatchResult matchResultFind$default = Regex.find$default(regex, str, 0, 2, null);
        return (matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null || (str2 = (String) CollectionsKt.getOrNull(groupValues, 1)) == null || (intOrNull = StringsKt.toIntOrNull(str2)) == null) ? Qualities.Unknown.getValue() : intOrNull.intValue();
    }

    private final String roundTo2Decimals(double value) {
        double dRint = Math.rint(value * 100.0d) / 100.0d;
        long j = (long) dRint;
        return j + '.' + StringsKt.padStart(String.valueOf((long) Math.rint((dRint - j) * 100.0d)), 2, '0');
    }

    private final String formatBytes(long bytes) {
        if (bytes < 1073741824) {
            return roundTo2Decimals(bytes / 1048576.0d) + " MB";
        }
        return roundTo2Decimals(bytes / 1.073741824E9d) + " GB";
    }

    /* JADX INFO: compiled from: Gofile.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Gofile$AccountResponse;", "", "data", "Lcom/lagradost/cloudstream3/extractors/Gofile$AccountData;", "<init>", "(Lcom/lagradost/cloudstream3/extractors/Gofile$AccountData;)V", "getData", "()Lcom/lagradost/cloudstream3/extractors/Gofile$AccountData;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class AccountResponse {

        @JsonProperty("data")
        private final AccountData data;

        /* JADX WARN: Multi-variable type inference failed */
        public AccountResponse() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ AccountResponse copy$default(AccountResponse accountResponse, AccountData accountData, int i, Object obj) {
            if ((i & 1) != 0) {
                accountData = accountResponse.data;
            }
            return accountResponse.copy(accountData);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final AccountData getData() {
            return this.data;
        }

        public final AccountResponse copy(@JsonProperty("data") AccountData data) {
            return new AccountResponse(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof AccountResponse) && Intrinsics.areEqual(this.data, ((AccountResponse) other).data);
        }

        public int hashCode() {
            AccountData accountData = this.data;
            if (accountData == null) {
                return 0;
            }
            return accountData.hashCode();
        }

        public String toString() {
            return "AccountResponse(data=" + this.data + ')';
        }

        public AccountResponse(@JsonProperty("data") AccountData accountData) {
            this.data = accountData;
        }

        public /* synthetic */ AccountResponse(AccountData accountData, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : accountData);
        }

        public final AccountData getData() {
            return this.data;
        }
    }

    /* JADX INFO: compiled from: Gofile.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Gofile$AccountData;", "", "token", "", "<init>", "(Ljava/lang/String;)V", "getToken", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class AccountData {

        @JsonProperty("token")
        private final String token;

        /* JADX WARN: Multi-variable type inference failed */
        public AccountData() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ AccountData copy$default(AccountData accountData, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = accountData.token;
            }
            return accountData.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getToken() {
            return this.token;
        }

        public final AccountData copy(@JsonProperty("token") String token) {
            return new AccountData(token);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof AccountData) && Intrinsics.areEqual(this.token, ((AccountData) other).token);
        }

        public int hashCode() {
            String str = this.token;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        public String toString() {
            return "AccountData(token=" + this.token + ')';
        }

        public AccountData(@JsonProperty("token") String str) {
            this.token = str;
        }

        public /* synthetic */ AccountData(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str);
        }

        public final String getToken() {
            return this.token;
        }
    }

    /* JADX INFO: compiled from: Gofile.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Gofile$GofileResponse;", "", "data", "Lcom/lagradost/cloudstream3/extractors/Gofile$GofileData;", "<init>", "(Lcom/lagradost/cloudstream3/extractors/Gofile$GofileData;)V", "getData", "()Lcom/lagradost/cloudstream3/extractors/Gofile$GofileData;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class GofileResponse {

        @JsonProperty("data")
        private final GofileData data;

        /* JADX WARN: Multi-variable type inference failed */
        public GofileResponse() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ GofileResponse copy$default(GofileResponse gofileResponse, GofileData gofileData, int i, Object obj) {
            if ((i & 1) != 0) {
                gofileData = gofileResponse.data;
            }
            return gofileResponse.copy(gofileData);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final GofileData getData() {
            return this.data;
        }

        public final GofileResponse copy(@JsonProperty("data") GofileData data) {
            return new GofileResponse(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof GofileResponse) && Intrinsics.areEqual(this.data, ((GofileResponse) other).data);
        }

        public int hashCode() {
            GofileData gofileData = this.data;
            if (gofileData == null) {
                return 0;
            }
            return gofileData.hashCode();
        }

        public String toString() {
            return "GofileResponse(data=" + this.data + ')';
        }

        public GofileResponse(@JsonProperty("data") GofileData gofileData) {
            this.data = gofileData;
        }

        public /* synthetic */ GofileResponse(GofileData gofileData, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : gofileData);
        }

        public final GofileData getData() {
            return this.data;
        }
    }

    /* JADX INFO: compiled from: Gofile.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0016\b\u0003\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003HÆ\u0003J!\u0010\u000b\u001a\u00020\u00002\u0016\b\u0003\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004J\n\u0010\u0011\u001a\u00020\u0004HÖ\u0081\u0004R$\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0012"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Gofile$GofileData;", "", "children", "", "", "Lcom/lagradost/cloudstream3/extractors/Gofile$GofileFile;", "<init>", "(Ljava/util/Map;)V", "getChildren", "()Ljava/util/Map;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class GofileData {

        @JsonProperty("children")
        private final Map<String, GofileFile> children;

        /* JADX WARN: Multi-variable type inference failed */
        public GofileData() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ GofileData copy$default(GofileData gofileData, Map map, int i, Object obj) {
            if ((i & 1) != 0) {
                map = gofileData.children;
            }
            return gofileData.copy(map);
        }

        public final Map<String, GofileFile> component1() {
            return this.children;
        }

        public final GofileData copy(@JsonProperty("children") Map<String, GofileFile> children) {
            return new GofileData(children);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof GofileData) && Intrinsics.areEqual(this.children, ((GofileData) other).children);
        }

        public int hashCode() {
            Map<String, GofileFile> map = this.children;
            if (map == null) {
                return 0;
            }
            return map.hashCode();
        }

        public String toString() {
            return "GofileData(children=" + this.children + ')';
        }

        public GofileData(@JsonProperty("children") Map<String, GofileFile> map) {
            this.children = map;
        }

        public /* synthetic */ GofileData(Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : map);
        }

        public final Map<String, GofileFile> getChildren() {
            return this.children;
        }
    }

    /* JADX INFO: compiled from: Gofile.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u000fJ>\u0010\u0015\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001d"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Gofile$GofileFile;", "", "type", "", "name", "link", ContentDisposition.Parameters.Size, "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)V", "getType", "()Ljava/lang/String;", "getName", "getLink", "getSize", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)Lcom/lagradost/cloudstream3/extractors/Gofile$GofileFile;", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class GofileFile {

        @JsonProperty("link")
        private final String link;

        @JsonProperty("name")
        private final String name;

        @JsonProperty(ContentDisposition.Parameters.Size)
        private final Long size;

        @JsonProperty("type")
        private final String type;

        public GofileFile() {
            this(null, null, null, null, 15, null);
        }

        public static /* synthetic */ GofileFile copy$default(GofileFile gofileFile, String str, String str2, String str3, Long l, int i, Object obj) {
            if ((i & 1) != 0) {
                str = gofileFile.type;
            }
            if ((i & 2) != 0) {
                str2 = gofileFile.name;
            }
            if ((i & 4) != 0) {
                str3 = gofileFile.link;
            }
            if ((i & 8) != 0) {
                l = gofileFile.size;
            }
            return gofileFile.copy(str, str2, str3, l);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getLink() {
            return this.link;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Long getSize() {
            return this.size;
        }

        public final GofileFile copy(@JsonProperty("type") String type, @JsonProperty("name") String name, @JsonProperty("link") String link, @JsonProperty(ContentDisposition.Parameters.Size) Long size) {
            return new GofileFile(type, name, link, size);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof GofileFile)) {
                return false;
            }
            GofileFile gofileFile = (GofileFile) other;
            return Intrinsics.areEqual(this.type, gofileFile.type) && Intrinsics.areEqual(this.name, gofileFile.name) && Intrinsics.areEqual(this.link, gofileFile.link) && Intrinsics.areEqual(this.size, gofileFile.size);
        }

        public int hashCode() {
            String str = this.type;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.name;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.link;
            int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            Long l = this.size;
            return iHashCode3 + (l != null ? l.hashCode() : 0);
        }

        public String toString() {
            return "GofileFile(type=" + this.type + ", name=" + this.name + ", link=" + this.link + ", size=" + this.size + ')';
        }

        public GofileFile(@JsonProperty("type") String str, @JsonProperty("name") String str2, @JsonProperty("link") String str3, @JsonProperty(ContentDisposition.Parameters.Size) Long l) {
            this.type = str;
            this.name = str2;
            this.link = str3;
            this.size = l;
        }

        public final String getType() {
            return this.type;
        }

        public final String getName() {
            return this.name;
        }

        public final String getLink() {
            return this.link;
        }

        public /* synthetic */ GofileFile(String str, String str2, String str3, Long l, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? 0L : l);
        }

        public final Long getSize() {
            return this.size;
        }
    }
}
