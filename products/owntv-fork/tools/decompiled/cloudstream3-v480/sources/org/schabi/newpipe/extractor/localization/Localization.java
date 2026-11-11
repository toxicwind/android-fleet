package org.schabi.newpipe.extractor.localization;

import j$.util.DesugarCollections;
import j$.util.Objects;
import j$.util.Optional;
import j$.util.function.Function$CC;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.utils.LocaleCompat;

/* JADX INFO: loaded from: classes8.dex */
public class Localization implements Serializable {
    public static final Localization DEFAULT = new Localization("en", "GB");

    @Nullable
    private final String countryCode;

    @Nonnull
    private final String languageCode;

    @Nonnull
    public static List<Localization> listFrom(String... strArr) {
        ArrayList arrayList = new ArrayList();
        for (final String str : strArr) {
            arrayList.add(fromLocalizationCode(str).orElseThrow(new Supplier() { // from class: org.schabi.newpipe.extractor.localization.Localization$$ExternalSyntheticLambda1
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Localization.lambda$listFrom$0(str);
                }
            }));
        }
        return DesugarCollections.unmodifiableList(arrayList);
    }

    static /* synthetic */ IllegalArgumentException lambda$listFrom$0(String str) {
        return new IllegalArgumentException("Not a localization code: " + str);
    }

    @Nonnull
    public static Optional<Localization> fromLocalizationCode(String str) {
        return LocaleCompat.forLanguageTag(str).map(new Function() { // from class: org.schabi.newpipe.extractor.localization.Localization$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Localization.fromLocale((Locale) obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        });
    }

    public Localization(@Nonnull String str, @Nullable String str2) {
        this.languageCode = str;
        this.countryCode = str2;
    }

    public Localization(@Nonnull String str) {
        this(str, null);
    }

    @Nonnull
    public String getLanguageCode() {
        return this.languageCode;
    }

    @Nonnull
    public String getCountryCode() {
        String str = this.countryCode;
        return str == null ? "" : str;
    }

    public static Localization fromLocale(@Nonnull Locale locale) {
        return new Localization(locale.getLanguage(), locale.getCountry());
    }

    public String getLocalizationCode() {
        String str;
        String str2 = this.languageCode;
        String str3 = this.countryCode;
        if (str3 == null) {
            str = "";
        } else {
            str = "-" + str3;
        }
        return str2 + str;
    }

    public String toString() {
        return "Localization[" + getLocalizationCode() + "]";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Localization)) {
            return false;
        }
        Localization localization = (Localization) obj;
        return this.languageCode.equals(localization.languageCode) && Objects.equals(this.countryCode, localization.countryCode);
    }

    public int hashCode() {
        return (this.languageCode.hashCode() * 31) + Objects.hashCode(this.countryCode);
    }

    public static Locale getLocaleFromThreeLetterCode(@Nonnull String str) throws ParsingException {
        String[] iSOLanguages = Locale.getISOLanguages();
        HashMap map = new HashMap(iSOLanguages.length);
        for (String str2 : iSOLanguages) {
            Locale locale = new Locale(str2);
            map.put(locale.getISO3Language(), locale);
        }
        if (map.containsKey(str)) {
            return (Locale) map.get(str);
        }
        throw new ParsingException("Could not get Locale from this three letter language code" + str);
    }
}
