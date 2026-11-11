package me.xdrop.fuzzywuzzy;

/* JADX INFO: loaded from: classes5.dex */
@Deprecated
public abstract class StringProcessor implements ToStringFunction<String> {
    @Deprecated
    public abstract String process(String str);

    @Override // me.xdrop.fuzzywuzzy.ToStringFunction
    public String apply(String str) {
        return process(str);
    }
}
