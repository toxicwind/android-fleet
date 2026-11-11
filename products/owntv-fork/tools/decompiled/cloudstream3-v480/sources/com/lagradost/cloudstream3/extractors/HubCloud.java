package com.lagradost.cloudstream3.extractors;

import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.api.Log;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.ParCollectionsKt;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorApiKt;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.Qualities;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import io.ktor.http.ContentDisposition;
import io.ktor.http.URLUtilsKt;
import io.ktor.http.Url;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
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
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/* JADX INFO: compiled from: HubCloud.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000f0\u00132\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u000f0\u0013H\u0096@¢\u0006\u0002\u0010\u0017J\u0012\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0005H\u0002J\u0010\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H\u0002J\u000e\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0005R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u001e"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/HubCloud;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "mainUrl", "getMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "getUrl", "", ImagesContract.URL, "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getIndexQuality", "", "str", "getBaseUrl", "cleanTitle", "title", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class HubCloud extends ExtractorApi {
    private final boolean requiresReferer;
    private final String name = "Hub-Cloud";
    private final String mainUrl = "https://hubcloud.*";

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.HubCloud$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: HubCloud.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.HubCloud", f = "HubCloud.kt", i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {36, 53, 65}, m = "getUrl", n = {ImagesContract.URL, "referer", "subtitleCallback", "callback", "tag", "realUrl", "baseUrl", ImagesContract.URL, "referer", "subtitleCallback", "callback", "tag", "realUrl", "baseUrl", "href", ImagesContract.URL, "referer", "subtitleCallback", "callback", "tag", "realUrl", "baseUrl", "href", "document", ContentDisposition.Parameters.Size, "header", "headerDetails", "labelExtras", "quality"}, nl = {37, 54, 155}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "I$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
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
            return HubCloud.this.getUrl(null, null, null, null, this);
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

    /* JADX WARN: Code duplicated, block: B:108:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:48:0x01be A[Catch: Exception -> 0x01e7, TRY_LEAVE, TryCatch #3 {Exception -> 0x01e7, blocks: (B:45:0x0194, B:48:0x01be), top: B:102:0x0194 }] */
    /* JADX WARN: Code duplicated, block: B:64:0x023f  */
    /* JADX WARN: Code duplicated, block: B:66:0x0249  */
    /* JADX WARN: Code duplicated, block: B:69:0x02a5  */
    /* JADX WARN: Code duplicated, block: B:72:0x02be  */
    /* JADX WARN: Code duplicated, block: B:73:0x02c3  */
    /* JADX WARN: Code duplicated, block: B:75:0x02c7  */
    /* JADX WARN: Code duplicated, block: B:76:0x02ca  */
    /* JADX WARN: Code duplicated, block: B:79:0x02d3  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Code duplicated, block: B:80:0x02d8  */
    /* JADX WARN: Code duplicated, block: B:82:0x02dc  */
    /* JADX WARN: Code duplicated, block: B:83:0x02df  */
    /* JADX WARN: Code duplicated, block: B:86:0x02f9  */
    /* JADX WARN: Code duplicated, block: B:87:0x0310  */
    /* JADX WARN: Code duplicated, block: B:90:0x031d  */
    /* JADX WARN: Instruction removed from duplicated block: B:90:0x031d, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v2 */
    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public Object getUrl(String str, String str2, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        String str3;
        boolean z;
        String str4;
        String baseUrl;
        int i;
        String str5;
        char c;
        int i2;
        String str6;
        String str7;
        String str8;
        Function1<? super ExtractorLink, Unit> function3;
        Function1<? super SubtitleFile, Unit> function4;
        String str9;
        String str10;
        Function1<? super ExtractorLink, Unit> function5;
        Object obj;
        AnonymousClass1 anonymousClass2;
        String str11;
        String str12;
        String str13;
        String str14;
        Function1<? super SubtitleFile, Unit> function6;
        String str15;
        String strAttr;
        Element elementSelectFirst;
        String strText;
        String str16;
        Element elementSelectFirst2;
        String strText2;
        String str17;
        String strCleanTitle;
        StringBuilder sb;
        char c2;
        Elements elements;
        AnonymousClass2 anonymousClass3;
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
        Object obj2 = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = anonymousClass1.label;
        String str18 = null;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj2);
            str3 = "HubCloud";
            try {
                URLUtilsKt.Url(str);
                z = true;
            } catch (Exception e) {
                Log.INSTANCE.e("HubCloud", "Invalid URL: " + e.getMessage());
                z = false;
            }
            str4 = z ? str : null;
            if (str4 == null) {
                return Unit.INSTANCE;
            }
            baseUrl = getBaseUrl(str4);
            try {
                if (!StringsKt.contains$default((CharSequence) str4, (CharSequence) "hubcloud.php", false, 2, (Object) null)) {
                    try {
                        Requests app2 = MainActivityKt.getApp();
                        anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(str);
                        anonymousClass1.L$1 = str2;
                        anonymousClass1.L$2 = function1;
                        anonymousClass1.L$3 = function2;
                        anonymousClass1.L$4 = "HubCloud";
                        anonymousClass1.L$5 = SpillingKt.nullOutSpilledVariable(str4);
                        anonymousClass1.L$6 = baseUrl;
                        anonymousClass1.label = 1;
                        String str19 = str4;
                        str18 = null;
                        baseUrl = baseUrl;
                        c = 0;
                        AnonymousClass1 anonymousClass4 = anonymousClass1;
                        i = 2;
                        i2 = 1;
                        try {
                            obj2 = Requests.get$default(app2, str19, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass4, 4094, null);
                            anonymousClass1 = anonymousClass4;
                            if (obj2 != coroutine_suspended) {
                                str = str;
                                str2 = str2;
                                function1 = function1;
                                function2 = function2;
                                str3 = "HubCloud";
                                str4 = str19;
                                str5 = baseUrl;
                                strAttr = ((NiceResponse) obj2).getDocument().select("#download").attr("href");
                                Intrinsics.checkNotNullExpressionValue(strAttr, "attr(...)");
                                if (!StringsKt.startsWith(strAttr, "http", (boolean) i2)) {
                                    StringBuilder sb2 = new StringBuilder();
                                    char[] cArr = new char[i2];
                                    cArr[c] = '/';
                                    sb2.append(StringsKt.trimEnd(str5, cArr));
                                    sb2.append('/');
                                    char[] cArr2 = new char[i2];
                                    cArr2[c] = '/';
                                    sb2.append(StringsKt.trimStart(strAttr, cArr2));
                                    strAttr = sb2.toString();
                                }
                                String str20 = str4;
                                str4 = strAttr;
                                str6 = str20;
                                String str21 = str3;
                                baseUrl = str5;
                                str3 = str21;
                                str7 = str3;
                                str8 = str4;
                                str5 = baseUrl;
                                function3 = function2;
                                function4 = function1;
                                str9 = str2;
                                str10 = str;
                                if (StringsKt.isBlank(str8)) {
                                    Log.INSTANCE.w(str7, "No valid href found");
                                    return Unit.INSTANCE;
                                }
                                Requests app3 = MainActivityKt.getApp();
                                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(str10);
                                anonymousClass1.L$1 = str9;
                                anonymousClass1.L$2 = function4;
                                anonymousClass1.L$3 = function3;
                                anonymousClass1.L$4 = str7;
                                anonymousClass1.L$5 = SpillingKt.nullOutSpilledVariable(str6);
                                anonymousClass1.L$6 = SpillingKt.nullOutSpilledVariable(str5);
                                anonymousClass1.L$7 = SpillingKt.nullOutSpilledVariable(str8);
                                anonymousClass1.label = i;
                                AnonymousClass1 anonymousClass5 = anonymousClass1;
                                function5 = function3;
                                obj = Requests.get$default(app3, str8, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass5, 4094, null);
                                anonymousClass2 = anonymousClass5;
                                if (obj != coroutine_suspended) {
                                    str11 = str6;
                                    str12 = str5;
                                    obj2 = obj;
                                    str13 = str8;
                                    str14 = str10;
                                    function6 = function4;
                                    str15 = str9;
                                    Document document = ((NiceResponse) obj2).getDocument();
                                    elementSelectFirst = document.selectFirst("i#size");
                                    if (elementSelectFirst != null) {
                                        strText = elementSelectFirst.text();
                                    } else {
                                        strText = str18;
                                    }
                                    if (strText == null) {
                                        str16 = "";
                                    } else {
                                        str16 = strText;
                                    }
                                    elementSelectFirst2 = document.selectFirst("div.card-header");
                                    if (elementSelectFirst2 != null) {
                                        strText2 = elementSelectFirst2.text();
                                    } else {
                                        strText2 = str18;
                                    }
                                    if (strText2 == null) {
                                        str17 = "";
                                    } else {
                                        str17 = strText2;
                                    }
                                    strCleanTitle = cleanTitle(str17);
                                    sb = new StringBuilder();
                                    Function1<? super ExtractorLink, Unit> function7 = function5;
                                    String str22 = str15;
                                    if (strCleanTitle.length() > 0) {
                                        StringBuilder sb3 = new StringBuilder("[");
                                        sb3.append(strCleanTitle);
                                        c2 = AbstractJsonLexerKt.END_LIST;
                                        sb3.append(AbstractJsonLexerKt.END_LIST);
                                        sb.append(sb3.toString());
                                    } else {
                                        c2 = AbstractJsonLexerKt.END_LIST;
                                    }
                                    if (str16.length() > 0) {
                                        sb.append("[" + str16 + c2);
                                    }
                                    String string = sb.toString();
                                    int indexQuality = getIndexQuality(str17);
                                    Elements elementsSelect = document.select("div.card-body h2 a.btn");
                                    Intrinsics.checkNotNullExpressionValue(elementsSelect, "select(...)");
                                    elements = elementsSelect;
                                    String str23 = str17;
                                    anonymousClass3 = new AnonymousClass2(function7, str22, string, str7, this, function6, indexQuality, null);
                                    anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(str14);
                                    anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str22);
                                    anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(function6);
                                    anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function7);
                                    anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(str7);
                                    anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(str11);
                                    anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(str12);
                                    anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(str13);
                                    anonymousClass2.L$8 = SpillingKt.nullOutSpilledVariable(document);
                                    anonymousClass2.L$9 = SpillingKt.nullOutSpilledVariable(str16);
                                    anonymousClass2.L$10 = SpillingKt.nullOutSpilledVariable(str23);
                                    anonymousClass2.L$11 = SpillingKt.nullOutSpilledVariable(strCleanTitle);
                                    anonymousClass2.L$12 = SpillingKt.nullOutSpilledVariable(string);
                                    anonymousClass2.I$0 = indexQuality;
                                    anonymousClass2.label = 3;
                                    if (ParCollectionsKt.amap(elements, anonymousClass3, anonymousClass2) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                }
                            }
                        } catch (Exception e2) {
                            e = e2;
                            anonymousClass1 = anonymousClass4;
                            str4 = str19;
                            str5 = baseUrl;
                            Log.INSTANCE.e(str3, "Failed to extract href: " + e.getMessage());
                            str6 = str4;
                            str7 = str3;
                            str8 = "";
                            function3 = function2;
                            function4 = function1;
                            str9 = str2;
                            str10 = str;
                            if (StringsKt.isBlank(str8)) {
                                Log.INSTANCE.w(str7, "No valid href found");
                                return Unit.INSTANCE;
                            }
                            Requests app4 = MainActivityKt.getApp();
                            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(str10);
                            anonymousClass1.L$1 = str9;
                            anonymousClass1.L$2 = function4;
                            anonymousClass1.L$3 = function3;
                            anonymousClass1.L$4 = str7;
                            anonymousClass1.L$5 = SpillingKt.nullOutSpilledVariable(str6);
                            anonymousClass1.L$6 = SpillingKt.nullOutSpilledVariable(str5);
                            anonymousClass1.L$7 = SpillingKt.nullOutSpilledVariable(str8);
                            anonymousClass1.label = i;
                            AnonymousClass1 anonymousClass6 = anonymousClass1;
                            function5 = function3;
                            obj = Requests.get$default(app4, str8, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass6, 4094, null);
                            anonymousClass2 = anonymousClass6;
                            if (obj != coroutine_suspended) {
                                str11 = str6;
                                str12 = str5;
                                obj2 = obj;
                                str13 = str8;
                                str14 = str10;
                                function6 = function4;
                                str15 = str9;
                                Document document2 = ((NiceResponse) obj2).getDocument();
                                elementSelectFirst = document2.selectFirst("i#size");
                                if (elementSelectFirst != null) {
                                    strText = elementSelectFirst.text();
                                } else {
                                    strText = str18;
                                }
                                if (strText == null) {
                                    str16 = "";
                                } else {
                                    str16 = strText;
                                }
                                elementSelectFirst2 = document2.selectFirst("div.card-header");
                                if (elementSelectFirst2 != null) {
                                    strText2 = elementSelectFirst2.text();
                                } else {
                                    strText2 = str18;
                                }
                                if (strText2 == null) {
                                    str17 = "";
                                } else {
                                    str17 = strText2;
                                }
                                strCleanTitle = cleanTitle(str17);
                                sb = new StringBuilder();
                                Function1<? super ExtractorLink, Unit> function8 = function5;
                                String str24 = str15;
                                if (strCleanTitle.length() > 0) {
                                    StringBuilder sb4 = new StringBuilder("[");
                                    sb4.append(strCleanTitle);
                                    c2 = AbstractJsonLexerKt.END_LIST;
                                    sb4.append(AbstractJsonLexerKt.END_LIST);
                                    sb.append(sb4.toString());
                                } else {
                                    c2 = AbstractJsonLexerKt.END_LIST;
                                }
                                if (str16.length() > 0) {
                                    sb.append("[" + str16 + c2);
                                }
                                String string2 = sb.toString();
                                int indexQuality2 = getIndexQuality(str17);
                                Elements elementsSelect2 = document2.select("div.card-body h2 a.btn");
                                Intrinsics.checkNotNullExpressionValue(elementsSelect2, "select(...)");
                                elements = elementsSelect2;
                                String str25 = str17;
                                anonymousClass3 = new AnonymousClass2(function8, str24, string2, str7, this, function6, indexQuality2, null);
                                anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(str14);
                                anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str24);
                                anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(function6);
                                anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function8);
                                anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(str7);
                                anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(str11);
                                anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(str12);
                                anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(str13);
                                anonymousClass2.L$8 = SpillingKt.nullOutSpilledVariable(document2);
                                anonymousClass2.L$9 = SpillingKt.nullOutSpilledVariable(str16);
                                anonymousClass2.L$10 = SpillingKt.nullOutSpilledVariable(str25);
                                anonymousClass2.L$11 = SpillingKt.nullOutSpilledVariable(strCleanTitle);
                                anonymousClass2.L$12 = SpillingKt.nullOutSpilledVariable(string2);
                                anonymousClass2.I$0 = indexQuality2;
                                anonymousClass2.label = 3;
                                if (ParCollectionsKt.amap(elements, anonymousClass3, anonymousClass2) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                return Unit.INSTANCE;
                            }
                            return coroutine_suspended;
                        }
                    } catch (Exception e3) {
                        e = e3;
                        i = 2;
                        str5 = baseUrl;
                        Log.INSTANCE.e(str3, "Failed to extract href: " + e.getMessage());
                        str6 = str4;
                        str7 = str3;
                        str8 = "";
                        function3 = function2;
                        function4 = function1;
                        str9 = str2;
                        str10 = str;
                        if (StringsKt.isBlank(str8)) {
                            Log.INSTANCE.w(str7, "No valid href found");
                            return Unit.INSTANCE;
                        }
                        Requests app5 = MainActivityKt.getApp();
                        anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(str10);
                        anonymousClass1.L$1 = str9;
                        anonymousClass1.L$2 = function4;
                        anonymousClass1.L$3 = function3;
                        anonymousClass1.L$4 = str7;
                        anonymousClass1.L$5 = SpillingKt.nullOutSpilledVariable(str6);
                        anonymousClass1.L$6 = SpillingKt.nullOutSpilledVariable(str5);
                        anonymousClass1.L$7 = SpillingKt.nullOutSpilledVariable(str8);
                        anonymousClass1.label = i;
                        AnonymousClass1 anonymousClass7 = anonymousClass1;
                        function5 = function3;
                        obj = Requests.get$default(app5, str8, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass7, 4094, null);
                        anonymousClass2 = anonymousClass7;
                        if (obj != coroutine_suspended) {
                            str11 = str6;
                            str12 = str5;
                            obj2 = obj;
                            str13 = str8;
                            str14 = str10;
                            function6 = function4;
                            str15 = str9;
                            Document document3 = ((NiceResponse) obj2).getDocument();
                            elementSelectFirst = document3.selectFirst("i#size");
                            if (elementSelectFirst != null) {
                                strText = elementSelectFirst.text();
                            } else {
                                strText = str18;
                            }
                            if (strText == null) {
                                str16 = "";
                            } else {
                                str16 = strText;
                            }
                            elementSelectFirst2 = document3.selectFirst("div.card-header");
                            if (elementSelectFirst2 != null) {
                                strText2 = elementSelectFirst2.text();
                            } else {
                                strText2 = str18;
                            }
                            if (strText2 == null) {
                                str17 = "";
                            } else {
                                str17 = strText2;
                            }
                            strCleanTitle = cleanTitle(str17);
                            sb = new StringBuilder();
                            Function1<? super ExtractorLink, Unit> function9 = function5;
                            String str26 = str15;
                            if (strCleanTitle.length() > 0) {
                                StringBuilder sb5 = new StringBuilder("[");
                                sb5.append(strCleanTitle);
                                c2 = AbstractJsonLexerKt.END_LIST;
                                sb5.append(AbstractJsonLexerKt.END_LIST);
                                sb.append(sb5.toString());
                            } else {
                                c2 = AbstractJsonLexerKt.END_LIST;
                            }
                            if (str16.length() > 0) {
                                sb.append("[" + str16 + c2);
                            }
                            String string3 = sb.toString();
                            int indexQuality3 = getIndexQuality(str17);
                            Elements elementsSelect3 = document3.select("div.card-body h2 a.btn");
                            Intrinsics.checkNotNullExpressionValue(elementsSelect3, "select(...)");
                            elements = elementsSelect3;
                            String str27 = str17;
                            anonymousClass3 = new AnonymousClass2(function9, str26, string3, str7, this, function6, indexQuality3, null);
                            anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(str14);
                            anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str26);
                            anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(function6);
                            anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function9);
                            anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(str7);
                            anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(str11);
                            anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(str12);
                            anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(str13);
                            anonymousClass2.L$8 = SpillingKt.nullOutSpilledVariable(document3);
                            anonymousClass2.L$9 = SpillingKt.nullOutSpilledVariable(str16);
                            anonymousClass2.L$10 = SpillingKt.nullOutSpilledVariable(str27);
                            anonymousClass2.L$11 = SpillingKt.nullOutSpilledVariable(strCleanTitle);
                            anonymousClass2.L$12 = SpillingKt.nullOutSpilledVariable(string3);
                            anonymousClass2.I$0 = indexQuality3;
                            anonymousClass2.label = 3;
                            if (ParCollectionsKt.amap(elements, anonymousClass3, anonymousClass2) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            return Unit.INSTANCE;
                        }
                        return coroutine_suspended;
                    }
                } else {
                    anonymousClass1 = anonymousClass1;
                    i = 2;
                    str = str;
                    str2 = str2;
                    function1 = function1;
                    function2 = function2;
                    str18 = null;
                    str6 = str4;
                    str7 = str3;
                    str8 = str4;
                    str5 = baseUrl;
                    function3 = function2;
                    function4 = function1;
                    str9 = str2;
                    str10 = str;
                    if (StringsKt.isBlank(str8)) {
                        Log.INSTANCE.w(str7, "No valid href found");
                        return Unit.INSTANCE;
                    }
                    Requests app6 = MainActivityKt.getApp();
                    anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(str10);
                    anonymousClass1.L$1 = str9;
                    anonymousClass1.L$2 = function4;
                    anonymousClass1.L$3 = function3;
                    anonymousClass1.L$4 = str7;
                    anonymousClass1.L$5 = SpillingKt.nullOutSpilledVariable(str6);
                    anonymousClass1.L$6 = SpillingKt.nullOutSpilledVariable(str5);
                    anonymousClass1.L$7 = SpillingKt.nullOutSpilledVariable(str8);
                    anonymousClass1.label = i;
                    AnonymousClass1 anonymousClass8 = anonymousClass1;
                    function5 = function3;
                    obj = Requests.get$default(app6, str8, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass8, 4094, null);
                    anonymousClass2 = anonymousClass8;
                    if (obj != coroutine_suspended) {
                        str11 = str6;
                        str12 = str5;
                        obj2 = obj;
                        str13 = str8;
                        str14 = str10;
                        function6 = function4;
                        str15 = str9;
                        Document document4 = ((NiceResponse) obj2).getDocument();
                        elementSelectFirst = document4.selectFirst("i#size");
                        if (elementSelectFirst != null) {
                            strText = elementSelectFirst.text();
                        } else {
                            strText = str18;
                        }
                        if (strText == null) {
                            str16 = "";
                        } else {
                            str16 = strText;
                        }
                        elementSelectFirst2 = document4.selectFirst("div.card-header");
                        if (elementSelectFirst2 != null) {
                            strText2 = elementSelectFirst2.text();
                        } else {
                            strText2 = str18;
                        }
                        if (strText2 == null) {
                            str17 = "";
                        } else {
                            str17 = strText2;
                        }
                        strCleanTitle = cleanTitle(str17);
                        sb = new StringBuilder();
                        Function1<? super ExtractorLink, Unit> function10 = function5;
                        String str28 = str15;
                        if (strCleanTitle.length() > 0) {
                            StringBuilder sb6 = new StringBuilder("[");
                            sb6.append(strCleanTitle);
                            c2 = AbstractJsonLexerKt.END_LIST;
                            sb6.append(AbstractJsonLexerKt.END_LIST);
                            sb.append(sb6.toString());
                        } else {
                            c2 = AbstractJsonLexerKt.END_LIST;
                        }
                        if (str16.length() > 0) {
                            sb.append("[" + str16 + c2);
                        }
                        String string4 = sb.toString();
                        int indexQuality4 = getIndexQuality(str17);
                        Elements elementsSelect4 = document4.select("div.card-body h2 a.btn");
                        Intrinsics.checkNotNullExpressionValue(elementsSelect4, "select(...)");
                        elements = elementsSelect4;
                        String str29 = str17;
                        anonymousClass3 = new AnonymousClass2(function10, str28, string4, str7, this, function6, indexQuality4, null);
                        anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(str14);
                        anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str28);
                        anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(function6);
                        anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function10);
                        anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(str7);
                        anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(str11);
                        anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(str12);
                        anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(str13);
                        anonymousClass2.L$8 = SpillingKt.nullOutSpilledVariable(document4);
                        anonymousClass2.L$9 = SpillingKt.nullOutSpilledVariable(str16);
                        anonymousClass2.L$10 = SpillingKt.nullOutSpilledVariable(str29);
                        anonymousClass2.L$11 = SpillingKt.nullOutSpilledVariable(strCleanTitle);
                        anonymousClass2.L$12 = SpillingKt.nullOutSpilledVariable(string4);
                        anonymousClass2.I$0 = indexQuality4;
                        anonymousClass2.label = 3;
                        if (ParCollectionsKt.amap(elements, anonymousClass3, anonymousClass2) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                }
            } catch (Exception e4) {
                e = e4;
                i = 2;
            }
            return coroutine_suspended;
        }
        if (i3 == 1) {
            str5 = (String) anonymousClass1.L$6;
            str4 = (String) anonymousClass1.L$5;
            str3 = (String) anonymousClass1.L$4;
            function2 = (Function1) anonymousClass1.L$3;
            function1 = (Function1) anonymousClass1.L$2;
            str2 = (String) anonymousClass1.L$1;
            str = (String) anonymousClass1.L$0;
            try {
                ResultKt.throwOnFailure(obj2);
                anonymousClass1 = anonymousClass1;
                i = 2;
                str18 = null;
                c = 0;
                i2 = 1;
                try {
                    strAttr = ((NiceResponse) obj2).getDocument().select("#download").attr("href");
                    Intrinsics.checkNotNullExpressionValue(strAttr, "attr(...)");
                    if (!StringsKt.startsWith(strAttr, "http", (boolean) i2)) {
                        StringBuilder sb7 = new StringBuilder();
                        char[] cArr3 = new char[i2];
                        cArr3[c] = '/';
                        sb7.append(StringsKt.trimEnd(str5, cArr3));
                        sb7.append('/');
                        char[] cArr4 = new char[i2];
                        cArr4[c] = '/';
                        sb7.append(StringsKt.trimStart(strAttr, cArr4));
                        strAttr = sb7.toString();
                    }
                    String str210 = str4;
                    str4 = strAttr;
                    str6 = str210;
                    String str211 = str3;
                    baseUrl = str5;
                    str3 = str211;
                    str7 = str3;
                    str8 = str4;
                    str5 = baseUrl;
                } catch (Exception e5) {
                    e = e5;
                    Log.INSTANCE.e(str3, "Failed to extract href: " + e.getMessage());
                    str6 = str4;
                    str7 = str3;
                    str8 = "";
                }
            } catch (Exception e6) {
                e = e6;
                anonymousClass1 = anonymousClass1;
                i = 2;
                str18 = null;
                Log.INSTANCE.e(str3, "Failed to extract href: " + e.getMessage());
                str6 = str4;
                str7 = str3;
                str8 = "";
                function3 = function2;
                function4 = function1;
                str9 = str2;
                str10 = str;
                if (StringsKt.isBlank(str8)) {
                    Log.INSTANCE.w(str7, "No valid href found");
                    return Unit.INSTANCE;
                }
                Requests app7 = MainActivityKt.getApp();
                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(str10);
                anonymousClass1.L$1 = str9;
                anonymousClass1.L$2 = function4;
                anonymousClass1.L$3 = function3;
                anonymousClass1.L$4 = str7;
                anonymousClass1.L$5 = SpillingKt.nullOutSpilledVariable(str6);
                anonymousClass1.L$6 = SpillingKt.nullOutSpilledVariable(str5);
                anonymousClass1.L$7 = SpillingKt.nullOutSpilledVariable(str8);
                anonymousClass1.label = i;
                AnonymousClass1 anonymousClass9 = anonymousClass1;
                function5 = function3;
                obj = Requests.get$default(app7, str8, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass9, 4094, null);
                anonymousClass2 = anonymousClass9;
                if (obj != coroutine_suspended) {
                    str11 = str6;
                    str12 = str5;
                    obj2 = obj;
                    str13 = str8;
                    str14 = str10;
                    function6 = function4;
                    str15 = str9;
                    Document document5 = ((NiceResponse) obj2).getDocument();
                    elementSelectFirst = document5.selectFirst("i#size");
                    if (elementSelectFirst != null) {
                        strText = elementSelectFirst.text();
                    } else {
                        strText = str18;
                    }
                    if (strText == null) {
                        str16 = "";
                    } else {
                        str16 = strText;
                    }
                    elementSelectFirst2 = document5.selectFirst("div.card-header");
                    if (elementSelectFirst2 != null) {
                        strText2 = elementSelectFirst2.text();
                    } else {
                        strText2 = str18;
                    }
                    if (strText2 == null) {
                        str17 = "";
                    } else {
                        str17 = strText2;
                    }
                    strCleanTitle = cleanTitle(str17);
                    sb = new StringBuilder();
                    Function1<? super ExtractorLink, Unit> function11 = function5;
                    String str212 = str15;
                    if (strCleanTitle.length() > 0) {
                        StringBuilder sb8 = new StringBuilder("[");
                        sb8.append(strCleanTitle);
                        c2 = AbstractJsonLexerKt.END_LIST;
                        sb8.append(AbstractJsonLexerKt.END_LIST);
                        sb.append(sb8.toString());
                    } else {
                        c2 = AbstractJsonLexerKt.END_LIST;
                    }
                    if (str16.length() > 0) {
                        sb.append("[" + str16 + c2);
                    }
                    String string5 = sb.toString();
                    int indexQuality5 = getIndexQuality(str17);
                    Elements elementsSelect5 = document5.select("div.card-body h2 a.btn");
                    Intrinsics.checkNotNullExpressionValue(elementsSelect5, "select(...)");
                    elements = elementsSelect5;
                    String str213 = str17;
                    anonymousClass3 = new AnonymousClass2(function11, str212, string5, str7, this, function6, indexQuality5, null);
                    anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(str14);
                    anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str212);
                    anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(function6);
                    anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function11);
                    anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(str7);
                    anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(str11);
                    anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(str12);
                    anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(str13);
                    anonymousClass2.L$8 = SpillingKt.nullOutSpilledVariable(document5);
                    anonymousClass2.L$9 = SpillingKt.nullOutSpilledVariable(str16);
                    anonymousClass2.L$10 = SpillingKt.nullOutSpilledVariable(str213);
                    anonymousClass2.L$11 = SpillingKt.nullOutSpilledVariable(strCleanTitle);
                    anonymousClass2.L$12 = SpillingKt.nullOutSpilledVariable(string5);
                    anonymousClass2.I$0 = indexQuality5;
                    anonymousClass2.label = 3;
                    if (ParCollectionsKt.amap(elements, anonymousClass3, anonymousClass2) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                }
                return coroutine_suspended;
            }
            function3 = function2;
            function4 = function1;
            str9 = str2;
            str10 = str;
            if (StringsKt.isBlank(str8)) {
                Log.INSTANCE.w(str7, "No valid href found");
                return Unit.INSTANCE;
            }
            Requests app8 = MainActivityKt.getApp();
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(str10);
            anonymousClass1.L$1 = str9;
            anonymousClass1.L$2 = function4;
            anonymousClass1.L$3 = function3;
            anonymousClass1.L$4 = str7;
            anonymousClass1.L$5 = SpillingKt.nullOutSpilledVariable(str6);
            anonymousClass1.L$6 = SpillingKt.nullOutSpilledVariable(str5);
            anonymousClass1.L$7 = SpillingKt.nullOutSpilledVariable(str8);
            anonymousClass1.label = i;
            AnonymousClass1 anonymousClass10 = anonymousClass1;
            function5 = function3;
            obj = Requests.get$default(app8, str8, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass10, 4094, null);
            anonymousClass2 = anonymousClass10;
            if (obj != coroutine_suspended) {
                str11 = str6;
                str12 = str5;
                obj2 = obj;
                str13 = str8;
                str14 = str10;
                function6 = function4;
                str15 = str9;
                Document document6 = ((NiceResponse) obj2).getDocument();
                elementSelectFirst = document6.selectFirst("i#size");
                if (elementSelectFirst != null) {
                    strText = elementSelectFirst.text();
                } else {
                    strText = str18;
                }
                if (strText == null) {
                    str16 = "";
                } else {
                    str16 = strText;
                }
                elementSelectFirst2 = document6.selectFirst("div.card-header");
                if (elementSelectFirst2 != null) {
                    strText2 = elementSelectFirst2.text();
                } else {
                    strText2 = str18;
                }
                if (strText2 == null) {
                    str17 = "";
                } else {
                    str17 = strText2;
                }
                strCleanTitle = cleanTitle(str17);
                sb = new StringBuilder();
                Function1<? super ExtractorLink, Unit> function12 = function5;
                String str214 = str15;
                if (strCleanTitle.length() > 0) {
                    StringBuilder sb9 = new StringBuilder("[");
                    sb9.append(strCleanTitle);
                    c2 = AbstractJsonLexerKt.END_LIST;
                    sb9.append(AbstractJsonLexerKt.END_LIST);
                    sb.append(sb9.toString());
                } else {
                    c2 = AbstractJsonLexerKt.END_LIST;
                }
                if (str16.length() > 0) {
                    sb.append("[" + str16 + c2);
                }
                String string6 = sb.toString();
                int indexQuality6 = getIndexQuality(str17);
                Elements elementsSelect6 = document6.select("div.card-body h2 a.btn");
                Intrinsics.checkNotNullExpressionValue(elementsSelect6, "select(...)");
                elements = elementsSelect6;
                String str215 = str17;
                anonymousClass3 = new AnonymousClass2(function12, str214, string6, str7, this, function6, indexQuality6, null);
                anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(str14);
                anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str214);
                anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(function6);
                anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function12);
                anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(str7);
                anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(str11);
                anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(str12);
                anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(str13);
                anonymousClass2.L$8 = SpillingKt.nullOutSpilledVariable(document6);
                anonymousClass2.L$9 = SpillingKt.nullOutSpilledVariable(str16);
                anonymousClass2.L$10 = SpillingKt.nullOutSpilledVariable(str215);
                anonymousClass2.L$11 = SpillingKt.nullOutSpilledVariable(strCleanTitle);
                anonymousClass2.L$12 = SpillingKt.nullOutSpilledVariable(string6);
                anonymousClass2.I$0 = indexQuality6;
                anonymousClass2.label = 3;
                if (ParCollectionsKt.amap(elements, anonymousClass3, anonymousClass2) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return coroutine_suspended;
        }
        if (i3 == 2) {
            String str30 = (String) anonymousClass1.L$7;
            String str31 = (String) anonymousClass1.L$6;
            String str32 = (String) anonymousClass1.L$5;
            String str33 = (String) anonymousClass1.L$4;
            Function1<? super ExtractorLink, Unit> function13 = (Function1) anonymousClass1.L$3;
            Function1<? super SubtitleFile, Unit> function14 = (Function1) anonymousClass1.L$2;
            String str34 = (String) anonymousClass1.L$1;
            str14 = (String) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj2);
            str12 = str31;
            function6 = function14;
            str18 = null;
            str7 = str33;
            anonymousClass2 = anonymousClass1;
            function5 = function13;
            str13 = str30;
            str15 = str34;
            str11 = str32;
            Document document7 = ((NiceResponse) obj2).getDocument();
            elementSelectFirst = document7.selectFirst("i#size");
            if (elementSelectFirst != null) {
                strText = elementSelectFirst.text();
            } else {
                strText = str18;
            }
            if (strText == null) {
                str16 = "";
            } else {
                str16 = strText;
            }
            elementSelectFirst2 = document7.selectFirst("div.card-header");
            if (elementSelectFirst2 != null) {
                strText2 = elementSelectFirst2.text();
            } else {
                strText2 = str18;
            }
            if (strText2 == null) {
                str17 = "";
            } else {
                str17 = strText2;
            }
            strCleanTitle = cleanTitle(str17);
            sb = new StringBuilder();
            Function1<? super ExtractorLink, Unit> function15 = function5;
            String str216 = str15;
            if (strCleanTitle.length() > 0) {
                StringBuilder sb10 = new StringBuilder("[");
                sb10.append(strCleanTitle);
                c2 = AbstractJsonLexerKt.END_LIST;
                sb10.append(AbstractJsonLexerKt.END_LIST);
                sb.append(sb10.toString());
            } else {
                c2 = AbstractJsonLexerKt.END_LIST;
            }
            if (str16.length() > 0) {
                sb.append("[" + str16 + c2);
            }
            String string7 = sb.toString();
            int indexQuality7 = getIndexQuality(str17);
            Elements elementsSelect7 = document7.select("div.card-body h2 a.btn");
            Intrinsics.checkNotNullExpressionValue(elementsSelect7, "select(...)");
            elements = elementsSelect7;
            String str217 = str17;
            anonymousClass3 = new AnonymousClass2(function15, str216, string7, str7, this, function6, indexQuality7, null);
            anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(str14);
            anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str216);
            anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(function6);
            anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function15);
            anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(str7);
            anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(str11);
            anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(str12);
            anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(str13);
            anonymousClass2.L$8 = SpillingKt.nullOutSpilledVariable(document7);
            anonymousClass2.L$9 = SpillingKt.nullOutSpilledVariable(str16);
            anonymousClass2.L$10 = SpillingKt.nullOutSpilledVariable(str217);
            anonymousClass2.L$11 = SpillingKt.nullOutSpilledVariable(strCleanTitle);
            anonymousClass2.L$12 = SpillingKt.nullOutSpilledVariable(string7);
            anonymousClass2.I$0 = indexQuality7;
            anonymousClass2.label = 3;
            if (ParCollectionsKt.amap(elements, anonymousClass3, anonymousClass2) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i3 != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i4 = anonymousClass1.I$0;
            ResultKt.throwOnFailure(obj2);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.HubCloud$getUrl$2, reason: invalid class name */
    /* JADX INFO: compiled from: HubCloud.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u000b\u0010\u0002\u001a\u00070\u0003¢\u0006\u0002\b\u0004H\n"}, d2 = {"<anonymous>", "", "element", "Lorg/jsoup/nodes/Element;", "Lkotlin/jvm/internal/EnhancedNullability;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.HubCloud$getUrl$2", f = "HubCloud.kt", i = {0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 8, 8, 8}, l = {72, 82, 91, 95, 112, 122, 132, 142, 151}, m = "invokeSuspend", n = {"element", "link", "text", "element", "link", "text", "element", "link", "text", "element", "link", "text", "buzzResp", "dlink", "element", "link", "text", "baseUrlLink", "finalURL", "element", "link", "text", "element", "link", "text", "element", "link", "text", "element", "link", "text"}, nl = {71, 81, 92, 94, 111, 121, 131, 141, 153}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2"}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<Element, Continuation<? super Object>, Object> {
        final /* synthetic */ Function1<ExtractorLink, Unit> $callback;
        final /* synthetic */ String $labelExtras;
        final /* synthetic */ int $quality;
        final /* synthetic */ String $referer;
        final /* synthetic */ Function1<SubtitleFile, Unit> $subtitleCallback;
        final /* synthetic */ String $tag;
        /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        final /* synthetic */ HubCloud this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(Function1<? super ExtractorLink, Unit> function1, String str, String str2, String str3, HubCloud hubCloud, Function1<? super SubtitleFile, Unit> function2, int i, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$callback = function1;
            this.$referer = str;
            this.$labelExtras = str2;
            this.$tag = str3;
            this.this$0 = hubCloud;
            this.$subtitleCallback = function2;
            this.$quality = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$callback, this.$referer, this.$labelExtras, this.$tag, this.this$0, this.$subtitleCallback, this.$quality, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Element element, Continuation<? super Object> continuation) {
            return invoke2(element, (Continuation<Object>) continuation);
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(Element element, Continuation<Object> continuation) {
            return ((AnonymousClass2) create(element, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:39:0x0215  */
        /* JADX WARN: Code duplicated, block: B:40:0x0217  */
        /* JADX WARN: Code duplicated, block: B:43:0x0221  */
        /* JADX WARN: Code duplicated, block: B:46:0x028b  */
        /* JADX WARN: Code duplicated, block: B:48:0x0290  */
        /* JADX WARN: Instruction removed from duplicated block: B:43:0x0221, please report this as an issue */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Exception {
            String strAttr;
            String strText;
            String str;
            Object objNewExtractorLink$default;
            Function1 function1;
            Object objNewExtractorLink$default2;
            Function1 function2;
            Object objNewExtractorLink$default3;
            Function1 function3;
            Object objNewExtractorLink$default4;
            Function1 function4;
            Object obj2;
            String str2;
            Object objNewExtractorLink$default5;
            Function1 function5;
            Object objNewExtractorLink$default6;
            Function1 function6;
            NiceResponse niceResponse;
            String str3;
            String str4;
            Function1<ExtractorLink, Unit> function7;
            Object objNewExtractorLink$default7;
            Function1 function8;
            Element element = (Element) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    strAttr = element.attr("href");
                    Intrinsics.checkNotNullExpressionValue(strAttr, "attr(...)");
                    strText = element.text();
                    Intrinsics.checkNotNullExpressionValue(strText, "text(...)");
                    String str5 = strText;
                    if (StringsKt.contains((CharSequence) str5, (CharSequence) "FSL Server", true)) {
                        Function1<ExtractorLink, Unit> function9 = this.$callback;
                        String str6 = this.$referer + " [FSL Server]";
                        this.L$0 = SpillingKt.nullOutSpilledVariable(element);
                        this.L$1 = SpillingKt.nullOutSpilledVariable(strAttr);
                        this.L$2 = SpillingKt.nullOutSpilledVariable(strText);
                        this.L$3 = function9;
                        this.label = 1;
                        objNewExtractorLink$default6 = ExtractorApiKt.newExtractorLink$default(str6, this.$referer + " [FSL Server] " + this.$labelExtras, strAttr, null, new AnonymousClass1(this.$quality, null), this, 8, null);
                        if (objNewExtractorLink$default6 != coroutine_suspended) {
                            function6 = function9;
                            function6.invoke(objNewExtractorLink$default6);
                            return Unit.INSTANCE;
                        }
                    } else if (StringsKt.contains((CharSequence) str5, (CharSequence) "Download File", true)) {
                        Function1<ExtractorLink, Unit> function10 = this.$callback;
                        String strValueOf = String.valueOf(this.$referer);
                        this.L$0 = SpillingKt.nullOutSpilledVariable(element);
                        this.L$1 = SpillingKt.nullOutSpilledVariable(strAttr);
                        this.L$2 = SpillingKt.nullOutSpilledVariable(strText);
                        this.L$3 = function10;
                        this.label = 2;
                        objNewExtractorLink$default5 = ExtractorApiKt.newExtractorLink$default(strValueOf, this.$referer + ' ' + this.$labelExtras, strAttr, null, new C01622(this.$quality, null), this, 8, null);
                        if (objNewExtractorLink$default5 != coroutine_suspended) {
                            function5 = function10;
                            function5.invoke(objNewExtractorLink$default5);
                            return Unit.INSTANCE;
                        }
                    } else if (StringsKt.contains((CharSequence) str5, (CharSequence) "BuzzServer", true)) {
                        Requests app2 = MainActivityKt.getApp();
                        this.L$0 = SpillingKt.nullOutSpilledVariable(element);
                        this.L$1 = SpillingKt.nullOutSpilledVariable(strAttr);
                        this.L$2 = SpillingKt.nullOutSpilledVariable(strText);
                        this.label = 3;
                        obj2 = Requests.get$default(app2, strAttr + "/download", null, strAttr, null, null, false, 0, null, 0L, null, false, null, this, 4058, null);
                        if (obj2 != coroutine_suspended) {
                            str2 = strAttr;
                            niceResponse = (NiceResponse) obj2;
                            str3 = niceResponse.getHeaders().get("hx-redirect");
                            if (str3 == null) {
                                str4 = "";
                            } else {
                                str4 = str3;
                            }
                            if (!StringsKt.isBlank(str4)) {
                                function7 = this.$callback;
                                String str7 = this.$referer + " [BuzzServer]";
                                this.L$0 = SpillingKt.nullOutSpilledVariable(element);
                                this.L$1 = SpillingKt.nullOutSpilledVariable(str2);
                                this.L$2 = SpillingKt.nullOutSpilledVariable(strText);
                                this.L$3 = SpillingKt.nullOutSpilledVariable(niceResponse);
                                this.L$4 = SpillingKt.nullOutSpilledVariable(str4);
                                this.L$5 = function7;
                                this.label = 4;
                                objNewExtractorLink$default7 = ExtractorApiKt.newExtractorLink$default(str7, this.$referer + " [BuzzServer] " + this.$labelExtras, str4, null, new AnonymousClass3(this.$quality, null), this, 8, null);
                                if (objNewExtractorLink$default7 != coroutine_suspended) {
                                    function8 = function7;
                                    function8.invoke(objNewExtractorLink$default7);
                                }
                            } else {
                                Log.INSTANCE.w(this.$tag, "BuzzServer: No redirect");
                            }
                            return Unit.INSTANCE;
                        }
                    } else if (StringsKt.contains((CharSequence) str5, (CharSequence) "pixeldra", true) || StringsKt.contains((CharSequence) str5, (CharSequence) "pixel", true)) {
                        String baseUrl = this.this$0.getBaseUrl(strAttr);
                        if (StringsKt.contains((CharSequence) strAttr, (CharSequence) "download", true)) {
                            str = strAttr;
                        } else {
                            str = baseUrl + "/api/file/" + StringsKt.substringAfterLast$default(strAttr, "/", (String) null, 2, (Object) null) + "?download";
                        }
                        Function1<ExtractorLink, Unit> function11 = this.$callback;
                        this.L$0 = SpillingKt.nullOutSpilledVariable(element);
                        this.L$1 = SpillingKt.nullOutSpilledVariable(strAttr);
                        this.L$2 = SpillingKt.nullOutSpilledVariable(strText);
                        this.L$3 = SpillingKt.nullOutSpilledVariable(baseUrl);
                        this.L$4 = SpillingKt.nullOutSpilledVariable(str);
                        this.L$5 = function11;
                        this.label = 5;
                        objNewExtractorLink$default = ExtractorApiKt.newExtractorLink$default("Pixeldrain", "Pixeldrain " + this.$labelExtras, str, null, new AnonymousClass4(this.$quality, null), this, 8, null);
                        if (objNewExtractorLink$default != coroutine_suspended) {
                            function1 = function11;
                            function1.invoke(objNewExtractorLink$default);
                            return Unit.INSTANCE;
                        }
                    } else if (StringsKt.contains((CharSequence) str5, (CharSequence) "S3 Server", true)) {
                        Function1<ExtractorLink, Unit> function12 = this.$callback;
                        String str8 = this.$referer + " S3 Server";
                        this.L$0 = SpillingKt.nullOutSpilledVariable(element);
                        this.L$1 = SpillingKt.nullOutSpilledVariable(strAttr);
                        this.L$2 = SpillingKt.nullOutSpilledVariable(strText);
                        this.L$3 = function12;
                        this.label = 6;
                        objNewExtractorLink$default4 = ExtractorApiKt.newExtractorLink$default(str8, this.$referer + " S3 Server " + this.$labelExtras, strAttr, null, new AnonymousClass5(this.$quality, null), this, 8, null);
                        if (objNewExtractorLink$default4 != coroutine_suspended) {
                            function4 = function12;
                            function4.invoke(objNewExtractorLink$default4);
                            return Unit.INSTANCE;
                        }
                    } else if (StringsKt.contains((CharSequence) str5, (CharSequence) "FSLv2", true)) {
                        Function1<ExtractorLink, Unit> function13 = this.$callback;
                        String str9 = this.$referer + " FSLv2";
                        this.L$0 = SpillingKt.nullOutSpilledVariable(element);
                        this.L$1 = SpillingKt.nullOutSpilledVariable(strAttr);
                        this.L$2 = SpillingKt.nullOutSpilledVariable(strText);
                        this.L$3 = function13;
                        this.label = 7;
                        objNewExtractorLink$default3 = ExtractorApiKt.newExtractorLink$default(str9, this.$referer + " FSLv2 " + this.$labelExtras, strAttr, null, new AnonymousClass6(this.$quality, null), this, 8, null);
                        if (objNewExtractorLink$default3 != coroutine_suspended) {
                            function3 = function13;
                            function3.invoke(objNewExtractorLink$default3);
                            return Unit.INSTANCE;
                        }
                    } else if (StringsKt.contains((CharSequence) str5, (CharSequence) "Mega Server", true)) {
                        Function1<ExtractorLink, Unit> function14 = this.$callback;
                        String str10 = this.$referer + " [Mega Server]";
                        this.L$0 = SpillingKt.nullOutSpilledVariable(element);
                        this.L$1 = SpillingKt.nullOutSpilledVariable(strAttr);
                        this.L$2 = SpillingKt.nullOutSpilledVariable(strText);
                        this.L$3 = function14;
                        this.label = 8;
                        objNewExtractorLink$default2 = ExtractorApiKt.newExtractorLink$default(str10, this.$referer + " [Mega Server] " + this.$labelExtras, strAttr, null, new AnonymousClass7(this.$quality, null), this, 8, null);
                        if (objNewExtractorLink$default2 != coroutine_suspended) {
                            function2 = function14;
                            function2.invoke(objNewExtractorLink$default2);
                            return Unit.INSTANCE;
                        }
                    } else {
                        this.L$0 = SpillingKt.nullOutSpilledVariable(element);
                        this.L$1 = SpillingKt.nullOutSpilledVariable(strAttr);
                        this.L$2 = SpillingKt.nullOutSpilledVariable(strText);
                        this.label = 9;
                        Object objLoadExtractor = ExtractorApiKt.loadExtractor(strAttr, "", this.$subtitleCallback, this.$callback, this);
                        if (objLoadExtractor != coroutine_suspended) {
                            return objLoadExtractor;
                        }
                    }
                    str2 = strAttr;
                    return coroutine_suspended;
                case 1:
                    function6 = (Function1) this.L$3;
                    ResultKt.throwOnFailure(obj);
                    objNewExtractorLink$default6 = obj;
                    function6.invoke(objNewExtractorLink$default6);
                    return Unit.INSTANCE;
                case 2:
                    function5 = (Function1) this.L$3;
                    ResultKt.throwOnFailure(obj);
                    objNewExtractorLink$default5 = obj;
                    function5.invoke(objNewExtractorLink$default5);
                    return Unit.INSTANCE;
                case 3:
                    strText = (String) this.L$2;
                    String str11 = (String) this.L$1;
                    ResultKt.throwOnFailure(obj);
                    str2 = str11;
                    obj2 = obj;
                    str2 = strAttr;
                    niceResponse = (NiceResponse) obj2;
                    str3 = niceResponse.getHeaders().get("hx-redirect");
                    if (str3 == null) {
                        str4 = "";
                    } else {
                        str4 = str3;
                    }
                    if (!StringsKt.isBlank(str4)) {
                        function7 = this.$callback;
                        String str12 = this.$referer + " [BuzzServer]";
                        this.L$0 = SpillingKt.nullOutSpilledVariable(element);
                        this.L$1 = SpillingKt.nullOutSpilledVariable(str2);
                        this.L$2 = SpillingKt.nullOutSpilledVariable(strText);
                        this.L$3 = SpillingKt.nullOutSpilledVariable(niceResponse);
                        this.L$4 = SpillingKt.nullOutSpilledVariable(str4);
                        this.L$5 = function7;
                        this.label = 4;
                        objNewExtractorLink$default7 = ExtractorApiKt.newExtractorLink$default(str12, this.$referer + " [BuzzServer] " + this.$labelExtras, str4, null, new AnonymousClass3(this.$quality, null), this, 8, null);
                        if (objNewExtractorLink$default7 != coroutine_suspended) {
                            function8 = function7;
                            function8.invoke(objNewExtractorLink$default7);
                        }
                        str2 = strAttr;
                        return coroutine_suspended;
                    }
                    Log.INSTANCE.w(this.$tag, "BuzzServer: No redirect");
                    return Unit.INSTANCE;
                case 4:
                    function8 = (Function1) this.L$5;
                    ResultKt.throwOnFailure(obj);
                    objNewExtractorLink$default7 = obj;
                    function8.invoke(objNewExtractorLink$default7);
                    return Unit.INSTANCE;
                case 5:
                    function1 = (Function1) this.L$5;
                    ResultKt.throwOnFailure(obj);
                    objNewExtractorLink$default = obj;
                    function1.invoke(objNewExtractorLink$default);
                    return Unit.INSTANCE;
                case 6:
                    function4 = (Function1) this.L$3;
                    ResultKt.throwOnFailure(obj);
                    objNewExtractorLink$default4 = obj;
                    function4.invoke(objNewExtractorLink$default4);
                    return Unit.INSTANCE;
                case 7:
                    function3 = (Function1) this.L$3;
                    ResultKt.throwOnFailure(obj);
                    objNewExtractorLink$default3 = obj;
                    function3.invoke(objNewExtractorLink$default3);
                    return Unit.INSTANCE;
                case 8:
                    function2 = (Function1) this.L$3;
                    ResultKt.throwOnFailure(obj);
                    objNewExtractorLink$default2 = obj;
                    function2.invoke(objNewExtractorLink$default2);
                    return Unit.INSTANCE;
                case 9:
                    ResultKt.throwOnFailure(obj);
                    return obj;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.HubCloud$getUrl$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: HubCloud.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.HubCloud$getUrl$2$1", f = "HubCloud.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
        static final class AnonymousClass1 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
            final /* synthetic */ int $quality;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(int i, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$quality = i;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$quality, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(extractorLink, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                ExtractorLink extractorLink = (ExtractorLink) this.L$0;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                extractorLink.setQuality(this.$quality);
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.HubCloud$getUrl$2$2, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: HubCloud.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.HubCloud$getUrl$2$2", f = "HubCloud.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
        static final class C01622 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
            final /* synthetic */ int $quality;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01622(int i, Continuation<? super C01622> continuation) {
                super(2, continuation);
                this.$quality = i;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C01622 c01622 = new C01622(this.$quality, continuation);
                c01622.L$0 = obj;
                return c01622;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
                return ((C01622) create(extractorLink, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                ExtractorLink extractorLink = (ExtractorLink) this.L$0;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                extractorLink.setQuality(this.$quality);
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.HubCloud$getUrl$2$3, reason: invalid class name */
        /* JADX INFO: compiled from: HubCloud.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.HubCloud$getUrl$2$3", f = "HubCloud.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
        static final class AnonymousClass3 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
            final /* synthetic */ int $quality;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass3(int i, Continuation<? super AnonymousClass3> continuation) {
                super(2, continuation);
                this.$quality = i;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$quality, continuation);
                anonymousClass3.L$0 = obj;
                return anonymousClass3;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
                return ((AnonymousClass3) create(extractorLink, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                ExtractorLink extractorLink = (ExtractorLink) this.L$0;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                extractorLink.setQuality(this.$quality);
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.HubCloud$getUrl$2$4, reason: invalid class name */
        /* JADX INFO: compiled from: HubCloud.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.HubCloud$getUrl$2$4", f = "HubCloud.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
        static final class AnonymousClass4 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
            final /* synthetic */ int $quality;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass4(int i, Continuation<? super AnonymousClass4> continuation) {
                super(2, continuation);
                this.$quality = i;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass4 anonymousClass4 = new AnonymousClass4(this.$quality, continuation);
                anonymousClass4.L$0 = obj;
                return anonymousClass4;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
                return ((AnonymousClass4) create(extractorLink, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                ExtractorLink extractorLink = (ExtractorLink) this.L$0;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                extractorLink.setQuality(this.$quality);
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.HubCloud$getUrl$2$5, reason: invalid class name */
        /* JADX INFO: compiled from: HubCloud.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.HubCloud$getUrl$2$5", f = "HubCloud.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
        static final class AnonymousClass5 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
            final /* synthetic */ int $quality;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass5(int i, Continuation<? super AnonymousClass5> continuation) {
                super(2, continuation);
                this.$quality = i;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass5 anonymousClass5 = new AnonymousClass5(this.$quality, continuation);
                anonymousClass5.L$0 = obj;
                return anonymousClass5;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
                return ((AnonymousClass5) create(extractorLink, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                ExtractorLink extractorLink = (ExtractorLink) this.L$0;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                extractorLink.setQuality(this.$quality);
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.HubCloud$getUrl$2$6, reason: invalid class name */
        /* JADX INFO: compiled from: HubCloud.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.HubCloud$getUrl$2$6", f = "HubCloud.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
        static final class AnonymousClass6 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
            final /* synthetic */ int $quality;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass6(int i, Continuation<? super AnonymousClass6> continuation) {
                super(2, continuation);
                this.$quality = i;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass6 anonymousClass6 = new AnonymousClass6(this.$quality, continuation);
                anonymousClass6.L$0 = obj;
                return anonymousClass6;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
                return ((AnonymousClass6) create(extractorLink, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                ExtractorLink extractorLink = (ExtractorLink) this.L$0;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                extractorLink.setQuality(this.$quality);
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.HubCloud$getUrl$2$7, reason: invalid class name */
        /* JADX INFO: compiled from: HubCloud.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.HubCloud$getUrl$2$7", f = "HubCloud.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
        static final class AnonymousClass7 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
            final /* synthetic */ int $quality;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass7(int i, Continuation<? super AnonymousClass7> continuation) {
                super(2, continuation);
                this.$quality = i;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass7 anonymousClass7 = new AnonymousClass7(this.$quality, continuation);
                anonymousClass7.L$0 = obj;
                return anonymousClass7;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
                return ((AnonymousClass7) create(extractorLink, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                ExtractorLink extractorLink = (ExtractorLink) this.L$0;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                extractorLink.setQuality(this.$quality);
                return Unit.INSTANCE;
            }
        }
    }

    private final int getIndexQuality(String str) {
        List<String> groupValues;
        String str2;
        Integer intOrNull;
        Regex regex = new Regex("(\\d{3,4})[pP]");
        if (str == null) {
            str = "";
        }
        MatchResult matchResultFind$default = Regex.find$default(regex, str, 0, 2, null);
        return (matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null || (str2 = (String) CollectionsKt.getOrNull(groupValues, 1)) == null || (intOrNull = StringsKt.toIntOrNull(str2)) == null) ? Qualities.P2160.getValue() : intOrNull.intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getBaseUrl(String url) {
        try {
            Url Url = URLUtilsKt.Url(url);
            return Url.getProtocol().getName() + "://" + Url.getHost();
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX WARN: Code duplicated, block: B:37:0x011a  */
    /* JADX WARN: Code duplicated, block: B:40:0x0124  */
    /* JADX WARN: Code duplicated, block: B:45:0x013d  */
    /* JADX WARN: Code duplicated, block: B:51:0x0151  */
    /* JADX WARN: Code duplicated, block: B:70:0x0162 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:71:0x0147 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:75:0x0162 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:78:0x00d0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:79:? A[LOOP:3: B:49:0x014b->B:79:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:80:0x0136 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:81:? A[LOOP:4: B:38:0x011e->B:81:?, LOOP_END, SYNTHETIC] */
    public final String cleanTitle(String title) {
        int iNextIndex;
        List list;
        Iterator it;
        List list2;
        Iterator it2;
        Intrinsics.checkNotNullParameter(title, "title");
        List listSplit$default = StringsKt.split$default((CharSequence) title, new String[]{".", "-", "_"}, false, 0, 6, (Object) null);
        List listListOf = CollectionsKt.listOf((Object[]) new String[]{"WEBRip", "WEB-DL", "WEB", "BluRay", "HDRip", "DVDRip", "HDTV", "CAM", "TS", "R5", "DVDScr", "BRRip", "BDRip", "DVD", "PDTV", "HD"});
        List listListOf2 = CollectionsKt.listOf((Object[]) new String[]{"AAC", "AC3", "DTS", "MP3", "FLAC", "DD5", "EAC3", "Atmos"});
        List listListOf3 = CollectionsKt.listOf((Object[]) new String[]{"ESub", "ESubs", "Subs", "MultiSub", "NoSub", "EnglishSub", "HindiSub"});
        List listListOf4 = CollectionsKt.listOf((Object[]) new String[]{"x264", "x265", "H264", "HEVC", "AVC"});
        Iterator it3 = listSplit$default.iterator();
        int i = 0;
        loop0: while (true) {
            if (!it3.hasNext()) {
                i = -1;
                break;
            }
            String str = (String) it3.next();
            List list3 = listListOf;
            if (!(list3 instanceof Collection) || !list3.isEmpty()) {
                Iterator it4 = list3.iterator();
                while (it4.hasNext()) {
                    if (StringsKt.contains((CharSequence) str, (CharSequence) it4.next(), true)) {
                        break loop0;
                    }
                }
            }
            i++;
        }
        ListIterator listIterator = listSplit$default.listIterator(listSplit$default.size());
        loop2: while (true) {
            if (!listIterator.hasPrevious()) {
                iNextIndex = -1;
                break;
            }
            String str2 = (String) listIterator.previous();
            List list4 = listListOf3;
            if ((list4 instanceof Collection) && list4.isEmpty()) {
                list = listListOf2;
                if (!(list instanceof Collection)) {
                    it = list.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (StringsKt.contains((CharSequence) str2, (CharSequence) it.next(), true)) {
                            }
                        } else {
                            list2 = listListOf4;
                            if (list2 instanceof Collection) {
                            }
                            it2 = list2.iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    continue;
                                } else if (StringsKt.contains((CharSequence) str2, (CharSequence) it2.next(), true)) {
                                }
                            }
                        }
                    }
                } else {
                    it = list.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (StringsKt.contains((CharSequence) str2, (CharSequence) it.next(), true)) {
                            }
                        } else {
                            list2 = listListOf4;
                            if (list2 instanceof Collection) {
                            }
                            it2 = list2.iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    continue;
                                } else if (StringsKt.contains((CharSequence) str2, (CharSequence) it2.next(), true)) {
                                }
                            }
                        }
                    }
                }
            } else {
                Iterator it5 = list4.iterator();
                while (true) {
                    if (it5.hasNext()) {
                        if (StringsKt.contains((CharSequence) str2, (CharSequence) it5.next(), true)) {
                        }
                    } else {
                        list = listListOf2;
                        if (!(list instanceof Collection) && list.isEmpty()) {
                            list2 = listListOf4;
                            if (list2 instanceof Collection) {
                            }
                            it2 = list2.iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    continue;
                                } else if (StringsKt.contains((CharSequence) str2, (CharSequence) it2.next(), true)) {
                                }
                            }
                        } else {
                            it = list.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    if (StringsKt.contains((CharSequence) str2, (CharSequence) it.next(), true)) {
                                    }
                                } else {
                                    list2 = listListOf4;
                                    if ((list2 instanceof Collection) || !list2.isEmpty()) {
                                        it2 = list2.iterator();
                                        while (true) {
                                            if (it2.hasNext()) {
                                                continue;
                                            } else if (StringsKt.contains((CharSequence) str2, (CharSequence) it2.next(), true)) {
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            iNextIndex = listIterator.nextIndex();
            break loop2;
        }
        if (i != -1 && iNextIndex != -1 && iNextIndex >= i) {
            return CollectionsKt.joinToString$default(listSplit$default.subList(i, iNextIndex + 1), ".", null, null, 0, null, null, 62, null);
        }
        if (i != -1) {
            return CollectionsKt.joinToString$default(listSplit$default.subList(i, listSplit$default.size()), ".", null, null, 0, null, null, 62, null);
        }
        return CollectionsKt.joinToString$default(CollectionsKt.takeLast(listSplit$default, 3), ".", null, null, 0, null, null, 62, null);
    }
}
