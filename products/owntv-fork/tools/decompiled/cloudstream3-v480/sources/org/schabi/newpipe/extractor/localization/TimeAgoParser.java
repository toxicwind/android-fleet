package org.schabi.newpipe.extractor.localization;

import j$.time.LocalDateTime;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.TemporalUnit;
import j$.util.Collection;
import j$.util.function.Function$CC;
import j$.util.function.Predicate$CC;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.stream.Stream;
import org.schabi.newpipe.extractor.timeago.PatternsHolder;
import org.schabi.newpipe.extractor.utils.Parser;

/* JADX INFO: loaded from: classes8.dex */
public class TimeAgoParser {
    private final LocalDateTime now;
    private final PatternsHolder patternsHolder;

    public TimeAgoParser(PatternsHolder patternsHolder, LocalDateTime localDateTime) {
        this.patternsHolder = patternsHolder;
        this.now = localDateTime;
    }

    public DateWrapper parse(String str) throws ParsingException {
        for (Map.Entry<ChronoUnit, Map<String, Integer>> entry : this.patternsHolder.specialCases().entrySet()) {
            ChronoUnit key = entry.getKey();
            for (Map.Entry<String, Integer> entry2 : entry.getValue().entrySet()) {
                String key2 = entry2.getKey();
                int iIntValue = entry2.getValue().intValue();
                if (m10957x807d43e8(str, key2)) {
                    return getResultFor(iIntValue, key);
                }
            }
        }
        return getResultFor(parseTimeAgoAmount(str), parseChronoUnit(str));
    }

    private int parseTimeAgoAmount(String str) {
        try {
            return Integer.parseInt(str.replaceAll("\\D+", ""));
        } catch (NumberFormatException unused) {
            return 1;
        }
    }

    private ChronoUnit parseChronoUnit(final String str) throws ParsingException {
        return (ChronoUnit) Collection.EL.stream(this.patternsHolder.asMap().entrySet()).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.localization.TimeAgoParser$$ExternalSyntheticLambda1
            public /* synthetic */ Predicate and(Predicate predicate) {
                return Predicate$CC.$default$and(this, predicate);
            }

            public /* synthetic */ Predicate negate() {
                return Predicate$CC.$default$negate(this);
            }

            public /* synthetic */ Predicate or(Predicate predicate) {
                return Predicate$CC.$default$or(this, predicate);
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.m10958xa6114ce9(str, (Map.Entry) obj);
            }
        }).map(new Function() { // from class: org.schabi.newpipe.extractor.localization.TimeAgoParser$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return (ChronoUnit) ((Map.Entry) obj).getKey();
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).findFirst().orElseThrow(new Supplier() { // from class: org.schabi.newpipe.extractor.localization.TimeAgoParser$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return TimeAgoParser.lambda$parseChronoUnit$2(str);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$parseChronoUnit$1$org-schabi-newpipe-extractor-localization-TimeAgoParser, reason: not valid java name */
    /* synthetic */ boolean m10958xa6114ce9(final String str, Map.Entry entry) {
        return Collection.EL.stream((java.util.Collection) entry.getValue()).anyMatch(new Predicate() { // from class: org.schabi.newpipe.extractor.localization.TimeAgoParser$$ExternalSyntheticLambda0
            public /* synthetic */ Predicate and(Predicate predicate) {
                return Predicate$CC.$default$and(this, predicate);
            }

            public /* synthetic */ Predicate negate() {
                return Predicate$CC.$default$negate(this);
            }

            public /* synthetic */ Predicate or(Predicate predicate) {
                return Predicate$CC.$default$or(this, predicate);
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.m10957x807d43e8(str, (String) obj);
            }
        });
    }

    static /* synthetic */ ParsingException lambda$parseChronoUnit$2(String str) {
        return new ParsingException("Unable to parse the date: " + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: textualDateMatches, reason: merged with bridge method [inline-methods] */
    public boolean m10957x807d43e8(String str, String str2) {
        String strQuote;
        if (str.equals(str2)) {
            return true;
        }
        if (this.patternsHolder.wordSeparator().isEmpty()) {
            return str.toLowerCase().contains(str2.toLowerCase());
        }
        String strQuote2 = Pattern.quote(str2.toLowerCase());
        if (this.patternsHolder.wordSeparator().equals(Stream.ID_UNKNOWN)) {
            strQuote = "[ \\t\\xA0\\u1680\\u180e\\u2000-\\u200a\\u202f\\u205f\\u3000\\d]";
        } else {
            strQuote = Pattern.quote(this.patternsHolder.wordSeparator());
        }
        return Parser.isMatch("(^|" + strQuote + ")" + strQuote2 + "($|" + strQuote + ")", str.toLowerCase());
    }

    private DateWrapper getResultFor(int i, ChronoUnit chronoUnit) {
        LocalDateTime localDateTimeMinus;
        ChronoUnit chronoUnit2 = ChronoUnit.YEARS;
        LocalDateTime localDateTime = this.now;
        if (chronoUnit == chronoUnit2) {
            localDateTimeMinus = localDateTime.minusYears(i).minusDays(1L);
        } else {
            localDateTimeMinus = localDateTime.minus(i, (TemporalUnit) chronoUnit);
        }
        boolean zIsDateBased = chronoUnit.isDateBased();
        if (zIsDateBased) {
            localDateTimeMinus = localDateTimeMinus.truncatedTo(ChronoUnit.DAYS);
        }
        return new DateWrapper(localDateTimeMinus, zIsDateBased);
    }
}
