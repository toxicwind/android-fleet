package io.ktor.http.parsing.regex;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.http.parsing.AnyOfGrammar;
import io.ktor.http.parsing.AtLeastOne;
import io.ktor.http.parsing.ComplexGrammar;
import io.ktor.http.parsing.Grammar;
import io.ktor.http.parsing.ManyGrammar;
import io.ktor.http.parsing.MaybeGrammar;
import io.ktor.http.parsing.NamedGrammar;
import io.ktor.http.parsing.OrGrammar;
import io.ktor.http.parsing.Parser;
import io.ktor.http.parsing.RangeGrammar;
import io.ktor.http.parsing.RawGrammar;
import io.ktor.http.parsing.SimpleGrammar;
import io.ktor.http.parsing.StringGrammar;
import io.ktor.util.date.GMTDateParser;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: RegexParserGenerator.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010!\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a\u0013\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001aA\u0010\r\u001a\u00020\f*\u00020\u00002\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00042\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\r\u0010\u000e\u001a5\u0010\u0012\u001a\u00020\u0011*\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00042\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lio/ktor/http/parsing/Grammar;", "Lio/ktor/http/parsing/Parser;", "buildRegexParser", "(Lio/ktor/http/parsing/Grammar;)Lio/ktor/http/parsing/Parser;", "", "", "", "", "groups", TypedValues.CycleType.S_WAVE_OFFSET, "", "shouldGroup", "Lio/ktor/http/parsing/regex/GrammarRegex;", "toRegex", "(Lio/ktor/http/parsing/Grammar;Ljava/util/Map;IZ)Lio/ktor/http/parsing/regex/GrammarRegex;", "key", ES6Iterator.VALUE_PROPERTY, "", "add", "(Ljava/util/Map;Ljava/lang/String;I)V", "ktor-http"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class RegexParserGeneratorKt {
    public static final Parser buildRegexParser(Grammar grammar) {
        Intrinsics.checkNotNullParameter(grammar, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        return new RegexParser(new Regex(toRegex$default(grammar, linkedHashMap, 0, false, 6, null).getRegex()), linkedHashMap);
    }

    static /* synthetic */ GrammarRegex toRegex$default(Grammar grammar, Map map, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 1;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return toRegex(grammar, map, i, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final GrammarRegex toRegex(Grammar grammar, Map<String, List<Integer>> map, int i, boolean z) {
        char c;
        if (grammar instanceof StringGrammar) {
            return new GrammarRegex(Regex.INSTANCE.escape(((StringGrammar) grammar).getValue()), 0, false, 6, null);
        }
        if (grammar instanceof RawGrammar) {
            return new GrammarRegex(((RawGrammar) grammar).getValue(), 0, false, 6, null);
        }
        if (grammar instanceof NamedGrammar) {
            NamedGrammar namedGrammar = (NamedGrammar) grammar;
            GrammarRegex regex$default = toRegex$default(namedGrammar.getGrammar(), map, i + 1, false, 4, null);
            add(map, namedGrammar.getName(), i);
            return new GrammarRegex(regex$default.getRegex(), regex$default.getGroupsCount(), true);
        }
        if (grammar instanceof ComplexGrammar) {
            StringBuilder sb = new StringBuilder();
            int groupsCount = z ? i + 1 : i;
            int i2 = 0;
            for (Object obj : ((ComplexGrammar) grammar).getGrammars()) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                GrammarRegex regex = toRegex((Grammar) obj, map, groupsCount, true);
                if (i2 != 0 && (grammar instanceof OrGrammar)) {
                    sb.append("|");
                }
                sb.append(regex.getRegex());
                groupsCount += regex.getGroupsCount();
                i2 = i3;
            }
            int i4 = groupsCount - i;
            if (z) {
                i4--;
            }
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            return new GrammarRegex(string, i4, z);
        }
        if (grammar instanceof SimpleGrammar) {
            if (grammar instanceof MaybeGrammar) {
                c = '?';
            } else if (grammar instanceof ManyGrammar) {
                c = GMTDateParser.ANY;
            } else {
                if (!(grammar instanceof AtLeastOne)) {
                    throw new IllegalStateException(("Unsupported simple grammar element: " + grammar).toString());
                }
                c = '+';
            }
            GrammarRegex regex2 = toRegex(((SimpleGrammar) grammar).getGrammar(), map, i, true);
            return new GrammarRegex(regex2.getRegex() + c, regex2.getGroupsCount(), false, 4, null);
        }
        if (grammar instanceof AnyOfGrammar) {
            return new GrammarRegex("[" + Regex.INSTANCE.escape(((AnyOfGrammar) grammar).getValue()) + AbstractJsonLexerKt.END_LIST, 0, false, 6, null);
        }
        if (!(grammar instanceof RangeGrammar)) {
            throw new IllegalStateException(("Unsupported grammar element: " + grammar).toString());
        }
        StringBuilder sb2 = new StringBuilder("[");
        RangeGrammar rangeGrammar = (RangeGrammar) grammar;
        sb2.append(rangeGrammar.getFrom());
        sb2.append('-');
        sb2.append(rangeGrammar.getTo());
        sb2.append(AbstractJsonLexerKt.END_LIST);
        return new GrammarRegex(sb2.toString(), 0, false, 6, null);
    }

    private static final void add(Map<String, List<Integer>> map, String str, int i) {
        if (!map.containsKey(str)) {
            map.put(str, new ArrayList());
        }
        List<Integer> list = map.get(str);
        Intrinsics.checkNotNull(list);
        list.add(Integer.valueOf(i));
    }
}
