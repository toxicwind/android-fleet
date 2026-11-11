package me.xdrop.fuzzywuzzy;

/* JADX INFO: loaded from: classes5.dex */
public interface Ratio extends Applicable {
    @Override // me.xdrop.fuzzywuzzy.Applicable
    int apply(String str, String str2);

    int apply(String str, String str2, ToStringFunction<String> toStringFunction);
}
