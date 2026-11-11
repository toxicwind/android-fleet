package org.schabi.newpipe.extractor.localization;

import j$.util.DesugarCollections;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes8.dex */
public class ContentCountry implements Serializable {
    public static final ContentCountry DEFAULT = new ContentCountry(Localization.DEFAULT.getCountryCode());

    @Nonnull
    private final String countryCode;

    public static List<ContentCountry> listFrom(String... strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            arrayList.add(new ContentCountry(str));
        }
        return DesugarCollections.unmodifiableList(arrayList);
    }

    public ContentCountry(@Nonnull String str) {
        this.countryCode = str;
    }

    @Nonnull
    public String getCountryCode() {
        return this.countryCode;
    }

    public String toString() {
        return getCountryCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ContentCountry) {
            return this.countryCode.equals(((ContentCountry) obj).countryCode);
        }
        return false;
    }

    public int hashCode() {
        return this.countryCode.hashCode();
    }
}
