package me.xdrop.fuzzywuzzy.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import me.xdrop.fuzzywuzzy.Ratio;
import me.xdrop.fuzzywuzzy.ToStringFunction;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: loaded from: classes5.dex */
public class TokenSet extends RatioAlgorithm {
    @Override // me.xdrop.fuzzywuzzy.algorithms.RatioAlgorithm
    public int apply(String str, String str2, Ratio ratio, ToStringFunction<String> toStringFunction) {
        String strApply = toStringFunction.apply(str);
        String strApply2 = toStringFunction.apply(str2);
        Set<String> set = Utils.tokenizeSet(strApply);
        Set<String> set2 = Utils.tokenizeSet(strApply2);
        Set setIntersection = SetUtils.intersection(set, set2);
        Set setDifference = SetUtils.difference(set, set2);
        Set setDifference2 = SetUtils.difference(set2, set);
        String strTrim = Utils.sortAndJoin((Set<String>) setIntersection, Stream.ID_UNKNOWN).trim();
        String strTrim2 = (strTrim + Stream.ID_UNKNOWN + Utils.sortAndJoin((Set<String>) setDifference, Stream.ID_UNKNOWN)).trim();
        String strTrim3 = (strTrim + Stream.ID_UNKNOWN + Utils.sortAndJoin((Set<String>) setDifference2, Stream.ID_UNKNOWN)).trim();
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(ratio.apply(strTrim, strTrim2)));
        arrayList.add(Integer.valueOf(ratio.apply(strTrim, strTrim3)));
        arrayList.add(Integer.valueOf(ratio.apply(strTrim2, strTrim3)));
        return ((Integer) Collections.max(arrayList)).intValue();
    }
}
