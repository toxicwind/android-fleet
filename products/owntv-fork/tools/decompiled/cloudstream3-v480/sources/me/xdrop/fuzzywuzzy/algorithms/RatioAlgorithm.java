package me.xdrop.fuzzywuzzy.algorithms;

import me.xdrop.fuzzywuzzy.Ratio;
import me.xdrop.fuzzywuzzy.ToStringFunction;
import me.xdrop.fuzzywuzzy.ratios.SimpleRatio;

/* JADX INFO: loaded from: classes5.dex */
public abstract class RatioAlgorithm extends BasicAlgorithm {
    private Ratio ratio;

    public abstract int apply(String str, String str2, Ratio ratio, ToStringFunction<String> toStringFunction);

    public RatioAlgorithm() {
        this.ratio = new SimpleRatio();
    }

    public RatioAlgorithm(ToStringFunction<String> toStringFunction) {
        super(toStringFunction);
    }

    public RatioAlgorithm(Ratio ratio) {
        this.ratio = ratio;
    }

    public RatioAlgorithm(ToStringFunction<String> toStringFunction, Ratio ratio) {
        super(toStringFunction);
        this.ratio = ratio;
    }

    public RatioAlgorithm with(Ratio ratio) {
        setRatio(ratio);
        return this;
    }

    public int apply(String str, String str2, Ratio ratio) {
        return apply(str, str2, ratio, getStringFunction());
    }

    @Override // me.xdrop.fuzzywuzzy.algorithms.BasicAlgorithm
    public int apply(String str, String str2, ToStringFunction<String> toStringFunction) {
        return apply(str, str2, getRatio(), toStringFunction);
    }

    public void setRatio(Ratio ratio) {
        this.ratio = ratio;
    }

    public Ratio getRatio() {
        return this.ratio;
    }
}
