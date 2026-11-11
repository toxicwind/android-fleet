package org.schabi.newpipe.extractor.timeago;

import j$.time.temporal.ChronoUnit;
import j$.util.function.Function$CC;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

/* JADX INFO: loaded from: classes8.dex */
public abstract class PatternsHolder {
    private final Collection<String> days;
    private final Collection<String> hours;
    private final Collection<String> minutes;
    private final Collection<String> months;
    private final Collection<String> seconds;
    private final Map<ChronoUnit, Map<String, Integer>> specialCases;
    private final Collection<String> weeks;
    private final String wordSeparator;
    private final Collection<String> years;

    protected PatternsHolder(String str, Collection<String> collection, Collection<String> collection2, Collection<String> collection3, Collection<String> collection4, Collection<String> collection5, Collection<String> collection6, Collection<String> collection7) {
        this.specialCases = new EnumMap(ChronoUnit.class);
        this.wordSeparator = str;
        this.seconds = collection;
        this.minutes = collection2;
        this.hours = collection3;
        this.days = collection4;
        this.weeks = collection5;
        this.months = collection6;
        this.years = collection7;
    }

    protected PatternsHolder(String str, String[] strArr, String[] strArr2, String[] strArr3, String[] strArr4, String[] strArr5, String[] strArr6, String[] strArr7) {
        this(str, Arrays.asList(strArr), Arrays.asList(strArr2), Arrays.asList(strArr3), Arrays.asList(strArr4), Arrays.asList(strArr5), Arrays.asList(strArr6), Arrays.asList(strArr7));
    }

    public String wordSeparator() {
        return this.wordSeparator;
    }

    public Collection<String> seconds() {
        return this.seconds;
    }

    public Collection<String> minutes() {
        return this.minutes;
    }

    public Collection<String> hours() {
        return this.hours;
    }

    public Collection<String> days() {
        return this.days;
    }

    public Collection<String> weeks() {
        return this.weeks;
    }

    public Collection<String> months() {
        return this.months;
    }

    public Collection<String> years() {
        return this.years;
    }

    public Map<ChronoUnit, Map<String, Integer>> specialCases() {
        return this.specialCases;
    }

    static /* synthetic */ Map lambda$putSpecialCase$0(ChronoUnit chronoUnit) {
        return new LinkedHashMap();
    }

    protected void putSpecialCase(ChronoUnit chronoUnit, String str, int i) {
        ((Map) j$.util.Map.EL.computeIfAbsent(this.specialCases, chronoUnit, new Function() { // from class: org.schabi.newpipe.extractor.timeago.PatternsHolder$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return PatternsHolder.lambda$putSpecialCase$0((ChronoUnit) obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        })).put(str, Integer.valueOf(i));
    }

    public Map<ChronoUnit, Collection<String>> asMap() {
        EnumMap enumMap = new EnumMap(ChronoUnit.class);
        enumMap.put(ChronoUnit.SECONDS, seconds());
        enumMap.put(ChronoUnit.MINUTES, minutes());
        enumMap.put(ChronoUnit.HOURS, hours());
        enumMap.put(ChronoUnit.DAYS, days());
        enumMap.put(ChronoUnit.WEEKS, weeks());
        enumMap.put(ChronoUnit.MONTHS, months());
        enumMap.put(ChronoUnit.YEARS, years());
        return enumMap;
    }
}
