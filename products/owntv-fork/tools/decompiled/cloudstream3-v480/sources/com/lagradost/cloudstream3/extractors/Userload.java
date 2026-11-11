package com.lagradost.cloudstream3.extractors;

import androidx.tvprovider.media.tv.TvContractCompat;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorApiKt;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.JsInterpreterKt;
import com.lagradost.cloudstream3.utils.JsUnpacker;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
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
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchGroup;
import kotlin.text.MatchGroupCollection;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jsoup.nodes.Element;
import org.slf4j.Marker;

/* JADX INFO: compiled from: Userload.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u0013\u001a\u00020\u0005H\u0002J\u0016\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0005H\u0082@¢\u0006\u0002\u0010\u0016J\u001c\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u0018\u001a\u00020\u0005H\u0082@¢\u0006\u0002\u0010\u0016J(\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00122\u0006\u0010\u001b\u001a\u00020\u00052\b\u0010\u001c\u001a\u0004\u0018\u00010\u0005H\u0096@¢\u0006\u0002\u0010\u001dR\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u0014\u0010\r\u001a\u00020\u000eX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001e"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Userload;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "mainUrl", "getMainUrl", "setMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "splitInput", "", TvContractCompat.PARAM_INPUT, "evaluateMath", "mathExpression", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "decodeVideoJs", "text", "getUrl", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", ImagesContract.URL, "referer", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class Userload extends ExtractorApi {
    private final boolean requiresReferer;
    private String name = "Userload";
    private String mainUrl = "https://userload.co";

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Userload$decodeVideoJs$1, reason: invalid class name */
    /* JADX INFO: compiled from: Userload.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Userload", f = "Userload.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {56}, m = "decodeVideoJs", n = {"text", "data", "chars", "newchars", "subchar", "$this$forEach$iv", "element$iv", "v", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "it", "$i$f$forEach", "$i$a$-forEach-Userload$decodeVideoJs$2", "$i$f$map", "$i$f$mapTo", "$i$a$-map-Userload$decodeVideoJs$2$1"}, nl = {107}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$7", "L$8", "L$9", "L$11", "L$12", "L$14", "L$15", "I$0", "I$1", "I$2", "I$3", "I$4"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
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
            return Userload.this.decodeVideoJs(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Userload$evaluateMath$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Userload.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Userload", f = "Userload.kt", i = {0}, l = {33}, m = "evaluateMath", n = {"mathExpression"}, nl = {-1}, s = {"L$0"}, v = 2)
    static final class C07201 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C07201(Continuation<? super C07201> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Userload.this.evaluateMath(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Userload$getUrl$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Userload.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Userload", f = "Userload.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, l = {71, 74, 76, 79, 84, 86}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "extractedLinksList", "$this", ImagesContract.URL, "referer", "extractedLinksList", "response", "jsToUnpack", "unpacked", "$this", ImagesContract.URL, "referer", "extractedLinksList", "response", "jsToUnpack", "unpacked", "videoJs", "videoJsToDecode", "$this", ImagesContract.URL, "referer", "extractedLinksList", "response", "jsToUnpack", "unpacked", "videoJs", "videoJsToDecode", "values", "morocco", "mycountry", "$this", ImagesContract.URL, "referer", "extractedLinksList", "response", "jsToUnpack", "unpacked", "videoJs", "videoJsToDecode", "values", "morocco", "mycountry", "videoLinkPage", "videoLink", "$this", ImagesContract.URL, "referer", "extractedLinksList", "response", "jsToUnpack", "unpacked", "videoJs", "videoJsToDecode", "values", "morocco", "mycountry", "videoLinkPage", "videoLink", "nameSource"}, nl = {72, 75, 77, 83, 85, 85}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14"}, v = 2)
    static final class C07211 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$15;
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

        C07211(Continuation<? super C07211> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Userload.getUrl$suspendImpl(Userload.this, (String) null, (String) null, (Continuation<? super List<? extends ExtractorLink>>) this);
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

    private final List<String> splitInput(String input) {
        ArrayList arrayList = new ArrayList();
        int length = input.length();
        int i = 0;
        String str = "";
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = input.charAt(i2);
            if (cCharAt == '(') {
                i++;
            } else if (cCharAt == ')') {
                i--;
            }
            str = str + cCharAt;
            if (i == 0) {
                if (!StringsKt.isBlank(str) && !Intrinsics.areEqual(str, Marker.ANY_NON_NULL_MARKER)) {
                    arrayList.add(str);
                }
                str = "";
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object evaluateMath(String str, Continuation<? super String> continuation) {
        C07201 c07201;
        if (continuation instanceof C07201) {
            c07201 = (C07201) continuation;
            if ((c07201.label & Integer.MIN_VALUE) != 0) {
                c07201.label -= Integer.MIN_VALUE;
            } else {
                c07201 = new C07201(continuation);
            }
        } else {
            c07201 = new C07201(continuation);
        }
        C07201 c07202 = c07201;
        Object objM8645evalJs1Y68eR8$default = c07202.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07202.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objM8645evalJs1Y68eR8$default);
            c07202.L$0 = SpillingKt.nullOutSpilledVariable(str);
            c07202.label = 1;
            objM8645evalJs1Y68eR8$default = JsInterpreterKt.m8645evalJs1Y68eR8$default("eval(" + str + ')', null, 0L, 0L, c07202, 14, null);
            if (objM8645evalJs1Y68eR8$default == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objM8645evalJs1Y68eR8$default);
        }
        return JsInterpreterKt.jsValueToString(objM8645evalJs1Y68eR8$default);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:21:0x018f  */
    /* JADX WARN: Code duplicated, block: B:24:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:26:0x023d A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:27:0x023e  */
    /* JADX WARN: Code duplicated, block: B:29:0x0285  */
    /* JADX WARN: Code duplicated, block: B:31:0x02a1  */
    /* JADX WARN: Code duplicated, block: B:33:0x02ad  */
    /* JADX WARN: Code duplicated, block: B:53:0x02b0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x018f -> B:22:0x01c5). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x023e -> B:28:0x025a). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object decodeVideoJs(java.lang.String r27, kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> r28) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 840
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.extractors.Userload.decodeVideoJs(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0215  */
    /* JADX WARN: Code duplicated, block: B:35:0x028d  */
    /* JADX WARN: Code duplicated, block: B:39:0x02d0  */
    /* JADX WARN: Code duplicated, block: B:43:0x0306  */
    /* JADX WARN: Code duplicated, block: B:49:0x036b  */
    /* JADX WARN: Code duplicated, block: B:55:0x0460  */
    /* JADX WARN: Code duplicated, block: B:59:0x04ef  */
    /* JADX WARN: Code duplicated, block: B:63:0x05a2  */
    /* JADX WARN: Code duplicated, block: B:67:0x031c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:69:0x0300 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:72:0x0381 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:74:0x0365 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v35 */
    static /* synthetic */ Object getUrl$suspendImpl(Userload userload, String str, String str2, Continuation<? super List<? extends ExtractorLink>> continuation) throws IOException {
        C07211 c07211;
        int i;
        String str3;
        String str4;
        String str5;
        List list;
        Object obj;
        Userload userload2;
        String text;
        MatchResult matchResultFind$default;
        boolean z;
        String value;
        String strUnpack;
        List list2;
        Userload userload3;
        String str6;
        String str7;
        boolean z2;
        Object obj2;
        String str8;
        String str9;
        String str10;
        List list3;
        String str11;
        Userload userload4;
        MatchGroupCollection groups;
        NiceResponse niceResponse;
        String text2;
        Object objDecodeVideoJs;
        NiceResponse niceResponse2;
        String str12;
        String str13;
        List list4;
        String str14;
        String str15;
        ?? r0;
        String str16;
        String str17;
        Userload userload5;
        List list5;
        ArrayList arrayList;
        ArrayList arrayList2;
        String strDropLast;
        List list6;
        String str18;
        String str19;
        Object objPost$default;
        String str20;
        Object obj3;
        Userload userload6;
        String str21;
        List list7;
        NiceResponse niceResponse3;
        String text3;
        List list8;
        Object obj4;
        String str22;
        Object obj5;
        String str23;
        String str24;
        NiceResponse niceResponse4;
        String str25;
        String str26;
        String str27;
        String str28;
        NiceResponse niceResponse5;
        Userload userload7;
        List list9;
        List list10;
        if (continuation instanceof C07211) {
            c07211 = (C07211) continuation;
            if ((c07211.label & Integer.MIN_VALUE) != 0) {
                c07211.label -= Integer.MIN_VALUE;
            } else {
                c07211 = userload.new C07211(continuation);
            }
        } else {
            c07211 = userload.new C07211(continuation);
        }
        C07211 c07212 = c07211;
        Object objNewExtractorLink$default = c07212.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c07212.label) {
            case 0:
                ResultKt.throwOnFailure(objNewExtractorLink$default);
                ArrayList arrayList3 = new ArrayList();
                Requests app2 = MainActivityKt.getApp();
                c07212.L$0 = userload;
                c07212.L$1 = str;
                c07212.L$2 = SpillingKt.nullOutSpilledVariable(str2);
                c07212.L$3 = arrayList3;
                c07212.label = 1;
                i = 2;
                str3 = null;
                Object obj6 = Requests.get$default(app2, str, null, null, null, null, false, 0, null, 0L, null, false, null, c07212, 4094, null);
                c07212 = c07212;
                if (obj6 != coroutine_suspended) {
                    str4 = str;
                    str5 = str2;
                    list = arrayList3;
                    obj = obj6;
                    userload2 = userload;
                    text = ((NiceResponse) obj).getText();
                    matchResultFind$default = Regex.find$default(new Regex("ext/javascript\">eval((.|\\n)*?)</script>"), text, 0, i, str3);
                    if (matchResultFind$default != null || (groups = matchResultFind$default.getGroups()) == null) {
                        z = true;
                    } else {
                        z = true;
                        MatchGroup matchGroup = groups.get(1);
                        if (matchGroup != null) {
                            value = matchGroup.getValue();
                        }
                        strUnpack = new JsUnpacker(value).unpack();
                        Requests app3 = MainActivityKt.getApp();
                        String str29 = userload2.getMainUrl() + "/api/assets/userload/js/videojs.js";
                        c07212.L$0 = userload2;
                        c07212.L$1 = str4;
                        c07212.L$2 = SpillingKt.nullOutSpilledVariable(str5);
                        c07212.L$3 = list;
                        c07212.L$4 = SpillingKt.nullOutSpilledVariable(text);
                        c07212.L$5 = SpillingKt.nullOutSpilledVariable(value);
                        c07212.L$6 = strUnpack;
                        c07212.label = i;
                        list2 = list;
                        C07211 c07213 = c07212;
                        userload3 = userload2;
                        str6 = str4;
                        str7 = value;
                        z2 = false;
                        obj2 = Requests.get$default(app3, str29, null, null, null, null, false, 0, null, 0L, null, false, null, c07213, 4094, null);
                        c07212 = c07213;
                        if (obj2 != coroutine_suspended) {
                            str8 = str5;
                            str9 = text;
                            str10 = str6;
                            list3 = list2;
                            str11 = strUnpack;
                            userload4 = userload3;
                            niceResponse = (NiceResponse) obj2;
                            text2 = niceResponse.getText();
                            c07212.L$0 = userload4;
                            c07212.L$1 = str10;
                            c07212.L$2 = SpillingKt.nullOutSpilledVariable(str8);
                            c07212.L$3 = list3;
                            c07212.L$4 = SpillingKt.nullOutSpilledVariable(str9);
                            c07212.L$5 = SpillingKt.nullOutSpilledVariable(str7);
                            c07212.L$6 = str11;
                            c07212.L$7 = SpillingKt.nullOutSpilledVariable(niceResponse);
                            c07212.L$8 = SpillingKt.nullOutSpilledVariable(text2);
                            c07212.label = 3;
                            objDecodeVideoJs = userload4.decodeVideoJs(text2, c07212);
                            if (objDecodeVideoJs != coroutine_suspended) {
                                niceResponse2 = niceResponse;
                                str12 = str11;
                                str13 = text2;
                                list4 = list3;
                                str14 = str10;
                                str15 = str7;
                                r0 = z2;
                                str16 = str9;
                                str17 = str8;
                                userload5 = userload4;
                                list5 = (List) objDecodeVideoJs;
                                Intrinsics.checkNotNull(str12);
                                String str30 = str12;
                                List listSplit$default = StringsKt.split$default((CharSequence) str30, new String[]{";"}, false, 0, 6, (Object) null);
                                arrayList = new ArrayList();
                                for (Object obj7 : listSplit$default) {
                                    if (StringsKt.contains$default((String) obj7, (CharSequence) list5.get(r0), (boolean) r0, i, (Object) null)) {
                                        arrayList.add(obj7);
                                    }
                                }
                                String strDropLast2 = StringsKt.dropLast(StringsKt.drop((String) StringsKt.split$default((CharSequence) arrayList.get(r0), new String[]{"="}, false, 0, 6, (Object) null).get(1), 1), 1);
                                List listSplit$default2 = StringsKt.split$default((CharSequence) str30, new String[]{";"}, false, 0, 6, (Object) null);
                                arrayList2 = new ArrayList();
                                for (Object obj8 : listSplit$default2) {
                                    if (StringsKt.contains$default((String) obj8, (CharSequence) list5.get(1), (boolean) r0, i, (Object) null)) {
                                        arrayList2.add(obj8);
                                    }
                                }
                                strDropLast = StringsKt.dropLast(StringsKt.drop((String) StringsKt.split$default((CharSequence) arrayList2.get(r0), new String[]{"="}, false, 0, 6, (Object) null).get(1), 1), 1);
                                Requests app4 = MainActivityKt.getApp();
                                String str31 = userload5.getMainUrl() + "/api/request/";
                                Pair[] pairArr = new Pair[i];
                                pairArr[r0] = TuplesKt.to("morocco", strDropLast2);
                                pairArr[1] = TuplesKt.to("mycountry", strDropLast);
                                Map mapMapOf = MapsKt.mapOf(pairArr);
                                c07212.L$0 = userload5;
                                c07212.L$1 = str14;
                                c07212.L$2 = SpillingKt.nullOutSpilledVariable(str17);
                                c07212.L$3 = list4;
                                c07212.L$4 = SpillingKt.nullOutSpilledVariable(str16);
                                c07212.L$5 = SpillingKt.nullOutSpilledVariable(str15);
                                c07212.L$6 = SpillingKt.nullOutSpilledVariable(str12);
                                c07212.L$7 = SpillingKt.nullOutSpilledVariable(niceResponse2);
                                c07212.L$8 = SpillingKt.nullOutSpilledVariable(str13);
                                c07212.L$9 = SpillingKt.nullOutSpilledVariable(list5);
                                c07212.L$10 = SpillingKt.nullOutSpilledVariable(strDropLast2);
                                c07212.L$11 = SpillingKt.nullOutSpilledVariable(strDropLast);
                                c07212.label = 4;
                                C07211 c07214 = c07212;
                                list6 = list4;
                                str18 = str14;
                                str19 = strDropLast2;
                                objPost$default = Requests.post$default(app4, str31, null, null, null, null, mapMapOf, null, null, null, false, 0, null, 0L, null, false, null, c07214, 65502, null);
                                c07212 = c07214;
                                if (objPost$default != coroutine_suspended) {
                                    str20 = strDropLast;
                                    obj3 = list5;
                                    objNewExtractorLink$default = objPost$default;
                                    userload6 = userload5;
                                    str21 = str18;
                                    list7 = list6;
                                    niceResponse3 = (NiceResponse) objNewExtractorLink$default;
                                    text3 = niceResponse3.getText();
                                    Requests app5 = MainActivityKt.getApp();
                                    c07212.L$0 = userload6;
                                    c07212.L$1 = SpillingKt.nullOutSpilledVariable(str21);
                                    c07212.L$2 = SpillingKt.nullOutSpilledVariable(str17);
                                    c07212.L$3 = list7;
                                    c07212.L$4 = SpillingKt.nullOutSpilledVariable(str16);
                                    c07212.L$5 = SpillingKt.nullOutSpilledVariable(str15);
                                    c07212.L$6 = SpillingKt.nullOutSpilledVariable(str12);
                                    c07212.L$7 = SpillingKt.nullOutSpilledVariable(niceResponse2);
                                    c07212.L$8 = SpillingKt.nullOutSpilledVariable(str13);
                                    c07212.L$9 = SpillingKt.nullOutSpilledVariable(obj3);
                                    c07212.L$10 = SpillingKt.nullOutSpilledVariable(str19);
                                    c07212.L$11 = SpillingKt.nullOutSpilledVariable(str20);
                                    c07212.L$12 = SpillingKt.nullOutSpilledVariable(niceResponse3);
                                    c07212.L$13 = text3;
                                    c07212.label = 5;
                                    list8 = list7;
                                    C07211 c07215 = c07212;
                                    obj4 = Requests.get$default(app5, str21, null, null, null, null, false, 0, null, 0L, null, false, null, c07215, 4094, null);
                                    c07212 = c07215;
                                    if (obj4 != coroutine_suspended) {
                                        str22 = str21;
                                        obj5 = obj3;
                                        str23 = text3;
                                        str24 = str13;
                                        niceResponse4 = niceResponse2;
                                        str25 = str12;
                                        str26 = str15;
                                        str27 = str16;
                                        str28 = str17;
                                        niceResponse5 = niceResponse3;
                                        userload7 = userload6;
                                        list9 = list8;
                                        Element elementSelectFirst = ((NiceResponse) obj4).getDocument().head().selectFirst("title");
                                        Intrinsics.checkNotNull(elementSelectFirst);
                                        String strText = elementSelectFirst.text();
                                        Intrinsics.checkNotNullExpressionValue(strText, "text(...)");
                                        String name = userload7.getName();
                                        NiceResponse niceResponse6 = niceResponse5;
                                        String name2 = userload7.getName();
                                        String str32 = str23;
                                        AnonymousClass2 anonymousClass2 = userload7.new AnonymousClass2(strText, null);
                                        c07212.L$0 = SpillingKt.nullOutSpilledVariable(userload7);
                                        c07212.L$1 = SpillingKt.nullOutSpilledVariable(str22);
                                        c07212.L$2 = SpillingKt.nullOutSpilledVariable(str28);
                                        c07212.L$3 = list9;
                                        c07212.L$4 = SpillingKt.nullOutSpilledVariable(str27);
                                        c07212.L$5 = SpillingKt.nullOutSpilledVariable(str26);
                                        c07212.L$6 = SpillingKt.nullOutSpilledVariable(str25);
                                        c07212.L$7 = SpillingKt.nullOutSpilledVariable(niceResponse4);
                                        c07212.L$8 = SpillingKt.nullOutSpilledVariable(str24);
                                        c07212.L$9 = SpillingKt.nullOutSpilledVariable(obj5);
                                        c07212.L$10 = SpillingKt.nullOutSpilledVariable(str19);
                                        c07212.L$11 = SpillingKt.nullOutSpilledVariable(str20);
                                        c07212.L$12 = SpillingKt.nullOutSpilledVariable(niceResponse6);
                                        c07212.L$13 = SpillingKt.nullOutSpilledVariable(str32);
                                        c07212.L$14 = SpillingKt.nullOutSpilledVariable(strText);
                                        c07212.L$15 = list9;
                                        c07212.label = 6;
                                        objNewExtractorLink$default = ExtractorApiKt.newExtractorLink$default(name, name2, str32, null, anonymousClass2, c07212, 8, null);
                                        if (objNewExtractorLink$default != coroutine_suspended) {
                                            list10 = list9;
                                            list9.add(objNewExtractorLink$default);
                                            return list10;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    value = str3;
                    strUnpack = new JsUnpacker(value).unpack();
                    Requests app6 = MainActivityKt.getApp();
                    String str210 = userload2.getMainUrl() + "/api/assets/userload/js/videojs.js";
                    c07212.L$0 = userload2;
                    c07212.L$1 = str4;
                    c07212.L$2 = SpillingKt.nullOutSpilledVariable(str5);
                    c07212.L$3 = list;
                    c07212.L$4 = SpillingKt.nullOutSpilledVariable(text);
                    c07212.L$5 = SpillingKt.nullOutSpilledVariable(value);
                    c07212.L$6 = strUnpack;
                    c07212.label = i;
                    list2 = list;
                    C07211 c07216 = c07212;
                    userload3 = userload2;
                    str6 = str4;
                    str7 = value;
                    z2 = false;
                    obj2 = Requests.get$default(app6, str210, null, null, null, null, false, 0, null, 0L, null, false, null, c07216, 4094, null);
                    c07212 = c07216;
                    if (obj2 != coroutine_suspended) {
                        str8 = str5;
                        str9 = text;
                        str10 = str6;
                        list3 = list2;
                        str11 = strUnpack;
                        userload4 = userload3;
                        niceResponse = (NiceResponse) obj2;
                        text2 = niceResponse.getText();
                        c07212.L$0 = userload4;
                        c07212.L$1 = str10;
                        c07212.L$2 = SpillingKt.nullOutSpilledVariable(str8);
                        c07212.L$3 = list3;
                        c07212.L$4 = SpillingKt.nullOutSpilledVariable(str9);
                        c07212.L$5 = SpillingKt.nullOutSpilledVariable(str7);
                        c07212.L$6 = str11;
                        c07212.L$7 = SpillingKt.nullOutSpilledVariable(niceResponse);
                        c07212.L$8 = SpillingKt.nullOutSpilledVariable(text2);
                        c07212.label = 3;
                        objDecodeVideoJs = userload4.decodeVideoJs(text2, c07212);
                        if (objDecodeVideoJs != coroutine_suspended) {
                            niceResponse2 = niceResponse;
                            str12 = str11;
                            str13 = text2;
                            list4 = list3;
                            str14 = str10;
                            str15 = str7;
                            r0 = z2;
                            str16 = str9;
                            str17 = str8;
                            userload5 = userload4;
                            list5 = (List) objDecodeVideoJs;
                            Intrinsics.checkNotNull(str12);
                            String str33 = str12;
                            List listSplit$default3 = StringsKt.split$default((CharSequence) str33, new String[]{";"}, false, 0, 6, (Object) null);
                            arrayList = new ArrayList();
                            while (r10.hasNext()) {
                                if (StringsKt.contains$default((String) obj7, (CharSequence) list5.get(r0), (boolean) r0, i, (Object) null)) {
                                    arrayList.add(obj7);
                                }
                            }
                            String strDropLast3 = StringsKt.dropLast(StringsKt.drop((String) StringsKt.split$default((CharSequence) arrayList.get(r0), new String[]{"="}, false, 0, 6, (Object) null).get(1), 1), 1);
                            List listSplit$default4 = StringsKt.split$default((CharSequence) str33, new String[]{";"}, false, 0, 6, (Object) null);
                            arrayList2 = new ArrayList();
                            while (r9.hasNext()) {
                                if (StringsKt.contains$default((String) obj8, (CharSequence) list5.get(1), (boolean) r0, i, (Object) null)) {
                                    arrayList2.add(obj8);
                                }
                            }
                            strDropLast = StringsKt.dropLast(StringsKt.drop((String) StringsKt.split$default((CharSequence) arrayList2.get(r0), new String[]{"="}, false, 0, 6, (Object) null).get(1), 1), 1);
                            Requests app7 = MainActivityKt.getApp();
                            String str34 = userload5.getMainUrl() + "/api/request/";
                            Pair[] pairArr2 = new Pair[i];
                            pairArr2[r0] = TuplesKt.to("morocco", strDropLast3);
                            pairArr2[1] = TuplesKt.to("mycountry", strDropLast);
                            Map mapMapOf2 = MapsKt.mapOf(pairArr2);
                            c07212.L$0 = userload5;
                            c07212.L$1 = str14;
                            c07212.L$2 = SpillingKt.nullOutSpilledVariable(str17);
                            c07212.L$3 = list4;
                            c07212.L$4 = SpillingKt.nullOutSpilledVariable(str16);
                            c07212.L$5 = SpillingKt.nullOutSpilledVariable(str15);
                            c07212.L$6 = SpillingKt.nullOutSpilledVariable(str12);
                            c07212.L$7 = SpillingKt.nullOutSpilledVariable(niceResponse2);
                            c07212.L$8 = SpillingKt.nullOutSpilledVariable(str13);
                            c07212.L$9 = SpillingKt.nullOutSpilledVariable(list5);
                            c07212.L$10 = SpillingKt.nullOutSpilledVariable(strDropLast3);
                            c07212.L$11 = SpillingKt.nullOutSpilledVariable(strDropLast);
                            c07212.label = 4;
                            C07211 c07217 = c07212;
                            list6 = list4;
                            str18 = str14;
                            str19 = strDropLast3;
                            objPost$default = Requests.post$default(app7, str34, null, null, null, null, mapMapOf2, null, null, null, false, 0, null, 0L, null, false, null, c07217, 65502, null);
                            c07212 = c07217;
                            if (objPost$default != coroutine_suspended) {
                                str20 = strDropLast;
                                obj3 = list5;
                                objNewExtractorLink$default = objPost$default;
                                userload6 = userload5;
                                str21 = str18;
                                list7 = list6;
                                niceResponse3 = (NiceResponse) objNewExtractorLink$default;
                                text3 = niceResponse3.getText();
                                Requests app8 = MainActivityKt.getApp();
                                c07212.L$0 = userload6;
                                c07212.L$1 = SpillingKt.nullOutSpilledVariable(str21);
                                c07212.L$2 = SpillingKt.nullOutSpilledVariable(str17);
                                c07212.L$3 = list7;
                                c07212.L$4 = SpillingKt.nullOutSpilledVariable(str16);
                                c07212.L$5 = SpillingKt.nullOutSpilledVariable(str15);
                                c07212.L$6 = SpillingKt.nullOutSpilledVariable(str12);
                                c07212.L$7 = SpillingKt.nullOutSpilledVariable(niceResponse2);
                                c07212.L$8 = SpillingKt.nullOutSpilledVariable(str13);
                                c07212.L$9 = SpillingKt.nullOutSpilledVariable(obj3);
                                c07212.L$10 = SpillingKt.nullOutSpilledVariable(str19);
                                c07212.L$11 = SpillingKt.nullOutSpilledVariable(str20);
                                c07212.L$12 = SpillingKt.nullOutSpilledVariable(niceResponse3);
                                c07212.L$13 = text3;
                                c07212.label = 5;
                                list8 = list7;
                                C07211 c07218 = c07212;
                                obj4 = Requests.get$default(app8, str21, null, null, null, null, false, 0, null, 0L, null, false, null, c07218, 4094, null);
                                c07212 = c07218;
                                if (obj4 != coroutine_suspended) {
                                    str22 = str21;
                                    obj5 = obj3;
                                    str23 = text3;
                                    str24 = str13;
                                    niceResponse4 = niceResponse2;
                                    str25 = str12;
                                    str26 = str15;
                                    str27 = str16;
                                    str28 = str17;
                                    niceResponse5 = niceResponse3;
                                    userload7 = userload6;
                                    list9 = list8;
                                    Element elementSelectFirst2 = ((NiceResponse) obj4).getDocument().head().selectFirst("title");
                                    Intrinsics.checkNotNull(elementSelectFirst2);
                                    String strText2 = elementSelectFirst2.text();
                                    Intrinsics.checkNotNullExpressionValue(strText2, "text(...)");
                                    String name3 = userload7.getName();
                                    NiceResponse niceResponse7 = niceResponse5;
                                    String name4 = userload7.getName();
                                    String str35 = str23;
                                    AnonymousClass2 anonymousClass3 = userload7.new AnonymousClass2(strText2, null);
                                    c07212.L$0 = SpillingKt.nullOutSpilledVariable(userload7);
                                    c07212.L$1 = SpillingKt.nullOutSpilledVariable(str22);
                                    c07212.L$2 = SpillingKt.nullOutSpilledVariable(str28);
                                    c07212.L$3 = list9;
                                    c07212.L$4 = SpillingKt.nullOutSpilledVariable(str27);
                                    c07212.L$5 = SpillingKt.nullOutSpilledVariable(str26);
                                    c07212.L$6 = SpillingKt.nullOutSpilledVariable(str25);
                                    c07212.L$7 = SpillingKt.nullOutSpilledVariable(niceResponse4);
                                    c07212.L$8 = SpillingKt.nullOutSpilledVariable(str24);
                                    c07212.L$9 = SpillingKt.nullOutSpilledVariable(obj5);
                                    c07212.L$10 = SpillingKt.nullOutSpilledVariable(str19);
                                    c07212.L$11 = SpillingKt.nullOutSpilledVariable(str20);
                                    c07212.L$12 = SpillingKt.nullOutSpilledVariable(niceResponse7);
                                    c07212.L$13 = SpillingKt.nullOutSpilledVariable(str35);
                                    c07212.L$14 = SpillingKt.nullOutSpilledVariable(strText2);
                                    c07212.L$15 = list9;
                                    c07212.label = 6;
                                    objNewExtractorLink$default = ExtractorApiKt.newExtractorLink$default(name3, name4, str35, null, anonymousClass3, c07212, 8, null);
                                    if (objNewExtractorLink$default != coroutine_suspended) {
                                        list10 = list9;
                                        list9.add(objNewExtractorLink$default);
                                        return list10;
                                    }
                                }
                            }
                        }
                    }
                }
                return coroutine_suspended;
            case 1:
                List list11 = (List) c07212.L$3;
                String str36 = (String) c07212.L$2;
                str4 = (String) c07212.L$1;
                Userload userload8 = (Userload) c07212.L$0;
                ResultKt.throwOnFailure(objNewExtractorLink$default);
                obj = objNewExtractorLink$default;
                i = 2;
                list = list11;
                str5 = str36;
                userload2 = userload8;
                str3 = null;
                text = ((NiceResponse) obj).getText();
                matchResultFind$default = Regex.find$default(new Regex("ext/javascript\">eval((.|\\n)*?)</script>"), text, 0, i, str3);
                if (matchResultFind$default != null) {
                    z = true;
                    value = str3;
                } else {
                    z = true;
                    value = str3;
                }
                strUnpack = new JsUnpacker(value).unpack();
                Requests app9 = MainActivityKt.getApp();
                String str211 = userload2.getMainUrl() + "/api/assets/userload/js/videojs.js";
                c07212.L$0 = userload2;
                c07212.L$1 = str4;
                c07212.L$2 = SpillingKt.nullOutSpilledVariable(str5);
                c07212.L$3 = list;
                c07212.L$4 = SpillingKt.nullOutSpilledVariable(text);
                c07212.L$5 = SpillingKt.nullOutSpilledVariable(value);
                c07212.L$6 = strUnpack;
                c07212.label = i;
                list2 = list;
                C07211 c07219 = c07212;
                userload3 = userload2;
                str6 = str4;
                str7 = value;
                z2 = false;
                obj2 = Requests.get$default(app9, str211, null, null, null, null, false, 0, null, 0L, null, false, null, c07219, 4094, null);
                c07212 = c07219;
                if (obj2 != coroutine_suspended) {
                    str8 = str5;
                    str9 = text;
                    str10 = str6;
                    list3 = list2;
                    str11 = strUnpack;
                    userload4 = userload3;
                    niceResponse = (NiceResponse) obj2;
                    text2 = niceResponse.getText();
                    c07212.L$0 = userload4;
                    c07212.L$1 = str10;
                    c07212.L$2 = SpillingKt.nullOutSpilledVariable(str8);
                    c07212.L$3 = list3;
                    c07212.L$4 = SpillingKt.nullOutSpilledVariable(str9);
                    c07212.L$5 = SpillingKt.nullOutSpilledVariable(str7);
                    c07212.L$6 = str11;
                    c07212.L$7 = SpillingKt.nullOutSpilledVariable(niceResponse);
                    c07212.L$8 = SpillingKt.nullOutSpilledVariable(text2);
                    c07212.label = 3;
                    objDecodeVideoJs = userload4.decodeVideoJs(text2, c07212);
                    if (objDecodeVideoJs != coroutine_suspended) {
                        niceResponse2 = niceResponse;
                        str12 = str11;
                        str13 = text2;
                        list4 = list3;
                        str14 = str10;
                        str15 = str7;
                        r0 = z2;
                        str16 = str9;
                        str17 = str8;
                        userload5 = userload4;
                        list5 = (List) objDecodeVideoJs;
                        Intrinsics.checkNotNull(str12);
                        String str37 = str12;
                        List listSplit$default5 = StringsKt.split$default((CharSequence) str37, new String[]{";"}, false, 0, 6, (Object) null);
                        arrayList = new ArrayList();
                        while (r10.hasNext()) {
                            if (StringsKt.contains$default((String) obj7, (CharSequence) list5.get(r0), (boolean) r0, i, (Object) null)) {
                                arrayList.add(obj7);
                            }
                        }
                        String strDropLast4 = StringsKt.dropLast(StringsKt.drop((String) StringsKt.split$default((CharSequence) arrayList.get(r0), new String[]{"="}, false, 0, 6, (Object) null).get(1), 1), 1);
                        List listSplit$default6 = StringsKt.split$default((CharSequence) str37, new String[]{";"}, false, 0, 6, (Object) null);
                        arrayList2 = new ArrayList();
                        while (r9.hasNext()) {
                            if (StringsKt.contains$default((String) obj8, (CharSequence) list5.get(1), (boolean) r0, i, (Object) null)) {
                                arrayList2.add(obj8);
                            }
                        }
                        strDropLast = StringsKt.dropLast(StringsKt.drop((String) StringsKt.split$default((CharSequence) arrayList2.get(r0), new String[]{"="}, false, 0, 6, (Object) null).get(1), 1), 1);
                        Requests app10 = MainActivityKt.getApp();
                        String str38 = userload5.getMainUrl() + "/api/request/";
                        Pair[] pairArr3 = new Pair[i];
                        pairArr3[r0] = TuplesKt.to("morocco", strDropLast4);
                        pairArr3[1] = TuplesKt.to("mycountry", strDropLast);
                        Map mapMapOf3 = MapsKt.mapOf(pairArr3);
                        c07212.L$0 = userload5;
                        c07212.L$1 = str14;
                        c07212.L$2 = SpillingKt.nullOutSpilledVariable(str17);
                        c07212.L$3 = list4;
                        c07212.L$4 = SpillingKt.nullOutSpilledVariable(str16);
                        c07212.L$5 = SpillingKt.nullOutSpilledVariable(str15);
                        c07212.L$6 = SpillingKt.nullOutSpilledVariable(str12);
                        c07212.L$7 = SpillingKt.nullOutSpilledVariable(niceResponse2);
                        c07212.L$8 = SpillingKt.nullOutSpilledVariable(str13);
                        c07212.L$9 = SpillingKt.nullOutSpilledVariable(list5);
                        c07212.L$10 = SpillingKt.nullOutSpilledVariable(strDropLast4);
                        c07212.L$11 = SpillingKt.nullOutSpilledVariable(strDropLast);
                        c07212.label = 4;
                        C07211 c072110 = c07212;
                        list6 = list4;
                        str18 = str14;
                        str19 = strDropLast4;
                        objPost$default = Requests.post$default(app10, str38, null, null, null, null, mapMapOf3, null, null, null, false, 0, null, 0L, null, false, null, c072110, 65502, null);
                        c07212 = c072110;
                        if (objPost$default != coroutine_suspended) {
                            str20 = strDropLast;
                            obj3 = list5;
                            objNewExtractorLink$default = objPost$default;
                            userload6 = userload5;
                            str21 = str18;
                            list7 = list6;
                            niceResponse3 = (NiceResponse) objNewExtractorLink$default;
                            text3 = niceResponse3.getText();
                            Requests app11 = MainActivityKt.getApp();
                            c07212.L$0 = userload6;
                            c07212.L$1 = SpillingKt.nullOutSpilledVariable(str21);
                            c07212.L$2 = SpillingKt.nullOutSpilledVariable(str17);
                            c07212.L$3 = list7;
                            c07212.L$4 = SpillingKt.nullOutSpilledVariable(str16);
                            c07212.L$5 = SpillingKt.nullOutSpilledVariable(str15);
                            c07212.L$6 = SpillingKt.nullOutSpilledVariable(str12);
                            c07212.L$7 = SpillingKt.nullOutSpilledVariable(niceResponse2);
                            c07212.L$8 = SpillingKt.nullOutSpilledVariable(str13);
                            c07212.L$9 = SpillingKt.nullOutSpilledVariable(obj3);
                            c07212.L$10 = SpillingKt.nullOutSpilledVariable(str19);
                            c07212.L$11 = SpillingKt.nullOutSpilledVariable(str20);
                            c07212.L$12 = SpillingKt.nullOutSpilledVariable(niceResponse3);
                            c07212.L$13 = text3;
                            c07212.label = 5;
                            list8 = list7;
                            C07211 c072111 = c07212;
                            obj4 = Requests.get$default(app11, str21, null, null, null, null, false, 0, null, 0L, null, false, null, c072111, 4094, null);
                            c07212 = c072111;
                            if (obj4 != coroutine_suspended) {
                                str22 = str21;
                                obj5 = obj3;
                                str23 = text3;
                                str24 = str13;
                                niceResponse4 = niceResponse2;
                                str25 = str12;
                                str26 = str15;
                                str27 = str16;
                                str28 = str17;
                                niceResponse5 = niceResponse3;
                                userload7 = userload6;
                                list9 = list8;
                                Element elementSelectFirst3 = ((NiceResponse) obj4).getDocument().head().selectFirst("title");
                                Intrinsics.checkNotNull(elementSelectFirst3);
                                String strText3 = elementSelectFirst3.text();
                                Intrinsics.checkNotNullExpressionValue(strText3, "text(...)");
                                String name5 = userload7.getName();
                                NiceResponse niceResponse8 = niceResponse5;
                                String name6 = userload7.getName();
                                String str39 = str23;
                                AnonymousClass2 anonymousClass4 = userload7.new AnonymousClass2(strText3, null);
                                c07212.L$0 = SpillingKt.nullOutSpilledVariable(userload7);
                                c07212.L$1 = SpillingKt.nullOutSpilledVariable(str22);
                                c07212.L$2 = SpillingKt.nullOutSpilledVariable(str28);
                                c07212.L$3 = list9;
                                c07212.L$4 = SpillingKt.nullOutSpilledVariable(str27);
                                c07212.L$5 = SpillingKt.nullOutSpilledVariable(str26);
                                c07212.L$6 = SpillingKt.nullOutSpilledVariable(str25);
                                c07212.L$7 = SpillingKt.nullOutSpilledVariable(niceResponse4);
                                c07212.L$8 = SpillingKt.nullOutSpilledVariable(str24);
                                c07212.L$9 = SpillingKt.nullOutSpilledVariable(obj5);
                                c07212.L$10 = SpillingKt.nullOutSpilledVariable(str19);
                                c07212.L$11 = SpillingKt.nullOutSpilledVariable(str20);
                                c07212.L$12 = SpillingKt.nullOutSpilledVariable(niceResponse8);
                                c07212.L$13 = SpillingKt.nullOutSpilledVariable(str39);
                                c07212.L$14 = SpillingKt.nullOutSpilledVariable(strText3);
                                c07212.L$15 = list9;
                                c07212.label = 6;
                                objNewExtractorLink$default = ExtractorApiKt.newExtractorLink$default(name5, name6, str39, null, anonymousClass4, c07212, 8, null);
                                if (objNewExtractorLink$default != coroutine_suspended) {
                                    list10 = list9;
                                    list9.add(objNewExtractorLink$default);
                                    return list10;
                                }
                            }
                        }
                    }
                }
                return coroutine_suspended;
            case 2:
                String str40 = (String) c07212.L$6;
                String str41 = (String) c07212.L$5;
                String str42 = (String) c07212.L$4;
                list3 = (List) c07212.L$3;
                String str43 = (String) c07212.L$2;
                str10 = (String) c07212.L$1;
                Userload userload9 = (Userload) c07212.L$0;
                ResultKt.throwOnFailure(objNewExtractorLink$default);
                str7 = str41;
                userload4 = userload9;
                obj2 = objNewExtractorLink$default;
                i = 2;
                str8 = str43;
                str9 = str42;
                str11 = str40;
                z2 = false;
                niceResponse = (NiceResponse) obj2;
                text2 = niceResponse.getText();
                c07212.L$0 = userload4;
                c07212.L$1 = str10;
                c07212.L$2 = SpillingKt.nullOutSpilledVariable(str8);
                c07212.L$3 = list3;
                c07212.L$4 = SpillingKt.nullOutSpilledVariable(str9);
                c07212.L$5 = SpillingKt.nullOutSpilledVariable(str7);
                c07212.L$6 = str11;
                c07212.L$7 = SpillingKt.nullOutSpilledVariable(niceResponse);
                c07212.L$8 = SpillingKt.nullOutSpilledVariable(text2);
                c07212.label = 3;
                objDecodeVideoJs = userload4.decodeVideoJs(text2, c07212);
                if (objDecodeVideoJs != coroutine_suspended) {
                    niceResponse2 = niceResponse;
                    str12 = str11;
                    str13 = text2;
                    list4 = list3;
                    str14 = str10;
                    str15 = str7;
                    r0 = z2;
                    str16 = str9;
                    str17 = str8;
                    userload5 = userload4;
                    list5 = (List) objDecodeVideoJs;
                    Intrinsics.checkNotNull(str12);
                    String str310 = str12;
                    List listSplit$default7 = StringsKt.split$default((CharSequence) str310, new String[]{";"}, false, 0, 6, (Object) null);
                    arrayList = new ArrayList();
                    while (r10.hasNext()) {
                        if (StringsKt.contains$default((String) obj7, (CharSequence) list5.get(r0), (boolean) r0, i, (Object) null)) {
                            arrayList.add(obj7);
                        }
                    }
                    String strDropLast5 = StringsKt.dropLast(StringsKt.drop((String) StringsKt.split$default((CharSequence) arrayList.get(r0), new String[]{"="}, false, 0, 6, (Object) null).get(1), 1), 1);
                    List listSplit$default8 = StringsKt.split$default((CharSequence) str310, new String[]{";"}, false, 0, 6, (Object) null);
                    arrayList2 = new ArrayList();
                    while (r9.hasNext()) {
                        if (StringsKt.contains$default((String) obj8, (CharSequence) list5.get(1), (boolean) r0, i, (Object) null)) {
                            arrayList2.add(obj8);
                        }
                    }
                    strDropLast = StringsKt.dropLast(StringsKt.drop((String) StringsKt.split$default((CharSequence) arrayList2.get(r0), new String[]{"="}, false, 0, 6, (Object) null).get(1), 1), 1);
                    Requests app12 = MainActivityKt.getApp();
                    String str311 = userload5.getMainUrl() + "/api/request/";
                    Pair[] pairArr4 = new Pair[i];
                    pairArr4[r0] = TuplesKt.to("morocco", strDropLast5);
                    pairArr4[1] = TuplesKt.to("mycountry", strDropLast);
                    Map mapMapOf4 = MapsKt.mapOf(pairArr4);
                    c07212.L$0 = userload5;
                    c07212.L$1 = str14;
                    c07212.L$2 = SpillingKt.nullOutSpilledVariable(str17);
                    c07212.L$3 = list4;
                    c07212.L$4 = SpillingKt.nullOutSpilledVariable(str16);
                    c07212.L$5 = SpillingKt.nullOutSpilledVariable(str15);
                    c07212.L$6 = SpillingKt.nullOutSpilledVariable(str12);
                    c07212.L$7 = SpillingKt.nullOutSpilledVariable(niceResponse2);
                    c07212.L$8 = SpillingKt.nullOutSpilledVariable(str13);
                    c07212.L$9 = SpillingKt.nullOutSpilledVariable(list5);
                    c07212.L$10 = SpillingKt.nullOutSpilledVariable(strDropLast5);
                    c07212.L$11 = SpillingKt.nullOutSpilledVariable(strDropLast);
                    c07212.label = 4;
                    C07211 c072112 = c07212;
                    list6 = list4;
                    str18 = str14;
                    str19 = strDropLast5;
                    objPost$default = Requests.post$default(app12, str311, null, null, null, null, mapMapOf4, null, null, null, false, 0, null, 0L, null, false, null, c072112, 65502, null);
                    c07212 = c072112;
                    if (objPost$default != coroutine_suspended) {
                        str20 = strDropLast;
                        obj3 = list5;
                        objNewExtractorLink$default = objPost$default;
                        userload6 = userload5;
                        str21 = str18;
                        list7 = list6;
                        niceResponse3 = (NiceResponse) objNewExtractorLink$default;
                        text3 = niceResponse3.getText();
                        Requests app13 = MainActivityKt.getApp();
                        c07212.L$0 = userload6;
                        c07212.L$1 = SpillingKt.nullOutSpilledVariable(str21);
                        c07212.L$2 = SpillingKt.nullOutSpilledVariable(str17);
                        c07212.L$3 = list7;
                        c07212.L$4 = SpillingKt.nullOutSpilledVariable(str16);
                        c07212.L$5 = SpillingKt.nullOutSpilledVariable(str15);
                        c07212.L$6 = SpillingKt.nullOutSpilledVariable(str12);
                        c07212.L$7 = SpillingKt.nullOutSpilledVariable(niceResponse2);
                        c07212.L$8 = SpillingKt.nullOutSpilledVariable(str13);
                        c07212.L$9 = SpillingKt.nullOutSpilledVariable(obj3);
                        c07212.L$10 = SpillingKt.nullOutSpilledVariable(str19);
                        c07212.L$11 = SpillingKt.nullOutSpilledVariable(str20);
                        c07212.L$12 = SpillingKt.nullOutSpilledVariable(niceResponse3);
                        c07212.L$13 = text3;
                        c07212.label = 5;
                        list8 = list7;
                        C07211 c072113 = c07212;
                        obj4 = Requests.get$default(app13, str21, null, null, null, null, false, 0, null, 0L, null, false, null, c072113, 4094, null);
                        c07212 = c072113;
                        if (obj4 != coroutine_suspended) {
                            str22 = str21;
                            obj5 = obj3;
                            str23 = text3;
                            str24 = str13;
                            niceResponse4 = niceResponse2;
                            str25 = str12;
                            str26 = str15;
                            str27 = str16;
                            str28 = str17;
                            niceResponse5 = niceResponse3;
                            userload7 = userload6;
                            list9 = list8;
                            Element elementSelectFirst4 = ((NiceResponse) obj4).getDocument().head().selectFirst("title");
                            Intrinsics.checkNotNull(elementSelectFirst4);
                            String strText4 = elementSelectFirst4.text();
                            Intrinsics.checkNotNullExpressionValue(strText4, "text(...)");
                            String name7 = userload7.getName();
                            NiceResponse niceResponse9 = niceResponse5;
                            String name8 = userload7.getName();
                            String str312 = str23;
                            AnonymousClass2 anonymousClass5 = userload7.new AnonymousClass2(strText4, null);
                            c07212.L$0 = SpillingKt.nullOutSpilledVariable(userload7);
                            c07212.L$1 = SpillingKt.nullOutSpilledVariable(str22);
                            c07212.L$2 = SpillingKt.nullOutSpilledVariable(str28);
                            c07212.L$3 = list9;
                            c07212.L$4 = SpillingKt.nullOutSpilledVariable(str27);
                            c07212.L$5 = SpillingKt.nullOutSpilledVariable(str26);
                            c07212.L$6 = SpillingKt.nullOutSpilledVariable(str25);
                            c07212.L$7 = SpillingKt.nullOutSpilledVariable(niceResponse4);
                            c07212.L$8 = SpillingKt.nullOutSpilledVariable(str24);
                            c07212.L$9 = SpillingKt.nullOutSpilledVariable(obj5);
                            c07212.L$10 = SpillingKt.nullOutSpilledVariable(str19);
                            c07212.L$11 = SpillingKt.nullOutSpilledVariable(str20);
                            c07212.L$12 = SpillingKt.nullOutSpilledVariable(niceResponse9);
                            c07212.L$13 = SpillingKt.nullOutSpilledVariable(str312);
                            c07212.L$14 = SpillingKt.nullOutSpilledVariable(strText4);
                            c07212.L$15 = list9;
                            c07212.label = 6;
                            objNewExtractorLink$default = ExtractorApiKt.newExtractorLink$default(name7, name8, str312, null, anonymousClass5, c07212, 8, null);
                            if (objNewExtractorLink$default != coroutine_suspended) {
                                list10 = list9;
                                list9.add(objNewExtractorLink$default);
                                return list10;
                            }
                        }
                    }
                }
                return coroutine_suspended;
            case 3:
                String str44 = (String) c07212.L$8;
                NiceResponse niceResponse10 = (NiceResponse) c07212.L$7;
                String str45 = (String) c07212.L$6;
                String str46 = (String) c07212.L$5;
                str9 = (String) c07212.L$4;
                List list12 = (List) c07212.L$3;
                str8 = (String) c07212.L$2;
                String str47 = (String) c07212.L$1;
                userload4 = (Userload) c07212.L$0;
                ResultKt.throwOnFailure(objNewExtractorLink$default);
                str13 = str44;
                niceResponse2 = niceResponse10;
                str12 = str45;
                r0 = 0;
                str15 = str46;
                list4 = list12;
                str14 = str47;
                objDecodeVideoJs = objNewExtractorLink$default;
                i = 2;
                str16 = str9;
                str17 = str8;
                userload5 = userload4;
                list5 = (List) objDecodeVideoJs;
                Intrinsics.checkNotNull(str12);
                String str313 = str12;
                List listSplit$default9 = StringsKt.split$default((CharSequence) str313, new String[]{";"}, false, 0, 6, (Object) null);
                arrayList = new ArrayList();
                while (r10.hasNext()) {
                    if (StringsKt.contains$default((String) obj7, (CharSequence) list5.get(r0), (boolean) r0, i, (Object) null)) {
                        arrayList.add(obj7);
                    }
                }
                String strDropLast6 = StringsKt.dropLast(StringsKt.drop((String) StringsKt.split$default((CharSequence) arrayList.get(r0), new String[]{"="}, false, 0, 6, (Object) null).get(1), 1), 1);
                List listSplit$default10 = StringsKt.split$default((CharSequence) str313, new String[]{";"}, false, 0, 6, (Object) null);
                arrayList2 = new ArrayList();
                while (r9.hasNext()) {
                    if (StringsKt.contains$default((String) obj8, (CharSequence) list5.get(1), (boolean) r0, i, (Object) null)) {
                        arrayList2.add(obj8);
                    }
                }
                strDropLast = StringsKt.dropLast(StringsKt.drop((String) StringsKt.split$default((CharSequence) arrayList2.get(r0), new String[]{"="}, false, 0, 6, (Object) null).get(1), 1), 1);
                Requests app14 = MainActivityKt.getApp();
                String str314 = userload5.getMainUrl() + "/api/request/";
                Pair[] pairArr5 = new Pair[i];
                pairArr5[r0] = TuplesKt.to("morocco", strDropLast6);
                pairArr5[1] = TuplesKt.to("mycountry", strDropLast);
                Map mapMapOf5 = MapsKt.mapOf(pairArr5);
                c07212.L$0 = userload5;
                c07212.L$1 = str14;
                c07212.L$2 = SpillingKt.nullOutSpilledVariable(str17);
                c07212.L$3 = list4;
                c07212.L$4 = SpillingKt.nullOutSpilledVariable(str16);
                c07212.L$5 = SpillingKt.nullOutSpilledVariable(str15);
                c07212.L$6 = SpillingKt.nullOutSpilledVariable(str12);
                c07212.L$7 = SpillingKt.nullOutSpilledVariable(niceResponse2);
                c07212.L$8 = SpillingKt.nullOutSpilledVariable(str13);
                c07212.L$9 = SpillingKt.nullOutSpilledVariable(list5);
                c07212.L$10 = SpillingKt.nullOutSpilledVariable(strDropLast6);
                c07212.L$11 = SpillingKt.nullOutSpilledVariable(strDropLast);
                c07212.label = 4;
                C07211 c072114 = c07212;
                list6 = list4;
                str18 = str14;
                str19 = strDropLast6;
                objPost$default = Requests.post$default(app14, str314, null, null, null, null, mapMapOf5, null, null, null, false, 0, null, 0L, null, false, null, c072114, 65502, null);
                c07212 = c072114;
                if (objPost$default != coroutine_suspended) {
                    str20 = strDropLast;
                    obj3 = list5;
                    objNewExtractorLink$default = objPost$default;
                    userload6 = userload5;
                    str21 = str18;
                    list7 = list6;
                    niceResponse3 = (NiceResponse) objNewExtractorLink$default;
                    text3 = niceResponse3.getText();
                    Requests app15 = MainActivityKt.getApp();
                    c07212.L$0 = userload6;
                    c07212.L$1 = SpillingKt.nullOutSpilledVariable(str21);
                    c07212.L$2 = SpillingKt.nullOutSpilledVariable(str17);
                    c07212.L$3 = list7;
                    c07212.L$4 = SpillingKt.nullOutSpilledVariable(str16);
                    c07212.L$5 = SpillingKt.nullOutSpilledVariable(str15);
                    c07212.L$6 = SpillingKt.nullOutSpilledVariable(str12);
                    c07212.L$7 = SpillingKt.nullOutSpilledVariable(niceResponse2);
                    c07212.L$8 = SpillingKt.nullOutSpilledVariable(str13);
                    c07212.L$9 = SpillingKt.nullOutSpilledVariable(obj3);
                    c07212.L$10 = SpillingKt.nullOutSpilledVariable(str19);
                    c07212.L$11 = SpillingKt.nullOutSpilledVariable(str20);
                    c07212.L$12 = SpillingKt.nullOutSpilledVariable(niceResponse3);
                    c07212.L$13 = text3;
                    c07212.label = 5;
                    list8 = list7;
                    C07211 c072115 = c07212;
                    obj4 = Requests.get$default(app15, str21, null, null, null, null, false, 0, null, 0L, null, false, null, c072115, 4094, null);
                    c07212 = c072115;
                    if (obj4 != coroutine_suspended) {
                        str22 = str21;
                        obj5 = obj3;
                        str23 = text3;
                        str24 = str13;
                        niceResponse4 = niceResponse2;
                        str25 = str12;
                        str26 = str15;
                        str27 = str16;
                        str28 = str17;
                        niceResponse5 = niceResponse3;
                        userload7 = userload6;
                        list9 = list8;
                        Element elementSelectFirst5 = ((NiceResponse) obj4).getDocument().head().selectFirst("title");
                        Intrinsics.checkNotNull(elementSelectFirst5);
                        String strText5 = elementSelectFirst5.text();
                        Intrinsics.checkNotNullExpressionValue(strText5, "text(...)");
                        String name9 = userload7.getName();
                        NiceResponse niceResponse11 = niceResponse5;
                        String name10 = userload7.getName();
                        String str315 = str23;
                        AnonymousClass2 anonymousClass6 = userload7.new AnonymousClass2(strText5, null);
                        c07212.L$0 = SpillingKt.nullOutSpilledVariable(userload7);
                        c07212.L$1 = SpillingKt.nullOutSpilledVariable(str22);
                        c07212.L$2 = SpillingKt.nullOutSpilledVariable(str28);
                        c07212.L$3 = list9;
                        c07212.L$4 = SpillingKt.nullOutSpilledVariable(str27);
                        c07212.L$5 = SpillingKt.nullOutSpilledVariable(str26);
                        c07212.L$6 = SpillingKt.nullOutSpilledVariable(str25);
                        c07212.L$7 = SpillingKt.nullOutSpilledVariable(niceResponse4);
                        c07212.L$8 = SpillingKt.nullOutSpilledVariable(str24);
                        c07212.L$9 = SpillingKt.nullOutSpilledVariable(obj5);
                        c07212.L$10 = SpillingKt.nullOutSpilledVariable(str19);
                        c07212.L$11 = SpillingKt.nullOutSpilledVariable(str20);
                        c07212.L$12 = SpillingKt.nullOutSpilledVariable(niceResponse11);
                        c07212.L$13 = SpillingKt.nullOutSpilledVariable(str315);
                        c07212.L$14 = SpillingKt.nullOutSpilledVariable(strText5);
                        c07212.L$15 = list9;
                        c07212.label = 6;
                        objNewExtractorLink$default = ExtractorApiKt.newExtractorLink$default(name9, name10, str315, null, anonymousClass6, c07212, 8, null);
                        if (objNewExtractorLink$default != coroutine_suspended) {
                            list10 = list9;
                            list9.add(objNewExtractorLink$default);
                            return list10;
                        }
                    }
                }
                return coroutine_suspended;
            case 4:
                String str48 = (String) c07212.L$11;
                String str49 = (String) c07212.L$10;
                Object obj9 = (List) c07212.L$9;
                String str50 = (String) c07212.L$8;
                NiceResponse niceResponse12 = (NiceResponse) c07212.L$7;
                String str51 = (String) c07212.L$6;
                String str52 = (String) c07212.L$5;
                String str53 = (String) c07212.L$4;
                List list13 = (List) c07212.L$3;
                String str54 = (String) c07212.L$2;
                String str55 = (String) c07212.L$1;
                Userload userload10 = (Userload) c07212.L$0;
                ResultKt.throwOnFailure(objNewExtractorLink$default);
                str20 = str48;
                str19 = str49;
                obj3 = obj9;
                str13 = str50;
                niceResponse2 = niceResponse12;
                str12 = str51;
                str15 = str52;
                str16 = str53;
                list7 = list13;
                str17 = str54;
                str21 = str55;
                userload6 = userload10;
                niceResponse3 = (NiceResponse) objNewExtractorLink$default;
                text3 = niceResponse3.getText();
                Requests app16 = MainActivityKt.getApp();
                c07212.L$0 = userload6;
                c07212.L$1 = SpillingKt.nullOutSpilledVariable(str21);
                c07212.L$2 = SpillingKt.nullOutSpilledVariable(str17);
                c07212.L$3 = list7;
                c07212.L$4 = SpillingKt.nullOutSpilledVariable(str16);
                c07212.L$5 = SpillingKt.nullOutSpilledVariable(str15);
                c07212.L$6 = SpillingKt.nullOutSpilledVariable(str12);
                c07212.L$7 = SpillingKt.nullOutSpilledVariable(niceResponse2);
                c07212.L$8 = SpillingKt.nullOutSpilledVariable(str13);
                c07212.L$9 = SpillingKt.nullOutSpilledVariable(obj3);
                c07212.L$10 = SpillingKt.nullOutSpilledVariable(str19);
                c07212.L$11 = SpillingKt.nullOutSpilledVariable(str20);
                c07212.L$12 = SpillingKt.nullOutSpilledVariable(niceResponse3);
                c07212.L$13 = text3;
                c07212.label = 5;
                list8 = list7;
                C07211 c072116 = c07212;
                obj4 = Requests.get$default(app16, str21, null, null, null, null, false, 0, null, 0L, null, false, null, c072116, 4094, null);
                c07212 = c072116;
                if (obj4 != coroutine_suspended) {
                    str22 = str21;
                    obj5 = obj3;
                    str23 = text3;
                    str24 = str13;
                    niceResponse4 = niceResponse2;
                    str25 = str12;
                    str26 = str15;
                    str27 = str16;
                    str28 = str17;
                    niceResponse5 = niceResponse3;
                    userload7 = userload6;
                    list9 = list8;
                    Element elementSelectFirst6 = ((NiceResponse) obj4).getDocument().head().selectFirst("title");
                    Intrinsics.checkNotNull(elementSelectFirst6);
                    String strText6 = elementSelectFirst6.text();
                    Intrinsics.checkNotNullExpressionValue(strText6, "text(...)");
                    String name11 = userload7.getName();
                    NiceResponse niceResponse13 = niceResponse5;
                    String name12 = userload7.getName();
                    String str316 = str23;
                    AnonymousClass2 anonymousClass7 = userload7.new AnonymousClass2(strText6, null);
                    c07212.L$0 = SpillingKt.nullOutSpilledVariable(userload7);
                    c07212.L$1 = SpillingKt.nullOutSpilledVariable(str22);
                    c07212.L$2 = SpillingKt.nullOutSpilledVariable(str28);
                    c07212.L$3 = list9;
                    c07212.L$4 = SpillingKt.nullOutSpilledVariable(str27);
                    c07212.L$5 = SpillingKt.nullOutSpilledVariable(str26);
                    c07212.L$6 = SpillingKt.nullOutSpilledVariable(str25);
                    c07212.L$7 = SpillingKt.nullOutSpilledVariable(niceResponse4);
                    c07212.L$8 = SpillingKt.nullOutSpilledVariable(str24);
                    c07212.L$9 = SpillingKt.nullOutSpilledVariable(obj5);
                    c07212.L$10 = SpillingKt.nullOutSpilledVariable(str19);
                    c07212.L$11 = SpillingKt.nullOutSpilledVariable(str20);
                    c07212.L$12 = SpillingKt.nullOutSpilledVariable(niceResponse13);
                    c07212.L$13 = SpillingKt.nullOutSpilledVariable(str316);
                    c07212.L$14 = SpillingKt.nullOutSpilledVariable(strText6);
                    c07212.L$15 = list9;
                    c07212.label = 6;
                    objNewExtractorLink$default = ExtractorApiKt.newExtractorLink$default(name11, name12, str316, null, anonymousClass7, c07212, 8, null);
                    if (objNewExtractorLink$default != coroutine_suspended) {
                        list10 = list9;
                        list9.add(objNewExtractorLink$default);
                        return list10;
                    }
                }
                return coroutine_suspended;
            case 5:
                String str56 = (String) c07212.L$13;
                NiceResponse niceResponse14 = (NiceResponse) c07212.L$12;
                String str57 = (String) c07212.L$11;
                String str58 = (String) c07212.L$10;
                Object obj10 = (List) c07212.L$9;
                str24 = (String) c07212.L$8;
                niceResponse4 = (NiceResponse) c07212.L$7;
                str25 = (String) c07212.L$6;
                str26 = (String) c07212.L$5;
                str27 = (String) c07212.L$4;
                List list14 = (List) c07212.L$3;
                str28 = (String) c07212.L$2;
                String str59 = (String) c07212.L$1;
                Userload userload11 = (Userload) c07212.L$0;
                ResultKt.throwOnFailure(objNewExtractorLink$default);
                str20 = str57;
                str19 = str58;
                str23 = str56;
                str22 = str59;
                niceResponse5 = niceResponse14;
                obj4 = objNewExtractorLink$default;
                userload7 = userload11;
                list9 = list14;
                obj5 = obj10;
                Element elementSelectFirst7 = ((NiceResponse) obj4).getDocument().head().selectFirst("title");
                Intrinsics.checkNotNull(elementSelectFirst7);
                String strText7 = elementSelectFirst7.text();
                Intrinsics.checkNotNullExpressionValue(strText7, "text(...)");
                String name13 = userload7.getName();
                NiceResponse niceResponse15 = niceResponse5;
                String name14 = userload7.getName();
                String str317 = str23;
                AnonymousClass2 anonymousClass8 = userload7.new AnonymousClass2(strText7, null);
                c07212.L$0 = SpillingKt.nullOutSpilledVariable(userload7);
                c07212.L$1 = SpillingKt.nullOutSpilledVariable(str22);
                c07212.L$2 = SpillingKt.nullOutSpilledVariable(str28);
                c07212.L$3 = list9;
                c07212.L$4 = SpillingKt.nullOutSpilledVariable(str27);
                c07212.L$5 = SpillingKt.nullOutSpilledVariable(str26);
                c07212.L$6 = SpillingKt.nullOutSpilledVariable(str25);
                c07212.L$7 = SpillingKt.nullOutSpilledVariable(niceResponse4);
                c07212.L$8 = SpillingKt.nullOutSpilledVariable(str24);
                c07212.L$9 = SpillingKt.nullOutSpilledVariable(obj5);
                c07212.L$10 = SpillingKt.nullOutSpilledVariable(str19);
                c07212.L$11 = SpillingKt.nullOutSpilledVariable(str20);
                c07212.L$12 = SpillingKt.nullOutSpilledVariable(niceResponse15);
                c07212.L$13 = SpillingKt.nullOutSpilledVariable(str317);
                c07212.L$14 = SpillingKt.nullOutSpilledVariable(strText7);
                c07212.L$15 = list9;
                c07212.label = 6;
                objNewExtractorLink$default = ExtractorApiKt.newExtractorLink$default(name13, name14, str317, null, anonymousClass8, c07212, 8, null);
                if (objNewExtractorLink$default != coroutine_suspended) {
                    list10 = list9;
                    list9.add(objNewExtractorLink$default);
                    return list10;
                }
                return coroutine_suspended;
            case 6:
                list9 = (List) c07212.L$15;
                list10 = (List) c07212.L$3;
                ResultKt.throwOnFailure(objNewExtractorLink$default);
                list9.add(objNewExtractorLink$default);
                return list10;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Userload$getUrl$2, reason: invalid class name */
    /* JADX INFO: compiled from: Userload.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Userload$getUrl$2", f = "Userload.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $nameSource;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$nameSource = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = Userload.this.new AnonymousClass2(this.$nameSource, continuation);
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
            extractorLink.setReferer(Userload.this.getMainUrl());
            extractorLink.setQuality(ExtractorApiKt.getQualityFromName(this.$nameSource));
            return Unit.INSTANCE;
        }
    }
}
