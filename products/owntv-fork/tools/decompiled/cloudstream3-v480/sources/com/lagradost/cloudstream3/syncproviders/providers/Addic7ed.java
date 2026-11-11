package com.lagradost.cloudstream3.syncproviders.providers;

import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.room.RoomMasterTable;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.cast.MediaTrack;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.subtitles.AbstractSubtitleEntities;
import com.lagradost.cloudstream3.syncproviders.AuthData;
import com.lagradost.cloudstream3.syncproviders.SubtitleAPI;
import com.lagradost.cloudstream3.utils.SubtitleHelper;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/* JADX INFO: compiled from: Addic7ed.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u000e\u001a\u00020\u0005*\u00020\u0005H\u0002J(\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0096@¢\u0006\u0002\u0010\u0016J\"\u0010\u0017\u001a\u0004\u0018\u00010\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0018\u001a\u00020\u0011H\u0096@¢\u0006\u0002\u0010\u0019R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR&\u0010\u001a\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u001c0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/Addic7ed;", "Lcom/lagradost/cloudstream3/syncproviders/SubtitleAPI;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "idPrefix", "getIdPrefix", "requiresLogin", "", "getRequiresLogin", "()Z", "fixUrl", "search", "", "Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleEntity;", "auth", "Lcom/lagradost/cloudstream3/syncproviders/AuthData;", SearchIntents.EXTRA_QUERY, "Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleSearch;", "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleSearch;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "load", MediaTrack.ROLE_SUBTITLE, "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "langTagIETF2Addic7ed", "", "Lkotlin/Pair;", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Addic7ed extends SubtitleAPI {
    public static final String HOST = "https://www.addic7ed.com";
    public static final String TAG = "ADDIC7ED";
    private final boolean requiresLogin;
    private final String name = "Addic7ed";
    private final String idPrefix = "addic7ed";
    private final Map<String, Pair<String, String>> langTagIETF2Addic7ed = MapsKt.mapOf(TuplesKt.to("ar", new Pair("38", "Arabic")), TuplesKt.to("az", new Pair("48", "Azerbaijani")), TuplesKt.to("bg", new Pair("35", "Bulgarian")), TuplesKt.to("bn", new Pair("47", "Bengali")), TuplesKt.to(CmcdConfiguration.KEY_BUFFER_STARVATION, new Pair("44", "Bosnian")), TuplesKt.to("ca", new Pair("12", "Català")), TuplesKt.to("cs", new Pair("14", "Czech")), TuplesKt.to("cy", new Pair("65", "Welsh")), TuplesKt.to("da", new Pair("30", "Danish")), TuplesKt.to("de", new Pair("11", "German")), TuplesKt.to("el", new Pair("27", "Greek")), TuplesKt.to("en", new Pair("1", "English")), TuplesKt.to("es-419", new Pair("6", "Spanish (Latin America)")), TuplesKt.to("es-ar", new Pair("69", "Spanish (Argentina)")), TuplesKt.to("es-es", new Pair("5", "Spanish (Spain)")), TuplesKt.to("es", new Pair("4", "Spanish")), TuplesKt.to("et", new Pair("54", "Estonian")), TuplesKt.to("eu", new Pair("13", "Euskera")), TuplesKt.to("fa", new Pair("43", "Persian")), TuplesKt.to("fi", new Pair("28", "Finnish")), TuplesKt.to("fr-ca", new Pair("53", "French (Canadian)")), TuplesKt.to("fr", new Pair("8", "French")), TuplesKt.to("gl", new Pair("15", "Galego")), TuplesKt.to("he", new Pair("23", "Hebrew")), TuplesKt.to("hi", new Pair("55", "Hindi")), TuplesKt.to("hr", new Pair("31", "Croatian")), TuplesKt.to("hu", new Pair("20", "Hungarian")), TuplesKt.to("hy", new Pair("50", "Armenian")), TuplesKt.to("id", new Pair("37", "Indonesian")), TuplesKt.to("is", new Pair("56", "Icelandic")), TuplesKt.to("it", new Pair("7", "Italian")), TuplesKt.to("ja", new Pair("32", "Japanese")), TuplesKt.to("kn", new Pair("66", "Kannada")), TuplesKt.to("ko", new Pair(RoomMasterTable.DEFAULT_ID, "Korean")), TuplesKt.to("lt", new Pair("58", "Lithuanian")), TuplesKt.to("lv", new Pair("57", "Latvian")), TuplesKt.to("mk", new Pair("49", "Macedonian")), TuplesKt.to("ml", new Pair("67", "Malayalam")), TuplesKt.to("mr", new Pair("62", "Marathi")), TuplesKt.to("ms", new Pair("40", "Malay")), TuplesKt.to("nl", new Pair("17", "Dutch")), TuplesKt.to("no", new Pair("29", "Norwegian")), TuplesKt.to("pl", new Pair("21", "Polish")), TuplesKt.to("pt-br", new Pair("10", "Portuguese (Brazilian)")), TuplesKt.to("pt", new Pair("9", "Portuguese")), TuplesKt.to("ro", new Pair("26", "Romanian")), TuplesKt.to("ru", new Pair("19", "Russian")), TuplesKt.to("si", new Pair("60", "Sinhala")), TuplesKt.to("sk", new Pair("25", "Slovak")), TuplesKt.to("sl", new Pair("22", "Slovenian")), TuplesKt.to("sq", new Pair("52", "Albanian")), TuplesKt.to("sr-latn", new Pair("36", "Serbian (Latin)")), TuplesKt.to("sr", new Pair("39", "Serbian (Cyrillic)")), TuplesKt.to("sv", new Pair("18", "Swedish")), TuplesKt.to("ta", new Pair("59", "Tamil")), TuplesKt.to("te", new Pair("63", "Telugu")), TuplesKt.to("th", new Pair("46", "Thai")), TuplesKt.to("tl", new Pair("68", "Tagalog")), TuplesKt.to("tlh", new Pair("61", "Klingon")), TuplesKt.to("tr", new Pair("16", "Turkish")), TuplesKt.to("uk", new Pair("51", "Ukrainian")), TuplesKt.to("vi", new Pair("45", "Vietnamese")), TuplesKt.to("yue", new Pair("64", "Cantonese")), TuplesKt.to("zh-hans", new Pair("41", "Chinese (Simplified)")), TuplesKt.to("zh-hant", new Pair("24", "Chinese (Traditional)")));

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.Addic7ed$search$1, reason: invalid class name */
    /* JADX INFO: compiled from: Addic7ed.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.Addic7ed", f = "Addic7ed.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {68, 78, 108}, m = "search", n = {"auth", SearchIntents.EXTRA_QUERY, "langTagIETF", "langNumAddic7ed", "langName", "title", "searchQuery", "downloadPage", "epNum", "seasonNum", "yearNum", "auth", SearchIntents.EXTRA_QUERY, "langTagIETF", "langNumAddic7ed", "langName", "title", "searchQuery", "downloadPage", "response", "hostDocument", "showId", "epNum", "seasonNum", "yearNum", "auth", SearchIntents.EXTRA_QUERY, "langTagIETF", "langNumAddic7ed", "langName", "title", "searchQuery", "downloadPage", "response", "hostDocument", "epNum", "seasonNum", "yearNum"}, nl = {69, 81, 111}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "I$0", "I$1", "I$2"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$10;
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
            return Addic7ed.this.search(null, null, this);
        }
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public String getName() {
        return this.name;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public String getIdPrefix() {
        return this.idPrefix;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public boolean getRequiresLogin() {
        return this.requiresLogin;
    }

    private final String fixUrl(String str) {
        if (StringsKt.startsWith$default(str, "/", false, 2, (Object) null)) {
            return HOST + str;
        }
        if (StringsKt.startsWith$default(str, "http", false, 2, (Object) null)) {
            return str;
        }
        return "https://www.addic7ed.com/" + str;
    }

    /* JADX WARN: Code duplicated, block: B:109:0x049b  */
    /* JADX WARN: Code duplicated, block: B:113:0x0524  */
    /* JADX WARN: Code duplicated, block: B:117:0x0560  */
    /* JADX WARN: Code duplicated, block: B:119:0x0573  */
    /* JADX WARN: Code duplicated, block: B:122:0x0582  */
    /* JADX WARN: Code duplicated, block: B:125:0x05be  */
    /* JADX WARN: Code duplicated, block: B:127:0x05c6  */
    /* JADX WARN: Code duplicated, block: B:128:0x05cb  */
    /* JADX WARN: Code duplicated, block: B:132:0x05fa  */
    /* JADX WARN: Code duplicated, block: B:138:0x05fd A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:141:0x0403 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:67:0x0360  */
    /* JADX WARN: Code duplicated, block: B:74:0x03b5  */
    /* JADX WARN: Code duplicated, block: B:76:0x03bd  */
    /* JADX WARN: Code duplicated, block: B:77:0x03c2  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Code duplicated, block: B:81:0x03de  */
    /* JADX WARN: Code duplicated, block: B:82:0x03e1  */
    /* JADX WARN: Code duplicated, block: B:84:0x03f0  */
    /* JADX WARN: Code duplicated, block: B:86:0x0400  */
    /* JADX WARN: Instruction removed from duplicated block: B:109:0x049b, please report this as an issue */
    @Override // com.lagradost.cloudstream3.syncproviders.SubtitleAPI
    public Object search(AuthData authData, AbstractSubtitleEntities.SubtitleSearch subtitleSearch, Continuation<? super List<AbstractSubtitleEntities.SubtitleEntity>> continuation) {
        AnonymousClass1 anonymousClass1;
        String strBoxInt;
        String strFromTagToEnglishLanguageName;
        String str;
        boolean z;
        String str2;
        Object obj;
        boolean z2;
        String str3;
        int i;
        AnonymousClass1 anonymousClass2;
        Object obj2;
        String str4;
        String str5;
        String str6;
        int i2;
        int i3;
        int i4;
        String str7;
        Object obj3;
        AuthData authData2;
        String str8;
        int i5;
        String str9;
        String str10;
        int i6;
        int i7;
        String str11;
        String url;
        String str12;
        String str13;
        int i8;
        int i9;
        String str14;
        int i10;
        String str15;
        String str16;
        char c;
        Object obj4;
        String str17;
        String str18;
        int i11;
        int i12;
        String str19;
        String strAttr;
        String str20;
        int i13;
        String str21;
        int i14;
        int i15;
        ArrayList arrayList;
        Integer intOrNull;
        int i16;
        String str22;
        Object objSearch$newSubtitleEntity;
        Element elementSelectFirst;
        Addic7ed addic7ed;
        String strFixUrl;
        String strText;
        boolean z3;
        String strAttr2;
        Document document;
        String str23;
        ArrayList arrayList2;
        Element elementSelectFirst2;
        String str24;
        String strSubstringBefore$default;
        Element elementSelectFirst3;
        String str25;
        Addic7ed addic7ed2;
        String strFixUrl2;
        AbstractSubtitleEntities.SubtitleEntity subtitleEntitySearch$newSubtitleEntity;
        String strAttr3;
        String strText2;
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
        Object obj5 = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i17 = anonymousClass1.label;
        String str26 = "text(...)";
        if (i17 == 0) {
            ResultKt.throwOnFailure(obj5);
            String lang = subtitleSearch.getLang();
            if (lang == null) {
                lang = MainAPIKt.AllLanguagesName;
            }
            Pair<String, String> pair = this.langTagIETF2Addic7ed.get(lang);
            if (pair == null || (strBoxInt = pair.getFirst()) == null) {
                strBoxInt = Boxing.boxInt(0);
            }
            Pair<String, String> pair2 = this.langTagIETF2Addic7ed.get(lang);
            if ((pair2 == null || (strFromTagToEnglishLanguageName = pair2.getSecond()) == null) && (strFromTagToEnglishLanguageName = SubtitleHelper.INSTANCE.fromTagToEnglishLanguageName(lang)) == null) {
                strFromTagToEnglishLanguageName = "Completed";
            }
            String string = StringsKt.trim((CharSequence) subtitleSearch.getQuery()).toString();
            Integer epNumber = subtitleSearch.getEpNumber();
            int iIntValue = epNumber != null ? epNumber.intValue() : 0;
            Integer seasonNumber = subtitleSearch.getSeasonNumber();
            int iIntValue2 = seasonNumber != null ? seasonNumber.intValue() : 0;
            Integer year = subtitleSearch.getYear();
            int iIntValue3 = year != null ? year.intValue() : 0;
            String str27 = iIntValue2 > 0 ? string + ' ' + iIntValue2 + ' ' + iIntValue : string;
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(authData);
            anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(subtitleSearch);
            anonymousClass1.L$2 = lang;
            anonymousClass1.L$3 = strBoxInt;
            anonymousClass1.L$4 = strFromTagToEnglishLanguageName;
            anonymousClass1.L$5 = SpillingKt.nullOutSpilledVariable(string);
            anonymousClass1.L$6 = SpillingKt.nullOutSpilledVariable(str27);
            str = "";
            anonymousClass1.L$7 = SpillingKt.nullOutSpilledVariable("");
            anonymousClass1.I$0 = iIntValue;
            anonymousClass1.I$1 = iIntValue2;
            anonymousClass1.I$2 = iIntValue3;
            anonymousClass1.label = 1;
            z = true;
            String str28 = strFromTagToEnglishLanguageName;
            int i18 = iIntValue;
            int i19 = iIntValue2;
            int i20 = iIntValue3;
            String str29 = str27;
            Object obj6 = strBoxInt;
            AnonymousClass1 anonymousClass3 = anonymousClass1;
            str2 = "href";
            obj = coroutine_suspended;
            z2 = false;
            str3 = "a[href~=updated\\/|original\\/]";
            String str30 = lang;
            i = 2;
            Object obj7 = Requests.get$default(MainActivityKt.getApp(), "https://www.addic7ed.com/search.php?search=" + str27 + "&Submit=Search", null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass3, 4094, null);
            anonymousClass2 = anonymousClass3;
            if (obj7 != obj) {
                obj2 = obj6;
                str4 = str30;
                str5 = str28;
                str6 = string;
                i2 = i18;
                i3 = i19;
                i4 = i20;
                str7 = str29;
                obj3 = obj7;
                authData2 = authData;
            }
            return obj;
        }
        if (i17 != 1) {
            if (i17 == 2) {
                int i21 = anonymousClass1.I$2;
                int i22 = anonymousClass1.I$1;
                int i23 = anonymousClass1.I$0;
                Object obj8 = anonymousClass1.L$3;
                String str31 = (String) anonymousClass1.L$2;
                ResultKt.throwOnFailure(obj5);
                i13 = i22;
                str3 = "a[href~=updated\\/|original\\/]";
                str21 = str31;
                str2 = "href";
                str26 = "text(...)";
                String str32 = "select(...)";
                z = true;
                str20 = null;
                i15 = i21;
                i14 = i23;
                Elements<Element> elementsSelect = ((NiceResponse) obj5).getDocument().select("#season tbody tr");
                Intrinsics.checkNotNullExpressionValue(elementsSelect, str32);
                arrayList = new ArrayList();
                for (Element element : elementsSelect) {
                    String strText3 = element.select("td:eq(1)").text();
                    str26 = str26;
                    Intrinsics.checkNotNullExpressionValue(strText3, str26);
                    intOrNull = StringsKt.toIntOrNull(strText3);
                    if (intOrNull == null && intOrNull.intValue() == i14) {
                        String str33 = element.select("td:eq(2)").text() + '\n' + element.select("td:eq(4)").text();
                        String str34 = str3;
                        elementSelectFirst = element.selectFirst(str34);
                        str22 = str2;
                        if (elementSelectFirst != null) {
                            strAttr2 = elementSelectFirst.attr(str22);
                            addic7ed = this;
                            if (strAttr2 != null) {
                                strFixUrl = addic7ed.fixUrl(strAttr2);
                            }
                            strText = element.select("td:eq(6)").text();
                            Intrinsics.checkNotNullExpressionValue(strText, str26);
                            str3 = str34;
                            if (strText.length() > 0) {
                                z3 = z;
                            } else {
                                z3 = false;
                            }
                            i16 = i13;
                            objSearch$newSubtitleEntity = search$newSubtitleEntity(addic7ed, i16, str21, i14, i15, str33, strFixUrl, z3);
                        } else {
                            addic7ed = this;
                        }
                        strFixUrl = str20;
                        strText = element.select("td:eq(6)").text();
                        Intrinsics.checkNotNullExpressionValue(strText, str26);
                        str3 = str34;
                        if (strText.length() > 0) {
                            z3 = z;
                        } else {
                            z3 = false;
                        }
                        i16 = i13;
                        objSearch$newSubtitleEntity = search$newSubtitleEntity(addic7ed, i16, str21, i14, i15, str33, strFixUrl, z3);
                    } else {
                        i16 = i13;
                        str22 = str2;
                        objSearch$newSubtitleEntity = str20;
                    }
                    if (objSearch$newSubtitleEntity != null) {
                        arrayList.add(objSearch$newSubtitleEntity);
                    }
                    i13 = i16;
                    i14 = i14;
                    str2 = str22;
                }
                return arrayList;
            }
            if (i17 != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i24 = anonymousClass1.I$2;
            int i25 = anonymousClass1.I$1;
            i11 = anonymousClass1.I$0;
            str18 = (String) anonymousClass1.L$4;
            Object obj9 = anonymousClass1.L$3;
            String str35 = (String) anonymousClass1.L$2;
            ResultKt.throwOnFailure(obj5);
            i10 = i25;
            str15 = "a[href~=updated\\/|original\\/]";
            str17 = str35;
            str16 = "href";
            str26 = "text(...)";
            i12 = i24;
            obj4 = obj5;
            c = ')';
            document = ((NiceResponse) obj4).getDocument();
            Elements<Element> elementsSelect2 = document.select(".tabel95 .tabel95 tr:has(.language):contains(" + str18 + c);
            str23 = "select(...)";
            Intrinsics.checkNotNullExpressionValue(elementsSelect2, str23);
            arrayList2 = new ArrayList();
            for (Element element2 : elementsSelect2) {
                StringBuilder sb = new StringBuilder();
                elementSelectFirst2 = document.selectFirst("span.titulo");
                if (elementSelectFirst2 != null || (strText2 = elementSelectFirst2.text()) == null) {
                    str24 = null;
                    strSubstringBefore$default = null;
                } else {
                    str24 = null;
                    strSubstringBefore$default = StringsKt.substringBefore$default(strText2, " Subtitle", (String) null, 2, (Object) null);
                }
                sb.append(strSubstringBefore$default);
                sb.append('\n');
                Element elementParent = element2.parent();
                Intrinsics.checkNotNull(elementParent);
                String strText4 = elementParent.select(".NewsTitle").text();
                String str36 = str26;
                Intrinsics.checkNotNullExpressionValue(strText4, str36);
                sb.append(StringsKt.substringBefore$default(StringsKt.substringAfter$default(strText4, "Version ", str24, 2, str24), ", Duration", str24, 2, str24));
                String string2 = sb.toString();
                elementSelectFirst3 = element2.selectFirst(str15);
                str25 = str16;
                if (elementSelectFirst3 != null) {
                    strAttr3 = elementSelectFirst3.attr(str25);
                    addic7ed2 = this;
                    if (strAttr3 != null) {
                        strFixUrl2 = addic7ed2.fixUrl(strAttr3);
                    }
                    Element elementParent2 = element2.parent();
                    Intrinsics.checkNotNull(elementParent2);
                    Elements elementsSelect3 = elementParent2.select("tr:last-child [title=\"Hearing Impaired\"]");
                    Intrinsics.checkNotNullExpressionValue(elementsSelect3, str23);
                    boolean z4 = !elementsSelect3.isEmpty();
                    String str37 = str15;
                    String str38 = str23;
                    int i26 = i11;
                    int i27 = i10;
                    subtitleEntitySearch$newSubtitleEntity = search$newSubtitleEntity(addic7ed2, i27, str17, i26, i12, string2, strFixUrl2, z4);
                    if (subtitleEntitySearch$newSubtitleEntity != null) {
                        arrayList2.add(subtitleEntitySearch$newSubtitleEntity);
                    }
                    i10 = i27;
                    i11 = i26;
                    str26 = str36;
                    str16 = str25;
                    str15 = str37;
                    str23 = str38;
                } else {
                    addic7ed2 = this;
                }
                strFixUrl2 = str24;
                Element elementParent3 = element2.parent();
                Intrinsics.checkNotNull(elementParent3);
                Elements elementsSelect4 = elementParent3.select("tr:last-child [title=\"Hearing Impaired\"]");
                Intrinsics.checkNotNullExpressionValue(elementsSelect4, str23);
                boolean z5 = !elementsSelect4.isEmpty();
                String str39 = str15;
                String str310 = str23;
                int i28 = i11;
                int i29 = i10;
                subtitleEntitySearch$newSubtitleEntity = search$newSubtitleEntity(addic7ed2, i29, str17, i28, i12, string2, strFixUrl2, z5);
                if (subtitleEntitySearch$newSubtitleEntity != null) {
                    arrayList2.add(subtitleEntitySearch$newSubtitleEntity);
                }
                i10 = i29;
                i11 = i28;
                str26 = str36;
                str16 = str25;
                str15 = str39;
                str23 = str310;
            }
            return arrayList2;
        }
        int i30 = anonymousClass1.I$2;
        int i31 = anonymousClass1.I$1;
        int i32 = anonymousClass1.I$0;
        String str40 = (String) anonymousClass1.L$7;
        str7 = (String) anonymousClass1.L$6;
        str6 = (String) anonymousClass1.L$5;
        String str41 = (String) anonymousClass1.L$4;
        obj2 = anonymousClass1.L$3;
        obj3 = obj5;
        String str42 = (String) anonymousClass1.L$2;
        subtitleSearch = (AbstractSubtitleEntities.SubtitleSearch) anonymousClass1.L$1;
        AuthData authData3 = (AuthData) anonymousClass1.L$0;
        ResultKt.throwOnFailure(obj3);
        str3 = "a[href~=updated\\/|original\\/]";
        str = str40;
        str2 = "href";
        str5 = str41;
        z2 = false;
        z = true;
        str4 = str42;
        i2 = i32;
        i3 = i31;
        i4 = i30;
        anonymousClass2 = anonymousClass1;
        obj = coroutine_suspended;
        authData2 = authData3;
        i = 2;
        AbstractSubtitleEntities.SubtitleSearch subtitleSearch2 = subtitleSearch;
        NiceResponse niceResponse = (NiceResponse) obj3;
        Document document2 = niceResponse.getDocument();
        AuthData authData4 = authData2;
        if (!StringsKt.contains$default(niceResponse.getUrl(), "/movie/", z2, i, (Object) null)) {
            str8 = str7;
            if (!StringsKt.contains$default(niceResponse.getUrl(), "/serie/", z2, i, (Object) null)) {
                if (StringsKt.contains$default(niceResponse.getUrl(), "/show/", z2, i, (Object) null)) {
                    String strSubstringAfterLast$default = StringsKt.substringAfterLast$default(niceResponse.getUrl(), "/", (String) null, i, (Object) null);
                    anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(authData4);
                    anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(subtitleSearch2);
                    anonymousClass2.L$2 = str4;
                    anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(obj2);
                    anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(str5);
                    anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(str6);
                    anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(str8);
                    anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(str);
                    anonymousClass2.L$8 = SpillingKt.nullOutSpilledVariable(niceResponse);
                    anonymousClass2.L$9 = SpillingKt.nullOutSpilledVariable(document2);
                    anonymousClass2.L$10 = SpillingKt.nullOutSpilledVariable(strSubstringAfterLast$default);
                    anonymousClass2.I$0 = i2;
                    anonymousClass2.I$1 = i3;
                    anonymousClass2.I$2 = i4;
                    anonymousClass2.label = i;
                    str20 = null;
                    int i33 = i4;
                    String str43 = str4;
                    int i34 = i2;
                    i13 = i3;
                    obj5 = Requests.get$default(MainActivityKt.getApp(), "https://www.addic7ed.com/ajax_loadShow.php?show=" + strSubstringAfterLast$default + "&season=" + i3 + "&langs=|" + obj2 + "|&hd=0&hi=0", null, "https://www.addic7ed.com/", null, null, false, 0, null, 0L, null, false, null, anonymousClass2, 4090, null);
                    if (obj5 != obj) {
                        str21 = str43;
                        i14 = i34;
                        i15 = i33;
                        Elements<Element> elementsSelect5 = ((NiceResponse) obj5).getDocument().select("#season tbody tr");
                        Intrinsics.checkNotNullExpressionValue(elementsSelect5, str32);
                        arrayList = new ArrayList();
                        while (r9.hasNext()) {
                            String strText5 = element.select("td:eq(1)").text();
                            str26 = str26;
                            Intrinsics.checkNotNullExpressionValue(strText5, str26);
                            intOrNull = StringsKt.toIntOrNull(strText5);
                            if (intOrNull == null) {
                                i16 = i13;
                                str22 = str2;
                                objSearch$newSubtitleEntity = str20;
                            } else {
                                String str311 = element.select("td:eq(2)").text() + '\n' + element.select("td:eq(4)").text();
                                String str312 = str3;
                                elementSelectFirst = element.selectFirst(str312);
                                str22 = str2;
                                if (elementSelectFirst != null) {
                                    strAttr2 = elementSelectFirst.attr(str22);
                                    addic7ed = this;
                                    if (strAttr2 != null) {
                                        strFixUrl = addic7ed.fixUrl(strAttr2);
                                    }
                                    strText = element.select("td:eq(6)").text();
                                    Intrinsics.checkNotNullExpressionValue(strText, str26);
                                    str3 = str312;
                                    if (strText.length() > 0) {
                                        z3 = z;
                                    } else {
                                        z3 = false;
                                    }
                                    i16 = i13;
                                    objSearch$newSubtitleEntity = search$newSubtitleEntity(addic7ed, i16, str21, i14, i15, str311, strFixUrl, z3);
                                } else {
                                    addic7ed = this;
                                }
                                strFixUrl = str20;
                                strText = element.select("td:eq(6)").text();
                                Intrinsics.checkNotNullExpressionValue(strText, str26);
                                str3 = str312;
                                if (strText.length() > 0) {
                                    z3 = z;
                                } else {
                                    z3 = false;
                                }
                                i16 = i13;
                                objSearch$newSubtitleEntity = search$newSubtitleEntity(addic7ed, i16, str21, i14, i15, str311, strFixUrl, z3);
                            }
                            if (objSearch$newSubtitleEntity != null) {
                                arrayList.add(objSearch$newSubtitleEntity);
                            }
                            i13 = i16;
                            i14 = i14;
                            str2 = str22;
                        }
                        return arrayList;
                    }
                } else {
                    i6 = i3;
                    i7 = i2;
                    String str44 = str4;
                    i5 = i4;
                    str9 = str44;
                    str11 = str2;
                    Elements elementsSelect6 = document2.select("table.tabel a");
                    if (i6 > 0) {
                        str10 = null;
                        str19 = "a[href~=serie\\/.+\\/" + i6 + "\\/" + i7 + "\\/\\w]";
                    } else {
                        str10 = null;
                        str19 = i5 > 0 ? "a[href~=movie\\/]:contains(" + i5 + ')' : "a[href~=movie\\/]";
                    }
                    Element elementSelectFirst4 = elementsSelect6.selectFirst(str19);
                    if (elementSelectFirst4 == null || (strAttr = elementSelectFirst4.attr(str11)) == null || (url = fixUrl(strAttr)) == null) {
                        return str10;
                    }
                }
                return obj;
            }
            String str45 = str11;
            str12 = str10;
            if (StringsKt.contains$default((CharSequence) url, (CharSequence) "/serie/", false, 2, (Object) str12)) {
                url = StringsKt.substringBeforeLast$default(url, "/", str12, 2, str12) + '/' + obj2;
            }
            Requests app2 = MainActivityKt.getApp();
            anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(authData4);
            anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(subtitleSearch2);
            anonymousClass2.L$2 = str9;
            anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(obj2);
            anonymousClass2.L$4 = str5;
            anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(str6);
            anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(str8);
            anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(url);
            anonymousClass2.L$8 = SpillingKt.nullOutSpilledVariable(niceResponse);
            anonymousClass2.L$9 = SpillingKt.nullOutSpilledVariable(document2);
            anonymousClass2.I$0 = i7;
            anonymousClass2.I$1 = i6;
            anonymousClass2.I$2 = i5;
            anonymousClass2.label = 3;
            str13 = str9;
            i8 = i7;
            i9 = i5;
            str14 = str5;
            i10 = i6;
            AnonymousClass1 anonymousClass4 = anonymousClass2;
            str15 = str3;
            str16 = str45;
            String str46 = url;
            c = ')';
            obj4 = Requests.get$default(app2, str46, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass4, 4094, null);
            if (obj4 != obj) {
                str17 = str13;
                str18 = str14;
                i11 = i8;
                i12 = i9;
                document = ((NiceResponse) obj4).getDocument();
                Elements<Element> elementsSelect7 = document.select(".tabel95 .tabel95 tr:has(.language):contains(" + str18 + c);
                str23 = "select(...)";
                Intrinsics.checkNotNullExpressionValue(elementsSelect7, str23);
                arrayList2 = new ArrayList();
                while (r11.hasNext()) {
                    StringBuilder sb2 = new StringBuilder();
                    elementSelectFirst2 = document.selectFirst("span.titulo");
                    if (elementSelectFirst2 != null) {
                        str24 = null;
                        strSubstringBefore$default = null;
                    } else {
                        str24 = null;
                        strSubstringBefore$default = null;
                    }
                    sb2.append(strSubstringBefore$default);
                    sb2.append('\n');
                    Element elementParent4 = element2.parent();
                    Intrinsics.checkNotNull(elementParent4);
                    String strText6 = elementParent4.select(".NewsTitle").text();
                    String str313 = str26;
                    Intrinsics.checkNotNullExpressionValue(strText6, str313);
                    sb2.append(StringsKt.substringBefore$default(StringsKt.substringAfter$default(strText6, "Version ", str24, 2, str24), ", Duration", str24, 2, str24));
                    String string3 = sb2.toString();
                    elementSelectFirst3 = element2.selectFirst(str15);
                    str25 = str16;
                    if (elementSelectFirst3 != null) {
                        strAttr3 = elementSelectFirst3.attr(str25);
                        addic7ed2 = this;
                        if (strAttr3 != null) {
                            strFixUrl2 = addic7ed2.fixUrl(strAttr3);
                        }
                        Element elementParent5 = element2.parent();
                        Intrinsics.checkNotNull(elementParent5);
                        Elements elementsSelect8 = elementParent5.select("tr:last-child [title=\"Hearing Impaired\"]");
                        Intrinsics.checkNotNullExpressionValue(elementsSelect8, str23);
                        boolean z6 = !elementsSelect8.isEmpty();
                        String str314 = str15;
                        String str315 = str23;
                        int i210 = i11;
                        int i211 = i10;
                        subtitleEntitySearch$newSubtitleEntity = search$newSubtitleEntity(addic7ed2, i211, str17, i210, i12, string3, strFixUrl2, z6);
                        if (subtitleEntitySearch$newSubtitleEntity != null) {
                            arrayList2.add(subtitleEntitySearch$newSubtitleEntity);
                        }
                        i10 = i211;
                        i11 = i210;
                        str26 = str313;
                        str16 = str25;
                        str15 = str314;
                        str23 = str315;
                    } else {
                        addic7ed2 = this;
                    }
                    strFixUrl2 = str24;
                    Element elementParent6 = element2.parent();
                    Intrinsics.checkNotNull(elementParent6);
                    Elements elementsSelect9 = elementParent6.select("tr:last-child [title=\"Hearing Impaired\"]");
                    Intrinsics.checkNotNullExpressionValue(elementsSelect9, str23);
                    boolean z7 = !elementsSelect9.isEmpty();
                    String str316 = str15;
                    String str317 = str23;
                    int i212 = i11;
                    int i213 = i10;
                    subtitleEntitySearch$newSubtitleEntity = search$newSubtitleEntity(addic7ed2, i213, str17, i212, i12, string3, strFixUrl2, z7);
                    if (subtitleEntitySearch$newSubtitleEntity != null) {
                        arrayList2.add(subtitleEntitySearch$newSubtitleEntity);
                    }
                    i10 = i213;
                    i11 = i212;
                    str26 = str313;
                    str16 = str25;
                    str15 = str316;
                    str23 = str317;
                }
                return arrayList2;
            }
            return obj;
        }
        str8 = str7;
        String str47 = str4;
        i5 = i4;
        str9 = str47;
        str10 = null;
        i6 = i3;
        i7 = i2;
        str11 = str2;
        url = niceResponse.getUrl();
        String str48 = str11;
        str12 = str10;
        if (StringsKt.contains$default((CharSequence) url, (CharSequence) "/serie/", false, 2, (Object) str12)) {
            url = StringsKt.substringBeforeLast$default(url, "/", str12, 2, str12) + '/' + obj2;
        }
        Requests app3 = MainActivityKt.getApp();
        anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(authData4);
        anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(subtitleSearch2);
        anonymousClass2.L$2 = str9;
        anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(obj2);
        anonymousClass2.L$4 = str5;
        anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(str6);
        anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(str8);
        anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(url);
        anonymousClass2.L$8 = SpillingKt.nullOutSpilledVariable(niceResponse);
        anonymousClass2.L$9 = SpillingKt.nullOutSpilledVariable(document2);
        anonymousClass2.I$0 = i7;
        anonymousClass2.I$1 = i6;
        anonymousClass2.I$2 = i5;
        anonymousClass2.label = 3;
        str13 = str9;
        i8 = i7;
        i9 = i5;
        str14 = str5;
        i10 = i6;
        AnonymousClass1 anonymousClass5 = anonymousClass2;
        str15 = str3;
        str16 = str48;
        String str49 = url;
        c = ')';
        obj4 = Requests.get$default(app3, str49, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass5, 4094, null);
        if (obj4 != obj) {
            str17 = str13;
            str18 = str14;
            i11 = i8;
            i12 = i9;
            document = ((NiceResponse) obj4).getDocument();
            Elements<Element> elementsSelect10 = document.select(".tabel95 .tabel95 tr:has(.language):contains(" + str18 + c);
            str23 = "select(...)";
            Intrinsics.checkNotNullExpressionValue(elementsSelect10, str23);
            arrayList2 = new ArrayList();
            while (r11.hasNext()) {
                StringBuilder sb3 = new StringBuilder();
                elementSelectFirst2 = document.selectFirst("span.titulo");
                if (elementSelectFirst2 != null) {
                    str24 = null;
                    strSubstringBefore$default = null;
                } else {
                    str24 = null;
                    strSubstringBefore$default = null;
                }
                sb3.append(strSubstringBefore$default);
                sb3.append('\n');
                Element elementParent7 = element2.parent();
                Intrinsics.checkNotNull(elementParent7);
                String strText7 = elementParent7.select(".NewsTitle").text();
                String str318 = str26;
                Intrinsics.checkNotNullExpressionValue(strText7, str318);
                sb3.append(StringsKt.substringBefore$default(StringsKt.substringAfter$default(strText7, "Version ", str24, 2, str24), ", Duration", str24, 2, str24));
                String string4 = sb3.toString();
                elementSelectFirst3 = element2.selectFirst(str15);
                str25 = str16;
                if (elementSelectFirst3 != null) {
                    strAttr3 = elementSelectFirst3.attr(str25);
                    addic7ed2 = this;
                    if (strAttr3 != null) {
                        strFixUrl2 = addic7ed2.fixUrl(strAttr3);
                    }
                    Element elementParent8 = element2.parent();
                    Intrinsics.checkNotNull(elementParent8);
                    Elements elementsSelect11 = elementParent8.select("tr:last-child [title=\"Hearing Impaired\"]");
                    Intrinsics.checkNotNullExpressionValue(elementsSelect11, str23);
                    boolean z8 = !elementsSelect11.isEmpty();
                    String str319 = str15;
                    String str3110 = str23;
                    int i214 = i11;
                    int i215 = i10;
                    subtitleEntitySearch$newSubtitleEntity = search$newSubtitleEntity(addic7ed2, i215, str17, i214, i12, string4, strFixUrl2, z8);
                    if (subtitleEntitySearch$newSubtitleEntity != null) {
                        arrayList2.add(subtitleEntitySearch$newSubtitleEntity);
                    }
                    i10 = i215;
                    i11 = i214;
                    str26 = str318;
                    str16 = str25;
                    str15 = str319;
                    str23 = str3110;
                } else {
                    addic7ed2 = this;
                }
                strFixUrl2 = str24;
                Element elementParent9 = element2.parent();
                Intrinsics.checkNotNull(elementParent9);
                Elements elementsSelect12 = elementParent9.select("tr:last-child [title=\"Hearing Impaired\"]");
                Intrinsics.checkNotNullExpressionValue(elementsSelect12, str23);
                boolean z9 = !elementsSelect12.isEmpty();
                String str3111 = str15;
                String str3112 = str23;
                int i216 = i11;
                int i217 = i10;
                subtitleEntitySearch$newSubtitleEntity = search$newSubtitleEntity(addic7ed2, i217, str17, i216, i12, string4, strFixUrl2, z9);
                if (subtitleEntitySearch$newSubtitleEntity != null) {
                    arrayList2.add(subtitleEntitySearch$newSubtitleEntity);
                }
                i10 = i217;
                i11 = i216;
                str26 = str318;
                str16 = str25;
                str15 = str3111;
                str23 = str3112;
            }
            return arrayList2;
        }
        return obj;
    }

    private static final AbstractSubtitleEntities.SubtitleEntity search$newSubtitleEntity(Addic7ed addic7ed, int i, String str, int i2, int i3, String str2, String str3, boolean z) {
        String str4;
        String str5 = str2;
        if (str5 == null || StringsKt.isBlank(str5) || (str4 = str3) == null || StringsKt.isBlank(str4)) {
            return null;
        }
        return new AbstractSubtitleEntities.SubtitleEntity(addic7ed.getIdPrefix(), str2, str, str3, i > 0 ? TvType.TvSeries : TvType.Movie, addic7ed.getName(), Integer.valueOf(i2), Integer.valueOf(i), Integer.valueOf(i3), z, MapsKt.mapOf(TuplesKt.to("referer", "https://www.addic7ed.com/")));
    }

    @Override // com.lagradost.cloudstream3.syncproviders.SubtitleAPI
    public Object load(AuthData authData, AbstractSubtitleEntities.SubtitleEntity subtitleEntity, Continuation<? super String> continuation) {
        return subtitleEntity.getData();
    }
}
