package org.schabi.newpipe.extractor.utils;

import j$.util.Optional;
import java.util.Locale;

/* JADX INFO: loaded from: classes8.dex */
public final class LocaleCompat {
    private LocaleCompat() {
    }

    public static Optional<Locale> forLanguageTag(String str) {
        if (str.contains("-")) {
            String[] strArrSplit = str.split("-", -1);
            if (strArrSplit.length > 2) {
                return Optional.of(new Locale(strArrSplit[0], strArrSplit[1], strArrSplit[2]));
            }
            if (strArrSplit.length > 1) {
                return Optional.of(new Locale(strArrSplit[0], strArrSplit[1]));
            }
            if (strArrSplit.length == 1) {
                return Optional.of(new Locale(strArrSplit[0]));
            }
        } else if (str.contains("_")) {
            String[] strArrSplit2 = str.split("_", -1);
            if (strArrSplit2.length > 2) {
                return Optional.of(new Locale(strArrSplit2[0], strArrSplit2[1], strArrSplit2[2]));
            }
            if (strArrSplit2.length > 1) {
                return Optional.of(new Locale(strArrSplit2[0], strArrSplit2[1]));
            }
            if (strArrSplit2.length == 1) {
                return Optional.of(new Locale(strArrSplit2[0]));
            }
        } else {
            return Optional.of(new Locale(str));
        }
        return Optional.empty();
    }
}
