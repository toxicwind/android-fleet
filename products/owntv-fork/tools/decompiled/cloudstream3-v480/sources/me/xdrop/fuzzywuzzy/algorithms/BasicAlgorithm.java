package me.xdrop.fuzzywuzzy.algorithms;

import me.xdrop.fuzzywuzzy.Applicable;
import me.xdrop.fuzzywuzzy.ToStringFunction;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BasicAlgorithm implements Applicable {
    private ToStringFunction<String> stringFunction;

    public abstract int apply(String str, String str2, ToStringFunction<String> toStringFunction);

    public BasicAlgorithm() {
        this.stringFunction = new DefaultStringFunction();
    }

    public BasicAlgorithm(ToStringFunction<String> toStringFunction) {
        this.stringFunction = toStringFunction;
    }

    @Override // me.xdrop.fuzzywuzzy.Applicable
    public int apply(String str, String str2) {
        return apply(str, str2, this.stringFunction);
    }

    public BasicAlgorithm with(ToStringFunction<String> toStringFunction) {
        setStringFunction(toStringFunction);
        return this;
    }

    public BasicAlgorithm noProcessor() {
        this.stringFunction = ToStringFunction.NO_PROCESS;
        return this;
    }

    void setStringFunction(ToStringFunction<String> toStringFunction) {
        this.stringFunction = toStringFunction;
    }

    public ToStringFunction<String> getStringFunction() {
        return this.stringFunction;
    }
}
