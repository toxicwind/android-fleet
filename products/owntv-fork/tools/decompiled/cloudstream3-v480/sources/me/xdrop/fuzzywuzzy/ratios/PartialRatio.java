package me.xdrop.fuzzywuzzy.ratios;

import java.util.ArrayList;
import java.util.Collections;
import me.xdrop.diffutils.DiffUtils;
import me.xdrop.diffutils.structs.MatchingBlock;
import me.xdrop.fuzzywuzzy.Ratio;
import me.xdrop.fuzzywuzzy.ToStringFunction;

/* JADX INFO: loaded from: classes5.dex */
public class PartialRatio implements Ratio {
    @Override // me.xdrop.fuzzywuzzy.Ratio, me.xdrop.fuzzywuzzy.Applicable
    public int apply(String str, String str2) {
        if (str.length() > str2.length()) {
            str2 = str;
            str = str2;
        }
        MatchingBlock[] matchingBlocks = DiffUtils.getMatchingBlocks(str, str2);
        ArrayList arrayList = new ArrayList();
        for (MatchingBlock matchingBlock : matchingBlocks) {
            int i = matchingBlock.dpos - matchingBlock.spos;
            if (i <= 0) {
                i = 0;
            }
            int length = str.length() + i;
            if (length > str2.length()) {
                length = str2.length();
            }
            double ratio = DiffUtils.getRatio(str, str2.substring(i, length));
            if (ratio > 0.995d) {
                return 100;
            }
            arrayList.add(Double.valueOf(ratio));
        }
        return (int) Math.round(((Double) Collections.max(arrayList)).doubleValue() * 100.0d);
    }

    @Override // me.xdrop.fuzzywuzzy.Ratio
    public int apply(String str, String str2, ToStringFunction<String> toStringFunction) {
        return apply(toStringFunction.apply(str), toStringFunction.apply(str2));
    }
}
