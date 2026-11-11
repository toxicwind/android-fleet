package me.xdrop.fuzzywuzzy.algorithms;

import java.util.Arrays;
import java.util.List;
import me.xdrop.fuzzywuzzy.Ratio;
import me.xdrop.fuzzywuzzy.ToStringFunction;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: loaded from: classes5.dex */
public class TokenSort extends RatioAlgorithm {
    @Override // me.xdrop.fuzzywuzzy.algorithms.RatioAlgorithm
    public int apply(String str, String str2, Ratio ratio, ToStringFunction<String> toStringFunction) {
        return ratio.apply(processAndSort(str, toStringFunction), processAndSort(str2, toStringFunction));
    }

    private static String processAndSort(String str, ToStringFunction<String> toStringFunction) {
        return Utils.sortAndJoin((List<String>) Arrays.asList(toStringFunction.apply(str).split("\\s+")), Stream.ID_UNKNOWN).trim();
    }
}
