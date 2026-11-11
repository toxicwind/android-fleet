package com.lagradost.cloudstream3.extractors;

import com.lagradost.cloudstream3.ErrorLoadingException;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.CharsKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: compiled from: Rabbitstream.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0096@¢\u0006\u0002\u0010\u0010J\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0012H\u0082@¢\u0006\u0002\u0010\u0014R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u000e\u0010\f\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Megacloud;", "Lcom/lagradost/cloudstream3/extractors/Rabbitstream;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "mainUrl", "getMainUrl", "embed", "getEmbed", "scriptUrl", "extractRealKey", "Lkotlin/Pair;", "sources", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getKeys", "", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Megacloud extends Rabbitstream {
    private final String name = "Megacloud";
    private final String mainUrl = "https://megacloud.tv";
    private final String embed = "embed-2/ajax/e-1";
    private final String scriptUrl = getMainUrl() + "/js/player/a/prod/e1-player.min.js";

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Megacloud$extractRealKey$1, reason: invalid class name */
    /* JADX INFO: compiled from: Rabbitstream.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Megacloud", f = "Rabbitstream.kt", i = {0}, l = {27}, m = "extractRealKey", n = {"sources"}, nl = {28}, s = {"L$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Megacloud.this.extractRealKey(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Megacloud$getKeys$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Rabbitstream.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Megacloud", f = "Rabbitstream.kt", i = {}, l = {46}, m = "getKeys", n = {}, nl = {52}, s = {}, v = 2)
    static final class C07181 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C07181(Continuation<? super C07181> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Megacloud.this.getKeys(this);
        }
    }

    @Override // com.lagradost.cloudstream3.extractors.Rabbitstream, com.lagradost.cloudstream3.utils.ExtractorApi
    public String getName() {
        return this.name;
    }

    @Override // com.lagradost.cloudstream3.extractors.Rabbitstream, com.lagradost.cloudstream3.utils.ExtractorApi
    public String getMainUrl() {
        return this.mainUrl;
    }

    @Override // com.lagradost.cloudstream3.extractors.Rabbitstream
    public String getEmbed() {
        return this.embed;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    @Override // com.lagradost.cloudstream3.extractors.Rabbitstream
    public Object extractRealKey(String str, Continuation<? super Pair<String, String>> continuation) throws ErrorLoadingException {
        AnonymousClass1 anonymousClass1;
        String str2;
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
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            anonymousClass1.L$0 = str;
            anonymousClass1.label = 1;
            Object keys = getKeys(anonymousClass1);
            if (keys == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = keys;
            str2 = str;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            str2 = (String) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj);
        }
        char[] charArray = str2.toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "toCharArray(...)");
        int iIntValue = 0;
        String str3 = "";
        for (List list : (List) obj) {
            int iIntValue2 = ((Number) list.get(0)).intValue() + iIntValue;
            int iIntValue3 = ((Number) list.get(1)).intValue() + iIntValue2;
            while (iIntValue2 < iIntValue3) {
                str3 = str3 + charArray[iIntValue2];
                charArray[iIntValue2] = ' ';
                iIntValue2++;
            }
            iIntValue += ((Number) list.get(1)).intValue();
        }
        return TuplesKt.to(str3, StringsKt.replace$default(ArraysKt.joinToString$default(charArray, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null), Stream.ID_UNKNOWN, "", false, 4, (Object) null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public final Object getKeys(Continuation<? super List<? extends List<Integer>>> continuation) throws ErrorLoadingException {
        C07181 c07181;
        List listEmptyList;
        if (continuation instanceof C07181) {
            c07181 = (C07181) continuation;
            if ((c07181.label & Integer.MIN_VALUE) != 0) {
                c07181.label -= Integer.MIN_VALUE;
            } else {
                c07181 = new C07181(continuation);
            }
        } else {
            c07181 = new C07181(continuation);
        }
        Object obj = c07181.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07181.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Requests app2 = MainActivityKt.getApp();
            String str = this.scriptUrl;
            c07181.label = 1;
            Object obj2 = Requests.get$default(app2, str, null, null, null, null, false, 0, null, 0L, null, false, null, c07181, 4094, null);
            if (obj2 == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = obj2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        String text = ((NiceResponse) obj).getText();
        List<MatchResult> list = SequencesKt.toList(Regex.findAll$default(new Regex("case\\s*0x[0-9a-f]+:(?![^;]*=partKey)\\s*\\w+\\s*=\\s*(\\w+)\\s*,\\s*\\w+\\s*=\\s*(\\w+);"), text, 0, 2, null));
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (MatchResult matchResult : list) {
            try {
                listEmptyList = CollectionsKt.listOf((Object[]) new Integer[]{Boxing.boxInt(Integer.parseInt(getKeys$matchingKey(text, matchResult.getGroupValues().get(r5)), CharsKt.checkRadix(16))), Boxing.boxInt(Integer.parseInt(getKeys$matchingKey(text, matchResult.getGroupValues().get(2)), CharsKt.checkRadix(16)))});
            } catch (NumberFormatException unused) {
                listEmptyList = CollectionsKt.emptyList();
            }
            arrayList.add(listEmptyList);
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj3 : arrayList) {
            if (!((List) obj3).isEmpty()) {
                arrayList2.add(obj3);
            }
        }
        return arrayList2;
    }

    private static final String getKeys$matchingKey(String str, String str2) throws ErrorLoadingException {
        List<String> groupValues;
        String str3;
        String strRemovePrefix;
        MatchResult matchResultFind$default = Regex.find$default(new Regex("," + str2 + "=((?:0x)?([0-9a-fA-F]+))"), str, 0, 2, null);
        if (matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null || (str3 = groupValues.get(1)) == null || (strRemovePrefix = StringsKt.removePrefix(str3, (CharSequence) "0x")) == null) {
            throw new ErrorLoadingException("Failed to match the key");
        }
        return strRemovePrefix;
    }
}
