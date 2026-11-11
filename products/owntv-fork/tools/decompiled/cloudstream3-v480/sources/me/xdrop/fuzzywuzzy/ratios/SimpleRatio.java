package me.xdrop.fuzzywuzzy.ratios;

import me.xdrop.diffutils.DiffUtils;
import me.xdrop.fuzzywuzzy.Ratio;
import me.xdrop.fuzzywuzzy.ToStringFunction;

/* JADX INFO: loaded from: classes5.dex */
public class SimpleRatio implements Ratio {
    @Override // me.xdrop.fuzzywuzzy.Ratio, me.xdrop.fuzzywuzzy.Applicable
    public int apply(String str, String str2) {
        return (int) Math.round(DiffUtils.getRatio(str, str2) * 100.0d);
    }

    @Override // me.xdrop.fuzzywuzzy.Ratio
    public int apply(String str, String str2, ToStringFunction<String> toStringFunction) {
        return apply(toStringFunction.apply(str), toStringFunction.apply(str2));
    }
}
