package com.lagradost.cloudstream3.utils;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Levenshtein.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J,\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\nJ,\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\nJ\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0002¨\u0006\u000e"}, d2 = {"Lcom/lagradost/cloudstream3/utils/Levenshtein;", "", "<init>", "()V", "ratio", "", "s1", "", "s2", "processor", "Lkotlin/Function1;", "partialRatio", "basicRatio", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Levenshtein {
    public static final Levenshtein INSTANCE = new Levenshtein();

    static final String partialRatio$lambda$0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it;
    }

    static final String ratio$lambda$0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it;
    }

    private Levenshtein() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ int ratio$default(Levenshtein levenshtein, String str, String str2, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            function1 = new Function1() { // from class: com.lagradost.cloudstream3.utils.Levenshtein$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return Levenshtein.ratio$lambda$0((String) obj2);
                }
            };
        }
        return levenshtein.ratio(str, str2, function1);
    }

    public final int ratio(String s1, String s2, Function1<? super String, String> processor) {
        Intrinsics.checkNotNullParameter(s1, "s1");
        Intrinsics.checkNotNullParameter(s2, "s2");
        Intrinsics.checkNotNullParameter(processor, "processor");
        return (int) Math.rint(100.0d * basicRatio(processor.invoke(s1), processor.invoke(s2)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ int partialRatio$default(Levenshtein levenshtein, String str, String str2, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            function1 = new Function1() { // from class: com.lagradost.cloudstream3.utils.Levenshtein$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return Levenshtein.partialRatio$lambda$0((String) obj2);
                }
            };
        }
        return levenshtein.partialRatio(str, str2, function1);
    }

    public final int partialRatio(String s1, String s2, Function1<? super String, String> processor) {
        Intrinsics.checkNotNullParameter(s1, "s1");
        Intrinsics.checkNotNullParameter(s2, "s2");
        Intrinsics.checkNotNullParameter(processor, "processor");
        String strInvoke = processor.invoke(s1);
        String strInvoke2 = processor.invoke(s2);
        if (strInvoke.length() >= strInvoke2.length()) {
            strInvoke2 = strInvoke;
            strInvoke = strInvoke2;
        }
        if (strInvoke.length() == 0) {
            return strInvoke2.length() == 0 ? 100 : 0;
        }
        MatchingBlock[] matchingBlocks = LevenshteinKt.getMatchingBlocks(strInvoke.length(), strInvoke2.length(), LevenshteinKt.getEditOps(strInvoke, strInvoke2));
        ArrayList arrayList = new ArrayList();
        for (MatchingBlock matchingBlock : matchingBlocks) {
            int dpos = matchingBlock.getDpos() - matchingBlock.getSpos();
            if (dpos <= 0) {
                dpos = 0;
            }
            int length = strInvoke.length() + dpos;
            if (length > strInvoke2.length()) {
                length = strInvoke2.length();
            }
            String strSubstring = strInvoke2.substring(dpos, length);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            double dBasicRatio = basicRatio(strInvoke, strSubstring);
            if (dBasicRatio > 0.995d) {
                return 100;
            }
            arrayList.add(Double.valueOf(dBasicRatio));
        }
        Double dMaxOrNull = CollectionsKt.maxOrNull((Iterable<? extends Double>) arrayList);
        return (int) Math.rint(100.0d * (dMaxOrNull != null ? dMaxOrNull.doubleValue() : 0.0d));
    }

    private final double basicRatio(String s1, String s2) {
        int length = s1.length() + s2.length();
        if (length == 0) {
            return 1.0d;
        }
        return ((double) (length - LevenshteinKt.levEditDistance(s1, s2, 1))) / ((double) length);
    }
}
