package me.xdrop.fuzzywuzzy.algorithms;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.ToStringFunction;

/* JADX INFO: loaded from: classes5.dex */
public class WeightedRatio extends BasicAlgorithm {
    public static final double PARTIAL_SCALE = 0.9d;
    public static final boolean TRY_PARTIALS = true;
    public static final double UNBASE_SCALE = 0.95d;

    @Override // me.xdrop.fuzzywuzzy.algorithms.BasicAlgorithm
    public int apply(String str, String str2, ToStringFunction<String> toStringFunction) {
        String strApply = toStringFunction.apply(str);
        String strApply2 = toStringFunction.apply(str2);
        int length = strApply.length();
        int length2 = strApply2.length();
        if (length == 0 || length2 == 0) {
            return 0;
        }
        int iRatio = FuzzySearch.ratio(strApply, strApply2);
        double dMax = ((double) Math.max(length, length2)) / ((double) Math.min(length, length2));
        boolean z = dMax >= 1.5d;
        double d = dMax > 8.0d ? 0.6d : 0.9d;
        return (int) (z ? Math.round(PrimitiveUtils.max(iRatio, ((double) FuzzySearch.partialRatio(strApply, strApply2)) * d, ((double) FuzzySearch.tokenSortPartialRatio(strApply, strApply2)) * 0.95d * d, ((double) FuzzySearch.tokenSetPartialRatio(strApply, strApply2)) * 0.95d * d)) : Math.round(PrimitiveUtils.max(iRatio, ((double) FuzzySearch.tokenSortRatio(strApply, strApply2)) * 0.95d, ((double) FuzzySearch.tokenSetRatio(strApply, strApply2)) * 0.95d)));
    }
}
